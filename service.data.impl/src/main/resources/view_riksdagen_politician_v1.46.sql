-- Drop and recreate VIEW_RIKSDAGEN_POLITICIAN with grouped committee leadership role tracking
-- Version: 1.46
-- Author: Intelligence Operative  
-- Date: 2025-12-22
--
-- Changes:
-- - Add total_committee_chair_assignments / current_committee_chair_assignments
-- - Add total_committee_vice_chair_all_assignments / current_committee_vice_chair_all_assignments
-- - Add total_suppleant_assignments / current_suppleant_assignments (14,757 assignments in data)
-- - Add total_statsminister_assignments / current_statsminister_assignments (10 assignments)
-- - Add total_party_leader_assignments / current_party_leader_assignments (Partiledare: 23, Tillförordnad partiledare: 1)
-- - Add total_party_secretary_assignments / current_party_secretary_assignments (Partisekreterare: 28, Tillförordnad partisekreterare: 2)
--
-- Total new columns: 12 (6 role categories × 2 columns each)

DROP VIEW IF EXISTS public.view_riksdagen_politician CASCADE;

CREATE VIEW public.view_riksdagen_politician AS
 SELECT base.person_id,
    base.first_name,
    base.last_name,
    base.party,
    base.born_year,
    base.gender,
    base.first_assignment_date,
    base.last_assignment_date,
    base.total_days_served,
    base.total_days_served_parliament,
    base.total_days_served_committee,
    base.total_days_served_government,
    base.total_days_served_eu,
    base.active,
    base.total_assignments,
    base.current_assignments,
    base.total_days_served_speaker,
    base.active_speaker,
    base.total_days_served_party,
    base.active_party,
    base.current_committee_assignments,
    base.current_ministry_assignments,
    base.current_party_assignments,
    base.current_speaker_assignments,
    base.total_committee_assignments,
    base.total_party_assignments,
    base.total_speaker_assignments,
    base.total_ministry_assignments,
    base.active_eu,
    base.active_government,
    base.active_committee,
    base.active_parliament,
    base.total_committee_substitute_assignments,
    base.current_committee_substitute_assignments,
    base.total_days_served_committee_substitute,
    base.total_committee_leadership_assignments,
    base.current_committee_leadership_assignments,
    base.total_days_served_committee_leadership,
    -- New grouped committee leadership columns
    base.total_committee_chair_assignments,
    base.current_committee_chair_assignments,
    base.total_committee_vice_chair_all_assignments,
    base.current_committee_vice_chair_all_assignments,
    -- New general role tracking columns
    base.total_suppleant_assignments,
    base.current_suppleant_assignments,
    base.total_statsminister_assignments,
    base.current_statsminister_assignments,
    base.total_party_leader_assignments,
    base.current_party_leader_assignments,
    base.total_party_secretary_assignments,
    base.current_party_secretary_assignments,
    COALESCE(ds.total_documents, (0)::bigint) AS total_documents,
    COALESCE(ds.party_motions, (0)::bigint) AS party_motions,
    COALESCE(ds.individual_motions, (0)::bigint) AS individual_motions,
    COALESCE(ds.committee_motions, (0)::bigint) AS committee_motions,
    COALESCE(ds.multi_party_motions, (0)::bigint) AS multi_party_motions,
    COALESCE(ds.follow_up_motions, (0)::bigint) AS follow_up_motions,
    COALESCE(ds.documents_last_year, (0)::bigint) AS documents_last_year,
    COALESCE(ds.years_active, 0) AS document_years_active,
    COALESCE(ds.docs_per_year, (0)::numeric) AS average_docs_per_year,
    COALESCE(ds.activity_level, 'Inactive'::text) AS doc_activity_level,
    COALESCE(ds.activity_profile, 'None'::text) AS doc_activity_profile,
    COALESCE(ds.collaboration_percentage, (0)::numeric) AS collaboration_percentage,
    ds.document_types,
    ds.first_document_date,
    ds.last_document_date
   FROM (( SELECT view_riksdagen_party_member.id AS person_id,
            max((view_riksdagen_party_member.first_name)::text) AS first_name,
            max((view_riksdagen_party_member.last_name)::text) AS last_name,
            max((view_riksdagen_party_member.party)::text) AS party,
            max(view_riksdagen_party_member.born_year) AS born_year,
            max((view_riksdagen_party_member.gender)::text) AS gender,
            COALESCE(min(assignment_data.from_date), CURRENT_DATE) AS first_assignment_date,
            COALESCE(max(assignment_data.to_date), CURRENT_DATE) AS last_assignment_date,
            sum(
                CASE
                    WHEN ((assignment_data.status)::text ~~ 'Ledig%'::text) THEN 0
                    ELSE (
                    CASE
                        WHEN (COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE COALESCE(assignment_data.to_date, CURRENT_DATE)
                    END -
                    CASE
                        WHEN (assignment_data.from_date >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE assignment_data.from_date
                    END)
                END) AS total_days_served,
            sum(
                CASE
                    WHEN (((assignment_data.assignment_type)::text = 'kammaruppdrag'::text) AND ((assignment_data.status)::text !~~ 'Ledig%'::text)) THEN (
                    CASE
                        WHEN (COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE COALESCE(assignment_data.to_date, CURRENT_DATE)
                    END -
                    CASE
                        WHEN (assignment_data.from_date > CURRENT_DATE) THEN CURRENT_DATE
                        ELSE assignment_data.from_date
                    END)
                    ELSE 0
                END) AS total_days_served_parliament,
            sum(
                CASE
                    WHEN ((assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text)) THEN (
                    CASE
                        WHEN (COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE COALESCE(assignment_data.to_date, CURRENT_DATE)
                    END -
                    CASE
                        WHEN (assignment_data.from_date >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE assignment_data.from_date
                    END)
                    ELSE 0
                END) AS total_days_served_committee,
            sum(
                CASE
                    WHEN (((assignment_data.role_code)::text ~~ '%MINISTER'::text) OR ((assignment_data.detail)::text ~~ '%departementet'::text) OR ((assignment_data.detail)::text = 'Statsrådsberedningen'::text)) THEN (
                    CASE
                        WHEN (COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE COALESCE(assignment_data.to_date, CURRENT_DATE)
                    END -
                    CASE
                        WHEN (assignment_data.from_date >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE assignment_data.from_date
                    END)
                    ELSE 0
                END) AS total_days_served_government,
            sum(
                CASE
                    WHEN ((assignment_data.detail)::text = 'Europaparlamentet'::text) THEN (
                    CASE
                        WHEN (COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE COALESCE(assignment_data.to_date, CURRENT_DATE)
                    END -
                    CASE
                        WHEN (assignment_data.from_date >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE assignment_data.from_date
                    END)
                    ELSE 0
                END) AS total_days_served_eu,
                CASE
                    WHEN (max(assignment_data.to_date) >= CURRENT_DATE) THEN true
                    ELSE false
                END AS active,
            count(*) AS total_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE)) THEN 1
                    ELSE 0
                END) AS current_assignments,
            sum(
                CASE
                    WHEN ((assignment_data.assignment_type)::text = 'talmansuppdrag'::text) THEN (
                    CASE
                        WHEN (COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE COALESCE(assignment_data.to_date, CURRENT_DATE)
                    END -
                    CASE
                        WHEN (assignment_data.from_date >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE assignment_data.from_date
                    END)
                    ELSE 0
                END) AS total_days_served_speaker,
                CASE
                    WHEN (sum(
                    CASE
                        WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND ((assignment_data.assignment_type)::text = 'talmansuppdrag'::text)) THEN 1
                        ELSE 0
                    END) > 0) THEN true
                    ELSE false
                END AS active_speaker,
            sum(
                CASE
                    WHEN ((assignment_data.assignment_type)::text = 'partiuppdrag'::text) THEN (
                    CASE
                        WHEN (COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE COALESCE(assignment_data.to_date, CURRENT_DATE)
                    END -
                    CASE
                        WHEN (assignment_data.from_date >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE assignment_data.from_date
                    END)
                    ELSE 0
                END) AS total_days_served_party,
                CASE
                    WHEN (sum(
                    CASE
                        WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND ((assignment_data.assignment_type)::text = 'partiuppdrag'::text)) THEN 1
                        ELSE 0
                    END) > 0) THEN true
                    ELSE false
                END AS active_party,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND (assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text)) THEN 1
                    ELSE 0
                END) AS current_committee_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND (((assignment_data.role_code)::text ~~ '%MINISTER'::text) OR ((assignment_data.detail)::text ~~ '%departementet'::text) OR ((assignment_data.detail)::text = 'Statsrådsberedningen'::text))) THEN 1
                    ELSE 0
                END) AS current_ministry_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND ((assignment_data.assignment_type)::text = 'partiuppdrag'::text)) THEN 1
                    ELSE 0
                END) AS current_party_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND ((assignment_data.assignment_type)::text = 'talmansuppdrag'::text)) THEN 1
                    ELSE 0
                END) AS current_speaker_assignments,
            sum(
                CASE
                    WHEN ((assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text)) THEN 1
                    ELSE 0
                END) AS total_committee_assignments,
            sum(
                CASE
                    WHEN ((assignment_data.assignment_type)::text = 'partiuppdrag'::text) THEN 1
                    ELSE 0
                END) AS total_party_assignments,
            sum(
                CASE
                    WHEN ((assignment_data.assignment_type)::text = 'talmansuppdrag'::text) THEN 1
                    ELSE 0
                END) AS total_speaker_assignments,
            sum(
                CASE
                    WHEN (((assignment_data.role_code)::text ~~ '%MINISTER'::text) OR ((assignment_data.detail)::text ~~ '%departementet'::text) OR ((assignment_data.detail)::text = 'Statsrådsberedningen'::text)) THEN 1
                    ELSE 0
                END) AS total_ministry_assignments,
                CASE
                    WHEN (sum(
                    CASE
                        WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND ((assignment_data.detail)::text = 'Europaparlamentet'::text)) THEN 1
                        ELSE 0
                    END) > 0) THEN true
                    ELSE false
                END AS active_eu,
                CASE
                    WHEN (sum(
                    CASE
                        WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND (((assignment_data.role_code)::text ~~ '%MINISTER'::text) OR ((assignment_data.detail)::text ~~ '%departementet'::text) OR ((assignment_data.detail)::text = 'Statsrådsberedningen'::text))) THEN 1
                        ELSE 0
                    END) > 0) THEN true
                    ELSE false
                END AS active_government,
                CASE
                    WHEN (sum(
                    CASE
                        WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND (assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text)) THEN 1
                        ELSE 0
                    END) > 0) THEN true
                    ELSE false
                END AS active_committee,
                CASE
                    WHEN (sum(
                    CASE
                        WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND ((assignment_data.assignment_type)::text = 'kammaruppdrag'::text) AND ((assignment_data.status)::text !~~ 'Ledig%'::text)) THEN 1
                        ELSE 0
                    END) > 0) THEN true
                    ELSE false
                END AS active_parliament,
            sum(
                CASE
                    WHEN ((assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text) AND (((assignment_data.role_code)::text ~~* '%suppleant%'::text) OR ((assignment_data.role_code)::text ~~* '%ersättare%'::text))) THEN 1
                    ELSE 0
                END) AS total_committee_substitute_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND (assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text) AND (((assignment_data.role_code)::text ~~* '%suppleant%'::text) OR ((assignment_data.role_code)::text ~~* '%ersättare%'::text))) THEN 1
                    ELSE 0
                END) AS current_committee_substitute_assignments,
            sum(
                CASE
                    WHEN ((assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text) AND (((assignment_data.role_code)::text ~~* '%suppleant%'::text) OR ((assignment_data.role_code)::text ~~* '%ersättare%'::text))) THEN (
                    CASE
                        WHEN (COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE COALESCE(assignment_data.to_date, CURRENT_DATE)
                    END -
                    CASE
                        WHEN (assignment_data.from_date >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE assignment_data.from_date
                    END)
                    ELSE 0
                END) AS total_days_served_committee_substitute,
            sum(
                CASE
                    WHEN ((assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text) AND (((assignment_data.role_code)::text ~~* '%ordförande%'::text) OR ((assignment_data.role_code)::text ~~* '%vice ordförande%'::text))) THEN 1
                    ELSE 0
                END) AS total_committee_leadership_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) AND (assignment_data.from_date <= CURRENT_DATE) AND (assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text) AND (((assignment_data.role_code)::text ~~* '%ordförande%'::text) OR ((assignment_data.role_code)::text ~~* '%vice ordförande%'::text))) THEN 1
                    ELSE 0
                END) AS current_committee_leadership_assignments,
            sum(
                CASE
                    WHEN ((assignment_data.org_code IS NOT NULL) AND ((assignment_data.assignment_type)::text = 'uppdrag'::text) AND (((assignment_data.role_code)::text ~~* '%ordförande%'::text) OR ((assignment_data.role_code)::text ~~* '%vice ordförande%'::text))) THEN (
                    CASE
                        WHEN (COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE COALESCE(assignment_data.to_date, CURRENT_DATE)
                    END -
                    CASE
                        WHEN (assignment_data.from_date >= CURRENT_DATE) THEN CURRENT_DATE
                        ELSE assignment_data.from_date
                    END)
                    ELSE 0
                END) AS total_days_served_committee_leadership,
            -- New: Committee Chair assignments (Ordförande only)
            sum(
                CASE
                    WHEN ((assignment_data.role_code)::text = 'Ordförande'::text) THEN 1
                    ELSE 0
                END) AS total_committee_chair_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) 
                      AND (assignment_data.from_date <= CURRENT_DATE)
                      AND ((assignment_data.role_code)::text = 'Ordförande'::text)) THEN 1
                    ELSE 0
                END) AS current_committee_chair_assignments,
            -- New: Vice Chair assignments (ALL LEVELS GROUPED: Vice, Förste, Andre, Tredje)
            sum(
                CASE
                    WHEN ((assignment_data.role_code)::text IN ('Vice ordförande', 'Förste vice ordförande', 'Andre vice ordförande', 'Tredje vice ordförande')) THEN 1
                    ELSE 0
                END) AS total_committee_vice_chair_all_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) 
                      AND (assignment_data.from_date <= CURRENT_DATE)
                      AND ((assignment_data.role_code)::text IN ('Vice ordförande', 'Förste vice ordförande', 'Andre vice ordförande', 'Tredje vice ordförande'))) THEN 1
                    ELSE 0
                END) AS current_committee_vice_chair_all_assignments,
            -- New: Suppleant assignments (any assignment with Suppleant in role_code, not limited to committee)
            sum(
                CASE
                    WHEN ((assignment_data.role_code)::text = 'Suppleant'::text) THEN 1
                    ELSE 0
                END) AS total_suppleant_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) 
                      AND (assignment_data.from_date <= CURRENT_DATE)
                      AND ((assignment_data.role_code)::text = 'Suppleant'::text)) THEN 1
                    ELSE 0
                END) AS current_suppleant_assignments,
            -- New: Statsminister assignments (Prime Minister)
            sum(
                CASE
                    WHEN ((assignment_data.role_code)::text = 'Statsminister'::text) THEN 1
                    ELSE 0
                END) AS total_statsminister_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) 
                      AND (assignment_data.from_date <= CURRENT_DATE)
                      AND ((assignment_data.role_code)::text = 'Statsminister'::text)) THEN 1
                    ELSE 0
                END) AS current_statsminister_assignments,
            -- New: Party Leader assignments (Partiledare + Tillförordnad partiledare)
            sum(
                CASE
                    WHEN ((assignment_data.role_code)::text IN ('Partiledare', 'Tillförordnad partiledare')) THEN 1
                    ELSE 0
                END) AS total_party_leader_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) 
                      AND (assignment_data.from_date <= CURRENT_DATE)
                      AND ((assignment_data.role_code)::text IN ('Partiledare', 'Tillförordnad partiledare'))) THEN 1
                    ELSE 0
                END) AS current_party_leader_assignments,
            -- New: Party Secretary assignments (Partisekreterare + Tillförordnad partisekreterare)
            sum(
                CASE
                    WHEN ((assignment_data.role_code)::text IN ('Partisekreterare', 'Tillförordnad partisekreterare')) THEN 1
                    ELSE 0
                END) AS total_party_secretary_assignments,
            sum(
                CASE
                    WHEN ((COALESCE(assignment_data.to_date, CURRENT_DATE) >= CURRENT_DATE) 
                      AND (assignment_data.from_date <= CURRENT_DATE)
                      AND ((assignment_data.role_code)::text IN ('Partisekreterare', 'Tillförordnad partisekreterare'))) THEN 1
                    ELSE 0
                END) AS current_party_secretary_assignments
           FROM (public.assignment_data
             LEFT JOIN public.view_riksdagen_party_member ON (((assignment_data.intressent_id)::text = (view_riksdagen_party_member.id)::text)))
          GROUP BY view_riksdagen_party_member.id) base
     LEFT JOIN public.view_riksdagen_politician_document_summary ds ON (((base.person_id)::text = (ds.person_reference_id)::text)));

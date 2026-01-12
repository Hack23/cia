-- Drop and recreate MATERIALIZED VIEW VIEW_RIKSDAGEN_POLITICIAN_DOCUMENT_SUMMARY
-- Version: 1.51
-- Author: Intelligence Operative
-- Date: 2026-02-12
--
-- Changes:
-- - Add total_questions (document_type = 'fr')
-- - Add total_interpellations (document_type = 'ip')
-- - Ensure 'propositions' is preserved
--
-- Note: Dropping a materialized view with CASCADE will drop dependent views (view_riksdagen_politician)

DROP MATERIALIZED VIEW IF EXISTS public.view_riksdagen_politician_document_summary CASCADE;

CREATE MATERIALIZED VIEW public.view_riksdagen_politician_document_summary AS
 SELECT person_reference_id,
    count(*) AS total_documents,
    count(
        CASE
            WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Partimotion'::text)) THEN 1
            ELSE NULL::integer
        END) AS party_motions,
    count(
        CASE
            WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Enskild motion'::text)) THEN 1
            ELSE NULL::integer
        END) AS individual_motions,
    count(
        CASE
            WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Följdmotion'::text)) THEN 1
            ELSE NULL::integer
        END) AS follow_up_motions,
    count(
        CASE
            WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Kommittémotion'::text)) THEN 1
            ELSE NULL::integer
        END) AS committee_motions,
    count(
        CASE
            WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Flerpartimotion'::text)) THEN 1
            ELSE NULL::integer
        END) AS multi_party_motions,
    round(((100.0 * (count(
        CASE
            WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Partimotion'::text)) THEN 1
            ELSE NULL::integer
        END))::numeric) / (NULLIF(count(*), 0))::numeric), 1) AS party_motions_pct,
    round(((100.0 * (count(
        CASE
            WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Enskild motion'::text)) THEN 1
            ELSE NULL::integer
        END))::numeric) / (NULLIF(count(*), 0))::numeric), 1) AS individual_motions_pct,
    round(((100.0 * (count(
        CASE
            WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Kommittémotion'::text)) THEN 1
            ELSE NULL::integer
        END))::numeric) / (NULLIF(count(*), 0))::numeric), 1) AS committee_motions_pct,
    min(made_public_date) AS first_document_date,
    max(made_public_date) AS last_document_date,
    (EXTRACT(year FROM age((max(made_public_date))::timestamp with time zone, (min(made_public_date))::timestamp with time zone)))::integer AS years_active,
    round(((count(*))::numeric / NULLIF(EXTRACT(year FROM age((max(made_public_date))::timestamp with time zone, (min(made_public_date))::timestamp with time zone)), (0)::numeric)), 1) AS docs_per_year,
    count(
        CASE
            WHEN (made_public_date >= (CURRENT_DATE - '1 year'::interval)) THEN 1
            ELSE NULL::integer
        END) AS documents_last_year,
    count(
        CASE
            WHEN ((document_type)::text = 'prop'::text) THEN 1
            ELSE NULL::integer
        END) AS propositions,
    count(
        CASE
            WHEN (((document_type)::text = 'prop'::text) AND ((sub_type)::text = 'skr'::text)) THEN 1
            ELSE NULL::integer
        END) AS government_communications,
    count(
        CASE
            WHEN ((document_type)::text = 'fr'::text) THEN 1
            ELSE NULL::integer
        END) AS total_questions,
    count(
        CASE
            WHEN ((document_type)::text = 'ip'::text) THEN 1
            ELSE NULL::integer
        END) AS total_interpellations,
    array_agg(DISTINCT (((document_type)::text || ':'::text) || (COALESCE(sub_type, ''::character varying))::text)) AS document_types,
        CASE
            WHEN (count(
            CASE
                WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Partimotion'::text)) THEN 1
                ELSE NULL::integer
            END) > count(
            CASE
                WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Enskild motion'::text)) THEN 1
                ELSE NULL::integer
            END)) THEN 'Party-focused'::text
            WHEN (count(
            CASE
                WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Kommittémotion'::text)) THEN 1
                ELSE NULL::integer
            END) > count(
            CASE
                WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Enskild motion'::text)) THEN 1
                ELSE NULL::integer
            END)) THEN 'Committee-focused'::text
            WHEN (count(
            CASE
                WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Enskild motion'::text)) THEN 1
                ELSE NULL::integer
            END) > 0) THEN 'Individual-focused'::text
            ELSE 'Mixed'::text
        END AS activity_profile,
        CASE
            WHEN (count(*) > 200) THEN 'Very High'::text
            WHEN (count(*) > 100) THEN 'High'::text
            WHEN (count(*) > 50) THEN 'Medium'::text
            WHEN (count(*) > 0) THEN 'Low'::text
            ELSE 'Inactive'::text
        END AS activity_level,
    round(((100.0 * (count(
        CASE
            WHEN (((document_type)::text = 'mot'::text) AND ((sub_type)::text = 'Flerpartimotion'::text)) THEN 1
            ELSE NULL::integer
        END))::numeric) / (NULLIF(count(
        CASE
            WHEN ((document_type)::text = 'mot'::text) THEN 1
            ELSE NULL::integer
        END), 0))::numeric), 1) AS collaboration_percentage
   FROM public.view_riksdagen_politician_document
  GROUP BY person_reference_id
  WITH NO DATA;

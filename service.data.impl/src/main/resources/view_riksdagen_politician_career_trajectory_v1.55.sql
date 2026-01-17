DROP VIEW IF EXISTS view_riksdagen_politician_career_trajectory CASCADE;

CREATE VIEW view_riksdagen_politician_career_trajectory AS
WITH career_cycles AS (
    SELECT 
        p.id AS person_id,
        p.first_name,
        p.last_name,
        p.party,
        CASE 
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2002 AND 2005 THEN 2002
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2006 AND 2009 THEN 2006
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2010 AND 2013 THEN 2010
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2014 AND 2017 THEN 2014
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2018 AND 2021 THEN 2018
            WHEN EXTRACT(YEAR FROM vd.vote_date) BETWEEN 2022 AND 2025 THEN 2022
            ELSE 2026
        END AS election_year,
        MIN(vd.vote_date) AS first_vote,
        MAX(vd.vote_date) AS last_vote,
        COUNT(*) AS ballot_count,
        -- Attendance rate: percentage of votes where politician was not absent
        ROUND(AVG(CASE WHEN vd.vote != 'Frånvarande' THEN 1 ELSE 0 END) * 100, 2) AS attendance_rate,
        -- Win rate: percentage of votes where politician voted with winning side
        -- Using party won status from ballot summary as proxy for individual win
        ROUND(AVG(CASE 
            WHEN ps.party_won THEN 1 
            ELSE 0 
        END) * 100, 2) AS win_rate,
        -- Count leadership assignments during this election cycle
        COUNT(DISTINCT ad.hjid) FILTER (
            WHERE ad.role_code IN ('Ordförande', 'Vice ordförande', 'Talman', 
                                   'Statsminister', 'Vice statsminister', 'Partiledare', 'Gruppledare')
        ) AS leadership_roles,
        -- Count documents where politician is referenced (approximate metric)
        COUNT(DISTINCT dpr.person_reference_id) AS documents_authored
    FROM person_data p
    JOIN vote_data vd ON p.id = vd.embedded_id_intressent_id
    LEFT JOIN view_riksdagen_vote_data_ballot_party_summary ps 
        ON vd.embedded_id_ballot_id = ps.embedded_id_ballot_id 
        AND vd.party = ps.embedded_id_party
    LEFT JOIN assignment_data ad 
        ON p.id = ad.intressent_id
    LEFT JOIN document_person_reference_da_0 dpr 
        ON dpr.person_reference_id = p.id
    WHERE vd.vote_date IS NOT NULL
    GROUP BY p.id, p.first_name, p.last_name, p.party, election_year
),
career_metrics AS (
    SELECT 
        person_id, 
        first_name, 
        last_name, 
        party, 
        election_year,
        ballot_count, 
        attendance_rate, 
        win_rate, 
        leadership_roles,
        documents_authored,
        ROW_NUMBER() OVER (PARTITION BY person_id ORDER BY election_year) AS career_cycle_number,
        COUNT(*) OVER (PARTITION BY person_id) AS total_cycles,
        MIN(election_year) OVER (PARTITION BY person_id) AS career_start_year,
        MAX(election_year) OVER (PARTITION BY person_id) AS career_end_year,
        AVG(attendance_rate) OVER (PARTITION BY person_id) AS avg_career_attendance,
        LAG(attendance_rate) OVER (PARTITION BY person_id ORDER BY election_year) AS prev_attendance_rate
    FROM career_cycles
)
SELECT 
    person_id, 
    first_name, 
    last_name, 
    party, 
    election_year,
    career_cycle_number, 
    total_cycles,
    career_start_year, 
    career_end_year,
    ballot_count, 
    attendance_rate, 
    win_rate, 
    leadership_roles,
    documents_authored,
    avg_career_attendance,
    ROUND(attendance_rate - avg_career_attendance, 2) AS performance_vs_baseline,
    CASE 
        WHEN career_cycle_number = 1 THEN 'EARLY_CAREER'
        WHEN career_cycle_number::FLOAT / total_cycles < 0.5 THEN 'MID_CAREER'
        ELSE 'LATE_CAREER'
    END AS career_stage,
    CASE 
        WHEN prev_attendance_rate IS NULL THEN 'NEW_ENTRY'
        WHEN attendance_rate > prev_attendance_rate + 5 THEN 'IMPROVING'
        WHEN attendance_rate < prev_attendance_rate - 5 THEN 'DECLINING'
        ELSE 'STABLE'
    END AS performance_trend,
    CASE
        WHEN attendance_rate > avg_career_attendance + 10 AND career_cycle_number::FLOAT / total_cycles > 0.6 
            THEN 'PEAK_PERFORMANCE'
        WHEN attendance_rate < avg_career_attendance - 10 AND career_cycle_number::FLOAT / total_cycles > 0.6 
            THEN 'LATE_CAREER_DECLINE'
        WHEN attendance_rate > avg_career_attendance + 5 AND career_cycle_number <= 2 
            THEN 'RISING_STAR'
        WHEN attendance_rate < 70 AND career_cycle_number <= 2 
            THEN 'STRUGGLING_NEWCOMER'
        ELSE 'CONSISTENT'
    END AS career_pattern
FROM career_metrics
ORDER BY person_id, election_year;

COMMENT ON VIEW view_riksdagen_politician_career_trajectory IS 
'Career trajectory analysis tracking politician performance across election cycles (2002-2026).
Provides metrics for attendance rates, win rates, leadership roles, and documents authored per cycle.
Classifies career stages (early/mid/late), performance trends (improving/declining/stable), 
and career patterns (peak/decline/rising star). Used for Predictive Intelligence Framework (Framework 4)
to forecast career trajectories and resignation risks.';

--
-- PostgreSQL database dump
--

\restrict o4oMvF3Qo0AIvwVfBvJWsr6BFKO2U0crt7fGIY3cDxTck7YgqpeRhUiHjTlqKei

-- Dumped from database version 16.11 (Ubuntu 16.11-1.pgdg24.04+1)
-- Dumped by pg_dump version 16.11 (Ubuntu 16.11-1.pgdg24.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


SET default_table_access_method = heap;

--
-- Name: against_proposal_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.against_proposal_container (
    hjid bigint NOT NULL
);


--
-- Name: against_proposal_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.against_proposal_data (
    hjid bigint NOT NULL,
    header character varying(255),
    number_value numeric,
    parties character varying(255),
    proposal_issue_number numeric,
    proposal_type character varying(255),
    against_proposal_list_agains_0 bigint
);


--
-- Name: agency; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.agency (
    hjid bigint NOT NULL,
    agency_name character varying(255),
    description character varying(255),
    model_object_id integer,
    model_object_version integer
);


--
-- Name: application_action_event; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.application_action_event (
    hjid bigint NOT NULL,
    application_operation character varying(255),
    created_date timestamp without time zone,
    event_group character varying(255),
    model_object_id integer,
    model_object_version integer,
    events_application_session_h_0 bigint,
    session_id character varying(255),
    page character varying(255),
    page_mode character varying(255),
    element_id character varying(255),
    action_name character varying(255),
    user_id character varying(255),
    error_message character varying(8192),
    application_message character varying(255)
);


--
-- Name: application_configuration; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.application_configuration (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer,
    config_title character varying(255),
    config_description character varying(255),
    configuration_group character varying(255),
    component character varying(255),
    component_title character varying(255),
    component_description character varying(255),
    property_id character varying(255),
    property_value character varying(255),
    created_date timestamp without time zone,
    updated_date timestamp without time zone
);


--
-- Name: application_session; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.application_session (
    hjid bigint NOT NULL,
    created_date timestamp without time zone,
    ip_information character varying(255),
    model_object_id integer,
    model_object_version integer,
    session_type character varying(255),
    user_agent_information character varying(255),
    session_id character varying(255),
    locale character varying(255),
    operating_system character varying(255),
    user_id character varying(255),
    destroyed_date timestamp without time zone,
    screen_size character varying(255),
    time_zone character varying(255)
);


--
-- Name: application_view; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.application_view (
    hjid bigint NOT NULL,
    perspective character varying(255),
    data_source_information_appl_0 bigint,
    operational_information_appl_0 bigint,
    performance_indicators_appli_0 bigint,
    quality_assurance_applicatio_0 bigint,
    target_profile_application_v_0 bigint
);


--
-- Name: assignment_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.assignment_data (
    hjid bigint NOT NULL,
    assignment_type character varying(255),
    detail character varying(65536),
    from_date date,
    intressent_id character varying(255),
    order_number character varying(255),
    org_code character varying(255),
    role_code character varying(255),
    status character varying(255),
    to_date date,
    assignment_list_person_assig_0 bigint
);


--
-- Name: assignment_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.assignment_element (
    hjid bigint NOT NULL,
    assignment_type character varying(255),
    detail character varying(65536),
    from_date date,
    intressent_id character varying(255),
    order_number character varying(255),
    org_code character varying(255),
    role_code character varying(255),
    status character varying(255),
    to_date date,
    uppdrag_person_assignment_el_0 bigint
);


--
-- Name: committee_document_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.committee_document_data (
    id character varying(255) NOT NULL,
    committee_proposal_url_xml character varying(255),
    created_date date,
    document_status_url_www character varying(255),
    document_status_url_xml character varying(255),
    document_url_html character varying(255),
    document_url_text character varying(255),
    end_number numeric,
    hangar_id numeric,
    label character varying(255),
    org character varying(255),
    public_date date,
    rm character varying(255),
    status character varying(255),
    sub_title text,
    sub_type character varying(255),
    temp_label text,
    title text
);


--
-- Name: committee_proposal_component_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.committee_proposal_component_0 (
    hjid bigint NOT NULL,
    against_proposal_container_c_0 bigint,
    committee_proposal_container_0 bigint,
    document_committee_proposal__0 character varying(255)
);


--
-- Name: committee_proposal_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.committee_proposal_container (
    hjid bigint NOT NULL
);


--
-- Name: committee_proposal_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.committee_proposal_data (
    hjid bigint NOT NULL,
    against_proposal_number numeric,
    against_proposal_parties character varying(255),
    ballot_id character varying(255),
    ballot_summary_item text,
    ballot_url_xml character varying(255),
    committee_report character varying(255),
    decision_type character varying(255),
    header character varying(255),
    issue_number numeric,
    proposal text,
    rm character varying(255),
    winner character varying(255),
    committee_proposal_list_comm_0 bigint
);


--
-- Name: countries_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.countries_element (
    hjid bigint NOT NULL,
    page numeric,
    pages numeric,
    per_page numeric,
    total numeric
);


--
-- Name: country_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.country_element (
    hjid bigint NOT NULL,
    adminregion_id character varying(255),
    adminregion_value character varying(255),
    capital_city character varying(255),
    country_name character varying(255),
    id character varying(255),
    income_level_id character varying(255),
    income_level_value character varying(255),
    iso_2code character varying(255),
    latitude character varying(255),
    lending_type_id character varying(255),
    lending_type_value character varying(255),
    longitude character varying(255),
    region_id character varying(255),
    region_value character varying(255),
    country_countries_element_hj_0 bigint
);


--
-- Name: data_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.data_element (
    hjid bigint NOT NULL,
    page numeric,
    pages numeric,
    per_page numeric,
    total numeric
);


--
-- Name: data_source_content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.data_source_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: detail_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.detail_data (
    hjid bigint NOT NULL,
    code character varying(255),
    detail character varying(65536),
    detail_type character varying(255),
    intressent_id character varying(255),
    detail_list_person_detail_da_0 bigint
);


--
-- Name: detail_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.detail_element (
    hjid bigint NOT NULL,
    code character varying(255),
    detail character varying(65536),
    detail_type character varying(255),
    intressent_id character varying(255)
);


--
-- Name: document_activity_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_activity_container (
    hjid bigint NOT NULL
);


--
-- Name: document_activity_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_activity_data (
    hjid bigint NOT NULL,
    activity_name character varying(255),
    code character varying(255),
    created_date date,
    order_number numeric,
    process character varying(255),
    status character varying(255),
    document_activities_document_0 bigint
);


--
-- Name: document_attachment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_attachment (
    hjid bigint NOT NULL,
    file_name character varying(255),
    file_size numeric,
    file_type character varying(255),
    file_url character varying(255),
    document_attachment_list_doc_0 bigint
);


--
-- Name: document_attachment_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_attachment_container (
    hjid bigint NOT NULL
);


--
-- Name: document_container_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_container_element (
    hjid bigint NOT NULL,
    created character varying(255),
    datum character varying(255),
    debug character varying(255),
    document_version character varying(255),
    hits numeric,
    hits_from numeric,
    hits_to numeric,
    next_page character varying(255),
    page numeric,
    total_pages numeric,
    warning character varying(255)
);


--
-- Name: document_content_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_content_data (
    hjid bigint NOT NULL,
    content text,
    id character varying(255)
);


--
-- Name: document_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_data (
    id character varying(255) NOT NULL,
    committee_report_url_xml character varying(255),
    document_status_url_www character varying(255),
    document_status_url_xml character varying(255),
    document_type character varying(255),
    document_url_html character varying(255),
    document_url_text character varying(255),
    final_number numeric,
    hangar_id character varying(255),
    label character varying(255),
    made_public_date date,
    number_value numeric,
    org character varying(255),
    rm character varying(255),
    status character varying(255),
    sub_title character varying(255),
    sub_type character varying(255),
    temp_label character varying(255),
    title character varying(65536)
);


--
-- Name: document_detail_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_detail_container (
    hjid bigint NOT NULL
);


--
-- Name: document_detail_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_detail_data (
    hjid bigint NOT NULL,
    code character varying(255),
    detail_name character varying(255),
    text character varying(10485760),
    document_detail_list_documen_0 bigint
);


--
-- Name: document_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_element (
    id character varying(255) NOT NULL,
    committee_report_url_xml character varying(255),
    created_date character varying(255),
    document_format character varying(255),
    document_status_url_xml character varying(255),
    document_type character varying(255),
    document_url_html character varying(255),
    document_url_text character varying(255),
    hit numeric,
    kall_id character varying(255),
    label character varying(255),
    made_public_date character varying(255),
    number_value numeric,
    org character varying(255),
    related_id character varying(255),
    rm character varying(255),
    status character varying(255),
    sub_title character varying(65536),
    sub_type character varying(255),
    system_date character varying(255),
    temp_label character varying(255),
    title character varying(65536),
    dokument_document_container__0 bigint,
    domain_org character varying(255),
    database_source character varying(255),
    origin character varying(255),
    lang character varying(255),
    summary character varying(65536),
    note character varying(65536),
    note_title character varying(16384),
    debate_name character varying(255),
    document_name character varying(255),
    doc_type character varying(255)
);


--
-- Name: document_person_reference_co_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_person_reference_co_0 (
    hjid bigint NOT NULL
);


--
-- Name: document_person_reference_da_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_person_reference_da_0 (
    hjid bigint NOT NULL,
    order_number integer,
    party_short_code character varying(255),
    person_reference_id character varying(255),
    reference_name character varying(255),
    role_description character varying(255),
    document_person_reference_li_1 bigint
);


--
-- Name: document_proposal_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_proposal_container (
    hjid bigint NOT NULL,
    proposal_document_proposal_c_0 bigint
);


--
-- Name: document_proposal_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_proposal_data (
    hjid bigint NOT NULL,
    chamber character varying(255),
    committee character varying(255),
    decision_type character varying(255),
    designation integer,
    processed_in character varying(255),
    proposal_number integer,
    wording text,
    wording_2 text,
    wording_3 text,
    wording_4 text
);


--
-- Name: document_reference_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_reference_container (
    hjid bigint NOT NULL
);


--
-- Name: document_reference_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_reference_data (
    hjid bigint NOT NULL,
    detail character varying(65536),
    reference_document_id character varying(255),
    reference_type character varying(255),
    document_reference_list_docu_0 bigint
);


--
-- Name: document_status_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_status_container (
    hjid bigint NOT NULL,
    document_category character varying(255),
    document_document_status_con_0 character varying(255),
    document_activity_container__0 bigint,
    document_attachment_containe_0 bigint,
    document_detail_container_do_0 bigint,
    document_person_reference_co_1 bigint,
    document_reference_container_0 bigint,
    document_proposal_document_s_0 bigint
);


--
-- Name: domain_portal; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.domain_portal (
    domain_name character varying(255),
    hjid bigint NOT NULL
);


--
-- Name: encrypted_value; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.encrypted_value (
    id bigint NOT NULL,
    storage character varying(4096),
    user_id character varying(255),
    vault_name character varying(255)
);


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: indicator_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.indicator_element (
    hjid bigint NOT NULL,
    id character varying(255),
    indicator_name character varying(2048),
    source_id character varying(2048),
    source_value character varying(2048),
    source_note character varying(65536),
    source_organization character varying(65536),
    topics_indicator_element_hjid bigint,
    indicator__indicators_elemen_0 bigint
);


--
-- Name: indicators_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.indicators_element (
    hjid bigint NOT NULL,
    page numeric,
    pages numeric,
    per_page numeric,
    total numeric
);


--
-- Name: jv_commit; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.jv_commit (
    commit_pk bigint NOT NULL,
    author character varying(200),
    commit_date timestamp without time zone,
    commit_id numeric(22,2),
    commit_date_instant character varying(200) DEFAULT '2000-01-01T12:00:00.001Z'::character varying
);


--
-- Name: jv_commit_pk_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.jv_commit_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jv_commit_property; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.jv_commit_property (
    property_name character varying(191) NOT NULL,
    property_value character varying(600),
    commit_fk bigint NOT NULL
);


--
-- Name: jv_global_id; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.jv_global_id (
    global_id_pk bigint NOT NULL,
    local_id character varying(191),
    fragment character varying(200),
    type_name character varying(200),
    owner_id_fk bigint
);


--
-- Name: jv_global_id_pk_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.jv_global_id_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jv_snapshot; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.jv_snapshot (
    snapshot_pk bigint NOT NULL,
    type character varying(200),
    version bigint,
    state text,
    changed_properties text,
    managed_type character varying(200),
    global_id_fk bigint,
    commit_fk bigint
);


--
-- Name: jv_snapshot_pk_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.jv_snapshot_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: language_content_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.language_content_data (
    hjid bigint NOT NULL,
    created_date timestamp without time zone,
    from_language character varying(255),
    language_content_type character varying(255),
    language_value character varying(10485760),
    model_object_id integer,
    model_object_version integer,
    ref_key character varying(10485760),
    to_language character varying(255),
    last_modified_date timestamp without time zone,
    key_group character varying(255),
    location_context character varying(255)
);


--
-- Name: language_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.language_data (
    hjid bigint NOT NULL,
    language_name character varying(255),
    model_object_id integer,
    model_object_version integer,
    created_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    language_enabled boolean,
    auto_translation_enabled boolean,
    language_code character varying(255),
    translation_status character varying(255)
);


--
-- Name: operational_information_cont_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.operational_information_cont_0 (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: performance_indicator_content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.performance_indicator_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: person_assignment_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_assignment_data (
    hjid bigint NOT NULL
);


--
-- Name: person_assignment_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_assignment_element (
    hjid bigint NOT NULL
);


--
-- Name: person_container_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_container_data (
    hjid bigint NOT NULL,
    person_person_container_data_0 character varying(255)
);


--
-- Name: person_container_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_container_element (
    hjid bigint NOT NULL
);


--
-- Name: person_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_data (
    id character varying(255) NOT NULL,
    born_year integer,
    election_region character varying(255),
    first_name character varying(255),
    gender character varying(255),
    hangar_guid character varying(255),
    image_url_192 character varying(255),
    image_url_80 character varying(255),
    image_url_max character varying(255),
    last_name character varying(255),
    party character varying(255),
    person_url_xml character varying(255),
    place character varying(255),
    status character varying(255),
    person_assignment_data_perso_0 bigint,
    person_detail_data_person_da_0 bigint
);


--
-- Name: person_detail_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_detail_data (
    hjid bigint NOT NULL
);


--
-- Name: person_detail_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_detail_element (
    hjid bigint NOT NULL,
    detail_list_person_detail_el_0 bigint
);


--
-- Name: person_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_element (
    id character varying(255) NOT NULL,
    born_year character varying(255),
    election_region character varying(255),
    first_name character varying(255),
    gender character varying(255),
    hangar_guid character varying(255),
    image_url_192 character varying(255),
    image_url_80 character varying(255),
    image_url_max character varying(255),
    last_name character varying(255),
    party character varying(255),
    person_url_xml character varying(255),
    place character varying(255),
    status character varying(255),
    person_assignment_element_pe_0 bigint,
    person_detail_element_person_0 bigint,
    person_person_container_elem_0 bigint
);


--
-- Name: portal; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.portal (
    hjid bigint NOT NULL,
    description character varying(255),
    google_map_api_key character varying(255),
    model_object_id integer,
    model_object_version integer,
    portal_name character varying(255),
    portal_type character varying(255),
    portals_agency_hjid bigint
);


--
-- Name: qrtz_blob_triggers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_blob_triggers (
    sched_name character varying(100) NOT NULL,
    trigger_name character varying(150) NOT NULL,
    trigger_group character varying(150) NOT NULL,
    job_data bytea
);


--
-- Name: qrtz_calendars; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_calendars (
    sched_name character varying(100) NOT NULL,
    calendar_name character varying(200) NOT NULL,
    calendar bytea NOT NULL
);


--
-- Name: qrtz_cron_triggers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_cron_triggers (
    sched_name character varying(100) NOT NULL,
    trigger_name character varying(150) NOT NULL,
    trigger_group character varying(150) NOT NULL,
    cron_expression character varying(120) NOT NULL,
    time_zone_id character varying(80)
);


--
-- Name: qrtz_fired_triggers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_fired_triggers (
    sched_name character varying(100) NOT NULL,
    entry_id character varying(95) NOT NULL,
    trigger_name character varying(150) NOT NULL,
    trigger_group character varying(150) NOT NULL,
    instance_name character varying(200) NOT NULL,
    fired_time bigint NOT NULL,
    sched_time bigint NOT NULL,
    priority integer NOT NULL,
    state character varying(16) NOT NULL,
    job_name character varying(150),
    job_group character varying(150),
    is_nonconcurrent boolean,
    requests_recovery boolean
);


--
-- Name: qrtz_job_details; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_job_details (
    sched_name character varying(100) NOT NULL,
    job_name character varying(150) NOT NULL,
    job_group character varying(150) NOT NULL,
    description character varying(250),
    job_class_name character varying(250) NOT NULL,
    is_durable boolean NOT NULL,
    is_nonconcurrent boolean NOT NULL,
    is_update_data boolean NOT NULL,
    requests_recovery boolean NOT NULL,
    job_data bytea
);


--
-- Name: qrtz_locks; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_locks (
    sched_name character varying(100) NOT NULL,
    lock_name character varying(40) NOT NULL
);


--
-- Name: qrtz_paused_trigger_grps; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_paused_trigger_grps (
    sched_name character varying(100) NOT NULL,
    trigger_group character varying(200) NOT NULL
);


--
-- Name: qrtz_scheduler_state; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_scheduler_state (
    sched_name character varying(100) NOT NULL,
    instance_name character varying(200) NOT NULL,
    last_checkin_time bigint NOT NULL,
    checkin_interval bigint NOT NULL
);


--
-- Name: qrtz_simple_triggers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_simple_triggers (
    sched_name character varying(100) NOT NULL,
    trigger_name character varying(150) NOT NULL,
    trigger_group character varying(150) NOT NULL,
    repeat_count bigint NOT NULL,
    repeat_interval bigint NOT NULL,
    times_triggered bigint NOT NULL
);


--
-- Name: qrtz_simprop_triggers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_simprop_triggers (
    sched_name character varying(100) NOT NULL,
    trigger_name character varying(150) NOT NULL,
    trigger_group character varying(150) NOT NULL,
    str_prop_1 character varying(512),
    str_prop_2 character varying(512),
    str_prop_3 character varying(512),
    int_prop_1 integer,
    int_prop_2 integer,
    long_prop_1 bigint,
    long_prop_2 bigint,
    dec_prop_1 numeric(13,4),
    dec_prop_2 numeric(13,4),
    bool_prop_1 boolean,
    bool_prop_2 boolean
);


--
-- Name: qrtz_triggers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_triggers (
    sched_name character varying(100) NOT NULL,
    trigger_name character varying(150) NOT NULL,
    trigger_group character varying(150) NOT NULL,
    job_name character varying(150) NOT NULL,
    job_group character varying(150) NOT NULL,
    description character varying(250),
    next_fire_time bigint,
    prev_fire_time bigint,
    priority integer,
    trigger_state character varying(16) NOT NULL,
    trigger_type character varying(8) NOT NULL,
    start_time bigint NOT NULL,
    end_time bigint,
    calendar_name character varying(200),
    misfire_instr smallint,
    job_data bytea
);


--
-- Name: quality_assurance_content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.quality_assurance_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: rule_violation; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rule_violation (
    id bigint NOT NULL,
    detected_date timestamp without time zone,
    reference_id character varying(255),
    name character varying(255),
    resource_type character varying(255),
    rule_description character varying(255),
    rule_group character varying(255),
    status character varying(255),
    positive character varying(255),
    rule_name character varying(255)
);


--
-- Name: sweden_county_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_county_data (
    hjid bigint NOT NULL,
    code numeric,
    county_name character varying(255),
    county_regions_sweden_county_0 bigint
);


--
-- Name: sweden_county_data_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_county_data_container (
    hjid bigint NOT NULL
);


--
-- Name: sweden_county_electoral_area; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_county_electoral_area (
    hjid bigint NOT NULL,
    code numeric,
    electoral_area_name character varying(255),
    first_round numeric,
    number_of_seats numeric,
    number_of_voters numeric,
    rest character varying(255),
    second_round numeric,
    landstingsvalkrets_sweden_co_0 bigint
);


--
-- Name: sweden_county_electoral_regi_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_county_electoral_regi_0 (
    hjid bigint NOT NULL
);


--
-- Name: sweden_county_electoral_regi_1; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_county_electoral_regi_1 (
    hjid bigint NOT NULL,
    code numeric,
    county_name character varying(255),
    seats numeric,
    county_electoral_regions_swe_0 bigint
);


--
-- Name: sweden_election_region; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_election_region (
    hjid bigint NOT NULL,
    county_id character varying(255),
    municipal_id character varying(255),
    region_name character varying(255)
);


--
-- Name: sweden_election_type; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_election_type (
    hjid bigint NOT NULL,
    election_code character varying(255),
    election_type character varying(255),
    region_sweden_election_type__0 bigint,
    election_types_sweden_electi_0 bigint
);


--
-- Name: sweden_election_type_contain_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_election_type_contain_0 (
    hjid bigint NOT NULL
);


--
-- Name: sweden_municipality_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_municipality_data (
    hjid bigint NOT NULL,
    code numeric,
    municipal_name character varying(255),
    kommun_sweden_county_data_hj_0 bigint
);


--
-- Name: sweden_municipality_election_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_municipality_election_0 (
    hjid bigint NOT NULL,
    code numeric,
    election_region_name character varying(255),
    first_round numeric,
    number_of_seats numeric,
    number_of_voters numeric,
    rest character varying(255),
    second_round numeric,
    kommunvalkrets_sweden_munici_0 bigint
);


--
-- Name: sweden_parliament_electoral__0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_parliament_electoral__0 (
    hjid bigint NOT NULL
);


--
-- Name: sweden_parliament_electoral__1; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_parliament_electoral__1 (
    hjid bigint NOT NULL,
    election_region_name character varying(255),
    first_round numeric,
    number_of_seats numeric,
    number_of_voters numeric,
    rest character varying(255),
    second_round numeric,
    parliament_electoral_regions_0 bigint
);


--
-- Name: sweden_political_party; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_political_party (
    hjid bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    co_address character varying(255),
    email character varying(255),
    fax_number character varying(255),
    party_id character varying(255),
    party_name character varying(255),
    phone_number character varying(255),
    post_code character varying(255),
    registered_date date,
    short_code character varying(255),
    website character varying(255),
    parties_sweden_election_regi_0 bigint
);


--
-- Name: target_profile_content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.target_profile_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: topic; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.topic (
    hjid bigint NOT NULL,
    id character varying(255),
    value_ character varying(255),
    topic_topics_hjid bigint
);


--
-- Name: topics; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.topics (
    hjid bigint NOT NULL
);


--
-- Name: user_account; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_account (
    hjid bigint NOT NULL,
    email character varying(255),
    model_object_id integer,
    model_object_version integer,
    number_of_visits integer,
    user_id character varying(255),
    user_role character varying(255),
    user_type character varying(255),
    username character varying(255),
    userpassword character varying(255),
    country character varying(255),
    created_date timestamp without time zone,
    user_lock_status character varying(255),
    user_email_status character varying(255)
);


--
-- Name: user_account_address; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_account_address (
    hjid bigint NOT NULL,
    hjvalue character varying(255),
    hjindex integer NOT NULL
);


--
-- Name: view_application_action_event_page_annual_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_annual_summary AS
 SELECT page AS embedded_id_page,
    date_trunc('year'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY (date_trunc('year'::text, created_date)) ORDER BY (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY (date_trunc('year'::text, created_date)) ORDER BY (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE (page IS NOT NULL)
  GROUP BY page, (date_trunc('year'::text, created_date))
  ORDER BY (date_trunc('year'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_daily_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_daily_summary AS
 SELECT page AS embedded_id_page,
    date_trunc('day'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY (date_trunc('day'::text, created_date)) ORDER BY (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY (date_trunc('day'::text, created_date)) ORDER BY (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE (page IS NOT NULL)
  GROUP BY page, (date_trunc('day'::text, created_date))
  ORDER BY (date_trunc('day'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_element_annual_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_element_annual_summary AS
 SELECT page AS embedded_id_page,
    element_id AS embedded_id_element_id,
    date_trunc('year'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY page, (date_trunc('year'::text, created_date)) ORDER BY (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY page, (date_trunc('year'::text, created_date)) ORDER BY (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE ((page IS NOT NULL) AND (element_id IS NOT NULL) AND ((element_id)::text <> ''::text))
  GROUP BY page, element_id, (date_trunc('year'::text, created_date))
  ORDER BY (date_trunc('year'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_element_daily_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_element_daily_summary AS
 SELECT page AS embedded_id_page,
    element_id AS embedded_id_element_id,
    date_trunc('day'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY page, (date_trunc('day'::text, created_date)) ORDER BY (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY page, (date_trunc('day'::text, created_date)) ORDER BY (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE ((page IS NOT NULL) AND (element_id IS NOT NULL) AND ((element_id)::text <> ''::text))
  GROUP BY page, element_id, (date_trunc('day'::text, created_date))
  ORDER BY (date_trunc('day'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_element_hourly_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_element_hourly_summary AS
 SELECT page AS embedded_id_page,
    element_id AS embedded_id_element_id,
    date_trunc('hour'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY page, (date_trunc('hour'::text, created_date)) ORDER BY (date_trunc('hour'::text, created_date)), (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY page, (date_trunc('hour'::text, created_date)) ORDER BY (date_trunc('hour'::text, created_date)), (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE ((page IS NOT NULL) AND (element_id IS NOT NULL) AND ((element_id)::text <> ''::text))
  GROUP BY page, element_id, (date_trunc('hour'::text, created_date))
  ORDER BY (date_trunc('hour'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_element_weekly_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_element_weekly_summary AS
 SELECT page AS embedded_id_page,
    element_id AS embedded_id_element_id,
    date_trunc('week'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY page, (date_trunc('week'::text, created_date)) ORDER BY (date_trunc('week'::text, created_date)), (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY page, (date_trunc('week'::text, created_date)) ORDER BY (date_trunc('week'::text, created_date)), (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE ((page IS NOT NULL) AND (element_id IS NOT NULL) AND ((element_id)::text <> ''::text))
  GROUP BY page, element_id, (date_trunc('week'::text, created_date))
  ORDER BY (date_trunc('week'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_hourly_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_hourly_summary AS
 SELECT page AS embedded_id_page,
    date_trunc('hour'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY (date_trunc('hour'::text, created_date)) ORDER BY (date_trunc('hour'::text, created_date)), (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY (date_trunc('hour'::text, created_date)) ORDER BY (date_trunc('hour'::text, created_date)), (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE (page IS NOT NULL)
  GROUP BY page, (date_trunc('hour'::text, created_date))
  ORDER BY (date_trunc('hour'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_modes_annual_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_modes_annual_summary AS
 SELECT page AS embedded_id_page,
    page_mode AS embedded_id_page_mode,
    date_trunc('year'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY (date_trunc('year'::text, created_date)) ORDER BY (date_trunc('year'::text, created_date)), (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY (date_trunc('year'::text, created_date)) ORDER BY (date_trunc('year'::text, created_date)), (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE ((page IS NOT NULL) AND (page_mode IS NOT NULL))
  GROUP BY page, page_mode, (date_trunc('year'::text, created_date))
  ORDER BY (date_trunc('year'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_modes_daily_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_modes_daily_summary AS
 SELECT page AS embedded_id_page,
    page_mode AS embedded_id_page_mode,
    date_trunc('day'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY (date_trunc('day'::text, created_date)) ORDER BY (date_trunc('day'::text, created_date)), (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY (date_trunc('day'::text, created_date)) ORDER BY (date_trunc('day'::text, created_date)), (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE ((page IS NOT NULL) AND (page_mode IS NOT NULL))
  GROUP BY page, page_mode, (date_trunc('day'::text, created_date))
  ORDER BY (date_trunc('day'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_modes_hourly_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_modes_hourly_summary AS
 SELECT page AS embedded_id_page,
    page_mode AS embedded_id_page_mode,
    date_trunc('hour'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY (date_trunc('hour'::text, created_date)) ORDER BY (date_trunc('hour'::text, created_date)), (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY (date_trunc('hour'::text, created_date)) ORDER BY (date_trunc('hour'::text, created_date)), (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE ((page IS NOT NULL) AND (page_mode IS NOT NULL))
  GROUP BY page, page_mode, (date_trunc('hour'::text, created_date))
  ORDER BY (date_trunc('hour'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_modes_weekly_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_modes_weekly_summary AS
 SELECT page AS embedded_id_page,
    page_mode AS embedded_id_page_mode,
    date_trunc('week'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY (date_trunc('week'::text, created_date)) ORDER BY (date_trunc('week'::text, created_date)), (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY (date_trunc('week'::text, created_date)) ORDER BY (date_trunc('week'::text, created_date)), (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE ((page IS NOT NULL) AND (page_mode IS NOT NULL))
  GROUP BY page, page_mode, (date_trunc('week'::text, created_date))
  ORDER BY (date_trunc('week'::text, created_date)), (count(*)) DESC;


--
-- Name: view_application_action_event_page_weekly_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_application_action_event_page_weekly_summary AS
 SELECT page AS embedded_id_page,
    date_trunc('week'::text, created_date) AS embedded_id_created_date,
    count(*) AS hits,
    percent_rank() OVER (PARTITION BY (date_trunc('week'::text, created_date)) ORDER BY (date_trunc('week'::text, created_date)), (count(*)) DESC) AS rank_percentage,
    rank() OVER (PARTITION BY (date_trunc('week'::text, created_date)) ORDER BY (date_trunc('week'::text, created_date)), (count(*)) DESC) AS rank
   FROM public.application_action_event
  WHERE (page IS NOT NULL)
  GROUP BY page, (date_trunc('week'::text, created_date))
  ORDER BY (date_trunc('week'::text, created_date)), (count(*)) DESC;


--
-- Name: view_audit_author_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_audit_author_summary AS
 SELECT min(commit_pk) AS id,
    author,
    count(*) AS changes,
    min(commit_date) AS first_date,
    max(commit_date) AS last_date
   FROM public.jv_commit
  GROUP BY author;


--
-- Name: view_audit_data_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_audit_data_summary AS
 SELECT row_number() OVER (ORDER BY relname) AS id,
    relname AS data_type,
    n_live_tup AS data_size
   FROM pg_stat_user_tables
  ORDER BY relname;


--
-- Name: view_riksdagen_politician_document; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_politician_document AS
 SELECT e3.hjid AS id,
    e3.document_document_status_con_0 AS doc_id,
    e3.document_type,
    e3.label,
    e3.made_public_date,
    e3.org,
    e3.number_value,
    e3.rm,
    e3.status,
    e3.sub_title,
    e3.sub_type,
    e3.temp_label,
    e3.title,
    e4.role_description,
    e4.person_reference_id,
    e4.reference_name,
    e4.party_short_code,
    e4.order_number
   FROM (( SELECT document_status_container.hjid,
            document_status_container.document_category,
            document_status_container.document_document_status_con_0,
            document_status_container.document_activity_container__0,
            document_status_container.document_attachment_containe_0,
            document_status_container.document_detail_container_do_0,
            document_status_container.document_person_reference_co_1,
            document_status_container.document_reference_container_0,
            document_status_container.document_proposal_document_s_0,
            document_data.id,
            document_data.committee_report_url_xml,
            document_data.document_status_url_www,
            document_data.document_status_url_xml,
            document_data.document_type,
            document_data.document_url_html,
            document_data.document_url_text,
            document_data.final_number,
            document_data.hangar_id,
            document_data.label,
            document_data.made_public_date,
            document_data.number_value,
            document_data.org,
            document_data.rm,
            document_data.status,
            document_data.sub_title,
            document_data.sub_type,
            document_data.temp_label,
            document_data.title
           FROM (public.document_status_container
             LEFT JOIN public.document_data ON (((document_status_container.document_document_status_con_0)::text = (document_data.id)::text)))) e3
     LEFT JOIN ( SELECT document_person_reference_da_0.hjid AS id,
            document_person_reference_da_0.role_description,
            document_person_reference_da_0.person_reference_id,
            document_person_reference_da_0.reference_name,
            document_person_reference_da_0.party_short_code,
            document_person_reference_da_0.order_number,
            document_person_reference_da_0.document_person_reference_li_1
           FROM (public.document_person_reference_da_0
             LEFT JOIN ( SELECT document_person_reference_co_0.hjid AS person_id_ref
                   FROM (public.document_status_container
                     LEFT JOIN public.document_person_reference_co_0 ON ((document_status_container.hjid = document_person_reference_co_0.hjid)))) e2 ON ((document_person_reference_da_0.document_person_reference_li_1 = e2.person_id_ref)))) e4 ON ((e3.document_person_reference_co_1 = e4.document_person_reference_li_1)))
  WITH NO DATA;


--
-- Name: view_riksdagen_committee; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_committee AS
 SELECT convert_from((a.detail)::bytea, 'UTF8'::name) AS embedded_id_detail,
    a.org_code AS embedded_id_org_code,
    count(a.org_code) AS total_assignments,
    min(a.from_date) AS first_assignment_date,
    max(a.to_date) AS last_assignment_date,
    sum(
        CASE
            WHEN (COALESCE(a.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN 1
            ELSE 0
        END) AS current_member_size,
        CASE
            WHEN (max(a.to_date) >= CURRENT_DATE) THEN true
            ELSE false
        END AS active,
    sum(
        CASE
            WHEN ((a.status)::text !~~ 'Ledig%'::text) THEN (
            CASE
                WHEN (COALESCE(a.to_date, CURRENT_DATE) >= CURRENT_DATE) THEN CURRENT_DATE
                ELSE COALESCE(a.to_date, CURRENT_DATE)
            END -
            CASE
                WHEN (a.from_date >= CURRENT_DATE) THEN CURRENT_DATE
                ELSE a.from_date
            END)
            ELSE 0
        END) AS total_days_served,
    sum(
        CASE
            WHEN (((a.role_code)::text ~~* '%ordförande%'::text) OR ((a.role_code)::text ~~* '%vice ordförande%'::text)) THEN 1
            ELSE 0
        END) AS total_leadership_positions,
    sum(
        CASE
            WHEN ((((a.role_code)::text ~~* '%ordförande%'::text) OR ((a.role_code)::text ~~* '%vice ordförande%'::text)) AND (COALESCE(a.to_date, CURRENT_DATE) >= CURRENT_DATE)) THEN 1
            ELSE 0
        END) AS current_leadership_positions,
    sum(
        CASE
            WHEN (((a.role_code)::text ~~* '%suppleant%'::text) OR ((a.role_code)::text ~~* '%ersättare%'::text)) THEN 1
            ELSE 0
        END) AS total_substitute_positions,
    sum(
        CASE
            WHEN ((((a.role_code)::text ~~* '%suppleant%'::text) OR ((a.role_code)::text ~~* '%ersättare%'::text)) AND (COALESCE(a.to_date, CURRENT_DATE) >= CURRENT_DATE)) THEN 1
            ELSE 0
        END) AS current_substitute_positions,
    sum(
        CASE
            WHEN ((NOT ((a.role_code)::text ~~* ANY (ARRAY['%ordförande%'::text, '%vice ordförande%'::text, '%suppleant%'::text, '%ersättare%'::text]))) AND (COALESCE(a.to_date, CURRENT_DATE) >= CURRENT_DATE)) THEN 1
            ELSE 0
        END) AS current_regular_members,
    COALESCE(doc_stats.total_documents, (0)::bigint) AS total_documents,
    COALESCE(doc_stats.documents_last_year, (0)::bigint) AS documents_last_year,
    COALESCE(doc_stats.avg_documents_per_member, (0)::numeric) AS avg_documents_per_member,
    COALESCE(doc_stats.total_committee_motions, (0)::bigint) AS total_committee_motions,
    COALESCE(doc_stats.total_follow_up_motions, (0)::bigint) AS total_follow_up_motions,
        CASE
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 200) THEN 'Very High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 100) THEN 'High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 50) THEN 'Medium'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 0) THEN 'Low'::text
            ELSE 'Inactive'::text
        END AS activity_level
   FROM (public.assignment_data a
     LEFT JOIN ( SELECT view_riksdagen_politician_document.org AS committee_org_code,
            count(*) AS total_documents,
            count(
                CASE
                    WHEN (view_riksdagen_politician_document.made_public_date >= (CURRENT_DATE - '1 year'::interval)) THEN 1
                    ELSE NULL::integer
                END) AS documents_last_year,
            round(((count(*))::numeric / (NULLIF(count(DISTINCT view_riksdagen_politician_document.person_reference_id), 0))::numeric), 1) AS avg_documents_per_member,
            count(
                CASE
                    WHEN (((view_riksdagen_politician_document.document_type)::text = 'mot'::text) AND ((view_riksdagen_politician_document.sub_type)::text = 'Kommittémotion'::text)) THEN 1
                    ELSE NULL::integer
                END) AS total_committee_motions,
            count(
                CASE
                    WHEN (((view_riksdagen_politician_document.document_type)::text = 'mot'::text) AND ((view_riksdagen_politician_document.sub_type)::text = 'Följdmotion'::text)) THEN 1
                    ELSE NULL::integer
                END) AS total_follow_up_motions
           FROM public.view_riksdagen_politician_document
          WHERE (view_riksdagen_politician_document.org IS NOT NULL)
          GROUP BY view_riksdagen_politician_document.org) doc_stats ON (((doc_stats.committee_org_code)::text = (a.org_code)::text)))
  WHERE ((a.org_code IS NOT NULL) AND ((a.assignment_type)::text = 'uppdrag'::text))
  GROUP BY a.detail, a.org_code, doc_stats.total_documents, doc_stats.documents_last_year, doc_stats.avg_documents_per_member, doc_stats.total_committee_motions, doc_stats.total_follow_up_motions;


--
-- Name: view_riksdagen_committee_decisions; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_committee_decisions AS
 SELECT cd.id AS embedded_id_id,
    cd.title,
    cpd.header,
    cd.hangar_id AS embedded_id_hangar_id,
    cd.label AS committee_report,
    cd.rm,
    cd.end_number,
    cpd.issue_number AS embedded_id_issue_nummer,
    upper((cd.org)::text) AS org,
    cd.created_date,
    cd.public_date,
    cd.committee_proposal_url_xml,
    cpd.decision_type,
    cpd.ballot_id,
    cpd.against_proposal_parties,
    cpd.against_proposal_number,
    cpd.winner
   FROM (public.committee_proposal_data cpd
     LEFT JOIN public.committee_document_data cd ON ((((cd.label)::text = (cpd.committee_report)::text) AND ((cpd.rm)::text = (cd.rm)::text))))
  WITH NO DATA;


--
-- Name: vote_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.vote_data (
    embedded_id_ballot_id character varying(255) NOT NULL,
    embedded_id_concern character varying(255) NOT NULL,
    embedded_id_intressent_id character varying(255) NOT NULL,
    embedded_id_issue character varying(255) NOT NULL,
    ballot_type character varying(255),
    bank_number character varying(255),
    born_year integer,
    electoral_region character varying(255),
    electoral_region_number numeric,
    first_name character varying(255),
    full_name character varying(255),
    gender character varying(255),
    label character varying(255),
    last_name character varying(255),
    party character varying(255),
    place character varying(255),
    rm character varying(255),
    vote character varying(255),
    vote_date date
);


--
-- Name: view_riksdagen_vote_data_ballot_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary AS
 WITH vote_counts AS (
         SELECT vote_data.embedded_id_ballot_id,
            vote_data.embedded_id_concern,
            vote_data.embedded_id_issue,
            max((vote_data.ballot_type)::text) AS ballot_type,
            max((vote_data.rm)::text) AS rm,
            max((vote_data.label)::text) AS label,
            max(vote_data.vote_date) AS vote_date,
            round(avg(vote_data.born_year)) AS avg_born_year,
            count(*) AS total_votes,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'JA'::text) THEN 1
                    ELSE 0
                END) AS yes_votes,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'NEJ'::text) THEN 1
                    ELSE 0
                END) AS no_votes,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'AVSTÅR'::text) THEN 1
                    ELSE 0
                END) AS abstain_votes,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'FRÅNVARANDE'::text) THEN 1
                    ELSE 0
                END) AS absent_votes,
            sum(
                CASE
                    WHEN ((vote_data.gender)::text = 'MAN'::text) THEN 1
                    ELSE 0
                END) AS male_count,
            (sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'JA'::text) THEN 1
                    ELSE 0
                END) > sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'NEJ'::text) THEN 1
                    ELSE 0
                END)) AS approved,
            (sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'JA'::text) THEN 1
                    ELSE 0
                END) = sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'NEJ'::text) THEN 1
                    ELSE 0
                END)) AS no_winner
           FROM public.vote_data
          GROUP BY vote_data.embedded_id_ballot_id, vote_data.embedded_id_issue, vote_data.embedded_id_concern
        )
 SELECT embedded_id_ballot_id,
    embedded_id_concern,
    embedded_id_issue,
    ballot_type,
    rm,
    label,
    vote_date,
    avg_born_year,
    total_votes,
    yes_votes,
    no_votes,
    abstain_votes,
    absent_votes,
    approved,
    no_winner,
    round(((100.0 * (yes_votes)::numeric) / (NULLIF(total_votes, 0))::numeric), 2) AS percentage_yes,
    round(((100.0 * (no_votes)::numeric) / (NULLIF(total_votes, 0))::numeric), 2) AS percentage_no,
    round(((100.0 * (absent_votes)::numeric) / (NULLIF(total_votes, 0))::numeric), 2) AS percentage_absent,
    round(((100.0 * (abstain_votes)::numeric) / (NULLIF(total_votes, 0))::numeric), 2) AS percentage_abstain,
    round(((100.0 * (male_count)::numeric) / (NULLIF(total_votes, 0))::numeric), 2) AS percentage_male
   FROM vote_counts
  ORDER BY vote_date
  WITH NO DATA;


--
-- Name: view_riksdagen_committee_ballot_decision_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_committee_ballot_decision_summary AS
 SELECT d.embedded_id_id,
    s.embedded_id_issue,
    d.embedded_id_hangar_id,
    d.committee_report,
    d.rm,
    d.title,
    d.header AS sub_title,
    d.end_number,
    upper(d.org) AS org,
    d.created_date,
    d.public_date,
    d.ballot_id,
    d.decision_type,
    d.against_proposal_parties,
    d.against_proposal_number,
    d.winner,
    s.embedded_id_concern,
    s.ballot_type,
    s.label,
    s.vote_date,
    s.avg_born_year,
    s.total_votes,
    s.yes_votes,
    s.no_votes,
    s.abstain_votes,
    s.absent_votes,
    s.approved,
    s.no_winner,
    s.percentage_yes,
    s.percentage_no,
    s.percentage_absent,
    s.percentage_abstain,
    s.percentage_male
   FROM (public.view_riksdagen_committee_decisions d
     LEFT JOIN public.view_riksdagen_vote_data_ballot_summary s ON ((((d.ballot_id)::text = (s.embedded_id_ballot_id)::text) AND ((d.rm)::text = s.rm) AND ((d.embedded_id_issue_nummer)::text = (s.embedded_id_issue)::text))))
  WHERE (((d.decision_type)::text = 'röstning'::text) AND (d.ballot_id IS NOT NULL) AND (s.embedded_id_ballot_id IS NOT NULL))
  WITH NO DATA;


--
-- Name: view_committee_productivity; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_committee_productivity AS
 WITH committee_membership AS (
         SELECT rc.embedded_id_org_code AS committee_code,
            rc.embedded_id_detail AS committee_name,
            count(DISTINCT ad.intressent_id) AS total_members,
            count(DISTINCT ad.intressent_id) FILTER (WHERE ((ad.role_code)::text = 'Ordförande'::text)) AS chairs_count,
            count(DISTINCT ad.intressent_id) FILTER (WHERE ((ad.role_code)::text = 'Vice ordförande'::text)) AS vice_chairs_count,
            count(DISTINCT ad.intressent_id) FILTER (WHERE ((ad.role_code)::text = 'Ledamot'::text)) AS regular_members,
            count(DISTINCT ad.intressent_id) FILTER (WHERE (((ad.role_code)::text ~~* '%suppleant%'::text) OR ((ad.role_code)::text ~~* '%ersättare%'::text))) AS substitutes
           FROM (public.view_riksdagen_committee rc
             LEFT JOIN public.assignment_data ad ON ((((ad.org_code)::text = (rc.embedded_id_org_code)::text) AND ((ad.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((ad.to_date IS NULL) OR (ad.to_date >= CURRENT_DATE)))))
          GROUP BY rc.embedded_id_org_code, rc.embedded_id_detail
        ), committee_decisions AS (
         SELECT view_riksdagen_committee_decisions.org AS committee_code,
            count(DISTINCT view_riksdagen_committee_decisions.embedded_id_id) AS total_decisions,
            count(DISTINCT view_riksdagen_committee_decisions.embedded_id_id) FILTER (WHERE (view_riksdagen_committee_decisions.public_date >= (CURRENT_DATE - '1 year'::interval))) AS decisions_last_year,
            count(DISTINCT view_riksdagen_committee_decisions.embedded_id_id) FILTER (WHERE (view_riksdagen_committee_decisions.public_date >= (CURRENT_DATE - '1 mon'::interval))) AS decisions_last_month,
            max(view_riksdagen_committee_decisions.public_date) AS latest_decision_date,
            min(view_riksdagen_committee_decisions.public_date) FILTER (WHERE (view_riksdagen_committee_decisions.public_date >= (CURRENT_DATE - '1 year'::interval))) AS first_decision_last_year
           FROM public.view_riksdagen_committee_decisions
          GROUP BY view_riksdagen_committee_decisions.org
        ), committee_documents AS (
         SELECT upper((cdd.org)::text) AS committee_code,
            count(DISTINCT cdd.id) AS total_documents,
            count(DISTINCT cdd.id) FILTER (WHERE (cdd.public_date >= (CURRENT_DATE - '1 year'::interval))) AS documents_last_year,
            count(DISTINCT cdd.id) FILTER (WHERE ((cdd.sub_type)::text = 'mot'::text)) AS motions,
            count(DISTINCT cdd.id) FILTER (WHERE ((cdd.sub_type)::text = 'prop'::text)) AS propositions,
            count(DISTINCT cdd.id) FILTER (WHERE ((cdd.label)::text ~~ '%bet%'::text)) AS reports
           FROM public.committee_document_data cdd
          WHERE (cdd.public_date >= (CURRENT_DATE - '2 years'::interval))
          GROUP BY (upper((cdd.org)::text))
        ), committee_ballots AS (
         SELECT view_riksdagen_committee_ballot_decision_summary.org AS committee_code,
            count(DISTINCT view_riksdagen_committee_ballot_decision_summary.ballot_id) AS total_ballots_decided,
            avg(view_riksdagen_committee_ballot_decision_summary.percentage_yes) AS avg_yes_percentage,
            avg(view_riksdagen_committee_ballot_decision_summary.percentage_no) AS avg_no_percentage
           FROM public.view_riksdagen_committee_ballot_decision_summary
          WHERE (view_riksdagen_committee_ballot_decision_summary.vote_date >= (CURRENT_DATE - '1 year'::interval))
          GROUP BY view_riksdagen_committee_ballot_decision_summary.org
        ), productivity_calculations AS (
         SELECT cm.committee_code,
            cm.committee_name,
            cm.total_members,
            cm.chairs_count,
            cm.vice_chairs_count,
            cm.regular_members,
            cm.substitutes,
            cd.total_decisions,
            cd.decisions_last_year,
            cd.decisions_last_month,
            cd.latest_decision_date,
            cdoc.total_documents,
            cdoc.documents_last_year,
            cdoc.motions,
            cdoc.propositions,
            cdoc.reports,
            cb.total_ballots_decided,
            cb.avg_yes_percentage,
            (((((LEAST(((COALESCE(cd.decisions_last_year, (0)::bigint))::numeric / 20.0), 1.0) * (30)::numeric) + (LEAST(((COALESCE(cdoc.documents_last_year, (0)::bigint))::numeric / 30.0), 1.0) * (25)::numeric)) + (
                CASE
                    WHEN (cd.latest_decision_date >= (CURRENT_DATE - '1 mon'::interval)) THEN 20
                    WHEN (cd.latest_decision_date >= (CURRENT_DATE - '3 mons'::interval)) THEN 15
                    WHEN (cd.latest_decision_date >= (CURRENT_DATE - '6 mons'::interval)) THEN 10
                    ELSE 0
                END)::numeric) + (
                CASE
                    WHEN ((cm.chairs_count > 0) AND (cm.total_members >= 10)) THEN 15
                    WHEN ((cm.chairs_count > 0) AND (cm.total_members >= 5)) THEN 10
                    WHEN (cm.total_members >= 5) THEN 5
                    ELSE 0
                END)::numeric) + (LEAST(
                CASE
                    WHEN (cm.total_members > 0) THEN (((COALESCE(cd.decisions_last_year, (0)::bigint))::numeric / (cm.total_members)::numeric) / 2.0)
                    ELSE (0)::numeric
                END, 1.0) * (10)::numeric)) AS calculated_productivity_score
           FROM (((committee_membership cm
             LEFT JOIN committee_decisions cd ON ((cd.committee_code = (cm.committee_code)::text)))
             LEFT JOIN committee_documents cdoc ON ((cdoc.committee_code = (cm.committee_code)::text)))
             LEFT JOIN committee_ballots cb ON ((cb.committee_code = (cm.committee_code)::text)))
        )
 SELECT committee_code,
    committee_name,
    total_members,
    chairs_count,
    vice_chairs_count,
    regular_members,
    substitutes,
        CASE
            WHEN (chairs_count = 0) THEN 'NO_LEADERSHIP'::text
            WHEN (total_members < 10) THEN 'UNDERSTAFFED'::text
            WHEN (total_members >= 15) THEN 'WELL_STAFFED'::text
            ELSE 'ADEQUATE_STAFFING'::text
        END AS staffing_status,
    COALESCE(total_decisions, (0)::bigint) AS total_decisions_all_time,
    COALESCE(decisions_last_year, (0)::bigint) AS decisions_last_year,
    COALESCE(decisions_last_month, (0)::bigint) AS decisions_last_month,
    latest_decision_date,
    COALESCE((CURRENT_DATE - latest_decision_date), '-1'::integer) AS days_since_last_decision,
    COALESCE(total_documents, (0)::bigint) AS total_documents,
    COALESCE(documents_last_year, (0)::bigint) AS documents_last_year,
    COALESCE(motions, (0)::bigint) AS motions_count,
    COALESCE(propositions, (0)::bigint) AS propositions_count,
    COALESCE(reports, (0)::bigint) AS reports_count,
        CASE
            WHEN (total_members > 0) THEN round(((COALESCE(decisions_last_year, (0)::bigint))::numeric / (total_members)::numeric), 2)
            ELSE (0)::numeric
        END AS decisions_per_member,
        CASE
            WHEN (total_members > 0) THEN round(((COALESCE(documents_last_year, (0)::bigint))::numeric / (total_members)::numeric), 2)
            ELSE (0)::numeric
        END AS documents_per_member,
    COALESCE(total_ballots_decided, (0)::bigint) AS ballots_decided_last_year,
    round(COALESCE(avg_yes_percentage, (0)::numeric), 2) AS avg_approval_rate,
    round(calculated_productivity_score, 2) AS productivity_score,
        CASE
            WHEN (calculated_productivity_score >= (70)::numeric) THEN 'HIGHLY_PRODUCTIVE'::text
            WHEN (calculated_productivity_score >= (50)::numeric) THEN 'PRODUCTIVE'::text
            WHEN (calculated_productivity_score >= (30)::numeric) THEN 'BELOW_AVERAGE'::text
            ELSE 'INACTIVE'::text
        END AS productivity_level,
    concat_ws(' | '::text,
        CASE
            WHEN (chairs_count = 0) THEN 'Missing leadership'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (total_members < 10) THEN 'Understaffed'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(decisions_last_year, (0)::bigint) < 10) THEN 'Low decision output'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(documents_last_year, (0)::bigint) < 5) THEN 'Low document production'::text
            ELSE NULL::text
        END,
        CASE
            WHEN ((latest_decision_date IS NOT NULL) AND (latest_decision_date < (CURRENT_DATE - '3 mons'::interval))) THEN 'Stagnant activity'::text
            ELSE NULL::text
        END) AS performance_concerns,
        CASE
            WHEN (chairs_count = 0) THEN 'Appoint committee leadership immediately'::text
            WHEN ((total_members < 10) AND (COALESCE(decisions_last_year, (0)::bigint) < 10)) THEN 'Increase staffing and provide resources'::text
            WHEN (COALESCE(decisions_last_year, (0)::bigint) < 10) THEN 'Review committee mandate and workload'::text
            WHEN ((latest_decision_date IS NOT NULL) AND (latest_decision_date < (CURRENT_DATE - '3 mons'::interval))) THEN 'Investigate inactivity causes'::text
            ELSE 'Maintain current operations'::text
        END AS recommendation
   FROM productivity_calculations
  ORDER BY (round(calculated_productivity_score, 2)) DESC, COALESCE(decisions_last_year, (0)::bigint) DESC;


--
-- Name: view_committee_productivity_matrix; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_committee_productivity_matrix AS
 WITH committee_documents AS (
         SELECT view_riksdagen_politician_document.org AS committee_code,
            date_trunc('quarter'::text, (view_riksdagen_politician_document.made_public_date)::timestamp with time zone) AS period_start,
            count(*) AS total_documents,
            count(DISTINCT
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = 'Utskottsbetänkande'::text) THEN view_riksdagen_politician_document.doc_id
                    ELSE NULL::character varying
                END) AS committee_reports,
            count(DISTINCT
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = 'Motion'::text) THEN view_riksdagen_politician_document.doc_id
                    ELSE NULL::character varying
                END) AS motions_handled,
            count(DISTINCT view_riksdagen_politician_document.person_reference_id) AS active_members,
            min(view_riksdagen_politician_document.made_public_date) AS first_document_date,
            max(view_riksdagen_politician_document.made_public_date) AS last_document_date
           FROM public.view_riksdagen_politician_document
          WHERE ((view_riksdagen_politician_document.made_public_date >= (CURRENT_DATE - '3 years'::interval)) AND (view_riksdagen_politician_document.org IS NOT NULL) AND ((view_riksdagen_politician_document.org)::text ~~ '%U'::text))
          GROUP BY view_riksdagen_politician_document.org, (date_trunc('quarter'::text, (view_riksdagen_politician_document.made_public_date)::timestamp with time zone))
        ), committee_info AS (
         SELECT DISTINCT view_riksdagen_committee.embedded_id_org_code AS committee_code,
            view_riksdagen_committee.embedded_id_detail AS committee_name,
                CASE
                    WHEN ((view_riksdagen_committee.embedded_id_org_code)::text ~~ '%U'::text) THEN 'Committee'::text
                    ELSE 'Other'::text
                END AS committee_category
           FROM public.view_riksdagen_committee
          WHERE (view_riksdagen_committee.embedded_id_org_code IS NOT NULL)
        ), productivity_benchmarks AS (
         SELECT committee_documents.period_start,
            avg(committee_documents.total_documents) AS avg_documents_per_committee,
            stddev(committee_documents.total_documents) AS stddev_documents,
            max(committee_documents.total_documents) AS max_documents,
            min(committee_documents.total_documents) AS min_documents,
            percentile_cont((0.5)::double precision) WITHIN GROUP (ORDER BY ((committee_documents.total_documents)::double precision)) AS median_documents
           FROM committee_documents
          GROUP BY committee_documents.period_start
        ), productivity_analysis AS (
         SELECT cd.committee_code,
            ci.committee_name,
            ci.committee_category,
            cd.period_start,
            cd.total_documents,
            cd.committee_reports,
            cd.motions_handled,
            cd.active_members,
            cd.first_document_date,
            cd.last_document_date,
            pb.avg_documents_per_committee,
            pb.median_documents,
            pb.max_documents,
            pb.min_documents,
            lag(cd.total_documents, 1) OVER (PARTITION BY cd.committee_code ORDER BY cd.period_start) AS prev_documents,
            lag(cd.active_members, 1) OVER (PARTITION BY cd.committee_code ORDER BY cd.period_start) AS prev_members,
            round(avg(cd.total_documents) OVER (PARTITION BY cd.committee_code ORDER BY cd.period_start ROWS BETWEEN 3 PRECEDING AND CURRENT ROW), 2) AS ma_4quarter_documents
           FROM ((committee_documents cd
             LEFT JOIN committee_info ci ON (((cd.committee_code)::text = (ci.committee_code)::text)))
             LEFT JOIN productivity_benchmarks pb ON ((cd.period_start = pb.period_start)))
        )
 SELECT committee_code,
    committee_name,
    committee_category,
    period_start,
    (((period_start + '3 mons'::interval) - '1 day'::interval))::date AS period_end,
    EXTRACT(year FROM (period_start)::timestamp without time zone) AS year,
    EXTRACT(quarter FROM (period_start)::timestamp without time zone) AS quarter,
    total_documents,
    committee_reports,
    motions_handled,
    active_members,
    round(((total_documents)::numeric / (NULLIF(active_members, 0))::numeric), 2) AS documents_per_member,
    round(((committee_reports)::numeric / (NULLIF(active_members, 0))::numeric), 2) AS reports_per_member,
    (total_documents - COALESCE(prev_documents, total_documents)) AS document_change,
    round(((((total_documents - COALESCE(prev_documents, total_documents)))::numeric / (NULLIF(prev_documents, 0))::numeric) * (100)::numeric), 2) AS document_change_pct,
    ma_4quarter_documents,
    round(avg_documents_per_committee, 2) AS period_avg_documents,
    round((median_documents)::numeric, 2) AS period_median_documents,
    max_documents AS period_max_documents,
    min_documents AS period_min_documents,
    round(((total_documents)::numeric - avg_documents_per_committee), 2) AS vs_average,
    round(((((total_documents)::numeric / NULLIF(avg_documents_per_committee, (0)::numeric)) - (1)::numeric) * (100)::numeric), 2) AS vs_average_pct,
        CASE
            WHEN ((total_documents)::numeric >= (avg_documents_per_committee * 1.5)) THEN 'HIGHLY_PRODUCTIVE'::text
            WHEN ((total_documents)::numeric >= (avg_documents_per_committee * 1.2)) THEN 'ABOVE_AVERAGE'::text
            WHEN ((total_documents)::numeric >= (avg_documents_per_committee * 0.8)) THEN 'AVERAGE'::text
            WHEN ((total_documents)::numeric >= (avg_documents_per_committee * 0.5)) THEN 'BELOW_AVERAGE'::text
            ELSE 'UNDERPERFORMING'::text
        END AS productivity_level,
        CASE
            WHEN ((total_documents - COALESCE(prev_documents, total_documents)) >= 10) THEN 'STRONG_INCREASE'::text
            WHEN ((total_documents - COALESCE(prev_documents, total_documents)) >= 5) THEN 'MODERATE_INCREASE'::text
            WHEN ((total_documents - COALESCE(prev_documents, total_documents)) > 0) THEN 'SLIGHT_INCREASE'::text
            WHEN ((total_documents - COALESCE(prev_documents, total_documents)) <= '-10'::integer) THEN 'STRONG_DECREASE'::text
            WHEN ((total_documents - COALESCE(prev_documents, total_documents)) <= '-5'::integer) THEN 'MODERATE_DECREASE'::text
            WHEN ((total_documents - COALESCE(prev_documents, total_documents)) < 0) THEN 'SLIGHT_DECREASE'::text
            ELSE 'STABLE'::text
        END AS productivity_trend,
        CASE
            WHEN (((total_documents)::numeric >= (avg_documents_per_committee * 1.5)) AND (active_members >= 10)) THEN 'Exceptional committee productivity and engagement'::text
            WHEN ((total_documents)::numeric >= (avg_documents_per_committee * 1.2)) THEN 'Above-average committee performance'::text
            WHEN ((total_documents)::numeric < (avg_documents_per_committee * 0.5)) THEN 'Committee productivity concerns - investigation recommended'::text
            WHEN ((total_documents - COALESCE(prev_documents, total_documents)) <= '-10'::integer) THEN 'Significant productivity decline detected'::text
            ELSE 'Standard committee operations'::text
        END AS productivity_assessment,
    first_document_date,
    last_document_date,
    (last_document_date - first_document_date) AS activity_span_days
   FROM productivity_analysis
  WHERE (committee_code IS NOT NULL)
  ORDER BY period_start DESC, total_documents DESC;


--
-- Name: view_decision_temporal_trends; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_decision_temporal_trends AS
 WITH daily_decisions AS (
         SELECT dd.made_public_date AS decision_day,
            count(*) AS daily_decisions,
            round(((100.0 * (count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%BIFALL%'::text) OR (upper((dpd.chamber)::text) ~~ '%GODKÄNT%'::text) OR (upper((dpd.chamber)::text) ~~ '%BIFALLA%'::text))))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS daily_approval_rate,
            count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%BIFALL%'::text) OR (upper((dpd.chamber)::text) ~~ '%GODKÄNT%'::text) OR (upper((dpd.chamber)::text) ~~ '%BIFALLA%'::text))) AS approved_decisions,
            count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%AVSLAG%'::text) OR (upper((dpd.chamber)::text) ~~ '%AVSLÅ%'::text))) AS rejected_decisions,
            count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%ÅTERFÖRVISNING%'::text) OR (upper((dpd.chamber)::text) ~~ '%ÅTERFÖRVISA%'::text))) AS referred_back_decisions,
            count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%UTSKOTT%'::text) AND (upper((dpd.chamber)::text) !~~ '%ÅTERFÖRVISNING%'::text))) AS committee_referral_decisions
           FROM (((public.document_proposal_data dpd
             JOIN public.document_proposal_container dpc ON ((dpc.proposal_document_proposal_c_0 = dpd.hjid)))
             JOIN public.document_status_container dsc ON ((dsc.document_proposal_document_s_0 = dpc.hjid)))
             JOIN public.document_data dd ON (((dd.id)::text = (dsc.document_document_status_con_0)::text)))
          WHERE ((dd.made_public_date IS NOT NULL) AND (dd.made_public_date >= (CURRENT_DATE - '5 years'::interval)) AND (dpd.chamber IS NOT NULL) AND (length((dpd.chamber)::text) >= 6) AND (length((dpd.chamber)::text) <= 29))
          GROUP BY dd.made_public_date
        )
 SELECT decision_day,
    daily_decisions,
    daily_approval_rate,
    approved_decisions,
    rejected_decisions,
    referred_back_decisions,
    committee_referral_decisions,
    round(avg(daily_decisions) OVER (ORDER BY decision_day ROWS BETWEEN 6 PRECEDING AND CURRENT ROW), 2) AS ma_7day_decisions,
    round(avg(daily_decisions) OVER (ORDER BY decision_day ROWS BETWEEN 29 PRECEDING AND CURRENT ROW), 2) AS ma_30day_decisions,
    round(avg(daily_decisions) OVER (ORDER BY decision_day ROWS BETWEEN 89 PRECEDING AND CURRENT ROW), 2) AS ma_90day_decisions,
    round(avg(daily_approval_rate) OVER (ORDER BY decision_day ROWS BETWEEN 29 PRECEDING AND CURRENT ROW), 2) AS ma_30day_approval_rate,
    lag(daily_decisions, 365) OVER (ORDER BY decision_day) AS decisions_last_year,
    (daily_decisions - lag(daily_decisions, 365) OVER (ORDER BY decision_day)) AS yoy_decisions_change,
    round(((100.0 * ((daily_decisions - lag(daily_decisions, 365) OVER (ORDER BY decision_day)))::numeric) / (NULLIF(lag(daily_decisions, 365) OVER (ORDER BY decision_day), 0))::numeric), 2) AS yoy_decisions_change_pct,
    EXTRACT(year FROM decision_day) AS decision_year,
    EXTRACT(month FROM decision_day) AS decision_month,
    EXTRACT(week FROM decision_day) AS decision_week,
    EXTRACT(dow FROM decision_day) AS decision_day_of_week,
        CASE
            WHEN (EXTRACT(month FROM decision_day) = ANY (ARRAY[(7)::numeric, (8)::numeric])) THEN 'Summer Recess'::text
            WHEN (EXTRACT(month FROM decision_day) = ANY (ARRAY[(12)::numeric, (1)::numeric])) THEN 'Winter Recess'::text
            WHEN (EXTRACT(month FROM decision_day) = ANY (ARRAY[(2)::numeric, (3)::numeric])) THEN 'Spring Session'::text
            WHEN (EXTRACT(month FROM decision_day) = ANY (ARRAY[(9)::numeric, (10)::numeric, (11)::numeric])) THEN 'Autumn Session'::text
            WHEN (EXTRACT(month FROM decision_day) = ANY (ARRAY[(4)::numeric, (5)::numeric, (6)::numeric])) THEN 'Late Spring Session'::text
            ELSE 'Active Session'::text
        END AS parliamentary_period,
    ((('Q'::text || (EXTRACT(quarter FROM decision_day))::text) || ' '::text) || (EXTRACT(year FROM decision_day))::text) AS decision_quarter
   FROM daily_decisions
  ORDER BY decision_day DESC;


--
-- Name: view_document_data_committee_report_url; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_document_data_committee_report_url AS
 SELECT id,
    committee_report_url_xml
   FROM public.document_data
  WHERE (committee_report_url_xml IS NOT NULL);


--
-- Name: view_ministry_decision_impact; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_ministry_decision_impact AS
 SELECT dd.org AS ministry_code,
    dpd.committee,
    dpd.decision_type,
    date_trunc('quarter'::text, (dd.made_public_date)::timestamp with time zone) AS decision_quarter,
    EXTRACT(year FROM dd.made_public_date) AS decision_year,
    EXTRACT(quarter FROM dd.made_public_date) AS quarter_num,
    count(*) AS total_proposals,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%BIFALL%'::text) OR (upper((dpd.chamber)::text) ~~ '%GODKÄNT%'::text) OR (upper((dpd.chamber)::text) ~~ '%BIFALLA%'::text))) AS approved_proposals,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%AVSLAG%'::text) OR (upper((dpd.chamber)::text) ~~ '%AVSLÅ%'::text))) AS rejected_proposals,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%ÅTERFÖRVISNING%'::text) OR (upper((dpd.chamber)::text) ~~ '%ÅTERFÖRVISA%'::text))) AS referred_back_proposals,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%UTSKOTT%'::text) AND (upper((dpd.chamber)::text) !~~ '%ÅTERFÖRVISNING%'::text))) AS committee_referral_proposals,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) !~~ '%BIFALL%'::text) AND (upper((dpd.chamber)::text) !~~ '%AVSLAG%'::text) AND (upper((dpd.chamber)::text) !~~ '%GODKÄNT%'::text) AND (upper((dpd.chamber)::text) !~~ '%BIFALLA%'::text) AND (upper((dpd.chamber)::text) !~~ '%AVSLÅ%'::text) AND (upper((dpd.chamber)::text) !~~ '%ÅTERFÖRVISNING%'::text) AND (upper((dpd.chamber)::text) !~~ '%ÅTERFÖRVISA%'::text) AND (upper((dpd.chamber)::text) !~~ '%UTSKOTT%'::text))) AS other_decisions,
    round(((100.0 * (count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%BIFALL%'::text) OR (upper((dpd.chamber)::text) ~~ '%GODKÄNT%'::text) OR (upper((dpd.chamber)::text) ~~ '%BIFALLA%'::text))))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS approval_rate,
    round(((100.0 * (count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%AVSLAG%'::text) OR (upper((dpd.chamber)::text) ~~ '%AVSLÅ%'::text))))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS rejection_rate,
    round(((100.0 * (count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%UTSKOTT%'::text) AND (upper((dpd.chamber)::text) !~~ '%ÅTERFÖRVISNING%'::text))))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS committee_referral_rate,
    min(dd.made_public_date) AS earliest_proposal_date,
    max(dd.made_public_date) AS latest_proposal_date
   FROM (((public.document_data dd
     JOIN public.document_status_container dsc ON (((dsc.document_document_status_con_0)::text = (dd.id)::text)))
     JOIN public.document_proposal_container dpc ON ((dpc.hjid = dsc.document_proposal_document_s_0)))
     JOIN public.document_proposal_data dpd ON ((dpd.hjid = dpc.proposal_document_proposal_c_0)))
  WHERE (((dd.document_type)::text = 'prop'::text) AND (dd.org IS NOT NULL) AND (dpd.committee IS NOT NULL) AND (dpd.chamber IS NOT NULL) AND (dd.made_public_date IS NOT NULL) AND (length((dpd.chamber)::text) >= 6) AND (length((dpd.chamber)::text) <= 29))
  GROUP BY dd.org, dpd.committee, dpd.decision_type, (date_trunc('quarter'::text, (dd.made_public_date)::timestamp with time zone)), (EXTRACT(year FROM dd.made_public_date)), (EXTRACT(quarter FROM dd.made_public_date))
 HAVING (count(*) > 0)
  ORDER BY (EXTRACT(year FROM dd.made_public_date)) DESC, (EXTRACT(quarter FROM dd.made_public_date)) DESC, dd.org, dpd.committee;


--
-- Name: view_ministry_effectiveness_trends; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_ministry_effectiveness_trends AS
 WITH ministry_base AS (
         SELECT DISTINCT assignment_data.org_code,
            lower((assignment_data.org_code)::text) AS org_code_lower,
            assignment_data.org_code AS short_code,
            assignment_data.detail AS name
           FROM public.assignment_data
          WHERE (((assignment_data.assignment_type)::text = 'Departement'::text) AND (assignment_data.org_code IS NOT NULL))
        ), ministry_document_data AS (
         SELECT dsc.hjid AS id,
            dd.document_type,
            dd.made_public_date,
            dd.org,
            dpr.person_reference_id
           FROM (((public.document_status_container dsc
             LEFT JOIN public.document_data dd ON (((dsc.document_document_status_con_0)::text = (dd.id)::text)))
             LEFT JOIN public.document_person_reference_co_0 dprc ON ((dsc.hjid = dprc.hjid)))
             LEFT JOIN public.document_person_reference_da_0 dpr ON ((dpr.document_person_reference_li_1 = dprc.hjid)))
          WHERE (dd.made_public_date IS NOT NULL)
        ), ministry_quarterly_metrics AS (
         SELECT m.org_code,
            m.short_code,
            m.name,
            date_trunc('quarter'::text, (doc.made_public_date)::timestamp with time zone) AS period_start,
            count(DISTINCT doc.id) AS documents_produced,
            count(DISTINCT
                CASE
                    WHEN (lower((doc.document_type)::text) = 'prop'::text) THEN doc.id
                    ELSE NULL::bigint
                END) AS propositions,
            count(DISTINCT
                CASE
                    WHEN (lower((doc.document_type)::text) = 'ds'::text) THEN doc.id
                    ELSE NULL::bigint
                END) AS government_bills,
            count(DISTINCT
                CASE
                    WHEN (lower((doc.document_type)::text) = ANY (ARRAY['prop'::text, 'ds'::text])) THEN doc.id
                    ELSE NULL::bigint
                END) AS legislative_documents,
            count(DISTINCT doc.person_reference_id) AS active_members
           FROM (ministry_base m
             LEFT JOIN ministry_document_data doc ON (((lower((doc.org)::text) = m.org_code_lower) AND (doc.made_public_date >= (CURRENT_DATE - '3 years'::interval)))))
          GROUP BY m.org_code, m.short_code, m.name, (date_trunc('quarter'::text, (doc.made_public_date)::timestamp with time zone))
        ), trend_calculations AS (
         SELECT ministry_quarterly_metrics.org_code,
            ministry_quarterly_metrics.short_code,
            ministry_quarterly_metrics.name,
            ministry_quarterly_metrics.period_start,
            ministry_quarterly_metrics.documents_produced,
            ministry_quarterly_metrics.propositions,
            ministry_quarterly_metrics.government_bills,
            ministry_quarterly_metrics.legislative_documents,
            ministry_quarterly_metrics.active_members,
            lag(ministry_quarterly_metrics.documents_produced, 1) OVER (PARTITION BY ministry_quarterly_metrics.org_code ORDER BY ministry_quarterly_metrics.period_start) AS prev_documents,
            lag(ministry_quarterly_metrics.active_members, 1) OVER (PARTITION BY ministry_quarterly_metrics.org_code ORDER BY ministry_quarterly_metrics.period_start) AS prev_members,
            lag(ministry_quarterly_metrics.legislative_documents, 1) OVER (PARTITION BY ministry_quarterly_metrics.org_code ORDER BY ministry_quarterly_metrics.period_start) AS prev_legislative,
            round(avg(ministry_quarterly_metrics.documents_produced) OVER (PARTITION BY ministry_quarterly_metrics.org_code ORDER BY ministry_quarterly_metrics.period_start ROWS BETWEEN 3 PRECEDING AND CURRENT ROW), 2) AS ma_4quarter_documents,
            round(avg(ministry_quarterly_metrics.legislative_documents) OVER (PARTITION BY ministry_quarterly_metrics.org_code ORDER BY ministry_quarterly_metrics.period_start ROWS BETWEEN 3 PRECEDING AND CURRENT ROW), 2) AS ma_4quarter_legislative,
            round(avg((ministry_quarterly_metrics.active_members)::numeric) OVER (PARTITION BY ministry_quarterly_metrics.org_code ORDER BY ministry_quarterly_metrics.period_start ROWS BETWEEN 3 PRECEDING AND CURRENT ROW), 2) AS ma_4quarter_members
           FROM ministry_quarterly_metrics
          WHERE (ministry_quarterly_metrics.period_start IS NOT NULL)
        )
 SELECT org_code,
    short_code,
    name,
    period_start,
    (((period_start + '3 mons'::interval) - '1 day'::interval))::date AS period_end,
    EXTRACT(year FROM period_start) AS year,
    EXTRACT(quarter FROM period_start) AS quarter,
    documents_produced,
    propositions,
    government_bills,
    legislative_documents,
    active_members,
    (documents_produced - COALESCE(prev_documents, documents_produced)) AS document_change,
    (active_members - COALESCE(prev_members, active_members)) AS member_change,
    (legislative_documents - COALESCE(prev_legislative, legislative_documents)) AS legislative_change,
    ma_4quarter_documents,
    ma_4quarter_legislative,
    ma_4quarter_members,
    round(((documents_produced)::numeric / (NULLIF(active_members, 0))::numeric), 2) AS documents_per_member,
    round(((legislative_documents)::numeric / (NULLIF(active_members, 0))::numeric), 2) AS legislative_per_member,
        CASE
            WHEN (documents_produced >= 50) THEN 'HIGHLY_PRODUCTIVE'::text
            WHEN (documents_produced >= 30) THEN 'PRODUCTIVE'::text
            WHEN (documents_produced >= 15) THEN 'MODERATE'::text
            ELSE 'LOW_PRODUCTIVITY'::text
        END AS productivity_level,
        CASE
            WHEN (legislative_documents = 0) THEN 'INACTIVE_LEGISLATION'::text
            WHEN (legislative_documents >= 10) THEN 'HIGH_LEGISLATIVE_OUTPUT'::text
            WHEN (legislative_documents >= 5) THEN 'MODERATE_LEGISLATIVE_OUTPUT'::text
            ELSE 'LOW_LEGISLATIVE_OUTPUT'::text
        END AS legislative_status,
        CASE
            WHEN (active_members = 0) THEN 'NO_ACTIVE_STAFF'::text
            WHEN (active_members = 1) THEN 'CRITICALLY_UNDERSTAFFED'::text
            WHEN (active_members <= 3) THEN 'UNDERSTAFFED'::text
            WHEN (active_members <= 10) THEN 'ADEQUATELY_STAFFED'::text
            ELSE 'WELL_STAFFED'::text
        END AS staffing_status,
        CASE
            WHEN ((ma_4quarter_documents < (10)::numeric) AND ((documents_produced - COALESCE(prev_documents, documents_produced)) <= '-5'::integer)) THEN 'SEVERE_DECLINE'::text
            WHEN ((ma_4quarter_documents < (20)::numeric) AND ((documents_produced - COALESCE(prev_documents, documents_produced)) <= '-3'::integer)) THEN 'MODERATE_DECLINE'::text
            WHEN ((documents_produced - COALESCE(prev_documents, documents_produced)) < 0) THEN 'SLIGHT_DECLINE'::text
            WHEN ((documents_produced - COALESCE(prev_documents, documents_produced)) > 0) THEN 'IMPROVING'::text
            ELSE 'STABLE'::text
        END AS stagnation_indicator,
        CASE
            WHEN ((documents_produced >= 50) AND (active_members >= 10) AND (legislative_documents >= 10)) THEN 'Exceptional ministry performance - high output and capacity'::text
            WHEN ((documents_produced >= 30) AND (active_members >= 5)) THEN 'Strong ministry effectiveness'::text
            WHEN ((documents_produced < 15) OR (active_members <= 3) OR (legislative_documents = 0)) THEN 'Ministry performance concerns - investigation recommended'::text
            WHEN ((ma_4quarter_documents < (20)::numeric) AND ((documents_produced - COALESCE(prev_documents, documents_produced)) <= '-5'::integer)) THEN 'Significant ministry decline detected - intervention needed'::text
            ELSE 'Standard ministry operations'::text
        END AS effectiveness_assessment
   FROM trend_calculations
  ORDER BY org_code, period_start DESC;


--
-- Name: view_ministry_productivity_matrix; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_ministry_productivity_matrix AS
 WITH ministry_base AS (
         SELECT DISTINCT assignment_data.org_code,
            lower((assignment_data.org_code)::text) AS org_code_lower,
            assignment_data.org_code AS short_code,
            assignment_data.detail AS name
           FROM public.assignment_data
          WHERE (((assignment_data.assignment_type)::text = 'Departement'::text) AND (assignment_data.org_code IS NOT NULL))
        ), ministry_document_data AS (
         SELECT dsc.hjid AS id,
            dd.document_type,
            dd.made_public_date,
            dd.org,
            dpr.person_reference_id
           FROM (((public.document_status_container dsc
             LEFT JOIN public.document_data dd ON (((dsc.document_document_status_con_0)::text = (dd.id)::text)))
             LEFT JOIN public.document_person_reference_co_0 dprc ON ((dsc.hjid = dprc.hjid)))
             LEFT JOIN public.document_person_reference_da_0 dpr ON ((dpr.document_person_reference_li_1 = dprc.hjid)))
          WHERE (dd.made_public_date IS NOT NULL)
        ), ministry_annual_metrics AS (
         SELECT m.org_code,
            m.short_code,
            m.name,
            EXTRACT(year FROM doc.made_public_date) AS year,
            count(DISTINCT doc.id) AS documents_produced,
            count(DISTINCT
                CASE
                    WHEN (lower((doc.document_type)::text) = 'prop'::text) THEN doc.id
                    ELSE NULL::bigint
                END) AS propositions,
            count(DISTINCT
                CASE
                    WHEN (lower((doc.document_type)::text) = 'ds'::text) THEN doc.id
                    ELSE NULL::bigint
                END) AS government_bills,
            count(DISTINCT doc.person_reference_id) AS unique_contributors,
            min(doc.made_public_date) AS earliest_document,
            max(doc.made_public_date) AS latest_document
           FROM (ministry_base m
             LEFT JOIN ministry_document_data doc ON (((lower((doc.org)::text) = m.org_code_lower) AND (doc.made_public_date >= (CURRENT_DATE - '3 years'::interval)))))
          GROUP BY m.org_code, m.short_code, m.name, (EXTRACT(year FROM doc.made_public_date))
        ), productivity_benchmarks AS (
         SELECT ministry_annual_metrics.year,
            percentile_cont((0.25)::double precision) WITHIN GROUP (ORDER BY ((ministry_annual_metrics.documents_produced)::double precision)) AS p25_documents,
            percentile_cont((0.50)::double precision) WITHIN GROUP (ORDER BY ((ministry_annual_metrics.documents_produced)::double precision)) AS median_documents,
            percentile_cont((0.75)::double precision) WITHIN GROUP (ORDER BY ((ministry_annual_metrics.documents_produced)::double precision)) AS p75_documents,
            avg(ministry_annual_metrics.documents_produced) AS avg_documents
           FROM ministry_annual_metrics
          WHERE (ministry_annual_metrics.year IS NOT NULL)
          GROUP BY ministry_annual_metrics.year
        )
 SELECT mam.org_code,
    mam.short_code,
    mam.name,
    mam.year,
    mam.documents_produced,
    mam.propositions,
    mam.government_bills,
    mam.unique_contributors,
    mam.earliest_document,
    mam.latest_document,
    pb.median_documents,
    pb.avg_documents,
    pb.p25_documents,
    pb.p75_documents,
    round(((((mam.documents_produced)::numeric - pb.avg_documents) / NULLIF(pb.avg_documents, (0)::numeric)) * (100)::numeric), 2) AS pct_vs_average,
        CASE
            WHEN (((mam.documents_produced)::numeric)::double precision >= pb.p75_documents) THEN 'TOP_QUARTILE'::text
            WHEN (((mam.documents_produced)::numeric)::double precision >= pb.median_documents) THEN 'ABOVE_MEDIAN'::text
            WHEN (((mam.documents_produced)::numeric)::double precision >= pb.p25_documents) THEN 'BELOW_MEDIAN'::text
            ELSE 'BOTTOM_QUARTILE'::text
        END AS productivity_quartile,
        CASE
            WHEN (((mam.documents_produced)::numeric)::double precision >= pb.p75_documents) THEN 'High-performing ministry'::text
            WHEN (((mam.documents_produced)::numeric)::double precision < pb.p25_documents) THEN 'Underperforming ministry - review needed'::text
            ELSE 'Standard ministry performance'::text
        END AS performance_assessment
   FROM (ministry_annual_metrics mam
     LEFT JOIN productivity_benchmarks pb ON ((pb.year = mam.year)))
  WHERE (mam.year IS NOT NULL)
  ORDER BY mam.year DESC, mam.documents_produced DESC;


--
-- Name: view_ministry_risk_evolution; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_ministry_risk_evolution AS
 WITH ministry_base AS (
         SELECT DISTINCT assignment_data.org_code,
            lower((assignment_data.org_code)::text) AS org_code_lower,
            assignment_data.detail AS name
           FROM public.assignment_data
          WHERE (((assignment_data.assignment_type)::text = 'Departement'::text) AND (assignment_data.org_code IS NOT NULL))
        ), quarterly_periods AS (
         SELECT date_trunc('quarter'::text, (CURRENT_DATE - make_interval(months => n.n))) AS period_start
           FROM generate_series(0, 21, 3) n(n)
        ), ministry_quarters AS (
         SELECT m.org_code,
            m.org_code_lower,
            m.name,
            qp.period_start AS assessment_period
           FROM (ministry_base m
             CROSS JOIN quarterly_periods qp)
        ), ministry_document_data AS (
         SELECT dsc.hjid AS id,
            dd.document_type,
            dd.made_public_date,
            dd.org,
            dpr.person_reference_id
           FROM (((public.document_status_container dsc
             LEFT JOIN public.document_data dd ON (((dsc.document_document_status_con_0)::text = (dd.id)::text)))
             LEFT JOIN public.document_person_reference_co_0 dprc ON ((dsc.hjid = dprc.hjid)))
             LEFT JOIN public.document_person_reference_da_0 dpr ON ((dpr.document_person_reference_li_1 = dprc.hjid)))
          WHERE (dd.made_public_date IS NOT NULL)
        ), quarterly_performance AS (
         SELECT mq.org_code,
            mq.name,
            mq.assessment_period,
            count(DISTINCT doc.id) AS documents_produced,
            count(DISTINCT
                CASE
                    WHEN (lower((doc.document_type)::text) = ANY (ARRAY['prop'::text, 'ds'::text])) THEN doc.id
                    ELSE NULL::bigint
                END) AS legislative_count,
            count(DISTINCT doc.person_reference_id) AS active_members
           FROM (ministry_quarters mq
             LEFT JOIN ministry_document_data doc ON (((lower((doc.org)::text) = mq.org_code_lower) AND (date_trunc('quarter'::text, (doc.made_public_date)::timestamp with time zone) = mq.assessment_period) AND (doc.made_public_date >= (CURRENT_DATE - '2 years'::interval)))))
          GROUP BY mq.org_code, mq.name, mq.assessment_period
        ), risk_calculations AS (
         SELECT quarterly_performance.org_code,
            quarterly_performance.name,
            quarterly_performance.assessment_period,
            quarterly_performance.documents_produced,
            quarterly_performance.legislative_count,
            quarterly_performance.active_members,
            lag(quarterly_performance.documents_produced, 1) OVER (PARTITION BY quarterly_performance.org_code ORDER BY quarterly_performance.assessment_period) AS prev_documents,
            lag(quarterly_performance.legislative_count, 1) OVER (PARTITION BY quarterly_performance.org_code ORDER BY quarterly_performance.assessment_period) AS prev_legislative,
            lag(quarterly_performance.active_members, 1) OVER (PARTITION BY quarterly_performance.org_code ORDER BY quarterly_performance.assessment_period) AS prev_members,
            avg(quarterly_performance.documents_produced) OVER (PARTITION BY quarterly_performance.org_code ORDER BY quarterly_performance.assessment_period ROWS BETWEEN 3 PRECEDING AND CURRENT ROW) AS rolling_avg_documents
           FROM quarterly_performance
        )
 SELECT org_code,
    name,
    assessment_period,
    (((assessment_period + '3 mons'::interval) - '1 day'::interval))::date AS period_end,
    EXTRACT(year FROM assessment_period) AS year,
    EXTRACT(quarter FROM assessment_period) AS quarter,
    documents_produced,
    legislative_count,
    active_members,
    (documents_produced - COALESCE(prev_documents, documents_produced)) AS document_trend,
    (legislative_count - COALESCE(prev_legislative, legislative_count)) AS legislative_trend,
    (active_members - COALESCE(prev_members, active_members)) AS staffing_trend,
    rolling_avg_documents,
        CASE
            WHEN ((documents_produced = 0) AND (active_members = 0)) THEN 'CRITICAL'::text
            WHEN ((documents_produced < 5) OR (active_members <= 1)) THEN 'HIGH'::text
            WHEN ((documents_produced < 10) OR (active_members <= 2)) THEN 'MEDIUM'::text
            WHEN ((documents_produced - COALESCE(prev_documents, documents_produced)) < '-5'::integer) THEN 'ELEVATED'::text
            ELSE 'LOW'::text
        END AS risk_level,
        CASE
            WHEN ((documents_produced = 0) AND (active_members = 0)) THEN 'Critical: No activity detected - immediate review required'::text
            WHEN (documents_produced < 5) THEN 'High Risk: Minimal document production - capacity concerns'::text
            WHEN (active_members <= 1) THEN 'High Risk: Critically understaffed ministry'::text
            WHEN ((documents_produced - COALESCE(prev_documents, documents_produced)) < '-5'::integer) THEN 'Elevated Risk: Significant production decline detected'::text
            WHEN ((documents_produced >= 20) AND (active_members >= 5)) THEN 'Low Risk: Healthy ministry operations'::text
            ELSE 'Medium Risk: Standard operations with minor concerns'::text
        END AS risk_assessment
   FROM risk_calculations
  ORDER BY org_code, assessment_period DESC;


--
-- Name: view_riksdagen_vote_data_ballot_party_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary AS
 WITH party_vote_counts AS (
         SELECT vote_data.embedded_id_ballot_id,
            vote_data.embedded_id_concern,
            vote_data.embedded_id_issue,
            vote_data.party,
            count(*) AS member_count,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'JA'::text) THEN 1
                    ELSE 0
                END) AS yes_count,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'NEJ'::text) THEN 1
                    ELSE 0
                END) AS no_count,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'AVSTÅR'::text) THEN 1
                    ELSE 0
                END) AS abstain_count,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'FRÅNVARANDE'::text) THEN 1
                    ELSE 0
                END) AS absent_count,
            sum(
                CASE
                    WHEN ((vote_data.gender)::text = 'MAN'::text) THEN 1
                    ELSE 0
                END) AS male_count,
            round(avg(vote_data.born_year)) AS avg_party_born_year,
            max((vote_data.ballot_type)::text) AS ballot_type,
            max((vote_data.rm)::text) AS rm,
            max((vote_data.label)::text) AS label,
            max(vote_data.vote_date) AS vote_date
           FROM public.vote_data
          GROUP BY vote_data.embedded_id_ballot_id, vote_data.embedded_id_concern, vote_data.embedded_id_issue, vote_data.party
        )
 SELECT pvc.embedded_id_ballot_id,
    pvc.embedded_id_concern,
    pvc.embedded_id_issue,
    pvc.party AS embedded_id_party,
    max(s.avg_born_year) AS avg_born_year,
    max(s.total_votes) AS total_votes,
    max(s.yes_votes) AS yes_votes,
    max(s.no_votes) AS no_votes,
    max(s.abstain_votes) AS abstain_votes,
    max(s.absent_votes) AS absent_votes,
    bool_or(s.approved) AS approved,
    (((pvc.yes_count > pvc.no_count) AND bool_or(s.approved)) OR ((pvc.no_count > pvc.yes_count) AND (NOT bool_or(s.approved)))) AS party_won,
    bool_or(s.no_winner) AS no_winner,
    max(s.percentage_yes) AS percentage_yes,
    max(s.percentage_no) AS percentage_no,
    max(s.percentage_absent) AS percentage_absent,
    max(s.percentage_abstain) AS percentage_abstain,
    max(s.percentage_male) AS percentage_male,
    pvc.ballot_type,
    pvc.rm,
    pvc.label,
    pvc.vote_date,
    pvc.avg_party_born_year AS party_avg_born_year,
    pvc.member_count AS party_total_votes,
    pvc.yes_count AS party_yes_votes,
    pvc.no_count AS party_no_votes,
    pvc.abstain_count AS party_abstain_votes,
    pvc.absent_count AS party_absent_votes,
    (pvc.yes_count > pvc.no_count) AS party_approved,
    (pvc.yes_count = pvc.no_count) AS party_no_winner,
    round(((100.0 * (pvc.yes_count)::numeric) / (NULLIF(pvc.member_count, 0))::numeric), 2) AS party_percentage_yes,
    round(((100.0 * (pvc.no_count)::numeric) / (NULLIF(pvc.member_count, 0))::numeric), 2) AS party_percentage_no,
    round(((100.0 * (pvc.absent_count)::numeric) / (NULLIF(pvc.member_count, 0))::numeric), 2) AS party_percentage_absent,
    round(((100.0 * (pvc.abstain_count)::numeric) / (NULLIF(pvc.member_count, 0))::numeric), 2) AS party_percentage_abstain,
    round(((100.0 * (pvc.male_count)::numeric) / (NULLIF(pvc.member_count, 0))::numeric), 2) AS party_percentage_male
   FROM (party_vote_counts pvc
     LEFT JOIN public.view_riksdagen_vote_data_ballot_summary s ON ((((pvc.embedded_id_ballot_id)::text = (s.embedded_id_ballot_id)::text) AND ((pvc.embedded_id_issue)::text = (s.embedded_id_issue)::text) AND ((pvc.embedded_id_concern)::text = (s.embedded_id_concern)::text))))
  GROUP BY pvc.embedded_id_ballot_id, pvc.embedded_id_issue, pvc.embedded_id_concern, pvc.party, pvc.member_count, pvc.yes_count, pvc.no_count, pvc.abstain_count, pvc.absent_count, pvc.male_count, pvc.avg_party_born_year, pvc.ballot_type, pvc.rm, pvc.label, pvc.vote_date
  ORDER BY pvc.vote_date
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_politician_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary AS
 SELECT vote_data.embedded_id_ballot_id,
    vote_data.embedded_id_intressent_id,
    vote_data.embedded_id_concern,
    vote_data.embedded_id_issue,
    vote_data.party,
    max((vote_data.first_name)::text) AS first_name,
    max((vote_data.last_name)::text) AS last_name,
    max((vote_data.gender)::text) AS gender,
    max(vote_data.born_year) AS born_year,
    max(view_riksdagen_vote_data_ballot_party_summary.avg_born_year) AS avg_born_year,
    max(view_riksdagen_vote_data_ballot_party_summary.total_votes) AS total_votes,
    max(view_riksdagen_vote_data_ballot_party_summary.yes_votes) AS yes_votes,
    max(view_riksdagen_vote_data_ballot_party_summary.no_votes) AS no_votes,
    max(view_riksdagen_vote_data_ballot_party_summary.abstain_votes) AS abstain_votes,
    max(view_riksdagen_vote_data_ballot_party_summary.absent_votes) AS absent_votes,
    bool_or(view_riksdagen_vote_data_ballot_party_summary.approved) AS approved,
    max((vote_data.vote)::text) AS vote,
        CASE
            WHEN ((((vote_data.vote)::text = 'JA'::text) AND bool_or(view_riksdagen_vote_data_ballot_party_summary.approved)) OR (((vote_data.vote)::text = 'NEJ'::text) AND (NOT bool_or(view_riksdagen_vote_data_ballot_party_summary.approved)))) THEN true
            ELSE false
        END AS won,
        CASE
            WHEN ((((vote_data.vote)::text = 'NEJ'::text) AND bool_or(view_riksdagen_vote_data_ballot_party_summary.party_approved)) OR (((vote_data.vote)::text = 'JA'::text) AND (NOT bool_or(view_riksdagen_vote_data_ballot_party_summary.party_approved)))) THEN true
            ELSE false
        END AS rebel,
    bool_or(view_riksdagen_vote_data_ballot_party_summary.party_won) AS party_won,
    bool_or(view_riksdagen_vote_data_ballot_party_summary.no_winner) AS no_winner,
    max(view_riksdagen_vote_data_ballot_party_summary.percentage_yes) AS percentage_yes,
    max(view_riksdagen_vote_data_ballot_party_summary.percentage_no) AS percentage_no,
    max(view_riksdagen_vote_data_ballot_party_summary.percentage_absent) AS percentage_absent,
    max(view_riksdagen_vote_data_ballot_party_summary.percentage_abstain) AS percentage_abstain,
    max(view_riksdagen_vote_data_ballot_party_summary.percentage_male) AS percentage_male,
    max((vote_data.ballot_type)::text) AS ballot_type,
    max((vote_data.rm)::text) AS rm,
    max((vote_data.label)::text) AS label,
    max(vote_data.vote_date) AS vote_date,
    max(view_riksdagen_vote_data_ballot_party_summary.party_avg_born_year) AS party_avg_born_year,
    max(view_riksdagen_vote_data_ballot_party_summary.party_total_votes) AS party_total_votes,
    max(view_riksdagen_vote_data_ballot_party_summary.party_yes_votes) AS party_yes_votes,
    max(view_riksdagen_vote_data_ballot_party_summary.party_no_votes) AS party_no_votes,
    max(view_riksdagen_vote_data_ballot_party_summary.party_abstain_votes) AS party_abstain_votes,
    max(view_riksdagen_vote_data_ballot_party_summary.party_absent_votes) AS party_absent_votes,
    bool_or(view_riksdagen_vote_data_ballot_party_summary.party_approved) AS party_approved,
    bool_or(view_riksdagen_vote_data_ballot_party_summary.party_no_winner) AS party_no_winner,
    max(view_riksdagen_vote_data_ballot_party_summary.party_percentage_yes) AS party_percentage_yes,
    max(view_riksdagen_vote_data_ballot_party_summary.party_percentage_no) AS party_percentage_no,
    max(view_riksdagen_vote_data_ballot_party_summary.party_percentage_absent) AS party_percentage_absent,
    max(view_riksdagen_vote_data_ballot_party_summary.party_percentage_abstain) AS party_percentage_abstain,
    max(view_riksdagen_vote_data_ballot_party_summary.party_percentage_male) AS party_percentage_male
   FROM (public.vote_data
     LEFT JOIN public.view_riksdagen_vote_data_ballot_party_summary ON ((((vote_data.embedded_id_ballot_id)::text = (view_riksdagen_vote_data_ballot_party_summary.embedded_id_ballot_id)::text) AND ((vote_data.embedded_id_issue)::text = (view_riksdagen_vote_data_ballot_party_summary.embedded_id_issue)::text) AND ((vote_data.embedded_id_concern)::text = (view_riksdagen_vote_data_ballot_party_summary.embedded_id_concern)::text) AND ((vote_data.party)::text = (view_riksdagen_vote_data_ballot_party_summary.embedded_id_party)::text))))
  GROUP BY vote_data.embedded_id_ballot_id, vote_data.embedded_id_issue, vote_data.embedded_id_concern, vote_data.embedded_id_intressent_id, vote_data.party, vote_data.vote
  ORDER BY (max(vote_data.vote_date))
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_politician_summary_daily; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary_daily AS
 SELECT vote_date AS embedded_id_vote_date,
    embedded_id_intressent_id,
    max(first_name) AS first_name,
    max(last_name) AS last_name,
    max(gender) AS gender,
    max(born_year) AS born_year,
    max((party)::text) AS party,
    count(*) AS number_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    max(total_votes) AS avg_total_votes,
    round(avg(yes_votes), 2) AS avg_yes_votes,
    round(avg(no_votes), 2) AS avg_no_votes,
    round(avg(abstain_votes), 2) AS avg_abstain_votes,
    round(avg(absent_votes), 2) AS avg_absent_votes,
    round(((100.0 * (sum(
        CASE
            WHEN approved THEN 1
            ELSE 0
        END))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS percentage_approved,
    round(avg(percentage_yes), 2) AS avg_percentage_yes,
    round(avg(percentage_no), 2) AS avg_percentage_no,
    round(avg(percentage_absent), 2) AS avg_percentage_absent,
    round(avg(percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(percentage_male), 2) AS avg_percentage_male,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(party_total_votes) AS party_total_votes,
    sum(party_yes_votes) AS party_yes_votes,
    sum(party_no_votes) AS party_no_votes,
    sum(party_abstain_votes) AS party_abstain_votes,
    sum(party_absent_votes) AS party_absent_votes,
    sum(
        CASE
            WHEN (vote = 'JA'::text) THEN 1
            ELSE 0
        END) AS politician_yes_votes,
    sum(
        CASE
            WHEN (vote = 'NEJ'::text) THEN 1
            ELSE 0
        END) AS politician_no_votes,
    sum(
        CASE
            WHEN (vote = 'AVSTÅR'::text) THEN 1
            ELSE 0
        END) AS politician_abstain_votes,
    sum(
        CASE
            WHEN (vote = 'FRÅNVARANDE'::text) THEN 1
            ELSE 0
        END) AS politician_absent_votes,
    round(((100.0 * (sum(
        CASE
            WHEN (vote = 'JA'::text) THEN 1
            ELSE 0
        END))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS politician_percentage_yes,
    round(((100.0 * (sum(
        CASE
            WHEN (vote = 'NEJ'::text) THEN 1
            ELSE 0
        END))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS politician_percentage_no,
    round(((100.0 * (sum(
        CASE
            WHEN (vote = 'FRÅNVARANDE'::text) THEN 1
            ELSE 0
        END))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS politician_percentage_absent,
    round(((100.0 * (sum(
        CASE
            WHEN (vote = 'AVSTÅR'::text) THEN 1
            ELSE 0
        END))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS politician_percentage_abstain,
    round(avg(party_avg_born_year), 0) AS party_avg_born_year,
    round(avg(party_percentage_male), 2) AS party_avg_percentage_male,
    round(((100.0 * sum(party_yes_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_yes,
    round(((100.0 * sum(party_no_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_no,
    round(((100.0 * sum(party_abstain_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_abstain,
    round(((100.0 * sum(party_absent_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_absent,
    round(((100.0 * sum(yes_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_absent,
    sum(
        CASE
            WHEN party_won THEN 1
            ELSE 0
        END) AS party_won_total,
    round((((100 * sum(
        CASE
            WHEN party_won THEN 1
            ELSE 0
        END)) / NULLIF(count(*), 0)))::numeric, 2) AS party_won_percentage,
    sum(
        CASE
            WHEN approved THEN 1
            ELSE 0
        END) AS approved_total,
    round((((100 * sum(
        CASE
            WHEN approved THEN 1
            ELSE 0
        END)) / NULLIF(count(*), 0)))::numeric, 2) AS approved_percentage,
    round((((100 * sum(
        CASE
            WHEN won THEN 1
            ELSE 0
        END)) / NULLIF(count(*), 0)))::numeric, 2) AS won_percentage,
    sum(
        CASE
            WHEN won THEN 1
            ELSE 0
        END) AS won_total,
    round((((100 * sum(
        CASE
            WHEN rebel THEN 1
            ELSE 0
        END)) / NULLIF(count(*), 0)))::numeric, 2) AS rebel_percentage,
    sum(
        CASE
            WHEN rebel THEN 1
            ELSE 0
        END) AS rebel_total
   FROM public.view_riksdagen_vote_data_ballot_politician_summary
  GROUP BY embedded_id_intressent_id, vote_date
  WITH NO DATA;


--
-- Name: view_party_effectiveness_trends; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_party_effectiveness_trends AS
 WITH quarterly_party_voting AS (
         SELECT view_riksdagen_vote_data_ballot_politician_summary_daily.party,
            date_trunc('quarter'::text, (view_riksdagen_vote_data_ballot_politician_summary_daily.embedded_id_vote_date)::timestamp with time zone) AS period_start,
            count(DISTINCT view_riksdagen_vote_data_ballot_politician_summary_daily.embedded_id_intressent_id) AS active_members,
            sum(view_riksdagen_vote_data_ballot_politician_summary_daily.number_ballots) AS total_ballots,
            sum(view_riksdagen_vote_data_ballot_politician_summary_daily.total_votes) AS total_votes,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.avg_percentage_absent), 2) AS avg_absence_rate,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.won_percentage), 2) AS avg_win_rate,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.rebel_percentage), 2) AS avg_rebel_rate,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.avg_percentage_yes), 2) AS avg_yes_rate
           FROM public.view_riksdagen_vote_data_ballot_politician_summary_daily
          WHERE ((view_riksdagen_vote_data_ballot_politician_summary_daily.embedded_id_vote_date >= (CURRENT_DATE - '3 years'::interval)) AND (view_riksdagen_vote_data_ballot_politician_summary_daily.party IS NOT NULL))
          GROUP BY view_riksdagen_vote_data_ballot_politician_summary_daily.party, (date_trunc('quarter'::text, (view_riksdagen_vote_data_ballot_politician_summary_daily.embedded_id_vote_date)::timestamp with time zone))
        ), party_documents AS (
         SELECT view_riksdagen_politician_document.party_short_code AS party,
            date_trunc('quarter'::text, (view_riksdagen_politician_document.made_public_date)::timestamp with time zone) AS period_start,
            count(*) AS documents_produced,
            count(DISTINCT
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = 'Motion'::text) THEN view_riksdagen_politician_document.doc_id
                    ELSE NULL::character varying
                END) AS motions_count,
            count(DISTINCT view_riksdagen_politician_document.person_reference_id) AS active_document_authors
           FROM public.view_riksdagen_politician_document
          WHERE ((view_riksdagen_politician_document.made_public_date >= (CURRENT_DATE - '3 years'::interval)) AND (view_riksdagen_politician_document.party_short_code IS NOT NULL))
          GROUP BY view_riksdagen_politician_document.party_short_code, (date_trunc('quarter'::text, (view_riksdagen_politician_document.made_public_date)::timestamp with time zone))
        ), party_violations AS (
         SELECT pd.party,
            date_trunc('quarter'::text, rv.detected_date) AS period_start,
            count(DISTINCT rv.id) AS violation_count,
            count(DISTINCT rv.reference_id) AS members_with_violations
           FROM (public.rule_violation rv
             JOIN public.person_data pd ON (((rv.reference_id)::text = (pd.id)::text)))
          WHERE (((rv.resource_type)::text = 'POLITICIAN'::text) AND ((rv.status)::text = 'ACTIVE'::text) AND (rv.detected_date >= (CURRENT_DATE - '3 years'::interval)) AND (pd.party IS NOT NULL))
          GROUP BY pd.party, (date_trunc('quarter'::text, rv.detected_date))
        ), trend_analysis AS (
         SELECT qpv.party,
            qpv.period_start,
            qpv.active_members,
            qpv.total_ballots,
            qpv.total_votes,
            qpv.avg_absence_rate,
            qpv.avg_win_rate,
            qpv.avg_rebel_rate,
            qpv.avg_yes_rate,
            COALESCE(pds.documents_produced, (0)::bigint) AS documents_produced,
            COALESCE(pds.motions_count, (0)::bigint) AS motions_count,
            COALESCE(pds.active_document_authors, (0)::bigint) AS active_document_authors,
            COALESCE(pvs.violation_count, (0)::bigint) AS violation_count,
            COALESCE(pvs.members_with_violations, (0)::bigint) AS members_with_violations,
            lag(qpv.avg_win_rate, 1) OVER (PARTITION BY qpv.party ORDER BY qpv.period_start) AS prev_win_rate,
            lag(qpv.avg_absence_rate, 1) OVER (PARTITION BY qpv.party ORDER BY qpv.period_start) AS prev_absence_rate,
            lag(qpv.active_members, 1) OVER (PARTITION BY qpv.party ORDER BY qpv.period_start) AS prev_members,
            round(avg(qpv.avg_win_rate) OVER (PARTITION BY qpv.party ORDER BY qpv.period_start ROWS BETWEEN 3 PRECEDING AND CURRENT ROW), 2) AS ma_4quarter_win_rate,
            round(avg(qpv.avg_absence_rate) OVER (PARTITION BY qpv.party ORDER BY qpv.period_start ROWS BETWEEN 3 PRECEDING AND CURRENT ROW), 2) AS ma_4quarter_absence
           FROM ((quarterly_party_voting qpv
             LEFT JOIN party_documents pds ON (((qpv.party = (pds.party)::text) AND (qpv.period_start = pds.period_start))))
             LEFT JOIN party_violations pvs ON (((qpv.party = (pvs.party)::text) AND (qpv.period_start = pvs.period_start))))
        )
 SELECT party,
    period_start,
    (((period_start + '3 mons'::interval) - '1 day'::interval))::date AS period_end,
    EXTRACT(year FROM period_start) AS year,
    EXTRACT(quarter FROM period_start) AS quarter,
    active_members,
    total_ballots,
    total_votes,
    avg_absence_rate,
    avg_win_rate,
    avg_rebel_rate,
    avg_yes_rate,
    documents_produced,
    motions_count,
    active_document_authors,
    violation_count,
    members_with_violations,
    round((avg_win_rate - prev_win_rate), 2) AS win_rate_trend,
    round((avg_absence_rate - prev_absence_rate), 2) AS absence_trend,
    (active_members - prev_members) AS member_change,
    ma_4quarter_win_rate,
    ma_4quarter_absence,
    round(((documents_produced)::numeric / (NULLIF(active_members, 0))::numeric), 2) AS docs_per_member,
    round((total_votes / (NULLIF(active_members, 0))::numeric), 2) AS votes_per_member,
    round(((violation_count)::numeric / (NULLIF(active_members, 0))::numeric), 2) AS violations_per_member,
        CASE
            WHEN ((avg_win_rate >= (75)::numeric) AND (avg_absence_rate < (10)::numeric)) THEN 'HIGH_PERFORMANCE'::text
            WHEN ((avg_win_rate >= (60)::numeric) AND (avg_absence_rate < (15)::numeric)) THEN 'GOOD_PERFORMANCE'::text
            WHEN ((avg_win_rate >= (45)::numeric) OR (avg_absence_rate < (20)::numeric)) THEN 'MODERATE_PERFORMANCE'::text
            ELSE 'LOW_PERFORMANCE'::text
        END AS performance_level,
        CASE
            WHEN (documents_produced >= 50) THEN 'HIGHLY_PRODUCTIVE'::text
            WHEN (documents_produced >= 20) THEN 'PRODUCTIVE'::text
            WHEN (documents_produced >= 10) THEN 'MODERATELY_PRODUCTIVE'::text
            ELSE 'LOW_PRODUCTIVITY'::text
        END AS productivity_level,
        CASE
            WHEN ((avg_win_rate >= (70)::numeric) AND (avg_absence_rate < (12)::numeric) AND (violation_count <= 2)) THEN 'Strong organizational effectiveness'::text
            WHEN ((avg_win_rate >= (55)::numeric) AND (avg_absence_rate < (18)::numeric)) THEN 'Solid party performance'::text
            WHEN ((avg_absence_rate >= (20)::numeric) OR (violation_count >= 5)) THEN 'Performance concerns detected'::text
            ELSE 'Standard party operations'::text
        END AS effectiveness_assessment
   FROM trend_analysis
  ORDER BY party, period_start DESC;


--
-- Name: view_riksdagen_vote_data_ballot_politician_summary_annual; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary_annual AS
 SELECT date(date_trunc('year'::text, (embedded_id_vote_date)::timestamp with time zone)) AS embedded_id_vote_date,
    embedded_id_intressent_id,
    max(first_name) AS first_name,
    max(last_name) AS last_name,
    max(gender) AS gender,
    max(born_year) AS born_year,
    max(party) AS party,
    sum(number_ballots) AS number_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(avg_percentage_yes), 2) AS avg_percentage_yes,
    round(avg(avg_percentage_no), 2) AS avg_percentage_no,
    round(avg(avg_percentage_absent), 2) AS avg_percentage_absent,
    round(avg(avg_percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(avg_percentage_male), 2) AS avg_percentage_male,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(party_total_votes) AS party_total_votes,
    sum(party_yes_votes) AS party_yes_votes,
    sum(party_no_votes) AS party_no_votes,
    sum(party_abstain_votes) AS party_abstain_votes,
    sum(party_absent_votes) AS party_absent_votes,
    round(avg(party_avg_born_year), 0) AS party_avg_born_year,
    round(avg(party_avg_percentage_male), 2) AS party_avg_percentage_male,
    round(((100.0 * sum(party_yes_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_yes,
    round(((100.0 * sum(party_no_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_no,
    round(((100.0 * sum(party_abstain_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_abstain,
    round(((100.0 * sum(party_absent_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_absent,
    sum(party_won_total) AS party_won_total,
    sum(politician_yes_votes) AS politician_yes_votes,
    sum(politician_no_votes) AS politician_no_votes,
    sum(politician_abstain_votes) AS politician_abstain_votes,
    sum(politician_absent_votes) AS politician_absent_votes,
    round(((100.0 * sum(politician_yes_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_yes,
    round(((100.0 * sum(politician_no_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_no,
    round(((100.0 * sum(politician_abstain_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_abstain,
    round(((100.0 * sum(politician_absent_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_absent,
    round((((100)::numeric * sum(won_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS won_percentage,
    sum(won_total) AS won_total,
    round((((100)::numeric * sum(rebel_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS rebel_percentage,
    sum(rebel_total) AS rebel_total,
    round((((100)::numeric * sum(party_won_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS party_won_percentage,
    sum(approved_total) AS approved_total,
    round((((100)::numeric * sum(approved_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS approved_percentage,
    round(((100.0 * sum(yes_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_absent,
    round(avg(percentage_approved), 2) AS avg_percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_politician_summary_daily
  GROUP BY (date(date_trunc('year'::text, (embedded_id_vote_date)::timestamp with time zone))), embedded_id_intressent_id
  WITH NO DATA;


--
-- Name: view_party_performance_metrics; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_party_performance_metrics AS
 WITH party_member_counts AS (
         SELECT person_data.party,
            count(*) FILTER (WHERE ((person_data.status)::text = 'active'::text)) AS active_members,
            count(*) FILTER (WHERE ((person_data.status)::text = 'inactive'::text)) AS inactive_members
           FROM public.person_data
          WHERE (person_data.party IS NOT NULL)
          GROUP BY person_data.party
        ), party_violations AS (
         SELECT p.party,
            count(DISTINCT rv.id) AS total_violations,
            count(DISTINCT rv.reference_id) AS members_with_violations,
            max(rv.detected_date) AS latest_violation
           FROM (public.person_data p
             JOIN public.rule_violation rv ON ((((rv.reference_id)::text = (p.id)::text) AND ((rv.resource_type)::text = 'POLITICIAN'::text) AND ((rv.status)::text = 'ACTIVE'::text))))
          WHERE (p.party IS NOT NULL)
          GROUP BY p.party
        ), party_voting_annual AS (
         SELECT view_riksdagen_vote_data_ballot_politician_summary_annual.party,
            sum(view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes) AS total_party_votes,
            avg(view_riksdagen_vote_data_ballot_politician_summary_annual.avg_percentage_absent) AS avg_absence_rate,
            avg(view_riksdagen_vote_data_ballot_politician_summary_annual.won_percentage) AS avg_win_rate,
            avg(view_riksdagen_vote_data_ballot_politician_summary_annual.rebel_percentage) AS avg_rebel_rate,
            avg(((100)::numeric - view_riksdagen_vote_data_ballot_politician_summary_annual.avg_percentage_absent)) AS avg_participation_rate
           FROM public.view_riksdagen_vote_data_ballot_politician_summary_annual
          WHERE ((view_riksdagen_vote_data_ballot_politician_summary_annual.embedded_id_vote_date >= date_trunc('year'::text, (CURRENT_DATE - '1 year'::interval))) AND (view_riksdagen_vote_data_ballot_politician_summary_annual.embedded_id_vote_date < date_trunc('year'::text, (CURRENT_DATE)::timestamp with time zone)) AND (view_riksdagen_vote_data_ballot_politician_summary_annual.party IS NOT NULL))
          GROUP BY view_riksdagen_vote_data_ballot_politician_summary_annual.party
        ), party_documents AS (
         SELECT p.party,
            count(DISTINCT vpd.id) AS documents_produced,
            count(DISTINCT vpd.id) FILTER (WHERE ((vpd.document_type)::text = 'Motion'::text)) AS motions_produced,
            count(DISTINCT vpd.id) FILTER (WHERE ((vpd.document_type)::text = 'Proposition'::text)) AS propositions_produced
           FROM (public.person_data p
             JOIN public.view_riksdagen_politician_document vpd ON ((((vpd.person_reference_id)::text = (p.id)::text) AND (vpd.made_public_date >= (CURRENT_DATE - '1 year'::interval)))))
          WHERE (p.party IS NOT NULL)
          GROUP BY p.party
        ), party_government_roles AS (
         SELECT p.party,
            count(DISTINCT p.id) FILTER (WHERE ((ad.assignment_type)::text = 'Departement'::text)) AS ministers_count,
            count(DISTINCT p.id) FILTER (WHERE (((ad.assignment_type)::text = 'Riksdagsorgan'::text) AND ((ad.role_code)::text = 'Ordförande'::text))) AS committee_chairs_count
           FROM (public.person_data p
             JOIN public.assignment_data ad ON (((ad.intressent_id)::text = (p.id)::text)))
          WHERE ((ad.to_date IS NULL) OR ((ad.to_date >= CURRENT_DATE) AND (p.party IS NOT NULL)))
          GROUP BY p.party
        ), performance_calculations AS (
         SELECT pp.short_code AS party,
            pp.party_name,
            pp.registered_date AS registration_date,
            pmc.active_members,
            pmc.inactive_members,
            pv.total_violations,
            pv.members_with_violations,
            pv.latest_violation,
            pva.total_party_votes,
            pva.avg_absence_rate,
            pva.avg_win_rate,
            pva.avg_rebel_rate,
            pva.avg_participation_rate,
            pd.documents_produced,
            pd.motions_produced,
            pd.propositions_produced,
            pgr.ministers_count,
            pgr.committee_chairs_count,
            ((((((COALESCE(pva.avg_participation_rate, (0)::numeric) * (25)::numeric) / 100.0) + ((COALESCE(pva.avg_win_rate, (0)::numeric) * (25)::numeric) / 100.0)) + ((((100)::numeric - COALESCE(pva.avg_rebel_rate, (0)::numeric)) * (20)::numeric) / 100.0)) + LEAST((
                CASE
                    WHEN (COALESCE(pmc.active_members, (0)::bigint) > 0) THEN ((COALESCE(pd.documents_produced, (0)::bigint))::numeric / (pmc.active_members)::numeric)
                    ELSE (0)::numeric
                END * (3)::numeric), (15)::numeric)) + ((15)::numeric - LEAST(((COALESCE(pv.total_violations, (0)::bigint))::numeric * 0.5), (15)::numeric))) AS calculated_performance_score
           FROM (((((public.sweden_political_party pp
             LEFT JOIN party_member_counts pmc ON (((pmc.party)::text = (pp.short_code)::text)))
             LEFT JOIN party_violations pv ON (((pv.party)::text = (pp.short_code)::text)))
             LEFT JOIN party_voting_annual pva ON ((pva.party = (pp.short_code)::text)))
             LEFT JOIN party_documents pd ON (((pd.party)::text = (pp.short_code)::text)))
             LEFT JOIN party_government_roles pgr ON (((pgr.party)::text = (pp.short_code)::text)))
          WHERE (pp.short_code IS NOT NULL)
        )
 SELECT party,
    party_name,
    registration_date,
    COALESCE(active_members, (0)::bigint) AS active_members,
    COALESCE(inactive_members, (0)::bigint) AS inactive_members,
    COALESCE(total_violations, (0)::bigint) AS total_violations,
    COALESCE(members_with_violations, (0)::bigint) AS members_with_violations,
        CASE
            WHEN (COALESCE(active_members, (0)::bigint) > 0) THEN round((((COALESCE(members_with_violations, (0)::bigint))::numeric / (active_members)::numeric) * (100)::numeric), 2)
            ELSE (0)::numeric
        END AS violation_rate_percentage,
    latest_violation AS latest_member_violation,
    COALESCE(total_party_votes, (0)::numeric) AS total_votes_last_year,
    round(COALESCE(avg_absence_rate, (0)::numeric), 2) AS avg_absence_rate,
    round(COALESCE(avg_win_rate, (0)::numeric), 2) AS avg_win_rate,
    round(COALESCE(avg_rebel_rate, (0)::numeric), 2) AS avg_rebel_rate,
    round(COALESCE(avg_participation_rate, (0)::numeric), 2) AS avg_participation_rate,
    COALESCE(documents_produced, (0)::bigint) AS documents_last_year,
    COALESCE(motions_produced, (0)::bigint) AS motions_last_year,
    COALESCE(propositions_produced, (0)::bigint) AS propositions_last_year,
        CASE
            WHEN (COALESCE(active_members, (0)::bigint) > 0) THEN round(((COALESCE(documents_produced, (0)::bigint))::numeric / (active_members)::numeric), 2)
            ELSE (0)::numeric
        END AS docs_per_member,
    COALESCE(ministers_count, (0)::bigint) AS current_ministers,
    COALESCE(committee_chairs_count, (0)::bigint) AS current_committee_chairs,
    round(calculated_performance_score, 2) AS performance_score,
        CASE
            WHEN (calculated_performance_score >= (75)::numeric) THEN 'EXCELLENT'::text
            WHEN (calculated_performance_score >= (60)::numeric) THEN 'GOOD'::text
            WHEN (calculated_performance_score >= (40)::numeric) THEN 'AVERAGE'::text
            ELSE 'BELOW_AVERAGE'::text
        END AS performance_level,
    concat_ws(' | '::text,
        CASE
            WHEN (COALESCE(avg_participation_rate, (0)::numeric) >= (85)::numeric) THEN 'High participation'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(avg_win_rate, (0)::numeric) >= (60)::numeric) THEN 'Effective voting'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(avg_rebel_rate, (0)::numeric) <= (5)::numeric) THEN 'Strong discipline'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(documents_produced, (0)::bigint) >= 50) THEN 'High productivity'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(ministers_count, (0)::bigint) >= 1) THEN 'Government representation'::text
            ELSE NULL::text
        END) AS strengths,
    concat_ws(' | '::text,
        CASE
            WHEN (COALESCE(avg_absence_rate, (0)::numeric) >= (15)::numeric) THEN 'High absenteeism'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(avg_win_rate, (0)::numeric) < (30)::numeric) THEN 'Low effectiveness'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(avg_rebel_rate, (0)::numeric) >= (15)::numeric) THEN 'Discipline issues'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(documents_produced, (0)::bigint) < 10) THEN 'Low productivity'::text
            ELSE NULL::text
        END,
        CASE
            WHEN (COALESCE(total_violations, (0)::bigint) >= 10) THEN 'Multiple violations'::text
            ELSE NULL::text
        END) AS weaknesses
   FROM performance_calculations
  ORDER BY (round(calculated_performance_score, 2)) DESC, COALESCE(active_members, (0)::bigint) DESC;


--
-- Name: view_politician_behavioral_trends; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_politician_behavioral_trends AS
 WITH monthly_voting_data AS (
         SELECT view_riksdagen_vote_data_ballot_politician_summary_daily.embedded_id_intressent_id AS person_id,
            date_trunc('month'::text, (view_riksdagen_vote_data_ballot_politician_summary_daily.embedded_id_vote_date)::timestamp with time zone) AS period_start,
            max(view_riksdagen_vote_data_ballot_politician_summary_daily.first_name) AS first_name,
            max(view_riksdagen_vote_data_ballot_politician_summary_daily.last_name) AS last_name,
            max(view_riksdagen_vote_data_ballot_politician_summary_daily.party) AS party,
            sum(view_riksdagen_vote_data_ballot_politician_summary_daily.number_ballots) AS total_ballots,
            sum(view_riksdagen_vote_data_ballot_politician_summary_daily.total_votes) AS total_votes,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.avg_percentage_absent), 2) AS avg_absence_rate,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.avg_percentage_yes), 2) AS avg_yes_rate,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.avg_percentage_no), 2) AS avg_no_rate,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.avg_percentage_abstain), 2) AS avg_abstain_rate,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.won_percentage), 2) AS avg_win_rate,
            round(avg(view_riksdagen_vote_data_ballot_politician_summary_daily.rebel_percentage), 2) AS avg_rebel_rate
           FROM public.view_riksdagen_vote_data_ballot_politician_summary_daily
          WHERE (view_riksdagen_vote_data_ballot_politician_summary_daily.embedded_id_vote_date >= (CURRENT_DATE - '3 years'::interval))
          GROUP BY view_riksdagen_vote_data_ballot_politician_summary_daily.embedded_id_intressent_id, (date_trunc('month'::text, (view_riksdagen_vote_data_ballot_politician_summary_daily.embedded_id_vote_date)::timestamp with time zone))
        ), violation_counts AS (
         SELECT rule_violation.reference_id AS person_id,
            date_trunc('month'::text, rule_violation.detected_date) AS period_start,
            count(*) AS violation_count,
            count(DISTINCT rule_violation.rule_group) AS violation_types,
            max(rule_violation.detected_date) AS latest_violation_date
           FROM public.rule_violation
          WHERE (((rule_violation.resource_type)::text = 'POLITICIAN'::text) AND ((rule_violation.status)::text = 'ACTIVE'::text) AND (rule_violation.detected_date >= (CURRENT_DATE - '3 years'::interval)))
          GROUP BY rule_violation.reference_id, (date_trunc('month'::text, rule_violation.detected_date))
        ), trend_calculations AS (
         SELECT mvd.person_id,
            mvd.period_start,
            mvd.first_name,
            mvd.last_name,
            mvd.party,
            mvd.total_ballots,
            mvd.total_votes,
            mvd.avg_absence_rate,
            mvd.avg_yes_rate,
            mvd.avg_no_rate,
            mvd.avg_abstain_rate,
            mvd.avg_win_rate,
            mvd.avg_rebel_rate,
            COALESCE(vc.violation_count, (0)::bigint) AS violation_count,
            COALESCE(vc.violation_types, (0)::bigint) AS violation_types,
            lag(mvd.avg_absence_rate, 1) OVER (PARTITION BY mvd.person_id ORDER BY mvd.period_start) AS prev_absence_rate,
            lag(mvd.avg_win_rate, 1) OVER (PARTITION BY mvd.person_id ORDER BY mvd.period_start) AS prev_win_rate,
            lag(mvd.avg_rebel_rate, 1) OVER (PARTITION BY mvd.person_id ORDER BY mvd.period_start) AS prev_rebel_rate,
            round(avg(mvd.avg_absence_rate) OVER (PARTITION BY mvd.person_id ORDER BY mvd.period_start ROWS BETWEEN 2 PRECEDING AND CURRENT ROW), 2) AS ma_3month_absence,
            round(avg(mvd.avg_win_rate) OVER (PARTITION BY mvd.person_id ORDER BY mvd.period_start ROWS BETWEEN 2 PRECEDING AND CURRENT ROW), 2) AS ma_3month_win_rate,
            round(avg(mvd.avg_rebel_rate) OVER (PARTITION BY mvd.person_id ORDER BY mvd.period_start ROWS BETWEEN 2 PRECEDING AND CURRENT ROW), 2) AS ma_3month_rebel_rate
           FROM (monthly_voting_data mvd
             LEFT JOIN violation_counts vc ON ((((mvd.person_id)::text = (vc.person_id)::text) AND (mvd.period_start = vc.period_start))))
        )
 SELECT person_id,
    period_start,
    (((period_start + '1 mon'::interval) - '1 day'::interval))::date AS period_end,
    first_name,
    last_name,
    party,
    total_ballots,
    total_votes,
    avg_absence_rate,
    avg_yes_rate,
    avg_no_rate,
    avg_abstain_rate,
    avg_win_rate,
    avg_rebel_rate,
    violation_count,
    violation_types,
    round((avg_absence_rate - prev_absence_rate), 2) AS absence_trend,
    round((avg_win_rate - prev_win_rate), 2) AS win_rate_trend,
    round((avg_rebel_rate - prev_rebel_rate), 2) AS rebel_rate_trend,
    ma_3month_absence,
    ma_3month_win_rate,
    ma_3month_rebel_rate,
        CASE
            WHEN (avg_absence_rate >= (30)::numeric) THEN 'HIGH_ABSENTEEISM'::text
            WHEN (avg_absence_rate >= (15)::numeric) THEN 'MODERATE_ABSENTEEISM'::text
            WHEN (avg_absence_rate >= (5)::numeric) THEN 'LOW_ABSENTEEISM'::text
            ELSE 'EXCELLENT_ATTENDANCE'::text
        END AS attendance_status,
        CASE
            WHEN (avg_win_rate >= (80)::numeric) THEN 'HIGHLY_EFFECTIVE'::text
            WHEN (avg_win_rate >= (60)::numeric) THEN 'EFFECTIVE'::text
            WHEN (avg_win_rate >= (40)::numeric) THEN 'MODERATELY_EFFECTIVE'::text
            ELSE 'LOW_EFFECTIVENESS'::text
        END AS effectiveness_status,
        CASE
            WHEN (avg_rebel_rate >= (20)::numeric) THEN 'HIGH_INDEPENDENCE'::text
            WHEN (avg_rebel_rate >= (10)::numeric) THEN 'MODERATE_INDEPENDENCE'::text
            WHEN (avg_rebel_rate >= (5)::numeric) THEN 'LOW_INDEPENDENCE'::text
            ELSE 'PARTY_LOYAL'::text
        END AS discipline_status,
        CASE
            WHEN ((avg_absence_rate >= (30)::numeric) OR (avg_rebel_rate >= (20)::numeric) OR (violation_count >= 3)) THEN 'ELEVATED_RISK'::text
            WHEN ((avg_absence_rate >= (15)::numeric) OR (avg_rebel_rate >= (10)::numeric) OR (violation_count >= 1)) THEN 'MODERATE_RISK'::text
            WHEN ((avg_win_rate >= (70)::numeric) AND (avg_absence_rate < (10)::numeric)) THEN 'HIGH_PERFORMER'::text
            ELSE 'STANDARD_BEHAVIOR'::text
        END AS behavioral_assessment
   FROM trend_calculations
  WHERE (total_ballots >= (5)::numeric)
  ORDER BY person_id, period_start DESC;


--
-- Name: view_politician_risk_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_politician_risk_summary AS
 WITH politician_vote_metrics AS (
         SELECT p.id AS person_id,
            count(DISTINCT vd.embedded_id_ballot_id) AS total_votes,
            count(DISTINCT vd.embedded_id_ballot_id) FILTER (WHERE ((vd.vote)::text = 'Frånvarande'::text)) AS absent_votes,
            count(DISTINCT vd.embedded_id_ballot_id) FILTER (WHERE (((vd.vote)::text <> (vd.party)::text) AND ((vd.vote)::text <> 'Frånvarande'::text))) AS rebel_votes
           FROM (public.person_data p
             LEFT JOIN public.vote_data vd ON ((((vd.embedded_id_intressent_id)::text = (p.id)::text) AND (vd.vote_date >= (CURRENT_DATE - '2 years'::interval)))))
          WHERE ((p.status)::text = 'active'::text)
          GROUP BY p.id
        ), politician_document_metrics AS (
         SELECT dpr.person_reference_id,
            count(DISTINCT dsc.hjid) AS documents_last_year
           FROM (((public.document_status_container dsc
             LEFT JOIN public.document_data dd ON (((dsc.document_document_status_con_0)::text = (dd.id)::text)))
             LEFT JOIN public.document_person_reference_co_0 dprc ON ((dsc.hjid = dprc.hjid)))
             LEFT JOIN public.document_person_reference_da_0 dpr ON ((dpr.document_person_reference_li_1 = dprc.hjid)))
          WHERE ((dd.made_public_date >= (CURRENT_DATE - '1 year'::interval)) AND (dpr.person_reference_id IS NOT NULL))
          GROUP BY dpr.person_reference_id
        ), risk_calculations AS (
         SELECT p.id AS person_id,
            p.first_name,
            p.last_name,
            p.party,
            p.status,
            count(DISTINCT rv.id) AS total_violations,
            max(rv.detected_date) AS latest_violation_date,
            count(DISTINCT rv.id) FILTER (WHERE ((rv.rule_group)::text = 'ABSENTEEISM'::text)) AS absenteeism_violations,
            count(DISTINCT rv.id) FILTER (WHERE ((rv.rule_group)::text = 'EFFECTIVENESS'::text)) AS effectiveness_violations,
            count(DISTINCT rv.id) FILTER (WHERE ((rv.rule_group)::text = 'DISCIPLINE'::text)) AS discipline_violations,
            count(DISTINCT rv.id) FILTER (WHERE ((rv.rule_group)::text = 'PRODUCTIVITY'::text)) AS productivity_violations,
            count(DISTINCT rv.id) FILTER (WHERE ((rv.rule_group)::text = 'COLLABORATION'::text)) AS collaboration_violations,
            COALESCE(round((((pvm.absent_votes)::numeric / (NULLIF(pvm.total_votes, 0))::numeric) * (100)::numeric), 2), (0)::numeric) AS annual_absence_rate,
            COALESCE(round((((pvm.rebel_votes)::numeric / (NULLIF(pvm.total_votes, 0))::numeric) * (100)::numeric), 2), (0)::numeric) AS annual_rebel_rate,
            COALESCE(pvm.total_votes, (0)::bigint) AS annual_vote_count,
            COALESCE(pdm.documents_last_year, (0)::bigint) AS documents_last_year,
            ((((LEAST((count(DISTINCT rv.id) * 2), (40)::bigint))::numeric + ((COALESCE(round((((pvm.absent_votes)::numeric / (NULLIF(pvm.total_votes, 0))::numeric) * (100)::numeric), 2), (0)::numeric) * (30)::numeric) / 100.0)) + ((COALESCE(round((((pvm.rebel_votes)::numeric / (NULLIF(pvm.total_votes, 0))::numeric) * (100)::numeric), 2), (0)::numeric) * (20)::numeric) / 100.0)) + (
                CASE
                    WHEN (COALESCE(pdm.documents_last_year, (0)::bigint) < 5) THEN 10
                    ELSE 0
                END)::numeric) AS calculated_risk_score
           FROM (((public.person_data p
             LEFT JOIN politician_vote_metrics pvm ON (((pvm.person_id)::text = (p.id)::text)))
             LEFT JOIN politician_document_metrics pdm ON (((pdm.person_reference_id)::text = (p.id)::text)))
             LEFT JOIN public.rule_violation rv ON ((((rv.reference_id)::text = (p.id)::text) AND ((rv.resource_type)::text = 'POLITICIAN'::text) AND ((rv.status)::text = 'ACTIVE'::text))))
          WHERE ((p.status)::text = 'active'::text)
          GROUP BY p.id, p.first_name, p.last_name, p.party, p.status, pvm.absent_votes, pvm.total_votes, pvm.rebel_votes, pdm.documents_last_year
        )
 SELECT person_id,
    first_name,
    last_name,
    party,
    status,
    total_violations,
    latest_violation_date,
    absenteeism_violations,
    effectiveness_violations,
    discipline_violations,
    productivity_violations,
    collaboration_violations,
    annual_absence_rate,
    annual_rebel_rate,
    annual_vote_count,
    documents_last_year,
    round(calculated_risk_score, 2) AS risk_score,
        CASE
            WHEN (calculated_risk_score >= (70)::numeric) THEN 'CRITICAL'::text
            WHEN (calculated_risk_score >= (50)::numeric) THEN 'HIGH'::text
            WHEN (calculated_risk_score >= (30)::numeric) THEN 'MEDIUM'::text
            WHEN (calculated_risk_score >= (10)::numeric) THEN 'LOW'::text
            ELSE 'MINIMAL'::text
        END AS risk_level,
        CASE
            WHEN (calculated_risk_score >= (70)::numeric) THEN 'Critical risk politician - immediate investigation required'::text
            WHEN (calculated_risk_score >= (50)::numeric) THEN 'High risk politician - performance concerns warrant review'::text
            WHEN (calculated_risk_score >= (30)::numeric) THEN 'Moderate risk - monitor for declining performance'::text
            WHEN (calculated_risk_score < (10)::numeric) THEN 'Low risk - performing within acceptable standards'::text
            ELSE 'Standard risk profile'::text
        END AS risk_assessment
   FROM risk_calculations
  ORDER BY calculated_risk_score DESC, total_violations DESC;


--
-- Name: view_riksdagen_coalition_alignment_matrix; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_coalition_alignment_matrix AS
 WITH party_votes AS (
         SELECT vote_data.party,
            vote_data.embedded_id_ballot_id AS ballot_id,
            vote_data.vote,
            vote_data.vote_date
           FROM public.vote_data
          WHERE ((vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval)) AND (vote_data.party IS NOT NULL) AND (vote_data.vote IS NOT NULL))
        ), party_pairs AS (
         SELECT DISTINCT p1.party AS party1,
            p2.party AS party2
           FROM (party_votes p1
             CROSS JOIN party_votes p2)
          WHERE ((p1.party)::text < (p2.party)::text)
        ), alignment_metrics AS (
         SELECT pp.party1,
            pp.party2,
            count(DISTINCT pv1.ballot_id) AS total_votes,
            count(DISTINCT
                CASE
                    WHEN ((pv1.vote)::text = (pv2.vote)::text) THEN pv1.ballot_id
                    ELSE NULL::character varying
                END) AS aligned_votes,
            count(DISTINCT
                CASE
                    WHEN (((pv1.vote)::text = 'Ja'::text) AND ((pv2.vote)::text = 'Ja'::text)) THEN pv1.ballot_id
                    ELSE NULL::character varying
                END) AS both_yes,
            count(DISTINCT
                CASE
                    WHEN (((pv1.vote)::text = 'Nej'::text) AND ((pv2.vote)::text = 'Nej'::text)) THEN pv1.ballot_id
                    ELSE NULL::character varying
                END) AS both_no,
            count(DISTINCT
                CASE
                    WHEN (((pv1.vote)::text = 'Avstå'::text) OR ((pv2.vote)::text = 'Avstå'::text)) THEN pv1.ballot_id
                    ELSE NULL::character varying
                END) AS abstention_count,
            min(pv1.vote_date) AS earliest_vote,
            max(pv1.vote_date) AS latest_vote
           FROM ((party_pairs pp
             JOIN party_votes pv1 ON (((pv1.party)::text = (pp.party1)::text)))
             JOIN party_votes pv2 ON ((((pv2.party)::text = (pp.party2)::text) AND ((pv2.ballot_id)::text = (pv1.ballot_id)::text))))
          GROUP BY pp.party1, pp.party2
        )
 SELECT party1,
    party2,
    total_votes AS shared_votes,
    aligned_votes,
    (total_votes - aligned_votes) AS opposed_votes,
    round(((aligned_votes)::numeric / (NULLIF(total_votes, 0))::numeric), 4) AS alignment_rate,
        CASE
            WHEN (((aligned_votes)::numeric / (NULLIF(total_votes, 0))::numeric) >= 0.80) THEN 'STRONG_COALITION'::text
            WHEN (((aligned_votes)::numeric / (NULLIF(total_votes, 0))::numeric) >= 0.60) THEN 'MODERATE_COALITION'::text
            WHEN (((aligned_votes)::numeric / (NULLIF(total_votes, 0))::numeric) >= 0.40) THEN 'WEAK_ALIGNMENT'::text
            ELSE 'OPPOSITION'::text
        END AS coalition_likelihood,
    'N/A'::text AS bloc_relationship,
        CASE
            WHEN (((aligned_votes)::numeric / (NULLIF(total_votes, 0))::numeric) >= 0.80) THEN 'Strong coalition partnership - consistent aligned voting'::text
            WHEN (((aligned_votes)::numeric / (NULLIF(total_votes, 0))::numeric) >= 0.60) THEN 'Moderate coalition alignment - generally cooperative'::text
            WHEN (((aligned_votes)::numeric / (NULLIF(total_votes, 0))::numeric) <= 0.40) THEN 'Opposition positioning - frequently divergent votes'::text
            ELSE 'Neutral relationship - mixed voting patterns'::text
        END AS intelligence_comment,
    (EXTRACT(year FROM earliest_vote))::integer AS first_year,
    (EXTRACT(year FROM latest_vote))::integer AS last_year,
    (((EXTRACT(year FROM latest_vote) - EXTRACT(year FROM earliest_vote)) + (1)::numeric))::integer AS years_observed
   FROM alignment_metrics
  WHERE (total_votes >= 10)
  ORDER BY (round(((aligned_votes)::numeric / (NULLIF(total_votes, 0))::numeric), 4)) DESC, total_votes DESC;


--
-- Name: view_riksdagen_committee_ballot_decision_party_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_committee_ballot_decision_party_summary AS
 SELECT p.embedded_id_party,
    d.embedded_id_id,
    p.embedded_id_issue,
    d.embedded_id_hangar_id,
    d.committee_report,
    d.rm,
    d.title,
    d.header AS sub_title,
    d.end_number,
    upper(d.org) AS org,
    d.created_date,
    d.public_date,
    d.ballot_id,
    d.decision_type,
    d.against_proposal_parties,
    d.against_proposal_number,
    d.winner,
    p.embedded_id_concern,
    p.ballot_type,
    p.label,
    p.vote_date,
    p.avg_born_year,
    p.total_votes,
    p.yes_votes,
    p.no_votes,
    p.abstain_votes,
    p.absent_votes,
    p.approved,
    p.no_winner,
    p.percentage_yes,
    p.percentage_no,
    p.percentage_absent,
    p.percentage_abstain,
    p.percentage_male,
    p.party_avg_born_year,
    p.party_total_votes,
    p.party_yes_votes,
    p.party_no_votes,
    p.party_abstain_votes,
    p.party_absent_votes,
    p.party_approved,
    p.party_no_winner,
    p.party_percentage_yes,
    p.party_percentage_no,
    p.party_percentage_absent,
    p.party_percentage_abstain,
    p.party_percentage_male
   FROM (public.view_riksdagen_committee_decisions d
     LEFT JOIN public.view_riksdagen_vote_data_ballot_party_summary p ON ((((d.ballot_id)::text = (p.embedded_id_ballot_id)::text) AND ((d.rm)::text = p.rm) AND ((d.embedded_id_issue_nummer)::text = (p.embedded_id_issue)::text))))
  WHERE (((d.decision_type)::text = 'röstning'::text) AND (d.ballot_id IS NOT NULL) AND (p.embedded_id_ballot_id IS NOT NULL))
  WITH NO DATA;


--
-- Name: view_riksdagen_committee_ballot_decision_politician_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_committee_ballot_decision_politician_summary AS
 SELECT p.embedded_id_intressent_id,
    p.party,
    d.embedded_id_id,
    p.embedded_id_issue,
    d.embedded_id_hangar_id,
    p.first_name,
    p.last_name,
    p.born_year,
    p.vote,
    p.won,
    p.rebel,
    d.committee_report,
    d.rm,
    d.title,
    d.header AS sub_title,
    d.end_number,
    upper(d.org) AS org,
    d.created_date,
    d.public_date,
    d.ballot_id,
    d.decision_type,
    d.against_proposal_parties,
    d.against_proposal_number,
    d.winner,
    p.embedded_id_concern,
    p.ballot_type,
    p.label,
    p.vote_date,
    p.avg_born_year,
    p.total_votes,
    p.yes_votes,
    p.no_votes,
    p.abstain_votes,
    p.absent_votes,
    p.approved,
    p.no_winner,
    p.percentage_yes,
    p.percentage_no,
    p.percentage_absent,
    p.percentage_abstain,
    p.percentage_male,
    p.party_avg_born_year,
    p.party_total_votes,
    p.party_yes_votes,
    p.party_no_votes,
    p.party_abstain_votes,
    p.party_absent_votes,
    p.party_approved,
    p.party_no_winner,
    p.party_percentage_yes,
    p.party_percentage_no,
    p.party_percentage_absent,
    p.party_percentage_abstain,
    p.party_percentage_male
   FROM (public.view_riksdagen_committee_decisions d
     LEFT JOIN public.view_riksdagen_vote_data_ballot_politician_summary p ON ((((d.ballot_id)::text = (p.embedded_id_ballot_id)::text) AND ((d.rm)::text = p.rm) AND ((d.embedded_id_issue_nummer)::text = (p.embedded_id_issue)::text))))
  WHERE (((d.decision_type)::text = 'röstning'::text) AND (d.ballot_id IS NOT NULL) AND (p.embedded_id_ballot_id IS NOT NULL))
  WITH NO DATA;


--
-- Name: view_riksdagen_committee_decision_type_org_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_committee_decision_type_org_summary AS
 SELECT upper((decision_type)::text) AS embedded_id_decision_type,
    upper(org) AS embedded_id_org,
    created_date AS embedded_id_decision_date,
    count(*) AS total,
    sum(
        CASE
            WHEN ((winner)::text = 'utskottet'::text) THEN 1
            ELSE 0
        END) AS committee_winner,
    sum(
        CASE
            WHEN ((winner)::text ~~ 'reservation%'::text) THEN 1
            ELSE 0
        END) AS reservation_winner
   FROM public.view_riksdagen_committee_decisions
  GROUP BY (upper((decision_type)::text)), created_date, (upper(org))
  WITH NO DATA;


--
-- Name: view_riksdagen_committee_decision_type_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_committee_decision_type_summary AS
 SELECT upper((decision_type)::text) AS embedded_id_decision_type,
    created_date AS embedded_id_decision_date,
    count(*) AS total,
    sum(
        CASE
            WHEN ((winner)::text = 'utskottet'::text) THEN 1
            ELSE 0
        END) AS committee_winner,
    sum(
        CASE
            WHEN ((winner)::text ~~ 'reservation%'::text) THEN 1
            ELSE 0
        END) AS reservation_winner
   FROM public.view_riksdagen_committee_decisions
  GROUP BY (upper((decision_type)::text)), created_date
  ORDER BY created_date
  WITH NO DATA;


--
-- Name: view_riksdagen_committee_parliament_member_proposal; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_committee_parliament_member_proposal AS
 SELECT view_riksdagen_committee.embedded_id_detail,
    view_riksdagen_committee.embedded_id_org_code,
    view_riksdagen_committee.total_assignments,
    document_data.id,
    document_data.committee_report_url_xml,
    document_data.document_status_url_www,
    document_data.document_status_url_xml,
    document_data.document_type,
    document_data.document_url_html,
    document_data.document_url_text,
    document_data.final_number,
    document_data.hangar_id,
    document_data.label,
    document_data.made_public_date,
    document_data.number_value,
    document_data.org,
    document_data.rm,
    document_data.status,
    document_data.sub_title,
    document_data.sub_type,
    document_data.temp_label,
    document_data.title
   FROM (public.view_riksdagen_committee
     LEFT JOIN public.document_data ON (((view_riksdagen_committee.embedded_id_org_code)::text = (document_data.org)::text)))
  WHERE (((document_data.document_type)::text = ANY (ARRAY[('mot'::character varying)::text, ('MOT'::character varying)::text, ('Motion'::character varying)::text])) OR (upper((document_data.document_type)::text) = 'MOT'::text));


--
-- Name: view_riksdagen_committee_role_member; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_committee_role_member AS
 SELECT a.hjid AS role_id,
    a.detail,
    a.role_code,
    p.first_name,
    p.last_name,
    a.from_date,
    a.to_date,
    p.id AS person_id,
    p.party,
    (
        CASE
            WHEN (a.to_date > CURRENT_DATE) THEN CURRENT_DATE
            ELSE a.to_date
        END -
        CASE
            WHEN (a.from_date > CURRENT_DATE) THEN CURRENT_DATE
            ELSE a.from_date
        END) AS total_days_served,
        CASE
            WHEN ((a.to_date > CURRENT_DATE) AND (a.from_date < CURRENT_DATE)) THEN true
            ELSE false
        END AS active,
    COALESCE(doc_stats.total_documents, (0)::bigint) AS total_documents,
    COALESCE(doc_stats.documents_last_year, (0)::bigint) AS documents_last_year,
    COALESCE(doc_stats.committee_reports, (0)::bigint) AS total_committee_reports,
    COALESCE(doc_stats.statements, (0)::bigint) AS total_statements,
    COALESCE(doc_stats.initiatives, (0)::bigint) AS total_initiatives,
        CASE
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 40) THEN 'Very High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 20) THEN 'High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 10) THEN 'Medium'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 0) THEN 'Low'::text
            ELSE 'Inactive'::text
        END AS activity_level,
        CASE
            WHEN ((a.role_code)::text = 'Ordförande'::text) THEN 'Chairman'::text
            WHEN ((a.role_code)::text = 'Förste vice ordförande'::text) THEN 'First Vice Chairman'::text
            WHEN ((a.role_code)::text = 'Andre vice ordförande'::text) THEN 'Second Vice Chairman'::text
            WHEN ((a.role_code)::text = 'Tredje vice ordförande'::text) THEN 'Third Vice Chairman'::text
            WHEN ((a.role_code)::text = 'Vice ordförande'::text) THEN 'Vice Chairman'::text
            WHEN ((a.role_code)::text = 'Ledamot'::text) THEN 'Member'::text
            WHEN ((a.role_code)::text = 'Suppleant'::text) THEN 'Deputy Member'::text
            WHEN ((a.role_code)::text = 'Extra suppleant'::text) THEN 'Additional Deputy'::text
            WHEN ((a.role_code)::text = 'Deputerad'::text) THEN 'Deputy'::text
            ELSE 'Other'::text
        END AS role_type,
        CASE
            WHEN ((a.detail)::text = 'Arbetsmarknadsutskottet'::text) THEN 'Labor Market'::text
            WHEN ((a.detail)::text = 'Civilutskottet'::text) THEN 'Civil Affairs'::text
            WHEN ((a.detail)::text = 'Finansutskottet'::text) THEN 'Finance'::text
            WHEN ((a.detail)::text = 'Försvarsutskottet'::text) THEN 'Defense'::text
            WHEN ((a.detail)::text = 'Justitieutskottet'::text) THEN 'Justice'::text
            WHEN ((a.detail)::text = 'Konstitutionsutskottet'::text) THEN 'Constitution'::text
            WHEN ((a.detail)::text = 'Kulturutskottet'::text) THEN 'Culture'::text
            WHEN ((a.detail)::text = 'Miljö- och jordbruksutskottet'::text) THEN 'Environment and Agriculture'::text
            WHEN ((a.detail)::text = 'Näringsutskottet'::text) THEN 'Industry and Trade'::text
            WHEN ((a.detail)::text = 'Skatteutskottet'::text) THEN 'Taxation'::text
            WHEN ((a.detail)::text = 'Socialförsäkringsutskottet'::text) THEN 'Social Insurance'::text
            WHEN ((a.detail)::text = 'Socialutskottet'::text) THEN 'Social Affairs'::text
            WHEN ((a.detail)::text = 'Trafikutskottet'::text) THEN 'Transport'::text
            WHEN ((a.detail)::text = 'Utbildningsutskottet'::text) THEN 'Education'::text
            WHEN ((a.detail)::text = 'Utrikesutskottet'::text) THEN 'Foreign Affairs'::text
            WHEN ((a.detail)::text ~~ 'Sammansatta%'::text) THEN 'Joint Committee'::text
            WHEN ((a.detail)::text = 'EU-nämnden'::text) THEN 'EU Affairs'::text
            WHEN ((a.detail)::text = 'Lagutskottet'::text) THEN 'Law'::text
            WHEN ((a.detail)::text = 'Bostadsutskottet'::text) THEN 'Housing'::text
            ELSE 'Other'::text
        END AS committee_type
   FROM ((public.assignment_data a
     LEFT JOIN public.person_data p ON (((a.intressent_id)::text = (p.id)::text)))
     LEFT JOIN LATERAL ( SELECT view_riksdagen_politician_document.person_reference_id,
            count(*) AS total_documents,
            count(
                CASE
                    WHEN (view_riksdagen_politician_document.made_public_date >= (CURRENT_DATE - '1 year'::interval)) THEN 1
                    ELSE NULL::integer
                END) AS documents_last_year,
            count(
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = 'bet'::text) THEN 1
                    ELSE NULL::integer
                END) AS committee_reports,
            count(
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = 'yttr'::text) THEN 1
                    ELSE NULL::integer
                END) AS statements,
            count(
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = ANY (ARRAY[('mot'::character varying)::text, ('prop'::character varying)::text, ('frs'::character varying)::text])) THEN 1
                    ELSE NULL::integer
                END) AS initiatives
           FROM public.view_riksdagen_politician_document
          WHERE (view_riksdagen_politician_document.made_public_date >= (CURRENT_DATE - '5 years'::interval))
          GROUP BY view_riksdagen_politician_document.person_reference_id) doc_stats ON (((doc_stats.person_reference_id)::text = (p.id)::text)))
  WHERE ((a.org_code IS NOT NULL) AND ((a.assignment_type)::text = 'uppdrag'::text))
  ORDER BY a.detail,
        CASE a.role_code
            WHEN 'Ordförande'::text THEN 1
            WHEN 'Förste vice ordförande'::text THEN 2
            WHEN 'Andre vice ordförande'::text THEN 3
            WHEN 'Tredje vice ordförande'::text THEN 4
            WHEN 'Vice ordförande'::text THEN 5
            WHEN 'Ledamot'::text THEN 6
            WHEN 'Suppleant'::text THEN 7
            WHEN 'Extra suppleant'::text THEN 8
            WHEN 'Deputerad'::text THEN 9
            ELSE 10
        END, a.from_date DESC, p.last_name, p.first_name;


--
-- Name: view_riksdagen_committee_roles; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_committee_roles AS
 SELECT DISTINCT detail AS embedded_id_detail,
    role_code,
    count(detail) AS total_assignments,
    min(from_date) AS first_assignment_date,
    max(to_date) AS last_assignment_date
   FROM public.assignment_data
  WHERE ((org_code IS NOT NULL) AND ((assignment_type)::text = 'uppdrag'::text))
  GROUP BY detail, role_code;


--
-- Name: view_riksdagen_crisis_resilience_indicators; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_crisis_resilience_indicators AS
 WITH monthly_activity AS (
         SELECT date_trunc('month'::text, (vote_data.vote_date)::timestamp with time zone) AS activity_month,
            count(DISTINCT vote_data.embedded_id_ballot_id) AS ballot_count
           FROM public.vote_data
          WHERE (vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval))
          GROUP BY (date_trunc('month'::text, (vote_data.vote_date)::timestamp with time zone))
        ), activity_thresholds AS (
         SELECT percentile_cont((0.5)::double precision) WITHIN GROUP (ORDER BY ((monthly_activity.ballot_count)::double precision)) AS median_ballots,
            percentile_cont((0.75)::double precision) WITHIN GROUP (ORDER BY ((monthly_activity.ballot_count)::double precision)) AS p75_ballots,
            avg(monthly_activity.ballot_count) AS avg_ballots
           FROM monthly_activity
        ), classified_periods AS (
         SELECT ma.activity_month,
            ma.ballot_count,
                CASE
                    WHEN ((ma.ballot_count)::double precision >= at.p75_ballots) THEN 'CRISIS'::text
                    WHEN ((ma.ballot_count)::double precision >= at.median_ballots) THEN 'ELEVATED'::text
                    ELSE 'NORMAL'::text
                END AS period_type
           FROM (monthly_activity ma
             CROSS JOIN activity_thresholds at)
        ), crisis_voting AS (
         SELECT vd.embedded_id_intressent_id AS person_id,
            vd.party,
            count(*) AS crisis_votes,
            count(*) FILTER (WHERE ((vd.vote)::text = 'Frånvarande'::text)) AS crisis_absent,
            count(*) FILTER (WHERE ((vd.vote)::text = 'Ja'::text)) AS crisis_yes,
            count(*) FILTER (WHERE ((vd.vote)::text = 'Nej'::text)) AS crisis_no,
            count(*) FILTER (WHERE (((vd.vote)::text <> (vd.party)::text) AND ((vd.vote)::text <> 'Frånvarande'::text))) AS crisis_rebellions
           FROM (public.vote_data vd
             JOIN classified_periods cp ON (((date_trunc('month'::text, (vd.vote_date)::timestamp with time zone) = cp.activity_month) AND (cp.period_type = ANY (ARRAY['CRISIS'::text, 'ELEVATED'::text])))))
          GROUP BY vd.embedded_id_intressent_id, vd.party
        ), normal_voting AS (
         SELECT vd.embedded_id_intressent_id AS person_id,
            count(*) AS normal_votes,
            count(*) FILTER (WHERE ((vd.vote)::text = 'Frånvarande'::text)) AS normal_absent,
            count(*) FILTER (WHERE ((vd.vote)::text = 'Ja'::text)) AS normal_yes,
            count(*) FILTER (WHERE ((vd.vote)::text = 'Nej'::text)) AS normal_no,
            count(*) FILTER (WHERE (((vd.vote)::text <> (vd.party)::text) AND ((vd.vote)::text <> 'Frånvarande'::text))) AS normal_rebellions
           FROM (public.vote_data vd
             JOIN classified_periods cp ON (((date_trunc('month'::text, (vd.vote_date)::timestamp with time zone) = cp.activity_month) AND (cp.period_type = 'NORMAL'::text))))
          GROUP BY vd.embedded_id_intressent_id
        ), all_voting_politicians AS (
         SELECT DISTINCT vote_data.embedded_id_intressent_id AS person_id
           FROM public.vote_data
          WHERE (vote_data.vote_date >= (CURRENT_DATE - '5 years'::interval))
        )
 SELECT p.id AS person_id,
    p.first_name,
    p.last_name,
    p.party,
    COALESCE(cv.crisis_votes, (0)::bigint) AS crisis_period_votes,
    round((((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) * (100)::numeric), 2) AS crisis_absence_rate,
    round(((100)::numeric - (((COALESCE(cv.crisis_rebellions, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) * (100)::numeric)), 2) AS crisis_party_discipline,
    COALESCE(nv.normal_votes, (0)::bigint) AS normal_period_votes,
    round((((COALESCE(nv.normal_absent, (0)::bigint))::numeric / (NULLIF(nv.normal_votes, 0))::numeric) * (100)::numeric), 2) AS normal_absence_rate,
    round(((((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) * (100)::numeric) - (((COALESCE(nv.normal_absent, (0)::bigint))::numeric / (NULLIF(nv.normal_votes, 0))::numeric) * (100)::numeric)), 2) AS absence_rate_change,
    round(
        CASE
            WHEN (COALESCE(cv.crisis_votes, (0)::bigint) < 5) THEN NULL::numeric
            ELSE ((((50)::numeric - LEAST((50)::numeric, (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) * (100)::numeric))) + ((30)::numeric - LEAST((30)::numeric, abs(((((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) * (100)::numeric) - (((COALESCE(nv.normal_absent, (0)::bigint))::numeric / (NULLIF(nv.normal_votes, 0))::numeric) * (100)::numeric)))))) + ((20)::numeric - LEAST((20)::numeric, (((COALESCE(cv.crisis_rebellions, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) * (100)::numeric))))
        END, 4) AS resilience_score,
        CASE
            WHEN ((COALESCE(cv.crisis_votes, (0)::bigint) >= 10) AND (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) < 0.1)) THEN 'HIGHLY_RESILIENT'::text
            WHEN ((COALESCE(cv.crisis_votes, (0)::bigint) >= 5) AND (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) < 0.2)) THEN 'RESILIENT'::text
            WHEN ((COALESCE(cv.crisis_votes, (0)::bigint) >= 5) AND (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) < 0.4)) THEN 'MODERATE_RESILIENCE'::text
            WHEN (COALESCE(cv.crisis_votes, (0)::bigint) < 5) THEN 'INSUFFICIENT_DATA'::text
            ELSE 'LOW_RESILIENCE'::text
        END AS resilience_classification,
        CASE
            WHEN ((COALESCE(cv.crisis_votes, (0)::bigint) >= 10) AND (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) < 0.1)) THEN 'Highly engaged during crisis periods - strong institutional resilience'::text
            WHEN ((COALESCE(cv.crisis_votes, (0)::bigint) >= 5) AND (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) < 0.2)) THEN 'Good crisis response - reliable during high-activity periods'::text
            WHEN ((COALESCE(cv.crisis_votes, (0)::bigint) >= 5) AND (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) < 0.4)) THEN 'Moderate crisis engagement - some absence during critical votes'::text
            WHEN (COALESCE(cv.crisis_votes, (0)::bigint) < 5) THEN 'Insufficient crisis period data for assessment'::text
            ELSE 'Poor crisis response - high absence during high-activity periods'::text
        END AS pressure_performance_assessment
   FROM (((public.person_data p
     JOIN all_voting_politicians avp ON (((avp.person_id)::text = (p.id)::text)))
     LEFT JOIN crisis_voting cv ON (((cv.person_id)::text = (p.id)::text)))
     LEFT JOIN normal_voting nv ON (((nv.person_id)::text = (p.id)::text)))
  WHERE ((p.status)::text = ANY (ARRAY[('active'::character varying)::text, ('Active'::character varying)::text, ('ACTIVE'::character varying)::text]))
  ORDER BY
        CASE
            WHEN ((COALESCE(cv.crisis_votes, (0)::bigint) >= 10) AND (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) < 0.1)) THEN 1
            WHEN ((COALESCE(cv.crisis_votes, (0)::bigint) >= 5) AND (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) < 0.2)) THEN 2
            WHEN ((COALESCE(cv.crisis_votes, (0)::bigint) >= 5) AND (((COALESCE(cv.crisis_absent, (0)::bigint))::numeric / (NULLIF(cv.crisis_votes, 0))::numeric) < 0.4)) THEN 3
            WHEN (COALESCE(cv.crisis_votes, (0)::bigint) < 5) THEN 4
            ELSE 5
        END, p.last_name, p.first_name;


--
-- Name: view_riksdagen_document_type_daily_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_document_type_daily_summary AS
 SELECT "left"((made_public_date)::text, 10) AS embedded_id_public_date,
    document_type AS embedded_id_document_type,
    count(*) AS total
   FROM public.document_element
  GROUP BY ("left"((made_public_date)::text, 10)), document_type
  WITH NO DATA;


--
-- Name: view_riksdagen_goverment; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_goverment AS
 SELECT a.detail AS name_id,
    count(a.detail) AS total_assignments,
    min(a.from_date) AS first_assignment_date,
    max(a.to_date) AS last_assignment_date,
    sum((
        CASE
            WHEN (a.to_date > CURRENT_DATE) THEN CURRENT_DATE
            ELSE a.to_date
        END - a.from_date)) AS total_days_served,
        CASE
            WHEN (max(a.to_date) > CURRENT_DATE) THEN true
            ELSE false
        END AS active,
    sum(
        CASE
            WHEN (a.to_date > CURRENT_DATE) THEN 1
            ELSE 0
        END) AS current_member_size,
    COALESCE(doc_stats.total_documents, (0)::bigint) AS total_documents,
    COALESCE(doc_stats.documents_last_year, (0)::bigint) AS documents_last_year,
    COALESCE(doc_stats.avg_documents_per_member, (0)::numeric) AS avg_documents_per_member,
    COALESCE(doc_stats.propositions, (0)::bigint) AS total_propositions,
    COALESCE(doc_stats.government_bills, (0)::bigint) AS total_government_bills,
        CASE
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 200) THEN 'Very High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 100) THEN 'High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 50) THEN 'Medium'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 0) THEN 'Low'::text
            ELSE 'Inactive'::text
        END AS activity_level
   FROM (public.assignment_data a
     LEFT JOIN LATERAL ( SELECT vpd.org AS ministry_detail,
            count(*) AS total_documents,
            count(
                CASE
                    WHEN (vpd.made_public_date >= (CURRENT_DATE - '1 year'::interval)) THEN 1
                    ELSE NULL::integer
                END) AS documents_last_year,
            round(((count(*))::numeric / (NULLIF(count(DISTINCT vpd.person_reference_id), 0))::numeric), 1) AS avg_documents_per_member,
            count(
                CASE
                    WHEN ((vpd.document_type)::text = 'prop'::text) THEN 1
                    ELSE NULL::integer
                END) AS propositions,
            count(
                CASE
                    WHEN (((vpd.document_type)::text = 'prop'::text) AND ((vpd.sub_type)::text = 'skr'::text)) THEN 1
                    ELSE NULL::integer
                END) AS government_bills
           FROM public.view_riksdagen_politician_document vpd
          WHERE ((((vpd.org)::text ~~ '%departementet'::text) OR ((vpd.org)::text = 'Statsrådsberedningen'::text)) AND ((vpd.org)::text = (a.detail)::text))
          GROUP BY vpd.org) doc_stats ON (true))
  WHERE ((((a.role_code)::text ~~ '%MINISTER%'::text) OR ((a.role_code)::text = 'STATSRÅD'::text) OR ((a.detail)::text ~~ '%departementet'::text) OR ((a.detail)::text = 'Statsrådsberedningen'::text)) AND ((a.status)::text <> 'LEDIG'::text))
  GROUP BY a.detail, doc_stats.total_documents, doc_stats.documents_last_year, doc_stats.avg_documents_per_member, doc_stats.propositions, doc_stats.government_bills;


--
-- Name: view_riksdagen_goverment_proposals; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_goverment_proposals AS
 SELECT id,
    title,
    sub_title,
    status,
    org,
    hangar_id,
    label,
    made_public_date,
    number_value,
    document_status_url_xml
   FROM public.document_data
  WHERE (((document_type)::text = ANY (ARRAY[('prop'::character varying)::text, ('PROP'::character varying)::text, ('Proposition'::character varying)::text])) OR (upper((document_type)::text) = 'PROP'::text));


--
-- Name: view_riksdagen_goverment_role_member; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_goverment_role_member AS
 SELECT a.hjid AS role_id,
    a.detail,
    a.role_code,
    p.first_name,
    p.last_name,
    a.from_date,
    a.to_date,
    p.id AS person_id,
    p.party,
    (
        CASE
            WHEN (a.to_date > CURRENT_DATE) THEN CURRENT_DATE
            ELSE a.to_date
        END -
        CASE
            WHEN (a.from_date > CURRENT_DATE) THEN CURRENT_DATE
            ELSE a.from_date
        END) AS total_days_served,
        CASE
            WHEN ((a.to_date > CURRENT_DATE) AND (a.from_date < CURRENT_DATE)) THEN true
            ELSE false
        END AS active,
    COALESCE(doc_stats.total_documents, (0)::bigint) AS total_documents,
    COALESCE(doc_stats.documents_last_year, (0)::bigint) AS documents_last_year,
    COALESCE(doc_stats.propositions, (0)::bigint) AS total_propositions,
    COALESCE(doc_stats.government_bills, (0)::bigint) AS total_government_bills,
        CASE
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 50) THEN 'Very High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 25) THEN 'High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 10) THEN 'Medium'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 0) THEN 'Low'::text
            ELSE 'Inactive'::text
        END AS activity_level,
        CASE
            WHEN ((a.role_code)::text ~~ '%MINISTER%'::text) THEN 'Minister'::text
            WHEN ((a.role_code)::text = 'STATSRÅD'::text) THEN 'Minister of State'::text
            ELSE 'Minister of State'::text
        END AS role_type
   FROM ((public.assignment_data a
     LEFT JOIN public.person_data p ON (((a.intressent_id)::text = (p.id)::text)))
     LEFT JOIN LATERAL ( SELECT view_riksdagen_politician_document.person_reference_id,
            count(*) AS total_documents,
            count(
                CASE
                    WHEN (view_riksdagen_politician_document.made_public_date >= (CURRENT_DATE - '1 year'::interval)) THEN 1
                    ELSE NULL::integer
                END) AS documents_last_year,
            count(
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = 'prop'::text) THEN 1
                    ELSE NULL::integer
                END) AS propositions,
            count(
                CASE
                    WHEN (((view_riksdagen_politician_document.document_type)::text = 'prop'::text) AND ((view_riksdagen_politician_document.sub_type)::text = 'skr'::text)) THEN 1
                    ELSE NULL::integer
                END) AS government_bills
           FROM public.view_riksdagen_politician_document
          WHERE (view_riksdagen_politician_document.made_public_date >= (CURRENT_DATE - '5 years'::interval))
          GROUP BY view_riksdagen_politician_document.person_reference_id) doc_stats ON (((doc_stats.person_reference_id)::text = (p.id)::text)))
  WHERE (((a.role_code)::text ~~ '%MINISTER%'::text) OR ((a.role_code)::text = 'STATSRÅD'::text) OR ((a.detail)::text ~~ '%departementet'::text) OR ((a.detail)::text = 'Statsrådsberedningen'::text))
  ORDER BY a.detail, a.from_date DESC;


--
-- Name: view_riksdagen_goverment_roles; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_goverment_roles AS
 SELECT DISTINCT detail AS embedded_id_detail,
    role_code,
    count(detail) AS total_assignments,
    min(from_date) AS first_assignment_date,
    max(to_date) AS last_assignment_date
   FROM public.assignment_data
  WHERE (((role_code)::text ~~ '%MINISTER'::text) OR ((detail)::text ~~ '%departementet'::text) OR ((detail)::text = 'Statsrådsberedningen'::text))
  GROUP BY detail, role_code;


--
-- Name: view_riksdagen_party_momentum_analysis; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party_momentum_analysis AS
 WITH quarterly_support AS (
         SELECT vote_data.party,
            EXTRACT(year FROM vote_data.vote_date) AS year,
            EXTRACT(quarter FROM vote_data.vote_date) AS quarter,
            count(DISTINCT vote_data.embedded_id_ballot_id) AS ballots_participated,
            count(
                CASE
                    WHEN ((vote_data.vote)::text = 'Ja'::text) THEN 1
                    ELSE NULL::integer
                END) AS yes_votes,
            count(
                CASE
                    WHEN ((vote_data.vote)::text = 'Nej'::text) THEN 1
                    ELSE NULL::integer
                END) AS no_votes,
            count(
                CASE
                    WHEN ((vote_data.vote)::text = 'Avstår'::text) THEN 1
                    ELSE NULL::integer
                END) AS abstain_votes,
            count(
                CASE
                    WHEN ((vote_data.vote)::text = 'Frånvarande'::text) THEN 1
                    ELSE NULL::integer
                END) AS absent_votes,
            ((count(
                CASE
                    WHEN ((vote_data.vote)::text = 'Ja'::text) THEN 1
                    ELSE NULL::integer
                END))::double precision / (NULLIF(count(*), 0))::double precision) AS participation_rate
           FROM public.vote_data
          WHERE (vote_data.vote_date >= '2010-01-01'::date)
          GROUP BY vote_data.party, (EXTRACT(year FROM vote_data.vote_date)), (EXTRACT(quarter FROM vote_data.vote_date))
        ), momentum_calc AS (
         SELECT quarterly_support.party,
            quarterly_support.year,
            quarterly_support.quarter,
            quarterly_support.ballots_participated,
            quarterly_support.participation_rate,
            lag(quarterly_support.participation_rate, 1) OVER (PARTITION BY quarterly_support.party ORDER BY quarterly_support.year, quarterly_support.quarter) AS prev_quarter_rate,
            (quarterly_support.participation_rate - lag(quarterly_support.participation_rate, 1) OVER (PARTITION BY quarterly_support.party ORDER BY quarterly_support.year, quarterly_support.quarter)) AS momentum,
            avg(quarterly_support.participation_rate) OVER (PARTITION BY quarterly_support.party ORDER BY quarterly_support.year, quarterly_support.quarter ROWS BETWEEN 3 PRECEDING AND CURRENT ROW) AS ma_4quarter,
            stddev(quarterly_support.participation_rate) OVER (PARTITION BY quarterly_support.party ORDER BY quarterly_support.year, quarterly_support.quarter ROWS BETWEEN 3 PRECEDING AND CURRENT ROW) AS volatility_4quarter
           FROM quarterly_support
        ), acceleration_calc AS (
         SELECT momentum_calc.party,
            momentum_calc.year,
            momentum_calc.quarter,
            momentum_calc.ballots_participated,
            momentum_calc.participation_rate,
            momentum_calc.prev_quarter_rate,
            momentum_calc.momentum,
            momentum_calc.ma_4quarter,
            momentum_calc.volatility_4quarter,
            (COALESCE(momentum_calc.momentum, (0)::double precision) - COALESCE(lag(momentum_calc.momentum, 1) OVER (PARTITION BY momentum_calc.party ORDER BY momentum_calc.year, momentum_calc.quarter), (0)::double precision)) AS acceleration
           FROM momentum_calc
        )
 SELECT party,
    year,
    quarter,
    concat(year, '-Q', quarter) AS period,
    ballots_participated,
    round((participation_rate)::numeric, 4) AS participation_rate,
    round((prev_quarter_rate)::numeric, 4) AS prev_quarter_rate,
    round((momentum)::numeric, 4) AS momentum,
    round((ma_4quarter)::numeric, 4) AS moving_avg_4q,
    round((volatility_4quarter)::numeric, 4) AS volatility,
    round((acceleration)::numeric, 4) AS acceleration,
        CASE
            WHEN (COALESCE(momentum, (0)::double precision) > (0.05)::double precision) THEN 'STRONG_POSITIVE'::text
            WHEN (COALESCE(momentum, (0)::double precision) > (0.02)::double precision) THEN 'POSITIVE'::text
            WHEN (COALESCE(momentum, (0)::double precision) > ('-0.02'::numeric)::double precision) THEN 'STABLE'::text
            WHEN (COALESCE(momentum, (0)::double precision) > ('-0.05'::numeric)::double precision) THEN 'NEGATIVE'::text
            ELSE 'STRONG_NEGATIVE'::text
        END AS trend_direction,
        CASE
            WHEN (COALESCE(volatility_4quarter, (0)::double precision) < (0.02)::double precision) THEN 'VERY_STABLE'::text
            WHEN (COALESCE(volatility_4quarter, (0)::double precision) < (0.05)::double precision) THEN 'STABLE'::text
            WHEN (COALESCE(volatility_4quarter, (0)::double precision) < (0.10)::double precision) THEN 'MODERATE'::text
            WHEN (COALESCE(volatility_4quarter, (0)::double precision) < (0.15)::double precision) THEN 'VOLATILE'::text
            ELSE 'HIGHLY_VOLATILE'::text
        END AS stability_classification,
        CASE
            WHEN ((COALESCE(momentum, (0)::double precision) > (0)::double precision) AND (COALESCE(volatility_4quarter, (0)::double precision) < (0.05)::double precision)) THEN 'SUSTAINED_GROWTH'::text
            WHEN ((COALESCE(momentum, (0)::double precision) > (0)::double precision) AND (COALESCE(volatility_4quarter, (0)::double precision) >= (0.05)::double precision)) THEN 'UNSTABLE_GROWTH'::text
            WHEN ((COALESCE(momentum, (0)::double precision) < (0)::double precision) AND (COALESCE(volatility_4quarter, (0)::double precision) < (0.05)::double precision)) THEN 'STEADY_DECLINE'::text
            WHEN ((COALESCE(momentum, (0)::double precision) < (0)::double precision) AND (COALESCE(volatility_4quarter, (0)::double precision) >= (0.05)::double precision)) THEN 'VOLATILE_DECLINE'::text
            ELSE 'STABLE'::text
        END AS intelligence_assessment
   FROM acceleration_calc
  WHERE (ballots_participated >= 5)
  ORDER BY party, year DESC, quarter DESC;


--
-- Name: view_riksdagen_politician_influence_metrics; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_politician_influence_metrics AS
 WITH co_voting_pairs AS (
         SELECT v1.embedded_id_intressent_id AS person_1,
            v2.embedded_id_intressent_id AS person_2,
            count(*) AS co_votes,
            sum(
                CASE
                    WHEN ((v1.vote)::text = (v2.vote)::text) THEN 1
                    ELSE 0
                END) AS aligned_votes,
            ((sum(
                CASE
                    WHEN ((v1.vote)::text = (v2.vote)::text) THEN 1
                    ELSE 0
                END))::double precision / (NULLIF(count(*), 0))::double precision) AS alignment_rate
           FROM (public.vote_data v1
             JOIN public.vote_data v2 ON ((((v1.embedded_id_ballot_id)::text = (v2.embedded_id_ballot_id)::text) AND ((v1.embedded_id_intressent_id)::text < (v2.embedded_id_intressent_id)::text))))
          WHERE ((v1.vote_date >= (CURRENT_DATE - '3 years'::interval)) AND ((v1.vote)::text = ANY (ARRAY[('Ja'::character varying)::text, ('Nej'::character varying)::text])) AND ((v2.vote)::text = ANY (ARRAY[('Ja'::character varying)::text, ('Nej'::character varying)::text])))
          GROUP BY v1.embedded_id_intressent_id, v2.embedded_id_intressent_id
         HAVING (count(*) >= 10)
        ), network_connections AS (
         SELECT co_voting_pairs.person_1 AS person_id
           FROM co_voting_pairs
          WHERE (co_voting_pairs.alignment_rate >= (0.7)::double precision)
        UNION ALL
         SELECT co_voting_pairs.person_2 AS person_id
           FROM co_voting_pairs
          WHERE (co_voting_pairs.alignment_rate >= (0.7)::double precision)
        ), influence_metrics AS (
         SELECT network_connections.person_id,
            count(*) AS strong_connections
           FROM network_connections
          GROUP BY network_connections.person_id
        ), network_median AS (
         SELECT percentile_cont((0.5)::double precision) WITHIN GROUP (ORDER BY ((influence_metrics.strong_connections)::double precision)) AS median_connections
           FROM influence_metrics
        )
 SELECT p.id AS person_id,
    p.first_name,
    p.last_name,
    p.party,
    COALESCE(im.strong_connections, (0)::bigint) AS network_connections,
    round((( SELECT network_median.median_connections
           FROM network_median))::numeric, 2) AS network_median,
        CASE
            WHEN ((COALESCE(im.strong_connections, (0)::bigint))::double precision >= (( SELECT network_median.median_connections
               FROM network_median) * (2)::double precision)) THEN 'HIGHLY_INFLUENTIAL'::text
            WHEN ((COALESCE(im.strong_connections, (0)::bigint))::double precision >= (( SELECT network_median.median_connections
               FROM network_median) * (1.5)::double precision)) THEN 'INFLUENTIAL'::text
            WHEN ((COALESCE(im.strong_connections, (0)::bigint))::double precision >= ( SELECT network_median.median_connections
               FROM network_median)) THEN 'MODERATELY_INFLUENTIAL'::text
            WHEN (COALESCE(im.strong_connections, (0)::bigint) > 0) THEN 'LIMITED_INFLUENCE'::text
            ELSE 'MINIMAL_INFLUENCE'::text
        END AS influence_classification,
        CASE
            WHEN (COALESCE(im.strong_connections, (0)::bigint) >= 20) THEN 'STRONG_BROKER'::text
            WHEN (COALESCE(im.strong_connections, (0)::bigint) >= 10) THEN 'MODERATE_BROKER'::text
            WHEN (COALESCE(im.strong_connections, (0)::bigint) >= 5) THEN 'WEAK_BROKER'::text
            ELSE 'NON_BROKER'::text
        END AS broker_classification,
        CASE
            WHEN ((COALESCE(im.strong_connections, (0)::bigint))::double precision >= (( SELECT network_median.median_connections
               FROM network_median) * (2)::double precision)) THEN 'High influence - extensive cross-party network connections'::text
            WHEN ((COALESCE(im.strong_connections, (0)::bigint))::double precision >= (( SELECT network_median.median_connections
               FROM network_median) * (1.5)::double precision)) THEN 'Notable influence - above-average network centrality'::text
            WHEN ((COALESCE(im.strong_connections, (0)::bigint))::double precision >= ( SELECT network_median.median_connections
               FROM network_median)) THEN 'Standard influence - typical network engagement'::text
            WHEN (COALESCE(im.strong_connections, (0)::bigint) > 0) THEN 'Limited influence - below-average network connections'::text
            ELSE 'Minimal network influence detected'::text
        END AS influence_assessment
   FROM (public.person_data p
     LEFT JOIN influence_metrics im ON (((im.person_id)::text = (p.id)::text)))
  WHERE ((p.status)::text = ANY (ARRAY[('active'::character varying)::text, ('Active'::character varying)::text, ('ACTIVE'::character varying)::text]))
  ORDER BY COALESCE(im.strong_connections, (0)::bigint) DESC, p.last_name, p.first_name;


--
-- Name: view_riksdagen_voting_anomaly_detection; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_voting_anomaly_detection AS
 WITH party_consensus AS (
         SELECT vote_data.embedded_id_ballot_id,
            vote_data.party,
            vote_data.vote,
            count(*) AS vote_count,
            row_number() OVER (PARTITION BY vote_data.embedded_id_ballot_id, vote_data.party ORDER BY (count(*)) DESC) AS rank
           FROM public.vote_data
          WHERE (((vote_data.vote)::text = ANY (ARRAY[('Ja'::character varying)::text, ('Nej'::character varying)::text, ('Avstår'::character varying)::text])) AND (vote_data.party IS NOT NULL) AND (vote_data.vote_date >= (CURRENT_DATE - '3 years'::interval)))
          GROUP BY vote_data.embedded_id_ballot_id, vote_data.party, vote_data.vote
        ), party_majority_vote AS (
         SELECT party_consensus.embedded_id_ballot_id,
            party_consensus.party,
            party_consensus.vote AS party_consensus_vote,
            party_consensus.vote_count AS consensus_count
           FROM party_consensus
          WHERE (party_consensus.rank = 1)
        ), party_vote_counts AS (
         SELECT vote_data.embedded_id_ballot_id,
            vote_data.party,
            count(*) AS total_party_votes
           FROM public.vote_data
          WHERE ((vote_data.party IS NOT NULL) AND (vote_data.vote_date >= (CURRENT_DATE - '3 years'::interval)))
          GROUP BY vote_data.embedded_id_ballot_id, vote_data.party
        ), rebel_votes AS (
         SELECT vd.embedded_id_intressent_id AS person_id,
            vd.embedded_id_ballot_id,
            vd.party,
            vd.vote AS person_vote,
            pmv.party_consensus_vote,
            pmv.consensus_count,
            pvc.total_party_votes,
            round((((pmv.consensus_count)::numeric / (NULLIF(pvc.total_party_votes, 0))::numeric) * (100)::numeric), 2) AS consensus_strength
           FROM ((public.vote_data vd
             JOIN party_majority_vote pmv ON ((((vd.embedded_id_ballot_id)::text = (pmv.embedded_id_ballot_id)::text) AND ((vd.party)::text = (pmv.party)::text))))
             JOIN party_vote_counts pvc ON ((((vd.embedded_id_ballot_id)::text = (pvc.embedded_id_ballot_id)::text) AND ((vd.party)::text = (pvc.party)::text))))
          WHERE (((vd.vote)::text <> (pmv.party_consensus_vote)::text) AND ((vd.vote)::text = ANY (ARRAY[('Ja'::character varying)::text, ('Nej'::character varying)::text, ('Avstår'::character varying)::text])) AND (vd.vote_date >= (CURRENT_DATE - '3 years'::interval)))
        )
 SELECT p.id AS person_id,
    p.first_name,
    p.last_name,
    p.party,
    count(DISTINCT rv.embedded_id_ballot_id) AS total_rebellions,
    count(DISTINCT rv.embedded_id_ballot_id) FILTER (WHERE (rv.consensus_strength >= (80)::numeric)) AS strong_consensus_rebellions,
    count(DISTINCT rv.embedded_id_ballot_id) FILTER (WHERE (rv.consensus_strength >= (90)::numeric)) AS very_strong_consensus_rebellions,
    round(avg(rv.consensus_strength), 2) AS avg_consensus_strength_rebelled_against,
        CASE
            WHEN (count(DISTINCT rv.embedded_id_ballot_id) FILTER (WHERE (rv.consensus_strength >= (90)::numeric)) >= 5) THEN 'FREQUENT_STRONG_REBEL'::text
            WHEN (count(DISTINCT rv.embedded_id_ballot_id) FILTER (WHERE (rv.consensus_strength >= (80)::numeric)) >= 10) THEN 'CONSISTENT_REBEL'::text
            WHEN (count(DISTINCT rv.embedded_id_ballot_id) >= 20) THEN 'MODERATE_REBEL'::text
            WHEN (count(DISTINCT rv.embedded_id_ballot_id) >= 5) THEN 'OCCASIONAL_REBEL'::text
            ELSE 'PARTY_ALIGNED'::text
        END AS anomaly_classification,
        CASE
            WHEN (count(DISTINCT rv.embedded_id_ballot_id) FILTER (WHERE (rv.consensus_strength >= (90)::numeric)) >= 5) THEN 'HIGH ANOMALY: Frequent rebellion against very strong party consensus (90%+)'::text
            WHEN (count(DISTINCT rv.embedded_id_ballot_id) FILTER (WHERE (rv.consensus_strength >= (80)::numeric)) >= 10) THEN 'MODERATE ANOMALY: Consistent rebellion against strong party consensus (80%+)'::text
            WHEN (count(DISTINCT rv.embedded_id_ballot_id) >= 20) THEN 'MILD ANOMALY: Moderate pattern of voting against party majority'::text
            WHEN (count(DISTINCT rv.embedded_id_ballot_id) >= 5) THEN 'LOW ANOMALY: Occasional independent voting behavior'::text
            ELSE 'NO ANOMALY: Votes consistently with party consensus'::text
        END AS anomaly_assessment
   FROM (public.person_data p
     LEFT JOIN rebel_votes rv ON (((rv.person_id)::text = (p.id)::text)))
  WHERE ((p.status)::text = ANY (ARRAY[('active'::character varying)::text, ('Active'::character varying)::text, ('ACTIVE'::character varying)::text]))
  GROUP BY p.id, p.first_name, p.last_name, p.party
 HAVING (count(DISTINCT rv.embedded_id_ballot_id) > 0)
  ORDER BY (count(DISTINCT rv.embedded_id_ballot_id) FILTER (WHERE (rv.consensus_strength >= (90)::numeric))) DESC, (count(DISTINCT rv.embedded_id_ballot_id)) DESC, p.last_name, p.first_name;


--
-- Name: view_riksdagen_intelligence_dashboard; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_intelligence_dashboard AS
 WITH momentum_summary AS (
         SELECT count(DISTINCT view_riksdagen_party_momentum_analysis.party) FILTER (WHERE (view_riksdagen_party_momentum_analysis.trend_direction = ANY (ARRAY['STRONG_POSITIVE'::text, 'POSITIVE'::text]))) AS parties_gaining_momentum,
            count(DISTINCT view_riksdagen_party_momentum_analysis.party) FILTER (WHERE (view_riksdagen_party_momentum_analysis.trend_direction = ANY (ARRAY['STRONG_NEGATIVE'::text, 'NEGATIVE'::text]))) AS parties_losing_momentum,
            count(DISTINCT view_riksdagen_party_momentum_analysis.party) FILTER (WHERE (view_riksdagen_party_momentum_analysis.stability_classification = ANY (ARRAY['VOLATILE'::text, 'HIGHLY_VOLATILE'::text]))) AS volatile_parties
           FROM public.view_riksdagen_party_momentum_analysis
        ), coalition_summary AS (
         SELECT count(*) FILTER (WHERE (view_riksdagen_coalition_alignment_matrix.coalition_likelihood = 'STRONG_COALITION'::text)) AS high_probability_coalitions,
            count(*) FILTER (WHERE (view_riksdagen_coalition_alignment_matrix.coalition_likelihood = ANY (ARRAY['STRONG_COALITION'::text, 'MODERATE_COALITION'::text]))) AS cross_bloc_alliances
           FROM public.view_riksdagen_coalition_alignment_matrix
        ), anomaly_summary AS (
         SELECT count(*) FILTER (WHERE (view_riksdagen_voting_anomaly_detection.anomaly_classification = ANY (ARRAY['FREQUENT_STRONG_REBEL'::text, 'CONSISTENT_REBEL'::text]))) AS high_defection_risks,
            count(*) FILTER (WHERE (view_riksdagen_voting_anomaly_detection.anomaly_classification = ANY (ARRAY['FREQUENT_STRONG_REBEL'::text, 'CONSISTENT_REBEL'::text, 'MODERATE_REBEL'::text, 'OCCASIONAL_REBEL'::text]))) AS low_discipline_politicians
           FROM public.view_riksdagen_voting_anomaly_detection
        ), influence_summary AS (
         SELECT count(*) FILTER (WHERE (view_riksdagen_politician_influence_metrics.broker_classification = ANY (ARRAY['STRONG_BROKER'::text, 'MODERATE_BROKER'::text]))) AS power_brokers,
            count(*) FILTER (WHERE (view_riksdagen_politician_influence_metrics.influence_classification = 'HIGHLY_INFLUENTIAL'::text)) AS highly_connected_politicians
           FROM public.view_riksdagen_politician_influence_metrics
        ), resilience_summary AS (
         SELECT count(*) FILTER (WHERE (view_riksdagen_crisis_resilience_indicators.resilience_classification = 'HIGHLY_RESILIENT'::text)) AS crisis_ready_politicians,
            count(*) FILTER (WHERE (view_riksdagen_crisis_resilience_indicators.resilience_classification = 'LOW_RESILIENCE'::text)) AS low_resilience_politicians
           FROM public.view_riksdagen_crisis_resilience_indicators
        ), vote_stats AS (
         SELECT max(vote_data.vote_date) AS latest_vote_data,
            count(DISTINCT vote_data.embedded_id_ballot_id) FILTER (WHERE (vote_data.vote_date >= (CURRENT_DATE - '30 days'::interval))) AS ballots_last_30_days
           FROM public.vote_data
        )
 SELECT ms.parties_gaining_momentum,
    ms.parties_losing_momentum,
    ms.volatile_parties,
    cs.high_probability_coalitions,
    cs.cross_bloc_alliances,
    ans.high_defection_risks,
    ans.low_discipline_politicians,
    ins.power_brokers,
    ins.highly_connected_politicians,
    rs.crisis_ready_politicians,
    rs.low_resilience_politicians,
        CASE
            WHEN (ans.high_defection_risks >= 5) THEN 'HIGH_POLITICAL_INSTABILITY_RISK'::text
            WHEN (ms.volatile_parties >= 3) THEN 'MODERATE_POLITICAL_INSTABILITY_RISK'::text
            ELSE 'STABLE_POLITICAL_ENVIRONMENT'::text
        END AS stability_assessment,
        CASE
            WHEN (cs.cross_bloc_alliances >= 2) THEN 'POTENTIAL_REALIGNMENT_DETECTED'::text
            WHEN (cs.high_probability_coalitions >= 5) THEN 'STABLE_COALITION_PATTERNS'::text
            ELSE 'UNCERTAIN_COALITION_LANDSCAPE'::text
        END AS coalition_assessment,
    vs.latest_vote_data,
    vs.ballots_last_30_days,
    CURRENT_TIMESTAMP AS intelligence_report_timestamp
   FROM (((((momentum_summary ms
     CROSS JOIN coalition_summary cs)
     CROSS JOIN anomaly_summary ans)
     CROSS JOIN influence_summary ins)
     CROSS JOIN resilience_summary rs)
     CROSS JOIN vote_stats vs);


--
-- Name: view_riksdagen_member_proposals; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_member_proposals AS
 SELECT id,
    committee_report_url_xml,
    created_date,
    document_format,
    document_status_url_xml,
    document_type,
    document_url_html,
    document_url_text,
    hit,
    kall_id,
    label,
    made_public_date,
    number_value,
    org,
    related_id,
    rm,
    status,
    sub_title,
    sub_type,
    system_date,
    temp_label,
    title,
    dokument_document_container__0
   FROM public.document_element
  WHERE (((document_type)::text = ANY (ARRAY[('mot'::character varying)::text, ('MOT'::character varying)::text, ('Motion'::character varying)::text])) OR (upper((document_type)::text) = 'MOT'::text));


--
-- Name: view_riksdagen_org_document_daily_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_org_document_daily_summary AS
 SELECT "left"((made_public_date)::text, 10) AS embedded_id_public_date,
    org AS embedded_id_org,
    document_type,
    count(*) AS total
   FROM public.document_element
  GROUP BY ("left"((made_public_date)::text, 10)), document_type, org
  WITH NO DATA;


--
-- Name: view_riksdagen_politician_document_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

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


--
-- Name: view_riksdagen_party_member; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party_member AS
 SELECT sp.hjid,
    sp.address,
    sp.city,
    sp.co_address,
    sp.email,
    sp.fax_number,
    sp.party_id,
    sp.party_name,
    sp.phone_number,
    sp.post_code,
    sp.registered_date,
    sp.short_code,
    sp.website,
    sp.parties_sweden_election_regi_0,
    p.id,
    p.born_year,
    p.election_region,
    p.first_name,
    p.gender,
    p.hangar_guid,
    p.image_url_192,
    p.image_url_80,
    p.image_url_max,
    p.last_name,
    p.party,
    p.person_url_xml,
    p.place,
    p.status,
    p.person_assignment_data_perso_0,
    p.person_detail_data_person_da_0,
    COALESCE(ds.total_documents, (0)::bigint) AS total_documents,
    COALESCE(ds.party_motions, (0)::bigint) AS party_motions,
    COALESCE(ds.individual_motions, (0)::bigint) AS individual_motions,
    COALESCE(ds.committee_motions, (0)::bigint) AS committee_motions,
    COALESCE(ds.multi_party_motions, (0)::bigint) AS multi_party_motions,
    COALESCE(ds.documents_last_year, (0)::bigint) AS documents_last_year,
    COALESCE(ds.activity_level, 'Inactive'::text) AS activity_level,
    COALESCE(ds.activity_profile, 'None'::text) AS activity_profile,
    COALESCE(ds.collaboration_percentage, (0)::numeric) AS collaboration_percentage
   FROM ((public.sweden_political_party sp
     JOIN public.person_data p ON (((p.party)::text = (sp.short_code)::text)))
     LEFT JOIN public.view_riksdagen_politician_document_summary ds ON (((p.id)::text = (ds.person_reference_id)::text)));


--
-- Name: view_riksdagen_party; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party AS
 SELECT party_id AS party_number,
    party_name,
    short_code AS party_id,
    website,
    registered_date,
    count(DISTINCT id) AS head_count,
    sum(COALESCE(total_documents, (0)::bigint)) AS total_documents,
    round(avg(COALESCE(total_documents, (0)::bigint)), 1) AS avg_documents_per_member,
    sum(COALESCE(documents_last_year, (0)::bigint)) AS documents_last_year,
    count(
        CASE
            WHEN (activity_level = 'Very High'::text) THEN 1
            ELSE NULL::integer
        END) AS very_high_activity_members,
    count(
        CASE
            WHEN (activity_level = 'High'::text) THEN 1
            ELSE NULL::integer
        END) AS high_activity_members,
    count(
        CASE
            WHEN (activity_level = 'Medium'::text) THEN 1
            ELSE NULL::integer
        END) AS medium_activity_members,
    count(
        CASE
            WHEN (activity_level = 'Low'::text) THEN 1
            ELSE NULL::integer
        END) AS low_activity_members,
    count(
        CASE
            WHEN (activity_profile = 'Party-focused'::text) THEN 1
            ELSE NULL::integer
        END) AS party_focused_members,
    count(
        CASE
            WHEN (activity_profile = 'Committee-focused'::text) THEN 1
            ELSE NULL::integer
        END) AS committee_focused_members,
    count(
        CASE
            WHEN (activity_profile = 'Individual-focused'::text) THEN 1
            ELSE NULL::integer
        END) AS individual_focused_members,
    round(avg(COALESCE(collaboration_percentage, (0)::numeric)), 1) AS avg_collaboration_percentage
   FROM public.view_riksdagen_party_member
  GROUP BY party_id, party_name, short_code, website, registered_date
  ORDER BY short_code;


--
-- Name: view_riksdagen_party_ballot_support_annual_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party_ballot_support_annual_summary AS
 SELECT to_char(date_trunc('month'::text, (p1.vote_date)::timestamp with time zone), 'YYYY-MM-DD'::text) AS embedded_id_date,
    p1.embedded_id_party,
    p2.embedded_id_party AS embedded_id_other_party,
    sum(
        CASE
            WHEN (p1.party_approved = p2.party_approved) THEN 1
            ELSE 0
        END) AS agree_count,
    sum(
        CASE
            WHEN (p1.party_approved = p2.party_approved) THEN 0
            ELSE 1
        END) AS disagre_count,
    ((sum(
        CASE
            WHEN (p1.party_approved = p2.party_approved) THEN 0
            ELSE 1
        END) * 100) / count(*)) AS disagree_percentage,
    count(*) AS total_ballots
   FROM (public.view_riksdagen_vote_data_ballot_party_summary p1
     JOIN public.view_riksdagen_vote_data_ballot_party_summary p2 ON ((((p1.embedded_id_party)::text <> (p2.embedded_id_party)::text) AND ((p1.embedded_id_ballot_id)::text = (p2.embedded_id_ballot_id)::text) AND ((p1.embedded_id_issue)::text = (p2.embedded_id_issue)::text))))
  GROUP BY p1.embedded_id_party, p2.embedded_id_party, (to_char(date_trunc('month'::text, (p1.vote_date)::timestamp with time zone), 'YYYY-MM-DD'::text));


--
-- Name: view_riksdagen_party_coalation_against_annual_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party_coalation_against_annual_summary AS
 SELECT quote_literal(upper(replace(replace((against_proposal_parties)::text, ' '::text, ''::text), '"'::text, ''::text))) AS embedded_id_group_against,
    SUBSTRING(rm FROM 1 FOR 4) AS embedded_id_year,
    count(*) AS total
   FROM public.committee_proposal_data
  WHERE (((decision_type)::text = 'röstning'::text) AND (against_proposal_parties IS NOT NULL) AND ((against_proposal_parties)::text <> ''::text) AND (char_length((against_proposal_parties)::text) > 10))
  GROUP BY (quote_literal(upper(replace(replace((against_proposal_parties)::text, ' '::text, ''::text), '"'::text, ''::text)))), rm
  ORDER BY rm;


--
-- Name: view_riksdagen_party_decision_flow; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party_decision_flow AS
 SELECT dpr.party_short_code AS party,
    dpd.committee,
    dpd.decision_type,
    dd.org AS committee_org,
    date_trunc('month'::text, (dd.made_public_date)::timestamp with time zone) AS decision_month,
    EXTRACT(year FROM dd.made_public_date) AS decision_year,
    EXTRACT(month FROM dd.made_public_date) AS decision_month_num,
    count(*) AS total_proposals,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%BIFALL%'::text) OR (upper((dpd.chamber)::text) ~~ '%GODKÄNT%'::text) OR (upper((dpd.chamber)::text) ~~ '%BIFALLA%'::text))) AS approved_proposals,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%AVSLAG%'::text) OR (upper((dpd.chamber)::text) ~~ '%AVSLÅ%'::text))) AS rejected_proposals,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%ÅTERFÖRVISNING%'::text) OR (upper((dpd.chamber)::text) ~~ '%ÅTERFÖRVISA%'::text))) AS referred_back_proposals,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) !~~ '%BIFALL%'::text) AND (upper((dpd.chamber)::text) !~~ '%AVSLAG%'::text) AND (upper((dpd.chamber)::text) !~~ '%GODKÄNT%'::text) AND (upper((dpd.chamber)::text) !~~ '%BIFALLA%'::text) AND (upper((dpd.chamber)::text) !~~ '%AVSLÅ%'::text) AND (upper((dpd.chamber)::text) !~~ '%ÅTERFÖRVISNING%'::text) AND (upper((dpd.chamber)::text) !~~ '%ÅTERFÖRVISA%'::text))) AS other_decisions,
    round(((100.0 * (count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%BIFALL%'::text) OR (upper((dpd.chamber)::text) ~~ '%GODKÄNT%'::text) OR (upper((dpd.chamber)::text) ~~ '%BIFALLA%'::text))))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS approval_rate,
    round(((100.0 * (count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%AVSLAG%'::text) OR (upper((dpd.chamber)::text) ~~ '%AVSLÅ%'::text))))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS rejection_rate,
    min(dd.made_public_date) AS earliest_decision_date,
    max(dd.made_public_date) AS latest_decision_date
   FROM (((((public.document_proposal_data dpd
     JOIN public.document_proposal_container dpc ON ((dpc.proposal_document_proposal_c_0 = dpd.hjid)))
     JOIN public.document_status_container dsc ON ((dsc.document_proposal_document_s_0 = dpc.hjid)))
     JOIN public.document_data dd ON (((dd.id)::text = (dsc.document_document_status_con_0)::text)))
     LEFT JOIN public.document_person_reference_co_0 dprc ON ((dprc.hjid = dsc.document_person_reference_co_1)))
     LEFT JOIN public.document_person_reference_da_0 dpr ON ((dpr.document_person_reference_li_1 = dprc.hjid)))
  WHERE ((dpd.chamber IS NOT NULL) AND (dpd.committee IS NOT NULL) AND (dd.made_public_date IS NOT NULL) AND (length((dpd.chamber)::text) >= 6) AND (length((dpd.chamber)::text) <= 29))
  GROUP BY dpr.party_short_code, dpd.committee, dpd.decision_type, dd.org, (date_trunc('month'::text, (dd.made_public_date)::timestamp with time zone)), (EXTRACT(year FROM dd.made_public_date)), (EXTRACT(month FROM dd.made_public_date))
 HAVING (count(*) > 0)
  ORDER BY (EXTRACT(year FROM dd.made_public_date)) DESC, (EXTRACT(month FROM dd.made_public_date)) DESC, dpr.party_short_code, dpd.committee;


--
-- Name: view_riksdagen_party_document_daily_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_party_document_daily_summary AS
 SELECT made_public_date AS embedded_id_public_date,
    party_short_code AS embedded_id_party_short_code,
    document_type AS embedded_id_document_type,
    count(DISTINCT id) AS distinct_documents,
    count(DISTINCT person_reference_id) AS distinct_people,
    count(*) AS total
   FROM public.view_riksdagen_politician_document
  GROUP BY made_public_date, document_type, party_short_code
  WITH NO DATA;


--
-- Name: view_riksdagen_politician; Type: VIEW; Schema: public; Owner: -
--

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
                END) AS total_days_served_committee_leadership
           FROM (public.assignment_data
             LEFT JOIN public.view_riksdagen_party_member ON (((assignment_data.intressent_id)::text = (view_riksdagen_party_member.id)::text)))
          GROUP BY view_riksdagen_party_member.id) base
     LEFT JOIN public.view_riksdagen_politician_document_summary ds ON (((base.person_id)::text = (ds.person_reference_id)::text)));


--
-- Name: view_riksdagen_party_document_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party_document_summary AS
 SELECT p.party,
    count(DISTINCT pds.person_reference_id) AS total_contributing_members,
    sum(pds.total_documents) AS total_party_documents,
    round(avg(pds.total_documents), 1) AS avg_documents_per_member,
    sum(pds.party_motions) AS total_party_motions,
    sum(pds.individual_motions) AS total_individual_motions,
    sum(pds.committee_motions) AS total_committee_motions,
    sum(pds.multi_party_motions) AS total_collaborative_motions,
    count(
        CASE
            WHEN (pds.activity_level = 'Very High'::text) THEN 1
            ELSE NULL::integer
        END) AS very_high_activity_members,
    count(
        CASE
            WHEN (pds.activity_level = 'High'::text) THEN 1
            ELSE NULL::integer
        END) AS high_activity_members,
    count(
        CASE
            WHEN (pds.activity_level = 'Medium'::text) THEN 1
            ELSE NULL::integer
        END) AS medium_activity_members,
    count(
        CASE
            WHEN (pds.activity_level = 'Low'::text) THEN 1
            ELSE NULL::integer
        END) AS low_activity_members,
    count(
        CASE
            WHEN (pds.activity_profile = 'Party-focused'::text) THEN 1
            ELSE NULL::integer
        END) AS party_focused_members,
    count(
        CASE
            WHEN (pds.activity_profile = 'Committee-focused'::text) THEN 1
            ELSE NULL::integer
        END) AS committee_focused_members,
    count(
        CASE
            WHEN (pds.activity_profile = 'Individual-focused'::text) THEN 1
            ELSE NULL::integer
        END) AS individual_focused_members,
    min(pds.first_document_date) AS first_party_document,
    max(pds.last_document_date) AS last_party_document,
    sum(pds.documents_last_year) AS total_documents_last_year,
    count(
        CASE
            WHEN (pds.documents_last_year > 0) THEN 1
            ELSE NULL::integer
        END) AS active_members_last_year,
    round(avg(pds.docs_per_year), 1) AS avg_docs_per_year,
    round(avg(pds.collaboration_percentage), 1) AS avg_collaboration_pct,
    sum(pds.propositions) AS total_propositions,
    sum(pds.government_communications) AS total_government_communications
   FROM (public.view_riksdagen_politician_document_summary pds
     JOIN public.view_riksdagen_politician p ON (((pds.person_reference_id)::text = (p.person_id)::text)))
  GROUP BY p.party;


--
-- Name: view_riksdagen_party_role_member; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party_role_member AS
 SELECT a.hjid AS role_id,
    a.detail,
    a.role_code,
    p.first_name,
    p.last_name,
    a.from_date,
    a.to_date,
    p.id AS person_id,
    p.party,
    (
        CASE
            WHEN ((a.to_date > CURRENT_DATE) OR (a.to_date IS NULL)) THEN CURRENT_DATE
            ELSE a.to_date
        END -
        CASE
            WHEN (a.from_date > CURRENT_DATE) THEN CURRENT_DATE
            ELSE a.from_date
        END) AS total_days_served,
        CASE
            WHEN ((a.to_date IS NULL) OR ((a.to_date > CURRENT_DATE) AND (a.from_date < CURRENT_DATE))) THEN true
            ELSE false
        END AS active,
    COALESCE(doc_stats.total_documents, (0)::bigint) AS total_documents,
    COALESCE(doc_stats.documents_last_year, (0)::bigint) AS documents_last_year,
    COALESCE(doc_stats.motions, (0)::bigint) AS total_motions,
    COALESCE(doc_stats.interpellations, (0)::bigint) AS total_interpellations,
    COALESCE(doc_stats.written_questions, (0)::bigint) AS total_written_questions,
        CASE
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 50) THEN 'Very High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 25) THEN 'High'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 10) THEN 'Medium'::text
            WHEN (COALESCE(doc_stats.documents_last_year, (0)::bigint) > 0) THEN 'Low'::text
            ELSE 'Inactive'::text
        END AS activity_level,
        CASE
            WHEN ((a.role_code)::text = 'Partiledare'::text) THEN 'Party Leader'::text
            WHEN ((a.role_code)::text = 'Språkrör'::text) THEN 'Party Spokesperson'::text
            WHEN ((a.role_code)::text = 'Gruppledare'::text) THEN 'Group Leader'::text
            WHEN ((a.role_code)::text ~~ '%Förste vice gruppledare%'::text) THEN 'First Deputy Group Leader'::text
            WHEN ((a.role_code)::text ~~ '%Andre vice gruppledare%'::text) THEN 'Second Deputy Group Leader'::text
            WHEN ((a.role_code)::text = 'Partisekreterare'::text) THEN 'Party Secretary'::text
            WHEN ((a.role_code)::text = 'Tillförordnad partisekreterare'::text) THEN 'Acting Party Secretary'::text
            WHEN ((a.role_code)::text = 'Kvittningsperson'::text) THEN 'Pairing Representative'::text
            ELSE 'Other'::text
        END AS role_type
   FROM ((public.assignment_data a
     LEFT JOIN public.person_data p ON (((a.intressent_id)::text = (p.id)::text)))
     LEFT JOIN LATERAL ( SELECT view_riksdagen_politician_document.person_reference_id,
            count(*) AS total_documents,
            count(
                CASE
                    WHEN (view_riksdagen_politician_document.made_public_date >= (CURRENT_DATE - '1 year'::interval)) THEN 1
                    ELSE NULL::integer
                END) AS documents_last_year,
            count(
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = 'mot'::text) THEN 1
                    ELSE NULL::integer
                END) AS motions,
            count(
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = 'ip'::text) THEN 1
                    ELSE NULL::integer
                END) AS interpellations,
            count(
                CASE
                    WHEN ((view_riksdagen_politician_document.document_type)::text = 'frs'::text) THEN 1
                    ELSE NULL::integer
                END) AS written_questions
           FROM public.view_riksdagen_politician_document
          WHERE (view_riksdagen_politician_document.made_public_date >= (CURRENT_DATE - '5 years'::interval))
          GROUP BY view_riksdagen_politician_document.person_reference_id) doc_stats ON (((doc_stats.person_reference_id)::text = (p.id)::text)))
  WHERE ((a.assignment_type)::text = 'partiuppdrag'::text)
  ORDER BY a.detail, a.role_code, a.from_date DESC;


--
-- Name: view_riksdagen_party_signatures_document_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party_signatures_document_summary AS
 SELECT upper((party_short_code)::text) AS party,
    count(*) AS total
   FROM public.document_person_reference_da_0
  WHERE (NOT ((party_short_code)::text = ''::text))
  GROUP BY (upper((party_short_code)::text))
  ORDER BY (count(*));


--
-- Name: view_riksdagen_party_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_party_summary AS
 SELECT p.party,
    min(p.first_assignment_date) AS first_assignment_date,
    max(p.last_assignment_date) AS last_assignment_date,
    (sum(p.total_days_served))::bigint AS total_days_served,
    (sum(p.total_days_served_parliament))::bigint AS total_days_served_parliament,
    (sum(p.total_days_served_committee))::bigint AS total_days_served_committee,
    (sum(p.total_days_served_government))::bigint AS total_days_served_government,
    (sum(p.total_days_served_eu))::bigint AS total_days_served_eu,
    (sum(p.total_days_served_speaker))::bigint AS total_days_served_speaker,
    (sum(p.total_days_served_party))::bigint AS total_days_served_party,
    bool_or(p.active) AS active,
    bool_or(p.active_eu) AS active_eu,
    bool_or(p.active_government) AS active_government,
    bool_or(p.active_committee) AS active_committee,
    bool_or(p.active_parliament) AS active_parliament,
    bool_or(p.active_speaker) AS active_speaker,
    bool_or(p.active_party) AS active_party,
    sum(
        CASE
            WHEN p.active THEN 1
            ELSE 0
        END) AS total_active,
    sum(
        CASE
            WHEN p.active_eu THEN 1
            ELSE 0
        END) AS total_active_eu,
    sum(
        CASE
            WHEN p.active_government THEN 1
            ELSE 0
        END) AS total_active_government,
    sum(
        CASE
            WHEN p.active_committee THEN 1
            ELSE 0
        END) AS total_active_committee,
    sum(
        CASE
            WHEN p.active_parliament THEN 1
            ELSE 0
        END) AS total_active_parliament,
    (sum(p.total_assignments))::bigint AS total_assignments,
    (sum(p.total_party_assignments))::bigint AS total_party_assignments,
    (sum(p.total_committee_assignments))::bigint AS total_committee_assignments,
    (sum(p.total_ministry_assignments))::bigint AS total_ministry_assignments,
    (sum(p.total_speaker_assignments))::bigint AS total_speaker_assignments,
    (sum(p.current_assignments))::bigint AS current_assignments,
    (sum(p.current_party_assignments))::bigint AS current_party_assignments,
    (sum(p.current_committee_assignments))::bigint AS current_committee_assignments,
    (sum(p.current_ministry_assignments))::bigint AS current_ministry_assignments,
    (sum(p.current_speaker_assignments))::bigint AS current_speaker_assignments,
    (sum(p.total_committee_substitute_assignments))::bigint AS total_committee_substitute_assignments,
    (sum(p.current_committee_substitute_assignments))::bigint AS current_committee_substitute_assignments,
    (sum(p.total_days_served_committee_substitute))::bigint AS total_days_served_committee_substitute,
    (sum(p.total_committee_leadership_assignments))::bigint AS total_committee_leadership_assignments,
    (sum(p.current_committee_leadership_assignments))::bigint AS current_committee_leadership_assignments,
    (sum(p.total_days_served_committee_leadership))::bigint AS total_days_served_committee_leadership,
    sum(COALESCE(ds.total_documents, (0)::bigint)) AS total_documents,
    round(avg(COALESCE(ds.total_documents, (0)::bigint)), 1) AS avg_documents_per_member,
    sum(COALESCE(ds.party_motions, (0)::bigint)) AS total_party_motions,
    sum(COALESCE(ds.individual_motions, (0)::bigint)) AS total_individual_motions,
    sum(COALESCE(ds.committee_motions, (0)::bigint)) AS total_committee_motions,
    sum(COALESCE(ds.multi_party_motions, (0)::bigint)) AS total_collaborative_motions,
    sum(COALESCE(ds.follow_up_motions, (0)::bigint)) AS total_follow_up_motions,
    count(
        CASE
            WHEN (ds.activity_level = 'Very High'::text) THEN 1
            ELSE NULL::integer
        END) AS very_high_activity_members,
    count(
        CASE
            WHEN (ds.activity_level = 'High'::text) THEN 1
            ELSE NULL::integer
        END) AS high_activity_members,
    count(
        CASE
            WHEN (ds.activity_level = 'Medium'::text) THEN 1
            ELSE NULL::integer
        END) AS medium_activity_members,
    count(
        CASE
            WHEN (ds.activity_level = 'Low'::text) THEN 1
            ELSE NULL::integer
        END) AS low_activity_members,
    count(
        CASE
            WHEN (ds.activity_profile = 'Party-focused'::text) THEN 1
            ELSE NULL::integer
        END) AS party_focused_members,
    count(
        CASE
            WHEN (ds.activity_profile = 'Committee-focused'::text) THEN 1
            ELSE NULL::integer
        END) AS committee_focused_members,
    count(
        CASE
            WHEN (ds.activity_profile = 'Individual-focused'::text) THEN 1
            ELSE NULL::integer
        END) AS individual_focused_members,
    count(
        CASE
            WHEN (ds.documents_last_year > 0) THEN 1
            ELSE NULL::integer
        END) AS currently_active_members,
    sum(COALESCE(ds.documents_last_year, (0)::bigint)) AS total_documents_last_year,
    round(avg(COALESCE(ds.documents_last_year, (0)::bigint)), 1) AS avg_documents_last_year,
    min(ds.first_document_date) AS first_party_document,
    max(ds.last_document_date) AS last_party_document,
    round(avg(COALESCE(ds.collaboration_percentage, (0)::numeric)), 1) AS avg_collaboration_percentage,
    sum(
        CASE
            WHEN (ds.collaboration_percentage > (50)::numeric) THEN 1
            ELSE 0
        END) AS highly_collaborative_members
   FROM (public.view_riksdagen_politician p
     LEFT JOIN public.view_riksdagen_politician_document_summary ds ON (((p.person_id)::text = (ds.person_reference_id)::text)))
  GROUP BY p.party
  ORDER BY p.party;


--
-- Name: view_riksdagen_person_signed_document_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_person_signed_document_summary AS
 SELECT person_reference_id AS person_id,
    reference_name AS person_name,
    max((party_short_code)::text) AS party,
    count(*) AS total
   FROM public.document_person_reference_da_0
  GROUP BY person_reference_id, reference_name
  ORDER BY (count(*));


--
-- Name: view_riksdagen_politician_ballot_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_politician_ballot_summary AS
 WITH politician_voting_aggregates AS (
         SELECT view_riksdagen_vote_data_ballot_politician_summary_annual.embedded_id_intressent_id,
            sum(view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes) AS total_votes,
            count(DISTINCT view_riksdagen_vote_data_ballot_politician_summary_annual.embedded_id_vote_date) AS voting_days,
            min(view_riksdagen_vote_data_ballot_politician_summary_annual.embedded_id_vote_date) AS first_vote_date,
            max(view_riksdagen_vote_data_ballot_politician_summary_annual.embedded_id_vote_date) AS last_vote_date,
            round((sum((view_riksdagen_vote_data_ballot_politician_summary_annual.percentage_yes * view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes)) / NULLIF(sum(view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes), (0)::numeric)), 2) AS yes_percentage,
            round((sum((view_riksdagen_vote_data_ballot_politician_summary_annual.percentage_no * view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes)) / NULLIF(sum(view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes), (0)::numeric)), 2) AS no_percentage,
            round((sum((view_riksdagen_vote_data_ballot_politician_summary_annual.percentage_absent * view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes)) / NULLIF(sum(view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes), (0)::numeric)), 2) AS absence_rate,
            round((sum((view_riksdagen_vote_data_ballot_politician_summary_annual.rebel_percentage * view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes)) / NULLIF(sum(view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes), (0)::numeric)), 2) AS rebel_rate,
            round((sum((view_riksdagen_vote_data_ballot_politician_summary_annual.won_percentage * view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes)) / NULLIF(sum(view_riksdagen_vote_data_ballot_politician_summary_annual.total_votes), (0)::numeric)), 2) AS success_rate
           FROM public.view_riksdagen_vote_data_ballot_politician_summary_annual
          GROUP BY view_riksdagen_vote_data_ballot_politician_summary_annual.embedded_id_intressent_id
        )
 SELECT pm.id AS person_id,
    pm.first_name,
    pm.last_name,
    pm.party,
    pm.born_year,
    pm.gender,
    pm.status,
    pm.election_region,
    COALESCE(pva.total_votes, (0)::numeric) AS total_votes,
    COALESCE(pva.yes_percentage, (0)::numeric) AS yes_percentage,
    COALESCE(pva.no_percentage, (0)::numeric) AS no_percentage,
    COALESCE(pva.absence_rate, (0)::numeric) AS absence_rate,
    COALESCE(pva.rebel_rate, (0)::numeric) AS rebel_rate,
    COALESCE(pva.success_rate, (0)::numeric) AS success_rate,
    COALESCE(pva.voting_days, (0)::bigint) AS voting_days,
    pva.first_vote_date,
    pva.last_vote_date,
        CASE
            WHEN ((pva.absence_rate IS NULL) OR (pva.absence_rate = (0)::numeric)) THEN 100.0
            ELSE round(((100)::numeric - pva.absence_rate), 2)
        END AS voting_participation_rate,
        CASE
            WHEN (pva.rebel_rate IS NULL) THEN 100.0
            ELSE round(((100)::numeric - pva.rebel_rate), 2)
        END AS loyalty_rate,
        CASE
            WHEN ((pva.success_rate IS NULL) OR (pva.rebel_rate IS NULL)) THEN (0)::numeric
            ELSE round((pva.success_rate * ((1)::numeric - (pva.rebel_rate / 100.0))), 2)
        END AS voting_consistency_score,
        CASE
            WHEN ((pva.total_votes IS NULL) OR (pva.total_votes = (0)::numeric)) THEN 'No voting record'::text
            WHEN (COALESCE(pva.absence_rate, (0)::numeric) > (25)::numeric) THEN 'Frequently absent'::text
            WHEN (COALESCE(pva.rebel_rate, (0)::numeric) >= (25)::numeric) THEN 'High propensity to dissent'::text
            WHEN (COALESCE(pva.rebel_rate, (0)::numeric) >= (10)::numeric) THEN 'Moderate propensity to dissent'::text
            WHEN (COALESCE(pva.rebel_rate, (0)::numeric) >= (5)::numeric) THEN 'Occasional dissent'::text
            WHEN (COALESCE(pva.success_rate, (0)::numeric) > (70)::numeric) THEN 'Often on winning side'::text
            ELSE 'Generally aligned'::text
        END AS analysis_comment
   FROM (public.view_riksdagen_party_member pm
     LEFT JOIN politician_voting_aggregates pva ON (((pm.id)::text = (pva.embedded_id_intressent_id)::text)))
  ORDER BY pm.last_name, pm.first_name;


--
-- Name: view_riksdagen_politician_decision_pattern; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_politician_decision_pattern AS
 SELECT pd.id AS person_id,
    pd.first_name,
    pd.last_name,
    dpr.party_short_code AS party,
    dpd.committee,
    dd.org AS committee_org,
    date_trunc('month'::text, (dd.made_public_date)::timestamp with time zone) AS decision_month,
    EXTRACT(year FROM dd.made_public_date) AS decision_year,
    EXTRACT(month FROM dd.made_public_date) AS decision_month_num,
    count(*) AS total_decisions,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%BIFALL%'::text) OR (upper((dpd.chamber)::text) ~~ '%GODKÄNT%'::text) OR (upper((dpd.chamber)::text) ~~ '%BIFALLA%'::text))) AS approved_decisions,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%AVSLAG%'::text) OR (upper((dpd.chamber)::text) ~~ '%AVSLÅ%'::text))) AS rejected_decisions,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%ÅTERFÖRVISNING%'::text) OR (upper((dpd.chamber)::text) ~~ '%ÅTERFÖRVISA%'::text))) AS referred_back_decisions,
    count(*) FILTER (WHERE ((upper((dpd.chamber)::text) !~~ '%BIFALL%'::text) AND (upper((dpd.chamber)::text) !~~ '%AVSLAG%'::text) AND (upper((dpd.chamber)::text) !~~ '%GODKÄNT%'::text) AND (upper((dpd.chamber)::text) !~~ '%BIFALLA%'::text) AND (upper((dpd.chamber)::text) !~~ '%AVSLÅ%'::text) AND (upper((dpd.chamber)::text) !~~ '%ÅTERFÖRVISNING%'::text) AND (upper((dpd.chamber)::text) !~~ '%ÅTERFÖRVISA%'::text))) AS other_decisions,
    round(((100.0 * (count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%BIFALL%'::text) OR (upper((dpd.chamber)::text) ~~ '%GODKÄNT%'::text) OR (upper((dpd.chamber)::text) ~~ '%BIFALLA%'::text))))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS approval_rate,
    round(((100.0 * (count(*) FILTER (WHERE ((upper((dpd.chamber)::text) ~~ '%AVSLAG%'::text) OR (upper((dpd.chamber)::text) ~~ '%AVSLÅ%'::text))))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS rejection_rate,
    min(dd.made_public_date) AS earliest_decision_date,
    max(dd.made_public_date) AS latest_decision_date
   FROM ((((((public.document_proposal_data dpd
     JOIN public.document_proposal_container dpc ON ((dpc.proposal_document_proposal_c_0 = dpd.hjid)))
     JOIN public.document_status_container dsc ON ((dsc.document_proposal_document_s_0 = dpc.hjid)))
     JOIN public.document_data dd ON (((dd.id)::text = (dsc.document_document_status_con_0)::text)))
     JOIN public.document_person_reference_co_0 dprc ON ((dprc.hjid = dsc.document_person_reference_co_1)))
     JOIN public.document_person_reference_da_0 dpr ON ((dpr.document_person_reference_li_1 = dprc.hjid)))
     JOIN public.person_data pd ON (((pd.id)::text = (dpr.person_reference_id)::text)))
  WHERE ((dpd.chamber IS NOT NULL) AND (dpd.committee IS NOT NULL) AND (dd.made_public_date IS NOT NULL) AND (pd.id IS NOT NULL) AND (length((dpd.chamber)::text) >= 6) AND (length((dpd.chamber)::text) <= 29))
  GROUP BY pd.id, pd.first_name, pd.last_name, dpr.party_short_code, dpd.committee, dd.org, (date_trunc('month'::text, (dd.made_public_date)::timestamp with time zone)), (EXTRACT(year FROM dd.made_public_date)), (EXTRACT(month FROM dd.made_public_date))
 HAVING (count(*) > 0)
  ORDER BY (EXTRACT(year FROM dd.made_public_date)) DESC, (EXTRACT(month FROM dd.made_public_date)) DESC, pd.last_name, pd.first_name, dpd.committee;


--
-- Name: view_riksdagen_politician_document_daily_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_politician_document_daily_summary AS
 SELECT made_public_date AS embedded_id_public_date,
    person_reference_id AS embedded_id_person_id,
    document_type AS embedded_id_document_type,
    count(*) AS total
   FROM public.view_riksdagen_politician_document
  GROUP BY made_public_date, document_type, person_reference_id
  WITH NO DATA;


--
-- Name: view_riksdagen_politician_experience_summary; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_riksdagen_politician_experience_summary AS
 WITH role_day_spans AS (
         SELECT a.intressent_id AS person_id,
            a.assignment_type,
            a.role_code,
            a.org_code,
                CASE
                    WHEN (a.to_date IS NULL) THEN (CURRENT_DATE - a.from_date)
                    ELSE (a.to_date - a.from_date)
                END AS days_in_role,
                CASE
                    WHEN ((a.org_code)::text = ANY (ARRAY[('FiU'::character varying)::text, ('KU'::character varying)::text, ('UU'::character varying)::text, ('FÖU'::character varying)::text, ('JuU'::character varying)::text])) THEN 'Key Parliamentary Committees'::text
                    WHEN ((a.org_code)::text = 'Statsrådsberedningen'::text) THEN 'Prime Minister''s Office'::text
                    WHEN ((a.org_code)::text = 'AU'::text) THEN 'Arbetsmarknad (Committee)'::text
                    WHEN ((a.org_code)::text = 'SoU'::text) THEN 'Social (Committee)'::text
                    WHEN ((a.org_code)::text = 'sou'::text) THEN 'Social (Committee)'::text
                    WHEN ((a.org_code)::text = 'SfU'::text) THEN 'Social Insurance (Committee)'::text
                    WHEN ((a.org_code)::text = 'FiU'::text) THEN 'Finance/Budget (Committee)'::text
                    WHEN ((a.org_code)::text = 'NU'::text) THEN 'Business/Industry (Committee)'::text
                    WHEN ((a.org_code)::text = 'UFÖU'::text) THEN 'Foreign & Defense (Committee)'::text
                    WHEN ((a.org_code)::text = 'FÖU'::text) THEN 'Defense (Committee)'::text
                    WHEN ((a.org_code)::text = 'KU'::text) THEN 'Constitutional (Committee)'::text
                    WHEN ((a.org_code)::text = 'KUU'::text) THEN 'Constitution & Foreign Affairs (Committee)'::text
                    WHEN ((a.org_code)::text = 'KrU'::text) THEN 'Culture (Committee)'::text
                    WHEN ((a.org_code)::text = 'CKrU'::text) THEN 'Civil & Culture (Committee)'::text
                    WHEN ((a.org_code)::text = 'USoU'::text) THEN 'Foreign & Social (Committee)'::text
                    WHEN ((a.org_code)::text = 'UMJU'::text) THEN 'Foreign, Environment & Agriculture (Committee)'::text
                    WHEN ((a.org_code)::text = 'MJU'::text) THEN 'Environment & Agriculture (Committee)'::text
                    WHEN ((a.org_code)::text = 'BoU'::text) THEN 'Housing (Committee)'::text
                    WHEN ((a.org_code)::text = 'SkU'::text) THEN 'Taxes (Committee)'::text
                    WHEN ((a.org_code)::text = 'UbU'::text) THEN 'Education (Committee)'::text
                    WHEN ((a.org_code)::text = 'UU'::text) THEN 'Foreign Affairs (Committee)'::text
                    WHEN ((a.org_code)::text = 'EUN'::text) THEN 'EU Affairs (Committee)'::text
                    WHEN ((a.org_code)::text = 'JuU'::text) THEN 'Justice (Committee)'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'SB'::text)) THEN 'Prime Ministers Office'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'Fi'::text)) THEN 'Finance Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'UD'::text)) THEN 'Foreign Affairs Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'Ju'::text)) THEN 'Justice Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'N'::text)) THEN 'Business and Innovation Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'M'::text)) THEN 'Environment Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'A'::text)) THEN 'Labor Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'I'::text)) THEN 'Infrastructure Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'LI'::text)) THEN 'Rural Affairs Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'Jo'::text)) THEN 'Agriculture Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'S'::text)) THEN 'Social Affairs Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'U'::text)) THEN 'Education Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'FÖ'::text)) THEN 'Defense Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'IJ'::text)) THEN 'Integration and Gender Equality Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'KN'::text)) THEN 'Climate and Business Ministry'::text
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.org_code)::text = 'Ku'::text)) THEN 'Culture Ministry'::text
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = ANY (ARRAY[('Partiledare'::character varying)::text, ('Gruppledare'::character varying)::text, ('Partisekreterare'::character varying)::text, ('Kvittningsperson'::character varying)::text]))) THEN 'Party Leadership'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('BSPC'::character varying)::text, ('EFTA'::character varying)::text, ('EG'::character varying)::text, ('OSSE'::character varying)::text, ('PA-UfM'::character varying)::text, ('Europol'::character varying)::text])) THEN 'International Affairs'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('NR'::character varying)::text, ('RFK'::character varying)::text, ('RJ'::character varying)::text, ('RRS'::character varying)::text])) THEN 'Regional and National Cooperation'::text
                    WHEN (((a.org_code)::text = ANY (ARRAY[('BN'::character varying)::text, ('CPAR'::character varying)::text, ('DEM'::character varying)::text, ('DN'::character varying)::text, ('EES'::character varying)::text, ('ER'::character varying)::text, ('ESK'::character varying)::text, ('RB'::character varying)::text, ('RGK'::character varying)::text, ('UN'::character varying)::text])) AND ((a.role_code)::text = ANY (ARRAY[('Ledamot'::character varying)::text, ('Ordförande'::character varying)::text, ('Vice ordförande'::character varying)::text]))) THEN 'Legislative and Oversight Committees'::text
                    WHEN ((a.org_code)::text = 'FöU'::text) THEN 'Defense (Committee)'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('NSÖ'::character varying)::text, ('ÖN'::character varying)::text, ('RS'::character varying)::text])) THEN 'Regional and National Cooperation'::text
                    WHEN ((a.org_code)::text = 'UFöU'::text) THEN 'Foreign & Defense (Committee)'::text
                    WHEN ((a.org_code)::text = 'EP'::text) THEN 'European Parliament'::text
                    WHEN (((a.org_code)::text = ANY (ARRAY[('BN'::character varying)::text, ('CPAR'::character varying)::text, ('DEM'::character varying)::text, ('DN'::character varying)::text, ('EES'::character varying)::text, ('ER'::character varying)::text, ('ESK'::character varying)::text, ('RB'::character varying)::text, ('RGK'::character varying)::text, ('UN'::character varying)::text])) AND ((a.role_code)::text = ANY (ARRAY[('Ledamot'::character varying)::text, ('Ordförande'::character varying)::text, ('Vice ordförande'::character varying)::text]))) THEN 'Legislative and Oversight Committees'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('CU'::character varying)::text, ('LU'::character varying)::text, ('KD'::character varying)::text, ('FÖU'::character varying)::text, ('JuSoU'::character varying)::text, ('VB'::character varying)::text])) THEN 'Legislative and Oversight Committees'::text
                    WHEN ((a.org_code)::text = 'kam'::text) THEN 'Speaker''s Office'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('RJ'::character varying)::text, ('Systembolaget'::character varying)::text])) THEN 'Special Oversight Roles'::text
                    WHEN ((a.role_code)::text = ANY (ARRAY[('Suppleant'::character varying)::text, ('Extra suppleant'::character varying)::text, ('Ersättare'::character varying)::text, ('Personlig suppleant'::character varying)::text])) THEN 'Substitute Roles'::text
                    WHEN ((a.org_code)::text = 'UFÖU'::text) THEN 'Foreign & Defense (Committee)'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('TK'::character varying)::text, ('sku'::character varying)::text])) THEN 'Other Legislative Committees'::text
                    WHEN ((a.assignment_type)::text = 'partiuppdrag'::text) THEN 'Party Leadership'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('UFÖU'::character varying)::text, ('VPN'::character varying)::text, ('RRPR'::character varying)::text, ('RRR'::character varying)::text])) THEN 'Regional and National Cooperation'::text
                    WHEN ((a.org_code)::text = 'Systembolaget'::text) THEN 'Special Oversight Roles'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('EMPA'::character varying)::text, ('IPU'::character varying)::text, ('NATO'::character varying)::text])) THEN 'International Affairs'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('FiU'::character varying)::text, ('KU'::character varying)::text, ('JU'::character varying)::text, ('BoU'::character varying)::text, ('TU'::character varying)::text])) THEN 'Legislative Committees'::text
                    WHEN ((a.assignment_type)::text = 'Departement'::text) THEN 'Ministry'::text
                    WHEN ((a.role_code)::text = 'Personlig ersättare'::text) THEN 'Substitute Roles'::text
                    WHEN ((a.org_code)::text = 'EU'::text) THEN 'EU Affairs (Committee)'::text
                    WHEN ((a.org_code)::text = 'LR'::text) THEN 'Regional and National Cooperation'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('RAN'::character varying)::text, ('RAR'::character varying)::text])) THEN 'Legislative and Oversight Committees'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('FiU'::character varying)::text, ('KU'::character varying)::text, ('UU'::character varying)::text, ('FÖU'::character varying)::text, ('JuU'::character varying)::text])) THEN 'Key Parliamentary Committees'::text
                    WHEN ((a.org_code)::text = 'Statsrådsberedningen'::text) THEN 'Prime Ministers Office'::text
                    WHEN ((a.org_code)::text = 'UFÖU'::text) THEN 'Foreign & Defense (Committee)'::text
                    WHEN ((a.org_code)::text = 'EU'::text) THEN 'EU Affairs (Committee)'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('EFTA'::character varying)::text, ('EG'::character varying)::text, ('OSSE'::character varying)::text, ('PA-UfM'::character varying)::text, ('BSPC'::character varying)::text])) THEN 'International Affairs'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('NR'::character varying)::text, ('RFK'::character varying)::text, ('RJ'::character varying)::text, ('RRS'::character varying)::text])) THEN 'Regional and National Cooperation'::text
                    WHEN ((a.org_code)::text = ANY (ARRAY[('MJU'::character varying)::text, ('BoU'::character varying)::text, ('TU'::character varying)::text])) THEN 'Other Legislative Committees'::text
                    WHEN ((a.assignment_type)::text = 'partiuppdrag'::text) THEN 'Party Leadership'::text
                    WHEN ((a.role_code)::text = ANY (ARRAY[('Suppleant'::character varying)::text, ('Extra suppleant'::character varying)::text, ('Ersättare'::character varying)::text, ('Personlig suppleant'::character varying)::text])) THEN 'Substitute Roles'::text
                    WHEN ((a.role_code)::text = ANY (ARRAY[('Suppleant'::character varying)::text, ('Extra suppleant'::character varying)::text])) THEN 'Substitute Roles'::text
                    ELSE 'Other'::text
                END AS knowledge_area,
                CASE
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text = 'Statsminister'::text)) THEN 50000
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = 'Partiledare'::text)) THEN 45000
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text = 'Vice statsminister'::text)) THEN 45000
                    WHEN (((a.assignment_type)::text = 'kammaruppdrag'::text) AND ((a.role_code)::text = 'Talman'::text)) THEN 40000
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text ~~* '%minister%'::text)) THEN 40000
                    WHEN (((a.assignment_type)::text = 'Riksdagsorgan'::text) AND ((a.role_code)::text = 'Ordförande'::text)) THEN 35000
                    WHEN (((a.assignment_type)::text = 'uppdrag'::text) AND ((a.role_code)::text = 'Ordförande'::text) AND ((a.org_code)::text = ANY (ARRAY[('FiU'::character varying)::text, ('KU'::character varying)::text, ('UU'::character varying)::text, ('FÖU'::character varying)::text, ('JuU'::character varying)::text]))) THEN 35000
                    WHEN (((a.assignment_type)::text = 'uppdrag'::text) AND ((a.role_code)::text = 'Vice ordförande'::text)) THEN 30000
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = ANY (ARRAY[('Gruppledare'::character varying)::text, ('Partisekreterare'::character varying)::text]))) THEN 30000
                    WHEN (((a.assignment_type)::text = 'kammaruppdrag'::text) AND ((a.role_code)::text = 'Riksdagsledamot'::text)) THEN 20000
                    WHEN (((a.assignment_type)::text = 'Europaparlamentet'::text) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 20000
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 15000
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.role_code)::text = 'Ledamot'::text) AND ((a.org_code)::text = ANY (ARRAY[('FiU'::character varying)::text, ('KU'::character varying)::text, ('UU'::character varying)::text, ('FÖU'::character varying)::text]))) THEN 18000
                    WHEN ((a.role_code)::text = ANY (ARRAY[('Suppleant'::character varying)::text, ('Ersättare'::character varying)::text])) THEN 10000
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.role_code)::text = 'Suppleant'::text) AND ((a.org_code)::text = ANY (ARRAY[('FiU'::character varying)::text, ('KU'::character varying)::text, ('UU'::character varying)::text]))) THEN 12000
                    WHEN (((a.assignment_type)::text = 'Riksdagsorgan'::text) AND ((a.org_code)::text = ANY (ARRAY[('RJ'::character varying)::text, ('NR'::character varying)::text, ('RFK'::character varying)::text, ('RRS'::character varying)::text]))) THEN 7000
                    WHEN (((a.assignment_type)::text = 'uppdrag'::text) AND ((a.role_code)::text = 'Ledamot'::text) AND ((a.org_code)::text = ANY (ARRAY[('MJU'::character varying)::text, ('BoU'::character varying)::text, ('TU'::character varying)::text]))) THEN 6000
                    WHEN (((a.assignment_type)::text = 'Riksdagsorgan'::text) AND ((a.org_code)::text = ANY (ARRAY[('Systembolaget'::character varying)::text, ('EUN'::character varying)::text]))) THEN 4000
                    WHEN (((a.assignment_type)::text = 'uppdrag'::text) AND ((a.role_code)::text = ANY (ARRAY[('Adjungerad'::character varying)::text, ('Sekreterare'::character varying)::text]))) THEN 3000
                    ELSE 1000
                END AS role_weight,
                CASE
                    WHEN (((a.role_code)::text ~~* '%ersättare%'::text) OR ((a.role_code)::text ~~* '%suppleant%'::text)) THEN 1
                    ELSE 0
                END AS is_substitute,
                CASE
                    WHEN ((a.role_code)::text = ANY (ARRAY[('Ordförande'::character varying)::text, ('Vice ordförande'::character varying)::text, ('Gruppledare'::character varying)::text, ('Partiledare'::character varying)::text, ('Partisekreterare'::character varying)::text, ('Förste vice gruppledare'::character varying)::text, ('Andre vice gruppledare'::character varying)::text])) THEN 1
                    ELSE 0
                END AS is_leadership,
                CASE
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text = 'Statsminister'::text)) THEN 1000.0
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = 'Partiledare'::text)) THEN 950.0
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text = 'Vice statsminister'::text)) THEN 900.0
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND (((a.role_code)::text ~~* '%minister%'::text) OR ((a.role_code)::text = 'Statsråd'::text))) THEN 850.0
                    WHEN (((a.assignment_type)::text = 'kammaruppdrag'::text) AND ((a.role_code)::text = 'Talman'::text)) THEN (800)::numeric
                    WHEN (((a.assignment_type)::text = 'talmansuppdrag'::text) AND ((a.role_code)::text = ANY (ARRAY[('Förste vice talman'::character varying)::text, ('Andre vice talman'::character varying)::text, ('Tredje vice talman'::character varying)::text]))) THEN 750.0
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.role_code)::text = 'Ordförande'::text)) THEN 700.0
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = ANY (ARRAY[('Gruppledare'::character varying)::text, ('Partisekreterare'::character varying)::text]))) THEN 650.0
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.role_code)::text = 'Vice ordförande'::text)) THEN 600.0
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = ANY (ARRAY[('Förste vice gruppledare'::character varying)::text, ('Andre vice gruppledare'::character varying)::text]))) THEN 550.0
                    WHEN (((a.assignment_type)::text = 'kammaruppdrag'::text) AND ((a.role_code)::text = 'Riksdagsledamot'::text)) THEN 500.0
                    WHEN (((a.assignment_type)::text = 'Europaparlamentet'::text) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 450.0
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 400.0
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.org_code)::text = ANY (ARRAY[('UFÖU'::character varying)::text, ('EU'::character varying)::text])) AND ((a.role_code)::text = 'Ordförande'::text)) THEN 350.0
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.org_code)::text = ANY (ARRAY[('UFÖU'::character varying)::text, ('EU'::character varying)::text])) AND ((a.role_code)::text = 'Vice ordförande'::text)) THEN 300.0
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.org_code)::text = ANY (ARRAY[('UFÖU'::character varying)::text, ('EU'::character varying)::text])) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 250.0
                    WHEN (((a.assignment_type)::text = 'Riksdagsorgan'::text) AND ((a.org_code)::text = ANY (ARRAY[('RJ'::character varying)::text, ('Systembolaget'::character varying)::text, ('NR'::character varying)::text, ('RFK'::character varying)::text, ('RRS'::character varying)::text]))) THEN 200.0
                    WHEN ((a.role_code)::text = ANY (ARRAY[('Ersättare'::character varying)::text, ('Statsrådsersättare'::character varying)::text])) THEN 150.0
                    WHEN ((a.role_code)::text = ANY (ARRAY[('Suppleant'::character varying)::text, ('Extra suppleant'::character varying)::text])) THEN 100.0
                    WHEN (((a.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) AND ((a.org_code)::text = ANY (ARRAY[('MJU'::character varying)::text, ('BoU'::character varying)::text, ('TU'::character varying)::text]))) THEN 50.0
                    ELSE 10.0
                END AS area_weight
           FROM public.assignment_data a
        ), per_role_stats AS (
         SELECT p.id AS person_id,
            max((p.first_name)::text) AS first_name,
            max((p.last_name)::text) AS last_name,
            r.assignment_type,
            r.role_code,
            r.org_code,
            r.knowledge_area,
            count(*) AS total_assignments,
            sum(r.days_in_role) AS total_days,
            sum((((r.days_in_role * r.role_weight))::numeric * r.area_weight)) AS weighted_experience,
            sum(
                CASE
                    WHEN (r.is_substitute = 1) THEN r.days_in_role
                    ELSE 0
                END) AS substitute_days,
            sum(
                CASE
                    WHEN (r.is_leadership = 1) THEN r.days_in_role
                    ELSE 0
                END) AS leadership_days
           FROM (role_day_spans r
             JOIN public.person_data p ON (((p.id)::text = (r.person_id)::text)))
          GROUP BY p.id, r.assignment_type, r.role_code, r.org_code, r.knowledge_area
        ), experience_summary AS (
         SELECT per_role_stats.person_id,
            max(per_role_stats.first_name) AS first_name,
            max(per_role_stats.last_name) AS last_name,
            sum(per_role_stats.total_days) AS total_days,
            sum(per_role_stats.weighted_experience) AS total_weighted_exp,
            sum(
                CASE
                    WHEN ((per_role_stats.assignment_type)::text = 'Departement'::text) THEN per_role_stats.total_days
                    ELSE (0)::bigint
                END) AS govt_days,
            sum(
                CASE
                    WHEN ((per_role_stats.assignment_type)::text = 'kammaruppdrag'::text) THEN per_role_stats.total_days
                    ELSE (0)::bigint
                END) AS riksdag_days,
            sum(
                CASE
                    WHEN ((per_role_stats.assignment_type)::text = 'partiuppdrag'::text) THEN per_role_stats.total_days
                    ELSE (0)::bigint
                END) AS party_days,
            sum(
                CASE
                    WHEN ((per_role_stats.assignment_type)::text = ANY (ARRAY[('uppdrag'::character varying)::text, ('Riksdagsorgan'::character varying)::text])) THEN per_role_stats.total_days
                    ELSE (0)::bigint
                END) AS committee_days,
            sum(per_role_stats.substitute_days) AS total_substitute_days,
            sum(per_role_stats.leadership_days) AS total_leadership_days,
            json_agg(json_build_object('area', per_role_stats.knowledge_area, 'days', per_role_stats.total_days, 'weightedExp', per_role_stats.weighted_experience) ORDER BY per_role_stats.weighted_experience DESC) AS knowledge_areas,
            json_agg(json_build_object('type', per_role_stats.assignment_type, 'role', per_role_stats.role_code, 'org', per_role_stats.org_code, 'days', per_role_stats.total_days, 'weightedExp', per_role_stats.weighted_experience, 'substituteDays', per_role_stats.substitute_days, 'leadershipDays', per_role_stats.leadership_days) ORDER BY per_role_stats.weighted_experience DESC) AS roles
           FROM per_role_stats
          GROUP BY per_role_stats.person_id
        ), political_analysis AS (
         SELECT es.person_id,
            es.first_name,
            es.last_name,
            es.total_days,
            es.total_weighted_exp,
            es.govt_days,
            es.riksdag_days,
            es.party_days,
            es.committee_days,
            es.total_substitute_days,
            es.total_leadership_days,
            es.knowledge_areas,
            es.roles,
                CASE
                    WHEN ((es.govt_days > (2500)::numeric) AND (es.riksdag_days > (4000)::numeric) AND (es.committee_days > (2500)::numeric)) THEN 'EXTENSIVE_EXPERIENCE'::text
                    WHEN ((es.govt_days > (2000)::numeric) AND ((es.riksdag_days > (3000)::numeric) OR (es.committee_days > (1500)::numeric))) THEN 'SIGNIFICANT_GOVERNMENT'::text
                    WHEN ((es.riksdag_days > (3500)::numeric) AND ((es.govt_days > (1000)::numeric) OR (es.committee_days > (1200)::numeric))) THEN 'LONG_SERVING_PARLIAMENT'::text
                    WHEN ((es.committee_days > (1500)::numeric) AND (es.riksdag_days > (1000)::numeric)) THEN 'ACTIVE_COMMITTEES'::text
                    WHEN (es.party_days > (1000)::numeric) THEN 'PARTY_LEADERSHIP'::text
                    ELSE 'MIXED_EXPERIENCE'::text
                END AS experience_level,
                CASE
                    WHEN ((((((es.govt_days > (0)::numeric))::integer + ((es.riksdag_days > (0)::numeric))::integer) + ((es.party_days > (0)::numeric))::integer) + ((es.committee_days > (0)::numeric))::integer) >= 4) THEN 'HIGH'::text
                    WHEN ((((((es.govt_days > (0)::numeric))::integer + ((es.riksdag_days > (0)::numeric))::integer) + ((es.party_days > (0)::numeric))::integer) + ((es.committee_days > (0)::numeric))::integer) = 3) THEN 'MEDIUM'::text
                    WHEN ((((((es.govt_days > (0)::numeric))::integer + ((es.riksdag_days > (0)::numeric))::integer) + ((es.party_days > (0)::numeric))::integer) + ((es.committee_days > (0)::numeric))::integer) = 2) THEN 'LOW'::text
                    ELSE 'VERY_LOW'::text
                END AS experience_breadth,
                CASE
                    WHEN (es.total_leadership_days > (1200)::numeric) THEN 'SIGNIFICANT_LEADERSHIP'::text
                    WHEN ((es.total_leadership_days >= (600)::numeric) AND (es.total_leadership_days <= (1200)::numeric)) THEN 'MODERATE_LEADERSHIP'::text
                    WHEN ((es.total_leadership_days >= (200)::numeric) AND (es.total_leadership_days <= (600)::numeric)) THEN 'SOME_LEADERSHIP'::text
                    ELSE 'NO_LEADERSHIP'::text
                END AS leadership_profile,
                CASE
                    WHEN (((es.total_substitute_days)::double precision / (NULLIF(es.total_days, (0)::numeric))::double precision) > (0.5)::double precision) THEN 'PRIMARILY_SUBSTITUTE'::text
                    WHEN (((es.total_substitute_days)::double precision / (NULLIF(es.total_days, (0)::numeric))::double precision) > (0.3)::double precision) THEN 'FREQUENT_SUBSTITUTE'::text
                    WHEN (((es.total_substitute_days)::double precision / (NULLIF(es.total_days, (0)::numeric))::double precision) > (0.1)::double precision) THEN 'OCCASIONAL_SUBSTITUTE'::text
                    ELSE 'REGULAR_ROLES'::text
                END AS role_stability,
                CASE
                    WHEN (es.total_weighted_exp > (120000)::numeric) THEN 'SENIOR_STATESPERSON'::text
                    WHEN ((es.total_weighted_exp >= (60000)::numeric) AND (es.total_weighted_exp <= (120000)::numeric)) THEN 'ESTABLISHED_POLITICIAN'::text
                    WHEN ((es.total_weighted_exp >= (20000)::numeric) AND (es.total_weighted_exp <= (60000)::numeric)) THEN 'EXPERIENCED_POLITICIAN'::text
                    WHEN ((es.total_weighted_exp >= (7000)::numeric) AND (es.total_weighted_exp <= (20000)::numeric)) THEN 'MID_CAREER'::text
                    ELSE 'EARLY_CAREER'::text
                END AS career_phase,
                CASE
                    WHEN ((( SELECT count(DISTINCT ka.area) AS count
                       FROM jsonb_array_elements((es.knowledge_areas)::jsonb) ka(area)) <= 2) AND (es.total_days > (1000)::numeric)) THEN 'HIGHLY_SPECIALIZED'::text
                    WHEN (( SELECT count(DISTINCT ka.area) AS count
                       FROM jsonb_array_elements((es.knowledge_areas)::jsonb) ka(area)) <= 4) THEN 'MODERATELY_SPECIALIZED'::text
                    ELSE 'BROADLY_EXPERIENCED'::text
                END AS specialization_level
           FROM experience_summary es
        )
 SELECT person_id,
    first_name,
    last_name,
    total_days,
    total_weighted_exp,
    govt_days,
    riksdag_days,
    party_days,
    committee_days,
    total_substitute_days,
    total_leadership_days,
    (knowledge_areas)::text AS knowledge_areas_json,
    (roles)::text AS roles_json,
    experience_level,
    experience_breadth,
    leadership_profile,
    role_stability,
    career_phase,
    specialization_level,
    concat_ws(' || '::text,
        CASE experience_level
            WHEN 'EXTENSIVE_EXPERIENCE'::text THEN 'Distinguished career spanning government, parliament, and committees'::text
            WHEN 'SIGNIFICANT_GOVERNMENT'::text THEN 'Notable government service record'::text
            WHEN 'LONG_SERVING_PARLIAMENT'::text THEN 'Long-standing parliamentary experience'::text
            WHEN 'ACTIVE_COMMITTEES'::text THEN 'Significant committee work experience'::text
            WHEN 'PARTY_LEADERSHIP'::text THEN 'Strong party leadership background'::text
            ELSE 'Diverse political experience'::text
        END,
        CASE experience_breadth
            WHEN 'HIGH'::text THEN 'Demonstrates broad political competence across multiple domains'::text
            WHEN 'MEDIUM'::text THEN 'Shows focused expertise in select political areas'::text
            ELSE 'Specialized in specific political domain'::text
        END,
        CASE leadership_profile
            WHEN 'SIGNIFICANT_LEADERSHIP'::text THEN 'Extensive leadership experience with over 1000 days in key positions'::text
            WHEN 'MODERATE_LEADERSHIP'::text THEN 'Proven leadership capabilities with over 500 days in leadership roles'::text
            WHEN 'SOME_LEADERSHIP'::text THEN 'Some leadership experience'::text
            ELSE 'Primarily collaborative roles'::text
        END,
        CASE role_stability
            WHEN 'PRIMARILY_SUBSTITUTE'::text THEN 'Frequently serves in substitute positions, showing adaptability'::text
            WHEN 'FREQUENT_SUBSTITUTE'::text THEN 'Regular substitute experience complementing primary roles'::text
            WHEN 'OCCASIONAL_SUBSTITUTE'::text THEN 'Mainly stable positions with occasional substitute duties'::text
            ELSE 'Consistent role appointments'::text
        END,
        CASE career_phase
            WHEN 'SENIOR_STATESPERSON'::text THEN 'Senior political figure with extensive influence'::text
            WHEN 'ESTABLISHED_POLITICIAN'::text THEN 'Well-established political career'::text
            WHEN 'EXPERIENCED_POLITICIAN'::text THEN 'Seasoned political operator'::text
            WHEN 'MID_CAREER'::text THEN 'Building significant political experience'::text
            ELSE 'Developing political career'::text
        END,
        CASE specialization_level
            WHEN 'HIGHLY_SPECIALIZED'::text THEN 'Deep expertise in specific policy areas'::text
            WHEN 'MODERATELY_SPECIALIZED'::text THEN 'Balanced mix of specialized knowledge and broader experience'::text
            ELSE 'Broad political portfolio'::text
        END) AS political_analysis_comment
   FROM political_analysis
  ORDER BY total_weighted_exp DESC;


--
-- Name: view_riksdagen_vote_data_ballot_party_summary_daily; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_daily AS
 WITH daily_stats AS (
         SELECT view_riksdagen_vote_data_ballot_party_summary.vote_date AS embedded_id_vote_date,
            view_riksdagen_vote_data_ballot_party_summary.embedded_id_party,
            count(*) AS number_ballots,
            sum(view_riksdagen_vote_data_ballot_party_summary.total_votes) AS total_votes,
            sum(view_riksdagen_vote_data_ballot_party_summary.yes_votes) AS yes_votes,
            sum(view_riksdagen_vote_data_ballot_party_summary.no_votes) AS no_votes,
            sum(view_riksdagen_vote_data_ballot_party_summary.abstain_votes) AS abstain_votes,
            sum(view_riksdagen_vote_data_ballot_party_summary.absent_votes) AS absent_votes,
            sum(view_riksdagen_vote_data_ballot_party_summary.party_total_votes) AS party_total_votes,
            sum(view_riksdagen_vote_data_ballot_party_summary.party_yes_votes) AS party_yes_votes,
            sum(view_riksdagen_vote_data_ballot_party_summary.party_no_votes) AS party_no_votes,
            sum(view_riksdagen_vote_data_ballot_party_summary.party_abstain_votes) AS party_abstain_votes,
            sum(view_riksdagen_vote_data_ballot_party_summary.party_absent_votes) AS party_absent_votes,
            sum(
                CASE
                    WHEN view_riksdagen_vote_data_ballot_party_summary.party_won THEN 1
                    ELSE 0
                END) AS party_won_total,
            sum(
                CASE
                    WHEN view_riksdagen_vote_data_ballot_party_summary.approved THEN 1
                    ELSE 0
                END) AS approved_total,
            round(avg(view_riksdagen_vote_data_ballot_party_summary.avg_born_year), 0) AS avg_born_year,
            round(avg(view_riksdagen_vote_data_ballot_party_summary.party_avg_born_year), 0) AS party_avg_born_year,
            max(view_riksdagen_vote_data_ballot_party_summary.total_votes) AS avg_total_votes,
            avg(view_riksdagen_vote_data_ballot_party_summary.percentage_yes) AS orig_percentage_yes,
            avg(view_riksdagen_vote_data_ballot_party_summary.percentage_no) AS orig_percentage_no,
            avg(view_riksdagen_vote_data_ballot_party_summary.percentage_absent) AS orig_percentage_absent,
            avg(view_riksdagen_vote_data_ballot_party_summary.percentage_abstain) AS orig_percentage_abstain,
            avg(view_riksdagen_vote_data_ballot_party_summary.percentage_male) AS orig_percentage_male,
            avg(view_riksdagen_vote_data_ballot_party_summary.party_percentage_male) AS orig_party_percentage_male
           FROM public.view_riksdagen_vote_data_ballot_party_summary
          GROUP BY view_riksdagen_vote_data_ballot_party_summary.embedded_id_party, view_riksdagen_vote_data_ballot_party_summary.vote_date
        )
 SELECT embedded_id_vote_date,
    embedded_id_party,
    number_ballots,
    avg_born_year,
    avg_total_votes,
    round((yes_votes / (NULLIF(number_ballots, 0))::numeric), 2) AS avg_yes_votes,
    round((no_votes / (NULLIF(number_ballots, 0))::numeric), 2) AS avg_no_votes,
    round((abstain_votes / (NULLIF(number_ballots, 0))::numeric), 2) AS avg_abstain_votes,
    round((absent_votes / (NULLIF(number_ballots, 0))::numeric), 2) AS avg_absent_votes,
    round(((100.0 * (approved_total)::numeric) / (NULLIF(number_ballots, 0))::numeric), 2) AS percentage_approved,
    round(orig_percentage_yes, 2) AS avg_percentage_yes,
    round(orig_percentage_no, 2) AS avg_percentage_no,
    round(orig_percentage_absent, 2) AS avg_percentage_absent,
    round(orig_percentage_abstain, 2) AS avg_percentage_abstain,
    round(orig_percentage_male, 2) AS avg_percentage_male,
    total_votes,
    yes_votes,
    no_votes,
    abstain_votes,
    absent_votes,
    party_total_votes,
    party_yes_votes,
    party_no_votes,
    party_abstain_votes,
    party_absent_votes,
    party_avg_born_year,
    round(orig_party_percentage_male, 2) AS party_avg_percentage_male,
    round(((100.0 * party_yes_votes) / NULLIF(party_total_votes, (0)::numeric)), 2) AS party_percentage_yes,
    round(((100.0 * party_no_votes) / NULLIF(party_total_votes, (0)::numeric)), 2) AS party_percentage_no,
    round(((100.0 * party_abstain_votes) / NULLIF(party_total_votes, (0)::numeric)), 2) AS party_percentage_abstain,
    round(((100.0 * party_absent_votes) / NULLIF(party_total_votes, (0)::numeric)), 2) AS party_percentage_absent,
    round(((100.0 * yes_votes) / NULLIF(total_votes, (0)::numeric)), 2) AS percentage_yes,
    round(((100.0 * no_votes) / NULLIF(total_votes, (0)::numeric)), 2) AS percentage_no,
    round(((100.0 * abstain_votes) / NULLIF(total_votes, (0)::numeric)), 2) AS percentage_abstain,
    round(((100.0 * absent_votes) / NULLIF(total_votes, (0)::numeric)), 2) AS percentage_absent,
    party_won_total,
    round(((100.0 * (party_won_total)::numeric) / (NULLIF(number_ballots, 0))::numeric), 2) AS party_won_percentage,
    approved_total,
    round(((100.0 * (approved_total)::numeric) / (NULLIF(number_ballots, 0))::numeric), 2) AS approved_percentage
   FROM daily_stats
  ORDER BY embedded_id_vote_date, embedded_id_party
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_party_summary_annual; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_annual AS
 SELECT date(date_trunc('year'::text, (embedded_id_vote_date)::timestamp with time zone)) AS embedded_id_vote_date,
    embedded_id_party,
    sum(number_ballots) AS number_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(avg_percentage_yes), 2) AS avg_percentage_yes,
    round(avg(avg_percentage_no), 2) AS avg_percentage_no,
    round(avg(avg_percentage_absent), 2) AS avg_percentage_absent,
    round(avg(avg_percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(avg_percentage_male), 2) AS avg_percentage_male,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(party_total_votes) AS party_total_votes,
    sum(party_yes_votes) AS party_yes_votes,
    sum(party_no_votes) AS party_no_votes,
    sum(party_abstain_votes) AS party_abstain_votes,
    sum(party_absent_votes) AS party_absent_votes,
    round(avg(party_avg_born_year), 0) AS party_avg_born_year,
    round(avg(party_avg_percentage_male), 2) AS party_avg_percentage_male,
    round(((100.0 * sum(party_yes_votes)) / sum(party_total_votes)), 2) AS party_percentage_yes,
    round(((100.0 * sum(party_no_votes)) / sum(party_total_votes)), 2) AS party_percentage_no,
    round(((100.0 * sum(party_abstain_votes)) / sum(party_total_votes)), 2) AS party_percentage_abstain,
    round(((100.0 * sum(party_absent_votes)) / sum(party_total_votes)), 2) AS party_percentage_absent,
    sum(party_won_total) AS party_won_total,
    round((((100)::numeric * sum(party_won_total)) / sum(number_ballots)), 2) AS party_won_percentage,
    sum(approved_total) AS approved_total,
    round((((100)::numeric * sum(approved_total)) / sum(number_ballots)), 2) AS approved_percentage,
    round(((100.0 * sum(yes_votes)) / sum(total_votes)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / sum(total_votes)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / sum(total_votes)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / sum(total_votes)), 2) AS percentage_absent,
    round(avg(percentage_approved), 2) AS avg_percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_party_summary_daily
  GROUP BY (date(date_trunc('year'::text, (embedded_id_vote_date)::timestamp with time zone))), embedded_id_party
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_party_summary_monthly; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_monthly AS
 SELECT date(date_trunc('month'::text, (embedded_id_vote_date)::timestamp with time zone)) AS embedded_id_vote_date,
    embedded_id_party,
    sum(number_ballots) AS number_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(avg_percentage_yes), 2) AS avg_percentage_yes,
    round(avg(avg_percentage_no), 2) AS avg_percentage_no,
    round(avg(avg_percentage_absent), 2) AS avg_percentage_absent,
    round(avg(avg_percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(avg_percentage_male), 2) AS avg_percentage_male,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(party_total_votes) AS party_total_votes,
    sum(party_yes_votes) AS party_yes_votes,
    sum(party_no_votes) AS party_no_votes,
    sum(party_abstain_votes) AS party_abstain_votes,
    sum(party_absent_votes) AS party_absent_votes,
    round(avg(party_avg_born_year), 0) AS party_avg_born_year,
    round(avg(party_avg_percentage_male), 2) AS party_avg_percentage_male,
    round(((100.0 * sum(party_yes_votes)) / sum(party_total_votes)), 2) AS party_percentage_yes,
    round(((100.0 * sum(party_no_votes)) / sum(party_total_votes)), 2) AS party_percentage_no,
    round(((100.0 * sum(party_abstain_votes)) / sum(party_total_votes)), 2) AS party_percentage_abstain,
    round(((100.0 * sum(party_absent_votes)) / sum(party_total_votes)), 2) AS party_percentage_absent,
    sum(party_won_total) AS party_won_total,
    round((((100)::numeric * sum(party_won_total)) / sum(number_ballots)), 2) AS party_won_percentage,
    sum(approved_total) AS approved_total,
    round((((100)::numeric * sum(approved_total)) / sum(number_ballots)), 2) AS approved_percentage,
    round(((100.0 * sum(yes_votes)) / sum(total_votes)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / sum(total_votes)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / sum(total_votes)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / sum(total_votes)), 2) AS percentage_absent,
    round(avg(percentage_approved), 2) AS avg_percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_party_summary_daily
  GROUP BY (date(date_trunc('month'::text, (embedded_id_vote_date)::timestamp with time zone))), embedded_id_party
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_party_summary_weekly; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_weekly AS
 SELECT date(date_trunc('week'::text, (embedded_id_vote_date)::timestamp with time zone)) AS embedded_id_vote_date,
    embedded_id_party,
    sum(number_ballots) AS number_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(avg_percentage_yes), 2) AS avg_percentage_yes,
    round(avg(avg_percentage_no), 2) AS avg_percentage_no,
    round(avg(avg_percentage_absent), 2) AS avg_percentage_absent,
    round(avg(avg_percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(avg_percentage_male), 2) AS avg_percentage_male,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(party_total_votes) AS party_total_votes,
    sum(party_yes_votes) AS party_yes_votes,
    sum(party_no_votes) AS party_no_votes,
    sum(party_abstain_votes) AS party_abstain_votes,
    sum(party_absent_votes) AS party_absent_votes,
    round(avg(party_avg_born_year), 0) AS party_avg_born_year,
    round(avg(party_avg_percentage_male), 2) AS party_avg_percentage_male,
    round(((100.0 * sum(party_yes_votes)) / sum(party_total_votes)), 2) AS party_percentage_yes,
    round(((100.0 * sum(party_no_votes)) / sum(party_total_votes)), 2) AS party_percentage_no,
    round(((100.0 * sum(party_abstain_votes)) / sum(party_total_votes)), 2) AS party_percentage_abstain,
    round(((100.0 * sum(party_absent_votes)) / sum(party_total_votes)), 2) AS party_percentage_absent,
    sum(party_won_total) AS party_won_total,
    round((((100)::numeric * sum(party_won_total)) / sum(number_ballots)), 2) AS party_won_percentage,
    sum(approved_total) AS approved_total,
    round((((100)::numeric * sum(approved_total)) / sum(number_ballots)), 2) AS approved_percentage,
    round(((100.0 * sum(yes_votes)) / sum(total_votes)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / sum(total_votes)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / sum(total_votes)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / sum(total_votes)), 2) AS percentage_absent,
    round(avg(percentage_approved), 2) AS avg_percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_party_summary_daily
  GROUP BY (date(date_trunc('week'::text, (embedded_id_vote_date)::timestamp with time zone))), embedded_id_party
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_politician_summary_monthly; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary_monthly AS
 SELECT date(date_trunc('month'::text, (embedded_id_vote_date)::timestamp with time zone)) AS embedded_id_vote_date,
    embedded_id_intressent_id,
    max(first_name) AS first_name,
    max(last_name) AS last_name,
    max(gender) AS gender,
    max(born_year) AS born_year,
    max(party) AS party,
    sum(number_ballots) AS number_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(avg_percentage_yes), 2) AS avg_percentage_yes,
    round(avg(avg_percentage_no), 2) AS avg_percentage_no,
    round(avg(avg_percentage_absent), 2) AS avg_percentage_absent,
    round(avg(avg_percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(avg_percentage_male), 2) AS avg_percentage_male,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(party_total_votes) AS party_total_votes,
    sum(party_yes_votes) AS party_yes_votes,
    sum(party_no_votes) AS party_no_votes,
    sum(party_abstain_votes) AS party_abstain_votes,
    sum(party_absent_votes) AS party_absent_votes,
    round(avg(party_avg_born_year), 0) AS party_avg_born_year,
    round(avg(party_avg_percentage_male), 2) AS party_avg_percentage_male,
    round(((100.0 * sum(party_yes_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_yes,
    round(((100.0 * sum(party_no_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_no,
    round(((100.0 * sum(party_abstain_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_abstain,
    round(((100.0 * sum(party_absent_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_absent,
    sum(party_won_total) AS party_won_total,
    sum(politician_yes_votes) AS politician_yes_votes,
    sum(politician_no_votes) AS politician_no_votes,
    sum(politician_abstain_votes) AS politician_abstain_votes,
    sum(politician_absent_votes) AS politician_absent_votes,
    round(((100.0 * sum(politician_yes_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_yes,
    round(((100.0 * sum(politician_no_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_no,
    round(((100.0 * sum(politician_abstain_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_abstain,
    round(((100.0 * sum(politician_absent_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_absent,
    round((((100)::numeric * sum(won_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS won_percentage,
    sum(won_total) AS won_total,
    round((((100)::numeric * sum(rebel_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS rebel_percentage,
    sum(rebel_total) AS rebel_total,
    round((((100)::numeric * sum(party_won_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS party_won_percentage,
    sum(approved_total) AS approved_total,
    round((((100)::numeric * sum(approved_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS approved_percentage,
    round(((100.0 * sum(yes_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_absent,
    round(avg(percentage_approved), 2) AS avg_percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_politician_summary_daily
  GROUP BY (date(date_trunc('month'::text, (embedded_id_vote_date)::timestamp with time zone))), embedded_id_intressent_id
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_politician_summary_weekly; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary_weekly AS
 SELECT date(date_trunc('week'::text, (embedded_id_vote_date)::timestamp with time zone)) AS embedded_id_vote_date,
    embedded_id_intressent_id,
    max(first_name) AS first_name,
    max(last_name) AS last_name,
    max(gender) AS gender,
    max(born_year) AS born_year,
    max(party) AS party,
    sum(number_ballots) AS number_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(avg_percentage_yes), 2) AS avg_percentage_yes,
    round(avg(avg_percentage_no), 2) AS avg_percentage_no,
    round(avg(avg_percentage_absent), 2) AS avg_percentage_absent,
    round(avg(avg_percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(avg_percentage_male), 2) AS avg_percentage_male,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(party_total_votes) AS party_total_votes,
    sum(party_yes_votes) AS party_yes_votes,
    sum(party_no_votes) AS party_no_votes,
    sum(party_abstain_votes) AS party_abstain_votes,
    sum(party_absent_votes) AS party_absent_votes,
    round(avg(party_avg_born_year), 0) AS party_avg_born_year,
    round(avg(party_avg_percentage_male), 2) AS party_avg_percentage_male,
    round(((100.0 * sum(party_yes_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_yes,
    round(((100.0 * sum(party_no_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_no,
    round(((100.0 * sum(party_abstain_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_abstain,
    round(((100.0 * sum(party_absent_votes)) / NULLIF(sum(party_total_votes), (0)::numeric)), 2) AS party_percentage_absent,
    sum(party_won_total) AS party_won_total,
    sum(politician_yes_votes) AS politician_yes_votes,
    sum(politician_no_votes) AS politician_no_votes,
    sum(politician_abstain_votes) AS politician_abstain_votes,
    sum(politician_absent_votes) AS politician_absent_votes,
    round(((100.0 * sum(politician_yes_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_yes,
    round(((100.0 * sum(politician_no_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_no,
    round(((100.0 * sum(politician_abstain_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_abstain,
    round(((100.0 * sum(politician_absent_votes)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS politician_percentage_absent,
    round((((100)::numeric * sum(won_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS won_percentage,
    sum(won_total) AS won_total,
    round((((100)::numeric * sum(rebel_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS rebel_percentage,
    sum(rebel_total) AS rebel_total,
    round((((100)::numeric * sum(party_won_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS party_won_percentage,
    sum(approved_total) AS approved_total,
    round((((100)::numeric * sum(approved_total)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS approved_percentage,
    round(((100.0 * sum(yes_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_absent,
    round(avg(percentage_approved), 2) AS avg_percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_politician_summary_daily
  GROUP BY (date(date_trunc('week'::text, (embedded_id_vote_date)::timestamp with time zone))), embedded_id_intressent_id
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_summary_daily; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_daily AS
 SELECT vote_date,
    count(*) AS number_ballots,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(
        CASE
            WHEN approved THEN 1
            ELSE 0
        END) AS approved_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(percentage_male), 2) AS avg_percentage_male,
    round(avg(yes_votes), 2) AS avg_yes_votes,
    round(avg(no_votes), 2) AS avg_no_votes,
    round(avg(abstain_votes), 2) AS avg_abstain_votes,
    round(avg(absent_votes), 2) AS avg_absent_votes,
    round(avg(percentage_yes), 2) AS avg_percentage_yes,
    round(avg(percentage_no), 2) AS avg_percentage_no,
    round(avg(percentage_absent), 2) AS avg_percentage_absent,
    round(avg(percentage_abstain), 2) AS avg_percentage_abstain,
    round(((100.0 * sum(yes_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_absent,
    round(((100.0 * (sum(
        CASE
            WHEN approved THEN 1
            ELSE 0
        END))::numeric) / (NULLIF(count(*), 0))::numeric), 2) AS percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_summary
  GROUP BY vote_date
  ORDER BY vote_date
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_summary_annual; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_annual AS
 SELECT date(date_trunc('year'::text, (vote_date)::timestamp with time zone)) AS vote_date,
    sum(number_ballots) AS number_ballots,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(approved_ballots) AS approved_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(avg_percentage_male), 2) AS avg_percentage_male,
    round(avg(avg_percentage_yes), 2) AS avg_percentage_yes,
    round(avg(avg_percentage_no), 2) AS avg_percentage_no,
    round(avg(avg_percentage_absent), 2) AS avg_percentage_absent,
    round(avg(avg_percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(percentage_approved), 2) AS avg_percentage_approved,
    round(((100.0 * sum(yes_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_absent,
    round(((100.0 * sum(approved_ballots)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_summary_daily
  GROUP BY (date(date_trunc('year'::text, (vote_date)::timestamp with time zone)))
  ORDER BY (date(date_trunc('year'::text, (vote_date)::timestamp with time zone)))
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_summary_monthly; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_monthly AS
 SELECT date(date_trunc('month'::text, (vote_date)::timestamp with time zone)) AS vote_date,
    sum(number_ballots) AS number_ballots,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(approved_ballots) AS approved_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(avg_percentage_yes), 2) AS avg_percentage_yes,
    round(avg(avg_percentage_no), 2) AS avg_percentage_no,
    round(avg(avg_percentage_absent), 2) AS avg_percentage_absent,
    round(avg(avg_percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(avg_percentage_male), 2) AS avg_percentage_male,
    round(avg(percentage_approved), 2) AS avg_percentage_approved,
    round(((100.0 * sum(yes_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_absent,
    round(((100.0 * sum(approved_ballots)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_summary_daily
  GROUP BY (date(date_trunc('month'::text, (vote_date)::timestamp with time zone)))
  ORDER BY (date(date_trunc('month'::text, (vote_date)::timestamp with time zone)))
  WITH NO DATA;


--
-- Name: view_riksdagen_vote_data_ballot_summary_weekly; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_weekly AS
 SELECT date(date_trunc('week'::text, (vote_date)::timestamp with time zone)) AS vote_date,
    sum(number_ballots) AS number_ballots,
    sum(total_votes) AS total_votes,
    sum(yes_votes) AS yes_votes,
    sum(no_votes) AS no_votes,
    sum(abstain_votes) AS abstain_votes,
    sum(absent_votes) AS absent_votes,
    sum(approved_ballots) AS approved_ballots,
    round(avg(avg_born_year), 0) AS avg_born_year,
    round(avg(avg_percentage_yes), 2) AS avg_percentage_yes,
    round(avg(avg_percentage_no), 2) AS avg_percentage_no,
    round(avg(avg_percentage_absent), 2) AS avg_percentage_absent,
    round(avg(avg_percentage_abstain), 2) AS avg_percentage_abstain,
    round(avg(avg_percentage_male), 2) AS avg_percentage_male,
    round(avg(percentage_approved), 2) AS avg_percentage_approved,
    round(((100.0 * sum(yes_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_yes,
    round(((100.0 * sum(no_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_no,
    round(((100.0 * sum(abstain_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_abstain,
    round(((100.0 * sum(absent_votes)) / NULLIF(sum(total_votes), (0)::numeric)), 2) AS percentage_absent,
    round(((100.0 * sum(approved_ballots)) / NULLIF(sum(number_ballots), (0)::numeric)), 2) AS percentage_approved
   FROM public.view_riksdagen_vote_data_ballot_summary_daily
  GROUP BY (date(date_trunc('week'::text, (vote_date)::timestamp with time zone)))
  ORDER BY (date(date_trunc('week'::text, (vote_date)::timestamp with time zone)))
  WITH NO DATA;


--
-- Name: view_risk_score_evolution; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.view_risk_score_evolution AS
 WITH party_ballot_majority AS (
         SELECT vote_data.embedded_id_ballot_id,
            vote_data.party,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'Ja'::text) THEN 1
                    ELSE 0
                END) AS party_yes_count,
            sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'Nej'::text) THEN 1
                    ELSE 0
                END) AS party_no_count,
            (sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'Ja'::text) THEN 1
                    ELSE 0
                END) > sum(
                CASE
                    WHEN ((vote_data.vote)::text = 'Nej'::text) THEN 1
                    ELSE 0
                END)) AS party_approved
           FROM public.vote_data
          WHERE ((vote_data.vote_date >= (CURRENT_DATE - '3 years'::interval)) AND (vote_data.party IS NOT NULL))
          GROUP BY vote_data.embedded_id_ballot_id, vote_data.party
        ), politician_votes_with_rebel AS (
         SELECT vd.embedded_id_intressent_id,
            vd.vote_date,
            vd.vote,
            vd.party,
            pbm.party_approved,
                CASE
                    WHEN (((vd.vote)::text = 'Nej'::text) AND (pbm.party_approved = true)) THEN true
                    WHEN (((vd.vote)::text = 'Ja'::text) AND (pbm.party_approved = false)) THEN true
                    ELSE false
                END AS is_rebel
           FROM (public.vote_data vd
             JOIN party_ballot_majority pbm ON ((((vd.embedded_id_ballot_id)::text = (pbm.embedded_id_ballot_id)::text) AND ((vd.party)::text = (pbm.party)::text))))
          WHERE (vd.vote_date >= (CURRENT_DATE - '3 years'::interval))
        ), politician_document_data AS (
         SELECT dsc.hjid AS id,
            dd.document_type,
            dd.made_public_date,
            dd.org,
            dpr.person_reference_id
           FROM (((public.document_status_container dsc
             LEFT JOIN public.document_data dd ON (((dsc.document_document_status_con_0)::text = (dd.id)::text)))
             LEFT JOIN public.document_person_reference_co_0 dprc ON ((dsc.hjid = dprc.hjid)))
             LEFT JOIN public.document_person_reference_da_0 dpr ON ((dpr.document_person_reference_li_1 = dprc.hjid)))
          WHERE (dd.made_public_date IS NOT NULL)
        ), monthly_risk_base AS (
         SELECT p.id AS person_id,
            p.first_name,
            p.last_name,
            p.party,
            date_trunc('month'::text, (pvr.vote_date)::timestamp with time zone) AS assessment_period,
            round((((count(*) FILTER (WHERE ((pvr.vote)::text = 'Frånvarande'::text)))::numeric / (NULLIF(count(*), 0))::numeric) * (100)::numeric), 2) AS absence_rate,
            round((((count(*) FILTER (WHERE (pvr.is_rebel = true)))::numeric / (NULLIF(count(*) FILTER (WHERE ((pvr.vote)::text = ANY (ARRAY[('Ja'::character varying)::text, ('Nej'::character varying)::text]))), 0))::numeric) * (100)::numeric), 2) AS rebel_rate,
            count(*) AS ballot_count,
            count(DISTINCT vpd.id) AS document_count
           FROM ((public.person_data p
             LEFT JOIN politician_votes_with_rebel pvr ON (((pvr.embedded_id_intressent_id)::text = (p.id)::text)))
             LEFT JOIN politician_document_data vpd ON ((((vpd.person_reference_id)::text = (p.id)::text) AND (vpd.made_public_date >= (CURRENT_DATE - '3 years'::interval)) AND (date_trunc('month'::text, (vpd.made_public_date)::timestamp with time zone) = date_trunc('month'::text, (pvr.vote_date)::timestamp with time zone)))))
          WHERE ((p.status)::text = ANY (ARRAY[('active'::character varying)::text, ('Active'::character varying)::text, ('ACTIVE'::character varying)::text]))
          GROUP BY p.id, p.first_name, p.last_name, p.party, (date_trunc('month'::text, (pvr.vote_date)::timestamp with time zone))
        ), monthly_violations AS (
         SELECT rule_violation.reference_id AS person_id,
            date_trunc('month'::text, rule_violation.detected_date) AS assessment_period,
            count(*) AS violation_count,
            count(DISTINCT rule_violation.rule_group) AS violation_categories,
            string_agg(DISTINCT (rule_violation.rule_group)::text, ', '::text ORDER BY (rule_violation.rule_group)::text) AS violation_types
           FROM public.rule_violation
          WHERE (((rule_violation.resource_type)::text = 'POLITICIAN'::text) AND ((rule_violation.status)::text = 'ACTIVE'::text) AND (rule_violation.detected_date >= (CURRENT_DATE - '3 years'::interval)))
          GROUP BY rule_violation.reference_id, (date_trunc('month'::text, rule_violation.detected_date))
        ), risk_calculations AS (
         SELECT mrb.person_id,
            mrb.first_name,
            mrb.last_name,
            mrb.party,
            mrb.assessment_period,
            mrb.absence_rate,
            mrb.rebel_rate,
            mrb.ballot_count,
            mrb.document_count,
            COALESCE(mv.violation_count, (0)::bigint) AS violation_count,
            COALESCE(mv.violation_categories, (0)::bigint) AS violation_categories,
            COALESCE(mv.violation_types, ''::text) AS violation_types,
            round(((((LEAST((COALESCE(mv.violation_count, (0)::bigint) * 2), (40)::bigint))::numeric + ((COALESCE(mrb.absence_rate, (0)::numeric) * (30)::numeric) / 100.0)) + ((COALESCE(mrb.rebel_rate, (0)::numeric) * (20)::numeric) / 100.0)) + (
                CASE
                    WHEN (mrb.document_count < 5) THEN 10
                    ELSE 0
                END)::numeric), 2) AS calculated_risk_score,
            lag(round(((((LEAST((COALESCE(mv.violation_count, (0)::bigint) * 2), (40)::bigint))::numeric + ((COALESCE(mrb.absence_rate, (0)::numeric) * (30)::numeric) / 100.0)) + ((COALESCE(mrb.rebel_rate, (0)::numeric) * (20)::numeric) / 100.0)) + (
                CASE
                    WHEN (mrb.document_count < 5) THEN 10
                    ELSE 0
                END)::numeric), 2), 1) OVER (PARTITION BY mrb.person_id ORDER BY mrb.assessment_period) AS prev_risk_score
           FROM (monthly_risk_base mrb
             LEFT JOIN monthly_violations mv ON ((((mrb.person_id)::text = (mv.person_id)::text) AND (mrb.assessment_period = mv.assessment_period))))
          WHERE ((mrb.ballot_count >= 5) AND (mrb.assessment_period IS NOT NULL))
        )
 SELECT person_id,
    first_name,
    last_name,
    party,
    assessment_period,
    (((assessment_period + '1 mon'::interval) - '1 day'::interval))::date AS assessment_period_end,
    absence_rate,
    rebel_rate,
    ballot_count,
    document_count,
    violation_count,
    violation_categories,
    violation_types,
    calculated_risk_score AS risk_score,
    prev_risk_score,
    round((calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)), 2) AS risk_score_change,
        CASE
            WHEN ((calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) >= (10)::numeric) THEN 'SIGNIFICANT_INCREASE'::text
            WHEN ((calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) >= (5)::numeric) THEN 'MODERATE_INCREASE'::text
            WHEN ((calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) > (0)::numeric) THEN 'SLIGHT_INCREASE'::text
            WHEN ((calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) <= ('-10'::integer)::numeric) THEN 'SIGNIFICANT_DECREASE'::text
            WHEN ((calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) <= ('-5'::integer)::numeric) THEN 'MODERATE_DECREASE'::text
            WHEN ((calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) < (0)::numeric) THEN 'SLIGHT_DECREASE'::text
            ELSE 'STABLE'::text
        END AS risk_trend,
        CASE
            WHEN (calculated_risk_score >= (70)::numeric) THEN 'CRITICAL'::text
            WHEN (calculated_risk_score >= (50)::numeric) THEN 'HIGH'::text
            WHEN (calculated_risk_score >= (30)::numeric) THEN 'MODERATE'::text
            WHEN (calculated_risk_score >= (15)::numeric) THEN 'LOW'::text
            ELSE 'MINIMAL'::text
        END AS risk_severity,
        CASE
            WHEN (COALESCE(prev_risk_score, (0)::numeric) > (0)::numeric) THEN
            CASE
                WHEN ((prev_risk_score < (30)::numeric) AND (calculated_risk_score >= (30)::numeric)) THEN 'ESCALATION_TO_MODERATE'::text
                WHEN ((prev_risk_score < (50)::numeric) AND (calculated_risk_score >= (50)::numeric)) THEN 'ESCALATION_TO_HIGH'::text
                WHEN ((prev_risk_score < (70)::numeric) AND (calculated_risk_score >= (70)::numeric)) THEN 'ESCALATION_TO_CRITICAL'::text
                WHEN ((prev_risk_score >= (70)::numeric) AND (calculated_risk_score < (70)::numeric)) THEN 'DEESCALATION_FROM_CRITICAL'::text
                WHEN ((prev_risk_score >= (50)::numeric) AND (calculated_risk_score < (50)::numeric)) THEN 'DEESCALATION_FROM_HIGH'::text
                WHEN ((prev_risk_score >= (30)::numeric) AND (calculated_risk_score < (30)::numeric)) THEN 'DEESCALATION_FROM_MODERATE'::text
                ELSE 'NO_SEVERITY_TRANSITION'::text
            END
            ELSE 'INITIAL_ASSESSMENT'::text
        END AS severity_transition,
        CASE
            WHEN (calculated_risk_score >= (70)::numeric) THEN 'CRITICAL: Immediate attention required'::text
            WHEN ((calculated_risk_score >= (50)::numeric) AND ((calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) >= (5)::numeric)) THEN 'HIGH RISK: Escalating trend detected'::text
            WHEN (calculated_risk_score >= (50)::numeric) THEN 'HIGH RISK: Monitor closely'::text
            WHEN ((calculated_risk_score >= (30)::numeric) AND ((calculated_risk_score - COALESCE(prev_risk_score, calculated_risk_score)) >= (10)::numeric)) THEN 'MODERATE RISK: Rapid escalation warning'::text
            WHEN (calculated_risk_score >= (30)::numeric) THEN 'MODERATE RISK: Standard monitoring'::text
            WHEN ((prev_risk_score >= (50)::numeric) AND (calculated_risk_score < (30)::numeric)) THEN 'IMPROVING: Effective risk mitigation'::text
            ELSE 'LOW RISK: Normal operations'::text
        END AS risk_assessment
   FROM risk_calculations
  ORDER BY person_id, assessment_period DESC;


--
-- Name: world_bank_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.world_bank_data (
    hjid bigint NOT NULL,
    country_id character varying(255),
    country_value character varying(255),
    data_value character varying(255),
    indicator_id character varying(255),
    indicator_value character varying(255),
    year_date integer,
    data__data_element_hjid bigint
);


--
-- Name: view_worldbank_indicator_data_country_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: -
--

CREATE MATERIALIZED VIEW public.view_worldbank_indicator_data_country_summary AS
 SELECT world_bank_data.indicator_id AS embedded_id_indicator_id,
    world_bank_data.country_id AS embedded_id_country_id,
    max((indicators.indicator_name)::text) AS indicator_name,
    max((indicators.source_id)::text) AS source_id,
    max((indicators.source_value)::text) AS source_value,
    max((indicators.source_note)::text) AS source_note,
    max((indicators.source_organization)::text) AS source_organization,
    min(world_bank_data.year_date) AS start_year,
    max(world_bank_data.year_date) AS end_year,
    count(*) AS data_point,
    max(indicators.topics) AS topics
   FROM (public.world_bank_data
     LEFT JOIN ( SELECT indicator_element.hjid,
            indicator_element.id,
            indicator_element.indicator_name,
            indicator_element.source_id,
            indicator_element.source_value,
            indicator_element.source_note,
            indicator_element.source_organization,
            indicator_element.topics_indicator_element_hjid,
            indicator_element.indicator__indicators_elemen_0,
            derivedtable.topic_topics_hjid,
            derivedtable.topics,
            derivedtable.number_of_topics
           FROM (public.indicator_element
             JOIN ( SELECT topic.topic_topics_hjid,
                    string_agg(DISTINCT (topic.value_)::text, ';'::text) AS topics,
                    count(*) AS number_of_topics
                   FROM public.topic
                  GROUP BY topic.topic_topics_hjid) derivedtable ON ((indicator_element.topics_indicator_element_hjid = derivedtable.topic_topics_hjid)))) indicators ON (((world_bank_data.indicator_id)::text = (indicators.id)::text)))
  WHERE (((world_bank_data.data_value)::text <> ''::text) AND ((world_bank_data.data_value)::text <> '0'::text) AND (world_bank_data.year_date IS NOT NULL) AND (indicators.source_organization IS NOT NULL) AND ((indicators.source_organization)::text <> ''::text))
  GROUP BY world_bank_data.indicator_id, world_bank_data.country_id
  WITH NO DATA;


--
-- Name: vote_meta_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.vote_meta_data (
    hjid bigint NOT NULL,
    ballot_type character varying(255),
    group_behavior character varying(255),
    outcome character varying(255),
    proffessional_behavior character varying(255),
    target character varying(255)
);


--
-- Data for Name: against_proposal_container; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.against_proposal_container (hjid) FROM stdin;
\.


--
-- Data for Name: against_proposal_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.against_proposal_data (hjid, header, number_value, parties, proposal_issue_number, proposal_type, against_proposal_list_agains_0) FROM stdin;
\.


--
-- Data for Name: agency; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.agency (hjid, agency_name, description, model_object_id, model_object_version) FROM stdin;
\.


--
-- Data for Name: application_action_event; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.application_action_event (hjid, application_operation, created_date, event_group, model_object_id, model_object_version, events_application_session_h_0, session_id, page, page_mode, element_id, action_name, user_id, error_message, application_message) FROM stdin;
\.


--
-- Data for Name: application_configuration; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.application_configuration (hjid, model_object_id, model_object_version, config_title, config_description, configuration_group, component, component_title, component_description, property_id, property_value, created_date, updated_date) FROM stdin;
\.


--
-- Data for Name: application_session; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.application_session (hjid, created_date, ip_information, model_object_id, model_object_version, session_type, user_agent_information, session_id, locale, operating_system, user_id, destroyed_date, screen_size, time_zone) FROM stdin;
\.


--
-- Data for Name: application_view; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.application_view (hjid, perspective, data_source_information_appl_0, operational_information_appl_0, performance_indicators_appli_0, quality_assurance_applicatio_0, target_profile_application_v_0) FROM stdin;
\.


--
-- Data for Name: assignment_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.assignment_data (hjid, assignment_type, detail, from_date, intressent_id, order_number, org_code, role_code, status, to_date, assignment_list_person_assig_0) FROM stdin;
\.


--
-- Data for Name: assignment_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.assignment_element (hjid, assignment_type, detail, from_date, intressent_id, order_number, org_code, role_code, status, to_date, uppdrag_person_assignment_el_0) FROM stdin;
\.


--
-- Data for Name: committee_document_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.committee_document_data (id, committee_proposal_url_xml, created_date, document_status_url_www, document_status_url_xml, document_url_html, document_url_text, end_number, hangar_id, label, org, public_date, rm, status, sub_title, sub_type, temp_label, title) FROM stdin;
\.


--
-- Data for Name: committee_proposal_component_0; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.committee_proposal_component_0 (hjid, against_proposal_container_c_0, committee_proposal_container_0, document_committee_proposal__0) FROM stdin;
\.


--
-- Data for Name: committee_proposal_container; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.committee_proposal_container (hjid) FROM stdin;
\.


--
-- Data for Name: committee_proposal_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.committee_proposal_data (hjid, against_proposal_number, against_proposal_parties, ballot_id, ballot_summary_item, ballot_url_xml, committee_report, decision_type, header, issue_number, proposal, rm, winner, committee_proposal_list_comm_0) FROM stdin;
\.


--
-- Data for Name: countries_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.countries_element (hjid, page, pages, per_page, total) FROM stdin;
\.


--
-- Data for Name: country_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.country_element (hjid, adminregion_id, adminregion_value, capital_city, country_name, id, income_level_id, income_level_value, iso_2code, latitude, lending_type_id, lending_type_value, longitude, region_id, region_value, country_countries_element_hj_0) FROM stdin;
\.


--
-- Data for Name: data_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.data_element (hjid, page, pages, per_page, total) FROM stdin;
\.


--
-- Data for Name: data_source_content; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.data_source_content (hjid, model_object_id, model_object_version) FROM stdin;
\.


--
-- Data for Name: detail_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.detail_data (hjid, code, detail, detail_type, intressent_id, detail_list_person_detail_da_0) FROM stdin;
\.


--
-- Data for Name: detail_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.detail_element (hjid, code, detail, detail_type, intressent_id) FROM stdin;
\.


--
-- Data for Name: document_activity_container; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_activity_container (hjid) FROM stdin;
\.


--
-- Data for Name: document_activity_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_activity_data (hjid, activity_name, code, created_date, order_number, process, status, document_activities_document_0) FROM stdin;
\.


--
-- Data for Name: document_attachment; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_attachment (hjid, file_name, file_size, file_type, file_url, document_attachment_list_doc_0) FROM stdin;
\.


--
-- Data for Name: document_attachment_container; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_attachment_container (hjid) FROM stdin;
\.


--
-- Data for Name: document_container_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_container_element (hjid, created, datum, debug, document_version, hits, hits_from, hits_to, next_page, page, total_pages, warning) FROM stdin;
\.


--
-- Data for Name: document_content_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_content_data (hjid, content, id) FROM stdin;
\.


--
-- Data for Name: document_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_data (id, committee_report_url_xml, document_status_url_www, document_status_url_xml, document_type, document_url_html, document_url_text, final_number, hangar_id, label, made_public_date, number_value, org, rm, status, sub_title, sub_type, temp_label, title) FROM stdin;
\.


--
-- Data for Name: document_detail_container; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_detail_container (hjid) FROM stdin;
\.


--
-- Data for Name: document_detail_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_detail_data (hjid, code, detail_name, text, document_detail_list_documen_0) FROM stdin;
\.


--
-- Data for Name: document_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_element (id, committee_report_url_xml, created_date, document_format, document_status_url_xml, document_type, document_url_html, document_url_text, hit, kall_id, label, made_public_date, number_value, org, related_id, rm, status, sub_title, sub_type, system_date, temp_label, title, dokument_document_container__0, domain_org, database_source, origin, lang, summary, note, note_title, debate_name, document_name, doc_type) FROM stdin;
\.


--
-- Data for Name: document_person_reference_co_0; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_person_reference_co_0 (hjid) FROM stdin;
\.


--
-- Data for Name: document_person_reference_da_0; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_person_reference_da_0 (hjid, order_number, party_short_code, person_reference_id, reference_name, role_description, document_person_reference_li_1) FROM stdin;
\.


--
-- Data for Name: document_proposal_container; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_proposal_container (hjid, proposal_document_proposal_c_0) FROM stdin;
\.


--
-- Data for Name: document_proposal_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_proposal_data (hjid, chamber, committee, decision_type, designation, processed_in, proposal_number, wording, wording_2, wording_3, wording_4) FROM stdin;
\.


--
-- Data for Name: document_reference_container; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_reference_container (hjid) FROM stdin;
\.


--
-- Data for Name: document_reference_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_reference_data (hjid, detail, reference_document_id, reference_type, document_reference_list_docu_0) FROM stdin;
\.


--
-- Data for Name: document_status_container; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.document_status_container (hjid, document_category, document_document_status_con_0, document_activity_container__0, document_attachment_containe_0, document_detail_container_do_0, document_person_reference_co_1, document_reference_container_0, document_proposal_document_s_0) FROM stdin;
\.


--
-- Data for Name: domain_portal; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.domain_portal (domain_name, hjid) FROM stdin;
\.


--
-- Data for Name: encrypted_value; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.encrypted_value (id, storage, user_id, vault_name) FROM stdin;
\.


--
-- Data for Name: indicator_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.indicator_element (hjid, id, indicator_name, source_id, source_value, source_note, source_organization, topics_indicator_element_hjid, indicator__indicators_elemen_0) FROM stdin;
\.


--
-- Data for Name: indicators_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.indicators_element (hjid, page, pages, per_page, total) FROM stdin;
\.


--
-- Data for Name: jv_commit; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.jv_commit (commit_pk, author, commit_date, commit_id, commit_date_instant) FROM stdin;
\.


--
-- Data for Name: jv_commit_property; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.jv_commit_property (property_name, property_value, commit_fk) FROM stdin;
\.


--
-- Data for Name: jv_global_id; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.jv_global_id (global_id_pk, local_id, fragment, type_name, owner_id_fk) FROM stdin;
\.


--
-- Data for Name: jv_snapshot; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.jv_snapshot (snapshot_pk, type, version, state, changed_properties, managed_type, global_id_fk, commit_fk) FROM stdin;
\.


--
-- Data for Name: language_content_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.language_content_data (hjid, created_date, from_language, language_content_type, language_value, model_object_id, model_object_version, ref_key, to_language, last_modified_date, key_group, location_context) FROM stdin;
\.


--
-- Data for Name: language_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.language_data (hjid, language_name, model_object_id, model_object_version, created_date, last_modified_date, language_enabled, auto_translation_enabled, language_code, translation_status) FROM stdin;
\.


--
-- Data for Name: operational_information_cont_0; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.operational_information_cont_0 (hjid, model_object_id, model_object_version) FROM stdin;
\.


--
-- Data for Name: performance_indicator_content; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.performance_indicator_content (hjid, model_object_id, model_object_version) FROM stdin;
\.


--
-- Data for Name: person_assignment_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person_assignment_data (hjid) FROM stdin;
\.


--
-- Data for Name: person_assignment_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person_assignment_element (hjid) FROM stdin;
\.


--
-- Data for Name: person_container_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person_container_data (hjid, person_person_container_data_0) FROM stdin;
\.


--
-- Data for Name: person_container_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person_container_element (hjid) FROM stdin;
\.


--
-- Data for Name: person_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person_data (id, born_year, election_region, first_name, gender, hangar_guid, image_url_192, image_url_80, image_url_max, last_name, party, person_url_xml, place, status, person_assignment_data_perso_0, person_detail_data_person_da_0) FROM stdin;
\.


--
-- Data for Name: person_detail_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person_detail_data (hjid) FROM stdin;
\.


--
-- Data for Name: person_detail_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person_detail_element (hjid, detail_list_person_detail_el_0) FROM stdin;
\.


--
-- Data for Name: person_element; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.person_element (id, born_year, election_region, first_name, gender, hangar_guid, image_url_192, image_url_80, image_url_max, last_name, party, person_url_xml, place, status, person_assignment_element_pe_0, person_detail_element_person_0, person_person_container_elem_0) FROM stdin;
\.


--
-- Data for Name: portal; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.portal (hjid, description, google_map_api_key, model_object_id, model_object_version, portal_name, portal_type, portals_agency_hjid) FROM stdin;
\.


--
-- Data for Name: qrtz_blob_triggers; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_blob_triggers (sched_name, trigger_name, trigger_group, job_data) FROM stdin;
\.


--
-- Data for Name: qrtz_calendars; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_calendars (sched_name, calendar_name, calendar) FROM stdin;
\.


--
-- Data for Name: qrtz_cron_triggers; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_cron_triggers (sched_name, trigger_name, trigger_group, cron_expression, time_zone_id) FROM stdin;
\.


--
-- Data for Name: qrtz_fired_triggers; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_fired_triggers (sched_name, entry_id, trigger_name, trigger_group, instance_name, fired_time, sched_time, priority, state, job_name, job_group, is_nonconcurrent, requests_recovery) FROM stdin;
\.


--
-- Data for Name: qrtz_job_details; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_job_details (sched_name, job_name, job_group, description, job_class_name, is_durable, is_nonconcurrent, is_update_data, requests_recovery, job_data) FROM stdin;
\.


--
-- Data for Name: qrtz_locks; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_locks (sched_name, lock_name) FROM stdin;
\.


--
-- Data for Name: qrtz_paused_trigger_grps; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_paused_trigger_grps (sched_name, trigger_group) FROM stdin;
\.


--
-- Data for Name: qrtz_scheduler_state; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_scheduler_state (sched_name, instance_name, last_checkin_time, checkin_interval) FROM stdin;
\.


--
-- Data for Name: qrtz_simple_triggers; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_simple_triggers (sched_name, trigger_name, trigger_group, repeat_count, repeat_interval, times_triggered) FROM stdin;
\.


--
-- Data for Name: qrtz_simprop_triggers; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_simprop_triggers (sched_name, trigger_name, trigger_group, str_prop_1, str_prop_2, str_prop_3, int_prop_1, int_prop_2, long_prop_1, long_prop_2, dec_prop_1, dec_prop_2, bool_prop_1, bool_prop_2) FROM stdin;
\.


--
-- Data for Name: qrtz_triggers; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.qrtz_triggers (sched_name, trigger_name, trigger_group, job_name, job_group, description, next_fire_time, prev_fire_time, priority, trigger_state, trigger_type, start_time, end_time, calendar_name, misfire_instr, job_data) FROM stdin;
\.


--
-- Data for Name: quality_assurance_content; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.quality_assurance_content (hjid, model_object_id, model_object_version) FROM stdin;
\.


--
-- Data for Name: rule_violation; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.rule_violation (id, detected_date, reference_id, name, resource_type, rule_description, rule_group, status, positive, rule_name) FROM stdin;
\.


--
-- Data for Name: sweden_county_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_county_data (hjid, code, county_name, county_regions_sweden_county_0) FROM stdin;
\.


--
-- Data for Name: sweden_county_data_container; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_county_data_container (hjid) FROM stdin;
\.


--
-- Data for Name: sweden_county_electoral_area; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_county_electoral_area (hjid, code, electoral_area_name, first_round, number_of_seats, number_of_voters, rest, second_round, landstingsvalkrets_sweden_co_0) FROM stdin;
\.


--
-- Data for Name: sweden_county_electoral_regi_0; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_county_electoral_regi_0 (hjid) FROM stdin;
\.


--
-- Data for Name: sweden_county_electoral_regi_1; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_county_electoral_regi_1 (hjid, code, county_name, seats, county_electoral_regions_swe_0) FROM stdin;
\.


--
-- Data for Name: sweden_election_region; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_election_region (hjid, county_id, municipal_id, region_name) FROM stdin;
\.


--
-- Data for Name: sweden_election_type; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_election_type (hjid, election_code, election_type, region_sweden_election_type__0, election_types_sweden_electi_0) FROM stdin;
\.


--
-- Data for Name: sweden_election_type_contain_0; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_election_type_contain_0 (hjid) FROM stdin;
\.


--
-- Data for Name: sweden_municipality_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_municipality_data (hjid, code, municipal_name, kommun_sweden_county_data_hj_0) FROM stdin;
\.


--
-- Data for Name: sweden_municipality_election_0; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_municipality_election_0 (hjid, code, election_region_name, first_round, number_of_seats, number_of_voters, rest, second_round, kommunvalkrets_sweden_munici_0) FROM stdin;
\.


--
-- Data for Name: sweden_parliament_electoral__0; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_parliament_electoral__0 (hjid) FROM stdin;
\.


--
-- Data for Name: sweden_parliament_electoral__1; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_parliament_electoral__1 (hjid, election_region_name, first_round, number_of_seats, number_of_voters, rest, second_round, parliament_electoral_regions_0) FROM stdin;
\.


--
-- Data for Name: sweden_political_party; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.sweden_political_party (hjid, address, city, co_address, email, fax_number, party_id, party_name, phone_number, post_code, registered_date, short_code, website, parties_sweden_election_regi_0) FROM stdin;
\.


--
-- Data for Name: target_profile_content; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.target_profile_content (hjid, model_object_id, model_object_version) FROM stdin;
\.


--
-- Data for Name: topic; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.topic (hjid, id, value_, topic_topics_hjid) FROM stdin;
\.


--
-- Data for Name: topics; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.topics (hjid) FROM stdin;
\.


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.user_account (hjid, email, model_object_id, model_object_version, number_of_visits, user_id, user_role, user_type, username, userpassword, country, created_date, user_lock_status, user_email_status) FROM stdin;
\.


--
-- Data for Name: user_account_address; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.user_account_address (hjid, hjvalue, hjindex) FROM stdin;
\.


--
-- Data for Name: vote_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.vote_data (embedded_id_ballot_id, embedded_id_concern, embedded_id_intressent_id, embedded_id_issue, ballot_type, bank_number, born_year, electoral_region, electoral_region_number, first_name, full_name, gender, label, last_name, party, place, rm, vote, vote_date) FROM stdin;
\.


--
-- Data for Name: vote_meta_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.vote_meta_data (hjid, ballot_type, group_behavior, outcome, proffessional_behavior, target) FROM stdin;
\.


--
-- Data for Name: world_bank_data; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.world_bank_data (hjid, country_id, country_value, data_value, indicator_id, indicator_value, year_date, data__data_element_hjid) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: jv_commit_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.jv_commit_pk_seq', 1, false);


--
-- Name: jv_global_id_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.jv_global_id_pk_seq', 1, false);


--
-- Name: jv_snapshot_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.jv_snapshot_pk_seq', 1, false);


--
-- Name: qrtz_calendars QRTZ_CALENDARS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_calendars
    ADD CONSTRAINT "QRTZ_CALENDARS_pkey" PRIMARY KEY (sched_name, calendar_name);


--
-- Name: qrtz_cron_triggers QRTZ_CRON_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_cron_triggers
    ADD CONSTRAINT "QRTZ_CRON_TRIGGERS_pkey" PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- Name: qrtz_fired_triggers QRTZ_FIRED_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_fired_triggers
    ADD CONSTRAINT "QRTZ_FIRED_TRIGGERS_pkey" PRIMARY KEY (sched_name, entry_id);


--
-- Name: qrtz_job_details QRTZ_JOB_DETAILS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_job_details
    ADD CONSTRAINT "QRTZ_JOB_DETAILS_pkey" PRIMARY KEY (sched_name, job_name, job_group);


--
-- Name: qrtz_locks QRTZ_LOCKS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_locks
    ADD CONSTRAINT "QRTZ_LOCKS_pkey" PRIMARY KEY (sched_name, lock_name);


--
-- Name: qrtz_paused_trigger_grps QRTZ_PAUSED_TRIGGER_GRPS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_paused_trigger_grps
    ADD CONSTRAINT "QRTZ_PAUSED_TRIGGER_GRPS_pkey" PRIMARY KEY (sched_name, trigger_group);


--
-- Name: qrtz_scheduler_state QRTZ_SCHEDULER_STATE_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_scheduler_state
    ADD CONSTRAINT "QRTZ_SCHEDULER_STATE_pkey" PRIMARY KEY (sched_name, instance_name);


--
-- Name: qrtz_simple_triggers QRTZ_SIMPLE_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_simple_triggers
    ADD CONSTRAINT "QRTZ_SIMPLE_TRIGGERS_pkey" PRIMARY KEY (sched_name);


--
-- Name: qrtz_simprop_triggers QRTZ_SIMPROP_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_simprop_triggers
    ADD CONSTRAINT "QRTZ_SIMPROP_TRIGGERS_pkey" PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- Name: qrtz_triggers QRTZ_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_triggers
    ADD CONSTRAINT "QRTZ_TRIGGERS_pkey" PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- Name: qrtz_blob_triggers QRTZ_bytea_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_blob_triggers
    ADD CONSTRAINT "QRTZ_bytea_TRIGGERS_pkey" PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- Name: against_proposal_container against_proposal_container_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.against_proposal_container
    ADD CONSTRAINT against_proposal_container_pkey PRIMARY KEY (hjid);


--
-- Name: against_proposal_data against_proposal_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.against_proposal_data
    ADD CONSTRAINT against_proposal_data_pkey PRIMARY KEY (hjid);


--
-- Name: agency agency_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.agency
    ADD CONSTRAINT agency_pkey PRIMARY KEY (hjid);


--
-- Name: application_action_event application_action_event_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_action_event
    ADD CONSTRAINT application_action_event_pkey PRIMARY KEY (hjid);


--
-- Name: application_configuration application_configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_configuration
    ADD CONSTRAINT application_configuration_pkey PRIMARY KEY (hjid);


--
-- Name: application_session application_session_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_session
    ADD CONSTRAINT application_session_pkey PRIMARY KEY (hjid);


--
-- Name: application_view application_view_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT application_view_pkey PRIMARY KEY (hjid);


--
-- Name: assignment_data assignment_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assignment_data
    ADD CONSTRAINT assignment_data_pkey PRIMARY KEY (hjid);


--
-- Name: assignment_element assignment_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assignment_element
    ADD CONSTRAINT assignment_element_pkey PRIMARY KEY (hjid);


--
-- Name: committee_document_data committee_document_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.committee_document_data
    ADD CONSTRAINT committee_document_data_pkey PRIMARY KEY (id);


--
-- Name: committee_proposal_component_0 committee_proposal_component_0_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.committee_proposal_component_0
    ADD CONSTRAINT committee_proposal_component_0_pkey PRIMARY KEY (hjid);


--
-- Name: committee_proposal_container committee_proposal_container_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.committee_proposal_container
    ADD CONSTRAINT committee_proposal_container_pkey PRIMARY KEY (hjid);


--
-- Name: committee_proposal_data committee_proposal_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.committee_proposal_data
    ADD CONSTRAINT committee_proposal_data_pkey PRIMARY KEY (hjid);


--
-- Name: countries_element countries_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.countries_element
    ADD CONSTRAINT countries_element_pkey PRIMARY KEY (hjid);


--
-- Name: country_element country_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.country_element
    ADD CONSTRAINT country_element_pkey PRIMARY KEY (hjid);


--
-- Name: data_element data_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.data_element
    ADD CONSTRAINT data_element_pkey PRIMARY KEY (hjid);


--
-- Name: data_source_content data_source_content_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.data_source_content
    ADD CONSTRAINT data_source_content_pkey PRIMARY KEY (hjid);


--
-- Name: detail_data detail_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.detail_data
    ADD CONSTRAINT detail_data_pkey PRIMARY KEY (hjid);


--
-- Name: detail_element detail_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.detail_element
    ADD CONSTRAINT detail_element_pkey PRIMARY KEY (hjid);


--
-- Name: document_activity_container document_activity_container_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_activity_container
    ADD CONSTRAINT document_activity_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_activity_data document_activity_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_activity_data
    ADD CONSTRAINT document_activity_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_attachment_container document_attachment_container_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_attachment_container
    ADD CONSTRAINT document_attachment_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_attachment document_attachment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_attachment
    ADD CONSTRAINT document_attachment_pkey PRIMARY KEY (hjid);


--
-- Name: document_container_element document_container_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_container_element
    ADD CONSTRAINT document_container_element_pkey PRIMARY KEY (hjid);


--
-- Name: document_content_data document_content_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_content_data
    ADD CONSTRAINT document_content_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_data document_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_data
    ADD CONSTRAINT document_data_pkey PRIMARY KEY (id);


--
-- Name: document_detail_container document_detail_container_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_detail_container
    ADD CONSTRAINT document_detail_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_detail_data document_detail_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_detail_data
    ADD CONSTRAINT document_detail_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_element document_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_element
    ADD CONSTRAINT document_element_pkey PRIMARY KEY (id);


--
-- Name: document_person_reference_co_0 document_person_reference_co_0_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_person_reference_co_0
    ADD CONSTRAINT document_person_reference_co_0_pkey PRIMARY KEY (hjid);


--
-- Name: document_person_reference_da_0 document_person_reference_da_0_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_person_reference_da_0
    ADD CONSTRAINT document_person_reference_da_0_pkey PRIMARY KEY (hjid);


--
-- Name: document_proposal_container document_proposal_container_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_proposal_container
    ADD CONSTRAINT document_proposal_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_proposal_data document_proposal_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_proposal_data
    ADD CONSTRAINT document_proposal_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_reference_container document_reference_container_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_reference_container
    ADD CONSTRAINT document_reference_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_reference_data document_reference_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_reference_data
    ADD CONSTRAINT document_reference_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_status_container document_status_container_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT document_status_container_pkey PRIMARY KEY (hjid);


--
-- Name: domain_portal domain_portal_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.domain_portal
    ADD CONSTRAINT domain_portal_pkey PRIMARY KEY (hjid);


--
-- Name: indicator_element indicator_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.indicator_element
    ADD CONSTRAINT indicator_element_pkey PRIMARY KEY (hjid);


--
-- Name: indicators_element indicators_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.indicators_element
    ADD CONSTRAINT indicators_element_pkey PRIMARY KEY (hjid);


--
-- Name: jv_commit jv_commit_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.jv_commit
    ADD CONSTRAINT jv_commit_pk PRIMARY KEY (commit_pk);


--
-- Name: jv_commit_property jv_commit_property_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.jv_commit_property
    ADD CONSTRAINT jv_commit_property_pk PRIMARY KEY (property_name, commit_fk);


--
-- Name: jv_global_id jv_global_id_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.jv_global_id
    ADD CONSTRAINT jv_global_id_pk PRIMARY KEY (global_id_pk);


--
-- Name: jv_snapshot jv_snapshot_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.jv_snapshot
    ADD CONSTRAINT jv_snapshot_pk PRIMARY KEY (snapshot_pk);


--
-- Name: language_content_data language_content_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.language_content_data
    ADD CONSTRAINT language_content_data_pkey PRIMARY KEY (hjid);


--
-- Name: language_data language_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.language_data
    ADD CONSTRAINT language_data_pkey PRIMARY KEY (hjid);


--
-- Name: operational_information_cont_0 operational_information_cont_0_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.operational_information_cont_0
    ADD CONSTRAINT operational_information_cont_0_pkey PRIMARY KEY (hjid);


--
-- Name: performance_indicator_content performance_indicator_content_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.performance_indicator_content
    ADD CONSTRAINT performance_indicator_content_pkey PRIMARY KEY (hjid);


--
-- Name: person_assignment_data person_assignment_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_assignment_data
    ADD CONSTRAINT person_assignment_data_pkey PRIMARY KEY (hjid);


--
-- Name: person_assignment_element person_assignment_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_assignment_element
    ADD CONSTRAINT person_assignment_element_pkey PRIMARY KEY (hjid);


--
-- Name: person_container_data person_container_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_container_data
    ADD CONSTRAINT person_container_data_pkey PRIMARY KEY (hjid);


--
-- Name: person_container_element person_container_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_container_element
    ADD CONSTRAINT person_container_element_pkey PRIMARY KEY (hjid);


--
-- Name: person_data person_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_data
    ADD CONSTRAINT person_data_pkey PRIMARY KEY (id);


--
-- Name: person_detail_data person_detail_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_detail_data
    ADD CONSTRAINT person_detail_data_pkey PRIMARY KEY (hjid);


--
-- Name: person_detail_element person_detail_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_detail_element
    ADD CONSTRAINT person_detail_element_pkey PRIMARY KEY (hjid);


--
-- Name: person_element person_element_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_element
    ADD CONSTRAINT person_element_pkey PRIMARY KEY (id);


--
-- Name: portal portal_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.portal
    ADD CONSTRAINT portal_pkey PRIMARY KEY (hjid);


--
-- Name: quality_assurance_content quality_assurance_content_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.quality_assurance_content
    ADD CONSTRAINT quality_assurance_content_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_data_container sweden_county_data_container_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_county_data_container
    ADD CONSTRAINT sweden_county_data_container_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_data sweden_county_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_county_data
    ADD CONSTRAINT sweden_county_data_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_electoral_area sweden_county_electoral_area_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_county_electoral_area
    ADD CONSTRAINT sweden_county_electoral_area_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_electoral_regi_0 sweden_county_electoral_regi_0_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_county_electoral_regi_0
    ADD CONSTRAINT sweden_county_electoral_regi_0_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_electoral_regi_1 sweden_county_electoral_regi_1_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_county_electoral_regi_1
    ADD CONSTRAINT sweden_county_electoral_regi_1_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_election_region sweden_election_region_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_election_region
    ADD CONSTRAINT sweden_election_region_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_election_type_contain_0 sweden_election_type_contain_0_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_election_type_contain_0
    ADD CONSTRAINT sweden_election_type_contain_0_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_election_type sweden_election_type_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_election_type
    ADD CONSTRAINT sweden_election_type_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_municipality_data sweden_municipality_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_municipality_data
    ADD CONSTRAINT sweden_municipality_data_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_municipality_election_0 sweden_municipality_election_0_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_municipality_election_0
    ADD CONSTRAINT sweden_municipality_election_0_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_parliament_electoral__0 sweden_parliament_electoral__0_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_parliament_electoral__0
    ADD CONSTRAINT sweden_parliament_electoral__0_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_parliament_electoral__1 sweden_parliament_electoral__1_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_parliament_electoral__1
    ADD CONSTRAINT sweden_parliament_electoral__1_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_political_party sweden_political_party_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_political_party
    ADD CONSTRAINT sweden_political_party_pkey PRIMARY KEY (hjid);


--
-- Name: target_profile_content target_profile_content_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.target_profile_content
    ADD CONSTRAINT target_profile_content_pkey PRIMARY KEY (hjid);


--
-- Name: topic topic_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pkey PRIMARY KEY (hjid);


--
-- Name: topics topics_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.topics
    ADD CONSTRAINT topics_pkey PRIMARY KEY (hjid);


--
-- Name: user_account_address user_account_address_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_account_address
    ADD CONSTRAINT user_account_address_pkey PRIMARY KEY (hjid, hjindex);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (hjid);


--
-- Name: vote_data vote_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.vote_data
    ADD CONSTRAINT vote_data_pkey PRIMARY KEY (embedded_id_ballot_id, embedded_id_concern, embedded_id_intressent_id, embedded_id_issue);


--
-- Name: vote_meta_data vote_meta_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.vote_meta_data
    ADD CONSTRAINT vote_meta_data_pkey PRIMARY KEY (hjid);


--
-- Name: world_bank_data world_bank_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.world_bank_data
    ADD CONSTRAINT world_bank_data_pkey PRIMARY KEY (hjid);


--
-- Name: application_action_event_created_date_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX application_action_event_created_date_idx ON public.application_action_event USING btree (created_date);


--
-- Name: application_action_event_element_id_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX application_action_event_element_id_idx ON public.application_action_event USING btree (element_id);


--
-- Name: application_action_event_element_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX application_action_event_element_idx ON public.application_action_event USING btree (element_id);


--
-- Name: application_action_event_page_created_date_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX application_action_event_page_created_date_idx ON public.application_action_event USING btree (page, created_date);


--
-- Name: application_action_event_page_element_id_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX application_action_event_page_element_id_idx ON public.application_action_event USING btree (page, element_id);


--
-- Name: application_action_event_page_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX application_action_event_page_idx ON public.application_action_event USING btree (page);


--
-- Name: application_action_event_sessionid_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX application_action_event_sessionid_idx ON public.application_action_event USING btree (session_id);


--
-- Name: application_session_created_date_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX application_session_created_date_idx ON public.application_session USING btree (created_date);


--
-- Name: application_session_ip_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX application_session_ip_idx ON public.application_session USING btree (ip_information);


--
-- Name: idx_annual_date_party; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_annual_date_party ON public.view_riksdagen_vote_data_ballot_party_summary_annual USING btree (embedded_id_vote_date, embedded_id_party);


--
-- Name: idx_annual_summary_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_annual_summary_date ON public.view_riksdagen_vote_data_ballot_summary_annual USING btree (vote_date);


--
-- Name: idx_assignment_data_assignment_type; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_assignment_data_assignment_type ON public.assignment_data USING btree (assignment_type);


--
-- Name: idx_assignment_data_ministry_dates; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_assignment_data_ministry_dates ON public.assignment_data USING btree (org_code, role_code, from_date DESC, to_date DESC) WHERE ((assignment_type)::text = 'Departement'::text);


--
-- Name: idx_assignment_data_org_code; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_assignment_data_org_code ON public.assignment_data USING btree (org_code);


--
-- Name: idx_assignment_data_type_dates; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_assignment_data_type_dates ON public.assignment_data USING btree (assignment_type, from_date, to_date, intressent_id);


--
-- Name: idx_ballot_decision_party_party; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_ballot_decision_party_party ON public.view_riksdagen_committee_ballot_decision_party_summary USING btree (embedded_id_party);


--
-- Name: idx_ballot_decision_politician_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_ballot_decision_politician_id ON public.view_riksdagen_committee_ballot_decision_politician_summary USING btree (embedded_id_intressent_id);


--
-- Name: idx_ballot_decision_summary_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_ballot_decision_summary_date ON public.view_riksdagen_committee_ballot_decision_summary USING btree (vote_date);


--
-- Name: idx_ballot_summary_approved; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_ballot_summary_approved ON public.view_riksdagen_vote_data_ballot_summary USING btree (approved);


--
-- Name: idx_ballot_summary_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_ballot_summary_date ON public.view_riksdagen_vote_data_ballot_summary USING btree (vote_date);


--
-- Name: idx_ballot_summary_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_ballot_summary_id ON public.view_riksdagen_vote_data_ballot_summary USING btree (embedded_id_ballot_id, embedded_id_issue, embedded_id_concern);


--
-- Name: idx_committee_decisions_ballot; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_committee_decisions_ballot ON public.view_riksdagen_committee_decisions USING btree (ballot_id);


--
-- Name: idx_committee_decisions_rm_issue; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_committee_decisions_rm_issue ON public.view_riksdagen_committee_decisions USING btree (rm, embedded_id_issue_nummer);


--
-- Name: idx_daily_summary_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_daily_summary_date ON public.view_riksdagen_vote_data_ballot_summary_daily USING btree (vote_date);


--
-- Name: idx_decision_type_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_decision_type_date ON public.view_riksdagen_committee_decision_type_summary USING btree (embedded_id_decision_date);


--
-- Name: idx_decision_type_org_composite; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_decision_type_org_composite ON public.view_riksdagen_committee_decision_type_org_summary USING btree (embedded_id_decision_type, embedded_id_org);


--
-- Name: idx_doc_data_made_public_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_doc_data_made_public_date ON public.document_data USING btree (made_public_date) WHERE (made_public_date IS NOT NULL);


--
-- Name: idx_doc_data_org; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_doc_data_org ON public.document_data USING btree (org) WHERE (org IS NOT NULL);


--
-- Name: idx_doc_data_org_type_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_doc_data_org_type_date ON public.document_data USING btree (org, document_type, made_public_date) WHERE ((org IS NOT NULL) AND ((document_type)::text = 'prop'::text) AND (made_public_date IS NOT NULL));


--
-- Name: idx_doc_proposal_committee; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_doc_proposal_committee ON public.document_proposal_data USING btree (committee) WHERE (committee IS NOT NULL);


--
-- Name: idx_doc_proposal_decision_type; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_doc_proposal_decision_type ON public.document_proposal_data USING btree (decision_type) WHERE (decision_type IS NOT NULL);


--
-- Name: idx_document_data_ministry_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_document_data_ministry_date ON public.document_data USING btree (org, made_public_date DESC) WHERE ((org IS NOT NULL) AND ((org)::text ~~ '%departement%'::text));


--
-- Name: idx_monthly_date_party; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_monthly_date_party ON public.view_riksdagen_vote_data_ballot_party_summary_monthly USING btree (embedded_id_vote_date, embedded_id_party);


--
-- Name: idx_monthly_summary_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_monthly_summary_date ON public.view_riksdagen_vote_data_ballot_summary_monthly USING btree (vote_date);


--
-- Name: idx_party_daily_summary_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_party_daily_summary_date ON public.view_riksdagen_vote_data_ballot_party_summary_daily USING btree (embedded_id_vote_date);


--
-- Name: idx_party_daily_summary_party; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_party_daily_summary_party ON public.view_riksdagen_vote_data_ballot_party_summary_daily USING btree (embedded_id_party);


--
-- Name: idx_party_summary_approved; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_party_summary_approved ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (party_approved);


--
-- Name: idx_party_summary_ballot_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_party_summary_ballot_id ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (embedded_id_ballot_id, embedded_id_issue, embedded_id_concern);


--
-- Name: idx_party_summary_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_party_summary_date ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (vote_date);


--
-- Name: idx_party_summary_party; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_party_summary_party ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (embedded_id_party);


--
-- Name: idx_party_summary_party_won; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_party_summary_party_won ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (party_won);


--
-- Name: idx_person_ref_party; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_person_ref_party ON public.document_person_reference_da_0 USING btree (party_short_code) WHERE (party_short_code IS NOT NULL);


--
-- Name: idx_person_ref_person_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_person_ref_person_id ON public.document_person_reference_da_0 USING btree (person_reference_id) WHERE (person_reference_id IS NOT NULL);


--
-- Name: idx_person_status; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_person_status ON public.person_data USING btree (status, party) WHERE ((status)::text = 'active'::text);


--
-- Name: idx_politician_annual_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_annual_date ON public.view_riksdagen_vote_data_ballot_politician_summary_annual USING btree (embedded_id_vote_date);


--
-- Name: idx_politician_annual_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_annual_id ON public.view_riksdagen_vote_data_ballot_politician_summary_annual USING btree (embedded_id_intressent_id);


--
-- Name: idx_politician_document_date_org; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_document_date_org ON public.view_riksdagen_politician_document USING btree (made_public_date DESC, org, party_short_code);


--
-- Name: idx_politician_document_ministry; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_document_ministry ON public.view_riksdagen_politician_document USING btree (org, made_public_date DESC, document_type) WHERE (org IS NOT NULL);


--
-- Name: idx_politician_monthly_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_monthly_date ON public.view_riksdagen_vote_data_ballot_politician_summary_monthly USING btree (embedded_id_vote_date);


--
-- Name: idx_politician_monthly_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_monthly_id ON public.view_riksdagen_vote_data_ballot_politician_summary_monthly USING btree (embedded_id_intressent_id);


--
-- Name: idx_politician_summary_party; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_summary_party ON public.view_riksdagen_vote_data_ballot_politician_summary USING btree (party);


--
-- Name: idx_politician_summary_politician; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_summary_politician ON public.view_riksdagen_vote_data_ballot_politician_summary USING btree (embedded_id_intressent_id);


--
-- Name: idx_politician_summary_vote_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_summary_vote_date ON public.view_riksdagen_vote_data_ballot_politician_summary USING btree (vote_date);


--
-- Name: idx_politician_weekly_composite; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_weekly_composite ON public.view_riksdagen_vote_data_ballot_politician_summary_weekly USING btree (embedded_id_vote_date, embedded_id_intressent_id);


--
-- Name: idx_politician_weekly_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_weekly_date ON public.view_riksdagen_vote_data_ballot_politician_summary_weekly USING btree (embedded_id_vote_date);


--
-- Name: idx_politician_weekly_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_politician_weekly_id ON public.view_riksdagen_vote_data_ballot_politician_summary_weekly USING btree (embedded_id_intressent_id);


--
-- Name: idx_qrtz_ft_inst_job_req_rcvry; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_ft_inst_job_req_rcvry ON public.qrtz_fired_triggers USING btree (sched_name, instance_name, requests_recovery);


--
-- Name: idx_qrtz_ft_j_g; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_ft_j_g ON public.qrtz_fired_triggers USING btree (sched_name, job_name, job_group);


--
-- Name: idx_qrtz_ft_jg; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_ft_jg ON public.qrtz_fired_triggers USING btree (sched_name, job_group);


--
-- Name: idx_qrtz_ft_t_g; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_ft_t_g ON public.qrtz_fired_triggers USING btree (sched_name, trigger_name, trigger_group);


--
-- Name: idx_qrtz_ft_tg; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_ft_tg ON public.qrtz_fired_triggers USING btree (sched_name, trigger_group);


--
-- Name: idx_qrtz_ft_trig_inst_name; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_ft_trig_inst_name ON public.qrtz_fired_triggers USING btree (sched_name, instance_name);


--
-- Name: idx_qrtz_t_c; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_c ON public.qrtz_triggers USING btree (sched_name, calendar_name);


--
-- Name: idx_qrtz_t_g; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_g ON public.qrtz_triggers USING btree (sched_name, trigger_group);


--
-- Name: idx_qrtz_t_j; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_j ON public.qrtz_triggers USING btree (sched_name, job_name, job_group);


--
-- Name: idx_qrtz_t_jg; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_jg ON public.qrtz_triggers USING btree (sched_name, job_group);


--
-- Name: idx_qrtz_t_n_g_state; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_n_g_state ON public.qrtz_triggers USING btree (sched_name, trigger_group, trigger_state);


--
-- Name: idx_qrtz_t_n_state; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_n_state ON public.qrtz_triggers USING btree (sched_name, trigger_name, trigger_group, trigger_state);


--
-- Name: idx_qrtz_t_next_fire_time; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_next_fire_time ON public.qrtz_triggers USING btree (sched_name, next_fire_time);


--
-- Name: idx_qrtz_t_nft_misfire; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_nft_misfire ON public.qrtz_triggers USING btree (sched_name, misfire_instr, next_fire_time);


--
-- Name: idx_qrtz_t_nft_st; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_nft_st ON public.qrtz_triggers USING btree (sched_name, trigger_state, next_fire_time);


--
-- Name: idx_qrtz_t_nft_st_misfire; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_nft_st_misfire ON public.qrtz_triggers USING btree (sched_name, misfire_instr, next_fire_time, trigger_state);


--
-- Name: idx_qrtz_t_nft_st_misfire_grp; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_nft_st_misfire_grp ON public.qrtz_triggers USING btree (sched_name, misfire_instr, next_fire_time, trigger_group, trigger_state);


--
-- Name: idx_qrtz_t_state; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_qrtz_t_state ON public.qrtz_triggers USING btree (sched_name, trigger_state);


--
-- Name: idx_rpd_doc_type_subtype; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_rpd_doc_type_subtype ON public.view_riksdagen_politician_document USING btree (document_type, sub_type);


--
-- Name: idx_rpd_made_public_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_rpd_made_public_date ON public.view_riksdagen_politician_document USING btree (made_public_date);


--
-- Name: idx_rpd_party_short_code; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_rpd_party_short_code ON public.view_riksdagen_politician_document USING btree (party_short_code);


--
-- Name: idx_rpd_person_ref_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_rpd_person_ref_id ON public.view_riksdagen_politician_document USING btree (person_reference_id);


--
-- Name: idx_rule_violation_date_resource; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_rule_violation_date_resource ON public.rule_violation USING btree (detected_date DESC, reference_id, resource_type) WHERE ((status)::text = 'ACTIVE'::text);


--
-- Name: idx_rule_violation_detected_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_rule_violation_detected_date ON public.rule_violation USING btree (detected_date DESC);


--
-- Name: idx_rule_violation_resource; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_rule_violation_resource ON public.rule_violation USING btree (reference_id, resource_type, status) WHERE ((resource_type)::text = 'POLITICIAN'::text);


--
-- Name: idx_vote_data_ballot_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_vote_data_ballot_id ON public.vote_data USING btree (embedded_id_ballot_id, embedded_id_issue, embedded_id_concern);


--
-- Name: idx_vote_data_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_vote_data_date ON public.vote_data USING btree (vote_date);


--
-- Name: idx_vote_data_party; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_vote_data_party ON public.vote_data USING btree (party);


--
-- Name: idx_vote_data_votes; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_vote_data_votes ON public.vote_data USING btree (vote);


--
-- Name: idx_vote_summary_daily_date_person; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_vote_summary_daily_date_person ON public.view_riksdagen_vote_data_ballot_politician_summary_daily USING btree (embedded_id_vote_date DESC, embedded_id_intressent_id);


--
-- Name: idx_weekly_date_party; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_weekly_date_party ON public.view_riksdagen_vote_data_ballot_party_summary_weekly USING btree (embedded_id_vote_date, embedded_id_party);


--
-- Name: idx_weekly_summary_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_weekly_summary_date ON public.view_riksdagen_vote_data_ballot_summary_weekly USING btree (vote_date);


--
-- Name: jv_commit_commit_id_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX jv_commit_commit_id_idx ON public.jv_commit USING btree (commit_id);


--
-- Name: jv_commit_property_commit_fk_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX jv_commit_property_commit_fk_idx ON public.jv_commit_property USING btree (commit_fk);


--
-- Name: jv_commit_property_property_name_property_value_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX jv_commit_property_property_name_property_value_idx ON public.jv_commit_property USING btree (property_name, property_value);


--
-- Name: jv_global_id_local_id_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX jv_global_id_local_id_idx ON public.jv_global_id USING btree (local_id);


--
-- Name: jv_snapshot_commit_fk_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX jv_snapshot_commit_fk_idx ON public.jv_snapshot USING btree (commit_fk);


--
-- Name: jv_snapshot_global_id_fk_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX jv_snapshot_global_id_fk_idx ON public.jv_snapshot USING btree (global_id_fk);


--
-- Name: person_element fk_13jay3yk8opnt33758httu9kb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_element
    ADD CONSTRAINT fk_13jay3yk8opnt33758httu9kb FOREIGN KEY (person_detail_element_person_0) REFERENCES public.person_detail_element(hjid);


--
-- Name: application_view fk_2ivjcdwosa63ant7jc5c6cojj; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_2ivjcdwosa63ant7jc5c6cojj FOREIGN KEY (target_profile_application_v_0) REFERENCES public.target_profile_content(hjid);


--
-- Name: country_element fk_3k0s1gih1msbej3bp2iotg52y; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.country_element
    ADD CONSTRAINT fk_3k0s1gih1msbej3bp2iotg52y FOREIGN KEY (country_countries_element_hj_0) REFERENCES public.countries_element(hjid);


--
-- Name: person_element fk_3o85sqp9yss0nler1yl1umfl1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_element
    ADD CONSTRAINT fk_3o85sqp9yss0nler1yl1umfl1 FOREIGN KEY (person_person_container_elem_0) REFERENCES public.person_container_element(hjid);


--
-- Name: sweden_county_electoral_regi_1 fk_4y4vi3cafmbdhvckntfn7qdps; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_county_electoral_regi_1
    ADD CONSTRAINT fk_4y4vi3cafmbdhvckntfn7qdps FOREIGN KEY (county_electoral_regions_swe_0) REFERENCES public.sweden_county_electoral_regi_0(hjid);


--
-- Name: against_proposal_data fk_5u5u77qsrpa2qy6umqrph4tyf; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.against_proposal_data
    ADD CONSTRAINT fk_5u5u77qsrpa2qy6umqrph4tyf FOREIGN KEY (against_proposal_list_agains_0) REFERENCES public.against_proposal_container(hjid);


--
-- Name: person_container_data fk_5w4uvrhl3l7c441b7ra7p8txr; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_container_data
    ADD CONSTRAINT fk_5w4uvrhl3l7c441b7ra7p8txr FOREIGN KEY (person_person_container_data_0) REFERENCES public.person_data(id);


--
-- Name: document_status_container fk_6crp887w8xy3e4i143yyydjqv; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_6crp887w8xy3e4i143yyydjqv FOREIGN KEY (document_reference_container_0) REFERENCES public.document_reference_container(hjid);


--
-- Name: portal fk_7c8jfw8bnxrm2aj26w9qlx340; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.portal
    ADD CONSTRAINT fk_7c8jfw8bnxrm2aj26w9qlx340 FOREIGN KEY (portals_agency_hjid) REFERENCES public.agency(hjid);


--
-- Name: sweden_county_electoral_area fk_7h4feuc5bwu12tyaka1nx1ln; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_county_electoral_area
    ADD CONSTRAINT fk_7h4feuc5bwu12tyaka1nx1ln FOREIGN KEY (landstingsvalkrets_sweden_co_0) REFERENCES public.sweden_county_electoral_regi_1(hjid);


--
-- Name: assignment_data fk_84o1dcsfeyp1o25nfdpppa7oe; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assignment_data
    ADD CONSTRAINT fk_84o1dcsfeyp1o25nfdpppa7oe FOREIGN KEY (assignment_list_person_assig_0) REFERENCES public.person_assignment_data(hjid);


--
-- Name: document_status_container fk_86c52yf22uk0bpcs1qoc3aeyv; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_86c52yf22uk0bpcs1qoc3aeyv FOREIGN KEY (document_attachment_containe_0) REFERENCES public.document_attachment_container(hjid);


--
-- Name: user_account_address fk_8931ymg13vy6vfkrichtst7bj; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_account_address
    ADD CONSTRAINT fk_8931ymg13vy6vfkrichtst7bj FOREIGN KEY (hjid) REFERENCES public.user_account(hjid);


--
-- Name: indicator_element fk_8l1m1pum4e3catw4443rup4q5; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.indicator_element
    ADD CONSTRAINT fk_8l1m1pum4e3catw4443rup4q5 FOREIGN KEY (indicator__indicators_elemen_0) REFERENCES public.indicators_element(hjid);


--
-- Name: committee_proposal_component_0 fk_90arga58ce9bnjkc6lws04uhw; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.committee_proposal_component_0
    ADD CONSTRAINT fk_90arga58ce9bnjkc6lws04uhw FOREIGN KEY (committee_proposal_container_0) REFERENCES public.committee_proposal_container(hjid);


--
-- Name: indicator_element fk_92h99v4i1pmr69x0y43pocv2a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.indicator_element
    ADD CONSTRAINT fk_92h99v4i1pmr69x0y43pocv2a FOREIGN KEY (topics_indicator_element_hjid) REFERENCES public.topics(hjid);


--
-- Name: domain_portal fk_9ln0n5axxjuxtbpepyad69rel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.domain_portal
    ADD CONSTRAINT fk_9ln0n5axxjuxtbpepyad69rel FOREIGN KEY (hjid) REFERENCES public.portal(hjid);


--
-- Name: document_status_container fk_9q1ktfb77gieq0xugoqe2fidd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_9q1ktfb77gieq0xugoqe2fidd FOREIGN KEY (document_detail_container_do_0) REFERENCES public.document_detail_container(hjid);


--
-- Name: application_view fk_9x5havflf3rdfkaw1hangbemd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_9x5havflf3rdfkaw1hangbemd FOREIGN KEY (quality_assurance_applicatio_0) REFERENCES public.quality_assurance_content(hjid);


--
-- Name: person_detail_element fk_a6syxeadcisfnnfjqemog93qd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_detail_element
    ADD CONSTRAINT fk_a6syxeadcisfnnfjqemog93qd FOREIGN KEY (detail_list_person_detail_el_0) REFERENCES public.detail_element(hjid);


--
-- Name: sweden_political_party fk_c2f4dhdce9p61sg50rnww73c1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_political_party
    ADD CONSTRAINT fk_c2f4dhdce9p61sg50rnww73c1 FOREIGN KEY (parties_sweden_election_regi_0) REFERENCES public.sweden_election_region(hjid);


--
-- Name: document_reference_data fk_c4uqb4d6xqa5d7afwen8sny67; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_reference_data
    ADD CONSTRAINT fk_c4uqb4d6xqa5d7afwen8sny67 FOREIGN KEY (document_reference_list_docu_0) REFERENCES public.document_reference_container(hjid);


--
-- Name: detail_data fk_diexjlb9hdrfv7g5y06cj6nu5; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.detail_data
    ADD CONSTRAINT fk_diexjlb9hdrfv7g5y06cj6nu5 FOREIGN KEY (detail_list_person_detail_da_0) REFERENCES public.person_detail_data(hjid);


--
-- Name: world_bank_data fk_e0yghurnnhmkahpt7ydf008fo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.world_bank_data
    ADD CONSTRAINT fk_e0yghurnnhmkahpt7ydf008fo FOREIGN KEY (data__data_element_hjid) REFERENCES public.data_element(hjid);


--
-- Name: committee_proposal_component_0 fk_eofapva6jn5k3h5gnj4whyilb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.committee_proposal_component_0
    ADD CONSTRAINT fk_eofapva6jn5k3h5gnj4whyilb FOREIGN KEY (against_proposal_container_c_0) REFERENCES public.against_proposal_container(hjid);


--
-- Name: application_view fk_f4bptktby95bygv359chn7lbn; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_f4bptktby95bygv359chn7lbn FOREIGN KEY (performance_indicators_appli_0) REFERENCES public.performance_indicator_content(hjid);


--
-- Name: document_activity_data fk_gruc53dqu0smf6s1a0gkelvdm; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_activity_data
    ADD CONSTRAINT fk_gruc53dqu0smf6s1a0gkelvdm FOREIGN KEY (document_activities_document_0) REFERENCES public.document_activity_container(hjid);


--
-- Name: sweden_municipality_data fk_gykahsnks6y9v8y5novlxagnf; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_municipality_data
    ADD CONSTRAINT fk_gykahsnks6y9v8y5novlxagnf FOREIGN KEY (kommun_sweden_county_data_hj_0) REFERENCES public.sweden_county_data(hjid);


--
-- Name: committee_proposal_data fk_hs04ji7kqvwd7313ryp20vo0x; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.committee_proposal_data
    ADD CONSTRAINT fk_hs04ji7kqvwd7313ryp20vo0x FOREIGN KEY (committee_proposal_list_comm_0) REFERENCES public.committee_proposal_container(hjid);


--
-- Name: document_status_container fk_iirofquegnrpnuonvnydf6wfb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_iirofquegnrpnuonvnydf6wfb FOREIGN KEY (document_proposal_document_s_0) REFERENCES public.document_proposal_container(hjid);


--
-- Name: document_status_container fk_jjcxsqmdnjw0nbwducjyecdg4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_jjcxsqmdnjw0nbwducjyecdg4 FOREIGN KEY (document_document_status_con_0) REFERENCES public.document_data(id);


--
-- Name: person_data fk_jrgy7nw6n071uok8p1hkp03rh; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_data
    ADD CONSTRAINT fk_jrgy7nw6n071uok8p1hkp03rh FOREIGN KEY (person_detail_data_person_da_0) REFERENCES public.person_detail_data(hjid);


--
-- Name: committee_proposal_component_0 fk_k78eqmx2m3ja0267xhthfeio4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.committee_proposal_component_0
    ADD CONSTRAINT fk_k78eqmx2m3ja0267xhthfeio4 FOREIGN KEY (document_committee_proposal__0) REFERENCES public.committee_document_data(id);


--
-- Name: assignment_element fk_ks1811fwuno6vqof0lskerpib; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.assignment_element
    ADD CONSTRAINT fk_ks1811fwuno6vqof0lskerpib FOREIGN KEY (uppdrag_person_assignment_el_0) REFERENCES public.person_assignment_element(hjid);


--
-- Name: document_attachment fk_lean1i0p0e5rv28my9297lq22; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_attachment
    ADD CONSTRAINT fk_lean1i0p0e5rv28my9297lq22 FOREIGN KEY (document_attachment_list_doc_0) REFERENCES public.document_attachment_container(hjid);


--
-- Name: document_person_reference_da_0 fk_lsfup3rosph7239t1idorm1cd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_person_reference_da_0
    ADD CONSTRAINT fk_lsfup3rosph7239t1idorm1cd FOREIGN KEY (document_person_reference_li_1) REFERENCES public.document_person_reference_co_0(hjid);


--
-- Name: document_proposal_container fk_m55tt4vaimgb5qk7xj9mgxmry; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_proposal_container
    ADD CONSTRAINT fk_m55tt4vaimgb5qk7xj9mgxmry FOREIGN KEY (proposal_document_proposal_c_0) REFERENCES public.document_proposal_data(hjid);


--
-- Name: person_element fk_m6dcdojsb6iv9lrego5kurr7p; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_element
    ADD CONSTRAINT fk_m6dcdojsb6iv9lrego5kurr7p FOREIGN KEY (person_assignment_element_pe_0) REFERENCES public.person_assignment_element(hjid);


--
-- Name: document_status_container fk_ng4kjnv3cm4e6ud3fikwi6p7i; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_ng4kjnv3cm4e6ud3fikwi6p7i FOREIGN KEY (document_person_reference_co_1) REFERENCES public.document_person_reference_co_0(hjid);


--
-- Name: application_action_event fk_nlqlshlogsx2g8u5d3y28my28; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_action_event
    ADD CONSTRAINT fk_nlqlshlogsx2g8u5d3y28my28 FOREIGN KEY (events_application_session_h_0) REFERENCES public.application_session(hjid);


--
-- Name: document_element fk_o24n54auwa2xyflis6nkrajpd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_element
    ADD CONSTRAINT fk_o24n54auwa2xyflis6nkrajpd FOREIGN KEY (dokument_document_container__0) REFERENCES public.document_container_element(hjid);


--
-- Name: topic fk_o7ol28sotu1r12n8txv2gigok; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT fk_o7ol28sotu1r12n8txv2gigok FOREIGN KEY (topic_topics_hjid) REFERENCES public.topics(hjid);


--
-- Name: sweden_election_type fk_ob50ibby6jamvitbxknoucifg; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_election_type
    ADD CONSTRAINT fk_ob50ibby6jamvitbxknoucifg FOREIGN KEY (region_sweden_election_type__0) REFERENCES public.sweden_election_region(hjid);


--
-- Name: application_view fk_p8b7gnxeglk71etbbql3j184s; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_p8b7gnxeglk71etbbql3j184s FOREIGN KEY (data_source_information_appl_0) REFERENCES public.data_source_content(hjid);


--
-- Name: sweden_county_data fk_pndlg3q6ly10qbs8e3s9wikyu; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_county_data
    ADD CONSTRAINT fk_pndlg3q6ly10qbs8e3s9wikyu FOREIGN KEY (county_regions_sweden_county_0) REFERENCES public.sweden_county_data_container(hjid);


--
-- Name: qrtz_cron_triggers fk_qrtz_cron_triggers_qrtz_triggers; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_cron_triggers
    ADD CONSTRAINT fk_qrtz_cron_triggers_qrtz_triggers FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES public.qrtz_triggers(sched_name, trigger_name, trigger_group) ON DELETE CASCADE;


--
-- Name: qrtz_simple_triggers fk_qrtz_simple_triggers_qrtz_triggers; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_simple_triggers
    ADD CONSTRAINT fk_qrtz_simple_triggers_qrtz_triggers FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES public.qrtz_triggers(sched_name, trigger_name, trigger_group) ON DELETE CASCADE;


--
-- Name: qrtz_simprop_triggers fk_qrtz_simprop_triggers_qrtz_triggers; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_simprop_triggers
    ADD CONSTRAINT fk_qrtz_simprop_triggers_qrtz_triggers FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES public.qrtz_triggers(sched_name, trigger_name, trigger_group) ON DELETE CASCADE;


--
-- Name: qrtz_triggers fk_qrtz_triggers_qrtz_job_details; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.qrtz_triggers
    ADD CONSTRAINT fk_qrtz_triggers_qrtz_job_details FOREIGN KEY (sched_name, job_name, job_group) REFERENCES public.qrtz_job_details(sched_name, job_name, job_group) ON DELETE CASCADE;


--
-- Name: document_detail_data fk_quor6wesrmk9ierjyr7ni8wch; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_detail_data
    ADD CONSTRAINT fk_quor6wesrmk9ierjyr7ni8wch FOREIGN KEY (document_detail_list_documen_0) REFERENCES public.document_detail_container(hjid);


--
-- Name: sweden_parliament_electoral__1 fk_qvgtilwwyipbrv6b2cv6fcp27; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_parliament_electoral__1
    ADD CONSTRAINT fk_qvgtilwwyipbrv6b2cv6fcp27 FOREIGN KEY (parliament_electoral_regions_0) REFERENCES public.sweden_parliament_electoral__0(hjid);


--
-- Name: document_status_container fk_r2dkprhp4xfhrcck9sf31b9xl; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_r2dkprhp4xfhrcck9sf31b9xl FOREIGN KEY (document_activity_container__0) REFERENCES public.document_activity_container(hjid);


--
-- Name: sweden_municipality_election_0 fk_r3jht5oci01uxhwaa39uxsg2t; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_municipality_election_0
    ADD CONSTRAINT fk_r3jht5oci01uxhwaa39uxsg2t FOREIGN KEY (kommunvalkrets_sweden_munici_0) REFERENCES public.sweden_municipality_data(hjid);


--
-- Name: person_data fk_rd2pmyb4gmu2vbxklh6er8ayc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.person_data
    ADD CONSTRAINT fk_rd2pmyb4gmu2vbxklh6er8ayc FOREIGN KEY (person_assignment_data_perso_0) REFERENCES public.person_assignment_data(hjid);


--
-- Name: sweden_election_type fk_rp3tjh4jmpmoxh05oo9fq9qh6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sweden_election_type
    ADD CONSTRAINT fk_rp3tjh4jmpmoxh05oo9fq9qh6 FOREIGN KEY (election_types_sweden_electi_0) REFERENCES public.sweden_election_type_contain_0(hjid);


--
-- Name: application_view fk_x8sbg6y7h0vavmun6i7h8oae; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_x8sbg6y7h0vavmun6i7h8oae FOREIGN KEY (operational_information_appl_0) REFERENCES public.operational_information_cont_0(hjid);


--
-- Name: jv_commit_property jv_commit_property_commit_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.jv_commit_property
    ADD CONSTRAINT jv_commit_property_commit_fk FOREIGN KEY (commit_fk) REFERENCES public.jv_commit(commit_pk);


--
-- Name: jv_global_id jv_global_id_owner_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.jv_global_id
    ADD CONSTRAINT jv_global_id_owner_id_fk FOREIGN KEY (owner_id_fk) REFERENCES public.jv_global_id(global_id_pk);


--
-- Name: jv_snapshot jv_snapshot_commit_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.jv_snapshot
    ADD CONSTRAINT jv_snapshot_commit_fk FOREIGN KEY (commit_fk) REFERENCES public.jv_commit(commit_pk);


--
-- Name: jv_snapshot jv_snapshot_global_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.jv_snapshot
    ADD CONSTRAINT jv_snapshot_global_id_fk FOREIGN KEY (global_id_fk) REFERENCES public.jv_global_id(global_id_pk);


--
-- PostgreSQL database dump complete
--

\unrestrict o4oMvF3Qo0AIvwVfBvJWsr6BFKO2U0crt7fGIY3cDxTck7YgqpeRhUiHjTlqKei


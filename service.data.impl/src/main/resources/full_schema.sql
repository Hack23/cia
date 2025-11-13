--
-- PostgreSQL database dump
--

\restrict JXHyzmlBHuu6ryPuaqNFxjhfuXPBREQdJmma0Xpy6XFA4y3lYSxXF65nhir1NSi

-- Dumped from database version 16.10 (Ubuntu 16.10-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.10 (Ubuntu 16.10-0ubuntu0.24.04.1)

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
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: pg_stat_statements; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;


--
-- Name: EXTENSION pg_stat_statements; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pg_stat_statements IS 'track planning and execution statistics of all SQL statements executed';


--
-- Name: pgaudit; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgaudit WITH SCHEMA public;


--
-- Name: EXTENSION pgaudit; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgaudit IS 'provides auditing functionality';


--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


--
-- Name: vector; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS vector WITH SCHEMA public;


--
-- Name: EXTENSION vector; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION vector IS 'vector data type and ivfflat and hnsw access methods';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: against_proposal_container; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.against_proposal_container (
    hjid bigint NOT NULL
);


ALTER TABLE public.against_proposal_container OWNER TO eris;

--
-- Name: COLUMN against_proposal_container.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.against_proposal_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: against_proposal_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.against_proposal_data OWNER TO eris;

--
-- Name: COLUMN against_proposal_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.against_proposal_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.header; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.against_proposal_data.header IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.number_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.against_proposal_data.number_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.parties; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.against_proposal_data.parties IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.proposal_issue_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.against_proposal_data.proposal_issue_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.proposal_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.against_proposal_data.proposal_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.against_proposal_list_agains_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.against_proposal_data.against_proposal_list_agains_0 IS 'DATA.Public GDPR.NA';


--
-- Name: agency; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.agency (
    hjid bigint NOT NULL,
    agency_name character varying(255),
    description character varying(255),
    model_object_id integer,
    model_object_version integer
);


ALTER TABLE public.agency OWNER TO eris;

--
-- Name: COLUMN agency.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.agency.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN agency.agency_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.agency.agency_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN agency.description; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.agency.description IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN agency.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.agency.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN agency.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.agency.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: application_action_event; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.application_action_event OWNER TO eris;

--
-- Name: COLUMN application_action_event.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.hjid IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.application_operation; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.application_operation IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.created_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.created_date IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.event_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.event_group IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.model_object_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.model_object_version IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.events_application_session_h_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.events_application_session_h_0 IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.session_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.session_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.page; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.page IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.page_mode; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.page_mode IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.element_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.element_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.action_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.action_name IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.user_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.user_id IS 'DATA.Sensitive GDPR.Personal_Identifier';


--
-- Name: COLUMN application_action_event.error_message; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.error_message IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.application_message; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_action_event.application_message IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: application_configuration; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.application_configuration OWNER TO eris;

--
-- Name: COLUMN application_configuration.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.hjid IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.model_object_id IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.model_object_version IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.config_title; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.config_title IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.config_description; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.config_description IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.configuration_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.configuration_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.component; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.component IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.component_title; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.component_title IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.component_description; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.component_description IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.property_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.property_id IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.property_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.property_value IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.created_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.created_date IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.updated_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_configuration.updated_date IS 'DATA.Sensitive GDPR.NA';


--
-- Name: application_session; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.application_session OWNER TO eris;

--
-- Name: COLUMN application_session.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.hjid IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.created_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.created_date IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.ip_information; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.ip_information IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.model_object_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.model_object_version IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.session_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.session_type IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.user_agent_information; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.user_agent_information IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.session_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.session_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.locale; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.locale IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.operating_system; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.operating_system IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.user_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.user_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.destroyed_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.destroyed_date IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.screen_size; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.screen_size IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.time_zone; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.application_session.time_zone IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: application_view; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.application_view OWNER TO eris;

--
-- Name: assignment_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.assignment_data OWNER TO eris;

--
-- Name: COLUMN assignment_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.assignment_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.assignment_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.detail; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.detail IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.from_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.from_date IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.intressent_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.intressent_id IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN assignment_data.order_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.order_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.org_code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.org_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.role_code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.role_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.status IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.to_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.to_date IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.assignment_list_person_assig_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_data.assignment_list_person_assig_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: assignment_element; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.assignment_element OWNER TO eris;

--
-- Name: COLUMN assignment_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.assignment_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.assignment_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.detail; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.detail IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.from_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.from_date IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.intressent_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.intressent_id IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN assignment_element.order_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.order_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.org_code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.org_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.role_code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.role_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.status IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.to_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.to_date IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.uppdrag_person_assignment_el_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.assignment_element.uppdrag_person_assignment_el_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: committee_document_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.committee_document_data OWNER TO eris;

--
-- Name: COLUMN committee_document_data.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.committee_proposal_url_xml; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.committee_proposal_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.created_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.document_status_url_www; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.document_status_url_www IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.document_status_url_xml; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.document_status_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.document_url_html; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.document_url_html IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.document_url_text; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.document_url_text IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.end_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.end_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.hangar_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.hangar_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.label; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.org; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.org IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.public_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.public_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.rm; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.rm IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.status IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.sub_title; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.sub_title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.sub_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.sub_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.temp_label; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.temp_label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.title; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_document_data.title IS 'DATA.Public GDPR.NA';


--
-- Name: committee_proposal_component_0; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.committee_proposal_component_0 (
    hjid bigint NOT NULL,
    against_proposal_container_c_0 bigint,
    committee_proposal_container_0 bigint,
    document_committee_proposal__0 character varying(255)
);


ALTER TABLE public.committee_proposal_component_0 OWNER TO eris;

--
-- Name: COLUMN committee_proposal_component_0.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_component_0.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_component_0.against_proposal_container_c_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_component_0.against_proposal_container_c_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_component_0.committee_proposal_container_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_component_0.committee_proposal_container_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_component_0.document_committee_proposal__0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_component_0.document_committee_proposal__0 IS 'DATA.Public GDPR.NA';


--
-- Name: committee_proposal_container; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.committee_proposal_container (
    hjid bigint NOT NULL
);


ALTER TABLE public.committee_proposal_container OWNER TO eris;

--
-- Name: COLUMN committee_proposal_container.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: committee_proposal_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.committee_proposal_data OWNER TO eris;

--
-- Name: COLUMN committee_proposal_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.against_proposal_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.against_proposal_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.against_proposal_parties; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.against_proposal_parties IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.ballot_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.ballot_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.ballot_summary_item; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.ballot_summary_item IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.ballot_url_xml; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.ballot_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.committee_report; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.committee_report IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.decision_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.decision_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.header; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.header IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.issue_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.issue_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.proposal; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.proposal IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.rm; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.rm IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.winner; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.winner IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.committee_proposal_list_comm_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.committee_proposal_data.committee_proposal_list_comm_0 IS 'DATA.Public GDPR.NA';


--
-- Name: countries_element; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.countries_element (
    hjid bigint NOT NULL,
    page numeric,
    pages numeric,
    per_page numeric,
    total numeric
);


ALTER TABLE public.countries_element OWNER TO eris;

--
-- Name: COLUMN countries_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.countries_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN countries_element.page; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.countries_element.page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN countries_element.pages; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.countries_element.pages IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN countries_element.per_page; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.countries_element.per_page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN countries_element.total; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.countries_element.total IS 'DATA.Public GDPR.NA';


--
-- Name: country_element; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.country_element OWNER TO eris;

--
-- Name: COLUMN country_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.adminregion_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.adminregion_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.adminregion_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.adminregion_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.capital_city; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.capital_city IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.country_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.country_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.income_level_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.income_level_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.income_level_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.income_level_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.iso_2code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.iso_2code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.latitude; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.latitude IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.lending_type_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.lending_type_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.lending_type_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.lending_type_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.longitude; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.longitude IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.region_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.region_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.region_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.region_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.country_countries_element_hj_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.country_element.country_countries_element_hj_0 IS 'DATA.Public GDPR.NA';


--
-- Name: data_element; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.data_element (
    hjid bigint NOT NULL,
    page numeric,
    pages numeric,
    per_page numeric,
    total numeric
);


ALTER TABLE public.data_element OWNER TO eris;

--
-- Name: COLUMN data_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.data_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_element.page; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.data_element.page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_element.pages; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.data_element.pages IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_element.per_page; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.data_element.per_page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_element.total; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.data_element.total IS 'DATA.Public GDPR.NA';


--
-- Name: data_source_content; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.data_source_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


ALTER TABLE public.data_source_content OWNER TO eris;

--
-- Name: COLUMN data_source_content.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.data_source_content.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_source_content.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.data_source_content.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_source_content.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.data_source_content.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO eris;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO eris;

--
-- Name: detail_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.detail_data (
    hjid bigint NOT NULL,
    code character varying(255),
    detail character varying(65536),
    detail_type character varying(255),
    intressent_id character varying(255),
    detail_list_person_detail_da_0 bigint
);


ALTER TABLE public.detail_data OWNER TO eris;

--
-- Name: COLUMN detail_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN detail_data.code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_data.code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN detail_data.detail; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_data.detail IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN detail_data.detail_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_data.detail_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN detail_data.intressent_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_data.intressent_id IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN detail_data.detail_list_person_detail_da_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_data.detail_list_person_detail_da_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: detail_element; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.detail_element (
    hjid bigint NOT NULL,
    code character varying(255),
    detail character varying(65536),
    detail_type character varying(255),
    intressent_id character varying(255)
);


ALTER TABLE public.detail_element OWNER TO eris;

--
-- Name: COLUMN detail_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_element.hjid IS 'DATA.Public GDPR.PERSONAL';


--
-- Name: COLUMN detail_element.code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_element.code IS 'DATA.Public GDPR.PERSONAL';


--
-- Name: COLUMN detail_element.detail; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_element.detail IS 'DATA.Public GDPR.PERSONAL';


--
-- Name: COLUMN detail_element.detail_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_element.detail_type IS 'DATA.Public GDPR.PERSONAL';


--
-- Name: COLUMN detail_element.intressent_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.detail_element.intressent_id IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: document_activity_container; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_activity_container (
    hjid bigint NOT NULL
);


ALTER TABLE public.document_activity_container OWNER TO eris;

--
-- Name: COLUMN document_activity_container.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_activity_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: document_activity_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.document_activity_data OWNER TO eris;

--
-- Name: COLUMN document_activity_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_activity_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.activity_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_activity_data.activity_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_activity_data.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.created_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_activity_data.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.order_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_activity_data.order_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.process; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_activity_data.process IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_activity_data.status IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.document_activities_document_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_activity_data.document_activities_document_0 IS 'DATA.Public GDPR.NA';


--
-- Name: document_attachment; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_attachment (
    hjid bigint NOT NULL,
    file_name character varying(255),
    file_size numeric,
    file_type character varying(255),
    file_url character varying(255),
    document_attachment_list_doc_0 bigint
);


ALTER TABLE public.document_attachment OWNER TO eris;

--
-- Name: COLUMN document_attachment.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_attachment.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.file_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_attachment.file_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.file_size; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_attachment.file_size IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.file_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_attachment.file_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.file_url; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_attachment.file_url IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.document_attachment_list_doc_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_attachment.document_attachment_list_doc_0 IS 'DATA.Public GDPR.NA';


--
-- Name: document_attachment_container; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_attachment_container (
    hjid bigint NOT NULL
);


ALTER TABLE public.document_attachment_container OWNER TO eris;

--
-- Name: COLUMN document_attachment_container.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_attachment_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: document_container_element; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.document_container_element OWNER TO eris;

--
-- Name: COLUMN document_container_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.created; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.created IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.datum; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.datum IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.debug; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.debug IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.document_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.document_version IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.hits; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.hits IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.hits_from; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.hits_from IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.hits_to; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.hits_to IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.next_page; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.next_page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.page; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.total_pages; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.total_pages IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.warning; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_container_element.warning IS 'DATA.Public GDPR.NA';


--
-- Name: document_content_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_content_data (
    hjid bigint NOT NULL,
    content text,
    id character varying(255)
);


ALTER TABLE public.document_content_data OWNER TO eris;

--
-- Name: COLUMN document_content_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_content_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_content_data.content; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_content_data.content IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_content_data.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_content_data.id IS 'DATA.Public GDPR.NA';


--
-- Name: document_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.document_data OWNER TO eris;

--
-- Name: COLUMN document_data.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.committee_report_url_xml; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.committee_report_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_status_url_www; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.document_status_url_www IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_status_url_xml; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.document_status_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.document_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_url_html; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.document_url_html IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_url_text; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.document_url_text IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.final_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.final_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.hangar_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.hangar_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.label; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.made_public_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.made_public_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.number_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.number_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.org; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.org IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.rm; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.rm IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.status IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.sub_title; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.sub_title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.sub_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.sub_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.temp_label; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.temp_label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.title; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_data.title IS 'DATA.Public GDPR.NA';


--
-- Name: document_detail_container; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_detail_container (
    hjid bigint NOT NULL
);


ALTER TABLE public.document_detail_container OWNER TO eris;

--
-- Name: COLUMN document_detail_container.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_detail_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: document_detail_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_detail_data (
    hjid bigint NOT NULL,
    code character varying(255),
    detail_name character varying(255),
    text character varying(10485760),
    document_detail_list_documen_0 bigint
);


ALTER TABLE public.document_detail_data OWNER TO eris;

--
-- Name: COLUMN document_detail_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_detail_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_detail_data.code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_detail_data.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_detail_data.detail_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_detail_data.detail_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_detail_data.text; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_detail_data.text IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_detail_data.document_detail_list_documen_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_detail_data.document_detail_list_documen_0 IS 'DATA.Public GDPR.NA';


--
-- Name: document_element; Type: TABLE; Schema: public; Owner: eris
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
    summary character varying(8192),
    note character varying(8192),
    note_title character varying(8192),
    debate_name character varying(255),
    document_name character varying(255),
    doc_type character varying(255)
);


ALTER TABLE public.document_element OWNER TO eris;

--
-- Name: COLUMN document_element.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.committee_report_url_xml; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.committee_report_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.created_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_format; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.document_format IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_status_url_xml; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.document_status_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.document_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_url_html; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.document_url_html IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_url_text; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.document_url_text IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.hit; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.hit IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.kall_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.kall_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.label; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.made_public_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.made_public_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.number_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.number_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.org; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.org IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.related_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.related_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.rm; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.rm IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.status IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.sub_title; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.sub_title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.sub_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.sub_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.system_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.system_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.temp_label; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.temp_label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.title; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.dokument_document_container__0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.dokument_document_container__0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.domain_org; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.domain_org IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.database_source; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.database_source IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.origin; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.origin IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.lang; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.lang IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.summary; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.summary IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.note; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.note IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.note_title; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.note_title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.debate_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.debate_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.document_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.doc_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_element.doc_type IS 'DATA.Public GDPR.NA';


--
-- Name: document_person_reference_co_0; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_person_reference_co_0 (
    hjid bigint NOT NULL
);


ALTER TABLE public.document_person_reference_co_0 OWNER TO eris;

--
-- Name: COLUMN document_person_reference_co_0.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_person_reference_co_0.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: document_person_reference_da_0; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.document_person_reference_da_0 OWNER TO eris;

--
-- Name: COLUMN document_person_reference_da_0.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_person_reference_da_0.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.order_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_person_reference_da_0.order_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.party_short_code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_person_reference_da_0.party_short_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.person_reference_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_person_reference_da_0.person_reference_id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.reference_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_person_reference_da_0.reference_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.role_description; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_person_reference_da_0.role_description IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.document_person_reference_li_1; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_person_reference_da_0.document_person_reference_li_1 IS 'DATA.Public GDPR.Personal';


--
-- Name: document_proposal_container; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_proposal_container (
    hjid bigint NOT NULL,
    proposal_document_proposal_c_0 bigint
);


ALTER TABLE public.document_proposal_container OWNER TO eris;

--
-- Name: COLUMN document_proposal_container.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_container.proposal_document_proposal_c_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_container.proposal_document_proposal_c_0 IS 'DATA.Public GDPR.NA';


--
-- Name: document_proposal_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.document_proposal_data OWNER TO eris;

--
-- Name: COLUMN document_proposal_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.chamber; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.chamber IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.committee; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.committee IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.decision_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.decision_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.designation; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.designation IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.processed_in; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.processed_in IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.proposal_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.proposal_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.wording; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.wording IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.wording_2; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.wording_2 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.wording_3; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.wording_3 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.wording_4; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_proposal_data.wording_4 IS 'DATA.Public GDPR.NA';


--
-- Name: document_reference_container; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_reference_container (
    hjid bigint NOT NULL
);


ALTER TABLE public.document_reference_container OWNER TO eris;

--
-- Name: COLUMN document_reference_container.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_reference_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: document_reference_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.document_reference_data (
    hjid bigint NOT NULL,
    detail character varying(65536),
    reference_document_id character varying(255),
    reference_type character varying(255),
    document_reference_list_docu_0 bigint
);


ALTER TABLE public.document_reference_data OWNER TO eris;

--
-- Name: COLUMN document_reference_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_reference_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_reference_data.detail; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_reference_data.detail IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_reference_data.reference_document_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_reference_data.reference_document_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_reference_data.reference_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_reference_data.reference_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_reference_data.document_reference_list_docu_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_reference_data.document_reference_list_docu_0 IS 'DATA.Public GDPR.NA';


--
-- Name: document_status_container; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.document_status_container OWNER TO eris;

--
-- Name: COLUMN document_status_container.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_status_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_category; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_status_container.document_category IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_document_status_con_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_status_container.document_document_status_con_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_activity_container__0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_status_container.document_activity_container__0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_attachment_containe_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_status_container.document_attachment_containe_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_detail_container_do_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_status_container.document_detail_container_do_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_person_reference_co_1; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_status_container.document_person_reference_co_1 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_reference_container_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_status_container.document_reference_container_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_proposal_document_s_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.document_status_container.document_proposal_document_s_0 IS 'DATA.Public GDPR.NA';


--
-- Name: domain_portal; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.domain_portal (
    domain_name character varying(255),
    hjid bigint NOT NULL
);


ALTER TABLE public.domain_portal OWNER TO eris;

--
-- Name: COLUMN domain_portal.domain_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.domain_portal.domain_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN domain_portal.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.domain_portal.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: encrypted_value; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.encrypted_value (
    id bigint NOT NULL,
    storage character varying(4096),
    user_id character varying(255),
    vault_name character varying(255)
);


ALTER TABLE public.encrypted_value OWNER TO eris;

--
-- Name: COLUMN encrypted_value.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.encrypted_value.id IS 'DATA.Confidential GDPR.Personal_Sensitive';


--
-- Name: COLUMN encrypted_value.storage; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.encrypted_value.storage IS 'DATA.Confidential GDPR.Personal_Sensitive';


--
-- Name: COLUMN encrypted_value.user_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.encrypted_value.user_id IS 'DATA.Sensitive GDPR.Personal_Identifier';


--
-- Name: COLUMN encrypted_value.vault_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.encrypted_value.vault_name IS 'DATA.Sensitive GDPR.na';


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: eris
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.hibernate_sequence OWNER TO eris;

--
-- Name: indicator_element; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.indicator_element OWNER TO eris;

--
-- Name: COLUMN indicator_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicator_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicator_element.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.indicator_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicator_element.indicator_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.source_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicator_element.source_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.source_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicator_element.source_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.source_note; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicator_element.source_note IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.source_organization; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicator_element.source_organization IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.topics_indicator_element_hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicator_element.topics_indicator_element_hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.indicator__indicators_elemen_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicator_element.indicator__indicators_elemen_0 IS 'DATA.Public GDPR.NA';


--
-- Name: indicators_element; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.indicators_element (
    hjid bigint NOT NULL,
    page numeric,
    pages numeric,
    per_page numeric,
    total numeric
);


ALTER TABLE public.indicators_element OWNER TO eris;

--
-- Name: COLUMN indicators_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicators_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicators_element.page; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicators_element.page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicators_element.pages; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicators_element.pages IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicators_element.per_page; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicators_element.per_page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicators_element.total; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.indicators_element.total IS 'DATA.Public GDPR.NA';


--
-- Name: jv_commit; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.jv_commit (
    commit_pk bigint NOT NULL,
    author character varying(200),
    commit_date timestamp without time zone,
    commit_id numeric(22,2),
    commit_date_instant character varying(200) DEFAULT '2000-01-01T12:00:00.001Z'::character varying
);


ALTER TABLE public.jv_commit OWNER TO eris;

--
-- Name: jv_commit_pk_seq; Type: SEQUENCE; Schema: public; Owner: eris
--

CREATE SEQUENCE public.jv_commit_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jv_commit_pk_seq OWNER TO eris;

--
-- Name: jv_commit_property; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.jv_commit_property (
    property_name character varying(191) NOT NULL,
    property_value character varying(600),
    commit_fk bigint NOT NULL
);


ALTER TABLE public.jv_commit_property OWNER TO eris;

--
-- Name: jv_global_id; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.jv_global_id (
    global_id_pk bigint NOT NULL,
    local_id character varying(191),
    fragment character varying(200),
    type_name character varying(200),
    owner_id_fk bigint
);


ALTER TABLE public.jv_global_id OWNER TO eris;

--
-- Name: jv_global_id_pk_seq; Type: SEQUENCE; Schema: public; Owner: eris
--

CREATE SEQUENCE public.jv_global_id_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jv_global_id_pk_seq OWNER TO eris;

--
-- Name: jv_snapshot; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.jv_snapshot OWNER TO eris;

--
-- Name: jv_snapshot_pk_seq; Type: SEQUENCE; Schema: public; Owner: eris
--

CREATE SEQUENCE public.jv_snapshot_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jv_snapshot_pk_seq OWNER TO eris;

--
-- Name: language_content_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.language_content_data OWNER TO eris;

--
-- Name: COLUMN language_content_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.created_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.from_language; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.from_language IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.language_content_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.language_content_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.language_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.language_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.ref_key; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.ref_key IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.to_language; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.to_language IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.last_modified_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.last_modified_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.key_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.key_group IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.location_context; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_content_data.location_context IS 'DATA.Public GDPR.NA';


--
-- Name: language_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.language_data OWNER TO eris;

--
-- Name: COLUMN language_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.language_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.language_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.created_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.last_modified_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.last_modified_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.language_enabled; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.language_enabled IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.auto_translation_enabled; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.auto_translation_enabled IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.language_code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.language_code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.translation_status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.language_data.translation_status IS 'DATA.Public GDPR.NA';


--
-- Name: operational_information_cont_0; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.operational_information_cont_0 (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


ALTER TABLE public.operational_information_cont_0 OWNER TO eris;

--
-- Name: COLUMN operational_information_cont_0.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.operational_information_cont_0.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN operational_information_cont_0.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.operational_information_cont_0.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN operational_information_cont_0.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.operational_information_cont_0.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: performance_indicator_content; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.performance_indicator_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


ALTER TABLE public.performance_indicator_content OWNER TO eris;

--
-- Name: COLUMN performance_indicator_content.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.performance_indicator_content.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN performance_indicator_content.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.performance_indicator_content.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN performance_indicator_content.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.performance_indicator_content.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: person_assignment_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.person_assignment_data (
    hjid bigint NOT NULL
);


ALTER TABLE public.person_assignment_data OWNER TO eris;

--
-- Name: COLUMN person_assignment_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_assignment_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: person_assignment_element; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.person_assignment_element (
    hjid bigint NOT NULL
);


ALTER TABLE public.person_assignment_element OWNER TO eris;

--
-- Name: COLUMN person_assignment_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_assignment_element.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: person_container_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.person_container_data (
    hjid bigint NOT NULL,
    person_person_container_data_0 character varying(255)
);


ALTER TABLE public.person_container_data OWNER TO eris;

--
-- Name: COLUMN person_container_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_container_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_container_data.person_person_container_data_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_container_data.person_person_container_data_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: person_container_element; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.person_container_element (
    hjid bigint NOT NULL
);


ALTER TABLE public.person_container_element OWNER TO eris;

--
-- Name: COLUMN person_container_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_container_element.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: person_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.person_data OWNER TO eris;

--
-- Name: COLUMN person_data.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.born_year; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.born_year IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.election_region; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.election_region IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.first_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.first_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.gender; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.gender IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.hangar_guid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.hangar_guid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.image_url_192; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.image_url_192 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.image_url_80; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.image_url_80 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.image_url_max; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.image_url_max IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.last_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.last_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.party; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.party IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.person_url_xml; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.person_url_xml IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.place; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.place IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.status IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.person_assignment_data_perso_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.person_assignment_data_perso_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.person_detail_data_person_da_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_data.person_detail_data_person_da_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: person_detail_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.person_detail_data (
    hjid bigint NOT NULL
);


ALTER TABLE public.person_detail_data OWNER TO eris;

--
-- Name: COLUMN person_detail_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_detail_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: person_detail_element; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.person_detail_element (
    hjid bigint NOT NULL,
    detail_list_person_detail_el_0 bigint
);


ALTER TABLE public.person_detail_element OWNER TO eris;

--
-- Name: COLUMN person_detail_element.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_detail_element.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_detail_element.detail_list_person_detail_el_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_detail_element.detail_list_person_detail_el_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: person_element; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.person_element OWNER TO eris;

--
-- Name: COLUMN person_element.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.born_year; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.born_year IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.election_region; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.election_region IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.first_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.first_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.gender; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.gender IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.hangar_guid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.hangar_guid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.image_url_192; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.image_url_192 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.image_url_80; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.image_url_80 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.image_url_max; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.image_url_max IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.last_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.last_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.party; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.party IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.person_url_xml; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.person_url_xml IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.place; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.place IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.status IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.person_assignment_element_pe_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.person_assignment_element_pe_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.person_detail_element_person_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.person_detail_element_person_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.person_person_container_elem_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.person_element.person_person_container_elem_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: portal; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.portal OWNER TO eris;

--
-- Name: COLUMN portal.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.portal.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.description; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.portal.description IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.google_map_api_key; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.portal.google_map_api_key IS 'DATA.Confidential GDPR.NA';


--
-- Name: COLUMN portal.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.portal.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.portal.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.portal_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.portal.portal_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.portal_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.portal.portal_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.portals_agency_hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.portal.portals_agency_hjid IS 'DATA.Public GDPR.NA';


--
-- Name: qrtz_blob_triggers; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.qrtz_blob_triggers (
    sched_name character varying(100) NOT NULL,
    trigger_name character varying(150) NOT NULL,
    trigger_group character varying(150) NOT NULL,
    job_data bytea
);


ALTER TABLE public.qrtz_blob_triggers OWNER TO eris;

--
-- Name: COLUMN qrtz_blob_triggers.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_blob_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_blob_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_blob_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_blob_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_blob_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_blob_triggers.job_data; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_blob_triggers.job_data IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_calendars; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.qrtz_calendars (
    sched_name character varying(100) NOT NULL,
    calendar_name character varying(200) NOT NULL,
    calendar bytea NOT NULL
);


ALTER TABLE public.qrtz_calendars OWNER TO eris;

--
-- Name: COLUMN qrtz_calendars.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_calendars.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_calendars.calendar_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_calendars.calendar_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_calendars.calendar; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_calendars.calendar IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_cron_triggers; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.qrtz_cron_triggers (
    sched_name character varying(100) NOT NULL,
    trigger_name character varying(150) NOT NULL,
    trigger_group character varying(150) NOT NULL,
    cron_expression character varying(120) NOT NULL,
    time_zone_id character varying(80)
);


ALTER TABLE public.qrtz_cron_triggers OWNER TO eris;

--
-- Name: COLUMN qrtz_cron_triggers.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_cron_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_cron_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_cron_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_cron_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_cron_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_cron_triggers.cron_expression; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_cron_triggers.cron_expression IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_cron_triggers.time_zone_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_cron_triggers.time_zone_id IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_fired_triggers; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.qrtz_fired_triggers OWNER TO eris;

--
-- Name: COLUMN qrtz_fired_triggers.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.entry_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.entry_id IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.instance_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.instance_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.fired_time; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.fired_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.sched_time; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.sched_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.priority; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.priority IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.state; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.state IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.job_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.job_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.job_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.job_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.is_nonconcurrent; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.is_nonconcurrent IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.requests_recovery; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_fired_triggers.requests_recovery IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_job_details; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.qrtz_job_details OWNER TO eris;

--
-- Name: COLUMN qrtz_job_details.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.job_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.job_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.job_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.job_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.description; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.description IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.job_class_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.job_class_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.is_durable; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.is_durable IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.is_nonconcurrent; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.is_nonconcurrent IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.is_update_data; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.is_update_data IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.requests_recovery; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.requests_recovery IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.job_data; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_job_details.job_data IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_locks; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.qrtz_locks (
    sched_name character varying(100) NOT NULL,
    lock_name character varying(40) NOT NULL
);


ALTER TABLE public.qrtz_locks OWNER TO eris;

--
-- Name: COLUMN qrtz_locks.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_locks.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_locks.lock_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_locks.lock_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_paused_trigger_grps; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.qrtz_paused_trigger_grps (
    sched_name character varying(100) NOT NULL,
    trigger_group character varying(200) NOT NULL
);


ALTER TABLE public.qrtz_paused_trigger_grps OWNER TO eris;

--
-- Name: COLUMN qrtz_paused_trigger_grps.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_paused_trigger_grps.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_paused_trigger_grps.trigger_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_paused_trigger_grps.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_scheduler_state; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.qrtz_scheduler_state (
    sched_name character varying(100) NOT NULL,
    instance_name character varying(200) NOT NULL,
    last_checkin_time bigint NOT NULL,
    checkin_interval bigint NOT NULL
);


ALTER TABLE public.qrtz_scheduler_state OWNER TO eris;

--
-- Name: COLUMN qrtz_scheduler_state.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_scheduler_state.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_scheduler_state.instance_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_scheduler_state.instance_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_scheduler_state.last_checkin_time; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_scheduler_state.last_checkin_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_scheduler_state.checkin_interval; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_scheduler_state.checkin_interval IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_simple_triggers; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.qrtz_simple_triggers (
    sched_name character varying(100) NOT NULL,
    trigger_name character varying(150) NOT NULL,
    trigger_group character varying(150) NOT NULL,
    repeat_count bigint NOT NULL,
    repeat_interval bigint NOT NULL,
    times_triggered bigint NOT NULL
);


ALTER TABLE public.qrtz_simple_triggers OWNER TO eris;

--
-- Name: COLUMN qrtz_simple_triggers.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simple_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simple_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simple_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.repeat_count; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simple_triggers.repeat_count IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.repeat_interval; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simple_triggers.repeat_interval IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.times_triggered; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simple_triggers.times_triggered IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_simprop_triggers; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.qrtz_simprop_triggers OWNER TO eris;

--
-- Name: COLUMN qrtz_simprop_triggers.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.str_prop_1; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.str_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.str_prop_2; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.str_prop_2 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.str_prop_3; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.str_prop_3 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.int_prop_1; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.int_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.int_prop_2; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.int_prop_2 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.long_prop_1; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.long_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.long_prop_2; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.long_prop_2 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.dec_prop_1; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.dec_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.dec_prop_2; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.dec_prop_2 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.bool_prop_1; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.bool_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.bool_prop_2; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.bool_prop_2 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_triggers; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.qrtz_triggers OWNER TO eris;

--
-- Name: COLUMN qrtz_triggers.sched_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.job_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.job_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.job_group; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.job_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.description; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.description IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.next_fire_time; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.next_fire_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.prev_fire_time; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.prev_fire_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.priority; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.priority IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.trigger_state; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.trigger_state IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.trigger_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.trigger_type IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.start_time; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.start_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.end_time; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.end_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.calendar_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.calendar_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.misfire_instr; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.misfire_instr IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.job_data; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.qrtz_triggers.job_data IS 'DATA.Sensitive GDPR.NA';


--
-- Name: quality_assurance_content; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.quality_assurance_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


ALTER TABLE public.quality_assurance_content OWNER TO eris;

--
-- Name: COLUMN quality_assurance_content.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.quality_assurance_content.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN quality_assurance_content.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.quality_assurance_content.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN quality_assurance_content.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.quality_assurance_content.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: rule_violation; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.rule_violation OWNER TO eris;

--
-- Name: sweden_county_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.sweden_county_data (
    hjid bigint NOT NULL,
    code numeric,
    county_name character varying(255),
    county_regions_sweden_county_0 bigint
);


ALTER TABLE public.sweden_county_data OWNER TO eris;

--
-- Name: COLUMN sweden_county_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_data.code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_data.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_data.county_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_data.county_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_data.county_regions_sweden_county_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_data.county_regions_sweden_county_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_county_data_container; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.sweden_county_data_container (
    hjid bigint NOT NULL
);


ALTER TABLE public.sweden_county_data_container OWNER TO eris;

--
-- Name: COLUMN sweden_county_data_container.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_data_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_county_electoral_area; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.sweden_county_electoral_area OWNER TO eris;

--
-- Name: COLUMN sweden_county_electoral_area.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_area.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_area.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.electoral_area_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_area.electoral_area_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.first_round; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_area.first_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.number_of_seats; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_area.number_of_seats IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.number_of_voters; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_area.number_of_voters IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.rest; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_area.rest IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.second_round; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_area.second_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.landstingsvalkrets_sweden_co_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_area.landstingsvalkrets_sweden_co_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_county_electoral_regi_0; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.sweden_county_electoral_regi_0 (
    hjid bigint NOT NULL
);


ALTER TABLE public.sweden_county_electoral_regi_0 OWNER TO eris;

--
-- Name: COLUMN sweden_county_electoral_regi_0.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_0.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_county_electoral_regi_1; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.sweden_county_electoral_regi_1 (
    hjid bigint NOT NULL,
    code numeric,
    county_name character varying(255),
    seats numeric,
    county_electoral_regions_swe_0 bigint
);


ALTER TABLE public.sweden_county_electoral_regi_1 OWNER TO eris;

--
-- Name: COLUMN sweden_county_electoral_regi_1.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_regi_1.code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_regi_1.county_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.county_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_regi_1.seats; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.seats IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_regi_1.county_electoral_regions_swe_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.county_electoral_regions_swe_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_election_region; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.sweden_election_region (
    hjid bigint NOT NULL,
    county_id character varying(255),
    municipal_id character varying(255),
    region_name character varying(255)
);


ALTER TABLE public.sweden_election_region OWNER TO eris;

--
-- Name: COLUMN sweden_election_region.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_region.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_region.county_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_region.county_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_region.municipal_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_region.municipal_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_region.region_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_region.region_name IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_election_type; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.sweden_election_type (
    hjid bigint NOT NULL,
    election_code character varying(255),
    election_type character varying(255),
    region_sweden_election_type__0 bigint,
    election_types_sweden_electi_0 bigint
);


ALTER TABLE public.sweden_election_type OWNER TO eris;

--
-- Name: COLUMN sweden_election_type.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_type.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_type.election_code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_type.election_code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_type.election_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_type.election_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_type.region_sweden_election_type__0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_type.region_sweden_election_type__0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_type.election_types_sweden_electi_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_type.election_types_sweden_electi_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_election_type_contain_0; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.sweden_election_type_contain_0 (
    hjid bigint NOT NULL
);


ALTER TABLE public.sweden_election_type_contain_0 OWNER TO eris;

--
-- Name: COLUMN sweden_election_type_contain_0.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_election_type_contain_0.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_municipality_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.sweden_municipality_data (
    hjid bigint NOT NULL,
    code numeric,
    municipal_name character varying(255),
    kommun_sweden_county_data_hj_0 bigint
);


ALTER TABLE public.sweden_municipality_data OWNER TO eris;

--
-- Name: COLUMN sweden_municipality_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_data.code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_data.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_data.municipal_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_data.municipal_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_data.kommun_sweden_county_data_hj_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_data.kommun_sweden_county_data_hj_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_municipality_election_0; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.sweden_municipality_election_0 OWNER TO eris;

--
-- Name: COLUMN sweden_municipality_election_0.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_election_0.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_election_0.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.election_region_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_election_0.election_region_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.first_round; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_election_0.first_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.number_of_seats; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_election_0.number_of_seats IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.number_of_voters; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_election_0.number_of_voters IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.rest; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_election_0.rest IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.second_round; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_election_0.second_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.kommunvalkrets_sweden_munici_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_municipality_election_0.kommunvalkrets_sweden_munici_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_parliament_electoral__0; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.sweden_parliament_electoral__0 (
    hjid bigint NOT NULL
);


ALTER TABLE public.sweden_parliament_electoral__0 OWNER TO eris;

--
-- Name: COLUMN sweden_parliament_electoral__0.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_parliament_electoral__0.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_parliament_electoral__1; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.sweden_parliament_electoral__1 OWNER TO eris;

--
-- Name: COLUMN sweden_parliament_electoral__1.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.election_region_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.election_region_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.first_round; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.first_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.number_of_seats; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.number_of_seats IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.number_of_voters; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.number_of_voters IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.rest; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.rest IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.second_round; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.second_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.parliament_electoral_regions_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.parliament_electoral_regions_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_political_party; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.sweden_political_party OWNER TO eris;

--
-- Name: COLUMN sweden_political_party.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.address; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.address IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.city; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.city IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.co_address; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.co_address IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.email; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.email IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.fax_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.fax_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.party_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.party_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.party_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.party_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.phone_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.phone_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.post_code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.post_code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.registered_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.registered_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.short_code; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.short_code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.website; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.website IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.parties_sweden_election_regi_0; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.sweden_political_party.parties_sweden_election_regi_0 IS 'DATA.Public GDPR.NA';


--
-- Name: target_profile_content; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.target_profile_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


ALTER TABLE public.target_profile_content OWNER TO eris;

--
-- Name: COLUMN target_profile_content.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.target_profile_content.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN target_profile_content.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.target_profile_content.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN target_profile_content.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.target_profile_content.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: topic; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.topic (
    hjid bigint NOT NULL,
    id character varying(255),
    value_ character varying(255),
    topic_topics_hjid bigint
);


ALTER TABLE public.topic OWNER TO eris;

--
-- Name: COLUMN topic.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.topic.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN topic.id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.topic.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN topic.value_; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.topic.value_ IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN topic.topic_topics_hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.topic.topic_topics_hjid IS 'DATA.Public GDPR.NA';


--
-- Name: topics; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.topics (
    hjid bigint NOT NULL
);


ALTER TABLE public.topics OWNER TO eris;

--
-- Name: COLUMN topics.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.topics.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: user_account; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.user_account OWNER TO eris;

--
-- Name: COLUMN user_account.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.hjid IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.email; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.email IS 'DATA.Sensitive GDPR.Personal_Identifier';


--
-- Name: COLUMN user_account.model_object_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.model_object_id IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.model_object_version; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.model_object_version IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.number_of_visits; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.number_of_visits IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.user_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.user_id IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.user_role; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.user_role IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.user_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.user_type IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.username; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.username IS 'DATA.Sensitive GDPR.Personal_Identifier';


--
-- Name: COLUMN user_account.userpassword; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.userpassword IS 'DATA.Confidential GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.country; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.country IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN user_account.created_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.created_date IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN user_account.user_lock_status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.user_lock_status IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.user_email_status; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account.user_email_status IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: user_account_address; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.user_account_address (
    hjid bigint NOT NULL,
    hjvalue character varying(255),
    hjindex integer NOT NULL
);


ALTER TABLE public.user_account_address OWNER TO eris;

--
-- Name: COLUMN user_account_address.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account_address.hjid IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN user_account_address.hjvalue; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account_address.hjvalue IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN user_account_address.hjindex; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.user_account_address.hjindex IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: view_application_action_event_page_annual_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_annual_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_daily_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_daily_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_element_annual_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_element_annual_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_element_daily_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_element_daily_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_element_hourly_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_element_hourly_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_element_weekly_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_element_weekly_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_hourly_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_hourly_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_modes_annual_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_modes_annual_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_modes_daily_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_modes_daily_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_modes_hourly_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_modes_hourly_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_modes_weekly_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_modes_weekly_summary OWNER TO eris;

--
-- Name: view_application_action_event_page_weekly_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_application_action_event_page_weekly_summary OWNER TO eris;

--
-- Name: view_audit_author_summary; Type: VIEW; Schema: public; Owner: eris
--

CREATE VIEW public.view_audit_author_summary AS
 SELECT min(commit_pk) AS id,
    author,
    count(*) AS changes,
    min(commit_date) AS first_date,
    max(commit_date) AS last_date
   FROM public.jv_commit
  GROUP BY author;


ALTER VIEW public.view_audit_author_summary OWNER TO eris;

--
-- Name: view_audit_data_summary; Type: VIEW; Schema: public; Owner: eris
--

CREATE VIEW public.view_audit_data_summary AS
 SELECT row_number() OVER (ORDER BY relname) AS id,
    relname AS data_type,
    n_live_tup AS data_size
   FROM pg_stat_user_tables
  ORDER BY relname;


ALTER VIEW public.view_audit_data_summary OWNER TO eris;

--
-- Name: view_document_data_committee_report_url; Type: VIEW; Schema: public; Owner: eris
--

CREATE VIEW public.view_document_data_committee_report_url AS
 SELECT id,
    committee_report_url_xml
   FROM public.document_data
  WHERE (committee_report_url_xml IS NOT NULL);


ALTER VIEW public.view_document_data_committee_report_url OWNER TO eris;

--
-- Name: view_riksdagen_politician_document; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_politician_document OWNER TO eris;

--
-- Name: view_riksdagen_committee; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_committee OWNER TO eris;

--
-- Name: view_riksdagen_committee_decisions; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_committee_decisions OWNER TO eris;

--
-- Name: vote_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.vote_data OWNER TO eris;

--
-- Name: COLUMN vote_data.embedded_id_ballot_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.embedded_id_ballot_id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.embedded_id_concern; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.embedded_id_concern IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.embedded_id_intressent_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.embedded_id_intressent_id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.embedded_id_issue; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.embedded_id_issue IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.ballot_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.ballot_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.bank_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.bank_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.born_year; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.born_year IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.electoral_region; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.electoral_region IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.electoral_region_number; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.electoral_region_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.first_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.first_name IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.full_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.full_name IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.gender; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.gender IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.label; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.label IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.last_name; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.last_name IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.party; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.party IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.place; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.place IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.rm; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.rm IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.vote; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.vote IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.vote_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_data.vote_date IS 'DATA.Public GDPR.Personal';


--
-- Name: view_riksdagen_vote_data_ballot_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary OWNER TO eris;

--
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary IS 'Contains aggregated voting data per ballot. Updated through refresh_riksdagen_views()';


--
-- Name: view_riksdagen_vote_data_ballot_party_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary OWNER TO eris;

--
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary IS 'Contains party-level voting statistics and analyses. Updated through refresh_riksdagen_views()';


--
-- Name: view_riksdagen_committee_ballot_decision_party_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_committee_ballot_decision_party_summary OWNER TO eris;

--
-- Name: view_riksdagen_vote_data_ballot_politician_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary OWNER TO eris;

--
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary IS 'Contains individual politician voting patterns and analysis, including party alignment and rebel votes';


--
-- Name: view_riksdagen_committee_ballot_decision_politician_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_committee_ballot_decision_politician_summary OWNER TO eris;

--
-- Name: view_riksdagen_committee_ballot_decision_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_committee_ballot_decision_summary OWNER TO eris;

--
-- Name: view_riksdagen_committee_decision_type_org_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_committee_decision_type_org_summary OWNER TO eris;

--
-- Name: view_riksdagen_committee_decision_type_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_committee_decision_type_summary OWNER TO eris;

--
-- Name: view_riksdagen_committee_parliament_member_proposal; Type: VIEW; Schema: public; Owner: eris
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
  WHERE ((document_data.document_type)::text = 'MOT'::text);


ALTER VIEW public.view_riksdagen_committee_parliament_member_proposal OWNER TO eris;

--
-- Name: view_riksdagen_committee_role_member; Type: VIEW; Schema: public; Owner: eris
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
                    WHEN ((view_riksdagen_politician_document.document_type)::text = ANY ((ARRAY['mot'::character varying, 'prop'::character varying, 'frs'::character varying])::text[])) THEN 1
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


ALTER VIEW public.view_riksdagen_committee_role_member OWNER TO eris;

--
-- Name: view_riksdagen_committee_roles; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_committee_roles OWNER TO eris;

--
-- Name: view_riksdagen_document_type_daily_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
--

CREATE MATERIALIZED VIEW public.view_riksdagen_document_type_daily_summary AS
 SELECT "left"((made_public_date)::text, 10) AS embedded_id_public_date,
    document_type AS embedded_id_document_type,
    count(*) AS total
   FROM public.document_element
  GROUP BY ("left"((made_public_date)::text, 10)), document_type
  WITH NO DATA;


ALTER MATERIALIZED VIEW public.view_riksdagen_document_type_daily_summary OWNER TO eris;

--
-- Name: view_riksdagen_goverment; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_goverment OWNER TO eris;

--
-- Name: view_riksdagen_goverment_proposals; Type: VIEW; Schema: public; Owner: eris
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
  WHERE ((document_type)::text = 'PROP'::text);


ALTER VIEW public.view_riksdagen_goverment_proposals OWNER TO eris;

--
-- Name: view_riksdagen_goverment_role_member; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_goverment_role_member OWNER TO eris;

--
-- Name: view_riksdagen_goverment_roles; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_goverment_roles OWNER TO eris;

--
-- Name: view_riksdagen_member_proposals; Type: VIEW; Schema: public; Owner: eris
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
  WHERE ((document_type)::text = 'MOT'::text);


ALTER VIEW public.view_riksdagen_member_proposals OWNER TO eris;

--
-- Name: view_riksdagen_org_document_daily_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
--

CREATE MATERIALIZED VIEW public.view_riksdagen_org_document_daily_summary AS
 SELECT "left"((made_public_date)::text, 10) AS embedded_id_public_date,
    org AS embedded_id_org,
    document_type,
    count(*) AS total
   FROM public.document_element
  GROUP BY ("left"((made_public_date)::text, 10)), document_type, org
  WITH NO DATA;


ALTER MATERIALIZED VIEW public.view_riksdagen_org_document_daily_summary OWNER TO eris;

--
-- Name: view_riksdagen_politician_document_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_politician_document_summary OWNER TO eris;

--
-- Name: view_riksdagen_party_member; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_party_member OWNER TO eris;

--
-- Name: view_riksdagen_party; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_party OWNER TO eris;

--
-- Name: view_riksdagen_party_ballot_support_annual_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_party_ballot_support_annual_summary OWNER TO eris;

--
-- Name: view_riksdagen_party_coalation_against_annual_summary; Type: VIEW; Schema: public; Owner: eris
--

CREATE VIEW public.view_riksdagen_party_coalation_against_annual_summary AS
 SELECT quote_literal(upper(replace(replace((against_proposal_parties)::text, ' '::text, ''::text), '"'::text, ''::text))) AS embedded_id_group_against,
    SUBSTRING(rm FROM 1 FOR 4) AS embedded_id_year,
    count(*) AS total
   FROM public.committee_proposal_data
  WHERE (((decision_type)::text = 'röstning'::text) AND (against_proposal_parties IS NOT NULL) AND ((against_proposal_parties)::text <> ''::text) AND (char_length((against_proposal_parties)::text) > 10))
  GROUP BY (quote_literal(upper(replace(replace((against_proposal_parties)::text, ' '::text, ''::text), '"'::text, ''::text)))), rm
  ORDER BY rm;


ALTER VIEW public.view_riksdagen_party_coalation_against_annual_summary OWNER TO eris;

--
-- Name: view_riksdagen_party_document_daily_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_party_document_daily_summary OWNER TO eris;

--
-- Name: view_riksdagen_politician; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_politician OWNER TO eris;

--
-- Name: view_riksdagen_party_document_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_party_document_summary OWNER TO eris;

--
-- Name: view_riksdagen_party_role_member; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_party_role_member OWNER TO eris;

--
-- Name: view_riksdagen_party_signatures_document_summary; Type: VIEW; Schema: public; Owner: eris
--

CREATE VIEW public.view_riksdagen_party_signatures_document_summary AS
 SELECT upper((party_short_code)::text) AS party,
    count(*) AS total
   FROM public.document_person_reference_da_0
  WHERE (NOT ((party_short_code)::text = ''::text))
  GROUP BY (upper((party_short_code)::text))
  ORDER BY (count(*));


ALTER VIEW public.view_riksdagen_party_signatures_document_summary OWNER TO eris;

--
-- Name: view_riksdagen_party_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_party_summary OWNER TO eris;

--
-- Name: view_riksdagen_person_signed_document_summary; Type: VIEW; Schema: public; Owner: eris
--

CREATE VIEW public.view_riksdagen_person_signed_document_summary AS
 SELECT person_reference_id AS person_id,
    reference_name AS person_name,
    max((party_short_code)::text) AS party,
    count(*) AS total
   FROM public.document_person_reference_da_0
  GROUP BY person_reference_id, reference_name
  ORDER BY (count(*));


ALTER VIEW public.view_riksdagen_person_signed_document_summary OWNER TO eris;

--
-- Name: view_riksdagen_vote_data_ballot_politician_summary_daily; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary_daily OWNER TO eris;

--
-- Name: view_riksdagen_vote_data_ballot_politician_summary_annual; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary_annual OWNER TO eris;

--
-- Name: view_riksdagen_politician_ballot_summary; Type: VIEW; Schema: public; Owner: eris
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


ALTER VIEW public.view_riksdagen_politician_ballot_summary OWNER TO eris;

--
-- Name: view_riksdagen_politician_document_daily_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
--

CREATE MATERIALIZED VIEW public.view_riksdagen_politician_document_daily_summary AS
 SELECT made_public_date AS embedded_id_public_date,
    person_reference_id AS embedded_id_person_id,
    document_type AS embedded_id_document_type,
    count(*) AS total
   FROM public.view_riksdagen_politician_document
  GROUP BY made_public_date, document_type, person_reference_id
  WITH NO DATA;


ALTER MATERIALIZED VIEW public.view_riksdagen_politician_document_daily_summary OWNER TO eris;

--
-- Name: view_riksdagen_politician_experience_summary; Type: VIEW; Schema: public; Owner: eris
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
                    WHEN ((a.org_code)::text = ANY ((ARRAY['FiU'::character varying, 'KU'::character varying, 'UU'::character varying, 'FÖU'::character varying, 'JuU'::character varying])::text[])) THEN 'Key Parliamentary Committees'::text
                    WHEN ((a.org_code)::text = 'Statsrådsberedningen'::text) THEN 'Prime Minister’s Office'::text
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
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = ANY ((ARRAY['Partiledare'::character varying, 'Gruppledare'::character varying, 'Partisekreterare'::character varying, 'Kvittningsperson'::character varying])::text[]))) THEN 'Party Leadership'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['BSPC'::character varying, 'EFTA'::character varying, 'EG'::character varying, 'OSSE'::character varying, 'PA-UfM'::character varying, 'Europol'::character varying])::text[])) THEN 'International Affairs'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['NR'::character varying, 'RFK'::character varying, 'RJ'::character varying, 'RRS'::character varying])::text[])) THEN 'Regional and National Cooperation'::text
                    WHEN (((a.org_code)::text = ANY ((ARRAY['BN'::character varying, 'CPAR'::character varying, 'DEM'::character varying, 'DN'::character varying, 'EES'::character varying, 'ER'::character varying, 'ESK'::character varying, 'RB'::character varying, 'RGK'::character varying, 'UN'::character varying])::text[])) AND ((a.role_code)::text = ANY ((ARRAY['Ledamot'::character varying, 'Ordförande'::character varying, 'Vice ordförande'::character varying])::text[]))) THEN 'Legislative and Oversight Committees'::text
                    WHEN ((a.org_code)::text = 'FöU'::text) THEN 'Defense (Committee)'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['NSÖ'::character varying, 'ÖN'::character varying, 'RS'::character varying])::text[])) THEN 'Regional and National Cooperation'::text
                    WHEN ((a.org_code)::text = 'UFöU'::text) THEN 'Foreign & Defense (Committee)'::text
                    WHEN ((a.org_code)::text = 'EP'::text) THEN 'European Parliament'::text
                    WHEN (((a.org_code)::text = ANY ((ARRAY['BN'::character varying, 'CPAR'::character varying, 'DEM'::character varying, 'DN'::character varying, 'EES'::character varying, 'ER'::character varying, 'ESK'::character varying, 'RB'::character varying, 'RGK'::character varying, 'UN'::character varying])::text[])) AND ((a.role_code)::text = ANY ((ARRAY['Ledamot'::character varying, 'Ordförande'::character varying, 'Vice ordförande'::character varying])::text[]))) THEN 'Legislative and Oversight Committees'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['CU'::character varying, 'LU'::character varying, 'KD'::character varying, 'FÖU'::character varying, 'JuSoU'::character varying, 'VB'::character varying])::text[])) THEN 'Legislative and Oversight Committees'::text
                    WHEN ((a.org_code)::text = 'kam'::text) THEN 'Speaker’s Office'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['RJ'::character varying, 'Systembolaget'::character varying])::text[])) THEN 'Special Oversight Roles'::text
                    WHEN ((a.role_code)::text = ANY ((ARRAY['Suppleant'::character varying, 'Extra suppleant'::character varying, 'Ersättare'::character varying, 'Personlig suppleant'::character varying])::text[])) THEN 'Substitute Roles'::text
                    WHEN ((a.org_code)::text = 'UFÖU'::text) THEN 'Foreign & Defense (Committee)'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['TK'::character varying, 'sku'::character varying])::text[])) THEN 'Other Legislative Committees'::text
                    WHEN ((a.assignment_type)::text = 'partiuppdrag'::text) THEN 'Party Leadership'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['UFÖU'::character varying, 'VPN'::character varying, 'RRPR'::character varying, 'RRR'::character varying])::text[])) THEN 'Regional and National Cooperation'::text
                    WHEN ((a.org_code)::text = 'Systembolaget'::text) THEN 'Special Oversight Roles'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['EMPA'::character varying, 'IPU'::character varying, 'NATO'::character varying])::text[])) THEN 'International Affairs'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['FiU'::character varying, 'KU'::character varying, 'JU'::character varying, 'BoU'::character varying, 'TU'::character varying])::text[])) THEN 'Legislative Committees'::text
                    WHEN ((a.assignment_type)::text = 'Departement'::text) THEN 'Ministry'::text
                    WHEN ((a.role_code)::text = 'Personlig ersättare'::text) THEN 'Substitute Roles'::text
                    WHEN ((a.org_code)::text = 'EU'::text) THEN 'EU Affairs (Committee)'::text
                    WHEN ((a.org_code)::text = 'LR'::text) THEN 'Regional and National Cooperation'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['RAN'::character varying, 'RAR'::character varying])::text[])) THEN 'Legislative and Oversight Committees'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['FiU'::character varying, 'KU'::character varying, 'UU'::character varying, 'FÖU'::character varying, 'JuU'::character varying])::text[])) THEN 'Key Parliamentary Committees'::text
                    WHEN ((a.org_code)::text = 'Statsrådsberedningen'::text) THEN 'Prime Ministers Office'::text
                    WHEN ((a.org_code)::text = 'UFÖU'::text) THEN 'Foreign & Defense (Committee)'::text
                    WHEN ((a.org_code)::text = 'EU'::text) THEN 'EU Affairs (Committee)'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['EFTA'::character varying, 'EG'::character varying, 'OSSE'::character varying, 'PA-UfM'::character varying, 'BSPC'::character varying])::text[])) THEN 'International Affairs'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['NR'::character varying, 'RFK'::character varying, 'RJ'::character varying, 'RRS'::character varying])::text[])) THEN 'Regional and National Cooperation'::text
                    WHEN ((a.org_code)::text = ANY ((ARRAY['MJU'::character varying, 'BoU'::character varying, 'TU'::character varying])::text[])) THEN 'Other Legislative Committees'::text
                    WHEN ((a.assignment_type)::text = 'partiuppdrag'::text) THEN 'Party Leadership'::text
                    WHEN ((a.role_code)::text = ANY ((ARRAY['Suppleant'::character varying, 'Extra suppleant'::character varying, 'Ersättare'::character varying, 'Personlig suppleant'::character varying])::text[])) THEN 'Substitute Roles'::text
                    WHEN ((a.role_code)::text = ANY ((ARRAY['Suppleant'::character varying, 'Extra suppleant'::character varying])::text[])) THEN 'Substitute Roles'::text
                    ELSE 'Other'::text
                END AS knowledge_area,
                CASE
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text = 'Statsminister'::text)) THEN 50000
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = 'Partiledare'::text)) THEN 45000
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text = 'Vice statsminister'::text)) THEN 45000
                    WHEN (((a.assignment_type)::text = 'kammaruppdrag'::text) AND ((a.role_code)::text = 'Talman'::text)) THEN 40000
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text ~~* '%minister%'::text)) THEN 40000
                    WHEN (((a.assignment_type)::text = 'Riksdagsorgan'::text) AND ((a.role_code)::text = 'Ordförande'::text)) THEN 35000
                    WHEN (((a.assignment_type)::text = 'uppdrag'::text) AND ((a.role_code)::text = 'Ordförande'::text) AND ((a.org_code)::text = ANY ((ARRAY['FiU'::character varying, 'KU'::character varying, 'UU'::character varying, 'FÖU'::character varying, 'JuU'::character varying])::text[]))) THEN 35000
                    WHEN (((a.assignment_type)::text = 'uppdrag'::text) AND ((a.role_code)::text = 'Vice ordförande'::text)) THEN 30000
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = ANY ((ARRAY['Gruppledare'::character varying, 'Partisekreterare'::character varying])::text[]))) THEN 30000
                    WHEN (((a.assignment_type)::text = 'kammaruppdrag'::text) AND ((a.role_code)::text = 'Riksdagsledamot'::text)) THEN 20000
                    WHEN (((a.assignment_type)::text = 'Europaparlamentet'::text) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 20000
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 15000
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.role_code)::text = 'Ledamot'::text) AND ((a.org_code)::text = ANY ((ARRAY['FiU'::character varying, 'KU'::character varying, 'UU'::character varying, 'FÖU'::character varying])::text[]))) THEN 18000
                    WHEN ((a.role_code)::text = ANY ((ARRAY['Suppleant'::character varying, 'Ersättare'::character varying])::text[])) THEN 10000
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.role_code)::text = 'Suppleant'::text) AND ((a.org_code)::text = ANY ((ARRAY['FiU'::character varying, 'KU'::character varying, 'UU'::character varying])::text[]))) THEN 12000
                    WHEN (((a.assignment_type)::text = 'Riksdagsorgan'::text) AND ((a.org_code)::text = ANY ((ARRAY['RJ'::character varying, 'NR'::character varying, 'RFK'::character varying, 'RRS'::character varying])::text[]))) THEN 7000
                    WHEN (((a.assignment_type)::text = 'uppdrag'::text) AND ((a.role_code)::text = 'Ledamot'::text) AND ((a.org_code)::text = ANY ((ARRAY['MJU'::character varying, 'BoU'::character varying, 'TU'::character varying])::text[]))) THEN 6000
                    WHEN (((a.assignment_type)::text = 'Riksdagsorgan'::text) AND ((a.org_code)::text = ANY ((ARRAY['Systembolaget'::character varying, 'EUN'::character varying])::text[]))) THEN 4000
                    WHEN (((a.assignment_type)::text = 'uppdrag'::text) AND ((a.role_code)::text = ANY ((ARRAY['Adjungerad'::character varying, 'Sekreterare'::character varying])::text[]))) THEN 3000
                    ELSE 1000
                END AS role_weight,
                CASE
                    WHEN (((a.role_code)::text ~~* '%ersättare%'::text) OR ((a.role_code)::text ~~* '%suppleant%'::text)) THEN 1
                    ELSE 0
                END AS is_substitute,
                CASE
                    WHEN ((a.role_code)::text = ANY ((ARRAY['Ordförande'::character varying, 'Vice ordförande'::character varying, 'Gruppledare'::character varying, 'Partiledare'::character varying, 'Partisekreterare'::character varying, 'Förste vice gruppledare'::character varying, 'Andre vice gruppledare'::character varying])::text[])) THEN 1
                    ELSE 0
                END AS is_leadership,
                CASE
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text = 'Statsminister'::text)) THEN 1000.0
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = 'Partiledare'::text)) THEN 950.0
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND ((a.role_code)::text = 'Vice statsminister'::text)) THEN 900.0
                    WHEN (((a.assignment_type)::text = 'Departement'::text) AND (((a.role_code)::text ~~* '%minister%'::text) OR ((a.role_code)::text = 'Statsråd'::text))) THEN 850.0
                    WHEN (((a.assignment_type)::text = 'kammaruppdrag'::text) AND ((a.role_code)::text = 'Talman'::text)) THEN (800)::numeric
                    WHEN (((a.assignment_type)::text = 'talmansuppdrag'::text) AND ((a.role_code)::text = ANY ((ARRAY['Andre vice talman'::character varying, 'Tredje vice talman'::character varying])::text[]))) THEN 750.0
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.role_code)::text = 'Ordförande'::text)) THEN 700.0
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = ANY ((ARRAY['Gruppledare'::character varying, 'Partisekreterare'::character varying])::text[]))) THEN 650.0
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.role_code)::text = 'Vice ordförande'::text)) THEN 600.0
                    WHEN (((a.assignment_type)::text = 'partiuppdrag'::text) AND ((a.role_code)::text = ANY ((ARRAY['Förste vice gruppledare'::character varying, 'Andre vice gruppledare'::character varying])::text[]))) THEN 550.0
                    WHEN (((a.assignment_type)::text = 'kammaruppdrag'::text) AND ((a.role_code)::text = 'Riksdagsledamot'::text)) THEN 500.0
                    WHEN (((a.assignment_type)::text = 'Europaparlamentet'::text) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 450.0
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 400.0
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.org_code)::text = ANY ((ARRAY['UFÖU'::character varying, 'EU'::character varying])::text[])) AND ((a.role_code)::text = 'Ordförande'::text)) THEN 350.0
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.org_code)::text = ANY ((ARRAY['UFÖU'::character varying, 'EU'::character varying])::text[])) AND ((a.role_code)::text = 'Vice ordförande'::text)) THEN 300.0
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.org_code)::text = ANY ((ARRAY['UFÖU'::character varying, 'EU'::character varying])::text[])) AND ((a.role_code)::text = 'Ledamot'::text)) THEN 250.0
                    WHEN (((a.assignment_type)::text = 'Riksdagsorgan'::text) AND ((a.org_code)::text = ANY ((ARRAY['RJ'::character varying, 'Systembolaget'::character varying, 'NR'::character varying, 'RFK'::character varying, 'RRS'::character varying])::text[]))) THEN 200.0
                    WHEN ((a.role_code)::text = ANY ((ARRAY['Ersättare'::character varying, 'Statsrådsersättare'::character varying])::text[])) THEN 150.0
                    WHEN ((a.role_code)::text = ANY ((ARRAY['Suppleant'::character varying, 'Extra suppleant'::character varying])::text[])) THEN 100.0
                    WHEN (((a.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) AND ((a.org_code)::text = ANY ((ARRAY['MJU'::character varying, 'BoU'::character varying, 'TU'::character varying])::text[]))) THEN 50.0
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
                    WHEN ((per_role_stats.assignment_type)::text = ANY ((ARRAY['uppdrag'::character varying, 'Riksdagsorgan'::character varying])::text[])) THEN per_role_stats.total_days
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


ALTER VIEW public.view_riksdagen_politician_experience_summary OWNER TO eris;

--
-- Name: view_riksdagen_vote_data_ballot_party_summary_daily; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_daily OWNER TO eris;

--
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_daily; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_daily IS 'Daily party-level voting statistics with detailed metrics and success rates';


--
-- Name: view_riksdagen_vote_data_ballot_party_summary_annual; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_annual OWNER TO eris;

--
-- Name: view_riksdagen_vote_data_ballot_party_summary_monthly; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_monthly OWNER TO eris;

--
-- Name: view_riksdagen_vote_data_ballot_party_summary_weekly; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_weekly OWNER TO eris;

--
-- Name: view_riksdagen_vote_data_ballot_politician_summary_monthly; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary_monthly OWNER TO eris;

--
-- Name: view_riksdagen_vote_data_ballot_politician_summary_weekly; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary_weekly OWNER TO eris;

--
-- Name: view_riksdagen_vote_data_ballot_summary_daily; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_daily OWNER TO eris;

--
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_daily; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_daily IS 'Daily aggregation of voting statistics';


--
-- Name: view_riksdagen_vote_data_ballot_summary_annual; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_annual OWNER TO eris;

--
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_annual; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_annual IS 'Annual aggregation of voting statistics with averages and percentages';


--
-- Name: view_riksdagen_vote_data_ballot_summary_monthly; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_monthly OWNER TO eris;

--
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_monthly; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_monthly IS 'Monthly aggregation of voting statistics';


--
-- Name: view_riksdagen_vote_data_ballot_summary_weekly; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_weekly OWNER TO eris;

--
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_weekly; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_weekly IS 'Weekly aggregation of voting statistics';


--
-- Name: world_bank_data; Type: TABLE; Schema: public; Owner: eris
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


ALTER TABLE public.world_bank_data OWNER TO eris;

--
-- Name: COLUMN world_bank_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.world_bank_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.country_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.world_bank_data.country_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.country_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.world_bank_data.country_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.data_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.world_bank_data.data_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.indicator_id; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.world_bank_data.indicator_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.indicator_value; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.world_bank_data.indicator_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.year_date; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.world_bank_data.year_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.data__data_element_hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.world_bank_data.data__data_element_hjid IS 'DATA.Public GDPR.NA';


--
-- Name: view_worldbank_indicator_data_country_summary; Type: MATERIALIZED VIEW; Schema: public; Owner: eris
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


ALTER MATERIALIZED VIEW public.view_worldbank_indicator_data_country_summary OWNER TO eris;

--
-- Name: vote_meta_data; Type: TABLE; Schema: public; Owner: eris
--

CREATE TABLE public.vote_meta_data (
    hjid bigint NOT NULL,
    ballot_type character varying(255),
    group_behavior character varying(255),
    outcome character varying(255),
    proffessional_behavior character varying(255),
    target character varying(255)
);


ALTER TABLE public.vote_meta_data OWNER TO eris;

--
-- Name: COLUMN vote_meta_data.hjid; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_meta_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_meta_data.ballot_type; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_meta_data.ballot_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_meta_data.group_behavior; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_meta_data.group_behavior IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_meta_data.outcome; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_meta_data.outcome IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN vote_meta_data.proffessional_behavior; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_meta_data.proffessional_behavior IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_meta_data.target; Type: COMMENT; Schema: public; Owner: eris
--

COMMENT ON COLUMN public.vote_meta_data.target IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: qrtz_calendars QRTZ_CALENDARS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_calendars
    ADD CONSTRAINT "QRTZ_CALENDARS_pkey" PRIMARY KEY (sched_name, calendar_name);


--
-- Name: qrtz_cron_triggers QRTZ_CRON_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_cron_triggers
    ADD CONSTRAINT "QRTZ_CRON_TRIGGERS_pkey" PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- Name: qrtz_fired_triggers QRTZ_FIRED_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_fired_triggers
    ADD CONSTRAINT "QRTZ_FIRED_TRIGGERS_pkey" PRIMARY KEY (sched_name, entry_id);


--
-- Name: qrtz_job_details QRTZ_JOB_DETAILS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_job_details
    ADD CONSTRAINT "QRTZ_JOB_DETAILS_pkey" PRIMARY KEY (sched_name, job_name, job_group);


--
-- Name: qrtz_locks QRTZ_LOCKS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_locks
    ADD CONSTRAINT "QRTZ_LOCKS_pkey" PRIMARY KEY (sched_name, lock_name);


--
-- Name: qrtz_paused_trigger_grps QRTZ_PAUSED_TRIGGER_GRPS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_paused_trigger_grps
    ADD CONSTRAINT "QRTZ_PAUSED_TRIGGER_GRPS_pkey" PRIMARY KEY (sched_name, trigger_group);


--
-- Name: qrtz_scheduler_state QRTZ_SCHEDULER_STATE_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_scheduler_state
    ADD CONSTRAINT "QRTZ_SCHEDULER_STATE_pkey" PRIMARY KEY (sched_name, instance_name);


--
-- Name: qrtz_simple_triggers QRTZ_SIMPLE_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_simple_triggers
    ADD CONSTRAINT "QRTZ_SIMPLE_TRIGGERS_pkey" PRIMARY KEY (sched_name);


--
-- Name: qrtz_simprop_triggers QRTZ_SIMPROP_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_simprop_triggers
    ADD CONSTRAINT "QRTZ_SIMPROP_TRIGGERS_pkey" PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- Name: qrtz_triggers QRTZ_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_triggers
    ADD CONSTRAINT "QRTZ_TRIGGERS_pkey" PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- Name: qrtz_blob_triggers QRTZ_bytea_TRIGGERS_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_blob_triggers
    ADD CONSTRAINT "QRTZ_bytea_TRIGGERS_pkey" PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- Name: against_proposal_container against_proposal_container_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.against_proposal_container
    ADD CONSTRAINT against_proposal_container_pkey PRIMARY KEY (hjid);


--
-- Name: against_proposal_data against_proposal_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.against_proposal_data
    ADD CONSTRAINT against_proposal_data_pkey PRIMARY KEY (hjid);


--
-- Name: agency agency_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.agency
    ADD CONSTRAINT agency_pkey PRIMARY KEY (hjid);


--
-- Name: application_action_event application_action_event_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_action_event
    ADD CONSTRAINT application_action_event_pkey PRIMARY KEY (hjid);


--
-- Name: application_configuration application_configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_configuration
    ADD CONSTRAINT application_configuration_pkey PRIMARY KEY (hjid);


--
-- Name: application_session application_session_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_session
    ADD CONSTRAINT application_session_pkey PRIMARY KEY (hjid);


--
-- Name: application_view application_view_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT application_view_pkey PRIMARY KEY (hjid);


--
-- Name: assignment_data assignment_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.assignment_data
    ADD CONSTRAINT assignment_data_pkey PRIMARY KEY (hjid);


--
-- Name: assignment_element assignment_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.assignment_element
    ADD CONSTRAINT assignment_element_pkey PRIMARY KEY (hjid);


--
-- Name: committee_document_data committee_document_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.committee_document_data
    ADD CONSTRAINT committee_document_data_pkey PRIMARY KEY (id);


--
-- Name: committee_proposal_component_0 committee_proposal_component_0_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.committee_proposal_component_0
    ADD CONSTRAINT committee_proposal_component_0_pkey PRIMARY KEY (hjid);


--
-- Name: committee_proposal_container committee_proposal_container_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.committee_proposal_container
    ADD CONSTRAINT committee_proposal_container_pkey PRIMARY KEY (hjid);


--
-- Name: committee_proposal_data committee_proposal_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.committee_proposal_data
    ADD CONSTRAINT committee_proposal_data_pkey PRIMARY KEY (hjid);


--
-- Name: countries_element countries_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.countries_element
    ADD CONSTRAINT countries_element_pkey PRIMARY KEY (hjid);


--
-- Name: country_element country_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.country_element
    ADD CONSTRAINT country_element_pkey PRIMARY KEY (hjid);


--
-- Name: data_element data_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.data_element
    ADD CONSTRAINT data_element_pkey PRIMARY KEY (hjid);


--
-- Name: data_source_content data_source_content_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.data_source_content
    ADD CONSTRAINT data_source_content_pkey PRIMARY KEY (hjid);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: detail_data detail_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.detail_data
    ADD CONSTRAINT detail_data_pkey PRIMARY KEY (hjid);


--
-- Name: detail_element detail_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.detail_element
    ADD CONSTRAINT detail_element_pkey PRIMARY KEY (hjid);


--
-- Name: document_activity_container document_activity_container_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_activity_container
    ADD CONSTRAINT document_activity_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_activity_data document_activity_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_activity_data
    ADD CONSTRAINT document_activity_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_attachment_container document_attachment_container_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_attachment_container
    ADD CONSTRAINT document_attachment_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_attachment document_attachment_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_attachment
    ADD CONSTRAINT document_attachment_pkey PRIMARY KEY (hjid);


--
-- Name: document_container_element document_container_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_container_element
    ADD CONSTRAINT document_container_element_pkey PRIMARY KEY (hjid);


--
-- Name: document_content_data document_content_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_content_data
    ADD CONSTRAINT document_content_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_data document_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_data
    ADD CONSTRAINT document_data_pkey PRIMARY KEY (id);


--
-- Name: document_detail_container document_detail_container_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_detail_container
    ADD CONSTRAINT document_detail_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_detail_data document_detail_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_detail_data
    ADD CONSTRAINT document_detail_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_element document_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_element
    ADD CONSTRAINT document_element_pkey PRIMARY KEY (id);


--
-- Name: document_person_reference_co_0 document_person_reference_co_0_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_person_reference_co_0
    ADD CONSTRAINT document_person_reference_co_0_pkey PRIMARY KEY (hjid);


--
-- Name: document_person_reference_da_0 document_person_reference_da_0_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_person_reference_da_0
    ADD CONSTRAINT document_person_reference_da_0_pkey PRIMARY KEY (hjid);


--
-- Name: document_proposal_container document_proposal_container_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_proposal_container
    ADD CONSTRAINT document_proposal_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_proposal_data document_proposal_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_proposal_data
    ADD CONSTRAINT document_proposal_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_reference_container document_reference_container_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_reference_container
    ADD CONSTRAINT document_reference_container_pkey PRIMARY KEY (hjid);


--
-- Name: document_reference_data document_reference_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_reference_data
    ADD CONSTRAINT document_reference_data_pkey PRIMARY KEY (hjid);


--
-- Name: document_status_container document_status_container_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT document_status_container_pkey PRIMARY KEY (hjid);


--
-- Name: domain_portal domain_portal_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.domain_portal
    ADD CONSTRAINT domain_portal_pkey PRIMARY KEY (hjid);


--
-- Name: indicator_element indicator_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.indicator_element
    ADD CONSTRAINT indicator_element_pkey PRIMARY KEY (hjid);


--
-- Name: indicators_element indicators_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.indicators_element
    ADD CONSTRAINT indicators_element_pkey PRIMARY KEY (hjid);


--
-- Name: jv_commit jv_commit_pk; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.jv_commit
    ADD CONSTRAINT jv_commit_pk PRIMARY KEY (commit_pk);


--
-- Name: jv_commit_property jv_commit_property_pk; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.jv_commit_property
    ADD CONSTRAINT jv_commit_property_pk PRIMARY KEY (property_name, commit_fk);


--
-- Name: jv_global_id jv_global_id_pk; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.jv_global_id
    ADD CONSTRAINT jv_global_id_pk PRIMARY KEY (global_id_pk);


--
-- Name: jv_snapshot jv_snapshot_pk; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.jv_snapshot
    ADD CONSTRAINT jv_snapshot_pk PRIMARY KEY (snapshot_pk);


--
-- Name: language_content_data language_content_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.language_content_data
    ADD CONSTRAINT language_content_data_pkey PRIMARY KEY (hjid);


--
-- Name: language_data language_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.language_data
    ADD CONSTRAINT language_data_pkey PRIMARY KEY (hjid);


--
-- Name: operational_information_cont_0 operational_information_cont_0_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.operational_information_cont_0
    ADD CONSTRAINT operational_information_cont_0_pkey PRIMARY KEY (hjid);


--
-- Name: performance_indicator_content performance_indicator_content_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.performance_indicator_content
    ADD CONSTRAINT performance_indicator_content_pkey PRIMARY KEY (hjid);


--
-- Name: person_assignment_data person_assignment_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_assignment_data
    ADD CONSTRAINT person_assignment_data_pkey PRIMARY KEY (hjid);


--
-- Name: person_assignment_element person_assignment_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_assignment_element
    ADD CONSTRAINT person_assignment_element_pkey PRIMARY KEY (hjid);


--
-- Name: person_container_data person_container_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_container_data
    ADD CONSTRAINT person_container_data_pkey PRIMARY KEY (hjid);


--
-- Name: person_container_element person_container_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_container_element
    ADD CONSTRAINT person_container_element_pkey PRIMARY KEY (hjid);


--
-- Name: person_data person_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_data
    ADD CONSTRAINT person_data_pkey PRIMARY KEY (id);


--
-- Name: person_detail_data person_detail_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_detail_data
    ADD CONSTRAINT person_detail_data_pkey PRIMARY KEY (hjid);


--
-- Name: person_detail_element person_detail_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_detail_element
    ADD CONSTRAINT person_detail_element_pkey PRIMARY KEY (hjid);


--
-- Name: person_element person_element_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_element
    ADD CONSTRAINT person_element_pkey PRIMARY KEY (id);


--
-- Name: portal portal_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.portal
    ADD CONSTRAINT portal_pkey PRIMARY KEY (hjid);


--
-- Name: quality_assurance_content quality_assurance_content_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.quality_assurance_content
    ADD CONSTRAINT quality_assurance_content_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_data_container sweden_county_data_container_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_county_data_container
    ADD CONSTRAINT sweden_county_data_container_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_data sweden_county_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_county_data
    ADD CONSTRAINT sweden_county_data_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_electoral_area sweden_county_electoral_area_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_county_electoral_area
    ADD CONSTRAINT sweden_county_electoral_area_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_electoral_regi_0 sweden_county_electoral_regi_0_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_county_electoral_regi_0
    ADD CONSTRAINT sweden_county_electoral_regi_0_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_county_electoral_regi_1 sweden_county_electoral_regi_1_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_county_electoral_regi_1
    ADD CONSTRAINT sweden_county_electoral_regi_1_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_election_region sweden_election_region_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_election_region
    ADD CONSTRAINT sweden_election_region_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_election_type_contain_0 sweden_election_type_contain_0_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_election_type_contain_0
    ADD CONSTRAINT sweden_election_type_contain_0_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_election_type sweden_election_type_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_election_type
    ADD CONSTRAINT sweden_election_type_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_municipality_data sweden_municipality_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_municipality_data
    ADD CONSTRAINT sweden_municipality_data_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_municipality_election_0 sweden_municipality_election_0_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_municipality_election_0
    ADD CONSTRAINT sweden_municipality_election_0_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_parliament_electoral__0 sweden_parliament_electoral__0_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_parliament_electoral__0
    ADD CONSTRAINT sweden_parliament_electoral__0_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_parliament_electoral__1 sweden_parliament_electoral__1_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_parliament_electoral__1
    ADD CONSTRAINT sweden_parliament_electoral__1_pkey PRIMARY KEY (hjid);


--
-- Name: sweden_political_party sweden_political_party_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_political_party
    ADD CONSTRAINT sweden_political_party_pkey PRIMARY KEY (hjid);


--
-- Name: target_profile_content target_profile_content_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.target_profile_content
    ADD CONSTRAINT target_profile_content_pkey PRIMARY KEY (hjid);


--
-- Name: topic topic_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pkey PRIMARY KEY (hjid);


--
-- Name: topics topics_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.topics
    ADD CONSTRAINT topics_pkey PRIMARY KEY (hjid);


--
-- Name: user_account_address user_account_address_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.user_account_address
    ADD CONSTRAINT user_account_address_pkey PRIMARY KEY (hjid, hjindex);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (hjid);


--
-- Name: vote_data vote_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.vote_data
    ADD CONSTRAINT vote_data_pkey PRIMARY KEY (embedded_id_ballot_id, embedded_id_concern, embedded_id_intressent_id, embedded_id_issue);


--
-- Name: vote_meta_data vote_meta_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.vote_meta_data
    ADD CONSTRAINT vote_meta_data_pkey PRIMARY KEY (hjid);


--
-- Name: world_bank_data world_bank_data_pkey; Type: CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.world_bank_data
    ADD CONSTRAINT world_bank_data_pkey PRIMARY KEY (hjid);


--
-- Name: application_action_event_created_date_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX application_action_event_created_date_idx ON public.application_action_event USING btree (created_date);


--
-- Name: application_action_event_element_id_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX application_action_event_element_id_idx ON public.application_action_event USING btree (element_id);


--
-- Name: application_action_event_element_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX application_action_event_element_idx ON public.application_action_event USING btree (element_id);


--
-- Name: application_action_event_page_created_date_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX application_action_event_page_created_date_idx ON public.application_action_event USING btree (page, created_date);


--
-- Name: application_action_event_page_element_id_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX application_action_event_page_element_id_idx ON public.application_action_event USING btree (page, element_id);


--
-- Name: application_action_event_page_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX application_action_event_page_idx ON public.application_action_event USING btree (page);


--
-- Name: application_action_event_sessionid_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX application_action_event_sessionid_idx ON public.application_action_event USING btree (session_id);


--
-- Name: application_session_created_date_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX application_session_created_date_idx ON public.application_session USING btree (created_date);


--
-- Name: application_session_ip_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX application_session_ip_idx ON public.application_session USING btree (ip_information);


--
-- Name: idx_annual_date_party; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_annual_date_party ON public.view_riksdagen_vote_data_ballot_party_summary_annual USING btree (embedded_id_vote_date, embedded_id_party);


--
-- Name: idx_annual_summary_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_annual_summary_date ON public.view_riksdagen_vote_data_ballot_summary_annual USING btree (vote_date);


--
-- Name: idx_assignment_data_assignment_type; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_assignment_data_assignment_type ON public.assignment_data USING btree (assignment_type);


--
-- Name: idx_assignment_data_org_code; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_assignment_data_org_code ON public.assignment_data USING btree (org_code);


--
-- Name: idx_assignment_data_type_dates; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_assignment_data_type_dates ON public.assignment_data USING btree (assignment_type, from_date, to_date, intressent_id);


--
-- Name: idx_ballot_decision_party_party; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_ballot_decision_party_party ON public.view_riksdagen_committee_ballot_decision_party_summary USING btree (embedded_id_party);


--
-- Name: idx_ballot_decision_politician_id; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_ballot_decision_politician_id ON public.view_riksdagen_committee_ballot_decision_politician_summary USING btree (embedded_id_intressent_id);


--
-- Name: idx_ballot_decision_summary_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_ballot_decision_summary_date ON public.view_riksdagen_committee_ballot_decision_summary USING btree (vote_date);


--
-- Name: idx_ballot_summary_approved; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_ballot_summary_approved ON public.view_riksdagen_vote_data_ballot_summary USING btree (approved);


--
-- Name: idx_ballot_summary_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_ballot_summary_date ON public.view_riksdagen_vote_data_ballot_summary USING btree (vote_date);


--
-- Name: idx_ballot_summary_id; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_ballot_summary_id ON public.view_riksdagen_vote_data_ballot_summary USING btree (embedded_id_ballot_id, embedded_id_issue, embedded_id_concern);


--
-- Name: idx_committee_decisions_ballot; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_committee_decisions_ballot ON public.view_riksdagen_committee_decisions USING btree (ballot_id);


--
-- Name: idx_committee_decisions_rm_issue; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_committee_decisions_rm_issue ON public.view_riksdagen_committee_decisions USING btree (rm, embedded_id_issue_nummer);


--
-- Name: idx_daily_summary_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_daily_summary_date ON public.view_riksdagen_vote_data_ballot_summary_daily USING btree (vote_date);


--
-- Name: idx_decision_type_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_decision_type_date ON public.view_riksdagen_committee_decision_type_summary USING btree (embedded_id_decision_date);


--
-- Name: idx_decision_type_org_composite; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_decision_type_org_composite ON public.view_riksdagen_committee_decision_type_org_summary USING btree (embedded_id_decision_type, embedded_id_org);


--
-- Name: idx_monthly_date_party; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_monthly_date_party ON public.view_riksdagen_vote_data_ballot_party_summary_monthly USING btree (embedded_id_vote_date, embedded_id_party);


--
-- Name: idx_monthly_summary_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_monthly_summary_date ON public.view_riksdagen_vote_data_ballot_summary_monthly USING btree (vote_date);


--
-- Name: idx_party_daily_summary_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_party_daily_summary_date ON public.view_riksdagen_vote_data_ballot_party_summary_daily USING btree (embedded_id_vote_date);


--
-- Name: idx_party_daily_summary_party; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_party_daily_summary_party ON public.view_riksdagen_vote_data_ballot_party_summary_daily USING btree (embedded_id_party);


--
-- Name: idx_party_summary_approved; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_party_summary_approved ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (party_approved);


--
-- Name: idx_party_summary_ballot_id; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_party_summary_ballot_id ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (embedded_id_ballot_id, embedded_id_issue, embedded_id_concern);


--
-- Name: idx_party_summary_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_party_summary_date ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (vote_date);


--
-- Name: idx_party_summary_party; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_party_summary_party ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (embedded_id_party);


--
-- Name: idx_party_summary_party_won; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_party_summary_party_won ON public.view_riksdagen_vote_data_ballot_party_summary USING btree (party_won);


--
-- Name: idx_politician_annual_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_annual_date ON public.view_riksdagen_vote_data_ballot_politician_summary_annual USING btree (embedded_id_vote_date);


--
-- Name: idx_politician_annual_id; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_annual_id ON public.view_riksdagen_vote_data_ballot_politician_summary_annual USING btree (embedded_id_intressent_id);


--
-- Name: idx_politician_monthly_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_monthly_date ON public.view_riksdagen_vote_data_ballot_politician_summary_monthly USING btree (embedded_id_vote_date);


--
-- Name: idx_politician_monthly_id; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_monthly_id ON public.view_riksdagen_vote_data_ballot_politician_summary_monthly USING btree (embedded_id_intressent_id);


--
-- Name: idx_politician_summary_party; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_summary_party ON public.view_riksdagen_vote_data_ballot_politician_summary USING btree (party);


--
-- Name: idx_politician_summary_politician; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_summary_politician ON public.view_riksdagen_vote_data_ballot_politician_summary USING btree (embedded_id_intressent_id);


--
-- Name: idx_politician_summary_vote_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_summary_vote_date ON public.view_riksdagen_vote_data_ballot_politician_summary USING btree (vote_date);


--
-- Name: idx_politician_weekly_composite; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_weekly_composite ON public.view_riksdagen_vote_data_ballot_politician_summary_weekly USING btree (embedded_id_vote_date, embedded_id_intressent_id);


--
-- Name: idx_politician_weekly_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_weekly_date ON public.view_riksdagen_vote_data_ballot_politician_summary_weekly USING btree (embedded_id_vote_date);


--
-- Name: idx_politician_weekly_id; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_politician_weekly_id ON public.view_riksdagen_vote_data_ballot_politician_summary_weekly USING btree (embedded_id_intressent_id);


--
-- Name: idx_qrtz_ft_inst_job_req_rcvry; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_ft_inst_job_req_rcvry ON public.qrtz_fired_triggers USING btree (sched_name, instance_name, requests_recovery);


--
-- Name: idx_qrtz_ft_j_g; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_ft_j_g ON public.qrtz_fired_triggers USING btree (sched_name, job_name, job_group);


--
-- Name: idx_qrtz_ft_jg; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_ft_jg ON public.qrtz_fired_triggers USING btree (sched_name, job_group);


--
-- Name: idx_qrtz_ft_t_g; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_ft_t_g ON public.qrtz_fired_triggers USING btree (sched_name, trigger_name, trigger_group);


--
-- Name: idx_qrtz_ft_tg; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_ft_tg ON public.qrtz_fired_triggers USING btree (sched_name, trigger_group);


--
-- Name: idx_qrtz_ft_trig_inst_name; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_ft_trig_inst_name ON public.qrtz_fired_triggers USING btree (sched_name, instance_name);


--
-- Name: idx_qrtz_t_c; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_c ON public.qrtz_triggers USING btree (sched_name, calendar_name);


--
-- Name: idx_qrtz_t_g; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_g ON public.qrtz_triggers USING btree (sched_name, trigger_group);


--
-- Name: idx_qrtz_t_j; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_j ON public.qrtz_triggers USING btree (sched_name, job_name, job_group);


--
-- Name: idx_qrtz_t_jg; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_jg ON public.qrtz_triggers USING btree (sched_name, job_group);


--
-- Name: idx_qrtz_t_n_g_state; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_n_g_state ON public.qrtz_triggers USING btree (sched_name, trigger_group, trigger_state);


--
-- Name: idx_qrtz_t_n_state; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_n_state ON public.qrtz_triggers USING btree (sched_name, trigger_name, trigger_group, trigger_state);


--
-- Name: idx_qrtz_t_next_fire_time; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_next_fire_time ON public.qrtz_triggers USING btree (sched_name, next_fire_time);


--
-- Name: idx_qrtz_t_nft_misfire; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_nft_misfire ON public.qrtz_triggers USING btree (sched_name, misfire_instr, next_fire_time);


--
-- Name: idx_qrtz_t_nft_st; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_nft_st ON public.qrtz_triggers USING btree (sched_name, trigger_state, next_fire_time);


--
-- Name: idx_qrtz_t_nft_st_misfire; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_nft_st_misfire ON public.qrtz_triggers USING btree (sched_name, misfire_instr, next_fire_time, trigger_state);


--
-- Name: idx_qrtz_t_nft_st_misfire_grp; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_nft_st_misfire_grp ON public.qrtz_triggers USING btree (sched_name, misfire_instr, next_fire_time, trigger_group, trigger_state);


--
-- Name: idx_qrtz_t_state; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_qrtz_t_state ON public.qrtz_triggers USING btree (sched_name, trigger_state);


--
-- Name: idx_rpd_doc_type_subtype; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_rpd_doc_type_subtype ON public.view_riksdagen_politician_document USING btree (document_type, sub_type);


--
-- Name: idx_rpd_made_public_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_rpd_made_public_date ON public.view_riksdagen_politician_document USING btree (made_public_date);


--
-- Name: idx_rpd_party_short_code; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_rpd_party_short_code ON public.view_riksdagen_politician_document USING btree (party_short_code);


--
-- Name: idx_rpd_person_ref_id; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_rpd_person_ref_id ON public.view_riksdagen_politician_document USING btree (person_reference_id);


--
-- Name: idx_vote_data_ballot_id; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_vote_data_ballot_id ON public.vote_data USING btree (embedded_id_ballot_id, embedded_id_issue, embedded_id_concern);


--
-- Name: idx_vote_data_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_vote_data_date ON public.vote_data USING btree (vote_date);


--
-- Name: idx_vote_data_party; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_vote_data_party ON public.vote_data USING btree (party);


--
-- Name: idx_vote_data_votes; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_vote_data_votes ON public.vote_data USING btree (vote);


--
-- Name: idx_weekly_date_party; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_weekly_date_party ON public.view_riksdagen_vote_data_ballot_party_summary_weekly USING btree (embedded_id_vote_date, embedded_id_party);


--
-- Name: idx_weekly_summary_date; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX idx_weekly_summary_date ON public.view_riksdagen_vote_data_ballot_summary_weekly USING btree (vote_date);


--
-- Name: jv_commit_commit_id_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX jv_commit_commit_id_idx ON public.jv_commit USING btree (commit_id);


--
-- Name: jv_commit_property_commit_fk_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX jv_commit_property_commit_fk_idx ON public.jv_commit_property USING btree (commit_fk);


--
-- Name: jv_commit_property_property_name_property_value_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX jv_commit_property_property_name_property_value_idx ON public.jv_commit_property USING btree (property_name, property_value);


--
-- Name: jv_global_id_local_id_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX jv_global_id_local_id_idx ON public.jv_global_id USING btree (local_id);


--
-- Name: jv_global_id_owner_id_fk_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX jv_global_id_owner_id_fk_idx ON public.jv_global_id USING btree (owner_id_fk);


--
-- Name: jv_snapshot_commit_fk_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX jv_snapshot_commit_fk_idx ON public.jv_snapshot USING btree (commit_fk);


--
-- Name: jv_snapshot_global_id_fk_idx; Type: INDEX; Schema: public; Owner: eris
--

CREATE INDEX jv_snapshot_global_id_fk_idx ON public.jv_snapshot USING btree (global_id_fk);


--
-- Name: person_element fk_13jay3yk8opnt33758httu9kb; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_element
    ADD CONSTRAINT fk_13jay3yk8opnt33758httu9kb FOREIGN KEY (person_detail_element_person_0) REFERENCES public.person_detail_element(hjid);


--
-- Name: application_view fk_2ivjcdwosa63ant7jc5c6cojj; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_2ivjcdwosa63ant7jc5c6cojj FOREIGN KEY (target_profile_application_v_0) REFERENCES public.target_profile_content(hjid);


--
-- Name: country_element fk_3k0s1gih1msbej3bp2iotg52y; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.country_element
    ADD CONSTRAINT fk_3k0s1gih1msbej3bp2iotg52y FOREIGN KEY (country_countries_element_hj_0) REFERENCES public.countries_element(hjid);


--
-- Name: person_element fk_3o85sqp9yss0nler1yl1umfl1; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_element
    ADD CONSTRAINT fk_3o85sqp9yss0nler1yl1umfl1 FOREIGN KEY (person_person_container_elem_0) REFERENCES public.person_container_element(hjid);


--
-- Name: sweden_county_electoral_regi_1 fk_4y4vi3cafmbdhvckntfn7qdps; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_county_electoral_regi_1
    ADD CONSTRAINT fk_4y4vi3cafmbdhvckntfn7qdps FOREIGN KEY (county_electoral_regions_swe_0) REFERENCES public.sweden_county_electoral_regi_0(hjid);


--
-- Name: against_proposal_data fk_5u5u77qsrpa2qy6umqrph4tyf; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.against_proposal_data
    ADD CONSTRAINT fk_5u5u77qsrpa2qy6umqrph4tyf FOREIGN KEY (against_proposal_list_agains_0) REFERENCES public.against_proposal_container(hjid);


--
-- Name: person_container_data fk_5w4uvrhl3l7c441b7ra7p8txr; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_container_data
    ADD CONSTRAINT fk_5w4uvrhl3l7c441b7ra7p8txr FOREIGN KEY (person_person_container_data_0) REFERENCES public.person_data(id);


--
-- Name: document_status_container fk_6crp887w8xy3e4i143yyydjqv; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_6crp887w8xy3e4i143yyydjqv FOREIGN KEY (document_reference_container_0) REFERENCES public.document_reference_container(hjid);


--
-- Name: portal fk_7c8jfw8bnxrm2aj26w9qlx340; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.portal
    ADD CONSTRAINT fk_7c8jfw8bnxrm2aj26w9qlx340 FOREIGN KEY (portals_agency_hjid) REFERENCES public.agency(hjid);


--
-- Name: sweden_county_electoral_area fk_7h4feuc5bwu12tyaka1nx1ln; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_county_electoral_area
    ADD CONSTRAINT fk_7h4feuc5bwu12tyaka1nx1ln FOREIGN KEY (landstingsvalkrets_sweden_co_0) REFERENCES public.sweden_county_electoral_regi_1(hjid);


--
-- Name: assignment_data fk_84o1dcsfeyp1o25nfdpppa7oe; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.assignment_data
    ADD CONSTRAINT fk_84o1dcsfeyp1o25nfdpppa7oe FOREIGN KEY (assignment_list_person_assig_0) REFERENCES public.person_assignment_data(hjid);


--
-- Name: document_status_container fk_86c52yf22uk0bpcs1qoc3aeyv; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_86c52yf22uk0bpcs1qoc3aeyv FOREIGN KEY (document_attachment_containe_0) REFERENCES public.document_attachment_container(hjid);


--
-- Name: user_account_address fk_8931ymg13vy6vfkrichtst7bj; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.user_account_address
    ADD CONSTRAINT fk_8931ymg13vy6vfkrichtst7bj FOREIGN KEY (hjid) REFERENCES public.user_account(hjid);


--
-- Name: indicator_element fk_8l1m1pum4e3catw4443rup4q5; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.indicator_element
    ADD CONSTRAINT fk_8l1m1pum4e3catw4443rup4q5 FOREIGN KEY (indicator__indicators_elemen_0) REFERENCES public.indicators_element(hjid);


--
-- Name: committee_proposal_component_0 fk_90arga58ce9bnjkc6lws04uhw; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.committee_proposal_component_0
    ADD CONSTRAINT fk_90arga58ce9bnjkc6lws04uhw FOREIGN KEY (committee_proposal_container_0) REFERENCES public.committee_proposal_container(hjid);


--
-- Name: indicator_element fk_92h99v4i1pmr69x0y43pocv2a; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.indicator_element
    ADD CONSTRAINT fk_92h99v4i1pmr69x0y43pocv2a FOREIGN KEY (topics_indicator_element_hjid) REFERENCES public.topics(hjid);


--
-- Name: domain_portal fk_9ln0n5axxjuxtbpepyad69rel; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.domain_portal
    ADD CONSTRAINT fk_9ln0n5axxjuxtbpepyad69rel FOREIGN KEY (hjid) REFERENCES public.portal(hjid);


--
-- Name: document_status_container fk_9q1ktfb77gieq0xugoqe2fidd; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_9q1ktfb77gieq0xugoqe2fidd FOREIGN KEY (document_detail_container_do_0) REFERENCES public.document_detail_container(hjid);


--
-- Name: application_view fk_9x5havflf3rdfkaw1hangbemd; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_9x5havflf3rdfkaw1hangbemd FOREIGN KEY (quality_assurance_applicatio_0) REFERENCES public.quality_assurance_content(hjid);


--
-- Name: person_detail_element fk_a6syxeadcisfnnfjqemog93qd; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_detail_element
    ADD CONSTRAINT fk_a6syxeadcisfnnfjqemog93qd FOREIGN KEY (detail_list_person_detail_el_0) REFERENCES public.detail_element(hjid);


--
-- Name: sweden_political_party fk_c2f4dhdce9p61sg50rnww73c1; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_political_party
    ADD CONSTRAINT fk_c2f4dhdce9p61sg50rnww73c1 FOREIGN KEY (parties_sweden_election_regi_0) REFERENCES public.sweden_election_region(hjid);


--
-- Name: document_reference_data fk_c4uqb4d6xqa5d7afwen8sny67; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_reference_data
    ADD CONSTRAINT fk_c4uqb4d6xqa5d7afwen8sny67 FOREIGN KEY (document_reference_list_docu_0) REFERENCES public.document_reference_container(hjid);


--
-- Name: detail_data fk_diexjlb9hdrfv7g5y06cj6nu5; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.detail_data
    ADD CONSTRAINT fk_diexjlb9hdrfv7g5y06cj6nu5 FOREIGN KEY (detail_list_person_detail_da_0) REFERENCES public.person_detail_data(hjid);


--
-- Name: world_bank_data fk_e0yghurnnhmkahpt7ydf008fo; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.world_bank_data
    ADD CONSTRAINT fk_e0yghurnnhmkahpt7ydf008fo FOREIGN KEY (data__data_element_hjid) REFERENCES public.data_element(hjid);


--
-- Name: committee_proposal_component_0 fk_eofapva6jn5k3h5gnj4whyilb; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.committee_proposal_component_0
    ADD CONSTRAINT fk_eofapva6jn5k3h5gnj4whyilb FOREIGN KEY (against_proposal_container_c_0) REFERENCES public.against_proposal_container(hjid);


--
-- Name: application_view fk_f4bptktby95bygv359chn7lbn; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_f4bptktby95bygv359chn7lbn FOREIGN KEY (performance_indicators_appli_0) REFERENCES public.performance_indicator_content(hjid);


--
-- Name: document_activity_data fk_gruc53dqu0smf6s1a0gkelvdm; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_activity_data
    ADD CONSTRAINT fk_gruc53dqu0smf6s1a0gkelvdm FOREIGN KEY (document_activities_document_0) REFERENCES public.document_activity_container(hjid);


--
-- Name: sweden_municipality_data fk_gykahsnks6y9v8y5novlxagnf; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_municipality_data
    ADD CONSTRAINT fk_gykahsnks6y9v8y5novlxagnf FOREIGN KEY (kommun_sweden_county_data_hj_0) REFERENCES public.sweden_county_data(hjid);


--
-- Name: committee_proposal_data fk_hs04ji7kqvwd7313ryp20vo0x; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.committee_proposal_data
    ADD CONSTRAINT fk_hs04ji7kqvwd7313ryp20vo0x FOREIGN KEY (committee_proposal_list_comm_0) REFERENCES public.committee_proposal_container(hjid);


--
-- Name: document_status_container fk_iirofquegnrpnuonvnydf6wfb; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_iirofquegnrpnuonvnydf6wfb FOREIGN KEY (document_proposal_document_s_0) REFERENCES public.document_proposal_container(hjid);


--
-- Name: document_status_container fk_jjcxsqmdnjw0nbwducjyecdg4; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_jjcxsqmdnjw0nbwducjyecdg4 FOREIGN KEY (document_document_status_con_0) REFERENCES public.document_data(id);


--
-- Name: person_data fk_jrgy7nw6n071uok8p1hkp03rh; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_data
    ADD CONSTRAINT fk_jrgy7nw6n071uok8p1hkp03rh FOREIGN KEY (person_detail_data_person_da_0) REFERENCES public.person_detail_data(hjid);


--
-- Name: committee_proposal_component_0 fk_k78eqmx2m3ja0267xhthfeio4; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.committee_proposal_component_0
    ADD CONSTRAINT fk_k78eqmx2m3ja0267xhthfeio4 FOREIGN KEY (document_committee_proposal__0) REFERENCES public.committee_document_data(id);


--
-- Name: assignment_element fk_ks1811fwuno6vqof0lskerpib; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.assignment_element
    ADD CONSTRAINT fk_ks1811fwuno6vqof0lskerpib FOREIGN KEY (uppdrag_person_assignment_el_0) REFERENCES public.person_assignment_element(hjid);


--
-- Name: document_attachment fk_lean1i0p0e5rv28my9297lq22; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_attachment
    ADD CONSTRAINT fk_lean1i0p0e5rv28my9297lq22 FOREIGN KEY (document_attachment_list_doc_0) REFERENCES public.document_attachment_container(hjid);


--
-- Name: document_person_reference_da_0 fk_lsfup3rosph7239t1idorm1cd; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_person_reference_da_0
    ADD CONSTRAINT fk_lsfup3rosph7239t1idorm1cd FOREIGN KEY (document_person_reference_li_1) REFERENCES public.document_person_reference_co_0(hjid);


--
-- Name: document_proposal_container fk_m55tt4vaimgb5qk7xj9mgxmry; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_proposal_container
    ADD CONSTRAINT fk_m55tt4vaimgb5qk7xj9mgxmry FOREIGN KEY (proposal_document_proposal_c_0) REFERENCES public.document_proposal_data(hjid);


--
-- Name: person_element fk_m6dcdojsb6iv9lrego5kurr7p; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_element
    ADD CONSTRAINT fk_m6dcdojsb6iv9lrego5kurr7p FOREIGN KEY (person_assignment_element_pe_0) REFERENCES public.person_assignment_element(hjid);


--
-- Name: document_status_container fk_ng4kjnv3cm4e6ud3fikwi6p7i; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_ng4kjnv3cm4e6ud3fikwi6p7i FOREIGN KEY (document_person_reference_co_1) REFERENCES public.document_person_reference_co_0(hjid);


--
-- Name: application_action_event fk_nlqlshlogsx2g8u5d3y28my28; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_action_event
    ADD CONSTRAINT fk_nlqlshlogsx2g8u5d3y28my28 FOREIGN KEY (events_application_session_h_0) REFERENCES public.application_session(hjid);


--
-- Name: document_element fk_o24n54auwa2xyflis6nkrajpd; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_element
    ADD CONSTRAINT fk_o24n54auwa2xyflis6nkrajpd FOREIGN KEY (dokument_document_container__0) REFERENCES public.document_container_element(hjid);


--
-- Name: topic fk_o7ol28sotu1r12n8txv2gigok; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT fk_o7ol28sotu1r12n8txv2gigok FOREIGN KEY (topic_topics_hjid) REFERENCES public.topics(hjid);


--
-- Name: sweden_election_type fk_ob50ibby6jamvitbxknoucifg; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_election_type
    ADD CONSTRAINT fk_ob50ibby6jamvitbxknoucifg FOREIGN KEY (region_sweden_election_type__0) REFERENCES public.sweden_election_region(hjid);


--
-- Name: application_view fk_p8b7gnxeglk71etbbql3j184s; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_p8b7gnxeglk71etbbql3j184s FOREIGN KEY (data_source_information_appl_0) REFERENCES public.data_source_content(hjid);


--
-- Name: sweden_county_data fk_pndlg3q6ly10qbs8e3s9wikyu; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_county_data
    ADD CONSTRAINT fk_pndlg3q6ly10qbs8e3s9wikyu FOREIGN KEY (county_regions_sweden_county_0) REFERENCES public.sweden_county_data_container(hjid);


--
-- Name: qrtz_cron_triggers fk_qrtz_cron_triggers_qrtz_triggers; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_cron_triggers
    ADD CONSTRAINT fk_qrtz_cron_triggers_qrtz_triggers FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES public.qrtz_triggers(sched_name, trigger_name, trigger_group) ON DELETE CASCADE;


--
-- Name: qrtz_simple_triggers fk_qrtz_simple_triggers_qrtz_triggers; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_simple_triggers
    ADD CONSTRAINT fk_qrtz_simple_triggers_qrtz_triggers FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES public.qrtz_triggers(sched_name, trigger_name, trigger_group) ON DELETE CASCADE;


--
-- Name: qrtz_simprop_triggers fk_qrtz_simprop_triggers_qrtz_triggers; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_simprop_triggers
    ADD CONSTRAINT fk_qrtz_simprop_triggers_qrtz_triggers FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES public.qrtz_triggers(sched_name, trigger_name, trigger_group) ON DELETE CASCADE;


--
-- Name: qrtz_triggers fk_qrtz_triggers_qrtz_job_details; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.qrtz_triggers
    ADD CONSTRAINT fk_qrtz_triggers_qrtz_job_details FOREIGN KEY (sched_name, job_name, job_group) REFERENCES public.qrtz_job_details(sched_name, job_name, job_group) ON DELETE CASCADE;


--
-- Name: document_detail_data fk_quor6wesrmk9ierjyr7ni8wch; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_detail_data
    ADD CONSTRAINT fk_quor6wesrmk9ierjyr7ni8wch FOREIGN KEY (document_detail_list_documen_0) REFERENCES public.document_detail_container(hjid);


--
-- Name: sweden_parliament_electoral__1 fk_qvgtilwwyipbrv6b2cv6fcp27; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_parliament_electoral__1
    ADD CONSTRAINT fk_qvgtilwwyipbrv6b2cv6fcp27 FOREIGN KEY (parliament_electoral_regions_0) REFERENCES public.sweden_parliament_electoral__0(hjid);


--
-- Name: document_status_container fk_r2dkprhp4xfhrcck9sf31b9xl; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.document_status_container
    ADD CONSTRAINT fk_r2dkprhp4xfhrcck9sf31b9xl FOREIGN KEY (document_activity_container__0) REFERENCES public.document_activity_container(hjid);


--
-- Name: sweden_municipality_election_0 fk_r3jht5oci01uxhwaa39uxsg2t; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_municipality_election_0
    ADD CONSTRAINT fk_r3jht5oci01uxhwaa39uxsg2t FOREIGN KEY (kommunvalkrets_sweden_munici_0) REFERENCES public.sweden_municipality_data(hjid);


--
-- Name: person_data fk_rd2pmyb4gmu2vbxklh6er8ayc; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.person_data
    ADD CONSTRAINT fk_rd2pmyb4gmu2vbxklh6er8ayc FOREIGN KEY (person_assignment_data_perso_0) REFERENCES public.person_assignment_data(hjid);


--
-- Name: sweden_election_type fk_rp3tjh4jmpmoxh05oo9fq9qh6; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.sweden_election_type
    ADD CONSTRAINT fk_rp3tjh4jmpmoxh05oo9fq9qh6 FOREIGN KEY (election_types_sweden_electi_0) REFERENCES public.sweden_election_type_contain_0(hjid);


--
-- Name: application_view fk_x8sbg6y7h0vavmun6i7h8oae; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.application_view
    ADD CONSTRAINT fk_x8sbg6y7h0vavmun6i7h8oae FOREIGN KEY (operational_information_appl_0) REFERENCES public.operational_information_cont_0(hjid);


--
-- Name: jv_commit_property jv_commit_property_commit_fk; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.jv_commit_property
    ADD CONSTRAINT jv_commit_property_commit_fk FOREIGN KEY (commit_fk) REFERENCES public.jv_commit(commit_pk);


--
-- Name: jv_global_id jv_global_id_owner_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.jv_global_id
    ADD CONSTRAINT jv_global_id_owner_id_fk FOREIGN KEY (owner_id_fk) REFERENCES public.jv_global_id(global_id_pk);


--
-- Name: jv_snapshot jv_snapshot_commit_fk; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.jv_snapshot
    ADD CONSTRAINT jv_snapshot_commit_fk FOREIGN KEY (commit_fk) REFERENCES public.jv_commit(commit_pk);


--
-- Name: jv_snapshot jv_snapshot_global_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: eris
--

ALTER TABLE ONLY public.jv_snapshot
    ADD CONSTRAINT jv_snapshot_global_id_fk FOREIGN KEY (global_id_fk) REFERENCES public.jv_global_id(global_id_pk);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: TABLE view_riksdagen_party_document_summary; Type: ACL; Schema: public; Owner: eris
--

GRANT SELECT ON TABLE public.view_riksdagen_party_document_summary TO PUBLIC;


--
-- PostgreSQL database dump complete
--

\unrestrict JXHyzmlBHuu6ryPuaqNFxjhfuXPBREQdJmma0Xpy6XFA4y3lYSxXF65nhir1NSi


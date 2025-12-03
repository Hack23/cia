--
-- PostgreSQL database dump
--

\restrict cF2dIjkycQRzinXJKL9jjeEUUHOVpRBeOYrMAtbOeZSR0vowt1fnuml8qMrPdf9

-- Dumped from database version 16.10 (Ubuntu 16.10-1.pgdg24.04+1)
-- Dumped by pg_dump version 16.10 (Ubuntu 16.10-1.pgdg24.04+1)

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
-- Name: pg_stat_statements; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;


--
-- Name: EXTENSION pg_stat_statements; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION pg_stat_statements IS 'track planning and execution statistics of all SQL statements executed';


--
-- Name: pgaudit; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgaudit WITH SCHEMA public;


--
-- Name: EXTENSION pgaudit; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION pgaudit IS 'provides auditing functionality';


--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


--
-- Name: vector; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS vector WITH SCHEMA public;


--
-- Name: EXTENSION vector; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION vector IS 'vector data type and ivfflat and hnsw access methods';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: against_proposal_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.against_proposal_container (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN against_proposal_container.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.against_proposal_container.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN against_proposal_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.against_proposal_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.header; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.against_proposal_data.header IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.number_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.against_proposal_data.number_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.parties; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.against_proposal_data.parties IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.proposal_issue_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.against_proposal_data.proposal_issue_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.proposal_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.against_proposal_data.proposal_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN against_proposal_data.against_proposal_list_agains_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.against_proposal_data.against_proposal_list_agains_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN agency.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.agency.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN agency.agency_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.agency.agency_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN agency.description; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.agency.description IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN agency.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.agency.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN agency.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.agency.model_object_version IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN application_action_event.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.hjid IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.application_operation; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.application_operation IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.created_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.created_date IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.event_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.event_group IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.model_object_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.model_object_version IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.events_application_session_h_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.events_application_session_h_0 IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.session_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.session_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.page; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.page IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.page_mode; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.page_mode IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.element_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.element_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.action_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.action_name IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.user_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.user_id IS 'DATA.Sensitive GDPR.Personal_Identifier';


--
-- Name: COLUMN application_action_event.error_message; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.error_message IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_action_event.application_message; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_action_event.application_message IS 'DATA.Sensitive GDPR.Personal';


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
-- Name: COLUMN application_configuration.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.hjid IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.model_object_id IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.model_object_version IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.config_title; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.config_title IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.config_description; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.config_description IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.configuration_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.configuration_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.component; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.component IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.component_title; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.component_title IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.component_description; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.component_description IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.property_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.property_id IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.property_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.property_value IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.created_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.created_date IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN application_configuration.updated_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_configuration.updated_date IS 'DATA.Sensitive GDPR.NA';


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
-- Name: COLUMN application_session.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.hjid IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.created_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.created_date IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.ip_information; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.ip_information IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.model_object_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.model_object_version IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.session_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.session_type IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.user_agent_information; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.user_agent_information IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.session_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.session_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.locale; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.locale IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.operating_system; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.operating_system IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.user_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.user_id IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.destroyed_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.destroyed_date IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.screen_size; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.screen_size IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN application_session.time_zone; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.application_session.time_zone IS 'DATA.Sensitive GDPR.Personal';


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
-- Name: COLUMN assignment_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.assignment_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.assignment_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.detail; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.detail IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.from_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.from_date IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.intressent_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.intressent_id IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN assignment_data.order_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.order_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.org_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.org_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.role_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.role_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.status IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.to_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.to_date IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_data.assignment_list_person_assig_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_data.assignment_list_person_assig_0 IS 'DATA.Public GDPR.Personal';


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
-- Name: COLUMN assignment_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.assignment_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.assignment_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.detail; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.detail IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.from_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.from_date IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.intressent_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.intressent_id IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN assignment_element.order_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.order_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.org_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.org_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.role_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.role_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.status IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.to_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.to_date IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN assignment_element.uppdrag_person_assignment_el_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.assignment_element.uppdrag_person_assignment_el_0 IS 'DATA.Public GDPR.Personal';


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
-- Name: COLUMN committee_document_data.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.committee_proposal_url_xml; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.committee_proposal_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.created_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.document_status_url_www; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.document_status_url_www IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.document_status_url_xml; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.document_status_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.document_url_html; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.document_url_html IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.document_url_text; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.document_url_text IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.end_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.end_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.hangar_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.hangar_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.label; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.org; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.org IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.public_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.public_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.rm; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.rm IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.status IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.sub_title; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.sub_title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.sub_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.sub_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.temp_label; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.temp_label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_document_data.title; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_document_data.title IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN committee_proposal_component_0.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_component_0.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_component_0.against_proposal_container_c_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_component_0.against_proposal_container_c_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_component_0.committee_proposal_container_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_component_0.committee_proposal_container_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_component_0.document_committee_proposal__0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_component_0.document_committee_proposal__0 IS 'DATA.Public GDPR.NA';


--
-- Name: committee_proposal_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.committee_proposal_container (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN committee_proposal_container.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_container.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN committee_proposal_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.against_proposal_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.against_proposal_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.against_proposal_parties; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.against_proposal_parties IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.ballot_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.ballot_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.ballot_summary_item; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.ballot_summary_item IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.ballot_url_xml; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.ballot_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.committee_report; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.committee_report IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.decision_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.decision_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.header; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.header IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.issue_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.issue_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.proposal; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.proposal IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.rm; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.rm IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.winner; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.winner IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN committee_proposal_data.committee_proposal_list_comm_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.committee_proposal_data.committee_proposal_list_comm_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN countries_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.countries_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN countries_element.page; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.countries_element.page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN countries_element.pages; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.countries_element.pages IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN countries_element.per_page; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.countries_element.per_page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN countries_element.total; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.countries_element.total IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN country_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.adminregion_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.adminregion_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.adminregion_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.adminregion_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.capital_city; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.capital_city IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.country_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.country_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.income_level_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.income_level_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.income_level_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.income_level_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.iso_2code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.iso_2code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.latitude; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.latitude IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.lending_type_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.lending_type_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.lending_type_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.lending_type_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.longitude; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.longitude IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.region_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.region_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.region_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.region_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN country_element.country_countries_element_hj_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.country_element.country_countries_element_hj_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN data_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.data_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_element.page; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.data_element.page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_element.pages; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.data_element.pages IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_element.per_page; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.data_element.per_page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_element.total; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.data_element.total IS 'DATA.Public GDPR.NA';


--
-- Name: data_source_content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.data_source_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: COLUMN data_source_content.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.data_source_content.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_source_content.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.data_source_content.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN data_source_content.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.data_source_content.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: -
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


--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
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
-- Name: COLUMN detail_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN detail_data.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_data.code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN detail_data.detail; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_data.detail IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN detail_data.detail_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_data.detail_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN detail_data.intressent_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_data.intressent_id IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN detail_data.detail_list_person_detail_da_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_data.detail_list_person_detail_da_0 IS 'DATA.Public GDPR.Personal';


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
-- Name: COLUMN detail_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_element.hjid IS 'DATA.Public GDPR.PERSONAL';


--
-- Name: COLUMN detail_element.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_element.code IS 'DATA.Public GDPR.PERSONAL';


--
-- Name: COLUMN detail_element.detail; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_element.detail IS 'DATA.Public GDPR.PERSONAL';


--
-- Name: COLUMN detail_element.detail_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_element.detail_type IS 'DATA.Public GDPR.PERSONAL';


--
-- Name: COLUMN detail_element.intressent_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.detail_element.intressent_id IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: document_activity_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_activity_container (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN document_activity_container.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_activity_container.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN document_activity_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_activity_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.activity_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_activity_data.activity_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_activity_data.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.created_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_activity_data.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.order_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_activity_data.order_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.process; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_activity_data.process IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_activity_data.status IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_activity_data.document_activities_document_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_activity_data.document_activities_document_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN document_attachment.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_attachment.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.file_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_attachment.file_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.file_size; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_attachment.file_size IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.file_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_attachment.file_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.file_url; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_attachment.file_url IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_attachment.document_attachment_list_doc_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_attachment.document_attachment_list_doc_0 IS 'DATA.Public GDPR.NA';


--
-- Name: document_attachment_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_attachment_container (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN document_attachment_container.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_attachment_container.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN document_container_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.created; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.created IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.datum; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.datum IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.debug; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.debug IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.document_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.document_version IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.hits; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.hits IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.hits_from; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.hits_from IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.hits_to; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.hits_to IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.next_page; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.next_page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.page; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.total_pages; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.total_pages IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_container_element.warning; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_container_element.warning IS 'DATA.Public GDPR.NA';


--
-- Name: document_content_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_content_data (
    hjid bigint NOT NULL,
    content text,
    id character varying(255)
);


--
-- Name: COLUMN document_content_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_content_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_content_data.content; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_content_data.content IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_content_data.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_content_data.id IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN document_data.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.committee_report_url_xml; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.committee_report_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_status_url_www; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.document_status_url_www IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_status_url_xml; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.document_status_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.document_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_url_html; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.document_url_html IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.document_url_text; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.document_url_text IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.final_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.final_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.hangar_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.hangar_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.label; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.made_public_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.made_public_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.number_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.number_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.org; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.org IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.rm; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.rm IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.status IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.sub_title; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.sub_title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.sub_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.sub_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.temp_label; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.temp_label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_data.title; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_data.title IS 'DATA.Public GDPR.NA';


--
-- Name: document_detail_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_detail_container (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN document_detail_container.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_detail_container.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN document_detail_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_detail_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_detail_data.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_detail_data.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_detail_data.detail_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_detail_data.detail_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_detail_data.text; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_detail_data.text IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_detail_data.document_detail_list_documen_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_detail_data.document_detail_list_documen_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN document_element.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.committee_report_url_xml; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.committee_report_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.created_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_format; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.document_format IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_status_url_xml; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.document_status_url_xml IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.document_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_url_html; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.document_url_html IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_url_text; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.document_url_text IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.hit; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.hit IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.kall_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.kall_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.label; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.made_public_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.made_public_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.number_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.number_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.org; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.org IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.related_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.related_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.rm; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.rm IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.status IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.sub_title; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.sub_title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.sub_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.sub_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.system_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.system_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.temp_label; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.temp_label IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.title; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.dokument_document_container__0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.dokument_document_container__0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.domain_org; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.domain_org IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.database_source; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.database_source IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.origin; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.origin IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.lang; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.lang IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.summary; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.summary IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.note; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.note IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.note_title; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.note_title IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.debate_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.debate_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.document_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.document_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_element.doc_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_element.doc_type IS 'DATA.Public GDPR.NA';


--
-- Name: document_person_reference_co_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_person_reference_co_0 (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN document_person_reference_co_0.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_person_reference_co_0.hjid IS 'DATA.Public GDPR.Personal';


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
-- Name: COLUMN document_person_reference_da_0.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_person_reference_da_0.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.order_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_person_reference_da_0.order_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.party_short_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_person_reference_da_0.party_short_code IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.person_reference_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_person_reference_da_0.person_reference_id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.reference_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_person_reference_da_0.reference_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.role_description; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_person_reference_da_0.role_description IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN document_person_reference_da_0.document_person_reference_li_1; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_person_reference_da_0.document_person_reference_li_1 IS 'DATA.Public GDPR.Personal';


--
-- Name: document_proposal_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_proposal_container (
    hjid bigint NOT NULL,
    proposal_document_proposal_c_0 bigint
);


--
-- Name: COLUMN document_proposal_container.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_container.proposal_document_proposal_c_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_container.proposal_document_proposal_c_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN document_proposal_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.chamber; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.chamber IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.committee; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.committee IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.decision_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.decision_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.designation; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.designation IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.processed_in; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.processed_in IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.proposal_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.proposal_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.wording; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.wording IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.wording_2; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.wording_2 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.wording_3; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.wording_3 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_proposal_data.wording_4; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_proposal_data.wording_4 IS 'DATA.Public GDPR.NA';


--
-- Name: document_reference_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.document_reference_container (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN document_reference_container.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_reference_container.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN document_reference_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_reference_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_reference_data.detail; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_reference_data.detail IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_reference_data.reference_document_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_reference_data.reference_document_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_reference_data.reference_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_reference_data.reference_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_reference_data.document_reference_list_docu_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_reference_data.document_reference_list_docu_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN document_status_container.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_status_container.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_category; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_status_container.document_category IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_document_status_con_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_status_container.document_document_status_con_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_activity_container__0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_status_container.document_activity_container__0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_attachment_containe_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_status_container.document_attachment_containe_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_detail_container_do_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_status_container.document_detail_container_do_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_person_reference_co_1; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_status_container.document_person_reference_co_1 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_reference_container_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_status_container.document_reference_container_0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN document_status_container.document_proposal_document_s_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.document_status_container.document_proposal_document_s_0 IS 'DATA.Public GDPR.NA';


--
-- Name: domain_portal; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.domain_portal (
    domain_name character varying(255),
    hjid bigint NOT NULL
);


--
-- Name: COLUMN domain_portal.domain_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.domain_portal.domain_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN domain_portal.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.domain_portal.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN encrypted_value.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.encrypted_value.id IS 'DATA.Confidential GDPR.Personal_Sensitive';


--
-- Name: COLUMN encrypted_value.storage; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.encrypted_value.storage IS 'DATA.Confidential GDPR.Personal_Sensitive';


--
-- Name: COLUMN encrypted_value.user_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.encrypted_value.user_id IS 'DATA.Sensitive GDPR.Personal_Identifier';


--
-- Name: COLUMN encrypted_value.vault_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.encrypted_value.vault_name IS 'DATA.Sensitive GDPR.na';


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
-- Name: COLUMN indicator_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicator_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicator_element.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.indicator_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicator_element.indicator_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.source_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicator_element.source_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.source_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicator_element.source_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.source_note; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicator_element.source_note IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.source_organization; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicator_element.source_organization IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.topics_indicator_element_hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicator_element.topics_indicator_element_hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicator_element.indicator__indicators_elemen_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicator_element.indicator__indicators_elemen_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN indicators_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicators_element.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicators_element.page; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicators_element.page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicators_element.pages; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicators_element.pages IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicators_element.per_page; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicators_element.per_page IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN indicators_element.total; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.indicators_element.total IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN language_content_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.created_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.from_language; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.from_language IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.language_content_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.language_content_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.language_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.language_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.ref_key; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.ref_key IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.to_language; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.to_language IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.last_modified_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.last_modified_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.key_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.key_group IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_content_data.location_context; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_content_data.location_context IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN language_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.language_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.language_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.created_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.created_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.last_modified_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.last_modified_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.language_enabled; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.language_enabled IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.auto_translation_enabled; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.auto_translation_enabled IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.language_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.language_code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN language_data.translation_status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.language_data.translation_status IS 'DATA.Public GDPR.NA';


--
-- Name: operational_information_cont_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.operational_information_cont_0 (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: COLUMN operational_information_cont_0.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.operational_information_cont_0.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN operational_information_cont_0.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.operational_information_cont_0.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN operational_information_cont_0.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.operational_information_cont_0.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: performance_indicator_content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.performance_indicator_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: COLUMN performance_indicator_content.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.performance_indicator_content.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN performance_indicator_content.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.performance_indicator_content.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN performance_indicator_content.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.performance_indicator_content.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: person_assignment_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_assignment_data (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN person_assignment_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_assignment_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: person_assignment_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_assignment_element (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN person_assignment_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_assignment_element.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: person_container_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_container_data (
    hjid bigint NOT NULL,
    person_person_container_data_0 character varying(255)
);


--
-- Name: COLUMN person_container_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_container_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_container_data.person_person_container_data_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_container_data.person_person_container_data_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: person_container_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_container_element (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN person_container_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_container_element.hjid IS 'DATA.Public GDPR.Personal';


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
-- Name: COLUMN person_data.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.born_year; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.born_year IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.election_region; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.election_region IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.first_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.first_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.gender; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.gender IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.hangar_guid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.hangar_guid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.image_url_192; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.image_url_192 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.image_url_80; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.image_url_80 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.image_url_max; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.image_url_max IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.last_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.last_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.party; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.party IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.person_url_xml; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.person_url_xml IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.place; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.place IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.status IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.person_assignment_data_perso_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.person_assignment_data_perso_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_data.person_detail_data_person_da_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_data.person_detail_data_person_da_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: person_detail_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_detail_data (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN person_detail_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_detail_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: person_detail_element; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.person_detail_element (
    hjid bigint NOT NULL,
    detail_list_person_detail_el_0 bigint
);


--
-- Name: COLUMN person_detail_element.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_detail_element.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_detail_element.detail_list_person_detail_el_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_detail_element.detail_list_person_detail_el_0 IS 'DATA.Public GDPR.Personal';


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
-- Name: COLUMN person_element.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.born_year; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.born_year IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.election_region; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.election_region IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.first_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.first_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.gender; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.gender IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.hangar_guid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.hangar_guid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.image_url_192; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.image_url_192 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.image_url_80; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.image_url_80 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.image_url_max; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.image_url_max IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.last_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.last_name IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.party; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.party IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.person_url_xml; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.person_url_xml IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.place; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.place IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.status IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.person_assignment_element_pe_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.person_assignment_element_pe_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.person_detail_element_person_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.person_detail_element_person_0 IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN person_element.person_person_container_elem_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.person_element.person_person_container_elem_0 IS 'DATA.Public GDPR.Personal';


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
-- Name: COLUMN portal.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.portal.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.description; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.portal.description IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.google_map_api_key; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.portal.google_map_api_key IS 'DATA.Confidential GDPR.NA';


--
-- Name: COLUMN portal.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.portal.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.portal.model_object_version IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.portal_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.portal.portal_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.portal_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.portal.portal_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN portal.portals_agency_hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.portal.portals_agency_hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN qrtz_blob_triggers.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_blob_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_blob_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_blob_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_blob_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_blob_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_blob_triggers.job_data; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_blob_triggers.job_data IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_calendars; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_calendars (
    sched_name character varying(100) NOT NULL,
    calendar_name character varying(200) NOT NULL,
    calendar bytea NOT NULL
);


--
-- Name: COLUMN qrtz_calendars.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_calendars.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_calendars.calendar_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_calendars.calendar_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_calendars.calendar; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_calendars.calendar IS 'DATA.Sensitive GDPR.NA';


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
-- Name: COLUMN qrtz_cron_triggers.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_cron_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_cron_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_cron_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_cron_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_cron_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_cron_triggers.cron_expression; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_cron_triggers.cron_expression IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_cron_triggers.time_zone_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_cron_triggers.time_zone_id IS 'DATA.Sensitive GDPR.NA';


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
-- Name: COLUMN qrtz_fired_triggers.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.entry_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.entry_id IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.instance_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.instance_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.fired_time; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.fired_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.sched_time; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.sched_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.priority; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.priority IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.state; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.state IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.job_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.job_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.job_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.job_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.is_nonconcurrent; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.is_nonconcurrent IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_fired_triggers.requests_recovery; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_fired_triggers.requests_recovery IS 'DATA.Sensitive GDPR.NA';


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
-- Name: COLUMN qrtz_job_details.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.job_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.job_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.job_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.job_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.description; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.description IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.job_class_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.job_class_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.is_durable; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.is_durable IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.is_nonconcurrent; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.is_nonconcurrent IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.is_update_data; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.is_update_data IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.requests_recovery; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.requests_recovery IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_job_details.job_data; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_job_details.job_data IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_locks; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_locks (
    sched_name character varying(100) NOT NULL,
    lock_name character varying(40) NOT NULL
);


--
-- Name: COLUMN qrtz_locks.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_locks.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_locks.lock_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_locks.lock_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: qrtz_paused_trigger_grps; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.qrtz_paused_trigger_grps (
    sched_name character varying(100) NOT NULL,
    trigger_group character varying(200) NOT NULL
);


--
-- Name: COLUMN qrtz_paused_trigger_grps.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_paused_trigger_grps.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_paused_trigger_grps.trigger_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_paused_trigger_grps.trigger_group IS 'DATA.Sensitive GDPR.NA';


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
-- Name: COLUMN qrtz_scheduler_state.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_scheduler_state.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_scheduler_state.instance_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_scheduler_state.instance_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_scheduler_state.last_checkin_time; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_scheduler_state.last_checkin_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_scheduler_state.checkin_interval; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_scheduler_state.checkin_interval IS 'DATA.Sensitive GDPR.NA';


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
-- Name: COLUMN qrtz_simple_triggers.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simple_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simple_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simple_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.repeat_count; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simple_triggers.repeat_count IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.repeat_interval; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simple_triggers.repeat_interval IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simple_triggers.times_triggered; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simple_triggers.times_triggered IS 'DATA.Sensitive GDPR.NA';


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
-- Name: COLUMN qrtz_simprop_triggers.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.str_prop_1; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.str_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.str_prop_2; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.str_prop_2 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.str_prop_3; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.str_prop_3 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.int_prop_1; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.int_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.int_prop_2; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.int_prop_2 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.long_prop_1; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.long_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.long_prop_2; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.long_prop_2 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.dec_prop_1; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.dec_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.dec_prop_2; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.dec_prop_2 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.bool_prop_1; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.bool_prop_1 IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_simprop_triggers.bool_prop_2; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_simprop_triggers.bool_prop_2 IS 'DATA.Sensitive GDPR.NA';


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
-- Name: COLUMN qrtz_triggers.sched_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.sched_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.trigger_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.trigger_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.trigger_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.trigger_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.job_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.job_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.job_group; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.job_group IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.description; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.description IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.next_fire_time; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.next_fire_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.prev_fire_time; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.prev_fire_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.priority; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.priority IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.trigger_state; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.trigger_state IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.trigger_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.trigger_type IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.start_time; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.start_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.end_time; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.end_time IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.calendar_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.calendar_name IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.misfire_instr; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.misfire_instr IS 'DATA.Sensitive GDPR.NA';


--
-- Name: COLUMN qrtz_triggers.job_data; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.qrtz_triggers.job_data IS 'DATA.Sensitive GDPR.NA';


--
-- Name: quality_assurance_content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.quality_assurance_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: COLUMN quality_assurance_content.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.quality_assurance_content.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN quality_assurance_content.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.quality_assurance_content.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN quality_assurance_content.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.quality_assurance_content.model_object_version IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN sweden_county_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_data.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_data.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_data.county_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_data.county_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_data.county_regions_sweden_county_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_data.county_regions_sweden_county_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_county_data_container; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_county_data_container (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN sweden_county_data_container.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_data_container.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN sweden_county_electoral_area.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_area.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_area.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.electoral_area_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_area.electoral_area_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.first_round; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_area.first_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.number_of_seats; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_area.number_of_seats IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.number_of_voters; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_area.number_of_voters IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.rest; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_area.rest IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.second_round; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_area.second_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_area.landstingsvalkrets_sweden_co_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_area.landstingsvalkrets_sweden_co_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_county_electoral_regi_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_county_electoral_regi_0 (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN sweden_county_electoral_regi_0.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_0.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN sweden_county_electoral_regi_1.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_regi_1.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_regi_1.county_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.county_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_regi_1.seats; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.seats IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_county_electoral_regi_1.county_electoral_regions_swe_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_county_electoral_regi_1.county_electoral_regions_swe_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN sweden_election_region.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_region.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_region.county_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_region.county_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_region.municipal_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_region.municipal_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_region.region_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_region.region_name IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN sweden_election_type.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_type.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_type.election_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_type.election_code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_type.election_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_type.election_type IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_type.region_sweden_election_type__0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_type.region_sweden_election_type__0 IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_election_type.election_types_sweden_electi_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_type.election_types_sweden_electi_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_election_type_contain_0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_election_type_contain_0 (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN sweden_election_type_contain_0.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_election_type_contain_0.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN sweden_municipality_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_data.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_data.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_data.municipal_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_data.municipal_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_data.kommun_sweden_county_data_hj_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_data.kommun_sweden_county_data_hj_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN sweden_municipality_election_0.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_election_0.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_election_0.code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.election_region_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_election_0.election_region_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.first_round; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_election_0.first_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.number_of_seats; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_election_0.number_of_seats IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.number_of_voters; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_election_0.number_of_voters IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.rest; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_election_0.rest IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.second_round; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_election_0.second_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_municipality_election_0.kommunvalkrets_sweden_munici_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_municipality_election_0.kommunvalkrets_sweden_munici_0 IS 'DATA.Public GDPR.NA';


--
-- Name: sweden_parliament_electoral__0; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sweden_parliament_electoral__0 (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN sweden_parliament_electoral__0.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_parliament_electoral__0.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN sweden_parliament_electoral__1.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.election_region_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.election_region_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.first_round; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.first_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.number_of_seats; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.number_of_seats IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.number_of_voters; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.number_of_voters IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.rest; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.rest IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.second_round; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.second_round IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_parliament_electoral__1.parliament_electoral_regions_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_parliament_electoral__1.parliament_electoral_regions_0 IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN sweden_political_party.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.address; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.address IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.city; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.city IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.co_address; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.co_address IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.email; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.email IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.fax_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.fax_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.party_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.party_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.party_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.party_name IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.phone_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.phone_number IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.post_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.post_code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.registered_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.registered_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.short_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.short_code IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.website; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.website IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN sweden_political_party.parties_sweden_election_regi_0; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sweden_political_party.parties_sweden_election_regi_0 IS 'DATA.Public GDPR.NA';


--
-- Name: target_profile_content; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.target_profile_content (
    hjid bigint NOT NULL,
    model_object_id integer,
    model_object_version integer
);


--
-- Name: COLUMN target_profile_content.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.target_profile_content.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN target_profile_content.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.target_profile_content.model_object_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN target_profile_content.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.target_profile_content.model_object_version IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN topic.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.topic.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN topic.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.topic.id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN topic.value_; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.topic.value_ IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN topic.topic_topics_hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.topic.topic_topics_hjid IS 'DATA.Public GDPR.NA';


--
-- Name: topics; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.topics (
    hjid bigint NOT NULL
);


--
-- Name: COLUMN topics.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.topics.hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN user_account.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.hjid IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.email; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.email IS 'DATA.Sensitive GDPR.Personal_Identifier';


--
-- Name: COLUMN user_account.model_object_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.model_object_id IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.model_object_version; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.model_object_version IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.number_of_visits; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.number_of_visits IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.user_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.user_id IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.user_role; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.user_role IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.user_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.user_type IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.username; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.username IS 'DATA.Sensitive GDPR.Personal_Identifier';


--
-- Name: COLUMN user_account.userpassword; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.userpassword IS 'DATA.Confidential GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.country; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.country IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN user_account.created_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.created_date IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN user_account.user_lock_status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.user_lock_status IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: COLUMN user_account.user_email_status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account.user_email_status IS 'DATA.Sensitive GDPR.Personal_Sensitive';


--
-- Name: user_account_address; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_account_address (
    hjid bigint NOT NULL,
    hjvalue character varying(255),
    hjindex integer NOT NULL
);


--
-- Name: COLUMN user_account_address.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account_address.hjid IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN user_account_address.hjvalue; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account_address.hjvalue IS 'DATA.Sensitive GDPR.Personal';


--
-- Name: COLUMN user_account_address.hjindex; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.user_account_address.hjindex IS 'DATA.Sensitive GDPR.Personal';


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
-- Name: COLUMN vote_data.embedded_id_ballot_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.embedded_id_ballot_id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.embedded_id_concern; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.embedded_id_concern IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.embedded_id_intressent_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.embedded_id_intressent_id IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.embedded_id_issue; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.embedded_id_issue IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.ballot_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.ballot_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.bank_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.bank_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.born_year; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.born_year IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.electoral_region; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.electoral_region IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.electoral_region_number; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.electoral_region_number IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.first_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.first_name IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.full_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.full_name IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.gender; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.gender IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.label; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.label IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.last_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.last_name IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.party; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.party IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.place; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.place IS 'DATA.Public GDPR.Personal_Identifier';


--
-- Name: COLUMN vote_data.rm; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.rm IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.vote; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.vote IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_data.vote_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_data.vote_date IS 'DATA.Public GDPR.Personal';


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
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary IS 'Contains aggregated voting data per ballot. Updated through refresh_riksdagen_views()';


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
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary IS 'Contains party-level voting statistics and analyses. Updated through refresh_riksdagen_views()';


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
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_politician_summary IS 'Contains individual politician voting patterns and analysis, including party alignment and rebel votes';


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
                    WHEN ((a.org_code)::text = 'kam'::text) THEN 'Speaker’s Office'::text
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
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_daily; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_party_summary_daily IS 'Daily party-level voting statistics with detailed metrics and success rates';


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
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_daily; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_daily IS 'Daily aggregation of voting statistics';


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
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_annual; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_annual IS 'Annual aggregation of voting statistics with averages and percentages';


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
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_monthly; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_monthly IS 'Monthly aggregation of voting statistics';


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
-- Name: MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_weekly; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON MATERIALIZED VIEW public.view_riksdagen_vote_data_ballot_summary_weekly IS 'Weekly aggregation of voting statistics';


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
-- Name: COLUMN world_bank_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.world_bank_data.hjid IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.country_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.world_bank_data.country_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.country_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.world_bank_data.country_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.data_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.world_bank_data.data_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.indicator_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.world_bank_data.indicator_id IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.indicator_value; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.world_bank_data.indicator_value IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.year_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.world_bank_data.year_date IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN world_bank_data.data__data_element_hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.world_bank_data.data__data_element_hjid IS 'DATA.Public GDPR.NA';


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
-- Name: COLUMN vote_meta_data.hjid; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_meta_data.hjid IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_meta_data.ballot_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_meta_data.ballot_type IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_meta_data.group_behavior; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_meta_data.group_behavior IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_meta_data.outcome; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_meta_data.outcome IS 'DATA.Public GDPR.NA';


--
-- Name: COLUMN vote_meta_data.proffessional_behavior; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_meta_data.proffessional_behavior IS 'DATA.Public GDPR.Personal';


--
-- Name: COLUMN vote_meta_data.target; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.vote_meta_data.target IS 'DATA.Public GDPR.Personal_Identifier';


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
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


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

\unrestrict cF2dIjkycQRzinXJKL9jjeEUUHOVpRBeOYrMAtbOeZSR0vowt1fnuml8qMrPdf9

--
-- PostgreSQL database dump
--

\restrict cdfD8dHpF44kWCkaICLMKVzgbOrdnHNQcjWMRMoaAEpRIkvsfgsB7mmjsQ43T1a

-- Dumped from database version 16.10 (Ubuntu 16.10-1.pgdg24.04+1)
-- Dumped by pg_dump version 16.10 (Ubuntu 16.10-1.pgdg24.04+1)

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
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
1414872417007-1	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.703535	1	EXECUTED	9:a17731710305111df20dcfac7e5b1160	createSequence sequenceName=hibernate_sequence		\N	5.0.1	\N	\N	4727109315
1414872417007-2	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.710651	2	EXECUTED	9:bebfee0168369dd117b920db419f5ba0	createTable tableName=against_proposal_container		\N	5.0.1	\N	\N	4727109315
1414872417007-3	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.716488	3	EXECUTED	9:3b16d9891a4d1d8ea22473bc46c7853c	createTable tableName=against_proposal_data		\N	5.0.1	\N	\N	4727109315
1414872417007-4	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.722905	4	EXECUTED	9:02e3a0b58935a93e8e22b859d6e244ff	createTable tableName=agency		\N	5.0.1	\N	\N	4727109315
1414872417007-5	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.729686	5	EXECUTED	9:3fe4740c8b6b63adf7e6cd409d028222	createTable tableName=aggregated_bug_data		\N	5.0.1	\N	\N	4727109315
1414872417007-6	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.735597	6	EXECUTED	9:cb7c902d90871400b0f50e6517ae2e8b	createTable tableName=aggregated_country_data		\N	5.0.1	\N	\N	4727109315
1414872417007-7	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.741483	7	EXECUTED	9:54b3e671310d1733185a14870793775c	createTable tableName=application_action_event		\N	5.0.1	\N	\N	4727109315
1414872417007-8	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.747118	8	EXECUTED	9:415d9684eeca14269142e3c4af51ef8a	createTable tableName=application_session		\N	5.0.1	\N	\N	4727109315
1414872417007-9	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.752841	9	EXECUTED	9:4e882f95194b32ed19be40bc1c5d81db	createTable tableName=application_view		\N	5.0.1	\N	\N	4727109315
1414872417007-10	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.75945	10	EXECUTED	9:8310007bdad3e542147e9f0c9e6280b2	createTable tableName=assignment_data		\N	5.0.1	\N	\N	4727109315
1414872417007-11	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.766462	11	EXECUTED	9:e2a725018be9c645cc40cee1bf741de4	createTable tableName=assignment_element		\N	5.0.1	\N	\N	4727109315
1414872417007-12	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.774035	12	EXECUTED	9:539351894788ad2df1f65ed4d9dc6394	createTable tableName=committee_document_data		\N	5.0.1	\N	\N	4727109315
1414872417007-13	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.778316	13	EXECUTED	9:0740ea87d5e697e23d68d86cded379f6	createTable tableName=committee_proposal_component_0		\N	5.0.1	\N	\N	4727109315
1414872417007-14	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.78185	14	EXECUTED	9:531e4b3e23b59219af608aa521c60e1d	createTable tableName=committee_proposal_container		\N	5.0.1	\N	\N	4727109315
1414872417007-15	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.788381	15	EXECUTED	9:0cddcb46baaa15f458ceaa0022e10daf	createTable tableName=committee_proposal_data		\N	5.0.1	\N	\N	4727109315
1414872417007-16	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.793415	16	EXECUTED	9:6fd8d778cf34128bd204c56041b7d25f	createTable tableName=countries_element		\N	5.0.1	\N	\N	4727109315
1414872417007-17	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.801656	17	EXECUTED	9:46c38f6afb69bb4d0a78fba4b029f24c	createTable tableName=country_element		\N	5.0.1	\N	\N	4727109315
1414872417007-18	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.807644	18	EXECUTED	9:6906125604ba9910f945c7b07fd92962	createTable tableName=data_element		\N	5.0.1	\N	\N	4727109315
1414872417007-19	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.81168	19	EXECUTED	9:687a141851b84ed8986487e2f3f8e4e7	createTable tableName=data_source_content		\N	5.0.1	\N	\N	4727109315
1414872417007-20	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.817309	20	EXECUTED	9:409c0b3f5c827c4bf3c7421647dadb6a	createTable tableName=detail_data		\N	5.0.1	\N	\N	4727109315
1414872417007-21	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.823003	21	EXECUTED	9:37c989d40b81428a93a2e8c29b6d8cb2	createTable tableName=detail_element		\N	5.0.1	\N	\N	4727109315
1414872417007-22	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.826858	22	EXECUTED	9:5553ce8a3c28a583d259d7daafa9492f	createTable tableName=document_activity_container		\N	5.0.1	\N	\N	4727109315
1414872417007-23	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.832663	23	EXECUTED	9:91ca8c9ef2f1a0d4038a8f00ee9cfe48	createTable tableName=document_activity_data		\N	5.0.1	\N	\N	4727109315
1414872417007-24	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.839078	24	EXECUTED	9:6a981fe99433f472f37d626f792fd93e	createTable tableName=document_attachment		\N	5.0.1	\N	\N	4727109315
1414872417007-25	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.842588	25	EXECUTED	9:67651a14797870e3d71860f7886fb744	createTable tableName=document_attachment_container		\N	5.0.1	\N	\N	4727109315
1414872417007-26	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.848423	26	EXECUTED	9:363d579e1857630034ab68bb1ba09a54	createTable tableName=document_container_element		\N	5.0.1	\N	\N	4727109315
1414872417007-27	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.854284	27	EXECUTED	9:9cac16f6bb7fb5311b1c8210c62e8c6b	createTable tableName=document_content_data		\N	5.0.1	\N	\N	4727109315
1414872417007-28	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.861023	28	EXECUTED	9:eb4858110e94dda8090c4a09e2f019f3	createTable tableName=document_data		\N	5.0.1	\N	\N	4727109315
1414872417007-29	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.864525	29	EXECUTED	9:50b71c994cff70c92759390afb6d2b27	createTable tableName=document_detail_container		\N	5.0.1	\N	\N	4727109315
1414872417007-30	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.869857	30	EXECUTED	9:5eb7cc4f4e5da72a6d201a157ba3fed5	createTable tableName=document_detail_data		\N	5.0.1	\N	\N	4727109315
1414872417007-31	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.877038	31	EXECUTED	9:9d2fbddccf267f2773209465ff52cd57	createTable tableName=document_element		\N	5.0.1	\N	\N	4727109315
1414872417007-32	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.880435	32	EXECUTED	9:2de9b22e4860ecb9a912012f0cd0e429	createTable tableName=document_person_reference_co_0		\N	5.0.1	\N	\N	4727109315
1414872417007-33	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.885787	33	EXECUTED	9:81a2a16ef0155742bd93f54156b15812	createTable tableName=document_person_reference_da_0		\N	5.0.1	\N	\N	4727109315
1414872417007-34	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.889318	34	EXECUTED	9:65bb5a1b564a86e6817effbab9bde0d3	createTable tableName=document_reference_container		\N	5.0.1	\N	\N	4727109315
1414872417007-35	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.895817	35	EXECUTED	9:b6f271d72874d68d804fbbcc89bedcaa	createTable tableName=document_reference_data		\N	5.0.1	\N	\N	4727109315
1414872417007-36	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.901529	36	EXECUTED	9:d0adee5ea53440f01cf604c105f9aa56	createTable tableName=document_status_container		\N	5.0.1	\N	\N	4727109315
1414872417007-37	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.905034	37	EXECUTED	9:40d45e65bc2539a7d1faf45c6b9af3b2	createTable tableName=domain_portal		\N	5.0.1	\N	\N	4727109315
1414872417007-38	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.910629	38	EXECUTED	9:6a1b6c5054ffe7b83bf458b6d9d63010	createTable tableName=indicator_element		\N	5.0.1	\N	\N	4727109315
1414872417007-39	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.916694	39	EXECUTED	9:df16293062101f9e2f2552533409513c	createTable tableName=indicators_element		\N	5.0.1	\N	\N	4727109315
1414872417007-40	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.921804	40	EXECUTED	9:2924e43a2bfc8ab8ece4c7b7fc886751	createTable tableName=language_content_data		\N	5.0.1	\N	\N	4727109315
1414872417007-41	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.925512	41	EXECUTED	9:ad01e46b6ba2c604286127713ac8dbbd	createTable tableName=language_data		\N	5.0.1	\N	\N	4727109315
1414872417007-42	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.928928	42	EXECUTED	9:79ef7742f91be7455bd9dfa79657131b	createTable tableName=operational_information_cont_0		\N	5.0.1	\N	\N	4727109315
1414872417007-43	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.93218	43	EXECUTED	9:fa41612149189e9606f6ef67cbb39911	createTable tableName=performance_indicator_content		\N	5.0.1	\N	\N	4727109315
1414872417007-44	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.935342	44	EXECUTED	9:0e9e417a0f56444002cbcd5958e1263e	createTable tableName=person_assignment_data		\N	5.0.1	\N	\N	4727109315
1414872417007-45	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.93869	45	EXECUTED	9:2fefd56ec64a6b4c49b1def045d6396a	createTable tableName=person_assignment_element		\N	5.0.1	\N	\N	4727109315
1414872417007-46	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.942135	46	EXECUTED	9:9a28a213df35828e2be714b4d835e8d7	createTable tableName=person_container_data		\N	5.0.1	\N	\N	4727109315
1414872417007-47	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.945105	47	EXECUTED	9:1c0bac7e7ad922b52a618676b42062a8	createTable tableName=person_container_element		\N	5.0.1	\N	\N	4727109315
1414872417007-48	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.951307	48	EXECUTED	9:10b817f4fcc09c63904d708e1461c5b7	createTable tableName=person_data		\N	5.0.1	\N	\N	4727109315
1414872417007-49	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.954535	49	EXECUTED	9:38d2239d109bf8b3b63dced29595a4f0	createTable tableName=person_detail_data		\N	5.0.1	\N	\N	4727109315
1414872417007-50	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.958294	50	EXECUTED	9:0cfe316d63aba7817bbc73a2430ff7dc	createTable tableName=person_detail_element		\N	5.0.1	\N	\N	4727109315
1414872417007-51	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.964736	51	EXECUTED	9:ad721b60c177dedbbc3fdf5ccbefa90c	createTable tableName=person_element		\N	5.0.1	\N	\N	4727109315
1414872417007-52	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.969859	52	EXECUTED	9:96452dd1a3d5f9b7484eee940aa6c845	createTable tableName=portal		\N	5.0.1	\N	\N	4727109315
1414872417007-53	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.973152	53	EXECUTED	9:4bd5e7ade89c1e867b432998577ee25a	createTable tableName=quality_assurance_content		\N	5.0.1	\N	\N	4727109315
1414872417007-54	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.977683	54	EXECUTED	9:8d843eb1a26a4558446e4be5914dd5fe	createTable tableName=sweden_county_data		\N	5.0.1	\N	\N	4727109315
1414872417007-55	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.981306	55	EXECUTED	9:a671b76e045284f9ed125b93cd347528	createTable tableName=sweden_county_data_container		\N	5.0.1	\N	\N	4727109315
1414872417007-56	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.987075	56	EXECUTED	9:5d88b4f2bc6c9b00fd7dcfacb1848770	createTable tableName=sweden_county_electoral_area		\N	5.0.1	\N	\N	4727109315
1414872417007-57	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.990354	57	EXECUTED	9:c2b1be2b1568b0adffeadb4e60e07aa4	createTable tableName=sweden_county_electoral_regi_0		\N	5.0.1	\N	\N	4727109315
1414872417007-58	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.994919	58	EXECUTED	9:df3cb3fdfce9bf4f6805fb84e1b9ed8a	createTable tableName=sweden_county_electoral_regi_1		\N	5.0.1	\N	\N	4727109315
1414872417007-59	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:31.999915	59	EXECUTED	9:d14dfb21a1e78aad5fb77cb561fce391	createTable tableName=sweden_election_region		\N	5.0.1	\N	\N	4727109315
1414872417007-60	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.005172	60	EXECUTED	9:3bba4dd4cd8837bef66ccbad72ba7e48	createTable tableName=sweden_election_type		\N	5.0.1	\N	\N	4727109315
1414872417007-61	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.008389	61	EXECUTED	9:5324df8331f298acdc38fd7cf12b8258	createTable tableName=sweden_election_type_contain_0		\N	5.0.1	\N	\N	4727109315
1414872417007-62	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.013229	62	EXECUTED	9:633ea9271e4ae43b4821f604df1f59fa	createTable tableName=sweden_municipality_data		\N	5.0.1	\N	\N	4727109315
1414872417007-63	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.01853	63	EXECUTED	9:6d1944c67dd25f3a0648f9b1a5aa38b4	createTable tableName=sweden_municipality_election_0		\N	5.0.1	\N	\N	4727109315
1414872417007-64	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.021781	64	EXECUTED	9:b95825c026136a5346b16b9353ac5096	createTable tableName=sweden_parliament_electoral__0		\N	5.0.1	\N	\N	4727109315
1414872417007-65	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.026628	65	EXECUTED	9:08c4da69bcd3b32d1f3f73121e4cf374	createTable tableName=sweden_parliament_electoral__1		\N	5.0.1	\N	\N	4727109315
1414872417007-66	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.033512	66	EXECUTED	9:39166b5937a6de6e1e119be89a5768d4	createTable tableName=sweden_political_party		\N	5.0.1	\N	\N	4727109315
1414872417007-67	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.03687	67	EXECUTED	9:206e57b2f1ab489aedd5f7f77b76f5e8	createTable tableName=target_profile_content		\N	5.0.1	\N	\N	4727109315
1414872417007-68	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.041482	68	EXECUTED	9:36d1a12fecba42815db61876f9ffee82	createTable tableName=topic		\N	5.0.1	\N	\N	4727109315
1414872417007-69	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.044594	69	EXECUTED	9:74df0d4b5c616a83e5949698a4e5a8cc	createTable tableName=topics		\N	5.0.1	\N	\N	4727109315
1414872417007-70	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.049661	70	EXECUTED	9:0b270579251a30288c9ed0f7ce9cff3f	createTable tableName=user_account		\N	5.0.1	\N	\N	4727109315
1414872417007-71	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.053055	71	EXECUTED	9:d6f00140ffba825b869701ef3f17acc8	createTable tableName=user_account_address		\N	5.0.1	\N	\N	4727109315
1414872417007-72	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.063006	72	EXECUTED	9:3daaf3a53dcab5c0643c4d341da21ec7	createTable tableName=vote_data		\N	5.0.1	\N	\N	4727109315
1414872417007-73	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.067963	73	EXECUTED	9:f846b11d91b49720ec2c460922d362f7	createTable tableName=vote_meta_data		\N	5.0.1	\N	\N	4727109315
1414872417007-74	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.072995	74	EXECUTED	9:aa5adf76544d09e8999dabd5bba4718d	createTable tableName=world_bank_data		\N	5.0.1	\N	\N	4727109315
1414872417007-75	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.07771	75	EXECUTED	9:5f830995c29a8f2e62b79c93b739826a	addPrimaryKey constraintName=against_proposal_container_pkey, tableName=against_proposal_container		\N	5.0.1	\N	\N	4727109315
1414872417007-76	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.081345	76	EXECUTED	9:bba09af773c137dde53bcec477dc7be1	addPrimaryKey constraintName=against_proposal_data_pkey, tableName=against_proposal_data		\N	5.0.1	\N	\N	4727109315
1414872417007-77	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.084948	77	EXECUTED	9:e1e45c8df3139a437fa9282e167b2a49	addPrimaryKey constraintName=agency_pkey, tableName=agency		\N	5.0.1	\N	\N	4727109315
1414872417007-78	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.088707	78	EXECUTED	9:74ab57d4e742f0f890d089863fe99534	addPrimaryKey constraintName=aggregated_bug_data_pkey, tableName=aggregated_bug_data		\N	5.0.1	\N	\N	4727109315
1414872417007-79	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.092533	79	EXECUTED	9:43c9e9f0ca5431b8014b60e80d39d58f	addPrimaryKey constraintName=aggregated_country_data_pkey, tableName=aggregated_country_data		\N	5.0.1	\N	\N	4727109315
1414872417007-80	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.09632	80	EXECUTED	9:e40121efa7c2cb31c5579a83867222b9	addPrimaryKey constraintName=application_action_event_pkey, tableName=application_action_event		\N	5.0.1	\N	\N	4727109315
1414872417007-81	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.09987	81	EXECUTED	9:5e7a0cc84a0279421abc59b37fac7cbc	addPrimaryKey constraintName=application_session_pkey, tableName=application_session		\N	5.0.1	\N	\N	4727109315
1414872417007-82	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.103419	82	EXECUTED	9:8e761f77ad53316ecde3ae62be2f56e5	addPrimaryKey constraintName=application_view_pkey, tableName=application_view		\N	5.0.1	\N	\N	4727109315
1414872417007-83	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.107147	83	EXECUTED	9:d65482d7b31647dc4109f47a336ca0ba	addPrimaryKey constraintName=assignment_data_pkey, tableName=assignment_data		\N	5.0.1	\N	\N	4727109315
1414872417007-84	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.110641	84	EXECUTED	9:5fd2d99287f3fc7dc27fdeefa086f79c	addPrimaryKey constraintName=assignment_element_pkey, tableName=assignment_element		\N	5.0.1	\N	\N	4727109315
1414872417007-85	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.11422	85	EXECUTED	9:ee126e8d0870490635cafe2ce677b999	addPrimaryKey constraintName=committee_document_data_pkey, tableName=committee_document_data		\N	5.0.1	\N	\N	4727109315
1414872417007-86	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.11745	86	EXECUTED	9:80032ba89b6bd19cfd978fa3956b3863	addPrimaryKey constraintName=committee_proposal_component_0_pkey, tableName=committee_proposal_component_0		\N	5.0.1	\N	\N	4727109315
1414872417007-87	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.120794	87	EXECUTED	9:233f9d1a6e53489679d9a65fe698416a	addPrimaryKey constraintName=committee_proposal_container_pkey, tableName=committee_proposal_container		\N	5.0.1	\N	\N	4727109315
1414872417007-88	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.124204	88	EXECUTED	9:997901d2eee29b9ec4d46710ab3131a4	addPrimaryKey constraintName=committee_proposal_data_pkey, tableName=committee_proposal_data		\N	5.0.1	\N	\N	4727109315
1414872417007-89	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.127862	89	EXECUTED	9:c1f8f3587b607f95154d9d70467c9c5e	addPrimaryKey constraintName=countries_element_pkey, tableName=countries_element		\N	5.0.1	\N	\N	4727109315
1414872417007-90	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.131435	90	EXECUTED	9:40ccd4df593a2a66cd59300c2f5c7f15	addPrimaryKey constraintName=country_element_pkey, tableName=country_element		\N	5.0.1	\N	\N	4727109315
1414872417007-91	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.134745	91	EXECUTED	9:8308944e4e2c08b501b936a559f8afe1	addPrimaryKey constraintName=data_element_pkey, tableName=data_element		\N	5.0.1	\N	\N	4727109315
1414872417007-92	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.138064	92	EXECUTED	9:dfe68f89fa66095a18d2bb701148737f	addPrimaryKey constraintName=data_source_content_pkey, tableName=data_source_content		\N	5.0.1	\N	\N	4727109315
1414872417007-93	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.141894	93	EXECUTED	9:100740b8d9c1ea7e9ff9e242ab5f091f	addPrimaryKey constraintName=detail_data_pkey, tableName=detail_data		\N	5.0.1	\N	\N	4727109315
1414872417007-94	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.14517	94	EXECUTED	9:155d55d6251bd89a8ef243dd207ff3dd	addPrimaryKey constraintName=detail_element_pkey, tableName=detail_element		\N	5.0.1	\N	\N	4727109315
1414872417007-95	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.148771	95	EXECUTED	9:1f5717b882823e279aab6ce2f3e14ff5	addPrimaryKey constraintName=document_activity_container_pkey, tableName=document_activity_container		\N	5.0.1	\N	\N	4727109315
1414872417007-96	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.15214	96	EXECUTED	9:0f77822e71c514105619468165aae6d5	addPrimaryKey constraintName=document_activity_data_pkey, tableName=document_activity_data		\N	5.0.1	\N	\N	4727109315
1414872417007-97	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.155341	97	EXECUTED	9:cd6f418a41124aea3c186ee765665519	addPrimaryKey constraintName=document_attachment_container_pkey, tableName=document_attachment_container		\N	5.0.1	\N	\N	4727109315
1414872417007-98	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.158974	98	EXECUTED	9:b5d8dffc4ae85d22f7f4787d36043f4b	addPrimaryKey constraintName=document_attachment_pkey, tableName=document_attachment		\N	5.0.1	\N	\N	4727109315
1414872417007-99	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.162321	99	EXECUTED	9:2bfd0b28a7b11c92c03453704e522bda	addPrimaryKey constraintName=document_container_element_pkey, tableName=document_container_element		\N	5.0.1	\N	\N	4727109315
1414872417007-100	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.16594	100	EXECUTED	9:6395d6fc88ce72da42c56ff1c5e07867	addPrimaryKey constraintName=document_content_data_pkey, tableName=document_content_data		\N	5.0.1	\N	\N	4727109315
1414872417007-101	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.169542	101	EXECUTED	9:b32a6f1fe5048a5e0e85a05e111727e5	addPrimaryKey constraintName=document_data_pkey, tableName=document_data		\N	5.0.1	\N	\N	4727109315
1414872417007-102	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.172958	102	EXECUTED	9:2d9aaf92aee97b16ac125a5fc0e8dfcb	addPrimaryKey constraintName=document_detail_container_pkey, tableName=document_detail_container		\N	5.0.1	\N	\N	4727109315
1414872417007-103	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.176436	103	EXECUTED	9:860b27b44274c68226953af12bb7c2ba	addPrimaryKey constraintName=document_detail_data_pkey, tableName=document_detail_data		\N	5.0.1	\N	\N	4727109315
1414872417007-104	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.179985	104	EXECUTED	9:9f5914ca5b49f8cf29c24b7a85407e6d	addPrimaryKey constraintName=document_element_pkey, tableName=document_element		\N	5.0.1	\N	\N	4727109315
1414872417007-105	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.183386	105	EXECUTED	9:646fe77744ba782ad29a05f9758c726b	addPrimaryKey constraintName=document_person_reference_co_0_pkey, tableName=document_person_reference_co_0		\N	5.0.1	\N	\N	4727109315
1414872417007-106	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.18689	106	EXECUTED	9:066a0972b05e23920e12face81e6578b	addPrimaryKey constraintName=document_person_reference_da_0_pkey, tableName=document_person_reference_da_0		\N	5.0.1	\N	\N	4727109315
1414872417007-107	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.190148	107	EXECUTED	9:624552adc8948bf35b58b1d9ef3a543a	addPrimaryKey constraintName=document_reference_container_pkey, tableName=document_reference_container		\N	5.0.1	\N	\N	4727109315
1414872417007-108	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.193355	108	EXECUTED	9:9517d4a16d854bb77a5a8364e456c6fa	addPrimaryKey constraintName=document_reference_data_pkey, tableName=document_reference_data		\N	5.0.1	\N	\N	4727109315
1414872417007-109	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.196671	109	EXECUTED	9:9c3886a2a01a213cc0388b42cefa9c31	addPrimaryKey constraintName=document_status_container_pkey, tableName=document_status_container		\N	5.0.1	\N	\N	4727109315
1414872417007-110	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.199851	110	EXECUTED	9:5a47eb0ef2899df6880ccf0181487f36	addPrimaryKey constraintName=domain_portal_pkey, tableName=domain_portal		\N	5.0.1	\N	\N	4727109315
1414872417007-111	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.203095	111	EXECUTED	9:9bb637485c9c31166e28ee005df28382	addPrimaryKey constraintName=indicator_element_pkey, tableName=indicator_element		\N	5.0.1	\N	\N	4727109315
1414872417007-112	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.20645	112	EXECUTED	9:94c16cc4cb00a952a848f47b4d537f35	addPrimaryKey constraintName=indicators_element_pkey, tableName=indicators_element		\N	5.0.1	\N	\N	4727109315
1414872417007-113	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.209821	113	EXECUTED	9:9008e44a163d1c21e27bfc226f5f3637	addPrimaryKey constraintName=language_content_data_pkey, tableName=language_content_data		\N	5.0.1	\N	\N	4727109315
1414872417007-114	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.213124	114	EXECUTED	9:c883a7c469d1ebb67a81bf9e174ab4cf	addPrimaryKey constraintName=language_data_pkey, tableName=language_data		\N	5.0.1	\N	\N	4727109315
1414872417007-115	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.216664	115	EXECUTED	9:13ceee93ff17b702a450945b3bcb3249	addPrimaryKey constraintName=operational_information_cont_0_pkey, tableName=operational_information_cont_0		\N	5.0.1	\N	\N	4727109315
1414872417007-116	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.219998	116	EXECUTED	9:1524d7c6bd18a8d8a719c4f573486968	addPrimaryKey constraintName=performance_indicator_content_pkey, tableName=performance_indicator_content		\N	5.0.1	\N	\N	4727109315
1414872417007-117	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.223451	117	EXECUTED	9:04a2103790734cfce99dff36d2609ef1	addPrimaryKey constraintName=person_assignment_data_pkey, tableName=person_assignment_data		\N	5.0.1	\N	\N	4727109315
1414872417007-118	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.226785	118	EXECUTED	9:1ab775e2f146e761344cdb951894eae3	addPrimaryKey constraintName=person_assignment_element_pkey, tableName=person_assignment_element		\N	5.0.1	\N	\N	4727109315
1414872417007-119	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.230112	119	EXECUTED	9:9b2fa87e2c12c84e1f4a6cfcbf20c839	addPrimaryKey constraintName=person_container_data_pkey, tableName=person_container_data		\N	5.0.1	\N	\N	4727109315
1414872417007-120	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.233261	120	EXECUTED	9:efc05607aaa1e96f1c09107d6f334c8e	addPrimaryKey constraintName=person_container_element_pkey, tableName=person_container_element		\N	5.0.1	\N	\N	4727109315
1414872417007-121	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.23677	121	EXECUTED	9:6e35a4dcd47a49efd33421bc9f20fb66	addPrimaryKey constraintName=person_data_pkey, tableName=person_data		\N	5.0.1	\N	\N	4727109315
1414872417007-122	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.240216	122	EXECUTED	9:cf067de81b62440865e1ceb695d746fa	addPrimaryKey constraintName=person_detail_data_pkey, tableName=person_detail_data		\N	5.0.1	\N	\N	4727109315
1414872417007-123	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.243533	123	EXECUTED	9:4ecec7b11ded214c249fa3330edac6b0	addPrimaryKey constraintName=person_detail_element_pkey, tableName=person_detail_element		\N	5.0.1	\N	\N	4727109315
1414872417007-124	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.247214	124	EXECUTED	9:2cad1510173ec69b6b8cb232a97eab9b	addPrimaryKey constraintName=person_element_pkey, tableName=person_element		\N	5.0.1	\N	\N	4727109315
1414872417007-125	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.253786	125	EXECUTED	9:0d5aa0b601f6823fdeeb80895c8f0d28	addPrimaryKey constraintName=portal_pkey, tableName=portal		\N	5.0.1	\N	\N	4727109315
1414872417007-126	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.257533	126	EXECUTED	9:5e6770a50d8544af0afcb6c0d720715b	addPrimaryKey constraintName=quality_assurance_content_pkey, tableName=quality_assurance_content		\N	5.0.1	\N	\N	4727109315
1414872417007-127	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.261209	127	EXECUTED	9:b4e0e9778b2d4deeb4ece26f737744c5	addPrimaryKey constraintName=sweden_county_data_container_pkey, tableName=sweden_county_data_container		\N	5.0.1	\N	\N	4727109315
1414872417007-128	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.264981	128	EXECUTED	9:62edab145f5744b7ceb49858dc83eddd	addPrimaryKey constraintName=sweden_county_data_pkey, tableName=sweden_county_data		\N	5.0.1	\N	\N	4727109315
1414872417007-129	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.269093	129	EXECUTED	9:f29e2b6f4276458aa0d2337f97c7f320	addPrimaryKey constraintName=sweden_county_electoral_area_pkey, tableName=sweden_county_electoral_area		\N	5.0.1	\N	\N	4727109315
1414872417007-130	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.272941	130	EXECUTED	9:adf18b3ce957d1710d3a3337a3b3f995	addPrimaryKey constraintName=sweden_county_electoral_regi_0_pkey, tableName=sweden_county_electoral_regi_0		\N	5.0.1	\N	\N	4727109315
1414872417007-131	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.27643	131	EXECUTED	9:b1175e4a6a86b2a7013d246073480c44	addPrimaryKey constraintName=sweden_county_electoral_regi_1_pkey, tableName=sweden_county_electoral_regi_1		\N	5.0.1	\N	\N	4727109315
1414872417007-132	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.27981	132	EXECUTED	9:0ef7e7a7c0d5cda0a5dcbe5db56b7dfb	addPrimaryKey constraintName=sweden_election_region_pkey, tableName=sweden_election_region		\N	5.0.1	\N	\N	4727109315
1414872417007-133	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.28296	133	EXECUTED	9:95d0312014ad80f98a928a2e1ab77b6c	addPrimaryKey constraintName=sweden_election_type_contain_0_pkey, tableName=sweden_election_type_contain_0		\N	5.0.1	\N	\N	4727109315
1414872417007-134	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.286287	134	EXECUTED	9:2b80a19ab63216f2135737f548649efb	addPrimaryKey constraintName=sweden_election_type_pkey, tableName=sweden_election_type		\N	5.0.1	\N	\N	4727109315
1414872417007-135	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.289416	135	EXECUTED	9:d61ad75b5a17205bd28e961c19f9bdc4	addPrimaryKey constraintName=sweden_municipality_data_pkey, tableName=sweden_municipality_data		\N	5.0.1	\N	\N	4727109315
1414872417007-136	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.292858	136	EXECUTED	9:a9b60ba996dfcc86c556ecefd9434d07	addPrimaryKey constraintName=sweden_municipality_election_0_pkey, tableName=sweden_municipality_election_0		\N	5.0.1	\N	\N	4727109315
1414872417007-137	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.2959	137	EXECUTED	9:270f67f6d0e7885e13a29d873fb10753	addPrimaryKey constraintName=sweden_parliament_electoral__0_pkey, tableName=sweden_parliament_electoral__0		\N	5.0.1	\N	\N	4727109315
1414872417007-138	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.299207	138	EXECUTED	9:17e8429424a5fc7b90273685a0fee7a8	addPrimaryKey constraintName=sweden_parliament_electoral__1_pkey, tableName=sweden_parliament_electoral__1		\N	5.0.1	\N	\N	4727109315
1414872417007-139	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.302765	139	EXECUTED	9:05fc213ff95f0bacb5db6c1d213eb41a	addPrimaryKey constraintName=sweden_political_party_pkey, tableName=sweden_political_party		\N	5.0.1	\N	\N	4727109315
1414872417007-140	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.305941	140	EXECUTED	9:09a05025b9436e9f557c7fac3ab6d6a1	addPrimaryKey constraintName=target_profile_content_pkey, tableName=target_profile_content		\N	5.0.1	\N	\N	4727109315
1414872417007-141	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.309345	141	EXECUTED	9:d9a0d2ae0b49b01b86000dff4c08f92c	addPrimaryKey constraintName=topic_pkey, tableName=topic		\N	5.0.1	\N	\N	4727109315
1414872417007-142	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.312913	142	EXECUTED	9:c25699beb6697e89e15974720c428e76	addPrimaryKey constraintName=topics_pkey, tableName=topics		\N	5.0.1	\N	\N	4727109315
1414872417007-143	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.315604	143	EXECUTED	9:af73822f902ba605fc64ad455d4719d9	addPrimaryKey constraintName=user_account_address_pkey, tableName=user_account_address		\N	5.0.1	\N	\N	4727109315
1414872417007-144	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.318479	144	EXECUTED	9:8fcbc92628f8cba467c582d5ca7906e7	addPrimaryKey constraintName=user_account_pkey, tableName=user_account		\N	5.0.1	\N	\N	4727109315
1414872417007-145	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.321633	145	EXECUTED	9:f0838a94de3419c7ac3df04bd792c658	addPrimaryKey constraintName=vote_data_pkey, tableName=vote_data		\N	5.0.1	\N	\N	4727109315
1414872417007-146	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.324508	146	EXECUTED	9:5c275b66b7eed679e55738926e5b1536	addPrimaryKey constraintName=vote_meta_data_pkey, tableName=vote_meta_data		\N	5.0.1	\N	\N	4727109315
1414872417007-147	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.327043	147	EXECUTED	9:c43ec2fd8137eee136dc1c20828cbd70	addPrimaryKey constraintName=world_bank_data_pkey, tableName=world_bank_data		\N	5.0.1	\N	\N	4727109315
1414872417007-148	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.330891	148	EXECUTED	9:4508dab76977f081c98a65d7653e84b7	addForeignKeyConstraint baseTableName=person_element, constraintName=fk_13jay3yk8opnt33758httu9kb, referencedTableName=person_detail_element		\N	5.0.1	\N	\N	4727109315
1414872417007-149	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.33369	149	EXECUTED	9:91b8623f7717a1df4b4a2e6ca8b36512	addForeignKeyConstraint baseTableName=application_view, constraintName=fk_2ivjcdwosa63ant7jc5c6cojj, referencedTableName=target_profile_content		\N	5.0.1	\N	\N	4727109315
1414872417007-150	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.336331	150	EXECUTED	9:a42e80d7f82938b0012152684357dfbd	addForeignKeyConstraint baseTableName=country_element, constraintName=fk_3k0s1gih1msbej3bp2iotg52y, referencedTableName=countries_element		\N	5.0.1	\N	\N	4727109315
1414872417007-151	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.3389	151	EXECUTED	9:6590b1db53965caf6f41491e5d14069c	addForeignKeyConstraint baseTableName=person_element, constraintName=fk_3o85sqp9yss0nler1yl1umfl1, referencedTableName=person_container_element		\N	5.0.1	\N	\N	4727109315
1414872417007-152	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.341564	152	EXECUTED	9:5666684899b4932d7a7c5f10650b1504	addForeignKeyConstraint baseTableName=sweden_county_electoral_regi_1, constraintName=fk_4y4vi3cafmbdhvckntfn7qdps, referencedTableName=sweden_county_electoral_regi_0		\N	5.0.1	\N	\N	4727109315
1414872417007-153	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.344107	153	EXECUTED	9:5d7d31451a54ac259a8da618470e3019	addForeignKeyConstraint baseTableName=against_proposal_data, constraintName=fk_5u5u77qsrpa2qy6umqrph4tyf, referencedTableName=against_proposal_container		\N	5.0.1	\N	\N	4727109315
1414872417007-154	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.346621	154	EXECUTED	9:b5f757faef90de046c4cfeba596b2185	addForeignKeyConstraint baseTableName=person_container_data, constraintName=fk_5w4uvrhl3l7c441b7ra7p8txr, referencedTableName=person_data		\N	5.0.1	\N	\N	4727109315
1414872417007-155	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.348999	155	EXECUTED	9:d1716def0fa248256460135662cb0605	addForeignKeyConstraint baseTableName=document_status_container, constraintName=fk_6crp887w8xy3e4i143yyydjqv, referencedTableName=document_reference_container		\N	5.0.1	\N	\N	4727109315
1414872417007-156	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.351528	156	EXECUTED	9:87739cc9152ce23f5b403ba407615209	addForeignKeyConstraint baseTableName=portal, constraintName=fk_7c8jfw8bnxrm2aj26w9qlx340, referencedTableName=agency		\N	5.0.1	\N	\N	4727109315
1414872417007-157	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.353894	157	EXECUTED	9:285821ed49a91195d9d6e0f0273b0778	addForeignKeyConstraint baseTableName=sweden_county_electoral_area, constraintName=fk_7h4feuc5bwu12tyaka1nx1ln, referencedTableName=sweden_county_electoral_regi_1		\N	5.0.1	\N	\N	4727109315
1414872417007-158	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.357002	158	EXECUTED	9:29999cfa40cbc2af86e5e6f41ce1ff4f	addForeignKeyConstraint baseTableName=assignment_data, constraintName=fk_84o1dcsfeyp1o25nfdpppa7oe, referencedTableName=person_assignment_data		\N	5.0.1	\N	\N	4727109315
1414872417007-159	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.359831	159	EXECUTED	9:04f0321d6a659c24367adf06b21490ec	addForeignKeyConstraint baseTableName=document_status_container, constraintName=fk_86c52yf22uk0bpcs1qoc3aeyv, referencedTableName=document_attachment_container		\N	5.0.1	\N	\N	4727109315
1414872417007-160	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.362484	160	EXECUTED	9:cfb97bbb6da4e55006169e9879f04eea	addForeignKeyConstraint baseTableName=user_account_address, constraintName=fk_8931ymg13vy6vfkrichtst7bj, referencedTableName=user_account		\N	5.0.1	\N	\N	4727109315
1414872417007-161	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.365453	161	EXECUTED	9:35323e79371e0ed674f60d54da88447e	addForeignKeyConstraint baseTableName=indicator_element, constraintName=fk_8l1m1pum4e3catw4443rup4q5, referencedTableName=indicators_element		\N	5.0.1	\N	\N	4727109315
1414872417007-162	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.367771	162	EXECUTED	9:d5d46f2c32f0828c1ad2cab7488c0ced	addForeignKeyConstraint baseTableName=user_account, constraintName=fk_8mmnmcgjut9nc7dfhrgxi598f, referencedTableName=aggregated_country_data		\N	5.0.1	\N	\N	4727109315
1414872417007-163	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.370472	163	EXECUTED	9:1e64c99d1c965da030f1f77b7c65d3bd	addForeignKeyConstraint baseTableName=committee_proposal_component_0, constraintName=fk_90arga58ce9bnjkc6lws04uhw, referencedTableName=committee_proposal_container		\N	5.0.1	\N	\N	4727109315
1414872417007-164	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.372991	164	EXECUTED	9:4ff16e8d056a87f4c3ccd13acc819aa5	addForeignKeyConstraint baseTableName=indicator_element, constraintName=fk_92h99v4i1pmr69x0y43pocv2a, referencedTableName=topics		\N	5.0.1	\N	\N	4727109315
1414872417007-165	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.375329	165	EXECUTED	9:f030785453270c20bfdb38ca4dc425ee	addForeignKeyConstraint baseTableName=domain_portal, constraintName=fk_9ln0n5axxjuxtbpepyad69rel, referencedTableName=portal		\N	5.0.1	\N	\N	4727109315
1414872417007-166	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.377982	166	EXECUTED	9:4dacfe145969063359c67da63be19926	addForeignKeyConstraint baseTableName=document_status_container, constraintName=fk_9q1ktfb77gieq0xugoqe2fidd, referencedTableName=document_detail_container		\N	5.0.1	\N	\N	4727109315
1414872417007-167	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.380616	167	EXECUTED	9:f9d7c86e3c465fe6034392bcaa0261a8	addForeignKeyConstraint baseTableName=application_view, constraintName=fk_9x5havflf3rdfkaw1hangbemd, referencedTableName=quality_assurance_content		\N	5.0.1	\N	\N	4727109315
1414872417007-168	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.383175	168	EXECUTED	9:120cbf4620307c3f7962a3727eea6d93	addForeignKeyConstraint baseTableName=person_detail_element, constraintName=fk_a6syxeadcisfnnfjqemog93qd, referencedTableName=detail_element		\N	5.0.1	\N	\N	4727109315
1414872417007-169	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.385655	169	EXECUTED	9:8ddf896e49f7932380e5ef7471892039	addForeignKeyConstraint baseTableName=sweden_political_party, constraintName=fk_c2f4dhdce9p61sg50rnww73c1, referencedTableName=sweden_election_region		\N	5.0.1	\N	\N	4727109315
1414872417007-170	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.388017	170	EXECUTED	9:2d5e1c62af16eec4c4539ddfb9100fec	addForeignKeyConstraint baseTableName=document_reference_data, constraintName=fk_c4uqb4d6xqa5d7afwen8sny67, referencedTableName=document_reference_container		\N	5.0.1	\N	\N	4727109315
1414872417007-171	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.390665	171	EXECUTED	9:4e6aea0b15d2a2ed7264ed313d9547be	addForeignKeyConstraint baseTableName=detail_data, constraintName=fk_diexjlb9hdrfv7g5y06cj6nu5, referencedTableName=person_detail_data		\N	5.0.1	\N	\N	4727109315
1414872417007-172	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.393135	172	EXECUTED	9:e8560158fc903b2b52877dcc82cadbed	addForeignKeyConstraint baseTableName=world_bank_data, constraintName=fk_e0yghurnnhmkahpt7ydf008fo, referencedTableName=data_element		\N	5.0.1	\N	\N	4727109315
1414872417007-173	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.39545	173	EXECUTED	9:cbec00c068a73c3e5d6597c95e437200	addForeignKeyConstraint baseTableName=committee_proposal_component_0, constraintName=fk_eofapva6jn5k3h5gnj4whyilb, referencedTableName=against_proposal_container		\N	5.0.1	\N	\N	4727109315
1414872417007-174	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.397907	174	EXECUTED	9:ad7250e1d861b489dd7ca50bb3f49df2	addForeignKeyConstraint baseTableName=application_view, constraintName=fk_f4bptktby95bygv359chn7lbn, referencedTableName=performance_indicator_content		\N	5.0.1	\N	\N	4727109315
1414872417007-175	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.400709	175	EXECUTED	9:818f5d9ba52f5b4b7c5e22b7880d5b31	addForeignKeyConstraint baseTableName=document_activity_data, constraintName=fk_gruc53dqu0smf6s1a0gkelvdm, referencedTableName=document_activity_container		\N	5.0.1	\N	\N	4727109315
1414872417007-176	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.403192	176	EXECUTED	9:2829a37686f398cbd8bde3720b9ffbcb	addForeignKeyConstraint baseTableName=sweden_municipality_data, constraintName=fk_gykahsnks6y9v8y5novlxagnf, referencedTableName=sweden_county_data		\N	5.0.1	\N	\N	4727109315
1414872417007-177	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.405624	177	EXECUTED	9:74506407fb2de37ce7110ed9e6e84899	addForeignKeyConstraint baseTableName=committee_proposal_data, constraintName=fk_hs04ji7kqvwd7313ryp20vo0x, referencedTableName=committee_proposal_container		\N	5.0.1	\N	\N	4727109315
1414872417007-178	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.408042	178	EXECUTED	9:ff2b41caa6a89d740f2df18f82af73e5	addForeignKeyConstraint baseTableName=aggregated_country_data, constraintName=fk_j7l4eldeihr8g7ax7rv2irgk1, referencedTableName=country_element		\N	5.0.1	\N	\N	4727109315
1414872417007-179	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.410607	179	EXECUTED	9:de72bc11dc8b5b4e708484799424e2f3	addForeignKeyConstraint baseTableName=document_status_container, constraintName=fk_jjcxsqmdnjw0nbwducjyecdg4, referencedTableName=document_data		\N	5.0.1	\N	\N	4727109315
1414872417007-180	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.413148	180	EXECUTED	9:dd951cd80fae1d68360c4f14a0f8ae47	addForeignKeyConstraint baseTableName=person_data, constraintName=fk_jrgy7nw6n071uok8p1hkp03rh, referencedTableName=person_detail_data		\N	5.0.1	\N	\N	4727109315
1414872417007-181	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.41551	181	EXECUTED	9:54660e233cda4814c9a51132ed672342	addForeignKeyConstraint baseTableName=committee_proposal_component_0, constraintName=fk_k78eqmx2m3ja0267xhthfeio4, referencedTableName=committee_document_data		\N	5.0.1	\N	\N	4727109315
1414872417007-182	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.417842	182	EXECUTED	9:7300fd0907004ada150fc96cb7a0c74c	addForeignKeyConstraint baseTableName=assignment_element, constraintName=fk_ks1811fwuno6vqof0lskerpib, referencedTableName=person_assignment_element		\N	5.0.1	\N	\N	4727109315
1414872417007-183	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.420111	183	EXECUTED	9:60e718f175e5b30a169916f114881495	addForeignKeyConstraint baseTableName=document_attachment, constraintName=fk_lean1i0p0e5rv28my9297lq22, referencedTableName=document_attachment_container		\N	5.0.1	\N	\N	4727109315
1414872417007-184	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.422711	184	EXECUTED	9:06e2186d554b8ab401562b3ad4b848ae	addForeignKeyConstraint baseTableName=document_person_reference_da_0, constraintName=fk_lsfup3rosph7239t1idorm1cd, referencedTableName=document_person_reference_co_0		\N	5.0.1	\N	\N	4727109315
1414872417007-185	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.425042	185	EXECUTED	9:aa2334e93f5fe2a5ed438f52c7507776	addForeignKeyConstraint baseTableName=person_element, constraintName=fk_m6dcdojsb6iv9lrego5kurr7p, referencedTableName=person_assignment_element		\N	5.0.1	\N	\N	4727109315
25353872417007-324	party_trends-fix	db-changelog-1.26.xml	2025-12-03 01:58:34.642264	345	EXECUTED	9:60797ca4c12b59c5f244255c0bda44cf	createView viewName=view_riksdagen_party_ballot_support_annual_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-186	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.427429	186	EXECUTED	9:cc82b3c05a5e51eedf05c87248cea759	addForeignKeyConstraint baseTableName=document_status_container, constraintName=fk_ng4kjnv3cm4e6ud3fikwi6p7i, referencedTableName=document_person_reference_co_0		\N	5.0.1	\N	\N	4727109315
1414872417007-187	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.430045	187	EXECUTED	9:5eba99c12fbddf7df67ef71faf1a83de	addForeignKeyConstraint baseTableName=application_action_event, constraintName=fk_nlqlshlogsx2g8u5d3y28my28, referencedTableName=application_session		\N	5.0.1	\N	\N	4727109315
1414872417007-188	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.432649	188	EXECUTED	9:09fb71b57990439cfb427066fe1efddc	addForeignKeyConstraint baseTableName=document_element, constraintName=fk_o24n54auwa2xyflis6nkrajpd, referencedTableName=document_container_element		\N	5.0.1	\N	\N	4727109315
1414872417007-189	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.43544	189	EXECUTED	9:6e0bce434a5e5c4d0dc628b0529264db	addForeignKeyConstraint baseTableName=topic, constraintName=fk_o7ol28sotu1r12n8txv2gigok, referencedTableName=topics		\N	5.0.1	\N	\N	4727109315
1414872417007-190	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.438693	190	EXECUTED	9:8c38ea04b2f59bf71cf23a9896e12862	addForeignKeyConstraint baseTableName=sweden_election_type, constraintName=fk_ob50ibby6jamvitbxknoucifg, referencedTableName=sweden_election_region		\N	5.0.1	\N	\N	4727109315
1414872417007-191	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.441463	191	EXECUTED	9:093c24624d60c39f0c07c436100f5a40	addForeignKeyConstraint baseTableName=aggregated_bug_data, constraintName=fk_osdlir1nv0m8ckb1pbipgswj, referencedTableName=person_data		\N	5.0.1	\N	\N	4727109315
1414872417007-192	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.444322	192	EXECUTED	9:f82186446de4769ed31f4da13061611b	addForeignKeyConstraint baseTableName=application_view, constraintName=fk_p8b7gnxeglk71etbbql3j184s, referencedTableName=data_source_content		\N	5.0.1	\N	\N	4727109315
1414872417007-193	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.446879	193	EXECUTED	9:0ec91f1010fa7e1204a8124d5d8f5144	addForeignKeyConstraint baseTableName=sweden_county_data, constraintName=fk_pndlg3q6ly10qbs8e3s9wikyu, referencedTableName=sweden_county_data_container		\N	5.0.1	\N	\N	4727109315
1414872417007-194	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.449298	194	EXECUTED	9:452c2540e89f1802c787458cf6b48cd8	addForeignKeyConstraint baseTableName=document_detail_data, constraintName=fk_quor6wesrmk9ierjyr7ni8wch, referencedTableName=document_detail_container		\N	5.0.1	\N	\N	4727109315
1414872417007-195	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.451662	195	EXECUTED	9:cc1d30b724e685b87c77fc853efa1bc3	addForeignKeyConstraint baseTableName=sweden_parliament_electoral__1, constraintName=fk_qvgtilwwyipbrv6b2cv6fcp27, referencedTableName=sweden_parliament_electoral__0		\N	5.0.1	\N	\N	4727109315
1414872417007-196	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.453861	196	EXECUTED	9:ec3a97c733d887320cae227c31414d0d	addForeignKeyConstraint baseTableName=document_status_container, constraintName=fk_r2dkprhp4xfhrcck9sf31b9xl, referencedTableName=document_activity_container		\N	5.0.1	\N	\N	4727109315
1414872417007-197	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.456212	197	EXECUTED	9:9d34aebdd2d3710bc4969a4b919b9e44	addForeignKeyConstraint baseTableName=sweden_municipality_election_0, constraintName=fk_r3jht5oci01uxhwaa39uxsg2t, referencedTableName=sweden_municipality_data		\N	5.0.1	\N	\N	4727109315
1414872417007-198	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.458556	198	EXECUTED	9:fc898e94f68547d5d6b575ba264843b3	addForeignKeyConstraint baseTableName=person_data, constraintName=fk_rd2pmyb4gmu2vbxklh6er8ayc, referencedTableName=person_assignment_data		\N	5.0.1	\N	\N	4727109315
1414872417007-199	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.460812	199	EXECUTED	9:44b8fe081629b67789129dde1fad255d	addForeignKeyConstraint baseTableName=sweden_election_type, constraintName=fk_rp3tjh4jmpmoxh05oo9fq9qh6, referencedTableName=sweden_election_type_contain_0		\N	5.0.1	\N	\N	4727109315
1414872417007-200	pether (generated)	db-changelog-1.0.xml	2025-12-03 01:58:32.463271	200	EXECUTED	9:538e5077d44dd97071f9d407ac8812d0	addForeignKeyConstraint baseTableName=application_view, constraintName=fk_x8sbg6y7h0vavmun6i7h8oae, referencedTableName=operational_information_cont_0		\N	5.0.1	\N	\N	4727109315
1414872417007-201	pether	db-changelog-1.0.xml	2025-12-03 01:58:32.465687	201	EXECUTED	9:765cc5f3190db16a819f460b2c3a9835	modifyDataType columnName=content, tableName=document_content_data		\N	5.0.1	\N	\N	4727109315
1414872417007-202	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.477504	202	EXECUTED	9:08fb1bac7fdd7016ab7d47d2f70ebbfa	createView viewName=view_document_data_committee_report_url		\N	5.0.1	\N	\N	4727109315
1414872417007-203	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.479811	203	EXECUTED	9:534ad16ef62ab2164f305d17d8fba21b	modifyDataType columnName=proposal, tableName=committee_proposal_data		\N	5.0.1	\N	\N	4727109315
1414872417007-204	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.48464	204	EXECUTED	9:dad3fd6230b1f6b09a7ea190314f3e75	createView viewName=view_riksdagen_committee		\N	5.0.1	\N	\N	4727109315
1414872417007-205	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.488814	205	EXECUTED	9:cce5264c458c91c73e66133abbaca793	createView viewName=view_riksdagen_goverment		\N	5.0.1	\N	\N	4727109315
1414872417007-206	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.493337	206	EXECUTED	9:3c85c169ae80776fcc98b8be9fb3ed04	createView viewName=view_riksdagen_goverment_roles		\N	5.0.1	\N	\N	4727109315
1414872417007-207	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.499807	207	EXECUTED	9:e518955b8fdd65916aef6f8d2e13a803	createView viewName=view_riksdagen_committee_decisions		\N	5.0.1	\N	\N	4727109315
1414872417007-208	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.503723	208	EXECUTED	9:b261683ab49f67d5ad404b2cd5d5ec79	createView viewName=view_riksdagen_goverment_proposals		\N	5.0.1	\N	\N	4727109315
1414872417007-209	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.507098	209	EXECUTED	9:a14b26657c5b09346ccfe984cc94d11d	createView viewName=view_riksdagen_member_proposals		\N	5.0.1	\N	\N	4727109315
1416258476613-210	pether (generated)	db-changelog-1.1.xml	2025-12-03 01:58:32.510193	210	EXECUTED	9:c2effec0a954d668b8fd780eb04879d6	createTable tableName=document_proposal_container		\N	5.0.1	\N	\N	4727109315
1416258476613-211	pether (generated)	db-changelog-1.1.xml	2025-12-03 01:58:32.514505	211	EXECUTED	9:908f681226f2fb5abe4b199126d26a6b	createTable tableName=document_proposal_data		\N	5.0.1	\N	\N	4727109315
1416258476613-212	pether (generated)	db-changelog-1.1.xml	2025-12-03 01:58:32.518032	212	EXECUTED	9:3e0f9abc6d66f9b8582632524ec3806a	addColumn tableName=document_status_container; dropColumn columnName=document_proposal, tableName=document_status_container		\N	5.0.1	\N	\N	4727109315
1416258476613-213	pether (generated)	db-changelog-1.1.xml	2025-12-03 01:58:32.520897	213	EXECUTED	9:f866ec1b7098b04250e282bf216ce46b	addPrimaryKey constraintName=document_proposal_container_pkey, tableName=document_proposal_container		\N	5.0.1	\N	\N	4727109315
1416258476613-214	pether (generated)	db-changelog-1.1.xml	2025-12-03 01:58:32.523785	214	EXECUTED	9:758bf9de406141115f5a3dea3f38d224	addPrimaryKey constraintName=document_proposal_data_pkey, tableName=document_proposal_data		\N	5.0.1	\N	\N	4727109315
1416258476613-215	pether (generated)	db-changelog-1.1.xml	2025-12-03 01:58:32.526317	215	EXECUTED	9:bc8c5511c4210514433a23a9802a16ea	addForeignKeyConstraint baseTableName=document_proposal_container, constraintName=fk_m55tt4vaimgb5qk7xj9mgxmry, referencedTableName=document_proposal_data		\N	5.0.1	\N	\N	4727109315
1416258476613-216	pether (generated)	db-changelog-1.1.xml	2025-12-03 01:58:32.528625	216	EXECUTED	9:824a2b5b89c1f8445fe03bce8a1ed4a3	addForeignKeyConstraint baseTableName=document_status_container, constraintName=fk_iirofquegnrpnuonvnydf6wfb, referencedTableName=document_proposal_container		\N	5.0.1	\N	\N	4727109315
1414872417007-217	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.532164	217	EXECUTED	9:dc12d88f692a0de46300f916366fcd3d	modifyDataType columnName=wording, tableName=document_proposal_data; modifyDataType columnName=wording_2, tableName=document_proposal_data; modifyDataType columnName=wording_3, tableName=document_proposal_data; modifyDataType columnName=wording_4,...		\N	5.0.1	\N	\N	4727109315
1414872417007-218	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.53556	218	EXECUTED	9:bf01ae3c4a683f06ebfb04fb51534659	createView viewName=view_riksdagen_committee_roles		\N	5.0.1	\N	\N	4727109315
1414872417007-219	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.544595	219	EXECUTED	9:40844e607d59d90e5e0cceea37549f78	dropView viewName=view_riksdagen_committee; createView viewName=view_riksdagen_committee; createView viewName=view_riksdagen_committee_parliament_member_proposal		\N	5.0.1	\N	\N	4727109315
1414872417007-220	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.551438	220	EXECUTED	9:2989f55d70d91a3b877bdc4fbde2bcc7	dropView viewName=view_riksdagen_goverment_roles; createView viewName=view_riksdagen_goverment_roles; dropView viewName=view_riksdagen_committee_roles; createView viewName=view_riksdagen_committee_roles		\N	5.0.1	\N	\N	4727109315
1414872417007-221	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.555117	221	EXECUTED	9:22e8c979c00998ab0584a65208d0070c	dropView viewName=view_riksdagen_goverment; createView viewName=view_riksdagen_goverment		\N	5.0.1	\N	\N	4727109315
1414872417007-222	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.558686	222	EXECUTED	9:a03f84a49600f94a9947b1b8d5831149	createView viewName=view_riksdagen_party_member		\N	5.0.1	\N	\N	4727109315
1414872417007-223	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.561487	223	EXECUTED	9:d9e3c3c85411b661c092f2f8922b9c06	createView viewName=view_riksdagen_party		\N	5.0.1	\N	\N	4727109315
1414872417007-224	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.565256	224	EXECUTED	9:b57642b4b204201b692e94822604822c	dropView viewName=view_riksdagen_party; createView viewName=view_riksdagen_party		\N	5.0.1	\N	\N	4727109315
1414872417007-225	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.569645	225	EXECUTED	9:ea6a0f678b722d37e8d2862d86044b1e	dropView viewName=view_riksdagen_goverment; createView viewName=view_riksdagen_goverment		\N	5.0.1	\N	\N	4727109315
1414872417007-226	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.576882	226	EXECUTED	9:f61f477649fb8c7055d2b374748e2459	dropView viewName=view_riksdagen_committee_parliament_member_proposal; dropView viewName=view_riksdagen_committee; createView viewName=view_riksdagen_committee; createView viewName=view_riksdagen_committee_parliament_member_proposal		\N	5.0.1	\N	\N	4727109315
1414872417007-227	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.583524	227	EXECUTED	9:0e86f855ff9c4aef94315ddd54a3023d	createView viewName=view_riksdagen_goverment_role_member; createView viewName=view_riksdagen_committee_role_member		\N	5.0.1	\N	\N	4727109315
1414872417007-228	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.590192	228	EXECUTED	9:c1f77e255a88bcc694bbe19fe721d57c	createView viewName=view_riksdagen_politician		\N	5.0.1	\N	\N	4727109315
1414872417007-229	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.597301	229	EXECUTED	9:02389fc7a55e20948a4b646d84108740	dropView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_politician		\N	5.0.1	\N	\N	4727109315
1414872417007-230	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.606441	230	EXECUTED	9:74cca167d30b5635d5d604d621881082	dropView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_politician		\N	5.0.1	\N	\N	4727109315
1414872417007-231	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.611801	231	EXECUTED	9:083ad5ea0ee0c5582da18d78f074043d	createView viewName=view_riksdagen_party_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-232	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.617256	232	EXECUTED	9:8c5e85cc7b9722b6cf11cc1e2370af47	dropView viewName=view_riksdagen_party_summary; createView viewName=view_riksdagen_party_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-233	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.629301	233	EXECUTED	9:e615358ab9818929ffe7a8d502e4bb85	dropView viewName=view_riksdagen_party_summary; dropView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_party_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-234	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.635908	234	EXECUTED	9:eaa93e3fb1b9d6745bc1bc4f6decb446	createView viewName=view_riksdagen_politician_document		\N	5.0.1	\N	\N	4727109315
1414872417007-235	pether	db-changelog-1.1.xml	2025-12-03 01:58:32.654916	235	EXECUTED	9:2a4aaa3ad3c5adf40fd1e09942d17ba2	dropView viewName=view_riksdagen_party_summary; dropView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_party_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-236	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.673554	236	EXECUTED	9:7a212611fdd15ee2fe72ba19866e2f68	dropView viewName=view_riksdagen_party_summary; dropView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_party_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-237	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.682047	237	EXECUTED	9:2b9a267cae0600616bd0fab522c2210b	dropView viewName=view_riksdagen_goverment_role_member; createView viewName=view_riksdagen_goverment_role_member; dropView viewName=view_riksdagen_committee_role_member; createView viewName=view_riksdagen_committee_role_member; createView viewName...		\N	5.0.1	\N	\N	4727109315
1414872417007-238	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.688097	238	EXECUTED	9:9b345a7e03fca2d72d77f5d6b3351409	dropForeignKeyConstraint baseTableName=user_account, constraintName=fk_8mmnmcgjut9nc7dfhrgxi598f; dropTable tableName=aggregated_bug_data; dropTable tableName=aggregated_country_data; dropColumn columnName=country_user_account_hjid, tableName=user...		\N	5.0.1	\N	\N	4727109315
1414872417007-239	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.698617	239	EXECUTED	9:2114de6439a5fe3157a923c3d8ee36c7	dropView viewName=view_riksdagen_goverment_role_member; createView viewName=view_riksdagen_goverment_role_member; dropView viewName=view_riksdagen_committee_role_member; createView viewName=view_riksdagen_committee_role_member; dropView viewName=v...		\N	5.0.1	\N	\N	4727109315
1414872417007-263	pether	db-changelog-1.5.xml	2025-12-03 01:58:33.155533	263	EXECUTED	9:7d34f8665567dbd826ceff8e38177cc9	dropView viewName=view_worldbank_indicator_data_country_summary; createView viewName=view_worldbank_indicator_data_country_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-240	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.708884	240	EXECUTED	9:591c6d509fe1d4dde6b41c9ecc5105c9	dropView viewName=view_riksdagen_goverment_role_member; createView viewName=view_riksdagen_goverment_role_member; dropView viewName=view_riksdagen_committee_role_member; createView viewName=view_riksdagen_committee_role_member; dropView viewName=v...		\N	5.0.1	\N	\N	4727109315
1414872417007-241	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.723997	241	EXECUTED	9:76d4f14c8fcd7a22ede9b78231ff916b	createView viewName=view_riksdagen_vote_data_ballot_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-242	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.737626	242	EXECUTED	9:dc35246f6d762ea3fa127bf050173773	createView viewName=view_riksdagen_vote_data_ballot_party_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-243	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.759531	243	EXECUTED	9:bedaee0e37a39ab72fcf8b0a32ea1ac3	dropView viewName=view_riksdagen_vote_data_ballot_party_summary; dropView viewName=view_riksdagen_vote_data_ballot_summary; createView viewName=view_riksdagen_vote_data_ballot_summary; createView viewName=view_riksdagen_vote_data_ballot_party_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-244	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.76603	244	EXECUTED	9:475ac35a820437f1e15c480211d60234	createView viewName=view_riksdagen_vote_data_ballot_summary_daily		\N	5.0.1	\N	\N	4727109315
1414872417007-245	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.777388	245	EXECUTED	9:5068091c96f1fd1622105bb83527a07e	createView viewName=view_riksdagen_vote_data_ballot_summary_weekly; createView viewName=view_riksdagen_vote_data_ballot_summary_monthly; createView viewName=view_riksdagen_vote_data_ballot_summary_annual		\N	5.0.1	\N	\N	4727109315
1414872417007-246	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.801707	246	EXECUTED	9:68d9a9971d2ea9bd76d03d89521ad3d5	dropView viewName=view_riksdagen_vote_data_ballot_summary_weekly; dropView viewName=view_riksdagen_vote_data_ballot_summary_monthly; dropView viewName=view_riksdagen_vote_data_ballot_summary_annual; dropView viewName=view_riksdagen_vote_data_ballo...		\N	5.0.1	\N	\N	4727109315
1414872417007-247	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.82455	247	EXECUTED	9:98d9c264a755b9a836390a08d33878d7	createView viewName=view_riksdagen_vote_data_ballot_party_summary_daily		\N	5.0.1	\N	\N	4727109315
1414872417007-248	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.85086	248	EXECUTED	9:e26631ba948fed851e8ddafa9c91f959	createView viewName=view_riksdagen_vote_data_ballot_party_summary_weekly; createView viewName=view_riksdagen_vote_data_ballot_party_summary_monthly; createView viewName=view_riksdagen_vote_data_ballot_party_summary_annual		\N	5.0.1	\N	\N	4727109315
1414872417007-249	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.858391	249	EXECUTED	9:bc3ce6e9dfc901e50afc2fafa681b95a	createView viewName=view_riksdagen_vote_data_ballot_politician_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-250	pether	db-changelog-1.2.xml	2025-12-03 01:58:32.891316	250	EXECUTED	9:91e3908e9e189208b9758c3cc6c44c71	createView viewName=view_riksdagen_vote_data_ballot_politician_summary_daily; createView viewName=view_riksdagen_vote_data_ballot_politician_summary_weekly; createView viewName=view_riksdagen_vote_data_ballot_politician_summary_monthly; createView...		\N	5.0.1	\N	\N	4727109315
1414872417007-251	pether	db-changelog-1.3.xml	2025-12-03 01:58:32.976344	251	EXECUTED	9:ef6bbe1d06a5478a5c33db26bfd794f1	dropView viewName=view_riksdagen_vote_data_ballot_politician_summary_weekly; dropView viewName=view_riksdagen_vote_data_ballot_politician_summary_monthly; dropView viewName=view_riksdagen_vote_data_ballot_politician_summary_annual; dropView viewNa...		\N	5.0.1	\N	\N	4727109315
1414872417007-252	pether (generated)	db-changelog-1.4.xml	2025-12-03 01:58:32.982377	252	EXECUTED	9:76f1acc51844fae7e7ef064ee6c8fc08	dropView viewName=view_riksdagen_committee_decisions; modifyDataType columnName=title, tableName=committee_document_data; modifyDataType columnName=sub_title, tableName=committee_document_data; modifyDataType columnName=temp_label, tableName=commi...		\N	5.0.1	\N	\N	4727109315
1414872417007-253	pether	db-changelog-1.4.xml	2025-12-03 01:58:33.066356	253	EXECUTED	9:4e4b64893fd4dae4fd2bffadc7163fb2	dropView viewName=view_riksdagen_vote_data_ballot_politician_summary_weekly; dropView viewName=view_riksdagen_vote_data_ballot_politician_summary_monthly; dropView viewName=view_riksdagen_vote_data_ballot_politician_summary_annual; dropView viewNa...		\N	5.0.1	\N	\N	4727109315
1414872417007-254	pether	db-changelog-1.4.xml	2025-12-03 01:58:33.073386	254	EXECUTED	9:c7d4e2eda2ed22d4092964cf1f6756ab	createView viewName=view_riksdagen_document_type_daily_summary; createView viewName=view_riksdagen_politician_document_daily_summary; createView viewName=view_riksdagen_party_document_daily_summary; createView viewName=view_riksdagen_org_document_...		\N	5.0.1	\N	\N	4727109315
1414872417007-255	pether	db-changelog-1.4.xml	2025-12-03 01:58:33.077718	255	EXECUTED	9:de1ff4d851c9cfb6e0597760c4cfe11b	createView viewName=view_riksdagen_committee_decision_type_summary; createView viewName=view_riksdagen_committee_decision_type_org_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-256	pether	db-changelog-1.4.xml	2025-12-03 01:58:33.085531	256	EXECUTED	9:55cb855446f25c527751dcb9eda7bf1d	dropView viewName=view_riksdagen_committee_decision_type_summary; createView viewName=view_riksdagen_committee_decision_type_summary; dropView viewName=view_riksdagen_committee_decision_type_org_summary; createView viewName=view_riksdagen_committe...		\N	5.0.1	\N	\N	4727109315
1414872417007-257	pether	db-changelog-1.5.xml	2025-12-03 01:58:33.095541	257	EXECUTED	9:505f6fa901adab3bd33ddb3ad4a44967	dropView viewName=view_riksdagen_committee_decision_type_summary; dropView viewName=view_riksdagen_committee_decision_type_org_summary; dropView viewName=view_riksdagen_committee_decisions; createView viewName=view_riksdagen_committee_decisions; c...		\N	5.0.1	\N	\N	4727109315
1414872417007-258	pether	db-changelog-1.5.xml	2025-12-03 01:58:33.10944	258	EXECUTED	9:4939cc4cd5b64ed9cfb0305c17b1ac42	createView viewName=view_riksdagen_committee_ballot_decision_summary; createView viewName=view_riksdagen_committee_ballot_decision_party_summary; createView viewName=view_riksdagen_committee_ballot_decision_politician_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-259	pether	db-changelog-1.5.xml	2025-12-03 01:58:33.116606	259	EXECUTED	9:b574f8e847319078c295ab00b9db075a	dropView viewName=view_riksdagen_committee_decision_type_summary; createView viewName=view_riksdagen_committee_decision_type_summary; dropView viewName=view_riksdagen_committee_decision_type_org_summary; createView viewName=view_riksdagen_committe...		\N	5.0.1	\N	\N	4727109315
1414872417007-260	pether	db-changelog-1.5.xml	2025-12-03 01:58:33.140314	260	EXECUTED	9:6b5a8faf027b4da44412ecda2cb4c87f	dropView viewName=view_riksdagen_committee_decision_type_summary; dropView viewName=view_riksdagen_committee_decision_type_org_summary; dropView viewName=view_riksdagen_committee_ballot_decision_summary; dropView viewName=view_riksdagen_committee_...		\N	5.0.1	\N	\N	4727109315
1414872417007-261	pether	db-changelog-1.5.xml	2025-12-03 01:58:33.144754	261	EXECUTED	9:a898f1a6d116c317439ac2289b616988	createView viewName=view_worldbank_indicator_data_country_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-262	pether	db-changelog-1.5.xml	2025-12-03 01:58:33.14965	262	EXECUTED	9:943858a57243b94eab7e4150c2fcd36b	dropView viewName=view_worldbank_indicator_data_country_summary; createView viewName=view_worldbank_indicator_data_country_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-264	pether	db-changelog-1.5.xml	2025-12-03 01:58:33.319931	264	EXECUTED	9:0aa77dc47d643b3882f2a4ca3238c070	dropView viewName=view_riksdagen_committee_decision_type_summary; dropView viewName=view_riksdagen_committee_decision_type_org_summary; dropView viewName=view_riksdagen_committee_ballot_decision_summary; dropView viewName=view_riksdagen_committee_...		\N	5.0.1	\N	\N	4727109315
1414872417007-265	pether	db-changelog-1.6.xml	2025-12-03 01:58:33.344843	265	EXECUTED	9:6c82b7bad6f5bd4c961ed3cfee42cf1b	dropView viewName=view_riksdagen_document_type_daily_summary; dropView viewName=view_riksdagen_politician_document_daily_summary; dropView viewName=view_riksdagen_party_document_daily_summary; dropView viewName=view_riksdagen_org_document_daily_su...		\N	5.0.1	\N	\N	4727109315
1414872417007-266	pether	db-changelog-1.6.xml	2025-12-03 01:58:33.380901	266	EXECUTED	9:345962d1e2cffcbe3413eb509fd97886	sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql		\N	5.0.1	\N	\N	4727109315
1414872417007-267	pether	db-changelog-1.6.xml	2025-12-03 01:58:33.388257	267	EXECUTED	9:9fc625a3d56571e98a8d7eea06980e65	dropView viewName=view_worldbank_indicator_data_country_summary; sql		\N	5.0.1	\N	\N	4727109315
1414872417007-268	pether (generated)	db-changelog-1.6.xml	2025-12-03 01:58:33.393617	268	EXECUTED	9:53a2955ac5300632144a22b15a944a8e	addColumn tableName=application_session		\N	5.0.1	\N	\N	4727109315
1414872417007-269	pether (generated)	db-changelog-1.6.xml	2025-12-03 01:58:33.400683	269	EXECUTED	9:accbf9a9a30c5dd595a667a721f2ff89	addColumn tableName=application_action_event		\N	5.0.1	\N	\N	4727109315
1414872417007-270	pether (generated)	db-changelog-1.6.xml	2025-12-03 01:58:33.40333	270	EXECUTED	9:b415b0a981b9dfb061d94504b19b0878	addColumn tableName=user_account		\N	5.0.1	\N	\N	4727109315
1414872417007-271	pether (generated)	db-changelog-1.6.xml	2025-12-03 01:58:33.405991	271	EXECUTED	9:522e0502d2a4e5e8cebfbe7c193cdf24	addColumn tableName=application_session		\N	5.0.1	\N	\N	4727109315
1414872417007-272	pether (generated)	db-changelog-1.6.xml	2025-12-03 01:58:33.408811	272	EXECUTED	9:94728ce0aa674d7ef4fe9b0521b55fcb	addColumn tableName=application_session		\N	5.0.1	\N	\N	4727109315
1414872417007-273	pether	db-changelog-1.6.xml	2025-12-03 01:58:33.418391	273	EXECUTED	9:b9bc2c08e551503123084c32e2839f50	sql; sql; sql; sql		\N	5.0.1	\N	\N	4727109315
1414872417007-274	pether	db-changelog-1.6.xml	2025-12-03 01:58:33.427188	274	EXECUTED	9:b257435f43a13311f319b53b205ef7c1	sql; sql; sql; sql		\N	5.0.1	\N	\N	4727109315
1414872417007-275	pether	db-changelog-1.6.xml	2025-12-03 01:58:33.435872	275	EXECUTED	9:92661264af048495b1a63824cc71e551	sql; sql; sql; sql		\N	5.0.1	\N	\N	4727109315
1414872417007-276	pether	db-changelog-1.6.xml	2025-12-03 01:58:33.441497	276	EXECUTED	9:765d8cd0cdacd034d8a0674eaa880023	createTable tableName=application_configuration		\N	5.0.1	\N	\N	4727109315
1414872417007-277	pether	db-changelog-1.6.xml	2025-12-03 01:58:33.458717	277	EXECUTED	9:c60c41cdca28d541fd3a2b605b711d18	dropView viewName=view_riksdagen_party_summary; dropView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_politician; createView viewName=view_riksdagen_party_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-278	pether	db-changelog-1.6.xml	2025-12-03 01:58:33.479833	278	EXECUTED	9:302d61374938a5619b973c5f7a3fcd85	dropView viewName=view_riksdagen_party; dropView viewName=view_riksdagen_party_summary; dropView viewName=view_riksdagen_politician; dropView viewName=view_riksdagen_party_member; createView viewName=view_riksdagen_party_member; createView viewNam...		\N	5.0.1	\N	\N	4727109315
1414872417007-279	pether (generated)	db-changelog-1.7.xml	2025-12-03 01:58:33.487996	279	EXECUTED	9:2ff509c4bb043c2b24857cb07bc3c708	addColumn tableName=document_element		\N	5.0.1	\N	\N	4727109315
1414872417007-280	pether (generated)	db-changelog-1.7.xml	2025-12-03 01:58:33.490452	280	EXECUTED	9:63d7fcb99c22318c78bd80ac7ad9a2cb	addColumn tableName=document_element		\N	5.0.1	\N	\N	4727109315
1414872417007-281	pether (generated)	db-changelog-1.7.xml	2025-12-03 01:58:33.493396	281	EXECUTED	9:083be705e77430564ef073b8269b53d2	createTable tableName=USER_ACCOUNT_GOOGLE_AUTH_SCR_0		\N	5.0.1	\N	\N	4727109315
1414872417007-282	pether (generated)	db-changelog-1.7.xml	2025-12-03 01:58:33.497341	282	EXECUTED	9:0d24e99f24183005670d5e3d4445be97	addPrimaryKey constraintName=USER_ACCOUNT_GOOGLE_AUTH_SCR_0_pkey, tableName=USER_ACCOUNT_GOOGLE_AUTH_SCR_0		\N	5.0.1	\N	\N	4727109315
1414872417007-283	pether (generated)	db-changelog-1.7.xml	2025-12-03 01:58:33.500859	283	EXECUTED	9:3db9a552cdc9cc50f060dd5a467243db	addForeignKeyConstraint baseTableName=USER_ACCOUNT_GOOGLE_AUTH_SCR_0, constraintName=fk_8931ymg13vy6vfkrichdsd4, referencedTableName=user_account		\N	5.0.1	\N	\N	4727109315
1414872417007-284	pether (generated)	db-changelog-1.7.xml	2025-12-03 01:58:33.504396	284	EXECUTED	9:68a1690b5c267ec324fb414df94fef1c	addColumn tableName=user_account; addColumn tableName=user_account		\N	5.0.1	\N	\N	4727109315
1414872417007-285	pether (generated)	db-changelog-1.8.xml	2025-12-03 01:58:33.511084	285	EXECUTED	9:a28ecc2e242187c481c2726325c67bc6	addColumn tableName=language_data		\N	5.0.1	\N	\N	4727109315
1414872417007-286	pether (generated)	db-changelog-1.8.xml	2025-12-03 01:58:33.514724	286	EXECUTED	9:14d37229b20583fab65c4b9ccd61cbf6	addColumn tableName=language_content_data		\N	5.0.1	\N	\N	4727109315
1414872417007-287	quartz.generated	db-changelog-1.9.xml	2025-12-03 01:58:33.5542	287	EXECUTED	9:33c854d56c17f442b136d767015a4026	createTable tableName=QRTZ_CALENDARS; createTable tableName=QRTZ_CRON_TRIGGERS; createTable tableName=QRTZ_FIRED_TRIGGERS; createTable tableName=QRTZ_PAUSED_TRIGGER_GRPS; createTable tableName=QRTZ_SCHEDULER_STATE; createTable tableName=QRTZ_LOCKS...		\N	5.0.1	\N	\N	4727109315
1414872417007-288	quartz.generated	db-changelog-1.9.xml	2025-12-03 01:58:33.562623	288	EXECUTED	9:9e05c11d0073fe498c1b8efa9fc189ac	addForeignKeyConstraint baseTableName=QRTZ_CRON_TRIGGERS, constraintName=FK_QRTZ_CRON_TRIGGERS_QRTZ_TRIGGERS, referencedTableName=QRTZ_TRIGGERS; addForeignKeyConstraint baseTableName=QRTZ_SIMPLE_TRIGGERS, constraintName=FK_QRTZ_SIMPLE_TRIGGERS_QRT...		\N	5.0.1	\N	\N	4727109315
1414872417007-289	quartz.generated	db-changelog-1.9.xml	2025-12-03 01:58:33.59501	289	EXECUTED	9:f018d19e4b6045fcbc2e6672c7ba873d	createIndex indexName=IDX_QRTZ_T_J, tableName=QRTZ_TRIGGERS; createIndex indexName=IDX_QRTZ_T_JG, tableName=QRTZ_TRIGGERS; createIndex indexName=IDX_QRTZ_T_C, tableName=QRTZ_TRIGGERS; createIndex indexName=IDX_QRTZ_T_G, tableName=QRTZ_TRIGGERS; cr...		\N	5.0.1	\N	\N	4727109315
1414872417007-290	pether	db-changelog-1.10.xml	2025-12-03 01:58:33.616414	290	EXECUTED	9:3477a6ab16abd3502429f252c6f2c21a	dropView viewName=view_riksdagen_party; dropView viewName=view_riksdagen_party_summary; dropView viewName=view_riksdagen_politician; dropView viewName=view_riksdagen_party_member; createView viewName=view_riksdagen_party_member; createView viewNam...		\N	5.0.1	\N	\N	4727109315
1414872417007-291	pether	db-changelog-1.11.xml	2025-12-03 01:58:33.61911	291	EXECUTED	9:96a236431ea32c987c4572cb54e71c19	renameTable newTableName=QRTZ_BLOB_TRIGGERS, oldTableName=QRTZ_bytea_TRIGGERS		\N	5.0.1	\N	\N	4727109315
1414872417007-292	add-column-application-session	db-changelog-1.12.xml	2025-12-03 01:58:33.62144	292	EXECUTED	9:956bf680aea08e30a78b8d768dae529c	addColumn tableName=application_session		\N	5.0.1	\N	\N	4727109315
1414872417007-294	gdpr-classify-data	db-changelog-1.13.xml	2025-12-03 01:58:33.90875	293	EXECUTED	9:dc2a0e2901c2d886086e508ff01826b6	sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sq...		\N	5.0.1	\N	\N	4727109315
1414872417007-295	fix-missing-value-for-model-object-version	db-changelog-1.14.xml	2025-12-03 01:58:33.919519	294	EXECUTED	9:0eddefb0cd23c53120a00749d09808f9	update tableName=agency; update tableName=portal; update tableName=language_data; update tableName=language_content_data; update tableName=application_action_event; update tableName=application_configuration; update tableName=application_session; ...		\N	5.0.1	\N	\N	4727109315
1414872417007-296	adduseraccountcolumns	db-changelog-1.15.xml	2025-12-03 01:58:33.92975	295	EXECUTED	9:941c29c388e300e20109c3cba3fb8945	addColumn tableName=user_account; update tableName=user_account; addColumn tableName=user_account; update tableName=user_account		\N	5.0.1	\N	\N	4727109315
1414872417007-297	adduseraccountcolumns	db-changelog-1.15.xml	2025-12-03 01:58:33.934667	296	EXECUTED	9:fc729e50a9ac4d8481cfc0e5e615c094	update tableName=user_account		\N	5.0.1	\N	\N	4727109315
1414872417007-300	createextenions-only-works-if-superuser-so-create-before	db-changelog-1.16.xml	2025-12-03 01:58:33.954298	297	EXECUTED	9:3e937406a4d82aec00fddeafc4c55380	sql; sql; sql		\N	5.0.1	\N	\N	4727109315
1414872417007-301	createEncryptedValueTable	db-changelog-1.17.xml	2025-12-03 01:58:33.958898	298	EXECUTED	9:63965b31aa35ce0c2e58f92f14a071bc	createTable tableName=encrypted_value		\N	5.0.1	\N	\N	4727109315
1414872417007-302	encryptedValueTableAddColumns	db-changelog-1.18.xml	2025-12-03 01:58:33.962673	299	EXECUTED	9:9b23ce1c3d7f2d32d2cb725996846872	addColumn tableName=encrypted_value; addColumn tableName=encrypted_value		\N	5.0.1	\N	\N	4727109315
1414872417007-303	deleteUserAccountGoogleMFAColumns	db-changelog-1.18.xml	2025-12-03 01:58:33.969196	300	EXECUTED	9:1c6f160cd4532f7df63c6b6691d384dc	dropForeignKeyConstraint baseTableName=USER_ACCOUNT_GOOGLE_AUTH_SCR_0, constraintName=fk_8931ymg13vy6vfkrichdsd4; dropPrimaryKey constraintName=USER_ACCOUNT_GOOGLE_AUTH_SCR_0_pkey, tableName=USER_ACCOUNT_GOOGLE_AUTH_SCR_0; dropTable tableName=USER...		\N	5.0.1	\N	\N	4727109315
1414872417007-304	gdpr-classification-update-account	db-changelog-1.18.xml	2025-12-03 01:58:33.983884	301	EXECUTED	9:a785758642766d317c33f7a42de5b391	sql; sql; sql; sql; sql; sql		\N	5.0.1	\N	\N	4727109315
1414872417007-305	encryptedValueTableChangeStorageType	db-changelog-1.18.xml	2025-12-03 01:58:33.989256	302	EXECUTED	9:91dc17b871f8f01ef34ab9ab7bf2c363	modifyDataType columnName=storage, tableName=encrypted_value		\N	5.0.1	\N	\N	4727109315
1414872417007-312	auditViews	db-changelog-1.19.xml	2025-12-03 01:58:34.02118	303	EXECUTED	9:11cdf7f46afe7b66fc1f50bc92ec575a	createSequence sequenceName=jv_commit_pk_seq; createSequence sequenceName=jv_global_id_pk_seq; createSequence sequenceName=jv_snapshot_pk_seq; createTable tableName=jv_commit; createTable tableName=jv_commit_property; createTable tableName=jv_glob...		\N	5.0.1	\N	\N	4727109315
1414872417007-313	auditViews	db-changelog-1.20.xml	2025-12-03 01:58:34.025444	304	EXECUTED	9:1d1f97a288995bd7b6a0004330ef9ed3	createView viewName=view_audit_data_summary; createView viewName=view_audit_author_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-314	auditViews2	db-changelog-1.20.xml	2025-12-03 01:58:34.028554	305	EXECUTED	9:bcf4660ae87b9d67626071f78bc8e9d1	dropView viewName=view_audit_data_summary; createView viewName=view_audit_data_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-315	documentSummaryViews	db-changelog-1.21.xml	2025-12-03 01:58:34.033351	306	EXECUTED	9:6bd6a54409a0ca14cd36aaaa54ae8b58	createView viewName=view_riksdagen_person_signed_document_summary; createView viewName=view_riksdagen_party_signatures_document_summary		\N	5.0.1	\N	\N	4727109315
1414872417007-316	auditViews3	db-changelog-1.21.xml	2025-12-03 01:58:34.036627	307	EXECUTED	9:80be555ad4c46adc54270b99c8481315	dropView viewName=view_audit_data_summary; createView viewName=view_audit_data_summary		\N	5.0.1	\N	\N	4727109315
2414872417007-317	javersAddColumn	db-changelog-1.22.xml	2025-12-03 01:58:34.038999	308	EXECUTED	9:cadb829cc1c2e0dd2941a93cdd411563	addColumn tableName=jv_commit		\N	5.0.1	\N	\N	4727109315
2414872417007-318	javersChangeType	db-changelog-1.22.xml	2025-12-03 01:58:34.042867	309	EXECUTED	9:d795deb20b39b5a787ce1d98d48e65e5	modifyDataType columnName=commit_date_instant, tableName=jv_commit		\N	5.0.1	\N	\N	4727109315
2414872417007-319	javersDefaultValue2	db-changelog-1.22.xml	2025-12-03 01:58:34.044636	310	EXECUTED	9:423aa876ea8cd648b2cc511580862e4f	sql		\N	5.0.1	\N	\N	4727109315
2414872417007-320	javersDefaultValue	db-changelog-1.22.xml	2025-12-03 01:58:34.047177	311	EXECUTED	9:4e7a8dfeb87f3809922803b64ceded57	addDefaultValue columnName=commit_date_instant, tableName=jv_commit		\N	5.0.1	\N	\N	4727109315
2414872417007-321	party_trends	db-changelog-1.23.xml	2025-12-03 01:58:34.052506	312	EXECUTED	9:8909e0c5d3ef6ecfeb4af4e93d3295ef	createView viewName=view_riksdagen_party_ballot_support_annual_summary; createView viewName=view_riksdagen_party_coalation_against_annual_summary		\N	5.0.1	\N	\N	4727109315
2414872417007-322	party_trends	db-changelog-1.23.xml	2025-12-03 01:58:34.056532	313	EXECUTED	9:bdcfbe51f13c670188c9a003e84f7391	dropView viewName=view_riksdagen_party_ballot_support_annual_summary; createView viewName=view_riksdagen_party_ballot_support_annual_summary		\N	5.0.1	\N	\N	4727109315
2414872417007-323	party_trends	db-changelog-1.23.xml	2025-12-03 01:58:34.06003	314	EXECUTED	9:345a31d3673a4e908c0f842854b543e7	dropView viewName=view_riksdagen_party_coalation_against_annual_summary; createView viewName=view_riksdagen_party_coalation_against_annual_summary		\N	5.0.1	\N	\N	4727109315
2414872417007-324	party_trends	db-changelog-1.23.xml	2025-12-03 01:58:34.065939	315	EXECUTED	9:9aad9d408015cdda50e8624d708db21f	dropView viewName=view_riksdagen_party_ballot_support_annual_summary; createView viewName=view_riksdagen_party_ballot_support_annual_summary		\N	5.0.1	\N	\N	4727109315
2414872417007-324	party_trends	db-changelog-1.24.xml	2025-12-03 01:58:34.070264	316	EXECUTED	9:9aad9d408015cdda50e8624d708db21f	dropView viewName=view_riksdagen_party_ballot_support_annual_summary; createView viewName=view_riksdagen_party_ballot_support_annual_summary		\N	5.0.1	\N	\N	4727109315
2414872417007-325	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.074415	317	EXECUTED	9:a98bd9300cbce790409b1438eb5681ab	dropView viewName=view_riksdagen_party_role_member; createView viewName=view_riksdagen_party_role_member		\N	5.0.1	\N	\N	4727109315
2414872417007-326	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.078176	318	EXECUTED	9:769fd2cc719f8070361d0236c2f219ca	createTable tableName=rule_violation		\N	5.0.1	\N	\N	4727109315
2414872417007-327	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.080299	319	EXECUTED	9:49044a256f906a54d7ccb6cdf3eeba70	addColumn tableName=rule_violation		\N	5.0.1	\N	\N	4727109315
2414872417007-328	pether (generated)	db-changelog-1.24.xml	2025-12-03 01:58:34.083283	320	EXECUTED	9:6246b135264fb22f812528142c12222c	addPrimaryKey constraintName=application_configuration_pkey, tableName=application_configuration		\N	5.0.1	\N	\N	4727109315
1414872417007-329	pether (generated)	db-changelog-1.24.xml	2025-12-03 01:58:34.091156	321	EXECUTED	9:bbb17b2f46dd435a351912318ba127e7	sql; modifyDataType columnName=indicator_name, tableName=indicator_element; modifyDataType columnName=source_id, tableName=indicator_element; modifyDataType columnName=source_value, tableName=indicator_element; sql		\N	5.0.1	\N	\N	4727109315
1414872417007-330	pether (generated)	db-changelog-1.24.xml	2025-12-03 01:58:34.099093	322	EXECUTED	9:5dbf93e01244ac5381aab449179bfee3	createIndex indexName=application_action_event_created_date_idx, tableName=application_action_event; createIndex indexName=application_action_event_sessionid_idx, tableName=application_action_event; createIndex indexName=application_action_event_e...		\N	5.0.1	\N	\N	4727109315
1414872417007-331	pether (generated)	db-changelog-1.24.xml	2025-12-03 01:58:34.110776	323	EXECUTED	9:d98cea6bed923440f8c3a31871a66ca2	sql		\N	5.0.1	\N	\N	4727109315
extend-view-riksdagen-politician-20231002	pether 	db-changelog-1.24.xml	2025-12-03 01:58:34.145031	324	EXECUTED	9:c7e51eab90afc2cec2f73cd365e799bf	dropView viewName=view_riksdagen_party; dropView viewName=view_riksdagen_party_summary; dropView viewName=view_riksdagen_politician; dropView viewName=view_riksdagen_party_member; createView viewName=view_riksdagen_party_member; createView viewNam...		\N	5.0.1	\N	\N	4727109315
new_changeset_id_1	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.149323	325	EXECUTED	9:3111b7723aeefc1cbe0f17bb0fd23c0c	dropView viewName=view_application_action_event_page_annual_summary; createView viewName=view_application_action_event_page_annual_summary		\N	5.0.1	\N	\N	4727109315
new_changeset_id_2	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.154396	326	EXECUTED	9:50ec6175bbb9840ff5f9a86088f4cb4b	dropView viewName=view_application_action_event_page_daily_summary; createView viewName=view_application_action_event_page_daily_summary		\N	5.0.1	\N	\N	4727109315
new_changeset_id_3	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.159712	327	EXECUTED	9:79aafee120630bbaa7c54b9a557bc740	dropView viewName=view_application_action_event_page_element_annual_summary; createView viewName=view_application_action_event_page_element_annual_summary		\N	5.0.1	\N	\N	4727109315
new_changeset_id_4	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.163964	328	EXECUTED	9:3b0d31d9363ad768e1180b3b988c2fec	dropView viewName=view_application_action_event_page_element_daily_summary; createView viewName=view_application_action_event_page_element_daily_summary		\N	5.0.1	\N	\N	4727109315
new_changeset_id_5	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.170293	329	EXECUTED	9:0c64722b12c51dca3e64006372ccc5c8	createIndex indexName=application_action_event_page_idx, tableName=application_action_event; createIndex indexName=application_action_event_element_id_idx, tableName=application_action_event; createIndex indexName=application_action_event_page_cre...		\N	5.0.1	\N	\N	4727109315
1414872417007-265-document-summary-views	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.207076	330	EXECUTED	9:acc9a8db7b3db0cd2dcb3ce083a989a7	sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; sql; createView viewName=view_riksdagen_party_document_summary; createIndex indexName=idx_rpd_person_ref_id, tableName=view_riksdagen_politician_document; createIndex indexName=idx_rpd_doc_type_sub...		\N	5.0.1	\N	\N	4727109315
extend-view-riksdagen-politician-party-20231223	pethers	db-changelog-1.24.xml	2025-12-03 01:58:34.246902	331	EXECUTED	9:892d39a59b866cdaf0366544ffb0d3bd	dropView viewName=view_riksdagen_party; dropView viewName=view_riksdagen_party_summary; dropView viewName=view_riksdagen_party_document_summary; dropView viewName=view_riksdagen_politician; dropView viewName=view_riksdagen_party_member; createView...		\N	5.0.1	\N	\N	4727109315
1414872417007-204	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.261856	332	EXECUTED	9:69f5c89bfe631122c51b8dfe35022fa1	dropView viewName=view_riksdagen_committee_parliament_member_proposal; dropView viewName=view_riksdagen_committee; createView viewName=view_riksdagen_committee; createView viewName=view_riksdagen_committee_parliament_member_proposal; createIndex i...		\N	5.0.1	\N	\N	4727109315
1414872417007-225	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.274228	333	EXECUTED	9:e5519073bd6d48b443777395119afafb	dropView viewName=view_riksdagen_goverment; createView viewName=view_riksdagen_goverment; dropView viewName=view_riksdagen_goverment_role_member; createView viewName=view_riksdagen_goverment_role_member		\N	5.0.1	\N	\N	4727109315
party-role-member-1414872417007-227	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.281672	334	EXECUTED	9:fbe676dfd287959902eaeb407d2e1bd9	dropView viewName=view_riksdagen_party_role_member; createView viewName=view_riksdagen_party_role_member		\N	5.0.1	\N	\N	4727109315
committee-role-member-1414872417007-228	pether	db-changelog-1.24.xml	2025-12-03 01:58:34.291437	335	EXECUTED	9:033dba6bbd5a8556bd1ad33d81873080	dropView viewName=view_riksdagen_committee_role_member; createView viewName=view_riksdagen_committee_role_member		\N	5.0.1	\N	\N	4727109315
vote-data-324	pether	db-changelog-1.25.xml	2025-12-03 01:58:34.2977	336	EXECUTED	9:515f70411429a7a26e07be4b37742628	sql		\N	5.0.1	\N	\N	4727109315
20241224-ballot-summary-view	pethers	db-changelog-1.25.xml	2025-12-03 01:58:34.488349	337	EXECUTED	9:533172c9a2e8e77072ba3d377b95406e	sql; sql; sql; sql; sql; sql; sql; sql; sql		\N	5.0.1	\N	\N	4727109315
2024122623-extend-party-view	pethers	db-changelog-1.25.xml	2025-12-03 01:58:34.505335	338	EXECUTED	9:c0383a8d45978f6163e0ff64924f5ddc	dropView viewName=view_riksdagen_party; dropView viewName=view_riksdagen_party_summary; dropView viewName=view_riksdagen_party_document_summary; dropView viewName=view_riksdagen_politician; dropView viewName=view_riksdagen_party_member; createView...	Extend party view with additional political analysis metrics using existing data	\N	5.0.1	\N	\N	4727109315
20241226-improve-politician-view	pethers	db-changelog-1.25.xml	2025-12-03 01:58:34.514298	339	EXECUTED	9:f13bf14c659e3df9d2ba71facc1e144a	createView viewName=view_riksdagen_politician	Enhance politician view with comprehensive metrics and committee effectiveness	\N	5.0.1	\N	\N	4727109315
20241227-enhance-party-document-summary	pethers	db-changelog-1.25.xml	2025-12-03 01:58:34.523891	340	EXECUTED	9:ccc25367d2b7d5b9ffcbe5465066e6b0	createView viewName=view_riksdagen_party_document_summary	Enhance party document summary with comprehensive metrics while preserving existing functionality	\N	5.0.1	\N	\N	4727109315
20241227-enhance-party-summary-performance	pethers	db-changelog-1.25.xml	2025-12-03 01:58:34.532828	341	EXECUTED	9:d14911091c781dc9028e99cac0c0e9b8	createIndex indexName=idx_assignment_data_type_dates, tableName=assignment_data; sql	Add indexes to improve party summary view performance	\N	5.0.1	\N	\N	4727109315
2024122723-enhance-party-summary-performance	pethers	db-changelog-1.25.xml	2025-12-03 01:58:34.582411	342	EXECUTED	9:18fddfa9b12f93f8296ae4c494a712c8	sql	Add indexes to improve party summary view performance	\N	5.0.1	\N	\N	4727109315
8774122723-enhance-party	pethers	db-changelog-1.25.xml	2025-12-03 01:58:34.587072	343	EXECUTED	9:c7d05218977bc2edb5f16e8d878af5c3	createView viewName=view_riksdagen_party	Add indexes to improve party view	\N	5.0.1	\N	\N	4727109315
revert-jpa-model-update	pethers	db-changelog-1.26.xml	2025-12-03 01:58:34.638701	344	EXECUTED	9:ee172fa2cfbce95c78e2b983b0b507dc	dropView viewName=view_riksdagen_party; sql; dropView viewName=view_riksdagen_party_document_summary; dropView viewName=view_riksdagen_politician; dropView viewName=view_riksdagen_party_member; createView viewName=view_riksdagen_party_member; crea...		\N	5.0.1	\N	\N	4727109315
312321-view_riksdagen_politician_ballot_summary	pethers	db-changelog-1.27.xml	2025-12-03 01:58:34.647376	346	EXECUTED	9:14219bf99de56328f50c56503b50306c	createView viewName=view_riksdagen_politician_ballot_summary		\N	5.0.1	\N	\N	4727109315
672321-view_riksdagen_politician_experience_summary	pethers	db-changelog-1.28.xml	2025-12-03 01:58:34.671289	347	EXECUTED	9:3c2e1cfdcdcc06b9f96fd9f52d49c12a	createView viewName=view_riksdagen_politician_experience_summary		\N	5.0.1	\N	\N	4727109315
672321-view_riksdagen_politician_experience_summary-update	pethers	db-changelog-1.28.xml	2025-12-03 01:58:34.690156	348	EXECUTED	9:45e09b8b79cdd4f087c589f1773919f9	dropView viewName=view_riksdagen_politician_experience_summary; createView viewName=view_riksdagen_politician_experience_summary		\N	5.0.1	\N	\N	4727109315
672321-view_riksdagen_politician_experience_summary-update2	pethers	db-changelog-1.28.xml	2025-12-03 01:58:34.709053	349	EXECUTED	9:c47fecd951a060b4c17f34e08790d4b1	dropView viewName=view_riksdagen_politician_experience_summary; createView viewName=view_riksdagen_politician_experience_summary		\N	5.0.1	\N	\N	4727109315
672321-view_riksdagen_politician_experience_summary-update3	pethers	db-changelog-1.28.xml	2025-12-03 01:58:34.729852	350	EXECUTED	9:f2a8bbfb00ed3f67696a7bcca3d50c70	dropView viewName=view_riksdagen_politician_experience_summary; createView viewName=view_riksdagen_politician_experience_summary		\N	5.0.1	\N	\N	4727109315
672321-view_riksdagen_politician_experience_summary-update4	pethers	db-changelog-1.28.xml	2025-12-03 01:58:34.748593	351	EXECUTED	9:1126cc71ce6315bf43bcd967a39b9aa0	dropView viewName=view_riksdagen_politician_experience_summary; createView viewName=view_riksdagen_politician_experience_summary		\N	5.0.1	\N	\N	4727109315
67267321-view_riksdagen_politician_experience_summary-update5	pethers	db-changelog-1.28.xml	2025-12-03 01:58:34.774145	352	EXECUTED	9:dd2ca5f83bdadbb3fb61a8be06f4b873	dropView viewName=view_riksdagen_politician_experience_summary; createView viewName=view_riksdagen_politician_experience_summary		\N	5.0.1	\N	\N	4727109315
intops-2025111101-temporal-momentum	intelligence-ops	db-changelog-1.29.xml	2025-12-03 01:58:34.781457	353	EXECUTED	9:ebb0e4ff417fbb4dc385d74f99df287f	createView viewName=view_riksdagen_party_momentum_analysis	Temporal Momentum Analysis View\n        \n        Intelligence Purpose:\n        - Detect momentum shifts in party support\n        - Identify acceleration/deceleration patterns\n        - Calculate moving averages for trend smoothing\n        - Measur...	\N	5.0.1	\N	\N	4727109315
intops-2025111102-coalition-alignment	intelligence-ops	db-changelog-1.29.xml	2025-12-03 01:58:34.787918	354	EXECUTED	9:4754a1d61b41149c0d4fce931959b038	createView viewName=view_riksdagen_coalition_alignment_matrix	Coalition Voting Alignment Matrix\n        \n        Intelligence Purpose:\n        - Measure voting alignment between party pairs\n        - Identify natural coalition partners\n        - Detect coalition stress or breaking\n        - Predict coalition...	\N	5.0.1	\N	\N	4727109315
intops-2025111103-anomaly-detection	intelligence-ops	db-changelog-1.29.xml	2025-12-03 01:58:34.803426	355	EXECUTED	9:07a8006b3987714bbaf9a757605df73a	createView viewName=view_riksdagen_voting_anomaly_detection	Political Anomaly Detection View\n        \n        Intelligence Purpose:\n        - Detect unusual voting behavior by politicians\n        - Identify party discipline breakdowns\n        - Flag potential defection risks\n        - Monitor conscience vo...	\N	5.0.1	\N	\N	4727109315
intops-2025111104-influence-metrics	intelligence-ops	db-changelog-1.29.xml	2025-12-03 01:58:34.811632	356	EXECUTED	9:c2da8e7169d2c6c0358e4217462d4d7b	createView viewName=view_riksdagen_politician_influence_metrics	Politician Influence Network Metrics\n        \n        Intelligence Purpose:\n        - Calculate basic network centrality measures\n        - Identify key brokers and connectors\n        - Map informal influence beyond formal roles\n        - Detect p...	\N	5.0.1	\N	\N	4727109315
intops-2025111105-crisis-resilience	intelligence-ops	db-changelog-1.29.xml	2025-12-03 01:58:34.821156	357	EXECUTED	9:542aa60a4bce5eabfd5a50260a0bdf69	createView viewName=view_riksdagen_crisis_resilience_indicators	Political Crisis Resilience Indicators\n        \n        Intelligence Purpose:\n        - Assess politician performance during high-pressure periods\n        - Identify crisis-tested leaders\n        - Measure consistency under stress\n        - Evalua...	\N	5.0.1	\N	\N	4727109315
intops-2025111106-intelligence-dashboard	intelligence-ops	db-changelog-1.29.xml	2025-12-03 01:58:34.829002	358	EXECUTED	9:cbc88852e1308329aa065d9eb411639e	createView viewName=view_riksdagen_intelligence_dashboard	Intelligence Operations Summary Dashboard\n        \n        Intelligence Purpose:\n        - Provide executive-level overview of political landscape\n        - Aggregate key intelligence indicators\n        - Enable rapid situation assessment\n        ...	\N	5.0.1	\N	\N	4727109315
intops-2025111107-politician-risk-summary	intelligence-operative	db-changelog-1.29.xml	2025-12-03 01:58:34.837152	359	EXECUTED	9:d77a8fce731965ddc609c1104bcc0052	createView viewName=view_politician_risk_summary	Politician Risk Summary View\n        \n        Intelligence Purpose:\n        - Aggregate all risk indicators for each politician\n        - Provide comprehensive risk score based on rule violations, attendance (absence rates), voting effectiveness (...	\N	5.0.1	\N	\N	4727109315
intops-2025111107-politician-risk-idx	intelligence-operative	db-changelog-1.29.xml	2025-12-03 01:58:34.843375	360	EXECUTED	9:cdfed1beeb97276a94e40e00dfde6057	sql	Performance Indexes for Politician Risk Queries\n        \n        Indexes on frequently queried columns to support rapid risk assessment\n        and dashboard queries.	\N	5.0.1	\N	\N	4727109315
intops-2025111108-party-performance-metrics	intelligence-operative	db-changelog-1.29.xml	2025-12-03 01:58:34.861179	361	EXECUTED	9:001f5b3ea0c9f62f07d81f7b3e923f12	createView viewName=view_party_performance_metrics	Party Performance Metrics View\n        \n        Intelligence Purpose:\n        - Aggregate party-wide performance indicators\n        - Enable comparative party analysis\n        - Track organizational effectiveness\n        - Support coalition format...	\N	5.0.1	\N	\N	4727109315
intops-2025111109-committee-productivity	intelligence-operative	db-changelog-1.29.xml	2025-12-03 01:58:34.87707	362	EXECUTED	9:e9a0dd593d407533c4959eae10d1b42c	createView viewName=view_committee_productivity	Committee Productivity View\n        \n        Intelligence Purpose:\n        - Track committee legislative output\n        - Measure committee effectiveness\n        - Identify underperforming committees\n        - Support resource allocation decisions...	\N	5.0.1	\N	\N	4727109315
osint-2025111500-refresh-materialized-views	intelligence-operative	db-changelog-1.30.xml	2025-12-03 01:58:34.936555	363	EXECUTED	9:28eb73f089cc2b312024e56b87d7dd82	sql	Refresh Materialized Views for v1.30 Dependencies\n        \n        Purpose:\n        - Populate materialized views required by v1.30 OSINT views\n        - Ensure data availability for temporal analysis queries\n        - Execute in dependency order ...	\N	5.0.1	\N	\N	4727109315
osint-2025111501-politician-behavioral-trends	intelligence-operative	db-changelog-1.30.xml	2025-12-03 01:58:34.942975	364	EXECUTED	9:5d1ec1f15e980de2297f9a1ab18db6c6	createView viewName=view_politician_behavioral_trends	Politician Behavioral Trends View\n        \n        Intelligence Purpose:\n        - Track time-series behavioral patterns for each politician\n        - Monitor absence rates, voting effectiveness, party discipline\n        - Identify behavioral chan...	\N	5.0.1	\N	\N	4727109315
osint-2025111502-party-effectiveness-trends	intelligence-operative	db-changelog-1.30.xml	2025-12-03 01:58:34.9511	365	EXECUTED	9:e90b3cc562a26253599e50803c2ea928	createView viewName=view_party_effectiveness_trends	Party Effectiveness Trends View\n        \n        Intelligence Purpose:\n        - Monitor party-level performance metrics over time\n        - Track win rates, productivity, and collaboration patterns\n        - Identify organizational strengths and ...	\N	5.0.1	\N	\N	4727109315
osint-2025111503-risk-score-evolution	intelligence-operative	db-changelog-1.30.xml	2025-12-03 01:58:34.959087	366	EXECUTED	9:ec6554f6fd6a4de381f8a97d46bd5663	createView viewName=view_risk_score_evolution	Risk Score Evolution View\n        \n        Intelligence Purpose:\n        - Track historical changes in politician risk scores\n        - Monitor severity transitions (escalation/de-escalation)\n        - Identify risk patterns and triggers\n        -...	\N	5.0.1	\N	\N	4727109315
osint-2025111504-committee-productivity-matrix	intelligence-operative	db-changelog-1.30.xml	2025-12-03 01:58:34.965687	367	EXECUTED	9:07244f5572e4a6fe02140b2ee16c586f	createView viewName=view_committee_productivity_matrix	Committee Productivity Matrix View\n        \n        Intelligence Purpose:\n        - Monitor committee output and effectiveness by period\n        - Benchmark committee performance against historical data\n        - Identify productive vs. underperfo...	\N	5.0.1	\N	\N	4727109315
osint-2025111505-performance-indexes	intelligence-operative	db-changelog-1.30.xml	2025-12-03 01:58:34.970916	368	EXECUTED	9:e98bc86dc1435e8492ffaa48d97db931	sql	Performance Indexes for OSINT Intelligence Queries\n        \n        These indexes optimize the most common temporal queries used in\n        intelligence analysis and dashboard operations.	\N	5.0.1	\N	\N	4727109315
ministry-2025111701-effectiveness-trends	intelligence-operative	db-changelog-1.31.xml	2025-12-03 01:58:34.978804	369	EXECUTED	9:427996676625f2b8dce8395553aa0b77	createView viewName=view_ministry_effectiveness_trends	Ministry Effectiveness Trends View\n        \n        Intelligence Purpose:\n        - Track ministry-level performance metrics over time\n        - Monitor document production, legislative output, and staffing\n        - Identify productive vs. underp...	\N	5.0.1	\N	\N	4727109315
ministry-2025111702-productivity-matrix	intelligence-operative	db-changelog-1.31.xml	2025-12-03 01:58:34.985433	370	EXECUTED	9:179ccaf2df5469f386c4f0586073cea7	createView viewName=view_ministry_productivity_matrix	Ministry Productivity Matrix View\n        \n        Intelligence Purpose:\n        - Benchmark ministry performance against peers\n        - Identify most and least productive ministries\n        - Normalize metrics for fair comparison\n        - Suppo...	\N	5.0.1	\N	\N	4727109315
ministry-2025111703-risk-evolution	intelligence-operative	db-changelog-1.31.xml	2025-12-03 01:58:34.992613	371	EXECUTED	9:1a81b471095b1d7cb4d9c78e3393bb93	createView viewName=view_ministry_risk_evolution	Ministry Risk Evolution View\n        \n        Intelligence Purpose:\n        - Track historical changes in ministry risk scores\n        - Monitor risk severity transitions for ministries\n        - Identify risk patterns and triggers at ministry lev...	\N	5.0.1	\N	\N	4727109315
ministry-2025111704-performance-indexes	intelligence-operative	db-changelog-1.31.xml	2025-12-03 01:58:34.998276	372	EXECUTED	9:5186a2215528e93478d78b12fc23941b	sql	Performance Indexes for Ministry Intelligence Queries\n        \n        These indexes optimize the most common temporal queries used in\n        ministry intelligence analysis and government effectiveness dashboards.	\N	5.0.1	\N	\N	4727109315
fix-politician-risk-summary-1.32-001	intelligence-analyst	db-changelog-1.32.xml	2025-12-03 01:58:35.006929	373	EXECUTED	9:a5bd4210d7968d1004790f7d482867e8	dropView viewName=view_politician_risk_summary; createView viewName=view_politician_risk_summary	Fix view_politician_risk_summary to return data\n        \n        Root Cause: The JOIN condition on view_riksdagen_vote_data_ballot_politician_summary_annual\n        was filtering on an exact date (date_trunc('year', CURRENT_DATE - INTERVAL '1 year...	\N	5.0.1	\N	\N	4727109315
verify-other-views-1.32-002	intelligence-analyst	db-changelog-1.32.xml	2025-12-03 01:58:35.009093	374	EXECUTED	9:72d8a71e1aae0b931ebd79e83a10cf1d	sql	Verification changeset for other politician intelligence views\n        \n        After investigation, the following views should work correctly as they\n        query vote_data directly without restrictive date filters on aggregated views:\n        \n...	\N	5.0.1	\N	\N	4727109315
document-fixes-1.32-003	intelligence-analyst	db-changelog-1.32.xml	2025-12-03 01:58:35.011234	375	EXECUTED	9:8a10e26023ff9e74bf785363f03efdd6	sql	Documentation for v1.32 fixes\n        \n        This changeset documents the fixes applied to politician intelligence views\n        for inclusion in DATABASE_VIEW_INTELLIGENCE_CATALOG.md and \n        TROUBLESHOOTING_EMPTY_VIEWS.md.\n        \n       ...	\N	5.0.1	\N	\N	4727109315
fix-goverment-proposals-1.32-004	intelligence-analyst	db-changelog-1.32.xml	2025-12-03 01:58:35.01554	376	EXECUTED	9:c881ba745964943eb175bc0ff9c55520	dropView viewName=view_riksdagen_goverment_proposals; createView viewName=view_riksdagen_goverment_proposals	Fix view_riksdagen_goverment_proposals to return data\n        \n        Root Cause: The view filters by document_type = 'PROP' (uppercase), but\n        the actual data in document_data may use different case variations:\n        - 'prop' (lowercase)...	\N	5.0.1	\N	\N	4727109315
verify-ministry-dependencies-1.32-005	intelligence-analyst	db-changelog-1.32.xml	2025-12-03 01:58:35.018845	377	EXECUTED	9:344f3666045ec90f277df19c3812110c	sql	Verify Ministry View Dependencies\n        \n        The 3 ministry views created in v1.31 depend on:\n        1. assignment_data table with assignment_type = 'Departement'\n        2. view_riksdagen_politician_document materialized view\n        3. Ma...	\N	5.0.1	\N	\N	4727109315
document-ministry-troubleshooting-1.32-006	intelligence-analyst	db-changelog-1.32.xml	2025-12-03 01:58:35.020955	378	EXECUTED	9:11eae01f4f5f0d419aacbe440d4b60f3	sql	Document Ministry View Troubleshooting\n        \n        This changeset provides documentation for TROUBLESHOOTING_EMPTY_VIEWS.md\n        specific to ministry views created in v1.31.\n        \n        Ministry Views Fixed in v1.32:\n        1. view_r...	\N	5.0.1	\N	\N	4727109315
fix-member-proposals-1.33-001	intelligence-analyst	db-changelog-1.33.xml	2025-12-03 01:58:35.025458	379	EXECUTED	9:eac9e9cd1f0bd8fd4859b8d1954aa3c9	dropView viewName=view_riksdagen_member_proposals; createView viewName=view_riksdagen_member_proposals	Fix view_riksdagen_member_proposals to return data\n        \n        Root Cause: The view filters by document_type = 'MOT' (uppercase), but\n        the actual data in document_element contains 'mot' (lowercase).\n        \n        Solution: Use UPPER...	\N	5.0.1	\N	\N	4727109315
fix-committee-member-proposals-1.33-002	intelligence-analyst	db-changelog-1.33.xml	2025-12-03 01:58:35.030656	380	EXECUTED	9:ba278abacebf49620c4f2c950652b71d	dropView viewName=view_riksdagen_committee_parliament_member_proposal; createView viewName=view_riksdagen_committee_parliament_member_proposal	Fix view_riksdagen_committee_parliament_member_proposal to return data\n        \n        Root Cause: Same as view_riksdagen_member_proposals - the view filters \n        by document_type = 'MOT' (uppercase) but data contains 'mot' (lowercase).\n     ...	\N	5.0.1	\N	\N	4727109315
fix-crisis-resilience-indicators-1.33-003	intelligence-analyst	db-changelog-1.33.xml	2025-12-03 01:58:35.04372	381	EXECUTED	9:a9fb314829a17674f001f82a1d233f70	sql; createView viewName=view_riksdagen_crisis_resilience_indicators	Fix view_riksdagen_crisis_resilience_indicators to return data\n        \n        Root Cause: The view filters vote values using exact case matches like\n        'Ja', 'Nej', 'Frånvarande', but the actual data contains 'JA', 'NEJ', \n        'FRÅNVARA...	\N	5.0.1	\N	\N	4727109315
recreate-intelligence-dashboard-1.33-003b	intelligence-analyst	db-changelog-1.33.xml	2025-12-03 01:58:35.051864	382	EXECUTED	9:cb8a00ecaa8b7e60d6d2420ad1992a1c	sql; createView viewName=view_riksdagen_intelligence_dashboard	Recreate view_riksdagen_intelligence_dashboard after crisis resilience fix\n        \n        This view was dropped by CASCADE when fixing view_riksdagen_crisis_resilience_indicators.\n        The view aggregates multiple intelligence indicators into...	\N	5.0.1	\N	\N	4727109315
fix-risk-score-evolution-1.33-004	intelligence-analyst	db-changelog-1.33.xml	2025-12-03 01:58:35.065065	383	EXECUTED	9:af3c81ce2158677248721986f3adb9c9	dropView viewName=view_risk_score_evolution; createView viewName=view_risk_score_evolution	Fix view_risk_score_evolution to return data\n        \n        Root Cause: The view requires data from view_riksdagen_vote_data_ballot_politician_summary_daily\n        within a 3-year window, which may not always have recent data if materialized vi...	\N	5.0.1	\N	\N	4727109315
document-fixes-1.33-005	intelligence-analyst	db-changelog-1.33.xml	2025-12-03 01:58:35.067062	384	EXECUTED	9:a18f424c14dc0f2c6d7754d7e378a882	sql	Documentation for v1.33 fixes\n        \n        This changeset documents the fixes applied to 4 empty intelligence views\n        for inclusion in TROUBLESHOOTING_EMPTY_VIEWS.md.\n        \n        Summary of Changes:\n        1. view_riksdagen_member_...	\N	5.0.1	\N	\N	4727109315
1.34-intro	database-architect	db-changelog-1.34.xml	2025-12-03 01:58:35.068664	385	EXECUTED	9:619faeb099abb68856d15be29569099b	sql	Database Changelog v1.34 - Comprehensive Empty View Fixes with Validation\n        \n        Consolidates fixes from issues #7882-#7885 for 12 empty views.\n        Adds pre-flight and post-flight validation for data availability.	\N	5.0.1	\N	\N	4727109315
1.34-preflight	database-architect	db-changelog-1.34.xml	2025-12-03 01:58:35.071567	386	EXECUTED	9:25a3bba16e7a992a8e29fbbe94a7bfcc	sql	Pre-flight: Verify source data exists before applying view fixes	\N	5.0.1	\N	\N	4727109315
1.34-ministry-verify-001	intelligence-analyst	db-changelog-1.34.xml	2025-12-03 01:58:35.07455	387	EXECUTED	9:3e9501744493d58dfd62f8e4eb7ce968	sql	Ministry views - Expected to be empty without ministry assignment data	\N	5.0.1	\N	\N	4727109315
1.34-gov-proposals-002	intelligence-analyst	db-changelog-1.34.xml	2025-12-03 01:58:35.07847	388	EXECUTED	9:98ee9cac78930ae7df9dea29166db568	dropView viewName=view_riksdagen_goverment_proposals; createView viewName=view_riksdagen_goverment_proposals	Fix view_riksdagen_goverment_proposals - broader document_type filter\n        Catches 'prop', 'PROP', 'Proposition' variations	\N	5.0.1	\N	\N	4727109315
1.34-member-proposals-003	intelligence-analyst	db-changelog-1.34.xml	2025-12-03 01:58:35.08357	389	EXECUTED	9:b13f7b02bfd25a9791548c069dc378de	dropView viewName=view_riksdagen_member_proposals; createView viewName=view_riksdagen_member_proposals	Fix view_riksdagen_member_proposals - broader document_type filter\n        Catches 'mot', 'MOT', 'Motion' variations	\N	5.0.1	\N	\N	4727109315
1.34-committee-proposals-004	intelligence-analyst	db-changelog-1.34.xml	2025-12-03 01:58:35.089076	390	EXECUTED	9:acfb00d23673ba2bfbcf78bc83c33bd7	dropView viewName=view_riksdagen_committee_parliament_member_proposal; createView viewName=view_riksdagen_committee_parliament_member_proposal	Fix view_riksdagen_committee_parliament_member_proposal\n        Same broader filter as member proposals	\N	5.0.1	\N	\N	4727109315
1.34-risk-summary-005	intelligence-analyst	db-changelog-1.34.xml	2025-12-03 01:58:35.099896	391	EXECUTED	9:4692e16ed702c30c880c5bf3d6afffcb	dropView viewName=view_politician_risk_summary; createView viewName=view_politician_risk_summary	Fix view_politician_risk_summary - simplified version using direct vote_data\n        Removes dependency on aggregated summary views that may not have data	\N	5.0.1	\N	\N	4727109315
1.34-verify-others-006	intelligence-analyst	db-changelog-1.34.xml	2025-12-03 01:58:35.104873	392	EXECUTED	9:7f86a1ea0b6dacdf6ce2eef8ce19ad8d	sql	Verify other politician and intelligence views exist (from v1.33)	\N	5.0.1	\N	\N	4727109315
1.34-postflight	database-architect	db-changelog-1.34.xml	2025-12-03 01:58:35.122617	393	EXECUTED	9:24f59e1f777d8317d7fb94354be07200	sql	Post-flight: Verify all 12 views and check row counts	\N	5.0.1	\N	\N	4727109315
1.34-documentation	database-architect	db-changelog-1.34.xml	2025-12-03 01:58:35.125252	394	EXECUTED	9:215e4217ceea39d393d357dfab47613b	sql	Documentation for v1.34 comprehensive view fixes\n        \n        Summary:\n        - Pre-flight validation checks source data\n        - Ministry views verified (expected empty without ministry data)\n        - Government proposals view fixed (broad...	\N	5.0.1	\N	\N	4727109315
1.35-intro	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.127383	395	EXECUTED	9:32e18dd7e0d25938d04971bf2130e750	sql	Database Changelog v1.35 - Party Decision Flow View\n        \n        Creates view_riksdagen_party_decision_flow for aggregating proposal\n        decision data by party, enabling party-level legislative effectiveness\n        and coalition analysis.	\N	5.0.1	\N	\N	4727109315
1.35-preflight	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.130902	396	EXECUTED	9:b1f1b765b24f30a4b717ee0da5b5e940	sql	Pre-flight: Verify source data exists before creating view	\N	5.0.1	\N	\N	4727109315
1.35-party-decision-flow-001	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.140449	397	EXECUTED	9:1055961759e4cdf793808144bc58b684	createView viewName=view_riksdagen_party_decision_flow	Create view_riksdagen_party_decision_flow\n        \n        Aggregates proposal decision data by party, committee, decision type, and time period.\n        Provides comprehensive party-level decision intelligence including:\n        - Total proposals...	\N	5.0.1	\N	\N	4727109315
1.35-party-decision-flow-index-002	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.148383	398	EXECUTED	9:1de6549e5d2e5b58509674d1ae8a55c6	sql	Create indexes on base tables for performance optimization of party decision flow queries\n        \n        Indexes on frequently joined and filtered columns:\n        - document_proposal_data.committee (for committee-specific queries)\n        - doc...	\N	5.0.1	\N	\N	4727109315
1.35-postflight	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.156579	399	EXECUTED	9:e091dfbf1f8a01f3049f40855ae047f3	sql	Post-flight: Verify view creation and check initial data	\N	5.0.1	\N	\N	4727109315
1.35-documentation	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.158985	400	EXECUTED	9:a995133e402948e807b1759d61b1b7c5	sql	Documentation for v1.35 party decision flow view\n        \n        Summary:\n        - Creates view_riksdagen_party_decision_flow for party-level decision aggregation\n        - Enables party scorecards, coalition analysis, committee effectiveness tr...	\N	5.0.1	\N	\N	4727109315
1.35-politician-decision-pattern-001	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.168743	401	EXECUTED	9:5db797f3c39c8716ceb1c7fac014ad7e	createView viewName=view_riksdagen_politician_decision_pattern	Create view_riksdagen_politician_decision_pattern\n        \n        Tracks individual politician decision patterns from document_proposal_data,\n        enabling analysis of politician-level proposal success rates, committee work\n        effectivene...	\N	5.0.1	\N	\N	4727109315
1.35-politician-decision-pattern-index-002	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.171915	402	EXECUTED	9:f3b01d3c763eaaa45863294b17329605	sql	Create indexes for performance optimization of politician decision pattern queries\n        \n        Index on person_id for efficient politician-specific queries.\n        These complement the existing base table indexes created in changeSet 002.	\N	5.0.1	\N	\N	4727109315
1.35-politician-decision-pattern-postflight	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.182735	403	EXECUTED	9:d2d93d66a90ac420fd94af497ef5f333	sql	Post-flight: Verify politician decision pattern view creation	\N	5.0.1	\N	\N	4727109315
1.35-decision-temporal-trends-001	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.194442	404	EXECUTED	9:edc0dbf51168562761bc8fc2c7681d44	createView viewName=view_decision_temporal_trends	Create view_decision_temporal_trends\n        \n        Temporal trends view for decision flow analysis from DOCUMENT_PROPOSAL_DATA,\n        enabling time-series analysis of decision patterns, seasonal variations,\n        and predictive forecasting ...	\N	5.0.1	\N	\N	4727109315
1.35-decision-temporal-trends-postflight	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.201937	405	EXECUTED	9:88dd7d0ff0afee32b8e6c317ef3f0577	sql	Post-flight: Verify temporal decision trends view creation and validate data	\N	5.0.1	\N	\N	4727109315
1.35-ministry-decision-impact-001	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.210508	406	EXECUTED	9:d2f6d28b97abf69bccd816fc3258aa8c	createView viewName=view_ministry_decision_impact	Create view_ministry_decision_impact\n        \n        Tracks ministry-initiated proposal outcomes from DOCUMENT_PROPOSAL_DATA,\n        enabling analysis of which government ministries have the highest/lowest\n        success rates for their legisla...	\N	5.0.1	\N	\N	4727109315
1.35-ministry-decision-impact-index-002	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.215348	407	EXECUTED	9:73ef6912726a17535c1aa90fb2427261	sql	Create indexes for performance optimization of ministry decision impact queries\n        \n        Index on document_data.org (ministry_code) and document_type for efficient\n        ministry-specific and government proposal queries.	\N	5.0.1	\N	\N	4727109315
1.35-ministry-decision-impact-postflight	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.221531	408	EXECUTED	9:2b1646700fdb341fd0c3a4e6a16fb8d9	sql	Post-flight: Verify ministry decision impact view creation and validate data	\N	5.0.1	\N	\N	4727109315
1.35-final-summary	intelligence-operative	db-changelog-1.35.xml	2025-12-03 01:58:35.22357	409	EXECUTED	9:3438e13b3d71b2e99c0220e56a54a810	sql	Final documentation summary for v1.35 database changelog\n        \n        Summary of v1.35 views created:\n        1. view_riksdagen_party_decision_flow - Party-level decision aggregation\n        2. view_riksdagen_politician_decision_pattern - Indi...	\N	5.0.1	\N	\N	4727109315
1736000000000-1	import-error-fix	db-changelog-1.36.xml	2025-12-03 01:58:35.226102	410	EXECUTED	9:1b1abcc812870fd0d7432002253e45f6	modifyDataType columnName=note, tableName=document_element	Increase document_element.note from VARCHAR(8192) to VARCHAR(65536) to accommodate long EU committee notes with HTML lists	\N	5.0.1	\N	\N	4727109315
1736000000000-2	import-error-fix	db-changelog-1.36.xml	2025-12-03 01:58:35.228443	411	EXECUTED	9:c3780abe8ec3d2fe6ef55ee2d25541de	modifyDataType columnName=note_title, tableName=document_element	Increase document_element.note_title from VARCHAR(8192) to VARCHAR(16384) for consistency and future-proofing	\N	5.0.1	\N	\N	4727109315
1736000000000-3	import-error-fix	db-changelog-1.36.xml	2025-12-03 01:58:35.231187	412	EXECUTED	9:7d4d1848977aaccfa3635879774c556e	modifyDataType columnName=summary, tableName=document_element	Increase document_element.summary from VARCHAR(8192) to VARCHAR(65536) to match note field size	\N	5.0.1	\N	\N	4727109315
fix-ministry-effectiveness-1.37-001	intelligence-operative	db-changelog-1.37.xml	2025-12-03 01:58:35.244544	413	EXECUTED	9:2eeb05933237619abae29188032cd4d0	dropView viewName=view_ministry_effectiveness_trends; createView viewName=view_ministry_effectiveness_trends	Fix view_ministry_effectiveness_trends with case-insensitive org_code matching\n        \n        Root Cause: org_code values in assignment_data and view_riksdagen_politician_document\n        have inconsistent casing (e.g., "Departement" vs "departe...	\N	5.0.1	\N	\N	4727109315
fix-ministry-productivity-1.37-002	intelligence-operative	db-changelog-1.37.xml	2025-12-03 01:58:35.253944	414	EXECUTED	9:ce261dfdef96131a2f75ddb6101ac655	dropView viewName=view_ministry_productivity_matrix; createView viewName=view_ministry_productivity_matrix	Fix view_ministry_productivity_matrix with case-insensitive org_code matching\n        \n        Same root cause and solution as effectiveness trends view.	\N	5.0.1	\N	\N	4727109315
fix-ministry-risk-evolution-1.37-003	intelligence-operative	db-changelog-1.37.xml	2025-12-03 01:58:35.272862	415	EXECUTED	9:1e6651e08f0b89e129e0ad572f056309	dropView viewName=view_ministry_risk_evolution; createView viewName=view_ministry_risk_evolution	Fix view_ministry_risk_evolution with case-insensitive org_code matching\n        \n        Same root cause and solution as other ministry views.	\N	5.0.1	\N	\N	4727109315
fix-coalition-alignment-1.37-004	intelligence-operative	db-changelog-1.37.xml	2025-12-03 01:58:35.281842	416	EXECUTED	9:71d9f34b8fc38a50bff1687da325dcdb	sql; createView viewName=view_riksdagen_coalition_alignment_matrix	Fix view_riksdagen_coalition_alignment_matrix with expanded date range\n        \n        Root Cause: 2-year date filter too restrictive - many parties only have older vote data.\n        \n        Solution: Expand from 2 years to 5 years to capture m...	\N	5.0.1	\N	\N	4727109315
fix-politician-risk-summary-1.37-005	intelligence-operative	db-changelog-1.37.xml	2025-12-03 01:58:35.29087	417	EXECUTED	9:a50ccc76324b132113a20f9eb3fcc4d6	dropView viewName=view_politician_risk_summary; createView viewName=view_politician_risk_summary	Fix view_politician_risk_summary using direct vote_data aggregation\n        \n        Root Cause: Dependency on materialized view annual summary creates timing issues\n        where data may not exist for specific year calculations.\n        \n       ...	\N	5.0.1	\N	\N	4727109315
fix-risk-score-evolution-1.38-001	intelligence-operative	db-changelog-1.38.xml	2025-12-03 01:58:35.303544	418	EXECUTED	9:157a899e1c10d9b4e540d8749a91b376	dropView viewName=view_risk_score_evolution; createView viewName=view_risk_score_evolution	Fix view_risk_score_evolution by removing materialized view dependency\n        \n        Root Cause: The view depends on view_riksdagen_vote_data_ballot_politician_summary_daily\n        (materialized) which is not populated in schema-only databases...	\N	5.0.1	\N	\N	4727109315
fix-crisis-resilience-1.38-002	intelligence-operative	db-changelog-1.38.xml	2025-12-03 01:58:35.315483	419	EXECUTED	9:0f94269ccd9de3e53cbebdcff429df4e	dropView viewName=view_riksdagen_crisis_resilience_indicators; createView viewName=view_riksdagen_crisis_resilience_indicators	Fix view_riksdagen_crisis_resilience_indicators with expanded date range\n        \n        Root Cause: 2-year date filter is too restrictive for detecting crisis patterns.\n        Similar to coalition alignment view issue (fixed in 1.37).\n        \n...	\N	5.0.1	\N	\N	4727109315
fix-politician-influence-1.38-003	intelligence-operative	db-changelog-1.38.xml	2025-12-03 01:58:35.323819	420	EXECUTED	9:70aeb6db83f75b0e0f7fa78095c4b9d0	dropView viewName=view_riksdagen_politician_influence_metrics; createView viewName=view_riksdagen_politician_influence_metrics	Fix view_riksdagen_politician_influence_metrics with expanded parameters\n        \n        Root Cause: 1-year date filter with 20-vote minimum threshold is too restrictive,\n        filtering out meaningful network connections and influence patterns...	\N	5.0.1	\N	\N	4727109315
fix-voting-anomaly-1.38-004	intelligence-operative	db-changelog-1.38.xml	2025-12-03 01:58:35.341785	421	EXECUTED	9:2f4ac1c1a94ad8b9487e899f0751b225	dropView viewName=view_riksdagen_voting_anomaly_detection; createView viewName=view_riksdagen_voting_anomaly_detection	Fix view_riksdagen_voting_anomaly_detection with expanded date range\n        \n        Root Cause: 1-year date filter provides insufficient historical context for\n        reliable anomaly detection. Pattern recognition benefits from longer periods....	\N	5.0.1	\N	\N	4727109315
fix-coalition-alignment-1.38-0022	intelligence-operative	db-changelog-1.38.xml	2025-12-03 01:58:35.350421	422	EXECUTED	9:8dda1ad871d012db9cd2a4d950e07152	sql; createView viewName=view_riksdagen_coalition_alignment_matrix	Transformed coalition alignment view to match existing Java entity structure.\n        Maps new analytical logic to legacy column names for backwards compatibility.\n        \n        Mapping:\n        - total_votes -> shared_votes\n        - aligned_v...	\N	5.0.1	\N	\N	4727109315
fix-politician-risk-summary-matview-1.39-001	intelligence-operative	db-changelog-1.39.xml	2025-12-03 01:58:35.360283	423	EXECUTED	9:4c6fe59977c8548ce3b1bcceab3115b6	dropView viewName=view_politician_risk_summary; createView viewName=view_politician_risk_summary	Fix view_politician_risk_summary by removing materialized view dependency\n        \n        Root Cause: The view uses LEFT JOIN on view_riksdagen_politician_document\n        (materialized view). Even with LEFT JOIN, PostgreSQL cannot execute the\n  ...	\N	5.0.1	\N	\N	4727109315
fix-ministry-effectiveness-1.39-001	intelligence-operative	db-changelog-1.39.xml	2025-12-03 01:58:35.367607	424	EXECUTED	9:ebd5c2f743b97d91375d1814fed3d0bb	dropView viewName=view_ministry_effectiveness_trends; createView viewName=view_ministry_effectiveness_trends	Fix view_ministry_effectiveness_trends to return actual ministry data\n        \n        Root Cause: View filtered by LOWER(org_code) LIKE '%departement%' but actual\n        ministry org_codes are short codes ("KN", "N", etc.) that don't contain "de...	\N	5.0.1	\N	\N	4727109315
fix-ministry-productivity-1.39-002	intelligence-operative	db-changelog-1.39.xml	2025-12-03 01:58:35.373334	425	EXECUTED	9:ce83258b81e92bd942a9f97bb9a9f197	dropView viewName=view_ministry_productivity_matrix; createView viewName=view_ministry_productivity_matrix	Fix view_ministry_productivity_matrix to return actual ministry data\n        \n        Root Cause: Same as view_ministry_effectiveness_trends - view filtered by \n        LOWER(org_code) LIKE '%departement%' but actual ministry org_codes are short \n...	\N	5.0.1	\N	\N	4727109315
fix-ministry-risk-evolution-1.39-003	intelligence-operative	db-changelog-1.39.xml	2025-12-03 01:58:35.379067	426	EXECUTED	9:e8ee5d2c422b306e4e36882237f5a21b	dropView viewName=view_ministry_risk_evolution; createView viewName=view_ministry_risk_evolution	Fix view_ministry_risk_evolution to return actual ministry data\n        \n        Root Cause: Same as view_ministry_effectiveness_trends - view filtered by \n        LOWER(org_code) LIKE '%departement%' but actual ministry org_codes are short \n     ...	\N	5.0.1	\N	\N	4727109315
verify-ministry-views-1.39-004	intelligence-operative	db-changelog-1.39.xml	2025-12-03 01:58:35.384913	427	EXECUTED	9:0e011b36ae0faab2f83c753e34a86658	sql	Post-flight verification for all three ministry views\n        \n        Checks:\n        1. All three views exist and can be queried\n        2. Views return data when ministry assignments are present\n        3. Row counts are reasonable (>0 when dat...	\N	5.0.1	\N	\N	4727109315
document-ministry-fix-1.39-005	intelligence-operative	db-changelog-1.39.xml	2025-12-03 01:58:35.386647	428	EXECUTED	9:830bb959528e4365f577fc21b94da2a8	sql	Documentation for v1.39 ministry views fix\n        \n        Summary:\n        - Root cause: Incorrect filter on org_code LIKE '%departement%'\n        - Actual data: org_code has short codes like 'KN', 'N', not full names\n        - Solution: Remove ...	\N	5.0.1	\N	\N	4727109315
fix-crisis-resilience-1.40-001	intelligence-operative	db-changelog-1.40.xml	2025-12-03 01:58:35.396533	429	EXECUTED	9:e34edc89148de2ec4f4766d8cb341105	dropView viewName=view_riksdagen_crisis_resilience_indicators; createView viewName=view_riksdagen_crisis_resilience_indicators	Fix view_riksdagen_crisis_resilience_indicators to return data\n        \n        Root Cause: The crisis period detection algorithm had gaps - months between\n        1x and 1.5x average were not classified as either crisis or normal periods.\n       ...	\N	5.0.1	\N	\N	4727109315
verify-crisis-resilience-1.40-002	intelligence-operative	db-changelog-1.40.xml	2025-12-03 01:58:35.401675	430	EXECUTED	9:1c92d454c7d1820ed9dc736aa6e104b1	sql	Post-flight verification for view_riksdagen_crisis_resilience_indicators\n        \n        Checks:\n        1. View exists and can be queried\n        2. View returns data when vote_data and person_data are populated\n        3. Reports row count for ...	\N	5.0.1	\N	\N	4727109315
recreate-intelligence-dashboard-1.40-003	intelligence-operative	db-changelog-1.40.xml	2025-12-03 01:58:35.407302	431	EXECUTED	9:8817dc415e89d5760524c4ccf8b5b406	createView viewName=view_riksdagen_intelligence_dashboard	Recreate view_riksdagen_intelligence_dashboard after crisis resilience fix\n        \n        This view was dropped by CASCADE when fixing view_riksdagen_crisis_resilience_indicators\n        in changeSet fix-crisis-resilience-1.40-001 (cascadeConstr...	\N	5.0.1	\N	\N	4727109315
fix-risk-score-evolution-1.41-001	intelligence-operative	db-changelog-1.41.xml	2025-12-03 01:58:35.41702	432	EXECUTED	9:37f6b5ea6bb65b3a4437a01391adc4a6	dropView viewName=view_risk_score_evolution; createView viewName=view_risk_score_evolution	Fix view_risk_score_evolution to return data with correct rebel rate calculation\n        \n        Root Cause: The v1.38 fix used incorrect logic comparing vote type to party name:\n        `vd.vote != vd.party` (e.g., 'Ja' != 'S') which is always t...	\N	5.0.1	\N	\N	4727109315
verify-risk-score-evolution-1.41-002	intelligence-operative	db-changelog-1.41.xml	2025-12-03 01:58:35.42298	433	EXECUTED	9:aebf12c8545cfa083ec15d44c1563fb3	sql	Post-flight verification for view_risk_score_evolution\n        \n        Checks:\n        1. View exists and can be queried\n        2. View returns data when vote_data and person_data are populated\n        3. Reports row count for monitoring	\N	5.0.1	\N	\N	4727109315
fix-ministry-productivity-matrix-matview-1.42-001	intelligence-operative	db-changelog-1.42.xml	2025-12-03 01:58:35.429281	434	EXECUTED	9:d412e3784f5066b454b4444785c9b190	dropView viewName=view_ministry_productivity_matrix; createView viewName=view_ministry_productivity_matrix	Fix view_ministry_productivity_matrix by removing materialized view dependency\n        \n        Root Cause: The view uses LEFT JOIN on view_riksdagen_politician_document\n        (materialized view). Even with LEFT JOIN, PostgreSQL cannot execute t...	\N	5.0.1	\N	\N	4727109315
fix-ministry-effectiveness-trends-matview-1.42-002	intelligence-operative	db-changelog-1.42.xml	2025-12-03 01:58:35.437025	435	EXECUTED	9:c312343fc7c7e60a7228cf621f2ef36c	dropView viewName=view_ministry_effectiveness_trends; createView viewName=view_ministry_effectiveness_trends	Fix view_ministry_effectiveness_trends by removing materialized view dependency\n        \n        Root Cause: The view uses LEFT JOIN on view_riksdagen_politician_document\n        (materialized view). Even with LEFT JOIN, PostgreSQL cannot execute ...	\N	5.0.1	\N	\N	4727109315
fix-ministry-risk-evolution-matview-1.42-003	intelligence-operative	db-changelog-1.42.xml	2025-12-03 01:58:35.443167	436	EXECUTED	9:cbb86defc0af14dbb2c2613c7a4eecb3	dropView viewName=view_ministry_risk_evolution; createView viewName=view_ministry_risk_evolution	Fix view_ministry_risk_evolution by removing materialized view dependency\n        \n        Root Cause: The view uses LEFT JOIN on view_riksdagen_politician_document\n        (materialized view). Even with LEFT JOIN, PostgreSQL cannot execute the\n  ...	\N	5.0.1	\N	\N	4727109315
verify-ministry-views-1.42-004	intelligence-operative	db-changelog-1.42.xml	2025-12-03 01:58:35.449246	437	EXECUTED	9:5364a65df2b784a3c738ef622c551a7a	sql	Post-flight verification for all three ministry views\n        \n        Checks:\n        1. Views exist and can be queried (no materialized view error)\n        2. Views return data when ministry assignments exist\n        3. Reports row counts for mo...	\N	5.0.1	\N	\N	4727109315
fix-risk-score-evolution-matview-1.42-005	intelligence-operative	db-changelog-1.42.xml	2025-12-03 01:58:35.459263	438	EXECUTED	9:17f7c63ce28d9c98df086fd35afa7991	dropView viewName=view_risk_score_evolution; createView viewName=view_risk_score_evolution	Fix view_risk_score_evolution by removing materialized view dependency\n        \n        Root Cause: The view was re-introduced in db-changelog-1.41.xml (for rebel rate fix)\n        but still referenced view_riksdagen_politician_document (materiali...	\N	5.0.1	\N	\N	4727109315
document-ministry-views-fix-1.42-006	intelligence-operative	db-changelog-1.42.xml	2025-12-03 01:58:35.461162	439	EXECUTED	9:0f5bf4edfd8c9b20d4ace561ff91751c	sql	Documentation for v1.42 views fix\n        \n        Summary:\n        - Root cause: Dependency on unpopulated materialized view view_riksdagen_politician_document\n        - Solution: Replace materialized view with direct base table queries in all vi...	\N	5.0.1	\N	\N	4727109315
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- PostgreSQL database dump complete
--

\unrestrict cdfD8dHpF44kWCkaICLMKVzgbOrdnHNQcjWMRMoaAEpRIkvsfgsB7mmjsQ43T1a


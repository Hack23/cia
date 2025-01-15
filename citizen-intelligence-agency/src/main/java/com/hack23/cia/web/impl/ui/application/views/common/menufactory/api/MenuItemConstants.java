package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DataSummaryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.GovernmentBodyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface MenuItemConstants.
 */
public interface MenuItemConstants {

        /** The Constant PARTY_WON_DAILY_SUMMARY_CHART. */
        String PARTY_WON_DAILY_SUMMARY_CHART = "Party Won Daily Summary Chart";

        /** The Constant ROLE_CHART_PARTY_LEADERS. */
        String ROLE_CHART_PARTY_LEADERS = "Role chart, party leaders";

        /** The Constant VOTE_HISTORY. */
        String VOTE_HISTORY = "Vote history";

        /** The Constant ROLE_CHART_PARTY_LEADERS_DESCRIPTION. */
        String ROLE_CHART_PARTY_LEADERS_DESCRIPTION = "Gantt chart all party leaders";

        /** The Constant PARTY_WON_DAILY_SUMMARY_CHART_DESCRIPTION. */
        String PARTY_WON_DAILY_SUMMARY_CHART_DESCRIPTION = "Chart for Party over won,absent and party rebel votes";

        /** The Constant PARTY_AGAINST_COALATIONS_SUMMARY_DESCRIPTION. */
        String PARTY_AGAINST_COALATIONS_SUMMARY_DESCRIPTION = "Coalations with groups of diffrent parties aginst committe proposals ballots";

        /** The Constant PARTY_SUPPORT_SUMMARY_DESCRIPTION. */
        String PARTY_SUPPORT_SUMMARY_DESCRIPTION = "Trend of agreements with other parties during ballots";

        // Label constants
        String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT = "All parties total days served in parliament";
        String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT = "Current parties in committees";
        String CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT = "Current parties in government";
        String CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT = "Current parties in parliament";
        String PARTY_BY_TOTAL_MEMBERS_BASED_ON_ROLES_IN_DEPARTMENTS_COMMITTEES_AND_PARLIAMENT = "Party by total members across EU/gov/committees/parliament";
        String TOTAL_MEMBERS = "Total members";

        // Political analyst perspective descriptions (~50 chars)
        String DESC_ALL_PARTIES_ROLES = "All parties: influence across key institutions.";
        String DESC_GOVERNMENT_HEADCOUNT = "Governing parties: evaluating institutional strength.";
        String DESC_COMMITTEES_HEADCOUNT = "Committees: parties' agenda-setting influence.";
        String DESC_PARLIAMENT_HEADCOUNT = "Parliament: mapping party legislative leverage.";
        String DESC_DAYS_SERVED_PARLIAMENT = "All parties: experience shaping policy timelines.";
        String DESC_PAGE_VISIT_HISTORY = "Visit history: tracking public engagement patterns.";

        // Page mode commands
        PageModeMenuCommand COMMAND_CHARTS_ALL_PARTIES = new PageModeMenuCommand(
                        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALLPARTIES.toString());
        PageModeMenuCommand COMMAND_CHARTS_CURRENT_COMMITTEES = new PageModeMenuCommand(
                        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,
                        ChartIndicators.CURRENTCOMMITTEES.toString());
        PageModeMenuCommand COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES = new PageModeMenuCommand(
                        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,
                        ChartIndicators.CURRENTGOVERMENTPARTIES.toString());
        PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES = new PageModeMenuCommand(
                        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTIES.toString());

        /**
         * Commands for different page modes within the Government Body Ranking view.
         */
        PageModeMenuCommand GOVERNMENT_BODY_COMMAN_OVERVIEW = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        PageMode.OVERVIEW);
        PageModeMenuCommand GOVERNMENT_BODY_COMMAND_DATAGRID = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        PageMode.DATAGRID);
        PageModeMenuCommand GOVERNMENT_BODY_COMMAND_EXPENDITURE = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        GovernmentBodyPageMode.EXPENDITURE.toString());
        PageModeMenuCommand GOVERNMENT_BODY_COMMAND_HEADCOUNT = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        GovernmentBodyPageMode.HEADCOUNT.toString());
        PageModeMenuCommand GOVERNMENT_BODY_COMMAND_INCOME = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        GovernmentBodyPageMode.INCOME.toString());
        PageModeMenuCommand GOVERNMENT_BODY_MIN_COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        PageMode.PAGEVISITHISTORY);

        /** The Constant COMMAND_GOVERNMENT_BODIES_EXPENDITURE. */
        PageModeMenuCommand GOVERNMENT_BODY_MIN_COMMAND_GOVERNMENT_BODIES_EXPENDITURE = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME,
                        MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString());

        /** The Constant COMMAND_GOVERNMENT_BODIES_HEADCOUNT. */
        PageModeMenuCommand GOVERNMENT_BODY_MIN_COMMAND_GOVERNMENT_BODIES_HEADCOUNT = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME,
                        MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString());

        /** The Constant COMMAND_GOVERNMENT_BODIES_INCOME. */
        PageModeMenuCommand GOVERNMENT_BODY__COMMAND_GOVERNMENT_BODIES_INCOME = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME,
                        MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString());

        String MINISTRY_DOCUMENT_ACTIVITY_TEXT = "Document Activity";

        String MINISTRY_OVERVIEW_TEXT = "Overview";
        String MINISTRY_PAGE_VISIT_HISTORY_TEXT = "Page Visit History";
        String MINISTRY_PAGE_VISIT_HISTORY_DESCRIPTION = "View history of page visit for this page.";

        // UserHomeMenuItemFactoryImpl constants
        String USER_HOME = "User Home";
        String USER_PROFILE = "User Profile";
        String USER_SETTINGS = "User Settings";
        String USER_LOGOUT = "User Logout";
        String USER_HOME_DESCRIPTION = "Navigate to user home page";
        String USER_PROFILE_DESCRIPTION = "View and edit user profile";
        String USER_SETTINGS_DESCRIPTION = "Adjust user settings";
        String USER_LOGOUT_DESCRIPTION = "Logout from the application";

        /** The Constant DASHBOARD. */
        String DASHBOARD = "Dashboard";

        /** The Constant ADMIN_TEXT. */
        String ADMIN_TEXT = "Admin";

        /** The Constant AGENCY. */
        String AGENCY = "Agency";

        /** The Constant AGENT_OPERATIONS_TEXT. */
        String AGENT_OPERATIONS_TEXT = "Agent operations";

        /** The Constant APPLICATION. */
        String APPLICATION = "Application";

        /** The Constant APPLICATION_CONFIGURATION. */
        String APPLICATION_CONFIGURATION = "System settings";

        /** The Constant APPLICATION_EVENT. */
        String APPLICATION_EVENT = "Application Event";

        /** The Constant APPLICATION_EVENT_CHARTS. */
        String APPLICATION_EVENT_CHARTS = "Application Event charts";

        /** The Constant APPLICATION_SESSION. */
        String APPLICATION_SESSION = "Application Session";

        /** The Constant APPLICATION_SESSION_CHARTS. */
        String APPLICATION_SESSION_CHARTS = "Active Daily Users";

        /** The Constant COMMAND_AGENCY. */
        PageModeMenuCommand COMMAND_AGENCY = new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, "");

        /** The Constant COMMAND_AGENT_OPERATION. */
        PageModeMenuCommand COMMAND_AGENT_OPERATION = new PageModeMenuCommand(
                        AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, "");

        /** The Constant COMMAND_APPLICATION_CONFIGURATION. */
        PageModeMenuCommand COMMAND_APPLICATION_CONFIGURATION = new PageModeMenuCommand(
                        AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, "");

        /** The Constant COMMAND_APPLICATION_EVENTS. */
        PageModeMenuCommand COMMAND_APPLICATION_EVENTS = new PageModeMenuCommand(
                        AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "");

        /** The Constant COMMAND_APPLICATION_EVENTS_CHARTS. */
        PageModeMenuCommand COMMAND_APPLICATION_EVENTS_CHARTS = new PageModeMenuCommand(
                        AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS);

        /** The Constant COMMAND_APPLICATION_SESSION. */
        PageModeMenuCommand COMMAND_APPLICATION_SESSION = new PageModeMenuCommand(
                        AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, "");

        /** The Constant COMMAND_APPLICATION_SESSION_CHARTS. */
        PageModeMenuCommand COMMAND_APPLICATION_SESSION_CHARTS = new PageModeMenuCommand(
                        AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, PageMode.CHARTS);

        /** The Constant COMMAND_AUTHOR_DATASUMMARY. */
        PageModeMenuCommand COMMAND_AUTHOR_DATASUMMARY = new PageModeMenuCommand(
                        AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, DataSummaryPageMode.AUTHORS.toString());

        /** The Constant COMMAND_COUNTRY. */
        PageModeMenuCommand COMMAND_COUNTRY = new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, "");

        /** The Constant COMMAND_DATASUMMARY. */
        PageModeMenuCommand COMMAND_DATASUMMARY = new PageModeMenuCommand(
                        AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, "");

        /** The Constant COMMAND_EMAIL. */
        PageModeMenuCommand COMMAND_EMAIL = new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME,
                        "");

        /** The Constant COMMAND_LANGUAGE. */
        PageModeMenuCommand COMMAND_LANGUAGE = new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME,
                        "");

        /** The Constant COMMAND_LOGIN. */
        PageModeMenuCommand COMMAND_LOGIN = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.LOGIN.toString());

        /** The Constant COMMAND_LOGOUT. */
        PageModeMenuCommand COMMAND_LOGOUT = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.LOGOUT.toString());

        /** The Constant COMMAND_MAINVIEW_OVERVIEW. */
        PageModeMenuCommand COMMAND_MAINVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        PageMode.OVERVIEW);

        /** The Constant COMMAND_DASHBOARDVIEW_OVERVIEW. */
        PageModeMenuCommand COMMAND_DASHBOARDVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.DASHBOARD_VIEW_NAME,
                        PageMode.OVERVIEW);

        /** The Constant COMMAND_MAINVIEW_PAGEVISITHISTORY. */
        PageModeMenuCommand COMMAND_MAINVIEW_PAGEVISITHISTORY = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        PageMode.PAGEVISITHISTORY);

        /** The Constant COMMAND_MONITORING. */
        PageModeMenuCommand COMMAND_MONITORING = new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME,
                        "");

        /** The Constant COMMAND_PORTAL. */
        PageModeMenuCommand COMMAND_PORTAL = new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, "");

        /** The Constant COMMAND_REGISTER. */
        PageModeMenuCommand COMMAND_REGISTER = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.REGISTER.toString());

        /** The Constant COMMAND_USERACCOUNT. */
        PageModeMenuCommand COMMAND_USERACCOUNT = new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME,
                        "");

        /** The Constant COMMAND_USERHOME. */
        PageModeMenuCommand COMMAND_USERHOME = new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, "");

        /** The Constant CONFIGURATION. */
        String CONFIGURATION = "Configuration";

        /** The Constant COUNTRY. */
        String COUNTRY = "Country";

        /** The Constant DATA_AUTHOR_SUMMARY. */
        String DATA_AUTHOR_SUMMARY = "Data author summary";

        /** The Constant DATA_SUMMARY_TEXT. */
        String DATA_SUMMARY_TEXT = "Data Summary";

        /** The Constant DISPLAY_SIZE_LG_DEVICE. */
        int DISPLAY_SIZE_LG_DEVICE = 4;

        /** The Constant DISPLAY_SIZE_MD_DEVICE. */
        int DISPLAY_SIZE_MD_DEVICE = 4;

        /** The Constant DISPLAY_SIZE_XS_DEVICE. */
        int DISPLAY_SIZE_XS_DEVICE = 12;

        /** The Constant DISPLAYS_SIZE_XM_DEVICE. */
        int DISPLAYS_SIZE_XM_DEVICE = 6;

        /** The Constant EMAIL. */
        String EMAIL = "Email";

        /** The Constant HEADER_STYLE_NAME. */
        String HEADER_STYLE_NAME = "Header";

        /** The Constant LANGUAGE. */
        String LANGUAGE = "Language";

        /** The Constant LINK_STYLE_NAME. */
        String LINK_STYLE_NAME = "link";

        /** The Constant LOGIN. */
        String LOGIN = "Login";

        /** The Constant LOGOUT. */
        String LOGOUT = "Logout";

        /** The Constant MAIN. */
        String MAIN = "Main";

        /** The Constant MANAGEMENT. */
        String MANAGEMENT = "Management";

        /** The Constant MENU_BAR_WIDTH. */
        String MENU_BAR_WIDTH = "80%";

        /** The Constant PAGE_VISIT_HISTORY_TEXT. */
        String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

        /** The Constant PORTAL. */
        String PORTAL = "Portal";

        /** The Constant REGISTER. */
        String REGISTER = "Register";

        /** The Constant ROLE_ADMIN. */
        String ROLE_ADMIN = "ROLE_ADMIN";

        /** The Constant ROLE_USER. */
        String ROLE_USER = "ROLE_USER";

        /** The Constant START_TEXT. */
        String START_TEXT = "Start";

        /** The Constant SYSTEM_PERFORMANCE. */
        String SYSTEM_PERFORMANCE = "System Performance";

        /** The Constant USER_ACTIVITY. */
        String USER_ACTIVITY = "User Activity";

        /** The Constant USERACCOUNT. */
        String USERACCOUNT = "Useraccount";

        /** The Constant USERHOME. */
        String USERHOME = "Userhome";

        /** The Constant ALL_COMMITTEES_TOTAL_MEMBERS. */
        String ALL_COMMITTEES_TOTAL_MEMBERS = "All committees, total members";

        /** The Constant CHART_BY_TOPIC_TEXT. */
        String CHART_BY_TOPIC_TEXT = "Chart by topic";

        /** The Constant COMMAND_ALL_COMMITTEES_BY_HEADCOUNT. */
        PageModeMenuCommand COMMAND_ALL_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(
                        UserViews.COMMITTEE_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.ALLCOMMITTEESBYHEADCOUNT.toString());

        /** The Constant COMMAND_COMMITTEES_BY_PARTY. */
        PageModeMenuCommand COMMAND_COMMITTEES_BY_PARTY = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.COMMITTEESBYPARTY.toString());

        /** The Constant COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT. */
        PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(
                        UserViews.COMMITTEE_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.CURRENTCOMMITTEESBYHEADCOUNT.toString());

        /** The Constant COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED. */
        PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED = new PageModeMenuCommand(
                        UserViews.COMMITTEE_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.CURRENTCOMMITTEESBYPARTYDAYSSERVED.toString());

        /** The Constant COMMAND_DATAGRID. */
        PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
                        PageMode.DATAGRID);

        /** The Constant COMMAND_OVERVIEW. */
        PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
                        PageMode.OVERVIEW);

        /** The Constant COMMAND_PAGEVISIT_HISTORY. */
        PageModeMenuCommand COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
                        PageMode.PAGEVISITHISTORY);

        /** The Constant CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS. */
        String CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS = "Current and past member, polticial days";

        /** The Constant CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT. */
        String CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT = "Current committees, current members";

        /** The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS. */
        String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS = "Current parties in committees";

        /**
         * The Constant
         * CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES.
         */
        String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES = "Current parties,days served";

        /** The Constant OVERVIEW_TEXT. */
        String OVERVIEW_TEXT = "Overview";

        /** The Constant PAGE_VISIT_HISTORY_DESCRIPTION. */
        String PAGE_VISIT_HISTORY_DESCRIPTION = "View history of page visit for this page.";

        /** The Constant POLITICAL_WORK_SUMMARY_TEXT. */
        String POLITICAL_WORK_SUMMARY_TEXT = "Political Work Summary";

        /** The Constant POLITICAL_WORK_SUMMARY_DESCRIPTION. */
        String POLITICAL_WORK_SUMMARY_DESCRIPTION = "Scoreboard over current member size, political days served and total assignments";

        /** The Constant CURRENT_COMMITTEES_CURRENT_MEMBERS_DESCRIPTION. */
        String CURRENT_COMMITTEES_CURRENT_MEMBERS_DESCRIPTION = "Chart over current committees and member size";

        /**
         * The Constant
         * CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS_DESCRIPTION.
         */
        String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS_DESCRIPTION = "Chart over current parties active in committees and member size";

        /**
         * The Constant
         * CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES_DESCRIPTION.
         */
        String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES_DESCRIPTION = "Chart over current parties active in committees days served";

        /** The Constant ALL_COMMITTEES_TOTAL_MEMBERS_DESCRIPTION. */
        String ALL_COMMITTEES_TOTAL_MEMBERS_DESCRIPTION = "Chart over all committees and member size";

        /** The Constant DOCUMENT_ACTIVITY_TEXT. */
        String DOCUMENT_ACTIVITY_TEXT = "Document Activity";

        /** The Constant DOCUMENT_ACTIVITY_DESCRIPTION. */
        String DOCUMENT_ACTIVITY_DESCRIPTION = "View document activity.";

        /** The Constant SECURITY_SETTING_TEXT. */
        String SECURITY_SETTING_TEXT = "Security Settings";

        /** The Constant SECURITY_SETTINGS_DESCRIPTION. */
        String SECURITY_SETTINGS_DESCRIPTION = "View and update security settings.";

        /** The Constant USER_VISITS. */
        String USER_VISITS = "User Visits";

        /** The Constant USER_VISITS_DESCRIPTION. */
        String USER_VISITS_DESCRIPTION = "View user visit history.";

        /** The Constant USER_EVENTS. */
        String USER_EVENTS = "User Events";

        /** The Constant USER_EVENTS_DESCRIPTION = "View user event history."; */
        String USER_EVENTS_DESCRIPTION = "View user event history.";

        /** The Constant ROLES_TEXT. */
        String ROLES_TEXT = "Roles";

        /** The Constant CURRENT_MEMBERS_TEXT. */
        String CURRENT_MEMBERS_TEXT = "Current Members";

        /** The Constant MEMBER_HISTORY_TEXT. */
        String MEMBER_HISTORY_TEXT = "Member History";

        /** The Constant ROLE_GHANT_TEXT. */
        String ROLE_GHANT_TEXT = "Role Ghant";

        /** The Constant DOCUMENTS_TEXT. */
        String DOCUMENTS_TEXT = "Documents";

        /** The Constant DOCUMENT_HISTORY_TEXT. */
        String DOCUMENT_HISTORY_TEXT = "Document History";

        /** The Constant BALLOTS_TEXT. */
        String BALLOTS_TEXT = "Ballots";

        /** The Constant BALLOT_DECISION_SUMMARY_TEXT. */
        String BALLOT_DECISION_SUMMARY_TEXT = "Ballot Decision Summary";

        /** The Constant DECISION_SUMMARY_TEXT. */
        String DECISION_SUMMARY_TEXT = "Decision Summary";

        /** The Constant DECISION_TYPE_DAILY_SUMMARY_TEXT. */
        String DECISION_TYPE_DAILY_SUMMARY_TEXT = "Decision Type Daily Summary";

        /** The Constant CURRENT_MEMBERS_DESCRIPTION. */
        String CURRENT_MEMBERS_DESCRIPTION = "Current members description";

        /** The Constant MEMBER_HISTORY_DESCRIPTION. */
        String MEMBER_HISTORY_DESCRIPTION = "Member history description";

        /** The Constant ROLE_GHANT_DESCRIPTION. */
        String ROLE_GHANT_DESCRIPTION = "Role ghant description";

        /** The Constant DOCUMENT_HISTORY_DESCRIPTION. */
        String DOCUMENT_HISTORY_DESCRIPTION = "Document history description";

        /** The Constant BALLOT_DECISION_SUMMARY_DESCRIPTION. */
        String BALLOT_DECISION_SUMMARY_DESCRIPTION = "Ballot decision summary description";

        /** The Constant DECISION_SUMMARY_DESCRIPTION. */
        String DECISION_SUMMARY_DESCRIPTION = "Decision summary description";

        /** The Constant DECISION_TYPE_DAILY_SUMMARY_DESCRIPTION. */
        String DECISION_TYPE_DAILY_SUMMARY_DESCRIPTION = "Decision type daily summary description";

        /** The Constant DECISION_FLOW_DESCRIPTION. */
        String DECISION_FLOW_DESCRIPTION = "Decision flow description";

        /** The Constant COUNTRY_RANKING_TEXT. */
        String COUNTRY_RANKING_TEXT = "Country Ranking";

        /** The Constant COUNTRY_OVERVIEW_TEXT. */
        String COUNTRY_OVERVIEW_TEXT = "Country Overview";

        /** The Constant COUNTRY_COMMAND_OVERVIEW. */
        PageModeMenuCommand COUNTRY_COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME,
                        PageMode.OVERVIEW);

        /** The Constant COUNTRY_INDICATORS_SWEDEN. */
        String COUNTRY_INDICATORS_SWEDEN = "Country Indicators Sweden";

        /** The Constant COUNTRY_PAGE_VISIT_HISTORY_TEXT. */
        String COUNTRY_PAGE_VISIT_HISTORY_TEXT = "Country Page Visit History";

        /** The Constant COUNTRY_COMMAND_PAGEVISITHISTORY. */
        PageModeMenuCommand COUNTRY_COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(
                        UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

        /** The Constant BY_TOPIC. */
        String BY_TOPIC = "By Topic";

        /** The Constant DATA_POINTS_FOR_YEAR_ABOVE. */
        int DATA_POINTS_FOR_YEAR_ABOVE = 2000;

        /** The Constant MINIMUM_NUMBER_DATA_POINTS. */
        int MINIMUM_NUMBER_DATA_POINTS = 10;

        /** The Constant DOCUMENT. */
        String DOCUMENT = "Document";

        /** The Constant DOCUMENT_OVERVIEW_TEXT. */
        String DOCUMENT_OVERVIEW_TEXT = "Document Overview";

        /** The Constant PERSON_REFERENCES. */
        String PERSON_REFERENCES = "Person References";

        /** The Constant DOCUMENT_DETAILS. */
        String DOCUMENT_DETAILS = "Document Details";

        /** The Constant DOCUMENT_DATA. */
        String DOCUMENT_DATA = "Document Data";

        /** The Constant DOCUMENT_REFERENCES. */
        String DOCUMENT_REFERENCES = "Document References";

        /** The Constant DOCUMENT_DECISION. */
        String DOCUMENT_DECISION = "Document Decision";

        /** The Constant DOCUMENT_ATTACHEMENTS. */
        String DOCUMENT_ATTACHEMENTS = "Document Attachments";

        /** The Constant DOCUMENT_PAGE_VISIT_HISTORY_TEXT. */
        String DOCUMENT_PAGE_VISIT_HISTORY_TEXT = "Document Page Visit History";

        /** The Constant LIST_ALL. */
        String LIST_ALL = "List All";

        /** The Constant DOCUMENT_ACTIVITIES_AND_UPDATES. */
        String DOCUMENT_ACTIVITIES_AND_UPDATES = "Document activities and updates";

        /** The Constant REFERENCES_TO_INDIVIDUALS_IN_THE_DOCUMENT. */
        String REFERENCES_TO_INDIVIDUALS_IN_THE_DOCUMENT = "References to individuals in the document";

        /** The Constant DETAILED_INFORMATION_ABOUT_THE_DOCUMENT. */
        String DETAILED_INFORMATION_ABOUT_THE_DOCUMENT = "Detailed information about the document";

        /** The Constant COMPLETE_DOCUMENT_TEXT_AND_DATA. */
        String COMPLETE_DOCUMENT_TEXT_AND_DATA = "Complete document text and data";

        /** The Constant REFERENCES_CITED_IN_THE_DOCUMENT. */
        String REFERENCES_CITED_IN_THE_DOCUMENT = "References cited in the document";

        /** The Constant DECISIONS_AND_OUTCOMES_RELATED_TO_THE_DOCUMENT. */
        String DECISIONS_AND_OUTCOMES_RELATED_TO_THE_DOCUMENT = "Decisions and outcomes related to the document";

        /** The Constant ATTACHMENTS_AND_SUPPLEMENTARY_FILES. */
        String ATTACHMENTS_AND_SUPPLEMENTARY_FILES = "Attachments and supplementary files";

        /** The Constant GOVERNMENT_BODY_OVERVIEW_TEXT. */
        String GOVERNMENT_BODY_OVERVIEW_TEXT = "Government Body Overview";

        /** The Constant HEADCOUNT_CHART. */
        String HEADCOUNT_CHART = "Headcount Chart";

        /** The Constant INCOME. */
        String INCOME = "Income";

        /** The Constant EXPENDITURE. */
        String EXPENDITURE = "Expenditure";

        /** The Constant GOVERNMENT_BODY_PAGE_VISIT_HISTORY_TEXT. */
        String GOVERNMENT_BODY_PAGE_VISIT_HISTORY_TEXT = "Government Body Page Visit History";

        /** The Constant HEADCOUNT_DESCRIPTION. */
        String HEADCOUNT_DESCRIPTION = "Headcount description";

        /** The Constant INCOME_DESCRIPTION. */
        String INCOME_DESCRIPTION = "Income description";

        /** The Constant EXPENDITURE_DESCRIPTION. */
        String EXPENDITURE_DESCRIPTION = "Expenditure description";

        /** The Constant GOVERNMENT_BODY_PAGE_VISIT_HISTORY_DESCRIPTION. */
        String GOVERNMENT_BODY_PAGE_VISIT_HISTORY_DESCRIPTION = "Government body page visit history description";

        /** The Constant GOVERNMENT_BODIES_HEADCOUNT. */
        String GOVERNMENT_BODIES_HEADCOUNT = "Government Bodies Headcount";

        /** The Constant GOVERNMENT_BODIES_INCOME. */
        String GOVERNMENT_BODIES_INCOME = "Government Bodies Income";

        /** The Constant GOVERNMENT_BODIES_EXPENDITURE. */
        String GOVERNMENT_BODIES_EXPENDITURE = "Government Bodies Expenditure";

        /** The Constant GOVERNMENT_BODIES_HEADCOUNT_DESCRIPTION. */
        String GOVERNMENT_BODIES_HEADCOUNT_DESCRIPTION = "Government bodies headcount description";

        /** The Constant GOVERNMENT_BODIES_INCOME_DESCRIPTION. */
        String GOVERNMENT_BODIES_INCOME_DESCRIPTION = "Government bodies income description";

        /** The Constant GOVERNMENT_BODIES_EXPENDITURE_DESCRIPTION. */
        String GOVERNMENT_BODIES_EXPENDITURE_DESCRIPTION = "Government bodies expenditure description";

        /** The Constant USER_HOME_OVERVIEW_TEXT. */
        String USER_HOME_OVERVIEW_TEXT = "User Home Overview";

        /** The Constant SWEDEN_DASHBOARD. */
        String SWEDEN_DASHBOARD = "Sweden Dashboard";

        /** The Constant COMMAND_COMMITTEE_RANKING_DATAGRID. */
        PageModeMenuCommand COMMAND_COMMITTEE_RANKING_DATAGRID = new PageModeMenuCommand(
                        UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID);

        /** The Constant COMMAND_COMMITTEE_RANKING_OVERVIEW. */
        PageModeMenuCommand COMMAND_COMMITTEE_RANKING_OVERVIEW = new PageModeMenuCommand(
                        UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW);

        /** The Constant COMMAND_COUNTRY_RANKING_OVERVIEW. */
        PageModeMenuCommand COMMAND_COUNTRY_RANKING_OVERVIEW = new PageModeMenuCommand(
                        UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

        /** The Constant COMMAND_DOCUMENTS. */
        PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME,
                        PageMode.OVERVIEW);

        /** The Constant COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID. */
        PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.DATAGRID);

        /** The Constant COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW. */
        PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

        /** The Constant COMMAND_MINISTRY_RANKING_DATAGRID. */
        PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);

        /** The Constant COMMAND_MINISTRY_RANKING_OVERVIEW. */
        PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

        /** The Constant COMMAND_PARLIAMENT_RANKING_OVERVIEW. */
        PageModeMenuCommand COMMAND_PARLIAMENT_RANKING_OVERVIEW = new PageModeMenuCommand(
                        UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);

        /** The Constant COMMAND_PARTY_RANKING_DATAGRID. */
        PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
                        PageMode.DATAGRID);

        /** The Constant COMMAND_PARTY_RANKING_OVERVIEW. */
        PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
                        PageMode.OVERVIEW);

        /** The Constant COMMAND_POLITICIAN_RANKING_DATAGRID. */
        PageModeMenuCommand COMMAND_POLITICIAN_RANKING_DATAGRID = new PageModeMenuCommand(
                        UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID);

        /** The Constant COMMAND_POLITICIAN_RANKING_OVERVIEW. */
        PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(
                        UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

        /** The Constant COMMAND_SEARCH_DOCUMENT. */
        PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME, "");

        /** The Constant COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD. */
        PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());

        /** The Constant COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD. */
        PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
                        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,
                        ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString());

        /** The Constant COMMITTEE_RANKING_TEXT. */
        String COMMITTEE_RANKING_TEXT = "Committee Ranking";

        /** The Constant DESC_LEADERS_SCOREBOARD. */
        String DESC_LEADERS_SCOREBOARD = "Leaders: comparing party leadership impact.";

        /** The Constant PART_LEADERS_SCOREBOARD. */
        String PART_LEADERS_SCOREBOARD = "Party leaders scoreboard";

        /** The Constant COMMITTEE_RANKING_LINK_TEXT. */
        String COMMITTEE_RANKING_LINK_TEXT = COMMITTEE_RANKING_TEXT;

        /** The Constant COMMITTEES_LINK_TEXT. */
        String COMMITTEES_LINK_TEXT = "Committees";

        /** The Constant COUNTRY_RANKING_LINK_TEXT. */
        String COUNTRY_RANKING_LINK_TEXT = "Country Ranking";

        /** The Constant DOCUMENTS. */
        String DOCUMENTS = "Documents";

        /** The Constant GOVERNMENT_BODIES. */
        String GOVERNMENT_BODIES = "Government bodies";

        /** The Constant GOVERNMENT_BODY_RANKING. */
        String GOVERNMENT_BODY_RANKING = "Government body Ranking";

        /** The Constant MINISTRIES_LINK_TEXT. */
        String MINISTRIES_LINK_TEXT = "Ministries";

        /** The Constant MINISTRY_RANKING. */
        String MINISTRY_RANKING = "Ministry Ranking";

        /** The Constant MINISTRY_RANKING_LINK_TEXT. */
        String MINISTRY_RANKING_LINK_TEXT = MINISTRY_RANKING;

        /** The Constant PARLIAMENT_RANKING_LINK_TEXT. */
        String PARLIAMENT_RANKING_LINK_TEXT = "Parliament Ranking";

        /** The Constant PARTIES_LINK_TEXT. */
        String PARTIES_LINK_TEXT = "Parties";

        /** The Constant PARTY_RANKING. */
        String PARTY_RANKING = "Party Ranking";

        /** The Constant PARTY_RANKING_LINK_TEXT. */
        String PARTY_RANKING_LINK_TEXT = PARTY_RANKING;

        /** The Constant POLITICIAN_RANKING. */
        String POLITICIAN_RANKING = "Politician Ranking";

        /** The Constant POLITICIAN_RANKING_LINK_TEXT. */
        String POLITICIAN_RANKING_LINK_TEXT = POLITICIAN_RANKING;

        /** The Constant POLITICIANS_LINK_TEXT. */
        String POLITICIANS_LINK_TEXT = "Politicians";

        /** The Constant RANKING_TEXT. */
        String RANKING_TEXT = "Ranking";

        /** The Constant SEARCH_DOCUMENTS. */
        String SEARCH_DOCUMENTS = "Search documents";

        /** The Constant COUNTRY_RANKING_DESCRIPTION. */
        String COUNTRY_RANKING_DESCRIPTION = "Sweden’s governance: key indicators.";

        /** The Constant MINISTRIES_LEADER_SCOREBOARD. */
        String MINISTRIES_LEADER_SCOREBOARD = "Government Ministers Scoreboard";

        /** The Constant MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION. */
        String MINISTRIES_LEADER_SCOREBOARD_DESCRIPTION = "Cabinet leaders: power and institutional impact.";

        /** The Constant MINISTRY_RANKING_DESCRIPTION. */
        String MINISTRY_RANKING_DESCRIPTION = "Ministry influence on policy.";

        /** The Constant MINISTRIES_DESCRIPTION. */
        String MINISTRIES_DESCRIPTION = "All ministries: roles and commitments.";

        /** The Constant GOVERNMENT_BODY_RANKING_DESCRIPTION. */
        String GOVERNMENT_BODY_RANKING_DESCRIPTION = "Government bodies: performance snapshots.";

        /** The Constant GOVERNMENT_BODIES_DESCRIPTION. */
        String GOVERNMENT_BODIES_DESCRIPTION = "All bodies: structure and influence.";

        /** The Constant PARLIAMENT_RANKING_DESCRIPTION. */
        String PARLIAMENT_RANKING_DESCRIPTION = "Parliament: legislative effectiveness.";

        /** The Constant COMMITTEE_RANKING_DESCRIPTION. */
        String COMMITTEE_RANKING_DESCRIPTION = "Committees: shaping policy debates.";

        /** The Constant COMMITTEES_DESCRIPTION. */
        String COMMITTEES_DESCRIPTION = "All committees: roles and responsibilities.";

        /** The Constant PARTY_RANKING_DESCRIPTION. */
        String PARTY_RANKING_DESCRIPTION = "Parties: influence on agenda.";

        /** The Constant PARTIES_DESCRIPTION. */
        String PARTIES_DESCRIPTION = "All parties: leadership and policy roles.";

        /** The Constant POLITICIAN_RANKING_DESCRIPTION. */
        String POLITICIAN_RANKING_DESCRIPTION = "Politicians: individual impact.";

        /** The Constant POLITICIANS_DESCRIPTION. */
        String POLITICIANS_DESCRIPTION = "All politicians: service and contributions.";

        /** The Constant SEARCH_DOCUMENTS_DESCRIPTION. */
        String SEARCH_DOCUMENTS_DESCRIPTION = "Search documents: legislative records.";

        /** The Constant DOCUMENTS_DESCRIPTION. */
        String DOCUMENTS_DESCRIPTION = "All documents: official records.";

        /** The Constant HEADCOUNT. */
        String HEADCOUNT = "Headcount";

        /** The Constant COMMAND_GOVERNMENT_BODIES_HEADCOUNT. */
        PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_HEADCOUNT = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.GOVERNMENTBODIESHEADCOUNT.toString());

        /** The Constant COMMAND_GOVERNMENT_BODIES_INCOME. */
        PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_INCOME = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.GOVERNMENTBODIESINCOME.toString());

        /** The Constant COMMAND_GOVERNMENT_BODIES_EXPENDITURE. */
        PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_EXPENDITURE = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.GOVERNMENTBODIESEXPENDITURE.toString());

        /** The Constant GOVERNMENT_OUTCOME. */
        String GOVERNMENT_OUTCOME = "Government Outcome";

        /** The Constant COMMAND_GOVERNMENT_OUTCOME. */
        PageModeMenuCommand COMMAND_GOVERNMENT_OUTCOME = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.GOVERNMENTOUTCOME.toString());

        /** The Constant GOVERNMENT_OUTCOME_DESCRIPTION. */
        String GOVERNMENT_OUTCOME_DESCRIPTION = "Government outcome description";

        /** The Constant GOVERNMENT_ROLES_CHART. */
        String GOVERNMENT_ROLES_CHART = "Government Roles Chart";

        /** The Constant COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT. */
        PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(
                        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.ALLGOVERNMENTROLEGANTT.toString());

        /** The Constant GOVERNMENT_ROLES_CHART_DESCRIPTION. */
        String GOVERNMENT_ROLES_CHART_DESCRIPTION = "Government roles chart description";

        /** The Constant CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT. */
        String CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT = "Current Ministries, Current Members";

        /** The Constant COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT. */
        PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESBYHEADCOUNT.toString());

        /** The Constant CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_CURRENT_ASSIGNMENTS. */
        String CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_CURRENT_ASSIGNMENTS = "Current Parties Active in Ministries, Current Assignments";

        /** The Constant COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT. */
        PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.CURRENTPARTIESBYHEADCOUNT.toString());

        /** The Constant ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES. */
        String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES = "All Parties, Total Days Served in Ministries";

        /** The Constant COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS. */
        PageModeMenuCommand COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYTOTALDAYS.toString());

        /** The Constant ALL_MINISTRIES_TOTAL_MEMBERS. */
        String ALL_MINISTRIES_TOTAL_MEMBERS = "All Ministries, Total Members";

        /** The Constant COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT. */
        PageModeMenuCommand COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
                        UserViews.MINISTRY_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString());

        /** The Constant COMMAND_PAGEVISITHISTORY. */
        PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
                        PageMode.PAGEVISITHISTORY);

        /** The Constant PARTY_WINNER. */
        String PARTY_WINNER = "Party Winner";

        /** The Constant COMMAND_CHARTS_PARTY_WINNER. */
        PageModeMenuCommand COMMAND_CHARTS_PARTY_WINNER = new PageModeMenuCommand(
                        UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString());

        /** The Constant PARTY_WINNER_DESCRIPTION. */
        String PARTY_WINNER_DESCRIPTION = "Party winner description";

        /** The Constant PARTY_GENDER. */
        String PARTY_GENDER = "Party Gender";

        /** The Constant COMMAND_CHARTS_PARTY_GENDER. */
        PageModeMenuCommand COMMAND_CHARTS_PARTY_GENDER = new PageModeMenuCommand(
                        UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.PARTYGENDER.toString());

        /** The Constant PARTY_GENDER_DESCRIPTION. */
        String PARTY_GENDER_DESCRIPTION = "Party gender description";

        /** The Constant PARTY_AGE. */
        String PARTY_AGE = "Party Age";

        /** The Constant COMMAND_CHARTS_PARTY_AGE. */
        PageModeMenuCommand COMMAND_CHARTS_PARTY_AGE = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.PARTYAGE.toString());

        /** The Constant PARTY_AGE_DESCRIPTION. */
        String PARTY_AGE_DESCRIPTION = "Party age description";

        /** The Constant RISK_SUMMARY. */
        String RISK_SUMMARY = "Risk Summary";

        /** The Constant COMMAND_RISK_SUMMARY. */
        PageModeMenuCommand COMMAND_RISK_SUMMARY = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.RISKSUMMARY.toString());

        /** The Constant RISK_SUMMARY_DESCRIPTION. */
        String RISK_SUMMARY_DESCRIPTION = "Risk summary description";

        /** The Constant RULE_VIOLATIONS. */
        String RULE_VIOLATIONS = "Rule Violations";

        /** The Constant COMMAND_RULE_VIOLATION. */
        PageModeMenuCommand COMMAND_RULE_VIOLATION = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.RULEVIOLATION.toString());

        /** The Constant RULE_VIOLATIONS_DESCRIPTION. */
        String RULE_VIOLATIONS_DESCRIPTION = "Rule violations description";

        /** The Constant DOCUMENT_ACTIVITY_BY_TYPE. */
        String DOCUMENT_ACTIVITY_BY_TYPE = "Document Activity by Type";

        /** The Constant COMMAND_DOCUMENT_ACTIVITY. */
        PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.DOCUMENTACTIVITY.toString());

        /** The Constant DECISION_ACTIVITY_BY_TYPE. */
        String DECISION_ACTIVITY_BY_TYPE = "Decision Activity by Type";

        /** The Constant COMMAND_DECISION_ACTIVITY. */
        PageModeMenuCommand COMMAND_DECISION_ACTIVITY = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.DECISIONACTIVITY.toString());

        /** The Constant DECISION_ACTIVITY_DESCRIPTION. */
        String DECISION_ACTIVITY_DESCRIPTION = "Decision activity description";

        /** The Constant DECISION_FLOW. */
        String DECISION_FLOW = "Decision Flow";

        /** The Constant COMMAND_CHARTS_DECISION_FLOW. */
        PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
                        UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                        PageMode.CHARTS, ChartIndicators.DECISIONFLOW.toString());

        /** The Constant PARLIAMENT_RANKING_TEXT. */
        String PARLIAMENT_RANKING_TEXT = "Parliament Ranking";

        /** The Constant SWEDISH_PARLIAMENT_INDICATORS. */
        String SWEDISH_PARLIAMENT_INDICATORS = "Swedish Parliament Indicators";

        /** The Constant CURRENT_LEADERS. */
        String CURRENT_LEADERS = "Current Leaders";

        /** The Constant CURRENT_LEADERS_DESCRIPTION. */
        String CURRENT_LEADERS_DESCRIPTION = "Current leaders description";

        /** The Constant LEADER_HISTORY. */
        String LEADER_HISTORY = "Leader History";

        /** The Constant LEADER_HISTORY_DESCRIPTION. */
        String LEADER_HISTORY_DESCRIPTION = "Leader history description";

        /** The Constant GOVERMENT_ROLES. */
        String GOVERMENT_ROLES = "Government Roles";

        /** The Constant GOVERMENT_ROLES_DESCRIPTION. */
        String GOVERMENT_ROLES_DESCRIPTION = "Government roles description";

        /** The Constant COMMITTEE_ROLES. */
        String COMMITTEE_ROLES = "Committee Roles";

        /** The Constant COMMITTEE_ROLES_DESCRIPTION. */
        String COMMITTEE_ROLES_DESCRIPTION = "Committee roles description";

}
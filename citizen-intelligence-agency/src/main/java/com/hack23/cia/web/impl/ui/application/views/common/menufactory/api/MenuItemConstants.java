package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DataSummaryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface MenuItemConstants.
 */
public interface MenuItemConstants {

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
    PageModeMenuCommand COMMAND_APPLICATION_EVENTS_CHARTS = new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS);

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
    PageModeMenuCommand COMMAND_ALL_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
            PageMode.CHARTS, ChartIndicators.ALLCOMMITTEESBYHEADCOUNT.toString());

    /** The Constant COMMAND_COMMITTEES_BY_PARTY. */
    PageModeMenuCommand COMMAND_COMMITTEES_BY_PARTY = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
            PageMode.CHARTS, ChartIndicators.COMMITTEESBYPARTY.toString());

    /** The Constant COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT. */
    PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
            PageMode.CHARTS, ChartIndicators.CURRENTCOMMITTEESBYHEADCOUNT.toString());

    /** The Constant COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED. */
    PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME,
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

    /** The Constant COMMITTEE_RANKING_TEXT. */
    String COMMITTEE_RANKING_TEXT = "Committee Ranking";

    /** The Constant CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS. */
    String CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS = "Current and past member, polticial days";

    /** The Constant CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT. */
    String CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT = "Current committees, current members";

    /** The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS. */
    String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS = "Current parties in committees";

    /** The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES. */
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

    /** The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS_DESCRIPTION. */
    String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS_DESCRIPTION = "Chart over current parties active in committees and member size";

    /** The Constant CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES_DESCRIPTION. */
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
    PageModeMenuCommand COUNTRY_COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COUNTRY_INDICATORS_SWEDEN. */
    String COUNTRY_INDICATORS_SWEDEN = "Country Indicators Sweden";

    /** The Constant COUNTRY_PAGE_VISIT_HISTORY_TEXT. */
    String COUNTRY_PAGE_VISIT_HISTORY_TEXT = "Country Page Visit History";

    /** The Constant COUNTRY_COMMAND_PAGEVISITHISTORY. */
    PageModeMenuCommand COUNTRY_COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

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

    /** The Constant DOCUMENTS. */
    String DOCUMENTS = "Documents";

    /** The Constant LIST_ALL. */
    String LIST_ALL = "List All";

    /** The Constant COMMAND_DOCUMENTS. */
    PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.LIST);

    /** The Constant SEARCH_DOCUMENTS. */
    String SEARCH_DOCUMENTS = "Search Documents";

    /** The Constant COMMAND_SEARCH_DOCUMENT. */
    PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.SEARCH);

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

    /** The Constant GOVERNMENT_BODY_RANKING. */
    String GOVERNMENT_BODY_RANKING = "Government Body Ranking";

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

    /** The Constant MINISTRY_RANKING. */
    String MINISTRY_RANKING = "Ministry Ranking";

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
    PageModeMenuCommand COMMAND_COMMITTEE_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_COMMITTEE_RANKING_OVERVIEW. */
    PageModeMenuCommand COMMAND_COMMITTEE_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_COUNTRY_RANKING_OVERVIEW. */
    PageModeMenuCommand COMMAND_COUNTRY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_DOCUMENTS. */
    PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_MINISTRY_RANKING_DATAGRID. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_MINISTRY_RANKING_OVERVIEW. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_PARLIAMENT_RANKING_OVERVIEW. */
    PageModeMenuCommand COMMAND_PARLIAMENT_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_PARTY_RANKING_DATAGRID. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_PARTY_RANKING_OVERVIEW. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_POLITICIAN_RANKING_DATAGRID. */
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_DATAGRID = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The Constant COMMAND_POLITICIAN_RANKING_OVERVIEW. */
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The Constant COMMAND_SEARCH_DOCUMENT. */
    PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME, "");

    /** The Constant COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
            PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());

    /** The Constant COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString());

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

}

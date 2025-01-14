package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DataSummaryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface MenuItemConstants {

    String DASHBOARD = "Dashboard";
    String ADMIN_TEXT = "Admin";
    String AGENCY = "Agency";
    String AGENT_OPERATIONS_TEXT = "Agent operations";
    String APPLICATION = "Application";
    String APPLICATION_CONFIGURATION = "System settings";
    String APPLICATION_EVENT = "Application Event";
    String APPLICATION_EVENT_CHARTS = "Application Event charts";
    String APPLICATION_SESSION = "Application Session";
    String APPLICATION_SESSION_CHARTS = "Active Daily Users";
    PageModeMenuCommand COMMAND_AGENCY = new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_AGENT_OPERATION = new PageModeMenuCommand(AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, "");
    PageModeMenuCommand COMMAND_APPLICATION_CONFIGURATION = new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_APPLICATION_EVENTS = new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_APPLICATION_EVENTS_CHARTS = new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS);
    PageModeMenuCommand COMMAND_APPLICATION_SESSION = new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_APPLICATION_SESSION_CHARTS = new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, PageMode.CHARTS);
    PageModeMenuCommand COMMAND_AUTHOR_DATASUMMARY = new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, DataSummaryPageMode.AUTHORS.toString());
    PageModeMenuCommand COMMAND_COUNTRY = new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_DATASUMMARY = new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_EMAIL = new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_LANGUAGE = new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_LOGIN = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString());
    PageModeMenuCommand COMMAND_LOGOUT = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGOUT.toString());
    PageModeMenuCommand COMMAND_MAINVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.OVERVIEW);
    PageModeMenuCommand COMMAND_DASHBOARDVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.DASHBOARD_VIEW_NAME, PageMode.OVERVIEW);
    PageModeMenuCommand COMMAND_MAINVIEW_PAGEVISITHISTORY = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.PAGEVISITHISTORY);
    PageModeMenuCommand COMMAND_MONITORING = new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_PORTAL = new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_REGISTER = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString());
    PageModeMenuCommand COMMAND_USERACCOUNT = new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, "");
    PageModeMenuCommand COMMAND_USERHOME = new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, "");
    String CONFIGURATION = "Configuration";
    String COUNTRY = "Country";
    String DATA_AUTHOR_SUMMARY = "Data author summary";
    String DATA_SUMMARY_TEXT = "Data Summary";
    int DISPLAY_SIZE_LG_DEVICE = 4;
    int DISPLAY_SIZE_MD_DEVICE = 4;
    int DISPLAY_SIZE_XS_DEVICE = 12;
    int DISPLAYS_SIZE_XM_DEVICE = 6;
    String EMAIL = "Email";
    String HEADER_STYLE_NAME = "Header";
    String LANGUAGE = "Language";
    String LINK_STYLE_NAME = "link";
    String LOGIN = "Login";
    String LOGOUT = "Logout";
    String MAIN = "Main";
    String MANAGEMENT = "Management";
    String MENU_BAR_WIDTH = "80%";
    String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";
    String PORTAL = "Portal";
    String REGISTER = "Register";
    String ROLE_ADMIN = "ROLE_ADMIN";
    String ROLE_USER = "ROLE_USER";
    String START_TEXT = "Start";
    String SYSTEM_PERFORMANCE = "System Performance";
    String USER_ACTIVITY = "User Activity";
    String USERACCOUNT = "Useraccount";
    String USERHOME = "Userhome";

    // CommitteeRankingMenuItemFactoryImpl constants
    String ALL_COMMITTEES_TOTAL_MEMBERS = "All committees, total members";
    String CHART_BY_TOPIC_TEXT = "Chart by topic";
    PageModeMenuCommand COMMAND_ALL_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALLCOMMITTEESBYHEADCOUNT.toString());
    PageModeMenuCommand COMMAND_COMMITTEES_BY_PARTY = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.COMMITTEESBYPARTY.toString());
    PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTCOMMITTEESBYHEADCOUNT.toString());
    PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTCOMMITTEESBYPARTYDAYSSERVED.toString());
    PageModeMenuCommand COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID);
    PageModeMenuCommand COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW);
    PageModeMenuCommand COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
    String COMMITTEE_RANKING_TEXT = "Committee Ranking";
    String CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS = "Current and past member, polticial days";
    String CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT = "Current committees, current members";
    String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS = "Current parties in committees";
    String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES = "Current parties,days served";
    String OVERVIEW_TEXT = "Overview";
    String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";
    String POLITICAL_WORK_SUMMARY_TEXT = "Political Work Summary";
    String POLITICAL_WORK_SUMMARY_DESCRIPTION = "Scoreboard over current member size, political days served and total assignments";
    String CURRENT_COMMITTEES_CURRENT_MEMBERS_DESCRIPTION = "Chart over current committees and member size";
    String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS_DESCRIPTION = "Chart over current parties active in committees and member size";
    String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES_DESCRIPTION = "Chart over current parties active in committees days served";
    String ALL_COMMITTEES_TOTAL_MEMBERS_DESCRIPTION = "Chart over all committees and member size";
    String PAGE_VISIT_HISTORY_DESCRIPTION = "View history of page visit for this page.";

    // CountryMenuItemFactoryImpl constants
    PageModeMenuCommand COUNTRY_COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);
    PageModeMenuCommand COUNTRY_COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
    String COUNTRY_INDICATORS_SWEDEN = "Country Indicators, Sweden";
    String COUNTRY_RANKING_TEXT = "Counry Ranking";
    int DATA_POINTS_FOR_YEAR_ABOVE = 2010;
    int MINIMUM_NUMBER_DATA_POINTS = 10;
    String COUNTRY_OVERVIEW_TEXT = "Overview";
    String COUNTRY_PAGE_VISIT_HISTORY_TEXT = "Page Visit History";
    String BY_TOPIC = "By Topic";

    // DocumentMenuItemFactoryImpl constants
    PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME, PageMode.OVERVIEW);
    PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME, "");
    String DOCUMENT = "Document";
    String DOCUMENT_ACTIVITY_TEXT = "Document Activity";
    String DOCUMENT_ATTACHEMENTS = "Document Attachements";
    String DOCUMENT_DATA = "Document data";
    String DOCUMENT_DECISION = "Document Decision";
    String DOCUMENT_DETAILS = "Document details";
    String DOCUMENT_REFERENCES = "Document References";
    String DOCUMENTS = "Documents";
    String DOCUMENT_OVERVIEW_TEXT = "Overview";
    String DOCUMENT_PAGE_VISIT_HISTORY_TEXT = "Page Visit History";
    String PERSON_REFERENCES = "Person references";
    String LIST_ALL = "List all";
    String SEARCH_DOCUMENTS = "Search Documents";
    String DOCUMENT_ACTIVITIES_AND_UPDATES = "Document activities and updates.";
    String REFERENCES_TO_INDIVIDUALS_IN_THE_DOCUMENT = "References to individuals in the document.";
    String DETAILED_INFORMATION_ABOUT_THE_DOCUMENT = "Detailed information about the document.";
    String COMPLETE_DOCUMENT_TEXT_AND_DATA = "Complete document text and data.";
    String REFERENCES_CITED_IN_THE_DOCUMENT = "References cited in the document.";
    String DECISIONS_AND_OUTCOMES_RELATED_TO_THE_DOCUMENT = "Decisions and outcomes related to the document.";
    String ATTACHMENTS_AND_SUPPLEMENTARY_FILES = "Attachments and supplementary files.";

    // GovernmentBodyMenuItemFactoryImpl constants
    String EXPENDITURE = "Expenditure";
    String GOVERNMENT_BODY_RANKING = "GovernmentBody Ranking";
    String HEADCOUNT_CHART = "Headcount chart";
    String INCOME = "Income";
    String GOVERNMENT_BODY_OVERVIEW_TEXT = "Overview";
    String GOVERNMENT_BODY_PAGE_VISIT_HISTORY_TEXT = "Page Visit History";
    String HEADCOUNT_DESCRIPTION = "Government body: headcount trends.";
    String INCOME_DESCRIPTION = "Government body: income analysis.";
    String EXPENDITURE_DESCRIPTION = "Government body: expenditure review.";
    String GOVERNMENT_BODY_PAGE_VISIT_HISTORY_DESCRIPTION = "Page visit history for this government body.";

    // MinistryMenuItemFactoryImpl constants
    String CURRENT_MEMBERS_TEXT = "Current Members";
    String DOCUMENT_ACTIVITY_TEXT = "Document Activity";
    String DOCUMENT_HISTORY_TEXT = "Document history";
    String DOCUMENTS_TEXT = "Documents";
    String GOVERNMENT_BODIES_EXPENDITURE = "Government bodies expenditure";
    String GOVERNMENT_BODIES_HEADCOUNT = "Government bodies headcount";
    String GOVERNMENT_BODIES_INCOME = "Government bodies income";
    String MEMBER_HISTORY_TEXT = "Member History";
    String MINISTRY_RANKING = "Ministry Ranking";
    String MINISTRY_OVERVIEW_TEXT = "Overview";
    String MINISTRY_PAGE_VISIT_HISTORY_TEXT = "Page Visit History";
    String ROLE_GHANT_TEXT = "RoleGhant";
    String ROLES_TEXT = "Roles";
    String CURRENT_MEMBERS_DESCRIPTION = "Members currently holding positions";
    String MEMBER_HISTORY_DESCRIPTION = "Current and past members";
    String ROLE_GHANT_DESCRIPTION = "Gantt chart for all the roles";
    String GOVERNMENT_BODIES_HEADCOUNT_DESCRIPTION = "All government bodies that are governed by ministry";
    String GOVERNMENT_BODIES_INCOME_DESCRIPTION = "All government bodies income";
    String GOVERNMENT_BODIES_EXPENDITURE_DESCRIPTION = "All government bodies expenditure";
    String DOCUMENT_ACTIVITY_DESCRIPTION = "Chart over document activity";
    String DOCUMENT_HISTORY_DESCRIPTION = "List of all document sorted by most recent";
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
}

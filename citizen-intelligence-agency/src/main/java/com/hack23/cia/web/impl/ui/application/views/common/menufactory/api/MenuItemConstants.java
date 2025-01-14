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

    /** The Constant USER_EVENTS_DESCRIPTION. */
    String USER_EVENTS_DESCRIPTION = "View user event history.";

}

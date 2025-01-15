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
public interface PageModeMenuCommandConstants extends MenuItemConstants {

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

       /** The Constant COUNTRY_COMMAND_OVERVIEW. */
       PageModeMenuCommand COUNTRY_COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME,
                     PageMode.OVERVIEW);

       /** The Constant COUNTRY_COMMAND_PAGEVISITHISTORY. */
       PageModeMenuCommand COUNTRY_COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(
                     UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

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

       /** The Constant COMMAND_GOVERNMENT_OUTCOME. */
       PageModeMenuCommand COMMAND_GOVERNMENT_OUTCOME = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.GOVERNMENTOUTCOME.toString());

       /** The Constant COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT. */
       PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLGOVERNMENTROLEGANTT.toString());

       /** The Constant COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT. */
       PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESBYHEADCOUNT.toString());

       /** The Constant COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT. */
       PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTPARTIESBYHEADCOUNT.toString());

       /** The Constant COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS. */
       PageModeMenuCommand COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYTOTALDAYS.toString());

       /** The Constant COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT. */
       PageModeMenuCommand COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString());

       /** The Constant COMMAND_PAGEVISITHISTORY. */
       PageModeMenuCommand COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.PAGEVISITHISTORY);

       /** The Constant COMMAND_CHARTS_PARTY_WINNER. */
       PageModeMenuCommand COMMAND_CHARTS_PARTY_WINNER = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString());

       /** The Constant COMMAND_CHARTS_PARTY_GENDER. */
       PageModeMenuCommand COMMAND_CHARTS_PARTY_GENDER = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.PARTYGENDER.toString());

       /** The Constant COMMAND_CHARTS_PARTY_AGE. */
       PageModeMenuCommand COMMAND_CHARTS_PARTY_AGE = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.PARTYAGE.toString());

       /** The Constant COMMAND_RISK_SUMMARY. */
       PageModeMenuCommand COMMAND_RISK_SUMMARY = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.RISKSUMMARY.toString());

       /** The Constant COMMAND_RULE_VIOLATION. */
       PageModeMenuCommand COMMAND_RULE_VIOLATION = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.RULEVIOLATION.toString());

       /** The Constant COMMAND_DOCUMENT_ACTIVITY. */
       PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DOCUMENTACTIVITY.toString());

       /** The Constant COMMAND_DECISION_ACTIVITY. */
       PageModeMenuCommand COMMAND_DECISION_ACTIVITY = new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DECISIONACTIVITY.toString());

       /** The Constant COMMAND_CHARTS_DECISION_FLOW. */
       PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DECISIONFLOW.toString());

}
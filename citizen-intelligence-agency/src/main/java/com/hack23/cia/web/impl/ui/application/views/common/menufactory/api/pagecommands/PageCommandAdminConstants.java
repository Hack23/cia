package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.text.MenuItemConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DataSummaryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;


/**
 * The Interface MenuItemConstants.
 */
public interface PageCommandAdminConstants extends MenuItemConstants {


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
                     AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.OVERVIEW);

       /** The Constant COMMAND_APPLICATION_EVENTS_CHARTS. */
       PageModeMenuCommand COMMAND_APPLICATION_EVENTS_CHARTS = new PageModeMenuCommand(
                     AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS);

       /** The Constant COMMAND_APPLICATION_SESSION. */
       PageModeMenuCommand COMMAND_APPLICATION_SESSION = new PageModeMenuCommand(
                     AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, PageMode.OVERVIEW);

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
                     AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, PageMode.OVERVIEW);

       /** The Constant COMMAND_DATA_QUALITY. */
       PageModeMenuCommand COMMAND_DATA_QUALITY = new PageModeMenuCommand(
                     AdminViews.ADMIN_DATA_QUALITY_VIEW_NAME, "");

       /** The Constant COMMAND_EMAIL. */
       PageModeMenuCommand COMMAND_EMAIL = new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME,
                     "");

       /** The Constant COMMAND_LANGUAGE. */
       PageModeMenuCommand COMMAND_LANGUAGE = new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME,
                     "");


       /** The Constant COMMAND_MONITORING. */
       PageModeMenuCommand COMMAND_MONITORING = new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME,
                     "");

       /** The Constant COMMAND_PORTAL. */
       PageModeMenuCommand COMMAND_PORTAL = new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, "");

       /** The Constant COMMAND_USERACCOUNT. */
       PageModeMenuCommand COMMAND_USERACCOUNT = new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME,
                     "");

}
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;

/**
 * The Interface PageCommandMainViewConstants.
 */
public interface PageCommandMainViewConstants {

    /** The command mainview overview. */
    PageModeMenuCommand COMMAND_MAINVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     PageMode.OVERVIEW);

    /** The command dashboardview overview. */
    PageModeMenuCommand COMMAND_DASHBOARDVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.DASHBOARD_VIEW_NAME,
                     PageMode.OVERVIEW);

    /** The command mainview pagevisithistory. */
    PageModeMenuCommand COMMAND_MAINVIEW_PAGEVISITHISTORY = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     PageMode.PAGEVISITHISTORY);

    /** The command login. */
    PageModeMenuCommand COMMAND_LOGIN = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     ApplicationPageMode.LOGIN.toString());

    /** The command logout. */
    PageModeMenuCommand COMMAND_LOGOUT = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     ApplicationPageMode.LOGOUT.toString());

    /** The command register. */
    PageModeMenuCommand COMMAND_REGISTER = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     ApplicationPageMode.REGISTER.toString());

    /** The command main overview. */
    PageModeMenuCommand COMMAND_MAIN_OVERVIEW = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     PageMode.OVERVIEW);

    /** The command main ranking. */
    PageModeMenuCommand COMMAND_MAIN_RANKING = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     "ranking");

    /** The command main datagrid. */
    PageModeMenuCommand COMMAND_MAIN_DATAGRID = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     PageMode.DATAGRID);
}

package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;

public interface PageCommandMainViewConstants {
    PageModeMenuCommand COMMAND_MAINVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     PageMode.OVERVIEW);

    PageModeMenuCommand COMMAND_DASHBOARDVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.DASHBOARD_VIEW_NAME,
                     PageMode.OVERVIEW);

    PageModeMenuCommand COMMAND_MAINVIEW_PAGEVISITHISTORY = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     PageMode.PAGEVISITHISTORY);

    PageModeMenuCommand COMMAND_LOGIN = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     ApplicationPageMode.LOGIN.toString());

    PageModeMenuCommand COMMAND_LOGOUT = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     ApplicationPageMode.LOGOUT.toString());

    PageModeMenuCommand COMMAND_REGISTER = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                     ApplicationPageMode.REGISTER.toString());
}

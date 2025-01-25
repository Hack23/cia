package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;

public interface PageCommandApplicationConstants {
    PageModeMenuCommand COMMAND_DASHBOARDVIEW_OVERVIEW = new PageModeMenuCommand(
        CommonsViews.DASHBOARD_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(
        CommonsViews.MAIN_VIEW_NAME, "documents");
        
    PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(
        CommonsViews.MAIN_VIEW_NAME, "searchdocument");
        
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
        CommonsViews.MAIN_VIEW_NAME, PageMode.CHARTS, "MINISTRIES_LEADER_SCOREBOARD");
        
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
        CommonsViews.MAIN_VIEW_NAME, PageMode.CHARTS, "PARTIES_LEADER_SCOREBOARD");
}

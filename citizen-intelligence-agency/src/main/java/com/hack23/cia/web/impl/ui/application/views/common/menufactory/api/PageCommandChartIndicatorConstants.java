package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandChartIndicatorConstants {
    PageModeMenuCommand COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "ALL_MINISTRIES_BY_HEADCOUNT");
        
    PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "ALL_GOVERNMENT_ROLE_GANTT");
        
    PageModeMenuCommand COMMAND_GOVERNMENT_OUTCOME = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "GOVERNMENT_OUTCOME");
        
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "CURRENT_PARTIES_BY_HEADCOUNT");
}

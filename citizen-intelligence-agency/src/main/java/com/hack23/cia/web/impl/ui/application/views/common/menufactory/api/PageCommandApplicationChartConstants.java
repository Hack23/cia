package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandApplicationChartConstants {
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, "CURRENT_PARTIES_LEADER_SCOREBOARD");

    PageModeMenuCommand COMMAND_CHARTS_CURRENT_COMMITTEES = new PageModeMenuCommand(
        UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS, "CURRENT_COMMITTEES");
        
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_HEADCOUNT = new PageModeMenuCommand(
        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.CHARTS, "GOVERNMENT_BODIES_HEADCOUNT");
        
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_INCOME = new PageModeMenuCommand(
        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.CHARTS, "GOVERNMENT_BODIES_INCOME");
        
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_EXPENDITURE = new PageModeMenuCommand(
        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.CHARTS, "GOVERNMENT_BODIES_EXPENDITURE");
}

package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandPartyRankingConstants {
    // All commands should follow PARTY_RANKING_COMMAND_* pattern
    PageModeMenuCommand PARTY_RANKING_COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
                     PageMode.DATAGRID);

    PageModeMenuCommand PARTY_RANKING_COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
                     PageMode.OVERVIEW);

    PageModeMenuCommand PARTY_RANKING_COMMAND_CHARTS_ALL_PARTIES = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALLPARTIES.toString());

    PageModeMenuCommand PARTY_RANKING_COMMAND_CHARTS_CURRENT_PARTIES = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTIES.toString());
    
    PageModeMenuCommand PARTY_RANKING_COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,
                     ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString());

    PageModeMenuCommand PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

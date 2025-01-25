package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPartyRankingConstants.
 */
public interface PageCommandPartyRankingConstants {

    /** The party ranking command datagrid. */
    // All commands should follow PARTY_RANKING_COMMAND_* pattern
    PageModeMenuCommand PARTY_RANKING_COMMAND_DATAGRID = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
                     PageMode.DATAGRID);

    /** The party ranking command overview. */
    PageModeMenuCommand PARTY_RANKING_COMMAND_OVERVIEW = new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME,
                     PageMode.OVERVIEW);

    /** The party ranking command charts all parties. */
    PageModeMenuCommand PARTY_RANKING_COMMAND_CHARTS_ALL_PARTIES = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALLPARTIES.toString());

    /** The party ranking command charts current parties. */
    PageModeMenuCommand PARTY_RANKING_COMMAND_CHARTS_CURRENT_PARTIES = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTIES.toString());

    /** The party ranking command charts current parties leader scoreboard. */
    PageModeMenuCommand PARTY_RANKING_COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,
                     ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString());

    /** The party ranking command pagevisit history. */
    PageModeMenuCommand PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

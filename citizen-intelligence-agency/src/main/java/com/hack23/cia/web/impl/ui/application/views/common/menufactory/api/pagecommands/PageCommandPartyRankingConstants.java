package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPartyRankingConstants.
 */
public interface PageCommandPartyRankingConstants {


    /** The command charts current government parties. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,
                     ChartIndicators.CURRENTGOVERMENTPARTIES.toString());

    /** The command party leader scoreboard. */
    PageModeMenuCommand COMMAND_PARTY_LEADER_SCOREBOARD = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString());

    /** The command party ranking charts all parties. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_CHARTS_ALL_PARTIES = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALLPARTIES.toString());

    /** The command party ranking charts current committees. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_CHARTS_CURRENT_COMMITTEES = new PageModeMenuCommand(
            UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,ChartIndicators.CURRENTCOMMITTEES.toString());

    /** The command party ranking charts current parties. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_CHARTS_CURRENT_PARTIES = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTIES.toString());

    /** The command party ranking datagrid. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The command party ranking overview. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);



    /** The party ranking command pagevisit history. */
    PageModeMenuCommand PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY =
            new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

}

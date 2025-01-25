package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPartyRankingConstants.
 */
public interface PageCommandPartyRankingConstants {

    /** The command parties link. */
    PageModeMenuCommand COMMAND_PARTIES_LINK = new PageModeMenuCommand(
            UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The command party ranking all members. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_ALL_MEMBERS = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, "allmembers");

    /** The command party ranking current members. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_CURRENT_MEMBERS = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, "currentmembers");


    /** The command charts current government parties. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES = new PageModeMenuCommand(
                     UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS,
                     ChartIndicators.CURRENTGOVERMENTPARTIES.toString());

    /** The command charts party winner. */
    PageModeMenuCommand COMMAND_CHARTS_PARTY_WINNER = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString());

    /** The command charts party gender. */
    PageModeMenuCommand COMMAND_CHARTS_PARTY_GENDER = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.PARTYGENDER.toString());

    /** The command charts party age. */
    PageModeMenuCommand COMMAND_CHARTS_PARTY_AGE = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.PARTYAGE.toString());


    /** The party ranking command pagevisit history. */
    PageModeMenuCommand PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY =
            new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    /** The command party ranking overview. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command party ranking datagrid. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The command party leader scoreboard. */
    PageModeMenuCommand COMMAND_PARTY_LEADER_SCOREBOARD = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, "PARTYLEADERSCOREBOARD");

    /** The command party ranking charts current parties. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_CHARTS_CURRENT_PARTIES = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, "CURRENT_PARTIES");

    /** The command party ranking charts all parties. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_CHARTS_ALL_PARTIES = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, "ALL_PARTIES");

    /** The command charts current parties leader scoreboard. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
            UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS, "CURRENT_PARTIES_LEADER_SCOREBOARD");

}

package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPartyRankingConstants.
 */
public interface PageCommandPartyRankingConstants {

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
}

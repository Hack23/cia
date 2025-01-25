package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandPageModeConstants extends RankingNavigationConstants {
    // Generic base commands without view context - define these first
    PageModeMenuCommand COMMAND_GENERIC_OVERVIEW = new PageModeMenuCommand("", PageMode.OVERVIEW);
    PageModeMenuCommand COMMAND_GENERIC_PAGEVISIT = new PageModeMenuCommand("", PageMode.PAGEVISITHISTORY);
    PageModeMenuCommand COMMAND_GENERIC_DATAGRID = new PageModeMenuCommand("", PageMode.DATAGRID);
    PageModeMenuCommand COMMAND_GENERIC_CHARTS = new PageModeMenuCommand("", PageMode.CHARTS);

    // Base commands that reference the generic ones - these must come after
    PageModeMenuCommand COMMAND_OVERVIEW = COMMAND_GENERIC_OVERVIEW;
    PageModeMenuCommand COMMAND_PAGEVISIT = COMMAND_GENERIC_PAGEVISIT;
    PageModeMenuCommand COMMAND_DATAGRID = COMMAND_GENERIC_DATAGRID;
    PageModeMenuCommand COMMAND_CHARTS = COMMAND_GENERIC_CHARTS;

    // Party ranking commands
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD =
        new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS);
    PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW =
        new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);
    PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID =
        new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);
}

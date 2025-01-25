package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPageModeConstants.
 */
public interface PageCommandPageModeConstants extends RankingNavigationConstants {

    /** The command generic overview. */
    // Generic base commands without view context - define these first
    PageModeMenuCommand COMMAND_GENERIC_OVERVIEW = new PageModeMenuCommand("", PageMode.OVERVIEW);

    /** The command generic pagevisit. */
    PageModeMenuCommand COMMAND_GENERIC_PAGEVISIT = new PageModeMenuCommand("", PageMode.PAGEVISITHISTORY);

    /** The command generic datagrid. */
    PageModeMenuCommand COMMAND_GENERIC_DATAGRID = new PageModeMenuCommand("", PageMode.DATAGRID);

    /** The command generic charts. */
    PageModeMenuCommand COMMAND_GENERIC_CHARTS = new PageModeMenuCommand("", PageMode.CHARTS);

    /** The command overview. */
    // Base commands that reference the generic ones - these must come after
    PageModeMenuCommand COMMAND_OVERVIEW = COMMAND_GENERIC_OVERVIEW;

    /** The command pagevisit. */
    PageModeMenuCommand COMMAND_PAGEVISIT = COMMAND_GENERIC_PAGEVISIT;

    /** The command datagrid. */
    PageModeMenuCommand COMMAND_DATAGRID = COMMAND_GENERIC_DATAGRID;

    /** The command charts. */
    PageModeMenuCommand COMMAND_CHARTS = COMMAND_GENERIC_CHARTS;

    /** The command charts current parties leader scoreboard. */
    // Party ranking commands
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_LEADER_SCOREBOARD =
        new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS);

    /** The command party ranking overview. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW =
        new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command party ranking datagrid. */
    PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID =
        new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);
}

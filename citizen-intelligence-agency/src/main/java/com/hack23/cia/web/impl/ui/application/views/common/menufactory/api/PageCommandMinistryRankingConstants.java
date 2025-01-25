package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandMinistryRankingConstants.
 */
public interface PageCommandMinistryRankingConstants {

    /** The command ministry ranking datagrid. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The command ministry ranking overview. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command charts current ministries leader scoreboard. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());

    /** The command charts current ministries by headcount. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESBYHEADCOUNT.toString());

    /** The command charts all ministries by headcount. */
    PageModeMenuCommand COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString());

    /** The command charts allministries by total days. */
    PageModeMenuCommand COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYTOTALDAYS.toString());

    /** The command charts current parties by headcount. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTPARTIESBYHEADCOUNT.toString());

    /** The ministry ranking command pagevisit history. */
    PageModeMenuCommand MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    // Remove ambiguous constant
    // PageModeMenuCommand COMMAND_PAGEVISITHISTORY
}

package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandMinistryRankingConstants.
 */
public interface PageCommandMinistryRankingConstants {

    /** The ministry ranking command pagevisit history. */
    PageModeMenuCommand MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    /** The command ministry ranking overview. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command ministry ranking datagrid. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The command ministry ranking charts. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_CHARTS = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS);

    /** The command charts all ministries by headcount. */
    PageModeMenuCommand COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString());

    /** The command role charts. */
    PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALL_GOVERNMENT_ROLE_CHART.toString());

    /** The command government outcome. */
    PageModeMenuCommand COMMAND_GOVERNMENT_OUTCOME = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, MinistryPageMode.GOVERNMENT_OUTCOME.toString());

    /** The command government bodies income. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_INCOME = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString());

    /** The command government bodies expenditure. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_EXPENDITURE = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString());

    /** The command government bodies headcount. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_HEADCOUNT = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString());

    /** The command charts current parties by headcount. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTIESBYHEADCOUNT.toString());

    /** The command charts current ministries leader scoreboard. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());

    /** The command charts current ministries by headcount. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESBYHEADCOUNT.toString());

    /** The command charts all parties by headcount. */
    PageModeMenuCommand COMMAND_CHARTS_ALL_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALLPARTIESBYHEADCOUNT.toString());

}

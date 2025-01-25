package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandMinistryRankingConstants {
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());

    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTMINISTRIESBYHEADCOUNT.toString());

    PageModeMenuCommand COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString());

    PageModeMenuCommand COMMAND_CHARTS_ALLMINISTRIES_BY_TOTAL_DAYS = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYTOTALDAYS.toString());

    PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTPARTIESBYHEADCOUNT.toString());

    PageModeMenuCommand MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    // Remove ambiguous constant
    // PageModeMenuCommand COMMAND_PAGEVISITHISTORY
}

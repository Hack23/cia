package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandCommitteeRankingConstants {
    PageModeMenuCommand COMMAND_COMMITTEE_RANKING_DATAGRID = new PageModeMenuCommand(
                     UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID);

    PageModeMenuCommand COMMAND_COMMITTEE_RANKING_OVERVIEW = new PageModeMenuCommand(
                     UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    PageModeMenuCommand COMMAND_ALL_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.COMMITTEE_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLCOMMITTEESBYHEADCOUNT.toString());

    PageModeMenuCommand COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.COMMITTEE_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.CURRENTCOMMITTEESBYHEADCOUNT.toString());

    PageModeMenuCommand COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    // Remove this line to avoid ambiguity
    // PageModeMenuCommand COMMAND_PAGEVISIT_HISTORY = COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY;
}

package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandGovernmentBodyRankingConstants {
    PageModeMenuCommand GOVERNMENT_BODY_COMMAN_OVERVIEW = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.OVERVIEW);

    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.DATAGRID);
                     
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.GOVERNMENTBODIESHEADCOUNT.toString());

    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_INCOME = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.GOVERNMENTBODIESINCOME.toString());

    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_EXPENDITURE = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.GOVERNMENTBODIESEXPENDITURE.toString());

    PageModeMenuCommand COMMAND_GOVERNMENT_OUTCOME = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.GOVERNMENTOUTCOME.toString());

    PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLGOVERNMENTROLEGANTT.toString());

    PageModeMenuCommand GOVERNMENT_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    // Remove this line to avoid ambiguity
    // PageModeMenuCommand COMMAND_PAGEVISITHISTORY = GOVERNMENT_RANKING_COMMAND_PAGEVISIT_HISTORY;
}

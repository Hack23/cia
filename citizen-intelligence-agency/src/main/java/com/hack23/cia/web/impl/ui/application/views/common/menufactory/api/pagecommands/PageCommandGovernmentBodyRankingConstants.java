package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.GovernmentBodyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandGovernmentBodyRankingConstants.
 */
public interface PageCommandGovernmentBodyRankingConstants {

    /** The command government body ranking datagrid. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The command government body ranking overview. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command government bodies headcount. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_HEADCOUNT = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, GovernmentBodyPageMode.HEADCOUNT.toString());

    /** The command government bodies income. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_INCOME = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, GovernmentBodyPageMode.INCOME.toString());

    /** The command government bodies expenditure. */
    PageModeMenuCommand COMMAND_GOVERNMENT_BODIES_EXPENDITURE = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, GovernmentBodyPageMode.EXPENDITURE.toString());

    /** The command government outcome. */
    PageModeMenuCommand COMMAND_GOVERNMENT_OUTCOME = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.GOVERNMENTOUTCOME.toString());

    /** The command charts all government role gantt. */
    PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.ALLGOVERNMENTROLEGANTT.toString());

    /** The government ranking command pagevisit history. */
    PageModeMenuCommand GOVERNMENT_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);


}

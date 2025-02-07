package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPoliticianRankingConstants.
 */
public interface PageCommandPoliticianRankingConstants {

	/** The politician ranking command datagrid. */
	PageModeMenuCommand COMMAND_POLITICIAN_RANKING_DATAGRID = new PageModeMenuCommand(
			UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID);

	/** The politician ranking command overview. */
	PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(
			UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The politician ranking command indicators. */
	PageModeMenuCommand COMMAND_POLITICIAN_RANKING_INDICATORS = new PageModeMenuCommand(
			UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.INDICATORS);

	/** The pol rank command charts all parties by headcount. */
	PageModeMenuCommand POL_RANK_COMMAND_CHARTS_ALL_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(
			UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.ALLPARTIES.toString());

	/** The politician ranking command datagrid. */
	PageModeMenuCommand POLITICIAN_RANKING_COMMAND_DATAGRID = new PageModeMenuCommand(
			UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID);

	/** The politician ranking command overview. */
	PageModeMenuCommand POLITICIAN_RANKING_COMMAND_OVERVIEW = new PageModeMenuCommand(
			UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

	/** The politician ranking command pagevisit history. */
	PageModeMenuCommand POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
			UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND_CURRENT_PARTIES. */
	PageModeMenuCommand POLITICIAN_RANKING_COMMAND_CURRENT_PARTIES = new PageModeMenuCommand(
			UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.CURRENTPARTIES.toString());

}

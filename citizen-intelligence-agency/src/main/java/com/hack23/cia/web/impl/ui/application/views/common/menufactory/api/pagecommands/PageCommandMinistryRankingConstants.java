package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandMinistryRankingConstants.
 */
public interface PageCommandMinistryRankingConstants {

    /** The ministry ranking command pagevisit history. */
    PageModeMenuCommand MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY =
            new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);


    /** The command ministries link. */
    PageModeMenuCommand COMMAND_MINISTRIES_LINK = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);


    /** The command ministry ranking overview. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command ministry ranking datagrid. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The command ministry ranking charts. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_CHARTS = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS);

    /** The command ministry ranking pagevisit history. */
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_PAGEVISIT_HISTORY = new PageModeMenuCommand(
            UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

	/** The command charts all ministries by headcount. */
	PageModeMenuCommand COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
			UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "ALL_MINISTRIES_BY_HEADCOUNT");

	/** The command charts all government role gantt. */
	PageModeMenuCommand COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT = new PageModeMenuCommand(
			UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "ALL_GOVERNMENT_ROLE_GANTT");

	/** The command government outcome. */
	PageModeMenuCommand COMMAND_GOVERNMENT_OUTCOME = new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME,
			PageMode.CHARTS, "GOVERNMENT_OUTCOME");

	/** The command charts current parties by headcount. */
	PageModeMenuCommand COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT = new PageModeMenuCommand(
			UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "CURRENT_PARTIES_BY_HEADCOUNT");

	/** The command charts current ministries leader scoreboard. */
	PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
			UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "MINISTRIES_LEADER_SCOREBOARD");

	/** The command charts current ministries by headcount. */
	PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
			UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "CURRENT_MINISTRIES_BY_HEADCOUNT");


}

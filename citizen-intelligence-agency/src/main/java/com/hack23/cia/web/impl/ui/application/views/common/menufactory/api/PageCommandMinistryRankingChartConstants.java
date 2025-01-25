package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandMinistryRankingChartConstants.
 */
public interface PageCommandMinistryRankingChartConstants {
    /** The command charts current ministries leader scoreboard. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "MINISTRIES_LEADER_SCOREBOARD");
        
    /** The command charts current ministries by headcount. */
    PageModeMenuCommand COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS, "CURRENT_MINISTRIES_BY_HEADCOUNT");
}

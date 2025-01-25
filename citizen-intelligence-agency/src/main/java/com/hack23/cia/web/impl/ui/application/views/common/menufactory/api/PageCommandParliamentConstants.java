package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandParliamentConstants.
 */
public interface PageCommandParliamentConstants {

    /** The command parliament overview. */
    PageModeMenuCommand COMMAND_PARLIAMENT_OVERVIEW = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command risk summary. */
    PageModeMenuCommand COMMAND_RISK_SUMMARY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.RISKSUMMARY.toString());

    /** The command charts decision flow. */
    PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DECISIONFLOW.toString());

    /** The parliament command pagevisit history. */
    PageModeMenuCommand PARLIAMENT_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

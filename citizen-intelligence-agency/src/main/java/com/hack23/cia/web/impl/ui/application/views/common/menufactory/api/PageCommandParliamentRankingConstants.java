package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandParliamentRankingConstants.
 */
public interface PageCommandParliamentRankingConstants {

    /** The command parliament ranking overview. */
    PageModeMenuCommand COMMAND_PARLIAMENT_RANKING_OVERVIEW = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The command risk summary. */
    PageModeMenuCommand COMMAND_RISK_SUMMARY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, "risksummary");

    /** The command rule violation. */
    PageModeMenuCommand COMMAND_RULE_VIOLATION = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, "ruleviolation");

    /** The command document activity. */
    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DOCUMENTACTIVITY.toString());

    /** The command decision activity. */
    PageModeMenuCommand COMMAND_DECISION_ACTIVITY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DECISIONACTIVITY.toString());

    /** The command charts decision flow. */
    PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, "DECISION_FLOW");

    /** The parliament ranking command pagevisit history. */
    PageModeMenuCommand PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

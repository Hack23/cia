package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandParliamentRankingConstants {
    PageModeMenuCommand COMMAND_PARLIAMENT_RANKING_OVERVIEW = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    PageModeMenuCommand COMMAND_RISK_SUMMARY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.RISKSUMMARY.toString());

    PageModeMenuCommand COMMAND_RULE_VIOLATION = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.RULEVIOLATION.toString());

    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DOCUMENTACTIVITY.toString());

    PageModeMenuCommand COMMAND_DECISION_ACTIVITY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DECISIONACTIVITY.toString());

    PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DECISIONFLOW.toString());

    PageModeMenuCommand PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

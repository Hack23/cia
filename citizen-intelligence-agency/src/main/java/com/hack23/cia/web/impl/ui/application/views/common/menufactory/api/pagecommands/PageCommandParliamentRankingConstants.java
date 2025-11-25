package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.RiskIndicators;
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
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,PageMode.RULES, RiskIndicators.RISK_SUMMARY.toString());

    /** The command rule violation. */
    PageModeMenuCommand COMMAND_RULE_VIOLATION = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.RULES,RiskIndicators.RULE_VIOLATIONS.toString());

    /** The command document activity. */
    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DOCUMENTACTIVITYBYTYPE.toString());

    /** The command decision activity. */
    PageModeMenuCommand COMMAND_DECISION_ACTIVITY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME,
                     PageMode.CHARTS, ChartIndicators.DECISIONACTIVITYBYTYPE.toString());

    /** The command charts decision flow. */
    PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DECISION_FLOW_CHART.toString());

    /** The parliament ranking command pagevisit history. */
    PageModeMenuCommand PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    /** The command charts party winner. */
    PageModeMenuCommand COMMAND_CHARTS_PARTY_WINNER = new PageModeMenuCommand(
            UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString());

        /** The command charts party gender. */
        PageModeMenuCommand COMMAND_CHARTS_PARTY_GENDER = new PageModeMenuCommand(
            UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYGENDER.toString());

        /** The command charts party age. */
        PageModeMenuCommand COMMAND_CHARTS_PARTY_AGE = new PageModeMenuCommand(
            UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYAGE.toString());

        /** The command charts decision activity. */
        PageModeMenuCommand COMMAND_CHARTS_DECISION_ACTIVITY = new PageModeMenuCommand(
            UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DECISIONACTIVITYBYTYPE.toString());

        /** The command coalition prediction. */
        PageModeMenuCommand COMMAND_COALITION_PREDICTION = new PageModeMenuCommand(
            UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, ChartIndicators.COALITION_PREDICTION.toString());


}

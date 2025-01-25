package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandParliamentChartConstants {
    PageModeMenuCommand COMMAND_CHARTS_PARTY_WINNER = new PageModeMenuCommand(
        UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, "PARTY_WINNER");

    PageModeMenuCommand COMMAND_CHARTS_PARTY_GENDER = new PageModeMenuCommand(
        UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, "PARTY_GENDER");

    PageModeMenuCommand COMMAND_CHARTS_PARTY_AGE = new PageModeMenuCommand(
        UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, "PARTY_AGE");

    PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
        UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, "DECISION_FLOW");

    PageModeMenuCommand COMMAND_CHARTS_DECISION_ACTIVITY = new PageModeMenuCommand(
        UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.CHARTS, "DECISION_ACTIVITY");
}

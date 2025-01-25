package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandChartConstants {
    PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
        UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS, 
        ChartIndicators.DECISION_FLOW_CHART.toString());
        
    PageModeMenuCommand COMMAND_CHARTS_PARTY_WINNER = new PageModeMenuCommand(
        UserViews.PARTY_VIEW_NAME, PageMode.CHARTS, 
        ChartIndicators.PARTYWINNER.toString());
}

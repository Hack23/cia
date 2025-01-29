package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandCommitteeConstants.
 */
public interface PageCommandCommitteeConstants {


    /** The command committee overview. */
    PageModeMenuCommand COMMAND_COMMITTEE_OVERVIEW = new PageModeMenuCommand(
                     UserViews.COMMITTEE_VIEW_NAME, PageMode.OVERVIEW);

    /** The command committee role ghant. */
    PageModeMenuCommand COMMAND_COMMITTEE_ROLE_GHANT = new PageModeMenuCommand(
                     UserViews.COMMITTEE_VIEW_NAME, "roleghant");

    /** The command committee pagevisit history. */
    PageModeMenuCommand COMMAND_COMMITTEE_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    /** The command charts decision flow. */
    PageModeMenuCommand COMMAND_CHARTS_DECISION_FLOW = new PageModeMenuCommand(
            UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS,
            ChartIndicators.DECISION_FLOW_CHART.toString());

}

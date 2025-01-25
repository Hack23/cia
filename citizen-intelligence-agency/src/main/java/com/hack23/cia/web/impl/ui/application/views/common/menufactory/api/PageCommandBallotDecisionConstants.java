package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandBallotDecisionConstants {
    PageModeMenuCommand COMMAND_BALLOT_DECISION_SUMMARY = new PageModeMenuCommand(
        UserViews.BALLOT_VIEW_NAME, "ballotdecisionsummary");
        
    PageModeMenuCommand COMMAND_DECISION_SUMMARY = new PageModeMenuCommand(
        UserViews.BALLOT_VIEW_NAME, "decisionsummary");
        
    PageModeMenuCommand COMMAND_DECISION_FLOW = new PageModeMenuCommand(
        UserViews.BALLOT_VIEW_NAME, PageMode.CHARTS, "DECISION_FLOW");
}

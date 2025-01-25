package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandBallotConstants {
    PageModeMenuCommand COMMAND_BALLOT_OVERVIEW = new PageModeMenuCommand(
                     UserViews.BALLOT_VIEW_NAME, PageMode.OVERVIEW);
                     
    PageModeMenuCommand COMMAND_BALLOT_DECISION_SUMMARY = new PageModeMenuCommand(
                     UserViews.BALLOT_VIEW_NAME, "decisionsummary");
                     
    PageModeMenuCommand COMMAND_BALLOT_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.BALLOT_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

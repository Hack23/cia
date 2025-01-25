package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandBallotConstants.
 */
public interface PageCommandBallotConstants {

    /** The command ballot overview. */
    PageModeMenuCommand COMMAND_BALLOT_OVERVIEW = new PageModeMenuCommand(
                     UserViews.BALLOT_VIEW_NAME, PageMode.OVERVIEW);

    /** The command ballot decision summary. */
    PageModeMenuCommand COMMAND_BALLOT_DECISION_SUMMARY = new PageModeMenuCommand(
                     UserViews.BALLOT_VIEW_NAME, "decisionsummary");

    /** The command ballot pagevisit history. */
    PageModeMenuCommand COMMAND_BALLOT_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.BALLOT_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    /** The command ballot charts. */
    PageModeMenuCommand COMMAND_BALLOT_CHARTS = new PageModeMenuCommand(
                     UserViews.BALLOT_VIEW_NAME, PageMode.CHARTS);
}

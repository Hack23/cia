package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandBallotConstants.
 */
public interface PageCommandBallotConstants {

	/** The command ballot overview. */
	PageModeMenuCommand COMMAND_BALLOT_OVERVIEW = new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The command ballot charts. */
	PageModeMenuCommand COMMAND_BALLOT_CHARTS = new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, PageMode.CHARTS);

}

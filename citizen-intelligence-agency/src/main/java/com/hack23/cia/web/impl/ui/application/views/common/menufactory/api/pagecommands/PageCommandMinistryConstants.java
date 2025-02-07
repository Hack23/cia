package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandMinistryConstants.
 */
public interface PageCommandMinistryConstants {

	/** The ministry command charts all by headcount. */
	PageModeMenuCommand MINISTRY_COMMAND_CHARTS_ALL_BY_HEADCOUNT = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
			PageMode.CHARTS, ChartIndicators.ALLMINISTRIESBYHEADCOUNT.toString());

	/** The ministry command pagevisithistory. */
	PageModeMenuCommand MINISTRY_COMMAND_PAGEVISITHISTORY = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
			PageMode.PAGEVISITHISTORY);

}

package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
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

	/** The command ministry overview. */
	PageModeMenuCommand COMMAND_MINISTRY_OVERVIEW = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW);

	/** The command ministry current members. */
	PageModeMenuCommand COMMAND_MINISTRY_CURRENT_MEMBERS = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.CURRENTMEMBERS.toString());

	/** The command ministry member history. */
	PageModeMenuCommand COMMAND_MINISTRY_MEMBER_HISTORY = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.MEMBERHISTORY.toString());

	/** The command ministry role ghant. */
	PageModeMenuCommand COMMAND_MINISTRY_ROLE_GHANT = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.ROLEGHANT.toString());

	/** The command ministry government bodies headcount. */
	PageModeMenuCommand COMMAND_MINISTRY_GOVERNMENT_BODIES_HEADCOUNT = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString());

	/** The command ministry government bodies income. */
	PageModeMenuCommand COMMAND_MINISTRY_GOVERNMENT_BODIES_INCOME = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString());

	/** The command ministry government bodies expenditure. */
	PageModeMenuCommand COMMAND_MINISTRY_GOVERNMENT_BODIES_EXPENDITURE = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString());

	/** The command ministry document activity. */
	PageModeMenuCommand COMMAND_MINISTRY_DOCUMENT_ACTIVITY = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.DOCUMENTACTIVITY.toString());

	/** The command ministry document history. */
	PageModeMenuCommand COMMAND_MINISTRY_DOCUMENT_HISTORY = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, MinistryPageMode.DOCUMENTHISTORY.toString());

	/** The command ministry page visit history. */
	PageModeMenuCommand COMMAND_MINISTRY_PAGEVISIT_HISTORY = new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY.toString());

}

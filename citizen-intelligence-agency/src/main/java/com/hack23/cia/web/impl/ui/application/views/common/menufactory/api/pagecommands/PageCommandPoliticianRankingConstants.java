package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPoliticianRankingConstants.
 */
public interface PageCommandPoliticianRankingConstants {

    /** The politician ranking command datagrid. */
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_DATAGRID = new PageModeMenuCommand(
                     UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID);

    /** The politician ranking command overview. */
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(
                     UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    /** The politician ranking command indicators. */
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_INDICATORS = new PageModeMenuCommand(
                     UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.INDICATORS);

    /** The command politician current member ranking. */
    PageModeMenuCommand COMMAND_POLITICIAN_CURRENT_MEMBER_RANKING = new PageModeMenuCommand(
            UserViews.POLITICIAN_RANKING_VIEW_NAME, "currentmembers");

        /** The command politician all member ranking. */
        PageModeMenuCommand COMMAND_POLITICIAN_ALL_MEMBER_RANKING = new PageModeMenuCommand(
            UserViews.POLITICIAN_RANKING_VIEW_NAME, "allmembers");


        /** The politician ranking command datagrid. */
        PageModeMenuCommand POLITICIAN_RANKING_COMMAND_DATAGRID = new PageModeMenuCommand(
                UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID);

        /** The politician ranking command overview. */
        PageModeMenuCommand POLITICIAN_RANKING_COMMAND_OVERVIEW = new PageModeMenuCommand(
                UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

		/** The politician ranking command pagevisit history. */
		PageModeMenuCommand POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);


}

package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandPoliticianRankingConstants.
 */
public interface PageCommandPoliticianRankingConstants {

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

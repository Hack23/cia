package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

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

}

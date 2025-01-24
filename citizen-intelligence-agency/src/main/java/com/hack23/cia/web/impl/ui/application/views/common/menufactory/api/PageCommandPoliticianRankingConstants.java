package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandPoliticianRankingConstants {
    PageModeMenuCommand POLITICIAN_RANKING_COMMAND_DATAGRID = new PageModeMenuCommand(
                     UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID);

    PageModeMenuCommand POLITICIAN_RANKING_COMMAND_OVERVIEW = new PageModeMenuCommand(
                     UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);

    PageModeMenuCommand POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);

    // Keep old names for backward compatibility
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = POLITICIAN_RANKING_COMMAND_OVERVIEW;
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_DATAGRID = POLITICIAN_RANKING_COMMAND_DATAGRID;
}

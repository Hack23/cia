package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandPoliticianRankingMenuConstants {
    PageModeMenuCommand POLITICIAN_RANKING_COMMAND_OVERVIEW = new PageModeMenuCommand(
        UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand POLITICIAN_RANKING_COMMAND_DATAGRID = new PageModeMenuCommand(
        UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID);
}

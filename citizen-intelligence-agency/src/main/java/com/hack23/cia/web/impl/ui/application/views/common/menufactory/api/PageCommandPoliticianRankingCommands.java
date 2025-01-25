package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandPoliticianRankingCommands {
    PageModeMenuCommand COMMAND_POLITICIAN_CURRENT_MEMBER_RANKING = new PageModeMenuCommand(
        UserViews.POLITICIAN_RANKING_VIEW_NAME, "currentmembers");
        
    PageModeMenuCommand COMMAND_POLITICIAN_ALL_MEMBER_RANKING = new PageModeMenuCommand(
        UserViews.POLITICIAN_RANKING_VIEW_NAME, "allmembers");
        
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);
}

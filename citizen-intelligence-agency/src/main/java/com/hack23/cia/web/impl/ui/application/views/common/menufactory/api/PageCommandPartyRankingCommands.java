package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandPartyRankingCommands {
    PageModeMenuCommand COMMAND_PARTY_RANKING_ALL_MEMBERS = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, "allmembers");
        
    PageModeMenuCommand COMMAND_PARTY_RANKING_CURRENT_MEMBERS = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, "currentmembers");
        
    PageModeMenuCommand COMMAND_PARTY_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW);
}

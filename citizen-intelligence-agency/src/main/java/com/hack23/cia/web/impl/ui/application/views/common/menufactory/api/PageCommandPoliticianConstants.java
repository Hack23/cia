package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandPoliticianConstants {
    PageModeMenuCommand COMMAND_POLITICIAN_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand COMMAND_POLITICIAN_VIEW = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand COMMAND_POLITICIAN_BALLOT_HISTORY = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, "ballothistory");
        
    PageModeMenuCommand COMMAND_POLITICIAN_DOCUMENT_HISTORY = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, "documenthistory");
}

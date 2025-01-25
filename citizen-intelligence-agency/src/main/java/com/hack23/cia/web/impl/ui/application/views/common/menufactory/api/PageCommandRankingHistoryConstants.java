package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandRankingHistoryConstants {
    PageModeMenuCommand COUNTRY_RANKING_COMMAND_PAGEVISIT_HISTORY = 
        new PageModeMenuCommand(UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
        
    PageModeMenuCommand GOVERNMENT_BODY_COMMAND_PAGEVISIT_HISTORY =
        new PageModeMenuCommand(UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
        
    PageModeMenuCommand MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY =
        new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
        
    PageModeMenuCommand PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY =
        new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
        
    PageModeMenuCommand PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY =
        new PageModeMenuCommand(UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
        
    PageModeMenuCommand COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY =
        new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

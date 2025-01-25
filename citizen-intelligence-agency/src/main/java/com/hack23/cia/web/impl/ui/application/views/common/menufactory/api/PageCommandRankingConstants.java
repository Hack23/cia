package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandRankingConstants {
    PageModeMenuCommand COMMAND_COUNTRY_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.COUNTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand COMMAND_PARLIAMENT_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.PARLIAMENT_RANKING_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand COMMAND_COMMITTEE_RANKING_OVERVIEW = new PageModeMenuCommand(
        UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW);
}

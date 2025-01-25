package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandGridConstants {
    PageModeMenuCommand COMMAND_MINISTRY_RANKING_DATAGRID = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);
        
    PageModeMenuCommand COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID = new PageModeMenuCommand(
        UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME, PageMode.DATAGRID);
        
    PageModeMenuCommand COMMAND_COMMITTEE_RANKING_DATAGRID = new PageModeMenuCommand(
        UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID);
        
    PageModeMenuCommand COMMAND_PARTY_RANKING_DATAGRID = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);
}

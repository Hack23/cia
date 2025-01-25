package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandMenuBarConstants {
    PageModeMenuCommand COMMAND_MINISTRIES_LINK = new PageModeMenuCommand(
        UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID);
        
    PageModeMenuCommand COMMAND_COMMITTEES_LINK = new PageModeMenuCommand(
        UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID);
        
    PageModeMenuCommand COMMAND_PARTIES_LINK = new PageModeMenuCommand(
        UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID);
        
    PageModeMenuCommand COMMAND_POLITICIANS_LINK = new PageModeMenuCommand(
        UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW);
}

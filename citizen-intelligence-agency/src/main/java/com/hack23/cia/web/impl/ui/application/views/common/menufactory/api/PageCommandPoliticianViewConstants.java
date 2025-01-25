package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandPoliticianViewConstants {
    PageModeMenuCommand COMMAND_POLITICIAN_VIEW_OVERVIEW = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand COMMAND_POLITICIAN_VIEW_INDICATORS = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, PageMode.INDICATORS);
        
    PageModeMenuCommand COMMAND_POLITICIAN_VIEW_ROLES = new PageModeMenuCommand(
        UserViews.POLITICIAN_VIEW_NAME, "roles");
}

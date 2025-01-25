package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandSystemConstants.
 */
public interface PageCommandSystemConstants extends PageCommandUserConstants {
    // Common system-wide commands
    PageModeMenuCommand COMMAND_DOCUMENT_OVERVIEW = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW);
        
    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentactivity");
        
    PageModeMenuCommand COMMAND_DOCUMENT_DECISION = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentdecision");
        
    PageModeMenuCommand COMMAND_DOCUMENT_ATTACHMENTS = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentattachments");

    /** The command main view. */
    PageModeMenuCommand COMMAND_MAIN_VIEW = new PageModeMenuCommand(
        CommonsViews.MAIN_VIEW_NAME, PageMode.OVERVIEW);
        
    /** The command dashboard overview. */
    PageModeMenuCommand COMMAND_DASHBOARD_OVERVIEW = new PageModeMenuCommand(
        CommonsViews.DASHBOARD_VIEW_NAME, PageMode.OVERVIEW);
}

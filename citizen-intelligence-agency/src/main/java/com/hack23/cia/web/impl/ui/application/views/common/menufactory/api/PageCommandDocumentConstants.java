package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandDocumentConstants.
 */
public interface PageCommandDocumentConstants {

    /** The command document overview. */
    PageModeMenuCommand COMMAND_DOCUMENT_OVERVIEW = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW);

    /** The command document pagevisit history. */
    PageModeMenuCommand COMMAND_DOCUMENT_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

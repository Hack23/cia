package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Interface PageCommandDocumentConstants.
 */
public interface PageCommandDocumentConstants {

    /** The command document activity. */
    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW);

    /** The command document details. */
    PageModeMenuCommand COMMAND_DOCUMENT_DETAILS = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, "details");

    /** The command document references. */
    PageModeMenuCommand COMMAND_DOCUMENT_REFERENCES = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, "references");

    /** The command documents. */
    PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(
                     UserViews.DOCUMENTS_VIEW_NAME, PageMode.OVERVIEW);

    /** The command search document. */
    PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(
                     UserViews.SEARCH_DOCUMENT_VIEW_NAME, "");

    /** The command document pagevisit history. */
    PageModeMenuCommand COMMAND_DOCUMENT_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

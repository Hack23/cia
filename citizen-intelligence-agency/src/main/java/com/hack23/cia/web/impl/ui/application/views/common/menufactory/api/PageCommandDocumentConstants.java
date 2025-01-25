package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandDocumentConstants {
    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW);

    PageModeMenuCommand COMMAND_DOCUMENT_DETAILS = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, "details");

    PageModeMenuCommand COMMAND_DOCUMENT_REFERENCES = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, "references");

    PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(
                     UserViews.DOCUMENTS_VIEW_NAME, PageMode.OVERVIEW);

    PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(
                     UserViews.SEARCH_DOCUMENT_VIEW_NAME, "");

    PageModeMenuCommand COMMAND_DOCUMENT_PAGEVISIT_HISTORY = new PageModeMenuCommand(
                     UserViews.DOCUMENT_VIEW_NAME, PageMode.PAGEVISITHISTORY);
}

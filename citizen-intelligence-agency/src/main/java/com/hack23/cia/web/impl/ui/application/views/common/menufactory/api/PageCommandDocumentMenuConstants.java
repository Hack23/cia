package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandDocumentMenuConstants {
    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentactivity");
        
    PageModeMenuCommand COMMAND_DOCUMENT_PERSON_REFERENCES = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "personreferences");
        
    PageModeMenuCommand COMMAND_DOCUMENT_DECISION = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "decision");
        
    PageModeMenuCommand COMMAND_DOCUMENT_ATTACHMENTS = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "attachments");
}

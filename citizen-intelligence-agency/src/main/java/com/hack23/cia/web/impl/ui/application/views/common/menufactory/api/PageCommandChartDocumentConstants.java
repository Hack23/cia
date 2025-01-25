package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public interface PageCommandChartDocumentConstants {
    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentactivity");
        
    PageModeMenuCommand COMMAND_DOCUMENT_DETAILS = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentdetails");
        
    PageModeMenuCommand COMMAND_DOCUMENT_DATA = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentdata");
        
    PageModeMenuCommand COMMAND_DOCUMENT_REFERENCES = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "documentreferences");
        
    PageModeMenuCommand COMMAND_DOCUMENT_PERSON_REFERENCES = new PageModeMenuCommand(
        UserViews.DOCUMENT_VIEW_NAME, "personreferences");
}

package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
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

    /** The command document activity. */
    PageModeMenuCommand COMMAND_DOCUMENT_ACTIVITY = new PageModeMenuCommand(
            UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTACTIVITY.toString());

        /** The command document person references. */
        PageModeMenuCommand COMMAND_DOCUMENT_PERSON_REFERENCES = new PageModeMenuCommand(
            UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.PERSONREFERENCES.toString());

        /** The command document decision. */
        PageModeMenuCommand COMMAND_DOCUMENT_DECISION = new PageModeMenuCommand(
            UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTDECISION.toString());

        /** The command document attachments. */
        PageModeMenuCommand COMMAND_DOCUMENT_ATTACHMENTS = new PageModeMenuCommand(
            UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTATTACHMENTS.toString());


            /** The command document details. */
            PageModeMenuCommand COMMAND_DOCUMENT_DETAILS = new PageModeMenuCommand(
                UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTDETAILS.toString());

            /** The command document data. */
            PageModeMenuCommand COMMAND_DOCUMENT_DATA = new PageModeMenuCommand(
                UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTDATA.toString());

            /** The command document references. */
            PageModeMenuCommand COMMAND_DOCUMENT_REFERENCES = new PageModeMenuCommand(
                UserViews.DOCUMENT_VIEW_NAME, DocumentPageMode.DOCUMENTREFERENCES.toString());


            /** The command documents overview. */
            PageModeMenuCommand COMMAND_DOCUMENTS_OVERVIEW = new PageModeMenuCommand(
                    UserViews.DOCUMENTS_VIEW_NAME, PageMode.OVERVIEW);

}

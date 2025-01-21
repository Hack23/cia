package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

/**
 * The Interface PoliticianDocumentConstants.
 */
public interface PoliticianDocumentConstants {

    /** The column order. */
    String[] COLUMN_ORDER = { "rm", "madePublicDate", "documentType", "subType",
            "title", "subTitle", "referenceName", "partyShortCode", "personReferenceId", "roleDescription",
            "org", "id", "docId", "tempLabel", "label", "numberValue", "orderNumber", "status" };

    /** The hide columns. */
    String[] HIDE_COLUMNS = { "id", "partyShortCode", "personReferenceId",
            "numberValue", "orderNumber", "tempLabel", "referenceName", "docId", "label", "roleDescription" };

    /** The document grid name. */
    String DOCUMENT_GRID_NAME = "Documents";
}

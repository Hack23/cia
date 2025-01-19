package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

public interface PoliticianDocumentConstants {
    String[] COLUMN_ORDER = { "rm", "madePublicDate", "documentType", "subType",
            "title", "subTitle", "referenceName", "partyShortCode", "personReferenceId", "roleDescription", 
            "org", "id", "docId", "tempLabel", "label", "numberValue", "orderNumber", "status" };
    String[] HIDE_COLUMNS = { "id", "partyShortCode", "personReferenceId",
            "numberValue", "orderNumber", "tempLabel", "referenceName", "docId", "label", "roleDescription" };
    String DOCUMENT_GRID_NAME = "Documents";
}

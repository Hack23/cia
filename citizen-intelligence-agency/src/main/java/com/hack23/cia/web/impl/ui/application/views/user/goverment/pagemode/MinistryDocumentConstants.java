package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

public interface MinistryDocumentConstants {
    String DOCUMENTS = "Documents";
    String DOC_ID = "docId";
    
    String[] COLUMN_ORDER = { "id", "docId", "personReferenceId", "roleDescription",
            "org", "label", "rm", "madePublicDate", "numberValue", "title", "subTitle", 
            "tempLabel", "orderNumber", "documentType", "subType", "status", 
            "partyShortCode", "referenceName" };

    String[] HIDE_COLUMNS = { "id", "numberValue", "orderNumber", "tempLabel",
            "personReferenceId", "org", "roleDescription", "label", "subTitle", "docId" };
}


package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

public interface MinistryGridConstants {
    // Grid Names
    String CURRENT_MEMBERS = "Current Members";
    String MEMBER_HISTORY = "Member History";
    String DOCUMENTS = "Documents";

    // Member Column Configuration
    String[] MEMBER_COLUMN_ORDER = { "roleCode", "roleId", "personId", "firstName",
            "lastName", "party", "active", "totalDaysServed", "detail", "fromDate", "toDate" };
    String[] MEMBER_HIDE_COLUMNS = { "roleId", "personId", "detail", "active" };

    // Document Column Configuration
    String[] DOCUMENT_COLUMN_ORDER = { "id", "docId", "personReferenceId", "roleDescription",
            "org", "label", "rm", "madePublicDate", "numberValue", "title", "subTitle", 
            "tempLabel", "orderNumber", "documentType", "subType", "status", 
            "partyShortCode", "referenceName" };
    String[] DOCUMENT_HIDE_COLUMNS = { "id", "numberValue", "orderNumber", "tempLabel",
            "personReferenceId", "org", "roleDescription", "label", "subTitle", "docId" };
}

package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

/**
 * The Interface MinistryGridConstants.
 */
public interface MinistryGridConstants {

    /** The current members. */
    // Grid Names
    String CURRENT_MEMBERS = "Current Members";

    /** The member history. */
    String MEMBER_HISTORY = "Member History";

    /** The documents. */
    String DOCUMENTS = "Documents";

    /** The member column order. */
    // Member Column Configuration
    String[] MEMBER_COLUMN_ORDER = { "roleCode", "roleId", "personId", "firstName",
            "lastName", "party", "active", "totalDaysServed", "detail", "fromDate", "toDate" };

    /** The member hide columns. */
    String[] MEMBER_HIDE_COLUMNS = { "roleId", "personId", "detail", "active" };

    /** The document column order. */
    // Document Column Configuration
    String[] DOCUMENT_COLUMN_ORDER = { "id", "docId", "personReferenceId", "roleDescription",
            "org", "label", "rm", "madePublicDate", "numberValue", "title", "subTitle",
            "tempLabel", "orderNumber", "documentType", "subType", "status",
            "partyShortCode", "referenceName" };

    /** The document hide columns. */
    String[] DOCUMENT_HIDE_COLUMNS = { "id", "numberValue", "orderNumber", "tempLabel",
            "personReferenceId", "org", "roleDescription", "label", "subTitle", "docId" };
}
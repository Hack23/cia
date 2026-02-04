package com.hack23.cia.web.impl.ui.application.views.common.constants;

/**
 * The Interface CommitteeGridConstants.
 */
public interface CommitteeGridConstants {

    /** The member history grid name. */
    // Member History Grid
    String MEMBER_HISTORY_GRID_NAME = "Member History";

    /** The member history column order. */
    String[] MEMBER_HISTORY_COLUMN_ORDER = { "roleCode", "roleId", "personId", "firstName",
            "lastName", "party", "active", "totalDaysServed", "detail", "fromDate", "toDate" };

    /** The member history hidden columns. */
    String[] MEMBER_HISTORY_HIDDEN_COLUMNS = { "roleId", "personId", "detail" };

    /** The current members grid name. */
    // Current Members Grid
    String CURRENT_MEMBERS_GRID_NAME = "Current Members";

    /** The current members hidden columns. */
    String[] CURRENT_MEMBERS_HIDDEN_COLUMNS = { "roleId", "personId", "detail", "active" };

    /** The document history grid name. */
    // Document History Grid
    String DOCUMENT_HISTORY_GRID_NAME = "Documents";

    /** The document history column order. */
    String[] DOCUMENT_HISTORY_COLUMN_ORDER = { "rm", "madePublicDate", "id", "docId",
            "personReferenceId", "roleDescription", "title", "subTitle", "documentType", "subType",
            "org", "label", "numberValue", "status", "tempLabel", "orderNumber", "referenceName",
            "partyShortCode" };

    /** The document history hidden columns. */
    String[] DOCUMENT_HISTORY_HIDDEN_COLUMNS = { "id", "numberValue", "orderNumber", "tempLabel",
            "personReferenceId", "org", "docId", "label", "roleDescription" };

    /** The decision summary grid name. */
    // Decision Summary Grid
    String DECISION_SUMMARY_GRID_NAME = "Decision Summary";

    /** The decision summary column order. */
    String[] DECISION_SUMMARY_COLUMN_ORDER = { "createdDate", "publicDate", "committeeReport",
            "embeddedId.hangarId", "embeddedId.id", "embeddedId.issueNummer", "rm", "decisionType",
            "winner", "title", "header", "endNumber", "org", "committeeProposalUrlXml", "ballotId",
            "againstProposalParties", "againstProposalNumber" };

    /** The decision summary hidden columns. */
    String[] DECISION_SUMMARY_HIDDEN_COLUMNS = { "embeddedId", "embeddedId.hangarId", "embeddedId.id",
            "endNumber", "org", "committeeProposalUrlXml", "ballotId", "againstProposalParties",
            "againstProposalNumber", "createdDate" };
}

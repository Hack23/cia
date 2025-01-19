package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

public interface CommitteeGridConstants {
    // Member History Grid
    String MEMBER_HISTORY_GRID_NAME = "Member History";
    String[] MEMBER_HISTORY_COLUMN_ORDER = { "roleCode", "roleId", "personId", "firstName",
            "lastName", "party", "active", "totalDaysServed", "detail", "fromDate", "toDate" };
    String[] MEMBER_HISTORY_HIDDEN_COLUMNS = { "roleId", "personId", "detail" };

    // Current Members Grid
    String CURRENT_MEMBERS_GRID_NAME = "Current Members";
    String[] CURRENT_MEMBERS_HIDDEN_COLUMNS = { "roleId", "personId", "detail", "active" };

    // Document History Grid
    String DOCUMENT_HISTORY_GRID_NAME = "Documents";
    String[] DOCUMENT_HISTORY_COLUMN_ORDER = { "rm", "madePublicDate", "id", "docId",
            "personReferenceId", "roleDescription", "title", "subTitle", "documentType", "subType", 
            "org", "label", "numberValue", "status", "tempLabel", "orderNumber", "referenceName", 
            "partyShortCode" };
    String[] DOCUMENT_HISTORY_HIDDEN_COLUMNS = { "id", "numberValue", "orderNumber", "tempLabel",
            "personReferenceId", "org", "docId", "label", "roleDescription" };

    // Decision Summary Grid
    String DECISION_SUMMARY_GRID_NAME = "Decision Summary";
    String[] DECISION_SUMMARY_COLUMN_ORDER = { "createdDate", "publicDate", "committeeReport",
            "embeddedId.hangarId", "embeddedId.id", "embeddedId.issueNummer", "rm", "decisionType",
            "winner", "title", "header", "endNumber", "org", "committeeProposalUrlXml", "ballotId",
            "againstProposalParties", "againstProposalNumber" };
    String[] DECISION_SUMMARY_HIDDEN_COLUMNS = { "embeddedId", "embeddedId.hangarId", "embeddedId.id",
            "endNumber", "org", "committeeProposalUrlXml", "ballotId", "againstProposalParties",
            "againstProposalNumber", "createdDate" };
}

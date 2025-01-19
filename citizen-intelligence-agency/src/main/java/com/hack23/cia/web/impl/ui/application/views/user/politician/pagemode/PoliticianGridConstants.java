package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

public interface PoliticianGridConstants {
    // Document History Grid
    String[] DOC_HISTORY_COLUMN_ORDER = { "rm", "madePublicDate", "documentType", "subType",
            "title", "subTitle", "referenceName", "partyShortCode", "personReferenceId", "roleDescription", 
            "org", "id", "docId", "tempLabel", "label", "numberValue", "orderNumber", "status" };
    String[] DOC_HISTORY_HIDDEN_COLUMNS = { "id", "partyShortCode", "personReferenceId",
            "numberValue", "orderNumber", "tempLabel", "referenceName", "docId", "label", "roleDescription" };

    // Ballot Decision Grid
    String[] BALLOT_DECISION_COLUMN_ORDER = { "voteDate", "rm", "org", "committeeReport", "title",
            "subTitle", "winner", "embeddedId.concern", "embeddedId.issue", "vote", "won", "rebel", 
            "noWinner", "approved", "partyApproved", "againstProposalNumber", "againstProposalParties", 
            "totalVotes", "partyTotalVotes", "yesVotes", "partyYesVotes", "noVotes", "partyNoVotes", 
            "partyAbstainVotes", "abstainVotes", "partyAbsentVotes", "absentVotes", "bornYear", 
            "partyAvgBornYear", "avgBornYear", "ballotType", "decisionType", "ballotId" };
    String[] BALLOT_DECISION_HIDDEN_COLUMNS = { "label", "endNumber", "publicDate", "createdDate",
            "embeddedId", "partyNoWinner", "partyPercentageYes", "partyPercentageNo", 
            "partyPercentageAbsent", "partyPercentageAbstain", "percentageYes", "percentageNo", 
            "percentageAbsent", "percentageAbstain", "firstName", "lastName", "party", "ballotId", 
            "decisionType", "ballotType", "againstProposalNumber" };
}

package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

public interface CommitteeBallotConstants {
    String BALLOT_ID = "ballotId";
    String COMMITTEE_BALLOT_DECISION_SUMMARY = "Committee Ballot Decision Summary";
    
    String[] BALLOT_COLUMN_ORDER = { "voteDate", "embeddedId.concern", "embeddedId.id",
            "committeeReport", "embeddedId.issue", "rm", "title", "subTitle", "endNumber", "org", "createdDate",
            "publicDate", "ballotId", "decisionType", "againstProposalParties", "againstProposalNumber", "winner",
            "ballotType", "label", "avgBornYear", "totalVotes", "yesVotes", "noVotes", "abstainVotes", "absentVotes",
            "approved", "noWinner", "percentageYes", "percentageNo", "percentageAbsent", "percentageAbstain",
            "percentageMale" };

    String[] BALLOT_HIDE_COLUMNS = { "embeddedId", "embeddedId.id", "endNumber", "org",
            "createdDate", "publicDate", "ballotId", "decisionType", "label", "againstProposalNumber", "avgBornYear",
            "percentageMale", "approved", "noWinner", "ballotType", "percentageYes", "percentageNo", "percentageAbsent",
            "percentageAbstain" };

    String[] BALLOT_NESTED_PROPERTIES = { "embeddedId.concern", "embeddedId.issue",
            "embeddedId.id" };
}

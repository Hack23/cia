package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

public interface PoliticianBallotConstants {
    String BALLOT_ID = "ballotId";
    String[] COLUMN_ORDER = { "voteDate", "rm", "org", "committeeReport", "title",
            "subTitle", "winner", "embeddedId.concern", "embeddedId.issue", "vote", "won", "rebel", "noWinner",
            "approved", "partyApproved", "againstProposalNumber", "againstProposalParties", "totalVotes",
            "partyTotalVotes", "yesVotes", "partyYesVotes", "noVotes", "partyNoVotes", "partyAbstainVotes",
            "abstainVotes", "partyAbsentVotes", "absentVotes", "bornYear", "partyAvgBornYear", "avgBornYear",
            "ballotType", "decisionType", "ballotId" };
    String[] HIDE_COLUMNS = { "label", "endNumber", "publicDate", "createdDate",
            "embeddedId", "partyNoWinner", "partyPercentageYes", "partyPercentageNo", "partyPercentageAbsent",
            "partyPercentageAbstain", "percentageYes", "percentageNo", "percentageAbsent", "percentageAbstain",
            "firstName", "lastName", "party", "ballotId", "decisionType", "ballotType", "againstProposalNumber" };
    String[] NESTED_PROPERTIES = { "embeddedId.concern", "embeddedId.issue" };
    String COMMITTEE_BALLOT_DECISION_SUMMARY = "Committee Ballot Decision Summary";
}

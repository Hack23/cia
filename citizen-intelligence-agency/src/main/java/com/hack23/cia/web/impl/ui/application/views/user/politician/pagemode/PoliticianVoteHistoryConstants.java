package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

/**
 * The Interface PoliticianVoteHistoryConstants.
 */
public interface PoliticianVoteHistoryConstants {

    /** The embedded id ballot id. */
    String EMBEDDED_ID_BALLOT_ID = "embeddedId.ballotId";

    /** The column order. */
    String[] COLUMN_ORDER = { "voteDate", "rm", "label", "embeddedId.concern",
            "embeddedId.issue", "vote", "won", "partyWon", "rebel", "noWinner", "approved",
            "partyApproved", "totalVotes", "partyTotalVotes", "yesVotes", "partyYesVotes",
            "noVotes", "partyNoVotes", "partyAbstainVotes", "abstainVotes", "partyAbsentVotes",
            "absentVotes", "bornYear", "partyAvgBornYear", "avgBornYear", "gender",
            "partyPercentageMale", "percentageMale", "ballotType", "embeddedId.ballotId" };

    /** The hide columns. */
    String[] HIDE_COLUMNS = { "embeddedId", "partyNoWinner", "partyPercentageYes",
            "partyPercentageNo", "partyPercentageAbsent", "partyPercentageAbstain",
            "percentageYes", "percentageNo", "percentageAbsent", "percentageAbstain",
            "firstName", "lastName", "party", "embeddedId.ballotId", "ballotType" };

    /** The nested properties. */
    String[] NESTED_PROPERTIES = { "embeddedId.ballotId", "embeddedId.concern", "embeddedId.issue" };

    /** Analytical context descriptions */
    String VOTE_PATTERN_ANALYSIS = "Voting pattern distribution metrics";
    String PARTY_ALIGNMENT_METRICS = "Party alignment statistical analysis";
    String PARTICIPATION_ANALYTICS = "Engagement distribution indicators";
    String TEMPORAL_VOTING_PATTERNS = "Longitudinal voting pattern analysis";
    String DECISION_IMPACT_METRICS = "Legislative impact assessment metrics";
}

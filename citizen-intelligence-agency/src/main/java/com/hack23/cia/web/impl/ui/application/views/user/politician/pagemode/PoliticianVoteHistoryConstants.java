package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

public interface PoliticianVoteHistoryConstants {
    String EMBEDDED_ID_BALLOT_ID = "embeddedId.ballotId";
    
    String[] COLUMN_ORDER = { "voteDate", "rm", "label", "embeddedId.concern",
            "embeddedId.issue", "vote", "won", "partyWon", "rebel", "noWinner", "approved", 
            "partyApproved", "totalVotes", "partyTotalVotes", "yesVotes", "partyYesVotes", 
            "noVotes", "partyNoVotes", "partyAbstainVotes", "abstainVotes", "partyAbsentVotes", 
            "absentVotes", "bornYear", "partyAvgBornYear", "avgBornYear", "gender", 
            "partyPercentageMale", "percentageMale", "ballotType", "embeddedId.ballotId" };
            
    String[] HIDE_COLUMNS = { "embeddedId", "partyNoWinner", "partyPercentageYes",
            "partyPercentageNo", "partyPercentageAbsent", "partyPercentageAbstain", 
            "percentageYes", "percentageNo", "percentageAbsent", "percentageAbstain", 
            "firstName", "lastName", "party", "embeddedId.ballotId", "ballotType" };
            
    String[] NESTED_PROPERTIES = { "embeddedId.ballotId", "embeddedId.concern", "embeddedId.issue" };
}

package com.hack23.cia.web.impl.ui.application.views.user.ballot.pagemode;

/**
 * Constants for the ballot view pages.
 */
public interface BallotViewConstants {
    // Overview page constants
    String OVERVIEW_MAIN_TITLE_PREFIX = "Ballot Overview ";
    String OVERVIEW_PAGE_DESCRIPTION = "Explore and analyze ballot results and voting statistics.";
    String OVERVIEW_CARD_BALLOT_INFO = "Ballot Information";
    String OVERVIEW_CARD_BALLOT_PROFILE = "Ballot Profile";
    String OVERVIEW_CARD_VOTING_STATS = "Voting Statistics";
    
    // Grid constants
    String GRID_PARTY_BALLOT_SUMMARY = "Party Ballot Summary";
    String GRID_EMBEDDED_ID_PARTY = "embeddedId.party";
    
    // Field labels
    String FIELD_VOTE_DATE = "Vote Date:";
    String FIELD_TITLE = "Title:";
    String FIELD_SUBTITLE = "Subtitle:";  // Fixed: Added missing constant
    String FIELD_DECISION_TYPE = "Decision Type:";
    String FIELD_CONCERN = "Concern:";
    String FIELD_BALLOT_TYPE = "Ballot Type:";
    String FIELD_WINNER = "Winner:";
    String FIELD_APPROVED = "Approved:";
    String FIELD_BALLOT_ID = "Ballot ID:";
    String FIELD_RM = "RM:";
    String FIELD_ISSUE = "Issue:";
    String FIELD_LABEL = "Label:";
    String FIELD_TOTAL_VOTES = "Total Votes:";
    String FIELD_YES_VOTES = "Yes Votes:";
    String FIELD_NO_VOTES = "No Votes:";
    String FIELD_ABSTAIN_VOTES = "Abstain Votes:";
    String FIELD_ABSENT_VOTES = "Absent Votes:";
    
    // Charts page constants
    String CHARTS_TITLE_PREFIX = "Ballot Charts : ";
    String CHARTS_SUBTITLE = "Ballot Trends and Visualizations";
    String CHARTS_DESCRIPTION = "Provides insights into election trends by visualizing ballot data, assisting in strategic decision-making and voter engagement analysis.";
}

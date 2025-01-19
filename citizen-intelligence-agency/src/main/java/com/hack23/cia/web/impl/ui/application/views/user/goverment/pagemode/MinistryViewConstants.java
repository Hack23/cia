package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

/**
 * Constants for the ministry view pages.
 */
public interface MinistryViewConstants extends 
    MinistryPageTitleConstants,
    MinistryDescriptionConstants,
    MinistryGridConstants,
    MinistrySectionConstants,
    MinistryIconConstants,
    MinistryLayoutConstants,
    MinistryStatisticsConstants {
    
    // Page Titles
    String OVERVIEW_TITLE = "Ministry Overview";
    String ROLE_GHANT_TITLE = "Ministry Role Ghant";
    String DOCUMENT_HISTORY_TITLE = "Document History";
    String CURRENT_MEMBERS_TITLE = "Ministry Members";
    String EXPENDITURE_TITLE = "Expenditure Analysis";
    String MINISTRY_SERVICE_STATS_TITLE = "Service Statistics";
    String MINISTRY_DOCUMENT_STATS_TITLE = "Document Statistics";
    
    // Subtitles
    String ROLE_GHANT_SUBTITLE = "Role Timeline";
    String DOCUMENT_HISTORY_SUBTITLE = "Document History";
    String CURRENT_MEMBERS_SUBTITLE = "Current Members";
    String EXPENDITURE_SUBTITLE = "Government Bodies Expenditure";
    
    // Descriptions
    String ROLE_GHANT_DESC = "Visual representation of ministry roles over time.";
    String DOCUMENT_HISTORY_DESC = "Historical record of ministry documents.";
    String CURRENT_MEMBERS_DESC = "Current members serving in the ministry.";
    String EXPENDITURE_DESC = "Analysis of ministry expenditures.";
    
    // Actions
    String VISIT_MINISTRY = "VISIT_MINISTRY_VIEW";
    String MINISTRY_ID = "nameId";
    
    // Grid Config
    int DEFAULT_PAGE_SIZE = 40;
    boolean HIDE_DETAILS = true;
    
    // Labels
    String MINISTRY_CURRENT_MEMBERS_LABEL = "Current Members:";
    String MINISTRY_ACTIVITY_LEVEL_LABEL = "Activity Level:";
    String MINISTRY_LAST_ASSIGNMENT_LABEL = "Last Assignment:";
    String MINISTRY_TOTAL_DAYS_SERVED_LABEL = "Total Days Served:";
    String MINISTRY_DOCUMENTS_LAST_YEAR_LABEL = "Documents Last Year:";
    String MINISTRY_AVG_DOCUMENTS_MEMBER_LABEL = "Avg Documents/Member:";
    String MINISTRY_TOTAL_PROPOSITIONS_LABEL = "Total Propositions:";
    String MINISTRY_GOVERNMENT_BILLS_LABEL = "Government Bills:";
}

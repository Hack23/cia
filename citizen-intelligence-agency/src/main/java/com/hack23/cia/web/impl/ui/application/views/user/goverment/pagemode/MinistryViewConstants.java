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
}

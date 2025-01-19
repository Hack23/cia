package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

public interface MinistryStatisticsConstants {
    // Service Statistics Labels
    String TOTAL_ASSIGNMENTS = "Total Assignments:";
    String FIRST_ASSIGNMENT = "First Assignment:";
    String LAST_ASSIGNMENT = "Last Assignment:";
    String TOTAL_DAYS = "Total Days Served:";
    String ACTIVITY_LEVEL = "Activity Level:";
    
    // Service Statistics Descriptions
    String ASSIGNMENTS_DESC = "Total number of ministry assignments";
    String FIRST_DATE_DESC = "Date of first ministry assignment";
    String LAST_DATE_DESC = "Date of most recent ministry assignment";
    String TOTAL_DAYS_DESC = "Total days served in ministry";
    String ACTIVITY_LEVEL_DESC = "Current activity level in ministry";
    
    // Document Statistics Labels
    String TOTAL_DOCUMENTS = "Total Documents:";
    String DOCUMENTS_LAST_YEAR = "Documents Last Year:";
    String AVG_DOCUMENTS = "Avg Documents/Member:";
    String TOTAL_PROPOSITIONS = "Total Propositions:";
    String GOVERNMENT_BILLS = "Government Bills:";
    
    // Document Statistics Descriptions
    String TOTAL_DOCS_DESC = "Total number of ministry documents";
    String DOCS_LAST_YEAR_DESC = "Documents produced in the last year";
    // Alias the constants below to match references in code:
    String TOTAL_DOCUMENTS_DESC = TOTAL_DOCS_DESC;
    String DOCUMENTS_LAST_YEAR_DESC = DOCS_LAST_YEAR_DESC;
    String AVG_DOCS_DESC = "Average documents per ministry member";
    String TOTAL_PROPOSITIONS_DESC = "Total number of ministry propositions";
    String GOVERNMENT_BILLS_DESC = "Total number of government bills";
    
    // Member Statistics Labels
    String CURRENT_MEMBERS = "Current Members:";
    String TOTAL_MEMBERS = "Total Members:";
    String ACTIVE_MEMBERS = "Active Members:";
    
    // Member Statistics Descriptions
    String CURRENT_MEMBERS_DESC = "Number of current ministry members";
    String TOTAL_MEMBERS_DESC = "Total number of ministry members historically";
    String ACTIVE_MEMBERS_DESC = "Number of currently active ministry members";
}

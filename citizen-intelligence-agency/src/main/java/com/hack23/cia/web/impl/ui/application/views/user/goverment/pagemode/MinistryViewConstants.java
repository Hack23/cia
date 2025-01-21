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

    /** The overview title. */
    // Page Titles
    String OVERVIEW_TITLE = "Ministry Overview";

    /** The role ghant title. */
    String ROLE_GHANT_TITLE = "Ministry Role Ghant";

    /** The document history title. */
    String DOCUMENT_HISTORY_TITLE = "Document History";

    /** The current members title. */
    String CURRENT_MEMBERS_TITLE = "Ministry Members";

    /** The expenditure title. */
    String EXPENDITURE_TITLE = "Expenditure Analysis";

    /** The ministry service stats title. */
    String MINISTRY_SERVICE_STATS_TITLE = "Service Statistics";

    /** The ministry document stats title. */
    String MINISTRY_DOCUMENT_STATS_TITLE = "Document Statistics";

    /** The role ghant subtitle. */
    // Subtitles
    String ROLE_GHANT_SUBTITLE = "Role Timeline";

    /** The document history subtitle. */
    String DOCUMENT_HISTORY_SUBTITLE = "Document History";

    /** The current members subtitle. */
    String CURRENT_MEMBERS_SUBTITLE = "Current Members";

    /** The expenditure subtitle. */
    String EXPENDITURE_SUBTITLE = "Government Bodies Expenditure";

    /** The role ghant desc. */
    // Descriptions
    String ROLE_GHANT_DESC = "Visual representation of ministry roles over time.";

    /** The document history desc. */
    String DOCUMENT_HISTORY_DESC = "Historical record of ministry documents.";

    /** The current members desc. */
    String CURRENT_MEMBERS_DESC = "Current members serving in the ministry.";

    /** The expenditure desc. */
    String EXPENDITURE_DESC = "Analysis of ministry expenditures.";

    /** The visit ministry. */
    // Actions
    String VISIT_MINISTRY = "VISIT_MINISTRY_VIEW";

    /** The ministry id. */
    String MINISTRY_ID = "nameId";

    /** The default page size. */
    // Grid Config
    int DEFAULT_PAGE_SIZE = 40;

    /** The hide details. */
    boolean HIDE_DETAILS = true;

    /** The ministry current members label. */
    // Labels
    String MINISTRY_CURRENT_MEMBERS_LABEL = "Current Members:";

    /** The ministry activity level label. */
    String MINISTRY_ACTIVITY_LEVEL_LABEL = "Activity Level:";

    /** The ministry last assignment label. */
    String MINISTRY_LAST_ASSIGNMENT_LABEL = "Last Assignment:";

    /** The ministry total days served label. */
    String MINISTRY_TOTAL_DAYS_SERVED_LABEL = "Total Days Served:";

    /** The ministry documents last year label. */
    String MINISTRY_DOCUMENTS_LAST_YEAR_LABEL = "Documents Last Year:";

    /** The ministry avg documents member label. */
    String MINISTRY_AVG_DOCUMENTS_MEMBER_LABEL = "Avg Documents/Member:";

    /** The ministry total propositions label. */
    String MINISTRY_TOTAL_PROPOSITIONS_LABEL = "Total Propositions:";

    /** The ministry government bills label. */
    String MINISTRY_GOVERNMENT_BILLS_LABEL = "Government Bills:";
}

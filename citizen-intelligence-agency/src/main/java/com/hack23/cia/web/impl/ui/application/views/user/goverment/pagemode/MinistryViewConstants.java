package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

/**
 * Constants for the ministry view pages.
 */
public interface MinistryViewConstants extends
    MinistryPageTitleConstants,
    MinistryDescriptionConstants,
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
    String MINISTRY_SERVICE_STATS_TITLE = "Government Bodies Income ";

    /** The ministry document stats title. */
    String MINISTRY_DOCUMENT_STATS_TITLE = "Document Statistics";

    /** The page visit title. */
    String PAGEVISIT_TITLE = "Ministry Page Visit History";

    /** The all ministries headcount title. */
    String ALL_MINISTRIES_HEADCOUNT_TITLE = "Ministry Headcount Comparison";

    /** The role ghant subtitle. */
    // Subtitles
    String ROLE_GHANT_SUBTITLE = "Role Timeline";

    /** The document history subtitle. */
    String DOCUMENT_HISTORY_SUBTITLE = "Document History";

    /** The current members subtitle. */
    String CURRENT_MEMBERS_SUBTITLE = "Current Members";

    /** The expenditure subtitle. */
    String EXPENDITURE_SUBTITLE = "Government Bodies Expenditure";

    /** The headcount subtitle. */
    String HEADCOUNT_SUBTITLE = "Government Bodies Headcount";

    /** The income subtitle. */
    String INCOME_SUBTITLE = "Government Bodies Income";

    /** The page visit subtitle. */
    String PAGEVISIT_SUBTITLE = "Page Visit History";

    /** The all ministries headcount subtitle. */
    String ALL_MINISTRIES_HEADCOUNT_SUBTITLE = "Comparative Headcount";

    /** The role ghant desc. */
    // Descriptions
    String ROLE_GHANT_DESC = "Visual representation of ministry roles over time.";

    /** The document history desc. */
    String DOCUMENT_HISTORY_DESC = "Historical record of ministry documents.";

    /** The current members desc. */
    String CURRENT_MEMBERS_DESC = "Current members serving in the ministry.";

    /** The expenditure desc. */
    String EXPENDITURE_DESC = "Analysis of ministry expenditures.";

    /** The headcount desc. */
    String HEADCOUNT_DESC = "Provides detailed headcount data for government bodies under ministries.";

    /** The income desc. */
    String INCOME_DESC = "Provides detailed income data for government bodies under ministries.";

    /** The page visit desc. */
    String PAGEVISIT_DESC = "History of page visits for this ministry";

    /** The all ministries headcount desc. */
    String ALL_MINISTRIES_HEADCOUNT_DESC = "Comparative analysis of ministry staff numbers";

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

	/** The income title. */
	String INCOME_TITLE = "Government Bodies Income ";

	/** The headcount title. */
	String HEADCOUNT_TITLE = "Ministry Government Bodies Headcount ";
}

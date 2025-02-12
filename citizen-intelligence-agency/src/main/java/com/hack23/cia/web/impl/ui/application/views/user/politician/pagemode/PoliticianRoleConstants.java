package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

/**
 * The Interface PoliticianRoleConstants.
 */
public interface PoliticianRoleConstants {

    /** The assignments. */
    // Role List Grid
    String ASSIGNMENTS = "Assignments";

    /** The role column order. */
    String[] ROLE_COLUMN_ORDER = { "roleCode", "assignmentType", "status", "detail",
            "orgCode", "fromDate", "toDate" };

    /** The role hide columns. */
    String[] ROLE_HIDE_COLUMNS = { "hjid", "intressentId", "orderNumber", "orgCode" };

    /** The committee experience. */
    // Experience Labels with analytical focus
    String COMMITTEE_EXPERIENCE = "Committee Effectiveness Metrics:";

    /** The eu experience. */
    String EU_EXPERIENCE = "EU Engagement Analytics:";

    /** The government experience. */
    String GOVERNMENT_EXPERIENCE = "Executive Performance Metrics:";

    /** The parliament experience. */
    String PARLIAMENT_EXPERIENCE = "Legislative Impact Analysis:";

    /** The party experience. */
    String PARTY_EXPERIENCE = "Party Influence Metrics:";

    /** The speaker experience. */
    String SPEAKER_EXPERIENCE = "Parliamentary Leadership Analytics:";

    /** The total assignments. */
    String TOTAL_ASSIGNMENTS = "Cumulative Role Distribution:";

    /** The section role influence. */
    // Section Headers with metrics focus
    String SECTION_ROLE_INFLUENCE = "Position Impact Analytics";

    /** The section experience. */
    String SECTION_EXPERIENCE = "Competency Distribution Metrics";

    /** The section performance. */
    String SECTION_PERFORMANCE = "Legislative Performance Analysis";

    /** The section legislative. */
    String SECTION_LEGISLATIVE = "Policy Impact Metrics";

    /** The section party alignment. */
    String SECTION_PARTY_ALIGNMENT = "Political Alignment Analytics";
}

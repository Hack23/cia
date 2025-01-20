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
    // Experience Labels
    String COMMITTEE_EXPERIENCE = "Committee experience:";

    /** The eu experience. */
    String EU_EXPERIENCE = "EU experience:";

    /** The government experience. */
    String GOVERNMENT_EXPERIENCE = "Government experience:";

    /** The parliament experience. */
    String PARLIAMENT_EXPERIENCE = "Parliament experience:";

    /** The party experience. */
    String PARTY_EXPERIENCE = "Party experience:";

    /** The speaker experience. */
    String SPEAKER_EXPERIENCE = "Speaker experience:";

    /** The total assignments. */
    String TOTAL_ASSIGNMENTS = "Total Assignments:";

    /** The section role influence. */
    // Section Headers
    String SECTION_ROLE_INFLUENCE = "Political Role & Influence";

    /** The section experience. */
    String SECTION_EXPERIENCE = "Experience & Expertise";

    /** The section performance. */
    String SECTION_PERFORMANCE = "Parliamentary Performance";

    /** The section legislative. */
    String SECTION_LEGISLATIVE = "Legislative Impact";

    /** The section party alignment. */
    String SECTION_PARTY_ALIGNMENT = "Party Alignment & Cooperation";
}

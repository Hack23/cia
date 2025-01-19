package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

public interface PoliticianRoleConstants {
    // Role List Grid
    String ASSIGNMENTS = "Assignments";
    String[] ROLE_COLUMN_ORDER = { "roleCode", "assignmentType", "status", "detail",
            "orgCode", "fromDate", "toDate" };
    String[] ROLE_HIDE_COLUMNS = { "hjid", "intressentId", "orderNumber", "orgCode" };

    // Experience Labels
    String COMMITTEE_EXPERIENCE = "Committee experience:";
    String EU_EXPERIENCE = "EU experience:";
    String GOVERNMENT_EXPERIENCE = "Government experience:";
    String PARLIAMENT_EXPERIENCE = "Parliament experience:";
    String PARTY_EXPERIENCE = "Party experience:";
    String SPEAKER_EXPERIENCE = "Speaker experience:";
    String TOTAL_ASSIGNMENTS = "Total Assignments:";

    // Section Headers
    String SECTION_ROLE_INFLUENCE = "Political Role & Influence";
    String SECTION_EXPERIENCE = "Experience & Expertise";
    String SECTION_PERFORMANCE = "Parliamentary Performance";
    String SECTION_LEGISLATIVE = "Legislative Impact";
    String SECTION_PARTY_ALIGNMENT = "Party Alignment & Cooperation";
}

package com.hack23.cia.web.impl.ui.application.views.user.politicianranking.pagemode;

/**
 * The Interface PoliticianRankingGridConstants.
 */
public interface PoliticianRankingGridConstants {

    /** The politicians. */
    String POLITICIANS = "Representative Performance Analytics";

    /** The column groups. */
    // Enhanced analytical column groups
    String[] COLUMN_GROUPS = {
        "Representative Metrics",
        "Performance Indicators",
        "Legislative Analytics",
        "Position Distribution",
        "Temporal Analysis"
    };

    /** The activity level desc. */
    // Enhanced column descriptions
    String ACTIVITY_LEVEL_DESC = "Quantitative engagement metrics";

    /** The collaboration desc. */
    String COLLABORATION_DESC = "Cross-party interaction patterns";

    /** The document metrics desc. */
    String DOCUMENT_METRICS_DESC = "Legislative output analysis";

    /** The role distribution desc. */
    String ROLE_DISTRIBUTION_DESC = "Position allocation metrics";

    /** The temporal metrics desc. */
    String TEMPORAL_METRICS_DESC = "Time-series performance indicators";

    /** The column order. */
    String[] COLUMN_ORDER = {
        // Basic Information
        "personId", "firstName", "lastName", "party", "gender",
        // Current Activity & Performance
        "documentsLastYear", "averageDocsPerYear", "docActivityLevel",
        "docActivityProfile", "collaborationPercentage",
        // Document Breakdown
        "totalDocuments", "individualMotions", "partyMotions",
        "committeeMotions", "multiPartyMotions",
        // Current Roles
        "currentAssignments", "currentMinistryAssignments",
        "currentCommitteeAssignments", "currentCommitteeLeadershipAssignments",
        "currentCommitteeChairAssignments", "currentCommitteeViceChairAllAssignments",
        // Historical Performance
        "documentYearsActive", "totalDaysServed", "totalCommitteeAssignments",
        "totalMinistryAssignments",
        // Dates for Context
        "firstAssignmentDate", "lastAssignmentDate", "firstDocumentDate",
        "lastDocumentDate"
    };

    /** The hide columns. */
    String[] HIDE_COLUMNS = {
        // Hidden IDs and Boolean Flags
        "personId", "active", "activeEu", "activeGovernment", "activeCommittee",
        "activeParliament", "activeParty", "activeSpeaker", "bornYear",
        // Hidden Detail Metrics
        "followUpMotions", "totalDaysServedParliament", "totalDaysServedCommittee",
        "totalDaysServedGovernment", "totalDaysServedEu", "totalDaysServedSpeaker",
        "totalDaysServedParty", "totalDaysServedCommitteeSubstitute",
        "totalDaysServedCommitteeLeadership",
        // Hidden Assignment Details
        "totalPartyAssignments", "totalSpeakerAssignments", "currentPartyAssignments",
        "currentSpeakerAssignments", "totalCommitteeSubstituteAssignments",
        "currentCommitteeSubstituteAssignments",
        // Hidden Committee Leadership Totals (show current only)
        "totalCommitteeChairAssignments", "totalCommitteeViceChairAllAssignments",
        // Hidden Technical Fields
        "documentTypes", "documentTypesString"
    };
}

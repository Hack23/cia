package com.hack23.cia.web.impl.ui.application.views.user.politicianranking.pagemode;

/**
 * The Interface PoliticianRankingGridConstants.
 */
public interface PoliticianRankingGridConstants {

    /** The politicians. */
    String POLITICIANS = "Representative Performance Analytics";
    
    // Enhanced analytical column groups
    String[] COLUMN_GROUPS = {
        "Representative Metrics",
        "Performance Indicators",
        "Legislative Analytics",
        "Position Distribution",
        "Temporal Analysis"
    };
    
    // Enhanced column descriptions
    String ACTIVITY_LEVEL_DESC = "Quantitative engagement metrics";
    String COLLABORATION_DESC = "Cross-party interaction patterns";
    String DOCUMENT_METRICS_DESC = "Legislative output analysis";
    String ROLE_DISTRIBUTION_DESC = "Position allocation metrics";
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
        // Hidden Technical Fields
        "documentTypes", "documentTypesString"
    };
}

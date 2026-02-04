package com.hack23.cia.web.impl.ui.application.views.common.constants;

/**
 * The Interface CommitteeRankingConstants.
 *
 * This interface defines constants used for committee ranking views and page modes
 * within the Citizen Intelligence Agency web application.
 *
 * Key constants:
 * - CR_OVERVIEW_TITLE_HEADER: Title header for the committee ranking overview.
 * - CR_OVERVIEW_TITLE: Title for the committee ranking overview.
 * - CR_OVERVIEW_DESCRIPTION: Description for the committee ranking overview.
 * - CR_GRID_TITLE_HEADER: Title header for the committee ranking data grid.
 * - CR_GRID_TITLE: Title for the committee ranking data grid.
 * - CR_GRID_DESCRIPTION: Description for the committee ranking data grid.
 * - CR_VISIT_TITLE_HEADER: Title header for the committee ranking visit history.
 * - CR_VISIT_TITLE: Title for the committee ranking visit history.
 * - CR_VISIT_DESCRIPTION: Description for the committee ranking visit history.
 * - CR_ALL_TITLE_HEADER: Title header for the all committees charts.
 * - CR_ALL_TITLE: Title for the all committees charts.
 * - CR_ALL_DESCRIPTION: Description for the all committees charts.
 * - CR_CURRENT_TITLE_HEADER: Title header for the current committees charts.
 * - CR_CURRENT_TITLE: Title for the current committees charts.
 * - CR_CURRENT_DESCRIPTION: Description for the current committees charts.
 * - CR_PARTY_TITLE_HEADER: Title header for the committee by party charts.
 * - CR_PARTY_TITLE: Title for the committee by party charts.
 * - CR_PARTY_DESCRIPTION: Description for the committee by party charts.
 * - CR_CURRENT_PARTY_TITLE_HEADER: Title header for the current committee parties charts.
 * - CR_CURRENT_PARTY_TITLE: Title for the current committee parties charts.
 * - CR_CURRENT_PARTY_DESCRIPTION: Description for the current committee parties charts.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for PageModeContentFactory.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and CommitteeRankingPageMode.
 * - Depends on com.hack23.cia.model.internal.application.data.committee.impl for ViewRiksdagenCommittee and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
public interface CommitteeRankingConstants {

    /** The cr overview title header. */
    // Committee Ranking Overview
    String CR_OVERVIEW_TITLE_HEADER = "Committee Rankings";

    /** The cr overview title. */
    String CR_OVERVIEW_TITLE = "Ranking Details";

    /** The cr overview description. */
    String CR_OVERVIEW_DESCRIPTION = "Quantitative analysis of committee performance metrics and effectiveness indicators";

    /** The cr grid title header. */
    // Committee Ranking Data Grid
    String CR_GRID_TITLE_HEADER = "Committee Ranking Overview";

    /** The cr grid title. */
    String CR_GRID_TITLE = "Committee Rankings";

    /** The cr grid description. */
    String CR_GRID_DESCRIPTION = "Multi-dimensional analysis of committee performance patterns and participation metrics";

    /** The cr visit title header. */
    // Committee Ranking Visit History
    String CR_VISIT_TITLE_HEADER = "Committee Rankings";

    /** The cr visit title. */
    String CR_VISIT_TITLE = "Page Visit History";

    /** The cr visit description. */
    String CR_VISIT_DESCRIPTION = "Statistical analysis of committee page engagement patterns";

    /** The cr all title header. */
    // All Committees Charts
    String CR_ALL_TITLE_HEADER = "Committee Rankings";

    /** The cr all title. */
    String CR_ALL_TITLE = "Ranking of All Committees";

    /** The cr all description. */
    String CR_ALL_DESCRIPTION = "Comparative analysis of historical committee performance metrics";

    /** The cr current title header. */
    // Current Committees Charts
    String CR_CURRENT_TITLE_HEADER = "Committee Rankings";

    /** The cr current title. */
    String CR_CURRENT_TITLE = "Current Committees";

    /** The cr current description. */
    String CR_CURRENT_DESCRIPTION = "Statistical assessment of active committee effectiveness metrics";

    /** The cr party title header. */
    // Committee by Party Charts
    String CR_PARTY_TITLE_HEADER = "Committee Rankings";

    /** The cr party title. */
    String CR_PARTY_TITLE = "Committee by Party Charts Overview";

    /** The cr party description. */
    String CR_PARTY_DESCRIPTION = "Cross-sectional analysis of party-committee performance patterns";

    /** The cr current party title header. */
    // Current Committee Parties Charts
    String CR_CURRENT_PARTY_TITLE_HEADER = "Committee Rankings";

    /** The cr current party title. */
    String CR_CURRENT_PARTY_TITLE = "Current Committee Parties Charts Overview";

    /** The cr current party description. */
    String CR_CURRENT_PARTY_DESCRIPTION = "Quantitative evaluation of current party-committee interaction metrics";
}

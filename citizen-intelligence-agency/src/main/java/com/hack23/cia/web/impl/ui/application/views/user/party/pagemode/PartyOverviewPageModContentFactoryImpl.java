/*
 * Copyright 2010-2024 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PartyOverviewPageModContentFactoryImpl.
 */
@Component
public final class PartyOverviewPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/**
	 * Instantiates a new party overview page mod content factory impl.
	 */
	public PartyOverviewPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the content.
	 *
	 * @param parameters the parameters
	 * @param menuBar the menu bar
	 * @param panel the panel
	 * @return the layout
	 */
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		panel.setContent(panelContent);

		final String pageId = getPageId(parameters);
		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);

		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);
		createPageHeader(panel, panelContent, "Party Overview " + viewRiksdagenParty.getPartyName(),
				"Party Details", "Explore detailed information about political parties and their activities.");

		final Link addPartyPageLink = getPageLinkFactory().addPartyPageLink(viewRiksdagenParty);
		panelContent.addComponent(addPartyPageLink);
		panelContent.setExpandRatio(addPartyPageLink, ContentRatio.SMALL);

		// Load summary if available
		final DataContainer<ViewRiksdagenPartySummary, String> partySummaryDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartySummary.class);
		final ViewRiksdagenPartySummary viewRiksdagenPartySummary = partySummaryDataContainer.load(pageId);

		// Create a card panel similar to the politician overview
		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("politician-overview-card");
		cardPanel.setWidth("100%");
		cardPanel.setHeightUndefined();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setWidth("100%");
		cardPanel.setContent(cardContent);

		panelContent.addComponent(cardPanel);
		panelContent.setExpandRatio(cardPanel, ContentRatio.SMALL_GRID);

		// Header layout
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		final String titleText = viewRiksdagenParty.getPartyName();
		final Label titleLabel = new Label(titleText, ContentMode.HTML);
		titleLabel.addStyleName("card-title");
		titleLabel.setWidthUndefined();
		headerLayout.addComponent(titleLabel);

		cardContent.addComponent(headerLayout);

		// Divider line for better separation
		final Label divider = new Label("<hr/>", ContentMode.HTML);
		divider.addStyleName("card-divider");
		divider.setWidth("100%");
		cardContent.addComponent(divider);

		// Create single row for four sections
		final HorizontalLayout sectionsLayout = new HorizontalLayout();
		sectionsLayout.setSpacing(true);
		sectionsLayout.setWidth("100%");
		cardContent.addComponent(sectionsLayout);

		// 1. Political Influence & Position
		final VerticalLayout politicalInfluenceLayout = createSectionLayout("Political Influence & Position");
		addPoliticalInfluenceMetrics(politicalInfluenceLayout, viewRiksdagenParty, viewRiksdagenPartySummary);
		sectionsLayout.addComponent(politicalInfluenceLayout);
		sectionsLayout.setExpandRatio(politicalInfluenceLayout, 1.0f);

		// 2. Parliamentary Engagement
		final VerticalLayout parliamentaryEngagementLayout = createSectionLayout("Parliamentary Engagement");
		addParliamentaryEngagementMetrics(parliamentaryEngagementLayout, viewRiksdagenParty, viewRiksdagenPartySummary);
		sectionsLayout.addComponent(parliamentaryEngagementLayout);
		sectionsLayout.setExpandRatio(parliamentaryEngagementLayout, 1.0f);

		// 3. Legislative Impact
		final VerticalLayout legislativeImpactLayout = createSectionLayout("Legislative Impact");
		addLegislativeImpactMetrics(legislativeImpactLayout, viewRiksdagenParty, viewRiksdagenPartySummary);
		sectionsLayout.addComponent(legislativeImpactLayout);
		sectionsLayout.setExpandRatio(legislativeImpactLayout, 1.0f);

		// 4. Member Performance
		final VerticalLayout memberPerformanceLayout = createSectionLayout("Member Performance");
		addMemberPerformanceMetrics(memberPerformanceLayout, viewRiksdagenParty, viewRiksdagenPartySummary);
		sectionsLayout.addComponent(memberPerformanceLayout);
		sectionsLayout.setExpandRatio(memberPerformanceLayout, 1.0f);

		// After the card, add the overview layout
		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getPartyMenuItemFactory().createOverviewPage(overviewLayout, pageId);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
				pageId);
		return panelContent;
	}


	/**
	 * Creates a section layout with a title and consistent styling.
	 *
	 * @param title the section title
	 * @return the vertical layout configured for the section
	 */
	private VerticalLayout createSectionLayout(String title) {
	    final VerticalLayout layout = new VerticalLayout();
	    layout.setSpacing(true);
	    layout.setMargin(true);
	    layout.addStyleName("card-details-column");
	    layout.setWidth("100%");

	    final Label header = new Label(title);
	    header.addStyleName("card-section-title");
	    layout.addComponent(header);

	    // Add some vertical padding after the header
	    final Label padding = new Label();
	    padding.setHeight("10px");
	    layout.addComponent(padding);

	    return layout;
	}

	/**
	 * Adds the political influence metrics.
	 *
	 * @param layout the layout
	 * @param party the party
	 * @param summary the summary
	 */
	// 1. Political Influence & Position
	private void addPoliticalInfluenceMetrics(VerticalLayout layout,
	        ViewRiksdagenParty party,
	        ViewRiksdagenPartySummary summary) {

	    if (summary != null) {
	        // Government Influence
	        layout.addComponent(createInfoRow("Government Position:",
	            summary.isActiveGovernment() ? "In Government" : "Opposition",
	            VaadinIcons.INSTITUTION,
	            "Current position in government"));

	        layout.addComponent(createInfoRow("Ministers:",
	            String.valueOf(summary.getCurrentMinistryAssignments()),
	            VaadinIcons.GROUP,
	            "Current ministerial positions"));

	        // Parliamentary Strength
	        layout.addComponent(createInfoRow("Parliament Members:",
	            String.valueOf(party.getHeadCount()),
	            VaadinIcons.USERS,
	            "Total number of parliament members"));

	        layout.addComponent(createInfoRow("Committee Positions:",
	            String.valueOf(summary.getCurrentCommitteeAssignments()),
	            VaadinIcons.CLIPBOARD_USER,
	            "Current committee assignments"));

	        // Leadership Roles
	        layout.addComponent(createInfoRow("Leadership Positions:",
	            String.valueOf(summary.getCurrentCommitteeLeadershipAssignments()),
	            VaadinIcons.STAR,
	            "Current committee leadership roles"));
	    }
	}

	/**
	 * Adds the parliamentary engagement metrics.
	 *
	 * @param layout the layout
	 * @param party the party
	 * @param summary the summary
	 */
	// 2. Parliamentary Engagement
	private void addParliamentaryEngagementMetrics(VerticalLayout layout,
	        ViewRiksdagenParty party,
	        ViewRiksdagenPartySummary summary) {

	    if (summary != null) {
	        // Active Participation
	        layout.addComponent(createInfoRow("Parliament Activity:",
	            String.format(Locale.ENGLISH,"%.1f%%", calculateActivityRate(summary.getTotalActiveParliament(), party.getHeadCount())),
	            VaadinIcons.CHART_LINE,
	            "Percentage of active members in parliament"));

	        // Committee Engagement
	        layout.addComponent(createInfoRow("Committee Involvement:",
	            String.valueOf(summary.getTotalActiveCommittee()),
	            VaadinIcons.USERS,
	            "Members active in committees"));

	        // Historical Presence
	        layout.addComponent(createInfoRow("Days in Government:",
	            String.format(Locale.ENGLISH,"%,d", summary.getTotalDaysServedGovernment()),
	            VaadinIcons.CLOCK,
	            "Total days served in government"));

	        layout.addComponent(createInfoRow("Parliamentary Experience:",
	            String.format(Locale.ENGLISH,"%,d", summary.getTotalDaysServedParliament()),
	            VaadinIcons.CALENDAR_CLOCK,
	            "Total days served in parliament"));
	    }
	}

	/**
	 * Adds the legislative impact metrics.
	 *
	 * @param layout the layout
	 * @param party the party
	 * @param summary the summary
	 */
	// 3. Legislative Impact
	private void addLegislativeImpactMetrics(VerticalLayout layout,
	        ViewRiksdagenParty party,
	        ViewRiksdagenPartySummary summary) {

	    if (summary != null) {
	        // Legislative Production
	        layout.addComponent(createInfoRow("Total Motions:",
	            String.valueOf(summary.getTotalPartyMotions()),
	            VaadinIcons.FILE_TEXT,
	            "Total party-initiated motions"));

	        layout.addComponent(createInfoRow("Recent Activity:",
	            String.valueOf(summary.getTotalDocumentsLastYear()),
	            VaadinIcons.CHART_TIMELINE,
	            "Documents produced in the last year"));

	        // Cross-party Cooperation
	        layout.addComponent(createInfoRow("Collaboration Rate:",
	            String.format(Locale.ENGLISH,"%.1f%%", summary.getAvgCollaborationPercentage()),
	            VaadinIcons.CONNECT,
	            "Cross-party collaboration percentage"));

	        layout.addComponent(createInfoRow("Joint Initiatives:",
	            String.valueOf(summary.getTotalCollaborativeMotions()),
	            VaadinIcons.USERS,
	            "Multi-party collaborative motions"));

	        // Legislative Efficiency
	        layout.addComponent(createInfoRow("Productivity:",
	            String.format(Locale.ENGLISH,"%.1f", summary.getAvgDocumentsPerMember()),
	            VaadinIcons.CHART_GRID,
	            "Average documents per member"));
	    }
	}

	/**
	 * Adds the member performance metrics.
	 *
	 * @param layout the layout
	 * @param party the party
	 * @param summary the summary
	 */
	// 4. Member Performance
	private void addMemberPerformanceMetrics(VerticalLayout layout,
	        ViewRiksdagenParty party,
	        ViewRiksdagenPartySummary summary) {

	    if (summary != null) {
	        // Activity Distribution
	        layout.addComponent(createInfoRow("High Performers:",
	            String.format(Locale.ENGLISH,"%d (%d%%)",
	                summary.getVeryHighActivityMembers(),
	                calculatePercentage(summary.getVeryHighActivityMembers(), party.getHeadCount())),
	            VaadinIcons.STAR,
	            "Members with very high activity levels"));

	        // Member Focus Areas
	        layout.addComponent(createInfoRow("Party Policy Focus:",
	            String.format(Locale.ENGLISH,"%d (%d%%)",
	                summary.getPartyFocusedMembers(),
	                calculatePercentage(summary.getPartyFocusedMembers(), party.getHeadCount())),
	            VaadinIcons.FLAG,
	            "Members focused on party policy work"));

	        layout.addComponent(createInfoRow("Committee Focus:",
	            String.format(Locale.ENGLISH,"%d (%d%%)",
	                summary.getCommitteeFocusedMembers(),
	                calculatePercentage(summary.getCommitteeFocusedMembers(), party.getHeadCount())),
	            VaadinIcons.CLIPBOARD_USER,
	            "Members focused on committee work"));

	        // Collaboration Metrics
	        layout.addComponent(createInfoRow("Collaborative Members:",
	            String.format(Locale.ENGLISH,"%d (%d%%)",
	                summary.getHighlyCollaborativeMembers(),
	                calculatePercentage(summary.getHighlyCollaborativeMembers(), party.getHeadCount())),
	            VaadinIcons.CONNECT,
	            "Members with high cross-party collaboration"));
	    }
	}

	/**
	 * Calculate percentage.
	 *
	 * @param value the value
	 * @param total the total
	 * @return the int
	 */
	// Helper method for calculating percentages
	private int calculatePercentage(long value, long total) {
	    return total > 0 ? Math.round((float) value * 100 / total) : 0;
	}

	/**
	 * Calculate activity rate.
	 *
	 * @param activeMembers the active members
	 * @param totalMembers the total members
	 * @return the double
	 */
	// Helper method for calculating activity rates
	private double calculateActivityRate(long activeMembers, long totalMembers) {
	    return totalMembers > 0 ? (double) activeMembers * 100 / totalMembers : 0;
	}




	/**
	 * Creates a row displaying a caption and value, with optional icon and tooltip.
	 *
	 * @param caption the field caption
	 * @param value   the field value
	 * @param icon    a VaadinIcons icon for better visual cue
	 * @param tooltip optional tooltip to provide more info
	 * @return a HorizontalLayout representing the info row
	 */
	private HorizontalLayout createInfoRow(final String caption, final String value, VaadinIcons icon, final String tooltip) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("metric-label");
		layout.setWidthUndefined();

		if (icon != null) {
			final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
			iconLabel.addStyleName("card-info-icon");
			if (tooltip != null && !tooltip.isEmpty()) {
				iconLabel.setDescription(tooltip);
			}
			layout.addComponent(iconLabel);
		}

		final Label captionLabel = new Label(caption);
		captionLabel.addStyleName("card-info-caption");
		if (tooltip != null && !tooltip.isEmpty()) {
			captionLabel.setDescription(tooltip);
		}

		final Label valueLabel = new Label(value != null ? value : "");
		valueLabel.addStyleName("card-info-value");

		layout.addComponents(captionLabel, valueLabel);
		return layout;
	}

	/**
	 * Matches.
	 *
	 * @param page the page
	 * @param parameters the parameters
	 * @return true, if successful
	 */
	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}
}

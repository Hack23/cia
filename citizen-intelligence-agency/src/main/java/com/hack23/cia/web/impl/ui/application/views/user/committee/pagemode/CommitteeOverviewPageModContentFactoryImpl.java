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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
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
 * The Class CommitteeOverviewPageModContentFactoryImpl.
 */
@Component
public final class CommitteeOverviewPageModContentFactoryImpl extends AbstractCommitteePageModContentFactoryImpl {

	/**
	 * Instantiates a new committee overview page mod content factory impl.
	 */
	public CommitteeOverviewPageModContentFactoryImpl() {
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

		final ViewRiksdagenCommittee viewRiksdagenCommittee = getItem(parameters);
		getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent,
				"Committee Overview " + viewRiksdagenCommittee.getEmbeddedId().getDetail(),
				"Committee Details",
				"Detailed insights into parliamentary committees and their activities.");

		final Link addCommitteePageLink = getPageLinkFactory().addCommitteePageLink(viewRiksdagenCommittee);
		panelContent.addComponent(addCommitteePageLink);
		panelContent.setExpandRatio(addCommitteePageLink, ContentRatio.SMALL);

		// Create a card panel for the committee overview
		final Panel cardPanel = createCardPanel("Committee Overview");
		final VerticalLayout cardContent = (VerticalLayout) cardPanel.getContent();

		panelContent.addComponent(cardPanel);
		panelContent.setExpandRatio(cardPanel, ContentRatio.SMALL_GRID);

		// Two-column layout for committee attributes
		final HorizontalLayout attributesLayout = new HorizontalLayout();
		attributesLayout.setSpacing(true);
		attributesLayout.setWidth("100%");
		cardContent.addComponent(attributesLayout);

		// First column: Committee Profile (keep existing, but add activity level)
		final VerticalLayout profileDetailsLayout = new VerticalLayout();
		profileDetailsLayout.setSpacing(true);
		profileDetailsLayout.addStyleName("card-details-column");
		profileDetailsLayout.setWidthUndefined();

		final Label profileDetailsHeader = new Label("Committee Profile");
		profileDetailsHeader.addStyleName("card-section-title");
		profileDetailsLayout.addComponent(profileDetailsHeader);

		addInfoRowsToLayout(profileDetailsLayout,
		        new InfoRowItem("Detail:", viewRiksdagenCommittee.getEmbeddedId().getDetail(), VaadinIcons.INFO_CIRCLE),
		        new InfoRowItem("Status:", viewRiksdagenCommittee.isActive() ? "Active" : "Inactive", VaadinIcons.FLAG),
		        new InfoRowItem("Activity Level:", viewRiksdagenCommittee.getActivityLevel(), VaadinIcons.CHART),
		        new InfoRowItem("First Assignment:", String.valueOf(viewRiksdagenCommittee.getFirstAssignmentDate()), VaadinIcons.CALENDAR),
		        new InfoRowItem("Last Assignment:", String.valueOf(viewRiksdagenCommittee.getLastAssignmentDate()), VaadinIcons.CALENDAR_CLOCK)
		);

		// Second column: Membership Statistics
		final VerticalLayout membershipStatsLayout = new VerticalLayout();
		membershipStatsLayout.setSpacing(true);
		membershipStatsLayout.addStyleName("card-details-column");
		membershipStatsLayout.setWidthUndefined();

		final Label membershipStatsHeader = new Label("Membership Statistics");
		membershipStatsHeader.addStyleName("card-section-title");
		membershipStatsLayout.addComponent(membershipStatsHeader);

		addInfoRowsToLayout(membershipStatsLayout,
		        new InfoRowItem("Current Members:", String.valueOf(viewRiksdagenCommittee.getCurrentMemberSize()), VaadinIcons.GROUP),
		        new InfoRowItem("Regular Members:", String.valueOf(viewRiksdagenCommittee.getCurrentRegularMembers()), VaadinIcons.USER),
		        new InfoRowItem("Leadership Positions:", String.valueOf(viewRiksdagenCommittee.getCurrentLeadershipPositions()), VaadinIcons.STAR),
		        new InfoRowItem("Substitute Positions:", String.valueOf(viewRiksdagenCommittee.getCurrentSubstitutePositions()), VaadinIcons.USER_CLOCK),
		        new InfoRowItem("Total Leadership Roles:", String.valueOf(viewRiksdagenCommittee.getTotalLeadershipPositions()), VaadinIcons.USERS),
		        new InfoRowItem("Total Substitute Roles:", String.valueOf(viewRiksdagenCommittee.getTotalSubstitutePositions()), VaadinIcons.USERS)
		);

		// Third column: Document Statistics
		final VerticalLayout documentStatsLayout = new VerticalLayout();
		documentStatsLayout.setSpacing(true);
		documentStatsLayout.addStyleName("card-details-column");
		documentStatsLayout.setWidthUndefined();

		final Label documentStatsHeader = new Label("Document Statistics");
		documentStatsHeader.addStyleName("card-section-title");
		documentStatsLayout.addComponent(documentStatsHeader);

		addInfoRowsToLayout(documentStatsLayout,
		        new InfoRowItem("Total Documents:", String.valueOf(viewRiksdagenCommittee.getTotalDocuments()), VaadinIcons.FILE_TEXT),
		        new InfoRowItem("Documents Last Year:", String.valueOf(viewRiksdagenCommittee.getDocumentsLastYear()), VaadinIcons.FILE_O),
		        new InfoRowItem("Avg Documents/Member:", String.format(Locale.ENGLISH,"%.1f", viewRiksdagenCommittee.getAvgDocumentsPerMember()), VaadinIcons.CHART_LINE),
		        new InfoRowItem("Committee Motions:", String.valueOf(viewRiksdagenCommittee.getTotalCommitteeMotions()), VaadinIcons.FILE_PRESENTATION),
		        new InfoRowItem("Total Assignments:", String.valueOf(viewRiksdagenCommittee.getTotalAssignments()), VaadinIcons.TASKS),
		        new InfoRowItem("Days Served:", String.valueOf(viewRiksdagenCommittee.getTotalDaysServed()), VaadinIcons.CLOCK)
		);

		// Clear existing components and add all three columns
		attributesLayout.removeAllComponents();
		attributesLayout.addComponents(profileDetailsLayout, membershipStatsLayout, documentStatsLayout);

		 // Optional: Add some styling to make the columns more responsive
		attributesLayout.setWidth("100%");
		attributesLayout.setSpacing(true);

		// After the card, add the overview layout
		final VerticalLayout overviewLayout = createOverviewLayout();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getCommitteeMenuItemFactory().createOverviewPage(overviewLayout, pageId);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;
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

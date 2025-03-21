/*
 * Copyright 2010-2025 James Pether Sörling
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

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCommitteeConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.HorizontalLayout;
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

		CardInfoRowUtil.createPageHeader(panel, panelContent,
				CommitteeViewConstants.OV_TITLE_HEADER + viewRiksdagenCommittee.getEmbeddedId().getDetail(),
				CommitteeViewConstants.OV_TITLE,
				CommitteeViewConstants.OV_DESCRIPTION);

		final Link addCommitteePageLink = getPageLinkFactory().addCommitteePageLink(viewRiksdagenCommittee);
		panelContent.addComponent(addCommitteePageLink);
		panelContent.setExpandRatio(addCommitteePageLink, ContentRatio.SMALL);

		// Create a card panel for the committee overview
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

		CardInfoRowUtil.createCardHeader(cardContent,"Committee: " + viewRiksdagenCommittee.getEmbeddedId().getDetail());

		// Two-column layout for committee attributes
		final HorizontalLayout attributesLayout = new HorizontalLayout();
		attributesLayout.setSpacing(true);
		attributesLayout.setWidth("100%");
		cardContent.addComponent(attributesLayout);

		// First column: Committee Profile (keep existing, but add activity level)
		final VerticalLayout profileDetailsLayout = CardInfoRowUtil.createSectionLayout(CommitteeViewConstants.OV_SECTION_PROFILE);

		profileDetailsLayout.addComponent(CardInfoRowUtil.createInfoRow("Detail:", viewRiksdagenCommittee.getEmbeddedId().getDetail(),
		        VaadinIcons.INFO_CIRCLE, "Internal identifier detail for the committee"));
		profileDetailsLayout.addComponent(CardInfoRowUtil.createInfoRow("Status:", viewRiksdagenCommittee.isActive() ? "Active" : "Inactive",
		        VaadinIcons.FLAG, "Current committee status"));
		profileDetailsLayout.addComponent(CardInfoRowUtil.createInfoRow("Activity Level:", viewRiksdagenCommittee.getActivityLevel(),
		        VaadinIcons.CHART, "Committee's current activity level"));
		profileDetailsLayout.addComponent(CardInfoRowUtil.createInfoRow("First Assignment:", String.valueOf(viewRiksdagenCommittee.getFirstAssignmentDate()),
		        VaadinIcons.CALENDAR, "Date the committee's first assignment started"));
		profileDetailsLayout.addComponent(CardInfoRowUtil.createInfoRow("Last Assignment:", String.valueOf(viewRiksdagenCommittee.getLastAssignmentDate()),
		        VaadinIcons.CALENDAR_CLOCK, "Date of the committee's most recent assignment"));

		// Second column: Membership Statistics
		final VerticalLayout membershipStatsLayout = CardInfoRowUtil.createSectionLayout(CommitteeViewConstants.OV_SECTION_MEMBERSHIP);

		membershipStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Current Members:", String.valueOf(viewRiksdagenCommittee.getCurrentMemberSize()),
		        VaadinIcons.GROUP, "Total current committee members"));
		membershipStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Regular Members:", String.valueOf(viewRiksdagenCommittee.getCurrentRegularMembers()),
		        VaadinIcons.USER, "Number of current regular members"));
		membershipStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Leadership Positions:", String.valueOf(viewRiksdagenCommittee.getCurrentLeadershipPositions()),
		        VaadinIcons.STAR, "Current number of leadership positions"));
		membershipStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Substitute Positions:", String.valueOf(viewRiksdagenCommittee.getCurrentSubstitutePositions()),
		        VaadinIcons.USER_CLOCK, "Current number of substitute positions"));
		membershipStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Total Leadership Roles:", String.valueOf(viewRiksdagenCommittee.getTotalLeadershipPositions()),
		        VaadinIcons.USERS, "Historical total of leadership positions"));
		membershipStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Total Substitute Roles:", String.valueOf(viewRiksdagenCommittee.getTotalSubstitutePositions()),
		        VaadinIcons.USERS, "Historical total of substitute positions"));

		// Third column: Document Statistics
		final VerticalLayout documentStatsLayout = CardInfoRowUtil.createSectionLayout(CommitteeViewConstants.OV_SECTION_DOCUMENT);

		documentStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Total Documents:", String.valueOf(viewRiksdagenCommittee.getTotalDocuments()),
		        VaadinIcons.FILE_TEXT, "Total number of documents produced"));
		documentStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Documents Last Year:", String.valueOf(viewRiksdagenCommittee.getDocumentsLastYear()),
		        VaadinIcons.FILE_O, "Documents produced in the last year"));
		documentStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Avg Documents/Member:", String.format(Locale.ENGLISH,"%.1f", viewRiksdagenCommittee.getAvgDocumentsPerMember()),
		        VaadinIcons.CHART_LINE, "Average documents per committee member"));
		documentStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Committee Motions:", String.valueOf(viewRiksdagenCommittee.getTotalCommitteeMotions()),
		        VaadinIcons.FILE_PRESENTATION, "Total number of committee motions"));
		documentStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Total Assignments:", String.valueOf(viewRiksdagenCommittee.getTotalAssignments()),
		        VaadinIcons.TASKS, "Total number of assignments"));
		documentStatsLayout.addComponent(CardInfoRowUtil.createInfoRow("Days Served:", String.valueOf(viewRiksdagenCommittee.getTotalDaysServed()),
		        VaadinIcons.CLOCK, "Total days of committee service"));

		// Clear existing components and add all three columns
		attributesLayout.removeAllComponents();
		attributesLayout.addComponents(profileDetailsLayout, membershipStatsLayout, documentStatsLayout);

		// Optional: Add some styling to make the columns more responsive
		attributesLayout.setWidth("100%");
		attributesLayout.setSpacing(true);


		// After the card, add the overview layout
		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
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
		return PageCommandCommitteeConstants.COMMAND_COMMITTEE_OVERVIEW.matches(page, parameters);
	}

}

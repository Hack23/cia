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
 *  $Id$
 *  $HeadURL$
 */
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianExperienceSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianExperienceSummary.PoliticalRole;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PoliticianOverviewPageModContentFactoryImpl.
 */
@Component
public final class PoliticianOverviewPageModContentFactoryImpl extends AbstractPoliticianPageModContentFactoryImpl {

	/**
	 * Instantiates a new politician overview page mod content factory impl.
	 */
	public PoliticianOverviewPageModContentFactoryImpl() {
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
		final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);
		final ViewRiksdagenPoliticianBallotSummary viewRiksdagenPoliticianBallotSummary = getViewRiksdagenPoliticianBallotSummary(
				parameters);
	    final ViewRiksdagenPoliticianExperienceSummary experienceSummary = getViewRiksdagenPoliticianExperienceSummary(parameters);


		getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent,
				"Politician Overview " + viewRiksdagenPolitician.getFirstName() + ' '
						+ viewRiksdagenPolitician.getLastName() + " (" + viewRiksdagenPolitician.getParty() + ')',
				"Politician Details", "Detailed profiles and activities of politicians.");

		final PersonData personData = getApplicationManager().getDataContainer(PersonData.class)
				.load(viewRiksdagenPolitician.getPersonId());

		 createOverviewContent(panelContent, personData, viewRiksdagenPolitician, 
		            viewRiksdagenPoliticianBallotSummary, experienceSummary, pageId);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
				UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);

		return panelContent;
	}

	/**
	 * Gets the view riksdagen politician ballot summary.
	 *
	 * @param parameters the parameters
	 * @return the view riksdagen politician ballot summary
	 */
	protected ViewRiksdagenPoliticianBallotSummary getViewRiksdagenPoliticianBallotSummary(final String parameters) {
		final String pageId = getPageId(parameters);
		final PersonData personData = getApplicationManager().getDataContainer(PersonData.class).load(pageId);
		if (personData != null) {
			return getApplicationManager().getDataContainer(ViewRiksdagenPoliticianBallotSummary.class)
					.load(personData.getId());
		} else {
			return null;
		}
	}
	
	protected ViewRiksdagenPoliticianExperienceSummary getViewRiksdagenPoliticianExperienceSummary(final String parameters) {
	    final String pageId = getPageId(parameters);
	    return getApplicationManager().getDataContainer(ViewRiksdagenPoliticianExperienceSummary.class).load(pageId);
	}

	/**
	 * Creates the overview content in a card style similar to the scoreboard
	 * snippet.
	 *
	 * @param panelContent                         the panel content
	 * @param personData                           the person data
	 * @param viewRiksdagenPolitician              the view riksdagen politician
	 * @param viewRiksdagenPoliticianBallotSummary the view riksdagen politician
	 *                                             ballot summary
	 * @param pageId                               the page id
	 */
	private void createOverviewContent(final VerticalLayout panelContent, final PersonData personData,
	        final ViewRiksdagenPolitician viewRiksdagenPolitician,
	        final ViewRiksdagenPoliticianBallotSummary viewRiksdagenPoliticianBallotSummary,
	        final ViewRiksdagenPoliticianExperienceSummary experienceSummary,
	        final String pageId) {
		
		// Link to politician detail page
		final Link createPoliticianPageLink = getPageLinkFactory().createPoliticianPageLink(personData);
		createPoliticianPageLink.addStyleName("card-subtitle");
		panelContent.addComponent(createPoliticianPageLink);
		panelContent.setExpandRatio(createPoliticianPageLink, ContentRatio.SMALL);

		// Create a panel (card) for the politician
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


		createCardHeader(cardContent,viewRiksdagenPolitician.getFirstName() + " " + viewRiksdagenPolitician.getLastName()
		+ " (" + viewRiksdagenPolitician.getParty() + ")");

		// Party link
		final Link partyLink = new Link("Party " + viewRiksdagenPolitician.getParty(),
				new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/" + viewRiksdagenPolitician.getParty()));
		partyLink.setIcon(VaadinIcons.GROUP);
		partyLink.addStyleName("card-title");
		cardContent.addComponent(partyLink);


		// Image and details layout
		final HorizontalLayout imageAndDetailsLayout = new HorizontalLayout();
		imageAndDetailsLayout.setSpacing(true);
		imageAndDetailsLayout.setWidth("100%");
		cardContent.addComponent(imageAndDetailsLayout);

		// Politician image (smaller)
		final String imageUrl = personData.getImageUrl192().replace("http://", "https://");
		final Image image = new Image("", new ExternalResource(imageUrl));
		image.setDescription(
				"Picture of " + viewRiksdagenPolitician.getFirstName() + " " + viewRiksdagenPolitician.getLastName());
		image.setWidth(100, Unit.PIXELS);
		image.addStyleName("politician-image");

		imageAndDetailsLayout.addComponent(image);

		final HorizontalLayout sectionsGrid = new HorizontalLayout();
		sectionsGrid.setSpacing(true);
		sectionsGrid.setWidth("100%");
		imageAndDetailsLayout.addComponent(sectionsGrid);
		imageAndDetailsLayout.setExpandRatio(sectionsGrid, 1.0f);

		// 1. Political Role & Influence
		final VerticalLayout politicalRoleLayout = createSectionLayout("Political Role & Influence");
		addPoliticalRoleMetrics(politicalRoleLayout, viewRiksdagenPolitician, viewRiksdagenPoliticianBallotSummary);
		sectionsGrid.addComponent(politicalRoleLayout);
		sectionsGrid.setExpandRatio(politicalRoleLayout, 1.0f);
		
		  final VerticalLayout experienceLayout = createSectionLayout("Experience & Expertise");
		    addExperienceMetrics(experienceLayout, experienceSummary);
		    sectionsGrid.addComponent(experienceLayout);
		    sectionsGrid.setExpandRatio(experienceLayout, 1.0f);

		// 2. Parliamentary Performance
		final VerticalLayout performanceLayout = createSectionLayout("Parliamentary Performance");
		addParliamentaryPerformanceMetrics(performanceLayout, viewRiksdagenPolitician, viewRiksdagenPoliticianBallotSummary);
		sectionsGrid.addComponent(performanceLayout);
		sectionsGrid.setExpandRatio(performanceLayout, 1.0f);

		// 3. Legislative Activity
		final VerticalLayout legislativeLayout = createSectionLayout("Legislative Impact");
		addLegislativeMetrics(legislativeLayout, viewRiksdagenPolitician);
		sectionsGrid.addComponent(legislativeLayout);
		sectionsGrid.setExpandRatio(legislativeLayout, 1.0f);

		// 4. Party Alignment & Cooperation
		final VerticalLayout partyAlignmentLayout = createSectionLayout("Party Alignment & Cooperation");
		addPartyAlignmentMetrics(partyAlignmentLayout, viewRiksdagenPolitician, viewRiksdagenPoliticianBallotSummary);
		sectionsGrid.addComponent(partyAlignmentLayout);
		sectionsGrid.setExpandRatio(partyAlignmentLayout, 1.0f);


		imageAndDetailsLayout.addComponent(sectionsGrid);
		imageAndDetailsLayout.setExpandRatio(sectionsGrid, ContentRatio.SMALL_GRID);

		// After the card, add the overview layout for extended details
		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getPoliticianMenuItemFactory().createOverviewPage(overviewLayout, pageId);
	}

	
	private void addExperienceMetrics(VerticalLayout layout, ViewRiksdagenPoliticianExperienceSummary experienceSummary) {
	    if (experienceSummary != null) {
	        // Career Overview
	        layout.addComponent(createInfoRow("Career Phase:", 
	            experienceSummary.getCareerPhase().toString().replace("_", " "), 
	            VaadinIcons.CALENDAR_CLOCK, 
	            "Current career stage"));

	        // Experience Level
	        layout.addComponent(createInfoRow("Experience Level:", 
	            experienceSummary.getExperienceLevel().toString().replace("_", " "), 
	            VaadinIcons.CHART_TIMELINE, 
	            "Overall political experience classification"));

	        // Leadership Profile
	        layout.addComponent(createInfoRow("Leadership Role:", 
	            experienceSummary.getLeadershipProfile().toString().replace("_", " "), 
	            VaadinIcons.USER_STAR, 
	            "Leadership experience level"));

	        // Specialization
	        layout.addComponent(createInfoRow("Expertise:", 
	            experienceSummary.getSpecializationLevel().toString().replace("_", " "), 
	            VaadinIcons.SPECIALIST, 
	            "Area of specialization"));

	        // Experience Metrics
	        if (experienceSummary.getTotalDays() != null) {
	            layout.addComponent(createInfoRow("Total Days Served:", 
	                String.format(Locale.ENGLISH,"%,d", experienceSummary.getTotalDays()), 
	                VaadinIcons.CLOCK, 
	                "Total days in political service"));
	        }

	        // Top Knowledge Areas
	        if (experienceSummary.getKnowledgeAreas() != null && !experienceSummary.getKnowledgeAreas().isEmpty()) {
	            String topAreas = experienceSummary.getKnowledgeAreas().stream()
	                .filter(ka -> ka.getArea() != null && !ka.getArea().equals("Other"))
	                .sorted((ka1, ka2) -> ka2.getWeightedExp().compareTo(ka1.getWeightedExp()))
	                .limit(6)
	                .map(ka -> String.format(Locale.ENGLISH,"%s (Weight: %d)", 
	                    ka.getArea(), 
	                    ka.getWeightedExp()))
	                .collect(Collectors.joining(", "));

	            if (!topAreas.isEmpty()) {
	                layout.addComponent(createInfoRow("Key Policy Areas:", 
	                    topAreas, 
	                    VaadinIcons.CLIPBOARD_TEXT, 
	                    "Main areas of expertise with weighted importance"));
	            }
	        }

	        // Top Roles
	        if (experienceSummary.getRoles() != null && !experienceSummary.getRoles().isEmpty()) {
	            String topRoles = experienceSummary.getRoles().stream()
	                .filter(role -> role.getRole() != null && !role.getRole().equals("Other"))
	                .sorted((r1, r2) -> r2.getWeightedExp().compareTo(r1.getWeightedExp()))
	                .limit(6)
	                .map(role -> String.format(Locale.ENGLISH,"%s%s (Weight: %d)", 
	                    role.getRole(),
	                    role.getOrg() != null ? " - " + role.getOrg() : "",
	                    role.getWeightedExp()))
	                .collect(Collectors.joining(", "));

	            if (!topRoles.isEmpty()) {
	                layout.addComponent(createInfoRow("Key Political Roles:", 
	                    topRoles, 
	                    VaadinIcons.USERS, 
	                    "Most significant positions with weighted importance"));
	            }
	        }
	        
	        // Political Analysis Comment
	        if (StringUtils.isNotBlank(experienceSummary.getPoliticalAnalysisComment())) {
	            layout.addComponent(createInfoRow("Analysis:", 
	                experienceSummary.getPoliticalAnalysisComment(), 
	                VaadinIcons.COMMENT, 
	                "Political career analysis"));
	        }
	    }
	}
	
	private String formatRole(PoliticalRole role) {
	    return String.format(Locale.ENGLISH,"%s%s (%d days)", 
	        role.getRole(),
	        role.getOrg() != null ? " - " + role.getOrg() : "",
	        role.getDays() != null ? role.getDays() : 0);
	}
	
	/**
	 * Adds the political role metrics.
	 *
	 * @param layout the layout
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 */
	private void addPoliticalRoleMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician,
			ViewRiksdagenPoliticianBallotSummary ballotSummary) {

		layout.addComponent(createInfoRow("Current Role:", ballotSummary.getStatus(), VaadinIcons.INSTITUTION,
				"Current position in parliament"));
		layout.addComponent(createInfoRow("Region:", ballotSummary.getElectionRegion(), VaadinIcons.MAP_MARKER,
				"Electoral district"));
		layout.addComponent(createInfoRow("Career Length:",
				calculateServiceYears(politician.getFirstAssignmentDate(), politician.getLastAssignmentDate()),
				VaadinIcons.TIMER, "Years in parliament"));
		layout.addComponent(
				createInfoRow("Influence Score:", String.format(Locale.ENGLISH,"%.1f", ballotSummary.getVotingConsistencyScore()),
						VaadinIcons.CHART_GRID, "Overall parliamentary influence"));
	}

	/**
	 * Adds the parliamentary performance metrics.
	 *
	 * @param layout the layout
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 */
	private void addParliamentaryPerformanceMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician,
			ViewRiksdagenPoliticianBallotSummary ballotSummary) {

		layout.addComponent(
				createInfoRow("Attendance Rate:", String.format(Locale.ENGLISH,"%.1f%%", 100 - ballotSummary.getAbsenceRate()),
						VaadinIcons.USER_CHECK, "Session attendance rate"));
		layout.addComponent(createInfoRow("Voting Success:", String.format(Locale.ENGLISH,"%.1f%%", ballotSummary.getSuccessRate()),
				VaadinIcons.TROPHY, "Votes on winning side"));
		layout.addComponent(createInfoRow("Activity Level:", politician.getDocActivityLevel(), VaadinIcons.CHART_LINE,
				"Overall engagement level"));
	}

	/**
	 * Adds the legislative metrics.
	 *
	 * @param layout the layout
	 * @param politician the politician
	 */
	private void addLegislativeMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician) {

		layout.addComponent(createInfoRow("Documents/Year:", String.format(Locale.ENGLISH,"%.1f", politician.getAverageDocsPerYear()),
				VaadinIcons.FILE_TEXT, "Average documents per year"));
		layout.addComponent(createInfoRow("Individual Motions:", String.valueOf(politician.getIndividualMotions()),
				VaadinIcons.USER, "Personal motions submitted"));
		
		layout.addComponent(createInfoRow("Party Motions:", String.valueOf(politician.getPartyMotions()),
				VaadinIcons.USER, "Party motions signed"));		

		layout.addComponent(createInfoRow("Committee Motions:", String.valueOf(politician.getCommitteeMotions()),
				VaadinIcons.GROUP, "Committee-based motions"));
		layout.addComponent(createInfoRow("Document Impact:", politician.getDocActivityProfile(), VaadinIcons.CHART_3D,
				"Legislative influence assessment"));
	}

	/**
	 * Adds the party alignment metrics.
	 *
	 * @param layout the layout
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 */
	private void addPartyAlignmentMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician,
			ViewRiksdagenPoliticianBallotSummary ballotSummary) {

		layout.addComponent(createInfoRow("Party Loyalty:", String.format(Locale.ENGLISH,"%.1f%%", ballotSummary.getLoyaltyRate()),
				VaadinIcons.GROUP, "Party line adherence"));
		layout.addComponent(createInfoRow("Independence Rate:", String.format(Locale.ENGLISH,"%.1f%%", ballotSummary.getRebelRate()),
				VaadinIcons.RANDOM, "Votes against party line"));
		layout.addComponent(createInfoRow("Cross-Party Collaboration:",
				String.format(Locale.ENGLISH,"%.1f%%", politician.getCollaborationPercentage()), VaadinIcons.CONNECT,
				"Inter-party cooperation"));
		layout.addComponent(createInfoRow("Multi-Party Motions:", String.valueOf(politician.getMultiPartyMotions()),
				VaadinIcons.USERS, "Cross-party legislative initiatives"));
	}

	/**
	 * Calculate service years.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the string
	 */
	private String calculateServiceYears(Date start, Date end) {
		try {
			if (start == null || end == null) {
				return "N/A";
			}

			// Convert java.util.Date to milliseconds and calculate years
			final long diffInMillies = Math.abs(end.getTime() - start.getTime());
			final long years = diffInMillies / (1000L * 60 * 60 * 24 * 365);

			return years + " years";
		} catch (final Exception e) {
			return "N/A";
		}
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

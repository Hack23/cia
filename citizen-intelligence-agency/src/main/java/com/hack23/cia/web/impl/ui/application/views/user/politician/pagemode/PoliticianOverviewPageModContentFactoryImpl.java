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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianExperienceSummary;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PoliticianLeaderboardUtil;
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

	@Autowired
	private PoliticianLeaderboardUtil politicianLeaderboardUtil;

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

		CardInfoRowUtil.createPageHeader(panel, panelContent,
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

	/**
	 * Gets the view riksdagen politician experience summary.
	 *
	 * @param parameters the parameters
	 * @return the view riksdagen politician experience summary
	 */
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
	 * @param experienceSummary the experience summary
	 * @param pageId                               the page id
	 */
	private void createOverviewContent(final VerticalLayout panelContent, final PersonData personData,
	        final ViewRiksdagenPolitician viewRiksdagenPolitician,
	        final ViewRiksdagenPoliticianBallotSummary viewRiksdagenPoliticianBallotSummary,
	        final ViewRiksdagenPoliticianExperienceSummary experienceSummary,
	        final String pageId) {

		addPoliticalLink(panelContent, personData);

		final Panel cardPanel = new Panel();
		final VerticalLayout cardContent = createCardContent(panelContent, cardPanel);


		CardInfoRowUtil.createCardHeader(cardContent,viewRiksdagenPolitician.getFirstName() + " " + viewRiksdagenPolitician.getLastName()
		+ " (" + viewRiksdagenPolitician.getParty() + ")");

		// Party link
		addPartyLink(viewRiksdagenPolitician, cardContent);


		addAllSections(personData, viewRiksdagenPolitician, viewRiksdagenPoliticianBallotSummary, experienceSummary,
				cardContent);

		// After the card, add the overview layout for extended details
		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getPoliticianMenuItemFactory().createOverviewPage(overviewLayout, pageId);
	}

	/**
	 * Creates the card content.
	 *
	 * @param panelContent the panel content
	 * @param cardPanel the card panel
	 * @return the vertical layout
	 */
	private VerticalLayout createCardContent(final VerticalLayout panelContent, final Panel cardPanel) {
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
		return cardContent;
	}

	/**
	 * Adds the all sections.
	 *
	 * @param personData the person data
	 * @param viewRiksdagenPolitician the view riksdagen politician
	 * @param viewRiksdagenPoliticianBallotSummary the view riksdagen politician ballot summary
	 * @param experienceSummary the experience summary
	 * @param cardContent the card content
	 */
	private void addAllSections(final PersonData personData, final ViewRiksdagenPolitician viewRiksdagenPolitician,
			final ViewRiksdagenPoliticianBallotSummary viewRiksdagenPoliticianBallotSummary,
			final ViewRiksdagenPoliticianExperienceSummary experienceSummary, final VerticalLayout cardContent) {
		// Image and details layout
		final HorizontalLayout imageAndDetailsLayout = new HorizontalLayout();
		addImage(personData, viewRiksdagenPolitician, cardContent, imageAndDetailsLayout);

		final HorizontalLayout sectionsGrid = new HorizontalLayout();
		sectionsGrid.setSpacing(true);
		sectionsGrid.setWidth("100%");
		imageAndDetailsLayout.addComponent(sectionsGrid);
		imageAndDetailsLayout.setExpandRatio(sectionsGrid, 1.0f);

		// 1. Political Role & Influence
		final VerticalLayout politicalRoleLayout = CardInfoRowUtil.createSectionLayout("Political Role & Influence");
		addPoliticalRoleMetrics(politicalRoleLayout, viewRiksdagenPolitician, viewRiksdagenPoliticianBallotSummary,experienceSummary);
		sectionsGrid.addComponent(politicalRoleLayout);
		sectionsGrid.setExpandRatio(politicalRoleLayout, 1.0f);

		  final VerticalLayout experienceLayout = CardInfoRowUtil.createSectionLayout("Experience & Expertise");
		  politicianLeaderboardUtil.addExperienceMetrics(experienceLayout, experienceSummary);
		    sectionsGrid.addComponent(experienceLayout);
		    sectionsGrid.setExpandRatio(experienceLayout, 1.0f);

		// 2. Parliamentary Performance
		final VerticalLayout performanceLayout = CardInfoRowUtil.createSectionLayout("Parliamentary Performance");
		politicianLeaderboardUtil.addParliamentaryPerformanceMetrics(performanceLayout, viewRiksdagenPolitician, viewRiksdagenPoliticianBallotSummary);
		sectionsGrid.addComponent(performanceLayout);
		sectionsGrid.setExpandRatio(performanceLayout, 1.0f);

		// 3. Legislative Activity
		final VerticalLayout legislativeLayout = CardInfoRowUtil.createSectionLayout("Legislative Impact");
		politicianLeaderboardUtil.addLegislativeMetrics(legislativeLayout, viewRiksdagenPolitician);
		sectionsGrid.addComponent(legislativeLayout);
		sectionsGrid.setExpandRatio(legislativeLayout, 1.0f);

		// 4. Party Alignment & Cooperation
		final VerticalLayout partyAlignmentLayout = CardInfoRowUtil.createSectionLayout("Party Alignment & Cooperation");
		politicianLeaderboardUtil.addPartyAlignmentMetrics(partyAlignmentLayout, viewRiksdagenPolitician, viewRiksdagenPoliticianBallotSummary);
		sectionsGrid.addComponent(partyAlignmentLayout);
		sectionsGrid.setExpandRatio(partyAlignmentLayout, 1.0f);


		imageAndDetailsLayout.addComponent(sectionsGrid);
		imageAndDetailsLayout.setExpandRatio(sectionsGrid, ContentRatio.SMALL_GRID);
	}

	/**
	 * Adds the political link.
	 *
	 * @param panelContent the panel content
	 * @param personData the person data
	 */
	private void addPoliticalLink(final VerticalLayout panelContent, final PersonData personData) {
		final Link createPoliticianPageLink = getPageLinkFactory().createPoliticianPageLink(personData);
		createPoliticianPageLink.addStyleName("card-subtitle");
		panelContent.addComponent(createPoliticianPageLink);
		panelContent.setExpandRatio(createPoliticianPageLink, ContentRatio.SMALL);
	}

	/**
	 * Adds the party link.
	 *
	 * @param viewRiksdagenPolitician the view riksdagen politician
	 * @param cardContent the card content
	 */
	private void addPartyLink(final ViewRiksdagenPolitician viewRiksdagenPolitician, final VerticalLayout cardContent) {
		final Link partyLink = new Link("Party " + viewRiksdagenPolitician.getParty(),
				new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/" + viewRiksdagenPolitician.getParty()));
		partyLink.setIcon(VaadinIcons.GROUP);
		partyLink.addStyleName("card-title");
		cardContent.addComponent(partyLink);
	}

	/**
	 * Adds the image.
	 *
	 * @param personData the person data
	 * @param viewRiksdagenPolitician the view riksdagen politician
	 * @param cardContent the card content
	 * @param imageAndDetailsLayout the image and details layout
	 */
	private void addImage(final PersonData personData, final ViewRiksdagenPolitician viewRiksdagenPolitician,
			final VerticalLayout cardContent, final HorizontalLayout imageAndDetailsLayout) {
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
	}


	/**
	 * Adds the political role metrics.
	 *
	 * @param layout the layout
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 * @param experienceSummary the experience summary
	 */
	private void addPoliticalRoleMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician,
			ViewRiksdagenPoliticianBallotSummary ballotSummary, ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

		layout.addComponent(CardInfoRowUtil.createInfoRow("Current Role:", ballotSummary.getStatus(), VaadinIcons.INSTITUTION,
				"Current position in parliament"));
		layout.addComponent(CardInfoRowUtil.createInfoRow("Region:", ballotSummary.getElectionRegion(), VaadinIcons.MAP_MARKER,
				"Electoral district"));
		layout.addComponent(CardInfoRowUtil.createInfoRow("Career Length:",
				calculateServiceYears(politician.getFirstAssignmentDate(), politician.getLastAssignmentDate()),
				VaadinIcons.TIMER, "Years in parliament"));

		politicianLeaderboardUtil.addTopRoles(layout, experienceSummary);
		politicianLeaderboardUtil.addKnowledgeAreas(layout, experienceSummary);
		politicianLeaderboardUtil.addExperienceMetrics(layout,experienceSummary);
		politicianLeaderboardUtil.addPoliticalAnalysisComment(layout, experienceSummary);

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

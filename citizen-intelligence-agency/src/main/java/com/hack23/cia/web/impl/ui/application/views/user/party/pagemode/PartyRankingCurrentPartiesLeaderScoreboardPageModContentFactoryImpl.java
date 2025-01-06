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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianExperienceSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.util.PartyLeaderUtil;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PoliticianLeaderboardUtil;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
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
 * The Class PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl.
 */
@Service
public final class PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl
		extends AbstractPartyRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_RANKING_VIEW_NAME;

	/** The esv api. */
	// Adding EsvApi to fetch ministry-related data
	@Autowired
	private EsvApi esvApi;

	@Autowired
	private PoliticianLeaderboardUtil politicianLeaderboardUtil;

	/**
	 * Instantiates a new party ranking current parties leader scoreboard page mod content factory impl.
	 */
	public PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl() {
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
		getPartyRankingMenuItemFactory().createPartyRankingMenuBar(menuBar);

		CardInfoRowUtil.createPageHeader(panel, panelContent, "Current Party Leaders Scoreboard", "Leader Performance",
				"Evaluate the performance of current party leaders including those not in government.");

		final HorizontalLayout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();
		panelContent.addComponent(chartLayout);
		panelContent.setExpandRatio(chartLayout, ContentRatio.LARGE_FORM);

		final VerticalLayout wrapper = new VerticalLayout();
		wrapper.setSizeFull();
		chartLayout.addComponent(wrapper);

		final ResponsiveRow row = RowUtil.createGridLayout(wrapper);
		row.setSizeFull();

		final Map<String, ViewRiksdagenPolitician> politicianMap = loadPoliticiansByPersonId();
		final Map<String, Boolean> partyLeaderMap = PartyLeaderUtil.computePartyLeaders(getApplicationManager(), politicianMap.keySet());

		final List<ViewRiksdagenPolitician> partyLeaders = politicianMap.values().stream()
				.filter(p -> partyLeaderMap.getOrDefault(p.getPersonId(), false))
				.collect(Collectors.toList());

		// Sort: in government first, then alphabetical by last name
		partyLeaders.sort((a, b) -> {
			final boolean aInGov = a.isActiveGovernment();
			final boolean bInGov = b.isActiveGovernment();
			if (aInGov == bInGov) {
				return a.getLastName().compareToIgnoreCase(b.getLastName());
			}
			// government first
			return Boolean.compare(!aInGov, !bInGov);
		});

		// Load ESV data for ministries
		final Map<Integer, List<GovernmentBodyAnnualSummary>> dataMap = esvApi.getData();
		final int CURRENT_YEAR = 2024;
		final List<GovernmentBodyAnnualSummary> currentYearGovernmentBodies = dataMap.get(CURRENT_YEAR);
		final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry = currentYearGovernmentBodies
				.stream().collect(Collectors.groupingBy(GovernmentBodyAnnualSummary::getMinistry));

		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry = esvApi
				.getGovernmentBodyReportByMinistry();

		for (final ViewRiksdagenPolitician leader : partyLeaders) {
			final ViewRiksdagenPoliticianBallotSummary ballotSummary = getApplicationManager()
					.getDataContainer(ViewRiksdagenPoliticianBallotSummary.class).load(leader.getPersonId());
		    final ViewRiksdagenPoliticianExperienceSummary experienceSummary = getApplicationManager().getDataContainer(ViewRiksdagenPoliticianExperienceSummary.class).load(leader.getPersonId());

			final Panel cardPanel = createLeaderCard(leader, ballotSummary, governmentBodyByMinistry, reportByMinistry,experienceSummary);
			row.addColumn().withDisplayRules(12, 6, 4, 4).withComponent(cardPanel);
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;
	}

	/**
	 * Load politicians by person id.
	 *
	 * @return the map
	 */
	private Map<String, ViewRiksdagenPolitician> loadPoliticiansByPersonId() {
		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPolitician.class);

		final List<ViewRiksdagenPolitician> activePoliticians = politicianDataContainer.findListByProperty(
				new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.active);

		return activePoliticians.stream().collect(Collectors.toMap(ViewRiksdagenPolitician::getPersonId, p -> p));
	}

	/**
	 * Creates the leader card.
	 *
	 * @param leader the leader
	 * @param ballotSummary the ballot summary
	 * @param governmentBodyByMinistry the government body by ministry
	 * @param reportByMinistry the report by ministry
	 * @param experienceSummary
	 * @return the panel
	 */
	private Panel createLeaderCard(final ViewRiksdagenPolitician leader, final ViewRiksdagenPoliticianBallotSummary ballotSummary,
			final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry, final ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("leader-baseball-card");
		cardPanel.setSizeFull();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setSizeFull();
		cardPanel.setContent(cardContent);

		CardInfoRowUtil.createCardHeader(cardContent,"Partiledare " + leader.getFirstName() + " " + leader.getLastName() + " ("
				+ leader.getParty() + ")");

		// Politician detail link
		cardContent.addComponent(getPageLinkFactory().createPoliticianPageLink(leader));

		// Party link
		final Link partyLink = new Link("Party " + leader.getParty(),
				new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/" + leader.getParty()));
		partyLink.setIcon(VaadinIcons.GROUP);
		cardContent.addComponent(partyLink);

		final boolean isPartyLeader = PartyLeaderUtil.isPartyLeader(getApplicationManager(), leader.getPersonId());
		if (isPartyLeader) {
			final ViewRiksdagenPartyRoleMember leaderRole = PartyLeaderUtil.getPartyLeaderRole(getApplicationManager(), leader.getPersonId());
			if (leaderRole != null) {
				final Label subHeader = new Label("Partiledare (" + leader.getParty() + ") since " + leaderRole.getFromDate());
				subHeader.addStyleName("card-subtitle");
				cardContent.addComponent(subHeader);
			}
		}

		// Government or not
		ViewRiksdagenGovermentRoleMember govMember = null;
		if (leader.isActiveGovernment()) {
			final Label govLabel = new Label("Currently in Government");
			govLabel.addStyleName("card-subtitle");
			cardContent.addComponent(govLabel);

			// Add ministry summary if we can identify their ministry
			// The ministry detail is stored in the same structure as the ministry snippet:
			// We need to find which ministry they belong to
			// In the ministry snippet, "govMember.getDetail()" gives ministry detail key.
			// Here we only have leader, not govMember. We must find a corresponding approach:

			// Let's assume we can identify the leader's ministry from active government roles data:
			// we do similar approach: load active government role members and find the one matching this leader
			final ViewRiksdagenPolitician pol = leader; // same as leader
			govMember = findGovernmentRoleForLeader(pol);
			if (govMember != null) {
				politicianLeaderboardUtil.addMinistryRoleSummary(cardContent, govMember, governmentBodyByMinistry, reportByMinistry);
			}

		} else {
			final Label nonGovLabel = new Label("Not in Government");
			nonGovLabel.addStyleName("card-subtitle-nongov");
			cardContent.addComponent(nonGovLabel);
		}

		// Tenure and Experience rows
		final VerticalLayout statsContainer = new VerticalLayout();
		statsContainer.setSpacing(false);
		statsContainer.addStyleName("card-stats-container");
		statsContainer.setWidth("100%");

		// Tenure (assuming leader might have totalDaysServed property)
		final Label tenureIcon = new Label(VaadinIcons.CLOCK.getHtml(), ContentMode.HTML);
		tenureIcon.setDescription("Total Tenure");
		final Label tenureLabel = new Label("Tenure:");
		tenureLabel.addStyleName("card-tenure-text");
		final Label tenureValue = new Label(leader.getTotalDaysServedParty() + " days");
		tenureValue.addStyleName("card-tenure-value");
		final HorizontalLayout tenureLayout = new HorizontalLayout(tenureIcon, tenureLabel, tenureValue);
		tenureLayout.setSpacing(true);
		tenureLayout.addStyleName("card-tenure");
		statsContainer.addComponent(tenureLayout);

		// Experience
		final HorizontalLayout experienceLayout = new HorizontalLayout();
		experienceLayout.setSpacing(true);
		experienceLayout.addStyleName("card-experience-section");
		final Label expIcon = new Label(VaadinIcons.USER_CHECK.getHtml(), ContentMode.HTML);
		expIcon.setDescription("Political Experience");
		final Label expLabel = new Label("Experience:");
		expLabel.addStyleName("card-experience-text");

		final int govYears = (int) (leader.getTotalDaysServedGovernment() / 365);
		final int partyYears = (int) (leader.getTotalDaysServedParty() / 365);
		final int parliamentYears = (int) (leader.getTotalDaysServedParliament() / 365);
		final String expText = String.format(Locale.ENGLISH, "Government: %dy, Party: %dy, Parliament: %dy",
				govYears, partyYears, parliamentYears);
		final Label expValue = new Label(expText);
		expValue.addStyleName("card-experience-value");
		experienceLayout.addComponents(expIcon, expLabel, expValue);
		statsContainer.addComponent(experienceLayout);

		cardContent.addComponent(statsContainer);

		// Create grid for the four sections
		final HorizontalLayout sectionsGrid = new HorizontalLayout();
		sectionsGrid.setSpacing(true);
		sectionsGrid.setWidth("100%");


		// Add the four main sections
		final VerticalLayout politicalRoleLayout = CardInfoRowUtil.createSectionLayout("Political Role & Influence");
		addPoliticalRoleMetrics(politicalRoleLayout, PartyLeaderUtil.getPartyLeaderRole(getApplicationManager(), leader.getPersonId()), govMember, leader, ballotSummary,experienceSummary);
		sectionsGrid.addComponent(politicalRoleLayout);

		final VerticalLayout performanceLayout = CardInfoRowUtil.createSectionLayout("Parliamentary Performance");
		politicianLeaderboardUtil.addParliamentaryPerformanceMetrics(performanceLayout, leader, ballotSummary);
		sectionsGrid.addComponent(performanceLayout);

	    cardContent.addComponent(sectionsGrid);

		final HorizontalLayout sections2Grid = new HorizontalLayout();
		sections2Grid.setSpacing(true);
		sections2Grid.setWidth("100%");


		final VerticalLayout legislativeLayout = CardInfoRowUtil.createSectionLayout("Legislative Activity");
		politicianLeaderboardUtil.addLegislativeMetrics(legislativeLayout, leader);
		sections2Grid.addComponent(legislativeLayout);

		final VerticalLayout alignmentLayout = CardInfoRowUtil.createSectionLayout("Party Alignment");
		politicianLeaderboardUtil.addPartyAlignmentMetrics(alignmentLayout, leader, ballotSummary);
		sections2Grid.addComponent(alignmentLayout);
		cardContent.addComponent(sections2Grid);

		return cardPanel;
	}

	/**
	 * Find government role for leader.
	 *
	 * @param leader the leader
	 * @return the view riksdagen goverment role member
	 */
	private ViewRiksdagenGovermentRoleMember findGovernmentRoleForLeader(ViewRiksdagenPolitician leader) {
		// Similar to ministry snippet loadActiveGovernmentRoleMembers:
		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);
		final List<ViewRiksdagenGovermentRoleMember> activeGovMembers = govermentRoleMemberDataContainer.findListByProperty(
				new Object[] { Boolean.TRUE }, ViewRiksdagenGovermentRoleMember_.active);

		// Find the government role that matches this leader's personId
		return activeGovMembers.stream()
				.filter(govMember -> govMember.getPersonId().equals(leader.getPersonId()))
				.findFirst().orElse(null);
	}



	/**
	 * Adds the political role metrics.
	 *
	 * @param layout the layout
	 * @param riksdagenPartyRoleMember the riksdagen party role member
	 * @param govMember the gov member
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 * @param experienceSummary
	 */
	private void addPoliticalRoleMetrics(VerticalLayout layout, ViewRiksdagenPartyRoleMember riksdagenPartyRoleMember, ViewRiksdagenGovermentRoleMember govMember,
			ViewRiksdagenPolitician politician, ViewRiksdagenPoliticianBallotSummary ballotSummary, ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

		addPartyExperince(layout, riksdagenPartyRoleMember, govMember, politician);

        // Top Roles
		politicianLeaderboardUtil.addTopRoles(layout, experienceSummary);

		// Top Knowledge Areas
		politicianLeaderboardUtil.addKnowledgeAreas(layout, experienceSummary);

		politicianLeaderboardUtil.addExperienceMetrics(layout,experienceSummary);

		politicianLeaderboardUtil.addPoliticalAnalysisComment(layout, experienceSummary);

	}

	private void addPartyExperince(VerticalLayout layout, ViewRiksdagenPartyRoleMember riksdagenPartyRoleMember,
			ViewRiksdagenGovermentRoleMember govMember, ViewRiksdagenPolitician politician) {
		if (govMember != null) {
			layout.addComponent(CardInfoRowUtil.createInfoRow("Role:", govMember != null ? govMember.getRoleCode() : "N/A", VaadinIcons.INSTITUTION,
					"Current position in Government"));
			layout.addComponent(CardInfoRowUtil.createInfoRow("Career Length Government:",
					String.format(Locale.ENGLISH,"%,d days", govMember != null ? govMember.getTotalDaysServed() : 0),
					VaadinIcons.TIMER, "Years in Government"));
	    } else {
			layout.addComponent(CardInfoRowUtil.createInfoRow("Career Length Parlimanet:",
					String.format(Locale.ENGLISH,"%,d days", politician != null ? politician.getTotalDaysServedParliament() : 0),
					VaadinIcons.TIMER, "Years in Parlimanet"));
	    }

		layout.addComponent(CardInfoRowUtil.createInfoRow("Current Party Role:", riksdagenPartyRoleMember != null ? riksdagenPartyRoleMember.getRoleCode() : "N/A", VaadinIcons.INSTITUTION,
				"Current position in Party"));
		layout.addComponent(CardInfoRowUtil.createInfoRow("Career Length Party Leader:",
				String.format(Locale.ENGLISH,"%,d days", riksdagenPartyRoleMember != null ? riksdagenPartyRoleMember.getTotalDaysServed() : 0),
				VaadinIcons.TIMER, "Years as Party Leader"));
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
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString());
	}

}

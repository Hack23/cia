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
 * distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *  $Id$
 *  $HeadURL$
 */
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import java.util.HashMap;
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
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
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

		createPageHeader(panel, panelContent, "Current Party Leaders Scoreboard", "Leader Performance",
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
		final Map<String, Boolean> partyLeaderMap = computePartyLeaders(politicianMap.keySet());

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
			final Panel cardPanel = createLeaderCard(leader, ballotSummary, governmentBodyByMinistry, reportByMinistry);
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
	 * Compute party leaders.
	 *
	 * @param personIds the person ids
	 * @return the map
	 */
	private Map<String, Boolean> computePartyLeaders(Iterable<String> personIds) {
		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		final Map<String, Boolean> result = new HashMap<>();
		for (final String personId : personIds) {
			final List<ViewRiksdagenPartyRoleMember> roles = partyRoleMemberDataContainer.findListByProperty(
					new Object[] { personId, Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.personId,
					ViewRiksdagenPartyRoleMember_.active);

			final boolean isLeader = roles.stream()
					.anyMatch(role -> role.getRoleCode() != null && "Partiledare".equalsIgnoreCase(role.getRoleCode().trim()));
			result.put(personId, isLeader);
		}
		return result;
	}

	/**
	 * Checks if is party leader.
	 *
	 * @param personId the person id
	 * @return true, if is party leader
	 */
	private boolean isPartyLeader(String personId) {
		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		final List<ViewRiksdagenPartyRoleMember> partyRoles = partyRoleMemberDataContainer.findListByProperty(
				new Object[] { personId, Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.personId,
				ViewRiksdagenPartyRoleMember_.active);

		return partyRoles.stream().anyMatch(r -> "Partiledare".equalsIgnoreCase(r.getRoleCode()));
	}

	/**
	 * Gets the party leader role.
	 *
	 * @param personId the person id
	 * @return the party leader role
	 */
	private ViewRiksdagenPartyRoleMember getPartyLeaderRole(String personId) {
		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		final List<ViewRiksdagenPartyRoleMember> partyRoles = partyRoleMemberDataContainer.findListByProperty(
				new Object[] { personId, Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.personId,
				ViewRiksdagenPartyRoleMember_.active);

		return partyRoles.stream().filter(r -> "Partiledare".equalsIgnoreCase(r.getRoleCode())).findFirst().orElse(null);
	}

	/**
	 * Creates the leader card.
	 *
	 * @param leader the leader
	 * @param ballotSummary the ballot summary
	 * @param governmentBodyByMinistry the government body by ministry
	 * @param reportByMinistry the report by ministry
	 * @return the panel
	 */
	private Panel createLeaderCard(final ViewRiksdagenPolitician leader, final ViewRiksdagenPoliticianBallotSummary ballotSummary,
			final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry) {

		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("leader-baseball-card");
		cardPanel.setSizeFull();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setSizeFull();
		cardPanel.setContent(cardContent);

		// Header
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		final String titleText = "Partiledare " + leader.getFirstName() + " " + leader.getLastName() + " ("
				+ leader.getParty() + ")";
		final Label titleLabel = new Label(titleText);
		titleLabel.addStyleName("card-title");
		titleLabel.setWidthUndefined();
		headerLayout.addComponent(titleLabel);

		cardContent.addComponent(headerLayout);

		// Politician detail link
		cardContent.addComponent(getPageLinkFactory().createPoliticianPageLink(leader));

		// Party link
		final Link partyLink = new Link("Party " + leader.getParty(),
				new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/" + leader.getParty()));
		partyLink.setIcon(VaadinIcons.GROUP);
		cardContent.addComponent(partyLink);

		final boolean isPartyLeader = isPartyLeader(leader.getPersonId());
		if (isPartyLeader) {
			final ViewRiksdagenPartyRoleMember leaderRole = getPartyLeaderRole(leader.getPersonId());
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
				addMinistryRoleSummary(cardContent, govMember, governmentBodyByMinistry, reportByMinistry);
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
		final VerticalLayout politicalRoleLayout = createSectionLayout("Political Role & Influence");
		addPoliticalRoleMetrics(politicalRoleLayout,getPartyLeaderRole(leader.getPersonId()), govMember, leader, ballotSummary);
		sectionsGrid.addComponent(politicalRoleLayout);

		final VerticalLayout performanceLayout = createSectionLayout("Parliamentary Performance");
		addParliamentaryPerformanceMetrics(performanceLayout, leader, ballotSummary);
		sectionsGrid.addComponent(performanceLayout);

	    cardContent.addComponent(sectionsGrid);

		final HorizontalLayout sections2Grid = new HorizontalLayout();
		sections2Grid.setSpacing(true);
		sections2Grid.setWidth("100%");


		final VerticalLayout legislativeLayout = createSectionLayout("Legislative Activity");
		addLegislativeMetrics(legislativeLayout, leader);
		sections2Grid.addComponent(legislativeLayout);

		final VerticalLayout alignmentLayout = createSectionLayout("Party Alignment");
		addPartyAlignmentMetrics(alignmentLayout, leader, ballotSummary);
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
	 * Adds the ministry role summary.
	 *
	 * @param cardLayout the card layout
	 * @param govMember the gov member
	 * @param governmentBodyByMinistry the government body by ministry
	 * @param reportByMinistry the report by ministry
	 */
	private void addMinistryRoleSummary(final VerticalLayout cardLayout,
			final ViewRiksdagenGovermentRoleMember govMember,
			final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry) {

		cardLayout.addComponent(getPageLinkFactory().addMinistryPageLink(govMember.getDetail()));

		final List<GovernmentBodyAnnualSummary> ministryBodies = governmentBodyByMinistry.get(govMember.getDetail());
		if (ministryBodies == null || ministryBodies.isEmpty()) {
			return;
		}

		final int totalHeadCount = ministryBodies.stream().mapToInt(GovernmentBodyAnnualSummary::getAnnualWorkHeadCount)
				.sum();
		final int bodyCount = ministryBodies.size();

		cardLayout.addComponent(createMetricRow(VaadinIcons.GROUP,
				getPageLinkFactory().addMinistryGovermentBodiesPageLink(govMember.getDetail()),
				"Number of government bodies", String.valueOf(bodyCount)));

		cardLayout.addComponent(createMetricRow(VaadinIcons.USER,
				getPageLinkFactory().addMinistryGovermentBodiesHeadcountPageLink(govMember.getDetail()),
				"Total headcount of government bodies", String.valueOf(totalHeadCount)));

		final List<GovernmentBodyAnnualOutcomeSummary> outcomeSummaries = reportByMinistry.get(govMember.getDetail());
		double currentYearIncome = 0;
		double currentYearSpending = 0;
		final int CURRENT_YEAR = 2024;
		final String INKOMSTTITELGRUPPSNAMN = "Inkomsttitelgruppsnamn";
		final String EXPENDITURE_GROUP_NAME = "Utgiftsområdesnamn";

		if (outcomeSummaries != null) {
			final Map<Integer, Double> annualIncome = outcomeSummaries.stream()
					.filter(t -> t.getDescriptionFields().get(INKOMSTTITELGRUPPSNAMN) != null)
					.collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear,
							Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)));

			final Map<Integer, Double> annualSpending = outcomeSummaries.stream()
					.filter(t -> t.getDescriptionFields().get(EXPENDITURE_GROUP_NAME) != null)
					.collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear,
							Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)));

			if (annualIncome.get(CURRENT_YEAR) != null) {
				currentYearIncome = annualIncome.get(CURRENT_YEAR) / 1000;
			}

			if (annualSpending.get(CURRENT_YEAR) != null) {
				currentYearSpending = annualSpending.get(CURRENT_YEAR) / 1000;
			}
		}

		final String incomeStr = String.format(Locale.ENGLISH, "%.2f B SEK", currentYearIncome);
		cardLayout.addComponent(createMetricRow(VaadinIcons.ARROW_UP,
				getPageLinkFactory().addMinistryGovermentBodiesIncomePageLink(govMember.getDetail()),
				"Yearly Income (B SEK)", incomeStr));

		final String spendingStr = String.format(Locale.ENGLISH, "%.2f B SEK", currentYearSpending);
		cardLayout.addComponent(createMetricRow(VaadinIcons.ARROW_DOWN,
				getPageLinkFactory().addMinistrGovermentBodiesSpendingPageLink(govMember.getDetail()),
				"Yearly Spending (B SEK)", spendingStr));
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


	/**
	 * Adds the political role metrics.
	 *
	 * @param layout the layout
	 * @param riksdagenPartyRoleMember the riksdagen party role member
	 * @param govMember the gov member
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 */
	private void addPoliticalRoleMetrics(VerticalLayout layout, ViewRiksdagenPartyRoleMember riksdagenPartyRoleMember, ViewRiksdagenGovermentRoleMember govMember,
			ViewRiksdagenPolitician politician, ViewRiksdagenPoliticianBallotSummary ballotSummary) {

		if (govMember != null) {
			layout.addComponent(createInfoRow("Role:", govMember != null ? govMember.getRoleCode() : "N/A", VaadinIcons.INSTITUTION,
					"Current position in Government"));
			layout.addComponent(createInfoRow("Career Length Government:",
					String.format(Locale.ENGLISH,"%,d days", govMember != null ? govMember.getTotalDaysServed() : 0),
					VaadinIcons.TIMER, "Years in Government"));
	    } else {
			layout.addComponent(createInfoRow("Career Length Parlimanet:",
					String.format(Locale.ENGLISH,"%,d days", politician != null ? politician.getTotalDaysServedParliament() : 0),
					VaadinIcons.TIMER, "Years in Parlimanet"));
	    }

		layout.addComponent(createInfoRow("Current Party Role:", riksdagenPartyRoleMember != null ? riksdagenPartyRoleMember.getRoleCode() : "N/A", VaadinIcons.INSTITUTION,
				"Current position in Party"));
		layout.addComponent(createInfoRow("Career Length Party Leader:",
				String.format(Locale.ENGLISH,"%,d days", riksdagenPartyRoleMember != null ? riksdagenPartyRoleMember.getTotalDaysServed() : 0),
				VaadinIcons.TIMER, "Years as Party Leader"));


		layout.addComponent(
				createInfoRow("Influence Score:",
						String.format(Locale.ENGLISH, "%.1f", ballotSummary != null ? ballotSummary.getVotingConsistencyScore() : 0.0),
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
				createInfoRow("Attendance Rate:",
						String.format(Locale.ENGLISH, "%.1f%%", 100 - (ballotSummary != null ? ballotSummary.getAbsenceRate() : 0.0)),
						VaadinIcons.USER_CHECK, "Session attendance rate"));
		layout.addComponent(createInfoRow("Voting Success:",
				String.format(Locale.ENGLISH, "%.1f%%", ballotSummary != null ? ballotSummary.getSuccessRate() : 0.0),
				VaadinIcons.TROPHY, "Votes on winning side"));
		layout.addComponent(createInfoRow("Activity Level:", politician.getDocActivityLevel(), VaadinIcons.CHART_LINE,
				"Overall engagement level"));
		layout.addComponent(createInfoRow("Analysis Comment:",
				String.valueOf(ballotSummary != null ? ballotSummary.getAnalysisComment() : 0), VaadinIcons.USER_CARD,
				"Analysis Comment"));
	}

	/**
	 * Adds the legislative metrics.
	 *
	 * @param layout the layout
	 * @param politician the politician
	 */
	private void addLegislativeMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician) {

		layout.addComponent(createInfoRow("Documents/Year:",
				String.format(Locale.ENGLISH, "%.1f", politician.getAverageDocsPerYear()), VaadinIcons.FILE_TEXT,
				"Average documents per year"));
		layout.addComponent(createInfoRow("Individual Motions:", String.valueOf(politician.getIndividualMotions()),
				VaadinIcons.USER, "Personal motions submitted"));
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

		layout.addComponent(createInfoRow("Party Loyalty:",
				String.format(Locale.ENGLISH, "%.1f%%", ballotSummary != null ? ballotSummary.getLoyaltyRate() : 0.0),
				VaadinIcons.GROUP, "Party line adherence"));
		layout.addComponent(createInfoRow("Independence Rate:",
				String.format(Locale.ENGLISH, "%.1f%%", ballotSummary != null ? ballotSummary.getRebelRate() : 0.0),
				VaadinIcons.RANDOM, "Votes against party line"));
		layout.addComponent(createInfoRow("Cross-Party Collaboration:",
				String.format(Locale.ENGLISH, "%.1f%%", politician.getCollaborationPercentage()), VaadinIcons.CONNECT,
				"Inter-party cooperation"));
		layout.addComponent(createInfoRow("Multi-Party Motions:", String.valueOf(politician.getMultiPartyMotions()),
				VaadinIcons.USERS, "Cross-party legislative initiatives"));
	}

}

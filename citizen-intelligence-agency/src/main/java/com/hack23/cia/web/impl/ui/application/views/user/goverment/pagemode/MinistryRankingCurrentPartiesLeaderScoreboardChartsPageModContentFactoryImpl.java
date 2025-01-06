package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

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
 * The Class MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl.
 */
@Service
public final class MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl
		extends AbstractMinistryRankingPageModContentFactoryImpl {

	/** The Constant DISPLAY_SIZE_LG_DEVICE. */
	private static final int DISPLAY_SIZE_LG_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_MD_DEVICE. */
	private static final int DISPLAY_SIZE_MD_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_XS_DEVICE. */
	private static final int DISPLAY_SIZE_XS_DEVICE = 12;

	/** The Constant DISPLAYS_SIZE_XM_DEVICE. */
	private static final int DISPLAYS_SIZE_XM_DEVICE = 6;

	/** The Constant CURRENT_YEAR. */
	private static final int CURRENT_YEAR = 2024;

	/** The esv api. */
	private final EsvApi esvApi;

	@Autowired
	private PoliticianLeaderboardUtil politicianLeaderboardUtil;


	/**
	 * Instantiates a new ministry ranking current parties leader scoreboard charts page mod content factory impl.
	 *
	 * @param esvApi the esv api
	 */
	public MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl(final EsvApi esvApi) {
		super();
		this.esvApi = esvApi;
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
		panel.setSizeFull();

		final VerticalLayout panelContent = createPanelContent();
		panelContent.setSizeFull();
		panel.setContent(panelContent);

		getMinistryRankingMenuItemFactory().createMinistryRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		CardInfoRowUtil.createPageHeader(panel, panelContent, "Ministry Rankings", "Leader Scoreboard",
				"Visual representation of ministry leaders and their performance.");

		final ResponsiveRow row = RowUtil.createGridLayout(panelContent);
		row.setSizeFull();

		final List<ViewRiksdagenGovermentRoleMember> activeGovMembers = loadActiveGovernmentRoleMembers();
		final Map<String, List<ViewRiksdagenPolitician>> activePoliticianMap = loadActivePoliticiansByPersonId();

		final Map<String, Boolean> partyLeaderMap = PartyLeaderUtil.computePartyLeaders(getApplicationManager(), activePoliticianMap.keySet());

		// Sort roles
		activeGovMembers.sort((a, b) -> {
			final boolean aLeader = partyLeaderMap.getOrDefault(a.getPersonId(), false);
			final boolean bLeader = partyLeaderMap.getOrDefault(b.getPersonId(), false);
			final int aPriority = getRolePriority(a.getRoleCode(), aLeader);
			final int bPriority = getRolePriority(b.getRoleCode(), bLeader);
			return Integer.compare(aPriority, bPriority);
		});

		final Map<Integer, List<GovernmentBodyAnnualSummary>> dataMap = esvApi.getData();
		final List<GovernmentBodyAnnualSummary> currentYearGovernmentBodies = dataMap.get(CURRENT_YEAR);
		final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry = currentYearGovernmentBodies
				.stream().collect(Collectors.groupingBy(GovernmentBodyAnnualSummary::getMinistry));

		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry = esvApi
				.getGovernmentBodyReportByMinistry();

		for (final ViewRiksdagenGovermentRoleMember govMember : activeGovMembers) {
			final ViewRiksdagenPolitician politician = activePoliticianMap.get(govMember.getPersonId()).get(0);
			final ViewRiksdagenPoliticianBallotSummary ballotSummary = getApplicationManager()
					.getDataContainer(ViewRiksdagenPoliticianBallotSummary.class).load(govMember.getPersonId());
		    final ViewRiksdagenPoliticianExperienceSummary experienceSummary = getApplicationManager().getDataContainer(ViewRiksdagenPoliticianExperienceSummary.class).load(govMember.getPersonId());


			final Panel cardPanel = createBaseballStyleCard(govMember, politician, ballotSummary, governmentBodyByMinistry,
					reportByMinistry,experienceSummary);

			// Responsive column rules
			row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
					DISPLAY_SIZE_LG_DEVICE).withComponent(cardPanel);
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;
	}

	/**
	 * Load active government role members.
	 *
	 * @return the list
	 */
	private List<ViewRiksdagenGovermentRoleMember> loadActiveGovernmentRoleMembers() {
		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);
		return govermentRoleMemberDataContainer.findListByProperty(new Object[] { Boolean.TRUE },
				ViewRiksdagenGovermentRoleMember_.active);
	}

	/**
	 * Load active politicians by person id.
	 *
	 * @return the map
	 */
	private Map<String, List<ViewRiksdagenPolitician>> loadActivePoliticiansByPersonId() {
		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPolitician.class);
		final List<ViewRiksdagenPolitician> activePoliticians = politicianDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.activeGovernment);
		return activePoliticians.stream().collect(Collectors.groupingBy(ViewRiksdagenPolitician::getPersonId));
	}

	/**
	 * Gets the role priority.
	 *
	 * @param role the role
	 * @param isPartyLeader the is party leader
	 * @return the role priority
	 */
	private int getRolePriority(String role, boolean isPartyLeader) {
		final String roleNormalized = role.toLowerCase(Locale.ROOT).trim();
		if ("statsminister".equals(roleNormalized)) {
			return 1;
		} else if (isPartyLeader) {
			return 2;
		} else if (roleNormalized.endsWith("minister")) {
			return 3;
		} else if ("statsråd".equalsIgnoreCase(roleNormalized)) {
			return 4;
		} else {
			return 5;
		}
	}

	/**
	 * Creates the baseball style card.
	 *
	 * @param govMember the gov member
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 * @param governmentBodyByMinistry the government body by ministry
	 * @param reportByMinistry the report by ministry
	 * @param experienceSummary
	 * @return the panel
	 */
	private Panel createBaseballStyleCard(ViewRiksdagenGovermentRoleMember govMember,
			ViewRiksdagenPolitician politician, ViewRiksdagenPoliticianBallotSummary ballotSummary, Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry, ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("leader-baseball-card");
		cardPanel.setSizeFull();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setSizeFull();
		cardPanel.setContent(cardContent);

		 CardInfoRowUtil.createCardHeader(cardContent,govMember.getRoleCode() + " " + govMember.getFirstName() + " " + govMember.getLastName()
			+ " (" + govMember.getParty() + ")");
		cardContent.addComponent(getPageLinkFactory().createPoliticianPageLink(politician));

		final Link pageLink = new Link("Party " + politician.getParty(),
				new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/" + politician.getParty()));
		pageLink.setId(ViewAction.VISIT_PARTY_VIEW.name() + "/" + politician.getParty());
		pageLink.setIcon(VaadinIcons.GROUP);

		cardContent.addComponent(pageLink);

		final boolean isPartyLeader = PartyLeaderUtil.isPartyLeader(getApplicationManager(), politician.getPersonId());
		if (isPartyLeader) {
			final ViewRiksdagenPartyRoleMember leaderRole = PartyLeaderUtil.getPartyLeaderRole(getApplicationManager(), politician.getPersonId());
			if (leaderRole != null) {
				final Label subHeader = new Label(
						"Partiledare (" + govMember.getParty() + ") since " + leaderRole.getFromDate());
				subHeader.addStyleName("card-subtitle");
				cardContent.addComponent(subHeader);
			}
		}

		// After creating the divider following the header/subtitle
		// We create a vertical layout to hold Tenure and Experience on separate rows
		final VerticalLayout statsContainer = new VerticalLayout();
		statsContainer.setSpacing(false); // Less space between rows
		statsContainer.addStyleName("card-stats-container");
		statsContainer.setWidth("100%");

		// Tenure Row
		final HorizontalLayout tenureLayout = new HorizontalLayout();
		tenureLayout.setSpacing(true);
		tenureLayout.addStyleName("card-tenure");
		final Label tenureIcon = new Label(VaadinIcons.CLOCK.getHtml(), ContentMode.HTML);
		tenureIcon.setDescription("Tenure in days");

		final Label tenureLabel = new Label("Tenure:");
		tenureLabel.addStyleName("card-tenure-text");

		final Label tenureValue = new Label(govMember.getTotalDaysServed() + " days");
		tenureValue.addStyleName("card-tenure-value");

		tenureLayout.addComponents(tenureIcon, tenureLabel, tenureValue);
		statsContainer.addComponent(tenureLayout);

		// Experience Row
		final HorizontalLayout experienceLayout = new HorizontalLayout();
		experienceLayout.setSpacing(true);
		experienceLayout.addStyleName("card-experience-section");
		final Label expIcon = new Label(VaadinIcons.USER_CHECK.getHtml(), ContentMode.HTML);
		expIcon.setDescription("Political Experience");

		final Label expLabel = new Label("Experience:");
		expLabel.addStyleName("card-experience-text");

		final int govYears = (int) (politician.getTotalDaysServedGovernment() / 365);
		final int partyYears = (int) (politician.getTotalDaysServedParty() / 365);
		final int parliamentYears = (int) (politician.getTotalDaysServedParliament() / 365);
		final Label expValue = new Label(
				"Goverment: " + govYears + "y, Party: " + partyYears + "y, Parliament: " + parliamentYears + "y");
		expValue.addStyleName("card-experience-value");

		experienceLayout.addComponents(expIcon, expLabel, expValue);
		statsContainer.addComponent(experienceLayout);

		// Add the statsContainer to the cardContent
		cardContent.addComponent(statsContainer);

		// Create grid for the four sections
		final HorizontalLayout sectionsGrid = new HorizontalLayout();
		sectionsGrid.setSpacing(true);
		sectionsGrid.setWidth("100%");

		// Add the four main sections
		final VerticalLayout politicalRoleLayout = CardInfoRowUtil.createSectionLayout("Political Role & Influence");
		addPoliticalRoleMetrics(politicalRoleLayout, govMember, politician, ballotSummary, experienceSummary);
		sectionsGrid.addComponent(politicalRoleLayout);

		final VerticalLayout performanceLayout = CardInfoRowUtil.createSectionLayout("Parliamentary Performance");
		politicianLeaderboardUtil.addParliamentaryPerformanceMetrics(performanceLayout, politician, ballotSummary);
		sectionsGrid.addComponent(performanceLayout);

	    cardContent.addComponent(sectionsGrid);

		final HorizontalLayout sections2Grid = new HorizontalLayout();
		sections2Grid.setSpacing(true);
		sections2Grid.setWidth("100%");


		final VerticalLayout legislativeLayout = CardInfoRowUtil.createSectionLayout("Legislative Activity");
		politicianLeaderboardUtil.addLegislativeMetrics(legislativeLayout, politician);
		sections2Grid.addComponent(legislativeLayout);

		final VerticalLayout alignmentLayout = CardInfoRowUtil.createSectionLayout("Party Alignment");
		politicianLeaderboardUtil.addPartyAlignmentMetrics(alignmentLayout, politician, ballotSummary);
		sections2Grid.addComponent(alignmentLayout);
		cardContent.addComponent(sections2Grid);

		politicianLeaderboardUtil.addMinistryRoleSummary(cardContent, govMember, governmentBodyByMinistry, reportByMinistry);

		return cardPanel;
	}


	/**
	 * Adds the political role metrics.
	 *
	 * @param layout the layout
	 * @param govMember the gov member
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 * @param experienceSummary
	 */
	private void addPoliticalRoleMetrics(VerticalLayout layout, ViewRiksdagenGovermentRoleMember govMember,
			ViewRiksdagenPolitician politician, ViewRiksdagenPoliticianBallotSummary ballotSummary, ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

		layout.addComponent(CardInfoRowUtil.createInfoRow("Current Role:", govMember.getRoleCode(), VaadinIcons.INSTITUTION,
				"Current position in parliament"));
			layout.addComponent(CardInfoRowUtil.createInfoRow("Career Length:",
				String.format(Locale.ENGLISH,"%,d days", govMember.getTotalDaysServed()),
				VaadinIcons.TIMER, "Years in parliament"));
			layout.addComponent(CardInfoRowUtil.createInfoRow("Total Propositions:",
					String.format(Locale.ENGLISH,"%,d", govMember.getTotalPropositions()),
					VaadinIcons.GROUP, "Total Propositions"));
			layout.addComponent(CardInfoRowUtil.createInfoRow("Total Government Bills:",
					String.format(Locale.ENGLISH,"%,d", govMember.getTotalGovernmentBills()),
					VaadinIcons.GROUP, "Total Government Bills"));

			politicianLeaderboardUtil.addTopRoles(layout, experienceSummary);
			politicianLeaderboardUtil.addKnowledgeAreas(layout, experienceSummary);
			politicianLeaderboardUtil.addExperienceMetrics(layout,experienceSummary);
			politicianLeaderboardUtil.addPoliticalAnalysisComment(layout, experienceSummary);

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
				&& parameters.contains(ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());
	}

}

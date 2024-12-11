package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
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

@Service
public final class MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl
		extends AbstractMinistryRankingPageModContentFactoryImpl {

	private static final int DISPLAY_SIZE_LG_DEVICE = 4;
	private static final int DISPLAY_SIZE_MD_DEVICE = 4;
	private static final int DISPLAY_SIZE_XS_DEVICE = 12;
	private static final int DISPLAYS_SIZE_XM_DEVICE = 6;

	private static final String EXPENDITURE_GROUP_NAME = "Utgiftsområdesnamn";
	private static final String INKOMSTTITELGRUPPSNAMN = "Inkomsttitelgruppsnamn";
	private static final int CURRENT_YEAR = 2024;

	private final EsvApi esvApi;

	public MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl(final EsvApi esvApi) {
		super();
		this.esvApi = esvApi;
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		panel.setSizeFull();

		final VerticalLayout panelContent = createPanelContent();
		panelContent.setSizeFull();
		panel.setContent(panelContent);

		getMinistryRankingMenuItemFactory().createMinistryRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		createPageHeader(panel, panelContent, "Ministry Rankings", "Leader Scoreboard",
				"Visual representation of ministry leaders and their performance.");

		final ResponsiveRow row = RowUtil.createGridLayout(panelContent);
		row.setSizeFull();

		final List<ViewRiksdagenGovermentRoleMember> activeGovMembers = loadActiveGovernmentRoleMembers();
		final Map<String, List<ViewRiksdagenPolitician>> activePoliticianMap = loadActivePoliticiansByPersonId();

		Map<String, Boolean> partyLeaderMap = computePartyLeaders(activePoliticianMap.keySet());

		// Sort roles
		activeGovMembers.sort((a, b) -> {
			boolean aLeader = partyLeaderMap.getOrDefault(a.getPersonId(), false);
			boolean bLeader = partyLeaderMap.getOrDefault(b.getPersonId(), false);
			int aPriority = getRolePriority(a.getRoleCode(), aLeader);
			int bPriority = getRolePriority(b.getRoleCode(), bLeader);
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
			final Panel cardPanel = createBaseballStyleCard(govMember, politician, governmentBodyByMinistry,
					reportByMinistry);

			// Responsive column rules
			row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
					DISPLAY_SIZE_LG_DEVICE).withComponent(cardPanel);
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;
	}

	private List<ViewRiksdagenGovermentRoleMember> loadActiveGovernmentRoleMembers() {
		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);
		return govermentRoleMemberDataContainer.findListByProperty(new Object[] { Boolean.TRUE },
				ViewRiksdagenGovermentRoleMember_.active);
	}

	private Map<String, List<ViewRiksdagenPolitician>> loadActivePoliticiansByPersonId() {
		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPolitician.class);
		final List<ViewRiksdagenPolitician> activePoliticians = politicianDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.activeGovernment);
		return activePoliticians.stream().collect(Collectors.groupingBy(ViewRiksdagenPolitician::getPersonId));
	}

	private Map<String, Boolean> computePartyLeaders(Iterable<String> personIds) {
		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		Map<String, Boolean> result = new HashMap<>();
		for (String personId : personIds) {
			List<ViewRiksdagenPartyRoleMember> roles = partyRoleMemberDataContainer.findListByProperty(
					new Object[] { personId, Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.personId,
					ViewRiksdagenPartyRoleMember_.active);

			boolean isLeader = roles.stream().anyMatch(
					role -> role.getRoleCode() != null && "Partiledare".equalsIgnoreCase(role.getRoleCode().trim()));

			result.put(personId, isLeader);
		}
		return result;
	}

	private int getRolePriority(String role, boolean isPartyLeader) {
		String roleNormalized = role.toLowerCase(Locale.ROOT).trim();
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

	private Panel createBaseballStyleCard(ViewRiksdagenGovermentRoleMember govMember,
			ViewRiksdagenPolitician politician, Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry) {

		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("leader-baseball-card");
		cardPanel.setSizeFull();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setSizeFull();
		cardPanel.setContent(cardContent);

		// Header Section
		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		String titleText = govMember.getRoleCode() + " " + govMember.getFirstName() + " " + govMember.getLastName()
				+ " (" + govMember.getParty() + ")";
		Label titleLabel = new Label(titleText);
		titleLabel.addStyleName("card-title");
		titleLabel.setWidthUndefined();
		headerLayout.addComponent(titleLabel);

		cardContent.addComponent(headerLayout);
		cardContent.addComponent(getPageLinkFactory().createPoliticianPageLink(politician));

		final Link pageLink = new Link("Party " + politician.getParty(),
				new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/" + politician.getParty()));
		pageLink.setId(ViewAction.VISIT_PARTY_VIEW.name() + "/" + politician.getParty());
		pageLink.setIcon(VaadinIcons.GROUP);

		cardContent.addComponent(pageLink);

		boolean isPartyLeader = isPartyLeader(politician.getPersonId());
		if (isPartyLeader) {
			ViewRiksdagenPartyRoleMember leaderRole = getPartyLeaderRole(politician.getPersonId());
			if (leaderRole != null) {
				Label subHeader = new Label(
						"Partiledare (" + govMember.getParty() + ") since " + leaderRole.getFromDate());
				subHeader.addStyleName("card-subtitle");
				cardContent.addComponent(subHeader);
			}
		}

		// After creating the divider following the header/subtitle
		// We create a vertical layout to hold Tenure and Experience on separate rows
		VerticalLayout statsContainer = new VerticalLayout();
		statsContainer.setSpacing(false); // Less space between rows
		statsContainer.addStyleName("card-stats-container");
		statsContainer.setWidth("100%");

		// Tenure Row
		HorizontalLayout tenureLayout = new HorizontalLayout();
		tenureLayout.setSpacing(true);
		tenureLayout.addStyleName("card-tenure");
		Label tenureIcon = new Label(VaadinIcons.CLOCK.getHtml(), ContentMode.HTML);
		tenureIcon.setDescription("Tenure in days");

		Label tenureLabel = new Label("Tenure:");
		tenureLabel.addStyleName("card-tenure-text");

		Label tenureValue = new Label(govMember.getTotalDaysServed() + " days");
		tenureValue.addStyleName("card-tenure-value");

		tenureLayout.addComponents(tenureIcon, tenureLabel, tenureValue);
		statsContainer.addComponent(tenureLayout);

		// Experience Row
		HorizontalLayout experienceLayout = new HorizontalLayout();
		experienceLayout.setSpacing(true);
		experienceLayout.addStyleName("card-experience-section");
		Label expIcon = new Label(VaadinIcons.USER_CHECK.getHtml(), ContentMode.HTML);
		expIcon.setDescription("Political Experience");

		Label expLabel = new Label("Experience:");
		expLabel.addStyleName("card-experience-text");

		int govYears = (int) (politician.getTotalDaysServedGovernment() / 365);
		int partyYears = (int) (politician.getTotalDaysServedParty() / 365);
		int parliamentYears = (int) (politician.getTotalDaysServedParliament() / 365);
		Label expValue = new Label(
				"Goverment: " + govYears + "y, Party: " + partyYears + "y, Parliament: " + parliamentYears + "y");
		expValue.addStyleName("card-experience-value");

		experienceLayout.addComponents(expIcon, expLabel, expValue);
		statsContainer.addComponent(experienceLayout);

		// Add the statsContainer to the cardContent
		cardContent.addComponent(statsContainer);

//        // Another divider before ministry stats
//        Label divider2 = new Label();
//        divider2.addStyleName("card-divider");
//        divider2.setWidth("100%");
//        cardContent.addComponent(divider2);

		addMinistryRoleSummary(cardContent, govMember, governmentBodyByMinistry, reportByMinistry);

		return cardPanel;
	}

	private boolean isPartyLeader(String personId) {
		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		final List<ViewRiksdagenPartyRoleMember> partyRoles = partyRoleMemberDataContainer.findListByProperty(
				new Object[] { personId, Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.personId,
				ViewRiksdagenPartyRoleMember_.active);

		return partyRoles.stream().anyMatch(r -> "Partiledare".equalsIgnoreCase(r.getRoleCode()));
	}

	private ViewRiksdagenPartyRoleMember getPartyLeaderRole(String personId) {
		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		final List<ViewRiksdagenPartyRoleMember> partyRoles = partyRoleMemberDataContainer.findListByProperty(
				new Object[] { personId, Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.personId,
				ViewRiksdagenPartyRoleMember_.active);

		return partyRoles.stream().filter(r -> "Partiledare".equalsIgnoreCase(r.getRoleCode())).findFirst()
				.orElse(null);
	}

	private void addMinistryRoleSummary(final VerticalLayout cardLayout,
			final ViewRiksdagenGovermentRoleMember govMember,
			final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry) {

		cardLayout.addComponent(getPageLinkFactory().addMinistryPageLink(govMember.getDetail()));

		final List<GovernmentBodyAnnualSummary> ministryBodies = governmentBodyByMinistry.get(govMember.getDetail());
		final int totalHeadCount = ministryBodies.stream().mapToInt(GovernmentBodyAnnualSummary::getAnnualWorkHeadCount)
				.sum();

		// Bodies count
		int bodyCount = ministryBodies.size();
		cardLayout.addComponent(createMetricRow(VaadinIcons.GROUP,
				getPageLinkFactory().addMinistryGovermentBodiesPageLink(govMember.getDetail(), bodyCount),
				"Number of government bodies", String.valueOf(bodyCount) // display the count separately
		));

		// Headcount
		cardLayout.addComponent(createMetricRow(VaadinIcons.USER,
				getPageLinkFactory().addMinistryGovermentBodiesHeadcountPageLink(govMember.getDetail(), totalHeadCount),
				"Total headcount of government bodies", String.valueOf(totalHeadCount) // display the headcount
																						// separately
		));

		final List<GovernmentBodyAnnualOutcomeSummary> outcomeSummaries = reportByMinistry.get(govMember.getDetail());
		double currentYearIncome = 0;
		double currentYearSpending = 0;
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

		// Income
		String incomeStr = String.format(Locale.ROOT,"%.2f B SEK", currentYearIncome);
		cardLayout.addComponent(createMetricRow(VaadinIcons.ARROW_UP,
				getPageLinkFactory().addMinistryGovermentBodiesIncomePageLink(govMember.getDetail(), currentYearIncome),
				"Yearly Income (B SEK)", incomeStr // display income outside link
		));

		// Spending
		String spendingStr = String.format(Locale.ROOT,"%.2f B SEK", currentYearSpending);
		cardLayout.addComponent(
				createMetricRow(VaadinIcons.ARROW_DOWN, getPageLinkFactory().addMinistrGovermentBodiesSpendingPageLink(
						govMember.getDetail(), currentYearSpending), "Yearly Spending (B SEK)", spendingStr // display
																											// spending
																											// outside
																											// link
				));
	}

	private HorizontalLayout createMetricRow(VaadinIcons icon, com.vaadin.ui.Component linkComponent,
			String description, String valueText) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("metric-label");
		layout.setWidthUndefined();

		Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
		iconLabel.setDescription(description);

		// Value displayed outside of the link
		Label valueLabel = null;
		if (valueText != null && !valueText.isEmpty()) {
			valueLabel = new Label(valueText);
			valueLabel.addStyleName("metric-value");
		}

		layout.addComponent(iconLabel);
		layout.addComponent(linkComponent);
		if (valueLabel != null) {
			layout.addComponent(valueLabel);
		}

		return layout;
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());
	}

}

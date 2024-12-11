package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import java.util.List;
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
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Improved version of the Ministry Ranking Current Parties Leader Scoreboard
 * Charts Page with HTML content mode and proper type handling.
 */
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

	@Autowired
	private EsvApi esvApi;

	public MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getMinistryRankingMenuItemFactory().createMinistryRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		createPageHeader(panel, panelContent, "Ministry Rankings", "Leader Scoreboard",
				"Visual representation of ministry leaders and their performance.");

		final ResponsiveRow row = RowUtil.createGridLayout(panelContent);

		final List<ViewRiksdagenGovermentRoleMember> activeGovMembers = loadActiveGovernmentRoleMembers();
		final Map<String, List<ViewRiksdagenPolitician>> activePoliticianMap = loadActivePoliticiansByPersonId();

		final Map<Integer, List<GovernmentBodyAnnualSummary>> dataMap = esvApi.getData();
		final List<GovernmentBodyAnnualSummary> currentYearGovernmentBodies = dataMap.get(CURRENT_YEAR);
		final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry = currentYearGovernmentBodies
				.stream().collect(Collectors.groupingBy(GovernmentBodyAnnualSummary::getMinistry));

		for (final ViewRiksdagenGovermentRoleMember govMember : activeGovMembers) {
			final ViewRiksdagenPolitician politician = activePoliticianMap.get(govMember.getPersonId()).get(0);
			createGovernmentMemberDashboardCard(row, governmentBodyByMinistry, govMember, politician);
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
		final List<ViewRiksdagenPolitician> activePoliticians = politicianDataContainer.findListByProperty(
				new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.activeGovernment);
		return activePoliticians.stream().collect(Collectors.groupingBy(ViewRiksdagenPolitician::getPersonId));
	}

	private void createGovernmentMemberDashboardCard(final ResponsiveRow row,
			final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			final ViewRiksdagenGovermentRoleMember govMember, final ViewRiksdagenPolitician politician) {

		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("leader-card");
		cardPanel.setSizeUndefined();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardLayout = new VerticalLayout();
		cardLayout.setWidth(100, Unit.PERCENTAGE);
		cardLayout.setMargin(true);
		cardLayout.setSpacing(true);

		// Politician link
		cardLayout.addComponent(getPageLinkFactory().createPoliticianPageLink(politician));

		// Leader name/title
		final Label leaderName = new Label(govMember.getRoleCode() + " " + govMember.getFirstName() + " "
				+ govMember.getLastName() + " (" + govMember.getParty() + ")");
		leaderName.addStyleName("h2");
		leaderName.addStyleName("leader-card-header");
		cardLayout.addComponent(leaderName);

		// Add party role if available
		addPartyRoleInformation(cardLayout, politician, govMember.getParty());

		// Add political experience and tenure info
		addExperienceAndTenureInfo(cardLayout, govMember, politician);

		// Add ministry info
		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry = esvApi
				.getGovernmentBodyReportByMinistry();
		addMinistryRoleSummary(cardLayout, govMember, governmentBodyByMinistry, reportByMinistry);

		cardPanel.setContent(cardLayout);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(cardPanel);
	}

	private void addPartyRoleInformation(final VerticalLayout cardLayout, final ViewRiksdagenPolitician politician,
			final String party) {
		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		final List<ViewRiksdagenPartyRoleMember> partyRoles = partyRoleMemberDataContainer.findListByProperty(
				new Object[] { politician.getPersonId(), Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.personId,
				ViewRiksdagenPartyRoleMember_.active);

		if (!partyRoles.isEmpty()) {
			final ViewRiksdagenPartyRoleMember currentPartyRole = partyRoles.get(0);
			final Label partyRoleLabel = new Label(
					currentPartyRole.getRoleCode() + " (" + party + ") since " + currentPartyRole.getFromDate());
			partyRoleLabel.addStyleName("h3");
			partyRoleLabel.addStyleName("light-text");
			cardLayout.addComponent(partyRoleLabel);
		}
	}

	private void addExperienceAndTenureInfo(final VerticalLayout cardLayout,
			final ViewRiksdagenGovermentRoleMember govMember, final ViewRiksdagenPolitician politician) {

		// Tenure label
		final Label tenureLabel = new Label(VaadinIcons.CLOCK.getHtml() + " Held position for "
				+ govMember.getTotalDaysServed() + " days in " + govMember.getDetail());
		tenureLabel.setContentMode(ContentMode.HTML);
		tenureLabel.setWidth(100, Unit.PERCENTAGE);
		cardLayout.addComponent(tenureLabel);

		// Years of experience
		int govYears = (int) (politician.getTotalDaysServedGovernment() / 365);
		int partyYears = (int) (politician.getTotalDaysServedParty() / 365);
		int parliamentYears = (int) (politician.getTotalDaysServedParliament() / 365);

		final Label experienceLabel = new Label(VaadinIcons.USER_CHECK.getHtml() + " Political Experience: " + govYears
				+ " yr Gov, " + partyYears + " yr Party, " + parliamentYears + " yr Parliament");
		experienceLabel.setContentMode(ContentMode.HTML);
		experienceLabel.setWidth(100, Unit.PERCENTAGE);
		cardLayout.addComponent(experienceLabel);
	}

	private void addMinistryRoleSummary(final VerticalLayout cardLayout, final ViewRiksdagenGovermentRoleMember govMember,
			final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry) {

		// Ministry link
		cardLayout.addComponent(getPageLinkFactory().addMinistryPageLink(govMember.getDetail()));

		final List<GovernmentBodyAnnualSummary> ministryBodies = governmentBodyByMinistry.get(govMember.getDetail());
		final int totalHeadCount = ministryBodies.stream().mapToInt(GovernmentBodyAnnualSummary::getAnnualWorkHeadCount)
				.sum();

		// Government Bodies count label
		Label bodiesCountLabel = new Label(
				VaadinIcons.GROUP.getHtml() + " "
						+ getPageLinkFactory().addMinistryGovermentBodiesPageLink(govMember.getDetail(), ministryBodies.size()).getCaption());
		bodiesCountLabel.setContentMode(ContentMode.HTML);
		cardLayout.addComponent(bodiesCountLabel);

		// Headcount info
		Label headcountLabel = new Label(
				VaadinIcons.USER.getHtml() + " "
						+ getPageLinkFactory().addMinistryGovermentBodiesHeadcountPageLink(govMember.getDetail(), totalHeadCount)
								.getCaption());
		headcountLabel.setContentMode(ContentMode.HTML);
		cardLayout.addComponent(headcountLabel);

		// Financial info
		final List<GovernmentBodyAnnualOutcomeSummary> outcomeSummaries = reportByMinistry.get(govMember.getDetail());
		final Map<Integer, Double> annualIncome = outcomeSummaries.stream()
				.filter(t -> t.getDescriptionFields().get(INKOMSTTITELGRUPPSNAMN) != null)
				.collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear,
						Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)));

		final Map<Integer, Double> annualSpending = outcomeSummaries.stream()
				.filter(t -> t.getDescriptionFields().get(EXPENDITURE_GROUP_NAME) != null)
				.collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear,
						Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)));

		final double currentYearIncome = annualIncome.get(CURRENT_YEAR) / 1000;
		final double currentYearSpending = annualSpending.get(CURRENT_YEAR) / 1000;

		Label incomeLabel = new Label(VaadinIcons.ARROW_UP.getHtml() + " "
				+ getPageLinkFactory().addMinistryGovermentBodiesIncomePageLink(govMember.getDetail(), currentYearIncome)
						.getCaption());
		incomeLabel.setContentMode(ContentMode.HTML);
		cardLayout.addComponent(incomeLabel);

		Label spendingLabel = new Label(VaadinIcons.ARROW_DOWN.getHtml() + " "
				+ getPageLinkFactory().addMinistrGovermentBodiesSpendingPageLink(govMember.getDetail(), currentYearSpending)
						.getCaption());
		spendingLabel.setContentMode(ContentMode.HTML);
		cardLayout.addComponent(spendingLabel);
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());
	}

}

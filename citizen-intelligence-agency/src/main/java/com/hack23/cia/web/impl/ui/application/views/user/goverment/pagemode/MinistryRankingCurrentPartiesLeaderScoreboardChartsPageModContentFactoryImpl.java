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
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
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

		final Map<String, Boolean> partyLeaderMap = computePartyLeaders(activePoliticianMap.keySet());

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

		final Map<String, Boolean> result = new HashMap<>();
		for (final String personId : personIds) {
			final List<ViewRiksdagenPartyRoleMember> roles = partyRoleMemberDataContainer.findListByProperty(
					new Object[] { personId, Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.personId,
					ViewRiksdagenPartyRoleMember_.active);

			final boolean isLeader = roles.stream().anyMatch(
					role -> role.getRoleCode() != null && "Partiledare".equalsIgnoreCase(role.getRoleCode().trim()));

			result.put(personId, isLeader);
		}
		return result;
	}

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
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		final String titleText = govMember.getRoleCode() + " " + govMember.getFirstName() + " " + govMember.getLastName()
				+ " (" + govMember.getParty() + ")";
		final Label titleLabel = new Label(titleText);
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

		final boolean isPartyLeader = isPartyLeader(politician.getPersonId());
		if (isPartyLeader) {
			final ViewRiksdagenPartyRoleMember leaderRole = getPartyLeaderRole(politician.getPersonId());
			if (leaderRole != null) {
				final Label subHeader = new Label(
						"Partiledare (" + govMember.getParty() + ") since " + leaderRole.getFromDate());
				subHeader.addStyleName("card-subtitle");
				cardContent.addComponent(subHeader);
			}
		}

		// Create grid for the four sections
		final HorizontalLayout sectionsGrid = new HorizontalLayout();
		sectionsGrid.setSpacing(true);
		sectionsGrid.setWidth("100%");
		
		// Add the four main sections
		final VerticalLayout politicalRoleLayout = createSectionLayout("Political Role & Influence");
		addPoliticalRoleMetrics(politicalRoleLayout, govMember, politician, null);
		sectionsGrid.addComponent(politicalRoleLayout);
		
		final VerticalLayout performanceLayout = createSectionLayout("Parliamentary Performance");
		addParliamentaryPerformanceMetrics(performanceLayout, politician, null);
		sectionsGrid.addComponent(performanceLayout);
		
		final VerticalLayout legislativeLayout = createSectionLayout("Legislative Activity");
		addLegislativeMetrics(legislativeLayout, politician);
		sectionsGrid.addComponent(legislativeLayout);
		
		final VerticalLayout alignmentLayout = createSectionLayout("Party Alignment");
		addPartyAlignmentMetrics(alignmentLayout, politician, null);
		sectionsGrid.addComponent(alignmentLayout);

		cardContent.addComponent(sectionsGrid);

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
		final int bodyCount = ministryBodies.size();
		cardLayout.addComponent(createMetricRow(VaadinIcons.GROUP,
				getPageLinkFactory().addMinistryGovermentBodiesPageLink(govMember.getDetail()),
				"Number of government bodies", String.valueOf(bodyCount) // display the count separately
		));

		// Headcount
		cardLayout.addComponent(createMetricRow(VaadinIcons.USER,
				getPageLinkFactory().addMinistryGovermentBodiesHeadcountPageLink(govMember.getDetail()),
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
		final String incomeStr = String.format(Locale.ENGLISH,"%.2f B SEK", currentYearIncome);
		cardLayout.addComponent(createMetricRow(VaadinIcons.ARROW_UP,
				getPageLinkFactory().addMinistryGovermentBodiesIncomePageLink(govMember.getDetail()),
				"Yearly Income (B SEK)", incomeStr // display income outside link
		));

		// Spending
		final String spendingStr = String.format(Locale.ENGLISH,"%.2f B SEK", currentYearSpending);
		cardLayout.addComponent(
				createMetricRow(VaadinIcons.ARROW_DOWN, getPageLinkFactory().addMinistrGovermentBodiesSpendingPageLink(
						govMember.getDetail()), "Yearly Spending (B SEK)", spendingStr // display
																											// spending
																											// outside
																											// link
				));
	}

	private HorizontalLayout createMetricRow(VaadinIcons icon, com.vaadin.ui.Component linkComponent,
			String description, String valueText) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("metric-label");
		layout.setWidthUndefined();

		final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
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

	private void addPoliticalRoleMetrics(VerticalLayout layout, ViewRiksdagenGovermentRoleMember govMember, 
	      ViewRiksdagenPolitician politician, ViewRiksdagenPoliticianBallotSummary ballotSummary) {
	      
	      // Add existing tenure info
	      // Then add new metrics:
	      layout.addComponent(createInfoRow("Political Experience:", 
	          String.format("Gov: %dy, Party: %dy, Parliament: %dy",
	              politician.getTotalDaysServedGovernment()/365,
	              politician.getTotalDaysServedParty()/365, 
	              politician.getTotalDaysServedParliament()/365),
	          VaadinIcons.TROPHY, "Years served in different roles"));

	      layout.addComponent(createInfoRow("Current Assignments:", 
	          String.valueOf(politician.getCurrentAssignments()),
	          VaadinIcons.USER_CARD, "Current political assignments"));

	      if (ballotSummary != null) {
	          layout.addComponent(createInfoRow("Voting Consistency:", 
	              String.format(Locale.ENGLISH,"%.1f%%", ballotSummary.getVotingConsistencyScore()),
	              VaadinIcons.CHECK, "Consistency in voting patterns"));
	      }
	  }

	private void addParliamentaryPerformanceMetrics(VerticalLayout layout,
	      ViewRiksdagenPolitician politician, ViewRiksdagenPoliticianBallotSummary ballotSummary) {

	      if (ballotSummary != null) {
	          layout.addComponent(createInfoRow("Attendance Rate:", 
	              String.format(Locale.ENGLISH,"%.1f%%", 100 - ballotSummary.getAbsenceRate()),
	              VaadinIcons.USER_CHECK, "Session attendance rate"));

	          layout.addComponent(createInfoRow("Voting Success:", 
	              String.format(Locale.ENGLISH,"%.1f%%", ballotSummary.getSuccessRate()),
	              VaadinIcons.STAR, "Votes on winning side"));
	      }

	      layout.addComponent(createInfoRow("Parliamentary Activity:", 
	          politician.getDocActivityLevel(),
	          VaadinIcons.CHART_LINE, "Overall parliamentary engagement"));
	  }

	private void addLegislativeMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician) {
	      layout.addComponent(createInfoRow("Documents/Year:", 
	          String.format(Locale.ENGLISH,"%.1f", politician.getAverageDocsPerYear()),
	          VaadinIcons.FILE_TEXT, "Average documents per year"));

	      layout.addComponent(createInfoRow("Individual Motions:", 
	          String.valueOf(politician.getIndividualMotions()),
	          VaadinIcons.USER, "Personal motions submitted"));
	          
	      layout.addComponent(createInfoRow("Party Motions:", 
	          String.valueOf(politician.getPartyMotions()),
	          VaadinIcons.GROUP, "Party-sponsored motions"));
	  }

	private void addPartyAlignmentMetrics(VerticalLayout layout,
	      ViewRiksdagenPolitician politician, ViewRiksdagenPoliticianBallotSummary ballotSummary) {

	      if (ballotSummary != null) {
	          layout.addComponent(createInfoRow("Party Loyalty:", 
	              String.format(Locale.ENGLISH,"%.1f%%", ballotSummary.getLoyaltyRate()),
	              VaadinIcons.GROUP, "Party line adherence"));

	          layout.addComponent(createInfoRow("Independence Rate:", 
	              String.format(Locale.ENGLISH,"%.1f%%", ballotSummary.getRebelRate()),
	              VaadinIcons.RANDOM, "Votes against party line"));
	      }

	      layout.addComponent(createInfoRow("Cross-Party Work:", 
	          String.format(Locale.ENGLISH,"%.1f%%", politician.getCollaborationPercentage()),
	          VaadinIcons.CONNECT, "Inter-party cooperation"));
	  }

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

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());
	}

}

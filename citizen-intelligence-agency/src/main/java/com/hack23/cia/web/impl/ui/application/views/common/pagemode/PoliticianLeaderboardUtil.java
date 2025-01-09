package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianExperienceSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PoliticianLeaderboardUtil.
 */
@Service
public class PoliticianLeaderboardUtil extends CardInfoRowUtil {

	/** The page link factory. */
	@Autowired
	public PageLinkFactory pageLinkFactory;

	/**
	 * Instantiates a new politician leaderboard util.
	 */
	public PoliticianLeaderboardUtil() {
	}

	/**
	 * Adds the political analysis comment.
	 *
	 * @param layout the layout
	 * @param experienceSummary the experience summary
	 */
	public final void addPoliticalAnalysisComment(VerticalLayout layout,
			ViewRiksdagenPoliticianExperienceSummary experienceSummary) {
		// Split the analysis points and create a bullet list
		final String[] analysisPoints = experienceSummary.getPoliticalAnalysisComment().split("\\s*\\|\\|\\s*");
		final StringBuilder analys=new StringBuilder();
		for (final String point : analysisPoints) {
		    if (StringUtils.isNotBlank(point)) {
		    	analys.append(" • ").append(point);
		    }
		   }

		// Political Analysis Comment
		if (StringUtils.isNotBlank(experienceSummary.getPoliticalAnalysisComment())) {
		    layout.addComponent(createInfoRow("Analysis:",
		    		analys.toString(),
		        VaadinIcons.COMMENT,
		        "Political career analysis"));
		}
	}

	/**
	 * Adds the knowledge areas.
	 *
	 * @param layout the layout
	 * @param experienceSummary the experience summary
	 */
	public final void addKnowledgeAreas(VerticalLayout layout, ViewRiksdagenPoliticianExperienceSummary experienceSummary) {
		if (experienceSummary.getKnowledgeAreas() != null && !experienceSummary.getKnowledgeAreas().isEmpty()) {
		    final String topAreas = experienceSummary.getKnowledgeAreas().stream()
		        .filter(ka -> ka.getArea() != null && !ka.getArea().equals("Other"))
		        .sorted((ka1, ka2) -> ka2.getWeightedExp().compareTo(ka1.getWeightedExp()))
		        .limit(3)
		        .map(ka -> String.format(Locale.ENGLISH,"%s ",
		            ka.getArea()))
		        .collect(Collectors.joining(", "));

		    if (!topAreas.isEmpty()) {
		        layout.addComponent(createInfoRow("Key Policy Areas:",
		            topAreas,
		            VaadinIcons.CLIPBOARD_TEXT,
		            "Main areas of expertise with weighted importance"));
		    }
		}
	}

	/**
	 * Adds the top roles.
	 *
	 * @param layout the layout
	 * @param experienceSummary the experience summary
	 */
	public final void addTopRoles(VerticalLayout layout, ViewRiksdagenPoliticianExperienceSummary experienceSummary) {
		if (experienceSummary.getRoles() != null && !experienceSummary.getRoles().isEmpty()) {
		    final String topRoles = experienceSummary.getRoles().stream()
		        .filter(role -> role.getRole() != null && !role.getRole().equals("Other"))
		        .sorted((r1, r2) -> r2.getWeightedExp().compareTo(r1.getWeightedExp()))
		        .limit(3)
		        .map(role -> String.format(Locale.ENGLISH,"%s",
		            role.getRole()))
		        .collect(Collectors.joining(", "));

		    if (!topRoles.isEmpty()) {
		        layout.addComponent(createInfoRow("Key Political Roles:",
		            topRoles,
		            VaadinIcons.USERS,
		            "Most significant positions with weighted importance"));
		    }
		}
	}

	/**
	 * Adds the experience metrics.
	 *
	 * @param layout the layout
	 * @param experienceSummary the experience summary
	 */
	public final void addExperienceMetrics(VerticalLayout layout, ViewRiksdagenPoliticianExperienceSummary experienceSummary) {
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


 	    }
	}

	/**
	 * Adds the legislative metrics.
	 *
	 * @param layout the layout
	 * @param politician the politician
	 */
	public final void addLegislativeMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician) {

		layout.addComponent(createInfoRow("Documents/Year:", String.format(Locale.ENGLISH,"%.1f", politician.getAverageDocsPerYear()),
				VaadinIcons.FILE_TEXT, "Average documents per year"));
		layout.addComponent(createInfoRow("Individual Motions:", String.valueOf(politician.getIndividualMotions()),
				VaadinIcons.USER, "Personal motions submitted"));
		layout.addComponent(createInfoRow("Party Motions:", String.valueOf(politician.getPartyMotions()),
				VaadinIcons.GROUP, "Party-based motions"));
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
	public final void addPartyAlignmentMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician,
			ViewRiksdagenPoliticianBallotSummary ballotSummary) {

		layout.addComponent(createInfoRow("Party Loyalty:", String.format(Locale.ENGLISH,"%.1f%%", ballotSummary != null ? ballotSummary.getLoyaltyRate() : 0.0),
				VaadinIcons.GROUP, "Party line adherence"));
		layout.addComponent(createInfoRow("Independence Rate:", String.format(Locale.ENGLISH,"%.1f%%", ballotSummary != null ? ballotSummary.getRebelRate() : 0.0),
				VaadinIcons.RANDOM, "Votes against party line"));
		layout.addComponent(createInfoRow("Cross-Party Collaboration:",
				String.format(Locale.ENGLISH,"%.1f%%", politician.getCollaborationPercentage()), VaadinIcons.CONNECT,
				"Inter-party cooperation"));
		layout.addComponent(createInfoRow("Multi-Party Motions:", String.valueOf(politician.getMultiPartyMotions()),
				VaadinIcons.USERS, "Cross-party legislative initiatives"));
	}



	/**
	 * Adds the parliamentary performance metrics.
	 *
	 * @param layout the layout
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 */
	public final void addParliamentaryPerformanceMetrics(VerticalLayout layout, ViewRiksdagenPolitician politician,
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
	 * Adds the ministry role summary.
	 *
	 * @param cardLayout the card layout
	 * @param govMember the gov member
	 * @param governmentBodyByMinistry the government body by ministry
	 * @param reportByMinistry the report by ministry
	 */
	public final void addMinistryRoleSummary(final VerticalLayout cardLayout,
			final ViewRiksdagenGovermentRoleMember govMember,
			final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry) {

		cardLayout.addComponent(pageLinkFactory.addMinistryPageLink(govMember.getDetail()));

		final List<GovernmentBodyAnnualSummary> ministryBodies = governmentBodyByMinistry.get(govMember.getDetail());
		if (ministryBodies == null || ministryBodies.isEmpty()) {
			return;
		}

		final int totalHeadCount = ministryBodies.stream().mapToInt(GovernmentBodyAnnualSummary::getAnnualWorkHeadCount)
				.sum();
		final int bodyCount = ministryBodies.size();

		cardLayout.addComponent(createMetricRow(VaadinIcons.GROUP,
				pageLinkFactory.addMinistryGovermentBodiesPageLink(govMember.getDetail()),
				"Number of government bodies", String.valueOf(bodyCount)));

		cardLayout.addComponent(createMetricRow(VaadinIcons.USER,
				pageLinkFactory.addMinistryGovermentBodiesHeadcountPageLink(govMember.getDetail()),
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
				pageLinkFactory.addMinistryGovermentBodiesIncomePageLink(govMember.getDetail()),
				"Yearly Income (B SEK)", incomeStr));

		final String spendingStr = String.format(Locale.ENGLISH, "%.2f B SEK", currentYearSpending);
		cardLayout.addComponent(createMetricRow(VaadinIcons.ARROW_DOWN,
				pageLinkFactory.addMinistrGovermentBodiesSpendingPageLink(govMember.getDetail()),
				"Yearly Spending (B SEK)", spendingStr));
	}

	/**
	 * Loads active politicians by person ID.
	 *
	 * @param applicationManager the application manager
	 * @return a map of person ids to lists of active politicians
	 */
	public static Map<String, List<ViewRiksdagenPolitician>> loadActivePoliticiansByPersonId(ApplicationManager applicationManager) {
		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPolitician.class);
		final List<ViewRiksdagenPolitician> activePoliticians = politicianDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.activeGovernment);
		return activePoliticians.stream().collect(Collectors.groupingBy(ViewRiksdagenPolitician::getPersonId));
	}

	/**
	 * Creates a leader card.
	 *
	 * @param govMember the government role member
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 * @param governmentBodyByMinistry the government body by ministry
	 * @param reportByMinistry the report by ministry
	 * @param experienceSummary the experience summary
	 * @return the leader card panel
	 */
	public static Panel createLeaderCard(ViewRiksdagenGovermentRoleMember govMember,
			ViewRiksdagenPolitician politician, ViewRiksdagenPoliticianBallotSummary ballotSummary,
			Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
			Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry,
			ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("leader-baseball-card");
		cardPanel.setSizeFull();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setSizeFull();
		cardPanel.setContent(cardContent);

		CardInfoRowUtil.createCardHeader(cardContent, govMember.getRoleCode() + " " + govMember.getFirstName() + " " + govMember.getLastName()
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
		PoliticianLeaderboardUtil.addParliamentaryPerformanceMetrics(performanceLayout, politician, ballotSummary);
		sectionsGrid.addComponent(performanceLayout);

		cardContent.addComponent(sectionsGrid);

		final HorizontalLayout sections2Grid = new HorizontalLayout();
		sections2Grid.setSpacing(true);
		sections2Grid.setWidth("100%");

		final VerticalLayout legislativeLayout = CardInfoRowUtil.createSectionLayout("Legislative Activity");
		PoliticianLeaderboardUtil.addLegislativeMetrics(legislativeLayout, politician);
		sections2Grid.addComponent(legislativeLayout);

		final VerticalLayout alignmentLayout = CardInfoRowUtil.createSectionLayout("Party Alignment");
		PoliticianLeaderboardUtil.addPartyAlignmentMetrics(alignmentLayout, politician, ballotSummary);
		sections2Grid.addComponent(alignmentLayout);
		cardContent.addComponent(sections2Grid);

		PoliticianLeaderboardUtil.addMinistryRoleSummary(cardContent, govMember, governmentBodyByMinistry, reportByMinistry);

		return cardPanel;
	}

	/**
	 * Adds political role metrics to the layout.
	 *
	 * @param layout the layout
	 * @param govMember the government role member
	 * @param politician the politician
	 * @param ballotSummary the ballot summary
	 * @param experienceSummary the experience summary
	 */
	public static void addPoliticalRoleMetrics(VerticalLayout layout, ViewRiksdagenGovermentRoleMember govMember,
			ViewRiksdagenPolitician politician, ViewRiksdagenPoliticianBallotSummary ballotSummary,
			ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

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

		PoliticianLeaderboardUtil.addTopRoles(layout, experienceSummary);
		PoliticianLeaderboardUtil.addKnowledgeAreas(layout, experienceSummary);
		PoliticianLeaderboardUtil.addExperienceMetrics(layout, experienceSummary);
		PoliticianLeaderboardUtil.addPoliticalAnalysisComment(layout, experienceSummary);
	}
}

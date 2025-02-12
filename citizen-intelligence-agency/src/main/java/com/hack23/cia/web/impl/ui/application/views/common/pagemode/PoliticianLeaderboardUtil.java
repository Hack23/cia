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
	public final void addPoliticalAnalysisComment(final VerticalLayout layout,
			final ViewRiksdagenPoliticianExperienceSummary experienceSummary) {
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
	public final void addKnowledgeAreas(final VerticalLayout layout, final ViewRiksdagenPoliticianExperienceSummary experienceSummary) {
		if (experienceSummary.getKnowledgeAreas() != null && !experienceSummary.getKnowledgeAreas().isEmpty()) {
		    final String topAreas = buildTopString(
		        experienceSummary.getKnowledgeAreas(),
		        ka -> ka.getArea(),
		        ka -> ka.getWeightedExp()
		    );
		    if (!topAreas.isEmpty()) {
		        layout.addComponent(createInfoRow("Top Knowledge Areas:", topAreas, VaadinIcons.BOOK, "Key expertise"));
		    }
		}
	}

	/**
	 * Adds the top roles.
	 *
	 * @param layout the layout
	 * @param experienceSummary the experience summary
	 */
	public final void addTopRoles(final VerticalLayout layout, final ViewRiksdagenPoliticianExperienceSummary experienceSummary) {
		if (experienceSummary.getRoles() != null && !experienceSummary.getRoles().isEmpty()) {
		    final String topRoles = buildTopString(
		        experienceSummary.getRoles(),
		        role -> role.getRole(),
		        role -> role.getWeightedExp()
		    );
		    if (!topRoles.isEmpty()) {
		        layout.addComponent(createInfoRow("Top Roles:", topRoles, VaadinIcons.STAR, "Most significant roles")); // Changed from CROWN to STAR
		    }
		}
	}

	/**
	 * Adds the experience metrics.
	 *
	 * @param layout the layout
	 * @param experienceSummary the experience summary
	 */
	public final void addExperienceMetrics(final VerticalLayout layout, final ViewRiksdagenPoliticianExperienceSummary experienceSummary) {
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
	public final void addLegislativeMetrics(final VerticalLayout layout, final ViewRiksdagenPolitician politician) {

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
	public final void addPartyAlignmentMetrics(final VerticalLayout layout, final ViewRiksdagenPolitician politician,
			final ViewRiksdagenPoliticianBallotSummary ballotSummary) {

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
	public final void addParliamentaryPerformanceMetrics(final VerticalLayout layout, final ViewRiksdagenPolitician politician,
			final ViewRiksdagenPoliticianBallotSummary ballotSummary) {

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
	 * Builds the top string.
	 *
	 * @param <T> the generic type
	 * @param items the items
	 * @param nameFunction the name function
	 * @param weightFunction the weight function
	 * @return the string
	 */
	private <T> String buildTopString(final List<T> items,
	                                  final java.util.function.Function<T, String> nameFunction,
	                                  final java.util.function.Function<T, Long> weightFunction) {
	    return items.stream()
	        .filter(i -> nameFunction.apply(i) != null && !"Other".equals(nameFunction.apply(i)))
	        .sorted((o1, o2) -> weightFunction.apply(o2).compareTo(weightFunction.apply(o1)))
	        .limit(3)
	        .map(nameFunction)
	        .collect(Collectors.joining(", "));
	}

}

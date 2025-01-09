package com.hack23.cia.web.impl.ui.application.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianExperienceSummary;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PoliticianLeaderboardUtil;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Utility class for handling party leader related operations.
 */
public final class PartyLeaderUtil {

    private static final String ROLE_CODE_PARTILEDARE = "Partiledare";
    private PartyLeaderUtil() {
        // Utility class, no instantiation
    }

    /**
     * Checks if a person is a party leader.
     *
     * @param applicationManager the application manager
     * @param personId the person id
     * @return true, if the person is a party leader
     */
    public static boolean isPartyLeader(ApplicationManager applicationManager, String personId) {
        final DataContainer<ViewRiksdagenPartyRoleMember, String> container =
            applicationManager.getDataContainer(ViewRiksdagenPartyRoleMember.class);
        final List<ViewRiksdagenPartyRoleMember> roles =
            container.findListByProperty(new Object[] { personId, Boolean.TRUE },
                    ViewRiksdagenPartyRoleMember_.personId,
                    ViewRiksdagenPartyRoleMember_.active);

        return roles.stream()
                .anyMatch(r -> r.getRoleCode() != null
                    && ROLE_CODE_PARTILEDARE.equalsIgnoreCase(r.getRoleCode().trim()));
    }

    /**
     * Fetches the party leader role for a person.
     *
     * @param applicationManager the application manager
     * @param personId the person id
     * @return the party leader role, or null if not found
     */
    public static ViewRiksdagenPartyRoleMember getPartyLeaderRole(ApplicationManager applicationManager, String personId) {
        final DataContainer<ViewRiksdagenPartyRoleMember, String> container =
            applicationManager.getDataContainer(ViewRiksdagenPartyRoleMember.class);
        final List<ViewRiksdagenPartyRoleMember> roles =
            container.findListByProperty(new Object[] { personId, Boolean.TRUE },
                    ViewRiksdagenPartyRoleMember_.personId,
                    ViewRiksdagenPartyRoleMember_.active);

        return roles.stream()
                .filter(r -> r.getRoleCode() != null
                    && ROLE_CODE_PARTILEDARE.equalsIgnoreCase(r.getRoleCode().trim()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Computes party leaders for a list of person IDs.
     *
     * @param applicationManager the application manager
     * @param personIds the person ids
     * @return a map of person ids to boolean indicating if they are party leaders
     */
    public static Map<String, Boolean> computePartyLeaders(ApplicationManager applicationManager, Iterable<String> personIds) {
        final DataContainer<ViewRiksdagenPartyRoleMember, String> container =
            applicationManager.getDataContainer(ViewRiksdagenPartyRoleMember.class);

        final Map<String, Boolean> result = new HashMap<>();
        for (final String personId : personIds) {
            final List<ViewRiksdagenPartyRoleMember> roles =
                container.findListByProperty(new Object[] { personId, Boolean.TRUE },
                        ViewRiksdagenPartyRoleMember_.personId,
                        ViewRiksdagenPartyRoleMember_.active);

            final boolean isLeader = roles.stream()
                    .anyMatch(r -> r.getRoleCode() != null
                        && ROLE_CODE_PARTILEDARE.equalsIgnoreCase(r.getRoleCode().trim()));
            result.put(personId, isLeader);
        }
        return result;
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

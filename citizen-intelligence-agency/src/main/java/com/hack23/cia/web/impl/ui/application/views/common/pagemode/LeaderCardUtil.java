package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianExperienceSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class LeaderCardUtil.
 */
@Component
public class LeaderCardUtil {

    /** The application manager. */
    @Autowired
    private ApplicationManager applicationManager;

    /** The politician leaderboard util. */
    @Autowired
    private PoliticianLeaderboardUtil politicianLeaderboardUtil;

	/** The page link factory. */
	@Autowired
	public PageLinkFactory pageLinkFactory;

    /**
     * Load active government role members.
     *
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public List<ViewRiksdagenGovermentRoleMember> loadActiveGovernmentRoleMembers() {
        final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = applicationManager
                .getDataContainer(ViewRiksdagenGovermentRoleMember.class);
        return govermentRoleMemberDataContainer.findListByProperty(new Object[] { Boolean.TRUE },
                ViewRiksdagenGovermentRoleMember_.active);
    }

    /**
     * Load active politicians by person id.
     *
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<ViewRiksdagenPolitician>> loadActivePoliticiansByPersonId() {
        final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
                .getDataContainer(ViewRiksdagenPolitician.class);
        final List<ViewRiksdagenPolitician> activePoliticians = politicianDataContainer
                .findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.active);
        return activePoliticians.stream().collect(Collectors.groupingBy(ViewRiksdagenPolitician::getPersonId));
    }

    /**
     * Creates the base card.
     *
     * @return the panel
     */
    private Panel createBaseCard() {
        final Panel cardPanel = new Panel();
        cardPanel.addStyleName("leader-baseball-card");
        cardPanel.setSizeFull();
        Responsive.makeResponsive(cardPanel);
        return cardPanel;
    }

    /**
     * Creates the card content.
     *
     * @return the vertical layout
     */
    private VerticalLayout createCardContent() {
        final VerticalLayout cardContent = new VerticalLayout();
        cardContent.setMargin(true);
        cardContent.setSpacing(true);
        cardContent.setSizeFull();
        return cardContent;
    }

    /**
     * Adds the experience row.
     *
     * @param container the container
     * @param govYears the gov years
     * @param partyYears the party years
     * @param parliamentYears the parliament years
     */
    private void addExperienceRow(final VerticalLayout container, final int govYears, final int partyYears, final int parliamentYears) {
        final HorizontalLayout experienceLayout = CardInfoRowUtil.createStandardRow();
        experienceLayout.addStyleName(CardInfoRowUtil.CARD_EXPERIENCE);

        final Label expIcon = CardInfoRowUtil.createIconLabel(VaadinIcons.USER_CHECK, "Political Experience");
        final Label expLabel = new Label("Experience:");
        expLabel.addStyleName("card-experience-text");

        final String expText = String.format(Locale.ENGLISH,
            "Government: %dy, Party: %dy, Parliament: %dy",
            govYears, partyYears, parliamentYears);
        final Label expValue = new Label(expText);
        expValue.addStyleName(CardInfoRowUtil.CARD_INFO_VALUE);

        experienceLayout.addComponents(expIcon, expLabel, expValue);
        container.addComponent(experienceLayout);
    }

    /**
     * Creates the baseball style card.
     *
     * @param govMember the gov member
     * @param politician the politician
     * @param ballotSummary the ballot summary
     * @param governmentBodyByMinistry the government body by ministry
     * @param reportByMinistry the report by ministry
     * @param experienceSummary the experience summary
     * @return the panel
     */
    public Panel createBaseballStyleCard(final ViewRiksdagenGovermentRoleMember govMember,
            final ViewRiksdagenPolitician politician, final ViewRiksdagenPoliticianBallotSummary ballotSummary, final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
            final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry, final ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

        final Panel cardPanel = createBaseCard();
        final VerticalLayout cardContent = createCardContent();
        cardPanel.setContent(cardContent);

        CardInfoRowUtil.createCardHeader(cardContent,govMember.getRoleCode() + " " + govMember.getFirstName() + " " + govMember.getLastName()
            + " (" + govMember.getParty() + ")");
        cardContent.addComponent(pageLinkFactory.createPoliticianPageLink(politician));

        final Link pageLink = new Link("Party " + politician.getParty(),
                new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/" + politician.getParty()));
        pageLink.setId(ViewAction.VISIT_PARTY_VIEW.name() + "/" + politician.getParty());
        pageLink.setIcon(VaadinIcons.GROUP);

        cardContent.addComponent(pageLink);

        final boolean isPartyLeader = PartyLeaderUtil.isPartyLeader(applicationManager, politician.getPersonId());
        if (isPartyLeader) {
            final ViewRiksdagenPartyRoleMember leaderRole = PartyLeaderUtil.getPartyLeaderRole(applicationManager, politician.getPersonId());
            if (leaderRole != null) {
                final Label subHeader = new Label(
                        "Partiledare (" + govMember.getParty() + ") since " + leaderRole.getFromDate());
                subHeader.addStyleName("card-subtitle");
                cardContent.addComponent(subHeader);
            }
        }

        // After creating the divider following the header/subtitle
        // We create a vertical layout to hold Tenure and Experience on separate rows
        final VerticalLayout statsContainer = CardInfoRowUtil.createStatsContainer();

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
        final int govYears = (int) (politician.getTotalDaysServedGovernment() / 365);
        final int partyYears = (int) (politician.getTotalDaysServedParty() / 365);
        final int parliamentYears = (int) (politician.getTotalDaysServedParliament() / 365);
        addExperienceRow(statsContainer, govYears, partyYears, parliamentYears);

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
     * Creates the leader card.
     *
     * @param leader the leader
     * @param ballotSummary the ballot summary
     * @param governmentBodyByMinistry the government body by ministry
     * @param reportByMinistry the report by ministry
     * @param experienceSummary the experience summary
     * @return the panel
     */
    public Panel createLeaderCard(final ViewRiksdagenPolitician leader, final ViewRiksdagenPoliticianBallotSummary ballotSummary,
            final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry,
            final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry, final ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

        final Panel cardPanel = createBaseCard();
        final VerticalLayout cardContent = createCardContent();
        cardPanel.setContent(cardContent);

        CardInfoRowUtil.createCardHeader(cardContent,"Partiledare " + leader.getFirstName() + " " + leader.getLastName() + " ("
                + leader.getParty() + ")");

        // Politician detail link
        cardContent.addComponent(pageLinkFactory.createPoliticianPageLink(leader));

        // Party link
        final Link partyLink = new Link("Party " + leader.getParty(),
                new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/" + leader.getParty()));
        partyLink.setIcon(VaadinIcons.GROUP);
        cardContent.addComponent(partyLink);

        final boolean isPartyLeader = PartyLeaderUtil.isPartyLeader(applicationManager, leader.getPersonId());
        if (isPartyLeader) {
            final ViewRiksdagenPartyRoleMember leaderRole = PartyLeaderUtil.getPartyLeaderRole(applicationManager, leader.getPersonId());
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
        final VerticalLayout statsContainer = CardInfoRowUtil.createStatsContainer();

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
        final int govYears = (int) (leader.getTotalDaysServedGovernment() / 365);
        final int partyYears = (int) (leader.getTotalDaysServedParty() / 365);
        final int parliamentYears = (int) (leader.getTotalDaysServedParliament() / 365);
        addExperienceRow(statsContainer, govYears, partyYears, parliamentYears);

        cardContent.addComponent(statsContainer);

        // Create grid for the four sections
        final HorizontalLayout sectionsGrid = new HorizontalLayout();
        sectionsGrid.setSpacing(true);
        sectionsGrid.setWidth("100%");

        // Add the four main sections
        final VerticalLayout politicalRoleLayout = CardInfoRowUtil.createSectionLayout("Political Role & Influence");
        addPoliticalRoleMetrics(politicalRoleLayout, PartyLeaderUtil.getPartyLeaderRole(applicationManager, leader.getPersonId()), govMember, leader, ballotSummary,experienceSummary);
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
     * Adds the political role metrics.
     *
     * @param layout the layout
     * @param govMember the gov member
     * @param politician the politician
     * @param ballotSummary the ballot summary
     * @param experienceSummary the experience summary
     */
    private void addPoliticalRoleMetrics(final VerticalLayout layout, final ViewRiksdagenGovermentRoleMember govMember,
            final ViewRiksdagenPolitician politician, final ViewRiksdagenPoliticianBallotSummary ballotSummary, final ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

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
     * Adds the political role metrics.
     *
     * @param layout the layout
     * @param riksdagenPartyRoleMember the riksdagen party role member
     * @param govMember the gov member
     * @param politician the politician
     * @param ballotSummary the ballot summary
     * @param experienceSummary the experience summary
     */
    private void addPoliticalRoleMetrics(final VerticalLayout layout, final ViewRiksdagenPartyRoleMember riksdagenPartyRoleMember, final ViewRiksdagenGovermentRoleMember govMember,
            final ViewRiksdagenPolitician politician, final ViewRiksdagenPoliticianBallotSummary ballotSummary, final ViewRiksdagenPoliticianExperienceSummary experienceSummary) {

        addPartyExperince(layout, riksdagenPartyRoleMember, govMember, politician);

        // Top Roles
        politicianLeaderboardUtil.addTopRoles(layout, experienceSummary);

        // Top Knowledge Areas
        politicianLeaderboardUtil.addKnowledgeAreas(layout, experienceSummary);

        politicianLeaderboardUtil.addExperienceMetrics(layout,experienceSummary);

        politicianLeaderboardUtil.addPoliticalAnalysisComment(layout, experienceSummary);

    }

    /**
     * Adds the party experince.
     *
     * @param layout the layout
     * @param riksdagenPartyRoleMember the riksdagen party role member
     * @param govMember the gov member
     * @param politician the politician
     */
    private void addPartyExperince(final VerticalLayout layout, final ViewRiksdagenPartyRoleMember riksdagenPartyRoleMember,
            final ViewRiksdagenGovermentRoleMember govMember, final ViewRiksdagenPolitician politician) {
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
     * Find government role for leader.
     *
     * @param leader the leader
     * @return the view riksdagen goverment role member
     */
    @SuppressWarnings("unchecked")
    private ViewRiksdagenGovermentRoleMember findGovernmentRoleForLeader(final ViewRiksdagenPolitician leader) {
        final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = applicationManager
                .getDataContainer(ViewRiksdagenGovermentRoleMember.class);
        final List<ViewRiksdagenGovermentRoleMember> activeGovMembers = govermentRoleMemberDataContainer.findListByProperty(
                new Object[] { Boolean.TRUE }, ViewRiksdagenGovermentRoleMember_.active);

        return activeGovMembers.stream()
                .filter(govMember -> govMember.getPersonId().equals(leader.getPersonId()))
                .findFirst().orElse(null);
    }
}

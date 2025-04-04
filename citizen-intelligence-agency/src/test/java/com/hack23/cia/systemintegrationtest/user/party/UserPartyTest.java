package com.hack23.cia.systemintegrationtest.user.party;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyConstants;
import com.hack23.cia.web.impl.ui.application.views.user.party.pagemode.PartyViewConstants;

/**
 * The Class UserPartyRankingTest.
 */
@Category(IntegrationTest.class)
public final class UserPartyTest extends AbstractUITest {

    /** The party id. */
    private static final String PARTY_ID = "M";

    /**
     * Verify party overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_OVERVIEW.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
        		PartyViewConstants.OVERVIEW_HEADER,
    		    PartyViewConstants.GENERAL_SUBTITLE,
    		    PartyViewConstants.OVERVIEW_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_OVERVIEW.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party role ghant page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRoleGhantPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_ROLE_GHANT.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
        		PartyViewConstants.ROLE_GHANT_HEADER,
    			PartyViewConstants.ROLE_GHANT_TITLE,
    			PartyViewConstants.ROLE_GHANT_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_ROLE_GHANT.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party member history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyMemberHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_MEMBER_HISTORY.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.MEMBER_HISTORY_HEADER,
            PartyViewConstants.MEMBER_HISTORY_TITLE,
            PartyViewConstants.MEMBER_HISTORY_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_MEMBER_HISTORY.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party page visit history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_PAGE_VISIT_HISTORY.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.VISIT_HISTORY_HEADER,
            PartyViewConstants.VISIT_HISTORY_TITLE,
            PartyViewConstants.VISIT_HISTORY_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_PAGE_VISIT_HISTORY.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party support summary page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartySupportSummaryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_SUPPORT_SUMMARY.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.SUPPORT_SUMMARY_HEADER,
            PartyViewConstants.SUPPORT_SUMMARY_TITLE,
            PartyViewConstants.SUPPORT_SUMMARY_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_SUPPORT_SUMMARY.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party current leaders page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyCurrentLeadersPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_CURRENT_LEADERS.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.CURRENT_LEADERS_HEADER,
            PartyViewConstants.CURRENT_LEADERS_TITLE,
            PartyViewConstants.CURRENT_LEADERS_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_CURRENT_LEADERS.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party leader history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyLeaderHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_LEADER_HISTORY.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.LEADER_HISTORY_HEADER,
            PartyViewConstants.LEADER_HISTORY_TITLE,
            PartyViewConstants.LEADER_HISTORY_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_LEADER_HISTORY.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party current members page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyCurrentMembersPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_CURRENT_MEMBERS.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.CURRENT_MEMBERS_HEADER,
            PartyViewConstants.CURRENT_MEMBERS_TITLE,
            PartyViewConstants.CURRENT_MEMBERS_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_CURRENT_MEMBERS.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party document history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyDocumentHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_DOCUMENT_HISTORY.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.DOCUMENT_HISTORY_HEADER,
            PartyViewConstants.DOCUMENT_HISTORY_TITLE,
            PartyViewConstants.DOCUMENT_HISTORY_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_DOCUMENT_HISTORY.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party document activity page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyDocumentActivityPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_DOCUMENT_ACTIVITY.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.DOCUMENT_ACTIVITY_HEADER,
            PartyViewConstants.DOCUMENT_ACTIVITY_SUBTITLE,
            PartyViewConstants.DOCUMENT_ACTIVITY_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_DOCUMENT_ACTIVITY.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party committee roles page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyCommitteeRolesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_COMMITTEE_ROLES.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
        		PartyViewConstants.COMMITTEE_ROLES_HEADER,
    			PartyViewConstants.ROLES_SUBTITLE,
    			PartyViewConstants.COMMITTEE_ROLES_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_COMMITTEE_ROLES.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party government roles page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyGovernmentRolesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_GOVERNMENT_ROLES.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.GOVERNMENT_ROLES_HEADER,
            PartyViewConstants.GOVERNMENT_ROLES_TITLE,
            PartyViewConstants.GOVERNMENT_ROLES_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_GOVERNMENT_ROLES.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party won daily summary chart page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyWonDailySummaryChartPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_WON_DAILY_SUMMARY_CHART.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.DAILY_WON_HEADER,
            PartyViewConstants.DAILY_WON_TITLE,
            PartyViewConstants.DAILY_WON_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_WON_DAILY_SUMMARY_CHART.createItemPageCommand(PARTY_ID));
    }

    /**
     * Verify party ballot decision summary page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyBallotDecisionSummaryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_BALLOT_DECISION_SUMMARY.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
            PartyViewConstants.BALLOT_DECISIONS_HEADER,
            PartyViewConstants.BALLOT_DECISIONS_TITLE,
            PartyViewConstants.BALLOT_DECISIONS_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_BALLOT_DECISION_SUMMARY.createItemPageCommand(PARTY_ID));
    }


    /**
     * Verify party against coalition page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyAgainstCoalitionPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_AGAINST_COALITIONS_SUMMARY.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
        		 PartyViewConstants.COALITIONS_AGAINST_HEADER,
                 PartyViewConstants.COALITIONS_AGAINST_TITLE,
                 PartyViewConstants.COALITIONS_AGAINST_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_AGAINST_COALITIONS_SUMMARY.createItemPageCommand(PARTY_ID));
    }



    /**
     * Verify party vote history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyVoteHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyConstants.COMMAND_PARTY_VOTE_HISTORY.createItemPageCommand(PARTY_ID));
        pageVisit.verifyViewContent(
        		PartyViewConstants.VOTE_HISTORY_HEADER,
                PartyViewConstants.VOTE_HISTORY_TITLE,
                PartyViewConstants.VOTE_HISTORY_DESC);
        pageVisit.validatePage(PageCommandPartyConstants.COMMAND_PARTY_VOTE_HISTORY.createItemPageCommand(PARTY_ID));
    }

}
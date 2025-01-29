package com.hack23.cia.systemintegrationtest.user.party;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.user.partyranking.pagemode.PartyRankingViewConstants;

/**
 * The Class UserPartyRankingTest.
 */
@Category(IntegrationTest.class)
public final class UserPartyRankingTest extends AbstractUITest {


    /**
     * Verify party ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_OVERVIEW);
        pageVisit.verifyViewContent(
            PartyRankingViewConstants.OVERVIEW_TITLE,
            PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
            PartyRankingViewConstants.OVERVIEW_DESC);
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_OVERVIEW);
    }

    /**
     * Verify parties link page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartiesLinkPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_DATAGRID);
        pageVisit.verifyViewContent(
            PartyRankingViewConstants.DATAGRID_TITLE,
            PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
            PartyRankingViewConstants.DATAGRID_DESC);
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_DATAGRID);
    }

    /**
     * Verify party ranking page visit history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.verifyViewContent(
            PartyRankingViewConstants.PAGE_HISTORY_TITLE,
            PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
            PartyRankingViewConstants.PAGE_HISTORY_DESC);
        pageVisit.validatePage(PageCommandPartyRankingConstants.PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }

    /**
     * Verify party ranking current gov parties page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingCurrentGovPartiesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES);
        pageVisit.verifyViewContent(
            PartyRankingViewConstants.CURRENT_GOVERNMENT_TITLE,
            PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
            PartyRankingViewConstants.CURRENT_GOVERNMENT_DESC);
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES);
    }


    /**
     * Verify party ranking leader scoreboard page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingLeaderScoreboardPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_PARTY_LEADER_SCOREBOARD);
        pageVisit.verifyViewContent(
        		PartyRankingViewConstants.LEADER_SCOREBOARD_TITLE,
    			PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
    			PartyRankingViewConstants.LEADER_SCOREBOARD_DESC);
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_PARTY_LEADER_SCOREBOARD);
    }


    /**
     * Verify party ranking all parties page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingAllPartiesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_CHARTS_ALL_PARTIES);
        pageVisit.verifyViewContent(
        		PartyRankingViewConstants.ALL_PARTIES_TITLE,
    			PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
    			PartyRankingViewConstants.ALL_PARTIES_DESC);
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_CHARTS_ALL_PARTIES);
    }


    /**
     * Verify party ranking current committees page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingCurrentCommitteesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_CHARTS_CURRENT_COMMITTEES);
        pageVisit.verifyViewContent(
        		PartyRankingViewConstants.CURRENT_COMMITTEE_TITLE,
    			PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
    			PartyRankingViewConstants.CURRENT_COMMITTEE_DESC);
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_CHARTS_CURRENT_COMMITTEES);
    }

    /**
     * Verify party ranking current parties page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingCurrentPartiesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_CHARTS_CURRENT_PARTIES);
        pageVisit.verifyViewContent(
        		PartyRankingViewConstants.CURRENT_PARTIES_TITLE,
    		    PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
    		    PartyRankingViewConstants.CURRENT_PARTIES_DESC);
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_CHARTS_CURRENT_PARTIES);
    }


}

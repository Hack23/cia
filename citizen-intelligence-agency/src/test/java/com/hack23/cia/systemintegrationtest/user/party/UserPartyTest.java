package com.hack23.cia.systemintegrationtest.user.party;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyRankingConstants;

/**
 * The Class UserPartyTest.
 */
@Category(IntegrationTest.class)
public final class UserPartyTest extends AbstractUITest {

    /**
     * Verify party page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES);
        pageVisit.verifyViewContent("Current Government Charts", 
            "Government Performance", 
            "Analyze the performance of the current government using various charts.");
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_CHARTS_CURRENT_GOVERNMENT_PARTIES);
    }

    /**
     * Verify party ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_OVERVIEW);
        pageVisit.verifyViewContent("Party Rankings", 
            "Ranking Overview", 
            "Compare and rank political parties based on predefined metrics.");
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_PARTY_RANKING_OVERVIEW);
    }

    /**
     * Verify party winner page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyWinnerPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_CHARTS_PARTY_WINNER);
        pageVisit.verifyViewContent("Party Performance Dashboard", 
            "Party Rankings", 
            "Evaluate political parties using data on legislative activity, assignments, and influence.");
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_CHARTS_PARTY_WINNER);
    }

    /**
     * Verify party gender page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyGenderPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_CHARTS_PARTY_GENDER);
        pageVisit.verifyViewContent("Party Ranking - Current Parties Charts", 
            "Current Party Dynamics", 
            "Current parties: gauging influence and strategic footholds.");
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_CHARTS_PARTY_GENDER);
    }

    /**
     * Verify party age page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyAgePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_CHARTS_PARTY_AGE);
        pageVisit.verifyViewContent("Current Party Leaders Scoreboard", 
            "Leader Performance", 
            "Evaluate the performance of current party leaders including those not in government.");
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_CHARTS_PARTY_AGE);
    }

    /**
     * Verify parties link page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartiesLinkPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.COMMAND_PARTIES_LINK);
        pageVisit.verifyViewContent("All Parties Charts", 
            "Party Performance", 
            "Analyze the performance of all political parties using various charts.");
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_PARTIES_LINK);
    }

    /**
     * Verify party ranking page visit history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyRankingPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPartyRankingConstants.PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.verifyViewContent("Page Visit History", 
            "User Activity", 
            "Track the history of page visits for party rankings.");
        pageVisit.validatePage(PageCommandPartyRankingConstants.PARTY_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }
}

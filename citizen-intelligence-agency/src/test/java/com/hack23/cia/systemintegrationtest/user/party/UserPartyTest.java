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
        pageVisit.validatePage(PageCommandPartyRankingConstants.COMMAND_CHARTS_PARTY_AGE);
    }
}

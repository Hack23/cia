package com.hack23.cia.systemintegrationtest.user.parliament;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandParliamentRankingConstants;

/**
 * The Class UserParliamentTest.
 */
@Category(IntegrationTest.class)
public final class UserParliamentTest extends AbstractUITest {

    /**
     * Verify parliament page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyParliamentPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_PARLIAMENT_RANKING_OVERVIEW);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_PARLIAMENT_RANKING_OVERVIEW);
    }

    /**
     * Verify parliament ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyParliamentRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_PARLIAMENT_RANKING_OVERVIEW);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_PARLIAMENT_RANKING_OVERVIEW);
    }

    /**
     * Verify risk summary page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyRiskSummaryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_RISK_SUMMARY);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_RISK_SUMMARY);
    }

    /**
     * Verify rule violation page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyRuleViolationPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_RULE_VIOLATION);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_RULE_VIOLATION);
    }

    /**
     * Verify document activity page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentActivityPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_DOCUMENT_ACTIVITY);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_DOCUMENT_ACTIVITY);
    }
}

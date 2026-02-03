package com.hack23.cia.systemintegrationtest.user.parliament;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandParliamentRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode.ParliamentPageTitleConstants;
import com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode.ParliamentViewConstants;

/**
 * The Class UserParliamentITest.
 */
@Category(IntegrationTest.class)
public final class UserParliamentITest extends AbstractUITest {

    /**
     * Verify parliament page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyParliamentPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_PARLIAMENT_RANKING_OVERVIEW);
        pageVisit.verifyViewContent(ParliamentPageTitleConstants.OVERVIEW_TITLE,
            ParliamentPageTitleConstants.OVERVIEW_SUBTITLE,
            ParliamentPageTitleConstants.OVERVIEW_DESC);
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
        pageVisit.verifyViewContent(ParliamentPageTitleConstants.RISK_ASSESSMENT_TITLE,
            ParliamentPageTitleConstants.RISK_ASSESSMENT_SUBTITLE,
            ParliamentPageTitleConstants.RISK_ASSESSMENT_DESC);
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
        pageVisit.verifyViewContent(ParliamentPageTitleConstants.RULE_VIOLATIONS_TITLE,
            ParliamentPageTitleConstants.RULE_VIOLATIONS_SUBTITLE,
            ParliamentPageTitleConstants.RULE_VIOLATIONS_DESC);
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
        pageVisit.verifyViewContent(ParliamentPageTitleConstants.DOC_ACTIVITY_TITLE,
            ParliamentPageTitleConstants.DOC_ACTIVITY_SUBTITLE,
            ParliamentPageTitleConstants.DOC_ACTIVITY_DESC);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_DOCUMENT_ACTIVITY);
    }

    /**
     * Verify decision activity page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDecisionActivityPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_DECISION_ACTIVITY);
        pageVisit.verifyViewContent(ParliamentPageTitleConstants.DECISION_ACTIVITY_TITLE,
            ParliamentPageTitleConstants.DECISION_ACTIVITY_SUBTITLE,
            ParliamentPageTitleConstants.DECISION_ACTIVITY_DESC);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_DECISION_ACTIVITY);
    }

    /**
     * Verify parliament page visit history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyParliamentPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.verifyViewContent(ParliamentPageTitleConstants.VISIT_HISTORY_TITLE,
            ParliamentPageTitleConstants.VISIT_HISTORY_SUBTITLE,
            ParliamentPageTitleConstants.VISIT_HISTORY_DESC);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }

    /**
     * Verify decision flow page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDecisionFlowPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_CHARTS_DECISION_FLOW);
        pageVisit.verifyViewContent(ParliamentPageTitleConstants.DECISION_FLOW_TITLE,
        ParliamentPageTitleConstants.DECISION_FLOW_SUBTITLE,
        ParliamentPageTitleConstants.DECISION_FLOW_DESC);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_CHARTS_DECISION_FLOW);
    }

        /**
     * Verify party winner page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyWinnerPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_CHARTS_PARTY_WINNER);
        pageVisit.verifyViewContent(
            ParliamentViewConstants.PARTY_WINNER_TITLE,
		    ParliamentViewConstants.PARTY_WINNER_SUBTITLE,
		    ParliamentViewConstants.PARTY_WINNER_DESC);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_CHARTS_PARTY_WINNER);
    }

    /**
     * Verify party gender page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyGenderPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_CHARTS_PARTY_GENDER);
        pageVisit.verifyViewContent(
            ParliamentPageTitleConstants.PARTY_GENDER_TITLE,
		    ParliamentPageTitleConstants.PARTY_GENDER_SUBTITLE,
		    ParliamentPageTitleConstants.PARTY_GENDER_DESC);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_CHARTS_PARTY_GENDER);
    }

    /**
     * Verify party age page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPartyAgePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandParliamentRankingConstants.COMMAND_CHARTS_PARTY_AGE);
        pageVisit.verifyViewContent(
            ParliamentPageTitleConstants.PARTY_AGE_TITLE,
            ParliamentPageTitleConstants.PARTY_AGE_SUBTITLE,
            ParliamentPageTitleConstants.PARTY_AGE_DESC);
        pageVisit.validatePage(PageCommandParliamentRankingConstants.COMMAND_CHARTS_PARTY_AGE);
    }

}

package com.hack23.cia.systemintegrationtest.user.ministry;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMinistryRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;
import com.hack23.cia.web.impl.ui.application.views.user.govermentranking.pagemode.MinistryRankingViewConstants;

/**
 * The Class UserMinistryRankingITest.
 */
@Category(IntegrationTest.class)
public final class UserMinistryRankingITest extends AbstractUITest implements PageCommandUserConstants {

    /**
     * Verify ministry ranking ministry by headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRankingMinistryByHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.CURRENT_MINISTRIES_TITLE,
            MinistryRankingViewConstants.CURRENT_MINISTRIES_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
    }

    /**
     * Verify ministry ranking gov roles ghant page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRankingGovRolesGhantPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT);
        pageVisit.verifyViewContent(
        		MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
                MinistryRankingViewConstants.ALL_ROLES_TITLE,
                MinistryRankingViewConstants.ALL_ROLES_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_ALL_GOVERNMENT_ROLE_GANTT);
    }

    /**
     * Verify ministry ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_DATAGRID);
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.DATAGRID_TITLE,
            MinistryRankingViewConstants.DATAGRID_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_DATAGRID);
    }

    /**
     * Verify ministry ranking overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_OVERVIEW);
        // Maps to MinistryRankingOverviewPageModContentFactoryImpl
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.OVERVIEW_TITLE,
            MinistryRankingViewConstants.OVERVIEW_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_OVERVIEW);
    }

    /**
     * Verify charts current ministries leader scoreboard page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyChartsCurrentMinistriesLeaderScoreboardPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.LEADER_SCOREBOARD_TITLE,
            MinistryRankingViewConstants.LEADER_SCOREBOARD_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);
    }

    /**
     * Verify charts current ministries by headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyChartsCurrentMinistriesByHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
        // Maps to MinistryRankingCurrentMinistriesChartsPageModContentFactoryImpl
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.CURRENT_MINISTRIES_TITLE,
            MinistryRankingViewConstants.CURRENT_MINISTRIES_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
    }

    /**
     * Verify all ministries by headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAllMinistriesByHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT);
        // Maps to MinistryRankingAllMinistriesChartsPageModContentFactoryImpl
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.ALL_MINISTRIES_TITLE,
            MinistryRankingViewConstants.ALL_MINISTRIES_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT);
    }

    /**
     * Verify government outcome page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentOutcomePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_GOVERNMENT_OUTCOME);
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.GOV_OUTCOMES_TITLE,
            MinistryRankingViewConstants.GOV_OUTCOMES_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_GOVERNMENT_OUTCOME);
    }

    /**
     * Verify page visit history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.PAGE_HISTORY_TITLE,
            MinistryRankingViewConstants.PAGE_HISTORY_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.MINISTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }

    /**
     * Verify government body income page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyIncomePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME);
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.GOV_BODY_INCOME_TITLE,
            MinistryRankingViewConstants.GOV_BODY_INCOME_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME);
    }

    /**
     * Verify government body expenditure page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyExpenditurePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_GOVERNMENT_BODIES_EXPENDITURE);
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.GOV_BODY_EXP_TITLE,
            MinistryRankingViewConstants.GOV_BODY_EXP_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_GOVERNMENT_BODIES_EXPENDITURE);
    }

    /**
     * Verify government bodies headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodiesHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT);
        // Update to use correct title from constants
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.GOV_BODIES_HEADCOUNT_TITLE,
            MinistryRankingViewConstants.GOV_BODIES_HEADCOUNT_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT);
    }

    /**
     * Verify current parties headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCurrentPartiesHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT);
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.CURRENT_PARTIES_TITLE,
            MinistryRankingViewConstants.CURRENT_PARTIES_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_PARTIES_BY_HEADCOUNT);
    }

    /**
     * Verify all parties page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAllPartiesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_ALL_PARTIES_BY_HEADCOUNT);
        pageVisit.verifyViewContent(
            MinistryRankingViewConstants.TITLE_MINISTRY_RANKINGS,
            MinistryRankingViewConstants.ALL_PARTIES_TITLE,
            MinistryRankingViewConstants.ALL_PARTIES_DESC);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_ALL_PARTIES_BY_HEADCOUNT);
    }
}

package com.hack23.cia.systemintegrationtest.user.politician;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPoliticianRankingConstants;

/**
 * The Class UserPoliticianTest.
 */
@Category(IntegrationTest.class)
public final class UserPoliticianTest extends AbstractUITest {

    /**
     * Verify politician ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
    }

    /**
     * Verify politician ranking overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_OVERVIEW);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_OVERVIEW);
    }

    /**
     * Verify politician ranking page visit history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }


    /**
     * Verify politician ranking data grid page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingDataGridPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
    }
}

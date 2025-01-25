package com.hack23.cia.systemintegrationtest.user.politician;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandPoliticianRankingConstants;

@Category(IntegrationTest.class)
public final class UserPoliticianTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_OVERVIEW);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingChartsPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_CHARTS);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_CHARTS);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingDataGridPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
    }
}

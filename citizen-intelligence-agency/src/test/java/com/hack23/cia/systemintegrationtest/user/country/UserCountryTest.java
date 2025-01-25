package com.hack23.cia.systemintegrationtest.user.country;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandCountryRankingConstants;

@Category(IntegrationTest.class)
public final class UserCountryTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_OVERVIEW);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryRankingDataGridPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_DATAGRID);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryRankingChartsPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_CHARTS);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_CHARTS);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryRankingPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COUNTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }
}

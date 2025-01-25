package com.hack23.cia.systemintegrationtest.user.country;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCountryRankingConstants;

/**
 * The Class UserCountryTest.
 */
@Category(IntegrationTest.class)
public final class UserCountryTest extends AbstractUITest {

    /**
     * Verify country ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING_OVERVIEW);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING_OVERVIEW);
    }


    /**
     * Verify country ranking page visit history page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCountryRankingPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING_PAGEVISIT_HISTORY);
        pageVisit.validatePage(PageCommandCountryRankingConstants.COMMAND_COUNTRY_RANKING_PAGEVISIT_HISTORY);
    }
}

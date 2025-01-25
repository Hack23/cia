package com.hack23.cia.systemintegrationtest.user.governmentbody;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandGovernmentBodyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandGovernmentBodyRankingConstants;

/**
 * The Class UserGovernmentBodyTest.
 */
@Category(IntegrationTest.class)
public final class UserGovernmentBodyTest extends AbstractUITest {

    /**
     * Verify government body page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyConstants.GOVERNMENT_BODY_COMMAND_EXPENDITURE);
        pageVisit.validatePage(PageCommandGovernmentBodyConstants.GOVERNMENT_BODY_COMMAND_EXPENDITURE);
    }

    /**
     * Verify government body ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID);
    }

    /**
     * Verify government body ranking overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW);
    }

    /**
     * Verify government bodies headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodiesHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT);
    }

    /**
     * Verify government bodies income page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodiesIncomePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME);
    }
}

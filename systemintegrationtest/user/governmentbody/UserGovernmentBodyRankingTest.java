public class UserGovernmentBodyRankingTest {
package com.hack23.cia.systemintegrationtest.user.governmentbody;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandGovernmentBodyRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;

/**
 * The Class UserGovernmentBodyRankingTest.
 */
@Category(IntegrationTest.class)
public final class UserGovernmentBodyRankingTest extends AbstractUITest implements PageCommandUserConstants {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyRankingDataGridPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyCommandPageVisitHistory() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.GOVERNMENT_BODY_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.GOVERNMENT_BODY_COMMAND_PAGEVISIT_HISTORY);
    }
}
    
}

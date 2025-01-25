package com.hack23.cia.systemintegrationtest.user.governmentbody;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandGovernmentBodyRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;
import com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode.GovernmentBodyDescriptionConstants;

/**
 * The Class UserGovernmentBodyRankingTest.
 */
@Category(IntegrationTest.class)
public final class UserGovernmentBodyRankingTest extends AbstractUITest implements PageCommandUserConstants {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyRankingDataGridPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID);
        pageVisit.verifyViewContent(GovernmentBodyDescriptionConstants.RANKING_HEADER,
				GovernmentBodyDescriptionConstants.RANKING_SUBTITLE,
				GovernmentBodyDescriptionConstants.RANKING_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW);
        pageVisit.verifyViewContent(GovernmentBodyDescriptionConstants.RANKING_HEADER,
        GovernmentBodyDescriptionConstants.RANKING_SUBTITLE,
        GovernmentBodyDescriptionConstants.RANKING_DESC);        
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODY_RANKING_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyRankingHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT);
        pageVisit.verifyViewContent(GovernmentBodyDescriptionConstants.HEADCOUNT_HEADER,
        GovernmentBodyDescriptionConstants.HEADCOUNT_SUBTITLE,
        GovernmentBodyDescriptionConstants.HEADCOUNT_DESC);        
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_HEADCOUNT);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyIncomePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME);
        pageVisit.verifyViewContent(GovernmentBodyDescriptionConstants.INCOME_HEADER,
            GovernmentBodyDescriptionConstants.INCOME_SUBTITLE,
            GovernmentBodyDescriptionConstants.INCOME_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_INCOME);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyExpenditurePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_EXPENDITURE);
        pageVisit.verifyViewContent(GovernmentBodyDescriptionConstants.EXPENDITURE_HEADER,
            GovernmentBodyDescriptionConstants.EXPENDITURE_SUBTITLE,
            GovernmentBodyDescriptionConstants.EXPENDITURE_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.COMMAND_GOVERNMENT_BODIES_EXPENDITURE);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyRankingPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.GOVERNMENT_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.verifyViewContent(GovernmentBodyDescriptionConstants.VISIT_HISTORY_HEADER,
            GovernmentBodyDescriptionConstants.VISIT_HISTORY_SUBTITLE,
            GovernmentBodyDescriptionConstants.VISIT_HISTORY_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.GOVERNMENT_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyGovernmentBodyCommandOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandGovernmentBodyRankingConstants.GOVERNMENT_BODY_COMMAN_OVERVIEW);
        pageVisit.verifyViewContent(GovernmentBodyDescriptionConstants.RANKING_HEADER,
            GovernmentBodyDescriptionConstants.RANKING_SUBTITLE,
            GovernmentBodyDescriptionConstants.RANKING_DESC);
        pageVisit.validatePage(PageCommandGovernmentBodyRankingConstants.GOVERNMENT_BODY_COMMAN_OVERVIEW);
    }
}

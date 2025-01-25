package com.hack23.cia.systemintegrationtest.user.committee;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCommitteeRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;

/**
 * The Class UserCommitteeRankingTest.
 */
@Category(IntegrationTest.class)
public final class UserCommitteeRankingTest extends AbstractUITest implements PageCommandUserConstants {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCommitteeRankingDataGridPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_DATAGRID);
        pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCommitteeRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_OVERVIEW);
        pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAllCommitteesByHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_ALL_COMMITTEES_BY_HEADCOUNT);
        pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_ALL_COMMITTEES_BY_HEADCOUNT);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCurrentCommitteesByHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT);
        pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCommitteeRankingCommandPageVisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyChartsCurrentCommitteesPage() throws Exception {
         pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_CHARTS_CURRENT_COMMITTEES);
         pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_CHARTS_CURRENT_COMMITTEES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCommitteesLinkPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEES_LINK);
        pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEES_LINK);
    }

}

package com.hack23.cia.systemintegrationtest.user.politician;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPoliticianRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;

/**
 * The Class UserPoliticianRankingTest.
 */
@Category(IntegrationTest.class)
public final class UserPoliticianRankingTest extends AbstractUITest implements PageCommandUserConstants {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingDatagridPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING_DATAGRID);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING_OVERVIEW);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingIndicatorsPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING_INDICATORS);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING_INDICATORS);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianCurrentMemberRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_CURRENT_MEMBER_RANKING);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_CURRENT_MEMBER_RANKING);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianAllMemberRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_ALL_MEMBER_RANKING);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_ALL_MEMBER_RANKING);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingCommandDatagridPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingCommandOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_OVERVIEW);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingCommandPagevisitHistoryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }
}

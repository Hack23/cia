package com.hack23.cia.systemintegrationtest.user.politician;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPoliticianRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;
import com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode.PoliticianRankingDescriptionConstants;

/**
 * The Class UserPoliticianRankingTest.
 */
@Category(IntegrationTest.class)
public final class UserPoliticianRankingTest extends AbstractUITest implements PageCommandUserConstants {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingDatagridPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING_DATAGRID);
        pageVisit.verifyViewContent(PoliticianRankingDescriptionConstants.RANKING_HEADER,
            PoliticianRankingDescriptionConstants.PERFORMANCE_SUBTITLE,
            PoliticianRankingDescriptionConstants.PERFORMANCE_DESC);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_RANKING_OVERVIEW);
        pageVisit.verifyViewContent(PoliticianRankingDescriptionConstants.RANKING_HEADER,
            PoliticianRankingDescriptionConstants.RANKING_SUBTITLE,
            PoliticianRankingDescriptionConstants.RANKING_DESC);
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
        pageVisit.verifyViewContent(PoliticianRankingDescriptionConstants.CURRENT_PARTIES_HEADER,
            PoliticianRankingDescriptionConstants.CURRENT_PARTIES_SUBTITLE,
            PoliticianRankingDescriptionConstants.CURRENT_PARTIES_DESC);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_CURRENT_MEMBER_RANKING);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyPoliticianAllMemberRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandPoliticianRankingConstants.COMMAND_POLITICIAN_ALL_MEMBER_RANKING);
        pageVisit.verifyViewContent(PoliticianRankingDescriptionConstants.ALL_PARTIES_HEADER,
            PoliticianRankingDescriptionConstants.ALL_PARTIES_SUBTITLE,
            PoliticianRankingDescriptionConstants.ALL_PARTIES_DESC);
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
        pageVisit.verifyViewContent(PoliticianRankingDescriptionConstants.VISIT_HISTORY_HEADER,
            PoliticianRankingDescriptionConstants.VISIT_HISTORY_SUBTITLE,
            PoliticianRankingDescriptionConstants.VISIT_HISTORY_DESC);
        pageVisit.validatePage(PageCommandPoliticianRankingConstants.POLITICIAN_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }
}

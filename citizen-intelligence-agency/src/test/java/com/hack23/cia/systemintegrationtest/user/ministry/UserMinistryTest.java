package com.hack23.cia.systemintegrationtest.user.ministry;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandMinistryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandMinistryRankingConstants;

@Category(IntegrationTest.class)
public final class UserMinistryTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.MINISTRY_COMMAND_CHARTS_CURRENT_BY_HEADCOUNT);
        pageVisit.validatePage(PageCommandMinistryConstants.MINISTRY_COMMAND_CHARTS_CURRENT_BY_HEADCOUNT);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_DATAGRID);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_DATAGRID);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_OVERVIEW);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_OVERVIEW);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyChartsCurrentMinistriesLeaderScoreboardPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyChartsCurrentMinistriesByHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
    }
}

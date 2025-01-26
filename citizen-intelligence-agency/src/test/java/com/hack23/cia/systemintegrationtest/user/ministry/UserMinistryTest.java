package com.hack23.cia.systemintegrationtest.user.ministry;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMinistryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMinistryRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;

/**
 * The Class UserMinistryTest.
 */
@Category(IntegrationTest.class)
public final class UserMinistryTest extends AbstractUITest implements PageCommandUserConstants {

    /**
     * Verify ministry page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryConstants.MINISTRY_COMMAND_CHARTS_CURRENT_BY_HEADCOUNT);
        pageVisit.verifyViewContent("Ministry Rankings", 
            "Current Ministries", 
            "Visual representation of current ministries and their total headcount.");
        pageVisit.validatePage(PageCommandMinistryConstants.MINISTRY_COMMAND_CHARTS_CURRENT_BY_HEADCOUNT);
    }

    /**
     * Verify ministry ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_DATAGRID);
        pageVisit.verifyViewContent("Ministry Rankings", 
            "All Ministries", 
            "Visual representation of all ministries and their total headcount.");
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_DATAGRID);
    }

    /**
     * Verify ministry ranking overview page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyMinistryRankingOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_OVERVIEW);
        pageVisit.verifyViewContent("Ministry Rankings", 
            "Page Visit History", 
            "Tracks and visualizes the history of page visits for ministry rankings.");
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_MINISTRY_RANKING_OVERVIEW);
    }

    /**
     * Verify charts current ministries leader scoreboard page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyChartsCurrentMinistriesLeaderScoreboardPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);
        pageVisit.verifyViewContent("Ministry Rankings", 
            "Leader Scoreboard", 
            "Visual representation of ministry leaders and their performance.");
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_LEADER_SCOREBOARD);
    }

    /**
     * Verify charts current ministries by headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyChartsCurrentMinistriesByHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
        pageVisit.verifyViewContent("Ministry Rankings", 
            "Current Parties", 
            "Visual representation of current parties and their headcount.");
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_CURRENT_MINISTRIES_BY_HEADCOUNT);
    }

    /**
     * Verify all ministries by headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAllMinistriesByHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT);
        pageVisit.verifyViewContent("Ministry Rankings", 
            "All Roles", 
            "Visual representation of all government roles.");
        pageVisit.validatePage(PageCommandMinistryRankingConstants.COMMAND_CHARTS_ALL_MINISTRIES_BY_HEADCOUNT);
    }
}

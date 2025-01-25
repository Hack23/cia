package com.hack23.cia.systemintegrationtest.user.committee;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandCommitteeConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandCommitteeRankingConstants;

/**
 * The Class UserCommitteeTest.
 */
@Category(IntegrationTest.class)
public final class UserCommitteeTest extends AbstractUITest {

    /**
     * Verify committee page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCommitteePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeConstants.COMMAND_COMMITTEES_BY_PARTY);
        pageVisit.validatePage(PageCommandCommitteeConstants.COMMAND_COMMITTEES_BY_PARTY);
    }

    /**
     * Verify committee ranking page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCommitteeRankingPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_DATAGRID);
        pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_DATAGRID);
    }

    /**
     * Verify current committees by party days served page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyCurrentCommitteesByPartyDaysServedPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeConstants.COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED);
        pageVisit.validatePage(PageCommandCommitteeConstants.COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED);
    }

    /**
     * Verify charts current committees page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyChartsCurrentCommitteesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeConstants.COMMAND_CHARTS_CURRENT_COMMITTEES);
        pageVisit.validatePage(PageCommandCommitteeConstants.COMMAND_CHARTS_CURRENT_COMMITTEES);
    }

    /**
     * Verify all committees by headcount page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAllCommitteesByHeadcountPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_ALL_COMMITTEES_BY_HEADCOUNT);
        pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_ALL_COMMITTEES_BY_HEADCOUNT);
    }
}

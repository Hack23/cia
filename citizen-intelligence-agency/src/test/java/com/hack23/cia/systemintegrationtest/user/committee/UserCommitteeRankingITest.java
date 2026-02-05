package com.hack23.cia.systemintegrationtest.user.committee;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCommitteeRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserConstants;
import com.hack23.cia.web.impl.ui.application.views.common.constants.CommitteeViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.constants.CommitteeRankingConstants;

/**
 * The Class UserCommitteeRankingITest.
 */
@Category(IntegrationTest.class)
public final class UserCommitteeRankingITest extends AbstractUITest implements PageCommandUserConstants {

	/**
	 * Verify committee ranking overview page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeRankingOverviewPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_OVERVIEW);
		pageVisit.verifyViewContent(CommitteeRankingConstants.CR_OVERVIEW_TITLE_HEADER,
				CommitteeRankingConstants.CR_OVERVIEW_TITLE, CommitteeRankingConstants.CR_OVERVIEW_DESCRIPTION);
		pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_OVERVIEW);
	}

	/**
	 * Verify committee ranking data grid page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeRankingDataGridPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_DATAGRID);
		pageVisit.verifyViewContent(CommitteeRankingConstants.CR_GRID_TITLE_HEADER,
				CommitteeRankingConstants.CR_GRID_TITLE, CommitteeRankingConstants.CR_GRID_DESCRIPTION);
		pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_COMMITTEE_RANKING_DATAGRID);
	}

	/**
	 * Verify committee ranking command page visit history page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeRankingCommandPageVisitHistoryPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY);
		pageVisit.verifyViewContent(CommitteeRankingConstants.CR_VISIT_TITLE_HEADER,
				CommitteeRankingConstants.CR_VISIT_TITLE, CommitteeRankingConstants.CR_VISIT_DESCRIPTION);
		pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMITTEE_RANKING_COMMAND_PAGEVISIT_HISTORY);
	}

	/**
	 * Verify all committees by headcount page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyAllCommitteesByHeadcountPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_ALL_COMMITTEES_BY_HEADCOUNT);
		pageVisit.verifyViewContent(CommitteeRankingConstants.CR_ALL_TITLE_HEADER,
				CommitteeRankingConstants.CR_ALL_TITLE, CommitteeRankingConstants.CR_ALL_DESCRIPTION);
		pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_ALL_COMMITTEES_BY_HEADCOUNT);
	}

	/**
	 * Verify current committees by headcount page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCurrentCommitteesByHeadcountPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT);
		pageVisit.verifyViewContent(CommitteeRankingConstants.CR_CURRENT_TITLE_HEADER,
				CommitteeRankingConstants.CR_CURRENT_TITLE, CommitteeRankingConstants.CR_CURRENT_DESCRIPTION);
		pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_CURRENT_COMMITTEES_BY_HEADCOUNT);
	}

	/**
	 * Verify charts current committees page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyChartsCurrentCommitteesPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED);
		pageVisit.verifyViewContent(CommitteeViewConstants.CR_CURRENT_PARTY_TITLE_HEADER,
				CommitteeViewConstants.CR_CURRENT_PARTY_TITLE,
				CommitteeViewConstants.CR_CURRENT_PARTY_DESCRIPTION);
		pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_CURRENT_COMMITTEES_BY_PARTY_DAYS_SERVED);
	}

	/**
	 * Verify charts all parties page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyChartsAllPartiesPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandCommitteeRankingConstants.COMMAND_CURRENT_COMMITTEES_ALL_PARTIES);
		pageVisit.verifyViewContent(CommitteeViewConstants.CR_PARTY_TITLE_HEADER, CommitteeViewConstants.CR_PARTY_TITLE,
				CommitteeViewConstants.CR_PARTY_DESCRIPTION);
		pageVisit.validatePage(PageCommandCommitteeRankingConstants.COMMAND_CURRENT_COMMITTEES_ALL_PARTIES);
	}

}

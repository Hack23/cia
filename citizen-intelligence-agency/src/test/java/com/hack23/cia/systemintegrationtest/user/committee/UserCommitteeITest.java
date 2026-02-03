package com.hack23.cia.systemintegrationtest.user.committee;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandCommitteeConstants;
import com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode.CommitteePageTitleConstants;
import com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode.CommitteeViewConstants;

/**
 * The Class UserBallotITest.
 */
@Category(IntegrationTest.class)
public final class UserCommitteeITest extends AbstractUITest {

	/** The committee id. */
	private static final String COMMITTEE_ID = "JuU";

	/**
	 * Verify committee overview page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeOverviewPage() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_OVERVIEW.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteePageTitleConstants.OV_TITLE_HEADER, CommitteePageTitleConstants.OV_TITLE);
		pageVisit.validatePage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_OVERVIEW.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee document history page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeDocumentHistoryPage() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DOCUMENT_HISTORY.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteePageTitleConstants.DH_TITLE_HEADER, CommitteePageTitleConstants.DH_TITLE,
				CommitteePageTitleConstants.DH_DESCRIPTION);
		pageVisit.validatePage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DOCUMENT_HISTORY.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee document activity page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeDocumentActivityPage() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DOCUMENT_ACTIVITY.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteePageTitleConstants.DA_TITLE_HEADER, CommitteePageTitleConstants.DA_TITLE,
				CommitteePageTitleConstants.DA_DESCRIPTION);
		pageVisit.validatePage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DOCUMENT_ACTIVITY.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee current members page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeCurrentMembersPage() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_CURRENT_MEMBERS.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteePageTitleConstants.CM_TITLE_HEADER, CommitteePageTitleConstants.CM_TITLE,
				CommitteePageTitleConstants.CM_DESCRIPTION);
		pageVisit.validatePage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_CURRENT_MEMBERS.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee member history page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeMemberHistoryPage() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_MEMBER_HISTORY.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteePageTitleConstants.MH_TITLE_HEADER, CommitteePageTitleConstants.MH_TITLE,
				CommitteePageTitleConstants.MH_DESCRIPTION);
		pageVisit.validatePage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_MEMBER_HISTORY.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee role ghant page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeRoleGhantPage() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_ROLE_GHANT.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteePageTitleConstants.RG_TITLE_HEADER, CommitteePageTitleConstants.RG_TITLE,
				CommitteePageTitleConstants.RG_DESCRIPTION);
		pageVisit.validatePage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_ROLE_GHANT.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee ballot decision summary page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeBallotDecisionSummaryPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandCommitteeConstants.COMMAND_COMMITTEE_BALLOT_DECISION_SUMMARY
				.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteeViewConstants.BD_TITLE_HEADER, CommitteeViewConstants.BD_TITLE,
				CommitteeViewConstants.BD_DESCRIPTION);
		pageVisit.validatePage(PageCommandCommitteeConstants.COMMAND_COMMITTEE_BALLOT_DECISION_SUMMARY
				.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee decision summary page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeDecisionSummaryPage() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DECISION_SUMMARY.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent("Committee Decision Summary", "Decision Summary",
				"Overview of decisions made by the committee.");
		pageVisit.validatePage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_DECISION_SUMMARY.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee decision type daily summary page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeDecisionTypeDailySummaryPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandCommitteeConstants.COMMAND_COMMITTEE_DECISION_TYPE_DAILY_SUMMARY
				.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteeViewConstants.DD_TITLE_HEADER,
			    CommitteeViewConstants.DD_TITLE,
			    CommitteeViewConstants.DD_DESCRIPTION);
		pageVisit.validatePage(PageCommandCommitteeConstants.COMMAND_COMMITTEE_DECISION_TYPE_DAILY_SUMMARY
				.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee page visit history page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteePageVisitHistoryPage() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_PAGEVISIT_HISTORY.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteePageTitleConstants.PV_TITLE_HEADER, CommitteePageTitleConstants.PV_TITLE,
				CommitteePageTitleConstants.PV_DESCRIPTION);
		pageVisit.validatePage(
				PageCommandCommitteeConstants.COMMAND_COMMITTEE_PAGEVISIT_HISTORY.createItemPageCommand(COMMITTEE_ID));
	}

	/**
	 * Verify committee decision flow chart page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyCommitteeDecisionFlowChartPage() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandCommitteeConstants.COMMAND_CHARTS_DECISION_FLOW.createItemPageCommand(COMMITTEE_ID));
		pageVisit.verifyViewContent(CommitteePageTitleConstants.DF_TITLE_HEADER, CommitteePageTitleConstants.DF_TITLE,
				CommitteePageTitleConstants.DF_DESCRIPTION);
		pageVisit.validatePage(
				PageCommandCommitteeConstants.COMMAND_CHARTS_DECISION_FLOW.createItemPageCommand(COMMITTEE_ID));
	}
}

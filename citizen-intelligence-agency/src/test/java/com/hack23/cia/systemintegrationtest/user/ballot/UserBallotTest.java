package com.hack23.cia.systemintegrationtest.user.ballot;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandBallotConstants;
import com.hack23.cia.web.impl.ui.application.views.user.ballot.pagemode.BallotViewConstants;

/**
 * The Class UserBallotTest.
 */
@Category(IntegrationTest.class)
public final class UserBallotTest extends AbstractUITest {

	/** The Constant BALLOT_ID_NO_DEC. */
	private static final String BALLOT_ID_NO_DEC = "6459ABCF-ABDB-4511-87B8-C7CC09737090";
	/** The Constant BALLOT_ID. */
	private static final String BALLOT_ID = "2782DA9D-A6D1-4925-B1AC-2520A82A9BA8";

	/**
	 * Verify ballot overview page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyBallotOverviewDecisionVotePage() throws Exception {
		pageVisit.visitDirectPage(PageCommandBallotConstants.COMMAND_BALLOT_OVERVIEW.createItemPageCommand(BALLOT_ID));
		pageVisit.verifyViewContent(BallotViewConstants.OVERVIEW_MAIN_TITLE_PREFIX,
				BallotViewConstants.OVERVIEW_CARD_BALLOT_INFO, BallotViewConstants.OVERVIEW_PAGE_DESCRIPTION);
		pageVisit.validatePage(PageCommandBallotConstants.COMMAND_BALLOT_OVERVIEW.createItemPageCommand(BALLOT_ID));
	}


	/**
	 * Verify ballot overview simple vote page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyBallotOverviewSimpleVotePage() throws Exception {
		pageVisit.visitDirectPage(PageCommandBallotConstants.COMMAND_BALLOT_OVERVIEW.createItemPageCommand(BALLOT_ID_NO_DEC));
		pageVisit.verifyViewContent(BallotViewConstants.OVERVIEW_MAIN_TITLE_PREFIX,
				BallotViewConstants.OVERVIEW_CARD_BALLOT_INFO, BallotViewConstants.OVERVIEW_PAGE_DESCRIPTION);
		pageVisit.validatePage(PageCommandBallotConstants.COMMAND_BALLOT_OVERVIEW.createItemPageCommand(BALLOT_ID_NO_DEC));
	}


	/**
	 * Verify ballot chart page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyBallotChartPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandBallotConstants.COMMAND_BALLOT_CHARTS.createItemPageCommand(BALLOT_ID));
		pageVisit.verifyViewContent(BallotViewConstants.CHARTS_TITLE_PREFIX, BallotViewConstants.CHARTS_SUBTITLE,
				BallotViewConstants.CHARTS_DESCRIPTION);
		pageVisit.validatePage(PageCommandBallotConstants.COMMAND_BALLOT_CHARTS.createItemPageCommand(BALLOT_ID));
	}

	/**
	 * Verify ballot chart simple vote page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyBallotChartSimpleVotePage() throws Exception {
		pageVisit.visitDirectPage(PageCommandBallotConstants.COMMAND_BALLOT_CHARTS.createItemPageCommand(BALLOT_ID_NO_DEC));
		pageVisit.verifyViewContent(BallotViewConstants.CHARTS_TITLE_PREFIX, BallotViewConstants.CHARTS_SUBTITLE,
				BallotViewConstants.CHARTS_DESCRIPTION);
		pageVisit.validatePage(PageCommandBallotConstants.COMMAND_BALLOT_CHARTS.createItemPageCommand(BALLOT_ID_NO_DEC));
	}

}

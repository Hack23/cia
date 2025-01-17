package com.hack23.cia.systemintegrationtest.user;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public final class BallotViewTest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testBallotView() throws Exception {

		pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.BALLOT_VIEW_NAME, ""));
		pageVisit.verifyPageContent("Ballot View");

	}
}

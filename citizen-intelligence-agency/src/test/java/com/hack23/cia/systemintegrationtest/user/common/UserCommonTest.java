package com.hack23.cia.systemintegrationtest.user.common;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMainViewConstants;

/**
 * The Class UserCommonTest.
 */
@Category(IntegrationTest.class)
public final class UserCommonTest extends AbstractUITest {


	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyMainOverviewPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_MAINVIEW_OVERVIEW);
		pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_MAINVIEW_OVERVIEW);
	}


	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyPageVisitPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_MAINVIEW_PAGEVISITHISTORY);
		pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_MAINVIEW_PAGEVISITHISTORY);
	}


	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyLeaderboardPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_DASHBOARDVIEW_OVERVIEW);
		pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_DASHBOARDVIEW_OVERVIEW);
	}


	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyLoginPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_LOGIN);
		pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_LOGIN);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyRegisterPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_REGISTER);
		pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_REGISTER);
	}

}

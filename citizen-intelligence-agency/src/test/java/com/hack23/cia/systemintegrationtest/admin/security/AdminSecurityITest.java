package com.hack23.cia.systemintegrationtest.admin.security;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.admin.AbstractAdminTest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandAdminConstants;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

/**
 * The Class UserHomeITest.
 */
@Category(IntegrationTest.class)
public final class AdminSecurityITest extends AbstractAdminTest {

	/**
	 * Verify user account.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyUserAccount() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
		pageVisit.verifyViewContent(AdminViewConstants.ADMIN_USER_ACCOUNT_MANAGEMENT, AdminViewConstants.USER_ACCOUNT_OVERVIEW,
				AdminViewConstants.USER_ACCOUNT_MANAGEMENT_DESCRIPTION);
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
	}

	/**
	 * Verify session access.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifySessionAccess() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
		pageVisit.verifyViewContent(AdminViewConstants.ADMIN_APPLICATION_SESSIONS, AdminViewConstants.SESSION_DETAILS,
				AdminViewConstants.SESSION_OVERVIEW);
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
	}

	/**
	 * Verify anonymous access.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyNoAnonymousAccess() throws Exception {
		pageVisit.logoutUser();

		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
		pageVisit.verifyPageContent(AdminViewConstants.ACCESS_DENIED + AdminViews.ADMIN_USERACCOUNT_VIEW_NAME);

		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_MONITORING);
		pageVisit.verifyPageContent(AdminViewConstants.ACCESS_DENIED + AdminViews.ADMIN_MONITORING_VIEW_NAME);

		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
		pageVisit.verifyPageContent(AdminViewConstants.ACCESS_DENIED + AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME);

		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
		pageVisit.verifyPageContent(AdminViewConstants.ACCESS_DENIED + AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME);

		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AUTHOR_DATASUMMARY);
		pageVisit.verifyPageContent(AdminViewConstants.ACCESS_DENIED + AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME);

		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATA_QUALITY);
		pageVisit.verifyPageContent(AdminViewConstants.ACCESS_DENIED + AdminViews.ADMIN_DATA_QUALITY_VIEW_NAME);


	}

}

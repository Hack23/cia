package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public class AdminUserManagementSystemTest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyUserAccountManagementTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
		pageVisit.verifyPageContent("User Account Management");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyEmailConfigurationTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_EMAIL);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_EMAIL_MANAGEMENT);
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_EMAIL);
	}
}

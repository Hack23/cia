package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public final class AdminUserManagementTest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldManageLanguageSettings() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_LANGUAGE);
		pageVisit.verifyPageContent("Language");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_LANGUAGE);

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldManageUserAccount() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
		pageVisit.verifyPageContent("User");
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_USERACCOUNT);

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldManageCountrySettings() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_COUNTRY);
		pageVisit.verifyPageContent("Country");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_COUNTRY);

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldDeleteUserAccount() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
		pageVisit.verifyPageContent("Admin User Account Management");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
		final WebElement deleteButton = pageVisit.findButton("Perform DELETE");
		assertNotNull("Expect to find a Delete Button", deleteButton);
		pageVisit.performClickAction(deleteButton);
		pageVisit.checkNotificationMessage("Operation completeddesc");

	}
}


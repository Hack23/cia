package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@Category(IntegrationTest.class)
public final class AdminUserManagementTest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldManageLanguageSettings() throws Exception {
		pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
		pageVisit.verifyPageContent("Language");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldManageUserAccount() throws Exception {
		pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
		pageVisit.verifyPageContent("User");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldManageCountrySettings() throws Exception {
		pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));
		pageVisit.verifyPageContent("Country");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldDeleteUserAccount() throws Exception {
		pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
		pageVisit.verifyPageContent("Admin User Account Management");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
		final WebElement deleteButton = pageVisit.findButton("Perform DELETE");
		assertNotNull("Expect to find a Delete Button", deleteButton);
		pageVisit.performClickAction(deleteButton);
		pageVisit.checkNotificationMessage("Operation completeddesc");

	}
}

package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@Category(IntegrationTest.class)
public final class SessionManagementTest extends AbstractAdminTest {
	@Test(timeout = DEFAULT_TIMEOUT)

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldHandleSessionPagination() throws Exception {

		pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_APPLICATION_SESSION);

		final WebElement nextPageButton = pageVisit.findButton("next page");
		pageVisit.performClickAction(nextPageButton);

		// Test last page navigation
		final WebElement lastPageButton = pageVisit.findButton("last page");
		pageVisit.performClickAction(lastPageButton);

		final WebElement firstPageButton = pageVisit.findButton("first page");
		pageVisit.performClickAction(firstPageButton);


	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldShowSessionDetails() throws Exception {

		pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_APPLICATION_SESSION);
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
		pageVisit.verifyPageContent(AdminViewConstants.APPLICATION_ACTION_EVENT);

	}
}

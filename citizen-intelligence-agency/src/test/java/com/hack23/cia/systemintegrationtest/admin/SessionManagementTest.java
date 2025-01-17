package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public final class SessionManagementTest extends AbstractAdminTest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldHandleSessionPagination() throws Exception {

		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);

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

		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_APPLICATION_SESSION);
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
		pageVisit.verifyPageContent(AdminViewConstants.APPLICATION_SESSION_DETAILS);

	}
}

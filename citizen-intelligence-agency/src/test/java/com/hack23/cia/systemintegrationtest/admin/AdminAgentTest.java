package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public final class AdminAgentTest extends AbstractUITest {

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldStartImportOperations() throws Exception {
		pageVisit.loginAsAdmin();
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_AGENT_OPERATION_OVERVIEW);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_AGENT_OPERATION);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_AGENT_OPERATION_TASKS_OVERVIEW);

		final WebElement worldBankButton = pageVisit
				.findButton(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_SWEDEN_BUTTON);
		assertNotNull(worldBankButton);

		final WebElement riksdagenButton = pageVisit
				.findButton(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_SWEDEN_BUTTON);
		assertNotNull(riksdagenButton);

		pageVisit.performClickAction(riksdagenButton);
	}
}

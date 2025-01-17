package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

// ...imports...

@Category(IntegrationTest.class)
public class AdminApplicationSystemTest extends AbstractAdminTest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyApplicationSessionPaginationTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_APPLICATION_SESSION);

		// Test last page
		pageVisit.performClickAction(pageVisit.findButton("last page"));

		// Test next page
		pageVisit.performClickAction(pageVisit.findButton("first page"));
		pageVisit.performClickAction(pageVisit.findButton("next page"));

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyApplicationEventsChartTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS_CHARTS);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_APPLICATION_EVENT_CHARTS);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyApplicationSessionChartsTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION_CHARTS);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_APPLICATION_SESSION_CHARTS);
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION_CHARTS);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyAgentOperationsTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_AGENT_OPERATION_OVERVIEW);
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyApplicationGridOperationsTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_APPLICATION_SESSION);
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
		pageVisit.verifyPageContent(AdminViewConstants.APPLICATION_SESSION_DETAILS);
	}

	// ...other application management tests...
}

package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public class AdminPageSystemTest extends AbstractAdminTest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyAgencyPageTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENCY);
		pageVisit.verifyPageContent("Agency");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AGENCY);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyAgentOperationTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
		pageVisit.verifyPageContent("Agent Operation");
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyApplicationConfigurationPageTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
		pageVisit.verifyPageContent("Application Configuration");
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyApplicationEventsPageTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS);
		pageVisit.verifyPageContent("Admin Application Events");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyApplicationSessionPageTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
		pageVisit.verifyPageContent("Application Session");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
		pageVisit.verifyPageContent("ApplicationActionEvent");
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyMonitoringPageFailedAccessTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_MONITORING);
		pageVisit.verifyPageContent("Access denied:adminmonitoring");
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyMonitoringPageSuccessTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_MONITORING);
		pageVisit.verifyPageContent("Admin Monitoring");
		assertFalse("Dont expect this content",
				pageVisit.getIframesHtmlBodyAsText().contains("Login with Username and Password"));
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyDataSummaryPageOperationsTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATASUMMARY);

		// Verify all buttons exist
		assertNotNull("Expect to find a Refresh Views Button", pageVisit.findButton("Refresh Views"));
		assertNotNull("Expect to find Remove Application History button",
				pageVisit.findButton("Remove Application History"));
		assertNotNull("Expect to find Remove Documents button", pageVisit.findButton("Remove Documents"));
		assertNotNull("Expect to find Remove Politicians button", pageVisit.findButton("Remove Politicians"));
		assertNotNull("Expect to find Update Search Index button", pageVisit.findButton("Update Search Index"));

		// Test refresh views operation
		pageVisit.performClickAction(pageVisit.findButton("Refresh Views"));

		// Test search index update
		pageVisit.performClickAction(pageVisit.findButton("Update Search Index"));
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyLanguagePageTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_LANGUAGE);
		pageVisit.verifyPageContent("Language");
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_LANGUAGE);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyUserAccountPageTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
		pageVisit.verifyPageContent("User Account");
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_USERACCOUNT);
	}
}

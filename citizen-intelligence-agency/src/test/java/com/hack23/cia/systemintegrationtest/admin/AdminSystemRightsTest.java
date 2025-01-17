package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public class AdminSystemRightsTest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyMonitoringDeniedForAnonymousTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_MONITORING);
		pageVisit.verifyPageContent(AdminViewConstants.MONITORING_ACCESS_DENIED);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyAdminConfigurationsDeniedForAnonymousTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
		pageVisit.verifyPageContent(AdminViewConstants.ACCESS_DENIED);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyAdminDataManagementDeniedForAnonymousTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATASUMMARY);
		pageVisit.verifyPageContent(AdminViewConstants.ACCESS_DENIED);
	}
}


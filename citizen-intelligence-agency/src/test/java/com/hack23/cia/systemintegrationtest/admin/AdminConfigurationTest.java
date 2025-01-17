package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public final class AdminConfigurationTest extends AbstractAdminTest {

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldUpdateConfiguration() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_APPLICATION_CONFIGURATION);
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
		pageVisit.updateConfigurationProperty("Update Configuration.propertyValue", String.valueOf(false));
		pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
	}
}


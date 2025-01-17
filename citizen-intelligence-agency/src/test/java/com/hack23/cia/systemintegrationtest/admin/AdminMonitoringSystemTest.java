package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public class AdminMonitoringSystemTest extends AbstractAdminTest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyMonitoringAccessAndContentTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_MONITORING);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_MONITORING);
	}

}

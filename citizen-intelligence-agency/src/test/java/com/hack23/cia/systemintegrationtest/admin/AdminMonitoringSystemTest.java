package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public class AdminMonitoringSystemTest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyMonitoringAccessAndContentTest() throws Exception {
		pageVisit.loginAsAdmin();
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_MONITORING);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_MONITORING);
	}

}

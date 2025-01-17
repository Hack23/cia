package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

@Category(IntegrationTest.class)
public final class AdminDataTest extends AbstractAdminTest {

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldViewAdminData() throws Exception {
		pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATASUMMARY);
		pageVisit.verifyPageContent(AdminViewConstants.ADMIN_DATA_SUMMARY_OVERVIEW);

	}
}

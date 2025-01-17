package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

public final class AdminPortalTest extends AbstractAdminTest {

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldDisplayPortalManagement() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_PORTAL);
        
        verifyViewContent(
            AdminViewConstants.ADMIN_PORTAL_MANAGEMENT,
            AdminViewConstants.PORTAL_OVERVIEW,
            AdminViewConstants.PORTAL_MANAGEMENT_DESCRIPTION
        );
    }
}

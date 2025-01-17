package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

public final class AdminAgencyTest extends AbstractAdminTest {
    
    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldDisplayAgencyManagement() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENCY);
        
        verifyViewContent(
            AdminViewConstants.ADMIN_AGENCY_MANAGEMENT,
            AdminViewConstants.AGENCY_OVERVIEW,
            AdminViewConstants.AGENCY_MANAGEMENT_DESCRIPTION
        );
    }
}

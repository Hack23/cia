package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

public final class AdminCountryTest extends AbstractAdminTest {

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldDisplayCountryManagement() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_COUNTRY);
        
        verifyViewContent(
            AdminViewConstants.ADMIN_COUNTRY_MANAGEMENT,
            AdminViewConstants.COUNTRY_OVERVIEW,
            AdminViewConstants.COUNTRY_MANAGEMENT_DESCRIPTION
        );
    }
}

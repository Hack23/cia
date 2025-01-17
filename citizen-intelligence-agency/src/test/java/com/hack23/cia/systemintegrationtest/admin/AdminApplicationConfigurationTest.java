package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

public final class AdminApplicationConfigurationTest extends AbstractAdminTest {

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldDisplayAppConfig() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_CONFIGURATION);
        
        verifyViewContent(
            AdminViewConstants.ADMIN_APPLICATION_CONFIGURATION,
            AdminViewConstants.APPLICATION_CONFIGURATION_OVERVIEW,
            AdminViewConstants.APPLICATION_CONFIGURATION_DESCRIPTION
        );
    }
}

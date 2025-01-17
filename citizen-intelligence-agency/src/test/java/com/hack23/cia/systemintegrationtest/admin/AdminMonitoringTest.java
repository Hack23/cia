package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

public final class AdminMonitoringTest extends AbstractAdminTest {

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldDisplayMonitoring() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_MONITORING);
        
        verifyViewContent(
            AdminViewConstants.ADMIN_MONITORING,
            AdminViewConstants.MONITORING_CONTEXT_PATH
        );
    }
}

package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;

import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

public final class AdminEventChartTest extends AbstractAdminTest {

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldDisplayEventCharts() throws Exception {
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS_CHARTS);
        
        verifyViewContent(
            AdminViewConstants.ADMIN_APPLICATION_EVENT_CHARTS,
            AdminViewConstants.EVENT_ANALYSIS,
            AdminViewConstants.EVENT_DETAILS
        );
    }
}

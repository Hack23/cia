package com.hack23.cia.systemintegrationtest.user.views;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;

@Category(IntegrationTest.class)
public final class DashboardViewTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDashboardView() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_DASHBOARDVIEW_OVERVIEW);
        pageVisit.verifyPageContent("Dashboard");
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_DASHBOARDVIEW_OVERVIEW);
    }
}
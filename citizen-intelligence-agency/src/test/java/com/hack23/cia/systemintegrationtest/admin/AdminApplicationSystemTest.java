package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

// ...imports...

@Category(IntegrationTest.class)
public class AdminApplicationSystemTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationSessionPaginationTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
        pageVisit.verifyPageContent("Application Session");
        
        // Test last page
        pageVisit.performClickAction(pageVisit.findButton("last page"));
        
        // Test next page
        pageVisit.performClickAction(pageVisit.findButton("next page"));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationEventsChartTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS_CHARTS);
        pageVisit.verifyPageContent("Charts");
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationSessionChartsTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION_CHARTS);
        pageVisit.verifyPageContent("Admin Application Session Charts");
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION_CHARTS);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAgentOperationsTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
        pageVisit.verifyPageContent("Agent Operations");
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AGENT_OPERATION);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyApplicationGridOperationsTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
        pageVisit.verifyPageContent("Application Session");
        pageVisit.selectFirstGridRow();
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_APPLICATION_SESSION);
        pageVisit.verifyPageContent("ApplicationActionEvent");
    }

    // ...other application management tests...
}

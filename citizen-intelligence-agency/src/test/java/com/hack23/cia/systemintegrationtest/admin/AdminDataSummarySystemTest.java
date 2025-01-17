package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandAdminConstants;

// ...imports...

@Category(IntegrationTest.class)
public class AdminDataSummarySystemTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDataManagementOperationsTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATASUMMARY);
        
        // Test refresh views
        WebElement refreshButton = pageVisit.findButton("Refresh Views");
        assertNotNull(refreshButton);
        pageVisit.performClickAction(refreshButton);
        
        // Test update search index
        WebElement indexButton = pageVisit.findButton("Update Search Index");
        assertNotNull(indexButton);
        pageVisit.performClickAction(indexButton);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDataManagementRemoveOperationsTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATASUMMARY);
        
        assertNotNull("Expect to find Remove Application History button", 
            pageVisit.findButton("Remove Application History"));
        assertNotNull("Expect to find Remove Documents button", 
            pageVisit.findButton("Remove Documents"));
        assertNotNull("Expect to find Remove Politicians button", 
            pageVisit.findButton("Remove Politicians"));
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyAuthorDataSummaryTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_AUTHOR_DATASUMMARY);
        pageVisit.verifyPageContent("Author Summary");
        pageVisit.validatePage(PageCommandAdminConstants.COMMAND_AUTHOR_DATASUMMARY);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyRefreshViewsTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATASUMMARY);
        final WebElement refreshViewsButton = pageVisit.findButton("Refresh Views");
        assertNotNull("Expect to find a Refresh Views Button", refreshViewsButton);
        pageVisit.performClickAction(refreshViewsButton);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyRemoveDataOperationsButtonsTest() throws Exception {
        pageVisit.loginAsAdmin();
        pageVisit.visitDirectPage(PageCommandAdminConstants.COMMAND_DATASUMMARY);
        assertNotNull("Expect to find Remove Application History button", pageVisit.findButton("Remove Application History"));
        assertNotNull("Expect to find Remove Documents button", pageVisit.findButton("Remove Documents"));
        assertNotNull("Expect to find Remove Politicians button", pageVisit.findButton("Remove Politicians"));
    }

    // ...other data management tests...
}

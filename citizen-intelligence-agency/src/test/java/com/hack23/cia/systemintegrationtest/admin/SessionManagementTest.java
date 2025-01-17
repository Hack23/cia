package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.suites.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@Category(IntegrationTest.class)
public final class SessionManagementTest extends AbstractUITest {
  
    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldHandleSessionPagination() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.loginAsAdmin();
                pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
                pageVisit.verifyPageContent("Application Session");
                
                // Test last page navigation
                WebElement lastPageButton = pageVisit.findButton("last page");
                pageVisit.performClickAction(lastPageButton);
                
                // Test next page navigation
                WebElement nextPageButton = pageVisit.findButton("next page");
                pageVisit.performClickAction(nextPageButton);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Test interrupted", e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, TestConstants.DEFAULT_MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldShowSessionDetails() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.loginAsAdmin();
                pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
                pageVisit.verifyPageContent("Application Session");
                pageVisit.selectFirstGridRow();
                pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
                pageVisit.verifyPageContent("ApplicationActionEvent");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, TestConstants.DEFAULT_MAX_RETRIES);
    }
}

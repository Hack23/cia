package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.suites.TestConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@Category(IntegrationTest.class)
public final class AdminConfigurationTest extends AbstractUITest {

   
 
    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldUpdateConfiguration() throws Exception {
        Assert.assertNotNull("NO_WEBDRIVER_EXIST_FOR_BROWSER", driver);

        try {
            retryOnFailure(() -> {
                try {
                    pageVisit.loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(
                            new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Admin Application Configuration");
                    pageVisit.selectFirstGridRow();
                    pageVisit.validatePage(
                            new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));
                    pageVisit.updateConfigurationProperty("Update Configuration.propertyValue", String.valueOf(false));
                    pageVisit.validatePage(
                            new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError | Exception e) {
            throw new RuntimeException("Test failed", e);
        }
    }
}

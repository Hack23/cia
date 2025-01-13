package com.hack23.cia.systemintegrationtest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class AdminConfigurationTest extends AbstractUITest {

    private final UserPageVisit pageVisit;

    public AdminConfigurationTest(Browser browser) {
        this.pageVisit = new UserPageVisit(getWebDriver(), browser);
    }

    @Override
    protected Browser getBrowser() {
        return Browser.CHROME;
    }

    @Parameters(name = "AdminConfigTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldUpdateConfiguration() throws Exception {
        final WebDriver driver = getWebDriver();
        Assert.assertNotNull("NO_WEBDRIVER_EXIST_FOR_BROWSER " + getBrowser().toString(), driver);

        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
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

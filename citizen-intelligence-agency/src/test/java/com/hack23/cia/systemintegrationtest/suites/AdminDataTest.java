package com.hack23.cia.systemintegrationtest.suites;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.Browser;
import com.hack23.cia.systemintegrationtest.IntegrationTest;
import com.hack23.cia.systemintegrationtest.TestConstants;
import com.hack23.cia.systemintegrationtest.UserPageVisit;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class AdminDataTest extends AbstractUITest {

    private final Browser browser;
    private final UserPageVisit pageVisit;

    public AdminDataTest(final Browser browser) {
        this.browser = browser;
        this.pageVisit = new UserPageVisit(getWebDriver(), browser);
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }

    @Parameters(name = "AdminDataTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldViewAdminData() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));
                pageVisit.verifyPageContent("Admin Data Summary");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, TestConstants.DEFAULT_MAX_RETRIES);
    }
}

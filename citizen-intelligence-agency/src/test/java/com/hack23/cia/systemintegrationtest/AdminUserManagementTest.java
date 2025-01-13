package com.hack23.cia.systemintegrationtest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebElement;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class AdminUserManagementTest extends AbstractUITest {

    private final Browser browser;
    private final UserPageVisit pageVisit;

    public AdminUserManagementTest(final Browser browser) {
        this.browser = browser;
        this.pageVisit = new UserPageVisit(getWebDriver(), browser);
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }

    @Parameters(name = "AdminUserTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldManageLanguageSettings() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Language");
                    pageVisit.selectFirstGridRow();
                    pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldManageUserAccount() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("User");
                    pageVisit.selectFirstGridRow();
                    pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldManageCountrySettings() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Country");
                    pageVisit.selectFirstGridRow();
                    pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldDeleteUserAccount() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Admin User Account Management");
                    pageVisit.selectFirstGridRow();
                    pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
                    final WebElement deleteButton = pageVisit.findButton("Perform DELETE");
                    assertNotNull("Expect to find a Delete Button", deleteButton);
                    pageVisit.performClickAction(deleteButton);
                    pageVisit.checkNotificationMessage("Operation completeddesc");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

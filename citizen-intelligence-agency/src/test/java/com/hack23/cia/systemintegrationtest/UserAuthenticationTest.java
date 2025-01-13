package com.hack23.cia.systemintegrationtest;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class UserAuthenticationTest extends AbstractUITest {
    private static final int MAX_RETRIES = TestConstants.DEFAULT_MAX_RETRIES;
    private static final String EMAIL_SUFFIX = "@test.com";
    private static final String PATH_PREFIX = "main/";
    private static final String ERROR_USER_EXISTS = "USER_ALREADY_EXISTS";
    private static final String ERROR_INVALID_CREDENTIALS = "USERNAME_OR_PASSWORD_DO_NOT_MATCH";
    private final Browser browser;
    private final UserPageVisit pageVisit;

    public UserAuthenticationTest(final Browser browser) {
        this.browser = browser;
        this.pageVisit = new UserPageVisit(getWebDriver(), browser);
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }

    private String generatePassword() {
        return "Test123!" + UUID.randomUUID().toString();
    }

    @Parameters(name = "UserAuthTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldRegisterNewUser() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.REGISTER.toString()));

                String username = UUID.randomUUID().toString();
                String password = generatePassword();

                pageVisit.registerNewUser(username, password);
                pageVisit.logoutUser();

                UserPageVisit loginPageVisit = new UserPageVisit(getWebDriver(), browser);
                loginPageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.LOGIN.toString()));
                loginPageVisit.loginUser(username + EMAIL_SUFFIX, password);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldRegisterUserWithWeakPasswordFail() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.REGISTER.toString()));

                String username = UUID.randomUUID().toString();
                pageVisit.registerNewUserCheckView(username, "weak",
                        PATH_PREFIX + ApplicationPageMode.REGISTER.toString());
                pageVisit.checkNotificationMessage("Register failed:" +
                        "[Password must be 8 or more characters in length., Password must contain 1 or more uppercase characters., "
                        +
                        "Password must contain 1 or more digit characters., Password must contain 1 or more special characters.]");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldNotRegisterDuplicateUser() throws Exception {
        retryOnFailure(() -> {
            try {
                String username = UUID.randomUUID().toString();
                String password = generatePassword();

                // First registration
                pageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.REGISTER.toString()));
                pageVisit.registerNewUser(username, password);
                pageVisit.logoutUser();

                // Try registering same user again
                UserPageVisit secondRegisterVisit = new UserPageVisit(getWebDriver(), browser);
                secondRegisterVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.REGISTER.toString()));
                secondRegisterVisit.registerNewUserCheckView(username, password,
                        PATH_PREFIX + ApplicationPageMode.REGISTER.toString());
                secondRegisterVisit.checkNotificationMessage("Register failed:" +
                        ERROR_USER_EXISTS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldManageUserSecurity() throws Exception {
        retryOnFailure(() -> {
            try {
                String username = UUID.randomUUID().toString();
                String password = generatePassword();
                String newPassword = generatePassword();

                // Register and login
                pageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.REGISTER.toString()));
                pageVisit.registerNewUser(username, password);

                // Security settings navigation
                pageVisit.performClickAction(pageVisit.getMenuItem("Useraccount"));
                pageVisit.performClickAction(pageVisit.getMenuItem("Security settings"));

                // Test password change
                pageVisit.changePassword(password, newPassword, newPassword);
                pageVisit.checkNotificationMessage("Password changed successfully");

                // Test 2FA
                pageVisit.enableGoogleAuthenticator(newPassword);
                pageVisit.closeModal();
                pageVisit.disableGoogleAuthenticator(newPassword);

                // Verify login with new password
                pageVisit.logoutUser();
                pageVisit.loginUser(username + EMAIL_SUFFIX, newPassword);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldFailLoginWithWrongPassword() throws Exception {
        retryOnFailure(() -> {
            try {
                String username = UUID.randomUUID().toString();
                String password = generatePassword();

                // Register user
                pageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.REGISTER.toString()));
                pageVisit.registerNewUser(username, password);
                pageVisit.logoutUser();

                // Try wrong password
                pageVisit.loginUserCheckView(username + EMAIL_SUFFIX, "wrongpassword",
                        PATH_PREFIX + ApplicationPageMode.LOGIN);
                pageVisit.checkNotificationMessage("Login failed:" +
                        ERROR_INVALID_CREDENTIALS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }

    @Test(timeout = TestConstants.DEFAULT_TIMEOUT)
    public void shouldEnableAndDisableGoogleAuthenticator() throws Exception {
        retryOnFailure(() -> {
            try {
                String username = UUID.randomUUID().toString();
                String password = generatePassword();

                // Register and login
                pageVisit.visitDirectPage(new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
                        ApplicationPageMode.REGISTER.toString()));
                pageVisit.registerNewUser(username, password);

                // Enable 2FA
                pageVisit.performClickAction(pageVisit.getMenuItem("Useraccount"));
                pageVisit.performClickAction(pageVisit.getMenuItem("Security settings"));
                pageVisit.enableGoogleAuthenticator(password);
                pageVisit.verifyPageContent("Two-factor authentication enabled");

                // Disable 2FA
                pageVisit.disableGoogleAuthenticator(password);
                pageVisit.verifyPageContent("Two-factor authentication disabled");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, MAX_RETRIES);
    }
}

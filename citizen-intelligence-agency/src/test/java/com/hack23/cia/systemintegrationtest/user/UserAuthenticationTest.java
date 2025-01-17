package com.hack23.cia.systemintegrationtest.user;

import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.ui.TestConstants;
import com.hack23.cia.systemintegrationtest.ui.UserPageVisit;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;

@Category(IntegrationTest.class)
public final class UserAuthenticationTest extends AbstractUITest {
	private static final String EMAIL_SUFFIX = "@test.com";
	private static final String PATH_PREFIX = "main/";
	private static final String ERROR_USER_EXISTS = "USER_ALREADY_EXISTS";
	private static final String ERROR_INVALID_CREDENTIALS = "USERNAME_OR_PASSWORD_DO_NOT_MATCH";

	private String generatePassword() {
		return "Test123!" + UUID.randomUUID().toString();
	}

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldRegisterNewUser() throws Exception {

		pageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		pageVisit.registerNewUser(username, password);
		pageVisit.logoutUser();

		final UserPageVisit loginPageVisit = new UserPageVisit(driver);
		loginPageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));
		loginPageVisit.loginUser(username + EMAIL_SUFFIX, password);

	}

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldRegisterUserWithWeakPasswordFail() throws Exception {

		pageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));

		final String username = UUID.randomUUID().toString();
		pageVisit.registerNewUserCheckView(username, "weak", PATH_PREFIX + ApplicationPageMode.REGISTER.toString());
		pageVisit.checkNotificationMessage("Register failed:"
				+ "[Password must be 8 or more characters in length., Password must contain 1 or more uppercase characters., "
				+ "Password must contain 1 or more digit characters., Password must contain 1 or more special characters.]");

	}

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldNotRegisterDuplicateUser() throws Exception {

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		// First registration
		pageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));
		pageVisit.registerNewUser(username, password);
		pageVisit.logoutUser();

		// Try registering same user again
		final UserPageVisit secondRegisterVisit = new UserPageVisit(driver);
		secondRegisterVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));
		secondRegisterVisit.registerNewUserCheckView(username, password,
				PATH_PREFIX + ApplicationPageMode.REGISTER.toString());
		secondRegisterVisit.checkNotificationMessage("Register failed:" + ERROR_USER_EXISTS);

	}

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldManageUserSecurity() throws Exception {

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();
		final String newPassword = generatePassword();

		// Register and login
		pageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));
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

	}

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldFailLoginWithWrongPassword() throws Exception {

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		// Register user
		pageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));
		pageVisit.registerNewUser(username, password);
		pageVisit.logoutUser();

		// Try wrong password
		pageVisit.loginUserCheckView(username + EMAIL_SUFFIX, "wrongpassword", PATH_PREFIX + ApplicationPageMode.LOGIN);
		pageVisit.checkNotificationMessage("Login failed:" + ERROR_INVALID_CREDENTIALS);

	}

	@Test(timeout = TestConstants.DEFAULT_TIMEOUT)
	public void shouldEnableAndDisableGoogleAuthenticator() throws Exception {

		final String username = UUID.randomUUID().toString();
		final String password = generatePassword();

		// Register and login
		pageVisit.visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));
		pageVisit.registerNewUser(username, password);

		// Enable 2FA
		pageVisit.performClickAction(pageVisit.getMenuItem("Useraccount"));
		pageVisit.performClickAction(pageVisit.getMenuItem("Security settings"));
		pageVisit.enableGoogleAuthenticator(password);
		pageVisit.verifyPageContent("Two-factor authentication enabled");

		// Disable 2FA
		pageVisit.disableGoogleAuthenticator(password);
		pageVisit.verifyPageContent("Two-factor authentication disabled");

	}
}

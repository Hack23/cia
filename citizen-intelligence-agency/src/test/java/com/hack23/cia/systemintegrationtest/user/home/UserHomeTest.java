package com.hack23.cia.systemintegrationtest.user.home;

import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.ui.TestUtils;
import com.hack23.cia.systemintegrationtest.ui.UserPageVisit;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MenuItemConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.ChangePasswordClickListener;

/**
 * The Class UserHomeTest.
 */
@Category(IntegrationTest.class)
public final class UserHomeTest extends AbstractUITest {


	/**
	 * Site login user change password failure test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserChangePasswordFailureTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_REGISTER);
		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(userAccountMenuItem);
		pageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = pageVisit.getMenuItem(MenuItemConstants.SECURITY_SETTING_TEXT);
		assertNotNull(securitySettingMenuItem);
		pageVisit.performClickAction(securitySettingMenuItem);

		pageVisit.changePassword("wrong" + password,"new"+ password,"new" + password);

		pageVisit.checkNotificationMessage(ChangePasswordClickListener.PROBLEM_CHANGING_PASSWORD);
		pageVisit.logoutUser();

	}

	/**
	 * Site login user change password test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserChangePasswordTest() throws Exception {

		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);


		final WebElement userAccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(userAccountMenuItem);
		pageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = pageVisit.getMenuItem(MenuItemConstants.SECURITY_SETTING_TEXT);
		assertNotNull(securitySettingMenuItem);
		pageVisit.performClickAction(securitySettingMenuItem);

		pageVisit.changePassword(password,"new"+ password,"new" + password);
		pageVisit.logoutUser();
	}


	/**
	 * Site login user check user events test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserCheckUserEventsTest() throws Exception {

		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);

		pageVisit.logoutUser();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(driver);

		userLoginPageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_LOGIN);

		userLoginPageVisit.loginUser(username + "@test.com", password);


		final WebElement useraccountMenuItem = userLoginPageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(useraccountMenuItem);
		userLoginPageVisit.performClickAction(useraccountMenuItem);

		final WebElement userEventsMenuItem = userLoginPageVisit.getMenuItem("User Events");
		assertNotNull(userEventsMenuItem);
		userLoginPageVisit.performClickAction(userEventsMenuItem);
		userLoginPageVisit.logoutUser();
	}

	/**
	 * Site login user check user visits test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserCheckUserVisitsTest() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);

		pageVisit.logoutUser();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(driver);

		userLoginPageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_LOGIN);

		userLoginPageVisit.loginUser(username + "@test.com", password);


		final WebElement useraccountMenuItem = userLoginPageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(useraccountMenuItem);
		userLoginPageVisit.performClickAction(useraccountMenuItem);

		final WebElement userVisitsMenuItem = userLoginPageVisit.getMenuItem("User Visits");
		assertNotNull(userVisitsMenuItem);
		userLoginPageVisit.performClickAction(userVisitsMenuItem);
		userLoginPageVisit.logoutUser();
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserDisableGoogleAuthenticatorFailureTest() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(userAccountMenuItem);
		pageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = pageVisit.getMenuItem(MenuItemConstants.SECURITY_SETTING_TEXT);
		assertNotNull(securitySettingMenuItem);
		pageVisit.performClickAction(securitySettingMenuItem);

		pageVisit.disableGoogleAuthenticator("wrong" + password);

		pageVisit.checkNotificationMessage("Problem disable google authenticatorError message");
		pageVisit.logoutUser();
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserDisableGoogleAuthenticatorTest() throws Exception {

		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);


		final WebElement userAccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(userAccountMenuItem);
		pageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = pageVisit.getMenuItem(MenuItemConstants.SECURITY_SETTING_TEXT);
		assertNotNull(securitySettingMenuItem);
		pageVisit.performClickAction(securitySettingMenuItem);

		pageVisit.disableGoogleAuthenticator(password);
		pageVisit.logoutUser();

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserEnableGoogleAuthenticatorFailedLoginNoOtpTest() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(userAccountMenuItem);
		pageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = pageVisit.getMenuItem(MenuItemConstants.SECURITY_SETTING_TEXT);
		assertNotNull(securitySettingMenuItem);
		pageVisit.performClickAction(securitySettingMenuItem);


		pageVisit.enableGoogleAuthenticator(password);

		pageVisit.closeModal();

		pageVisit.logoutUser();

		final UserPageVisit failedLoginWrongMfaVisit = new UserPageVisit(driver);

		failedLoginWrongMfaVisit.visitDirectPage(PageCommandUserConstants.COMMAND_LOGIN);

		failedLoginWrongMfaVisit.loginUserCheckView(username + "@test.com", password,"123456","main/" + ApplicationPageMode.LOGIN);

		failedLoginWrongMfaVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginDeleteAccountTest() throws Exception {
		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(userAccountMenuItem);
		pageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = pageVisit.getMenuItem(MenuItemConstants.SECURITY_SETTING_TEXT);
		assertNotNull(securitySettingMenuItem);
		pageVisit.performClickAction(securitySettingMenuItem);


		pageVisit.deleteAccount(password);

		final UserPageVisit failedLoginUserDeletedVisit = new UserPageVisit(driver);

		failedLoginUserDeletedVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_LOGIN);

		failedLoginUserDeletedVisit.loginUserCheckView(username + "@test.com", password,"123456","main/" + ApplicationPageMode.LOGIN);

		failedLoginUserDeletedVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserEnableGoogleAuthenticatorFailureTest() throws Exception {

		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);

		final WebElement userAccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(userAccountMenuItem);
		pageVisit.performClickAction(userAccountMenuItem);


		final WebElement securitySettingMenuItem = pageVisit.getMenuItem(MenuItemConstants.SECURITY_SETTING_TEXT);
		assertNotNull(securitySettingMenuItem);
		pageVisit.performClickAction(securitySettingMenuItem);


		pageVisit.enableGoogleAuthenticator("wrong" + password);

		pageVisit.checkNotificationMessage("Problem enable google authenticatorError message");
	}


	/**
	 * Site login user enable google authenticator test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserEnableGoogleAuthenticatorTest() throws Exception {

		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);


		final WebElement userAccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(userAccountMenuItem);
		pageVisit.performClickAction(userAccountMenuItem);

		final WebElement securitySettingMenuItem = pageVisit.getMenuItem(MenuItemConstants.SECURITY_SETTING_TEXT);
		assertNotNull(securitySettingMenuItem);
		pageVisit.performClickAction(securitySettingMenuItem);

		pageVisit.enableGoogleAuthenticator(password);

		pageVisit.closeModal();
	}


	/**
	 * Site login user test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserTest() throws Exception {

		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);

		pageVisit.logoutUser();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(driver);

		userLoginPageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_LOGIN);

		userLoginPageVisit.loginUser(username + "@test.com", password);
	}


	/**
	 * Site login user wrong password test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void siteLoginUserWrongPasswordTest() throws Exception {

		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_REGISTER);

		final String username = UUID.randomUUID().toString();
		final String password = TestUtils.generatePassword();

		pageVisit.registerNewUser(username, password);

		pageVisit.logoutUser();

		final UserPageVisit userLoginPageVisit = new UserPageVisit(driver);

		userLoginPageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_LOGIN);

		userLoginPageVisit.loginUserCheckView(username + "@test.com", "wrongpassword","main/" + ApplicationPageMode.LOGIN);

		userLoginPageVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);

	}


}

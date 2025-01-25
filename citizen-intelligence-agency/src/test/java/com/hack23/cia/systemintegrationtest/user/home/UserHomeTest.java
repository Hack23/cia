package com.hack23.cia.systemintegrationtest.user.home;

import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.systemintegrationtest.ui.TestUtils;
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


		final WebElement useraccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(useraccountMenuItem);
		pageVisit.performClickAction(useraccountMenuItem);

		final WebElement userEventsMenuItem = pageVisit.getMenuItem("User Events");
		assertNotNull(userEventsMenuItem);
		pageVisit.performClickAction(userEventsMenuItem);
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

		final WebElement useraccountMenuItem = pageVisit.getMenuItem(MenuItemConstants.USERACCOUNT);
		assertNotNull(useraccountMenuItem);
		pageVisit.performClickAction(useraccountMenuItem);

		final WebElement userVisitsMenuItem = pageVisit.getMenuItem("User Visits");
		assertNotNull(userVisitsMenuItem);
		pageVisit.performClickAction(userVisitsMenuItem);
	}

	/**
	 * Site login user disable google authenticator failure test.
	 *
	 * @throws Exception the exception
	 */
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
	}

	/**
	 * Site login user disable google authenticator test.
	 *
	 * @throws Exception the exception
	 */
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
	}

	/**
	 * Site login user enable google authenticator failed login no otp test.
	 *
	 * @throws Exception the exception
	 */
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

		pageVisit.cleanBrowser();

		pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_LOGIN);

		pageVisit.loginUserCheckView(username + "@test.com", password,"123456","main/" + ApplicationPageMode.LOGIN);

		pageVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);
	}

	/**
	 * Site login delete account test.
	 *
	 * @throws Exception the exception
	 */
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

		pageVisit.cleanBrowser();

		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_LOGIN);

		pageVisit.loginUserCheckView(username + "@test.com", password,"123456","main/" + ApplicationPageMode.LOGIN);

		pageVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);
	}

	/**
	 * Site login user enable google authenticator failure test.
	 *
	 * @throws Exception the exception
	 */
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


		pageVisit.visitDirectPage(
				PageCommandUserConstants.COMMAND_LOGIN);

		pageVisit.loginUserCheckView(username + "@test.com", "wrongpassword","main/" + ApplicationPageMode.LOGIN);

		pageVisit.checkNotificationMessage("Login failed:" + LoginResponse.ErrorMessage.USERNAME_OR_PASSWORD_DO_NOT_MATCH);

	}

	/**
	 * Verify agency page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyAgencyPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_AGENCY);
		pageVisit.validatePage(PageCommandUserConstants.COMMAND_AGENCY);
	}

	/**
	 * Verify application configuration page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyApplicationConfigurationPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_APPLICATION_CONFIGURATION);
		pageVisit.validatePage(PageCommandUserConstants.COMMAND_APPLICATION_CONFIGURATION);
	}

	/**
	 * Verify ballot overview page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyBallotOverviewPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_BALLOT_OVERVIEW);
		pageVisit.validatePage(PageCommandUserConstants.COMMAND_BALLOT_OVERVIEW);
	}

	/**
	 * Verify main view overview page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyMainViewOverviewPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_MAINVIEW_OVERVIEW);
		pageVisit.validatePage(PageCommandUserConstants.COMMAND_MAINVIEW_OVERVIEW);
	}

	/**
	 * Verify monitoring page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyMonitoringPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_MONITORING);
		pageVisit.validatePage(PageCommandUserConstants.COMMAND_MONITORING);
	}
}

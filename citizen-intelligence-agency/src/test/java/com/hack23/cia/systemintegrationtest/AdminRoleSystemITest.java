/*
 * Copyright 2010-2024 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.systemintegrationtest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DataSummaryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;

/**
 * The Class AdminRoleSystemITest.
 */
public final class AdminRoleSystemITest extends AbstractRoleSystemITest {

	/** The Constant NO_WEBDRIVER_EXIST_FOR_BROWSER. */
	private static final String NO_WEBDRIVER_EXIST_FOR_BROWSER = "No webdriver exist for browser:";

	/**
	 * Instantiates a new admin role system test.
	 *
	 * @param browser
	 *            the browser
	 */
	public AdminRoleSystemITest(final String browser) {
		super(browser);
	}

	/**
	 * Browsers strings.
	 *
	 * @return the collection
	 */
	@Parameters(name = "AdminRoleSiteTest{index}: browser({0})")
	public static Collection<String[]> browsersStrings() {
		return Arrays.asList(new String[][] { { "chrome" } });
	}


	/**
	 * Site admin agency test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminAgencyTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Agency"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));

	}

	/**
	 * Site admin test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminAgentOperationTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, ""));

		userPageVisit.verifyViewActions(new ViewAction[] {ViewAction.VISIT_MAIN_VIEW});
		final WebElement importWorldBankDataButton =userPageVisit.findButton("Start IMPORT MODEL_EXTERNAL_WORLDBANK");
		assertNotNull("Start IMPORT MODEL_EXTERNAL_WORLDBANK Button",importWorldBankDataButton);

		final WebElement importRiksdagenDataButton =userPageVisit.findButton("Start IMPORT MODEL_EXTERNAL_RIKSDAGEN");
		assertNotNull("Start IMPORT MODEL_EXTERNAL_RIKSDAGEN Button",importRiksdagenDataButton);

		userPageVisit.performClickAction(importRiksdagenDataButton);
	}

	/**
	 * Site admin application configuration test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminApplicationConfigurationTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit
				.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Configuration"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit
				.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));

		userPageVisit.updateConfigurationProperty("Update Configuration.propertyValue", String.valueOf(false));

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));

	}

	/**
	 * Site admin application event charts test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminApplicationEventChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Action Event chart"));

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS));

	}

	/**
	 * Site admin application event test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminApplicationEventTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Action Event"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));

	}


	/**
	 * Site admin application session charts test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminApplicationSessionChartsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, PageMode.CHARTS));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Admin Daily Active Users Chart"));

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, PageMode.CHARTS));

	}

	/**
	 * Site admin application session last page test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminApplicationSessionLastPageTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Session"));

		final WebElement nextPageButton = userPageVisit.findButton("last page");
		userPageVisit.performClickAction(nextPageButton);
	}


	/**
	 * Site admin application session next page test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminApplicationSessionNextPageTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Session"));

		final WebElement nextPageButton = userPageVisit.findButton("next page");
		userPageVisit.performClickAction(nextPageButton);

	}

	/**
	 * Site admin application session test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminApplicationSessionTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Application Session"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));

		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("ApplicationActionEvent"));

	}

	/**
	 * Site admin country test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminCountryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Country"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));

	}

	/**
	 * Site admin email failed no valid email test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminEmailFailedNoValidEmailTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("email"));

		userPageVisit.sendEmailOnEmailPage("nonvalidemail", "siteAdminEmailFailedNoValidEmailTest", "siteAdminEmailFailedNoValidEmailTest content");

	}

	/**
	 * Site admin email test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminEmailTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("email"));

		userPageVisit.sendEmailOnEmailPage("james@hack23.com", "siteAdminEmailTest", "siteAdminEmailTest content");

		userPageVisit.checkNotificationMessage("Email Sentdesc");
	}


	/**
	 * Site admin language test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminLanguageTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Language"));

		clickFirstRowInGrid(userPageVisit);
		userPageVisit.validatePage(new
		PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));

	}

	/**
	 * Site admin monitoring failed access test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminMonitoringFailedAccessTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));
		assertTrue("Expect this content", userPageVisit.checkHtmlBodyContainsText("Access denied:adminmonitoring"));
	}


	/**
	 * Site admin monitoring success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminMonitoringSuccessTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));
		assertTrue("Expect this content", userPageVisit.checkHtmlBodyContainsText("Admin Monitoring"));

		assertFalse("Dont expect this content",
				userPageVisit.getIframesHtmlBodyAsText().contains("Login with Username and Password"));
	}


	/**
	 * Site admin portal test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminPortalTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Portal"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));

	}

	/**
	 * Site admin useraccount delete test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminUseraccountDeleteTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Useraccount"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));

		final WebElement deleteButton =userPageVisit.findButton("Perform DELETE");
		assertNotNull("Expect to find a Delete Button",deleteButton);

		userPageVisit.performClickAction(deleteButton);
		userPageVisit.checkNotificationMessage("Operation completeddesc");
	}


	/**
	 * Site admin useraccount test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 60000)
	public void siteAdminUseraccountTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);
		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
		assertTrue("Expect content",userPageVisit.checkHtmlBodyContainsText("Useraccount"));

		clickFirstRowInGrid(userPageVisit);

		userPageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));

	}

	/**
	 * Visit admin author summary view test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 55000)
	public void visitAdminAuthorSummaryViewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, DataSummaryPageMode.AUTHORS.toString()));
	}

	/**
	 * Visit admin data summary view refresh views test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 75000)
	public void visitAdminDataSummaryViewRefreshViewsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement refreshViewsButton =userPageVisit.findButton("Refresh Views");
		assertNotNull("Expect to find a Refresh Views Button",refreshViewsButton);

		userPageVisit.performClickAction(refreshViewsButton);
	}


	/**
	 * Visit admin data summary view remove application history test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 75000)
	public void visitAdminDataSummaryViewRemoveApplicationHistoryTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement removeApplicationHistoryButton =userPageVisit.findButton("Remove Application History");
		assertNotNull("Expect to find a Button",removeApplicationHistoryButton);
	}

	/**
	 * Visit admin data summary view remove documents test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 75000)
	public void visitAdminDataSummaryViewRemoveDocumentsTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement removeDocumentsButton =userPageVisit.findButton("Remove Documents");
		assertNotNull("Expect to find a Button",removeDocumentsButton);
	}


	/**
	 * Visit admin data summary view remove politician test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 75000)
	public void visitAdminDataSummaryViewRemovePoliticianTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement removePoliticiansButton =userPageVisit.findButton("Remove Politicians");
		assertNotNull("Expect to find a Button",removePoliticiansButton);
	}





	/**
	 * Visit admin data summary view.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 90000)
	public void visitAdminDataSummaryViewTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

	}

	/**
	 * Visit admin data summary view update search index test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(timeout = 75000)
	public void visitAdminDataSummaryViewUpdateSearchIndexTest() throws Exception {
		final WebDriver driver = getWebDriver();
		assertNotNull(NO_WEBDRIVER_EXIST_FOR_BROWSER + browser, driver);

		final UserPageVisit userPageVisit = new UserPageVisit(driver, browser);

		loginAsAdmin(userPageVisit);

		userPageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

		final WebElement updateSearchIndexButton =userPageVisit.findButton("Update Search Index");
		assertNotNull("Expect to find a Update Search Index Button",updateSearchIndexButton);

		userPageVisit.performClickAction(updateSearchIndexButton);

	}



}

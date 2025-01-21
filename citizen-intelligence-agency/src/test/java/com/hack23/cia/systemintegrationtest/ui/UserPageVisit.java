/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hack23.cia.systemintegrationtest.CitizenIntelligenceAgencyServer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class UserPageVisit.
 */
public final class UserPageVisit extends Assert {

	/** The system test target admin email. */
	protected static String systemTestTargetAdminEmail;

	/** The system test target admin password. */
	protected static String systemTestTargetAdminPassword;

	/** The Constant systemTestTargetUrl. */
	private static final String systemTestTargetUrl;

	static {
		final String systemTestTargetUrlProperty = System.getProperty("system.test.target.url");
		if (systemTestTargetUrlProperty != null && !systemTestTargetUrlProperty.trim().isEmpty()) {
			systemTestTargetUrl = systemTestTargetUrlProperty;
		} else {
			systemTestTargetUrl = CitizenIntelligenceAgencyServer.ACCESS_URL;
		}

		systemTestTargetAdminEmail = System.getProperty("system.test.target.admin.email");
		if (systemTestTargetAdminEmail == null) {
			systemTestTargetAdminEmail = "admin@hack23.com";
		}

		systemTestTargetAdminPassword = System.getProperty("system.test.target.admin.password");
		if (systemTestTargetAdminPassword == null) {
			systemTestTargetAdminPassword = "Admin4hack23!";
		}
	}

	/** The driver. */
	final WebDriver driver;

	/** The helper. */
	private final UserPageVisitHelper helper;

	/** The element helper. */
	private final ElementHelper elementHelper;

	/** The click helper. */
	private final ClickHelper clickHelper;

	/** The action. */
	private final Actions action;

	/**
	 * Instantiates a new user page visit.
	 *
	 * @param driver                    the driver
	 */
	public UserPageVisit(final WebDriver driver) {
		super();
		this.driver = driver;
		this.helper = new UserPageVisitHelper(driver);
		this.elementHelper = new ElementHelper(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT);
		this.clickHelper = new ClickHelper(driver, helper);
		action = new Actions(driver);
	}

	/**
	 * Change password.
	 *
	 * @param password the password
	 * @param newPassword the new password
	 * @param repeatNewPassword the repeat new password
	 */
	public void changePassword(final String password, final String newPassword, final String repeatNewPassword) {
		setFieldValue("Change password.currentPassword", password);
		setFieldValue("Change password.newPassword", newPassword);
		setFieldValue("Change password.repeatNewPassword", repeatNewPassword);

		clickHelper.clickWithRetry(findButton("Change password"));
	}

	/**
	 * Check html body contains text.
	 *
	 * @param text
	 *             the text
	 * @return true, if successful
	 */
	public boolean checkHtmlBodyContainsText(final String text) {
		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));
		wait.until(helper.containsViewAction(ViewAction.VISIT_MAIN_VIEW));
		return getHtmlBodyAsText().contains(text);
	}

	/**
	 * Check notification message.
	 *
	 * @param expectedValue
	 *                      the expected value
	 */
	public void checkNotificationMessage(final String expectedValue) {
		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-Notification")));
		final WebElement notification = driver.findElement(By.className("v-Notification"));
		assertNotNull(notification);
		assertEquals(expectedValue, notification.getText());

	}

	/**
	 * Close modal.
	 */
	public void closeModal() {
		final WebElement closeModalWindow = driver.findElement(By.className("v-window-closebox"));
		assertNotNull("Expect to find a Window close Box", closeModalWindow);

		performClickActionWithRetry(closeModalWindow);

	}

	/**
	 * Disable google authenticator.
	 *
	 * @param password the password
	 */
	public void disableGoogleAuthenticator(final String password) {
		setFieldValue("Disable Google Authenticator.userpassword", password);

		final WebElement enableGoogleAuthButton = driver.findElement(By.id("Disable Google Authenticator"));
		assertNotNull("Expect to find a Enable Google Authenticator Button", enableGoogleAuthButton);

		performClickActionWithRetry(enableGoogleAuthButton);
	}

	/**
	 * Delete account.
	 *
	 * @param password the password
	 */
	public void deleteAccount(final String password) {
		setFieldValue("Delete Account.userpassword", password);

		final WebElement deleteButton = driver.findElement(By.id("Delete Account"));
		assertNotNull("Expect to find a Delete Account Button", deleteButton);

		performClickActionWithRetry(deleteButton);
	}

	/**
	 * Enable google authenticator.
	 *
	 * @param password the password
	 */
	public void enableGoogleAuthenticator(final String password) {

		setFieldValue("Enable Google Authenticator.userpassword", password);

		final WebElement enableGoogleAuthButton = driver.findElement(By.id("Enable Google Authenticator"));
		assertNotNull("Expect to find a Enable Google Authenticator Button", enableGoogleAuthButton);

		performClickActionWithRetry(enableGoogleAuthButton);
	}

	/**
	 * Find button.
	 *
	 * @param buttonLabel
	 *                    the button label
	 * @return the web element
	 */
	public WebElement findButton(final String buttonLabel) {
		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));
		wait.until(helper.containsButton(buttonLabel));

		for (final WebElement webElement : helper.getButtons()) {
			final WebElement refreshElement = StaleElementUtils.refreshElement(webElement, driver);
			if (ExpectedConditions.not(ExpectedConditions.stalenessOf(refreshElement)).apply(driver)
					&& (buttonLabel.equalsIgnoreCase(refreshElement.getText().trim())
							|| refreshElement.getText().trim().endsWith(buttonLabel))) {
				return refreshElement;
			}
		}
		return null;
	}

	/**
	 * Find clickable.
	 *
	 * @param id the id
	 * @return the web element
	 */
	private WebElement findClickable(final String id) {
		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));
		wait.pollingEvery(Duration.ofMillis(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));

		return driver.findElement(By.id(id));
	}

	/**
	 * Gets the action ids by.
	 *
	 * @param action
	 *               the action
	 * @return the action ids by
	 */
	public List<String> getActionIdsBy(final ViewAction action) {
		final List<String> idList = new ArrayList<>();
		final String xPath = "//*[contains(@id,'" + action.name() + "')]";

		for (final WebElement webElement : driver.findElements(By.xpath(xPath))) {
			idList.add(webElement.getDomAttribute("id"));

		}

		return idList;
	}


	/**
	 * Gets the grid headers.
	 *
	 * @return the grid headers
	 */
	public List<WebElement> getGridHeaders() {
		final WebElement gridBody = driver.findElement(By.className("v-grid-header"));

		return gridBody.findElements(By.className("v-grid-row"));

	}

	/**
	 * Gets the grid rows.
	 *
	 * @return the grid rows
	 */
	public List<WebElement> getGridRows() {
		final WebElement gridBody = driver.findElement(By.className("v-grid-body"));
		return gridBody.findElements(By.className("v-grid-row"));

	}


/**
	 * Click first row in grid.
	 *
	 * @param userPageVisit
	 *                      the user page visit
	 * @throws InterruptedException
	 *                              the interrupted exception
	 */
	protected final void clickFirstRowInGrid(final UserPageVisit userPageVisit) throws InterruptedException {
		final List<WebElement> gridRows = userPageVisit.getGridRows();
		assertFalse(gridRows.isEmpty());

		final WebElement choosenRow = gridRows.iterator().next();

		final List<WebElement> cells = choosenRow.findElements(By.className("v-grid-cell"));

		final WebElement choosenCell = cells.iterator().next();

		userPageVisit.performClickAction(choosenCell);

	}

	/**
	 * Login as admin.
	 *
	 * @throws Exception                   the exception
	 */
	public final void loginAsAdmin() throws Exception {
		visitDirectPage(
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));
		loginUser(systemTestTargetAdminEmail, systemTestTargetAdminPassword);
	}


	/**
	 * Gets the html body as text.
	 *
	 * @return the html body as text
	 */
	String getHtmlBodyAsText() {
		return driver.findElement(By.tagName("body")).getText();
	}

	/**
	 * Gets the iframes html body as text.
	 *
	 * @return the iframes html body as text
	 */
	public String getIframesHtmlBodyAsText() {
		final StringBuilder stringBuilder = new StringBuilder();
		final List<WebElement> ele = driver.findElements(By.tagName("iframe"));
		for (final WebElement el : ele) {
			driver.switchTo().frame(el);

			stringBuilder.append(driver.findElement(By.tagName("body")).getText());
			driver.switchTo().defaultContent();
		}
		return stringBuilder.toString();
	}

	/**
	 * Gets the menu bar.
	 *
	 * @return the menu bar
	 */
	public WebElement getMenuBar() {
		return driver.findElement(By.className("v-menubar"));
	}

	/**
	 * Gets the menu item.
	 *
	 * @param caption
	 *                the caption
	 * @return the menu item
	 */

	public WebElement getMenuItem(final String... caption) {
		return getMenuItem(getMenuBar(), 1, caption);
	}

	/**
	 * Gets the menu item.
	 *
	 * @param element
	 *                the element
	 * @param level
	 *                the level
	 * @param caption
	 *                the caption
	 * @return the menu item
	 */
	WebElement getMenuItem(final WebElement element, final int level, final String... caption) {
		final List<WebElement> findElements = element.findElements(By.className("v-menubar-menuitem-caption"));
		if (caption.length == level) {
			for (final WebElement webElement : findElements) {
				if (!ExpectedConditions.stalenessOf(element).apply(driver) && StaleElementUtils
						.refreshElement(webElement, driver).getText().contains(caption[level - 1])) {
					return StaleElementUtils.refreshElement(webElement, driver);
				}
			}
		} else {
			for (final WebElement webElement : findElements) {
				if (!ExpectedConditions.stalenessOf(element).apply(driver)
						&& caption[level - 1].equals(StaleElementUtils.refreshElement(webElement, driver).getText())) {
					return getMenuItem(StaleElementUtils.refreshElement(webElement, driver), level + 1, caption);
				}
			}
		}

		final List<WebElement> findElements2 = driver.findElements(By.className("v-menubar-menuitem"));
		if (caption.length == level) {
			for (final WebElement webElement : findElements2) {
				if (!ExpectedConditions.stalenessOf(element).apply(driver) && StaleElementUtils
						.refreshElement(webElement, driver).getText().contains(caption[level - 1])) {
					return StaleElementUtils.refreshElement(webElement, driver);
				}
			}
		}

		return null;
	}

	/**
	 * Gets the menu item.
	 *
	 * @param element
	 *                the element
	 * @param caption
	 *                the caption
	 * @return the menu item
	 */
	public WebElement getMenuItem(final WebElement element, final String... caption) {

		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));
		wait.until(helper.containsMenuItem(this, element, caption));

		return getMenuItem(element, 1, caption);
	}

	/**
	 * Login user.
	 *
	 * @param username
	 *                 the username
	 * @param password
	 *                 the password
	 */
	public void loginUser(final String username, final String password) {
		loginUserCheckView(username, password, UserViews.USERHOME_VIEW_NAME);
	}

	/**
	 * Login user check view.
	 *
	 * @param username the username
	 * @param password the password
	 * @param view the view
	 */
	public void loginUserCheckView(final String username, final String password, final String view) {
		loginUserCheckView(username, password, null, view);
	}

	/**
	 * Login user check view.
	 *
	 * @param username                 the username
	 * @param password                 the password
	 * @param otpCode the otp code
	 * @param view                 the view
	 */
	public void loginUserCheckView(final String username, final String password, final String otpCode,
			final String view) {

		setFieldValue("Login.email", username);
		setFieldValue("Login.userpassword", password);

		if (otpCode != null) {
			setFieldValue("Login.otpCode", otpCode);
		}

		final WebElement loginButton = findButton("Login");
		assertNotNull("Expect to find a Login Button", loginButton);

		performClickActionWithRetry(loginButton);

		final String url = systemTestTargetUrl + "#!" + view;

		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT,(Duration.ofMillis(100)));
		wait.until(ExpectedConditions.urlContains(url));

		assertEquals(url, driver.getCurrentUrl());
	}

	/**
	 * Logout user.
	 */
	public void logoutUser() {

		final WebElement logoutButton = findButton("Logout");
		assertNotNull("Expect to find a Logout Button", logoutButton);

		final Cookie cookie = driver.manage().getCookieNamed("JSESSIONID");
		final String sessionId = cookie.getValue();

		performClickAction(logoutButton);

		final WebElement body = driver.findElement(By.tagName("body"));
		body.sendKeys(Keys.ESCAPE);

		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));
		wait.until(helper.containsViewAction(ViewAction.VISIT_MAIN_VIEW));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("body")));
		driver.navigate().refresh();
		action.pause(500L).perform();

		wait.until(webDriver -> "complete"
				.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState")));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("body")));

		final Cookie newCookie = driver.manage().getCookieNamed("JSESSIONID");

		final String newSessionId = newCookie.getValue();

		assertNotEquals(sessionId, newSessionId);

		final String url = systemTestTargetUrl + "#!" + CommonsViews.MAIN_VIEW_NAME;

		assertEquals(url, driver.getCurrentUrl());
		cleanBrowser();
	}




	/**
	 * Clean browser.
	 */
	private void cleanBrowser() {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.get("about:blank");
		}
	}


	/**
	 * Perform click action.
	 *
	 * @param clickElement
	 *                     the click element
	 */
	public void performClickAction(final WebElement clickElement) {
		performClickActionWithRetry(clickElement);
	}

	/**
	 * Register new user.
	 *
	 * @param username
	 *                 the username
	 * @param password
	 *                 the password
	 */
	public void registerNewUser(final String username, final String password) {
		registerNewUserCheckView(username, password, UserViews.USERHOME_VIEW_NAME);
	}

	/**
	 * Register new user check view.
	 *
	 * @param username
	 *                 the username
	 * @param password
	 *                 the password
	 * @param userView
	 *                 the user view
	 */
	public void registerNewUserCheckView(final String username, final String password, final String userView) {

		setFieldValue("Register.username", username);
		setFieldValue("Register.email", username + "@test.com");
		setFieldValue("Register.country", "Sweden");
		setFieldValue("Register.userpassword", password);

		performClickActionWithRetry(findClickable("Register"));

		findButton("Logout");

		if (userView != null) {
			final String url = systemTestTargetUrl + "#!" + userView;
			assertEquals(url, driver.getCurrentUrl());
		}
	}

	/**
	 * Search document.
	 *
	 * @param search
	 *               the search
	 */
	public void searchDocument(final String search) {

		setFieldValue("Search.searchExpression", search);

		performClickActionWithRetry(findClickable("Search"));

		final String url = systemTestTargetUrl + "#!" + UserViews.SEARCH_DOCUMENT_VIEW_NAME;

		assertEquals(url,
				driver.getCurrentUrl());

	}

	/**
	 * Send email on email page.
	 *
	 * @param email the email
	 * @param subject the subject
	 * @param content the content
	 */
	public void sendEmailOnEmailPage(final String email, final String subject, final String content) {

		setFieldValue("Email.email", email);
		setFieldValue("Email.subject", subject);
		setFieldValue("Email.content", content);

		performClickActionWithRetry(findClickable("Email"));
	}

	/**
	 * Sets the field value.
	 *
	 * @param id
	 *              the id
	 * @param value
	 *              the value
	 */
	private void setFieldValue(final String id, final String value) {
		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));

		final WebElement findElement = driver.findElement(By.id(id));
		findElement.clear();
		findElement.sendKeys(value);
	}

	/**
	 * Update configuration property.
	 *
	 * @param property
	 *                 the property
	 * @param value
	 *                 the value
	 */
	public void updateConfigurationProperty(final String property, final String value) {
		setFieldValue(property, value);
		final WebElement updateConfigButton = findButton("Update Configuration");
		assertNotNull("Expect to find a Update Config Button", updateConfigButton);

		performClickActionWithRetry(updateConfigButton);

	}

	/**
	 * Validate page.
	 *
	 * @param page
	 *             the page
	 */
	public void validatePage(final PageModeMenuCommand page) {
		final String url = systemTestTargetUrl + "#!" + page.getPagePath();

		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));
		wait.until(helper.containsViewAction(ViewAction.VISIT_MAIN_VIEW));

		final String text = getHtmlBodyAsText();
		assertNotNull(text);
		assertFalse("Page contains exception, url:" + url, text.contains("Exception"));
		assertFalse("Page contains widget exception, url:" + url, text.contains("Widget"));

		wait.until(ExpectedConditions.urlContains(url));
		assertNotNull(driver.getWindowHandle());

	}

	/**
	 * Verify view actions.
	 *
	 * @param viewActions
	 *                    the view actions
	 */
	public void verifyViewActions(final ViewAction[] viewActions) {
		final Set<ViewAction> actionsAvailable = helper.getActionsAvailable();
		for (final ViewAction viewAction : viewActions) {
			assertTrue(actionsAvailable.contains(viewAction));

		}
	}

	/**
	 * Visit direct page.
	 *
	 * @param page
	 *             the page
	 */
	public void visitDirectPage(final PageModeMenuCommand page) {
		final String url = systemTestTargetUrl + "#!" + page.getPagePath();
		driver.get(url);

		action.pause(500L).perform();
		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));

		wait.until(helper.containsViewAction(ViewAction.VISIT_MAIN_VIEW));

		final String text = getHtmlBodyAsText();
		assertNotNull(text);
		assertFalse("Page contains exception, url:" + url, text.contains("Exception"));
		assertFalse("Page contains widget exception, url:" + url, text.contains("Widget"));

		wait.until(ExpectedConditions.urlContains(url));
		assertEquals(url, driver.getCurrentUrl());
		assertNotNull(driver.getWindowHandle());
	}

	/**
	 * Visit main view.
	 */
	public void visitMainView() {
		final WebElement mainViewLink = driver.findElement(By
				.id(ViewAction.VISIT_MAIN_VIEW.name()));
		performClickActionWithRetry(mainViewLink);

		verifyViewActions(new ViewAction[] {
				ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW,
				ViewAction.VISIT_ADMIN_DATA_SUMMARY_VIEW,
				ViewAction.VISIT_POLITICIAN_RANKING_VIEW,
				ViewAction.VISIT_PARTY_RANKING_VIEW,
				ViewAction.VISIT_MINISTRY_RANKING_VIEW,
				ViewAction.VISIT_COMMITTEE_RANKING_VIEW,
				ViewAction.VISIT_COUNTRY_VIEW });
	}


	/**
	 * Visit start page.
	 */
	public void visitStartPage() {
		driver.get(systemTestTargetUrl);

		final FluentWait<WebDriver> wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT).pollingEvery(Duration.ofMillis(10));
		wait.until(helper.containsViewAction(ViewAction.VISIT_MAIN_VIEW));

		assertEquals(systemTestTargetUrl,
				driver.getCurrentUrl());
		assertEquals("Citizen Intelligence Agency", driver.getTitle());
		assertNotNull(driver.getWindowHandle());

		verifyViewActions(new ViewAction[] {
				ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW,
				ViewAction.VISIT_ADMIN_DATA_SUMMARY_VIEW,
				ViewAction.VISIT_POLITICIAN_RANKING_VIEW,
				ViewAction.VISIT_PARTY_RANKING_VIEW,
				ViewAction.VISIT_MINISTRY_RANKING_VIEW,
				ViewAction.VISIT_COMMITTEE_RANKING_VIEW,
				ViewAction.VISIT_COUNTRY_VIEW });
	}

	/**
	 * Verify page content.
	 *
	 * @param expectedContent the expected content
	 * @return the user page visit
	 */
	public UserPageVisit verifyPageContent(String expectedContent) {
		try {
			assertTrue("Expected content: " + expectedContent +"\nNot in:\n " + getHtmlBodyAsText(),
					checkHtmlBodyContainsText(expectedContent));
			return this;
		} catch (final Exception e) {
			throw new RuntimeException("Page content verification failed", e);
		}
	}

	/**
	 * Select first grid row.
	 *
	 * @return the user page visit
	 */
	public UserPageVisit selectFirstGridRow() {
		final WebElement firstRow = getGridRows().get(0);
		performClickActionWithRetry(firstRow);
		return this;
	}


	/**
	 * Perform click action with retry.
	 *
	 * @param element the element
	 */
	// The public click methods needed by all the calls
	public void performClickActionWithRetry(WebElement clickElement) {
		assertNotNull(clickElement);
		clickHelper.clickWithRetry(clickElement);
	}
}

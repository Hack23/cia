package com.hack23.cia.systemintegrationtest.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;

/**
 * The Class UserPageVisitHelper.
 */
public class UserPageVisitHelper {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(UserPageVisitHelper.class);

    /** The Constant DEFAULT_WAIT. */
    private static final Duration DEFAULT_WAIT = Duration.ofMillis(100);

    /** The screen shot number. */
    private static int screenShotNumber;

    /** The driver. */
    private final WebDriver driver;

    /**
     * Instantiates a new user page visit helper.
     *
     * @param driver the driver
     */
    UserPageVisitHelper(final WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Wait for page load.
     */
    public void waitForPageLoad() {
        new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT,DEFAULT_WAIT).until(containsViewAction(ViewAction.VISIT_MAIN_VIEW));


    }

    /**
     * Grab screenshot.
     */
    public void grabScreenshot() {
        try {
            final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            screenShotNumber++;
            final Path targetPath = Path.of("target", "screenshot_" + screenShotNumber + ".png");
            Files.copy(scrFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (final IOException e) {
            LOG.error("Failed to save screenshot", e);
        }
    }

    /**
     * Refresh element.
     *
     * @param element the element
     * @return the web element
     */
    public WebElement refreshElement(final WebElement element) {
        return StaleElementUtils.refreshElement(element, driver);
    }

    /**
     * Handle interrupted exception.
     *
     * @param e the e
     */
    public void handleInterruptedException(final InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Operation interrupted", e);
    }

    /**
     * Element is stale.
     *
     * @param element the element
     * @return the expected condition
     */
    public ExpectedCondition<Boolean> elementIsStale(final WebElement element) {
        return ExpectedConditions.stalenessOf(element);
    }

	/**
	 * Contains button.
	 *
	 * @param value the value
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> containsButton(final String value) {
		return new ExpectedCondition<>() {

			@Override
			public Boolean apply(final WebDriver driver) {
				for (final WebElement webElement : getButtons()) {
					final WebElement refreshElement = StaleElementUtils.refreshElement(webElement, driver);

					if (ExpectedConditions.not(ExpectedConditions.stalenessOf(refreshElement)).apply(driver)
							&& (value.equalsIgnoreCase(refreshElement.getText().trim())
									|| refreshElement.getText().trim().endsWith(value))) {
						return true;
					}
				}

				return false;
			}

			@Override
			public String toString() {
				return String.format("Button \"%s\". ", value);
			}
		};
	}

	/**
	 * Contains menu item.
	 *
	 * @param userPageVisit the user page visit
	 * @param element the element
	 * @param caption the caption
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> containsMenuItem(final UserPageVisit userPageVisit, final WebElement element, final String... caption) {
		return new ExpectedCondition<>() {
			@Override
			public Boolean apply(final WebDriver driver) {
				return !ExpectedConditions.stalenessOf(element).apply(driver)
						&& userPageVisit.getMenuItem(element, 1, caption) != null;
			}
		};
	}

	/**
	 * Contains text.
	 *
	 * @param userPageVisit the user page visit
	 * @param value the value
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> containsText(final UserPageVisit userPageVisit, final String value) {
		return new ExpectedCondition<>() {

			@Override
			public Boolean apply(final WebDriver driver) {
				return userPageVisit.getHtmlBodyAsText().contains(value);
			}

			@Override
			public String toString() {
				return String.format("containsText \"%s\". ", value);
			}
		};
	}

	/**
	 * Contains view action.
	 *
	 * @param value the value
	 * @return the expected condition
	 */
	public ExpectedCondition<Boolean> containsViewAction(final ViewAction value) {
		return new ExpectedCondition<>() {

			@Override
			public Boolean apply(final WebDriver driver) {
				return getActionsAvailable().contains(value);
			}

			@Override
			public String toString() {
				return String.format("ViewAction \"%s\". ", value);
			}
		};
	}

	/**
	 * Gets the button elements.
	 *
	 * @return the button elements
	 */
	List<WebElement> getButtonElements() {
		final List<WebElement> result = new ArrayList<>(driver.findElements(By.className("v-nativebutton")));
		result.addAll(driver.findElements(By.className("v-button")));
		result.addAll(driver.findElements(By.className("v-button-caption")));
		result.addAll(driver.findElements(By.tagName("a")));

		return result;
	}


	/**
	 * Gets the buttons.
	 *
	 * @return the buttons
	 */
	public List<WebElement> getButtons() {
		final List<WebElement> result = getButtonElements();
		final WebDriverWait wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT,DEFAULT_WAIT);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(result)));

		return getButtonElements();
	}

	/**
	 * Gets the actions available.
	 *
	 * @return the actions available
	 */
	public Set<ViewAction> getActionsAvailable() {
		final Set<ViewAction> actions = new HashSet<>();

		for (final ViewAction actionValue : ViewAction.values()) {
			if (driver.findElements(By.id(actionValue.name())).size() > 0) {
				actions.add(actionValue);
			}
		}
		return actions;
	}

	/**
	 * Wait for clickable.
	 *
	 * @param element the element
	 */
	public void waitForClickable(final WebElement element) {
		final WebDriverWait wait = new WebDriverWait(driver, TestConstants.WAIT_FOR_PAGE_ELEMENT,DEFAULT_WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(StaleElementUtils.refreshElement(element,driver)));


	}

}

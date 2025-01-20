package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The Class ElementHelper.
 */
public class ElementHelper {

    /** The driver. */
    private final WebDriver driver;

    /** The wait timeout. */
    private final Duration waitTimeout;

    /**
     * Instantiates a new element helper.
     *
     * @param driver the driver
     * @param waitTimeout the wait timeout
     */
    public ElementHelper(WebDriver driver, Duration waitTimeout) {
        this.driver = driver;
        this.waitTimeout = waitTimeout;
    }

    /**
     * Wait for clickable.
     *
     * @param locator the locator
     * @return the web element
     */
    public WebElement waitForClickable(By locator) {
        return new WebDriverWait(driver, waitTimeout)
            .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Find buttons.
     *
     * @return the list
     */
    public List<WebElement> findButtons() {
        final List<WebElement> buttons = new ArrayList<>();
        buttons.addAll(driver.findElements(By.className("v-nativebutton")));
        buttons.addAll(driver.findElements(By.className("v-button")));
        buttons.addAll(driver.findElements(By.className("v-button-caption")));
        buttons.addAll(driver.findElements(By.tagName("a")));
        return buttons;
    }

    /**
     * Find button by text.
     *
     * @param text the text
     * @return the web element
     */
    public WebElement findButtonByText(String text) {
        return findButtons().stream()
            .filter(button -> matchesButtonText(button, text))
            .findFirst()
            .orElse(null);
    }

    /**
     * Matches button text.
     *
     * @param button the button
     * @param text the text
     * @return true, if successful
     */
    private boolean matchesButtonText(WebElement button, String text) {
        final String buttonText = button.getText().trim();
        return text.equalsIgnoreCase(buttonText) || buttonText.endsWith(text);
    }
}

package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * The Class ClickHelper.
 */
public class ClickHelper {

    /** The actions. */
    private final Actions actions;

    /** The helper. */
    private final UserPageVisitHelper helper;

    /** The enable screen shot. */
    public static boolean enableScreenShot = false;

    /**
     * Instantiates a new click helper.
     *
     * @param driver the driver
     * @param helper the helper
     */
    public ClickHelper(WebDriver driver, UserPageVisitHelper helper) {
        this.actions = new Actions(driver);
        this.helper = helper;
    }

    /**
     * Click with retry.
     *
     * @param element the element
     */
    public void clickWithRetry(WebElement element) {
    	clickElement(element);
    }

    /**
     * Click with delay.
     *
     * @param element the element
     */
    public void clickWithDelay(WebElement element) {
    	clickElement(element);

    }

    /**
     * Click element.
     *
     * @param element the element
     */
    private void clickElement(WebElement element) {
        actions.pause(Duration.ofMillis(250))
                    .clickAndHold(helper.refreshElement(element))
                    .release()
                    .pause(Duration.ofMillis(250))
                    .perform();

        helper.waitForPageLoad();

        if(enableScreenShot) {
			helper.grabScreenshot();
		}
    }
}

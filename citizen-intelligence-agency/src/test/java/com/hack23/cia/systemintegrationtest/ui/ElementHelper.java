package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementHelper {
    private final WebDriver driver;
    private final Duration waitTimeout;

    public ElementHelper(WebDriver driver, Duration waitTimeout) {
        this.driver = driver;
        this.waitTimeout = waitTimeout;
    }

    public WebElement waitForClickable(By locator) {
        return new WebDriverWait(driver, waitTimeout)
            .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> findButtons() {
        final List<WebElement> buttons = new ArrayList<>();
        buttons.addAll(driver.findElements(By.className("v-nativebutton")));
        buttons.addAll(driver.findElements(By.className("v-button")));
        buttons.addAll(driver.findElements(By.className("v-button-caption")));
        buttons.addAll(driver.findElements(By.tagName("a")));
        return buttons;
    }

    public WebElement findButtonByText(String text) {
        return findButtons().stream()
            .filter(button -> matchesButtonText(button, text))
            .findFirst()
            .orElse(null);
    }

    private boolean matchesButtonText(WebElement button, String text) {
        final String buttonText = button.getText().trim();
        return text.equalsIgnoreCase(buttonText) || buttonText.endsWith(text);
    }
}

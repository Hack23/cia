/*
 * From http://www.sahajamit.com/post/mystery-of-stale-element-reference-exception/
 *
*/
package com.hack23.cia.systemintegrationtest.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class StaleElementUtils.
 */
public class StaleElementUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StaleElementUtils.class);


    /**
     * Checks if the given element is stale.
     *
     * @param element the element
     * @return true if stale, false if still valid
     */
    public static boolean isElementStale(final WebElement element) {
        try {
            element.isDisplayed(); // If this throws, we know it’s stale
            return false;
        } catch (final StaleElementReferenceException ex) {
            return true;
        }
    }

    /**
     * Attempt to "refresh" the element by parsing the internal toString()
     * representation to reconstruct a locator. If the element is <em>not</em>
     * stale, it is simply returned unchanged.
     *
     * @param elem   the stale element
     * @param driver the WebDriver
     * @return a fresh WebElement if possible, otherwise null if re-location fails
     */
    public static WebElement refreshElement(final WebElement elem, final WebDriver driver) {
        // If element is still good, just return it
        if (!isElementStale(elem)) {
            return elem;
        }

        Object lastObject = driver; // Start from the driver
        try {
            // Example: [[ChromeDriver: chrome on MAC (1234)] -> id: foo -> xpath: //div[...]]
            final String description = elem.toString();
            final String[] locatorSegments = description.split("->");

            // We iterate over each arrow segment to identify "key: value"
            for (final String segment : locatorSegments) {
                final String trimmed = segment.trim().replaceAll("^\\[+", "").replaceAll("\\]+$", "");
                final String[] parts = trimmed.split(": ", 2);
                if (parts.length != 2) {
                    LOGGER.debug("Skipping unrecognized segment format: '{}'", segment);
                    continue;
                }

                final String key = parts[0];   // e.g. "id", "xpath"
                String value = parts[1];       // e.g. "foo", "//div[...]"

                // Sometimes the bracket balancing is off; attempt to fix
                final int leftBracketsCount = value.length() - value.replace("[", "").length();
                final int rightBracketsCount = value.length() - value.replace("]", "").length();
                if (leftBracketsCount - rightBracketsCount == 1) {
                    value = value + "]";
                }

                // Re-find using reflection-based approach
                lastObject = getWebElement(lastObject, key, value);
                if (lastObject == null) {
                    LOGGER.warn("Could not re-locate element using key={} and value={}", key, value);
                    return null;
                }
            }

            // If lastObject is a valid WebElement, we assume it was re-located
            if (lastObject instanceof WebElement) {
                return (WebElement) lastObject;
            } else {
                LOGGER.warn("Failed to refresh stale element: lastObject is not a WebElement");
            }

        } catch (final Exception e) {
            LOGGER.error("Error refreshing the stale element: {}", elem, e);
        }

        // Return null if we failed to re-locate
        return null;
    }

    /**
     * Build a By locator from a 'key: value' pair via reflection. For example:
     * <pre>id: foo</pre> or <pre>xpath: //div[...]</pre>.
     *
     * @param key   e.g. "id", "xpath"
     * @param value e.g. "foo", "//div[...]"
     * @return a new By object
     * @throws InvocationTargetException if reflection call fails
     * @throws IllegalAccessException    if reflection call fails
     */
    private static By getBy(final String key, final String value)
            throws InvocationTargetException, IllegalAccessException {
        final String methodName = key.replace(" ", "");
        final Method m = getCaseInsensitiveStaticMethod(By.class, methodName);
        return (By) m.invoke(null, value);
    }

    /**
     * Reflectively find a static method on {@link By} that matches, ignoring case.
     * E.g. "xpath", "XPATH", "XPath" all return By.xpath(...).
     *
     * @param clazz      The By class
     * @param methodName The method name (case-insensitive)
     * @return matching Method
     */
    @SuppressWarnings("rawtypes")
    private static Method getCaseInsensitiveStaticMethod(final Class clazz, final String methodName) {
        final Method[] methods = clazz.getMethods();
        for (final Method m : methods) {
            if (m.getName().equalsIgnoreCase(methodName)) {
                return m;
            }
        }
        throw new IllegalStateException(String.format(Locale.ENGLISH,
                "No static method named '%s' found on class %s", methodName, clazz));
    }

    /**
     * Reflectively call findElement(By) on either a WebDriver or WebElement,
     * ignoring case for the method name. Returns null if invocation fails.
     *
     * @param lastObject a WebDriver or WebElement
     * @param key        e.g. "id", "xpath"
     * @param value      the locator value
     * @return the newly found WebElement or null
     */
    private static WebElement getWebElement(final Object lastObject, final String key, final String value) {
        try {
            final By by = getBy(key, value);

            if (lastObject instanceof WebDriver) {
                return ((WebDriver) lastObject).findElement(by);
            } else if (lastObject instanceof WebElement) {
                return ((WebElement) lastObject).findElement(by);
            } else {
                LOGGER.warn("Unable to call findElement(...) on object type: {}", lastObject.getClass());
                return null;
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            LOGGER.error("Failed to invoke By.{}(\"{}\") or findElement(...) via reflection", key, value, e);
            return null;
        } catch (final Exception ex) {
            LOGGER.error("Unexpected error creating or using By locator: {} = {}", key, value, ex);
            return null;
        }
    }

}

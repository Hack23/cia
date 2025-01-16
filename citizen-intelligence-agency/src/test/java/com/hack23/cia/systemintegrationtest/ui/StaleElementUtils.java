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

public class StaleElementUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StaleElementUtils.class);

	/**
	 * Gets the by.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the by
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	private static By getBy(final String key, final String value) throws InvocationTargetException, IllegalAccessException {
		final String methodName = key.replace(" ", "");
		final Method m = getCaseInsensitiveStaticDeclaredMethod(By.class, methodName);
		return (By) m.invoke(null, value);
	}

	/**
	 * Gets the case insensitive declared method.
	 *
	 * @param obj
	 *            the obj
	 * @param methodName
	 *            the method name
	 * @return the case insensitive declared method
	 */
	private static Method getCaseInsensitiveDeclaredMethod(final Object obj, final String methodName) {
		final Method[] methods = obj.getClass().getMethods();
		Method method = null;
		for (final Method m : methods) {
			if (m.getName().equalsIgnoreCase(methodName)) {
				method = m;
				break;
			}
		}
		if (method == null) {
			throw new IllegalStateException(String.format(Locale.ENGLISH,"%s Method name is not found for this Class %s", methodName,
					obj.getClass().toString()));
		}
		return method;
	}

	/**
	 * Gets the case insensitive static declared method.
	 *
	 * @param clazz
	 *            the clazz
	 * @param methodName
	 *            the method name
	 * @return the case insensitive static declared method
	 */
	@SuppressWarnings("rawtypes")
	private static Method getCaseInsensitiveStaticDeclaredMethod(final Class clazz, final String methodName) {
		final Method[] methods = clazz.getMethods();
		Method method = null;
		for (final Method m : methods) {
			if (m.getName().equalsIgnoreCase(methodName)) {
				method = m;
				break;
			}
		}
		if (method == null) {
			throw new IllegalStateException(
					String.format(Locale.ENGLISH,"%s Method name is not found for this Class %s", methodName, clazz.toString()));
		}
		return method;
	}

	/**
	 * Gets the web element.
	 *
	 * @param lastObject
	 *            the last object
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the web element
	 */
	private static WebElement getWebElement(final Object lastObject, final String key, final String value) {
		WebElement element = null;
		try {
			final By by = getBy(key, value);
			final Method m = getCaseInsensitiveDeclaredMethod(lastObject, "findElement");
			element = (WebElement) m.invoke(lastObject, by);
		} catch (final InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return element;
	}

	/**
	 * Checks if is element stale.
	 *
	 * @param e
	 *            the e
	 * @return true, if is element stale
	 */
	public static boolean isElementStale(final WebElement e) {
		try {
			e.isDisplayed();
			return false;
		} catch (final StaleElementReferenceException ex) {
			return true;
		}
	}

	/**
	 * Refresh element.
	 *
	 * @param elem
	 *            the elem
	 * @param driver
	 *            the driver
	 * @return the web element
	 */
	public static WebElement refreshElement(final WebElement elem, final WebDriver driver) {
		if (!isElementStale(elem)) {
			return elem;
		}
		Object lastObject = null;
		try {
			final String[] arr = elem.toString().split("->");
			for (final String s : arr) {
				final String newstr = s.trim().replaceAll("^\\[+", "").replaceAll("\\]+$", "");
				final String[] parts = newstr.split(": ");
				final String key = parts[0];
				String value = parts[1];
				final int leftBracketsCount = value.length() - value.replace("[", "").length();
				final int rightBracketscount = value.length() - value.replace("]", "").length();
				if (leftBracketsCount - rightBracketscount == 1) {
					value = value + "]";
				}
				if (lastObject == null) {
					lastObject = driver;
				} else {
					lastObject = getWebElement(lastObject, key, value);
				}
			}
		} catch (final Exception e) {
			LOGGER.error("Error in Refreshing the stale Element.");
		}
		return (WebElement) lastObject;
	}
}

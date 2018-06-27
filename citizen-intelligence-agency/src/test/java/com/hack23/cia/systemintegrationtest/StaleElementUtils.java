/*
 * From http://www.sahajamit.com/post/mystery-of-stale-element-reference-exception/
 * 
*/
package com.hack23.cia.systemintegrationtest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
	 * Refresh element.
	 *
	 * @param elem
	 *            the elem
	 * @param driver
	 *            the driver
	 * @return the web element
	 */
	public static WebElement refreshElement(WebElement elem, WebDriver driver) {
		if (!isElementStale(elem))
			return elem;
		Object lastObject = null;
		try {
			String[] arr = elem.toString().split("->");
			List<String> newStr = new ArrayList<String>();
			for (String s : arr) {
				String newstr = s.trim().replaceAll("^\\[+", "").replaceAll("\\]+$", "");
				String[] parts = newstr.split(": ");
				String key = parts[0];
				String value = parts[1];
				int leftBracketsCount = value.length() - value.replace("[", "").length();
				int rightBracketscount = value.length() - value.replace("]", "").length();
				if (leftBracketsCount - rightBracketscount == 1)
					value = value + "]";
				if (lastObject == null) {
					lastObject = driver;
				} else {
					lastObject = getWebElement(lastObject, key, value);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error in Refreshing the stale Element.");
		}
		return (WebElement) lastObject;
	}

	/**
	 * Checks if is element stale.
	 *
	 * @param e
	 *            the e
	 * @return true, if is element stale
	 */
	public static boolean isElementStale(WebElement e) {
		try {
			e.isDisplayed();
			return false;
		} catch (StaleElementReferenceException ex) {
			return true;
		}
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
	private static WebElement getWebElement(Object lastObject, String key, String value) {
		WebElement element = null;
		try {
			By by = getBy(key, value);
			Method m = getCaseInsensitiveDeclaredMethod(lastObject, "findElement");
			element = (WebElement) m.invoke(lastObject, by);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return element;
	}

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
	private static By getBy(String key, String value) throws InvocationTargetException, IllegalAccessException {
		By by = null;
		Class clazz = By.class;
		String methodName = key.replace(" ", "");
		Method m = getCaseInsensitiveStaticDeclaredMethod(clazz, methodName);
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
	private static Method getCaseInsensitiveDeclaredMethod(Object obj, String methodName) {
		Method[] methods = obj.getClass().getMethods();
		Method method = null;
		for (Method m : methods) {
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
	private static Method getCaseInsensitiveStaticDeclaredMethod(Class clazz, String methodName) {
		Method[] methods = clazz.getMethods();
		Method method = null;
		for (Method m : methods) {
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
}

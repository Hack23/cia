/*
 * Copyright 2010 James Pether Sörling
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
package com.hack23.cia.service.api.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.tocea.easycoverage.framework.checkers.ArrayIndexOutOfBoundExceptionChecker;
import com.tocea.easycoverage.framework.checkers.BijectiveCompareToChecker;
import com.tocea.easycoverage.framework.checkers.BijectiveEqualsChecker;
import com.tocea.easycoverage.framework.checkers.CloneChecker;
import com.tocea.easycoverage.framework.checkers.NPEConstructorChecker;
import com.tocea.easycoverage.framework.checkers.NPEMethodChecker;
import com.tocea.easycoverage.framework.checkers.NullValueEqualsChecker;
import com.tocea.easycoverage.framework.checkers.SetterChecker;
import com.tocea.easycoverage.framework.checkers.ToStringNotNullChecker;
import com.tocea.easycoverage.framework.junit.JUnitTestSuiteProvider;

import junit.framework.TestSuite;

/**
 * The Class ApiActionSanityTest.
 */
@RunWith(AllTests.class)
public final class ApiActionSanityTest extends AbstractUnitTest {

	private static final String EXPECT_CLASSES_IN_PACKAGE = "Expect classes in package";
	private static final char PACKAGE_SEPARATOR = '.';
	private static final char CLASS_FILE_DIRECTORY_SEPARATOR = '/';
	private static final String CLASS_SUFFIX = ".class";

	/**
	 * Suite.
	 *
	 * @return the test suite
	 */
	public static TestSuite suite() {
		JUnitTestSuiteProvider testSuiteProvider = new JUnitTestSuiteProvider();

		assertTrue(EXPECT_CLASSES_IN_PACKAGE,checkAllClassesInPackage(testSuiteProvider,"com.hack23.cia.service.api.action.application"));
		assertTrue(EXPECT_CLASSES_IN_PACKAGE,checkAllClassesInPackage(testSuiteProvider,"com.hack23.cia.service.api.action.admin"));
		assertTrue(EXPECT_CLASSES_IN_PACKAGE,checkAllClassesInPackage(testSuiteProvider,"com.hack23.cia.service.api.action.user"));


		testSuiteProvider.addClassChecker(ToStringNotNullChecker.class);
		testSuiteProvider.addClassChecker(BijectiveCompareToChecker.class);
		testSuiteProvider.addClassChecker(BijectiveEqualsChecker.class);
		testSuiteProvider.addClassChecker(CloneChecker.class);
		testSuiteProvider.addClassChecker(NPEConstructorChecker.class);
		testSuiteProvider.addClassChecker(NullValueEqualsChecker.class);

		testSuiteProvider.addMethodChecker(NPEMethodChecker.class);
		testSuiteProvider.addMethodChecker(SetterChecker.class);
		testSuiteProvider.addMethodChecker(ArrayIndexOutOfBoundExceptionChecker.class);

		return testSuiteProvider.getTestSuite();
	}

	/**
	 * Check all classes in package.
	 *
	 * @param testSuiteProvider
	 *            the test suite provider
	 * @param string
	 *            the string
	 */
	private static boolean checkAllClassesInPackage(JUnitTestSuiteProvider testSuiteProvider, String string) {
		List<Class<?>> allClasses = getAllClasses(string);
		for (Class<?> class1 : allClasses) {
			testSuiteProvider.addClass(class1);
		}
		return !allClasses.isEmpty();
	}

	/**
	 * Gets the all classes.
	 *
	 * @param pckgname
	 *            the pckgname
	 * @return the all classes
	 */
	private static List<Class<?>> getAllClasses(String pckgname) {
		final List<Class<?>> classes = new ArrayList<>();
		File directory = new File(
				Thread.currentThread().getContextClassLoader().getResource(pckgname.replace(PACKAGE_SEPARATOR, CLASS_FILE_DIRECTORY_SEPARATOR)).getFile());
		if (directory.exists()) {
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				if (files[i].endsWith(CLASS_SUFFIX)) {
					try {
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(pckgname);
						stringBuilder.append(PACKAGE_SEPARATOR);
						stringBuilder.append(files[i].substring(0, files[i].length() - 6));
						classes.add(Class.forName(stringBuilder.toString()));
					} catch (ClassNotFoundException e) {
					}
				}
			}
		}
		return classes;
	}
}

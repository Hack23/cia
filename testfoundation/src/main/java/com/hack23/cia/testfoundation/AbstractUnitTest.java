/*
 * Copyright 2010-2019 James Pether Sörling
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

package com.hack23.cia.testfoundation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tocea.easycoverage.framework.junit.JUnitTestSuiteProvider;

/**
 * The Class AbstractUnitTest.
 */
public abstract class AbstractUnitTest extends AbstractTest {

	private static final char PACKAGE_SEPARATOR = '.';
	private static final char CLASS_FILE_DIRECTORY_SEPARATOR = '/';
	private static final String CLASS_SUFFIX = ".class";

	/**
	 * Instantiates a new abstract unit test.
	 */
	protected AbstractUnitTest() {
		super();
	}

	/**
	 * Check all classes in package.
	 *
	 * @param testSuiteProvider the test suite provider
	 * @param string            the string
	 */
	public static final boolean checkAllClassesInPackage(final JUnitTestSuiteProvider testSuiteProvider,
			final String string) {
		final List<Class<?>> allClasses = getAllClasses(string);
		for (final Class<?> class1 : allClasses) {
			testSuiteProvider.addClass(class1);
		}
		return !allClasses.isEmpty();
	}

	/**
	 * Gets the all classes.
	 *
	 * @param pckgname the pckgname
	 * @return the all classes
	 */
	public static final List<Class<?>> getAllClasses(final String pckgname) {
		final List<Class<?>> classes = new ArrayList<>();
		final File directory = new File(Thread.currentThread().getContextClassLoader()
				.getResource(pckgname.replace(PACKAGE_SEPARATOR, CLASS_FILE_DIRECTORY_SEPARATOR)).getFile());
		if (directory.exists()) {
			final String[] files = directory.list();
			for (final String file : files) {
				if (file.endsWith(CLASS_SUFFIX)) {
					try {
						final StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(pckgname);
						stringBuilder.append(PACKAGE_SEPARATOR);
						stringBuilder.append(file.substring(0, file.length() - 6));
						classes.add(Class.forName(stringBuilder.toString()));
					} catch (final ClassNotFoundException e) {
					}
				}
			}
		}
		return classes;
	}

}

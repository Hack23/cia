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

package com.hack23.cia.service.api;

/**
 * The Interface ConfigurationManager.
 */
public interface ConfigurationManager {

	/**
	 * Gets the user configuration.
	 *
	 * @param url
	 *            the url
	 * @param locale
	 *            the locale
	 * @return the user configuration
	 */
	UserConfiguration getUserConfiguration(String url, String locale);

	/**
	 * Creates the default config if empty.
	 */
	void createDefaultConfigIfEmpty();

	/**
	 * Creates the default languages if empty.
	 */
	void createDefaultLanguagesIfEmpty();
}
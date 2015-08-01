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
 *	$Id: ConfigurationManager.java 6046 2015-05-06 20:42:53Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.api/src/main/java/com/hack23/cia/service/api/ConfigurationManager.java $
*/

package com.hack23.cia.service.api;


/**
 * The Interface ConfigurationManager.
 */
public interface ConfigurationManager {

	/**
	 * Gets the system configuration.
	 *
	 * @return the system configuration
	 */
	SystemConfiguration getSystemConfiguration();

	/**
	 * Gets the user configuration.
	 *
	 * @param url
	 *            the url
	 * @return the user configuration
	 */
	UserConfiguration getUserConfiguration(String url);
	
	/**
	 * Creates the default config if empty.
	 */
	void createDefaultConfigIfEmpty();
}
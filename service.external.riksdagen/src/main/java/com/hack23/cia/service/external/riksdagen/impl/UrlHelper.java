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
package com.hack23.cia.service.external.riksdagen.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class UrlHelper.
 */
public final class UrlHelper {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlHelper.class);


	/**
	 * Url encode.
	 *
	 * @param id the id
	 * @param charset the charset
	 * @return the string
	 */
	public static String urlEncode(final String id, final String charset) {
		try {
			return URLEncoder.encode(id, charset);
		} catch (final UnsupportedEncodingException e) {
			LOGGER.warn("Problem encoding {} : {}",id,e);
			return id;
		}
	}

}

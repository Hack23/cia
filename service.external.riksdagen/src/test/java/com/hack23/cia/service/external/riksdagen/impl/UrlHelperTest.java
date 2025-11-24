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

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

/**
 * The Class UrlHelperTest.
 */
public class UrlHelperTest extends Assert {

	/**
	 * Url encode succes test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void urlEncodeSuccesTest() throws Exception {
		new UrlHelper();
		assertEquals("%26", UrlHelper.urlEncode("&", StandardCharsets.UTF_8.toString()));
	}

	/**
	 * Url encode failure test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void urlEncodeFailureTest() throws Exception {
		new UrlHelper();
		assertEquals("&", UrlHelper.urlEncode("&", "bad encoding"));
	}

	/**
	 * Url encode Swedish characters test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void urlEncodeSwedishCharactersTest() throws Exception {
		// Test Swedish characters å, ä, ö
		assertEquals("hca3f%C3%B6u34", UrlHelper.urlEncode("hca3föu34", StandardCharsets.UTF_8.toString()));
		assertEquals("hc19f%C3%B6u6p2", UrlHelper.urlEncode("hc19föu6p2", StandardCharsets.UTF_8.toString()));
		assertEquals("hda1f%C3%B6u3p", UrlHelper.urlEncode("hda1föu3p", StandardCharsets.UTF_8.toString()));
		// Test all Swedish special characters
		assertEquals("%C3%A5", UrlHelper.urlEncode("å", StandardCharsets.UTF_8.toString()));
		assertEquals("%C3%A4", UrlHelper.urlEncode("ä", StandardCharsets.UTF_8.toString()));
		assertEquals("%C3%B6", UrlHelper.urlEncode("ö", StandardCharsets.UTF_8.toString()));
	}

}

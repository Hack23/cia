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

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.hack23.cia.service.external.riksdagen.api.DocumentTypeNames;

/**
 * The Class DocumentTypeNamesTest.
 */
public class DocumentTypeNamesTest extends Assert {

	/**
	 * Check code description test.
	 */
	@Test
	public void checkCodeDescriptionTest() {
		assertEquals("f-lista",DocumentTypeNames.AGENDA.getShortCode());
		assertEquals("Föredragningslista",DocumentTypeNames.AGENDA.getDescription());
	}

	/**
	 * Check no empty values test.
	 */
	@Test
	public void checkNoEmptyValuesTest() {
		final DocumentTypeNames[] values = DocumentTypeNames.values();
		for (final DocumentTypeNames documentTypeNames : values) {
			assertFalse(StringUtils.isEmpty(documentTypeNames.getShortCode()));
			assertFalse(StringUtils.isEmpty(documentTypeNames.getDescription()));
		}
	}
}

/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.service.data.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.data.api.DocumentElementDAO;

/**
 * The Class DocumentElementDAOITest.
 */
public final class DocumentElementDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The document element DAO. */
	@Autowired
	private DocumentElementDAO documentElementDAO;

	/**
	 * Test get missing document start from year.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetMissingDocumentStartFromYear() throws Exception {
		assertTrue("Expect documents for all past years in database",documentElementDAO.getMissingDocumentStartFromYear(2000) > 2015L);
	}

	/**
	 * Test get size.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetSize() throws Exception {
		assertTrue("Expect some documents in database",documentElementDAO.getSize() >= 0);
	}


	/**
	 * Gets the avaible document content test.
	 *
	 * @return the avaible document content test
	 */
	@Test
	public void getAvaibleDocumentContentTest() {
		final List<String> avaibleDocumentContent = documentElementDAO.getAvaibleDocumentContent();
		assertNotNull(avaibleDocumentContent);
		assertFalse(avaibleDocumentContent.isEmpty());
	}

	/**
	 * Gets the avaible document status test.
	 *
	 * @return the avaible document status test
	 */
	@Test
	public void getAvaibleDocumentStatusTest() {
		final List<String> avaibleDocumentStatus = documentElementDAO.getAvaibleDocumentStatus();
		assertNotNull(avaibleDocumentStatus);
		assertFalse(avaibleDocumentStatus.isEmpty());
	}

	/**
	 * Gets the id list test.
	 *
	 * @return the id list test
	 */
	@Test
	public void getIdListTest() {
		final List<String> idList = documentElementDAO.getIdList();
		assertNotNull(idList);
		assertFalse(idList.isEmpty());
	}

	/**
	 * Gets the missing document start from year before any documents test.
	 *
	 * @return the missing document start from year before any documents test
	 */
	@Test
	public void getMissingDocumentStartFromYearBeforeAnyDocumentsTest() {
		final int startFromYear = 2900;
		final int loadFromYear  = documentElementDAO.getMissingDocumentStartFromYear(startFromYear);
		assertEquals(startFromYear,loadFromYear);
	}

}

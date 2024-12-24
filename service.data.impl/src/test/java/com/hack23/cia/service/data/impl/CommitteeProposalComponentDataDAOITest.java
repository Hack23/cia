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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.data.api.CommitteeProposalComponentDataDAO;

/**
 * The Class CommitteeProposalComponentDataDAOITest.
 */
public final class CommitteeProposalComponentDataDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The dao. */
	@Autowired
	private CommitteeProposalComponentDataDAO dao;

	/**
	 * Check document content data exist test.
	 */
	@Test
	public void checkCommitteeDocumentDataExistTest() {
		assertTrue(dao.checkCommitteeDocumentData("GW01UbU11"));
	}

	/**
	 * Check document content data do not exist test.
	 */
	@Test
	public void checkCommitteeDocumentDataDoNotExistTest() {
		assertFalse(dao.checkCommitteeDocumentData("somemissingdocumentId"));
	}


	/**
	 * Gets the id list test.
	 *
	 * @return the id list test
	 */
	@Test
	public void getIdListTest() {
		assertFalse(dao.getIdList().isEmpty());
	}

}

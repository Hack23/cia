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
package com.hack23.cia.service.data.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.data.api.DataDAO;

/**
 * The Class DataDAOITest.
 */
public final class DataDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The data DAO. */
	@Autowired
	private DataDAO dataDAO;

	/**
	 * Gets the id list test.
	 *
	 * @return the id list test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getIdListTest() throws Exception {
		final List<String> idList = dataDAO.getIdList();
		assertNotNull(idList);
		assertFalse(idList.isEmpty());
	}

}

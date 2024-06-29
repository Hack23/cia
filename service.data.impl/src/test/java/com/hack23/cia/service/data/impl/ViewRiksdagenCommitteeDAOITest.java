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
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenCommitteeEmbeddedId;
import com.hack23.cia.service.data.api.ViewRiksdagenCommitteeDAO;

/**
 * The Class ViewRiksdagenCommitteeDAOITest.
 */
@Transactional
public class ViewRiksdagenCommitteeDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The dao. */
	@Autowired
	private ViewRiksdagenCommitteeDAO dao;

	/**
	 * Gets the id list test.
	 *
	 * @return the id list test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getIdListTest() throws Exception {
		final List<RiksdagenCommitteeEmbeddedId> all = dao.getIdList();
		assertNotNull(all);
		assertFalse(all.isEmpty());
	}
}

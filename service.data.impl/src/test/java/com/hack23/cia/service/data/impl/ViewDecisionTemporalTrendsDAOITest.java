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

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.impl.ViewDecisionTemporalTrends;
import com.hack23.cia.service.data.api.ViewDecisionTemporalTrendsDAO;

/**
 * The Class ViewDecisionTemporalTrendsDAOITest.
 * 
 * Integration test for decision temporal trends view entity mapping.
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 */
@Transactional
public class ViewDecisionTemporalTrendsDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The dao. */
	@Autowired
	private ViewDecisionTemporalTrendsDAO dao;

	/**
	 * Gets the id list test.
	 *
	 * @return the id list test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getIdListTest() throws Exception {
		final List<Date> all = dao.getIdList();
		assertNotNull("ID list should not be null", all);
	}

	/**
	 * Find all test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void findAllTest() throws Exception {
		final List<ViewDecisionTemporalTrends> all = dao.getAll();
		assertNotNull("Result list should not be null", all);
		
		// If data exists, validate entity mapping
		if (!all.isEmpty()) {
			final ViewDecisionTemporalTrends entity = all.get(0);
			assertNotNull("Decision day should not be null", entity.getDecisionDay());
			assertTrue("Daily decisions should be non-negative", entity.getDailyDecisions() >= 0);
		}
	}
}

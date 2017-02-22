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
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.service.component.agent.impl.worldbank.workers.data;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;

/**
 * The Class WorldbankUpdateServiceITest.
 */
@Transactional
public class WorldbankUpdateServiceITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The worldbank update service. */
	@Autowired
	private WorldbankUpdateService worldbankUpdateService;

	@Test
	public void updateIndicatorElementNullTest() throws Exception {
		worldbankUpdateService.updateIndicatorElement(null);
	}

	@Test
	public void updateCountryElementNullTest() throws Exception {
		worldbankUpdateService.updateCountryElement(null);
	}

	@Test
	public void updateDataNullOrEmptyTest() throws Exception {
		worldbankUpdateService.updateData(null);
		worldbankUpdateService.updateData(new ArrayList<>());
	}

}

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
package com.hack23.cia.service.component.agent.impl.riksdagen.workers.data;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenPersonApi;

/**
 * The Class RiksdagenUpdateServiceITest.
 */
@Transactional
public class RiksdagenUpdateServiceITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The riksdagen update service. */
	@Autowired
	private RiksdagenUpdateService riksdagenUpdateService;

	/** The riksdagen api. */
	@Autowired
	private RiksdagenPersonApi riksdagenApi;

	/**
	 * Update person data test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void updatePersonDataTest() throws Exception {
		riksdagenUpdateService.update(riksdagenApi.getPerson("0542160909628"));
	}

}

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
package com.hack23.cia.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.service.api.DataContainer;

/**
 * The Class ViewRiksdagenCommitteeDataContainerITest.
 */
public class ViewRiksdagenCommitteeDataContainerITest extends AbstractServiceFunctionalIntegrationTest {

	/** The view riksdagen committee data container. */
	@Autowired
	@Qualifier("ViewRiksdagenCommitteeDataContainer")
	private DataContainer<ViewRiksdagenCommittee,String> viewRiksdagenCommitteeDataContainer;

	/**
	 * Load not found failure test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void loadNotFoundFailureTest() throws Exception {
		setAuthenticatedAnonymousUser();
		assertNull(viewRiksdagenCommitteeDataContainer.load("nonValidId"));
	}

}

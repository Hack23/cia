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
package com.hack23.cia.service.component.agent.impl.val;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.val.partier.impl.SwedenPoliticalParty;
import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;
import com.hack23.cia.service.data.api.SwedenPoliticalPartyDAO;
import com.hack23.cia.service.external.val.api.ValApi;
import com.hack23.cia.service.external.val.api.ValApiException;

/**
 * The Class ValImportServiceImplITest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValImportServiceImplITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	@Autowired
	private SwedenPoliticalPartyDAO swedenPoliticalPartyDAO;

	@Autowired
	private ValImportService valImportService;

	/**
	 * Load political parties success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 */
	@Test
	@Transactional
	public void loadPoliticalPartiesSuccessTest() throws JMSException {
		for (final SwedenPoliticalParty swedenPoliticalParty : swedenPoliticalPartyDAO.getAll()) {
			swedenPoliticalPartyDAO.delete(swedenPoliticalParty);
		}
		assertTrue(0 == swedenPoliticalPartyDAO.getSize());
		valImportService.loadPoliticalParties();
	}

	/**
	 * Load political parties already loaded success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 */
	@Test
	@Transactional
	public void loadPoliticalPartiesFinalSuccessTest() throws JMSException {
		valImportService.loadPoliticalParties();
		assertTrue(swedenPoliticalPartyDAO.getSize() > 0);
	}

	/**
	 * Load political parties val api failure test.
	 *
	 * @throws ValApiException
	 *             the val api exception
	 */
	@Test
	@Transactional
	public void loadPoliticalPartiesFailureTest() throws ValApiException {
		for (final SwedenPoliticalParty swedenPoliticalParty : swedenPoliticalPartyDAO.getAll()) {
			swedenPoliticalPartyDAO.delete(swedenPoliticalParty);
		}

		final ValApi valApi = mock(ValApi.class);
		final ValImportServiceImpl valImportService = new ValImportServiceImpl(valApi,swedenPoliticalPartyDAO);

		when(valApi.getSwedenPoliticalParties()).thenThrow(new ValApiException("",new RuntimeException()));
		valImportService.loadPoliticalParties();
		verify(valApi, atLeastOnce()).getSwedenPoliticalParties();

	}

}
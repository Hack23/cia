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
package com.hack23.cia.service.component.agent.impl.worldbank.workgenerator;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.transaction.Transactional;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.util.ReflectionTestUtils;

import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;
import com.hack23.cia.service.component.agent.impl.worldbank.workgenerator.data.WorldbankImportService;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankCountryApi;

/**
 * The Class WorldBankCountryWorkGeneratorImplITest.
 */
@Transactional
public class WorldBankCountryWorkGeneratorImplITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The world bank data sources work generator. */
	@Autowired
	@Qualifier("WorldBankCountryWorkGeneratorImpl")
	private WorldBankDataSourcesWorkGenerator worldBankDataSourcesWorkGenerator;

	@Autowired
	private WorldbankImportService importService;

	/**
	 * Generate work orders all data is already imported success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 */
	@Test
	public void generateWorkOrdersAllDataIsAlreadyImportedSuccessTest() throws JMSException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);
        ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);

        assertTrue(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.COUNTRIES));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.DATA));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.INDICATORS));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.TOPIC));

        ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "importService", importService);

		worldBankDataSourcesWorkGenerator.generateWorkOrders();

		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);

		final ArgumentCaptor<Serializable> stringCaptor = ArgumentCaptor.forClass(Serializable.class);

		verify(jmsSenderMock, never()).send(destCaptor.capture(),stringCaptor.capture());

		assertTrue(stringCaptor.getAllValues().isEmpty());
		assertTrue(destCaptor.getAllValues().isEmpty());
	}


	/**
	 * Generate work orders data missing success test.
	 *
	 * @throws JMSException the JMS exception
	 */
	@Test
	public void generateWorkOrdersDataMissingSuccessTest() throws JMSException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);
        ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);

        final WorldbankImportService importService = mock(WorldbankImportService.class);
        when(importService.getWorldBankCountryMap()).thenReturn(new HashMap<>());

        ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "importService", importService);


        assertTrue(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.COUNTRIES));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.DATA));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.INDICATORS));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.TOPIC));

		worldBankDataSourcesWorkGenerator.generateWorkOrders();

		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);

		final ArgumentCaptor<Serializable> stringCaptor = ArgumentCaptor.forClass(Serializable.class);

		verify(jmsSenderMock, atLeastOnce()).send(destCaptor.capture(),stringCaptor.capture());

		final List<Serializable> capturedStrings = stringCaptor.getAllValues();
		final List<Destination> capturedDestinations = destCaptor.getAllValues();

		assertFalse(capturedStrings.isEmpty());
		assertFalse(capturedDestinations.isEmpty());
	}


	/**
	 * Generate work orders data failure test.
	 *
	 * @throws JMSException         the JMS exception
	 * @throws DataFailureException the data failure exception
	 */
	@Test
	public void generateWorkOrdersDataFailureTest() throws JMSException, DataFailureException {
		final WorldBankCountryApi worldBankCountryApi = mock(WorldBankCountryApi.class);
		when(worldBankCountryApi.getCountries()).thenThrow(new DataFailureException(new RuntimeException()));
		final WorldBankCountryWorkGeneratorImpl worldBankCountryWorkGeneratorImpl = new WorldBankCountryWorkGeneratorImpl(worldBankCountryApi);

		final JmsSender jmsSenderMock = mock(JmsSender.class);
        ReflectionTestUtils.setField(worldBankCountryWorkGeneratorImpl, "jmsSender", jmsSenderMock);

		worldBankCountryWorkGeneratorImpl.generateWorkOrders();

		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);
		final ArgumentCaptor<Serializable> stringCaptor = ArgumentCaptor.forClass(Serializable.class);

		verify(jmsSenderMock, never()).send(destCaptor.capture(),stringCaptor.capture());

		assertTrue(stringCaptor.getAllValues().isEmpty());
		assertTrue(destCaptor.getAllValues().isEmpty());
	}



}

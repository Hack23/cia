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
package com.hack23.cia.service.impl;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.data.api.DataViewer;

/**
 * The Class ViewDataDataContainerFactoryImplITest.
 */
public class ViewDataDataContainerFactoryImplITest extends AbstractServiceFunctionalIntegrationTest {

	/** The view riksdagen committee data container. */
	@Autowired
	@Qualifier("ViewDataDataContainerFactory")
	private DataViewer dataViewer;

	@Autowired
	private ViewDataDataContainerFactory viewDataDataContainerFactory;

	/**
	 * Find first by property test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void findFirstByPropertyTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final ViewRiksdagenPolitician findFirstByProperty = dataViewer.findFirstByProperty(ViewRiksdagenPolitician.class,
				ViewRiksdagenPolitician_.party, "s");
		assertNotNull(findFirstByProperty);
		final ViewRiksdagenPolitician findFirstByPropertyNull = dataViewer.findFirstByProperty(ViewRiksdagenPolitician.class,
				ViewRiksdagenPolitician_.party, "noParty");
		assertNull(findFirstByPropertyNull);
	}

	/**
	 * Find ordered list by property from factory test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void findOrderedListByPropertyFromFactoryTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final DataContainer<ViewRiksdagenPolitician, Serializable> dataContainer = viewDataDataContainerFactory
				.createDataContainer(ViewRiksdagenPolitician.class);

		final List<ViewRiksdagenPolitician> listByProperty = dataContainer.findOrderedListByProperty(
				ViewRiksdagenPolitician_.party, "S" , ViewRiksdagenPolitician_.lastName);
		assertNotNull(listByProperty);
		assertFalse(listByProperty.isEmpty());

		final List<ViewRiksdagenPolitician> listByPropertyEmpty = dataContainer.findOrderedListByProperty(
				ViewRiksdagenPolitician_.party, "noparty" , ViewRiksdagenPolitician_.lastName);
		assertNotNull(listByPropertyEmpty);
		assertTrue(listByPropertyEmpty.isEmpty());
	}

	/**
	 * Find ordered list by property test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void findOrderedListByPropertyTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final List<ViewRiksdagenPolitician> orderedListByProperty = dataViewer.findOrderedListByProperty(
				ViewRiksdagenPolitician.class, ViewRiksdagenPolitician_.party, "s", ViewRiksdagenPolitician_.lastName);
		assertNotNull(orderedListByProperty);
		assertFalse(orderedListByProperty.isEmpty());
	}

	/**
	 * Gets the page test.
	 *
	 * @return the page test
	 * @throws Exception the exception
	 */
	@Test
	public void getPageTest() throws Exception {
		final DataContainer<ViewRiksdagenPolitician, Serializable> dataContainer = viewDataDataContainerFactory
				.createDataContainer(ViewRiksdagenPolitician.class);
		setAuthenticatedAnonymousUser();
		final List<ViewRiksdagenPolitician> page = dataContainer.getPage(1, 20);
		assertNotNull(page);
		assertEquals(20, page.size());
	}

	/**
	 * Gets the page from factory test.
	 *
	 * @return the page from factory test
	 * @throws Exception the exception
	 */
	@Test
	public void getPageFromFactoryTest() throws Exception {
		setAuthenticatedAnonymousUser();
		final List<ViewRiksdagenPolitician> page = dataViewer.getPage(ViewRiksdagenPolitician.class, 1, 20);
		assertNotNull(page);
		assertEquals(20, page.size());
	}

}

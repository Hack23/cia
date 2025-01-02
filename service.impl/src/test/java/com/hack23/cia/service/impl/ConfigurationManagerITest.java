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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import com.hack23.cia.model.internal.application.system.impl.Agency;
import com.hack23.cia.model.internal.application.system.impl.LanguageData;
import com.hack23.cia.service.api.ConfigurationManager;
import com.hack23.cia.service.api.UserConfiguration;
import com.hack23.cia.service.data.api.AgencyDAO;
import com.hack23.cia.service.data.api.LanguageDataDAO;

/**
 * The Class ConfigurationManagerITest.
 */
public final class ConfigurationManagerITest extends AbstractServiceFunctionalIntegrationTest {

	/** The configuration manager. */
	@Autowired
	private ConfigurationManager configurationManager;

	/**
	 * Gets the user configuration success test.
	 *
	 * @return the user configuration success test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getUserConfigurationSuccessTest() throws Exception {
		setAuthenticatedAnonymousUser();

		final UserConfiguration userConfiguration = configurationManager.getUserConfiguration("www.hack23.com", "en");
		assertNotNull(EXPECT_A_RESULT, userConfiguration);
		assertNotNull(EXPECT_A_RESULT, userConfiguration.getAgency());
		assertNotNull(EXPECT_A_RESULT, userConfiguration.getPortal());
		assertNotNull(EXPECT_A_RESULT, userConfiguration.getLanguage());
	}

	/**
	 * Creates the default config if empty ignore when exist test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void createDefaultConfigIfEmptyIgnoreWhenExistTest() throws Exception {
		final AgencyDAO agencyDAO = mock(AgencyDAO.class);
		ReflectionTestUtils.setField(configurationManager, "agencyDAO", agencyDAO);
		final ArrayList<Agency> list = new ArrayList<>();
		list.add(new Agency());
		when(agencyDAO.getAll()).thenReturn(list);
		setAuthenticatedAdminuser();
		configurationManager.createDefaultConfigIfEmpty();
		verify(agencyDAO, never()).persist(ArgumentMatchers.any(Agency.class));
	}

	/**
	 * Creates the default config if empty ignore when empty test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void createDefaultConfigIfEmptyIgnoreWhenEmptyTest() throws Exception {
		final AgencyDAO agencyDAO = mock(AgencyDAO.class);
		ReflectionTestUtils.setField(configurationManager, "agencyDAO", agencyDAO);
		when(agencyDAO.getAll()).thenReturn(new ArrayList<>());
		setAuthenticatedAdminuser();
		configurationManager.createDefaultConfigIfEmpty();
		verify(agencyDAO, times(1)).persist(ArgumentMatchers.any(Agency.class));
	}

	/**
	 * Creates the default languages if empty ignore when exist test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void createDefaultLanguagesIfEmptyIgnoreWhenExistTest() throws Exception {
		final LanguageDataDAO languageDataDAO = mock(LanguageDataDAO.class);
		ReflectionTestUtils.setField(configurationManager, "languageDataDAO", languageDataDAO);
		final ArrayList<LanguageData> list = new ArrayList<>();
		list.add(new LanguageData());
		when(languageDataDAO.getAll()).thenReturn(list);
		setAuthenticatedAdminuser();
		configurationManager.createDefaultLanguagesIfEmpty();
		verify(languageDataDAO, never()).persist(ArgumentMatchers.any(List.class));
	}

	/**
	 * Creates the default languages if empty ignore when empty test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void createDefaultLanguagesIfEmptyIgnoreWhenEmptyTest() throws Exception {
		final LanguageDataDAO languageDataDAO = mock(LanguageDataDAO.class);
		ReflectionTestUtils.setField(configurationManager, "languageDataDAO", languageDataDAO);
		when(languageDataDAO.getAll()).thenReturn(new ArrayList<>());
		setAuthenticatedAdminuser();
		configurationManager.createDefaultLanguagesIfEmpty();
		verify(languageDataDAO, times(1)).persist(ArgumentMatchers.any(List.class));
	}

}
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration_;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.data.api.ApplicationConfigurationDAO;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;

/**
 * The Class ApplicationConfigurationServiceImpl.
 */
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
final class ApplicationConfigurationServiceImpl implements ApplicationConfigurationService {


	/** The application configuration dao. */
	@Autowired
	private ApplicationConfigurationDAO applicationConfigurationDAO;

	/**
	 * Instantiates a new application configuration service impl.
	 */
	public ApplicationConfigurationServiceImpl() {
		super();
	}

	@Override
	public ApplicationConfiguration checkValueOrLoadDefault(final String configTitle, final String configDescription,
			final ConfigurationGroup configurationGroup, final String component, final String componentTitle, final String componentDescription,
			final String propertyId, final String propertyValue) {

		final List<ApplicationConfiguration> findListByProperty = applicationConfigurationDAO.findListByProperty(new Object[]{component,configurationGroup,propertyId}, ApplicationConfiguration_.component,ApplicationConfiguration_.configurationGroup,ApplicationConfiguration_.propertyId);

		if (findListByProperty.isEmpty()) {
			final ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
			applicationConfiguration.setCreatedDate(new Date());
			applicationConfiguration.setUpdatedDate(new Date());

			applicationConfiguration.setConfigTitle(configTitle);
			applicationConfiguration.setConfigDescription(configDescription);
			applicationConfiguration.setConfigurationGroup(configurationGroup);

			applicationConfiguration.setComponent(component);
			applicationConfiguration.setComponentTitle(componentTitle);
			applicationConfiguration.setComponentDescription(componentDescription);

			applicationConfiguration.setPropertyId(propertyId);
			applicationConfiguration.setPropertyValue(propertyValue);

			applicationConfigurationDAO.persist(applicationConfiguration);

			return applicationConfiguration;
		} else {
			return findListByProperty.get(0);
		}

	}

}

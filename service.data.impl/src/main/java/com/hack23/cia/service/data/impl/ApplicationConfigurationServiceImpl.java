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
public class ApplicationConfigurationServiceImpl implements ApplicationConfigurationService {


	/** The application configuration dao. */
	@Autowired
	private ApplicationConfigurationDAO applicationConfigurationDAO;

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.ApplicationConfigurationService#checkValueOrLoadDefault(java.lang.String, java.lang.String, com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ApplicationConfiguration checkValueOrLoadDefault(String configTitle, String configDescription,
			ConfigurationGroup configurationGroup, String component, String componentTitle, String componentDescription,
			String propertyId, String propertyValue) {

		List<ApplicationConfiguration> findListByProperty = applicationConfigurationDAO.findListByProperty(new Object[]{component,configurationGroup,propertyId}, ApplicationConfiguration_.component,ApplicationConfiguration_.configurationGroup,ApplicationConfiguration_.propertyId);

		if (findListByProperty.isEmpty()) {
			ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
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
			return findListByProperty.iterator().next();
		}

	}

}

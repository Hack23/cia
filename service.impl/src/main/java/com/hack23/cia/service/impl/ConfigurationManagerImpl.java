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
package com.hack23.cia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.Agency;
import com.hack23.cia.model.internal.application.system.impl.Portal;
import com.hack23.cia.model.internal.application.system.impl.PortalType;
import com.hack23.cia.service.api.ConfigurationManager;
import com.hack23.cia.service.api.SystemConfiguration;
import com.hack23.cia.service.api.UserConfiguration;
import com.hack23.cia.service.data.api.AgencyDAO;

/**
 * The Class ConfigurationManagerImpl.
 */
@Service("ConfigurationManager")
@Transactional
public final class ConfigurationManagerImpl implements ConfigurationManager {

	/** The configuration. */
	@Autowired
	private SystemConfiguration configuration;

	/** The agency dao. */
	@Autowired
	private AgencyDAO agencyDAO;

	/**
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.api.ConfigurationManager#getConfiguration()
	 */
	@Override
	@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
	public SystemConfiguration getSystemConfiguration() {
		return configuration;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.api.ConfigurationManager#getUserConfiguration(
	 * java.lang.String)
	 */
	@Override
	@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
	public UserConfiguration getUserConfiguration(final String url) {
		final Agency agency = agencyDAO.getAll().get(0);
		Portal usePortal = null;
		for (final Portal portal : agency.getPortals()) {
			if (usePortal == null
					&& PortalType.DEFAULT == portal.getPortalType()) {
				usePortal = portal;
			} else if (url.contains(portal.getPortalName())) {
				usePortal = portal;
			}
		}

		return new UserConfigurationImpl(agency, usePortal);
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.service.api.ConfigurationManager#createDefaultConfigIfEmpty()
	 */
	@Override
	@Secured({"ROLE_ADMIN" })
	public void createDefaultConfigIfEmpty() {
		if (agencyDAO.getAll().isEmpty()) {
			final List<Portal> portals = new ArrayList<>();
			final Portal defaulPortal = new Portal()
					.withPortalType(PortalType.DEFAULT)
					.withDescription("Global Portal").withPortalName("Default");
			portals.add(defaulPortal);

			final Portal domainPortal = new Portal()
					.withPortalType(PortalType.DOMAIN)
					.withDescription("Hack23.com")
					.withPortalName("www.hack23.com");
			portals.add(domainPortal);

			final Agency agency = new Agency().withAgencyName(
					"Citizen Intelligence Agency").withDescription(
					"Tracking politicians like bugs");
			agency.setPortals(portals);
			agencyDAO.persist(agency);
		}
	}

}

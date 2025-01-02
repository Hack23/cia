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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.Agency;
import com.hack23.cia.model.internal.application.system.impl.LanguageData;
import com.hack23.cia.model.internal.application.system.impl.Portal;
import com.hack23.cia.model.internal.application.system.impl.PortalType;
import com.hack23.cia.service.api.ConfigurationManager;
import com.hack23.cia.service.api.UserConfiguration;
import com.hack23.cia.service.data.api.AgencyDAO;
import com.hack23.cia.service.data.api.LanguageDataDAO;

/**
 * The Class ConfigurationManagerImpl.
 */
@Service("ConfigurationManager")
@Transactional
final class ConfigurationManagerImpl implements ConfigurationManager {

	/** The Constant EXPECTED_LOCALE_LENGTH. */
	private static final int EXPECTED_LOCALE_LENGTH = 2;

	/** The agency dao. */
	@Autowired
	private AgencyDAO agencyDAO;

	/** The language data dao. */
	@Autowired
	private LanguageDataDAO languageDataDAO;

	/**
	 * Instantiates a new configuration manager impl.
	 */
	public ConfigurationManagerImpl() {
		super();
	}

	@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
	@Override
	public UserConfiguration getUserConfiguration(final String url,final String locale) {
		final Agency agency = agencyDAO.getAll().get(0);
		Portal usePortal = null;
		final LanguageData languageData = findLanguage(locale);
		for (final Portal portal : agency.getPortals()) {
			if (usePortal == null && PortalType.DEFAULT == portal.getPortalType() || url.contains(portal.getPortalName())) {
				usePortal = portal;
			}
		}

		return new UserConfigurationImpl(agency, usePortal,languageData);
	}

	/**
	 * Find language.
	 *
	 * @param locale
	 *            the locale
	 * @return the language data
	 */
	private LanguageData findLanguage(final String locale) {
		for (final LanguageData languageData : languageDataDAO.getAll()) {
			if (languageData.getLanguageCode().equalsIgnoreCase(locale)) {
				return languageData;
			}
		}
		return null;
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public void createDefaultConfigIfEmpty() {
		if (agencyDAO.getAll().isEmpty()) {
			final List<Portal> portals = new ArrayList<>();
			final Portal defaulPortal = new Portal().withPortalType(PortalType.DEFAULT).withDescription("Global Portal")
					.withPortalName("Default");
			portals.add(defaulPortal);

			final Portal domainPortal = new Portal().withPortalType(PortalType.DOMAIN).withDescription("Hack23.com")
					.withPortalName("www.hack23.com");
			portals.add(domainPortal);

			final Agency agency = new Agency().withAgencyName("Citizen Intelligence Agency")
					.withDescription("Tracking politicians like bugs");
			agency.setPortals(portals);
			agencyDAO.persist(agency);
		}
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public void createDefaultLanguagesIfEmpty() {
		if (languageDataDAO.getAll().isEmpty()) {
			languageDataDAO.persist(getSupportedLocalesLanguageData());

		}
	}

	/**
	 * Gets the supported locales language data.
	 *
	 * @return the supported locales language data
	 */
	private static List<LanguageData> getSupportedLocalesLanguageData() {
		final List<LanguageData> languages = new ArrayList<>();

		for (final Locale locale : DateFormat.getAvailableLocales()) {
			final String localeString = locale.toString().trim();
			if (locale.getDisplayCountry(Locale.ENGLISH).length() == 0 && !StringUtils.isEmpty(localeString)
					&& localeString.length() == EXPECTED_LOCALE_LENGTH) {
				languages.add(new LanguageData().withCreatedDate(new Date()).withLanguageCode(localeString)
						.withLanguageName(locale.getDisplayName(Locale.ENGLISH)).withLanguageEnabled(Boolean.FALSE));

			}
		}

		return languages;
	}

}

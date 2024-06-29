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

import com.hack23.cia.model.internal.application.system.impl.Agency;
import com.hack23.cia.model.internal.application.system.impl.LanguageData;
import com.hack23.cia.model.internal.application.system.impl.Portal;
import com.hack23.cia.service.api.UserConfiguration;

/**
 * The Class UserConfigurationImpl.
 */
final class UserConfigurationImpl implements UserConfiguration {

	/** The agency. */
	private final Agency agency;

	/** The portal. */
	private final Portal portal;

	private final LanguageData languageData;

	/**
	 * Instantiates a new user configuration impl.
	 *
	 * @param agency
	 *            the agency
	 * @param portal
	 *            the portal
	 */
	public UserConfigurationImpl(final Agency agency, final Portal portal, final LanguageData languageData) {
		super();
		this.agency = agency;
		this.portal = portal;
		this.languageData = languageData;
	}

	@Override
	public Agency getAgency() {
		return agency;
	}

	@Override
	public Portal getPortal() {
		return portal;
	}

	@Override
	public LanguageData getLanguage() {
		return languageData;
	}
}

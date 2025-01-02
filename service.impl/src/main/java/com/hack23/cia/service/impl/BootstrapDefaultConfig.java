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

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.api.ConfigurationManager;

/**
 * The Class BootstrapDefaultConfig.
 */
@Component
public final class BootstrapDefaultConfig {

	/** The configuration manager. */
	@Autowired
	private ConfigurationManager configurationManager;

	/**
	 * Instantiates a new bootstrap default config.
	 */
	public BootstrapDefaultConfig() {
		super();
	}

	/**
	 * Creates the default configuration.
	 */
	@PostConstruct
	public void createDefaultConfiguration() {
		configureAuthentication("ROLE_ADMIN");
		configurationManager.createDefaultConfigIfEmpty();
		configurationManager.createDefaultLanguagesIfEmpty();
		clearAuthentication();
	}

	/**
	 * Clear authentication.
	 */
	private static void clearAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	/**
	 * Configure authentication.
	 *
	 * @param role
	 *            the role
	 */
	private static void configureAuthentication(final String role) {
		final Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
		final Authentication authentication = new UsernamePasswordAuthenticationToken("service.impl.BootstrapDefaultConfig", "n/a", authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}

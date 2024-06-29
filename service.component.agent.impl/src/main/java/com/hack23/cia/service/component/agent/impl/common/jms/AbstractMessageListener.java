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
package com.hack23.cia.service.component.agent.impl.common.jms;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The Class AbstractMessageListener.
 */
public abstract class AbstractMessageListener {

	/**
	 * Clear authentication.
	 */
	protected static final void clearAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	/**
	 * Configure authentication.
	 */
	protected static final void configureAuthentication() {
		final Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
		final Authentication authentication = new UsernamePasswordAuthenticationToken("system.agent", "n/a", authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}

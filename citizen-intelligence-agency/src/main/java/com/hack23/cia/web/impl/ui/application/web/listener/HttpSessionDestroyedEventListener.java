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
package com.hack23.cia.web.impl.ui.application.web.listener;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.DestroyApplicationSessionRequest;

/**
 * The Class HttpSessionDestroyedEventListener.
 *
 * @see org.springframework.security.web.session.HttpSessionDestroyedEvent
 */
@Service
public final class HttpSessionDestroyedEventListener implements ApplicationListener<HttpSessionDestroyedEvent> {

	/** The Constant KEY. */
	private static final String KEY = "HttpSessionDestroyedEventListener";

	/** The Constant LOG_MSG_SESSION_DESTROYED_SESSION_ID. */
	private static final String LOG_MSG_SESSION_DESTROYED_SESSION_ID = "Session destroyed SESSION_ID :{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpSessionDestroyedEventListener.class);

	/** The Constant PRINCIPAL. */
	private static final String PRINCIPAL = "AnonymousUser";

	/** The Constant ROLE_ANONYMOUS. */
	private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new http session destroyed event listener.
	 */
	public HttpSessionDestroyedEventListener() {
		super();
	}

	@Override
	public void onApplicationEvent(final HttpSessionDestroyedEvent event) {
		final HttpSession httpSession = event.getSession();
		final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(ROLE_ANONYMOUS));
		final DestroyApplicationSessionRequest destroyApplicationSessionRequest = new DestroyApplicationSessionRequest();
		destroyApplicationSessionRequest.setSessionId(httpSession.getId());

		SecurityContextHolder.getContext()
				.setAuthentication(new AnonymousAuthenticationToken(KEY, PRINCIPAL, authorities));
		applicationManager.service(destroyApplicationSessionRequest);
		SecurityContextHolder.getContext().setAuthentication(null);

		LOGGER.info(LOG_MSG_SESSION_DESTROYED_SESSION_ID, httpSession.getId());
	}

}
/*
 * Copyright 2014 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.DestroyApplicationSessionRequest;

@Service
public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationEventListener.class);

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if (applicationEvent instanceof HttpSessionCreatedEvent) {
			HttpSession httpSession = ((HttpSessionCreatedEvent) applicationEvent).getSession();
			LOGGER.info("Session created SESSION_ID :{}", httpSession.getId());
		} else if (applicationEvent instanceof HttpSessionDestroyedEvent) {
			HttpSession httpSession = ((HttpSessionDestroyedEvent) applicationEvent).getSession();
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
			DestroyApplicationSessionRequest destroyApplicationSessionRequest = new DestroyApplicationSessionRequest();
			destroyApplicationSessionRequest.setSessionId(httpSession.getId());

			SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("key", "principal", authorities));
			applicationManager.service(destroyApplicationSessionRequest);
			SecurityContextHolder.getContext().setAuthentication(null);

			LOGGER.info("Session destroyed SESSION_ID :{}", httpSession.getId());
		} else {
			LOGGER.debug("ApplicationEvent :{}", applicationEvent.toString());
		}
	}
}

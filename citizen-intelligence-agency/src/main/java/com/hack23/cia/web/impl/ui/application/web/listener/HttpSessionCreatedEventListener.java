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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.stereotype.Service;

/**
 * The Class HttpSessionCreatedEventListener.
 *
 * @see HttpSessionCreatedEventEvent
 */
@Service
public final class HttpSessionCreatedEventListener implements ApplicationListener<HttpSessionCreatedEvent> {

	/** The Constant LOG_MSG_SESSION_CREATED_SESSION_ID. */
	private static final String LOG_MSG_SESSION_CREATED_SESSION_ID = "Session created SESSION_ID :{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpSessionCreatedEventListener.class);

	/**
	 * Instantiates a new http session created event listener.
	 */
	public HttpSessionCreatedEventListener() {
		super();
	}

	@Override
	public void onApplicationEvent(final HttpSessionCreatedEvent event) {
		LOGGER.info(LOG_MSG_SESSION_CREATED_SESSION_ID, event.getSession().getId());
	}

}

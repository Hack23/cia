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
package com.hack23.cia.service.api.action.admin;

import com.hack23.cia.service.api.action.common.ServiceRequest;


/**
 * The Class TranslateLanguageContentToLanguageRequest.
 */
public final class TranslateLanguageContentToLanguageRequest implements ServiceRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /** The session id. */
    private String sessionId;

	/** The language id. */
	private Long languageId;


	/**
	 * Instantiates a new translate language content to language request.
	 */
	public TranslateLanguageContentToLanguageRequest() {
		super();
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId
	 *            the new session id
	 */
	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets the language id.
	 *
	 * @return the language id
	 */
	public Long getLanguageId() {
		return languageId;
	}

	/**
	 * Sets the language id.
	 *
	 * @param languageId
	 *            the new language id
	 */
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}


}

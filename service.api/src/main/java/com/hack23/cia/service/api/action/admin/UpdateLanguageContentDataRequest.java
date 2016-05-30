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
 * The Class UpdateLanguageContentDataRequest.
 */
public final class UpdateLanguageContentDataRequest implements ServiceRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /** The session id. */
    private String sessionId;

	/** The language content id. */
	private Long languageContentId;

    /** The language value. */
    private String languageValue;

	/**
	 * Instantiates a new update language content data request.
	 */
	public UpdateLanguageContentDataRequest() {
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
	 * Gets the language content id.
	 *
	 * @return the language content id
	 */
	public Long getLanguageContentId() {
		return languageContentId;
	}

	/**
	 * Sets the language content id.
	 *
	 * @param languageContentId
	 *            the new language content id
	 */
	public void setLanguageContentId(Long languageContentId) {
		this.languageContentId = languageContentId;
	}

	/**
	 * Gets the language value.
	 *
	 * @return the language value
	 */
	public String getLanguageValue() {
		return languageValue;
	}

	/**
	 * Sets the language value.
	 *
	 * @param languageValue
	 *            the new language value
	 */
	public void setLanguageValue(String languageValue) {
		this.languageValue = languageValue;
	}

}

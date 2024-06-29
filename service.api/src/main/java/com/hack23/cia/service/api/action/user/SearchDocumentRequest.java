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
package com.hack23.cia.service.api.action.user;

import javax.validation.constraints.NotNull;

import com.hack23.cia.service.api.action.common.AbstractRequest;

/**
 * The Class SearchDocumentRequest.
 */
public final class SearchDocumentRequest extends AbstractRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session id. */
	@NotNull
	private String sessionId;

	/** The search expression. */
	private String searchExpression;

	/** The max results. */
	private Integer maxResults;

	/**
	 * Instantiates a new search document request.
	 */
	public SearchDocumentRequest() {
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
	 * Gets the search expression.
	 *
	 * @return the search expression
	 */
	public String getSearchExpression() {
		return searchExpression;
	}

	/**
	 * Sets the search expression.
	 *
	 * @param searchExpression
	 *            the new search expression
	 */
	public void setSearchExpression(final String searchExpression) {
		this.searchExpression = searchExpression;
	}

	/**
	 * Gets the max results.
	 *
	 * @return the max results
	 */
	public Integer getMaxResults() {
		return maxResults;
	}

	/**
	 * Sets the max results.
	 *
	 * @param maxResults
	 *            the new max results
	 */
	public void setMaxResults(final Integer maxResults) {
		this.maxResults = maxResults;
	}

}

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
package com.hack23.cia.service.api.action.admin;

import javax.validation.constraints.NotNull;

import com.hack23.cia.service.api.action.common.AbstractRequest;

/**
 * The Class RemoveDataRequest.
 */
public final class RemoveDataRequest extends AbstractRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The Enum DataType.
	 */
	public enum DataType {
		POLITICIAN, DOCUMENTS, APPLICATION_HISTORY;
	}

	/** The session id. */
	@NotNull
	private String sessionId;

	/** The data type. */
	@NotNull
	private DataType dataType;

	/**
	 * Instantiates a new removes the data request.
	 */
	public RemoveDataRequest() {
		super();
	}

	/**
	 * Gets the data type.
	 *
	 * @return the data type
	 */
	public DataType getDataType() {
		return dataType;
	}

	/**
	 * Sets the data type.
	 *
	 * @param dataType
	 *            the new data type
	 */
	public void setDataType(final DataType dataType) {
		this.dataType = dataType;
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

}

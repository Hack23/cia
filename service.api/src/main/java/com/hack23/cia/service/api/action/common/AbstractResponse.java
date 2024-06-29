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
package com.hack23.cia.service.api.action.common;

/**
 * The Class AbstractResponse.
 */
public abstract class AbstractResponse extends AbstractMessage implements ServiceResponse {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The result. */
	private final ServiceResult result;

	/** The error message. */
	private String errorMessage;

	/**
	 * Instantiates a new abstract response.
	 *
	 * @param result
	 *            the result
	 */
	public AbstractResponse(final ServiceResult result) {
		super();
		this.result = result;
	}

	@Override
	public final ServiceResult getResult() {
		return result;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage
	 *            the new error message
	 */
	public final void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public final String getErrorMessage() {
		return errorMessage;
	}
}

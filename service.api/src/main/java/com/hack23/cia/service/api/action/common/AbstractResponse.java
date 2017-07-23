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
package com.hack23.cia.service.api.action.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class AbstractResponse.
 */
public abstract class AbstractResponse implements ServiceResponse {

	/** The result. */
	private final ServiceResult result;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * With error message.
	 *
	 * @param errorMessage
	 *            the error message
	 * @return the service response
	 */
	public ServiceResponse withErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public final boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}

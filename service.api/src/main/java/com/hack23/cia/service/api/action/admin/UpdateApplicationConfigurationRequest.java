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
package com.hack23.cia.service.api.action.admin;

import javax.validation.constraints.NotNull;

import com.hack23.cia.service.api.action.common.AbstractRequest;

/**
 * The Class UpdateApplicationConfigurationRequest.
 */
public final class UpdateApplicationConfigurationRequest extends AbstractRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session id. */
	@NotNull
	private String sessionId;

	/** The application configuration id. */
	private Long applicationConfigurationId;

	/** The config title. */
	private String configTitle;

	/** The config description. */
	private String configDescription;

	/** The component title. */
	private String componentTitle;

	/** The component description. */
	private String componentDescription;

	/** The property value. */
	private String propertyValue;

	/**
	 * Instantiates a new update application configuration request.
	 */
	public UpdateApplicationConfigurationRequest() {
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
	 * Gets the application configuration id.
	 *
	 * @return the application configuration id
	 */
	public Long getApplicationConfigurationId() {
		return applicationConfigurationId;
	}

	/**
	 * Sets the application configuration id.
	 *
	 * @param applicationConfigurationId
	 *            the new application configuration id
	 */
	public void setApplicationConfigurationId(final Long applicationConfigurationId) {
		this.applicationConfigurationId = applicationConfigurationId;
	}

	/**
	 * Gets the config title.
	 *
	 * @return the config title
	 */
	public String getConfigTitle() {
		return configTitle;
	}

	/**
	 * Sets the config title.
	 *
	 * @param configTitle
	 *            the new config title
	 */
	public void setConfigTitle(final String configTitle) {
		this.configTitle = configTitle;
	}

	/**
	 * Gets the config description.
	 *
	 * @return the config description
	 */
	public String getConfigDescription() {
		return configDescription;
	}

	/**
	 * Sets the config description.
	 *
	 * @param configDescription
	 *            the new config description
	 */
	public void setConfigDescription(final String configDescription) {
		this.configDescription = configDescription;
	}

	/**
	 * Gets the component title.
	 *
	 * @return the component title
	 */
	public String getComponentTitle() {
		return componentTitle;
	}

	/**
	 * Sets the component title.
	 *
	 * @param componentTitle
	 *            the new component title
	 */
	public void setComponentTitle(final String componentTitle) {
		this.componentTitle = componentTitle;
	}

	/**
	 * Gets the component description.
	 *
	 * @return the component description
	 */
	public String getComponentDescription() {
		return componentDescription;
	}

	/**
	 * Sets the component description.
	 *
	 * @param componentDescription
	 *            the new component description
	 */
	public void setComponentDescription(final String componentDescription) {
		this.componentDescription = componentDescription;
	}

	/**
	 * Gets the property value.
	 *
	 * @return the property value
	 */
	public String getPropertyValue() {
		return propertyValue;
	}

	/**
	 * Sets the property value.
	 *
	 * @param propertyValue
	 *            the new property value
	 */
	public void setPropertyValue(final String propertyValue) {
		this.propertyValue = propertyValue;
	}

}

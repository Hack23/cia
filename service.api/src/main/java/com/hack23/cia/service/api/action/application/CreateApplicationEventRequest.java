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
package com.hack23.cia.service.api.action.application;

import javax.validation.constraints.NotNull;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.action.common.AbstractRequest;

/**
 * The Class CreateApplicationEventRequest.
 */
public final class CreateApplicationEventRequest extends AbstractRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The event group. */
	private ApplicationEventGroup eventGroup;

	/** The application operation. */
	private ApplicationOperationType applicationOperation;

	/** The page. */
	private String page;

	/** The page mode. */
	private String pageMode;

	/** The element id. */
	private String elementId;

	/** The action name. */
	private String actionName;

	/** The session id. */
	@NotNull
	private String sessionId;

	/** The user id. */
	private String userId;

	/** The error message. */
	private String errorMessage;

	/** The application message. */
	private String applicationMessage;

	/**
	 * Instantiates a new creates the application event request.
	 */
	public CreateApplicationEventRequest() {
		super();
	}

	/**
	 * Gets the event group.
	 *
	 * @return the event group
	 */
	public ApplicationEventGroup getEventGroup() {
		return eventGroup;
	}

	/**
	 * Sets the event group.
	 *
	 * @param eventGroup
	 *            the new event group
	 */
	public void setEventGroup(final ApplicationEventGroup eventGroup) {
		this.eventGroup = eventGroup;
	}

	/**
	 * Gets the application operation.
	 *
	 * @return the application operation
	 */
	public ApplicationOperationType getApplicationOperation() {
		return applicationOperation;
	}

	/**
	 * Sets the application operation.
	 *
	 * @param applicationOperation
	 *            the new application operation
	 */
	public void setApplicationOperation(final ApplicationOperationType applicationOperation) {
		this.applicationOperation = applicationOperation;
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
	 * Gets the page.
	 *
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page
	 *            the new page
	 */
	public void setPage(final String page) {
		this.page = page;
	}

	/**
	 * Gets the page mode.
	 *
	 * @return the page mode
	 */
	public String getPageMode() {
		return pageMode;
	}

	/**
	 * Sets the page mode.
	 *
	 * @param pageMode
	 *            the new page mode
	 */
	public void setPageMode(final String pageMode) {
		this.pageMode = pageMode;
	}

	/**
	 * Gets the element id.
	 *
	 * @return the element id
	 */
	public String getElementId() {
		return elementId;
	}

	/**
	 * Sets the element id.
	 *
	 * @param elementId
	 *            the new element id
	 */
	public void setElementId(final String elementId) {
		this.elementId = elementId;
	}

	/**
	 * Gets the action name.
	 *
	 * @return the action name
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * Sets the action name.
	 *
	 * @param actionName
	 *            the new action name
	 */
	public void setActionName(final String actionName) {
		this.actionName = actionName;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage
	 *            the new error message
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the application message.
	 *
	 * @return the application message
	 */
	public String getApplicationMessage() {
		return applicationMessage;
	}

	/**
	 * Sets the application message.
	 *
	 * @param applicationMessage
	 *            the new application message
	 */
	public void setApplicationMessage(final String applicationMessage) {
		this.applicationMessage = applicationMessage;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

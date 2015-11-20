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
package com.hack23.cia.service.api.action.application;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.action.common.ServiceRequest;

/**
 * The Class CreateApplicationEventRequest.
 */
public class CreateApplicationEventRequest implements ServiceRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session id. */
	private String sessionId;

	/** The event group. */
	private ApplicationEventGroup eventGroup;

	/** The application operation. */
	private ApplicationOperationType applicationOperation;

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
	public void setEventGroup(ApplicationEventGroup eventGroup) {
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
	public void setApplicationOperation(ApplicationOperationType applicationOperation) {
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
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}

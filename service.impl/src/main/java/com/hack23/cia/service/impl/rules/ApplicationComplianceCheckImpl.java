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
package com.hack23.cia.service.impl.rules;

import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;

/**
 * The Class ApplicationComplianceCheckImpl.
 *
 * Compliance check wrapper for application-level security analysis,
 * including brute force attack detection based on failed authentication
 * attempt counts per session.
 */
public final class ApplicationComplianceCheckImpl extends AbstractComplianceCheckImpl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session id. */
	private final String sessionId;

	/** The ip information. */
	private final String ipInformation;

	/** The failed authentication attempts. */
	private final long failedAuthenticationAttempts;

	/** The name. */
	private final String name;

	/**
	 * Instantiates a new application compliance check impl.
	 *
	 * @param sessionId                    the session id
	 * @param ipInformation                the ip information
	 * @param failedAuthenticationAttempts the failed authentication attempts
	 */
	public ApplicationComplianceCheckImpl(final String sessionId, final String ipInformation,
			final long failedAuthenticationAttempts) {
		super(ResourceType.APPLICATION);
		this.sessionId = sessionId;
		this.ipInformation = ipInformation;
		this.failedAuthenticationAttempts = failedAuthenticationAttempts;
		this.name = "Session:" + sessionId + " IP:" + ipInformation;
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
	 * Gets the ip information.
	 *
	 * @return the ip information
	 */
	public String getIpInformation() {
		return ipInformation;
	}

	/**
	 * Gets the failed authentication attempts.
	 *
	 * @return the failed authentication attempts
	 */
	public long getFailedAuthenticationAttempts() {
		return failedAuthenticationAttempts;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getId() {
		return sessionId;
	}

}

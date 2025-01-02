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
package com.hack23.cia.service.impl.action.application.access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hack23.cia.service.impl.action.application.access.LoginBlockedAccess.LoginBlockResult;

/**
 * The Class LoginBlockResultImpl.
 */
final class LoginBlockResultImpl implements LoginBlockResult {

	/** The is blocked. */
	private boolean isBlocked;

	/** The messages. */
	private final List<String> messages = new ArrayList<>();

	/**
	 * Instantiates a new login block result impl.
	 */
	public LoginBlockResultImpl() {
		super();
	}

	@Override
	public boolean isBlocked() {
		return isBlocked;
	}

	/**
	 * Adds the messages.
	 *
	 * @param msg the msg
	 */
	public void addMessages(final String msg) {
		this.messages.add(msg);
	}

	/**
	 * Sets the blocked.
	 *
	 * @param isBlocked the new blocked
	 */
	public void setBlocked(final boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	@Override
	public List<String> getMessages() {
		return Collections.unmodifiableList(messages);
	}

}
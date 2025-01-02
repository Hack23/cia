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
package com.hack23.cia.service.impl.action.application.encryption;

import com.hack23.cia.model.internal.application.user.impl.UserAccount;

/**
 * The Interface VaultManager.
 */
public interface VaultManager {

	/**
	 * Gets the encrypted value.
	 *
	 * @param password
	 *            the password
	 * @param userExist
	 *            the user exist
	 * @return the encrypted value
	 */
	String getEncryptedValue(String password, UserAccount userExist);

	/**
	 * Encrypt value.
	 *
	 * @param password
	 *            the password
	 * @param userId
	 *            the user id
	 * @param value
	 *            the value
	 * @return the string
	 */
	String encryptValue(String password, String userId, String value);

	/**
	 * Decrypt value.
	 *
	 * @param password
	 *            the password
	 * @param userId
	 *            the user id
	 * @param value
	 *            the value
	 * @return the string
	 */
	String decryptValue(String password, String userId, String value);


}

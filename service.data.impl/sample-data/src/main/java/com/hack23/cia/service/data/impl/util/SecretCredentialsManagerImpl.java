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
package com.hack23.cia.service.data.impl.util;

import java.io.IOException;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.secretsmanager.caching.SecretCache;
import com.amazonaws.services.secretsmanager.model.AWSSecretsManagerException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * The Class SecretCredentialsManager.
 */
public class SecretCredentialsManagerImpl implements SecretCredentialsManager {


	/** The Constant FALSE. */
	private static final String FALSE = "false";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SecretCredentialsManagerImpl.class);

	/** The secret name. */
	private final String secretName;

	/** The secret enabled. */
	private final String secretEnabled;

	/** The username. */
	private final String username;

	/** The password. */
	private final String password;

	private SecretCache secretCache;

	/**
	 * Instantiates a new secret credentials manager.
	 *
	 * @param secretName    the secret name
	 * @param secretEnabled the secret enabled
	 * @param username      the username
	 * @param password      the password
	 */
	public SecretCredentialsManagerImpl(final String secretName, final String secretEnabled,final String username, final String password) {
		super();
		this.secretName = secretName;
		this.secretEnabled = secretEnabled;
		this.username = username;
		this.password = password;
	}

	@Override
	public final String getPassword() {
		return getSecretField(SecretData::getPassword,password);
	}

	@Override
	public final String getUsername() {
		return getSecretField(SecretData::getUsername,username);
	}

	private String getSecretField(final Function<SecretData, String> t, final String defaultStr) {
		if (FALSE.equalsIgnoreCase(secretEnabled)) {
			return defaultStr;
		}

		try {
			if (secretCache == null) {
				secretCache = getSecretCache();
			}

	    	final ObjectMapper mapper = new ObjectMapper();
	    	return t.apply(mapper.readValue(secretCache.getSecretString(secretName),SecretData.class));
	    } catch (AWSSecretsManagerException | IOException e) {
	    	LOGGER.error("Problem getting username from secretsmanager using secret:{} :{}:{}", secretName, e.getMessage(),e.getClass().getName());
	    	throw new RuntimeException(e);
	    }
	}

	/**
	 * Gets the secret cache.
	 *
	 * @return the secret cache
	 */
	protected SecretCache getSecretCache() {
		return new SecretCache(	);
	}

}

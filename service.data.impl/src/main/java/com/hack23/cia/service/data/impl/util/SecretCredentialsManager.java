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
package com.hack23.cia.service.data.impl.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.DecryptionFailureException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.InternalServiceErrorException;
import com.amazonaws.services.secretsmanager.model.InvalidParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class SecretCredentialsManager.
 */
public final class SecretCredentialsManager {

	/** The Constant FALSE. */
	private static final String FALSE = "false";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SecretCredentialsManager.class);

	/** The secret name. */
	private final String secretName;
	
	/** The secret enabled. */
	private final String secretEnabled;

	/** The username. */
	private final String username;

	/** The password. */
	private final String password;
	
	/**
	 * Instantiates a new secret credentials manager.
	 *
	 * @param secretName    the secret name
	 * @param secretEnabled the secret enabled
	 * @param username      the username
	 * @param password      the password
	 */
	public SecretCredentialsManager(String secretName, String secretEnabled,String username, String password) {
		super();
		this.secretName = secretName;
		this.secretEnabled = secretEnabled;
		this.username = username;
		this.password = password;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 * @throws Exception the exception
	 */
	public String getPassword() {	   
		if (FALSE.equalsIgnoreCase(secretEnabled)) {
			return password;
		}
	    try {
	    	final AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
                    .withRegion("eu-west-1")
                    .build();	    	
	    	final ObjectMapper mapper = new ObjectMapper();	   	 
	    	return mapper.readValue(client.getSecretValue(new GetSecretValueRequest().withSecretId(secretName)).getSecretString(), UsernamePassword.class).password;	    	
	    } catch (DecryptionFailureException | InternalServiceErrorException | InvalidParameterException | IOException e) {
	    	LOGGER.error("Problem getting password from secretsmanager using secret:" + secretName, e);
	    	throw new RuntimeException(e);
	    }
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 * @throws Exception the exception
	 */
	public String getUsername() {	    
		if (FALSE.equalsIgnoreCase(secretEnabled)) {
			return username;
		}

	    try {
	    	final AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
                    .withRegion("eu-west-1")
                    .build();	    	
	    	final ObjectMapper mapper = new ObjectMapper();	   	 
	    	return mapper.readValue(client.getSecretValue(new GetSecretValueRequest().withSecretId(secretName)).getSecretString(), UsernamePassword.class).username;	    	
	    } catch (DecryptionFailureException | InternalServiceErrorException | InvalidParameterException | IOException e) {
	    	LOGGER.error("Problem getting username from secretsmanager using secret:" + secretName, e);
	    	throw new RuntimeException(e);
	    }
	    
	}

	/**
	 * The Class UsernamePassword.
	 */
	public static class UsernamePassword {
		
		/** The username. */
		private String username;
		
		/** The password. */
		private String password;

		public UsernamePassword() {
			super();
		}

		/**
		 * Instantiates a new username password.
		 *
		 * @param username the username
		 * @param password the password
		 */
		public UsernamePassword(final String username, final String password) {
			super();
			this.username = username;
			this.password = password;
		}

		/**
		 * Gets the username.
		 *
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * Sets the username.
		 *
		 * @param username the new username
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * Gets the password.
		 *
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * Sets the password.
		 *
		 * @param password the new password
		 */
		public void setPassword(String password) {
			this.password = password;
		}
	}
}

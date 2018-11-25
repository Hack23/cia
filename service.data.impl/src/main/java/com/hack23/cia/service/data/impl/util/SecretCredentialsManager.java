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

import com.amazonaws.secretsmanager.caching.SecretCache;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.DecryptionFailureException;
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
	 */
	public String getPassword() {	   
		if (FALSE.equalsIgnoreCase(secretEnabled)) {
			return password;
		}
	    try {
	    	final SecretCache secretCache = new SecretCache(AWSSecretsManagerClientBuilder.standard()
                    .withRegion("eu-west-1"));
	    	final ObjectMapper mapper = new ObjectMapper();	   	 
	    	return mapper.readValue(secretCache.getSecretString(secretName),UsernamePassword.class).password;	    	
	    } catch (DecryptionFailureException | InternalServiceErrorException | InvalidParameterException | IOException e) {
	    	LOGGER.error("Problem getting password from secretsmanager using secret:" + secretName, e);
	    	throw new RuntimeException(e);
	    }
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {	    
		if (FALSE.equalsIgnoreCase(secretEnabled)) {
			return username;
		}

	    try {
	    	final SecretCache secretCache = new SecretCache(AWSSecretsManagerClientBuilder.standard()
                    .withRegion("eu-west-1"));
	    	final ObjectMapper mapper = new ObjectMapper();	   	 
	    	return mapper.readValue(secretCache.getSecretString(secretName),UsernamePassword.class).username;	    	
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
		
		/** The engine. */
		private String engine;
		
		/** The port. */
		private String port;
		
		/** The host. */
		private String host;
		
		/** The dbname. */
		private String dbname;	


		/**
		 * Instantiates a new username password.
		 */
		public UsernamePassword() {
			super();
		}

				
		/**
		 * Instantiates a new username password.
		 *
		 * @param username the username
		 * @param password the password
		 * @param engine   the engine
		 * @param port     the port
		 * @param host     the host
		 * @param dbname   the dbname
		 */
		public UsernamePassword(String username, String password, String engine, String port, String host,
				String dbname) {
			super();
			this.username = username;
			this.password = password;
			this.engine = engine;
			this.port = port;
			this.host = host;
			this.dbname = dbname;
		}



		/**
		 * Gets the engine.
		 *
		 * @return the engine
		 */
		public String getEngine() {
			return engine;
		}

		/**
		 * Sets the engine.
		 *
		 * @param engine the new engine
		 */
		public void setEngine(String engine) {
			this.engine = engine;
		}

		/**
		 * Gets the port.
		 *
		 * @return the port
		 */
		public String getPort() {
			return port;
		}

		/**
		 * Sets the port.
		 *
		 * @param port the new port
		 */
		public void setPort(String port) {
			this.port = port;
		}

		/**
		 * Gets the host.
		 *
		 * @return the host
		 */
		public String getHost() {
			return host;
		}

		/**
		 * Sets the host.
		 *
		 * @param host the new host
		 */
		public void setHost(String host) {
			this.host = host;
		}

		/**
		 * Gets the dbname.
		 *
		 * @return the dbname
		 */
		public String getDbname() {
			return dbname;
		}

		/**
		 * Sets the dbname.
		 *
		 * @param dbname the new dbname
		 */
		public void setDbname(String dbname) {
			this.dbname = dbname;
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

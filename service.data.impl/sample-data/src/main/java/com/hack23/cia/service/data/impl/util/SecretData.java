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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class SecretData.
 */
final class SecretData {

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

	/** The db instance identifier. */
	private String dbInstanceIdentifier;


	/**
	 * Instantiates a new secret data.
	 */
	public SecretData() {
		super();
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
	public void setEngine(final String engine) {
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
	public void setPort(final String port) {
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
	public void setHost(final String host) {
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
	public void setDbname(final String dbname) {
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
	public void setUsername(final String username) {
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
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Gets the db instance identifier.
	 *
	 * @return the db instance identifier
	 */
	public String getDbInstanceIdentifier() {
		return dbInstanceIdentifier;
	}

	/**
	 * Sets the db instance identifier.
	 *
	 * @param dbInstanceIdentifier the new db instance identifier
	 */
	public void setDbInstanceIdentifier(final String dbInstanceIdentifier) {
		this.dbInstanceIdentifier = dbInstanceIdentifier;
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}

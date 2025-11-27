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

import java.sql.SQLException;

import javax.naming.Reference;
import javax.sql.XAConnection;

import org.postgresql.xa.PGXADataSource;

/**
 * The Class SecretPGXADataSource.
 */
public class SecretPGXADataSource extends PGXADataSource {

	/** The secret credentials manager. */
	private final SecretCredentialsManager secretCredentialsManager;

	/**
	 * Instantiates a new secret PGXA data source.
	 *
	 * @param secretCredentialsManager the secret credentials manager
	 */
	public SecretPGXADataSource(final SecretCredentialsManager secretCredentialsManager) {
		super();
		this.secretCredentialsManager = secretCredentialsManager;
	}

	@Override
	public XAConnection getXAConnection() throws SQLException {
		return getXAConnection(secretCredentialsManager.getUsername(), secretCredentialsManager.getPassword());
	}

	@Override
	protected Reference createReference() {
		return new SecretReference(secretCredentialsManager,getClass().getName(), SecretPGXADataSourceFactory.class.getName(), null);
	}


	/**
	 * The Class SecretReference.
	 */
	public static class SecretReference extends Reference {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The secret credentials manager. */
		private final SecretCredentialsManager secretCredentialsManager;

		/**
		 * Instantiates a new secret reference.
		 *
		 * @param secretCredentialsManager the secret credentials manager
		 * @param className                the class name
		 * @param factory                  the factory
		 * @param factoryLocation          the factory location
		 */
		public SecretReference(final SecretCredentialsManager secretCredentialsManager,final String className, final String factory, final String factoryLocation) {
			super(className,factory, factoryLocation);
			this.secretCredentialsManager = secretCredentialsManager;
		}

		/**
		 * Gets the secret credentials manager.
		 *
		 * @return the secret credentials manager
		 */
		public final SecretCredentialsManager getSecretCredentialsManager() {
			return secretCredentialsManager;
		}
	}
}

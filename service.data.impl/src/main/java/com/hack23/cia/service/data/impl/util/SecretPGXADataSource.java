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

import java.sql.SQLException;

import javax.naming.Reference;
import javax.sql.XAConnection;

import org.postgresql.xa.PGXADataSource;

/**
 * The Class SecretPGXADataSource.
 */
public class SecretPGXADataSource extends PGXADataSource {

	private SecretCredentialsManager secretCredentialsManager;
	
	public SecretPGXADataSource(SecretCredentialsManager secretCredentialsManager) {
		super();
		this.secretCredentialsManager = secretCredentialsManager;
	}

	@Override
	public XAConnection getXAConnection() throws SQLException {
		return getXAConnection(secretCredentialsManager.getUsername(), secretCredentialsManager.getPassword());
	}

	@Override
	protected Reference createReference() {
		return new Reference(getClass().getName(), SecretPGXADataSourceFactory.class.getName(), null);
	}
}

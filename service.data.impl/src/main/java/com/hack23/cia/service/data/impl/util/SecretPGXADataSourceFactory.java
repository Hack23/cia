/*
 * Copyright 2010-2024 James Pether Sörling
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

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;

import org.postgresql.xa.PGXADataSourceFactory;

import com.hack23.cia.service.data.impl.util.SecretPGXADataSource.SecretReference;

/**
 * A factory for creating SecretPGXADataSource objects.
 */
public class SecretPGXADataSourceFactory extends PGXADataSourceFactory {

	@Override
	public Object getObjectInstance(final Object obj, final Name name, final Context nameCtx, final Hashtable<?, ?> environment)
			throws Exception {
		return loadSecretXADataSource((Reference) obj);
	}

	/**
	 * Load secret XA data source.
	 *
	 * @param ref the ref
	 * @return the object
	 */
	private Object loadSecretXADataSource(final Reference ref) {
		return loadBaseDataSource(new SecretPGXADataSource(((SecretReference)ref).getSecretCredentialsManager()), ref);
	}
}

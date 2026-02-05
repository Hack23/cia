/*
 * Copyright 2010 -2025 James Pether Sörling
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

package com.hack23.cia.model.external.riksdagen.dokumentlista.impl;

import org.hibernate.search.mapper.pojo.bridge.IdentifierBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeFromDocumentIdentifierContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeToDocumentIdentifierContext;

/**
 * The Class DefaultStringIdentifier.
 */
public final class DefaultStringIdentifier implements IdentifierBridge<String> {

	/**
	 * Instantiates a new default string identifier.
	 */
	public DefaultStringIdentifier() {
		super();
	}


	/**
	 * From document identifier.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @return the string
	 */
	@Override
	public String fromDocumentIdentifier(final String arg0, final IdentifierBridgeFromDocumentIdentifierContext arg1) {
		return arg0;
	}

	/**
	 * To document identifier.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @return the string
	 */
	@Override
	public String toDocumentIdentifier(final String arg0, final IdentifierBridgeToDocumentIdentifierContext arg1) {
		return arg0;
	}

}
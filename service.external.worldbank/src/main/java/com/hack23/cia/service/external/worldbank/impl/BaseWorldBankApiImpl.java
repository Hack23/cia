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
package com.hack23.cia.service.external.worldbank.impl;

import com.hack23.cia.service.external.common.api.XmlAgent;

/**
 * The Class BaseWorldBankApiImpl.
 */
class BaseWorldBankApiImpl {

	/** The Constant XMLNS_WB_HTTP_WWW_WORLDBANK_ORG. */
	protected static final String XMLNS_WB_HTTP_WWW_WORLDBANK_ORG = "xmlns:wb=\"http://www.worldbank.org\"";

	/** The xml agent. */
	private final XmlAgent xmlAgent;

	/**
	 * Instantiates a new base world bank api impl.
	 */
	BaseWorldBankApiImpl(final XmlAgent xmlAgent) {
		super();
		this.xmlAgent = xmlAgent;
	}

	/**
	 * Gets the xml agent.
	 *
	 * @return the xml agent
	 */
	protected final XmlAgent getXmlAgent() {
		return xmlAgent;
	}

}

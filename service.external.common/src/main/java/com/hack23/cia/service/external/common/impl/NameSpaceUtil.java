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
package com.hack23.cia.service.external.common.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Source;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderSAX2Factory;
import org.jdom2.transform.JDOMSource;

/**
 * The Class NameSpaceUtil.
 */
final class NameSpaceUtil {

	/**
	 * Instantiates a new name space util.
	 */
	private NameSpaceUtil() {
		super();
	}

	/**
	 * Sets the name space on xml stream.
	 *
	 * @param in        the in
	 * @param nameSpace the name space
	 * @return the source
	 * @throws JDOMException the JDOM exception
	 * @throws IOException   Signals that an I/O exception has occurred.
	 */
	public static Source setNameSpaceOnXmlStream(final InputStream in, final String nameSpace)
			throws JDOMException, IOException {
		final SAXBuilder sb = new SAXBuilder(new XMLReaderSAX2Factory(false));
		sb.setExpandEntities(false);
		sb.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		final Document doc = sb.build(in);
		doc.getRootElement().setNamespace(Namespace.getNamespace(nameSpace));
		return new JDOMSource(doc);
	}

}

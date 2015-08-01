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
 *	$Id: XmlAgentImpl.java 6119 2015-07-31 17:44:12Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.external.common/src/main/java/com/hack23/cia/service/external/common/impl/XmlAgentImpl.java $
 */
package com.hack23.cia.service.external.common.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.jdom2.Document;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderSAX2Factory;
import org.jdom2.transform.JDOMSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.external.common.api.XmlAgent;

/**
 * The Class AbstractXmlAgentImpl.
 */
@Service
public final class XmlAgentImpl implements XmlAgent {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(XmlAgentImpl.class);

	/**
	 * Instantiates a new abstract xml agent impl.
	 */
	public XmlAgentImpl() {
		super();
	}

	/**
	 * Read with string buffer.
	 *
	 * @param fr
	 *            the fr
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static String readWithStringBuffer(final Reader fr) throws IOException {

		final BufferedReader br = new BufferedReader(fr);
		String line;
		final StringBuilder result = new StringBuilder();
		while ((line = br.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}

	/**
	 * Retrive content.
	 *
	 * @param accessUrl
	 *            the access url
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public String retriveContent(final String accessUrl) throws Exception {
		final URL url = new URL(accessUrl.replace(" ",""));

		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(
				url.openStream(),"UTF-8"));

		return readWithStringBuffer(inputStream);
	}

	/**
	 * Sets the name space on xml stream.
	 *
	 * @param in
	 *            the in
	 * @param nameSpace
	 *            the name space
	 * @return the source
	 * @throws Exception
	 *             the exception
	 */
	private static Source setNameSpaceOnXmlStream(final InputStream in, final String nameSpace)
			throws Exception {
		final SAXBuilder sb = new SAXBuilder(new XMLReaderSAX2Factory(false));
		final Document doc = sb.build(in);
		doc.getRootElement().setNamespace(Namespace.getNamespace(nameSpace));
		return new JDOMSource(doc);
	}


	/**
	 * Unmarshall xml.
	 *
	 * @param unmarshaller
	 *            the unmarshaller
	 * @param accessUrl
	 *            the access url
	 * @return the object
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public Object unmarshallXml(final Unmarshaller unmarshaller, final String accessUrl) throws Exception {
		return unmarshallXml(unmarshaller, accessUrl,null,null,null);
	}

	/**
	 * Unmarshall xml.
	 *
	 * @param unmarshaller
	 *            the unmarshaller
	 * @param accessUrl
	 *            the access url
	 * @param nameSpace
	 *            the name space
	 * @param replace
	 *            the replace
	 * @param with
	 *            the with
	 * @return the object
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public Object unmarshallXml(final Unmarshaller unmarshaller, final String accessUrl,
			final String nameSpace,final String replace, final String with) throws Exception {

		LOGGER.info("Calls {}", accessUrl);
		final URL url = new URL(accessUrl.replace(" ",""));

		final URLConnection connection = url.openConnection();

		InputStream stream = connection.getInputStream();

		if ("gzip".equals(connection.getContentEncoding())) {
			stream = new GZIPInputStream(stream);
		}

		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(
				stream));

		String xmlContent = readWithStringBuffer(inputStream);

		if (replace != null) {
			xmlContent = xmlContent.replace(replace, with);
		}

		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				xmlContent.getBytes("UTF-8"));

		Source source;
		if (nameSpace != null) {
			source = setNameSpaceOnXmlStream(byteArrayInputStream, nameSpace);
		} else {
			source = new StreamSource(byteArrayInputStream);
		}

		return unmarshaller.unmarshal(source);
	}
}

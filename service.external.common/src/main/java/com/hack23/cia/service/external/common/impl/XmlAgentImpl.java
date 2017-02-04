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
package com.hack23.cia.service.external.common.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.client.fluent.Request;
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
 * The Class XmlAgentImpl.
 */
@Service
public final class XmlAgentImpl implements XmlAgent {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(XmlAgentImpl.class);

	/**
	 * Instantiates a new xml agent impl.
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

	@Override
	public String retriveContent(final String accessUrl) throws Exception {
		final URL url = new URL(accessUrl.replace(" ",""));

		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(
				url.openStream(),StandardCharsets.UTF_8));

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


	@Override
	public Object unmarshallXml(final Unmarshaller unmarshaller, final String accessUrl) throws Exception {
		return unmarshallXml(unmarshaller, accessUrl,null,null,null);
	}

	@Override
	public Object unmarshallXml(final Unmarshaller unmarshaller, final String accessUrl,
			final String nameSpace,final String replace, final String with) throws Exception {

		LOGGER.info("Calls {}", accessUrl);

		final boolean isWeb = accessUrl.toLowerCase().startsWith("http://") || accessUrl.toLowerCase().startsWith("https://");

		String xmlContent;
		if (isWeb) {
			xmlContent = Request.Get(accessUrl.replace(" ","")).execute().returnContent().asString(StandardCharsets.UTF_8);
		} else {
			xmlContent = readInputStream(accessUrl.replace(" ",""));
		}

		if (replace != null) {
			xmlContent = xmlContent.replace(replace, with);
		}

		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				xmlContent.getBytes(StandardCharsets.UTF_8));

		Source source;
		if (nameSpace != null) {
			source = setNameSpaceOnXmlStream(byteArrayInputStream, nameSpace);
		} else {
			source = new StreamSource(byteArrayInputStream);
		}

		return unmarshaller.unmarshal(source);
	}

	/**
	 * Read input stream.
	 *
	 * @param accessUrl
	 *            the access url
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	private static String readInputStream(final String accessUrl) throws Exception{
		final URL url = new URL(accessUrl.replace(" ",""));

		final URLConnection connection = url.openConnection();

		final InputStream stream = connection.getInputStream();


		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(
				stream,StandardCharsets.UTF_8));

		return readWithStringBuffer(inputStream);
	}
}

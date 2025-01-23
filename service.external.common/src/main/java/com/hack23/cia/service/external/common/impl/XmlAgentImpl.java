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
package com.hack23.cia.service.external.common.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.client.fluent.Request;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;

/**
 * The Class XmlAgentImpl.
 */
@Service
final class XmlAgentImpl implements XmlAgent {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlAgentImpl.class);

	/**
	 * Instantiates a new xml agent impl.
	 */
	public XmlAgentImpl() {
		super();
	}


	@Override
	public String retriveContent(final String accessUrl) throws XmlAgentException {
		try {
			return new String(readInputStreamAsBytes(accessUrl), StandardCharsets.UTF_8);
		} catch (final IOException e) {
			throw new XmlAgentException(e);
		}
	}


	@Override
	public Object unmarshallXml(final Unmarshaller unmarshaller, final String accessUrl) throws XmlAgentException {
		return unmarshallXml(unmarshaller, accessUrl, null, null, null);
	}

	@Override
	public Object unmarshallXml(final Unmarshaller unmarshaller, final String accessUrl, final String nameSpace,
			final String replace, final String with) throws XmlAgentException {

		try {
			LOGGER.info("Calls {}", accessUrl);

			final boolean isWeb = accessUrl.toLowerCase(Locale.ENGLISH).startsWith("http://")
					|| accessUrl.toLowerCase(Locale.ENGLISH).startsWith("https://");

			byte[] xmlContent;
			if (isWeb) {
				xmlContent = Request.Get(accessUrl).execute().returnContent().asBytes();
			} else {
				xmlContent = readInputStreamAsBytes(accessUrl);
			}

			if (replace != null) {
				final String xmlContentReplaced = new String(xmlContent, StandardCharsets.UTF_8).replace(replace, with);

				xmlContent = xmlContentReplaced.getBytes(StandardCharsets.UTF_8);
			}

			final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlContent);

			Source source;
			if (nameSpace != null) {
				source = NameSpaceUtil.setNameSpaceOnXmlStream(byteArrayInputStream, nameSpace);
			} else {
				source = new StreamSource(byteArrayInputStream);
			}

			return unmarshaller.unmarshal(source);
		} catch (IOException | JDOMException e) {
			throw new XmlAgentException(e);
		}
	}



	/**
	 * Read input stream as bytes.
	 *
	 * @param accessUrl the access url
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static byte[] readInputStreamAsBytes(final String accessUrl) throws IOException {
	    final URL url = URI.create(accessUrl).toURL();

	    final URLConnection connection = url.openConnection();
	    final int contentLength = connection.getContentLength();

	    try (InputStream inputStream = connection.getInputStream();
	         ByteArrayOutputStream outputStream =
	             contentLength > 0 ? new ByteArrayOutputStream(contentLength)
	                               : new ByteArrayOutputStream()) {

	        inputStream.transferTo(outputStream);

	        return outputStream.toByteArray();
	    }
	}
}

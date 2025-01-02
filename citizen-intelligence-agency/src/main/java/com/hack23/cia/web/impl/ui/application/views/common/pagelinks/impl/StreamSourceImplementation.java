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
package com.hack23.cia.web.impl.ui.application.views.common.pagelinks.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.StreamResource;

/**
 * The Class StreamSourceImplementation.
 */
public final class StreamSourceImplementation implements StreamResource.StreamSource {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamSourceImplementation.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The url. */
	private final String url;

	/**
	 * Instantiates a new stream source implementation.
	 *
	 * @param url the url
	 */
	public StreamSourceImplementation(final String url) {
		this.url = url;
	}

	@Override
	public InputStream getStream() {
		try {
			return URI.create(url).toURL().openStream();
		} catch (final Exception e) {
			LOGGER.warn("Problem getting url :"+ url +" error:" + e.getClass().getCanonicalName());
			return new ByteArrayInputStream(new byte[0]);
		}
	}
}

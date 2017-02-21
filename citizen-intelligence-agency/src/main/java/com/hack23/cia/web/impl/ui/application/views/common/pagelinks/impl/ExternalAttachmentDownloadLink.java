/*
 * Copyright 2014 James Pether Sörling
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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.StreamResource;
import com.vaadin.ui.Link;

/**
 * The Class ExternalAttachmentDownloadLink.
 */
public final class ExternalAttachmentDownloadLink extends Link {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExternalAttachmentDownloadLink.class);

	/** The file name. */
	private final String fileName;

	/** The file type. */
	private final String fileType;

	/** The file url. */
	private final String fileUrl;

	/**
	 * Instantiates a new external attachment download link.
	 *
	 * @param fileName
	 *            the file name
	 * @param fileType
	 *            the file type
	 * @param fileUrl
	 *            the file url
	 */
	public ExternalAttachmentDownloadLink(final String fileName, final String fileType, final String fileUrl) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		setCaption(fileName);
		setDescription("Download " + fileName);
		setTargetName("_blank");
	}

	@Override
	public void attach() {
		super.attach();

		final StreamResource.StreamSource source = new StreamResource.StreamSource() {

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			public InputStream getStream() {

				try {
					return new URL(fileUrl).openStream();
				} catch (final IOException e) {
					LOGGER.warn("Problem opening url:"+ fileUrl,e);
					return new ByteArrayInputStream(new byte[0]);
				}
			}
		};

		final StreamResource resource = new StreamResource(source, fileName);

		resource.getStream().setParameter("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		resource.setMIMEType("application/" + fileType);
		resource.setCacheTime(0);

		setResource(resource);
	}
}

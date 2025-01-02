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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentType;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;

/**
 * The Class RiksdagenDocumentStatusWorkGeneratorImpl.
 */
@Service("RiksdagenDocumentStatusWorkGeneratorImpl")
final class RiksdagenDocumentStatusWorkGeneratorImpl extends AbstractRiksdagenDataSourcesWorkGenerator {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenDocumentStatusWorkGeneratorImpl.class);

	/** The Constant RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT. */
	private static final String RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** The document status container workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer")
	private Destination documentStatusContainerWorkdestination;


	/**
	 * Instantiates a new riksdagen document status work generator impl.
	 */
	public RiksdagenDocumentStatusWorkGeneratorImpl() {
		super(RiksdagenDataSources.DOCUMENT_STATUS);
	}

	@Override
	public void generateWorkOrders() {
		try {
			final Map<String, String> documentStatusContainerMap = getImportService()
					.getDocumentStatusContainerMap();


			final List<DocumentType> selectedDocumentTypes = new ArrayList<>();
			selectedDocumentTypes.add(DocumentType.BET);
			selectedDocumentTypes.add(DocumentType.PROP);
			selectedDocumentTypes.add(DocumentType.MOT);



			final Map<String, String> documentElementMap = getImportService()
					.getDocumentElementMap(
							new SimpleDateFormat(
									RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT,Locale.ENGLISH).parse("1999-12-30 00:00:00"),
							selectedDocumentTypes, false);

			final Set<String> avaibleDocumentStatus = documentElementMap
					.keySet();

			for (final String id : avaibleDocumentStatus) {
				if (!documentStatusContainerMap.containsKey(id)) {
					getJmsSender().send(documentStatusContainerWorkdestination,
							id);
				}
			}

			for (final DocumentStatusContainer container : getImportService().getNoneCompletedDocumentStatusCommitteeReports()) {
				 if ("planerat".equals(container.getDocument().getStatus())) {
					 getJmsSender().send(documentStatusContainerWorkdestination,
							 container.getDocument().getId());
				 }
			}

		} catch (final ParseException e) {
			LOGGER.warn("Loading document status ", e);
		}
	}

}

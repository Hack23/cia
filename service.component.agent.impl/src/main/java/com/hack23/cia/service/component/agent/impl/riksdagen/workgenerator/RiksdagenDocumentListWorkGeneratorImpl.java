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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator;

import java.util.Map;

import javax.jms.Destination;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.service.external.common.api.ProcessDataStrategy;

/**
 * The Class RiksdagenDocumentListWorkGeneratorImpl.
 */
@Service
public final class RiksdagenDocumentListWorkGeneratorImpl extends AbstractRiksdagenDataSourcesWorkGenerator {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenDocumentListWorkGeneratorImpl.class);

	/** The document element workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement")
	private Destination documentElementWorkdestination;

	/**
	 * Instantiates a new riksdagen document list work generator impl.
	 */
	public RiksdagenDocumentListWorkGeneratorImpl() {
		super(RiksdagenDataSources.DOCUMENT_LIST);
	}

	@Override
	public void generateWorkOrders() {
		try {

			final int startYearForDocumentElement = getImportService().getStartYearForDocumentElement();

			final org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTime fromDateTime = fmt.parseDateTime(startYearForDocumentElement+ "-01-01");

			DateTime loadedWeekDate = fmt.parseDateTime(startYearForDocumentElement+ "-01-01");

			final DateTime toDate = new DateTime();
			while (loadedWeekDate.isBefore(toDate)) {
				loadedWeekDate = loadedWeekDate.plusWeeks(1);

				getRiksdagenApi().processDocumentList(fmt.print(fromDateTime),fmt.print(loadedWeekDate),new DocumentElementWorkProducer());
				fromDateTime=fromDateTime.plusWeeks(1);

			}

		} catch (final Exception e) {
			LOGGER.warn("error loading documents", e);
		}
	}

	/**
	 * The Class DocumentElementWorkProducer.
	 */
	private class DocumentElementWorkProducer implements
	ProcessDataStrategy<DocumentElement> {

		/** The document element map. */
		private final Map<String, String> documentElementMap = getImportService()
				.getDocumentElementMap();

		@Override
		public void process(final DocumentElement t) {
			try {
				if (!documentElementMap.containsKey(t.getId())) {
					sendMessage(documentElementWorkdestination,
							t);
				}
			} catch (final Exception e) {
				LOGGER.warn("Error proccessing documentElement",e);
			}
		}
	}

}

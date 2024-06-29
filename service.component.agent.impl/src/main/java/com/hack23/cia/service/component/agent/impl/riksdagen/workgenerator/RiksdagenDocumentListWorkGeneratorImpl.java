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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator;

import javax.jms.Destination;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.service.component.agent.impl.riksdagen.workers.LoadDocumentWork;

/**
 * The Class RiksdagenDocumentListWorkGeneratorImpl.
 */
@Service("RiksdagenDocumentListWorkGeneratorImpl")
final class RiksdagenDocumentListWorkGeneratorImpl extends AbstractRiksdagenDataSourcesWorkGenerator {

	/** The load document workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.service.component.agent.impl.riksdagen.workers.LoadDocumentWork")
	private Destination loadDocumentWorkdestination;

	/**
	 * Instantiates a new riksdagen document list work generator impl.
	 */
	public RiksdagenDocumentListWorkGeneratorImpl() {
		super(RiksdagenDataSources.DOCUMENT_LIST);
	}

	@Override
	public void generateWorkOrders() {
		final int startYearForDocumentElement = getImportService().getStartYearForDocumentElement();

		final org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime fromDateTime = fmt.parseDateTime(startYearForDocumentElement + "-01-01");

		DateTime loadedWeekDate = fmt.parseDateTime(startYearForDocumentElement + "-01-01");

		final DateTime toDate = new DateTime();
		while (loadedWeekDate.isBefore(toDate)) {
			loadedWeekDate = loadedWeekDate.plusWeeks(1);

			getJmsSender().send(loadDocumentWorkdestination,
					new LoadDocumentWork(fmt.print(fromDateTime), fmt.print(loadedWeekDate)));
			fromDateTime = fromDateTime.plusWeeks(1);
		}
	}

}

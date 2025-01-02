/*
 * Copyright 2010 -2025 James Pether Sörling
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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentType;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;

/**
 * The Class RiksdagenImportServiceITest.
 */
@Transactional
public class RiksdagenImportServiceITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The Constant DEFAULT_START_LOAD_YEAR. */
	private static final int DEFAULT_START_LOAD_YEAR = 2000;

	/** The riksdagen import service. */
	@Autowired
	private RiksdagenImportService riksdagenImportService;

	/**
	 * Gets the document element map test.
	 *
	 * @return the document element map test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getDocumentElementMapAfterDateForTypesTest() throws Exception {
		final List<DocumentType> selectedDocumentTypes = new ArrayList<>();
		selectedDocumentTypes.add(DocumentType.BET);
		selectedDocumentTypes.add(DocumentType.PROP);
		selectedDocumentTypes.add(DocumentType.MOT);

		final Map<String, String> documentElementMap = riksdagenImportService.getDocumentElementMap(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse("2016-02-02 00:00:00"),
				selectedDocumentTypes, false);

		assertNotNull("Expect a result",documentElementMap);
	}

	/**
	 * Gets the start year for document element test.
	 *
	 * @return the start year for document element test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getStartYearForDocumentElementTest() throws Exception {
		assertTrue("Expect at least default start year",riksdagenImportService.getStartYearForDocumentElement() >=DEFAULT_START_LOAD_YEAR);
	}

	/**
	 * Gets the none completed document status committee reports test.
	 *
	 * @return the none completed document status committee reports test
	 * @throws Exception the exception
	 */
	@Test
	public void getNoneCompletedDocumentStatusCommitteeReportsTest() throws Exception {
		final List<DocumentStatusContainer> reports = riksdagenImportService.getNoneCompletedDocumentStatusCommitteeReports();
		assertNotNull(reports);
		assertFalse(reports.isEmpty());
	}

}

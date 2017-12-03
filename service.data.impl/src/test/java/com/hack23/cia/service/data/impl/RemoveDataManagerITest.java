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
package com.hack23.cia.service.data.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalData;
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.service.data.api.DataViewer;
import com.hack23.cia.service.data.api.RemoveDataManager;

/**
 * The Class RemoveDataManagerITest.
 */
public final class RemoveDataManagerITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The remove data manager. */
	@Autowired
	private RemoveDataManager removeDataManager;

	/** The data viewer. */
	@Autowired
	private DataViewer dataViewer;


	/**
	 * Removes the person data test.
	 */
	@Test
	public void removePersonDataTest() {
		removeDataManager.removePersonData();
		assertEquals("Expect no data",0,dataViewer.getAll(PersonData.class).size());

	}

	/**
	 * Removes the committee proposals test.
	 */
	void removeCommitteeProposalsTest() {
		removeDataManager.removeCommitteeProposals();
		assertEquals("Expect no data",0,dataViewer.getAll(CommitteeProposalData.class).size());
	}

	/**
	 * Removes the document status test.
	 */
	void removeDocumentStatusTest() {
		removeDataManager.removeDocumentStatus();
		assertEquals("Expect no data",0,dataViewer.getAll(DocumentStatusContainer.class).size());

	}

	/**
	 * Removes the documents test.
	 */
	void removeDocumentsTest() {
		removeDataManager.removeDocuments();
		assertEquals("Expect no data",0,dataViewer.getAll(DocumentElement.class).size());
		assertEquals("Expect no data",0,dataViewer.getAll(DocumentContentData.class).size());
	}

	/**
	 * Removes the application history test.
	 */
	void removeApplicationHistoryTest() {
		removeDataManager.removeApplicationHistory();
		assertEquals("Expect no data",0,dataViewer.getAll(ApplicationActionEvent.class).size());
		assertEquals("Expect no data",0,dataViewer.getAll(ApplicationSession.class).size());

	}

}

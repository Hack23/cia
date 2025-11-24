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
package com.hack23.cia.service.external.riksdagen.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentPersonReferenceData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentType;
import com.hack23.cia.service.external.common.api.ProcessDataStrategy;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenDocumentApi;

/**
 * The Class RiksdagenDocumentApiImplITest.
 */
public final class RiksdagenDocumentApiImplITest extends
AbstractRiksdagenFunctionalIntegrationTest {

	/** The riksdagen api. */
	@Autowired
	private RiksdagenDocumentApi riksdagenApi;


	/**
	 * Gets the document content.
	 *
	 * @return the document content
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getDocumentContent() throws Exception {
		final DocumentContentData documentContent = riksdagenApi
				.getDocumentContent("GX11916");
		assertNotNull(documentContent);
		// Note: Original assertion "till statsrådet Cristina Husmark Pehrsson" updated to match
		// current document format which uses "Cristina Husmark Pehrsson (m), statsråd"
		assertTrue(documentContent.getContent().contains(
				"Cristina Husmark Pehrsson"));

		final DocumentContentData documentContent2 = riksdagenApi
				.getDocumentContent("GVA3FöU43");
		assertNotNull(documentContent2);
		assertTrue(documentContent2.getContent().contains(
				"Försvarsutskottet"));
	}

	/**
	 * Gets the document content with Swedish characters test.
	 * Tests that document IDs containing Swedish special characters (å, ä, ö)
	 * are properly URL-encoded when making HTTP requests to data.riksdagen.se.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void getDocumentContentWithSwedishCharactersTest() throws Exception {
		// Test with ö character - from production error logs
		final DocumentContentData documentContent1 = riksdagenApi
				.getDocumentContent("hca3föu34");
		assertNotNull(documentContent1);
		assertNotNull(documentContent1.getContent());
		assertTrue(documentContent1.getContent().length() > 0);
		// Test with another document ID containing ö
		final DocumentContentData documentContent2 = riksdagenApi
				.getDocumentContent("hc19föu6p2");
		assertNotNull(documentContent2);
		assertNotNull(documentContent2.getContent());
		assertTrue(documentContent2.getContent().length() > 0);
	}

	/**
	 * Gets the document content failure test.
	 *
	 * @return the document content failure test
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected =  DataFailureException.class)
	public void getDocumentContentFailureTest() throws Exception {
		new RiksdagenDocumentApiImpl(createMockXmlAgentThrowsException()).getDocumentContent("GX11916");
	}

	/**
	 * Gets the document list by changed since.
	 *
	 * @return the document list by changed since
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getDocumentListByChangedSince() throws Exception {
		final List<DocumentElement> dokumentList = riksdagenApi.getDocumentList(
				"2010-06-01","2010-09-01", 10);
		assertNotNull(dokumentList);
		assertTrue(dokumentList.size() >= 1);
	}

	/**
	 * Gets the document list by changed since failure.
	 *
	 * @return the document list by changed since failure
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected =  DataFailureException.class)
	public void getDocumentListByChangedSinceFailure() throws Exception {
		new RiksdagenDocumentApiImpl(createMockXmlAgentThrowsException()).getDocumentList(
				"2010-06-01","2010-09-01", 10);
	}



	/**
	 * Gets the document list by type.
	 *
	 * @return the document list by type
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getDocumentListByType() throws Exception {
		final List<DocumentElement> dokumentList = riksdagenApi.getDocumentList(
				DocumentType.BET, 10);
		assertNotNull(dokumentList);
		assertTrue(dokumentList.size() >= 100);
	}

	/**
	 * Gets the document list by type failure.
	 *
	 * @return the document list by type failure
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected =  DataFailureException.class)
	public void getDocumentListByTypeFailure() throws Exception {
		new RiksdagenDocumentApiImpl(createMockXmlAgentThrowsException()).getDocumentList(
				DocumentType.BET, 10);
	}

	/**
	 * Gets the document list by year test.
	 *
	 * @return the document list by year test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getDocumentListByYearTest() throws Exception {
		final List<DocumentElement> dokumentList = riksdagenApi.getDocumentList(2010,
				10);
		assertNotNull(dokumentList);
		assertTrue(dokumentList.size() >= 100);
	}


	/**
	 * Gets the document list by year failure test.
	 *
	 * @return the document list by year failure test
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected =  DataFailureException.class)
	public void getDocumentListByYearFailureTest() throws Exception {
		new RiksdagenDocumentApiImpl(createMockXmlAgentThrowsException()).getDocumentList(2010,10);
	}

	/**
	 * Gets the document status test.
	 *
	 * @return the document status test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getDocumentStatusTest() throws Exception {
		DocumentStatusContainer dokumentstatus = riksdagenApi
				.getDocumentStatus("GU01JuU11");
		assertNotNull(dokumentstatus);
		assertNotNull(dokumentstatus.getDocument().getMadePublicDate());
		assertNotNull(dokumentstatus.getDocument().getHangarId());

		assertEquals(DocumentType.BET.value(), dokumentstatus.getDocument().getDocumentType());
		assertTrue(dokumentstatus.getDocumentReferenceContainer().getDocumentReferenceList().size()>0);
		assertTrue(dokumentstatus.getDocumentDetailContainer().getDocumentDetailList().size()>0);

		dokumentstatus = riksdagenApi
				.getDocumentStatus("GQ02So321");
		assertNotNull(dokumentstatus);
		assertNotNull(dokumentstatus.getDocument().getMadePublicDate());
		assertNotNull(dokumentstatus.getDocument().getHangarId());
		assertEquals(DocumentType.MOT.value(), dokumentstatus.getDocument().getDocumentType());

		assertEquals("Avslag", dokumentstatus.getDocumentProposal().getProposal().getChamber());
		assertEquals(" Avslag", dokumentstatus.getDocumentProposal().getProposal().getCommittee());
		assertEquals("2003/04:SoU7", dokumentstatus.getDocumentProposal().getProposal().getProcessedIn());
		assertEquals("Röstning", dokumentstatus.getDocumentProposal().getProposal().getDecisionType());
		assertEquals("0540190357911", dokumentstatus.getDocumentPersonReferenceContainer().getDocumentPersonReferenceList().get(0).getPersonReferenceId());
		assertTrue(dokumentstatus.getDocumentDetailContainer().getDocumentDetailList().size()>0);


		dokumentstatus = riksdagenApi.getDocumentStatus("GVA3FöU43");
		assertNotNull(dokumentstatus);

		dokumentstatus = riksdagenApi
				.getDocumentStatus("GX02Ub453");
		assertNotNull(dokumentstatus);
		assertNotNull(dokumentstatus.getDocument().getMadePublicDate());
		assertNotNull(dokumentstatus.getDocument().getHangarId());
		assertEquals(DocumentType.MOT.value(), dokumentstatus.getDocument().getDocumentType());
		assertTrue(dokumentstatus.getDocumentDetailContainer().getDocumentDetailList().size()>0);
		assertEquals(1,dokumentstatus.getDocumentPersonReferenceContainer().getDocumentPersonReferenceList().size());

		final DocumentPersonReferenceData documentPersonReferenceData = dokumentstatus.getDocumentPersonReferenceContainer().getDocumentPersonReferenceList().iterator().next();
		assertEquals("Thomas Bodström",documentPersonReferenceData.getReferenceName());

	}

	/**
	 * Gets the document status failure test.
	 *
	 * @return the document status failure test
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected =  DataFailureException.class)
	public void getDocumentStatusFailureTest() throws Exception {
		new RiksdagenDocumentApiImpl(createMockXmlAgentThrowsException()).getDocumentStatus("GU01JuU11");
	}


	/**
	 * Process document list by changed since.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void processDocumentListByChangedSince() throws Exception {
		final List<DocumentElement> dokumentList = new ArrayList<>();

		final ProcessDataStrategy<DocumentElement> strategy = new ProcessDataStrategy<DocumentElement>() {
			@Override
			public void process(final DocumentElement t) {
				dokumentList.add(t);
			}
		};

		riksdagenApi.processDocumentList("2010-06-01","2010-09-01", strategy);
		assertNotNull(dokumentList);
		assertTrue(dokumentList.size() >= 1 && dokumentList.size() != 10000);
	}


	/**
	 * Process document list by changed since failure.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected =  DataFailureException.class)
	public void processDocumentListByChangedSinceFailure() throws Exception {
		final List<DocumentElement> dokumentList = new ArrayList<>();

		final ProcessDataStrategy<DocumentElement> strategy = new ProcessDataStrategy<DocumentElement>() {
			@Override
			public void process(final DocumentElement t) {
				dokumentList.add(t);
			}
		};

		new RiksdagenDocumentApiImpl(createMockXmlAgentThrowsException()).processDocumentList("2010-06-01","2010-09-01", strategy);
	}

}

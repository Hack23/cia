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
 *	$Id: RiksdagenApiImplTest.java 6119 2015-07-31 17:44:12Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.external.riksdagen/src/test/java/com/hack23/cia/service/external/riksdagen/impl/RiksdagenApiImplTest.java $
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
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.external.riksdagen.personlista.impl.PersonContainerElement;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;
import com.hack23.cia.model.external.riksdagen.voteringlista.impl.BallotDocumentElement;
import com.hack23.cia.service.external.common.api.ProcessDataStrategy;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenApi;

/**
 * The Class RiksdagenApiImplTest.
 */
public class RiksdagenApiImplTest extends
AbstractRiksdagenFunctionalIntegrationTest {

	/** The riksdagen api. */
	@Autowired
	RiksdagenApi riksdagenApi;

	/**
	 * Gets the ballot test.
	 *
	 * @return the ballot test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getBallotTest() throws Exception {
		final List<VoteData> votering = riksdagenApi
				.getBallot("E0B34EE1-3FD3-474A-81D7-781B04BB241E");
		assertNotNull(votering);
		final VoteData voteData = votering.iterator().next();
		assertNotNull(voteData.getEmbeddedId());
		assertNotNull(voteData.getEmbeddedId().getBallotId());
		assertNotNull(voteData.getEmbeddedId().getIntressentId());
		assertNotNull(voteData.getEmbeddedId().getIssue());
	}

	/**
	 * Gets the ballot list test.
	 *
	 * @return the ballot list test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getBallotListTest() throws Exception {
		final List<BallotDocumentElement> ballotList = riksdagenApi
				.getBallotList();
		assertNotNull(ballotList);

		assertTrue(ballotList.size() >= 7888);

	}


	/**
	 * Gets the committee proposal test.
	 *
	 * @return the committee proposal test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getCommitteeProposalTest() throws Exception {
		final CommitteeProposalComponentData proposal = riksdagenApi
				.getCommitteeProposal("GX01FiU35");
		assertNotNull(proposal);
	}

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
		assertTrue(documentContent.getContent().contains(
				"till statsrådet Cristina Husmark Pehrsson (m)"));
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

		assertEquals(DocumentType.BET, dokumentstatus.getDocument().getDocumentType());
		assertTrue(dokumentstatus.getDocumentReferenceContainer().getDocumentReferenceList().size()>0);
		assertTrue(dokumentstatus.getDocumentDetailContainer().getDocumentDetailList().size()>0);

		dokumentstatus = riksdagenApi
				.getDocumentStatus("GQ02So321");
		assertNotNull(dokumentstatus);
		assertNotNull(dokumentstatus.getDocument().getMadePublicDate());
		assertNotNull(dokumentstatus.getDocument().getHangarId());
		assertEquals(DocumentType.MOT, dokumentstatus.getDocument().getDocumentType());

		assertEquals("Avslag", dokumentstatus.getDocumentProposal().getProposal().getChamber());
		assertEquals(" Avslag", dokumentstatus.getDocumentProposal().getProposal().getCommittee());
		assertEquals("2003/04:SoU7", dokumentstatus.getDocumentProposal().getProposal().getProcessedIn());
		assertEquals("Röstning", dokumentstatus.getDocumentProposal().getProposal().getDecisionType());
		assertEquals("0540190357911", dokumentstatus.getDocumentPersonReferenceContainer().getDocumentPersonReferenceList().get(0).getPersonReferenceId());
		assertTrue(dokumentstatus.getDocumentDetailContainer().getDocumentDetailList().size()>0);


		dokumentstatus = riksdagenApi
				.getDocumentStatus("GX02Ub453");
		assertNotNull(dokumentstatus);
		assertNotNull(dokumentstatus.getDocument().getMadePublicDate());
		assertNotNull(dokumentstatus.getDocument().getHangarId());
		assertEquals(DocumentType.MOT, dokumentstatus.getDocument().getDocumentType());
		assertTrue(dokumentstatus.getDocumentDetailContainer().getDocumentDetailList().size()>0);
		assertEquals(1,dokumentstatus.getDocumentPersonReferenceContainer().getDocumentPersonReferenceList().size());

		final DocumentPersonReferenceData documentPersonReferenceData = dokumentstatus.getDocumentPersonReferenceContainer().getDocumentPersonReferenceList().iterator().next();
		assertEquals("Thomas Bodström",documentPersonReferenceData.getReferenceName());

	}


	/**
	 * Gets the person list test.
	 *
	 * @return the person list test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getPersonListTest() throws Exception {
		final PersonContainerElement personList = riksdagenApi.getPersonList();
		assertNotNull(personList);
		assertTrue(personList.getPerson().size() >= 1291);
	}

	/**
	 * Gets the person test.
	 *
	 * @return the person test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getPersonTest() throws Exception {
		final PersonData personElement = riksdagenApi.getPerson("0389619060499");
		assertNotNull(personElement);
		assertEquals("Bohman", personElement.getLastName());
	}

	/**
	 * Process document list by changed since.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void processDocumentListByChangedSince() throws Exception {
		final List<DocumentElement> dokumentList = new ArrayList<DocumentElement>();

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

}

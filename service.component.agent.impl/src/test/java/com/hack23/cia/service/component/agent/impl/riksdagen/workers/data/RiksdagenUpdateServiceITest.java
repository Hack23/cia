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
package com.hack23.cia.service.component.agent.impl.riksdagen.workers.data;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData_;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.external.riksdagen.person.impl.AssignmentData;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonAssignmentData;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.AgainstProposalContainer;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.AgainstProposalData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeDocumentData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData_;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalContainer;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalData;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataEmbeddedId;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataEmbeddedId_;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData_;
import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;
import com.hack23.cia.service.data.api.CommitteeProposalComponentDataDAO;
import com.hack23.cia.service.data.api.DocumentContentDataDAO;
import com.hack23.cia.service.data.api.DocumentElementDAO;
import com.hack23.cia.service.data.api.DocumentStatusContainerDAO;
import com.hack23.cia.service.data.api.PersonDataDAO;
import com.hack23.cia.service.data.api.VoteDataDAO;

/**
 * The Class RiksdagenUpdateServiceITest.
 */
@Transactional
public class RiksdagenUpdateServiceITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The riksdagen update service. */
	@Autowired
	private RiksdagenUpdateService riksdagenUpdateService;

	/**
	 * Update person data already exist success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updatePersonDataAlreadyExistSuccessTest() throws Exception {
		final PersonDataDAO personDataDAOMock = mock(PersonDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "personDataDAO", personDataDAOMock);

		final String personId = "0542160909628";

		final PersonData existPersonData = new PersonData();
		final List<AssignmentData> assignmentList = new ArrayList<>();
		assignmentList.add(new AssignmentData().withOrgCode("OrgCode").withRoleCode("RoleCode").withFromDate(new Date())
				.withToDate(new Date()).withIntressentId(personId));
		existPersonData.withPersonAssignmentData(new PersonAssignmentData().withAssignmentList(assignmentList));
		existPersonData.setId(personId);


		final PersonData personData = new PersonData();
		final List<AssignmentData> assignmentListNew = new ArrayList<>();
		assignmentListNew.add(new AssignmentData().withOrgCode("OrgCode").withRoleCode("RoleCode").withFromDate(new Date())
				.withToDate(new Date()).withIntressentId(personId));
		assignmentListNew.add(new AssignmentData().withOrgCode("OrgCodeNew").withRoleCode("RoleCodeNew").withFromDate(new Date())
				.withToDate(new Date()).withIntressentId(personId));

		personData.withPersonAssignmentData(new PersonAssignmentData().withAssignmentList(assignmentListNew));
		personData.setId(personId);

		when(personDataDAOMock.load(personId)).thenReturn(existPersonData);

		riksdagenUpdateService.update(personData);
		verify(personDataDAOMock, times(1)).persist(existPersonData);
	}

	/**
	 * Update person data success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updatePersonDataSuccessTest() throws Exception {
		final PersonDataDAO personDataDAOMock = mock(PersonDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "personDataDAO", personDataDAOMock);
		final String personId = "notExist";
		when(personDataDAOMock.load(personId)).thenReturn(null);
		final PersonData personData = new PersonData();
		riksdagenUpdateService.update(personData);
		verify(personDataDAOMock, times(1)).persist(personData);
	}

	/**
	 * Update document content data ignored already success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateDocumentContentDataIgnoredAlreadySuccessTest() throws Exception {
		final DocumentContentDataDAO documentContentDataDAO = mock(DocumentContentDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "documentContentDataDAO", documentContentDataDAO);

		final String docId = "GZ10190";
		final DocumentContentData documentContent = new DocumentContentData().withId(docId);
		when(documentContentDataDAO.findFirstByProperty(DocumentContentData_.id, docId)).thenReturn(documentContent);

		riksdagenUpdateService.updateDocumentContentData(documentContent);

		verify(documentContentDataDAO, never()).persist(documentContent);

	}

	/**
	 * Update document content data success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateDocumentContentDataSuccessTest() throws Exception {
		final DocumentContentDataDAO documentContentDataDAO = mock(DocumentContentDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "documentContentDataDAO", documentContentDataDAO);
		final String docId = "notExist";
		when(documentContentDataDAO.findFirstByProperty(DocumentContentData_.id, docId)).thenReturn(null);
		final DocumentContentData documentContent = new DocumentContentData().withId(docId);
		riksdagenUpdateService.updateDocumentContentData(documentContent);
		verify(documentContentDataDAO, times(1)).persist(documentContent);
	}

	/**
	 * Update committee proposal component data success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateCommitteeProposalComponentDataSuccessTest() throws Exception {
		final CommitteeProposalComponentDataDAO committeeProposalComponentDataDAO = mock(
				CommitteeProposalComponentDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "committeeProposalComponentDataDAO",
				committeeProposalComponentDataDAO);
		final CommitteeProposalComponentData documentProposal = new CommitteeProposalComponentData()
				.withDocument(new CommitteeDocumentData());
		when(committeeProposalComponentDataDAO.findFirstByProperty(CommitteeProposalComponentData_.document,
				documentProposal.getDocument())).thenReturn(null);
		riksdagenUpdateService.updateCommitteeProposalComponentData(documentProposal);
		verify(committeeProposalComponentDataDAO, times(1)).persist(documentProposal);
	}

	/**
	 * Update committee proposal component data already exist update success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateCommitteeProposalComponentDataAlreadyExistUpdateSuccessTest() throws Exception {
		final CommitteeProposalComponentDataDAO committeeProposalComponentDataDAO = mock(
				CommitteeProposalComponentDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "committeeProposalComponentDataDAO",
				committeeProposalComponentDataDAO);
		final CommitteeProposalComponentData documentProposal = new CommitteeProposalComponentData()
				.withDocument(new CommitteeDocumentData()).withCommitteeProposalContainer(new CommitteeProposalContainer()).withAgainstProposalContainer(new AgainstProposalContainer());

		when(committeeProposalComponentDataDAO.findFirstByProperty(CommitteeProposalComponentData_.document,
				documentProposal.getDocument())).thenReturn(documentProposal);
		riksdagenUpdateService.updateCommitteeProposalComponentData(documentProposal);
		verify(committeeProposalComponentDataDAO, times(1)).persist(documentProposal);
	}

	/**
	 * Update committee proposal component data already exist update with success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateCommitteeProposalComponentDataAlreadyExistUpdateWithListsSuccessTest() throws Exception {
		final CommitteeProposalComponentDataDAO committeeProposalComponentDataDAO = mock(
				CommitteeProposalComponentDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "committeeProposalComponentDataDAO",
				committeeProposalComponentDataDAO);
		final CommitteeProposalContainer committeeProposalContainer = new CommitteeProposalContainer();
		committeeProposalContainer.getCommitteeProposalList().add(new CommitteeProposalData());
		final AgainstProposalContainer againstProposalContainer = new AgainstProposalContainer();
		againstProposalContainer.getAgainstProposalList().add(new AgainstProposalData());

		final CommitteeProposalComponentData documentProposal = new CommitteeProposalComponentData()
				.withDocument(new CommitteeDocumentData()).withCommitteeProposalContainer(committeeProposalContainer).withAgainstProposalContainer(againstProposalContainer);

		when(committeeProposalComponentDataDAO.findFirstByProperty(CommitteeProposalComponentData_.document,
				documentProposal.getDocument())).thenReturn(documentProposal);
		riksdagenUpdateService.updateCommitteeProposalComponentData(documentProposal);
		verify(committeeProposalComponentDataDAO, times(1)).persist(documentProposal);
	}

	/**
	 * Update committee proposal component data already exist update with null lists success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateCommitteeProposalComponentDataAlreadyExistUpdateWithNullListsSuccessTest() throws Exception {
		final CommitteeProposalComponentDataDAO committeeProposalComponentDataDAO = mock(
				CommitteeProposalComponentDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "committeeProposalComponentDataDAO",
				committeeProposalComponentDataDAO);

		final CommitteeProposalComponentData documentProposal = new CommitteeProposalComponentData()
				.withDocument(new CommitteeDocumentData()).withCommitteeProposalContainer(null).withAgainstProposalContainer(null);

		when(committeeProposalComponentDataDAO.findFirstByProperty(CommitteeProposalComponentData_.document,
				documentProposal.getDocument())).thenReturn(documentProposal);
		riksdagenUpdateService.updateCommitteeProposalComponentData(documentProposal);
		verify(committeeProposalComponentDataDAO, times(1)).persist(documentProposal);
	}


	/**
	 * Update document element success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateDocumentElementSuccessTest() throws Exception {
		final DocumentElementDAO documentElementDAO = mock(DocumentElementDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "documentElementDAO", documentElementDAO);
		final String docId = "notExist";
		when(documentElementDAO.load(docId)).thenReturn(null);
		final DocumentElement documentElement = new DocumentElement().withId(docId);
		riksdagenUpdateService.updateDocumentElement(documentElement);
		verify(documentElementDAO, times(1)).persist(documentElement);
	}

	/**
	 * Update document element already exist success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateDocumentElementAlreadyExistSuccessTest() throws Exception {
		final DocumentElementDAO documentElementDAO = mock(DocumentElementDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "documentElementDAO", documentElementDAO);
		final String docId = "notExist";
		final DocumentElement documentElement = new DocumentElement().withId(docId);
		when(documentElementDAO.load(docId)).thenReturn(documentElement);
		riksdagenUpdateService.updateDocumentElement(documentElement);
		verify(documentElementDAO, times(1)).persist(documentElement);
	}

	/**
	 * Update document data success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateDocumentDataSuccessTest() throws Exception {
		final DocumentStatusContainerDAO documentStatusContainerDAO = mock(DocumentStatusContainerDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "documentStatusContainerDAO", documentStatusContainerDAO);
		final String docId = "notExist";
		when(documentStatusContainerDAO.findListByEmbeddedProperty(DocumentStatusContainer_.document,
				DocumentData.class, DocumentData_.id, docId)).thenReturn(new ArrayList<>());
		final DocumentStatusContainer documentStatusContainer = new DocumentStatusContainer()
				.withDocument(new DocumentData().withId(docId));
		riksdagenUpdateService.updateDocumentData(documentStatusContainer);
		verify(documentStatusContainerDAO, times(1)).persist(documentStatusContainer);
	}

	/**
	 * Update document data already exist ignore success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateDocumentDataAlreadyExistIgnoreSuccessTest() throws Exception {
		final DocumentStatusContainerDAO documentStatusContainerDAO = mock(DocumentStatusContainerDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "documentStatusContainerDAO", documentStatusContainerDAO);
		final String docId = "notExist";
		final ArrayList<DocumentStatusContainer> list = new ArrayList<>();
		list.add(new DocumentStatusContainer().withDocument(new DocumentData().withStatus("publicerat")));
		when(documentStatusContainerDAO.findListByEmbeddedProperty(DocumentStatusContainer_.document,
				DocumentData.class, DocumentData_.id, docId)).thenReturn(list);
		final DocumentStatusContainer documentStatusContainer = new DocumentStatusContainer()
				.withDocument(new DocumentData().withId(docId));
		riksdagenUpdateService.updateDocumentData(documentStatusContainer);
		verify(documentStatusContainerDAO, never()).persist(documentStatusContainer);
	}

	/**
	 * Update vote data data ignore empty success test.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateVoteDataDataIgnoreEmptySuccessTest() throws Exception {
		final VoteDataDAO voteDataDAO = mock(VoteDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "voteDataDAO", voteDataDAO);
		riksdagenUpdateService.updateVoteDataData(new ArrayList<>());
		verify(voteDataDAO, never()).persist(any(List.class));
	}

	/**
	 * Update vote data data success test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateVoteDataDataSuccessTest() throws Exception {
		final VoteDataDAO voteDataDAO = mock(VoteDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "voteDataDAO", voteDataDAO);
		final String ballotId = "notExist";
		final List<VoteData> ballotList = new ArrayList<>();
		ballotList.add(new VoteData().withEmbeddedId(new VoteDataEmbeddedId().withBallotId(ballotId)));
		when(voteDataDAO.findListByEmbeddedProperty(VoteData_.embeddedId, VoteDataEmbeddedId.class,
				VoteDataEmbeddedId_.ballotId, ballotId)).thenReturn(new ArrayList<>());
		riksdagenUpdateService.updateVoteDataData(ballotList);
		verify(voteDataDAO, times(1)).persist(ballotList);
	}

	/**
	 * Update vote data data already exist success test.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateVoteDataDataAlreadyExistSuccessTest() throws Exception {
		final VoteDataDAO voteDataDAO = mock(VoteDataDAO.class);
		ReflectionTestUtils.setField(riksdagenUpdateService, "voteDataDAO", voteDataDAO);
		final String ballotId = "exist";
		final List<VoteData> ballotList = new ArrayList<>();
		ballotList.add(new VoteData().withEmbeddedId(new VoteDataEmbeddedId().withBallotId(ballotId)));
		when(voteDataDAO.findListByEmbeddedProperty(VoteData_.embeddedId, VoteDataEmbeddedId.class,
				VoteDataEmbeddedId_.ballotId, ballotId)).thenReturn(ballotList);
		riksdagenUpdateService.updateVoteDataData(ballotList);
		verify(voteDataDAO, never()).persist(any(List.class));
	}

}

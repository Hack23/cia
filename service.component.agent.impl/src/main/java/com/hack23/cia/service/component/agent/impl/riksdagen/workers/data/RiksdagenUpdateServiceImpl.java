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
package com.hack23.cia.service.component.agent.impl.riksdagen.workers.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData_;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData_;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataEmbeddedId;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataEmbeddedId_;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData_;
import com.hack23.cia.service.data.api.CommitteeProposalComponentDataDAO;
import com.hack23.cia.service.data.api.DocumentContentDataDAO;
import com.hack23.cia.service.data.api.DocumentElementDAO;
import com.hack23.cia.service.data.api.DocumentStatusContainerDAO;
import com.hack23.cia.service.data.api.PersonDataDAO;
import com.hack23.cia.service.data.api.VoteDataDAO;

/**
 * The Class RiksdagenUpdateServiceImpl.
 */
@Component("RiksdagenUpdateService")
@Transactional(propagation = Propagation.MANDATORY)
final class RiksdagenUpdateServiceImpl implements RiksdagenUpdateService {

	/** The committee proposal component data dao. */
	@Autowired
	private CommitteeProposalComponentDataDAO committeeProposalComponentDataDAO;

	/** The document content data dao. */
	@Autowired
	private DocumentContentDataDAO documentContentDataDAO;

	/** The document element dao. */
	@Autowired
	private DocumentElementDAO documentElementDAO;

	/** The document status container dao. */
	@Autowired
	private DocumentStatusContainerDAO documentStatusContainerDAO;

	/** The person data dao. */
	@Autowired
	private PersonDataDAO personDataDAO;

	/** The vote data dao. */
	@Autowired
	private VoteDataDAO voteDataDAO;

	/**
	 * Instantiates a new riksdagen update service impl.
	 */
	public RiksdagenUpdateServiceImpl() {
		super();
	}

	@Override
	public void update(final PersonData personData) {
		if (personDataDAO.load(personData.getId()) != null) {
			//personDataDAO.merge(personData);
		} else {
			personDataDAO.persist(personData);
		}
	}

	@Override
	public void updateCommitteeProposalComponentData(final CommitteeProposalComponentData committeeProposal) {
		if (committeeProposalComponentDataDAO.findFirstByProperty(CommitteeProposalComponentData_.document,
				committeeProposal.getDocument()) != null) {
			//committeeProposalComponentDataDAO.merge(committeeProposal);
		} else {
			committeeProposalComponentDataDAO.persist(committeeProposal);
		}
	}

	@Override
	public void updateDocumentContentData(final DocumentContentData documentData) {
		if (documentContentDataDAO.findFirstByProperty(DocumentContentData_.id, documentData.getId()) != null) {
			// documentContentDataDAO.merge(documentData);
		} else {
			documentContentDataDAO.persist(documentData);
		}
	}

	@Override
	public void updateDocumentData(final DocumentStatusContainer documentData) {
		if(documentStatusContainerDAO.findListByEmbeddedProperty(DocumentStatusContainer_.document,DocumentData.class,DocumentData_.id,documentData.getDocument().getId()).isEmpty()) {
			documentStatusContainerDAO.persist(documentData);
		}
	}

	@Override
	public void updateDocumentElement(final DocumentElement documentData) {
		if (documentElementDAO.checkDocumentElement(documentData.getId())) {
			//documentElementDAO.merge(documentData);
		} else {
			documentElementDAO.persist(documentData);
		}

	}

	@Override
	public void updateVoteDataData(final List<VoteData> list) {
		if (list != null && !list.isEmpty()) {
			if (voteDataDAO.findListByEmbeddedProperty(VoteData_.embeddedId,VoteDataEmbeddedId.class,VoteDataEmbeddedId_.ballotId,
					list.get(0).getEmbeddedId().getBallotId()).isEmpty()) {
				voteDataDAO.persist(list);
			}
		}
	}

}

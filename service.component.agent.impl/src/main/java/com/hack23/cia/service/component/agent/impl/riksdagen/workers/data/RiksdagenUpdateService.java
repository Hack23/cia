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
package com.hack23.cia.service.component.agent.impl.riksdagen.workers.data;

import java.util.List;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;

/**
 * The Interface RiksdagenUpdateService.
 */
public interface RiksdagenUpdateService {


	/**
	 * Update.
	 *
	 * @param personData
	 *            the person data
	 */
	void update(PersonData personData);

	/**
	 * Update committee proposal component data.
	 *
	 * @param committeeProposal
	 *            the committee proposal
	 */
	void updateCommitteeProposalComponentData(
			CommitteeProposalComponentData committeeProposal);

	/**
	 * Update document content data.
	 *
	 * @param documentContent
	 *            the document content
	 */
	void updateDocumentContentData(DocumentContentData documentContent);

	/**
	 * Update document data.
	 *
	 * @param documentData
	 *            the document data
	 */
	void updateDocumentData(DocumentStatusContainer documentData);

	/**
	 * Update document element.
	 *
	 * @param documentElement
	 *            the document element
	 */
	void updateDocumentElement(DocumentElement documentElement);

	/**
	 * Update vote data data.
	 *
	 * @param ballot
	 *            the ballot
	 */
	void updateVoteDataData(List<VoteData> ballot);

}

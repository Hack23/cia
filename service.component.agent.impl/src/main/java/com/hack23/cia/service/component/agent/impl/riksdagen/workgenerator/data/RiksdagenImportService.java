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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentType;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;

/**
 * The Interface RiksdagenImportService.
 */
public interface RiksdagenImportService {


	/**
	 * Gets the start year for document element.
	 *
	 * @return the start year for document element
	 */
	int getStartYearForDocumentElement();

	/**
	 * Gets the avaible committee proposal.
	 *
	 * @return the avaible committee proposal
	 */
	List<String> getAvaibleCommitteeProposal();

	/**
	 * Gets the avaible document content.
	 *
	 * @return the avaible document content
	 */
	List<String> getAvaibleDocumentContent();


	/**
	 * Gets the committee proposal component data map.
	 *
	 * @return the committee proposal component data map
	 */
	Map<String, String> getCommitteeProposalComponentDataMap();

	/**
	 * Gets the document content map.
	 *
	 * @return the document content map
	 */
	Map<String, String> getDocumentContentMap();

	/**
	 * Gets the document element map.
	 *
	 * @param after
	 *            the after
	 * @param downloadType
	 *            the download type
	 * @param onlyWithDocStatus
	 *            the only with doc status
	 * @return the document element map
	 */
	Map<String, String> getDocumentElementMap(Date after,
			List<DocumentType> downloadType, boolean onlyWithDocStatus);

	/**
	 * Gets the document status container map.
	 *
	 * @return the document status container map
	 */
	Map<String, String> getDocumentStatusContainerMap();


	/**
	 * Gets the loaded ballot id map.
	 *
	 * @return the loaded ballot id map
	 */
	Map<String, String> getLoadedBallotIdMap();

	/**
	 * Gets the none completed document status committee reports.
	 *
	 * @return the none completed document status committee reports
	 */
	List<DocumentStatusContainer> getNoneCompletedDocumentStatusCommitteeReports();

	/**
	 * Gets the none completed committee proposal.
	 *
	 * @return the none completed committee proposal
	 */
	List<CommitteeProposalComponentData> getNoneCompletedCommitteeProposal();

}

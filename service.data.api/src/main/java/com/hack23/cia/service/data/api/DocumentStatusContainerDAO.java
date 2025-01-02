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
package com.hack23.cia.service.data.api;

import java.util.List;

import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;

/**
 * The Interface DocumentStatusContainerDAO.
 */
public interface DocumentStatusContainerDAO extends AbstractGenericDAO<DocumentStatusContainer, Long>{

	/**
	 * Check exist by document id.
	 *
	 * @param id
	 *            the id
	 * @return the int
	 */
	int checkExistByDocumentId(String id);

	/**
	 * Gets the avaible committee proposal.
	 *
	 * @return the avaible committee proposal
	 */
	List<String> getAvaibleCommitteeProposal();

	/**
	 * Gets the id list.
	 *
	 * @return the id list
	 */
	List<String> getIdList();
}

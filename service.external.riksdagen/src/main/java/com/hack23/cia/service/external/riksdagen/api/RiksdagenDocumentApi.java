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
package com.hack23.cia.service.external.riksdagen.api;

import java.util.List;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentType;
import com.hack23.cia.service.external.common.api.ProcessDataStrategy;


/**
 * The Interface RiksdagenDocumentApi.
 */
public interface RiksdagenDocumentApi {

	/**
	 * Gets the document content.
	 *
	 * @param id
	 *            the id
	 * @return the document content
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	DocumentContentData getDocumentContent(String id) throws DataFailureException;

	/**
	 * Gets the document list.
	 *
	 * @param documentType
	 *            the document type
	 * @param maxNumberPages
	 *            the max number pages
	 * @return the document list
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<DocumentElement> getDocumentList(DocumentType documentType, int maxNumberPages) throws DataFailureException;


	/**
	 * Gets the document list.
	 *
	 * @param year
	 *            the year
	 * @param maxNumberPages
	 *            the max number pages
	 * @return the document list
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<DocumentElement> getDocumentList(Integer year, int maxNumberPages) throws DataFailureException;

	/**
	 * Gets the document list.
	 *
	 * @param changedSinceDate
	 *            the changed since date
	 * @param changedToDate
	 *            the changed to date
	 * @param maxNumberPages
	 *            the max number pages
	 * @return the document list
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<DocumentElement> getDocumentList(String changedSinceDate,String changedToDate, int maxNumberPages) throws DataFailureException;

	/**
	 * Gets the document status.
	 *
	 * @param id
	 *            the id
	 * @return the document status
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	DocumentStatusContainer getDocumentStatus(String id) throws DataFailureException;


	/**
	 * Process document list.
	 *
	 * @param changedSinceDate
	 *            the changed since date
	 * @param changedToDate
	 *            the changed to date
	 * @param processStrategy
	 *            the process strategy
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	void processDocumentList(String changedSinceDate, String changedToDate,
			ProcessDataStrategy<DocumentElement> processStrategy) throws DataFailureException;

}

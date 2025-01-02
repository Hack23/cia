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
package com.hack23.cia.service.impl.action.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.SearchDocumentRequest;
import com.hack23.cia.service.api.action.user.SearchDocumentResponse;
import com.hack23.cia.service.data.api.DocumentContentDataDAO;
import com.hack23.cia.service.data.api.DocumentElementDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;

/**
 * The Class SearchDocumentService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout=600)
public final class SearchDocumentService extends
		AbstractBusinessServiceImpl<SearchDocumentRequest, SearchDocumentResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SearchDocumentService.class);

	/** The document element dao. */
	@Autowired
	private DocumentElementDAO documentElementDAO;

	@Autowired
	private DocumentContentDataDAO documentContentDataDAO;

	/**
	 * Instantiates a new search document service.
	 */
	public SearchDocumentService() {
		super(SearchDocumentRequest.class);
	}


	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN", "ROLE_ANONYMOUS" })
	public SearchDocumentResponse processService(
			final SearchDocumentRequest serviceRequest) {
		final SearchDocumentResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}

		LOGGER.info("{}:{}",serviceRequest.getClass().getSimpleName(),serviceRequest.getSearchExpression());
		final SearchDocumentResponse response = new SearchDocumentResponse(ServiceResult.SUCCESS);

		final List<DocumentElement> searchResultTitles = documentElementDAO.search(serviceRequest.getSearchExpression(), serviceRequest.getMaxResults(),"title","subTitle");
		if (!searchResultTitles.isEmpty()) {
		 response.setResultElement(searchResultTitles);
		} else {
			final List<DocumentContentData> searchResultContent = documentContentDataDAO.search(serviceRequest.getSearchExpression(), serviceRequest.getMaxResults(), "content");
			if (!searchResultContent.isEmpty()) {
				final List<DocumentElement> searchResultTitlesForContent = new ArrayList<>();

				for (final DocumentContentData documentContent : searchResultContent) {

					searchResultTitlesForContent.add(documentElementDAO.load(documentContent.getId()));
				}

				response.setResultElement(searchResultTitlesForContent);

			}
		}

		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);
		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}


	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(final SearchDocumentRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.READ);
		eventRequest.setActionName(SearchDocumentRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}


	@Override
	protected SearchDocumentResponse createErrorResponse() {
		return new SearchDocumentResponse(ServiceResult.FAILURE);
	}

}

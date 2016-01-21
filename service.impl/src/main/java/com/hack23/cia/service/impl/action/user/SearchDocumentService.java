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
package com.hack23.cia.service.impl.action.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.SearchDocumentRequest;
import com.hack23.cia.service.api.action.user.SearchDocumentResponse;
import com.hack23.cia.service.data.api.DocumentElementDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class SearchDocumentService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout=600)
public final class SearchDocumentService extends
		AbstractBusinessServiceImpl<SearchDocumentRequest, SearchDocumentResponse>
		implements BusinessService<SearchDocumentRequest, SearchDocumentResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SearchDocumentService.class);

	/**
	 * Instantiates a new search document service.
	 */
	public SearchDocumentService() {
		super(SearchDocumentRequest.class);
	}

	/** The create application event service. */
	@Autowired
	private BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	/** The document element dao. */
	@Autowired
	private DocumentElementDAO documentElementDAO;


	/** (non-Javadoc)
	 * @see com.hack23.cia.service.impl.action.common.BusinessService#processService(com.hack23.cia.service.api.action.common.ServiceRequest)
	 */
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN", "ROLE_ANONYMOUS" })
	public SearchDocumentResponse processService(
			final SearchDocumentRequest serviceRequest) {

		LOGGER.info(serviceRequest.getClass().getSimpleName() +":" + serviceRequest.getSearchExpression());


		final CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.READ);
		eventRequest.setActionName(SearchDocumentRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());

		final UserAccount userAccount = getUserAccountFromSecurityContext();


		if (userAccount != null) {

			eventRequest.setUserId(userAccount.getUserId());
		}

		final SearchDocumentResponse response = new SearchDocumentResponse(ServiceResult.SUCCESS);

		response.setResultElement(documentElementDAO.search(serviceRequest.getSearchExpression(), serviceRequest.getMaxResults(), "title","subTitle"));

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}



}

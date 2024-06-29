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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.SearchDocumentRequest;
import com.hack23.cia.service.api.action.user.SearchDocumentResponse;
import com.hack23.cia.web.impl.ui.application.views.pageclicklisteners.handlers.api.SearchDocumentResponseHandler;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class SearchDocumentClickListener.
 */
public class SearchDocumentClickListener extends AbstractClickListener implements ClickListener {

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "Error message";

	/** The Constant LOG_MSG_SEARCH_DOCUMENT. */
	private static final String LOG_MSG_SEARCH_DOCUMENT = "SearchDocument {}";

	/** The Constant LOG_MSG_SEARCH_DOCUMENT_FAILURE. */
	private static final String LOG_MSG_SEARCH_DOCUMENT_FAILURE = "SearchDocument {} failure";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchDocumentClickListener.class);

	/** The Constant SEARCH_FAILED. */
	private static final String SEARCH_FAILED = "Search failed";

	/** The Constant SEARCH_SUCCESS. */
	private static final String SEARCH_SUCCESS = "Search success";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The reqister request. */
	private final SearchDocumentRequest reqisterRequest;

	/** The response handler. */
	private final SearchDocumentResponseHandler responseHandler;

	/**
	 * Instantiates a new search document click listener.
	 *
	 * @param reqisterRequest
	 *            the reqister request
	 * @param responseHandler
	 *            the response handler
	 */
	public SearchDocumentClickListener(final SearchDocumentRequest reqisterRequest, final SearchDocumentResponseHandler responseHandler) {
		this.reqisterRequest = reqisterRequest;
		this.responseHandler = responseHandler;
	}

	@Override
	public final void buttonClick(final ClickEvent event) {
		final SearchDocumentResponse response = (SearchDocumentResponse) getApplicationManager().service(reqisterRequest);
		if (ServiceResult.SUCCESS == response.getResult()) {
			LOGGER.info(LOG_MSG_SEARCH_DOCUMENT,reqisterRequest.getSearchExpression());
			showNotification(SEARCH_SUCCESS,
	                  "Found :" + response.getResultElement().size(),
	                  Notification.Type.HUMANIZED_MESSAGE);
			responseHandler.handle(response);

		} else {
			showNotification(SEARCH_FAILED,
	                  ERROR_MESSAGE,
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info(LOG_MSG_SEARCH_DOCUMENT_FAILURE,reqisterRequest.getSearchExpression());
		}
	}
}
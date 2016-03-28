/*
 * Copyright 2014 James Pether Sörling
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

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.SearchDocumentRequest;
import com.hack23.cia.service.api.action.user.SearchDocumentResponse;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class SearchDocumentClickListener.
 */
public final class SearchDocumentClickListener implements ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchDocumentClickListener.class);

	/** The reqister request. */
	private final SearchDocumentRequest reqisterRequest;

	/** The application manager. */
	private transient ApplicationManager applicationManager;

	private final SearchDocumentResponseHandler responseHandler;

	/**
	 * Instantiates a new search document click listener.
	 *
	 * @param reqisterRequest
	 *            the reqister request
	 * @param applicationManager
	 *            the application manager
	 * @param responseHandler
	 *            the response handler
	 */
	public SearchDocumentClickListener(final SearchDocumentRequest reqisterRequest, final ApplicationManager applicationManager, final SearchDocumentResponseHandler responseHandler) {
		this.reqisterRequest = reqisterRequest;
		this.applicationManager = applicationManager;
		this.responseHandler = responseHandler;
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		final SearchDocumentResponse response = (SearchDocumentResponse) applicationManager.service(reqisterRequest);
		if (ServiceResult.SUCCESS == response.getResult()) {
			LOGGER.info("SearchDocument {}",reqisterRequest.getSearchExpression());
			Notification.show("Search success",
	                  "Found :" + response.getResultElement().size(),
	                  Notification.Type.HUMANIZED_MESSAGE);
			responseHandler.handle(response);

		} else {
			Notification.show("Search failed",
	                  "Error message",
	                  Notification.Type.WARNING_MESSAGE);
			LOGGER.info("SearchDocument {} failure",reqisterRequest.getSearchExpression() );
		}
	}
}
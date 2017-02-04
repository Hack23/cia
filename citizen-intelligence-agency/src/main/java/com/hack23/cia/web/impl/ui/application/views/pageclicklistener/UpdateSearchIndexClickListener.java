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

import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.service.api.action.admin.UpdateSearchIndexRequest;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class UpdateSearchIndexClickListener.
 */
public final class UpdateSearchIndexClickListener implements ClickListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant UPDATE_SEARCH_INDEX_STARTED. */
	private static final String UPDATE_SEARCH_INDEX_STARTED = "Update Search Index Started";

	/**
	 * Instantiates a new update search index click listener.
	 *
	 */
	public UpdateSearchIndexClickListener() {
		super();
	}

	@Override
	public void buttonClick(final ClickEvent event) {

		final UpdateSearchIndexRequest serviceRequest = new UpdateSearchIndexRequest();
		serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		ApplicationMangerAccess.getApplicationManager().asyncService(serviceRequest);
		Notification.show(UPDATE_SEARCH_INDEX_STARTED);
	}
}
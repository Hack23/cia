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

import com.hack23.cia.service.api.action.admin.UpdateSearchIndexRequest;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class UpdateSearchIndexClickListener.
 */
public class UpdateSearchIndexClickListener extends AbstractClickListener implements ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant UPDATE_SEARCH_INDEX_STARTED. */
	private static final String UPDATE_SEARCH_INDEX_STARTED = "Update Search Index Started";

	/** The service request. */
	private final UpdateSearchIndexRequest serviceRequest;

	/**
	 * Instantiates a new update search index click listener.
	 *
	 * @param serviceRequest the service request
	 */
	public UpdateSearchIndexClickListener(final UpdateSearchIndexRequest serviceRequest) {
		super();
		this.serviceRequest = serviceRequest;
	}

	@Override
	public final void buttonClick(final ClickEvent event) {
		getApplicationManager().asyncService(serviceRequest);
		showNotification(UPDATE_SEARCH_INDEX_STARTED, "desc", Notification.Type.HUMANIZED_MESSAGE);
	}
}
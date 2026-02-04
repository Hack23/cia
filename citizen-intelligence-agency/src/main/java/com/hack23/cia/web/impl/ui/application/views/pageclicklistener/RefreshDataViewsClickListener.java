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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import com.hack23.cia.service.api.action.admin.RefreshDataViewsRequest;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class RefreshDataViewsClickListener.
 *
 * @see com.vaadin.ui.Button.ClickEvent
 */
public final class RefreshDataViewsClickListener extends AbstractClickListener implements ClickListener {

	public static final String REFRESH_DESC = "refresh desc";

	/** The Constant REFRESH_VIEWS_STARTED. */
	public static final String REFRESH_VIEWS_STARTED = "Refresh Views Started";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The service request. */
	private final RefreshDataViewsRequest serviceRequest;

	/**
	 * Instantiates a new refresh data views click listener.
	 *
	 * @param serviceRequest the service request
	 */
	public RefreshDataViewsClickListener(final RefreshDataViewsRequest serviceRequest) {
		super();
		this.serviceRequest = serviceRequest;
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		getApplicationManager().asyncService(serviceRequest);
		showNotification(REFRESH_VIEWS_STARTED, REFRESH_DESC, Notification.Type.HUMANIZED_MESSAGE);
	}
}
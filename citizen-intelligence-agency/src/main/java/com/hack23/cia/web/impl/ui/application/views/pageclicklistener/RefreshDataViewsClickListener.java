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

import com.hack23.cia.service.api.action.admin.RefreshDataViewsRequest;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class RefreshDataViewsClickListener.
 */
public final class RefreshDataViewsClickListener implements ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant REFRESH_VIEWS_STARTED. */
	private static final String REFRESH_VIEWS_STARTED = "Refresh Views Started";

	/**
	 * Instantiates a new refresh data views click listener.
	 *
	 */
	public RefreshDataViewsClickListener() {
		super();
	}

	@Override
	public void buttonClick(final ClickEvent event) {

		final RefreshDataViewsRequest serviceRequest = new RefreshDataViewsRequest();
		serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		ApplicationMangerAccess.getApplicationManager().asyncService(serviceRequest);
		Notification.show(REFRESH_VIEWS_STARTED);
	}
}
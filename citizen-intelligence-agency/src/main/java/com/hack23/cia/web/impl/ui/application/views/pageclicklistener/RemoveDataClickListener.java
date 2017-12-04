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

import com.hack23.cia.service.api.action.admin.RemoveDataRequest;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest.DataType;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

/**
 * The Class RemoveDataClickListener.
 */
public final class RemoveDataClickListener implements ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant DATA_REMOVED. */
	private static final String DATA_REMOVED = "Data removed";
	
	/** The data type. */
	private final DataType dataType;
	
	/**
	 * Instantiates a new removes the data click listener.
	 *
	 * @param dataType
	 *            the data type
	 */
	public RemoveDataClickListener(RemoveDataRequest.DataType dataType) {
		super();
		this.dataType = dataType;
	}

	@Override
	public void buttonClick(final ClickEvent event) {

		final RemoveDataRequest serviceRequest = new RemoveDataRequest();
		serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		serviceRequest.setDataType(dataType);

		ApplicationMangerAccess.getApplicationManager().asyncService(serviceRequest);
		Notification.show(DATA_REMOVED);
	}
}
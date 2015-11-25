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
package com.hack23.cia.web.impl.ui.application.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;

@Service
public class PageActionEventHelperImpl implements PageActionEventHelper {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.action.PageActionEventHelper#createPageEvent(com.hack23.cia.web.impl.ui.application.action.ViewAction, com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createPageEvent(ViewAction viewAction,ApplicationEventGroup applicationEventGroup,String page, String pageMode, String elementId) {
		CreateApplicationEventRequest serviceRequest = new CreateApplicationEventRequest();
		serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		serviceRequest.setEventGroup(applicationEventGroup);
		serviceRequest.setApplicationOperation(ApplicationOperationType.READ);

		serviceRequest.setPage(page);
		serviceRequest.setPageMode(pageMode);
		serviceRequest.setElementId(elementId);

		serviceRequest.setActionName(viewAction.toString());

		serviceRequest.setApplicationMessage(viewAction.toString());
		serviceRequest.setErrorMessage("errorMessage");

		CreateApplicationEventResponse response = (CreateApplicationEventResponse) applicationManager
				.service(serviceRequest);
	}
}

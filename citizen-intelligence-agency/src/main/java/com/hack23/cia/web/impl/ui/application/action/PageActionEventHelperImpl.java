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
package com.hack23.cia.web.impl.ui.application.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.web.impl.ui.application.util.UserContextUtil;

/**
 * The Class PageActionEventHelperImpl.
 */
@Service
public final class PageActionEventHelperImpl implements PageActionEventHelper {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new page action event helper impl.
	 */
	public PageActionEventHelperImpl() {
		super();
	}


	@Override
	public void createPageEvent(final ViewAction viewAction,final ApplicationEventGroup applicationEventGroup,final String page, final String pageMode, final String elementId) {



		String pageModeToUse;

		if (pageMode != null && elementId != null && pageMode.contains(elementId)) {
			pageModeToUse= pageMode.replace(elementId, "").replace("/", "");
		} else {
			pageModeToUse = pageMode;
		}

		if ((pageModeToUse == null || "".equals(pageModeToUse)) && ApplicationEventGroup.USER == applicationEventGroup) {
			pageModeToUse="Overview";
		}

		final CreateApplicationEventRequest serviceRequest = new CreateApplicationEventRequest();
		serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		serviceRequest.setEventGroup(applicationEventGroup);
		serviceRequest.setApplicationOperation(ApplicationOperationType.READ);

		serviceRequest.setPage(StringUtils.defaultString(page));
		serviceRequest.setPageMode(StringUtils.defaultString(pageModeToUse));
		serviceRequest.setElementId(StringUtils.defaultString(elementId));

		serviceRequest.setActionName(viewAction.toString());

		serviceRequest.setUserId(UserContextUtil.getUserIdFromSecurityContext());

		serviceRequest.setApplicationMessage(viewAction.toString());

		applicationManager
				.service(serviceRequest);
	}

}

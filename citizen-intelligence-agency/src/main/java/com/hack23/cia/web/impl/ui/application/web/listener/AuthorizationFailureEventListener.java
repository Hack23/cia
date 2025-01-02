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
package com.hack23.cia.web.impl.ui.application.web.listener;

import java.text.MessageFormat;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.web.impl.ui.application.util.UserContextUtil;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

/**
 * The Class AuthorizationFailureEventListener.
 */
@Service
public final class AuthorizationFailureEventListener implements ApplicationListener<AuthorizationFailureEvent> {

	/** The Constant ACCESS_DENIED. */
	private static final String ACCESS_DENIED = "Access Denied";

	/** The Constant AUTHORITIES. */
	private static final String AUTHORITIES = "Authorities:";

	/** The Constant CRLF. */
	private static final String CRLF = "[\r\n]";

	/** The Constant CRLF_REPLACEMENT. */
	private static final String CRLF_REPLACEMENT = "";

	/** The Constant ERROR_MESSAGE_FORMAT. */
	private static final String ERROR_MESSAGE_FORMAT = "SECURITY:Url:{0} , Method{1} ,{2}{3}{4}{5} source:{6}";

	/**
	 * The Constant
	 * LOG_MSG_AUTHORIZATION_FAILURE_SESSION_ID_AUTHORITIES_REQUIRED_AUTHORITIES.
	 */
	private static final String LOG_MSG_AUTHORIZATION_FAILURE_SESSION_ID_AUTHORITIES_REQUIRED_AUTHORITIES = "SECURITY:Authorization Failure:: url : {} Method : {} SessionId :{} , Authorities : {} , RequiredAuthorities : {}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFailureEventListener.class);

	/** The Constant REQUIRED_AUTHORITIES. */
	private static final String REQUIRED_AUTHORITIES = " , RequiredAuthorities:";

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new authorization failure event listener.
	 */
	public AuthorizationFailureEventListener() {
		super();
	}

	@Override
	public void onApplicationEvent(final AuthorizationFailureEvent authorizationFailureEvent) {

		final String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

		final CreateApplicationEventRequest serviceRequest = new CreateApplicationEventRequest();
		serviceRequest.setSessionId(sessionId);

		serviceRequest.setEventGroup(ApplicationEventGroup.APPLICATION);
		serviceRequest.setApplicationOperation(ApplicationOperationType.AUTHORIZATION);

		serviceRequest.setUserId(UserContextUtil.getUserIdFromSecurityContext());

		final Page currentPageIfAny = Page.getCurrent();
		final String requestUrl = UserContextUtil.getRequestUrl(currentPageIfAny);
		final UI currentUiIfAny = UI.getCurrent();
		String methodInfo = "";

		if (currentPageIfAny != null && currentUiIfAny != null && currentUiIfAny.getNavigator() != null
				&& currentUiIfAny.getNavigator().getCurrentView() != null) {
			serviceRequest.setPage(currentUiIfAny.getNavigator().getCurrentView().getClass().getSimpleName());
			serviceRequest.setPageMode(currentPageIfAny.getUriFragment());
		}

		if (authorizationFailureEvent.getSource() instanceof final ReflectiveMethodInvocation methodInvocation) {
			if (methodInvocation.getThis() != null) {
				methodInfo = new StringBuilder().append(methodInvocation.getThis().getClass().getSimpleName())
						.append('.').append(methodInvocation.getMethod().getName()).toString();
			}
		}

		final Collection<? extends GrantedAuthority> authorities = authorizationFailureEvent.getAuthentication()
				.getAuthorities();
		final Collection<ConfigAttribute> configAttributes = authorizationFailureEvent.getConfigAttributes();

		serviceRequest.setErrorMessage(MessageFormat.format(ERROR_MESSAGE_FORMAT, requestUrl, methodInfo, AUTHORITIES,
				authorities, REQUIRED_AUTHORITIES, configAttributes, authorizationFailureEvent.getSource()));
		serviceRequest.setApplicationMessage(ACCESS_DENIED);

		applicationManager.service(serviceRequest);

		LOGGER.info(LOG_MSG_AUTHORIZATION_FAILURE_SESSION_ID_AUTHORITIES_REQUIRED_AUTHORITIES,
				requestUrl.replaceAll(CRLF, CRLF_REPLACEMENT), methodInfo.replaceAll(CRLF, CRLF_REPLACEMENT),
				sessionId.replaceAll(CRLF, CRLF_REPLACEMENT), authorities, configAttributes);
	}

}

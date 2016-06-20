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
package com.hack23.cia.web.impl.ui.application;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.DestroyApplicationSessionRequest;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

/**
 * The Class ApplicationEventListener.
 */
@Service
public final class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {

	/** The Constant REQUIRED_AUTHORITIES. */
	private static final String REQUIRED_AUTHORITIES = " , RequiredAuthorities:";

	/** The Constant PRINCIPAL. */
	private static final String PRINCIPAL = "principal";

	/** The Constant KEY. */
	private static final String KEY = "key";

	/** The Constant LOG_MSG_SESSION_CREATED_SESSION_ID. */
	private static final String LOG_MSG_SESSION_CREATED_SESSION_ID = "Session created SESSION_ID :{}";

	/** The Constant ROLE_ANONYMOUS. */
	private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

	/** The Constant LOG_MSG_SESSION_DESTROYED_SESSION_ID. */
	private static final String LOG_MSG_SESSION_DESTROYED_SESSION_ID = "Session destroyed SESSION_ID :{}";

	/** The Constant ACCESS_DENIED. */
	private static final String ACCESS_DENIED = "Access Denied";

	/** The Constant AUTHORITIES. */
	private static final String AUTHORITIES = "Authorities:";

	/**
	 * The Constant
	 * LOG_MSG_AUTHORIZATION_FAILURE_SESSION_ID_AUTHORITIES_REQUIRED_AUTHORITIES.
	 */
	private static final String LOG_MSG_AUTHORIZATION_FAILURE_SESSION_ID_AUTHORITIES_REQUIRED_AUTHORITIES = "Authorization Failure:: url : {} SessionId :{} , Authorities : {} , RequiredAuthorities : {}";

	/** The Constant LOG_MSG_APPLICATION_EVENT. */
	private static final String LOG_MSG_APPLICATION_EVENT = "ApplicationEvent :{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationEventListener.class);

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new application event listener.
	 */
	public ApplicationEventListener() {
		super();
	}


	@Override
	public void onApplicationEvent(final ApplicationEvent applicationEvent) {
		if (applicationEvent instanceof HttpSessionCreatedEvent) {
			final HttpSession httpSession = ((HttpSessionCreatedEvent) applicationEvent).getSession();
			LOGGER.info(LOG_MSG_SESSION_CREATED_SESSION_ID, httpSession.getId());
		} else if (applicationEvent instanceof HttpSessionDestroyedEvent) {
			final HttpSession httpSession = ((HttpSessionDestroyedEvent) applicationEvent).getSession();
			final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(ROLE_ANONYMOUS));
			final DestroyApplicationSessionRequest destroyApplicationSessionRequest = new DestroyApplicationSessionRequest();
			destroyApplicationSessionRequest.setSessionId(httpSession.getId());

			SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken(KEY, PRINCIPAL, authorities));
			applicationManager.service(destroyApplicationSessionRequest);
			SecurityContextHolder.getContext().setAuthentication(null);

			LOGGER.info(LOG_MSG_SESSION_DESTROYED_SESSION_ID, httpSession.getId());
		}  else if (applicationEvent instanceof AuthorizationFailureEvent) {
			final AuthorizationFailureEvent authorizationFailureEvent = (AuthorizationFailureEvent) applicationEvent;

			final String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

			final CreateApplicationEventRequest serviceRequest = new CreateApplicationEventRequest();
			serviceRequest.setSessionId(sessionId);

			serviceRequest.setEventGroup(ApplicationEventGroup.APPLICATION);
			serviceRequest.setApplicationOperation(ApplicationOperationType.AUTHORIZATION);

			serviceRequest.setUserId(getUserIdFromSecurityContext());


			Page currentPageIfAny = Page.getCurrent();
			String requestUrl = getRequestUrl(currentPageIfAny);
			UI currentUiIfAny = UI.getCurrent();

			if (currentPageIfAny != null && currentUiIfAny != null) {
				serviceRequest.setPage(currentUiIfAny.getNavigator().getCurrentView().getClass().getSimpleName());
				serviceRequest.setPageMode(currentPageIfAny.getUriFragment());
			}

			serviceRequest.setErrorMessage("Url:" + requestUrl +" ," + AUTHORITIES + authorizationFailureEvent.getAuthentication().getAuthorities() + REQUIRED_AUTHORITIES + authorizationFailureEvent.getConfigAttributes());
			serviceRequest.setApplicationMessage(ACCESS_DENIED);

			applicationManager
					.service(serviceRequest);

			LOGGER.info(LOG_MSG_AUTHORIZATION_FAILURE_SESSION_ID_AUTHORITIES_REQUIRED_AUTHORITIES,requestUrl, sessionId,authorizationFailureEvent.getAuthentication().getAuthorities().toString(),authorizationFailureEvent.getConfigAttributes().toString());
		} 	else {
			LOGGER.debug(LOG_MSG_APPLICATION_EVENT, applicationEvent.toString());
		}
	}

	/**
	 * Gets the user id from security context.
	 *
	 * @return the user id from security context
	 */
	private static String getUserIdFromSecurityContext() {

		String result=null;

		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication authentication = context.getAuthentication();
			if (authentication != null) {
				final Object principal = authentication.getPrincipal();

				if (principal instanceof UserAccount) {
					final UserAccount userAccount = (UserAccount) principal;
					result = userAccount.getUserId();
				}
			}
		}
		return result;
	}

	private static String getRequestUrl(final Page current) {
		if (current != null) {
			return current.getLocation().toString();

		} else {
			final HttpServletRequest httpRequest=((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			return httpRequest.getRequestURL().toString();
		}
	}

}

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
package com.hack23.cia.service.impl.action.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSessionType;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.DeleteAccountRequest;
import com.hack23.cia.service.api.action.user.DeleteAccountResponse;
import com.hack23.cia.service.data.api.ApplicationSessionDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;

/**
 * The Class DeleteAccountService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout=600)
public final class DeleteAccountService extends
		AbstractBusinessServiceImpl<DeleteAccountRequest, DeleteAccountResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeleteAccountService.class);

	/** The application session dao. */
	@Autowired
	private ApplicationSessionDAO applicationSessionDAO;

	/** The password encoder. */
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Instantiates a new delete account service.
	 */
	public DeleteAccountService() {
		super(DeleteAccountRequest.class);
	}


	/**
	 * Process service.
	 *
	 * @param serviceRequest the service request
	 * @return the sets the google authenticator credential response
	 */
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN"})
	public DeleteAccountResponse processService(
			final DeleteAccountRequest serviceRequest) {

		final DeleteAccountResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}

		LOGGER.info("{}:{}",serviceRequest.getClass().getSimpleName(),serviceRequest.getSessionId());
		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);

		final UserAccount userAccount = getUserAccountFromSecurityContext();

		DeleteAccountResponse response = new DeleteAccountResponse(ServiceResult.SUCCESS);
		if (userAccount != null) {

			if (passwordEncoder.matches(
					userAccount.getUserId() + ".uuid" + serviceRequest.getUserpassword(), userAccount.getUserpassword())) {

				final List<ApplicationSession> applicationSessionsByUser = applicationSessionDAO.findListByProperty(ApplicationSession_.userId, userAccount.getUserId());
				for (final ApplicationSession applicationSession : applicationSessionsByUser) {
					if (!serviceRequest.getSessionId().equals(applicationSession.getSessionId())) {
						applicationSessionDAO.delete(applicationSession);
					} else {
						applicationSession.setUserId(null);
						applicationSession.setSessionType(ApplicationSessionType.ANONYMOUS);
						applicationSession.setDestroyedDate(new Date());
						applicationSession.setScreenSize("1280x1024");
						applicationSession.setLocale(Locale.ENGLISH.toString());
						applicationSession.setUserAgentInformation("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36\n");
						final List<ApplicationActionEvent> events = applicationSession.getEvents();
						for (final ApplicationActionEvent event : events) {
							event.setUserId(null);
						}
						applicationSessionDAO.persist(applicationSession);
					}
				}

				getUserDAO().delete(userAccount);

				final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
				final AnonymousAuthenticationToken anonymousAuthenticationToken = new AnonymousAuthenticationToken(
						serviceRequest.getSessionId(), "ROLE_ANONYMOUS", authorities);
				SecurityContextHolder.getContext().setAuthentication(anonymousAuthenticationToken);

			} else {
				response = new DeleteAccountResponse(ServiceResult.FAILURE);
			}
		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}


	/**
	 * Creates the application event for service.
	 *
	 * @param serviceRequest the service request
	 * @return the creates the application event request
	 */
	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(
			final DeleteAccountRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.DELETE);
		eventRequest.setActionName(DeleteAccountRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}


	/**
	 * Creates the error response.
	 *
	 * @return the sets the google authenticator credential response
	 */
	@Override
	protected DeleteAccountResponse createErrorResponse() {
		return new DeleteAccountResponse(ServiceResult.FAILURE);
	}



}

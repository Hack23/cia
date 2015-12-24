/*
 * Copyright 2010 James Pether Sörling
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
package com.hack23.cia.service.impl.action.application;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.model.internal.application.user.impl.UserRole;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.UserDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class LoginService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public final class LoginService extends
		AbstractBusinessServiceImpl<LoginRequest, LoginResponse>
		implements BusinessService<LoginRequest, LoginResponse> {

	/** The user dao. */
	@Autowired
	private UserDAO userDAO;

	/** The create application event service. */
	@Autowired
	private BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	/** The password encoder. */
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	/**
	 * Instantiates a new login service.
	 */
	public LoginService() {
		super(LoginRequest.class);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.impl.action.common.BusinessService#processService(com.hack23.cia.service.api.action.common.ServiceRequest)
	 */
	@Override
	@Secured({"ROLE_ANONYMOUS","ROLE_USER","ROLE_ADMIN"})
	public LoginResponse processService(
			LoginRequest serviceRequest) {

		CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.AUTHENTICATION);
		eventRequest.setActionName(LoginRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		eventRequest.setElementId(serviceRequest.getEmail());

		UserAccount userExist = userDAO.findFirstByProperty(UserAccount_.email, serviceRequest.getEmail());

		LoginResponse response;
		if (userExist != null) {

			if (passwordEncoder.matches(userExist.getUserId()+".uuid"+ serviceRequest.getUserpassword(), userExist.getUserpassword())) {

				Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

				if (userExist.getUserRole().equals(UserRole.ADMIN)) {
					authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				} else if (userExist.getUserRole().equals(UserRole.USER)) {
					authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				}

				eventRequest.setUserId(userExist.getUserId());

				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userExist, userExist.getUserpassword(), authorities));

				userExist.setNumberOfVisits(userExist.getNumberOfVisits() + 1);
				userDAO.persist(userExist);
				response = new LoginResponse(ServiceResult.SUCCESS);

			} else {
				response = new LoginResponse(ServiceResult.FAILURE);
			}

		} else {
			response = new LoginResponse(ServiceResult.FAILURE);
		}
		eventRequest.setApplicationMessage(response.getResult().toString());

		createApplicationEventService.processService(eventRequest);

		return response;
	}

}

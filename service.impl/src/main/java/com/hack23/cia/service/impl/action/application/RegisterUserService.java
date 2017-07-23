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
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.model.internal.application.user.impl.UserRole;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;
import com.hack23.cia.service.data.api.UserDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;

/**
 * The Class RegisterUserService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public final class RegisterUserService extends
		AbstractBusinessServiceImpl<RegisterUserRequest, RegisterUserResponse>
		implements BusinessService<RegisterUserRequest, RegisterUserResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RegisterUserService.class);


	/** The create application event service. */
	@Autowired
	private BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	/** The application configuration service. */
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;

	/** The user dao. */
	@Autowired
	private UserDAO userDAO;

	/** The password encoder. */
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Instantiates a new register user service.
	 */
	public RegisterUserService() {
		super(RegisterUserRequest.class);
	}

	@Secured({"ROLE_ANONYMOUS"})
	@Override
	public RegisterUserResponse processService(
			final RegisterUserRequest serviceRequest) {

		final CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.CREATE);
		eventRequest.setActionName(RegisterUserRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		eventRequest.setElementId(serviceRequest.getEmail());

		final ApplicationConfiguration registeredUsersGetAdminConfig = applicationConfigurationService.checkValueOrLoadDefault("Registered User All get Role Admin", "Registered User All get Role Admin", ConfigurationGroup.AUTHORIZATION, RegisterUserService.class.getSimpleName(), "Register User Service", "Responsible for create of useraccounts", "registered.users.get.admin", "true");

		final UserAccount userNameExist = userDAO.findFirstByProperty(UserAccount_.username, serviceRequest.getUsername());
		final UserAccount userEmailExist = userDAO.findFirstByProperty(UserAccount_.email, serviceRequest.getEmail());

		RegisterUserResponse response;
		if (userEmailExist == null && userNameExist == null) {
			final UserAccount userAccount = new UserAccount();
			userAccount.setCountry(serviceRequest.getCountry());
			userAccount.setEmail(serviceRequest.getEmail());
			userAccount.setUsername(serviceRequest.getUsername());
			userAccount.setUserId(UUID.randomUUID().toString());
			userAccount.setUserpassword(passwordEncoder.encode(userAccount.getUserId()+".uuid"+ serviceRequest.getUserpassword()));
			userAccount.setNumberOfVisits(1);
			userAccount.setUserType(serviceRequest.getUserType());
			userAccount.setCreatedDate(new Date());
			userDAO.persist(userAccount);

			if ("true".equals(registeredUsersGetAdminConfig.getPropertyValue())) {
				userAccount.setUserRole(UserRole.ADMIN);
			} else {
				userAccount.setUserRole(UserRole.USER);
			}

			final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

			if (UserRole.ADMIN == userAccount.getUserRole()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else if (UserRole.USER == userAccount.getUserRole()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			}

			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userAccount, userAccount.getUserpassword(), authorities));

			eventRequest.setUserId(userAccount.getUserId());
			response = new RegisterUserResponse(ServiceResult.SUCCESS);
		} else {
			response = new RegisterUserResponse(ServiceResult.FAILURE);
			response.setErrorMessage(RegisterUserResponse.ErrorMessage.USER_ALREADY_EXIST.toString());
		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);
		LOGGER.info("Event: {}",eventRequest);
		return response;
	}

}

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

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.model.internal.application.user.impl.UserRole;
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
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

	/** The user dao. */
	@Autowired
	private UserDAO userDAO;

	/** The password encoder. */
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Instantiates a new register user service.
	 */
	public RegisterUserService() {
		super(RegisterUserRequest.class);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.impl.action.common.BusinessService#processService(com.hack23.cia.service.api.action.common.ServiceRequest)
	 */
	@Override
	@Secured({"ROLE_ANONYMOUS"})
	public RegisterUserResponse processService(
			RegisterUserRequest serviceRequest) {

		UserAccount userNameExist = userDAO.findFirstByProperty(UserAccount_.username, serviceRequest.getUsername());
		UserAccount userEmailExist = userDAO.findFirstByProperty(UserAccount_.username, serviceRequest.getEmail());

		RegisterUserResponse response;
		if (userEmailExist == null && userNameExist == null) {
			UserAccount userAccount = new UserAccount();
			userAccount.setCountry(serviceRequest.getCountry());
			userAccount.setEmail(serviceRequest.getEmail());
			userAccount.setUsername(serviceRequest.getUsername());
			userAccount.setUserId(UUID.randomUUID().toString());
			userAccount.setUserpassword(passwordEncoder.encode(userAccount.getUserId()+".uuid"+ serviceRequest.getUserpassword()));
			userAccount.setNumberOfVisits(1);
			userAccount.setUserRole(UserRole.USER);
			userAccount.setUserType(serviceRequest.getUserType());
			userDAO.persist(userAccount);
			response = new RegisterUserResponse(ServiceResult.SUCCESS);
		} else {
			response = new RegisterUserResponse(ServiceResult.FAILURE);
		}
		return response;
	}

}

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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;
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

	/**
	 * Instantiates a new register user service.
	 *
	 * @param clazz
	 *            the clazz
	 */
	public RegisterUserService() {
		super(RegisterUserRequest.class);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.impl.common.BusinessService#processService(com.hack23.cia.service.api.ServiceRequest)
	 */
	@Override
	public RegisterUserResponse processService(
			RegisterUserRequest serviceRequest) {
		return new RegisterUserResponse();
	}

}

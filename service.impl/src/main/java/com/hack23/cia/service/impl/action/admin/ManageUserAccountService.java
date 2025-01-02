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
package com.hack23.cia.service.impl.action.admin;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.model.internal.application.user.impl.UserLockStatus;
import com.hack23.cia.service.api.action.admin.ManageUserAccountRequest;
import com.hack23.cia.service.api.action.admin.ManageUserAccountResponse;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.RemoveDataManager;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;

/**
 * The Class ManageUserAccountService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public final class ManageUserAccountService
		extends AbstractBusinessServiceImpl<ManageUserAccountRequest, ManageUserAccountResponse> {

	/** The remove data manager. */
	@Autowired
	private RemoveDataManager removeDataManager;

	/** The user command map. */
	private final Map<ManageUserAccountRequest.AccountOperation,UserCommand> userCommandMap = new EnumMap<>(ManageUserAccountRequest.AccountOperation.class);

	/**
	 * Instantiates a new update application configuration service.
	 */
	public ManageUserAccountService() {
		super(ManageUserAccountRequest.class);
	}

	/**
	 * Inits the user command map.
	 */
	@PostConstruct
	public void initUserCommandMap() {
		userCommandMap.put(ManageUserAccountRequest.AccountOperation.DELETE, account -> {
			removeDataManager.removeUserAccountApplicationHistory(account.getUserId());
			getUserDAO().delete(account);
			return new ManageUserAccountResponse(ServiceResult.SUCCESS);
		});

		userCommandMap.put(ManageUserAccountRequest.AccountOperation.UNLOCK, account -> {
			account.setUserLockStatus(UserLockStatus.UNLOCKED);
			getUserDAO().persist(account);
			return new ManageUserAccountResponse(ServiceResult.SUCCESS);
		});

		userCommandMap.put(ManageUserAccountRequest.AccountOperation.LOCK, account -> {
			account.setUserLockStatus(UserLockStatus.LOCKED);
			getUserDAO().persist(account);
			return new ManageUserAccountResponse(ServiceResult.SUCCESS);
		});

	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	public ManageUserAccountResponse processService(final ManageUserAccountRequest serviceRequest) {
		final ManageUserAccountResponse inputValidation = inputValidation(serviceRequest);
		if (inputValidation != null) {
			return inputValidation;
		}

		final CreateApplicationEventRequest eventRequest = createApplicationEventForService(serviceRequest);

		final ManageUserAccountResponse response = performOperation(serviceRequest, eventRequest);
		createApplicationEventService.processService(eventRequest);

		return response;

	}

	/**
	 * Perform operation.
	 *
	 * @param serviceRequest
	 *            the service request
	 * @param eventRequest
	 *            the event request
	 * @return the manage user account response
	 */
	private ManageUserAccountResponse performOperation(final ManageUserAccountRequest serviceRequest,
			final CreateApplicationEventRequest eventRequest) {
		ManageUserAccountResponse response;
		eventRequest.setElementId(serviceRequest.getUserAcountId());
		eventRequest.setApplicationMessage(serviceRequest.getAccountOperation().toString());

		final UserAccount accountToModify = getUserDAO().findFirstByProperty(UserAccount_.userId,
				serviceRequest.getUserAcountId());

		final UserCommand userCommand = userCommandMap.get(serviceRequest.getAccountOperation());
		if (accountToModify != null && userCommand != null) {
			response = userCommand.execute(accountToModify);
		} else {
			response = new ManageUserAccountResponse(ServiceResult.FAILURE);
		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		return response;
	}

	@Override
	protected CreateApplicationEventRequest createApplicationEventForService(final ManageUserAccountRequest serviceRequest) {
		final CreateApplicationEventRequest eventRequest = createBaseApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.ADMIN);
		eventRequest.setApplicationOperation(ApplicationOperationType.UPDATE);
		eventRequest.setActionName(ManageUserAccountRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());
		return eventRequest;
	}

	@Override
	protected ManageUserAccountResponse createErrorResponse() {
		return new ManageUserAccountResponse(ServiceResult.FAILURE);
	}

	/**
	 * The Interface UserCommand.
	 */
	interface UserCommand {

		/**
		 * Execute.
		 *
		 * @param account
		 *            the account
		 * @return the manage user account response
		 */
		ManageUserAccountResponse execute(UserAccount account);
	}

}

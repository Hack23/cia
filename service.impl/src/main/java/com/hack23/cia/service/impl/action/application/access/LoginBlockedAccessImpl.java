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
package com.hack23.cia.service.impl.action.application.access;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.data.api.ApplicationActionEventDAO;
import com.hack23.cia.service.data.api.ApplicationSessionDAO;
import com.hack23.cia.service.data.api.UserDAO;

/**
 * The Class LoginBlockedAccessImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LoginBlockedAccessImpl implements LoginBlockedAccess {

	/** The user dao. */
	@Autowired
	private UserDAO userDAO;

	/** The application session dao. */
	@Autowired
	private ApplicationSessionDAO applicationSessionDAO;

	/** The application action event DAO. */
	@Autowired ApplicationActionEventDAO applicationActionEventDAO;

	@Override
	public LoginBlockResult isBlocked(String sessionId, String email) {
		LoginBlockResultImpl loginBlockResultImpl = new LoginBlockResultImpl();

		final UserAccount userExist = userDAO.findFirstByProperty(UserAccount_.email, email);

		if (userExist != null) {
			List<ApplicationActionEvent> failedLoginsByThisUser = applicationActionEventDAO.findListByProperty(new Object[] {email,ApplicationOperationType.AUTHENTICATION,ServiceResult.FAILURE.toString()},ApplicationActionEvent_.elementId,ApplicationActionEvent_.applicationOperation,ApplicationActionEvent_.applicationMessage);

			Date oneHourAgo = new Date(System.currentTimeMillis() - 3600 * 1000);
			Map<Boolean, List<ApplicationActionEvent>> recentOldLoginAttemptsMap = failedLoginsByThisUser.stream().collect(Collectors.groupingBy(x -> x.getCreatedDate().after(oneHourAgo)));
			List<ApplicationActionEvent> recentFailedLogins = recentOldLoginAttemptsMap.get(Boolean.valueOf(true));
			if (recentFailedLogins != null && recentFailedLogins.size() > 5) {
				loginBlockResultImpl.setBlocked(true);
				loginBlockResultImpl.setMessage("Blocked by more than 5 recent login attempts by this user");
			}
		}


		final ApplicationSession applicationSession = applicationSessionDAO.findFirstByProperty(ApplicationSession_.sessionId, sessionId);

		if (applicationSession != null) {
			List<ApplicationActionEvent> failedLoginsByThisSession = applicationActionEventDAO.findListByProperty(new Object[] {sessionId,ApplicationOperationType.AUTHENTICATION,ServiceResult.FAILURE.toString()},ApplicationActionEvent_.sessionId,ApplicationActionEvent_.applicationOperation,ApplicationActionEvent_.applicationMessage);
			if (failedLoginsByThisSession.size() > 5) {
				loginBlockResultImpl.setBlocked(true);
				loginBlockResultImpl.setMessage("Blocked by more than 5 login attempts by this session");
			}

			final List<ApplicationSession> applicationSessionsByIp = applicationSessionDAO.findListByProperty(ApplicationSession_.ipInformation, applicationSession.getIpInformation());
			//TODO
			// Block ip after failed flooding with failed attempts, recent hour,day,week

		}

		return loginBlockResultImpl;
	}

	/**
	 * The Class LoginBlockResultImpl.
	 */
	class LoginBlockResultImpl implements LoginBlockResult {

		private boolean isBlocked=false;
		private String message="";

		public boolean isBlocked() {
			return isBlocked;
		}
		public void setBlocked(boolean isBlocked) {
			this.isBlocked = isBlocked;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

	}

}

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
package com.hack23.cia.web.impl.ui.application.views.user.home.pagemode;

import java.util.Optional;

import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.util.UserContextUtil;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractBasicPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class AbstractPoliticianPageModContentFactoryImpl.
 */
abstract class AbstractUserHomePageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.USERHOME_VIEW_NAME;

	/**
	 * Instantiates a new abstract user home page mod content factory impl.
	 */
	AbstractUserHomePageModContentFactoryImpl() {
		super();
	}

	/**
	 * Gets the active user account.
	 *
	 * @return the active user account
	 */
	protected final Optional<UserAccount> getActiveUserAccount() {
		final DataContainer<UserAccount, Long> dataContainer = getApplicationManager()
				.getDataContainer(UserAccount.class);
		return dataContainer
				.getAllBy(UserAccount_.userId, UserContextUtil.getUserIdFromSecurityContext()).stream().findFirst();
	}

}

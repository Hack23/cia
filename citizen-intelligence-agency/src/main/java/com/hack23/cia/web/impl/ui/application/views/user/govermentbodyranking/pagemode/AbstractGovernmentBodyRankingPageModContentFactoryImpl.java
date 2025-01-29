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
package com.hack23.cia.web.impl.ui.application.views.user.govermentbodyranking.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.GovernmentBodyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractBasicPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class AbstractGovernmentBodyRankingPageModContentFactoryImpl.
 */
abstract class AbstractGovernmentBodyRankingPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.GOVERNMENT_BODY_RANKING_VIEW_NAME;

	/** The government body ranking menu item factory. */
	@Autowired
	private GovernmentBodyRankingMenuItemFactory governmentBodyRankingMenuItemFactory;

	/**
	 * Instantiates a new abstract government body ranking page mod content factory
	 * impl.
	 */
	AbstractGovernmentBodyRankingPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Gets the government body ranking menu item factory.
	 *
	 * @return the government body ranking menu item factory
	 */
	protected final GovernmentBodyRankingMenuItemFactory getGovernmentBodyRankingMenuItemFactory() {
		return governmentBodyRankingMenuItemFactory;
	}

}

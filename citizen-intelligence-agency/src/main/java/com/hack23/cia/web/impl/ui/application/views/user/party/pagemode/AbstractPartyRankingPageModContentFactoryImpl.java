/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractBasicPageModContentFactoryImpl;

/**
 * The Class AbstractRankingPageModContentFactoryImpl.
 */
abstract class AbstractPartyRankingPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {


	/** The party menu item factory. */
	@Autowired
	private PartyRankingMenuItemFactory partyRankingMenuItemFactory;


	/**
	 * Instantiates a new abstract party ranking page mod content factory impl.
	 */
	AbstractPartyRankingPageModContentFactoryImpl() {
		super();
	}


	/**
	 * Gets the party menu item factory.
	 *
	 * @return the party menu item factory
	 */
	protected final PartyRankingMenuItemFactory getPartyRankingMenuItemFactory() {
		return partyRankingMenuItemFactory;
	}

}

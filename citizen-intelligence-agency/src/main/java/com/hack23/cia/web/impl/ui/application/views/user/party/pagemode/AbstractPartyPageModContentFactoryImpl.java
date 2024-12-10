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

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractItemPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Component;

/**
 * The Class AbstractPoliticianPageModContentFactoryImpl.
 */
abstract class AbstractPartyPageModContentFactoryImpl extends AbstractItemPageModContentFactoryImpl<ViewRiksdagenParty> {

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_VIEW_NAME;

	/** The party menu item factory. */
	@Autowired
	private PartyMenuItemFactory partyMenuItemFactory;

	/**
	 * Instantiates a new abstract party page mod content factory impl.
	 */
	AbstractPartyPageModContentFactoryImpl() {
		super();
	}

	@Override
	protected ViewRiksdagenParty getItem(final String parameters) {
		return getApplicationManager().getDataContainer(ViewRiksdagenParty.class).load(getPageId(parameters));
	}

	/**
	 * Gets the party menu item factory.
	 *
	 * @return the party menu item factory
	 */
	protected final PartyMenuItemFactory getPartyMenuItemFactory() {
		return partyMenuItemFactory;
	}

	/**
	 * Page completed.
	 *
	 * @param parameters
	 *            the parameters
	 * @param panel
	 *            the panel
	 * @param pageId
	 *            the page id
	 */
	protected final void pageCompleted(final String parameters, final Component panel, final String pageId) {

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
				pageId);
	}

}

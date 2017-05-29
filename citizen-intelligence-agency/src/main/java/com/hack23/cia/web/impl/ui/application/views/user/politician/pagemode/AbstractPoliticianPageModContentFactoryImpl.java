/*
 * Copyright 2014 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Component;

/**
 * The Class AbstractPoliticianPageModContentFactoryImpl.
 */
abstract class AbstractPoliticianPageModContentFactoryImpl extends AbstractPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_VIEW_NAME;

	/** The Constant POLITICIAN. */
	private static final String POLITICIAN = "Politician:";

	/** The Constant DAYS_PER_STANDARD_YEAR. */
	private static final long DAYS_PER_STANDARD_YEAR = 365L;

	/** The politician ranking menu item factory. */
	@Autowired
	private PoliticianMenuItemFactory politicianMenuItemFactory;


	/**
	 * Instantiates a new abstract politician page mod content factory impl.
	 */
	AbstractPoliticianPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Convert to years string.
	 *
	 * @param totalDays
	 *            the total days
	 * @return the string
	 */
	protected final String convertToYearsString(final long totalDays) {
		final long years = totalDays / DAYS_PER_STANDARD_YEAR;
		final long days = totalDays - years * DAYS_PER_STANDARD_YEAR;

		return years + " Years " + days + " days";
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
	 * @param viewRiksdagenPolitician
	 *            the view riksdagen politician
	 */
	protected final void pageCompleted(final String parameters, final Component panel, final String pageId,
			final ViewRiksdagenPolitician viewRiksdagenPolitician) {
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
				UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);

		panel.setCaption(NAME + "::" + POLITICIAN + viewRiksdagenPolitician.getFirstName() + ' '
				+ viewRiksdagenPolitician.getLastName() + '(' + viewRiksdagenPolitician.getParty() + ')');
	}

	/**
	 * Gets the politician ranking menu item factory.
	 *
	 * @return the politician ranking menu item factory
	 */
	protected final PoliticianMenuItemFactory getPoliticianMenuItemFactory() {
		return politicianMenuItemFactory;
	}

}

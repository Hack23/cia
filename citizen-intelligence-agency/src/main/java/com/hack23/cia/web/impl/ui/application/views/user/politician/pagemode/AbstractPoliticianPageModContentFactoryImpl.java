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
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractItemPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class AbstractPoliticianPageModContentFactoryImpl.
 */
abstract class AbstractPoliticianPageModContentFactoryImpl extends AbstractItemPageModContentFactoryImpl<ViewRiksdagenPolitician> {

	/** The Constant DAYS_PER_STANDARD_YEAR. */
	private static final long DAYS_PER_STANDARD_YEAR = 365L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_VIEW_NAME;

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
	protected static final String convertToYearsString(final long totalDays) {
		final long years = totalDays / DAYS_PER_STANDARD_YEAR;
		final long days = totalDays - years * DAYS_PER_STANDARD_YEAR;

		return years + " Years " + days + " days";
	}

	@Override
	protected ViewRiksdagenPolitician getItem(final String parameters) {
		final String pageId = getPageId(parameters);
		final PersonData personData = getApplicationManager().getDataContainer(PersonData.class).load(pageId);
		if (personData != null) {
			return getApplicationManager().getDataContainer(ViewRiksdagenPolitician.class).load(personData.getId());
		} else {
			return null;
		}
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

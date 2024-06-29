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
package com.hack23.cia.web.impl.ui.application.views.user.country.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CountryMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractBasicPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class AbstractPoliticianPageModContentFactoryImpl.
 */
abstract class AbstractCountryPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.COUNTRY_RANKING_VIEW_NAME;

	/** The country menu item factory. */
	@Autowired
	private CountryMenuItemFactory countryMenuItemFactory;

	/**
	 * Instantiates a new abstract country page mod content factory impl.
	 */
	AbstractCountryPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Gets the test menu item factory.
	 *
	 * @return the test menu item factory
	 */
	protected final CountryMenuItemFactory getCountryMenuItemFactory() {
		return countryMenuItemFactory;
	}

}

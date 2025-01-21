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
package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.GovernmentBodyMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractItemPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class AbstractGovernmentBodyPageModContentFactoryImpl.
 */
abstract class AbstractGovernmentBodyPageModContentFactoryImpl extends AbstractItemPageModContentFactoryImpl<List<GovernmentBodyAnnualSummary>> {

	/** The Constant NAME. */
	public static final String NAME = UserViews.GOVERNMENT_BODY_VIEW_NAME;

	/** The esv api. */
	@Autowired
	private EsvApi esvApi;

	/** The government body menu item factory. */
	@Autowired
	private GovernmentBodyMenuItemFactory governmentBodyMenuItemFactory;

	/**
	 * Instantiates a new abstract government body page mod content factory impl.
	 */
	AbstractGovernmentBodyPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Gets the government body menu item factory.
	 *
	 * @return the government body menu item factory
	 */
	protected final GovernmentBodyMenuItemFactory getGovernmentBodyMenuItemFactory() {
		return governmentBodyMenuItemFactory;
	}

	@Override
	protected List<GovernmentBodyAnnualSummary> getItem(final String parameters) {
		final Map<String, List<GovernmentBodyAnnualSummary>> map = esvApi.getData().get(2024).stream().collect(Collectors.groupingBy(GovernmentBodyAnnualSummary::getOrgNumber));
		return map.get(getPageId(parameters));
	}

}

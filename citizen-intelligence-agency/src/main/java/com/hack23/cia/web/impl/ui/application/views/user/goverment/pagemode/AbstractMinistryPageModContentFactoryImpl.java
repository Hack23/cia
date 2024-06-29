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
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractItemPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class AbstractMinistryPageModContentFactoryImpl.
 */
abstract class AbstractMinistryPageModContentFactoryImpl extends AbstractItemPageModContentFactoryImpl<ViewRiksdagenMinistry> {

	/** The Constant NAME. */
	public static final String NAME = UserViews.MINISTRY_VIEW_NAME;

	@Autowired
	private MinistryMenuItemFactory ministryMenuItemFactory;

	/**
	 * Instantiates a new abstract ministry page mod content factory impl.
	 */
	AbstractMinistryPageModContentFactoryImpl() {
		super();
	}

	@Override
	protected ViewRiksdagenMinistry getItem(final String parameters) {
		return getApplicationManager().getDataContainer(ViewRiksdagenMinistry.class).load(getPageId(parameters));
	}

	protected final MinistryMenuItemFactory getMinistryMenuItemFactory() {
		return ministryMenuItemFactory;
	}

}

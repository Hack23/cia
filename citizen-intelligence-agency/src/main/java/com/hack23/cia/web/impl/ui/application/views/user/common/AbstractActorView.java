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
package com.hack23.cia.web.impl.ui.application.views.user.common;

import java.util.Map;

import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;

/**
 * The Class AbstractActorView.
 */
public abstract class AbstractActorView extends AbstractUserView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new abstract actor view.
	 *
	 * @param pageModeContentFactoryMap
	 *            the page mode content factory map
	 * @param pageName
	 *            the page name
	 */
	public AbstractActorView(final Map<String, PageModeContentFactory> pageModeContentFactoryMap, final String pageName) {
		super(pageModeContentFactoryMap,pageName);
	}

}

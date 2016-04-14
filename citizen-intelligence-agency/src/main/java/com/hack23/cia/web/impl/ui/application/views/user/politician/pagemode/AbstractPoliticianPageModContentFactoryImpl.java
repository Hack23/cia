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
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.web.impl.ui.application.action.PageActionEventHelper;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.PoliticianChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Panel;

/**
 * The Class AbstractPoliticianPageModContentFactoryImpl.
 */
public abstract class AbstractPoliticianPageModContentFactoryImpl extends AbstractPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_VIEW_NAME;

	/** The Constant POLITICIAN. */
	private static final String POLITICIAN = "Politician:";

	/** The Constant DAYS_PER_STANDARD_YEAR. */
	private static final long DAYS_PER_STANDARD_YEAR = 365L;

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The menu item factory. */
	@Autowired
	private MenuItemFactory menuItemFactory;

	/** The grid factory. */
	@Autowired
	private GridFactory gridFactory;

	/** The form factory. */
	@Autowired
	private FormFactory formFactory;

	/** The page link factory. */
	@Autowired
	private PageLinkFactory pageLinkFactory;

	/** The page action event helper. */
	@Autowired
	private PageActionEventHelper pageActionEventHelper;

	/** The chart data manager. */
	@Autowired
	private PoliticianChartDataManager chartDataManager;

	/**
	 * Instantiates a new abstract page mod content factory impl.
	 */
	protected AbstractPoliticianPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Gets the page id.
	 *
	 * @param parameters
	 *            the parameters
	 * @return the page id
	 */
	protected final String getPageId(final String parameters) {
		return parameters.substring(parameters.lastIndexOf('/') + "/".length(), parameters.length());
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


	protected final void pageCompleted(final String parameters, final Panel panel, final String pageId,
			final ViewRiksdagenPolitician viewRiksdagenPolitician) {
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
				UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);

		panel.setCaption(POLITICIAN + viewRiksdagenPolitician.getFirstName() + ' '
				+ viewRiksdagenPolitician.getLastName() + '(' + viewRiksdagenPolitician.getParty() + ')');
	}



	/**
	 * Gets the application manager.
	 *
	 * @return the application manager
	 */
	protected final ApplicationManager getApplicationManager() {
		return applicationManager;
	}

	/**
	 * Gets the menu item factory.
	 *
	 * @return the menu item factory
	 */
	protected final MenuItemFactory getMenuItemFactory() {
		return menuItemFactory;
	}

	/**
	 * Gets the grid factory.
	 *
	 * @return the grid factory
	 */
	protected final GridFactory getGridFactory() {
		return gridFactory;
	}

	/**
	 * Gets the form factory.
	 *
	 * @return the form factory
	 */
	protected final FormFactory getFormFactory() {
		return formFactory;
	}

	/**
	 * Gets the page link factory.
	 *
	 * @return the page link factory
	 */
	protected final PageLinkFactory getPageLinkFactory() {
		return pageLinkFactory;
	}

	/**
	 * Gets the page action event helper.
	 *
	 * @return the page action event helper
	 */
	protected final PageActionEventHelper getPageActionEventHelper() {
		return pageActionEventHelper;
	}

	/**
	 * Gets the chart data manager.
	 *
	 * @return the chart data manager
	 */
	public PoliticianChartDataManager getChartDataManager() {
		return chartDataManager;
	}

}

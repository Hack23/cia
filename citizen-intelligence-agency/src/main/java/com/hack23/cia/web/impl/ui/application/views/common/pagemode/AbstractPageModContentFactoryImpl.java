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
package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.web.impl.ui.application.action.PageActionEventHelper;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.AdminChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.api.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class AbstractPageModContentFactoryImpl.
 */
public abstract class AbstractPageModContentFactoryImpl implements PageModeContentFactory {

	/** The Constant GENERAL_PAGE_MODE_PAGE_VISIT. */
	public static final String GENERAL_PAGE_MODE_PAGE_VISIT = "General Page Mode Page Visit";

	/** The Constant CURRENT_PAGE_VISIT_HISTORY. */
	public static final String CURRENT_PAGE_VISIT_HISTORY = "Current Page Visit History";

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory menuItemFactory;

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

	/** The admin chart data manager. */
	@Autowired
	private AdminChartDataManager adminChartDataManager;

	/**
	 * Instantiates a new abstract page mod content factory impl.
	 */
	protected AbstractPageModContentFactoryImpl() {
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
		if (parameters != null) {
			final int pageNr = getPageNr(parameters);
			final String cleanedString = parameters.replace("[" + pageNr + "]", "");

			return cleanedString.substring(cleanedString.lastIndexOf('/') + "/".length(), cleanedString.length());
		} else {
			return "";
		}
	}

	/**
	 * Gets the page nr.
	 *
	 * @param parameters
	 *            the parameters
	 * @return the page nr
	 */
	protected final int getPageNr(final String parameters) {
		String pageNrValue;
		if (parameters != null && parameters.contains("[") && parameters.contains("]")) {
			pageNrValue = parameters.substring(parameters.indexOf('[') + 1, parameters.lastIndexOf(']'));
		} else {
			pageNrValue = "";
		}

		int pageNr = 1;

		if (pageNrValue.length() > 0) {
			pageNr = Integer.parseInt(pageNrValue);
		}
		return pageNr;
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
	protected final ApplicationMenuItemFactory getMenuItemFactory() {
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
	 * Creates the panel content.
	 *
	 * @return the vertical layout
	 */
	protected final VerticalLayout createPanelContent() {
		final VerticalLayout panelContent = new VerticalLayout();
		panelContent.setMargin(true);
		panelContent.setWidth(100, Unit.PERCENTAGE);
		panelContent.setHeight(100, Unit.PERCENTAGE);
		panelContent.setStyleName("Header");
		return panelContent;
	}

	/**
	 * Creates the page visit history.
	 *
	 * @param pageName
	 *            the page name
	 * @param pageId
	 *            the page id
	 * @param panelContent
	 *            the panel content
	 */
	protected final void createPageVisitHistory(final String pageName, final String pageId,
			final VerticalLayout panelContent) {

		final TabSheet tabsheet = new TabSheet();
		tabsheet.setWidth(100, Unit.PERCENTAGE);
		tabsheet.setHeight(100, Unit.PERCENTAGE);

		panelContent.addComponent(tabsheet);
		panelContent.setExpandRatio(tabsheet, ContentRatio.LARGE);

		final HorizontalLayout tabContentPageItemRankHistory = new HorizontalLayout();
		tabContentPageItemRankHistory.setWidth(100, Unit.PERCENTAGE);
		tabContentPageItemRankHistory.setHeight(100, Unit.PERCENTAGE);
		final Tab tabPageItemRankHistory = tabsheet.addTab(tabContentPageItemRankHistory);

		tabPageItemRankHistory.setCaption(CURRENT_PAGE_VISIT_HISTORY);
		adminChartDataManager.createApplicationActionEventPageElementDailySummaryChart(tabContentPageItemRankHistory,
				pageName, pageId);

		final HorizontalLayout tabContentPageModeSummary = new HorizontalLayout();
		tabContentPageModeSummary.setWidth(100, Unit.PERCENTAGE);
		tabContentPageModeSummary.setHeight(100, Unit.PERCENTAGE);
		final Tab tabPageModeSummary = tabsheet.addTab(tabContentPageModeSummary);
		tabPageModeSummary.setCaption(GENERAL_PAGE_MODE_PAGE_VISIT);

		adminChartDataManager.createApplicationActionEventPageModeDailySummaryChart(tabContentPageModeSummary,
				pageName);

	}

}

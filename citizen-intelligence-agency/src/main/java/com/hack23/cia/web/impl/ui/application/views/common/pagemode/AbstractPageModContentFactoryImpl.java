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
package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.web.impl.ui.application.action.PageActionEventHelper;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.AdminChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.api.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractPageModContentFactoryImpl.
 */
public abstract class AbstractPageModContentFactoryImpl implements PageModeContentFactory {

	/** The Constant CURRENT_PAGE_VISIT_HISTORY. */
	public static final String CURRENT_PAGE_VISIT_HISTORY = "Current Page Visit History";

	/** The Constant DEFAULT_RESULTS_PER_PAGE. */
	public static final int DEFAULT_RESULTS_PER_PAGE = 250;

	/** The Constant GENERAL_PAGE_MODE_PAGE_VISIT. */
	public static final String GENERAL_PAGE_MODE_PAGE_VISIT = "General Page Mode Page Visit";

	/** The admin chart data manager. */
	@Autowired
	private AdminChartDataManager adminChartDataManager;

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/** The form factory. */
	@Autowired
	private FormFactory formFactory;

	/** The grid factory. */
	@Autowired
	private GridFactory gridFactory;

	/** The menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory menuItemFactory;

	/** The page action event helper. */
	@Autowired
	private PageActionEventHelper pageActionEventHelper;

	/** The page link factory. */
	@Autowired
	private PageLinkFactory pageLinkFactory;

	/**
	 * Instantiates a new abstract page mod content factory impl.
	 */
	protected AbstractPageModContentFactoryImpl() {
		super();
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

		final TabSheet tabsheet = createTabSheet(panelContent);

		final HorizontalLayout tabContentPageItemRankHistory = createTabContent(tabsheet, CURRENT_PAGE_VISIT_HISTORY);
		adminChartDataManager.createApplicationActionEventPageElementDailySummaryChart(tabContentPageItemRankHistory,
				pageName, pageId);

		final HorizontalLayout tabContentPageModeSummary = createTabContent(tabsheet, GENERAL_PAGE_MODE_PAGE_VISIT);
		adminChartDataManager.createApplicationActionEventPageModeDailySummaryChart(tabContentPageModeSummary,
				pageName);

	}

	/**
	 * Creates the panel content.
	 *
	 * @return the vertical layout
	 */
	protected static final VerticalLayout createPanelContent() {
		final VerticalLayout panelContent = new VerticalLayout();
		panelContent.setMargin(true);
		panelContent.setWidth(100, Unit.PERCENTAGE);
		panelContent.setHeight(100, Unit.PERCENTAGE);
		panelContent.setStyleName("v-layout-content-overview-panel-level1");
		return panelContent;
	}

	/**
	 * Gets the admin chart data manager.
	 *
	 * @return the admin chart data manager
	 */
	protected final AdminChartDataManager getAdminChartDataManager() {
		return adminChartDataManager;
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
	 * Gets the form factory.
	 *
	 * @return the form factory
	 */
	protected final FormFactory getFormFactory() {
		return formFactory;
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
	 * Gets the menu item factory.
	 *
	 * @return the menu item factory
	 */
	protected final ApplicationMenuItemFactory getMenuItemFactory() {
		return menuItemFactory;
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
	 * Gets the page id.
	 *
	 * @param parameters
	 *            the parameters
	 * @return the page id
	 */
	protected static final String getPageId(final String parameters) {
		return PageModeMenuCommand.getPageId(parameters);
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
	 * Gets the page nr.
	 *
	 * @param parameters the parameters
	 * @return the page nr
	 */
	protected static final int getPageNr(final String parameters) {
		final String cleanedString = parameters;
		String pageNrValue;
		if (cleanedString != null && cleanedString.contains("[") && cleanedString.contains("]")) {
			pageNrValue = cleanedString.substring(cleanedString.indexOf('[') + 1, cleanedString.lastIndexOf(']'));
		} else {
			pageNrValue = "";
		}

		int pageNr = 1;

		if (pageNrValue.length() > 0 && !pageNrValue.contains("/")) {
			pageNr = Integer.parseInt(pageNrValue);
		}
		return pageNr;
	}

	/**
	 * Creates a TabSheet and adds it to the given panel content.
	 *
	 * @param panelContent the panel content
	 * @return the created TabSheet
	 */
	private TabSheet createTabSheet(final VerticalLayout panelContent) {
		final TabSheet tabsheet = new TabSheet();
		tabsheet.setWidth(100, Unit.PERCENTAGE);
		tabsheet.setHeight(100, Unit.PERCENTAGE);

		panelContent.addComponent(tabsheet);
		panelContent.setExpandRatio(tabsheet, ContentRatio.LARGE);

		return tabsheet;
	}

	/**
	 * Creates a HorizontalLayout for a tab and adds it to the given TabSheet.
	 *
	 * @param tabsheet the TabSheet
	 * @param caption  the caption for the tab
	 * @return the created HorizontalLayout
	 */
	private HorizontalLayout createTabContent(final TabSheet tabsheet, final String caption) {
		final HorizontalLayout tabContent = new HorizontalLayout();
		tabContent.setWidth(100, Unit.PERCENTAGE);
		tabContent.setHeight(100, Unit.PERCENTAGE);
		final Tab tab = tabsheet.addTab(tabContent);
		tab.setCaption(caption);
		return tabContent;
	}
}

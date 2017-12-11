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
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractPageModContentFactoryImpl.
 */
public abstract class AbstractPageModContentFactoryImpl implements PageModeContentFactory {

	/** The Constant GENERAL_PAGE_MODE_PAGE_VISIT. */
	public static final String GENERAL_PAGE_MODE_PAGE_VISIT = "General Page Mode Page Visit";

	/** The Constant CURRENT_PAGE_VISIT_HISTORY. */
	public static final String CURRENT_PAGE_VISIT_HISTORY = "Current Page Visit History";

	/** The Constant LIMIT_FOR_DISPLAYING_START_END_LINKS. */
	private static final int LIMIT_FOR_DISPLAYING_START_END_LINKS = 5;

	/** The Constant PAGE_ONE. */
	private static final int PAGE_ONE = 1;

	/** The Constant PAGE_SEPARATOR. */
	private static final char PAGE_SEPARATOR = '/';

	/** The Constant SHOW. */
	private static final String SHOW = " :: Show ";

	/** The Constant RESULTS_PER_PAGE. */
	private static final String RESULTS_PER_PAGE = " results per page:";

	/** The Constant PAGES_TOTAL_RESULTS. */
	private static final String PAGES_TOTAL_RESULTS = " pages. Total results:";

	/** The Constant PAGE_HEADER. */
	private static final String PAGE_HEADER = "Page: ";

	/** The Constant NEXT_PAGE. */
	private static final String NEXT_PAGE = "next page";

	/** The Constant FIRST_PAGE. */
	private static final String FIRST_PAGE = "first page";

	/** The Constant LAST_PAGE. */
	private static final String LAST_PAGE = "last page";

	/** The Constant PREVIOUS_PAGE. */
	private static final String PREVIOUS_PAGE = "previous page";

	/** The Constant DEFAULT_RESULTS_PER_PAGE. */
	public static final int DEFAULT_RESULTS_PER_PAGE = 250;

	/** The Constant DISPLAY_SIZE_LG_DEVICE. */
	private static final int DISPLAY_SIZE_LG_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_MD_DEVICE. */
	private static final int DISPLAY_SIZE_MD_DEVICE = 4;

	/** The Constant DISPLAYS_SIZE_XM_DEVICE. */
	private static final int DISPLAYS_SIZE_XM_DEVICE = 6;

	/** The Constant DISPLAY_SIZE_XS_DEVICE. */
	private static final int DISPLAY_SIZE_XS_DEVICE = 12;

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

	protected final AdminChartDataManager getAdminChartDataManager() {
		return adminChartDataManager;
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

	/**
	 * Creates the paging controls.
	 *
	 * @param content
	 *            the content
	 * @param name
	 *            the name
	 * @param pageId
	 *            the page id
	 * @param size
	 *            the size
	 * @param pageNr
	 *            the page nr
	 * @param resultPerPage
	 *            the result per page
	 */
	protected final void createPagingControls(final AbstractOrderedLayout content, final String name, final String pageId, final int size, final int pageNr,
			final int resultPerPage) {
				final HorizontalLayout pagingControls = new HorizontalLayout();
				pagingControls.setSpacing(true);
				pagingControls.setMargin(true);

				final int maxPages = (size +resultPerPage-1) / resultPerPage;

				final StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(PAGE_HEADER)
				.append(pageNr)
				.append(PAGE_SEPARATOR)
				.append(maxPages)
				.append(PAGES_TOTAL_RESULTS)
				.append(size)
				.append(RESULTS_PER_PAGE)
				.append(resultPerPage)
				.append(SHOW);
				final Label pageInfo = new Label(stringBuilder.toString());
				pagingControls.addComponent(pageInfo);
				pagingControls.setExpandRatio(pageInfo, ContentRatio.SMALL);


				if (pageNr > PAGE_ONE) {
					addPagingLink(PREVIOUS_PAGE,name, pageId, pageNr -1,pagingControls);
				}

				if (maxPages > PAGE_ONE && pageNr < maxPages) {
					addPagingLink(NEXT_PAGE,name, pageId, pageNr +1,pagingControls);
				}

				if (maxPages > LIMIT_FOR_DISPLAYING_START_END_LINKS && pageNr > PAGE_ONE) {
					addPagingLink(FIRST_PAGE,name, pageId, 1,pagingControls);
				}

				if (maxPages > LIMIT_FOR_DISPLAYING_START_END_LINKS && pageNr < maxPages) {
					addPagingLink(LAST_PAGE,name, pageId, maxPages,pagingControls);
				}

				content.addComponent(pagingControls);
				content.setExpandRatio(pagingControls, ContentRatio.SMALL2);

			}

	/**
	 * Adds the paging link.
	 *
	 * @param label
	 *            the label
	 * @param name
	 *            the name
	 * @param pageId
	 *            the page id
	 * @param pageNr
	 *            the page nr
	 * @param pagingControls
	 *            the paging controls
	 */
	private void addPagingLink(final String label, final String name, final String pageId, final int pageNr, final HorizontalLayout pagingControls) {
		final Link previousPageLink = getPageLinkFactory().createAdminPagingLink(label,name, pageId, String.valueOf(pageNr));
		pagingControls.addComponent(previousPageLink);
		pagingControls.setExpandRatio(previousPageLink, ContentRatio.SMALL);
	}

	protected final void createRowItem(final ResponsiveRow row, final Button button, final String description) {
		final CssLayout layout = new CssLayout();
		layout.addStyleName("v-layout-content-overview-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();
		
		button.addStyleName("itembox");
		button.addStyleName("title");
		Responsive.makeResponsive(button);
		button.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(button);
	
		final Label descriptionLabel = new Label(description);
		descriptionLabel.addStyleName("itembox");
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(descriptionLabel);
	
		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE,DISPLAYS_SIZE_XM_DEVICE,DISPLAY_SIZE_MD_DEVICE,DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	protected final void createRowComponent(final ResponsiveRow row, final Component component, final String description) {
		final CssLayout layout = new CssLayout();
		layout.addStyleName(".v-layout-content-pagemode-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();

		final Label descriptionLabel = new Label(description);
		descriptionLabel.addStyleName("itembox");
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(descriptionLabel);

		component.addStyleName("itembox");
		component.addStyleName("title");
		Responsive.makeResponsive(component);
		component.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(component);	
	
		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE,DISPLAYS_SIZE_XM_DEVICE,DISPLAY_SIZE_MD_DEVICE,DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	
	
	
	
	protected final ResponsiveRow createGridLayout(final VerticalLayout panelContent) {
		final ResponsiveLayout layout = new ResponsiveLayout();
		Responsive.makeResponsive(layout);
		layout.addStyleName("v-layout-content-overview-panel-level1");
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setHeight(100, Unit.PERCENTAGE);
		panelContent.addComponent(layout);
		panelContent.setExpandRatio(layout, ContentRatio.LARGE);
		return layout.addRow();
	}

}

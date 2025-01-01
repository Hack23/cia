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
package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.web.impl.ui.application.action.PageActionEventHelper;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.AdminChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.api.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
		if (parameters != null) {
			String cleanedString = parameters;
			if (parameters.contains("[")) {
				cleanedString = cleanedString.replace(cleanedString.substring(cleanedString.indexOf('[') , cleanedString.lastIndexOf(']')+1), "");
			}

			return cleanedString.substring(cleanedString.lastIndexOf('/') + "/".length());
		} else {
			return "";
		}
	}

	/**
	 * Creates the page header.
	 *
	 * @param panel the panel
	 * @param panelContent the panel content
	 * @param header the header
	 * @param pageHeader the page header
	 * @param pageDescription the page description
	 */
	protected static final void createPageHeader(final Panel panel, final VerticalLayout panelContent, final String header, final String pageHeader, final String pageDescription) {
		panel.setCaption(header);
		LabelFactory.createHeader2Label(panelContent, pageHeader);
		final Label descriptionLabel = new Label(pageDescription);
		descriptionLabel.addStyleName("itembox");
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		panelContent.addComponent(descriptionLabel);
		panelContent.setExpandRatio(descriptionLabel,ContentRatio.SMALL);
	}

    /**
     * Adds an info row to the parent layout if value is not null or empty.
     *
     * @param parent the parent layout
     * @param caption the caption
     * @param value the value
     * @param icon the icon
     */
	protected final void addInfoRowIfNotNull(final VerticalLayout parent, final String caption, final String value,
            final VaadinIcons icon) {
        if (value != null && !value.trim().isEmpty() && !"null".equalsIgnoreCase(value)) {
            parent.addComponent(createInfoRow(caption, value, icon));
        }
    }

    /**
     * Creates a simple info row (caption and value) with optional icon.
     *
     * @param caption the field caption
     * @param value   the field value
     * @param icon    a VaadinIcons icon
     * @return a HorizontalLayout representing the info row
     */
    protected final HorizontalLayout createInfoRow(final String caption, final String value, VaadinIcons icon) {
    	return createInfoRow(caption,value,icon,null);
    }


	/**
	 * Creates a row displaying a caption and value, with optional icon and tooltip.
	 *
	 * @param caption the field caption
	 * @param value   the field value
	 * @param icon    a VaadinIcons icon for better visual cue
	 * @param tooltip optional tooltip to provide more info
	 * @return a HorizontalLayout representing the info row
	 */
	protected final HorizontalLayout createInfoRow(final String caption, final String value, VaadinIcons icon,
			final String tooltip) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("metric-label");
		layout.setWidthUndefined();

		if (icon != null) {
			final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
			iconLabel.addStyleName("card-info-icon");
			if (tooltip != null && !tooltip.isEmpty()) {
				iconLabel.setDescription(tooltip);
			}
			layout.addComponent(iconLabel);
		}

		final Label captionLabel = new Label(caption);
		captionLabel.addStyleName("card-info-caption");
		if (tooltip != null && !tooltip.isEmpty()) {
			captionLabel.setDescription(tooltip);
		}

		final Label valueLabel = new Label(value != null ? value : "");
		valueLabel.addStyleName("card-info-value");

		layout.addComponents(captionLabel, valueLabel);
		return layout;
	}

	/**
	 * Creates a section layout with a title and consistent styling.
	 *
	 * @param title the section title
	 * @return the vertical layout configured for the section
	 */
	protected final VerticalLayout createSectionLayout(String title) {
	    final VerticalLayout layout = new VerticalLayout();
	    layout.setSpacing(true);
	    layout.setMargin(true);
	    layout.addStyleName("card-details-column");
	    layout.setWidth("100%");

	    final Label header = new Label(title);
	    header.addStyleName("card-section-title");
	    layout.addComponent(header);

	    // Add some vertical padding after the header
	    final Label padding = new Label();
	    padding.setHeight("10px");
	    layout.addComponent(padding);

	    return layout;
	}

	/**
	 * Creates the metric row.
	 *
	 * @param icon the icon
	 * @param linkComponent the link component
	 * @param description the description
	 * @param valueText the value text
	 * @return the horizontal layout
	 */
	protected final HorizontalLayout createMetricRow(VaadinIcons icon, com.vaadin.ui.Component linkComponent,
			String description, String valueText) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("metric-label");
		layout.setWidthUndefined();

		final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
		iconLabel.setDescription(description);

		// Value displayed outside of the link
		Label valueLabel = null;
		if (valueText != null && !valueText.isEmpty()) {
			valueLabel = new Label(valueText);
			valueLabel.addStyleName("metric-value");
		}

		layout.addComponent(iconLabel);
		layout.addComponent(linkComponent);
		if (valueLabel != null) {
			layout.addComponent(valueLabel);
		}

		return layout;
	}

	protected final void createCardHeader(final VerticalLayout cardContent, final String titleText) {
		// Card Header
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		final Label titleLabel = new Label(titleText, ContentMode.HTML);
		titleLabel.addStyleName("card-title");
		titleLabel.setWidthUndefined();
		headerLayout.addComponent(titleLabel);

		cardContent.addComponent(headerLayout);

		// Divider line
		final Label divider = new Label("<hr/>", ContentMode.HTML);
		divider.addStyleName("card-divider");
		divider.setWidth("100%");
		cardContent.addComponent(divider);
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

}

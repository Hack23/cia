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
package com.hack23.cia.web.impl.ui.application.views.admin.dataquality.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DataQualityOverviewPageModContentFactoryImpl.
 * 
 * Displays the data quality monitoring dashboard for 4 OSINT data sources.
 * 
 * NOTE: Hardcoded data values are placeholders for Phase 1.
 * Future phases will implement dynamic data retrieval from database/services.
 */
@Component
public final class DataQualityOverviewPageModContentFactoryImpl extends AbstractPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_DATA_QUALITY_VIEW_NAME;

	/** The Constant DATA_QUALITY_MONITORING. */
	private static final String DATA_QUALITY_MONITORING = "Data Quality Monitoring";
	
	/** The Constant OVERVIEW_DESCRIPTION. */
	private static final String OVERVIEW_DESCRIPTION = "Monitor data quality metrics for 4 OSINT data sources: Riksdagen API, Election Authority, World Bank, and Financial Authority";

	/** The Constant RIKSDAGEN_API. */
	public static final String RIKSDAGEN_API = "Riksdagen API";

	/** The Constant ELECTION_AUTHORITY. */
	public static final String ELECTION_AUTHORITY = "Election Authority";

	/** The Constant WORLD_BANK. */
	public static final String WORLD_BANK = "World Bank";

	/** The Constant FINANCIAL_AUTHORITY. */
	public static final String FINANCIAL_AUTHORITY = "Financial Authority";

	/** The Constant DATA_FRESHNESS. */
	public static final String DATA_FRESHNESS = "Data Freshness";

	/** The Constant DATA_COMPLETENESS. */
	public static final String DATA_COMPLETENESS = "Data Completeness";

	/** The Constant DATA_ACCURACY. */
	public static final String DATA_ACCURACY = "Data Accuracy";

	/** The Constant ACTIVE_ALERTS. */
	public static final String ACTIVE_ALERTS = "Active Alerts";

	/**
	 * Instantiates a new data quality overview page mod content factory impl.
	 */
	public DataQualityOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		CardInfoRowUtil.createPageHeader(panel, content, DATA_QUALITY_MONITORING, AdminViewConstants.ADMIN_DATA_QUALITY, OVERVIEW_DESCRIPTION);

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();
		horizontalLayout.addStyleName("v-layout-content-overview-panel-level1");

		content.addComponent(horizontalLayout);
		content.setExpandRatio(horizontalLayout, ContentRatio.LARGE);

		// Create data source status section
		createDataSourceStatusSection(horizontalLayout);

		final VerticalLayout metricsLayout = new VerticalLayout();
		metricsLayout.setSizeFull();
		metricsLayout.addStyleName("v-layout-content-overview-panel-level2");

		content.addComponent(metricsLayout);
		content.setExpandRatio(metricsLayout, ContentRatio.LARGE);

		final ResponsiveRow grid = RowUtil.createGridLayout(metricsLayout);

		// Create data quality metrics cards
		createDataQualityMetricsCards(grid);

		return content;
	}

	/**
	 * Creates the data source status section.
	 *
	 * @param layout the layout
	 */
	private void createDataSourceStatusSection(final HorizontalLayout layout) {
		final VerticalLayout statusLayout = new VerticalLayout();
		statusLayout.setSizeFull();
		statusLayout.setSpacing(true);
		statusLayout.setMargin(true);

		final Label titleLabel = new Label("Data Source Status");
		titleLabel.addStyleName("h2");
		statusLayout.addComponent(titleLabel);

		// Riksdagen API status
		createDataSourceStatusCard(statusLayout, RIKSDAGEN_API, "Swedish Parliament", VaadinIcons.INSTITUTION, "✓ Active", "Last update: 2 hours ago");

		// Election Authority status
		createDataSourceStatusCard(statusLayout, ELECTION_AUTHORITY, "Valmyndigheten", VaadinIcons.CHECK_SQUARE, "✓ Active", "Last update: 12 hours ago");

		// World Bank status
		createDataSourceStatusCard(statusLayout, WORLD_BANK, "Open Data", VaadinIcons.GLOBE, "✓ Active", "Last update: 24 hours ago");

		// Financial Authority status
		createDataSourceStatusCard(statusLayout, FINANCIAL_AUTHORITY, "ESV", VaadinIcons.MONEY, "✓ Active", "Last update: 6 hours ago");

		layout.addComponent(statusLayout);
	}

	/**
	 * Creates a data source status card.
	 *
	 * @param layout the layout
	 * @param name the name
	 * @param description the description
	 * @param icon the icon
	 * @param status the status
	 * @param lastUpdate the last update
	 */
	private void createDataSourceStatusCard(final VerticalLayout layout, final String name, 
			final String description, final VaadinIcons icon, final String status, final String lastUpdate) {
		final HorizontalLayout cardLayout = new HorizontalLayout();
		cardLayout.setWidth("100%");
		cardLayout.setSpacing(true);
		cardLayout.addStyleName("data-source-card");

		// Use icon directly without HTML rendering
		final Label iconLabel = new Label("");
		iconLabel.addStyleName("data-source-icon");
		iconLabel.setIcon(icon);
		cardLayout.addComponent(iconLabel);

		final VerticalLayout infoLayout = new VerticalLayout();
		infoLayout.setSpacing(false);
		infoLayout.setMargin(false);

		final Label nameLabel = new Label(name);
		nameLabel.addStyleName("h3");
		infoLayout.addComponent(nameLabel);

		final Label descLabel = new Label(description);
		descLabel.addStyleName("data-source-description");
		infoLayout.addComponent(descLabel);

		final Label statusLabel = new Label(status);
		statusLabel.addStyleName("data-source-status-active");
		infoLayout.addComponent(statusLabel);

		final Label updateLabel = new Label(lastUpdate);
		updateLabel.addStyleName("data-source-last-update");
		infoLayout.addComponent(updateLabel);

		cardLayout.addComponent(infoLayout);
		cardLayout.setExpandRatio(infoLayout, 1.0f);

		layout.addComponent(cardLayout);
	}

	/**
	 * Creates the data quality metrics cards.
	 *
	 * @param grid the grid
	 */
	private void createDataQualityMetricsCards(final ResponsiveRow grid) {
		// Data Freshness Card
		final VerticalLayout freshnessCard = new VerticalLayout();
		freshnessCard.setSpacing(true);
		freshnessCard.setMargin(true);
		freshnessCard.addStyleName("metric-card");

		final Label freshnessTitle = new Label(DATA_FRESHNESS);
		freshnessTitle.addStyleName("h3");
		freshnessCard.addComponent(freshnessTitle);

		final Label freshnessValue = new Label("98.5%");
		freshnessValue.addStyleName("metric-value");
		freshnessCard.addComponent(freshnessValue);

		final Label freshnessDesc = new Label("Data updated within 24 hours");
		freshnessDesc.addStyleName("metric-description");
		freshnessCard.addComponent(freshnessDesc);

		RowUtil.createRowComponent(grid, freshnessCard, "Data Freshness Metric");

		// Completeness Card
		final VerticalLayout completenessCard = new VerticalLayout();
		completenessCard.setSpacing(true);
		completenessCard.setMargin(true);
		completenessCard.addStyleName("metric-card");

		final Label completenessTitle = new Label(DATA_COMPLETENESS);
		completenessTitle.addStyleName("h3");
		completenessCard.addComponent(completenessTitle);

		final Label completenessValue = new Label("96.2%");
		completenessValue.addStyleName("metric-value");
		completenessCard.addComponent(completenessValue);

		final Label completenessDesc = new Label("Expected records present");
		completenessDesc.addStyleName("metric-description");
		completenessCard.addComponent(completenessDesc);

		RowUtil.createRowComponent(grid, completenessCard, "Data Completeness Metric");

		// Accuracy Card
		final VerticalLayout accuracyCard = new VerticalLayout();
		accuracyCard.setSpacing(true);
		accuracyCard.setMargin(true);
		accuracyCard.addStyleName("metric-card");

		final Label accuracyTitle = new Label(DATA_ACCURACY);
		accuracyTitle.addStyleName("h3");
		accuracyCard.addComponent(accuracyTitle);

		final Label accuracyValue = new Label("99.1%");
		accuracyValue.addStyleName("metric-value");
		accuracyCard.addComponent(accuracyValue);

		final Label accuracyDesc = new Label("Validation checks passed");
		accuracyDesc.addStyleName("metric-description");
		accuracyCard.addComponent(accuracyDesc);

		RowUtil.createRowComponent(grid, accuracyCard, "Data Accuracy Metric");

		// Alerts Card
		final VerticalLayout alertsCard = new VerticalLayout();
		alertsCard.setSpacing(true);
		alertsCard.setMargin(true);
		alertsCard.addStyleName("metric-card");

		final Label alertsTitle = new Label(ACTIVE_ALERTS);
		alertsTitle.addStyleName("h3");
		alertsCard.addComponent(alertsTitle);

		final Label alertsValue = new Label("2");
		alertsValue.addStyleName("metric-value");
		alertsCard.addComponent(alertsValue);

		final Label alertsDesc = new Label("Quality issues detected");
		alertsDesc.addStyleName("metric-description");
		alertsCard.addComponent(alertsDesc);

		RowUtil.createRowComponent(grid, alertsCard, "Active Alerts Metric");
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page);
	}

	@Override
	public boolean validReference(final String parameters) {
		return true;
	}

}

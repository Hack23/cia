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
package com.hack23.cia.web.impl.ui.application.views.user.business.pagemode;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractBasicPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.server.Responsive;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class BusinessExecutiveDashboardOverviewPageModContentFactoryImpl.
 * <p>
 * Creates the main executive dashboard view with business metrics, KPIs, and charts
 * based on targets from BUSINESS_PRODUCT_DOCUMENT.md.
 * </p>
 */
@Component
public final class BusinessExecutiveDashboardOverviewPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant NAME. */
	private static final String NAME = UserViews.BUSINESS_EXECUTIVE_DASHBOARD_VIEW_NAME;
	
	/** Business metrics constants from BUSINESS_PRODUCT_DOCUMENT.md */
	private static final double YEAR1_TARGET_REVENUE = 29700.0; // €29.7k
	private static final double YEAR2_TARGET_REVENUE = 71280.0; // €71.28k
	private static final double YEAR3_TARGET_REVENUE = 142560.0; // €142.56k
	private static final double TOTAL_TAM = 46000000.0; // €46M
	
	/** Format pattern for percentage display */
	private static final String PERCENTAGE_FORMAT = "%.1f";
	
	/** Format pattern for currency with percentage */
	private static final String CURRENCY_PERCENTAGE_FORMAT = "€%.0f / €%.0f (%.1f%%)";
	
	/** Format pattern for funnel percentage */
	private static final String FUNNEL_PERCENTAGE_FORMAT = "%.2f";
	
	/** The user home menu item factory. */
	@Autowired
	private UserHomeMenuItemFactory userHomeMenuItemFactory;
	
	/**
	 * Instantiates a new business executive dashboard overview page mod content factory impl.
	 */
	public BusinessExecutiveDashboardOverviewPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the content.
	 *
	 * @param parameters the parameters
	 * @param menuBar the menu bar
	 * @param panel the panel
	 * @return the layout
	 */
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		panel.setContent(panelContent);

		final String pageId = getPageId(parameters);
		
		// Populate menu bar
		userHomeMenuItemFactory.createUserHomeMenuBar(menuBar, pageId);

		// Create page header using CardInfoRowUtil for consistency
		CardInfoRowUtil.createPageHeader(panel, panelContent, 
			"📊 Executive Dashboard", 
			"Business Metrics Dashboard", 
			"Business metrics tracking for BUSINESS_PRODUCT_DOCUMENT.md strategy execution. Monitors progress toward €46M TAM and €29.7k-€142.5k revenue targets.");
		
		// Create KPI Cards Section
		createKpiCardsSection(panelContent);
		
		// Create Market Opportunity Section
		createMarketOpportunitySection(panelContent);
		
		// Create Revenue Progress Section  
		createRevenueProgressSection(panelContent);
		
		// Create Customer Acquisition Section
		createCustomerAcquisitionSection(panelContent);
		
		// Create Product Development Section
		createProductDevelopmentSection(panelContent);
		
		// Track page event
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_USER_HOME_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;
	}

	/**
	 * Creates the KPI cards section.
	 *
	 * @param panelContent the panel content
	 */
	private void createKpiCardsSection(final VerticalLayout panelContent) {
		final Panel kpiPanel = new Panel();
		kpiPanel.setCaption("Key Performance Indicators");
		kpiPanel.setWidth("100%");
		kpiPanel.setHeightUndefined();
		Responsive.makeResponsive(kpiPanel);
		
		final HorizontalLayout kpiLayout = new HorizontalLayout();
		kpiLayout.setSpacing(true);
		kpiLayout.setWidth("100%");
		kpiLayout.setMargin(true);
		
		// Monthly Recurring Revenue (MRR)
		kpiLayout.addComponent(createKpiCard("💰", "Monthly Recurring Revenue", "€2,475", 
			"▲ 12.5% MoM", "Target: €2,475/month (Year 1)"));
		
		// Average Revenue Per User (ARPU)
		kpiLayout.addComponent(createKpiCard("👤", "Average Revenue Per User", "€99.00", 
			"Professional tier", "Target: €99/user"));
		
		// Active Customers
		kpiLayout.addComponent(createKpiCard("🎯", "Active Customers", "28", 
			"Free: 20 | Paid: 8", "Target: 28 (Year 1)"));
		
		// Monthly Churn Rate
		kpiLayout.addComponent(createKpiCard("📉", "Monthly Churn Rate", "3.2%", 
			"✅ Healthy", "Target: <5%"));
		
		// API Usage Growth
		kpiLayout.addComponent(createKpiCard("🚀", "API Request Growth", "45k requests", 
			"▲ 18.3% MoM", "Professional + Enterprise"));
		
		kpiPanel.setContent(kpiLayout);
		panelContent.addComponent(kpiPanel);
		panelContent.setExpandRatio(kpiPanel, ContentRatio.SMALL);
	}

	/**
	 * Creates a KPI card.
	 *
	 * @param icon the icon
	 * @param title the title
	 * @param value the value
	 * @param subtitle the subtitle
	 * @param target the target
	 * @return the vertical layout
	 */
	private VerticalLayout createKpiCard(final String icon, final String title, final String value, 
			final String subtitle, final String target) {
		final VerticalLayout card = new VerticalLayout();
		card.setSpacing(false);
		card.setMargin(true);
		card.setWidth("100%");
		card.addStyleName("kpi-card");
		
		final Label iconLabel = new Label(icon);
		iconLabel.addStyleName("h2");
		card.addComponent(iconLabel);
		
		final Label valueLabel = new Label(value);
		valueLabel.addStyleName("h3");
		valueLabel.addStyleName("kpi-value");
		card.addComponent(valueLabel);
		
		final Label titleLabel = new Label(title);
		titleLabel.addStyleName("v-label-small");
		card.addComponent(titleLabel);
		
		final Label subtitleLabel = new Label(subtitle);
		subtitleLabel.addStyleName("v-label-light");
		subtitleLabel.addStyleName("v-label-tiny");
		card.addComponent(subtitleLabel);
		
		final Label targetLabel = new Label(target);
		targetLabel.addStyleName("v-label-light");
		targetLabel.addStyleName("v-label-tiny");
		card.addComponent(targetLabel);
		
		return card;
	}

	/**
	 * Creates the market opportunity section.
	 *
	 * @param panelContent the panel content
	 */
	private void createMarketOpportunitySection(final VerticalLayout panelContent) {
		final Panel marketPanel = new Panel();
		marketPanel.setCaption("Total Addressable Market: €46M");
		marketPanel.setWidth("100%");
		marketPanel.setHeightUndefined();
		Responsive.makeResponsive(marketPanel);
		
		final VerticalLayout marketLayout = new VerticalLayout();
		marketLayout.setMargin(true);
		marketLayout.setSpacing(true);
		marketLayout.setWidth("100%");
		
		// Market segments from BUSINESS_PRODUCT_DOCUMENT.md
		marketLayout.addComponent(createMarketSegmentRow("Political Consulting", "€15M", 32.6, "#3498db"));
		marketLayout.addComponent(createMarketSegmentRow("Corporate Affairs", "€12M", 26.1, "#e74c3c"));
		marketLayout.addComponent(createMarketSegmentRow("Media & Journalism", "€8M", 17.4, "#f39c12"));
		marketLayout.addComponent(createMarketSegmentRow("Gov Transparency", "€6M", 13.0, "#27ae60"));
		marketLayout.addComponent(createMarketSegmentRow("Academic Research", "€5M", 10.9, "#9b59b6"));
		
		marketPanel.setContent(marketLayout);
		panelContent.addComponent(marketPanel);
		panelContent.setExpandRatio(marketPanel, ContentRatio.SMALL);
	}

	/**
	 * Creates a market segment row.
	 *
	 * @param name the name
	 * @param value the value
	 * @param percentage the percentage
	 * @param color the color
	 * @return the horizontal layout
	 */
	private HorizontalLayout createMarketSegmentRow(final String name, final String value, 
			final double percentage, final String color) {
		final HorizontalLayout row = new HorizontalLayout();
		row.setWidth("100%");
		row.setSpacing(true);
		
		final Label nameLabel = new Label(name);
		nameLabel.setWidth("200px");
		row.addComponent(nameLabel);
		
		// Using Label as a visual progress bar by setting width and applying CSS styling
		// This is styled via the "progress-bar" CSS class for visual effect
		final Label barLabel = new Label();
		barLabel.setWidth(percentage + "%");
		barLabel.setHeight("20px");
		barLabel.addStyleName("progress-bar");
		row.addComponent(barLabel);
		row.setExpandRatio(barLabel, 1.0f);
		
		final Label valueLabel = new Label(value + " (" + String.format(Locale.ENGLISH, PERCENTAGE_FORMAT, percentage) + "%)");
		valueLabel.setWidth("150px");
		row.addComponent(valueLabel);
		
		return row;
	}

	/**
	 * Creates the revenue progress section.
	 *
	 * @param panelContent the panel content
	 */
	private void createRevenueProgressSection(final VerticalLayout panelContent) {
		final Panel revenuePanel = new Panel();
		revenuePanel.setCaption("Revenue Progress vs. Target");
		revenuePanel.setWidth("100%");
		revenuePanel.setHeightUndefined();
		Responsive.makeResponsive(revenuePanel);
		
		final VerticalLayout revenueLayout = new VerticalLayout();
		revenueLayout.setMargin(true);
		revenueLayout.setSpacing(true);
		revenueLayout.setWidth("100%");
		
		final Label descLabel = new Label(
			"Quarterly revenue tracking against Year 1 target of €29,700. " +
			"Current run rate: €2,475/month × 12 = €29,700 annually.");
		descLabel.addStyleName("v-label-light");
		revenueLayout.addComponent(descLabel);
		
		// Quarterly data
		final double q1Actual = 6000;
		final double q2Actual = 7200;
		final double q3Actual = 7425;
		final double q4Actual = 7425;
		final double quarterTarget = 7425;
		final double annualTarget = YEAR1_TARGET_REVENUE;
		
		// Quarterly progress bars
		revenueLayout.addComponent(createRevenueQuarterRow("Q1 2024", q1Actual, quarterTarget, (q1Actual / quarterTarget * 100.0)));
		revenueLayout.addComponent(createRevenueQuarterRow("Q2 2024", q2Actual, quarterTarget, (q2Actual / quarterTarget * 100.0)));
		revenueLayout.addComponent(createRevenueQuarterRow("Q3 2024 (Projected)", q3Actual, quarterTarget, (q3Actual / quarterTarget * 100.0)));
		revenueLayout.addComponent(createRevenueQuarterRow("Q4 2024 (Projected)", q4Actual, quarterTarget, (q4Actual / quarterTarget * 100.0)));
		
		// Calculate YTD from actual quarters
		final double ytd = q1Actual + q2Actual;
		final double onTrackPercent = annualTarget > 0 ? (ytd / annualTarget * 100.0) : 0.0;
		
		final Label summaryLabel = new Label(
			String.format(Locale.ENGLISH, "Year-to-Date: €%,.0f | Year 1 Target: €%,.0f | On Track: %.1f%%", ytd, annualTarget, onTrackPercent));
		summaryLabel.addStyleName("h4");
		revenueLayout.addComponent(summaryLabel);
		
		revenuePanel.setContent(revenueLayout);
		panelContent.addComponent(revenuePanel);
		panelContent.setExpandRatio(revenuePanel, ContentRatio.SMALL);
	}

	/**
	 * Creates a revenue quarter row.
	 *
	 * @param quarter the quarter
	 * @param actual the actual
	 * @param target the target
	 * @param percentage the percentage
	 * @return the horizontal layout
	 */
	private HorizontalLayout createRevenueQuarterRow(final String quarter, final double actual, 
			final double target, final double percentage) {
		final HorizontalLayout row = new HorizontalLayout();
		row.setWidth("100%");
		row.setSpacing(true);
		
		final Label quarterLabel = new Label(quarter);
		quarterLabel.setWidth("200px");
		row.addComponent(quarterLabel);
		
		// Using Label as a visual progress bar by setting width and applying CSS styling
		// This is styled via the "progress-bar" CSS class with conditional state classes
		final Label barLabel = new Label();
		barLabel.setWidth(percentage + "%");
		barLabel.setHeight("30px");
		barLabel.addStyleName("progress-bar");
		barLabel.addStyleName(percentage >= 100 ? "v-label-success" : percentage >= 80 ? "v-label-warning" : "v-label-error");
		row.addComponent(barLabel);
		row.setExpandRatio(barLabel, 1.0f);
		
		final Label valueLabel = new Label(
			String.format(Locale.ENGLISH, CURRENCY_PERCENTAGE_FORMAT, actual, target, percentage));
		valueLabel.setWidth("200px");
		row.addComponent(valueLabel);
		
		return row;
	}

	/**
	 * Creates the customer acquisition section.
	 *
	 * @param panelContent the panel content
	 */
	private void createCustomerAcquisitionSection(final VerticalLayout panelContent) {
		final Panel customerPanel = new Panel();
		customerPanel.setCaption("Customer Acquisition Funnel");
		customerPanel.setWidth("100%");
		customerPanel.setHeightUndefined();
		Responsive.makeResponsive(customerPanel);
		
		final VerticalLayout customerLayout = new VerticalLayout();
		customerLayout.setMargin(true);
		customerLayout.setSpacing(true);
		customerLayout.setWidth("100%");
		
		// Funnel stages
		customerLayout.addComponent(createFunnelStage("Website Visitors", 5000, 100.0, "🌐"));
		customerLayout.addComponent(createFunnelStage("Free Tier Signups", 500, 10.0, "📝"));
		customerLayout.addComponent(createFunnelStage("Trial Users (API)", 100, 2.0, "🔬"));
		customerLayout.addComponent(createFunnelStage("Professional Subscribers", 25, 0.5, "💼"));
		customerLayout.addComponent(createFunnelStage("Enterprise Clients", 3, 0.06, "🏢"));
		
		final Label conversionLabel = new Label(
			"Conversion Rates: Free 10.0% | Paid 5.0% | Enterprise 3.0%");
		conversionLabel.addStyleName("h4");
		customerLayout.addComponent(conversionLabel);
		
		customerPanel.setContent(customerLayout);
		panelContent.addComponent(customerPanel);
		panelContent.setExpandRatio(customerPanel, ContentRatio.SMALL);
	}

	/**
	 * Creates a funnel stage.
	 *
	 * @param name the name
	 * @param count the count
	 * @param percentage the percentage
	 * @param icon the icon
	 * @return the horizontal layout
	 */
	private HorizontalLayout createFunnelStage(final String name, final int count, 
			final double percentage, final String icon) {
		final HorizontalLayout row = new HorizontalLayout();
		row.setWidth("100%");
		row.setSpacing(true);
		
		final Label iconLabel = new Label(icon);
		iconLabel.setWidth("30px");
		row.addComponent(iconLabel);
		
		final Label nameLabel = new Label(name);
		nameLabel.setWidth("200px");
		row.addComponent(nameLabel);
		
		// Using Label as a visual progress bar by setting width and applying CSS styling
		// This is styled via the "progress-bar" CSS class for visual effect
		final Label barLabel = new Label();
		barLabel.setWidth(percentage + "%");
		barLabel.setHeight("40px");
		barLabel.addStyleName("progress-bar");
		row.addComponent(barLabel);
		row.setExpandRatio(barLabel, 1.0f);
		
		final Label valueLabel = new Label(count + " (" + String.format(Locale.ENGLISH, FUNNEL_PERCENTAGE_FORMAT, percentage) + "%)");
		valueLabel.setWidth("150px");
		row.addComponent(valueLabel);
		
		return row;
	}

	/**
	 * Creates the product development section.
	 *
	 * @param panelContent the panel content
	 */
	private void createProductDevelopmentSection(final VerticalLayout panelContent) {
		final Panel productPanel = new Panel();
		productPanel.setCaption("Product Development Status");
		productPanel.setWidth("100%");
		productPanel.setHeightUndefined();
		Responsive.makeResponsive(productPanel);
		
		final HorizontalLayout productLayout = new HorizontalLayout();
		productLayout.setMargin(true);
		productLayout.setSpacing(true);
		productLayout.setWidth("100%");
		
		// Backlog column
		final VerticalLayout backlogLayout = createKanbanColumn("📋 Backlog", 
			new String[] {
				"White-Label Platform",
				"Predictive Analytics Service",
				"Mobile App Beta",
				"Norwegian Market Data"
			});
		productLayout.addComponent(backlogLayout);
		productLayout.setExpandRatio(backlogLayout, 1.0f);
		
		// In Progress column
		final VerticalLayout inProgressLayout = createKanbanColumn("🚧 In Progress", 
			new String[] {
				"Executive Dashboard",
				"Risk Intelligence Feed",
				"API v2.0 Development",
				"Customer Analytics"
			});
		productLayout.addComponent(inProgressLayout);
		productLayout.setExpandRatio(inProgressLayout, 1.0f);
		
		// Completed column
		final VerticalLayout completedLayout = createKanbanColumn("✅ Completed", 
			new String[] {
				"Political Intelligence API",
				"Advanced Analytics Suite",
				"Database Views (85 views)",
				"JSON Export Specs"
			});
		productLayout.addComponent(completedLayout);
		productLayout.setExpandRatio(completedLayout, 1.0f);
		
		productPanel.setContent(productLayout);
		panelContent.addComponent(productPanel);
		panelContent.setExpandRatio(productPanel, ContentRatio.SMALL);
	}

	/**
	 * Creates a kanban column.
	 *
	 * @param title the title
	 * @param items the items
	 * @return the vertical layout
	 */
	private VerticalLayout createKanbanColumn(final String title, final String[] items) {
		final VerticalLayout column = new VerticalLayout();
		column.setSpacing(true);
		column.setWidth("100%");
		
		final Label titleLabel = new Label(title);
		titleLabel.addStyleName("h4");
		column.addComponent(titleLabel);
		
		for (final String item : items) {
			final Panel itemPanel = new Panel();
			itemPanel.setWidth("100%");
			itemPanel.setHeightUndefined();
			
			final Label itemLabel = new Label(item);
			itemLabel.addStyleName("v-label-small");
			itemPanel.setContent(itemLabel);
			
			column.addComponent(itemPanel);
		}
		
		final Label countLabel = new Label(items.length + " items");
		countLabel.addStyleName("v-label-light");
		countLabel.addStyleName("v-label-tiny");
		column.addComponent(countLabel);
		
		return column;
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page);
	}

}

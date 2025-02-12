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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.github.markash.ui.component.card.CounterStatisticModel;
import com.github.markash.ui.component.card.CounterStatisticsCard;
import com.github.markash.ui.component.card.StatisticShow;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.model.internal.application.data.rules.impl.Status;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMainViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The DashboardViewOverviewPageModContentFactoryImpl creates a high-level dashboard
 * overview for the Citizen Intelligence Agency. It displays various metrics and
 * statistics about the monarch, government, parliament, and related financial and
 * risk data.
 */
@Component
public final class DashboardViewOverviewPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant ORG_CODE_GOV_OFFICES. */
	private static final String ORG_CODE_GOV_OFFICES = "202100-3831";

	/** The Constant ORG_CODE_RIKSDAG. */
	private static final String ORG_CODE_RIKSDAG = "202100-2627";

	/** The Constant ORG_CODE_MONARCH. */
	private static final String ORG_CODE_MONARCH = "202100-3484";

	/** The Constant FINANCIAL. */
	private static final String FINANCIAL = "Financial";

	/** The Constant PAGE_PREFIX. */
	private static final String PAGE_PREFIX = "#!";

	/** The Constant PAGE_SEPARATOR. */
	private static final char PAGE_SEPARATOR = '/';

	/** The Constant DISPLAY_SIZE_LG_DEVICE. */
	private static final int DISPLAY_SIZE_LG_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_MD_DEVICE. */
	private static final int DISPLAY_SIZE_MD_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_XS_DEVICE. */
	private static final int DISPLAY_SIZE_XS_DEVICE = 12;

	/** The Constant DISPLAYS_SIZE_XM_DEVICE. */
	private static final int DISPLAYS_SIZE_XM_DEVICE = 6;

	/** The esv api. */
	@Autowired
	private EsvApi esvApi;

	/**
	 * Instantiates a new dashboard view overview page mod content factory.
	 */
	public DashboardViewOverviewPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Create content for the dashboard overview page. Displays KPIs, statistics,
	 * and financial info for monarch, government, parliament, and related entities.
	 *
	 * @param parameters the navigation parameters
	 * @param menuBar    the main menu bar
	 * @param panel      the main panel holding the content
	 * @return a Layout containing the dashboard overview
	 */
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		final String pageId = getPageId(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent,
				CommonViewConstants.DASHBOARD_TITLE,
				CommonViewConstants.DASHBOARD_HEADER,
				CommonViewConstants.DASHBOARD_DESCRIPTION);

		final ResponsiveRow row = RowUtil.createGridLayout(panelContent);

		createDashboardMonarch(row);
		createDashboardGovernment(row);
		createDashboardParliament(row);
		createDashboardPartRiskByType(row);
		createDashboardPartRiskBySeverity(row);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DASHBOARD_VIEW, ApplicationEventGroup.USER,
				CommonsViews.DASHBOARD_VIEW_NAME, parameters, pageId);

		return panelContent;
	}

	/**
	 * Create a section for Monarch info.
	 *
	 * @param row the row
	 */
	private void createDashboardMonarch(final ResponsiveRow row) {
		final VerticalLayout monarchLayout = createLayoutWithTitle("Monarch");

		// Basic Monarch info
		addFullWidthLabel(monarchLayout, "Head of state(King): Carl Gustaf Folke Hubertus since 15 September 1973");
		addFullWidthLabel(monarchLayout, "Future head of state(Queen): Victoria Ingrid Alice Désirée");

		addMonarchIncomeSpending(monarchLayout);

		row.addColumn()
				.withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE, DISPLAY_SIZE_LG_DEVICE)
				.withComponent(monarchLayout);
	}

	/**
	 * Create a section for Government info.
	 *
	 * @param row the row
	 */
	@SuppressWarnings("unchecked")
	private void createDashboardGovernment(final ResponsiveRow row) {
		final VerticalLayout govLayout = createLayoutWithTitle("Government");
		final HorizontalLayout statsLayout = new HorizontalLayout();
		Responsive.makeResponsive(statsLayout);

		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberData = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

		final List<ViewRiksdagenGovermentRoleMember> ministryMembers = govermentRoleMemberData
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenGovermentRoleMember_.active);

		statsLayout.addComponent(createStatisticCard("Members", ministryMembers.size()));

		final DataContainer<ViewRiksdagenMinistry, String> ministryData = getApplicationManager()
				.getDataContainer(ViewRiksdagenMinistry.class);

		final List<ViewRiksdagenMinistry> ministries = ministryData
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenMinistry_.active);

		statsLayout.addComponent(createStatisticCard("Ministries", ministries.size()));

		final DataContainer<ViewRiksdagenPartySummary, String> partyData = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartySummary.class);

		final List<ViewRiksdagenPartySummary> partiesInGov = partyData.findListByProperty(new Object[] { Boolean.TRUE },
				ViewRiksdagenPartySummary_.activeGovernment);

		statsLayout.addComponent(createStatisticCard("Parties", partiesInGov.size()));

		final List<GovernmentBodyAnnualSummary> governmentBodies = esvApi.getData().get(2024);
		statsLayout.addComponent(createStatisticCard("Government bodies", governmentBodies.size()));

		final int totalGovHeadcount = governmentBodies.stream().mapToInt(GovernmentBodyAnnualSummary::getAnnualWorkHeadCount).sum();
		statsLayout.addComponent(createStatisticCard("Headcount", totalGovHeadcount));

		govLayout.addComponent(statsLayout);
		addIncomeSpending(govLayout, ORG_CODE_GOV_OFFICES, "Regeringskansliet(Government Offices)", 1191, 1216, -70);

		row.addColumn()
				.withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE, DISPLAY_SIZE_LG_DEVICE)
				.withComponent(govLayout);
	}

	/**
	 * Create a section for Parliament info.
	 *
	 * @param row the row
	 */
	@SuppressWarnings("unchecked")
	private void createDashboardParliament(final ResponsiveRow row) {
		final VerticalLayout parliamentLayout = createLayoutWithTitle("Parliament");
		final HorizontalLayout statsLayout = new HorizontalLayout();
		Responsive.makeResponsive(statsLayout);

		final DataContainer<ViewRiksdagenPolitician, String> parliamentData = getApplicationManager()
				.getDataContainer(ViewRiksdagenPolitician.class);
		final List<ViewRiksdagenPolitician> parliamentMembers = parliamentData
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.activeParliament);

		statsLayout.addComponent(createStatisticCard("Members", parliamentMembers.size()));

		final DataContainer<ViewRiksdagenPartySummary, String> partyData = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartySummary.class);
		final List<ViewRiksdagenPartySummary> parliamentParties = partyData.findListByProperty(new Object[] { Boolean.TRUE },
				ViewRiksdagenPartySummary_.activeParliament);
		statsLayout.addComponent(createStatisticCard("Parties", parliamentParties.size()));

		final DataContainer<ViewRiksdagenCommittee, String> committeeData = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommittee.class);
		final List<ViewRiksdagenCommittee> committees = committeeData
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenCommittee_.active);
		statsLayout.addComponent(createStatisticCard("Committees", committees.size()));

		parliamentLayout.addComponent(statsLayout);
		addParliamentIncomeSpending(parliamentLayout, ORG_CODE_RIKSDAG, "Riksdagsförvaltningen(the Riksdag administration)", 1719);

		// Additional panel or content (if needed)
		final VerticalLayout layoutPanel = createPanelContent();
		parliamentLayout.addComponent(layoutPanel);

		row.addColumn()
				.withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE, DISPLAY_SIZE_LG_DEVICE)
				.withComponent(parliamentLayout);
	}

	/**
	 * Create a section listing the number of risks by type.
	 *
	 * @param row the row
	 */
	private void createDashboardPartRiskByType(final ResponsiveRow row) {
		final VerticalLayout layout = createLayoutWithTitle(CommonViewConstants.RISK_BY_TYPE_TITLE);
		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		final DataContainer<RuleViolation, String> violationData = getApplicationManager()
				.getDataContainer(RuleViolation.class);
		final List<RuleViolation> ruleViolations = violationData.getAll();

		// Group by ResourceType and show counts
		final Map<ResourceType, List<RuleViolation>> groupedByType = ruleViolations.stream()
				.collect(Collectors.groupingBy(RuleViolation::getResourceType));

		for (final Entry<ResourceType, List<RuleViolation>> entry : groupedByType.entrySet()) {
			horizontalLayout.addComponent(createStatisticCard(entry.getKey().toString(), entry.getValue().size()));
		}

		layout.addComponent(horizontalLayout);

		row.addColumn()
				.withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE, DISPLAY_SIZE_LG_DEVICE)
				.withComponent(layout);
	}

	/**
	 * Create a section listing the number of risks by severity.
	 *
	 * @param row the row
	 */
	private void createDashboardPartRiskBySeverity(final ResponsiveRow row) {
		final VerticalLayout layout = createLayoutWithTitle("Number of risk by severity");
		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		final DataContainer<RuleViolation, String> violationData = getApplicationManager()
				.getDataContainer(RuleViolation.class);
		final List<RuleViolation> ruleViolations = violationData.getAll();

		// Group by Status and show counts
		final Map<Status, List<RuleViolation>> groupedByStatus = ruleViolations.stream()
				.collect(Collectors.groupingBy(RuleViolation::getStatus));

		for (final Entry<Status, List<RuleViolation>> entry : groupedByStatus.entrySet()) {
			horizontalLayout.addComponent(createStatisticCard(entry.getKey().toString(), entry.getValue().size()));
		}

		layout.addComponent(horizontalLayout);

		row.addColumn()
				.withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE, DISPLAY_SIZE_LG_DEVICE)
				.withComponent(layout);
	}

	/**
	 * Add income/spending overview for a given orgId.
	 *
	 * @param layout the layout
	 * @param orgId the org id
	 * @param linkTitle the link title
	 * @param incomeBillionSek the income billion sek
	 * @param spendingBillionSek the spending billion sek
	 * @param resultBillionSek the result billion sek
	 */
	private void addIncomeSpending(final VerticalLayout layout, final String orgId, final String linkTitle,
			final int incomeBillionSek, final int spendingBillionSek, final int resultBillionSek) {

		addGovernmentBodyLink(layout, orgId, linkTitle);

		final Label titleLabel = new Label(FINANCIAL);
		Responsive.makeResponsive(titleLabel);
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);

		final HorizontalLayout financialLayout = new HorizontalLayout();
		Responsive.makeResponsive(financialLayout);

		createHeadCountCard(financialLayout, orgId);

		financialLayout.addComponent(createStatisticCard("Income(B SEK)", incomeBillionSek));
		financialLayout.addComponent(createStatisticCard("Spending(B SEK)", spendingBillionSek));
		financialLayout.addComponent(createStatisticCard("Result(B SEK)", resultBillionSek));

		layout.addComponent(financialLayout);
	}

	/**
	 * Add Parliament income and spending info.
	 *
	 * @param layout the layout
	 * @param orgId the org id
	 * @param linkTitle the link title
	 * @param spendingMSek the spending M sek
	 */
	private void addParliamentIncomeSpending(final VerticalLayout layout, final String orgId, final String linkTitle,
			final int spendingMSek) {

		addGovernmentBodyLink(layout, orgId, linkTitle);

		final Label titleLabel = new Label(FINANCIAL);
		Responsive.makeResponsive(titleLabel);
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);

		final HorizontalLayout financialLayout = new HorizontalLayout();
		Responsive.makeResponsive(financialLayout);

		createHeadCountCard(financialLayout, orgId);

		financialLayout.addComponent(createStatisticCard("Spending(M SEK)", spendingMSek));

		layout.addComponent(financialLayout);
	}

	/**
	 * Add Monarch income and spending info.
	 *
	 * @param layout the layout
	 */
	private void addMonarchIncomeSpending(final VerticalLayout layout) {
		addGovernmentBodyLink(layout, ORG_CODE_MONARCH, "Kungliga hov- och slottsstaten(The Royal Court)");

		final Label titleLabel = new Label(FINANCIAL);
		Responsive.makeResponsive(titleLabel);
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);

		final HorizontalLayout financialLayout = new HorizontalLayout();
		Responsive.makeResponsive(financialLayout);

		createHeadCountCard(financialLayout, ORG_CODE_MONARCH);

		// Example: Just Spending info
		financialLayout.addComponent(createStatisticCard("Spending(M SEK)", 148));

		layout.addComponent(financialLayout);
	}

	/**
	 * Create a statistic card with a given title and value.
	 *
	 * @param title the title
	 * @param value the value
	 * @return the counter statistics card
	 */
	private CounterStatisticsCard createStatisticCard(final String title, final int value) {
		return new CounterStatisticsCard(
				VaadinIcons.WARNING,
				new CounterStatisticModel(title, value).withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
				title);
	}

	/**
	 * Create a vertical layout with a title label.
	 *
	 * @param title the title
	 * @return the vertical layout
	 */
	private static VerticalLayout createLayoutWithTitle(final String title) {
		final VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("leader-baseball-card");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();

		final Label titleLabel = new Label(title);
		Responsive.makeResponsive(titleLabel);
		titleLabel.addStyleName("title");
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);

		return layout;
	}

	/**
	 * Add a full-width label to a layout.
	 *
	 * @param layout the layout
	 * @param text the text
	 */
	private static void addFullWidthLabel(final VerticalLayout layout, final String text) {
		final Label label = new Label(text);
		Responsive.makeResponsive(label);
		label.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(label);
	}

	/**
	 * Add a link to a government body page.
	 *
	 * @param layout the layout
	 * @param orgId the org id
	 * @param linkTitle the link title
	 */
	private void addGovernmentBodyLink(final VerticalLayout layout, final String orgId, final String linkTitle) {
		final Link pageLink = new Link(linkTitle,
				new ExternalResource(PAGE_PREFIX + UserViews.GOVERNMENT_BODY_VIEW_NAME + PAGE_SEPARATOR + orgId));
		pageLink.setId(ViewAction.VISIT_GOVERNMENT_BODY_VIEW.name() + PAGE_SEPARATOR + orgId);
		pageLink.setIcon(VaadinIcons.GROUP);
		Responsive.makeResponsive(pageLink);
		pageLink.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(pageLink);
	}

	/**
	 * Create a card showing headcount for a given orgId.
	 *
	 * @param panelContent the panel content
	 * @param orgId the org id
	 */
	private void createHeadCountCard(final Layout panelContent, final String orgId) {
		final Map<String, List<GovernmentBodyAnnualSummary>> yearlyData = esvApi.getData().get(2024).stream()
				.collect(Collectors.groupingBy(GovernmentBodyAnnualSummary::getOrgNumber));

		final List<GovernmentBodyAnnualSummary> summaries = yearlyData.get(getPageId(orgId));

		if (summaries != null && !summaries.isEmpty()) {
			final GovernmentBodyAnnualSummary bodySummary = summaries.get(0);
			if (bodySummary != null) {
				final Map<Integer,GovernmentBodyAnnualSummary> dataPerBody = esvApi.getDataPerGovernmentBody(bodySummary.getName());
				final Set<Integer> yearKeys = dataPerBody.keySet();
				final GovernmentBodyAnnualSummary latestSummary = dataPerBody.get(Collections.max(yearKeys));

				panelContent.addComponent(
						new CounterStatisticsCard(
								VaadinIcons.WARNING,
								new CounterStatisticModel("Headcount", latestSummary.getHeadCount())
										.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
								"Headcount"));
			}
		}
	}

	/**
	 * Checks if this factory matches the given page and parameters.
	 */
	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandMainViewConstants.COMMAND_DASHBOARDVIEW_OVERVIEW.matches(page, parameters);
	}

}

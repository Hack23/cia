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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GovernmentBodyChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class DashboardViewOverviewPageModContentFactoryImpl.
 */
@Component
public final class DashboardViewOverviewPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant ORG_CODE_GOV_OFFICES. */
	private static final String ORG_CODE_GOV_OFFICES = "202100-3831";

	/** The Constant FINANCIAL. */
	private static final String FINANCIAL = "Financial";

	/** The Constant CITIZEN_INTELLIGENCE_AGENCY_MAIN. */
	private static final String CITIZEN_INTELLIGENCE_AGENCY_MAIN = "Citizen Intelligence Agency::Dashboard";

	/** The Constant NAME. */
	public static final String NAME = CommonsViews.DASHBOARD_VIEW_NAME;

	/** The Constant PAGE_PREFIX. */
	private static final String PAGE_PREFIX = "#!";

	/** The Constant PAGE_SEPARATOR. */
	private static final Character PAGE_SEPARATOR = '/';

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

	/** The government body chart data manager. */
	@Autowired
	private GovernmentBodyChartDataManager governmentBodyChartDataManager;


	/**
	 * Instantiates a new dashboard view overview page mod content factory impl.
	 */
	public DashboardViewOverviewPageModContentFactoryImpl() {
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
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		final String pageId = getPageId(parameters);

		panel.setCaption(NAME + "::" + CITIZEN_INTELLIGENCE_AGENCY_MAIN);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		LabelFactory.createHeader2Label(panelContent, "Dashboard Overview");

		final Label descriptionLabel = new Label(
				"Visualize political activity in Sweden, present key performance indicators and metadata for the actors on national level");
		descriptionLabel.addStyleName("itembox");
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		panelContent.addComponent(descriptionLabel);

		final ResponsiveRow row = RowUtil.createGridLayout(panelContent);

		createDashboardMonarch(row);

		createDashboardGovernment(row);
		createDashboardParliament(row);

		createDashboardPartRiskByType(row);
		createDashboardPartRiskBySeverity(row);

		panel.setCaption(NAME + "::" + CITIZEN_INTELLIGENCE_AGENCY_MAIN);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DASHBOARD_VIEW, ApplicationEventGroup.USER,
				CommonsViews.DASHBOARD_VIEW_NAME, parameters, pageId);

		return panelContent;

	}


	/**
	 * Creates the dashboard monarch.
	 *
	 * @param row the row
	 */
	private void createDashboardMonarch(final ResponsiveRow row) {

		final CssLayout layout = createLayoutWithTitle("Monarch");

		final Label headOfStateLabel = new Label("Head of state(King): Carl Gustaf Folke Hubertus since 15 September 1973");
		Responsive.makeResponsive(headOfStateLabel);
		headOfStateLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(headOfStateLabel);
		final Label nextHeadOfStateLabel = new Label("Future head of state(Queen): Victoria Ingrid Alice Désirée;");
		Responsive.makeResponsive(nextHeadOfStateLabel);
		nextHeadOfStateLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(nextHeadOfStateLabel);


		addMonarchIncomeSpending(layout);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	/**
	 * Creates the layout with title.
	 *
	 * @param title the title
	 * @return the css layout
	 */
	private static CssLayout createLayoutWithTitle(final String title) {
		final CssLayout layout = new CssLayout();
		layout.addStyleName("v-layout-content-overview-dashboard-panel-level2");
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
	 * Creates the dashboard government.
	 *
	 * @param row the row
	 */
	private void createDashboardGovernment(final ResponsiveRow row) {

		final CssLayout layout = createLayoutWithTitle("Government");

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

		final List<ViewRiksdagenGovermentRoleMember> listMinistryMembers = govermentRoleMemberDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenGovermentRoleMember_.active);

		horizontalLayout
				.addComponent(
						new CounterStatisticsCard(VaadinIcons.WARNING,
								new CounterStatisticModel("Members", listMinistryMembers.size())
										.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
								"Members"));

		final DataContainer<ViewRiksdagenMinistry, String> ministryDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenMinistry.class);

		final List<ViewRiksdagenMinistry> listMinistries = ministryDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenMinistry_.active);

		horizontalLayout
				.addComponent(new CounterStatisticsCard(
						VaadinIcons.WARNING, new CounterStatisticModel("Ministries", listMinistries.size())
								.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
						"Ministries"));

		final DataContainer<ViewRiksdagenPartySummary, String> partyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartySummary.class);

		final List<ViewRiksdagenPartySummary> listparties = partyDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPartySummary_.activeGovernment);

		horizontalLayout.addComponent(
				new CounterStatisticsCard(VaadinIcons.WARNING, new CounterStatisticModel("Parties", listparties.size())
						.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true), "Parties"));


		final List<GovernmentBodyAnnualSummary> governmentBodies = esvApi.getData().get(2022);
		horizontalLayout
		.addComponent(
				new CounterStatisticsCard(VaadinIcons.WARNING,
						new CounterStatisticModel("Government bodies", governmentBodies.size())
								.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
						"Government bodies"));
		horizontalLayout
		.addComponent(new CounterStatisticsCard(
				VaadinIcons.WARNING, new CounterStatisticModel("Headcount", governmentBodies.stream().mapToInt(GovernmentBodyAnnualSummary::getAnnualWorkHeadCount).sum())
						.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
				"Headcount"));


		layout.addComponent(horizontalLayout);

		addIncomeSpending(layout);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}


	/**
	 * Adds the income spending.
	 *
	 * @param layout the layout
	 */
	private void addIncomeSpending(final CssLayout layout) {

		final Link pageLink = new Link("Regeringskansliet(Government Offices)", new ExternalResource(PAGE_PREFIX
				+ UserViews.GOVERNMENT_BODY_VIEW_NAME + PAGE_SEPARATOR + ORG_CODE_GOV_OFFICES));
		pageLink.setId(ViewAction.VISIT_GOVERNMENT_BODY_VIEW.name() + PAGE_SEPARATOR
				+ ORG_CODE_GOV_OFFICES);
		pageLink.setIcon(VaadinIcons.GROUP);
		Responsive.makeResponsive(pageLink);
		pageLink.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(pageLink);


		final Label titleLabel = new Label(FINANCIAL);
		Responsive.makeResponsive(titleLabel);
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);
		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);


		createHeadCountCard(horizontalLayout,"202100-3831");

		horizontalLayout
		.addComponent(new CounterStatisticsCard(
				VaadinIcons.WARNING, new CounterStatisticModel("Income(B SEK)", 1191)
						.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
				"Income"));


		horizontalLayout
		.addComponent(new CounterStatisticsCard(
				VaadinIcons.WARNING, new CounterStatisticModel("Spending(B SEK)", 1216)
						.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
				"Spending"));

		horizontalLayout
		.addComponent(new CounterStatisticsCard(
				VaadinIcons.WARNING, new CounterStatisticModel("Result(B SEK)", -70)
						.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
				"Result"));


		layout.addComponent(horizontalLayout);
	}


	/**
	 * Adds the parliament income spending.
	 *
	 * @param layout the layout
	 */
	private void addParliamentIncomeSpending(final CssLayout layout) {

		final Link pageLink = new Link("Riksdagsförvaltningen(the Riksdag administration)", new ExternalResource(PAGE_PREFIX
						+ UserViews.GOVERNMENT_BODY_VIEW_NAME + PAGE_SEPARATOR + "202100-2627"));
		pageLink.setId(ViewAction.VISIT_GOVERNMENT_BODY_VIEW.name() + PAGE_SEPARATOR
				+ "202100-2627");
		pageLink.setIcon(VaadinIcons.GROUP);
		Responsive.makeResponsive(pageLink);
		pageLink.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(pageLink);

		final Label titleLabel = new Label(FINANCIAL);
		Responsive.makeResponsive(titleLabel);
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);
		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		createHeadCountCard(horizontalLayout,"202100-2627");

		horizontalLayout
		.addComponent(new CounterStatisticsCard(
				VaadinIcons.WARNING, new CounterStatisticModel("Spending(M SEK)", 1719)
						.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
				"Spending"));

		layout.addComponent(horizontalLayout);
	}

	/**
	 * Adds the monarch income spending.
	 *
	 * @param layout the layout
	 */
	private void addMonarchIncomeSpending(final CssLayout layout) {

		final Link pageLink = new Link("Kungliga hov- och slottsstaten(The Royal Court)", new ExternalResource(PAGE_PREFIX
				+ UserViews.GOVERNMENT_BODY_VIEW_NAME + PAGE_SEPARATOR + "202100-3484"));
		pageLink.setId(ViewAction.VISIT_GOVERNMENT_BODY_VIEW.name() + PAGE_SEPARATOR
				+ "202100-3484");
		pageLink.setIcon(VaadinIcons.GROUP);
		Responsive.makeResponsive(pageLink);
		pageLink.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(pageLink);

		final Label titleLabel = new Label(FINANCIAL);
		Responsive.makeResponsive(titleLabel);
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);
		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		createHeadCountCard(horizontalLayout,"202100-3484");


		horizontalLayout
		.addComponent(new CounterStatisticsCard(
				VaadinIcons.WARNING, new CounterStatisticModel("Spending(M SEK)", 148)
						.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
				"Spending"));

		layout.addComponent(horizontalLayout);
	}



	/**
	 * Creates the dashboard parliament.
	 *
	 * @param row the row
	 */
	private void createDashboardParliament(final ResponsiveRow row) {
		final CssLayout layout = createLayoutWithTitle("Parliament");

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		final DataContainer<ViewRiksdagenPolitician, String> parliamentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPolitician.class);

		final List<ViewRiksdagenPolitician> parliamentMembers = parliamentRoleMemberDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.activeParliament);

		horizontalLayout
				.addComponent(
						new CounterStatisticsCard(VaadinIcons.WARNING,
								new CounterStatisticModel("Members", parliamentMembers.size())
										.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
								"Members"));

		final DataContainer<ViewRiksdagenMinistry, String> ministryDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenMinistry.class);

		final DataContainer<ViewRiksdagenPartySummary, String> partyDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartySummary.class);

		final List<ViewRiksdagenPartySummary> listparties = partyDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPartySummary_.activeParliament);

		horizontalLayout.addComponent(
				new CounterStatisticsCard(VaadinIcons.WARNING, new CounterStatisticModel("Parties", listparties.size())
						.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true), "Parties"));

		final DataContainer<ViewRiksdagenCommittee, String> committeeDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenCommittee.class);

		final List<ViewRiksdagenCommittee> listCommittees = committeeDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenCommittee_.active);

		horizontalLayout.addComponent(
					new CounterStatisticsCard(VaadinIcons.WARNING, new CounterStatisticModel("Committees", listCommittees.size())
							.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true), "Committees"));

		layout.addComponent(horizontalLayout);

		addParliamentIncomeSpending(layout);

		VerticalLayout layoutPanel = createPanelContent();

		layout.addComponent(layoutPanel);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	/**
	 * Creates the dashboard part risk by type.
	 *
	 * @param row the row
	 */
	private void createDashboardPartRiskByType(final ResponsiveRow row) {
		final CssLayout layout = createLayoutWithTitle("Number of risk by each type");

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		final DataContainer<RuleViolation, String> dataContainer = getApplicationManager()
				.getDataContainer(RuleViolation.class);

		final List<RuleViolation> ruleViolations = dataContainer.getAll();

		for (final Entry<ResourceType, List<RuleViolation>> statusEntry : ruleViolations.stream()
				.collect(Collectors.groupingBy(RuleViolation::getResourceType)).entrySet()) {
			horizontalLayout.addComponent(new CounterStatisticsCard(VaadinIcons.WARNING,
					new CounterStatisticModel("" + statusEntry.getKey(), statusEntry.getValue().size())
							.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
					"" + statusEntry.getKey()));
		}

		layout.addComponent(horizontalLayout);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	/**
	 * Creates the dashboard part risk by severity.
	 *
	 * @param row the row
	 */
	private void createDashboardPartRiskBySeverity(final ResponsiveRow row) {
		final CssLayout layout = createLayoutWithTitle("Number of risk by severity");

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		final DataContainer<RuleViolation, String> dataContainer = getApplicationManager()
				.getDataContainer(RuleViolation.class);

		final List<RuleViolation> ruleViolations = dataContainer.getAll();

		for (final Entry<Status, List<RuleViolation>> statusEntry : ruleViolations.stream()
				.collect(Collectors.groupingBy(RuleViolation::getStatus)).entrySet()) {
			horizontalLayout.addComponent(new CounterStatisticsCard(VaadinIcons.WARNING,
					new CounterStatisticModel("" + statusEntry.getKey(), statusEntry.getValue().size())
							.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
					"" + statusEntry.getKey()));
		}

		layout.addComponent(horizontalLayout);
		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}


	/**
	 * Creates the head count chart.
	 *
	 * @param panelContent the panel content
	 * @param orgId the org id
	 */
	private void createHeadCountCard(final Layout panelContent, final String orgId) {


		final Map<String, List<GovernmentBodyAnnualSummary>> map = esvApi.getData().get(2022).stream().collect(Collectors.groupingBy(GovernmentBodyAnnualSummary::getOrgNumber));
		final List<GovernmentBodyAnnualSummary> list = map.get(getPageId(orgId));

		if (list != null && !list.isEmpty()) {
			final GovernmentBodyAnnualSummary findName = list.get(0);

			if (findName != null) {
				Map<Integer,GovernmentBodyAnnualSummary> dataPerGovernmentBody = esvApi.getDataPerGovernmentBody(findName.getName());
				Set<Integer> keySet = dataPerGovernmentBody.keySet();
				GovernmentBodyAnnualSummary governmentBodyAnnualSummary = dataPerGovernmentBody.get(Collections.max(keySet));
				panelContent.addComponent(new CounterStatisticsCard(
						VaadinIcons.WARNING, new CounterStatisticModel("Headcount", governmentBodyAnnualSummary.getHeadCount())
								.withShow(StatisticShow.Sum).withIconHidden().withShowOnlyStatistic(true),
						"Headcount"));
			}
		}
	}



	/**
	 * Matches.
	 *
	 * @param page the page
	 * @param parameters the parameters
	 * @return true, if successful
	 */
	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page)
				&& (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

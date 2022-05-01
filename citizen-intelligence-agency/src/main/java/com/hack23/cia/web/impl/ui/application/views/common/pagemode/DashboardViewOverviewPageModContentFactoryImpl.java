/*
 * Copyright 2010-2021 James Pether Sörling
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.github.markash.ui.component.card.CounterStatisticModel;
import com.github.markash.ui.component.card.CounterStatisticsCard;
import com.github.markash.ui.component.card.StatisticShow;
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
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode.ParliamentRiskPageModContentFactoryImpl.ComplianceCheckImpl;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DashboardViewOverviewPageModContentFactoryImpl.
 */
@Component
public final class DashboardViewOverviewPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant CITIZEN_INTELLIGENCE_AGENCY_MAIN. */
	private static final String CITIZEN_INTELLIGENCE_AGENCY_MAIN = "Citizen Intelligence Agency::Dashboard";

	/** The Constant NAME. */
	public static final String NAME = CommonsViews.DASHBOARD_VIEW_NAME;

	/** The Constant DISPLAY_SIZE_LG_DEVICE. */
	private static final int DISPLAY_SIZE_LG_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_MD_DEVICE. */
	private static final int DISPLAY_SIZE_MD_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_XS_DEVICE. */
	private static final int DISPLAY_SIZE_XS_DEVICE = 12;

	/** The Constant DISPLAYS_SIZE_XM_DEVICE. */
	private static final int DISPLAYS_SIZE_XM_DEVICE = 6;

	/**
	 * Instantiates a new dashboard view overview page mod content factory impl.
	 */
	public DashboardViewOverviewPageModContentFactoryImpl() {
		super();
	}

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

		createDashboardGovernment(row);
		createDashboardParliament(row);
		createDashboardPartRiskByType(row);
		createDashboardPartRiskBySeverity(row);

		panel.setCaption(NAME + "::" + CITIZEN_INTELLIGENCE_AGENCY_MAIN);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DASHBOARD_VIEW, ApplicationEventGroup.USER,
				CommonsViews.DASHBOARD_VIEW_NAME, parameters, pageId);

		return panelContent;

	}

	private void createDashboardGovernment(final ResponsiveRow row) {

		final CssLayout layout = new CssLayout();
		layout.addStyleName("v-layout-content-overview-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();

		final Label titleLabel = new Label("Government");
		Responsive.makeResponsive(titleLabel);
//		button.setStyleName(LINK_STYLE_NAME);
		titleLabel.addStyleName("title");
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);

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

		layout.addComponent(horizontalLayout);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	private void createDashboardParliament(final ResponsiveRow row) {

		final CssLayout layout = new CssLayout();
		layout.addStyleName("v-layout-content-overview-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();

		final Label titleLabel = new Label("Parliament");
		Responsive.makeResponsive(titleLabel);
//		button.setStyleName(LINK_STYLE_NAME);
		titleLabel.addStyleName("title");
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);

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

		layout.addComponent(horizontalLayout);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	private void createDashboardPartRiskByType(final ResponsiveRow row) {

		final CssLayout layout = new CssLayout();
		layout.addStyleName("v-layout-content-overview-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();

		final Label titleLabel = new Label("Number of risk by each type");
		Responsive.makeResponsive(titleLabel);
//		button.setStyleName(LINK_STYLE_NAME);
		titleLabel.addStyleName("title");
		titleLabel.setWidth(100, Unit.PERCENTAGE);

		layout.addComponent(titleLabel);

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		final DataContainer<RuleViolation, String> dataContainer = getApplicationManager()
				.getDataContainer(RuleViolation.class);

		final List<RuleViolation> ruleViolations = dataContainer.getAll();
		final List<ComplianceCheckImpl> checks = new ArrayList<>();

		for (final Entry<String, List<RuleViolation>> idMapViolations : ruleViolations.stream()
				.collect(Collectors.groupingBy(RuleViolation::getReferenceId)).entrySet()) {
			checks.add(new ComplianceCheckImpl(idMapViolations.getValue()));
		}

		Collections.sort(checks,
				(o1, o2) -> Integer.compare(o2.getRuleViolations().size(), o1.getRuleViolations().size()));

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

	private void createDashboardPartRiskBySeverity(final ResponsiveRow row) {

		final CssLayout layout = new CssLayout();
		layout.addStyleName("v-layout-content-overview-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();

		final Label titleLabel = new Label("Number of risk by severity");
		Responsive.makeResponsive(titleLabel);
		titleLabel.addStyleName("title");
		titleLabel.setWidth(100, Unit.PERCENTAGE);

		layout.addComponent(titleLabel);

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		Responsive.makeResponsive(horizontalLayout);

		final DataContainer<RuleViolation, String> dataContainer = getApplicationManager()
				.getDataContainer(RuleViolation.class);

		final List<RuleViolation> ruleViolations = dataContainer.getAll();
		final List<ComplianceCheckImpl> checks = new ArrayList<>();

		for (final Entry<String, List<RuleViolation>> idMapViolations : ruleViolations.stream()
				.collect(Collectors.groupingBy(RuleViolation::getReferenceId)).entrySet()) {
			checks.add(new ComplianceCheckImpl(idMapViolations.getValue()));
		}

		Collections.sort(checks,
				(o1, o2) -> Integer.compare(o2.getRuleViolations().size(), o1.getRuleViolations().size()));

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

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page)
				&& (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

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
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class
 * MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl.
 */
@Service
public final class MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl
		extends AbstractMinistryRankingPageModContentFactoryImpl {

	/** The Constant CHARTS. */
	private static final String CHARTS = "Ministries leader scoreboard";

	/** The Constant DISPLAY_SIZE_LG_DEVICE. */
	private static final int DISPLAY_SIZE_LG_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_MD_DEVICE. */
	private static final int DISPLAY_SIZE_MD_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_XS_DEVICE. */
	private static final int DISPLAY_SIZE_XS_DEVICE = 12;

	/** The Constant DISPLAYS_SIZE_XM_DEVICE. */
	private static final int DISPLAYS_SIZE_XM_DEVICE = 6;

	/** The Constant EXPENDITURE_GROUP_NAME. */
	private static final String EXPENDITURE_GROUP_NAME = "Utgiftsområdesnamn";

	/** The Constant INKOMSTTITELGRUPPSNAMN. */
	private static final String INKOMSTTITELGRUPPSNAMN = "Inkomsttitelgruppsnamn";

	/** The esv api. */
	@Autowired
	private EsvApi esvApi;

	/**
	 * Instantiates a new ministry ranking current parties leader scoreboard charts
	 * page mod content factory impl.
	 */
	public MinistryRankingCurrentPartiesLeaderScoreboardChartsPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getMinistryRankingMenuItemFactory().createMinistryRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		LabelFactory.createHeader2Label(panelContent, "Dashboard Government members");

		final Label descriptionLabel = new Label(
				"Visualize political activity in Sweden, present key performance indicators and metadata for the actors on national level");
		descriptionLabel.addStyleName("itembox");
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		panelContent.addComponent(descriptionLabel);

		final ResponsiveRow row = RowUtil.createGridLayout(panelContent);

		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

		final List<ViewRiksdagenGovermentRoleMember> listMinistryMembers = govermentRoleMemberDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenGovermentRoleMember_.active);

		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPolitician.class);

		final List<ViewRiksdagenPolitician> parliamentDataList = politicianDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.activeGovernment);
		final Map<String, List<ViewRiksdagenPolitician>> parliamentMap = parliamentDataList.stream()
				.collect(Collectors.groupingBy(ViewRiksdagenPolitician::getPersonId));

		final Map<Integer, List<GovernmentBodyAnnualSummary>> dataMap = esvApi.getData();
		final List<GovernmentBodyAnnualSummary> headCountGovermentBodies = dataMap.get(2023);
		final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyMinistryMap = headCountGovermentBodies
				.stream().collect(Collectors.groupingBy(GovernmentBodyAnnualSummary::getMinistry));

		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		final List<ViewRiksdagenPartyRoleMember> partyDataList = partyRoleMemberDataContainer
				.findListByProperty(new Object[] { Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.active);

		for (final ViewRiksdagenGovermentRoleMember viewRiksdagenGovermentRoleMember : listMinistryMembers) {
			createDashboardGovernmentMember(row, governmentBodyMinistryMap, viewRiksdagenGovermentRoleMember,
					parliamentMap.get(viewRiksdagenGovermentRoleMember.getPersonId()).get(0));

		}

		panel.setCaption(NAME + "::" + CHARTS + parameters);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

	private void createDashboardGovernmentMember(final ResponsiveRow row,
			final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyMinistryMap,
			final ViewRiksdagenGovermentRoleMember viewRiksdagenGovermentRoleMember,
			final ViewRiksdagenPolitician viewRiksdagenPolitician) {

		final CssLayout layout = new CssLayout();
		layout.addStyleName("v-layout-content-overview-dashboard-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();


		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);


		final Label titleLabel = new Label(viewRiksdagenGovermentRoleMember.getRoleCode() + " "
				+ viewRiksdagenGovermentRoleMember.getFirstName() + " " + viewRiksdagenGovermentRoleMember.getLastName()
				+ " (" + viewRiksdagenGovermentRoleMember.getParty() +")");
		layout.addComponent(getPageLinkFactory().createPoliticianPageLink(viewRiksdagenPolitician));
		Responsive.makeResponsive(titleLabel);
		titleLabel.addStyleName("title");
		titleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(titleLabel);


		final List<ViewRiksdagenPartyRoleMember> partyRole = partyRoleMemberDataContainer.findListByProperty(new Object[] { viewRiksdagenPolitician.getPersonId(), Boolean.TRUE },ViewRiksdagenPartyRoleMember_.personId, ViewRiksdagenPartyRoleMember_.active);

		if (partyRole.size()> 0) {
			final Label partyRoleLabel = new Label(partyRole.get(0).getRoleCode() + " (" + viewRiksdagenGovermentRoleMember.getParty() +")  since " +partyRole.get(0).getFromDate() );
			Responsive.makeResponsive(partyRoleLabel);
			partyRoleLabel.addStyleName("title");
			partyRoleLabel.setWidth(100, Unit.PERCENTAGE);
			layout.addComponent(partyRoleLabel);

		}


		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry = esvApi.getGovernmentBodyReportByMinistry();

		createPoliticaExperienceSummary(governmentBodyMinistryMap, viewRiksdagenGovermentRoleMember, viewRiksdagenPolitician, layout);
		createMinistryRoleSummary(reportByMinistry, governmentBodyMinistryMap, viewRiksdagenGovermentRoleMember, viewRiksdagenPolitician, layout);


		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	private void createMinistryRoleSummary(final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry, final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyMinistryMap,
			final ViewRiksdagenGovermentRoleMember viewRiksdagenGovermentRoleMember,
			final ViewRiksdagenPolitician viewRiksdagenPolitician, final CssLayout layout) {

		
		final Label ministryTitleLabel = new Label(viewRiksdagenGovermentRoleMember.getDetail());
		Responsive.makeResponsive(ministryTitleLabel);
		ministryTitleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(ministryTitleLabel);


		final List<GovernmentBodyAnnualSummary> governentBodies = governmentBodyMinistryMap
				.get(viewRiksdagenGovermentRoleMember.getDetail());
		
		final Label govBodiesLabel = new Label("Government bodies:" + governentBodies.size() + " , Total Headcount(all goverment bodies):" + governentBodies.stream().mapToInt(GovernmentBodyAnnualSummary::getAnnualWorkHeadCount).sum());
		Responsive.makeResponsive(govBodiesLabel);
		govBodiesLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(govBodiesLabel);

		final List<GovernmentBodyAnnualOutcomeSummary> listOutCome = reportByMinistry.get(viewRiksdagenGovermentRoleMember.getDetail());
		final Map<Integer, Double> annualIncomeSummaryMap = listOutCome.stream().filter(t -> t.getDescriptionFields().get(INKOMSTTITELGRUPPSNAMN) != null).collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear,Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)));
		final Map<Integer, Double> annualSpendingSummaryMap = listOutCome.stream().filter(t -> t.getDescriptionFields().get(EXPENDITURE_GROUP_NAME) != null).collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear,Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)));

		final Label govBodiesFinanceLabel = new Label("Income(B SEK):" + governentBodies.size() + " , Spending(B SEK):" + annualSpendingSummaryMap.get(2023)/1000);
		Responsive.makeResponsive(govBodiesFinanceLabel);
		govBodiesFinanceLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(govBodiesFinanceLabel);

		layout.addComponent(getPageLinkFactory().addMinistryPageLink(viewRiksdagenGovermentRoleMember.getDetail()));
		layout.addComponent(getPageLinkFactory().addMinistryGovermentBodiesPageLink(viewRiksdagenGovermentRoleMember.getDetail(),governentBodies.size()));		
		layout.addComponent(getPageLinkFactory().addMinistryGovermentBodiesHeadcountPageLink(viewRiksdagenGovermentRoleMember.getDetail(),governentBodies.stream().mapToInt(GovernmentBodyAnnualSummary::getAnnualWorkHeadCount).sum()));
		layout.addComponent(getPageLinkFactory().addMinistryGovermentBodiesIncomePageLink(viewRiksdagenGovermentRoleMember.getDetail(),annualIncomeSummaryMap.get(2023) /1000));		
		layout.addComponent(getPageLinkFactory().addMinistrGovermentBodiesSpendingPageLink(viewRiksdagenGovermentRoleMember.getDetail(),annualSpendingSummaryMap.get(2023)/1000));

	}


	private void createPoliticaExperienceSummary(final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyMinistryMap,
			final ViewRiksdagenGovermentRoleMember viewRiksdagenGovermentRoleMember,
			final ViewRiksdagenPolitician viewRiksdagenPolitician, final CssLayout layout) {

		final Label ministryDaysServedLabel = new Label("Hold position for " + viewRiksdagenGovermentRoleMember.getTotalDaysServed() + " (days) in " + viewRiksdagenGovermentRoleMember.getDetail());
		Responsive.makeResponsive(ministryDaysServedLabel);
		ministryDaysServedLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(ministryDaysServedLabel);

		final Label ministryTitleLabel = new Label("Political Experience");
		Responsive.makeResponsive(ministryTitleLabel);
		ministryTitleLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(ministryTitleLabel);
		
		{		
		final Label experienceLabel = new Label("Government(year):" + viewRiksdagenPolitician.getTotalDaysServedGovernment() / 365 + " , Party(year):" + viewRiksdagenPolitician.getTotalDaysServedParty() / 365 + " , Parliament(year):" + viewRiksdagenPolitician.getTotalDaysServedParliament() / 365);
		Responsive.makeResponsive(experienceLabel);
		experienceLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(experienceLabel);
		}
	}




	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());
	}

}

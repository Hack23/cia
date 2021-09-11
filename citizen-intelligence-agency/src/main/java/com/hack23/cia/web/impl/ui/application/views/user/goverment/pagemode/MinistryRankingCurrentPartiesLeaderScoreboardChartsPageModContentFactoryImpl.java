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
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
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


		final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

		List<ViewRiksdagenGovermentRoleMember> listMinistryMembers = govermentRoleMemberDataContainer.findListByProperty(
						new Object[] { Boolean.TRUE },
						ViewRiksdagenGovermentRoleMember_.active);

		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPolitician.class);

		
		List<ViewRiksdagenPolitician> parliamentDataList = politicianDataContainer.findListByProperty(new Object[] { Boolean.TRUE },ViewRiksdagenPolitician_.activeGovernment);
		Map<String, List<ViewRiksdagenPolitician>> parliamentMap = parliamentDataList.stream().collect(Collectors.groupingBy(ViewRiksdagenPolitician::getPersonId));


		final Map<Integer, List<GovernmentBodyAnnualSummary>> dataMap = esvApi.getData();
		final List<GovernmentBodyAnnualSummary> headCountGovermentBodies = dataMap.get(2020);
		Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyMinistryMap = headCountGovermentBodies.stream().collect(Collectors.groupingBy(GovernmentBodyAnnualSummary::getMinistry));

		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		List<ViewRiksdagenPartyRoleMember> partyDataList = partyRoleMemberDataContainer.findListByProperty(
				new Object[] { Boolean.TRUE },ViewRiksdagenPartyRoleMember_.active);
		
		for (ViewRiksdagenGovermentRoleMember viewRiksdagenGovermentRoleMember : listMinistryMembers) {
			ViewRiksdagenPolitician viewRiksdagenPolitician = parliamentMap.get(viewRiksdagenGovermentRoleMember.getPersonId()).get(0);
			
			StringBuilder entryBuilder = new StringBuilder();
			entryBuilder.append(viewRiksdagenGovermentRoleMember.getRoleCode());
			entryBuilder.append(" ");
			entryBuilder.append(viewRiksdagenGovermentRoleMember.getFirstName());
			entryBuilder.append(" ");
			entryBuilder.append(viewRiksdagenGovermentRoleMember.getLastName());
			entryBuilder.append(" ");
			entryBuilder.append(viewRiksdagenGovermentRoleMember.getParty());
					
			entryBuilder.append("\nTotal days served ");
			entryBuilder.append(viewRiksdagenGovermentRoleMember.getTotalDaysServed());
			entryBuilder.append(" , goverment experience ");
			entryBuilder.append(viewRiksdagenPolitician.getTotalDaysServedGovernment());
			entryBuilder.append(" days in ");
			entryBuilder.append(viewRiksdagenPolitician.getTotalMinistryAssignments());
			entryBuilder.append(" assignments");

			entryBuilder.append("\nExperience from EU ");
			entryBuilder.append(viewRiksdagenPolitician.getTotalDaysServedEu());
			entryBuilder.append(" , parliament experience ");
			entryBuilder.append(viewRiksdagenPolitician.getTotalDaysServedParliament());
			entryBuilder.append(" days in ");
			entryBuilder.append(viewRiksdagenPolitician.getTotalDaysServedCommittee());
			entryBuilder.append(" committes");
			// Rate level experience EU,parliament,party, commmittee,speaker			
			//totalAssignments=8,totalCommitteeAssignments=3,totalDaysServed=259,totalDaysServedCommittee=28,totalDaysServedEu=0,totalDaysServedGovernment=217,totalDaysServedParliament=14,totalDaysServedParty=0,totalDaysServedSpeaker=0,totalMinistryAssignments=3,totalPartyAssignments=0,totalSpeakerAssignments=0]
			
			
			if (viewRiksdagenGovermentRoleMember.getRoleCode().contains("minister")) {
				List<GovernmentBodyAnnualSummary> governentBodies = governmentBodyMinistryMap.get(viewRiksdagenGovermentRoleMember.getDetail());
				entryBuilder.append("\nHead of ");
				entryBuilder.append(viewRiksdagenGovermentRoleMember.getDetail());
				entryBuilder.append(": number of government bodies: ");
				entryBuilder.append(governentBodies.size());
			
								
				entryBuilder.append("\nTotal headcount :");
				entryBuilder.append(governentBodies.stream().collect(Collectors.summingInt(GovernmentBodyAnnualSummary::getAnnualWorkHeadCount)));
				
				
				// Graph exist at https://192.168.1.15:28443/#!ministryranking/GOVERNMENT_BODIES_HEADCOUNT and for specific ministries at https://192.168.1.15:28443/#!ministry/GOVERNMENT_BODIES_HEADCOUNT/Kulturdepartementet
				
				// Income https://192.168.1.15:28443/#!ministry/GOVERNMENT_BODIES_INCOME/Kulturdepartementet
				
				// Expenditure https://192.168.1.15:28443/#!ministry/GOVERNMENT_BODIES_EXPENDITURE/Kulturdepartementet
				
				
			} else {
				entryBuilder.append("Supports Head of");
				entryBuilder.append(viewRiksdagenGovermentRoleMember.getDetail());
			}
			
			final VerticalLayout layout = new VerticalLayout();
			layout.setSizeFull();

			
			Label entry = new Label(entryBuilder.toString());
			layout.addComponent(entry);
			panelContent.addComponent(layout);
		}

	
		panel.setCaption(NAME + "::" + CHARTS + parameters);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.CURRENTMINISTRIESLEADERSCORECARD.toString());
	}

}

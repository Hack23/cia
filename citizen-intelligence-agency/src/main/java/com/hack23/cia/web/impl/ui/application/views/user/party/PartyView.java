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
package com.hack23.cia.web.impl.ui.application.views.user.party;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractGroupView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class PartyView.
 */
@Service
@Scope("prototype")
@VaadinView(value = PartyView.NAME, cached = true)
public final class PartyView extends AbstractGroupView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_VIEW_NAME;

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

	/** The chart data manager. */
	@Autowired
	private transient ChartDataManager chartDataManager;

	/** The menu item factory. */
	@Autowired
	private transient MenuItemFactory menuItemFactory;

	/** The grid factory. */
	@Autowired
	private transient GridFactory gridFactory;

	/** The form factory. */
	@Autowired
	private transient FormFactory formFactory;



	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
	 * .ViewChangeEvent)
	 */
	@Override
	public void enter(final ViewChangeEvent event) {

		final String parameters = event.getParameters();

		if (parameters != null) {



			final String pageId = parameters
					.substring(parameters.lastIndexOf('/') + "/".length(),
							parameters.length());


			final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
					.getDataContainer(ViewRiksdagenParty.class);

			final DataContainer<ViewRiksdagenPartySummary, String> partySummarydataContainer = applicationManager
					.getDataContainer(ViewRiksdagenPartySummary.class);

			final ViewRiksdagenParty viewRiksdagenParty = dataContainer
					.load(pageId);




			if (viewRiksdagenParty != null) {

				menuItemFactory.createPartyMenuBar(getBarmenu(), pageId);

				final VerticalLayout panelContent = new VerticalLayout();
				panelContent.setSizeFull();
				panelContent.setMargin(true);


				if (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
						|| parameters.contains(PageMode.Overview.toString())) {

					panelContent.addComponent(new Label("Overview"));

					panelContent.addComponent(pageLinkFactory.addPartyPageLink(viewRiksdagenParty));


					formFactory.addTextFields(
							panelContent,
							new BeanItem<ViewRiksdagenParty>(viewRiksdagenParty),
							ViewRiksdagenParty.class,
							Arrays.asList(new String[] { "partyName", "partyId",
									"headCount", "partyNumber", "registeredDate",
							"website" }));

					final ViewRiksdagenPartySummary viewRiksdagenPartySummary = partySummarydataContainer
							.load(pageId);

					if (viewRiksdagenPartySummary != null) {

						formFactory.addTextFields(panelContent,
								new BeanItem<ViewRiksdagenPartySummary>(
										viewRiksdagenPartySummary),
										ViewRiksdagenPartySummary.class,
										Arrays.asList(new String[] { "active",
												"firstAssignmentDate", "lastAssignmentDate",
												"currentAssignments", "totalAssignments",
												"totalDaysServed", "activeEu", "totalActiveEu",
												"totalDaysServedEu", "activeGovernment",
												"totalActiveGovernment",
												"totalDaysServedGovernment", "activeCommittee",
												"totalActiveCommittee",
												"totalDaysServedCommittee", "activeParliament",
												"totalActiveParliament",
										"totalDaysServedParliament" }));
					}

				} else if (parameters.contains(PageMode.Charts.toString())) {

					panelContent.addComponent(new Label("Charts"));

				} else if (parameters.contains(PartyPageMode.DocumentHistory.toString())) {

					panelContent.addComponent(new Label("Document History"));

					final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPoliticianDocument.class);

					final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<ViewRiksdagenPoliticianDocument>(
							ViewRiksdagenPoliticianDocument.class,
							politicianDocumentDataContainer.findOrderedListByProperty(
									ViewRiksdagenPoliticianDocument_.partyShortCode,
									pageId,ViewRiksdagenPoliticianDocument_.madePublicDate));

					final Grid politicianDocumentBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							politicianDocumentDataSource, "Member Document history",
							new String[] { "id", "docId", "referenceName",
									"partyShortCode", "personReferenceId",
									"roleDescription", "documentType", "subType",
									"org", "label", "rm", "madePublicDate",
									"numberValue", "status", "title", "subTitle",
									"tempLabel", "orderNumber" }, new String[] { "id",
									"partyShortCode", "personReferenceId",
									"numberValue", "orderNumber", "tempLabel" },
									"docId",
									new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME,"docId"), null);

					panelContent
					.addComponent(politicianDocumentBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.DocumentActivity.toString())) {
					panelContent.addComponent(new Label("Document Activity"));

					final DCharts createDocumentHistoryChart = chartDataManager.createDocumentHistoryPartyChart(pageId);
					panelContent.addComponent(createDocumentHistoryChart);

				} else if (parameters.contains(PartyPageMode.CurrentMembers.toString())) {

					panelContent.addComponent(new Label("Current Members"));

					final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPolitician.class);

					final BeanItemContainer<ViewRiksdagenPolitician> politicianDataSource = new BeanItemContainer<ViewRiksdagenPolitician>(
							ViewRiksdagenPolitician.class,
							politicianDataContainer.findListByProperty(new Object[] { viewRiksdagenParty.getPartyId(),
									true },
									ViewRiksdagenPolitician_.party,ViewRiksdagenPolitician_.active));

					final Grid partyMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							politicianDataSource, "Politicians", new String[] {
									"personId", "firstName", "lastName", "party",
									"gender", "bornYear", "totalAssignments",
									"currentAssignments", "firstAssignmentDate",
									"lastAssignmentDate", "totalDaysServed",
									"totalDaysServedParliament",
									"totalDaysServedCommittee",
									"totalDaysServedGovernment", "totalDaysServedEu",

									"active", "activeEu", "activeGovernment",
									"activeCommittee", "activeParliament",

									"activeParty", "activeSpeaker",
									"totalDaysServedSpeaker", "totalDaysServedParty",

									"totalPartyAssignments",
									"totalMinistryAssignments",
									"totalCommitteeAssignments",
									"totalSpeakerAssignments",

									"currentPartyAssignments",
									"currentMinistryAssignments",
									"currentCommitteeAssignments",
							"currentSpeakerAssignments" }, null, "personId",
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(partyMemberBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.MemberHistory.toString())) {

					panelContent.addComponent(new Label("MemberHistory"));

					final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPolitician.class);

					final BeanItemContainer<ViewRiksdagenPolitician> politicianDataSource = new BeanItemContainer<ViewRiksdagenPolitician>(
							ViewRiksdagenPolitician.class,
							politicianDataContainer.getAllBy(
									ViewRiksdagenPolitician_.party,
									viewRiksdagenParty.getPartyId()));

					final Grid partyMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							politicianDataSource, "Politicians", new String[] {
									"personId", "firstName", "lastName", "party",
									"gender", "bornYear", "totalAssignments",
									"currentAssignments", "firstAssignmentDate",
									"lastAssignmentDate", "totalDaysServed",
									"totalDaysServedParliament",
									"totalDaysServedCommittee",
									"totalDaysServedGovernment", "totalDaysServedEu",

									"active", "activeEu", "activeGovernment",
									"activeCommittee", "activeParliament",

									"activeParty", "activeSpeaker",
									"totalDaysServedSpeaker", "totalDaysServedParty",

									"totalPartyAssignments",
									"totalMinistryAssignments",
									"totalCommitteeAssignments",
									"totalSpeakerAssignments",

									"currentPartyAssignments",
									"currentMinistryAssignments",
									"currentCommitteeAssignments",
							"currentSpeakerAssignments" }, null, "personId",
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(partyMemberBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.CurrentLeaders.toString())) {

					panelContent.addComponent(new Label("Current Leaders"));

					final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPartyRoleMember.class);

						final BeanItemContainer<ViewRiksdagenPartyRoleMember> currentPartyMemberDataSource = new BeanItemContainer<ViewRiksdagenPartyRoleMember>(
								ViewRiksdagenPartyRoleMember.class,
								partyRoleMemberDataContainer.findListByProperty(
										new Object[] { viewRiksdagenParty.getPartyId(),
												true },
												ViewRiksdagenPartyRoleMember_.party,
												ViewRiksdagenPartyRoleMember_.active));

						final Grid currentPartyBeanItemGrid = gridFactory.createBasicBeanItemGrid(
								currentPartyMemberDataSource, "Current Leaders",
								new String[] { "roleId", "personId", "firstName",
										"lastName", "party", "active", "detail",
										"roleCode", "fromDate", "toDate",
								"totalDaysServed" }, new String[] { "roleId",
										"personId", "detail", "roleCode" }, null,
								new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

						panelContent.addComponent(currentPartyBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.LeaderHistory.toString())) {

					panelContent.addComponent(new Label("LeaderHistory"));

					final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPartyRoleMember.class);

					final BeanItemContainer<ViewRiksdagenPartyRoleMember> partyRoleMemberDataSource = new BeanItemContainer<ViewRiksdagenPartyRoleMember>(
							ViewRiksdagenPartyRoleMember.class,
							partyRoleMemberDataContainer.getAllBy(
									ViewRiksdagenPartyRoleMember_.party,
									viewRiksdagenParty.getPartyId()));

					final Grid partyRoleMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							partyRoleMemberDataSource, "Leader History", new String[] {
									"roleId", "personId", "firstName", "lastName",
									"party", "active", "detail", "roleCode",
									"fromDate", "toDate", "totalDaysServed" },
									new String[] { "roleId", "personId", "party", "detail",
							"roleCode" }, null,
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(partyRoleMemberBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.GovernmentRoles.toString())) {

					panelContent.addComponent(new Label("Government Roles"));


					final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

					final BeanItemContainer<ViewRiksdagenGovermentRoleMember> currentGovermentMemberDataSource = new BeanItemContainer<ViewRiksdagenGovermentRoleMember>(
							ViewRiksdagenGovermentRoleMember.class,
							govermentRoleMemberDataContainer.findListByProperty(
									new Object[] { viewRiksdagenParty.getPartyId(),
											true },
											ViewRiksdagenGovermentRoleMember_.party,
											ViewRiksdagenGovermentRoleMember_.active));

					final Grid currentGovermentMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							currentGovermentMemberDataSource,
							"Current Members",
							new String[] { "roleId", "personId", "firstName",
									"lastName", "detail", "active", "detail",
									"roleCode", "fromDate", "toDate", "totalDaysServed" },
									new String[] { "roleId", "personId", "party" }, null,
									new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent
					.addComponent(currentGovermentMemberBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.CommitteeRoles.toString())) {

					panelContent.addComponent(new Label("CommitteeRoles"));

					final DataContainer<ViewRiksdagenCommitteeRoleMember, String> committeeRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenCommitteeRoleMember.class);

					final BeanItemContainer<ViewRiksdagenCommitteeRoleMember> committeeMemberDataSource = new BeanItemContainer<ViewRiksdagenCommitteeRoleMember>(
							ViewRiksdagenCommitteeRoleMember.class,
							committeeRoleMemberDataContainer.findListByProperty(
									new Object[] { viewRiksdagenParty.getPartyId(),
											true },
											ViewRiksdagenCommitteeRoleMember_.party,
											ViewRiksdagenCommitteeRoleMember_.active));

					final Grid committeeMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							committeeMemberDataSource, "Current Members", new String[] {
									"roleId", "personId", "firstName", "lastName",
									"detail", "active", "detail", "roleCode",
									"fromDate", "toDate", "totalDaysServed" },
									new String[] { "roleId", "personId", "party" }, null,
									new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent
					.addComponent(committeeMemberBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.CommitteeBallotDecisionSummary.toString())) {

					panelContent.addComponent(new Label("CommitteeBallotDecisionSummary"));

				} else if (parameters.contains(PartyPageMode.VoteHistory.toString())) {

					panelContent.addComponent(new Label("VoteHistory"));

					final BeanItemContainer<ViewRiksdagenVoteDataBallotPartySummary> partyBallotDataSource = new BeanItemContainer<ViewRiksdagenVoteDataBallotPartySummary>(
							ViewRiksdagenVoteDataBallotPartySummary.class,
							chartDataManager.getViewRiksdagenVoteDataBallotPartySummary(
									pageId));

					final Grid partynBallotsBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							partyBallotDataSource,
							"Ballots",
							null,
							null,
							null,
							null, null);

					panelContent
					.addComponent(partynBallotsBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.PartyWonDailySummaryChart.toString())) {

					panelContent.addComponent(new Label("PartyWonDailySummaryChart"));
					panelContent.addComponent(chartDataManager.createPartyLineChart(pageId));

				}
				getPanel().setContent(panelContent);
				getPanel().setCaption(
						"Party:" + viewRiksdagenParty.getPartyName());

				pageActionEventHelper.createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);


		}
		}

	}



}
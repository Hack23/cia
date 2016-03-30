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
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary_;
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
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
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
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class PartyView.
 */
@Service
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(value = PartyView.NAME, cached = true)
public final class PartyView extends AbstractGroupView {

	private static final String GENERAL_PAGE_MODE_PAGE_VISIT = "General Page Mode Page Visit";

	private static final String CURRENT_PAGE_VISIT_HISTORY = "Current Page Visit History";

	private static final String PARTY_WON_DAILY_SUMMARY_CHART = "PartyWonDailySummaryChart";

	private static final String VOTE_HISTORY = "VoteHistory";

	private static final String COMMITTEE_BALLOT_DECISION_SUMMARY = "CommitteeBallotDecisionSummary";

	private static final String COMMITTEE_ROLES = "CommitteeRoles";

	private static final String GOVERNMENT_ROLES = "Government Roles";

	private static final String LEADER_HISTORY = "LeaderHistory";

	private static final String CURRENT_LEADERS = "Current Leaders";

	private static final String MEMBER_HISTORY = "MemberHistory";

	private static final String OVERVIEW = "Overview";

	private static final String CHARTS_NOT_IMPLEMENTED = "Charts Not Implemented";

	private static final String GHANT_NOT_IMPLEMENTED = "Ghant Not Implemented";

	private static final String DOCUMENT_HISTORY = "Document History";

	private static final String DOCUMENT_ACTIVITY = "Document Activity";

	private static final String CURRENT_MEMBERS = "Current Members";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_VIEW_NAME;

	/** The application manager. */
	@Autowired
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
	 * Instantiates a new party view.
	 */
	public PartyView() {
		super();
	}


	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}



	//@Secured({ "ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
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
						|| parameters.contains(PageMode.OVERVIEW.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(OVERVIEW));

					panelContent.addComponent(pageLinkFactory.addPartyPageLink(viewRiksdagenParty));


					formFactory.addTextFields(
							panelContent,
							new BeanItem<>(viewRiksdagenParty),
							ViewRiksdagenParty.class,
							Arrays.asList(new String[] { "partyName", "partyId",
									"headCount", "partyNumber", "registeredDate",
							"website" }));

					final ViewRiksdagenPartySummary viewRiksdagenPartySummary = partySummarydataContainer
							.load(pageId);

					if (viewRiksdagenPartySummary != null) {

						formFactory.addTextFields(panelContent,
								new BeanItem<>(
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

				} else if (parameters.contains(PageMode.CHARTS.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(CHARTS_NOT_IMPLEMENTED));

				} else if (parameters.contains(PartyPageMode.ROLEGHANT.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(GHANT_NOT_IMPLEMENTED));

				} else if (parameters.contains(PartyPageMode.DOCUMENTHISTORY.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(DOCUMENT_HISTORY));

					final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPoliticianDocument.class);

					final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<>(
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

				} else if (parameters.contains(PartyPageMode.DOCUMENTACTIVITY.toString())) {
					panelContent.addComponent(LabelFactory.createHeader2Label(DOCUMENT_ACTIVITY));

					final DCharts createDocumentHistoryChart = chartDataManager.createDocumentHistoryPartyChart(pageId);
					panelContent.addComponent(createDocumentHistoryChart);

				} else if (parameters.contains(PartyPageMode.CURRENTMEMBERS.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(CURRENT_MEMBERS));

					final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPolitician.class);

					final BeanItemContainer<ViewRiksdagenPolitician> politicianDataSource = new BeanItemContainer<>(
							ViewRiksdagenPolitician.class,
							politicianDataContainer.findListByProperty(new Object[] { viewRiksdagenParty.getPartyId(),
									Boolean.TRUE},
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

				} else if (parameters.contains(PartyPageMode.MEMBERHISTORY.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(MEMBER_HISTORY));

					final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPolitician.class);

					final BeanItemContainer<ViewRiksdagenPolitician> politicianDataSource = new BeanItemContainer<>(
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

				} else if (parameters.contains(PartyPageMode.CURRENTLEADERS.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(CURRENT_LEADERS));

					final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPartyRoleMember.class);

						final BeanItemContainer<ViewRiksdagenPartyRoleMember> currentPartyMemberDataSource = new BeanItemContainer<>(
								ViewRiksdagenPartyRoleMember.class,
								partyRoleMemberDataContainer.findListByProperty(
										new Object[] { viewRiksdagenParty.getPartyId(),
												Boolean.TRUE },
												ViewRiksdagenPartyRoleMember_.party,
												ViewRiksdagenPartyRoleMember_.active));

						final Grid currentPartyBeanItemGrid = gridFactory.createBasicBeanItemGrid(
								currentPartyMemberDataSource, CURRENT_LEADERS,
								new String[] { "roleId", "personId", "firstName",
										"lastName", "party", "active", "detail",
										"roleCode", "fromDate", "toDate",
								"totalDaysServed" }, new String[] { "roleId",
										"personId", "detail"}, null,
								new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

						panelContent.addComponent(currentPartyBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.LEADERHISTORY.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(LEADER_HISTORY));

					final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPartyRoleMember.class);

					final BeanItemContainer<ViewRiksdagenPartyRoleMember> partyRoleMemberDataSource = new BeanItemContainer<>(
							ViewRiksdagenPartyRoleMember.class,
							partyRoleMemberDataContainer.getAllBy(
									ViewRiksdagenPartyRoleMember_.party,
									viewRiksdagenParty.getPartyId()));

					final Grid partyRoleMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							partyRoleMemberDataSource, "Leader History", new String[] {
									"roleId", "personId", "firstName", "lastName",
									"party", "active", "detail", "roleCode",
									"fromDate", "toDate", "totalDaysServed" },
									new String[] { "roleId", "personId", "party", "detail"}, null,
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(partyRoleMemberBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.GOVERNMENTROLES.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(GOVERNMENT_ROLES));


					final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

					final BeanItemContainer<ViewRiksdagenGovermentRoleMember> currentGovermentMemberDataSource = new BeanItemContainer<>(
							ViewRiksdagenGovermentRoleMember.class,
							govermentRoleMemberDataContainer.findListByProperty(
									new Object[] { viewRiksdagenParty.getPartyId(),
											Boolean.TRUE },
											ViewRiksdagenGovermentRoleMember_.party,
											ViewRiksdagenGovermentRoleMember_.active));

					final Grid currentGovermentMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							currentGovermentMemberDataSource,
							CURRENT_MEMBERS,
							new String[] { "roleId", "personId", "firstName",
									"lastName", "detail", "active", "detail",
									"roleCode", "fromDate", "toDate", "totalDaysServed" },
									new String[] { "roleId", "personId", "party" }, null,
									new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent
					.addComponent(currentGovermentMemberBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.COMMITTEEROLES.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(COMMITTEE_ROLES));

					final DataContainer<ViewRiksdagenCommitteeRoleMember, String> committeeRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenCommitteeRoleMember.class);

					final BeanItemContainer<ViewRiksdagenCommitteeRoleMember> committeeMemberDataSource = new BeanItemContainer<>(
							ViewRiksdagenCommitteeRoleMember.class,
							committeeRoleMemberDataContainer.findListByProperty(
									new Object[] { viewRiksdagenParty.getPartyId(),
											Boolean.TRUE },
											ViewRiksdagenCommitteeRoleMember_.party,
											ViewRiksdagenCommitteeRoleMember_.active));

					final Grid committeeMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							committeeMemberDataSource, CURRENT_MEMBERS, new String[] {
									"roleId", "personId", "firstName", "lastName",
									"detail", "active", "detail", "roleCode",
									"fromDate", "toDate", "totalDaysServed" },
									new String[] { "roleId", "personId", "party" }, null,
									new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent
					.addComponent(committeeMemberBeanItemGrid);

				} else if (parameters.contains(PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(COMMITTEE_BALLOT_DECISION_SUMMARY));

					final DataContainer<ViewRiksdagenCommitteeBallotDecisionPartySummary, ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId> committeeBallotDecisionPartyDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenCommitteeBallotDecisionPartySummary.class);

					final List<ViewRiksdagenCommitteeBallotDecisionPartySummary> decisionPartySummaryList = committeeBallotDecisionPartyDataContainer.findOrderedListByEmbeddedProperty(ViewRiksdagenCommitteeBallotDecisionPartySummary.class, ViewRiksdagenCommitteeBallotDecisionPartySummary_.embeddedId, ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId.class, ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId_.party, pageId, ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId_.issue);


					final BeanItemContainer<ViewRiksdagenCommitteeBallotDecisionPartySummary> committeeBallotDecisionPartyDataSource = new BeanItemContainer<>(
							ViewRiksdagenCommitteeBallotDecisionPartySummary.class,
							decisionPartySummaryList);

					final Grid committeeBallotDecisionPartyBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							committeeBallotDecisionPartyDataSource,
							"Committee Ballot Decision Party Summary",
							null,
							null,
							null,
							null, null);

					panelContent
					.addComponent(committeeBallotDecisionPartyBeanItemGrid);


				} else if (parameters.contains(PartyPageMode.VOTEHISTORY.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(VOTE_HISTORY));

					final BeanItemContainer<ViewRiksdagenVoteDataBallotPartySummary> partyBallotDataSource = new BeanItemContainer<>(
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

				} else if (parameters.contains(PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(PARTY_WON_DAILY_SUMMARY_CHART));
					panelContent.addComponent(chartDataManager.createPartyLineChart(pageId));

				} else if (parameters.contains(PageMode.PAGEVISITHISTORY.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(CURRENT_PAGE_VISIT_HISTORY));
					panelContent.addComponent(chartDataManager.createApplicationActionEventPageElementDailySummaryChart(NAME,pageId));

					panelContent.addComponent(LabelFactory.createHeader2Label(GENERAL_PAGE_MODE_PAGE_VISIT));
					panelContent.addComponent(chartDataManager.createApplicationActionEventPageModeDailySummaryChart(NAME));
				}


				getPanel().setContent(panelContent);
				getPanel().setCaption(
						"Party:" + viewRiksdagenParty.getPartyName());

				pageActionEventHelper.createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);


		}
		}

	}





}
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
package com.hack23.cia.web.impl.ui.application.views.user.committee;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionSummary_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember_;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
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
 * The Class CommitteeView.
 */
@Service
@Scope("prototype")
@VaadinView(value = CommitteeView.NAME, cached = true)
public final class CommitteeView extends AbstractGroupView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.COMMITTEE_VIEW_NAME;

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

			final String pageId = parameters.substring(parameters.lastIndexOf('/') + "/".length(), parameters.length());

			final DataContainer<ViewRiksdagenCommittee, String> dataContainer = applicationManager
					.getDataContainer(ViewRiksdagenCommittee.class);

			final ViewRiksdagenCommittee viewRiksdagenCommittee = dataContainer.load(pageId);

			if (viewRiksdagenCommittee != null) {

				menuItemFactory.createCommitteeeMenuBar(getBarmenu(), pageId);
				final VerticalLayout panelContent = new VerticalLayout();
				panelContent.setSizeFull();
				panelContent.setMargin(true);

				if (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
						|| parameters.contains(PageMode.Overview.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Overview"));


					panelContent.addComponent(pageLinkFactory.addCommitteePageLink(viewRiksdagenCommittee));


					formFactory.addTextFields(panelContent, new BeanItem<ViewRiksdagenCommittee>(viewRiksdagenCommittee),
							ViewRiksdagenCommittee.class,
							Arrays.asList(new String[] { "embeddedId.detail", "active", "firstAssignmentDate",
									"lastAssignmentDate", "totalAssignments", "totalDaysServed",
									"currentMemberSize" }));

				} else if (parameters.contains(CommitteePageMode.DOCUMENT_HISTORY.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Document History"));



					final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPoliticianDocument.class);

					final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<ViewRiksdagenPoliticianDocument>(
							ViewRiksdagenPoliticianDocument.class,
							politicianDocumentDataContainer.findOrderedListByProperty(ViewRiksdagenPoliticianDocument_.org,
									viewRiksdagenCommittee.getEmbeddedId().getOrgCode().replace(" ", "")
											.replace("_", "").trim(),ViewRiksdagenPoliticianDocument_.madePublicDate));

					final Grid politicianDocumentBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							politicianDocumentDataSource, "Documents",
							new String[] { "id", "docId", "referenceName", "partyShortCode", "personReferenceId",
									"roleDescription", "documentType", "subType", "org", "label", "rm",
									"madePublicDate", "numberValue", "status", "title", "subTitle", "tempLabel",
									"orderNumber" },
							new String[] { "id", "numberValue", "orderNumber", "tempLabel", "personReferenceId",
									"org" },
							"docId", new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME,"docId"), null);

					panelContent.addComponent(politicianDocumentBeanItemGrid);

				} else if (parameters.contains(CommitteePageMode.DocumentActivity.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Document Activity"));


					final DCharts createDocumentHistoryChart = chartDataManager
							.createDocumentHistoryChartByOrg(viewRiksdagenCommittee.getEmbeddedId().getOrgCode());

					panelContent.addComponent(createDocumentHistoryChart);

				} else if (parameters.contains(CommitteePageMode.DecisionTypeDailySummary.toString())) {
					panelContent.addComponent(LabelFactory.createHeader2Label("Decision Type Daily Summary"));

					final DCharts createDecisionTypeChart = chartDataManager
							.createDecisionTypeChart(viewRiksdagenCommittee.getEmbeddedId().getOrgCode());
					panelContent.addComponent(createDecisionTypeChart);
				} else if (parameters.contains(CommitteePageMode.BallotDecisionSummary.toString())) {
					panelContent.addComponent(LabelFactory.createHeader2Label("Ballot Decision Summary"));


					final DataContainer<ViewRiksdagenCommitteeBallotDecisionSummary, ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId> committeeBallotDecisionPartyDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenCommitteeBallotDecisionSummary.class);

					List<ViewRiksdagenCommitteeBallotDecisionSummary> decisionPartySummaryList = committeeBallotDecisionPartyDataContainer.findOrderedListByProperty(ViewRiksdagenCommitteeBallotDecisionSummary_.org, pageId.toUpperCase(Locale.ENGLISH), ViewRiksdagenCommitteeBallotDecisionSummary_.createdDate);


					final BeanItemContainer<ViewRiksdagenCommitteeBallotDecisionSummary> committeeBallotDecisionPartyDataSource = new BeanItemContainer<ViewRiksdagenCommitteeBallotDecisionSummary>(
							ViewRiksdagenCommitteeBallotDecisionSummary.class,
							decisionPartySummaryList);

					final Grid committeeBallotDecisionPartyBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							committeeBallotDecisionPartyDataSource,
							"Committee Ballot Decision Summary",
							null,
							null,
							null,
							null, null);

					panelContent
					.addComponent(committeeBallotDecisionPartyBeanItemGrid);


				} else if (parameters.contains(CommitteePageMode.DecisionSummary.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Decision Summary"));

				} else if (parameters.contains(CommitteePageMode.CURRENT_MEMBERS.toString())) {
					panelContent.addComponent(LabelFactory.createHeader2Label("Current Members"));

					final DataContainer<ViewRiksdagenCommitteeRoleMember, String> committeeRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenCommitteeRoleMember.class);

					final BeanItemContainer<ViewRiksdagenCommitteeRoleMember> currentMembersMemberDataSource = new BeanItemContainer<ViewRiksdagenCommitteeRoleMember>(
							ViewRiksdagenCommitteeRoleMember.class,
							committeeRoleMemberDataContainer.findListByProperty(
									new Object[] { viewRiksdagenCommittee.getEmbeddedId().getDetail(), true },
									ViewRiksdagenCommitteeRoleMember_.detail,
									ViewRiksdagenCommitteeRoleMember_.active));

					final Grid currentMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							currentMembersMemberDataSource, "Current Members",
							new String[] { "roleId", "personId", "firstName", "lastName", "party", "active", "detail",
									"roleCode", "fromDate", "toDate", "totalDaysServed" },
							new String[] { "roleId", "personId", "detail" }, null,
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(currentMemberBeanItemGrid);

				} else if (parameters.contains(CommitteePageMode.MemberHistory.toString())) {
					panelContent.addComponent(LabelFactory.createHeader2Label("Member History"));

					final DataContainer<ViewRiksdagenCommitteeRoleMember, String> committeeRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenCommitteeRoleMember.class);

					final BeanItemContainer<ViewRiksdagenCommitteeRoleMember> committeeRoleMemberDataSource = new BeanItemContainer<ViewRiksdagenCommitteeRoleMember>(
							ViewRiksdagenCommitteeRoleMember.class,
							committeeRoleMemberDataContainer.getAllBy(ViewRiksdagenCommitteeRoleMember_.detail,
									viewRiksdagenCommittee.getEmbeddedId().getDetail()));

					final Grid commmitteeRoleMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							committeeRoleMemberDataSource, "Member History",
							new String[] { "roleId", "personId", "firstName", "lastName", "party", "active", "detail",
									"roleCode", "fromDate", "toDate", "totalDaysServed" },
							new String[] { "roleId", "personId", "detail" }, null,
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(commmitteeRoleMemberBeanItemGrid);

				} else if (parameters.contains(CommitteePageMode.RoleGhant.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("RoleGhant"));
				} else if (parameters.contains(PageMode.PageVisitHistory.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Current Page Visit History"));
					panelContent.addComponent(chartDataManager.createApplicationActionEventPageElementDailySummaryChart(NAME,pageId));

					panelContent.addComponent(LabelFactory.createHeader2Label("General Page Mode Page Visit"));
					panelContent.addComponent(chartDataManager.createApplicationActionEventPageModeDailySummaryChart(NAME));

				}

				getPanel().setContent(panelContent);
				getPanel().setCaption("Committee:" + viewRiksdagenCommittee.getEmbeddedId().getDetail());
				pageActionEventHelper.createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);

			}

		}
	}

}
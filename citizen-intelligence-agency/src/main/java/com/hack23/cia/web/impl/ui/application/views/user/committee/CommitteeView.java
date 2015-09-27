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

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeRoleMember_;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.CommitteeRoleMemberPageItemClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.GovermentRoleMemberPageItemClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.ViewRiksdagenPoliticianDocumentPageItemClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractGroupView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
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

					panelContent.addComponent(new Label("Overview"));

					addTextFields(panelContent, new BeanItem<ViewRiksdagenCommittee>(viewRiksdagenCommittee),
							ViewRiksdagenCommittee.class,
							Arrays.asList(new String[] { "embeddedId.detail", "active", "firstAssignmentDate",
									"lastAssignmentDate", "totalAssignments", "totalDaysServed",
									"currentMemberSize" }));

				} else if (parameters.contains(CommitteePageMode.DOCUMENT_HISTORY.toString())) {

					panelContent.addComponent(new Label("Document History"));

					final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPoliticianDocument.class);

					final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<ViewRiksdagenPoliticianDocument>(
							ViewRiksdagenPoliticianDocument.class,
							politicianDocumentDataContainer.getAllBy(ViewRiksdagenPoliticianDocument_.org,
									viewRiksdagenCommittee.getEmbeddedId().getOrgCode().replace(" ", "")
											.replace("_", "").trim()));

					final Grid politicianDocumentBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							politicianDocumentDataSource, "Documents",
							new String[] { "id", "docId", "referenceName", "partyShortCode", "personReferenceId",
									"roleDescription", "documentType", "subType", "org", "label", "rm",
									"madePublicDate", "numberValue", "status", "title", "subTitle", "tempLabel",
									"orderNumber" },
							new String[] { "id", "numberValue", "orderNumber", "tempLabel", "personReferenceId",
									"org" },
							"docId", new ViewRiksdagenPoliticianDocumentPageItemClickListener());

					panelContent.addComponent(politicianDocumentBeanItemGrid);

				} else if (parameters.contains(CommitteePageMode.DocumentActivity.toString())) {

					panelContent.addComponent(new Label("Document Activity"));

					final DCharts createDocumentHistoryChart = chartDataManager
							.createDocumentHistoryChartByOrg(viewRiksdagenCommittee.getEmbeddedId().getOrgCode());

					panelContent.addComponent(createDocumentHistoryChart);

				} else if (parameters.contains(CommitteePageMode.DecisionTypeDailySummary.toString())) {
					panelContent.addComponent(new Label("Decision Type Daily Summary"));

					final DCharts createDecisionTypeChart = chartDataManager
							.createDecisionTypeChart(viewRiksdagenCommittee.getEmbeddedId().getOrgCode());
					panelContent.addComponent(createDecisionTypeChart);
				} else if (parameters.contains(CommitteePageMode.BallotDecisionSummary.toString())) {
					panelContent.addComponent(new Label("Ballot Decision Summary"));

					getPanel().setContent(panelContent);
					getPanel().setCaption("Committee:" + viewRiksdagenCommittee.getEmbeddedId().getDetail());
				} else if (parameters.contains(CommitteePageMode.DecisionSummary.toString())) {

					panelContent.addComponent(new Label("Decision Summary"));

				} else if (parameters.contains(CommitteePageMode.CURRENT_MEMBERS.toString())) {
					panelContent.addComponent(new Label("Current Members"));

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
							new GovermentRoleMemberPageItemClickListener());

					panelContent.addComponent(currentMemberBeanItemGrid);

				} else if (parameters.contains(CommitteePageMode.MemberHistory.toString())) {
					panelContent.addComponent(new Label("Member History"));

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
							new CommitteeRoleMemberPageItemClickListener());

					panelContent.addComponent(commmitteeRoleMemberBeanItemGrid);

				} else if (parameters.contains(CommitteePageMode.RoleGhant.toString())) {

					panelContent.addComponent(new Label("RoleGhant"));
				}

				getPanel().setContent(panelContent);
				getPanel().setCaption("Committee:" + viewRiksdagenCommittee.getEmbeddedId().getDetail());

			}

		} else {
			getPanel().setCaption("Committee:" + event.getParameters());
		}

	}

}
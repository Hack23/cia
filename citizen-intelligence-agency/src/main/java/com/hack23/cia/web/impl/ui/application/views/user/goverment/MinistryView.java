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
package com.hack23.cia.web.impl.ui.application.views.user.goverment;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
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
 * The Class MinistryView.
 */
@Service
@Scope("prototype")
@VaadinView(value = MinistryView.NAME, cached = true)
public final class MinistryView extends AbstractGroupView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.MINISTRY_VIEW_NAME;

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

			final DataContainer<ViewRiksdagenMinistry, String> dataContainer = applicationManager
					.getDataContainer(ViewRiksdagenMinistry.class);

			final ViewRiksdagenMinistry viewRiksdagenMinistry = dataContainer.load(pageId);

			if (viewRiksdagenMinistry != null) {

				menuItemFactory.createMinistryMenuBar(getBarmenu(), pageId);
				final VerticalLayout panelContent = new VerticalLayout();
				panelContent.setSizeFull();
				panelContent.setMargin(true);

				if (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
						|| parameters.contains(PageMode.Overview.toString())) {

					panelContent.addComponent(new Label("Overview"));

					panelContent.addComponent(pageLinkFactory.addMinistryPageLink(viewRiksdagenMinistry));

					formFactory.addTextFields(panelContent, new BeanItem<ViewRiksdagenMinistry>(viewRiksdagenMinistry),
							ViewRiksdagenMinistry.class,
							Arrays.asList(
									new String[] { "nameId", "active", "firstAssignmentDate", "lastAssignmentDate",
											"totalAssignments", "totalDaysServed", "currentMemberSize" }));

				} else if (parameters.contains(MinistryPageMode.DocumentHistory.toString())) {

					panelContent.addComponent(new Label("Document History"));

					final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPoliticianDocument.class);

					final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<ViewRiksdagenPoliticianDocument>(
							ViewRiksdagenPoliticianDocument.class, politicianDocumentDataContainer
									.getAllBy(ViewRiksdagenPoliticianDocument_.org, viewRiksdagenMinistry.getNameId()));

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

				} else if (parameters.contains(MinistryPageMode.DocumentActivity.toString())) {

					panelContent.addComponent(new Label("Document Activity"));

					final DCharts createDocumentHistoryChart = chartDataManager
							.createDocumentHistoryChartByOrg(viewRiksdagenMinistry.getNameId());

					panelContent.addComponent(createDocumentHistoryChart);

				} else if (parameters.contains(MinistryPageMode.CurrentMembers.toString())) {

					panelContent.addComponent(new Label("Current Members"));

					final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

					final BeanItemContainer<ViewRiksdagenGovermentRoleMember> currentMembersMemberDataSource = new BeanItemContainer<ViewRiksdagenGovermentRoleMember>(
							ViewRiksdagenGovermentRoleMember.class,
							govermentRoleMemberDataContainer.findListByProperty(
									new Object[] { viewRiksdagenMinistry.getNameId(), true },
									ViewRiksdagenGovermentRoleMember_.detail,
									ViewRiksdagenGovermentRoleMember_.active));

					final Grid currentMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							currentMembersMemberDataSource, "Current Members",
							new String[] { "roleId", "personId", "firstName", "lastName", "party", "active", "detail",
									"roleCode", "fromDate", "toDate", "totalDaysServed" },
							new String[] { "roleId", "personId", "detail" }, null,
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(currentMemberBeanItemGrid);

				} else if (parameters.contains(MinistryPageMode.MemberHistory.toString())) {

					panelContent.addComponent(new Label("Member History"));

					final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

					final BeanItemContainer<ViewRiksdagenGovermentRoleMember> govermentRoleMemberDataSource = new BeanItemContainer<ViewRiksdagenGovermentRoleMember>(
							ViewRiksdagenGovermentRoleMember.class, govermentRoleMemberDataContainer.getAllBy(
									ViewRiksdagenGovermentRoleMember_.detail, viewRiksdagenMinistry.getNameId()));

					final Grid ministryRoleMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							govermentRoleMemberDataSource, "Member History",
							new String[] { "roleId", "personId", "firstName", "lastName", "party", "active", "detail",
									"roleCode", "fromDate", "toDate", "totalDaysServed" },
							new String[] { "roleId", "personId", "detail" }, null,
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(ministryRoleMemberBeanItemGrid);

				} else if (parameters.contains(MinistryPageMode.RoleGhant.toString())) {
					panelContent.addComponent(new Label("Role Ghant"));
				}

				getPanel().setContent(panelContent);
				getPanel().setCaption("Ministry:" + viewRiksdagenMinistry.getNameId());

			}

		}
	}

}
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
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.DocumentChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractGroupView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class MinistryView.
 */
@Service
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(value = MinistryView.NAME, cached = true)
public final class MinistryView extends AbstractGroupView {

	/** The Constant MINISTRY. */
	private static final String MINISTRY = "Ministry:";

	/** The Constant ROLE_GHANT. */
	private static final String ROLE_GHANT = "Role Ghant";

	/** The Constant MEMBER_HISTORY. */
	private static final String MEMBER_HISTORY = "Member History";

	/** The Constant CURRENT_MEMBERS. */
	private static final String CURRENT_MEMBERS = "Current Members";

	/** The Constant DOCUMENT_ACTIVITY. */
	private static final String DOCUMENT_ACTIVITY = "Document Activity";

	/** The Constant DOCUMENT_HISTORY. */
	private static final String DOCUMENT_HISTORY = "Document History";

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "Overview";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.MINISTRY_VIEW_NAME;

	/** The application manager. */
	@Autowired
	private transient ApplicationManager applicationManager;

	/** The chart data manager. */
	@Autowired
	private transient DocumentChartDataManager chartDataManager;

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
	 * Instantiates a new ministry view.
	 */
	public MinistryView() {
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
						|| parameters.contains(PageMode.OVERVIEW.toString())) {

					final Label createHeader2Label = LabelFactory.createHeader2Label(OVERVIEW);
					panelContent.addComponent(createHeader2Label);

					final Link addMinistryPageLink = pageLinkFactory.addMinistryPageLink(viewRiksdagenMinistry);
					panelContent.addComponent(addMinistryPageLink);

					final Panel formPanel = new Panel();
					formPanel.setSizeFull();

					panelContent.addComponent(formPanel);

					final FormLayout formContent = new FormLayout();
					formPanel.setContent(formContent);


					formFactory.addTextFields(formContent, new BeanItem<>(viewRiksdagenMinistry),
							ViewRiksdagenMinistry.class,
							Arrays.asList(
									new String[] { "nameId", "active", "firstAssignmentDate", "lastAssignmentDate",
											"totalAssignments", "totalDaysServed", "currentMemberSize" }));

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(addMinistryPageLink,ContentRatio.SMALL);
					panelContent.setExpandRatio(formPanel, ContentRatio.GRID);


				} else if (parameters.contains(MinistryPageMode.DOCUMENTHISTORY.toString())) {

					final Label createHeader2Label = LabelFactory.createHeader2Label(DOCUMENT_HISTORY);
					panelContent.addComponent(createHeader2Label);

					final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPoliticianDocument.class);

					final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<>(
							ViewRiksdagenPoliticianDocument.class, politicianDocumentDataContainer
									.findOrderedListByProperty(ViewRiksdagenPoliticianDocument_.org, viewRiksdagenMinistry.getNameId(),ViewRiksdagenPoliticianDocument_.madePublicDate));

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

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(politicianDocumentBeanItemGrid, ContentRatio.GRID);


				} else if (parameters.contains(MinistryPageMode.DOCUMENTACTIVITY.toString())) {

					final Label createHeader2Label = LabelFactory.createHeader2Label(DOCUMENT_ACTIVITY);
					panelContent.addComponent(createHeader2Label);

					final DCharts createDocumentHistoryChart = chartDataManager
							.createDocumentHistoryChartByOrg(viewRiksdagenMinistry.getNameId());

					panelContent.addComponent(createDocumentHistoryChart);

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(createDocumentHistoryChart, ContentRatio.GRID);


				} else if (parameters.contains(MinistryPageMode.CURRENTMEMBERS.toString())) {

					final Label createHeader2Label = LabelFactory.createHeader2Label(CURRENT_MEMBERS);
					panelContent.addComponent(createHeader2Label);

					final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

					final BeanItemContainer<ViewRiksdagenGovermentRoleMember> currentMembersMemberDataSource = new BeanItemContainer<>(
							ViewRiksdagenGovermentRoleMember.class,
							govermentRoleMemberDataContainer.findListByProperty(
									new Object[] { viewRiksdagenMinistry.getNameId(), Boolean.TRUE},
									ViewRiksdagenGovermentRoleMember_.detail,
									ViewRiksdagenGovermentRoleMember_.active));

					final Grid currentMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							currentMembersMemberDataSource, CURRENT_MEMBERS,
							new String[] { "roleId", "personId", "firstName", "lastName", "party", "active", "detail",
									"roleCode", "fromDate", "toDate", "totalDaysServed" },
							new String[] { "roleId", "personId", "detail" }, null,
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(currentMemberBeanItemGrid);

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(currentMemberBeanItemGrid, ContentRatio.GRID);


				} else if (parameters.contains(MinistryPageMode.MEMBERHISTORY.toString())) {

					final Label createHeader2Label = LabelFactory.createHeader2Label(MEMBER_HISTORY);
					panelContent.addComponent(createHeader2Label);

					final DataContainer<ViewRiksdagenGovermentRoleMember, String> govermentRoleMemberDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenGovermentRoleMember.class);

					final BeanItemContainer<ViewRiksdagenGovermentRoleMember> govermentRoleMemberDataSource = new BeanItemContainer<>(
							ViewRiksdagenGovermentRoleMember.class, govermentRoleMemberDataContainer.getAllBy(
									ViewRiksdagenGovermentRoleMember_.detail, viewRiksdagenMinistry.getNameId()));

					final Grid ministryRoleMemberBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							govermentRoleMemberDataSource, MEMBER_HISTORY,
							new String[] { "roleId", "personId", "firstName", "lastName", "party", "active", "detail",
									"roleCode", "fromDate", "toDate", "totalDaysServed" },
							new String[] { "roleId", "personId", "detail" }, null,
							new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId"), null);

					panelContent.addComponent(ministryRoleMemberBeanItemGrid);

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(ministryRoleMemberBeanItemGrid, ContentRatio.GRID);


				} else if (parameters.contains(MinistryPageMode.ROLEGHANT.toString())) {
					panelContent.addComponent(LabelFactory.createHeader2Label(ROLE_GHANT));
				} else if (parameters.contains(PageMode.PAGEVISITHISTORY.toString())) {

					createPageVisitHistory(NAME,pageId,panelContent);

				}

				getPanel().setContent(panelContent);
				getPanel().setCaption(MINISTRY + viewRiksdagenMinistry.getNameId());
				pageActionEventHelper.createPageEvent(ViewAction.VISIT_MINISTRY_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);

			}

		}
	}

}
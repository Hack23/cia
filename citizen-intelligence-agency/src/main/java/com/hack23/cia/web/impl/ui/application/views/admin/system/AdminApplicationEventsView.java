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
package com.hack23.cia.web.impl.ui.application.views.admin.system;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.admin.common.AbstractAdminView;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
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
 * The Class AdminDataSummaryView.
 */
@Service
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(AdminApplicationEventsView.NAME)
public final class AdminApplicationEventsView extends AbstractAdminView {

	private static final String ADMIN_APPLICATION_ACTION_EVENT = "Admin Application Action Event";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME;

	/** The application manager. */
	@Autowired
	private transient ApplicationManager applicationManager;

	/** The grid factory. */
	@Autowired
	private transient GridFactory gridFactory;

	/** The form factory. */
	@Autowired
	private transient FormFactory formFactory;

	/** The chart data manager. */
	@Autowired
	private transient ChartDataManager chartDataManager;

	/**
	 * Instantiates a new admin application events view.
	 */
	public AdminApplicationEventsView() {
		super();
	}

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		createListAndForm(null);
	}

	//@Secured({ "ROLE_ADMIN" })
	@Override
	public void enter(final ViewChangeEvent event) {
		final String parameters = event.getParameters();

		if (parameters != null) {
			createListAndForm(parameters.substring(parameters.lastIndexOf('/') + "/".length(), parameters.length()));
		}
	}

	/**
	 * Creates the list and form.
	 *
	 * @param pageId
	 *            the page id
	 */
	private void createListAndForm(final String pageId) {
		final VerticalLayout content = new VerticalLayout();

		final Label createHeader2Label = LabelFactory.createHeader2Label(ADMIN_APPLICATION_ACTION_EVENT);
		content.addComponent(createHeader2Label);
		content.setExpandRatio(createHeader2Label, ContentRatio.SMALL);

		final DataContainer<ApplicationActionEvent, Long> dataContainer = applicationManager.getDataContainer(ApplicationActionEvent.class);

		final BeanItemContainer<ApplicationActionEvent> politicianDocumentDataSource = new BeanItemContainer<>(ApplicationActionEvent.class,
				dataContainer.getAllOrderBy(ApplicationActionEvent_.createdDate));

		final Grid createBasicBeanItemGrid = gridFactory.createBasicBeanItemGrid(politicianDocumentDataSource, "ApplicationActionEvent",
				new String[] { "hjid", "createdDate", "eventGroup", "applicationOperation","page","pageMode","elementId","actionName","userId","sessionId","errorMessage","applicationMessage", "modelObjectVersion" },
				new String[] { "modelObjectId" }, "hjid",
				new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "hjid"), null);
		content.addComponent(createBasicBeanItemGrid);
		content.setExpandRatio(createBasicBeanItemGrid, ContentRatio.GRID);

		if (pageId != null && !pageId.isEmpty()) {

			final ApplicationActionEvent applicationActionEvent = dataContainer.load(Long.valueOf(pageId));

			if (applicationActionEvent != null) {

				 final Panel formPanel = new Panel();
				 formPanel.setSizeFull();

				final FormLayout formContent = new FormLayout();
				formPanel.setContent(formContent);

			formFactory.addTextFields(formContent, new BeanItem<>(applicationActionEvent), ApplicationActionEvent.class,
					Arrays.asList(new String[] { "hjid","createdDate", "eventGroup", "applicationOperation","page","pageMode","elementId","actionName","userId","sessionId","errorMessage","applicationMessage", "modelObjectVersion"  }));

			content.addComponent(formPanel);
			content.setExpandRatio(formPanel, ContentRatio.LARGE_FORM);

			}


		} else {
			final DCharts createApplicationActionEventPageDailySummaryChart = chartDataManager.createApplicationActionEventPageDailySummaryChart();


			content.addComponent(createApplicationActionEventPageDailySummaryChart);

			content.setExpandRatio(createApplicationActionEventPageDailySummaryChart, ContentRatio.LARGE);

		}

	 	final Link createMainViewPageLink = pageLinkFactory.createMainViewPageLink();
		content.addComponent(createMainViewPageLink);
		content.setExpandRatio(createMainViewPageLink,ContentRatio.SMALL);


		content.setWidth(100, Unit.PERCENTAGE);
		content.setHeight(100, Unit.PERCENTAGE);
		setContent(content);
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);

		pageActionEventHelper.createPageEvent(ViewAction.VISIT_ADMIN_APPLICATION_EVENTS_VIEW, ApplicationEventGroup.ADMIN, NAME, null, pageId);

	}

}

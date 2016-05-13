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
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;

import java.util.Arrays;

import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.AdminChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminApplicationEventsPageModContentFactoryImpl.
 */
@Component
public final class AdminApplicationEventsPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant ADMIN_APPLICATION_ACTION_EVENT. */
	private static final String ADMIN_APPLICATION_ACTION_EVENT = "Admin Application Action Event";

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME;

	/** The chart data manager. */
	@Autowired
	private AdminChartDataManager chartDataManager;

	/**
	 * Instantiates a new admin application events page mod content factory
	 * impl.
	 */
	public AdminApplicationEventsPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		final String pageId = getPageId(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);


		final Label createHeader2Label = LabelFactory.createHeader2Label(ADMIN_APPLICATION_ACTION_EVENT);
		content.addComponent(createHeader2Label);
		content.setExpandRatio(createHeader2Label, ContentRatio.SMALL);

		final DataContainer<ApplicationActionEvent, Long> dataContainer = getApplicationManager().getDataContainer(ApplicationActionEvent.class);

		final BeanItemContainer<ApplicationActionEvent> politicianDocumentDataSource = new BeanItemContainer<>(ApplicationActionEvent.class,
				dataContainer.getAllOrderBy(ApplicationActionEvent_.createdDate));

		final Grid createBasicBeanItemGrid = getGridFactory().createBasicBeanItemGrid(politicianDocumentDataSource, "ApplicationActionEvent",
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

			getFormFactory().addTextFields(formContent, new BeanItem<>(applicationActionEvent), ApplicationActionEvent.class,
					Arrays.asList(new String[] { "hjid","createdDate", "eventGroup", "applicationOperation","page","pageMode","elementId","actionName","userId","sessionId","errorMessage","applicationMessage", "modelObjectVersion"  }));

			content.addComponent(formPanel);
			content.setExpandRatio(formPanel, ContentRatio.LARGE_FORM);

			}


		} else {
			final DCharts createApplicationActionEventPageDailySummaryChart = chartDataManager.createApplicationActionEventPageDailySummaryChart();


			content.addComponent(createApplicationActionEventPageDailySummaryChart);

			content.setExpandRatio(createApplicationActionEventPageDailySummaryChart, ContentRatio.LARGE);

		}


		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_APPLICATION_EVENTS_VIEW, ApplicationEventGroup.ADMIN,
				NAME, null, pageId);

		return content;

	}

}

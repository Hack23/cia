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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.admin.common.AbstractAdminView;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class AdminDataSummaryView.
 */
@Service
@Scope(value="prototype")
@VaadinView(AdminApplicationSessionView.NAME)
public class AdminApplicationSessionView extends AbstractAdminView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME;

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

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
		createListAndForm(null);
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.
	 * ViewChangeEvent)
	 */
	@Override
	//@Secured({ "ROLE_ADMIN" })
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

		Label createHeader2Label = LabelFactory.createHeader2Label("Admin Application Session");
		content.addComponent(createHeader2Label);
		content.setExpandRatio(createHeader2Label, 1);


		final DataContainer<ApplicationSession, Long> dataContainer = applicationManager
				.getDataContainer(ApplicationSession.class);

		final BeanItemContainer<ApplicationSession> politicianDocumentDataSource = new BeanItemContainer<>(
				ApplicationSession.class, dataContainer.getAllOrderBy(ApplicationSession_.createdDate));

		Grid createBasicBeanItemGrid = gridFactory.createBasicBeanItemGrid(politicianDocumentDataSource, "ApplicationSession",
				new String[] { "hjid", "createdDate", "sessionType", "sessionId", "operatingSystem", "locale",
						"ipInformation", "userAgentInformation", "events" },
				new String[] { "modelObjectId", "modelObjectVersion" }, "hjid",
				new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, "hjid"), null);
		content.addComponent(createBasicBeanItemGrid);
		content.setExpandRatio(createBasicBeanItemGrid, 10);


		if (pageId != null && !pageId.isEmpty()) {

			final ApplicationSession applicationSession = dataContainer.load(Long.valueOf(pageId));

			if (applicationSession != null) {

				final VerticalLayout rightLayout = new VerticalLayout();
				rightLayout.setSizeFull();
				final HorizontalLayout horizontalLayout = new HorizontalLayout();
				horizontalLayout.setWidth("100%");
				content.addComponent(horizontalLayout);
				content.setExpandRatio(horizontalLayout, 10);

				 Panel formPanel = new Panel();
				 formPanel.setSizeFull();

				final FormLayout formContent = new FormLayout();
				formPanel.setContent(formContent);

				horizontalLayout.addComponent(formPanel);
				horizontalLayout.addComponent(rightLayout);

				formFactory.addTextFields(formContent, new BeanItem<>(applicationSession),
						ApplicationSession.class,
						Arrays.asList(new String[] { "hjid", "createdDate", "sessionId", "operatingSystem", "locale",
								"ipInformation", "userAgentInformation", "modelObjectVersion" }));

				final BeanItemContainer<ApplicationActionEvent> eventsItemContainer = new BeanItemContainer<>(
						ApplicationActionEvent.class, applicationSession.getEvents());

				Grid createBasicBeanItemGrid2 = gridFactory.createBasicBeanItemGrid(eventsItemContainer,
						"ApplicationActionEvent",
						new String[] { "hjid", "createdDate", "eventGroup", "applicationOperation", "page", "pageMode",
								"elementId", "actionName", "userId", "sessionId", "errorMessage", "applicationMessage",
								"modelObjectVersion" },
						new String[] { "modelObjectId" }, "hjid",
						new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "hjid"),
						null);
				rightLayout.addComponent(createBasicBeanItemGrid2);
			}

		}

		Link createMainViewPageLink = pageLinkFactory.createMainViewPageLink();
		content.addComponent(createMainViewPageLink);
		content.setExpandRatio(createMainViewPageLink, 2);


		content.setWidth(100, Unit.PERCENTAGE);
		content.setHeight(100, Unit.PERCENTAGE);
		setContent(content);
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
		pageActionEventHelper.createPageEvent(ViewAction.VISIT_ADMIN_APPLICATION_SESSION_VIEW,
				ApplicationEventGroup.ADMIN, NAME, null, pageId);

	}

}

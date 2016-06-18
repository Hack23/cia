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

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminApplicationSessionPageModContentFactoryImpl.
 */
@Component
public final class AdminApplicationSessionPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant ADMIN_APPLICATION_SESSION. */
	private static final String ADMIN_APPLICATION_SESSION = "Admin Application Session";

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME;

	/**
	 * Instantiates a new admin application session page mod content factory
	 * impl.
	 */
	public AdminApplicationSessionPageModContentFactoryImpl() {
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


		final Label createHeader2Label = LabelFactory.createHeader2Label(ADMIN_APPLICATION_SESSION);
		content.addComponent(createHeader2Label);
		content.setExpandRatio(createHeader2Label, ContentRatio.SMALL);


		final DataContainer<ApplicationSession, Long> dataContainer = getApplicationManager()
				.getDataContainer(ApplicationSession.class);

		final BeanItemContainer<ApplicationSession> politicianDocumentDataSource = new BeanItemContainer<>(
				ApplicationSession.class, dataContainer.getPageOrderBy(1,250,ApplicationSession_.createdDate));

		final Grid createBasicBeanItemGrid = getGridFactory().createBasicBeanItemGrid(politicianDocumentDataSource, "ApplicationSession",
				new String[] { "hjid", "createdDate", "sessionType", "sessionId", "operatingSystem", "locale",
						"ipInformation", "userAgentInformation", "events" },
				new String[] { "modelObjectId", "modelObjectVersion" }, "hjid",
				new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, "hjid"), null);
		content.addComponent(createBasicBeanItemGrid);
		content.setExpandRatio(createBasicBeanItemGrid, ContentRatio.GRID);


		if (pageId != null && !pageId.isEmpty()) {

			final ApplicationSession applicationSession = dataContainer.load(Long.valueOf(pageId));

			if (applicationSession != null) {

				final VerticalLayout rightLayout = new VerticalLayout();
				rightLayout.setSizeFull();
				final HorizontalLayout horizontalLayout = new HorizontalLayout();
				horizontalLayout.setWidth(ContentSize.FULL_SIZE);
				content.addComponent(horizontalLayout);
				content.setExpandRatio(horizontalLayout, ContentRatio.GRID);

				 final Panel formPanel = new Panel();
				 formPanel.setSizeFull();

				final FormLayout formContent = new FormLayout();
				formPanel.setContent(formContent);

				horizontalLayout.addComponent(formPanel);
				horizontalLayout.addComponent(rightLayout);

				getFormFactory().addTextFields(formContent, new BeanItem<>(applicationSession),
						ApplicationSession.class,
						Arrays.asList(new String[] { "hjid", "createdDate", "sessionId", "operatingSystem", "locale",
								"ipInformation", "userAgentInformation", "modelObjectVersion" }));

				final BeanItemContainer<ApplicationActionEvent> eventsItemContainer = new BeanItemContainer<>(
						ApplicationActionEvent.class, applicationSession.getEvents());

				final Grid createBasicBeanItemGrid2 = getGridFactory().createBasicBeanItemGrid(eventsItemContainer,
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

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_APPLICATION_SESSION_VIEW, ApplicationEventGroup.ADMIN,
				NAME, null, pageId);

		return content;

	}

}

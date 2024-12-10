/*
 * Copyright 2010-2024 James Pether Sörling
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
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.admin.UpdateApplicationConfigurationRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.UpdateApplicationConfigurationClickListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;



/**
 * The Class AdminApplicationConfigurationPageModContentFactoryImpl.
 */
@Component
public final class AdminApplicationConfigurationPageModContentFactoryImpl
		extends AbstractAdminSystemPageModContentFactoryImpl {

	private static final String APPLICATION_CONFIGURATION = "ApplicationConfiguration";

	private static final List<String> AS_LIST = Arrays.asList( "configurationGroup", "component","configTitle" ,"configDescription",
			"propertyValue", "createdDate",
			"updatedDate" );

	private static final List<String> AS_LIST2 = Arrays.asList( "configTitle",
			"configDescription", "componentTitle", "componentDescription", "propertyValue" );

	private static final String[] COLUMN_ORDER = { "hjid", "configurationGroup", "component", "componentTitle", "configTitle", "configDescription",
			 "componentDescription", "propertyId", "propertyValue" };

	private static final String[] HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion", "createdDate", "updatedDate","propertyId" ,"componentDescription", "componentTitle"};

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME;

	private static final String UPDATE_CONFIGURATION = "Update Configuration";

	/**
	 * Instantiates a new admin application configuration page mod content
	 * factory impl.
	 */
	public AdminApplicationConfigurationPageModContentFactoryImpl() {
		super(NAME);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		final String pageId = getPageId(parameters);
		final int pageNr= getPageNr(parameters);


		getMenuItemFactory().createMainPageMenuBar(menuBar);

		createPageHeader(panel, content, "Application Configuration", "Configuration Overview", "View and edit application settings and configurations for optimal performance.");

		final DataContainer<ApplicationConfiguration, Long> dataContainer = getApplicationManager()
				.getDataContainer(ApplicationConfiguration.class);

		final List<ApplicationConfiguration> pageOrderBy = dataContainer.getPageOrderBy(pageNr,DEFAULT_RESULTS_PER_PAGE, ApplicationConfiguration_.configurationGroup);

		getPagingUtil().createPagingControls(content,NAME,pageId, dataContainer.getSize(), pageNr, DEFAULT_RESULTS_PER_PAGE);

		getGridFactory().createBasicBeanItemGrid(content,ApplicationConfiguration.class,
				pageOrderBy,
				APPLICATION_CONFIGURATION,
				COLUMN_ORDER, HIDE_COLUMNS,
				new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, "hjid"), null, null);

		if (pageId != null && !pageId.isEmpty()) {

			final ApplicationConfiguration applicationConfiguration = dataContainer.load(Long.valueOf(pageId));

			if (applicationConfiguration != null) {

				final VerticalLayout leftLayout = new VerticalLayout();
				leftLayout.setSizeFull();
				final VerticalLayout rightLayout = new VerticalLayout();
				rightLayout.setSizeFull();
				final HorizontalLayout horizontalLayout = new HorizontalLayout();
				horizontalLayout.setWidth(ContentSize.FULL_SIZE);
				content.addComponent(horizontalLayout);
				horizontalLayout.addComponent(leftLayout);
				horizontalLayout.addComponent(rightLayout);


				getFormFactory().addFormPanelTextFields(leftLayout, applicationConfiguration,
						ApplicationConfiguration.class,
						AS_LIST);

				final UpdateApplicationConfigurationRequest request = new UpdateApplicationConfigurationRequest();
				request.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
				request.setApplicationConfigurationId(applicationConfiguration.getHjid());

				request.setConfigTitle(applicationConfiguration.getConfigTitle());
				request.setConfigDescription(applicationConfiguration.getConfigDescription());

				request.setComponentTitle(applicationConfiguration.getConfigTitle());
				request.setComponentDescription(applicationConfiguration.getComponentDescription());

				request.setPropertyValue(applicationConfiguration.getPropertyValue());

				final ClickListener buttonListener = new UpdateApplicationConfigurationClickListener(request);

				final Panel updateFormPanel = new Panel();
				updateFormPanel.setSizeFull();

				rightLayout.addComponent(updateFormPanel);

				final FormLayout updateFormContent = new FormLayout();
				updateFormPanel.setContent(updateFormContent);

				getFormFactory().addRequestInputFormFields(updateFormContent, request,
						UpdateApplicationConfigurationRequest.class, AS_LIST2,
						UPDATE_CONFIGURATION, buttonListener);

			}
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_APPLICATION_CONFIGURATION_VIEW,
				ApplicationEventGroup.ADMIN, NAME, null, pageId);

		return content;
	}

}

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

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminApplicationConfigurationPageModContentFactoryImpl.
 */
@Component
public final class AdminApplicationConfigurationPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	private static final String APPLICATION_CONFIGURATION = "ApplicationConfiguration";

	private static final String[] COLUMN_ORDER = { "hjid", "configKey", "configGroup", "configValue", "defaultValue",
			"configDescription", "modelObjectVersion" };

	private static final String[] HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion" };

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			AdminViews.ADMIN_APPLICATION_CONFIGURATION_VIEW_NAME, "hjid");

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_APPLICATION_CONFIGURATION_VIEW_NAME;

	/**
	 * Instantiates a new admin application configuration page mod content factory
	 * impl.
	 */
	public AdminApplicationConfigurationPageModContentFactoryImpl() {
		super(NAME);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createOverviewLayout();

		final String pageId = getPageId(parameters);
		final int pageNr = getPageNr(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		createPageHeader(panel, content, "Admin Application Configuration", "Configuration Overview",
				"Manage and review application configuration settings, including key-value pairs and descriptions.");

		final DataContainer<ApplicationConfiguration, Long> dataContainer = getApplicationManager()
				.getDataContainer(ApplicationConfiguration.class);
		final List<ApplicationConfiguration> pageOrderBy = dataContainer.getPageOrderBy(pageNr, DEFAULT_RESULTS_PER_PAGE,
				ApplicationConfiguration_.configKey);

		getPagingUtil().createPagingControls(content, NAME, pageId, dataContainer.getSize(), pageNr,
				DEFAULT_RESULTS_PER_PAGE);

		getGridFactory().createBasicBeanItemGrid(content, ApplicationConfiguration.class, pageOrderBy,
				APPLICATION_CONFIGURATION, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		if (pageId != null && !pageId.isEmpty()) {
			final VerticalLayout horizontalLayout = createSplitLayout();
			content.addComponent(horizontalLayout);
			content.setExpandRatio(horizontalLayout, ContentRatio.LARGE_FORM);

			final VerticalLayout leftLayout = createCardContentLayout();
			leftLayout.setSizeFull();
			leftLayout.addStyleName("v-layout-content-overview-panel-level1");

			final VerticalLayout rightLayout = createCardContentLayout();
			rightLayout.setSizeFull();
			rightLayout.addStyleName("v-layout-content-overview-panel-level2");

			horizontalLayout.addComponents(leftLayout, rightLayout);

			final ApplicationConfiguration applicationConfiguration = dataContainer.load(Long.valueOf(pageId));
			if (applicationConfiguration != null) {
				// Card panel for Application Configuration details
				final Panel cardPanel = createCardPanel("Application Configuration Details");
				final VerticalLayout cardContent = (VerticalLayout) cardPanel.getContent();

				leftLayout.addComponent(cardPanel);

				// Attributes layout
				final VerticalLayout attributesLayout = new VerticalLayout();
				attributesLayout.setSpacing(true);
				attributesLayout.setWidth("100%");
				cardContent.addComponent(attributesLayout);

				// Display fields in a card layout (skipping null or empty ones)
				addInfoRowsToLayout(attributesLayout,
						new InfoRowItem("Config Key:", applicationConfiguration.getConfigKey(), VaadinIcons.KEY),
						new InfoRowItem("Config Group:", applicationConfiguration.getConfigGroup(), VaadinIcons.GROUP),
						new InfoRowItem("Config Value:", applicationConfiguration.getConfigValue(), VaadinIcons.FILE_TEXT),
						new InfoRowItem("Default Value:", applicationConfiguration.getDefaultValue(), VaadinIcons.FILE_TEXT),
						new InfoRowItem("Description:", applicationConfiguration.getConfigDescription(), VaadinIcons.INFO_CIRCLE));
			}
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_APPLICATION_CONFIGURATION_VIEW, ApplicationEventGroup.ADMIN,
				NAME, null, pageId);

		return content;
	}
}

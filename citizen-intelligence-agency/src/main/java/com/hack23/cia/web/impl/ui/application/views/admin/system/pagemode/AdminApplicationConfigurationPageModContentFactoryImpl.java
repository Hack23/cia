/*
 * Copyright 2010-2025 James Pether Sörling
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
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.UpdateApplicationConfigurationClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
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

    /** The Constant APPLICATION_CONFIGURATION. */
    private static final String APPLICATION_CONFIGURATION = "ApplicationConfiguration";

    /** The Constant AS_LIST2. */
    private static final List<String> AS_LIST2 = Arrays.asList("configTitle", "configDescription", "componentTitle",
            "componentDescription", "propertyValue");

    /** The Constant COLUMN_ORDER. */
    private static final String[] COLUMN_ORDER = { "hjid", "configurationGroup", "component", "componentTitle",
            "configTitle", "configDescription", "componentDescription", "propertyId", "propertyValue" };

    /** The Constant HIDE_COLUMNS. */
    private static final String[] HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion", "createdDate",
            "updatedDate", "propertyId", "componentDescription", "componentTitle" };

    /** The Constant NAME. */
    public static final String NAME = AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME;

    /** The Constant UPDATE_CONFIGURATION. */
    private static final String UPDATE_CONFIGURATION = "Update Configuration";

    /**
     * Instantiates a new admin application configuration page mod content factory impl.
     */
    public AdminApplicationConfigurationPageModContentFactoryImpl() {
        super(NAME);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
        final VerticalLayout content = createPanelContent();

        final String pageId = getPageId(parameters);
        final int pageNr = getPageNr(parameters);

        getMenuItemFactory().createMainPageMenuBar(menuBar);

        CardInfoRowUtil.createPageHeader(panel, content, AdminViewConstants.ADMIN_APPLICATION_CONFIGURATION, AdminViewConstants.APPLICATION_CONFIGURATION_OVERVIEW,
                AdminViewConstants.APPLICATION_CONFIGURATION_OVERVIEW);

        final DataContainer<ApplicationConfiguration, Long> dataContainer = getApplicationManager()
                .getDataContainer(ApplicationConfiguration.class);

        final List<ApplicationConfiguration> pageOrderBy = dataContainer
                .getPageOrderBy(pageNr, DEFAULT_RESULTS_PER_PAGE, ApplicationConfiguration_.configurationGroup);

        getPagingUtil().createPagingControls(content, NAME, pageId, dataContainer.getSize(), pageNr,
                DEFAULT_RESULTS_PER_PAGE);

        getGridFactory().createBasicBeanItemGrid(
                content,
                ApplicationConfiguration.class,
                pageOrderBy,
                APPLICATION_CONFIGURATION,
                COLUMN_ORDER,
                HIDE_COLUMNS,
                new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, "hjid"),
                null,
                null
        );

        if (pageId != null && !pageId.isEmpty()) {
            final ApplicationConfiguration applicationConfiguration = dataContainer.load(Long.valueOf(pageId));
            if (applicationConfiguration != null) {

                final HorizontalLayout horizontalLayout = new HorizontalLayout();
                horizontalLayout.setWidth(ContentSize.FULL_SIZE);
                content.addComponent(horizontalLayout);

                final VerticalLayout leftLayout = new VerticalLayout();
                leftLayout.setSizeFull();
                leftLayout.addStyleName("v-layout-content-overview-panel-level1");

                final VerticalLayout rightLayout = new VerticalLayout();
                rightLayout.setSizeFull();
                rightLayout.addStyleName("v-layout-content-overview-panel-level2");

                horizontalLayout.addComponents(leftLayout, rightLayout);

                // Left side: Card layout for ApplicationConfiguration details
                final Panel cardPanel = new Panel();
                cardPanel.addStyleName("politician-overview-card"); // Reuse existing card style
                cardPanel.setWidth("100%");
                cardPanel.setHeightUndefined();
                Responsive.makeResponsive(cardPanel);

                final VerticalLayout cardContent = new VerticalLayout();
                cardContent.setMargin(true);
                cardContent.setSpacing(true);
                cardContent.setWidth("100%");
                cardPanel.setContent(cardContent);

                leftLayout.addComponent(cardPanel);

                CardInfoRowUtil.createCardHeader(cardContent,"Application Configuration Details");

                // Attributes layout
                final VerticalLayout attributesLayout = new VerticalLayout();
                attributesLayout.setSpacing(true);
                attributesLayout.setWidth("100%");
                cardContent.addComponent(attributesLayout);

                // Display relevant fields using info rows, skipping null or empty
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Configuration Group:",
                        applicationConfiguration.getConfigurationGroup().toString(), VaadinIcons.GROUP);
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Component:",
                        applicationConfiguration.getComponent(), VaadinIcons.TOOLS);
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Config Title:",
                        applicationConfiguration.getConfigTitle(), VaadinIcons.FILE_TEXT);
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Config Description:",
                        applicationConfiguration.getConfigDescription(), VaadinIcons.FILE_O);
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Property Value:",
                        applicationConfiguration.getPropertyValue(), VaadinIcons.PASTE);
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Created Date:",
                        String.valueOf(applicationConfiguration.getCreatedDate()), VaadinIcons.CALENDAR);
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Updated Date:",
                        String.valueOf(applicationConfiguration.getUpdatedDate()), VaadinIcons.CALENDAR_CLOCK);

                // Right side: Form for updating the application configuration
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

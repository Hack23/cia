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

import com.hack23.cia.model.internal.application.system.impl.Agency;
import com.hack23.cia.model.internal.application.system.impl.Agency_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.Portal;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.converters.ListPropertyConverter;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminAgencyPageModContentFactoryImpl.
 */
@Component
public final class AdminAgencyPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

    /** The Constant AGENCY. */
    private static final String AGENCY = "Agency";

    /** The Constant AGENCY_GRID_COLLECTION_PROPERTY_CONVERTERS. */
    private static final ListPropertyConverter[] AGENCY_GRID_COLLECTION_PROPERTY_CONVERTERS = {
            new ListPropertyConverter("portalName", "portals") };

    /** The Constant AGENCY_GRID_COLUMN_ORDER. */
    private static final String[] AGENCY_GRID_COLUMN_ORDER = { "hjid", "agencyName", "description", "portals",
            "modelObjectVersion" };

    /** The Constant AGENCY_GRID_HIDE_COLUMNS. */
    private static final String[] AGENCY_GRID_HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion" };

    /** The Constant AGENCY_GRID_LISTENER. */
    private static final PageItemPropertyClickListener AGENCY_GRID_LISTENER = new PageItemPropertyClickListener(
            AdminViews.ADMIN_AGENCY_VIEW_NAME, "hjid");

    /** The Constant NAME. */
    public static final String NAME = AdminViews.ADMIN_AGENCY_VIEW_NAME;

    /** The Constant PORTAL. */
    private static final String PORTAL = "Portal";

    /** The Constant PORTAL_GRID_COLUMN_ORDER. */
    private static final String[] PORTAL_GRID_COLUMN_ORDER = { "hjid", "portalName", "description", "portalType",
            "googleMapApiKey", "modelObjectVersion" };

    /** The Constant PORTAL_GRID_HIDE_COLUMNS. */
    private static final String[] PORTAL_GRID_HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion",
            "googleMapApiKey" };

    /** The Constant PORTAL_GRID_LISTENER. */
    private static final PageItemPropertyClickListener PORTAL_GRID_LISTENER = new PageItemPropertyClickListener(
            AdminViews.ADMIN_PORTAL_VIEW_NAME, "hjid");

    /**
     * Instantiates a new admin agency page mod content factory impl.
     */
    public AdminAgencyPageModContentFactoryImpl() {
        super(NAME);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
        final VerticalLayout content = createPanelContent();

        final String pageId = getPageId(parameters);
        final int pageNr = getPageNr(parameters);

        getMenuItemFactory().createMainPageMenuBar(menuBar);

        createPageHeader(panel, content, "Admin Agency Management", "Agency Overview",
                "Manage and review details of agencies, including organizational data and performance metrics.");

        final DataContainer<Agency, Long> dataContainer = getApplicationManager().getDataContainer(Agency.class);
        final List<Agency> pageOrderBy = dataContainer.getPageOrderBy(pageNr, DEFAULT_RESULTS_PER_PAGE,
                Agency_.agencyName);

        getPagingUtil().createPagingControls(content, NAME, pageId, dataContainer.getSize(), pageNr,
                DEFAULT_RESULTS_PER_PAGE);

        getGridFactory().createBasicBeanItemGrid(content, Agency.class, pageOrderBy, AGENCY,
                AGENCY_GRID_COLUMN_ORDER, AGENCY_GRID_HIDE_COLUMNS, AGENCY_GRID_LISTENER, null,
                AGENCY_GRID_COLLECTION_PROPERTY_CONVERTERS);

        if (pageId != null && !pageId.isEmpty()) {
            final HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setWidth(ContentSize.FULL_SIZE);
            content.addComponent(horizontalLayout);
            content.setExpandRatio(horizontalLayout, ContentRatio.LARGE_FORM);

            final VerticalLayout leftLayout = new VerticalLayout();
            leftLayout.setSizeFull();
            leftLayout.addStyleName("v-layout-content-overview-panel-level1");

            final VerticalLayout rightLayout = new VerticalLayout();
            rightLayout.setSizeFull();
            rightLayout.addStyleName("v-layout-content-overview-panel-level2");

            horizontalLayout.addComponents(leftLayout, rightLayout);

            final Agency agency = dataContainer.load(Long.valueOf(pageId));
            if (agency != null) {
                // Card panel for Agency details
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

                createCardHeader(cardContent, "Agency Details");

                // Attributes layout
                final VerticalLayout attributesLayout = new VerticalLayout();
                attributesLayout.setSpacing(true);
                attributesLayout.setWidth("100%");
                cardContent.addComponent(attributesLayout);

                // Display fields in a card layout (skipping null or empty ones)
                addInfoRowIfNotNull(attributesLayout, "Agency Name:", agency.getAgencyName(), VaadinIcons.FLAG);
                addInfoRowIfNotNull(attributesLayout, "Description:", agency.getDescription(), VaadinIcons.FILE_TEXT);

                // Right layout: portals grid
                getGridFactory().createBasicBeanItemGrid(rightLayout, Portal.class, agency.getPortals(),
                        PORTAL, PORTAL_GRID_COLUMN_ORDER, PORTAL_GRID_HIDE_COLUMNS,
                        PORTAL_GRID_LISTENER, null, null);
            }
        }

        getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_AGENCY_VIEW, ApplicationEventGroup.ADMIN,
                NAME, null, pageId);

        return content;
    }

}

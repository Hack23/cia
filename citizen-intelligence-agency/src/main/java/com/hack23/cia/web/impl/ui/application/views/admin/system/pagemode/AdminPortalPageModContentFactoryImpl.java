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

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.Portal;
import com.hack23.cia.model.internal.application.system.impl.Portal_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminPortalPageModContentFactoryImpl.
 */
@Component
public final class AdminPortalPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

    private static final String[] COLUMN_ORDER = {
        "hjid",
        "portalName",
        "description",
        "portalType",
        "googleMapApiKey",
        "modelObjectVersion"
    };

    private static final String[] HIDE_COLUMNS = {
        "hjid",
        "modelObjectId",
        "googleMapApiKey",
        "modelObjectVersion"
    };

    /** The Constant NAME. */
    public static final String NAME = AdminViews.ADMIN_PORTAL_VIEW_NAME;

    private static final String PORTAL2 = "Portal";

    /**
     * Instantiates a new admin portal page mod content factory impl.
     */
    public AdminPortalPageModContentFactoryImpl() {
        super(NAME);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
        final VerticalLayout content = createPanelContent();
        content.setStyleName("admin-portal-content");

        final String pageId = getPageId(parameters);
        final int pageNr = getPageNr(parameters);

        getMenuItemFactory().createMainPageMenuBar(menuBar);

        createPageHeader(panel, content,
            "Admin Portal Management",
            "Portal Overview",
            "Centralized management interface for accessing and controlling administrative tools."
        );

        final DataContainer<Portal, Long> dataContainer = getApplicationManager().getDataContainer(Portal.class);

        final List<Portal> pageOrderBy = dataContainer.getPageOrderBy(
            pageNr,
            DEFAULT_RESULTS_PER_PAGE,
            Portal_.portalName
        );

        getPagingUtil().createPagingControls(
            content,
            NAME,
            pageId,
            dataContainer.getSize(),
            pageNr,
            DEFAULT_RESULTS_PER_PAGE
        );

        getGridFactory().createBasicBeanItemGrid(
            content,
            Portal.class,
            pageOrderBy,
            PORTAL2,
            COLUMN_ORDER,
            HIDE_COLUMNS,
            new PageItemPropertyClickListener(AdminViews.ADMIN_PORTAL_VIEW_NAME, "hjid"),
            null,
            null
        );

        if (pageId != null && !pageId.isEmpty()) {
            final Portal portal = dataContainer.load(Long.valueOf(pageId));
            if (portal != null) {
                // Create a card-style layout for Portal details
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

                content.addComponent(cardPanel);

                // Card Header
                final HorizontalLayout headerLayout = new HorizontalLayout();
                headerLayout.setSpacing(true);
                headerLayout.setWidth("100%");
                headerLayout.addStyleName("card-header-section");

                final String titleText = "Portal Details";
                final Label titleLabel = new Label(titleText, ContentMode.HTML);
                titleLabel.addStyleName("card-title");
                titleLabel.setWidthUndefined();
                headerLayout.addComponent(titleLabel);

                cardContent.addComponent(headerLayout);

                // Divider
                final Label divider = new Label("<hr/>", ContentMode.HTML);
                divider.addStyleName("card-divider");
                divider.setWidth("100%");
                cardContent.addComponent(divider);

                // Attributes layout
                final VerticalLayout attributesLayout = new VerticalLayout();
                attributesLayout.setSpacing(true);
                attributesLayout.setWidth("100%");
                cardContent.addComponent(attributesLayout);

                // Display each field if not null or empty
                addInfoRowIfNotNull(attributesLayout, "Portal Name:", portal.getPortalName(), VaadinIcons.GLOBE_WIRE);
                addInfoRowIfNotNull(attributesLayout, "Description:", portal.getDescription(), VaadinIcons.FILE_TEXT);
                addInfoRowIfNotNull(attributesLayout, "Portal Type:", portal.getPortalType().toString(), VaadinIcons.VIEWPORT);
                addInfoRowIfNotNull(attributesLayout, "Google Map API Key:", portal.getGoogleMapApiKey(), VaadinIcons.KEY);
            }
        }

        getPageActionEventHelper().createPageEvent(
            ViewAction.VISIT_ADMIN_PORTAL_VIEW,
            ApplicationEventGroup.ADMIN,
            NAME,
            null,
            pageId
        );

        return content;
    }

    /**
     * Adds an info row to the parent layout if value is not null or empty.
     *
     * @param parent the parent layout
     * @param caption the field caption
     * @param value the field value
     * @param icon the VaadinIcons icon
     */
    private void addInfoRowIfNotNull(final VerticalLayout parent, final String caption, final String value, final VaadinIcons icon) {
        if (value != null && !value.trim().isEmpty() && !"null".equalsIgnoreCase(value)) {
            parent.addComponent(createInfoRow(caption, value, icon));
        }
    }

    /**
     * Creates a simple info row (caption and value) with an optional icon.
     *
     * @param caption the field caption
     * @param value   the field value
     * @param icon    a VaadinIcons icon
     * @return a HorizontalLayout representing the info row
     */
    private HorizontalLayout createInfoRow(final String caption, final String value, final VaadinIcons icon) {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.addStyleName("metric-label");
        layout.setWidthUndefined();

        if (icon != null) {
            final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
            iconLabel.addStyleName("card-info-icon");
            layout.addComponent(iconLabel);
        }

        final Label captionLabel = new Label(caption);
        captionLabel.addStyleName("card-info-caption");

        final Label valueLabel = new Label(value);
        valueLabel.addStyleName("card-info-value");

        layout.addComponents(captionLabel, valueLabel);
        return layout;
    }
}

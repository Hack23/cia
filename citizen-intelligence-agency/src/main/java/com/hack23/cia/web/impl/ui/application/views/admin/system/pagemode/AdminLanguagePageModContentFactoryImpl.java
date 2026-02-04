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

import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractAdminSystemPageModContentFactoryImpl;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.LanguageData;
import com.hack23.cia.model.internal.application.system.impl.LanguageData_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
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
 * The Class AdminLanguagePageModContentFactoryImpl.
 */
@Component
public final class AdminLanguagePageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

    /** The Constant COLUMN_ORDER. */
    private static final String[] COLUMN_ORDER = {
        "hjid",
        "languageName",
        "modelObjectVersion"
    };

    /** The Constant HIDE_COLUMNS. */
    private static final String[] HIDE_COLUMNS = {
        "hjid",
        "modelObjectId",
        "modelObjectVersion",
        "createdDate",
        "lastModifiedDate"
    };

    /** The Constant LANGUAGE_DATA. */
    private static final String LANGUAGE_DATA = "LanguageData";

    /** The Constant LISTENER. */
    private static final PageItemPropertyClickListener LISTENER =
            new PageItemPropertyClickListener(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, "hjid");

    /** The Constant NAME. */
    public static final String NAME = AdminViews.ADMIN_LANGUAGE_VIEW_NAME;

    /**
     * Instantiates a new admin language page mod content factory impl.
     */
    public AdminLanguagePageModContentFactoryImpl() {
        super(NAME);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
        final VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.setMargin(true);
        content.setSpacing(true);

        getMenuItemFactory().createMainPageMenuBar(menuBar);

        CardInfoRowUtil.createPageHeader(panel, content,
            AdminViewConstants.ADMIN_LANGUAGE_MANAGEMENT,
            AdminViewConstants.LANGUAGE_OVERVIEW,
            AdminViewConstants.LANGUAGE_ADMINISTRATION);

        final HorizontalLayout horizontalLayout = createHorizontalLayout();
        content.addComponent(horizontalLayout);
        content.setExpandRatio(horizontalLayout, ContentRatio.LARGE);

        final DataContainer<LanguageData, Long> dataContainer =
                getApplicationManager().getDataContainer(LanguageData.class);

        final List<LanguageData> pageOrderBy = dataContainer.getPageOrderBy(
                getPageNr(parameters),
                DEFAULT_RESULTS_PER_PAGE,
                LanguageData_.languageName
        );

        getPagingUtil().createPagingControls(
                content,
                NAME,
                getPageId(parameters),
                dataContainer.getSize(),
                getPageNr(parameters),
                DEFAULT_RESULTS_PER_PAGE
        );

        getGridFactory().createBasicBeanItemGrid(
                horizontalLayout,
                LanguageData.class,
                pageOrderBy,
                LANGUAGE_DATA,
                COLUMN_ORDER,
                HIDE_COLUMNS,
                LISTENER,
                null,
                null
        );

        if (getPageId(parameters) != null && !getPageId(parameters).isEmpty()) {
            final LanguageData languageData = dataContainer.load(Long.valueOf(getPageId(parameters)));
            if (languageData != null) {
                // Create a card-style panel to display LanguageData details
                final Panel cardPanel = new Panel();
                cardPanel.addStyleName("politician-overview-card"); // Reuse existing style
                cardPanel.setWidth("100%");
                cardPanel.setHeightUndefined();
                Responsive.makeResponsive(cardPanel);

                final VerticalLayout cardContent = new VerticalLayout();
                cardContent.setMargin(true);
                cardContent.setSpacing(true);
                cardContent.setWidth("100%");
                cardPanel.setContent(cardContent);

                horizontalLayout.addComponent(cardPanel);
                horizontalLayout.setExpandRatio(cardPanel, ContentRatio.LARGE_FORM);

                CardInfoRowUtil.createCardHeader(cardContent, AdminViewConstants.LANGUAGE_DETAILS);

                // Attributes layout
                final VerticalLayout attributesLayout = new VerticalLayout();
                attributesLayout.setSpacing(true);
                attributesLayout.setWidth("100%");
                cardContent.addComponent(attributesLayout);

                // Display each field if not null or empty
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.LANGUAGE_NAME, languageData.getLanguageName(), VaadinIcons.GLOBE);
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.CREATED_DATE, String.valueOf(languageData.getCreatedDate()), VaadinIcons.CALENDAR);
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.LAST_MODIFIED_DATE, String.valueOf(languageData.getLastModifiedDate()), VaadinIcons.CALENDAR_CLOCK);
                CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.LANGUAGE_ENABLED, String.valueOf(languageData.isLanguageEnabled()), VaadinIcons.CHECK);
            }
        }

        getPageActionEventHelper().createPageEvent(
                ViewAction.VISIT_ADMIN_LANGUAGE_VIEW,
                ApplicationEventGroup.ADMIN,
                NAME,
                null,
                getPageId(parameters)
        );

        return content;
    }

}

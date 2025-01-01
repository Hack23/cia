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

import java.text.MessageFormat;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.admin.ManageUserAccountRequest;
import com.hack23.cia.service.api.action.admin.ManageUserAccountRequest.AccountOperation;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.ManageUserAccountClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@Component
public final class AdminUserAccountPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {


    /** The Constant BUTTON_ID_PATTERN. */
    private static final String BUTTON_ID_PATTERN = "{0}.{1}";

    /** The Constant BUTTON_PATTERN. */
    private static final String BUTTON_PATTERN = "Perform {0}";

    private static final String[] COLUMN_ORDER = { "hjid", "modelObjectId", "modelObjectVersion", "createdDate",
            "userId", "username", "userType", "userRole", "userpassword", "email", "country", "numberOfVisits" };

    private static final String[] HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion", "userId",
            "userpassword", "address" };

    private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
            AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, "hjid");

    /** The Constant NAME. */
    public static final String NAME = AdminViews.ADMIN_USERACCOUNT_VIEW_NAME;

    private static final String USER_ACCOUNT = "UserAccount";

    /**
     * Instantiates a new admin user account page mod content factory impl.
     */
    public AdminUserAccountPageModContentFactoryImpl() {
        super(NAME);
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
        final VerticalLayout content = createPanelContent();

        final String pageId = getPageId(parameters);
        final int pageNr = getPageNr(parameters);

        getMenuItemFactory().createMainPageMenuBar(menuBar);

        createPageHeader(panel, content, "Admin User Account Management", "User Account Overview",
                "Manage user accounts, including roles, permissions, and activity logs.");

        final DataContainer<UserAccount, Long> dataContainer = getApplicationManager()
                .getDataContainer(UserAccount.class);

        final List<UserAccount> pageOrderBy = dataContainer.getPageOrderBy(pageNr, DEFAULT_RESULTS_PER_PAGE,
                UserAccount_.createdDate);

        getPagingUtil().createPagingControls(content, NAME, pageId, dataContainer.getSize(), pageNr,
                DEFAULT_RESULTS_PER_PAGE);

        getGridFactory().createBasicBeanItemGrid(content, UserAccount.class, pageOrderBy, USER_ACCOUNT,
                COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

        if (pageId != null && !pageId.isEmpty()) {
            final UserAccount userAccount = dataContainer.load(Long.valueOf(pageId));
            if (userAccount != null) {
                // Instead of form fields, use a card layout similar to other views.
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

                final String titleText = "User Account Details";
                final Label titleLabel = new Label(titleText, ContentMode.HTML);
                titleLabel.addStyleName("card-title");
                titleLabel.setWidthUndefined();
                headerLayout.addComponent(titleLabel);

                cardContent.addComponent(headerLayout);

                // Divider line
                final Label divider = new Label("<hr/>", ContentMode.HTML);
                divider.addStyleName("card-divider");
                divider.setWidth("100%");
                cardContent.addComponent(divider);

                // Attributes layout
                final VerticalLayout attributesLayout = new VerticalLayout();
                attributesLayout.setSpacing(true);
                attributesLayout.setWidth("100%");
                cardContent.addComponent(attributesLayout);

                // Display fields using a card layout approach with null-check
                addInfoRowIfNotNull(attributesLayout, "Username:", userAccount.getUsername(), VaadinIcons.USER);
                addInfoRowIfNotNull(attributesLayout, "Created Date:", String.valueOf(userAccount.getCreatedDate()),
                        VaadinIcons.CALENDAR);
                addInfoRowIfNotNull(attributesLayout, "Email:", userAccount.getEmail(), VaadinIcons.ENVELOPE);
                addInfoRowIfNotNull(attributesLayout, "Country:", userAccount.getCountry(), VaadinIcons.GLOBE);
                addInfoRowIfNotNull(attributesLayout, "Number Of Visits:", String.valueOf(userAccount.getNumberOfVisits()),
                        VaadinIcons.EYE);

                final VerticalLayout overviewLayout = new VerticalLayout();
                overviewLayout.setSizeFull();
                overviewLayout.addStyleName("v-layout-content-overview-panel-level2");
                content.addComponent(overviewLayout);
                content.setExpandRatio(overviewLayout, ContentRatio.LARGE);

                final ResponsiveRow grid = RowUtil.createGridLayout(overviewLayout);

                for (final AccountOperation accountOperation : ManageUserAccountRequest.AccountOperation.values()) {
                    final ManageUserAccountRequest request = new ManageUserAccountRequest();
                    request.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
                    request.setAccountOperation(accountOperation);
                    request.setUserAcountId(userAccount.getUserId());
                    final Button accountOperationButton = new Button(
                            MessageFormat.format(BUTTON_PATTERN, accountOperation), VaadinIcons.BULLSEYE);
                    accountOperationButton.addClickListener(new ManageUserAccountClickListener(request));
                    accountOperationButton.setId(
                            MessageFormat.format(BUTTON_ID_PATTERN, ViewAction.START_AGENT_BUTTON, accountOperation));
                    RowUtil.createRowItem(grid, accountOperationButton, "Will perform useraccount action");
                }
            }
        }

        getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_USERACCOUNT_VIEW, ApplicationEventGroup.ADMIN,
                NAME, null, pageId);

        return content;
    }

    @Override
    public boolean matches(final String page, final String parameters) {
        return NAME.equals(page) && (parameters == null || !parameters.contains("CHARTS"));
    }

}

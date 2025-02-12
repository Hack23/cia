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
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import java.util.Locale;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMinistryConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MinistryOverviewPageModContentFactoryImpl.
 */
@Component
public final class MinistryOverviewPageModContentFactoryImpl extends AbstractMinistryPageModContentFactoryImpl {

    /**
     * Instantiates a new ministry overview page mod content factory impl.
     */
    // Existing constructor remains the same
    public MinistryOverviewPageModContentFactoryImpl() {
        super();
    }

    /**
     * Creates the content.
     *
     * @param parameters the parameters
     * @param menuBar the menu bar
     * @param panel the panel
     * @return the layout
     */
    @Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
    @Override
    public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
        final VerticalLayout panelContent = createPanelContent();
        panel.setContent(panelContent);

        final String pageId = getPageId(parameters);
        final ViewRiksdagenMinistry viewRiksdagenMinistry = getItem(parameters);

        getMinistryMenuItemFactory().createMinistryMenuBar(menuBar, pageId);

        CardInfoRowUtil.createPageHeader(panel,
                panelContent,
                MinistryViewConstants.OVERVIEW_TITLE + " " + viewRiksdagenMinistry.getNameId(),
                MinistryViewConstants.OVERVIEW_SUBTITLE,
                MinistryViewConstants.OVERVIEW_DESC);

        final Link addMinistryPageLink = getPageLinkFactory().addMinistryPageLink(viewRiksdagenMinistry);
        panelContent.addComponent(addMinistryPageLink);
        panelContent.setExpandRatio(addMinistryPageLink, ContentRatio.SMALL);

        // Create a card panel
        final Panel cardPanel = new Panel();
        cardPanel.addStyleName(MinistryLayoutConstants.MINISTRY_CARD_STYLE);
        cardPanel.setWidth(MinistryLayoutConstants.WIDTH_100_PERCENT);
        cardPanel.setHeightUndefined();
        Responsive.makeResponsive(cardPanel);

        final VerticalLayout cardContent = new VerticalLayout();
        cardContent.setMargin(true);
        cardContent.setSpacing(true);
        cardContent.setWidth("100%");
        cardPanel.setContent(cardContent);

        panelContent.addComponent(cardPanel);
        panelContent.setExpandRatio(cardPanel, ContentRatio.SMALL_GRID);

        // Header layout
        CardInfoRowUtil.createCardHeader(cardContent,viewRiksdagenMinistry.getNameId());

        // Multi-column layout for attributes
        final HorizontalLayout attributesLayout = new HorizontalLayout();
        attributesLayout.setSpacing(true);
        attributesLayout.setWidth("100%");
        cardContent.addComponent(attributesLayout);

        // Column 1: Basic Ministry Details
        final VerticalLayout profileDetailsLayout = CardInfoRowUtil.createSectionLayout(MinistrySectionConstants.MINISTRY_PROFILE);

        createProfileDetails(profileDetailsLayout, viewRiksdagenMinistry);

        // Column 2: Service Statistics
        final VerticalLayout serviceStatsLayout = CardInfoRowUtil.createSectionLayout(MinistryViewConstants.MINISTRY_SERVICE_STATS_TITLE);

        createServiceStatisticsSection(serviceStatsLayout, viewRiksdagenMinistry);

        // Column 3: Document Statistics
        final VerticalLayout documentStatsLayout = CardInfoRowUtil.createSectionLayout(MinistryViewConstants.MINISTRY_DOCUMENT_STATS_TITLE);

        createDocumentStatisticsSection(documentStatsLayout, viewRiksdagenMinistry);

        // Add all columns to the attributes layout
        attributesLayout.addComponents(profileDetailsLayout, serviceStatsLayout, documentStatsLayout);

        // Add the overview layout
        final VerticalLayout overviewLayout = new VerticalLayout();
        overviewLayout.setSizeFull();
        panelContent.addComponent(overviewLayout);
        panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

        getMinistryMenuItemFactory().createOverviewPage(overviewLayout, pageId);

        getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_VIEW, ApplicationEventGroup.USER, NAME,
                parameters, pageId);

        return panelContent;
    }

    /**
     * Creates the profile details.
     *
     * @param layout the layout
     * @param ministry the ministry
     */
    private void createProfileDetails(final VerticalLayout layout, final ViewRiksdagenMinistry ministry) {
        layout.addComponent(CardInfoRowUtil.createInfoRow(
            MinistryFieldConstants.MINISTRY_ID_LABEL,
            ministry.getNameId(),
            MinistryIconConstants.INFO_ICON,
            MinistryFieldConstants.MINISTRY_ID_DESC));

        layout.addComponent(CardInfoRowUtil.createInfoRow(
            MinistryFieldConstants.STATUS_LABEL,
            ministry.isActive() ? "Active" : "Inactive",
            MinistryIconConstants.FLAG_ICON,
            MinistryFieldConstants.STATUS_DESC));

        layout.addComponent(CardInfoRowUtil.createInfoRow(MinistryViewConstants.MINISTRY_CURRENT_MEMBERS_LABEL,
            String.valueOf(ministry.getCurrentMemberSize()), VaadinIcons.GROUP,
            MinistryDescriptionConstants.CURRENT_MINISTRY_MEMBERS_DESC));
        layout.addComponent(CardInfoRowUtil.createInfoRow(MinistryViewConstants.MINISTRY_ACTIVITY_LEVEL_LABEL,
            ministry.getActivityLevel(), VaadinIcons.CHART,
            MinistryDescriptionConstants.ACTIVITY_LEVEL_DESC));
    }


    /**
     * Creates the service statistics section.
     *
     * @param layout the layout
     * @param ministry the ministry
     */
    private void createServiceStatisticsSection(final VerticalLayout layout, final ViewRiksdagenMinistry ministry) {
        layout.addComponent(CardInfoRowUtil.createInfoRow(
            MinistryStatisticsConstants.TOTAL_ASSIGNMENTS,
            String.valueOf(ministry.getTotalAssignments()),
            MinistryIconConstants.TASK_ICON,
            MinistryStatisticsConstants.ASSIGNMENTS_DESC));

        layout.addComponent(CardInfoRowUtil.createInfoRow(
            MinistryStatisticsConstants.FIRST_ASSIGNMENT,
            String.valueOf(ministry.getFirstAssignmentDate()),
            MinistryIconConstants.CALENDAR_ICON,
            MinistryStatisticsConstants.FIRST_DATE_DESC));

        layout.addComponent(CardInfoRowUtil.createInfoRow(MinistryViewConstants.MINISTRY_LAST_ASSIGNMENT_LABEL,
            String.valueOf(ministry.getLastAssignmentDate()), VaadinIcons.CALENDAR_CLOCK,
            MinistryDescriptionConstants.LAST_ASSIGNMENT_DESC));
        layout.addComponent(CardInfoRowUtil.createInfoRow(MinistryViewConstants.MINISTRY_TOTAL_DAYS_SERVED_LABEL,
            String.valueOf(ministry.getTotalDaysServed()), VaadinIcons.CLOCK,
            MinistryDescriptionConstants.TOTAL_DAYS_SERVED_DESC));
    }

    /**
     * Creates the document statistics section.
     *
     * @param layout the layout
     * @param ministry the ministry
     */
    private void createDocumentStatisticsSection(final VerticalLayout layout, final ViewRiksdagenMinistry ministry) {
        layout.addComponent(CardInfoRowUtil.createInfoRow(
            MinistryStatisticsConstants.TOTAL_DOCUMENTS,
            String.valueOf(ministry.getTotalDocuments()),
            MinistryIconConstants.FILE_ICON,
            MinistryStatisticsConstants.TOTAL_DOCS_DESC));

        layout.addComponent(CardInfoRowUtil.createInfoRow(
            MinistryStatisticsConstants.AVG_DOCUMENTS,
            String.valueOf(ministry.getAvgDocumentsPerMember()),
            MinistryIconConstants.CHART_ICON,
            MinistryStatisticsConstants.AVG_DOCS_DESC));

        layout.addComponent(CardInfoRowUtil.createInfoRow(MinistryViewConstants.MINISTRY_DOCUMENTS_LAST_YEAR_LABEL,
            String.valueOf(ministry.getDocumentsLastYear()), VaadinIcons.FILE_O,
            MinistryDescriptionConstants.DOCUMENTS_LAST_YEAR_DESC));
        layout.addComponent(CardInfoRowUtil.createInfoRow(MinistryViewConstants.MINISTRY_AVG_DOCUMENTS_MEMBER_LABEL,
            String.format(Locale.ENGLISH,"%.1f", ministry.getAvgDocumentsPerMember()),
            VaadinIcons.CHART_LINE, MinistryDescriptionConstants.AVG_DOCUMENTS_MEMBER_DESC));
        layout.addComponent(CardInfoRowUtil.createInfoRow(MinistryViewConstants.MINISTRY_TOTAL_PROPOSITIONS_LABEL,
            String.valueOf(ministry.getTotalPropositions()), VaadinIcons.FILE_PRESENTATION,
            MinistryDescriptionConstants.TOTAL_PROPOSITIONS_DESC));
        layout.addComponent(CardInfoRowUtil.createInfoRow(MinistryViewConstants.MINISTRY_GOVERNMENT_BILLS_LABEL,
            String.valueOf(ministry.getTotalGovernmentBills()), VaadinIcons.FILE_TEXT_O,
            MinistryDescriptionConstants.GOVERNMENT_BILLS_DESC));
    }

    /**
     * Matches.
     *
     * @param page the page
     * @param parameters the parameters
     * @return true, if successful
     */
    @Override
    public boolean matches(final String page, final String parameters) {
    	return PageCommandMinistryConstants.COMMAND_MINISTRY_OVERVIEW.matches(page, parameters);
    }
}
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

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
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
        final VerticalLayout serviceStatsLayout = CardInfoRowUtil.createSectionLayout("Service Statistics");

        createServiceStatisticsSection(serviceStatsLayout, viewRiksdagenMinistry);

        // Column 3: Document Statistics
        final VerticalLayout documentStatsLayout = CardInfoRowUtil.createSectionLayout("Document Statistics");

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

    private void createProfileDetails(VerticalLayout layout, ViewRiksdagenMinistry ministry) {
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

        layout.addComponent(CardInfoRowUtil.createInfoRow("Current Members:", String.valueOf(ministry.getCurrentMemberSize()),
            VaadinIcons.GROUP, "Number of current ministry members"));
        layout.addComponent(CardInfoRowUtil.createInfoRow("Activity Level:", ministry.getActivityLevel(),
            VaadinIcons.CHART, "Ministry's current activity level"));
    }

 
    private void createServiceStatisticsSection(VerticalLayout layout, ViewRiksdagenMinistry ministry) {
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

        layout.addComponent(CardInfoRowUtil.createInfoRow("Last Assignment:", String.valueOf(ministry.getLastAssignmentDate()),
            VaadinIcons.CALENDAR_CLOCK, "Date of most recent assignment"));
        layout.addComponent(CardInfoRowUtil.createInfoRow("Total Days Served:", String.valueOf(ministry.getTotalDaysServed()),
            VaadinIcons.CLOCK, "Total days of ministry service"));
    }

    private void createDocumentStatisticsSection(VerticalLayout layout, ViewRiksdagenMinistry ministry) {
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

        layout.addComponent(CardInfoRowUtil.createInfoRow("Documents Last Year:", String.valueOf(ministry.getDocumentsLastYear()),
            VaadinIcons.FILE_O, "Documents produced in the last year"));
        layout.addComponent(CardInfoRowUtil.createInfoRow("Avg Documents/Member:", String.format(Locale.ENGLISH,"%.1f", ministry.getAvgDocumentsPerMember()),
            VaadinIcons.CHART_LINE, "Average documents per ministry member"));
        layout.addComponent(CardInfoRowUtil.createInfoRow("Total Propositions:", String.valueOf(ministry.getTotalPropositions()),
            VaadinIcons.FILE_PRESENTATION, "Total number of propositions"));
        layout.addComponent(CardInfoRowUtil.createInfoRow("Government Bills:", String.valueOf(ministry.getTotalGovernmentBills()),
            VaadinIcons.FILE_TEXT_O, "Total number of government bills"));
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
        final String pageId = getPageId(parameters);
        return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
                || parameters.contains(PageMode.OVERVIEW.toString()));
    }
}
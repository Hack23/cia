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
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.icons.VaadinIcons;
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

        createPageHeader(panel,
                panelContent,
                "Ministry Overview " + viewRiksdagenMinistry.getNameId(),
                "Ministry Details",
                "Detailed view of ministries, their roles, and responsibilities.");

        final Link addMinistryPageLink = getPageLinkFactory().addMinistryPageLink(viewRiksdagenMinistry);
        panelContent.addComponent(addMinistryPageLink);
        panelContent.setExpandRatio(addMinistryPageLink, ContentRatio.SMALL);

        // Create a card panel
        final Panel cardPanel = createCardPanel(viewRiksdagenMinistry.getNameId());
        final VerticalLayout cardContent = (VerticalLayout) cardPanel.getContent();

        panelContent.addComponent(cardPanel);
        panelContent.setExpandRatio(cardPanel, ContentRatio.SMALL_GRID);

        // Multi-column layout for attributes
        final VerticalLayout attributesLayout = new VerticalLayout();
        attributesLayout.setSpacing(true);
        attributesLayout.setWidth("100%");
        cardContent.addComponent(attributesLayout);

        // Column 1: Basic Ministry Details
        final VerticalLayout profileDetailsLayout = new VerticalLayout();
        profileDetailsLayout.setSpacing(true);
        profileDetailsLayout.addStyleName("card-details-column");
        profileDetailsLayout.setWidthUndefined();

        addInfoRowsToLayout(profileDetailsLayout,
            new InfoRowItem("Ministry ID:", viewRiksdagenMinistry.getNameId(), VaadinIcons.INFO_CIRCLE),
            new InfoRowItem("Status:", viewRiksdagenMinistry.isActive() ? "Active" : "Inactive", VaadinIcons.FLAG),
            new InfoRowItem("Current Members:", String.valueOf(viewRiksdagenMinistry.getCurrentMemberSize()), VaadinIcons.GROUP),
            new InfoRowItem("Activity Level:", viewRiksdagenMinistry.getActivityLevel(), VaadinIcons.CHART)
        );

        // Column 2: Service Statistics
        final VerticalLayout serviceStatsLayout = new VerticalLayout();
        serviceStatsLayout.setSpacing(true);
        serviceStatsLayout.addStyleName("card-details-column");
        serviceStatsLayout.setWidthUndefined();

        addInfoRowsToLayout(serviceStatsLayout,
            new InfoRowItem("Total Assignments:", String.valueOf(viewRiksdagenMinistry.getTotalAssignments()), VaadinIcons.TASKS),
            new InfoRowItem("First Assignment:", String.valueOf(viewRiksdagenMinistry.getFirstAssignmentDate()), VaadinIcons.CALENDAR),
            new InfoRowItem("Last Assignment:", String.valueOf(viewRiksdagenMinistry.getLastAssignmentDate()), VaadinIcons.CALENDAR_CLOCK),
            new InfoRowItem("Total Days Served:", String.valueOf(viewRiksdagenMinistry.getTotalDaysServed()), VaadinIcons.CLOCK)
        );

        // Column 3: Document Statistics
        final VerticalLayout documentStatsLayout = new VerticalLayout();
        documentStatsLayout.setSpacing(true);
        documentStatsLayout.addStyleName("card-details-column");
        documentStatsLayout.setWidthUndefined();

        addInfoRowsToLayout(documentStatsLayout,
            new InfoRowItem("Total Documents:", String.valueOf(viewRiksdagenMinistry.getTotalDocuments()), VaadinIcons.FILE_TEXT),
            new InfoRowItem("Documents Last Year:", String.valueOf(viewRiksdagenMinistry.getDocumentsLastYear()), VaadinIcons.FILE_O),
            new InfoRowItem("Avg Documents/Member:", String.format(Locale.ENGLISH,"%.1f", viewRiksdagenMinistry.getAvgDocumentsPerMember()), VaadinIcons.CHART_LINE),
            new InfoRowItem("Total Propositions:", String.valueOf(viewRiksdagenMinistry.getTotalPropositions()), VaadinIcons.FILE_PRESENTATION),
            new InfoRowItem("Government Bills:", String.valueOf(viewRiksdagenMinistry.getTotalGovernmentBills()), VaadinIcons.FILE_TEXT_O)
        );

        // Add all columns to the attributes layout
        attributesLayout.addComponents(profileDetailsLayout, serviceStatsLayout, documentStatsLayout);

        // Add the overview layout
        final VerticalLayout overviewLayout = createOverviewLayout();
        panelContent.addComponent(overviewLayout);
        panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

        getMinistryMenuItemFactory().createOverviewPage(overviewLayout, pageId);

        getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_VIEW, ApplicationEventGroup.USER, NAME,
                parameters, pageId);

        return panelContent;
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

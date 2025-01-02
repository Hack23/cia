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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.DecisionFlowChartManager;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.DecisionFlowValueChangeListener;
import com.hack23.cia.web.widgets.charts.SankeyChart;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ParliamentDecisionFlowPageModContentFactoryImpl.
 */
@Component
public final class CommitteeDecisionFlowPageModContentFactoryImpl extends AbstractCommitteePageModContentFactoryImpl {

    /** The Constant DEFAULT_YEAR. */
    private static final String DEFAULT_YEAR = "2023/24";

    /** The Constant YEAR_SELECTOR_LABEL. */
    private static final String YEAR_SELECTOR_LABEL = "Select year";


    /** The decision flow chart manager. */
    @Autowired
    private DecisionFlowChartManager decisionFlowChartManager;

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
        final String pageId = getPageId(parameters);
        final ViewRiksdagenCommittee committee = getItem(parameters);

        setupMenuAndHeader(menuBar, panel, panelContent, pageId, committee);
        final String selectedYear = extractSelectedYear(parameters);
        final Map<String, List<ViewRiksdagenCommittee>> committeeMap = loadCommitteeMap();

        addYearSelector(panelContent, selectedYear, pageId);
        addDecisionFlowChart(panelContent, committee, committeeMap, selectedYear);
        addDecisionSummary(panelContent, committee, selectedYear);

        recordPageVisit(parameters, pageId);

        return panelContent;
    }

    /**
     * Creates list of available years for selection.
     * Goes from 2010/11 up to (currentYear+1)/(currentYear+2).
     *
     * @return Unmodifiable list of year strings in format "YYYY/YY"
     */
    private static List<String> createAvailableYears() {
        final int currentYear = LocalDate.now((ZoneOffset.UTC)).getYear();
        return IntStream.rangeClosed(2010, currentYear + 1)
            .mapToObj(year -> String.format(Locale.ENGLISH,"%d/%02d", year, (year + 1) % 100))
            .sorted(Comparator.reverseOrder()) // Most recent years first
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                Collections::unmodifiableList
            ));
    }

    /**
     * Setup menu and header.
     *
     * @param menuBar the menu bar
     * @param panel the panel
     * @param panelContent the panel content
     * @param pageId the page id
     * @param committee the committee
     */
    private void setupMenuAndHeader(MenuBar menuBar, Panel panel, VerticalLayout panelContent,
            String pageId, ViewRiksdagenCommittee committee) {
        getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);
        createPageHeader(panel, panelContent,
            "Committee Decision Flow " + committee.getEmbeddedId().getDetail(),
            "Decision Flow",
            "Analyze decision-making processes within committees."
        );
    }

    /**
     * Extract selected year.
     *
     * @param parameters the parameters
     * @return the string
     */
    private String extractSelectedYear(String parameters) {
        if (parameters != null && parameters.contains("[") && parameters.contains("]")) {
            return parameters.substring(
                parameters.indexOf('[') + 1,
                parameters.lastIndexOf(']')
            );
        }
        return DEFAULT_YEAR;
    }

    /**
     * Load committee map.
     *
     * @return the map
     */
    private Map<String, List<ViewRiksdagenCommittee>> loadCommitteeMap() {
        return getApplicationManager()
            .getDataContainer(ViewRiksdagenCommittee.class)
            .getAll()
            .stream()
            .collect(Collectors.groupingBy(
                committee -> committee.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH)
            ));
    }

    /**
     * Adds the year selector.
     *
     * @param panelContent the panel content
     * @param selectedYear the selected year
     * @param pageId the page id
     */
    private void addYearSelector(VerticalLayout panelContent, String selectedYear, String pageId) {
        final ComboBox<String> yearSelector = new ComboBox<>(YEAR_SELECTOR_LABEL, createAvailableYears());
        yearSelector.setWidth("200px");
        yearSelector.setEmptySelectionAllowed(false);
        yearSelector.setSelectedItem(selectedYear);
        yearSelector.addValueChangeListener(new DecisionFlowValueChangeListener(NAME, pageId));

        panelContent.addComponent(yearSelector);
        panelContent.setExpandRatio(yearSelector, ContentRatio.SMALL2);
    }

    /**
     * Adds the decision flow chart.
     *
     * @param panelContent the panel content
     * @param committee the committee
     * @param committeeMap the committee map
     * @param selectedYear the selected year
     */
    private void addDecisionFlowChart(VerticalLayout panelContent, ViewRiksdagenCommittee committee,
            Map<String, List<ViewRiksdagenCommittee>> committeeMap, String selectedYear) {
        final SankeyChart chart = decisionFlowChartManager.createCommitteeDecisionFlow(
            committee, committeeMap, selectedYear
        );
        chart.setWidth("100%");

        panelContent.addComponent(chart);
        panelContent.setExpandRatio(chart, ContentRatio.LARGE);
    }

    /**
     * Adds the decision summary.
     *
     * @param panelContent the panel content
     * @param committee the committee
     * @param selectedYear the selected year
     */
    private void addDecisionSummary(VerticalLayout panelContent,
            ViewRiksdagenCommittee committee, String selectedYear) {
        final TextArea summaryArea = decisionFlowChartManager.createCommitteeeDecisionSummary(
            committee, selectedYear
        );
        summaryArea.setSizeFull();
        summaryArea.setReadOnly(true);

        panelContent.addComponent(summaryArea);
        panelContent.setExpandRatio(summaryArea, ContentRatio.SMALL_GRID);
    }

    /**
     * Record page visit.
     *
     * @param parameters the parameters
     * @param pageId the page id
     */
    private void recordPageVisit(String parameters, String pageId) {
        getPageActionEventHelper().createPageEvent(
            ViewAction.VISIT_COMMITTEE_VIEW,
            ApplicationEventGroup.USER,
            NAME,
            parameters,
            pageId
        );
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
        return NAME.equals(page)
            && StringUtils.contains(parameters, PageMode.CHARTS.toString())
            && parameters.contains(ChartIndicators.DECISION_FLOW_CHART.toString());
    }
}
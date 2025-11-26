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
package com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.impl.performance.PerformanceMetrics;
import com.hack23.cia.service.impl.performance.PerformanceMetricsService;
import com.hack23.cia.service.impl.performance.PerformanceTimer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.DecisionFlowChartManager;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandParliamentRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
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
public final class ParliamentDecisionFlowPageModContentFactoryImpl extends AbstractParliamentPageModContentFactoryImpl {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParliamentDecisionFlowPageModContentFactoryImpl.class);

    /** The Constant DEFAULT_YEAR. */
    private static final String DEFAULT_YEAR = "2023/24";

    /** The Constant YEAR_SELECTOR_LABEL. */
    private static final String YEAR_SELECTOR_LABEL = "Select year";

    /** The Constant PERFORMANCE_THRESHOLD_MS. */
    private static final long PERFORMANCE_THRESHOLD_MS = 2000L;

    /** The decision flow chart manager. */
    @Autowired
    private DecisionFlowChartManager decisionFlowChartManager;

    /** The performance metrics service. */
    @Autowired
    private PerformanceMetricsService performanceMetricsService;

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
        final PerformanceTimer timer = performanceMetricsService.startTimer("decision_flow_page");
        
        try {
            final VerticalLayout panelContent = createPanelContent();

            timer.phase("menu_header_setup");
            setupMenuAndHeader(menuBar, panel, panelContent);
            
            timer.phase("data_loading");
            final String selectedYear = extractSelectedYear(parameters, DEFAULT_YEAR);
            final Map<String, List<ViewRiksdagenCommittee>> committeeMap = loadCommitteeMap();

            timer.phase("ui_component_creation");
            addYearSelector(panelContent, selectedYear);
            
            timer.phase("chart_generation");
            addDecisionFlowChart(panelContent, committeeMap, selectedYear);
            
            timer.phase("summary_generation");
            addDecisionSummary(panelContent, committeeMap, selectedYear);

            timer.phase("event_recording");
            recordPageVisit(parameters, selectedYear);
            
            timer.stop();
            
            logPerformanceMetrics(timer, selectedYear);

            return panelContent;
            
        } catch (final Exception e) {
            timer.error(e);
            throw e;
        }
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
     */
    private void setupMenuAndHeader(final MenuBar menuBar, final Panel panel, final VerticalLayout panelContent) {
        getParliamentMenuItemFactory().createParliamentTopicMenu(menuBar);
        CardInfoRowUtil.createPageHeader(panel, panelContent,
            ParliamentPageTitleConstants.DECISION_FLOW_TITLE,
            ParliamentPageTitleConstants.DECISION_FLOW_SUBTITLE,
            ParliamentPageTitleConstants.DECISION_FLOW_DESC);
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
     */
    private void addYearSelector(final VerticalLayout panelContent, final String selectedYear) {
        final ComboBox<String> yearSelector = new ComboBox<>(YEAR_SELECTOR_LABEL, createAvailableYears());
        yearSelector.setWidth("200px");
        yearSelector.setEmptySelectionAllowed(false);
        yearSelector.setSelectedItem(selectedYear);
        yearSelector.addValueChangeListener(new DecisionFlowValueChangeListener(NAME, ""));

        panelContent.addComponent(yearSelector);
        panelContent.setExpandRatio(yearSelector, ContentRatio.SMALL);
    }

    /**
     * Adds the decision flow chart.
     *
     * @param panelContent the panel content
     * @param committeeMap the committee map
     * @param selectedYear the selected year
     */
    private void addDecisionFlowChart(final VerticalLayout panelContent,
            final Map<String, List<ViewRiksdagenCommittee>> committeeMap, final String selectedYear) {
        final SankeyChart chart = decisionFlowChartManager.createAllDecisionFlow(committeeMap, selectedYear);
        chart.setWidth("100%");

        panelContent.addComponent(chart);
        panelContent.setExpandRatio(chart, ContentRatio.LARGE);
}

    /**
     * Adds the decision summary.
     *
     * @param panelContent the panel content
     * @param committeeMap the committee map
     * @param selectedYear the selected year
     */
    private void addDecisionSummary(final VerticalLayout panelContent,
            final Map<String, List<ViewRiksdagenCommittee>> committeeMap, final String selectedYear) {
        final TextArea summaryArea = decisionFlowChartManager.createCommitteeeDecisionSummary(committeeMap, selectedYear);
        summaryArea.setSizeFull();
        summaryArea.setReadOnly(true);

        panelContent.addComponent(summaryArea);
        panelContent.setExpandRatio(summaryArea, ContentRatio.SMALL_GRID);
    }

    /**
     * Record page visit.
     *
     * @param parameters the parameters
     * @param selectedYear the selected year
     */
    private void recordPageVisit(final String parameters, final String selectedYear) {
        getPageActionEventHelper().createPageEvent(
            ViewAction.VISIT_PARLIAMENT_RANKING_VIEW,
            ApplicationEventGroup.USER,
            NAME,
            parameters,
            selectedYear
        );
    }

    /**
     * Log performance metrics and send alerts if thresholds exceeded.
     *
     * @param timer the performance timer
     * @param year the selected year
     */
    private void logPerformanceMetrics(final PerformanceTimer timer, final String year) {
        final PerformanceMetrics metrics = timer.getMetrics();
        
        LOGGER.info("Decision Flow Page Performance for year {}: Total={}ms, Chart={}ms, DB={}ms, Memory={}MB",
            year,
            metrics.getTotalTimeMs(),
            metrics.getPhaseTimeMs("chart_generation"),
            metrics.getPhaseTimeMs("data_loading"),
            metrics.getMemoryUsedMb());
        
        performanceMetricsService.recordMetrics("decision_flow_page", year, metrics);
        
        if (metrics.getTotalTimeMs() > PERFORMANCE_THRESHOLD_MS) {
            LOGGER.warn("PERFORMANCE DEGRADATION: Decision flow page took {}ms (target: <{}ms) for year {}",
                metrics.getTotalTimeMs(), PERFORMANCE_THRESHOLD_MS, year);
            
            performanceMetricsService.sendPerformanceAlert(
                "Decision Flow Page Slow",
                String.format("Page load: %dms for year %s (threshold: %dms)", 
                    metrics.getTotalTimeMs(), year, PERFORMANCE_THRESHOLD_MS));
        }
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
    	return PageCommandParliamentRankingConstants.COMMAND_CHARTS_DECISION_FLOW.matches(page, parameters);
    }
}

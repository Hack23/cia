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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GovernmentBodyChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyChartDataManagerImpl.
 */
@Service
public final class GovernmentBodyChartDataManagerImpl extends AbstractChartDataManagerImpl
        implements GovernmentBodyChartDataManager {

    /** The Constant ALL_GOVERNMENT_BODIES. */
    private static final String ALL_GOVERNMENT_BODIES = "All government bodies";

    /** The Constant ANNUAL_EXPENDITURE. */
    private static final String ANNUAL_EXPENDITURE = "Annual Expenditure";

    /** The Constant ANNUAL_HEADCOUNT. */
    private static final String ANNUAL_HEADCOUNT = "Annual headcount";

    /** The Constant ANNUAL_HEADCOUNT_ALL_MINISTRIES. */
    private static final String ANNUAL_HEADCOUNT_ALL_MINISTRIES = "Annual headcount, all ministries";

    /** The Constant ANNUAL_HEADCOUNT_SUMMARY_ALL_GOVERNMENT_BODIES. */
    private static final String ANNUAL_HEADCOUNT_SUMMARY_ALL_GOVERNMENT_BODIES =
            "Annual headcount summary, all government bodies";

    /** The Constant ANNUAL_HEADCOUNT_TOTAL_ALL_GOVERNMENT_BODIES. */
    private static final String ANNUAL_HEADCOUNT_TOTAL_ALL_GOVERNMENT_BODIES =
            "Annual headcount total all government bodies";

    /** The Constant ANNUAL_INCOME. */
    private static final String ANNUAL_INCOME = "Annual Income";

    /** The Constant EXPENDITURE_GROUP_NAME. */
    private static final String EXPENDITURE_GROUP_NAME = "Utgiftsområdesnamn";

    /** The Constant INKOMSTTITELGRUPPSNAMN. */
    private static final String INKOMSTTITELGRUPPSNAMN = "Inkomsttitelgruppsnamn";

    /** The Constant INKOMSTTITELSNAMN. */
    private static final String INKOMSTTITELSNAMN = "Inkomsttitelsnamn";

    /** The Constant ANSLAGSPOSTSNAMN. */
    private static final String ANSLAGSPOSTSNAMN = "Anslagspostsnamn";

    /** The esv api. */
    @Autowired
    private EsvApi esvApi;


    /**
     * Adds a data point to the DataSeries if the year and value are valid and value > 0.
     *
     * @param dataSeries the data series
     * @param year the year
     * @param value the value
     */
    private static void addDataPoint(final DataSeries dataSeries, final Integer year, final Number value) {
        if (dataSeries == null || year == null || value == null) {
            return;
        }
        final double doubleValue = value.doubleValue();
        if (doubleValue > 0) {
            // Ensure consistent date format: " 01-JAN-YYYY"
            final String formattedDate = String.format(Locale.ENGLISH," 01-JAN-%d", year);
            dataSeries.add(formattedDate, doubleValue);
        }
    }

    /**
     * Creates a chart using the provided data/series objects, then appends it to the given layout.
     *
     * @param layout the layout
     * @param label the label
     * @param dataSeries the data series
     * @param series the series
     */
    private void addChartToLayout(final AbstractOrderedLayout layout, final String label,
                                  final DataSeries dataSeries, final Series series) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        Objects.requireNonNull(label, "Label cannot be null");

        final DCharts chart = new DCharts()
                .setDataSeries(dataSeries)
                .setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series))
                .show();

        ChartUtils.addChart(layout, label, chart, true);
    }

    /**
     * Consolidates logic to retrieve, group, and process data by a descriptive field
     * (e.g., 'Utgiftsområdesnamn' or 'Inkomsttitelgruppsnamn'), feeding the results into a DataSeries.
     *
     * @param dataSeries the data series
     * @param series the series
     * @param groupedData the grouped data
     */
    private void buildAnnualOutcomeDataSeriesByField(final DataSeries dataSeries, final Series series,
            final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> groupedData) {
        Optional.ofNullable(groupedData)
            .ifPresent(data -> data.entrySet().stream()
                .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
                .forEach(entry -> {
                    series.addSeries(new XYseries().setLabel(entry.getKey()));
                    dataSeries.newSeries();

                    entry.getValue().stream()
                        .filter(Objects::nonNull)
                        .forEach(summary -> {
                            // Only process if we have a valid year and value map
                            Optional.ofNullable(summary.getValueMap())
                                .ifPresent(valueMap -> {
                                    // Get the year directly from the summary
                                    final Integer year = summary.getYear();
                                    // Get the total value for this year
                                    final Double totalValue = valueMap.values().stream()
                                        .filter(Objects::nonNull)
                                        .mapToDouble(Number::doubleValue)
                                        .sum();

                                    // Only add if we have valid year and value
                                    if (year != null && totalValue > 0) {
                                        addDataPoint(dataSeries, year, totalValue);
                                    }
                                });
                        });
                }));
    }

    /**
     * Helper method to generate a headcount data series given a map of year -> list of summaries.
     *
     * @param dataSeries the data series
     * @param series the series
     * @param yearlyData the yearly data
     * @param label the label
     */
    private void buildHeadcountDataSeries(final DataSeries dataSeries, final Series series,
                                          final Map<Integer, List<GovernmentBodyAnnualSummary>> yearlyData,
                                          final String label) {
    	Optional.ofNullable(yearlyData)
        .ifPresent(data -> {
            series.addSeries(new XYseries().setLabel(label));
            dataSeries.newSeries();

            data.entrySet().stream()
                .filter(entry -> Objects.nonNull(entry.getValue()) && !entry.getValue().isEmpty())
                .forEach(entry -> {
                    final int totalHeadcount = entry.getValue().stream()
                        .filter(Objects::nonNull)
                        .mapToInt(GovernmentBodyAnnualSummary::getHeadCount)
                        .sum();
                    addDataPoint(dataSeries, entry.getKey(), totalHeadcount);
                });
        });
    }

    /**
     * Consolidates logic for creating a chart that is grouped by some string field in descriptionFields.
     *
     * @param layout the layout
     * @param field the field
     * @param chartLabel the chart label
     */
    private void createMinistryFieldSummary(final AbstractOrderedLayout layout, final String field, final String chartLabel) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        Objects.requireNonNull(field, "Field cannot be null");
        Objects.requireNonNull(chartLabel, "Chart label cannot be null");

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();

        Optional.ofNullable(esvApi.getGovernmentBodyReportByMinistry())
            .ifPresent(reportByMinistry ->
                reportByMinistry.entrySet().stream()
                    .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
                    .forEach(entry -> {
                        final Map<Integer, Double> annualTotals = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .filter(summary -> Optional.ofNullable(summary.getDescriptionFields())
                                .map(fields -> fields.get(field))
                                .isPresent())
                            .collect(Collectors.groupingBy(
                                GovernmentBodyAnnualOutcomeSummary::getYear,
                                Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)
                            ));

                        if (!annualTotals.isEmpty()) {
                            series.addSeries(new XYseries().setLabel(entry.getKey()));
                            dataSeries.newSeries();
                            annualTotals.forEach((year, total) ->
                                addDataPoint(dataSeries, year + 1, total));
                        }
                    }));

        addChartToLayout(layout, chartLabel, dataSeries, series);
    }

    // ==================== Public Chart Methods ====================

    @Override
    public void createGovernmentBodyExpenditureSummaryChart(final VerticalLayout layout) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report =
                esvApi.getGovernmentBodyReportByField(EXPENDITURE_GROUP_NAME);

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();
        buildAnnualOutcomeDataSeriesByField(dataSeries, series, report);

        addChartToLayout(layout, ANNUAL_EXPENDITURE, dataSeries, series);
    }

    @Override
    public void createGovernmentBodyExpenditureSummaryChart(final VerticalLayout layout,
                                                            final String governmentBodyName) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

        final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> allSummaries = esvApi.getGovernmentBodyReport();

        final List<GovernmentBodyAnnualOutcomeSummary> summariesForBody = Optional.ofNullable(allSummaries)
                .map(summaries -> summaries.get(governmentBodyName))
                .orElse(Collections.emptyList());

        final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> grouped = summariesForBody.stream()
                .filter(Objects::nonNull)
                .filter(summary -> Optional.ofNullable(summary.getDescriptionFields())
                    .map(fields -> fields.get(ANSLAGSPOSTSNAMN))
                    .isPresent())
                .sorted(Comparator.comparing(GovernmentBodyAnnualOutcomeSummary::getYear))
                .collect(Collectors.groupingBy(
                    summary -> summary.getDescriptionFields().get(ANSLAGSPOSTSNAMN)));

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();
        buildAnnualOutcomeDataSeriesByField(dataSeries, series, grouped);

        addChartToLayout(layout, governmentBodyName + " " + ANNUAL_EXPENDITURE, dataSeries, series);
    }

    @Override
    public void createGovernmentBodyHeadcountSummaryChart(final VerticalLayout layout) {
        Objects.requireNonNull(layout, "Layout cannot be null");

        final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getData();
        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();

        buildHeadcountDataSeries(dataSeries, series, map, ALL_GOVERNMENT_BODIES);
        addChartToLayout(layout, ANNUAL_HEADCOUNT_TOTAL_ALL_GOVERNMENT_BODIES, dataSeries, series);
    }

    @Override
    public void createGovernmentBodyHeadcountSummaryChart(final VerticalLayout layout,
                                                          final String governmentBodyName) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

        final Map<Integer, GovernmentBodyAnnualSummary> map = esvApi.getDataPerGovernmentBody(governmentBodyName);

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();
        series.addSeries(new XYseries().setLabel(governmentBodyName));
        dataSeries.newSeries();

        Optional.ofNullable(map)
        .ifPresent(yearSummaryMap -> yearSummaryMap.entrySet().stream()
            .filter(entry -> Objects.nonNull(entry.getValue()))
            .forEach(entry -> addDataPoint(dataSeries, entry.getKey(), entry.getValue().getHeadCount())));

        addChartToLayout(layout, governmentBodyName + " " + ANNUAL_HEADCOUNT, dataSeries, series);
    }

    @Override
    public void createGovernmentBodyIncomeSummaryChart(final VerticalLayout layout) {
        Objects.requireNonNull(layout, "Layout cannot be null");

        final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report =
                esvApi.getGovernmentBodyReportByField(INKOMSTTITELGRUPPSNAMN);

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();
        buildAnnualOutcomeDataSeriesByField(dataSeries, series, report);

        addChartToLayout(layout, ANNUAL_INCOME, dataSeries, series);
    }

    @Override
    public void createGovernmentBodyIncomeSummaryChart(final VerticalLayout layout,
                                                       final String governmentBodyName) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

        final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> allSummaries = esvApi.getGovernmentBodyReport();

        final List<GovernmentBodyAnnualOutcomeSummary> summariesForBody = Optional.ofNullable(allSummaries)
                .map(summaries -> summaries.get(governmentBodyName))
                .orElse(Collections.emptyList());

        final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> grouped = summariesForBody.stream()
                .filter(Objects::nonNull)
                .filter(summary -> Optional.ofNullable(summary.getDescriptionFields())
                    .map(fields -> fields.get(INKOMSTTITELSNAMN))
                    .isPresent())
                .sorted(Comparator.comparing(GovernmentBodyAnnualOutcomeSummary::getYear))
                .collect(Collectors.groupingBy(
                    summary -> summary.getDescriptionFields().get(INKOMSTTITELSNAMN)));

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();
        buildAnnualOutcomeDataSeriesByField(dataSeries, series, grouped);

        addChartToLayout(layout, governmentBodyName + " " + ANNUAL_INCOME, dataSeries, series);
    }

    @Override
    public void createMinistryGovernmentBodyExpenditureSummaryChart(final AbstractOrderedLayout layout) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        createMinistryFieldSummary(layout, EXPENDITURE_GROUP_NAME, "MinistryGovernmentBodySpendingSummaryChart");
    }

    @Override
    public void createMinistryGovernmentBodyExpenditureSummaryChart(final VerticalLayout layout,
                                                                    final String governmentBodyName) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

        final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report =
                esvApi.getGovernmentBodyReportByFieldAndMinistry(EXPENDITURE_GROUP_NAME, governmentBodyName);

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();
        buildAnnualOutcomeDataSeriesByField(dataSeries, series, report);

        addChartToLayout(layout, governmentBodyName + " " + ANNUAL_EXPENDITURE, dataSeries, series);
    }

    @Override
    public void createMinistryGovernmentBodyHeadcountSummaryChart(final AbstractOrderedLayout layout) {
        Objects.requireNonNull(layout, "Layout cannot be null");

        final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getData();
        final List<String> ministryNames = esvApi.getMinistryNames();

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();

        Optional.ofNullable(ministryNames)
            .filter(names -> map != null)
            .ifPresent(names -> names.stream()
                .forEach(ministryName -> {
                    series.addSeries(new XYseries().setLabel(ministryName));
                    dataSeries.newSeries();

                    map.entrySet().stream()
                        .filter(entry -> entry.getValue() != null)
                        .forEach(entry -> {
                            final int headcount = entry.getValue().stream()
                                .filter(Objects::nonNull)
                                .filter(summary -> summary.getMinistry() != null)
                                .filter(summary -> summary.getMinistry().equalsIgnoreCase(ministryName))
                                .mapToInt(GovernmentBodyAnnualSummary::getHeadCount)
                                .sum();
                            addDataPoint(dataSeries, entry.getKey(), headcount);
                        });
                }));

        addChartToLayout(layout, ANNUAL_HEADCOUNT_ALL_MINISTRIES, dataSeries, series);
    }

    @Override
    public void createMinistryGovernmentBodyHeadcountSummaryChart(final AbstractOrderedLayout layout,
                                                                  final String governmentBodyName) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

        final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getDataPerMinistry(governmentBodyName);
        final List<String> governmentBodyNames = esvApi.getGovernmentBodyNames(governmentBodyName);

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();

        Optional.ofNullable(governmentBodyNames)
            .filter(names -> map != null)
            .ifPresent(names -> names.stream()
                .forEach(govBodyName -> {
                    series.addSeries(new XYseries().setLabel(govBodyName));
                    dataSeries.newSeries();

                    map.entrySet().stream()
                        .filter(entry -> entry.getValue() != null)
                        .forEach(entry -> {
                            final int headcount = entry.getValue().stream()
                                .filter(Objects::nonNull)
                                .filter(summary -> govBodyName.equalsIgnoreCase(summary.getName()))
                                .mapToInt(GovernmentBodyAnnualSummary::getHeadCount)
                                .sum();
                            addDataPoint(dataSeries, entry.getKey(), headcount);
                        });
                }));

        if (map != null && governmentBodyNames != null) {
            addChartToLayout(layout,
                    governmentBodyName + " " + ANNUAL_HEADCOUNT_SUMMARY_ALL_GOVERNMENT_BODIES,
                    dataSeries,
                    series);
        }
    }

    @Override
    public void createMinistryGovernmentBodyIncomeSummaryChart(final AbstractOrderedLayout layout) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        createMinistryFieldSummary(layout, INKOMSTTITELGRUPPSNAMN, "MinistryGovernmentBodyIncomeSummaryChart");
    }

    @Override
    public void createMinistryGovernmentBodyIncomeSummaryChart(final VerticalLayout layout,
                                                               final String governmentBodyName) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

        final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report =
                esvApi.getGovernmentBodyReportByFieldAndMinistry(INKOMSTTITELGRUPPSNAMN, governmentBodyName);

        final DataSeries dataSeries = new DataSeries();
        final Series series = new Series();
        buildAnnualOutcomeDataSeriesByField(dataSeries, series, report);

        addChartToLayout(layout, governmentBodyName + " " + ANNUAL_INCOME, dataSeries, series);
    }
}

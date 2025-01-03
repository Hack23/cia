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

import java.util.List;
import java.util.Map;
import java.util.Objects;
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

@Service
public final class GovernmentBodyChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements GovernmentBodyChartDataManager {

	private static final String ALL_GOVERNMENT_BODIES = "All government bodies";
	private static final String ANNUAL_EXPENDITURE = "Annual Expenditure";
	private static final String ANNUAL_HEADCOUNT = "Annual headcount";
	private static final String ANNUAL_HEADCOUNT_ALL_MINISTRIES = "Annual headcount, all ministries";
	private static final String ANNUAL_HEADCOUNT_SUMMARY_ALL_GOVERNMENT_BODIES = "Annual headcount summary, all government bodies";
	private static final String ANNUAL_HEADCOUNT_TOTAL_ALL_GOVERNMENT_BODIES = "Annual headcount total all government bodies";
	private static final String ANNUAL_INCOME = "Annual Income";
	private static final String ANSLAGSPOSTSNAMN = "Anslagspostsnamn";
	private static final String EXPENDITURE_GROUP_NAME = "Utgiftsområdesnamn";
	private static final String FIRST_JAN_DATA_SUFFIX = " 01-JAN-";
	private static final String INKOMSTTITELGRUPPSNAMN = "Inkomsttitelgruppsnamn";
	private static final String INKOMSTTITELSNAMN = "Inkomsttitelsnamn";

	@Autowired
	private EsvApi esvApi;

	private static String formatDateForChart(Integer year) {
		return FIRST_JAN_DATA_SUFFIX + year;
	}

	private static void addDataPoint(DataSeries dataSeries, Integer year, Number value) {
		if (year != null && value != null && value.doubleValue() > 0) {
			dataSeries.add(formatDateForChart(year), value.doubleValue());
		}
	}

//    private static void addAnnualSummaryData(final DataSeries dataSeries, final Series series,
//            final Entry<String, List<GovernmentBodyAnnualOutcomeSummary>> entry,
//            final List<GovernmentBodyAnnualOutcomeSummary> allValues) {
//        series.addSeries(new XYseries().setLabel(entry.getKey()).setShowLabel(true));
//        dataSeries.newSeries();
//
//        allValues.stream()
//                .collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear,
//                        Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)))
//                .forEach((year, sum) -> {
//                    if (sum > 0) {
//                        addDataPoint(dataSeries, year, sum);
//                    }
//                });
//    }

	private void addChartToLayout(AbstractOrderedLayout layout, String label, DataSeries dataSeries, Series series) {
		ChartUtils.addChart(layout, label,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);
	}

	private void processAnnualData(DataSeries dataSeries, Series series,
			Map<String, List<GovernmentBodyAnnualOutcomeSummary>> data) {
		data.forEach((key, values) -> {
			if (!values.isEmpty()) {
				series.addSeries(new XYseries().setLabel(key));
				dataSeries.newSeries();
				values.forEach(summary -> {
					summary.getValueMap().forEach((date, value) -> {
						if (value != null && value > 0) {
							dataSeries.add(formatDateForChart(summary.getYear()), value);
						}
					});
				});
			}
		});
	}

	@Override
	public void createGovernmentBodyExpenditureSummaryChart(final VerticalLayout layout) {
		Objects.requireNonNull(layout, "Layout cannot be null");

		Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report = esvApi
				.getGovernmentBodyReportByField(EXPENDITURE_GROUP_NAME);
		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		processAnnualData(dataSeries, series, report);
		addChartToLayout(layout, ANNUAL_EXPENDITURE, dataSeries, series);
	}

	@Override
	public void createGovernmentBodyExpenditureSummaryChart(final VerticalLayout layout,
			final String governmentBodyName) {
		Objects.requireNonNull(layout, "Layout cannot be null");
		Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

		Map<String, List<GovernmentBodyAnnualOutcomeSummary>> summaries = esvApi.getGovernmentBodyReport()
				.get(governmentBodyName).stream().filter(p -> p.getDescriptionFields().get(ANSLAGSPOSTSNAMN) != null)
				.collect(Collectors.groupingBy(t -> t.getDescriptionFields().get(ANSLAGSPOSTSNAMN)));

		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		processAnnualData(dataSeries, series, summaries);
		addChartToLayout(layout, governmentBodyName + " " + ANNUAL_EXPENDITURE, dataSeries, series);
	}

	@Override
	public void createGovernmentBodyHeadcountSummaryChart(final VerticalLayout layout) {
		Objects.requireNonNull(layout, "Layout cannot be null");

		Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getData();
		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		series.addSeries(new XYseries().setLabel(ALL_GOVERNMENT_BODIES));
		dataSeries.newSeries();

		map.forEach((year, values) -> {
			int totalHeadcount = values.stream().mapToInt(GovernmentBodyAnnualSummary::getHeadCount).sum();
			addDataPoint(dataSeries, year, totalHeadcount);
		});

		addChartToLayout(layout, ANNUAL_HEADCOUNT_TOTAL_ALL_GOVERNMENT_BODIES, dataSeries, series);
	}

	@Override
	public void createGovernmentBodyHeadcountSummaryChart(final VerticalLayout layout,
			final String governmentBodyName) {
		Objects.requireNonNull(layout, "Layout cannot be null");
		Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

		Map<Integer, GovernmentBodyAnnualSummary> map = esvApi.getDataPerGovernmentBody(governmentBodyName);
		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		series.addSeries(new XYseries().setLabel(governmentBodyName));
		dataSeries.newSeries();

		map.forEach((year, summary) -> addDataPoint(dataSeries, year, summary.getHeadCount()));
		addChartToLayout(layout, governmentBodyName + " " + ANNUAL_HEADCOUNT, dataSeries, series);
	}

	@Override
	public void createGovernmentBodyIncomeSummaryChart(final VerticalLayout layout) {
		Objects.requireNonNull(layout, "Layout cannot be null");

		Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report = esvApi
				.getGovernmentBodyReportByField(INKOMSTTITELGRUPPSNAMN);
		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		processAnnualData(dataSeries, series, report);
		addChartToLayout(layout, ANNUAL_INCOME, dataSeries, series);
	}

	@Override
	public void createGovernmentBodyIncomeSummaryChart(final VerticalLayout layout, final String governmentBodyName) {
		Objects.requireNonNull(layout, "Layout cannot be null");
		Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

		Map<String, List<GovernmentBodyAnnualOutcomeSummary>> summaries = esvApi.getGovernmentBodyReport()
				.get(governmentBodyName).stream().filter(p -> p.getDescriptionFields().get(INKOMSTTITELSNAMN) != null)
				.collect(Collectors.groupingBy(t -> t.getDescriptionFields().get(INKOMSTTITELSNAMN)));

		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		processAnnualData(dataSeries, series, summaries);
		addChartToLayout(layout, governmentBodyName + " " + ANNUAL_INCOME, dataSeries, series);
	}

	@Override
	public void createMinistryGovernmentBodyExpenditureSummaryChart(final AbstractOrderedLayout layout) {
		Objects.requireNonNull(layout, "Layout cannot be null");
		createMinistrySummary(layout, EXPENDITURE_GROUP_NAME, "MinistryGovernmentBodySpendingSummaryChart");
	}

	@Override
	public void createMinistryGovernmentBodyExpenditureSummaryChart(final VerticalLayout layout,
			final String governmentBodyName) {
		Objects.requireNonNull(layout, "Layout cannot be null");
		Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

		Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report = esvApi
				.getGovernmentBodyReportByFieldAndMinistry(EXPENDITURE_GROUP_NAME, governmentBodyName);
		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		processAnnualData(dataSeries, series, report);
		addChartToLayout(layout, governmentBodyName + " " + ANNUAL_EXPENDITURE, dataSeries, series);
	}

	@Override
	public void createMinistryGovernmentBodyHeadcountSummaryChart(final AbstractOrderedLayout layout) {
		Objects.requireNonNull(layout, "Layout cannot be null");

		Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getData();
		List<String> ministryNames = esvApi.getMinistryNames();
		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		ministryNames.forEach(ministryName -> {
			series.addSeries(new XYseries().setLabel(ministryName));
			dataSeries.newSeries();

			map.forEach((year, summaries) -> {
				int headcount = summaries.stream().filter(p -> p.getMinistry().equalsIgnoreCase(ministryName))
						.mapToInt(GovernmentBodyAnnualSummary::getHeadCount).sum();
				addDataPoint(dataSeries, year, headcount);
			});
		});

		addChartToLayout(layout, ANNUAL_HEADCOUNT_ALL_MINISTRIES, dataSeries, series);
	}

	@Override
	public void createMinistryGovernmentBodyHeadcountSummaryChart(final AbstractOrderedLayout layout,
			final String governmentBodyName) {
		Objects.requireNonNull(layout, "Layout cannot be null");
		Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

		Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getDataPerMinistry(governmentBodyName);
		List<String> governmentBodyNames = esvApi.getGovernmentBodyNames(governmentBodyName);
		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		governmentBodyNames.forEach(govBodyName -> {
			series.addSeries(new XYseries().setLabel(govBodyName));
			dataSeries.newSeries();

			map.forEach((year, summaries) -> {
				int headcount = summaries.stream().filter(p -> p.getName().equalsIgnoreCase(govBodyName))
						.mapToInt(GovernmentBodyAnnualSummary::getHeadCount).sum();
				addDataPoint(dataSeries, year, headcount);
			});
		});

		addChartToLayout(layout, governmentBodyName + " " + ANNUAL_HEADCOUNT_SUMMARY_ALL_GOVERNMENT_BODIES, dataSeries,
				series);
	}

	@Override
	public void createMinistryGovernmentBodyIncomeSummaryChart(final AbstractOrderedLayout layout) {
		Objects.requireNonNull(layout, "Layout cannot be null");
		createMinistrySummary(layout, INKOMSTTITELGRUPPSNAMN, "MinistryGovernmentBodyIncomeSummaryChart");
	}

	@Override
	public void createMinistryGovernmentBodyIncomeSummaryChart(final VerticalLayout layout,
			final String governmentBodyName) {
		Objects.requireNonNull(layout, "Layout cannot be null");
		Objects.requireNonNull(governmentBodyName, "Government body name cannot be null");

		Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report = esvApi
				.getGovernmentBodyReportByFieldAndMinistry(INKOMSTTITELGRUPPSNAMN, governmentBodyName);
		DataSeries dataSeries = new DataSeries();
		Series series = new Series();

		processAnnualData(dataSeries, series, report);
		addChartToLayout(layout, governmentBodyName + " " + ANNUAL_INCOME, dataSeries, series);
	}

	private void createMinistrySummary(final AbstractOrderedLayout layout, final String field, final String label) {
		Objects.requireNonNull(layout, "Layout cannot be null");
		Objects.requireNonNull(field, "Field cannot be null");
		Objects.requireNonNull(label, "Label cannot be null");

		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry = esvApi
				.getGovernmentBodyReportByMinistry();
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		if (reportByMinistry != null) {
			reportByMinistry.forEach((ministry, summaries) -> {
				if (summaries != null && !summaries.isEmpty()) {
					series.addSeries(new XYseries().setLabel(ministry));
					dataSeries.newSeries();

					Map<Integer, Double> annualSummaryMap = summaries.stream().filter(
							t -> t.getDescriptionFields() != null && t.getDescriptionFields().get(field) != null)
							.collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear,
									Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)));

					annualSummaryMap.forEach((year, total) -> {
						if (total != null && total > 0) {
							addDataPoint(dataSeries, year + 1, total);
						}
					});
				}
			});
		}

		addChartToLayout(layout, label, dataSeries, series);
	}

}
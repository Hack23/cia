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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
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
	private static final String ANNUAL_HEADCOUNT_SUMMARY_ALL_GOVERNMENT_BODIES = " Annual headcount summary, all government bodies";

	/** The Constant ANNUAL_HEADCOUNT_TOTAL_ALL_GOVERNMENT_BODIES. */
	private static final String ANNUAL_HEADCOUNT_TOTAL_ALL_GOVERNMENT_BODIES = "Annual headcount total all government bodies";

	/** The Constant ANNUAL_INCOME. */
	private static final String ANNUAL_INCOME = "Annual Income";

	/** The Constant ANSLAGSPOSTSNAMN. */
	private static final String ANSLAGSPOSTSNAMN = "Anslagspostsnamn";

	/** The Constant EXPENDITURE_GROUP_NAME. */
	private static final String EXPENDITURE_GROUP_NAME = "Utgiftsområdesnamn";

	/** The Constant FIRST_JAN_DATA_SUFFIX. */
	private static final String FIRST_JAN_DATA_SUFFIX = "-01-01";

	/** The Constant FIRST_OF_JAN. */
	private static final String FIRST_OF_JAN = "01-JAN-";

	/** The Constant INKOMSTTITELGRUPPSNAMN. */
	private static final String INKOMSTTITELGRUPPSNAMN = "Inkomsttitelgruppsnamn";

	/** The Constant INKOMSTTITELSNAMN. */
	private static final String INKOMSTTITELSNAMN = "Inkomsttitelsnamn";

	/** The esv api. */
	@Autowired
	private EsvApi esvApi;

	/**
	 * Instantiates a new government body chart data manager impl.
	 */
	public GovernmentBodyChartDataManagerImpl() {
		super();
	}

	/**
	 * Adds the annual summary data.
	 *
	 * @param dataSeries the data series
	 * @param series     the series
	 * @param entry      the entry
	 * @param allValues  the all values
	 */
	private static void addAnnualSummaryData(final DataSeries dataSeries, final Series series,
			final Entry<String, List<GovernmentBodyAnnualOutcomeSummary>> entry,
			final List<GovernmentBodyAnnualOutcomeSummary> allValues) {
		series.addSeries(new XYseries().setLabel(entry.getKey()).setShowLabel(true));
		dataSeries.newSeries();

		final Map<Integer, List<GovernmentBodyAnnualOutcomeSummary>> map = allValues.stream()
				.collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear));

		for (final Entry<Integer, List<GovernmentBodyAnnualOutcomeSummary>> data : map.entrySet()) {
			final List<GovernmentBodyAnnualOutcomeSummary> values = data.getValue();
			final double sum = values.stream().mapToDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal).sum();
			if (sum > 0) {
				dataSeries.add(data.getKey() + FIRST_JAN_DATA_SUFFIX, (int) sum);
			}
		}
	}

	/**
	 * Adds the data serie value.
	 *
	 * @param dataSeries the data series
	 * @param entry      the entry
	 * @param value      the value
	 */
	private static void addDataSerieValue(final DataSeries dataSeries, final Entry entry,
			final int value) {
		if (entry.getKey() != null && value > 0) {
			dataSeries.add(FIRST_OF_JAN + entry.getKey(), value);
		}
	}

	/**
	 * Adds the entry data.
	 *
	 * @param dataSeries       the data series
	 * @param simpleDateFormat the simple date format
	 * @param entry            the entry
	 */
	private static void addEntryData(final DataSeries dataSeries, final SimpleDateFormat simpleDateFormat,
			final Entry<String, List<GovernmentBodyAnnualOutcomeSummary>> entry) {
		for (final GovernmentBodyAnnualOutcomeSummary data : entry.getValue()) {
			final Map<Date, Double> valueMap = data.getValueMap();

			for (final Entry<Date, Double> entryData : valueMap.entrySet()) {
				if (entryData.getValue() != null && entryData.getValue().intValue() > 0) {
					dataSeries.add(simpleDateFormat.format(entryData.getKey()) , entryData.getValue().intValue());
				}
			}
		}
	}

	/**
	 * Adds the annual data.
	 *
	 * @param layout
	 *            the layout
	 * @param governmentBodyName
	 *            the government body name
	 * @param label
	 *            the label
	 * @param annualOutcomeSummaries
	 *            the annual outcome summaries
	 */
	private void addAnnualData(final VerticalLayout layout, final String governmentBodyName, final String label,
			final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> annualOutcomeSummaries) {
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
		for (final Entry<String, List<GovernmentBodyAnnualOutcomeSummary>> entry : annualOutcomeSummaries.entrySet()) {
			series.addSeries(new XYseries().setLabel(entry.getKey()));
			dataSeries.newSeries();

			addEntryData(dataSeries, simpleDateFormat, entry);
			}

		ChartUtils.addChart(layout, governmentBodyName + " " + label,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);
	}

	/**
	 * Adds the annual summary.
	 *
	 * @param report
	 *            the report
	 * @param layout
	 *            the layout
	 * @param label
	 *            the label
	 */
	private void addAnnualSummary(final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> report, final VerticalLayout layout,
			final String label) {

		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		for (final Entry<String, List<GovernmentBodyAnnualOutcomeSummary>> entry : report.entrySet()) {

			final List<GovernmentBodyAnnualOutcomeSummary> allValues = entry.getValue();

			if (!allValues.isEmpty()) {
				addAnnualSummaryData(dataSeries, series, entry, allValues);
			}
		}

		ChartUtils.addChart(layout, label,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);
	}

	/**
	 * Creates the government body expenditure summary chart.
	 *
	 * @param layout the layout
	 */
	@Override
	public void createGovernmentBodyExpenditureSummaryChart(final VerticalLayout layout) {
		addAnnualSummary(esvApi.getGovernmentBodyReportByField(EXPENDITURE_GROUP_NAME), layout, ANNUAL_EXPENDITURE);
	}

	/**
	 * Creates the government body expenditure summary chart.
	 *
	 * @param layout the layout
	 * @param governmentBodyName the government body name
	 */
	@Override
	public void createGovernmentBodyExpenditureSummaryChart(final VerticalLayout layout, final String governmentBodyName) {
		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> annualOutcomeSummaries = esvApi.getGovernmentBodyReport().get(governmentBodyName)
				.stream().filter(p -> p.getDescriptionFields().get(ANSLAGSPOSTSNAMN) != null)
				.collect(Collectors.groupingBy(t -> t.getDescriptionFields().get(ANSLAGSPOSTSNAMN)));

		addAnnualData(layout, governmentBodyName, ANNUAL_EXPENDITURE, annualOutcomeSummaries);
	}

	/**
	 * Creates the government body headcount summary chart.
	 *
	 * @param layout the layout
	 */
	@Override
	public void createGovernmentBodyHeadcountSummaryChart(final VerticalLayout layout) {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getData();

		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		series.addSeries(new XYseries().setLabel(ALL_GOVERNMENT_BODIES));
		dataSeries.newSeries();

		for (final Entry<Integer, List<GovernmentBodyAnnualSummary>> entry : map.entrySet()) {
			addDataSerieValue(dataSeries, entry, entry.getValue().stream().mapToInt(GovernmentBodyAnnualSummary::getHeadCount).sum());
		}

		ChartUtils.addChart(layout, ANNUAL_HEADCOUNT_TOTAL_ALL_GOVERNMENT_BODIES,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);

	}


	/**
	 * Creates the government body headcount summary chart.
	 *
	 * @param layout the layout
	 * @param governmentBodyName the government body name
	 */
	@Override
	public void createGovernmentBodyHeadcountSummaryChart(final VerticalLayout layout, final String governmentBodyName) {
		final Map<Integer, GovernmentBodyAnnualSummary> map = esvApi.getDataPerGovernmentBody(governmentBodyName);

		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		series.addSeries(new XYseries().setLabel(governmentBodyName));
		dataSeries.newSeries();

		for (final Entry<Integer, GovernmentBodyAnnualSummary> entry : map.entrySet()) {
			addDataSerieValue(dataSeries, entry, entry.getValue().getHeadCount());
		}

		ChartUtils.addChart(layout, governmentBodyName + " "+ ANNUAL_HEADCOUNT,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);
	}

	/**
	 * Creates the government body income summary chart.
	 *
	 * @param layout the layout
	 */
	@Override
	public void createGovernmentBodyIncomeSummaryChart(final VerticalLayout layout) {
		addAnnualSummary(esvApi.getGovernmentBodyReportByField(INKOMSTTITELGRUPPSNAMN), layout, ANNUAL_INCOME);
	}

	/**
	 * Creates the government body income summary chart.
	 *
	 * @param layout the layout
	 * @param governmentBodyName the government body name
	 */
	@Override
	public void createGovernmentBodyIncomeSummaryChart(final VerticalLayout layout, final String governmentBodyName) {
		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> annualOutcomeSummaries = esvApi.getGovernmentBodyReport().get(governmentBodyName)
				.stream().filter(p -> p.getDescriptionFields().get(INKOMSTTITELSNAMN) != null)
				.collect(Collectors.groupingBy(t -> t.getDescriptionFields().get(INKOMSTTITELSNAMN)));

		addAnnualData(layout, governmentBodyName, ANNUAL_INCOME, annualOutcomeSummaries);

	}

	/**
	 * Creates the ministry government body expenditure summary chart.
	 *
	 * @param layout the layout
	 */
	@Override
	public void createMinistryGovernmentBodyExpenditureSummaryChart(final AbstractOrderedLayout layout) {
		createMinistrySummary(layout,EXPENDITURE_GROUP_NAME,"MinistryGovernmentBodySpendingSummaryChart");
	}

	/**
	 * Creates the ministry government body expenditure summary chart.
	 *
	 * @param layout the layout
	 * @param governmentBodyName the government body name
	 */
	@Override
	public void createMinistryGovernmentBodyExpenditureSummaryChart(final VerticalLayout layout, final String governmentBodyName) {
		addAnnualSummary(esvApi.getGovernmentBodyReportByFieldAndMinistry(EXPENDITURE_GROUP_NAME, governmentBodyName), layout, ANNUAL_EXPENDITURE);
	}

	/**
	 * Creates the ministry government body headcount summary chart.
	 *
	 * @param layout the layout
	 */
	@Override
	public void createMinistryGovernmentBodyHeadcountSummaryChart(final AbstractOrderedLayout layout) {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getData();
		final List<String> ministryNames = esvApi.getMinistryNames();

		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		for (final String ministryName : ministryNames) {

			series.addSeries(new XYseries().setLabel(ministryName));
			dataSeries.newSeries();

			for (final Entry<Integer, List<GovernmentBodyAnnualSummary>> entry : map.entrySet()) {
				addDataSerieValue(dataSeries, entry, entry.getValue().stream()
						.filter((final GovernmentBodyAnnualSummary p) -> p.getMinistry().equalsIgnoreCase(ministryName))
						.mapToInt(GovernmentBodyAnnualSummary::getHeadCount).sum());
			}
		}

		ChartUtils.addChart(layout, ANNUAL_HEADCOUNT_ALL_MINISTRIES,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);

	}


	/**
	 * Creates the ministry government body headcount summary chart.
	 *
	 * @param layout the layout
	 * @param governmentBodyName the government body name
	 */
	@Override
	public void createMinistryGovernmentBodyHeadcountSummaryChart(final AbstractOrderedLayout layout,
			final String governmentBodyName) {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getDataPerMinistry(governmentBodyName);
		final List<String> governmentBodyNames = esvApi.getGovernmentBodyNames(governmentBodyName);

		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		for (final String govBodyName : governmentBodyNames) {

			series.addSeries(new XYseries().setLabel(govBodyName));
			dataSeries.newSeries();

			for (final Entry<Integer, List<GovernmentBodyAnnualSummary>> entry : map.entrySet()) {
				addDataSerieValue(dataSeries, entry, entry.getValue().stream()
						.filter((final GovernmentBodyAnnualSummary p) -> p.getName().equalsIgnoreCase(govBodyName))
						.mapToInt(GovernmentBodyAnnualSummary::getHeadCount).sum());
			}
		}

		ChartUtils.addChart(layout, governmentBodyName + " "+  ANNUAL_HEADCOUNT_SUMMARY_ALL_GOVERNMENT_BODIES,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);

	}

	/**
	 * Creates the ministry government body income summary chart.
	 *
	 * @param layout the layout
	 */
	@Override
	public void createMinistryGovernmentBodyIncomeSummaryChart(final AbstractOrderedLayout layout) {
		createMinistrySummary(layout,INKOMSTTITELGRUPPSNAMN,"MinistryGovernmentBodyIncomeSummaryChart");

	}



	/**
	 * Creates the ministry government body income summary chart.
	 *
	 * @param layout the layout
	 * @param governmentBodyName the government body name
	 */
	@Override
	public void createMinistryGovernmentBodyIncomeSummaryChart(final VerticalLayout layout, final String governmentBodyName) {
		addAnnualSummary(esvApi.getGovernmentBodyReportByFieldAndMinistry(INKOMSTTITELGRUPPSNAMN,governmentBodyName), layout, ANNUAL_INCOME);
	}

	/**
	 * Creates the ministry summary.
	 *
	 * @param layout the layout
	 * @param field   the field
	 * @param label   the label
	 */
	private void createMinistrySummary(final AbstractOrderedLayout layout, final String field, final String label) {
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry = esvApi.getGovernmentBodyReportByMinistry();

		for (final Entry<String, List<GovernmentBodyAnnualOutcomeSummary>> entry : reportByMinistry.entrySet()) {
			series.addSeries(new XYseries().setLabel(entry.getKey()));
			dataSeries.newSeries();
			final Map<Integer, Double> annualSummaryMap = entry.getValue().stream().filter(t -> t.getDescriptionFields().get(field) != null).collect(Collectors.groupingBy(GovernmentBodyAnnualOutcomeSummary::getYear,Collectors.summingDouble(GovernmentBodyAnnualOutcomeSummary::getYearTotal)));

			for (final Entry<Integer, Double> entryData : annualSummaryMap.entrySet()) {
				if (entryData.getValue() != null && entryData.getValue().intValue() > 0) {
					dataSeries.add(entryData.getKey() +1  +"-01-01" , entryData.getValue());
				}
			}
		}

		ChartUtils.addChart(layout, label,
				new DCharts().setDataSeries(dataSeries)
						.setOptions(getChartOptions().createOptionsXYDateFloatLogYAxisLegendOutside(series)).show(),
				true);
	}

}

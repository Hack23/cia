/*
 * Copyright 2014 James Pether Sörling
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
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.options.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartOptions;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GovernmentBodyChartDataManager;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class GovernmentBodyChartDataManagerImpl.
 */
@Service
public class GovernmentBodyChartDataManagerImpl extends AbstractChartDataManagerImpl
		implements GovernmentBodyChartDataManager {

	@Autowired
	private EsvApi esvApi;
	
	/** The chart options. */
	@Autowired
	private static ChartOptions chartOptions;


	/**
	 * Instantiates a new government body chart data manager impl.
	 */
	public GovernmentBodyChartDataManagerImpl() {
		super();
	}

	@Override
	public void createGovernmentBodyHeadcountChart(AbstractOrderedLayout content, String name) {
		final Map<Integer, GovernmentBodyAnnualSummary> map = esvApi.getDataPerGovernmentBody(name);

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		series.addSeries(new XYseries().setLabel(name));

		dataSeries.newSeries();

		for (final Entry<Integer, GovernmentBodyAnnualSummary> entry : map.entrySet()) {
			GovernmentBodyAnnualSummary item = entry.getValue();
			if (entry.getKey() != null) {
				if (item != null && item.getHeadCount() > 0) {
					dataSeries.add("01-JAN-" + item.getYear(), item.getHeadCount());
				}
			}
		}

		addChart(content, name + " Annual headcount summary", new DCharts().setDataSeries(dataSeries)
				.setOptions(chartOptions.createOptionsXYDateFloatLegendOutside(series)).show());
	}

	@Override
	public void createMinistryGovernmentBodyHeadcountSummaryChart(AbstractOrderedLayout content, String name) {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getDataPerMinistry(name);
		final List<String> governmentBodyNames = esvApi.getGovernmentBodyNames(name);
		
		final DataSeries dataSeries = new DataSeries();
		final Series series = new Series();

		for (String govBodyName : governmentBodyNames) {
					
			series.addSeries(new XYseries().setLabel(govBodyName));
	
			dataSeries.newSeries();

			for (final Entry<Integer, List<GovernmentBodyAnnualSummary>> entry : map.entrySet()) {
	
				List<GovernmentBodyAnnualSummary> item = entry.getValue();
				Integer totalHeadcount = item.stream().filter(p -> p.getName().equalsIgnoreCase(govBodyName)).collect(Collectors.summingInt(p -> p.getHeadCount()));
	
				if (entry.getKey() != null) {
					if (item != null && totalHeadcount > 0) {
						dataSeries.add("01-JAN-" + entry.getKey(), totalHeadcount);
					}
	
				}
			}
		}

		addChart(content, name + " Annual headcount summary, all government bodies",
				new DCharts().setDataSeries(dataSeries)
						.setOptions(chartOptions.createOptionsXYDateFloatLegendOutside(series)).show());

	}

	@Override
	public void createMinistryGovernmentBodyHeadcountSummaryChart(AbstractOrderedLayout content) {
		final Map<Integer, List<GovernmentBodyAnnualSummary>> map = esvApi.getData();
		List<String> ministryNames = esvApi.getMinistryNames();

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		for (String ministryName : ministryNames) {

			series.addSeries(new XYseries().setLabel(ministryName));

			dataSeries.newSeries();

			for (final Entry<Integer, List<GovernmentBodyAnnualSummary>> entry : map.entrySet()) {

				List<GovernmentBodyAnnualSummary> item = entry.getValue();
				Integer totalHeadcount = item.stream().filter(p -> p.getMinistry().equalsIgnoreCase(ministryName)).collect(Collectors.summingInt(p -> p.getHeadCount()));

				if (entry.getKey() != null) {
					if (item != null && totalHeadcount > 0) {
						dataSeries.add("01-JAN-" + entry.getKey(), totalHeadcount);
					}

				}
			}
		}

		addChart(content, "Annual headcount, all ministries", new DCharts().setDataSeries(dataSeries)
				.setOptions(chartOptions.createOptionsXYDateFloatLegendOutside(series)).show());

	}

}

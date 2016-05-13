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

import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.SeriesToggles;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.LegendLocations;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.LegendRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.legend.EnhancedLegendRenderer;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;

import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartOptions;

/**
 * The Class ChartOptionsImpl.
 */
public final class ChartOptionsImpl implements ChartOptions {

	/** The Constant NUMBER_TICKS_DATE. */
	public static final int NUMBER_TICKS_DATE = 8;

	/** The Constant YEAR_MONTH_DAY_FORMAT. */
	public static final String YEAR_MONTH_DAY_FORMAT = "%Y-%#m-%#d";

	/** The Constant INSTANCE. */
	public static final ChartOptions INSTANCE = new ChartOptionsImpl();

	/**
	 * Instantiates a new chart options impl.
	 */
	private ChartOptionsImpl() {
		super();
	}

	@Override
	public Axes createAxesXYDateFloat() {
		return new Axes()
				.addAxis(new XYaxis().setRenderer(AxisRenderers.DATE)
						.setTickOptions(new AxisTickRenderer().setFormatString(YEAR_MONTH_DAY_FORMAT))
						.setNumberTicks(NUMBER_TICKS_DATE))
				.addAxis(new XYaxis(XYaxes.Y).setTickOptions(new AxisTickRenderer().setFormatString("%.2f")));
	}

	@Override
	public Options createOptionsXYDateFloatLegendOutside(final Series series) {
		return new Options().addOption(new SeriesDefaults()).addOption(createAxesXYDateFloat())
				.addOption(createHighLighterNorth()).addOption(series).addOption(createLegendOutside());
	}

	@Override
	public SeriesDefaults createSeriesDefaultPieChart() {
		return new SeriesDefaults().setRenderer(SeriesRenderers.PIE)
				.setRendererOptions(new PieRenderer().setShowDataLabels(true)).setShadow(true);
	}

	@Override
	public Highlighter createHighLighter() {
		return new Highlighter().setShow(true).setShowTooltip(true).setTooltipAlwaysVisible(true)
				.setKeepTooltipInsideChart(true);
	}

	@Override
	public Highlighter createHighLighterNorth() {
		return new Highlighter().setShow(true).setShowTooltip(true).setTooltipAlwaysVisible(true)
				.setKeepTooltipInsideChart(true).setTooltipLocation(TooltipLocations.NORTH)
				.setTooltipAxes(TooltipAxes.XY_BAR);
	}

	@Override
	public Legend createdLegendEnhancedInsideWest() {
		return new Legend().setShow(true).setPlacement(LegendPlacements.INSIDE_GRID).setLocation(LegendLocations.WEST)
				.setRenderer(LegendRenderers.ENHANCED).setRendererOptions(
						new EnhancedLegendRenderer().setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true));
	}

	@Override
	public Legend createLegendOutside() {
		return new Legend().setShow(true)
				.setRendererOptions(
						new EnhancedLegendRenderer().setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true))
				.setPlacement(LegendPlacements.OUTSIDE_GRID);
	}

}

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
import org.dussan.vaadin.dcharts.metadata.DataLabels;
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
import org.dussan.vaadin.dcharts.options.Cursor;
import org.dussan.vaadin.dcharts.options.Grid;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.legend.EnhancedLegendRenderer;
import org.dussan.vaadin.dcharts.renderers.series.DonutRenderer;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.springframework.stereotype.Component;

import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.ChartOptions;

/**
 * The Class ChartOptionsImpl.
 */
@Component
public final class ChartOptionsImpl implements ChartOptions {

	private static final String LEGEND_FONT_SIZE = "10px";

	private static final String BORDER_COLOR = "#83898c";

	private static final String GRIDLINE_COLOR = "#213f49";

	private static final String BACKGROUND_COLOR = "#13303f";

	private static final String TEXT_COLOR = "#ffffff";

	private static final String FONT_SIZE = "8px";

	private static final String FONT_FAMILY = "Inconsolata";

	/** The Constant NUMBER_TICKS_DATE. */
	public static final int NUMBER_TICKS_DATE = 8;

	/** The Constant YEAR_MONTH_DAY_FORMAT. */
	public static final String YEAR_MONTH_DAY_FORMAT = "%Y-%#m-%#d";

	/**
	 * Instantiates a new chart options impl.
	 */
	private ChartOptionsImpl() {
		super();
	}

	private static Axes createAxesXYDateFloat() {
		return new Axes()
				.addAxis(new XYaxis().setRenderer(AxisRenderers.DATE)
						.setTickOptions(new AxisTickRenderer().setFormatString(YEAR_MONTH_DAY_FORMAT).setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE))
						.setNumberTicks(NUMBER_TICKS_DATE))
				.addAxis(new XYaxis(XYaxes.Y).setTickOptions(new AxisTickRenderer().setFormatString("%.2f").setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE)).setNumberTicks(5));
	}

	private static Grid createDefaultGrid() {
		final Grid grid = new Grid();
		grid.setBackground(BACKGROUND_COLOR);
		grid.setGridLineColor(GRIDLINE_COLOR);
		grid.setBorderColor(BORDER_COLOR);
		return grid;
	}

	private static Legend createdLegendEnhancedInsideNorthWest() {
		return setLegendStyling(new Legend().setShow(true)
				.setRendererOptions(
						new EnhancedLegendRenderer().setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true))
				.setPlacement(LegendPlacements.INSIDE_GRID).setLocation(LegendLocations.NORTH_WEST));
	}

	private static Legend createdLegendEnhancedInsideWest() {
		return setLegendStyling(
				new Legend().setShow(true).setPlacement(LegendPlacements.INSIDE_GRID).setLocation(LegendLocations.WEST)
						.setRenderer(LegendRenderers.ENHANCED).setRendererOptions(new EnhancedLegendRenderer()
								.setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true)));
	}

	private static Highlighter createHighLighter() {
		return new Highlighter().setShow(true).setShowTooltip(true).setTooltipAlwaysVisible(true)
				.setKeepTooltipInsideChart(true);
	}

	private static Highlighter createHighLighterNorth() {
		return new Highlighter().setShow(true).setShowTooltip(true).setTooltipAlwaysVisible(true)
				.setKeepTooltipInsideChart(true).setTooltipLocation(TooltipLocations.NORTH)
				.setTooltipAxes(TooltipAxes.XY_BAR);
	}

	private static Legend createLegendOutside() {
		return setLegendStyling(new Legend().setShow(true)
				.setRendererOptions(
						new EnhancedLegendRenderer().setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true))
				.setPlacement(LegendPlacements.OUTSIDE_GRID));
	}

	private static SeriesDefaults createSeriesDefaultPieChart() {
		return new SeriesDefaults().setRenderer(SeriesRenderers.PIE)
				.setRendererOptions(new PieRenderer().setShowDataLabels(true)).setShadow(true);
	}

	private static Legend setLegendStyling(final Legend legend) {
		legend.setBackground(BACKGROUND_COLOR).setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(LEGEND_FONT_SIZE);
		return legend;
	}

	private Cursor createCursor() {
		final Cursor cursor = new Cursor().setShow(true);
		return cursor;
	}

	@Override
	public Options createOptionsCountryLineChart(final Series series) {
		final Axes axes = new Axes().addAxis(new XYaxis().setRenderer(AxisRenderers.DATE)
				.setTickOptions(new AxisTickRenderer().setFormatString(YEAR_MONTH_DAY_FORMAT).setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE))
				.setNumberTicks(NUMBER_TICKS_DATE)).addAxis(new XYaxis(XYaxes.Y).setTickOptions(new AxisTickRenderer().setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE)).setNumberTicks(5));

		final Options options = new Options().addOption(new SeriesDefaults()).addOption(axes)
				.addOption(createHighLighterNorth()).addOption(series).addOption(createLegendOutside())
				.addOption(createDefaultGrid());
		return options;
	}

	@Override
	public Options createOptionsDonoutChart() {
		final Legend legend = createdLegendEnhancedInsideNorthWest();

		final Highlighter highlighter = new Highlighter().setShow(true).setShowTooltip(true)
				.setTooltipAlwaysVisible(true).setKeepTooltipInsideChart(true);
		
		final SeriesDefaults seriesDefaults = new SeriesDefaults().setRenderer(SeriesRenderers.DONUT)
				.setRendererOptions(new DonutRenderer().setSliceMargin(3).setStartAngle(-90).setShowDataLabels(true)
						.setDataLabels(DataLabels.VALUE));


		final Options options = new Options().setSeriesDefaults(seriesDefaults).setLegend(legend)
				.setHighlighter(highlighter).addOption(createDefaultGrid());
		return options;
	}

	@Override
	public Options createOptionsDonoutChartWithSeries(final Series series) {
		final Legend legend = createdLegendEnhancedInsideNorthWest();

		final Highlighter highlighter = new Highlighter().setShow(true).setShowTooltip(true)
				.setTooltipAlwaysVisible(true).setKeepTooltipInsideChart(true);

		final SeriesDefaults seriesDefaults = new SeriesDefaults().setRenderer(SeriesRenderers.DONUT)
				.setRendererOptions(new DonutRenderer().setSliceMargin(3).setStartAngle(-90).setShowDataLabels(true)
						.setDataLabels(DataLabels.VALUE));

		final Options options = new Options().setSeriesDefaults(seriesDefaults).setLegend(legend)
				.setHighlighter(highlighter).addOption(series).addOption(createDefaultGrid());
		return options;
	}

	@Override
	public Options createOptionsPartyLineChart(final Series series) {
		final Options options = new Options().addOption(new SeriesDefaults()).addOption(createAxesXYDateFloat())
				.addOption(createHighLighterNorth()).addOption(createCursor()).addOption(series)
				.addOption(createLegendOutside()).addOption(createDefaultGrid());
		return options;
	}

	@Override
	public Options createOptionsPersonLineChart(final Series series) {
		final Options options = new Options().addOption(new SeriesDefaults()).addOption(createAxesXYDateFloat())
				.addOption(createHighLighterNorth()).addOption(createCursor()).addOption(series)
				.addOption(createLegendOutside()).addOption(createDefaultGrid());
		return options;
	}

	@Override
	public Options createOptionsPieChart() {
		final Options options = new Options().setSeriesDefaults(createSeriesDefaultPieChart())
				.setLegend(createdLegendEnhancedInsideWest()).setHighlighter(createHighLighter())
				.addOption(createDefaultGrid());
		return options;
	}

	@Override
	public Options createOptionsXYDateFloatLegendOutside(final Series series) {
		return new Options().addOption(new SeriesDefaults()).addOption(createAxesXYDateFloat())
				.addOption(createHighLighterNorth()).addOption(series).addOption(createLegendOutside())
				.addOption(createDefaultGrid());
	}

}

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

	/** The Constant BACKGROUND_COLOR. */
	private static final String BACKGROUND_COLOR = "#13303f";

	/** The Constant BORDER_COLOR. */
	private static final String BORDER_COLOR = "#83898c";

	/** The Constant FLOAT_FORMAT. */
	private static final String FLOAT_FORMAT = "%.2f";

	/** The Constant FONT_FAMILY. */
	private static final String FONT_FAMILY = "Arial";

	/** The Constant FONT_SIZE. */
	private static final String FONT_SIZE = "8px";

	/** The Constant GRIDLINE_COLOR. */
	private static final String GRIDLINE_COLOR = "#213f49";

	/** The Constant LEGEND_COLUMNS. */
	private static final int LEGEND_COLUMNS = 3;

	/** The Constant LEGEND_FONT_SIZE. */
	private static final String LEGEND_FONT_SIZE = "10px";

	/** The Constant LEGEND_ROWS. */
	private static final int LEGEND_ROWS = 20;

	/** The Constant NUMBER_TICKS. */
	private static final int NUMBER_TICKS = 5;

	/** The Constant NUMBER_TICKS_DATE. */
	public static final int NUMBER_TICKS_DATE = 8;

	/** The Constant ONE_COLUMN_NUMBER_OF_COLUMNS. */
	private static final int ONE_COLUMN_NUMBER_OF_COLUMNS = 1;

	/** The Constant ONE_COLUMN_NUMBER_OF_ROWS. */
	private static final int ONE_COLUMN_NUMBER_OF_ROWS = 12;

	/** The Constant ONE_ROW_NUMBER_OF_COLUMNS. */
	private static final int ONE_ROW_NUMBER_OF_COLUMNS = 10;

	/** The Constant ONE_ROW_NUMBER_OF_ROWS. */
	private static final int ONE_ROW_NUMBER_OF_ROWS = 1;

	/** The Constant SLICE_MARGIN. */
	private static final int SLICE_MARGIN = 3;

	/** The Constant START_ANGLE. */
	private static final int START_ANGLE = -90;

	/** The Constant TEXT_COLOR. */
	private static final String TEXT_COLOR = "#ffffff";

	/** The Constant YEAR_MONTH_DAY_FORMAT. */
	public static final String YEAR_MONTH_DAY_FORMAT = "%F";

	/**
	 * Instantiates a new chart options impl.
	 */
	private ChartOptionsImpl() {
		super();
	}

	/**
	 * Creates the axes XY date float.
	 *
	 * @return the axes
	 */
	private static Axes createAxesXYDateFloat() {
		return new Axes()
				.addAxis(new XYaxis().setRenderer(AxisRenderers.DATE)
						.setTickOptions(new AxisTickRenderer().setFormatString(YEAR_MONTH_DAY_FORMAT).setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE))
						.setNumberTicks(NUMBER_TICKS_DATE))
				.addAxis(new XYaxis(XYaxes.Y).setRenderer(AxisRenderers.LINEAR).setTickOptions(new AxisTickRenderer().setFormatString(FLOAT_FORMAT).setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE)).setNumberTicks(NUMBER_TICKS));
	}

	/**
	 * Creates the axes XY date float log.
	 *
	 * @return the axes
	 */
	private static Axes createAxesXYDateFloatLog() {
		return new Axes()
				.addAxis(new XYaxis().setRenderer(AxisRenderers.DATE)
						.setTickOptions(new AxisTickRenderer().setFormatString(YEAR_MONTH_DAY_FORMAT).setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE))
						.setNumberTicks(NUMBER_TICKS_DATE))
				.addAxis(new XYaxis(XYaxes.Y).setRenderer(AxisRenderers.LOG).setTickOptions(new AxisTickRenderer().setFormatString(FLOAT_FORMAT).setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE)).setNumberTicks(NUMBER_TICKS));
	}


	/**
	 * Creates the cursor.
	 *
	 * @return the cursor
	 */
	private static Cursor createCursor() {
		return new Cursor().setZoom(true).setLooseZoom(true).setShow(true);
	}

	/**
	 * Creates the default grid.
	 *
	 * @return the grid
	 */
	private static Grid createDefaultGrid() {
		final Grid grid = new Grid();
		grid.setBackground(BACKGROUND_COLOR);
		grid.setGridLineColor(GRIDLINE_COLOR);
		grid.setBorderColor(BORDER_COLOR);
		return grid;
	}

	/**
	 * Created legend enhanced inside north west.
	 *
	 * @return the legend
	 */
	private static Legend createdLegendEnhancedInsideNorthWest() {
		return setLegendStyling(new Legend().setShow(true)
				.setRendererOptions(
						new EnhancedLegendRenderer().setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true).setNumberColumns(LEGEND_COLUMNS).setNumberRows(LEGEND_ROWS))
				.setPlacement(LegendPlacements.INSIDE_GRID).setLocation(LegendLocations.NORTH_WEST));
	}

	/**
	 * Created legend enhanced inside west.
	 *
	 * @return the legend
	 */
	private static Legend createdLegendEnhancedInsideWest() {
		return setLegendStyling(
				new Legend().setShow(true).setPlacement(LegendPlacements.INSIDE_GRID).setLocation(LegendLocations.WEST)
						.setRenderer(LegendRenderers.ENHANCED).setRendererOptions(new EnhancedLegendRenderer()
								.setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true).setNumberColumns(LEGEND_COLUMNS).setNumberRows(LEGEND_ROWS)));
	}

	/**
	 * Creates the donout series default.
	 *
	 * @return the series defaults
	 */
	private static SeriesDefaults createDonoutSeriesDefault() {
		return new SeriesDefaults().setRenderer(SeriesRenderers.DONUT)
				.setRendererOptions(new DonutRenderer().setSliceMargin(SLICE_MARGIN).setStartAngle(START_ANGLE).setShowDataLabels(true)
						.setDataLabels(DataLabels.VALUE));
	}

	/**
	 * Creates the high lighter.
	 *
	 * @return the highlighter
	 */
	private static Highlighter createHighLighter() {
		return new Highlighter().setShow(true).setShowTooltip(true).setTooltipAlwaysVisible(true)
				.setKeepTooltipInsideChart(true);
	}

	/**
	 * Creates the high lighter north.
	 *
	 * @return the highlighter
	 */
	private static Highlighter createHighLighterNorth() {
		return new Highlighter().setShow(true).setShowTooltip(true).setTooltipAlwaysVisible(true)
				.setKeepTooltipInsideChart(true).setTooltipLocation(TooltipLocations.NORTH)
				.setTooltipAxes(TooltipAxes.XY_BAR).setShowMarker(true).setBringSeriesToFront(true);
	}

	/**
	 * Creates the legend outside.
	 *
	 * @return the legend
	 */
	private static Legend createLegendInsideOneRow() {
		return setLegendStyling(new Legend().setShow(true)
				.setRendererOptions(
						new EnhancedLegendRenderer().setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true).setNumberColumns(ONE_ROW_NUMBER_OF_COLUMNS).setNumberRows(ONE_ROW_NUMBER_OF_ROWS))
				.setPlacement(LegendPlacements.INSIDE_GRID));
	}

	/**
	 * Creates the legend outside.
	 *
	 * @return the legend
	 */
	private static Legend createLegendOutside() {
		return setLegendStyling(new Legend().setShow(true)
				.setRendererOptions(
						new EnhancedLegendRenderer().setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true).setNumberColumns(LEGEND_COLUMNS).setNumberRows(LEGEND_ROWS))
				.setPlacement(LegendPlacements.OUTSIDE_GRID));
	}

	/**
	 * Creates the legend outside one column.
	 *
	 * @return the legend
	 */
	private static Legend createLegendOutsideOneColumn() {
		return setLegendStyling(new Legend().setShow(true)
				.setRendererOptions(
						new EnhancedLegendRenderer().setSeriesToggle(SeriesToggles.NORMAL).setSeriesToggleReplot(true).setNumberColumns(ONE_COLUMN_NUMBER_OF_COLUMNS).setNumberRows(ONE_COLUMN_NUMBER_OF_ROWS))
				.setPlacement(LegendPlacements.OUTSIDE_GRID));
	}


	/**
	 * Creates the series default pie chart.
	 *
	 * @return the series defaults
	 */
	private static SeriesDefaults createSeriesDefaultPieChart() {
		return new SeriesDefaults().setRenderer(SeriesRenderers.PIE)
				.setRendererOptions(new PieRenderer().setShowDataLabels(true)).setShadow(true);
	}

	/**
	 * Sets the legend styling.
	 *
	 * @param legend
	 *            the legend
	 * @return the legend
	 */
	private static Legend setLegendStyling(final Legend legend) {
		legend.setBackground(BACKGROUND_COLOR).setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(LEGEND_FONT_SIZE);
		return legend;
	}

	/**
	 * Creates the options country line chart.
	 *
	 * @param chartSeries the chart series
	 * @return the options
	 */
	@Override
	public Options createOptionsCountryLineChart(final Series chartSeries) {
		final Axes chartAxes = new Axes().addAxis(new XYaxis().setRenderer(AxisRenderers.DATE)
				.setTickOptions(new AxisTickRenderer().setFormatString(YEAR_MONTH_DAY_FORMAT).setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE))
				.setNumberTicks(NUMBER_TICKS_DATE)).addAxis(new XYaxis(XYaxes.Y).setTickOptions(new AxisTickRenderer().setFontFamily(FONT_FAMILY).setTextColor(TEXT_COLOR).setFontSize(FONT_SIZE)).setNumberTicks(NUMBER_TICKS));

		return new Options().addOption(new SeriesDefaults()).addOption(chartAxes)
				.addOption(createHighLighterNorth()).addOption(chartSeries).addOption(createLegendInsideOneRow())
				.addOption(createDefaultGrid()).addOption(createCursor());
	}

	/**
	 * Creates the options donout chart.
	 *
	 * @return the options
	 */
	@Override
	public Options createOptionsDonoutChart() {
		return new Options().setSeriesDefaults(createDonoutSeriesDefault()).setLegend(createdLegendEnhancedInsideNorthWest())
				.setHighlighter(createHighLighter()).addOption(createDefaultGrid()).addOption(createCursor());
	}

	/**
	 * Creates the options donout chart with series.
	 *
	 * @param chartSeries the chart series
	 * @return the options
	 */
	@Override
	public Options createOptionsDonoutChartWithSeries(final Series chartSeries) {
		return new Options().setSeriesDefaults(createDonoutSeriesDefault()).setLegend(createdLegendEnhancedInsideNorthWest())
				.setHighlighter(createHighLighter()).addOption(chartSeries).addOption(createDefaultGrid()).addOption(createCursor());
	}

	/**
	 * Creates the options party line chart.
	 *
	 * @param chartSeries the chart series
	 * @return the options
	 */
	@Override
	public Options createOptionsPartyLineChart(final Series chartSeries) {
		return new Options().addOption(new SeriesDefaults()).addOption(createAxesXYDateFloat())
				.addOption(createHighLighterNorth()).addOption(createCursor()).addOption(chartSeries)
				.addOption(createLegendOutside()).addOption(createDefaultGrid());
	}

	/**
	 * Creates the options person line chart.
	 *
	 * @param chartSeries the chart series
	 * @return the options
	 */
	@Override
	public Options createOptionsPersonLineChart(final Series chartSeries) {
		return new Options().addOption(new SeriesDefaults()).addOption(createAxesXYDateFloat())
				.addOption(createHighLighterNorth()).addOption(createCursor()).addOption(chartSeries)
				.addOption(createLegendOutside()).addOption(createDefaultGrid());
	}

	/**
	 * Creates the options pie chart.
	 *
	 * @return the options
	 */
	@Override
	public Options createOptionsPieChart() {
		return new Options().setSeriesDefaults(createSeriesDefaultPieChart())
				.setLegend(createdLegendEnhancedInsideWest()).setHighlighter(createHighLighter())
				.addOption(createDefaultGrid()).addOption(createCursor());
	}

	/**
	 * Creates the options XY date float legend inside one column.
	 *
	 * @param chartSeries the chart series
	 * @return the options
	 */
	@Override
	public Options createOptionsXYDateFloatLegendInsideOneColumn(final Series chartSeries) {
		return new Options().addOption(new SeriesDefaults()).addOption(createAxesXYDateFloat())
				.addOption(createHighLighterNorth()).addOption(chartSeries).addOption(createLegendOutsideOneColumn())
				.addOption(createDefaultGrid()).addOption(createCursor());
	}

	/**
	 * Creates the options XY date float log Y axis legend outside.
	 *
	 * @param chartSeries the chart series
	 * @return the options
	 */
	@Override
	public Options createOptionsXYDateFloatLogYAxisLegendOutside(final Series chartSeries) {
		return new Options().addOption(new SeriesDefaults()).addOption(chartSeries).addOption(createAxesXYDateFloatLog())
				.addOption(createHighLighterNorth()).addOption(createLegendOutside())
				.addOption(createDefaultGrid()).addOption(createCursor());
	}

}

package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import org.dussan.vaadin.dcharts.DCharts;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

public final class ChartUtils {

    private static final int CHART_BOTTOM_MARGIN_SIZE = 2;
    private static final int CHART_LEFT_MARGIN = 2;
    private static final int CHART_RIGHT_MARGIN = 2;
    private static final int CHART_TOP_MARGIN_SIZE = 2;
    private static final int CHART_WIDTH_REDUCTION = 50;
    private static final double HEIGHT_PERCENTAGE_FULL_PAGE = 0.8;
    private static final double HEIGHT_PERCETAGE_HALF_PAGE = 0.5;
    private static final int MINIMUM_CHART_HEIGHT_FULL_PAGE = 400;
    private static final int MINIMUM_CHART_WIDTH = 600;
    private static final int NINIMUM_CHART_HEIGHT_HALF_PAGE = 200;

    private ChartUtils() {
        // Utility class
    }

    public static int getChartWindowHeight(final boolean isFullPage) {
        if (isFullPage) {
            return Math.max((int) (Page.getCurrent().getBrowserWindowHeight() * HEIGHT_PERCENTAGE_FULL_PAGE), MINIMUM_CHART_HEIGHT_FULL_PAGE);
        } else {
            return Math.max((int) (Page.getCurrent().getBrowserWindowHeight() * HEIGHT_PERCETAGE_HALF_PAGE), NINIMUM_CHART_HEIGHT_HALF_PAGE);
        }
    }

    public static int getChartWindowWidth() {
        return Math.max(Page.getCurrent().getBrowserWindowWidth() - CHART_WIDTH_REDUCTION, MINIMUM_CHART_WIDTH);
    }

    public static void addChart(final AbstractOrderedLayout layout, final String caption, final DCharts chart, final boolean isFullPage) {
        final HorizontalLayout horizontalLayout = new HorizontalLayout();

        final int browserWindowWidth = getChartWindowWidth();
        final int browserWindowHeight = getChartWindowHeight(isFullPage);

        horizontalLayout.setWidth(browserWindowWidth, Unit.PIXELS);
        horizontalLayout.setHeight(browserWindowHeight, Unit.PIXELS);
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(false);
        horizontalLayout.addStyleName("v-layout-content-overview-panel-level1");

        final Panel formPanel = new Panel();
        formPanel.setSizeFull();
        formPanel.setContent(horizontalLayout);
        formPanel.setCaption(caption);

        layout.addComponent(formPanel);
        layout.setExpandRatio(formPanel, ContentRatio.LARGE);

        chart.setWidth(100, Unit.PERCENTAGE);
        chart.setHeight(100, Unit.PERCENTAGE);
        chart.setMarginRight(CHART_RIGHT_MARGIN);
        chart.setMarginLeft(CHART_LEFT_MARGIN);
        chart.setMarginBottom(CHART_BOTTOM_MARGIN_SIZE);
        chart.setMarginTop(CHART_TOP_MARGIN_SIZE);

        horizontalLayout.addComponent(chart);
        chart.setCaption(caption);
    }
}

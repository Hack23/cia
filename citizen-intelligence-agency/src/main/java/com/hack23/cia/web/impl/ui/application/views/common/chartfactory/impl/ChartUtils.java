package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import org.dussan.vaadin.dcharts.DCharts;

import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import java.util.Optional;

/**
 * The Class ChartUtils.
 */
public final class ChartUtils {

    /** The Constant CHART_BOTTOM_MARGIN_SIZE. */
    private static final int CHART_BOTTOM_MARGIN_SIZE = 2;

    /** The Constant CHART_LEFT_MARGIN. */
    private static final int CHART_LEFT_MARGIN = 2;

    /** The Constant CHART_RIGHT_MARGIN = 2; */
    private static final int CHART_RIGHT_MARGIN = 2;

    /** The Constant CHART_TOP_MARGIN_SIZE. */
    private static final int CHART_TOP_MARGIN_SIZE = 2;

    /** The Constant CHART_WIDTH_REDUCTION. */
    private static final int CHART_WIDTH_REDUCTION = 50;

    /** The Constant HEIGHT_PERCENTAGE_FULL_PAGE. */
    private static final double HEIGHT_PERCENTAGE_FULL_PAGE = 0.8;

    /** The Constant HEIGHT_PERCETAGE_HALF_PAGE. */
    private static final double HEIGHT_PERCETAGE_HALF_PAGE = 0.5;

    /** The Constant MINIMUM_CHART_HEIGHT_FULL_PAGE. */
    private static final int MINIMUM_CHART_HEIGHT_FULL_PAGE = 400;

    /** The Constant MINIMUM_CHART_WIDTH. */
    private static final int MINIMUM_CHART_WIDTH = 600;

    /** The Constant NINIMUM_CHART_HEIGHT_HALF_PAGE. */
    private static final int NINIMUM_CHART_HEIGHT_HALF_PAGE = 200;

    /**
     * Instantiates a new chart utils.
     */
    private ChartUtils() {
        // Utility class
    }

    /**
     * Gets the chart window height.
     *
     * @param isFullPage the is full page
     * @return the chart window height
     */
    public static int getChartWindowHeight(final boolean isFullPage) {
        if (isFullPage) {
            return Math.max((int) (Page.getCurrent().getBrowserWindowHeight() * HEIGHT_PERCENTAGE_FULL_PAGE), MINIMUM_CHART_HEIGHT_FULL_PAGE);
        } else {
            return Math.max((int) (Page.getCurrent().getBrowserWindowHeight() * HEIGHT_PERCETAGE_HALF_PAGE), NINIMUM_CHART_HEIGHT_HALF_PAGE);
        }
    }

    /**
     * Gets the chart window width.
     *
     * @return the chart window width
     */
    public static int getChartWindowWidth() {
        return Math.max(Page.getCurrent().getBrowserWindowWidth() - CHART_WIDTH_REDUCTION, MINIMUM_CHART_WIDTH);
    }

    /**
     * Adds the chart.
     *
     * @param layout the layout
     * @param caption the caption
     * @param chart the chart
     * @param isFullPage the is full page
     */
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

    /**
     * Gets the party name.
     *
     * @param applicationManager the application manager
     * @param partySummary the party summary
     * @return the party name
     */
    public static String getPartyName(final ApplicationManager applicationManager, final String partySummary) {
        final DataContainer<ViewRiksdagenParty, String> partyDataContainer = applicationManager.getDataContainer(ViewRiksdagenParty.class);
        final ViewRiksdagenParty party = partyDataContainer.load(partySummary);
        return Optional.ofNullable(party).map(ViewRiksdagenParty::getPartyName).orElse(null);
    }

    /**
     * Gets the role color.
     *
     * @param roleCode the role code
     * @param role1 the role 1
     * @param role2 the role 2
     * @return the role color
     */
    public static String getRoleColor(final String roleCode, final String role1, final String role2) {
        if (roleCode.equalsIgnoreCase(role1)) {
            return "red";
        } else if (roleCode.equalsIgnoreCase(role2)) {
            return "blue";
        } else {
            return "green";
        }
    }
}

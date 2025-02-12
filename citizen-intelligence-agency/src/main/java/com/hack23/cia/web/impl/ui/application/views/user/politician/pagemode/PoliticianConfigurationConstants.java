package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

/**
 * Configuration constants for politician view components.
 */
public interface PoliticianConfigurationConstants {

    /** The metrics view mode. */
    // Analytics Configuration
    String METRICS_VIEW_MODE = "performance-metrics";

    /** The distribution view mode. */
    String DISTRIBUTION_VIEW_MODE = "distribution-analytics";

    /** The temporal view mode. */
    String TEMPORAL_VIEW_MODE = "temporal-analysis";

    /** The default page size. */
    // Grid Analytics Configuration
    int DEFAULT_PAGE_SIZE = 10;

    /** The grid multiselect. */
    boolean GRID_MULTISELECT = false;

    /** The grid analytics mode. */
    String GRID_ANALYTICS_MODE = "metrics-analysis";

    /** The default image width. */
    // Visualization Configuration
    int DEFAULT_IMAGE_WIDTH = 100;

    /** The image protocol. */
    String IMAGE_PROTOCOL = "https://";

    /** The default expand ratio. */
    float DEFAULT_EXPAND_RATIO = 1.0f;

    /** The default spacing. */
    int DEFAULT_SPACING = 10;

    /** The performance context. */
    // Analysis Context Labels
    String PERFORMANCE_CONTEXT = "Effectiveness Metrics";

    /** The distribution context. */
    String DISTRIBUTION_CONTEXT = "Pattern Analysis";

    /** The temporal context. */
    String TEMPORAL_CONTEXT = "Time-series Analytics";
}

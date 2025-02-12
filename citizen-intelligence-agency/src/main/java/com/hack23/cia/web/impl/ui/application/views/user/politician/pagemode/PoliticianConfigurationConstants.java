package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

/**
 * Configuration constants for politician view components.
 */
public interface PoliticianConfigurationConstants {
    // Analytics Configuration
    String METRICS_VIEW_MODE = "performance-metrics";
    String DISTRIBUTION_VIEW_MODE = "distribution-analytics";
    String TEMPORAL_VIEW_MODE = "temporal-analysis";
    
    // Grid Analytics Configuration
    int DEFAULT_PAGE_SIZE = 10;
    boolean GRID_MULTISELECT = false;
    String GRID_ANALYTICS_MODE = "metrics-analysis";
    
    // Visualization Configuration
    int DEFAULT_IMAGE_WIDTH = 100;
    String IMAGE_PROTOCOL = "https://";
    float DEFAULT_EXPAND_RATIO = 1.0f;
    int DEFAULT_SPACING = 10;
    
    // Analysis Context Labels
    String PERFORMANCE_CONTEXT = "Effectiveness Metrics";
    String DISTRIBUTION_CONTEXT = "Pattern Analysis";
    String TEMPORAL_CONTEXT = "Time-series Analytics";
}

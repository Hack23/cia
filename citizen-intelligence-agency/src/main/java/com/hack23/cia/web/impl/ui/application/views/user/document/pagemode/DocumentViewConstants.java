package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

/**
 * Constants for the document view pages.
 */
public interface DocumentViewConstants extends
    DocumentFieldConstants,
    DocumentPageTitleConstants {

    /**  Additional analytical context. */
    String DOCUMENT_ANALYSIS_CONTEXT = "Statistical analysis of document metrics and patterns";

    /** The temporal analysis context. */
    String TEMPORAL_ANALYSIS_CONTEXT = "Longitudinal analysis of document processing patterns";

    /** The distribution analysis context. */
    String DISTRIBUTION_ANALYSIS_CONTEXT = "Quantitative assessment of document distribution metrics";
}

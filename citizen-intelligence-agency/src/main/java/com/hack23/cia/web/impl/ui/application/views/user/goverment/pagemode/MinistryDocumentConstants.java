package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

/**
 * The Interface MinistryDocumentConstants.
 */
public interface MinistryDocumentConstants {

    /** The documents. */
    String DOCUMENTS = "Document Analytics";

    /** The doc id. */
    String DOC_ID = "docId";

    /** The column order. */
    String[] COLUMN_ORDER = { "id", "docId", "personReferenceId", "roleDescription",
            "org", "label", "rm", "madePublicDate", "numberValue", "title", "subTitle",
            "tempLabel", "orderNumber", "documentType", "subType", "status",
            "partyShortCode", "referenceName" };

    /** The hide columns. */
    String[] HIDE_COLUMNS = { "id", "numberValue", "orderNumber", "tempLabel",
            "personReferenceId", "org", "roleDescription", "label", "subTitle", "docId" };

    // Add analytical descriptions
    String DOCUMENT_METRICS = "Statistical Analysis of Document Distribution";
    String TEMPORAL_ANALYSIS = "Temporal Document Pattern Analysis";
    String CLASSIFICATION_METRICS = "Document Classification Analytics";
}

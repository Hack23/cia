package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

/**
 * The Interface DocumentGridConstants.
 */
public interface DocumentGridConstants {

    /** The grid title. */
    String GRID_TITLE = "Document";

    /** The grid column rm. */
    String GRID_COLUMN_RM = "rm";

    /** The grid column created date. */
    String GRID_COLUMN_CREATED_DATE = "createdDate";

    /** The grid column document name. */
    String GRID_COLUMN_DOCUMENT_NAME = "documentName";

    /** The grid column sub type. */
    String GRID_COLUMN_SUB_TYPE = "subType";

    /** The grid column title. */
    String GRID_COLUMN_TITLE = "title";

    /** The grid column sub title. */
    String GRID_COLUMN_SUB_TITLE = "subTitle";

    /** The grid column status. */
    String GRID_COLUMN_STATUS = "status";

    /** The grid hidden columns. */
    String[] GRID_HIDDEN_COLUMNS = { "rm", "lang", "noteTitle", "origin", "subType", "note", "subTitle", "status", "label",
            "id", "hit", "madePublicDate", "databaseSource", "domainOrg", "relatedId", "org",
            "documentType", "docType", "debateName", "tempLabel", "numberValue", "systemDate", "kallId",
            "documentFormat", "documentUrlText", "documentUrlHtml", "documentStatusUrlXml",
            "committeeReportUrlXml" };
}

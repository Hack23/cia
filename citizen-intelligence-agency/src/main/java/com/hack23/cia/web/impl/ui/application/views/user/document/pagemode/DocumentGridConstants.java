package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

public interface DocumentGridConstants {
    String GRID_TITLE = "Document";
    String GRID_COLUMN_RM = "rm";
    String GRID_COLUMN_CREATED_DATE = "createdDate";
    String GRID_COLUMN_DOCUMENT_NAME = "documentName";
    String GRID_COLUMN_SUB_TYPE = "subType";
    String GRID_COLUMN_TITLE = "title";
    String GRID_COLUMN_SUB_TITLE = "subTitle";
    String GRID_COLUMN_STATUS = "status";

    String[] GRID_HIDDEN_COLUMNS = { "rm", "lang", "noteTitle", "origin", "subType", "note", "subTitle", "status", "label",
            "id", "hit", "madePublicDate", "databaseSource", "domainOrg", "relatedId", "org",
            "documentType", "docType", "debateName", "tempLabel", "numberValue", "systemDate", "kallId",
            "documentFormat", "documentUrlText", "documentUrlHtml", "documentStatusUrlXml",
            "committeeReportUrlXml" };
}

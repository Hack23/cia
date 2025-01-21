package com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode;

/**
 * The Interface ParliamentRiskConstants.
 */
public interface ParliamentRiskConstants {

    /** The risk grid name. */
    String RISK_GRID_NAME = "Risk";

    /** The risk column order. */
    String[] RISK_COLUMN_ORDER = { "name", "status", "resourceType", "ruleName", "ruleGroup", "ruleDescription", "positive" };

    /** The risk hidden columns. */
    String[] RISK_HIDDEN_COLUMNS = { "referenceId" };

    /** The compliance grid name. */
    String COMPLIANCE_GRID_NAME = "Risk";

    /** The compliance column order. */
    String[] COMPLIANCE_COLUMN_ORDER = { "name", "resourceType", "numberRuleViolations", "ruleSummary" };

    /** The compliance hidden columns. */
    String[] COMPLIANCE_HIDDEN_COLUMNS = { "id", "ruleViolations" };
}

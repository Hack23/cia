package com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode;

public interface ParliamentRiskConstants {
    String RISK_GRID_NAME = "Risk";
    String[] RISK_COLUMN_ORDER = { "name", "status", "resourceType", "ruleName", "ruleGroup", "ruleDescription", "positive" };
    String[] RISK_HIDDEN_COLUMNS = { "referenceId" };

    String COMPLIANCE_GRID_NAME = "Risk";
    String[] COMPLIANCE_COLUMN_ORDER = { "name", "resourceType", "numberRuleViolations", "ruleSummary" };
    String[] COMPLIANCE_HIDDEN_COLUMNS = { "id", "ruleViolations" };
}

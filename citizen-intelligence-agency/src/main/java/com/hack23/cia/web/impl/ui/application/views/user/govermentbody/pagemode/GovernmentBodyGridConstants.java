package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

/**
 * The Interface GovernmentBodyGridConstants.
 */
public interface GovernmentBodyGridConstants {

    /** The government bodies. */
    String GOVERNMENT_BODIES = "Government bodies";

    /** The column order. */
    String[] COLUMN_ORDER = {
        "name", "ministry", "orgNumber",
        "headCount", "annualWorkHeadCount"
    };

    /** The hide columns. */
    String[] HIDE_COLUMNS = {
        "vat", "consecutiveNumber", "comment",
        "mCode", "govermentBodyId"
    };
}

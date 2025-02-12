package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

/**
 * The Interface MinistryMemberConstants.
 */
public interface MinistryMemberConstants {

    /** The member column order. */
    // Grid Configuration
    String[] MEMBER_COLUMN_ORDER = { "roleCode", "roleId", "personId", "firstName",
            "lastName", "party", "active", "totalDaysServed", "detail", "fromDate", "toDate" };

    /** The member hide columns. */
    String[] MEMBER_HIDE_COLUMNS = { "roleId", "personId", "detail" };

    /** The member history. */
    // Grid Names with analytical focus
    String MEMBER_HISTORY = "Membership Pattern Analysis";
    String CURRENT_MEMBERS = "Active Membership Distribution";
    String TEMPORAL_METRICS = "Temporal Distribution Analysis";
    String POSITION_ANALYTICS = "Position Distribution Metrics";

    /** The person id. */
    // Properties
    String PERSON_ID = "personId";

    /** The role id. */
    String ROLE_ID = "roleId";
}

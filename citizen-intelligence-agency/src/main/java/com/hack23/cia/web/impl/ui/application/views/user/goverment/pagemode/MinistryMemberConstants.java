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
    // Grid Names
    String MEMBER_HISTORY = "Member History";

    /** The current members. */
    String CURRENT_MEMBERS = "Current Members";

    /** The person id. */
    // Properties
    String PERSON_ID = "personId";

    /** The role id. */
    String ROLE_ID = "roleId";
}

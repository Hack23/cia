package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

public interface MinistryMemberConstants {
    // Grid Configuration
    String[] MEMBER_COLUMN_ORDER = { "roleCode", "roleId", "personId", "firstName",
            "lastName", "party", "active", "totalDaysServed", "detail", "fromDate", "toDate" };
            
    String[] MEMBER_HIDE_COLUMNS = { "roleId", "personId", "detail" };
    
    // Grid Names
    String MEMBER_HISTORY = "Member History";
    String CURRENT_MEMBERS = "Current Members";
    
    // Properties
    String PERSON_ID = "personId";
    String ROLE_ID = "roleId";
}

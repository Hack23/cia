package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

public interface GovernmentBodyGridConstants {
    String GOVERNMENT_BODIES = "Government bodies";
    
    String[] COLUMN_ORDER = { 
        "name", "ministry", "orgNumber",
        "headCount", "annualWorkHeadCount" 
    };
    
    String[] HIDE_COLUMNS = { 
        "vat", "consecutiveNumber", "comment", 
        "mCode", "govermentBodyId" 
    };
}

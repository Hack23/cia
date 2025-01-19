package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

/**
 * Constants for the government body view pages.
 */
public interface GovernmentBodyViewConstants extends
    GovernmentBodyDescriptionConstants,
    GovernmentBodyHeaderConstants,
    GovernmentBodyGridConstants,
    GovernmentBodyLayoutConstants,
    GovernmentBodySectionConstants,
    GovernmentBodyIconConstants {

    // View Names
    String GOVERNMENT_BODY_VIEW = "Government Body View";
    String GOVERNMENT_BODY_RANKING_VIEW = "Government Body Rankings";
    
    // Grid Properties
    String ORG_NUMBER_PROPERTY = "orgNumber";
    String GOVERNMENT_BODIES = "Government bodies";
    int DEFAULT_YEAR = 2024;
    
    // View Actions
    String VISIT_GOVERNMENT_BODY = "VISIT_GOVERNMENT_BODY";
    String VISIT_GOVERNMENT_RANKING = "VISIT_GOVERNMENT_RANKING";
}

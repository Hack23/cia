package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;

public class PoliticianTitleFormatter {
    public static String formatTitle(ViewRiksdagenPolitician politician) {
        return politician.getFirstName() + ' ' + 
               politician.getLastName() + '(' + 
               politician.getParty() + ')';
    }
}

package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;

/**
 * The Class PoliticianTitleFormatter.
 */
public class PoliticianTitleFormatter {

    /**
     * Format title.
     *
     * @param politician the politician
     * @return the string
     */
    public static String formatTitle(final ViewRiksdagenPolitician politician) {
        return politician.getFirstName() + ' ' +
               politician.getLastName() + '(' +
               politician.getParty() + ')';
    }
}

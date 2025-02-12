package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import java.util.Locale;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;

/**
 * The Class PoliticianPageTitleFormatter.
 */
public class PoliticianPageTitleFormatter {

    /**
     * Format title.
     *
     * @param politician the politician
     * @param pageTitle the page title
     * @return the string
     */
    public static String formatTitle(final ViewRiksdagenPolitician politician, final String pageTitle) {
        return String.format(Locale.ENGLISH, PoliticianPageTitleConstants.PAGE_TITLE_FORMAT,
            politician.getFirstName(),
            politician.getLastName(),
            politician.getParty()) + " " + pageTitle;
    }
}

package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import java.util.Locale;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;

public class PoliticianPageTitleFormatter {
    public static String formatTitle(ViewRiksdagenPolitician politician, String pageTitle) {
        return String.format(Locale.ENGLISH, PoliticianPageTitleConstants.PAGE_TITLE_FORMAT,
            politician.getFirstName(),
            politician.getLastName(),
            politician.getParty()) + " " + pageTitle;
    }
}

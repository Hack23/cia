package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

import java.util.Locale;

import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;

/**
 * The Class GovernmentBodyPageTitleFormatter.
 */
public class GovernmentBodyPageTitleFormatter {

    /** The Constant TITLE_FORMAT. */
    private static final String TITLE_FORMAT = "%s %s";

    /**
     * Format title.
     *
     * @param govBody the gov body
     * @param pageTitle the page title
     * @return the string
     */
    public static String formatTitle(GovernmentBodyAnnualSummary govBody, String pageTitle) {
        if (govBody == null) {
            return pageTitle;
        }
        return String.format(Locale.ENGLISH, TITLE_FORMAT, pageTitle, govBody.getName());
    }
}

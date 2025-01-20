package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

import java.util.Locale;

import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;

/**
 * The Class GovernmentBodyTitleFormatter.
 */
public class GovernmentBodyTitleFormatter {

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
        return String.format(Locale.ENGLISH, "%s %s", pageTitle, govBody.getName());
    }
}

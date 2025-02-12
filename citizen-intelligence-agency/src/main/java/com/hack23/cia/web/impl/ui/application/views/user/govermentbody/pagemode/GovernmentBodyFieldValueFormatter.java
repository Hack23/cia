package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

import java.util.Locale;

/**
 * The Class GovernmentBodyFieldValueFormatter.
 */
public class GovernmentBodyFieldValueFormatter {

    /**
     * Format year.
     *
     * @param year the year
     * @return the string
     */
    public static String formatYear(final int year) {
        return String.format(Locale.ENGLISH, "%d", year);
    }

    /**
     * Format count.
     *
     * @param count the count
     * @return the string
     */
    public static String formatCount(final int count) {
        return String.format(Locale.ENGLISH, "%d", count);
    }
}

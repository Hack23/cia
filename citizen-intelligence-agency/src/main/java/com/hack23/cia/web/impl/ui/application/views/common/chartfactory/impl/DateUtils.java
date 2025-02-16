package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

/**
 * The Class DateUtils.
 */
public final class DateUtils {

    /** The Constant DATE_FORMAT. */
    private static final String DATE_FORMAT = "dd-MMM-yyyy";

    /**
     * Instantiates a new date utils.
     */
    private DateUtils() {
        // Utility class
    }

    /**
     * Format date.
     *
     * @param date the date
     * @return the string
     */
    public static String formatDate(final Date date) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    /**
     * Strip dates after current date.
     *
     * @param toDate the to date
     * @return the date
     */
    public static Date stripDatesAfterCurrentDate(final Date toDate) {
        final DateTime currentTime = new DateTime();

        if (currentTime.isBefore(toDate.getTime())) {
            return currentTime.plusDays(1).toDate();
        } else {
            return toDate;
        }
    }
}

package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.text.ParseException;
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
     * Parses the date.
     *
     * @param dateString the date string
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date parseDate(final String dateString) throws ParseException {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.parse(dateString);
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

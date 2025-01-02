package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

public final class DateUtils {

    private static final String DATE_FORMAT = "dd-MMM-yyyy";

    private DateUtils() {
        // Utility class
    }

    public static String formatDate(final Date date) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public static Date parseDate(final String dateString) throws ParseException {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.parse(dateString);
    }

    public static Date stripDatesAfterCurrentDate(final Date toDate) {
        final DateTime currentTime = new DateTime();

        if (currentTime.isBefore(toDate.getTime())) {
            return currentTime.plusDays(1).toDate();
        } else {
            return toDate;
        }
    }
}

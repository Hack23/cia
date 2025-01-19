package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

import java.util.Locale;

public class GovernmentBodyFieldValueFormatter {
    public static String formatYear(int year) {
        return String.format(Locale.ENGLISH, "%d", year);
    }
    
    public static String formatCount(int count) {
        return String.format(Locale.ENGLISH, "%d", count);
    }
}

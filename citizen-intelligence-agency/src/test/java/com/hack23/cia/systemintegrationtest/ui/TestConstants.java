package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;
import java.util.UUID;

public final class TestConstants {
    public static final long DEFAULT_TIMEOUT = 25000;
    public static final int WAIT_FOR_PAGE_DELAY = 7500;
    public static final Duration WAIT_FOR_PAGE_ELEMENT = Duration.ofMillis(7500);

    private TestConstants() {
        // Prevent instantiation
    }

    public static final String DEFAULT_BROWSER = "chrome";

    public static final String ADMIN_EMAIL = "admin@hack23.com";
    public static final String DEFAULT_ADMIN_PASSWORD = "Admin4hack23!";

    public static final String TEST_EMAIL_DOMAIN = "@test.com";
    public static final String DEFAULT_COUNTRY = "Sweden";

    public static String generatePassword() {
        return UUID.randomUUID().toString();
    }
}

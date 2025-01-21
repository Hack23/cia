package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;
import java.util.UUID;

/**
 * The Class TestConstants.
 */
public final class TestConstants {

    /** The Constant DEFAULT_TIMEOUT. */
    public static final long DEFAULT_TIMEOUT = 25000;

    /** The Constant WAIT_FOR_PAGE_DELAY. */
    public static final int WAIT_FOR_PAGE_DELAY = 7500;

    /** The Constant WAIT_FOR_PAGE_ELEMENT. */
    public static final Duration WAIT_FOR_PAGE_ELEMENT = Duration.ofMillis(7500);

    /**
     * Instantiates a new test constants.
     */
    private TestConstants() {
        // Prevent instantiation
    }

    /** The Constant DEFAULT_BROWSER. */
    public static final String DEFAULT_BROWSER = "chrome";

    /** The Constant ADMIN_EMAIL. */
    public static final String ADMIN_EMAIL = "admin@hack23.com";

    /** The Constant DEFAULT_ADMIN_PASSWORD. */
    public static final String DEFAULT_ADMIN_PASSWORD = "Admin4hack23!";

    /** The Constant TEST_EMAIL_DOMAIN. */
    public static final String TEST_EMAIL_DOMAIN = "@test.com";

    /** The Constant DEFAULT_COUNTRY. */
    public static final String DEFAULT_COUNTRY = "Sweden";

    /**
     * Generate password.
     *
     * @return the string
     */
    public static String generatePassword() {
        return UUID.randomUUID().toString();
    }
}

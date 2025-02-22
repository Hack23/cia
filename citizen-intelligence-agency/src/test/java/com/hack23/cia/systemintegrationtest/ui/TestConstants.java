package com.hack23.cia.systemintegrationtest.ui;

import java.time.Duration;
import java.util.UUID;

/**
 * The Class TestConstants.
 */
public final class TestConstants {

    /** The Constant DEFAULT_TIMEOUT. */
    public static final long DEFAULT_TIMEOUT = 30000;

    /** The Constant WAIT_FOR_PAGE_DELAY. */
    public static final long CHECK = 100;

    /** The Constant WAIT_FOR_PAGE_ELEMENT. */
    public static final Duration WAIT_FOR_PAGE_ELEMENT = Duration.ofMillis(25000);

    /** The Constant DEFAULT_BROWSER_TIMEOUT. */
    public static final Duration DEFAULT_BROWSER_TIMEOUT = Duration.ofSeconds(7);

    /** The Constant CLICK_PAUSE. */
    public static final Duration CLICK_PAUSE = Duration.ofSeconds(5);

    /** The Constant CLICK__MOVE_TO_PAUSE. */
    public static final Duration CLICK__MOVE_TO_PAUSE = Duration.ofMillis(500);

    /** The Constant CLICK_PAUSE_AFTER. */
    public static final Duration CLICK_PAUSE_AFTER = Duration.ofMillis(1000);


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

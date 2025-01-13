package com.hack23.cia.systemintegrationtest;

public abstract class AbstractRoleSystemTest extends AbstractSystemIntegrationTest {
    protected final Browser browser;

    protected AbstractRoleSystemTest(final Browser browser) {
        this.browser = browser;
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }
}
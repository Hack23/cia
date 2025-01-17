package com.hack23.cia.systemintegrationtest.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hack23.cia.systemintegrationtest.admin.configuration.AdminConfigurationTest;
import com.hack23.cia.systemintegrationtest.admin.data.AdminDataTest;
import com.hack23.cia.systemintegrationtest.admin.operations.AdminOperationsTest;
import com.hack23.cia.systemintegrationtest.admin.security.AdminSecurityTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    // Admin Tests only - remove other test references that don't exist
    AdminConfigurationTest.class,
    AdminDataTest.class,
    AdminOperationsTest.class,
    AdminSecurityTest.class
})
public class IntegrationTestSuite {
    // Empty test suite
}

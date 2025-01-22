package com.hack23.cia.systemintegrationtest.suites;

import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hack23.cia.web.impl.ui.application.util.UserContextUtilTest;
import com.hack23.cia.web.impl.ui.application.util.WebBrowserUtilTest;

/**
 * The Class IntegrationTestSuite.
 */
@RunWith(Suite.class)
@DisplayName("CIA Unit Tests")
@Suite.SuiteClasses({
    UserContextUtilTest.class,
    WebBrowserUtilTest.class
})
public class UnitTestSuite {
		
}

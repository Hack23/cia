package com.hack23.cia.systemintegrationtest.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hack23.cia.systemintegrationtest.AdminAgentTest;
import com.hack23.cia.systemintegrationtest.AdminConfigurationTest;
import com.hack23.cia.systemintegrationtest.AdminDataTest;
import com.hack23.cia.systemintegrationtest.AdminEmailTest;
import com.hack23.cia.systemintegrationtest.AdminUserManagementTest;
import com.hack23.cia.systemintegrationtest.AuthorizationTest;
import com.hack23.cia.systemintegrationtest.BallotViewTest;
import com.hack23.cia.systemintegrationtest.CommitteeViewTest;
import com.hack23.cia.systemintegrationtest.MinistryViewTest;
import com.hack23.cia.systemintegrationtest.PartyViewTest;
import com.hack23.cia.systemintegrationtest.PoliticianViewTest;
import com.hack23.cia.systemintegrationtest.SearchViewTest;
import com.hack23.cia.systemintegrationtest.SessionManagementTest;
import com.hack23.cia.systemintegrationtest.UserAuthenticationTest;
import com.hack23.cia.systemintegrationtest.UserNavigationTest;
import com.hack23.cia.systemintegrationtest.ViewPageTest;
import com.hack23.cia.systemintegrationtest.ViewRankingTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // View Tests
        ViewPageTest.class,
        ViewRankingTest.class,
        SearchViewTest.class,
        PartyViewTest.class,
        MinistryViewTest.class,
        PoliticianViewTest.class,
        CommitteeViewTest.class,
        BallotViewTest.class,

        // User Tests
        UserAuthenticationTest.class,
        UserNavigationTest.class,
        AuthorizationTest.class,

        // Admin Tests
        AdminConfigurationTest.class,
        AdminUserManagementTest.class,
        AdminDataTest.class,
        AdminEmailTest.class,
        AdminAgentTest.class,

        // System Tests
        SessionManagementTest.class
})
public class IntegrationTestSuite {
    // Empty test suite
}

package com.hack23.cia.systemintegrationtest.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hack23.cia.systemintegrationtest.admin.AdminAgentTest;
import com.hack23.cia.systemintegrationtest.admin.AdminConfigurationTest;
import com.hack23.cia.systemintegrationtest.admin.AdminDataTest;
import com.hack23.cia.systemintegrationtest.admin.AdminEmailTest;
import com.hack23.cia.systemintegrationtest.admin.AdminUserManagementTest;
import com.hack23.cia.systemintegrationtest.admin.SessionManagementTest;
import com.hack23.cia.systemintegrationtest.user.AuthorizationTest;
import com.hack23.cia.systemintegrationtest.user.BallotViewTest;
import com.hack23.cia.systemintegrationtest.user.CommitteeViewTest;
import com.hack23.cia.systemintegrationtest.user.MinistryViewTest;
import com.hack23.cia.systemintegrationtest.user.PartyViewTest;
import com.hack23.cia.systemintegrationtest.user.PoliticianViewTest;
import com.hack23.cia.systemintegrationtest.user.SearchViewTest;
import com.hack23.cia.systemintegrationtest.user.UserAuthenticationTest;
import com.hack23.cia.systemintegrationtest.user.UserNavigationTest;
import com.hack23.cia.systemintegrationtest.user.ViewPageTest;
import com.hack23.cia.systemintegrationtest.user.ViewRankingTest;

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

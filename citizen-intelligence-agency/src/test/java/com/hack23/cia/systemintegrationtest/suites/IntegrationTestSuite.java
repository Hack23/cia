package com.hack23.cia.systemintegrationtest.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hack23.cia.systemintegrationtest.admin.AdminAgentTest;
import com.hack23.cia.systemintegrationtest.admin.AdminApplicationSystemTest;
import com.hack23.cia.systemintegrationtest.admin.AdminConfigurationSystemTest;
import com.hack23.cia.systemintegrationtest.admin.AdminConfigurationTest;
import com.hack23.cia.systemintegrationtest.admin.AdminDataSummarySystemTest;
import com.hack23.cia.systemintegrationtest.admin.AdminDataTest;
import com.hack23.cia.systemintegrationtest.admin.AdminEmailTest;
import com.hack23.cia.systemintegrationtest.admin.AdminMonitoringSystemTest;
import com.hack23.cia.systemintegrationtest.admin.AdminPageSystemTest;
import com.hack23.cia.systemintegrationtest.admin.AdminRoleSystemITest;
import com.hack23.cia.systemintegrationtest.admin.AdminUserManagementSystemTest;
import com.hack23.cia.systemintegrationtest.admin.AdminUserManagementTest;
import com.hack23.cia.systemintegrationtest.admin.SessionManagementTest;
import com.hack23.cia.systemintegrationtest.user.AuthorizationTest;
import com.hack23.cia.systemintegrationtest.user.BallotViewTest;
import com.hack23.cia.systemintegrationtest.user.CommitteeViewTest;
import com.hack23.cia.systemintegrationtest.user.DocumentViewTest;
import com.hack23.cia.systemintegrationtest.user.MinistryViewTest;
import com.hack23.cia.systemintegrationtest.user.PartyViewTest;
import com.hack23.cia.systemintegrationtest.user.PoliticianViewTest;
import com.hack23.cia.systemintegrationtest.user.SearchViewTest;
import com.hack23.cia.systemintegrationtest.user.UserAuthenticationTest;
import com.hack23.cia.systemintegrationtest.user.UserNavigationTest;
import com.hack23.cia.systemintegrationtest.user.UserRoleSystemITest;
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
        UserRoleSystemITest.class,
        DocumentViewTest.class,
        // User Tests
        UserAuthenticationTest.class,
        UserNavigationTest.class,
        AuthorizationTest.class,

        // Admin Tests
        AdminConfigurationTest.class,
        AdminConfigurationSystemTest.class,
        AdminUserManagementTest.class,
        AdminDataTest.class,
        AdminEmailTest.class,
        AdminAgentTest.class,
        AdminApplicationSystemTest.class,
        AdminDataSummarySystemTest.class,
        AdminMonitoringSystemTest.class,
        AdminRoleSystemITest.class,
        AdminUserManagementSystemTest.class,
        AdminUserManagementTest.class,


        // System Tests
        SessionManagementTest.class,
        AdminPageSystemTest.class
})
public class IntegrationTestSuite {
    // Empty test suite
}

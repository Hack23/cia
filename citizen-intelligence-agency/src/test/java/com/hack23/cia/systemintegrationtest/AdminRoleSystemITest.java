/*
 * Copyright 2010-2025 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.systemintegrationtest;

import static com.hack23.cia.systemintegrationtest.TestConstants.*;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;

// import com.hack23.cia.systemintegrationtest.TestConstants; // Unused import removed
// import com.hack23.cia.web.impl.ui.application.action.ViewAction; // Unused import removed

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DataSummaryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class AdminRoleSystemITest extends AbstractUITest {

    private final Browser browser;

    public AdminRoleSystemITest(final Browser browser) {
        this.browser = browser;
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }

    @Parameters(name = "AdminRoleSiteTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldAccessAdminAgencyPage() {
        retryOnFailure(() -> {
            try {
                loginAsAdmin(pageVisit);
                pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));
                pageVisit.verifyPageContent("Agency");
                pageVisit.selectFirstGridRow();
                pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, DEFAULT_MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void siteAdminApplicationEventChartsTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS));
                    pageVisit.verifyPageContent("Charts");
                    pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void siteAdminApplicationEventTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Admin Application Events");
                    pageVisit.selectFirstGridRow();
                    pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void siteAdminApplicationSessionChartsTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, PageMode.CHARTS));
                    pageVisit.verifyPageContent("Admin Application Session Charts");
                    pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, PageMode.CHARTS));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void siteAdminApplicationSessionLastPageTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Application Session");
                    final WebElement nextPageButton = pageVisit.findButton("last page");
                    pageVisit.performClickAction(nextPageButton);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void siteAdminApplicationSessionNextPageTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Application Session");
                    final WebElement nextPageButton = pageVisit.findButton("next page");
                    pageVisit.performClickAction(nextPageButton);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void siteAdminApplicationSessionTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Application Session");
                    pageVisit.selectFirstGridRow();
                    pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("ApplicationActionEvent");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void siteAdminMonitoringFailedAccessTest() {
        try {
            retryOnFailure(() -> {
                try {
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Access denied:adminmonitoring");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void siteAdminMonitoringSuccessTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Admin Monitoring");
                    assertFalse("Dont expect this content", pageVisit.getIframesHtmlBodyAsText().contains("Login with Username and Password"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void siteAdminPortalTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));
                    pageVisit.verifyPageContent("Portal");
                    pageVisit.selectFirstGridRow();
                    pageVisit.validatePage(new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void visitAdminAuthorSummaryViewTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, DataSummaryPageMode.AUTHORS.toString()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void visitAdminDataSummaryViewRefreshViewsTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));
                    final WebElement refreshViewsButton = pageVisit.findButton("Refresh Views");
                    assertNotNull("Expect to find a Refresh Views Button", refreshViewsButton);
                    pageVisit.performClickAction(refreshViewsButton);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void visitAdminDataSummaryViewRemoveApplicationHistoryTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));
                    final WebElement removeApplicationHistoryButton = pageVisit.findButton("Remove Application History");
                    assertNotNull("Expect to find a Button", removeApplicationHistoryButton);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void visitAdminDataSummaryViewRemoveDocumentsTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));
                    final WebElement removeDocumentsButton = pageVisit.findButton("Remove Documents");
                    assertNotNull("Expect to find a Button", removeDocumentsButton);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void visitAdminDataSummaryViewRemovePoliticianTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));
                    final WebElement removePoliticiansButton = pageVisit.findButton("Remove Politicians");
                    assertNotNull("Expect to find a Button", removePoliticiansButton);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void visitAdminDataSummaryViewTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void visitAdminDataSummaryViewUpdateSearchIndexTest() {
        try {
            retryOnFailure(() -> {
                try {
                    loginAsAdmin(pageVisit);
                    pageVisit.visitDirectPage(new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));
                    final WebElement updateSearchIndexButton = pageVisit.findButton("Update Search Index");
                    assertNotNull("Expect to find a Update Search Index Button", updateSearchIndexButton);
                    pageVisit.performClickAction(updateSearchIndexButton);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, TestConstants.DEFAULT_MAX_RETRIES);
        } catch (AssertionError e) {
            throw new RuntimeException("Test failed", e);
        }
    }
}

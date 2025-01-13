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

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserHomePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@RunWith(Parameterized.class)
@Category(IntegrationTest.class)
public final class UserRoleSystemITest extends AbstractUITest {

    private final Browser browser;
    private final UserPageVisit pageVisit;

    public UserRoleSystemITest(final Browser browser) {
        this.browser = browser;
        this.pageVisit = new UserPageVisit(getWebDriver(), browser);
    }

    @Override
    protected Browser getBrowser() {
        return browser;
    }

    @Parameters(name = "UserRoleSiteTest{index}: browser({0})")
    public static Collection<Browser[]> browsers() {
        return Arrays.asList(new Browser[][] { { Browser.CHROME } });
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldViewMinistryContent() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
                        PageMode.OVERVIEW, "N%C3%A4ringsdepartementet"));
                pageVisit.verifyPageContent("Ministry");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, TestConstants.DEFAULT_MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldViewPartyContent() throws Exception {
        retryOnFailure(() -> {
            try {
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
                        PageMode.OVERVIEW, "S"));
                pageVisit.verifyPageContent("Party");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, TestConstants.DEFAULT_MAX_RETRIES);
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void shouldHandleUnauthorizedAccess() throws Exception {
        retryOnFailure(() -> {
            try {
                // Test user events access
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME,
                        UserHomePageMode.USER_EVENTS.toString()));
                pageVisit.verifyPageContent("Access denied:userhome");

                // Test security settings access
                pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME,
                        UserHomePageMode.SECURITY_SETTINGS.toString()));
                pageVisit.verifyPageContent("Access denied:userhome");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, TestConstants.DEFAULT_MAX_RETRIES);
    }
}

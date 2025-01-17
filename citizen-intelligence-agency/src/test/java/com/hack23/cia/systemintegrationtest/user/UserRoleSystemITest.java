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
package com.hack23.cia.systemintegrationtest.user;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserHomePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public final class UserRoleSystemITest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldViewMinistryContent() throws Exception {

		pageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW, "N%C3%A4ringsdepartementet"));
		pageVisit.verifyPageContent("Ministry");

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldViewPartyContent() throws Exception {

		pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.OVERVIEW, "S"));
		pageVisit.verifyPageContent("Party");

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldHandleUnauthorizedAccess() throws Exception {

		// Test user events access
		pageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.USER_EVENTS.toString()));
		pageVisit.verifyPageContent("Access denied:userhome");

		// Test security settings access
		pageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, UserHomePageMode.SECURITY_SETTINGS.toString()));
		pageVisit.verifyPageContent("Access denied:userhome");

	}
}

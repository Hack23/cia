package com.hack23.cia.systemintegrationtest.user;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public final class PartyViewTest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldViewPartyCommitteeRoles() throws Exception {

		pageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.COMMITTEEROLES.toString(), "S"));
		pageVisit.verifyPageContent("Committee Roles");
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldViewPartyDocumentHistory() throws Exception {

		pageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.DOCUMENTHISTORY.toString(), "S"));
		pageVisit.verifyPageContent("Document History");
	}

	// ... other party view tests
}

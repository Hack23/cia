package com.hack23.cia.systemintegrationtest.user;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;

@Category(IntegrationTest.class)
public final class ViewPageTest extends AbstractUITest {
	private static final ViewAction[] VIEW_ACTIONS = { ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW,
			ViewAction.VISIT_ADMIN_DATA_SUMMARY_VIEW, ViewAction.VISIT_POLITICIAN_RANKING_VIEW,
			ViewAction.VISIT_PARTY_RANKING_VIEW, ViewAction.VISIT_MINISTRY_RANKING_VIEW,
			ViewAction.VISIT_COMMITTEE_RANKING_VIEW, ViewAction.VISIT_COUNTRY_VIEW };

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldViewMainPage() throws Exception {

		pageVisit.visitMainView();
		pageVisit.verifyViewActions(VIEW_ACTIONS);

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldViewStartPage() throws Exception {

		pageVisit.visitStartPage();
		pageVisit.verifyPageContent("Citizen Intelligence Agency");

	}
}

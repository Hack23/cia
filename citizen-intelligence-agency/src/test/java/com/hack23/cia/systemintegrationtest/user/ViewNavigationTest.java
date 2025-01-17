package com.hack23.cia.systemintegrationtest.user;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public final class ViewNavigationTest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldNavigateCommitteeRanking() throws Exception {

		pageVisit.VisitCommitteeRankingView();
		pageVisit.verifyPageContent("Committee Rankings");

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldNavigateMinistryRanking() throws Exception {

		pageVisit.VisitMinistryRankingView();
		pageVisit.verifyPageContent("Ministry Rankings");

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldNavigatePartyRanking() throws Exception {

		pageVisit.VisitPartyRankingView();
		pageVisit.verifyPageContent("Party Rankings");

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldNavigatePoliticianRanking() throws Exception {

		pageVisit.VisitPoliticianRankingView();
		pageVisit.verifyPageContent("Politician Rankings");

	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void shouldNavigatePoliticianViews() throws Exception {

		final String politicianId = "0980681611418";

		pageVisit.visitDirectPage(
				new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.OVERVIEW.toString(), politicianId));
		pageVisit.verifyPageContent("Politician");

		pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
				PoliticianPageMode.DOCUMENTACTIVITY.toString(), politicianId));
		pageVisit.verifyPageContent("Document Activity");

	}
}

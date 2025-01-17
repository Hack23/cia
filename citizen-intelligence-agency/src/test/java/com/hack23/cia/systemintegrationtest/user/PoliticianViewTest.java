package com.hack23.cia.systemintegrationtest.user;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

@Category(IntegrationTest.class)
public class PoliticianViewTest extends AbstractUITest {

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testPoliticianView() throws Exception {

		pageVisit.visitDirectPage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, ""));
		pageVisit.verifyPageContent("Politicians");
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, ""));

	}
}

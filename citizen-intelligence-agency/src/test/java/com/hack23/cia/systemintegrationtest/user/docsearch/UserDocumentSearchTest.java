package com.hack23.cia.systemintegrationtest.user.docsearch;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMainViewConstants;
import com.hack23.cia.web.impl.ui.application.views.user.document.pagemode.DocumentPageTitleConstants;

/**
 * The Class UserDocumentSearchTest.
 */
@Category(IntegrationTest.class)
public final class UserDocumentSearchTest extends AbstractUITest {

	/**
	 * Verify search document page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifySearchDocumentPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandMainViewConstants.COMMAND_SEARCH_DOCUMENT);
		pageVisit.verifyViewContent(DocumentPageTitleConstants.DOC_SEARCH_TITLE,
				DocumentPageTitleConstants.DOC_SEARCH_HEADER, DocumentPageTitleConstants.DOC_SEARCH_DESC);
		pageVisit.validatePage(PageCommandMainViewConstants.COMMAND_SEARCH_DOCUMENT);
	}
}

package com.hack23.cia.systemintegrationtest.user.documents;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandDocumentConstants;
import com.hack23.cia.web.impl.ui.application.views.common.paging.PagingUtilImpl;
import com.hack23.cia.web.impl.ui.application.views.user.document.pagemode.DocumentPageTitleConstants;

/**
 * The Class UserDocumentsTest.
 */
@Category(IntegrationTest.class)
public final class UserDocumentsTest extends AbstractUITest {

	/**
	 * Verify documents page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void verifyDocumentsPage() throws Exception {
		pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENTS_OVERVIEW);
		pageVisit.verifyViewContent(DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_TITLE,
				DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_HEADER,
				DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_DESC);
		pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENTS_OVERVIEW);
		pageVisit.selectFirstGridRow();
		pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_OVERVIEW);
	}


	/**
	 * Site documents paging page test.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = 60000)
	public void siteDocumentsPagingPageTest() throws Exception {
		pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENTS_OVERVIEW);
		pageVisit.verifyViewContent(DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_TITLE,
				DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_HEADER,
				DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_DESC);
		pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENTS_OVERVIEW);

		final WebElement nextPageButton = pageVisit.findButton(PagingUtilImpl.NEXT_PAGE);
		pageVisit.performClickAction(nextPageButton);

		final WebElement lastPageButton = pageVisit.findButton(PagingUtilImpl.LAST_PAGE);
		pageVisit.performClickAction(lastPageButton);

		final WebElement previousPageButton = pageVisit.findButton(PagingUtilImpl.PREVIOUS_PAGE);
		pageVisit.performClickAction(previousPageButton);

		final WebElement firstPageButton = pageVisit.findButton(PagingUtilImpl.FIRST_PAGE);
		pageVisit.performClickAction(firstPageButton);
	}

}

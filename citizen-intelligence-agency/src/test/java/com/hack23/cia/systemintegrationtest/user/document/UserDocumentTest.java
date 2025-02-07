package com.hack23.cia.systemintegrationtest.user.document;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandDocumentConstants;
import com.hack23.cia.web.impl.ui.application.views.user.document.pagemode.DocumentViewConstants;

/**
 * The Class UserDocumentsTest.
 */
@Category(IntegrationTest.class)
public final class UserDocumentTest extends AbstractUITest {

	/** The doc id. */
	private static String DOC_ID = "hb022647";


	/**
	 * Verify document attachement page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentAttachementPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_ATTACHMENTS.createItemPageCommand(DOC_ID));
        pageVisit.verifyViewContent(DocumentViewConstants.ATTACHMENTS_TITLE,
            DocumentViewConstants.ATTACHMENTS_SUBTITLE,
            DocumentViewConstants.ATTACHMENTS_DESC);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_ATTACHMENTS.createItemPageCommand(DOC_ID));
    }

    /**
     * Verify document data page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentDataPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_DATA.createItemPageCommand(DOC_ID));
        pageVisit.verifyViewContent(DocumentViewConstants.DATA_TITLE,
                DocumentViewConstants.DATA_SUBTITLE,
                DocumentViewConstants.DATA_DESC);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_DATA.createItemPageCommand(DOC_ID));
    }

    /**
     * Verify document decision page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentDecisionPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_DECISION.createItemPageCommand(DOC_ID));
        pageVisit.verifyViewContent(DocumentViewConstants.DECISION_TITLE,
                DocumentViewConstants.DECISION_SUBTITLE,
                DocumentViewConstants.DECISION_DESC);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_DECISION.createItemPageCommand(DOC_ID));
    }


	/**
	 * Verify document overview page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentOverviewPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_OVERVIEW.createItemPageCommand(DOC_ID));
        pageVisit.verifyViewContent(DocumentViewConstants.OVERVIEW_TITLE,
                DocumentViewConstants.OVERVIEW_SUBTITLE,
                DocumentViewConstants.OVERVIEW_DESC);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_OVERVIEW.createItemPageCommand(DOC_ID));
    }

    /**
     * Verify document page visit page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentPageVisitPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_PAGEVISIT_HISTORY.createItemPageCommand(DOC_ID));
        pageVisit.verifyViewContent(DocumentViewConstants.VISIT_HISTORY_TITLE,
                DocumentViewConstants.VISIT_HISTORY_SUBTITLE,
                DocumentViewConstants.VISIT_HISTORY_DESC);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_PAGEVISIT_HISTORY.createItemPageCommand(DOC_ID));
    }

    /**
     * Verify document person reference page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentPersonReferencePage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_PERSON_REFERENCES.createItemPageCommand(DOC_ID));
        pageVisit.verifyViewContent(DocumentViewConstants.PERSON_REFERENCES_TITLE,
                DocumentViewConstants.PERSON_REFERENCES_SUBTITLE,
                DocumentViewConstants.PERSON_REFERENCES_DESC);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_PERSON_REFERENCES.createItemPageCommand(DOC_ID));
    }

	/**
	 * Verify document activity page.
	 *
	 * @throws Exception the exception
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentActivityPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_ACTIVITY.createItemPageCommand(DOC_ID));
        pageVisit.verifyViewContent(DocumentViewConstants.ACTIVITY_TITLE,
    			DocumentViewConstants.ACTIVITY_SUBTITLE,
    			DocumentViewConstants.ACTIVITY_DESC);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_ACTIVITY.createItemPageCommand(DOC_ID));
    }

    /**
     * Verify document details page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentDetailsPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_DETAILS.createItemPageCommand(DOC_ID));
        pageVisit.verifyViewContent(DocumentViewConstants.DETAILS_TITLE,
                DocumentViewConstants.DETAILS_SUBTITLE,
                DocumentViewConstants.DETAILS_DESC);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_DETAILS.createItemPageCommand(DOC_ID));
    }

    /**
     * Verify document references page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentReferencesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_REFERENCES.createItemPageCommand(DOC_ID));
        pageVisit.verifyViewContent(DocumentViewConstants.REFERENCES_TITLE,
                DocumentViewConstants.REFERENCES_SUBTITLE,
                DocumentViewConstants.REFERENCES_DESC);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_REFERENCES.createItemPageCommand(DOC_ID));
    }

}

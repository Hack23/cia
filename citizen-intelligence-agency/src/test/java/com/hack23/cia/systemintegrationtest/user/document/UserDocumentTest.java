package com.hack23.cia.systemintegrationtest.user.document;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandDocumentConstants;

/**
 * The Class UserDocumentTest.
 */
@Category(IntegrationTest.class)
public final class UserDocumentTest extends AbstractUITest {

    /**
     * Verify document page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_ACTIVITY);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_ACTIVITY);
    }

    /**
     * Verify document details page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentDetailsPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_DETAILS);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_DETAILS);
    }

    /**
     * Verify document references page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentReferencesPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT_REFERENCES);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT_REFERENCES);
    }

    /**
     * Verify documents page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentsPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENTS);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENTS);
    }

    /**
     * Verify search document page.
     *
     * @throws Exception the exception
     */
    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifySearchDocumentPage() throws Exception {
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_SEARCH_DOCUMENT);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_SEARCH_DOCUMENT);
    }
}

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
        pageVisit.visitDirectPage(PageCommandDocumentConstants.COMMAND_DOCUMENT);
        pageVisit.validatePage(PageCommandDocumentConstants.COMMAND_DOCUMENT);
    }
}

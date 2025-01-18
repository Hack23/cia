package com.hack23.cia.systemintegrationtest.user.views;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PageCommandUserConstants;

@Category(IntegrationTest.class)  
public final class DocumentViewTest extends AbstractUITest {

    @Test(timeout = DEFAULT_TIMEOUT)
    public void verifyDocumentOverview() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_DOCUMENTS);
        pageVisit.verifyPageContent("Document View");
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_DOCUMENTS);
    }

    @Test(timeout = DEFAULT_TIMEOUT) 
    public void verifyDocumentSearch() throws Exception {
        pageVisit.visitDirectPage(PageCommandUserConstants.COMMAND_SEARCH_DOCUMENT);
        pageVisit.verifyPageContent("Search Documents");
        pageVisit.validatePage(PageCommandUserConstants.COMMAND_SEARCH_DOCUMENT);
    }
}

package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Before;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;

/**
 * The Class AbstractAdminTest.
 */
@Category(IntegrationTest.class)
public abstract class AbstractAdminTest extends AbstractUITest {

    /**
     * Admin setup.
     *
     * @throws Exception the exception
     */
    @Before
    public void adminSetup() throws Exception {
    	pageVisit.cleanBrowser();
        pageVisit.loginAsAdmin();
    }

    /**
     * Verify view content.
     *
     * @param contentToVerify the content to verify
     */
    protected void verifyViewContent(final String... contentToVerify) {
        for (final String content : contentToVerify) {
            pageVisit.verifyPageContent(content);
        }
    }
}

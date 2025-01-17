package com.hack23.cia.systemintegrationtest.admin;

import org.junit.Before;
import org.junit.experimental.categories.Category;

import com.hack23.cia.systemintegrationtest.AbstractUITest;
import com.hack23.cia.systemintegrationtest.categories.IntegrationTest;

@Category(IntegrationTest.class)
public abstract class AbstractAdminTest extends AbstractUITest {
    
    @Before
    public void adminSetup() throws Exception {
        pageVisit.loginAsAdmin();
    }
    
    protected void verifyViewContent(final String... contentToVerify) {
        for (String content : contentToVerify) {
            pageVisit.verifyPageContent(content);
        }
    }
}

package com.hack23.cia.systemintegrationtest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@ContextConfiguration(locations = {
    "classpath:/META-INF/application-context-test.xml",
    "classpath:/META-INF/database-context-test.xml"
})
@TestExecutionListeners(listeners = {
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class
})
public abstract class BaseTestConfig {
    // Base configuration for test classes
}

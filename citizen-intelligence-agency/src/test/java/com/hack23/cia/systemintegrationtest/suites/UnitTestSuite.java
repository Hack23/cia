package com.hack23.cia.systemintegrationtest.suites;

import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hack23.cia.web.impl.ui.application.CitizenIntelligenceAgencyHealthCheckServletTest;
import com.hack23.cia.web.impl.ui.application.util.UserContextUtilTest;
import com.hack23.cia.web.impl.ui.application.views.common.converters.ListPropertyConverterTest;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.impl.StringToEnumConverterTest;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.impl.StreamSourceImplementationTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.ComplianceCheckPageItemRendererClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.DecisionFlowValueChangeListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.DeleteAccountClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.DisableGoogleAuthenticatorCredentialClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.LogoutClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.ManageUserAccountClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.RemoveDataClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.RuleViolationPageItemRendererClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SearchDocumentClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SendEmailClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.UpdateApplicationConfigurationClickListenerTest;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.UpdateSearchIndexClickListenerTest;

/**
 * The Class IntegrationTestSuite.
 */
@RunWith(Suite.class)
@DisplayName("CIA Unit Tests")
@Suite.SuiteClasses({
    CitizenIntelligenceAgencyHealthCheckServletTest.class,
    UpdateSearchIndexClickListenerTest.class,
    ManageUserAccountClickListenerTest.class,
    SendEmailClickListenerTest.class,
    RuleViolationPageItemRendererClickListenerTest.class,
    DisableGoogleAuthenticatorCredentialClickListenerTest.class,
    DecisionFlowValueChangeListenerTest.class,
    SearchDocumentClickListenerTest.class,
    DeleteAccountClickListenerTest.class,
    UpdateApplicationConfigurationClickListenerTest.class,
    RemoveDataClickListenerTest.class,
    ComplianceCheckPageItemRendererClickListenerTest.class,
    LogoutClickListenerTest.class,
    PageItemPropertyClickListenerTest.class,
    StringToEnumConverterTest.class,
    StreamSourceImplementationTest.class,
    ListPropertyConverterTest.class,
    UserContextUtilTest.class})
public class UnitTestSuite {

}

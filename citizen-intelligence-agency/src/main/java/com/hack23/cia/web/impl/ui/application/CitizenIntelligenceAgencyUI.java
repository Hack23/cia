/*
 * Copyright 2010-2024 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.web.impl.ui.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationSessionType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.ConfigurationManager;
import com.hack23.cia.service.api.UserConfiguration;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.web.impl.ui.application.util.WebBrowserUtil;
import com.hack23.cia.web.impl.ui.application.views.common.MainView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.UI;


/**
 * The Class CitizenIntelligenceAgencyUI.
 */
@SpringUI
@SpringViewDisplay
@Theme("cia")
@Push(transport = Transport.WEBSOCKET_XHR)
@Viewport("width=device-width, initial-scale=1")
public final class CitizenIntelligenceAgencyUI extends UI {

	/** The Constant CRLF. */
	private static final String CRLF = "[\r\n]";

	/** The Constant CRLF_REPLACEMENT. */
	private static final String CRLF_REPLACEMENT = "";

	/** The Constant LOG_INFO_BROWSER_ADDRESS_APPLICATION_SESSION_ID_RESULT. */
	private static final String LOG_INFO_BROWSER_ADDRESS_APPLICATION_SESSION_ID_RESULT = "Browser url: {} , lang: {} , address: {} , application:{}, sessionId:{}, result:{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CitizenIntelligenceAgencyUI.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The application manager. */
	@Autowired
	private transient ApplicationManager applicationManager;

	@Autowired
	private transient ConfigurationManager configurationManager;

	/** The main view. */
	@Autowired
	private MainView mainView;

	/** The spring navigator. */
	@Autowired
	private SpringNavigator springNavigator;

	/**
	 * Instantiates a new citizen intelligence agency ui.
	 */
	public CitizenIntelligenceAgencyUI() {
		super();
	}


	@Override
	protected void init(final VaadinRequest request) {
		VaadinSession.getCurrent().setErrorHandler(new UiInstanceErrorHandler(this));
		setSizeFull();
		springNavigator.addView(CRLF_REPLACEMENT, mainView);
		setNavigator(springNavigator);


		final Page currentPage = Page.getCurrent();
		final String requestUrl = currentPage.getLocation().toString();
		final String language = request.getLocale().getLanguage();
		final UserConfiguration userConfiguration = configurationManager.getUserConfiguration(requestUrl, language);

		currentPage.setTitle(userConfiguration.getAgency().getAgencyName() + ":" + userConfiguration.getPortal().getPortalName() + ":" + userConfiguration.getLanguage().getLanguageName());

		if (getSession().getUIs().isEmpty()) {
			final WebBrowser webBrowser = currentPage.getWebBrowser();

			final CreateApplicationSessionRequest serviceRequest = new CreateApplicationSessionRequest();
			serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

			final String ipInformation = WebBrowserUtil.getIpInformation(webBrowser);
			serviceRequest.setIpInformation(ipInformation);
			serviceRequest.setTimeZone(webBrowser.getTimeZoneId());
			serviceRequest.setScreenSize(webBrowser.getScreenWidth() + "x" + webBrowser.getScreenHeight());
			serviceRequest.setUserAgentInformation(webBrowser.getBrowserApplication());
			serviceRequest.setLocale(webBrowser.getLocale().toString());
			serviceRequest.setOperatingSystem(WebBrowserUtil.getOperatingSystem(webBrowser));
			serviceRequest.setSessionType(ApplicationSessionType.ANONYMOUS);

			final ServiceResponse serviceResponse = applicationManager.service(serviceRequest);
			LOGGER.info(LOG_INFO_BROWSER_ADDRESS_APPLICATION_SESSION_ID_RESULT,requestUrl.replaceAll(CRLF,CRLF_REPLACEMENT),language.replaceAll(CRLF,CRLF_REPLACEMENT),ipInformation.replaceAll(CRLF,CRLF_REPLACEMENT),webBrowser.getBrowserApplication().replaceAll(CRLF,CRLF_REPLACEMENT),serviceRequest.getSessionId().replaceAll(CRLF,CRLF_REPLACEMENT),serviceResponse.getResult().toString().replaceAll(CRLF,CRLF_REPLACEMENT));
		}
	}


}

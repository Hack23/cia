/*
 * Copyright 2014 James Pether Sörling
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

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hack23.cia.model.internal.application.system.impl.ApplicationSessionType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.web.impl.ui.application.views.common.MainView;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import ru.xpoft.vaadin.DiscoveryNavigator;

/**
 * The Class CitizenIntelligenceAgencyUI.
 */
@Service(value = "ui")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Theme("cia")
@Push(transport = Transport.STREAMING)
public final class CitizenIntelligenceAgencyUI extends UI implements ErrorHandler {


	private static final String LOG_INFO_BROWSER_ADDRESS_APPLICATION_SESSION_ID_RESULT = "Browser address: {} , application:{}, sessionId:{}, result:{}";

	private static final String LOG_WARN_VAADIN_ERROR = "Vaadin error";

	private static final String UNKNOWN = "unknown";

	private static final String I_PHONE = "IPhone";

	private static final String IPAD = "IPad";

	private static final String IOS = "IOS";

	private static final String ANDROID = "Android";

	private static final String MAC_OSX = "MacOSX";

	private static final String WINDOWS_PHONE = "WindowsPhone";

	private static final String WINDOWS2 = "Windows";

	private static final String LINUX = "Linux";

	private static final String X_FORWARDED_FOR = "X-Forwarded-For";

	private static final String CITIZEN_INTELLIGENCE_AGENCY = "Citizen Intelligence Agency";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CitizenIntelligenceAgencyUI.class);

	/** The main view. */
	@Autowired
	private MainView mainView;

	/** The application manager. */
	@Autowired
	private transient ApplicationManager applicationManager;

	/**
	 * Instantiates a new citizen intelligence agency ui.
	 */
	public CitizenIntelligenceAgencyUI() {
		super();
	}

	/**
	 * Instantiates a new citizen intelligence agency ui.
	 *
	 * @param content
	 *            the content
	 */
	public CitizenIntelligenceAgencyUI(final Component content) {
		super(content);
	}


	@Override
	protected void init(final VaadinRequest request) {
		VaadinSession.getCurrent().setErrorHandler(this);
		setSizeFull();
		final DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);
		navigator.addView("", mainView);
		setNavigator(navigator);

		UI.getCurrent().setLocale(Locale.ENGLISH);

		final Page currentPage = Page.getCurrent();

		currentPage.setTitle(CITIZEN_INTELLIGENCE_AGENCY);

		if (getSession().getUIs().isEmpty()) {

		final WebBrowser webBrowser = currentPage.getWebBrowser();

		final CreateApplicationSessionRequest serviceRequest = new CreateApplicationSessionRequest();
		serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		final String ipInformation = getIpInformation(webBrowser);
		serviceRequest.setIpInformation(ipInformation);
		serviceRequest.setUserAgentInformation(webBrowser.getBrowserApplication());
		serviceRequest.setLocale(webBrowser.getLocale().toString());
		serviceRequest.setOperatingSystem(getOperatingSystem(webBrowser));
		serviceRequest.setSessionType(ApplicationSessionType.ANONYMOUS);

		final ServiceResponse serviceResponse = applicationManager.service(serviceRequest);
		LOGGER.info(LOG_INFO_BROWSER_ADDRESS_APPLICATION_SESSION_ID_RESULT,ipInformation,webBrowser.getBrowserApplication(),serviceRequest.getSessionId(),serviceResponse.getResult().toString());
		}
	}

	/**
	 * Gets the ip information.
	 *
	 * @param webBrowser
	 *            the web browser
	 * @return the ip information
	 */
	private static String getIpInformation(final WebBrowser webBrowser) {
		String ipInformation=webBrowser.getAddress();

		final HttpServletRequest httpRequest=((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		final String xForwardedForHeader = httpRequest.getHeader(X_FORWARDED_FOR);
		if (xForwardedForHeader != null) {
			final String[] split = xForwardedForHeader.split(",");
			if (split.length != 0) {
				ipInformation = split[0];
			}
		}
		return ipInformation;
	}



	/**
	 * Gets the operating system.
	 *
	 * @param webBrowser
	 *            the web browser
	 * @return the operating system
	 */
	private static String getOperatingSystem(final WebBrowser webBrowser) {
		String osName = UNKNOWN;
	       if (webBrowser.isLinux()) {
			osName = LINUX;
		} else if (webBrowser.isWindows()) {
			osName = WINDOWS2;
		} else if (webBrowser.isWindowsPhone()) {
			osName = WINDOWS_PHONE;
		} else if (webBrowser.isMacOSX()) {
			osName = MAC_OSX;
		} else if (webBrowser.isAndroid()) {
			osName = ANDROID;
		} else if (webBrowser.isIOS()) {
			osName = IOS;
		} else if (webBrowser.isIPad()) {
			osName = IPAD;
		} else if (webBrowser.isIPhone()) {
			osName = I_PHONE;
		}
		return osName;
	}


	@Override
	public void error(final com.vaadin.server.ErrorEvent event) {
	     if (event.getThrowable() instanceof AccessDeniedException) {
	            final AccessDeniedException accessDeniedException = (AccessDeniedException) event.getThrowable();
	            Notification.show(accessDeniedException.getMessage(), Notification.Type.ERROR_MESSAGE);
	            getUI().getNavigator().navigateTo(CommonsViews.MAIN_VIEW_NAME);
	            return;
	        } else if (event.getThrowable().getCause() !=null && event.getThrowable().getCause().getCause() != null && event.getThrowable().getCause().getCause().getCause() instanceof AccessDeniedException) {
	            final AccessDeniedException accessDeniedException = (AccessDeniedException) event.getThrowable().getCause().getCause().getCause();
	            Notification.show(accessDeniedException.getMessage(), Notification.Type.ERROR_MESSAGE);
	            getUI().getNavigator().navigateTo(CommonsViews.MAIN_VIEW_NAME);
	            return;
	        } else {
	        	LOGGER.warn(LOG_WARN_VAADIN_ERROR,event.getThrowable());
	        }
	}


}

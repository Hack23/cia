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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.MainView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.UI;

import ru.xpoft.vaadin.DiscoveryNavigator;
import ru.xpoft.vaadin.SpringVaadinServlet;

/**
 * The Class CitizenIntelligenceAgencyUI.
 */
@Service(value = "ui")
@Scope("prototype")
@Theme("cia")
@Push(transport = Transport.STREAMING)
public final class CitizenIntelligenceAgencyUI extends UI {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CitizenIntelligenceAgencyUI.class);

	/** The main view. */
	@Autowired
	private MainView mainView;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(final VaadinRequest request) {
		setSizeFull();
		final DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);
		navigator.addView("", mainView);
		setNavigator(navigator);

		UI.getCurrent().setLocale(new Locale("en"));

		final Page currentPage = Page.getCurrent();

		currentPage.setTitle("Citizen Intelligence Agency");

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("key", "principal", authorities));


		final WebBrowser webBrowser = currentPage.getWebBrowser();
		LOGGER.info("Browser address: {} , application:{}",webBrowser.getAddress(),webBrowser.getBrowserApplication());

	}


	/**
	 * The Class Servlet.
	 */
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = true, ui = CitizenIntelligenceAgencyUI.class, widgetset = "com.hack23.cia.web.widgets.WebWidgetSet")
	public static class Servlet extends SpringVaadinServlet {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
	}

}

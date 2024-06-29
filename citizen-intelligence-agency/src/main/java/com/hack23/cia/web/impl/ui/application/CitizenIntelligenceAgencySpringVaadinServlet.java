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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.DefaultUIProvider;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.internal.UIScopeImpl;
import com.vaadin.spring.internal.VaadinSessionScope;
import com.vaadin.spring.server.SpringVaadinServlet;

/**
 * The Class CitizenIntelligenceAgencySpringVaadinServlet.
 */
@WebServlet(value = "/*", loadOnStartup = 2, asyncSupported = true)
@VaadinServletConfiguration(productionMode = true, ui = CitizenIntelligenceAgencyUI.class, widgetset = "com.hack23.cia.web.widgets.WebWidgetSet")
public final class CitizenIntelligenceAgencySpringVaadinServlet extends SpringVaadinServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
		super.service(request, response);
	}

	@Override
	protected void servletInitialized() throws ServletException {
		final VaadinServletService service = getService();
		service.addSessionInitListener(event -> {
			final VaadinSession session = event.getSession();
			for (final UIProvider provider : session.getUIProviders()) {
				if (DefaultUIProvider.class.getCanonicalName().equals(provider.getClass().getCanonicalName())) {
					session.removeUIProvider(provider);
				}
			}
			session.addUIProvider(new CustomSpringUIProvider(session));
		});
		service.addSessionDestroyListener(event -> {
			final VaadinSession session = event.getSession();
			UIScopeImpl.cleanupSession(session);
			VaadinSessionScope.cleanupSession(session);
		});
	}
}
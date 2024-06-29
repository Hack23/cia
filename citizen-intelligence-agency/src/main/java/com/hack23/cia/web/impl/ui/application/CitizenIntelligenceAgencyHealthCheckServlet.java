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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class CitizenIntelligenceAgencyHealthCheckServlet.
 */
@WebServlet(value = "/healthcheck/*", loadOnStartup = 1, asyncSupported = true)
public final class CitizenIntelligenceAgencyHealthCheckServlet extends HttpServlet {

	/** The Constant OK. */
	private static final String OK = "OK";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TEXT_HTML. */
	private static final String TEXT_HTML = "text/html";

	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(TEXT_HTML);
		response.getWriter().println(OK);
	}
}
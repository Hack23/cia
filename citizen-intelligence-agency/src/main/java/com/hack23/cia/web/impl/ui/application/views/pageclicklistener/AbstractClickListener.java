/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hack23.cia.service.api.ApplicationManager;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * The Class AbstractClickListener.
 */
abstract class AbstractClickListener {

	/**
	 * Gets the application manager.
	 *
	 * @return the application manager
	 */
	protected ApplicationManager getApplicationManager() {
		return WebApplicationContextUtils
				.getWebApplicationContext(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
						.getRequest().getSession(true).getServletContext())
				.getBean(ApplicationManager.class);
	}

	/**
	 * Show notification.
	 *
	 * @param caption     the caption
	 * @param description the description
	 * @param type        the type
	 */
	protected void showNotification(final String caption, final String description, final Type type) {
		Notification.show(caption, description, type);
	}


}

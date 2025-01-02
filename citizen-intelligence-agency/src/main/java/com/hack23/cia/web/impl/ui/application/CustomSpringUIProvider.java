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
package com.hack23.cia.web.impl.ui.application;

import com.vaadin.server.UICreateEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.internal.SpringViewDisplayRegistrationBean;
import com.vaadin.spring.internal.UIID;
import com.vaadin.spring.server.SpringUIProvider;
import com.vaadin.ui.UI;
import com.vaadin.util.CurrentInstance;

/**
 * The Class CustomSpringUIProvider.
 */
public class CustomSpringUIProvider extends SpringUIProvider {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The spring view display registration bean. */
	private final SpringViewDisplayRegistrationBean springViewDisplayRegistrationBean = new SpringViewDisplayRegistrationBean();

	/**
	 * Instantiates a new custom spring UI provider.
	 *
	 * @param vaadinSession the vaadin session
	 */
	public CustomSpringUIProvider(final VaadinSession vaadinSession) {
		super(vaadinSession);
	}

	@Override
	public UI createInstance(final UICreateEvent event) {
		final Class<UIID> key = UIID.class;
		final UIID identifier = new UIID(event);
		CurrentInstance.set(key, identifier);
		try {
			logger.debug("Creating a new UI bean of class [{}] with identifier [{}]",
					event.getUIClass().getCanonicalName(), identifier);
			final UI ui = getWebApplicationContext().getBean(event.getUIClass());

			getSpringViewDisplayRegistrationBean().setBeanClass(event.getUIClass());

			configureNavigator(ui);
			return ui;
		} finally {
			CurrentInstance.set(key, null);
		}
	}

	@Override
	protected Object findSpringViewDisplay(final UI ui) {
		return getSpringViewDisplayRegistrationBean().getSpringViewDisplay(getWebApplicationContext());
	}

	/**
	 * Gets the spring view display registration bean.
	 *
	 * @return the spring view display registration bean
	 */
	private SpringViewDisplayRegistrationBean getSpringViewDisplayRegistrationBean() {
		return springViewDisplayRegistrationBean;

	}
}

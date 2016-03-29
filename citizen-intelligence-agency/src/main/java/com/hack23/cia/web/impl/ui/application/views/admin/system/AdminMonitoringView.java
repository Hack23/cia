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
package com.hack23.cia.web.impl.ui.application.views.admin.system;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.admin.common.AbstractAdminView;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class AdminDataSummaryView.
 */
@Service
@Scope(value="prototype")
@VaadinView(AdminMonitoringView.NAME)
public final class AdminMonitoringView extends AbstractAdminView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_MONITORING_VIEW_NAME;

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

	/**
	 * Instantiates a new admin monitoring view.
	 */
	public AdminMonitoringView() {
		super();
	}

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		createContent();
	}

	@Override
	public void enter(final ViewChangeEvent event) {

		createContent();
	}

	/**
	 * Creates the content.
	 */
	private void createContent() {
		final VerticalLayout content = new VerticalLayout();

		final BrowserFrame browser = new BrowserFrame("Admin Monitoring",
			    new ExternalResource("./monitoring"));
			browser.setSizeFull();

		content.addComponent(browser);

		content.addComponent(pageLinkFactory.createMainViewPageLink());
		content.setExpandRatio(browser, ContentRatio.FULL_SIZE);

		content.setSizeFull();
		setContent(content);


		setSizeFull();

		pageActionEventHelper.createPageEvent(ViewAction.VISIT_ADMIN_MONITORING_VIEW, ApplicationEventGroup.ADMIN, NAME, null, null);
	}

}

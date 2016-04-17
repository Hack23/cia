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

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.admin.common.AbstractAdminView;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
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
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(AdminMonitoringView.NAME)
public final class AdminMonitoringView extends AbstractAdminView {

	/** The Constant MONITORING_CONTEXT_PATH. */
	private static final String MONITORING_CONTEXT_PATH = "./monitoring";

	/** The Constant ADMIN_MONITORING. */
	private static final String ADMIN_MONITORING = "Admin Monitoring";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_MONITORING_VIEW_NAME;

	/** The page mode content factory map. */
	private final transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;

	/**
	 * Instantiates a new admin monitoring view.
	 *
	 * @param context
	 *            the context
	 */
	public AdminMonitoringView(final ApplicationContext context) {
		super();
		pageModeContentFactoryMap = context.getBeansOfType(PageModeContentFactory.class);

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
		final String parameters = event.getParameters();

		for (final PageModeContentFactory pageModeContentFactory : pageModeContentFactoryMap.values()) {

			if (pageModeContentFactory.matches(NAME, parameters)) {



				setContent(pageModeContentFactory.createContent(parameters, null, this));

				return;
			}
		}


		createContent();
	}

	/**
	 * Creates the content.
	 */
	private void createContent() {
		final VerticalLayout content = new VerticalLayout();

		final BrowserFrame browser = new BrowserFrame(ADMIN_MONITORING,
			    new ExternalResource(MONITORING_CONTEXT_PATH));
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

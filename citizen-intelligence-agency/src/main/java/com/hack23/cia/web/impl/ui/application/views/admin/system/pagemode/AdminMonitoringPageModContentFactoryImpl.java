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
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminMonitoringPageModContentFactoryImpl.
 */
@Component
public final class AdminMonitoringPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {


	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_MONITORING_VIEW_NAME;

	/**
	 * Instantiates a new admin agency page mod content factory impl.
	 */
	public AdminMonitoringPageModContentFactoryImpl() {
		super(NAME);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();
		content.addStyleName("v-layout-content-overview-panel-level1");

		final String pageId = getPageId(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		final BrowserFrame browser = new BrowserFrame(AdminViewConstants.ADMIN_MONITORING, new ExternalResource(AdminViewConstants.MONITORING_CONTEXT_PATH));
		browser.setSizeFull();

		content.addComponent(browser);
		content.setExpandRatio(browser, ContentRatio.FULL_SIZE);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_MONITORING_VIEW, ApplicationEventGroup.ADMIN,
				NAME, null, pageId);

		return content;

	}

}

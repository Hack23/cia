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
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminApplicationSessionChartsPageModContentFactoryImpl.
 */
@Component
public final class AdminApplicationSessionChartsPageModContentFactoryImpl
		extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant ADMIN_APPLICATION_ACTION_EVENT. */
	private static final String ADMIN_APPLICATION_ACTION_EVENT = "Admin Daily Active Users Chart";

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME;

	/**
	 * Instantiates a new admin application session charts page mod content factory impl.
	 */
	public AdminApplicationSessionChartsPageModContentFactoryImpl() {
		super(NAME);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		final String pageId = getPageId(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		LabelFactory.createHeader2Label(content, ADMIN_APPLICATION_ACTION_EVENT);

		getAdminChartDataManager().createApplicationSessionPageDailySummaryChart(content);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_APPLICATION_SESSION_VIEW,
				ApplicationEventGroup.ADMIN, NAME, null, pageId);

		return content;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return parameters != null && NAME.equals(page) && parameters.contains(PageMode.CHARTS.toString());
	}

}

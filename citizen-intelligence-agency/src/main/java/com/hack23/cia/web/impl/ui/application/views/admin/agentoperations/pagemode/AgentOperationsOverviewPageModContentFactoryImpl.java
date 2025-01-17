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
package com.hack23.cia.web.impl.ui.application.views.admin.agentoperations.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.StartAgentClickListener;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AgentOperationsOverviewPageModContentFactoryImpl.
 */
@Component
public final class AgentOperationsOverviewPageModContentFactoryImpl
		extends AbstractAgentOperationsPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME;


	/**
	 * Instantiates a new agent operations overview page mod content factory impl.
	 */
	public AgentOperationsOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		CardInfoRowUtil.createPageHeader(panel, content, AdminViewConstants.ADMIN_AGENT_OPERATION_OVERVIEW,
				AdminViewConstants.ADMIN_AGENT_OPERATION, AdminViewConstants.ADMIN_AGENT_OPERATION_TASKS_OVERVIEW);

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();

		content.addComponent(horizontalLayout);
		content.setExpandRatio(horizontalLayout, ContentRatio.LARGE);

		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		horizontalLayout.addComponent(overviewLayout);
		horizontalLayout.setExpandRatio(overviewLayout, ContentRatio.LARGE);

		final ResponsiveRow grid = RowUtil.createGridLayout(overviewLayout);

		final Button deployAgentSwedenButton = new Button(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_SWEDEN_BUTTON, VaadinIcons.BULLSEYE);
		deployAgentSwedenButton.addClickListener(
				new StartAgentClickListener(DataAgentTarget.MODEL_EXTERNAL_RIKSDAGEN, DataAgentOperation.IMPORT));
		deployAgentSwedenButton.setId(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_SWEDEN_BUTTON);
		RowUtil.createRowItem(grid, deployAgentSwedenButton, AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_DESC);
		final Button deployAgentWorldButton = new Button(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_WORLD_BUTTON, VaadinIcons.BULLSEYE);
		deployAgentWorldButton.addClickListener(
				new StartAgentClickListener(DataAgentTarget.MODEL_EXTERNAL_WORLDBANK, DataAgentOperation.IMPORT));
		deployAgentWorldButton.setId(AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_WORLD_BUTTON);
		RowUtil.createRowItem(grid, deployAgentWorldButton, AdminViewConstants.ADMIN_AGENT_OPERATION_DEPLOY_DESC);

		final String pageId = getPageId(parameters);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW,
				ApplicationEventGroup.ADMIN, NAME, null, pageId);

		return content;
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page);
	}

}

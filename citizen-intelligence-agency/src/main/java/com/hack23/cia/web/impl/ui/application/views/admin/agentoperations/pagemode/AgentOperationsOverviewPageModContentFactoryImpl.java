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
package com.hack23.cia.web.impl.ui.application.views.admin.agentoperations.pagemode;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.service.api.AgentContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.StartAgentClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
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

	/** The Constant OPERATION2. */
	private static final String OPERATION2 = "/Operation";

	/** The Constant TARGET2. */
	private static final String TARGET2 = "/Target";

	/** The Constant START. */
	private static final String START = "Start";

	/** The Constant OPERATION. */
	private static final String OPERATION = "Operation";

	/** The Constant TARGET. */
	private static final String TARGET = "Target";

	/** The Constant ADMIN_AGENT_OPERATION. */
	private static final String ADMIN_AGENT_OPERATION = "Admin Agent Operation";

	/** The agent container. */
	@Autowired
	private AgentContainer agentContainer;

	/**
	 * Instantiates a new agent operations overview page mod content factory
	 * impl.
	 */
	public AgentOperationsOverviewPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		LabelFactory.createHeader2Label(content,ADMIN_AGENT_OPERATION);

		final ComboBox targetSelect = new ComboBox(TARGET, Arrays.asList(DataAgentTarget.values()));
		targetSelect.setId(ViewAction.START_AGENT_BUTTON + TARGET2);
		content.addComponent(targetSelect);
		content.setExpandRatio(targetSelect, ContentRatio.SMALL2);

		final ComboBox operationSelect = new ComboBox(OPERATION, Arrays.asList(DataAgentOperation.values()));
		operationSelect.setId(ViewAction.START_AGENT_BUTTON + OPERATION2);
		content.addComponent(operationSelect);
		content.setExpandRatio(operationSelect, ContentRatio.SMALL2);

		final Button startAgentButton = new Button(START,
				new StartAgentClickListener(targetSelect, operationSelect, agentContainer));
		startAgentButton.setId(ViewAction.START_AGENT_BUTTON.name());
		startAgentButton.setIcon(VaadinIcons.CROSSHAIRS);
		content.addComponent(startAgentButton);
		content.setExpandRatio(startAgentButton, ContentRatio.SMALL3);

		content.setSizeFull();
		content.setMargin(false);
		content.setSpacing(true);

		content.setWidth(100, Unit.PERCENTAGE);
		content.setHeight(100, Unit.PERCENTAGE);

		return content;
	}

}

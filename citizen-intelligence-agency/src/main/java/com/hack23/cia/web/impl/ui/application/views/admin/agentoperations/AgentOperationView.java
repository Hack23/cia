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
package com.hack23.cia.web.impl.ui.application.views.admin.agentoperations;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.service.api.AgentContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.admin.common.AbstractAdminView;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class AgentOperationView.
 */
@Service
@Scope("prototype")
@VaadinView(AgentOperationView.NAME)
//@Secured({ "ROLE_ADMIN" })
public final class AgentOperationView extends AbstractAdminView implements
		Button.ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgentOperationView.class);


	/** The agent container. */
	@Autowired
	private transient AgentContainer agentContainer;

	/** The content. */
	private final VerticalLayout content = new VerticalLayout();

	/** The target select. */
	private ComboBox targetSelect;

	/** The operation select. */
	private ComboBox operationSelect;

	/**
	 * Instantiates a new agent operation view.
	 */
	public AgentOperationView() {
		super();
	}

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		content.addComponent(LabelFactory.createHeader2Label("Admin"));

		targetSelect = new ComboBox("Target", Arrays.asList(DataAgentTarget
				.values()));
		targetSelect.setId(ViewAction.START_AGENT_BUTTON + "/Target");
		content.addComponent(targetSelect);

		operationSelect = new ComboBox("Operation",
				Arrays.asList(DataAgentOperation.values()));
		operationSelect.setId(ViewAction.START_AGENT_BUTTON + "/Operation");
		content.addComponent(operationSelect);

		final Button startAgentButton = new Button("Start", this);
		startAgentButton.setId(ViewAction.START_AGENT_BUTTON.name());
		content.addComponent(startAgentButton);

		content.setSizeFull();
		content.setMargin(false);
		content.setSpacing(true);

		content.addComponent(pageLinkFactory.createMainViewPageLink());

		content.setWidth(100, Unit.PERCENTAGE);
		content.setHeight(100, Unit.PERCENTAGE);
		setContent(content);
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.
	 * ClickEvent)
	 */
	@Override
	public void buttonClick(final ClickEvent event) {
			if (targetSelect.getValue() != null && operationSelect.getValue()  != null) {
				final DataAgentWorkOrder dataAgentWorkOrder = new DataAgentWorkOrder();
				final DataAgentTarget target = DataAgentTarget.valueOf(targetSelect
						.getValue().toString());
				dataAgentWorkOrder.setTarget(target);
				dataAgentWorkOrder.setOperation(DataAgentOperation
						.valueOf(operationSelect.getValue().toString()));

				agentContainer.execute(dataAgentWorkOrder);
			}
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
	 * .ViewChangeEvent)
	 */
	@Override
	public void enter(final ViewChangeEvent event) {
	}

}

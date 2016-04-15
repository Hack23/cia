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
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.DataAgentOperation;
import com.hack23.cia.model.internal.application.data.impl.DataAgentTarget;
import com.hack23.cia.model.internal.application.data.impl.DataAgentWorkOrder;
import com.hack23.cia.service.api.AgentContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.admin.common.AbstractAdminView;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class AgentOperationView.
 */
@Service
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(AgentOperationView.NAME)
public final class AgentOperationView extends AbstractAdminView implements
		Button.ClickListener {

	private static final String OPERATION2 = "/Operation";

	private static final String TARGET2 = "/Target";

	private static final String LOG_MSG_EXECUTE_WORKORDER = "Execute workorder:{}";

	private static final String START = "Start";

	private static final String OPERATION = "Operation";

	private static final String TARGET = "Target";

	private static final String ADMIN_AGENT_OPERATION = "Admin Agent Operation";

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

	private final transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;

	/**
	 * Instantiates a new agent operation view.
	 *
	 * @param context
	 *            the context
	 */
	public AgentOperationView(final ApplicationContext context) {
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

	/**
	 * Creates the content.
	 */
	private void createContent() {
		content.removeAllComponents();
		final Label createHeader2Label = LabelFactory.createHeader2Label(ADMIN_AGENT_OPERATION);
		content.addComponent(createHeader2Label);
		content.setExpandRatio(createHeader2Label, ContentRatio.SMALL2);


		targetSelect = new ComboBox(TARGET, Arrays.asList(DataAgentTarget
				.values()));
		targetSelect.setId(ViewAction.START_AGENT_BUTTON + TARGET2);
		content.addComponent(targetSelect);
		content.setExpandRatio(targetSelect, ContentRatio.SMALL2);


		operationSelect = new ComboBox(OPERATION,
				Arrays.asList(DataAgentOperation.values()));
		operationSelect.setId(ViewAction.START_AGENT_BUTTON + OPERATION2);
		content.addComponent(operationSelect);
		content.setExpandRatio(operationSelect, ContentRatio.SMALL2);


		final Button startAgentButton = new Button(START, this);
		startAgentButton.setId(ViewAction.START_AGENT_BUTTON.name());
		content.addComponent(startAgentButton);
		content.setExpandRatio(startAgentButton, ContentRatio.SMALL3);


		content.setSizeFull();
		content.setMargin(false);
		content.setSpacing(true);

	 	final Link createMainViewPageLink = pageLinkFactory.createMainViewPageLink();
		content.addComponent(createMainViewPageLink);
		content.setExpandRatio(createMainViewPageLink,ContentRatio.SMALL);

		content.setWidth(100, Unit.PERCENTAGE);
		content.setHeight(100, Unit.PERCENTAGE);
		setContent(content);
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
	}

	@Override
	public void buttonClick(final ClickEvent event) {
			if (targetSelect.getValue() != null && operationSelect.getValue()  != null) {
				final DataAgentWorkOrder dataAgentWorkOrder = new DataAgentWorkOrder();
				final DataAgentTarget target = DataAgentTarget.valueOf(targetSelect
						.getValue().toString());
				dataAgentWorkOrder.setTarget(target);
				dataAgentWorkOrder.setOperation(DataAgentOperation
						.valueOf(operationSelect.getValue().toString()));
				LOGGER.info(LOG_MSG_EXECUTE_WORKORDER,dataAgentWorkOrder);
				agentContainer.execute(dataAgentWorkOrder);
			}
	}

	//@Secured({ "ROLE_ADMIN" })
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

}

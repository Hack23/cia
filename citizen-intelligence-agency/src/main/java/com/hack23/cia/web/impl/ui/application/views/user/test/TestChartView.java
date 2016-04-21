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
package com.hack23.cia.web.impl.ui.application.views.user.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.AbstractView;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class TestChartView.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(value = TestChartView.NAME, cached = true)
public final class TestChartView extends AbstractView {

	/** The Constant CITIZEN_INTELLIGENCE_AGENCY_TEST_CHART_VIEW. */
	private static final String CITIZEN_INTELLIGENCE_AGENCY_TEST_CHART_VIEW = "Citizen Intelligence Agency::Test Chart View";

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.TEST_CHART_VIEW_NAME;

	/** The menu item factory. */
	@Autowired
	private transient MenuItemFactory menuItemFactory;

	/** The page mode content. */
	private VerticalLayout pageModeContent;

	/**
	 * Instantiates a new test chart view.
	 *
	 * @param context
	 *            the context
	 */
	public TestChartView(final ApplicationContext context) {
		super(context.getBeansOfType(PageModeContentFactory.class), NAME);
	}

	/**
	 * Post construct.
	 */
	//@PostConstruct
	public void content() {
		setSizeFull();

		setCaption(CITIZEN_INTELLIGENCE_AGENCY_TEST_CHART_VIEW);

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		final MenuBar barmenu = new MenuBar();
		layout.addComponent(barmenu);

		menuItemFactory.createTestTopicMenu(barmenu);

		pageModeContent = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		layout.addComponent(pageModeContent);

		pageModeContent.addComponent(new Label(OVERVIEW));

		layout.addComponent(pageLinkFactory.createMainViewPageLink());

		setContent(layout);

	}

}
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
package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MainViewOverviewPageModContentFactoryImpl.
 */
@Component
public final class MainViewOverviewPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant CITIZEN_INTELLIGENCE_AGENCY_MAIN. */
	private static final String CITIZEN_INTELLIGENCE_AGENCY_MAIN = "Citizen Intelligence Agency::Main";

	/** The Constant NAME. */
	public static final String NAME = CommonsViews.MAIN_VIEW_NAME;

	/**
	 * Instantiates a new main view overview page mod content factory impl.
	 */
	public MainViewOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		final String pageId = getPageId(parameters);

		panel.setCaption(NAME + "::" + CITIZEN_INTELLIGENCE_AGENCY_MAIN);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		LabelFactory.createHeader2Label(panelContent, "MainOverview");


		final Label descriptionLabel = new Label("Visualize political activity in Sweden, present key performance indicators and metadata for the actors on national level");
		descriptionLabel.addStyleName("itembox");
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		panelContent.addComponent(descriptionLabel);

		getMenuItemFactory().createOverviewPage(panelContent);

		panel.setCaption(NAME + "::" + CITIZEN_INTELLIGENCE_AGENCY_MAIN);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MAIN_VIEW, ApplicationEventGroup.USER,
				CommonsViews.MAIN_VIEW_NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page)
				&& (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.RegisterUserClickListener;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class MainViewRegisterPageModContentFactoryImpl.
 */
@Component
public final class MainViewRegisterPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	private static final List<String> AS_LIST = Arrays.asList( "username", "email", "country", "userpassword" );

	/** The Constant CITIZEN_INTELLIGENCE_AGENCY_MAIN. */
	private static final String CITIZEN_INTELLIGENCE_AGENCY_MAIN = "Citizen Intelligence Agency::Main";

	/** The Constant NAME. */
	public static final String NAME = CommonsViews.MAIN_VIEW_NAME;

	private static final String REGISTER = "Register";

	/**
	 * Instantiates a new main view register page mod content factory impl.
	 */
	public MainViewRegisterPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();
		final String pageId = getPageId(parameters);


		getMenuItemFactory().createMainPageMenuBar(menuBar);

		final VerticalLayout registerLayout = new VerticalLayout();
		registerLayout.setSizeFull();

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		registerLayout.addComponent(formPanel);

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);

		final RegisterUserRequest reqisterRequest = new RegisterUserRequest();
		reqisterRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		reqisterRequest.setUsername("");
		reqisterRequest.setEmail("");
		reqisterRequest.setCountry("");
		reqisterRequest.setUserpassword("");
		final ClickListener reqisterListener = new RegisterUserClickListener(reqisterRequest);
		getFormFactory().addRequestInputFormFields(formContent, reqisterRequest,
				RegisterUserRequest.class,
				AS_LIST, REGISTER,
				reqisterListener);

		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		content.addComponent(overviewLayout);
		content.setExpandRatio(overviewLayout, ContentRatio.LARGE);

		final ResponsiveRow grid = RowUtil.createGridLayout(overviewLayout);
		RowUtil.createRowComponent(grid,registerLayout,"Register a new user");

		panel.setCaption(NAME + "::" + CITIZEN_INTELLIGENCE_AGENCY_MAIN);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MAIN_VIEW, ApplicationEventGroup.USER,
				CommonsViews.MAIN_VIEW_NAME, parameters, pageId);

		return content;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page)
				&& StringUtils.contains(parameters, ApplicationPageMode.REGISTER.toString());
	}

}

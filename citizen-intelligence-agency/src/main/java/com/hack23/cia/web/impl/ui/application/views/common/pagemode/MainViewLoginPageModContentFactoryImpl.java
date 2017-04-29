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
package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.vaadin.ui.LoginForm;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.web.impl.ui.application.action.ApplicationAction;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.ApplicationLoginListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MainViewLoginPageModContentFactoryImpl.
 */
@Component
public final class MainViewLoginPageModContentFactoryImpl extends AbstractPageModContentFactoryImpl {

	/** The Constant CITIZEN_INTELLIGENCE_AGENCY_MAIN. */
	private static final String CITIZEN_INTELLIGENCE_AGENCY_MAIN = "Citizen Intelligence Agency::Main";

	/** The Constant NAME. */
	public static final String NAME = CommonsViews.MAIN_VIEW_NAME;

	/**
	 * Instantiates a new main view login page mod content factory impl.
	 */
	public MainViewLoginPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page)
				&& (!StringUtils.isEmpty(parameters) && parameters.contains(ApplicationPageMode.LOGIN.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();
		final String pageId = getPageId(parameters);

		panel.setCaption(CITIZEN_INTELLIGENCE_AGENCY_MAIN);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		final LoginForm loginForm = new EmailPasswordLoginForm();
		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setOtpCode("");
		loginForm.addLoginListener(new ApplicationLoginListener(loginRequest));
		loginForm.setId(ApplicationAction.LOGIN.toString());
		loginForm.setIcon(FontAwesome.SIGN_IN);

		final BeanFieldGroup<LoginRequest> fieldGroup = new BeanFieldGroup<>(LoginRequest.class);
		fieldGroup.setItemDataSource(new BeanItem<>(loginRequest));
		fieldGroup.setReadOnly(true);
		fieldGroup.setBuffered(false);
		final Field<?> buildAndBind = fieldGroup.buildAndBind("otpCode");
		buildAndBind.setReadOnly(false);
		content.addComponent(buildAndBind);

		content.addComponent(loginForm);

		panel.setCaption(CITIZEN_INTELLIGENCE_AGENCY_MAIN);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MAIN_VIEW, ApplicationEventGroup.USER,
				CommonsViews.MAIN_VIEW_NAME, parameters, pageId);


		return content;

	}

}

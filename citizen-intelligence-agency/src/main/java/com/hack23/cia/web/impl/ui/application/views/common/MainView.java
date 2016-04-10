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
package com.hack23.cia.web.impl.ui.application.views.common;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.ejt.vaadin.loginform.DefaultVerticalLoginForm;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.ApplicationLoginListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.RegisterUserClickListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class MainView.
 */
@Service("MainView")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(MainView.NAME)
public final class MainView extends Panel implements View {

	private static final String CITIZEN_INTELLIGENCE_AGENCY_MAIN = "Citizen Intelligence Agency::Main";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = CommonsViews.MAIN_VIEW_NAME;

	/** The application manager. */
	@Autowired
	private transient ApplicationManager applicationManager;

	/** The page link factory. */
	@Autowired
	private transient PageLinkFactory pageLinkFactory;

	/** The menu item factory. */
	@Autowired
	private transient MenuItemFactory menuItemFactory;

	/** The form factory. */
	@Autowired
	private transient FormFactory formFactory;


	/**
	 * Instantiates a new main view.
	 */
	public MainView() {
		super();
	}

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createContent();

	}


	@Override
	public void enter(final ViewChangeEvent event) {
		createContent();
	}


	private void createContent() {
		setCaption(CITIZEN_INTELLIGENCE_AGENCY_MAIN);

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);

		layout.addComponent(menuItemFactory.createMainPageMenuBar());

		final TextArea totalpoliticantoplistLabel = new TextArea(
				"Politician Ranking by topic",
				"Time served in Parliament:ALL:CURRENT:*FILTER:Gender,Party,ElectionRegion"
						+ "\nTime served in Committees:ALL:CURRENT:*FILTER:Gender,Party,ElectionRegion"
						+ "\nTime served in Government:ALL:CURRENT:*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType,Gender,Party,ElectionRegion"
						+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType,Gender,Party,ElectionRegion"

						+ "\nTop votes:ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop vote party rebel NR/PERCENTAGE :ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop vote presence NR/PERCENTAGE :ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nSearch by name");
		totalpoliticantoplistLabel.setSizeFull();
		layout.addComponent(totalpoliticantoplistLabel);

		final TextArea totalpartytoplistLabel = new TextArea(
				"Party Ranking by topic",
				"Time served in Parliament:ALL:CURRENT:"
						+ "\nTime served in Committees:ALL:CURRENT:"
						+ "\nTime served in Government:ALL:CURRENT:"
						+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType"
						+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType"

						+ "\nTop votes NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nTop vote party rebel NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nTop vote presence NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
						+ "\nSearch by name");
		totalpartytoplistLabel.setSizeFull();
		layout.addComponent(totalpartytoplistLabel);

		layout.addComponent(pageLinkFactory.createAdminAgentOperationViewPageLink());
		layout.addComponent(pageLinkFactory.createAdminDataSummaryViewPageLink());
		layout.addComponent(pageLinkFactory.createPoliticianRankingViewPageLink());

		layout.addComponent(pageLinkFactory.createPartyRankingViewPageLink());


		layout.addComponent(pageLinkFactory.createCommitteeRankingViewPageLink());

		layout.addComponent(pageLinkFactory.createMinistryRankingViewPageLink());

		layout.addComponent(pageLinkFactory.createSearchDocumentViewPageLink());

		layout.addComponent(pageLinkFactory.createTestChartViewPageLink());

		layout.addComponent(pageLinkFactory.createMainViewPageLink());

		final DefaultVerticalLoginForm loginForm = new EmailPasswordLoginForm();
		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setOtpCode("");
		loginForm.addLoginListener(new ApplicationLoginListener(applicationManager,loginRequest));


		final BeanFieldGroup<LoginRequest> fieldGroup = new BeanFieldGroup<>(LoginRequest.class);
		fieldGroup.setItemDataSource(new BeanItem<>(loginRequest));
		fieldGroup.setReadOnly(true);
		fieldGroup.setBuffered(false);
		Field<?> buildAndBind = fieldGroup.buildAndBind("otpCode");
		buildAndBind.setReadOnly(false);
		layout.addComponent(buildAndBind);

		layout.addComponent(loginForm);

		final VerticalLayout registerLayout = new VerticalLayout();
		registerLayout.setSizeFull();

		final RegisterUserRequest reqisterRequest = new RegisterUserRequest();
		reqisterRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		reqisterRequest.setUsername("");
		reqisterRequest.setEmail("");
		reqisterRequest.setCountry("");
		reqisterRequest.setUserpassword("");
		final ClickListener reqisterListener = new RegisterUserClickListener(reqisterRequest,applicationManager);
		formFactory.addRequestInputFormFields(registerLayout,  new BeanItem<>(reqisterRequest), RegisterUserRequest.class,
				Arrays.asList(new String[] {"username","email", "country", "userpassword" }),"Register",reqisterListener);

		layout.addComponent(registerLayout);

		setContent(layout);
	}

}
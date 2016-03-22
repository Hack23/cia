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
package com.hack23.cia.web.impl.ui.application.views.admin.datasummary;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.admin.RefreshDataViewsRequest;
import com.hack23.cia.service.api.action.admin.UpdateSearchIndexRequest;
import com.hack23.cia.web.impl.ui.application.views.admin.common.AbstractAdminView;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.tablefactory.TableFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class AdminDataSummaryView.
 */
@Service
@Scope(value="prototype")
@VaadinView(AdminDataSummaryView.NAME)
public final class AdminDataSummaryView extends AbstractAdminView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME;

	/** The table factory. */
	@Autowired
	private transient TableFactory tableFactory;

	/** The content. */
	private final VerticalLayout content = new VerticalLayout();

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;


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
		final Label createHeader2Label = LabelFactory.createHeader2Label("Admin Data Summary");
		content.addComponent(createHeader2Label);
		content.setExpandRatio(createHeader2Label, 1);


		final Table createDataSummaryTable = tableFactory.createDataSummaryTable();
		content.addComponent(createDataSummaryTable);
		content.setExpandRatio(createDataSummaryTable, 15);

		content.setSizeFull();
		content.setMargin(false);
		content.setSpacing(true);

		final Button refreshViewsButton = new Button("Refresh Views");

		refreshViewsButton.addClickListener(event -> {


			final RefreshDataViewsRequest serviceRequest = new RefreshDataViewsRequest();
			serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

			applicationManager.asyncService(serviceRequest);
			Notification.show("Refresh Views Started");
		});

		content.addComponent(refreshViewsButton);
		content.setExpandRatio(refreshViewsButton, 1);


		final Button updateSearchIndexButton = new Button("Update Search Index");

		updateSearchIndexButton.addClickListener(event -> {


			final UpdateSearchIndexRequest serviceRequest = new UpdateSearchIndexRequest();
			serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

			applicationManager.asyncService(serviceRequest);
			Notification.show("Update Search Index Started");
		});

		content.addComponent(updateSearchIndexButton);
		content.setExpandRatio(updateSearchIndexButton, 1);


	 	final Link createMainViewPageLink = pageLinkFactory.createMainViewPageLink();
		content.addComponent(createMainViewPageLink);
		content.setExpandRatio(createMainViewPageLink,1);

		content.setWidth(100, Unit.PERCENTAGE);
		content.setHeight(100, Unit.PERCENTAGE);
		setContent(content);
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
	}

	@Override
	//@Secured({ "ROLE_ADMIN" })
	public void enter(final ViewChangeEvent event) {
		createContent();
	}

}

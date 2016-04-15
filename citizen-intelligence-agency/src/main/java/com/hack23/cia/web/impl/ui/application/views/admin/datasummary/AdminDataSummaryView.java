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

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.admin.RefreshDataViewsRequest;
import com.hack23.cia.service.api.action.admin.UpdateSearchIndexRequest;
import com.hack23.cia.web.impl.ui.application.views.admin.common.AbstractAdminView;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
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
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(AdminDataSummaryView.NAME)
public final class AdminDataSummaryView extends AbstractAdminView {

	private static final String UPDATE_SEARCH_INDEX_STARTED = "Update Search Index Started";

	private static final String UPDATE_SEARCH_INDEX = "Update Search Index";

	private static final String REFRESH_VIEWS_STARTED = "Refresh Views Started";

	private static final String REFRESH_VIEWS = "Refresh Views";

	private static final String ADMIN_DATA_SUMMARY = "Admin Data Summary";

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
	private transient ApplicationManager applicationManager;

	private final transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;


	/**
	 * Instantiates a new admin data summary view.
	 *
	 * @param context
	 *            the context
	 */
	public AdminDataSummaryView(final ApplicationContext context) {
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
		final Label createHeader2Label = LabelFactory.createHeader2Label(ADMIN_DATA_SUMMARY);
		content.addComponent(createHeader2Label);
		content.setExpandRatio(createHeader2Label, ContentRatio.SMALL);


		final Table createDataSummaryTable = tableFactory.createDataSummaryTable();
		content.addComponent(createDataSummaryTable);
		content.setExpandRatio(createDataSummaryTable, ContentRatio.LARGE);

		content.setSizeFull();
		content.setMargin(false);
		content.setSpacing(true);

		final Button refreshViewsButton = new Button(REFRESH_VIEWS);

		refreshViewsButton.addClickListener(event -> {


			final RefreshDataViewsRequest serviceRequest = new RefreshDataViewsRequest();
			serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

			applicationManager.asyncService(serviceRequest);
			Notification.show(REFRESH_VIEWS_STARTED);
		});

		content.addComponent(refreshViewsButton);
		content.setExpandRatio(refreshViewsButton, ContentRatio.SMALL);


		final Button updateSearchIndexButton = new Button(UPDATE_SEARCH_INDEX);

		updateSearchIndexButton.addClickListener(event -> {


			final UpdateSearchIndexRequest serviceRequest = new UpdateSearchIndexRequest();
			serviceRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

			applicationManager.asyncService(serviceRequest);
			Notification.show(UPDATE_SEARCH_INDEX_STARTED);
		});

		content.addComponent(updateSearchIndexButton);
		content.setExpandRatio(updateSearchIndexButton, ContentRatio.SMALL);


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
	//@Secured({ "ROLE_ADMIN" })
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

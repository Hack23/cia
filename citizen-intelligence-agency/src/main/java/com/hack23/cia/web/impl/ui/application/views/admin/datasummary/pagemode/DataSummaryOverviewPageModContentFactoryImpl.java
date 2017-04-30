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
package com.hack23.cia.web.impl.ui.application.views.admin.datasummary.pagemode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.tablefactory.TableFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.RefreshDataViewsClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.UpdateSearchIndexClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class DataSummaryOverviewPageModContentFactoryImpl.
 */
@Component
public final class DataSummaryOverviewPageModContentFactoryImpl extends AbstractDataSummaryPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME;

	/** The Constant UPDATE_SEARCH_INDEX. */
	private static final String UPDATE_SEARCH_INDEX = "Update Search Index";

	/** The Constant REFRESH_VIEWS. */
	private static final String REFRESH_VIEWS = "Refresh Views";

	/** The Constant ADMIN_DATA_SUMMARY. */
	private static final String ADMIN_DATA_SUMMARY = "Admin Data Summary";

	/** The table factory. */
	@Autowired
	private TableFactory tableFactory;

	/**
	 * Instantiates a new data summary overview page mod content factory impl.
	 */
	public DataSummaryOverviewPageModContentFactoryImpl() {
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

		LabelFactory.createHeader2Label(content,ADMIN_DATA_SUMMARY);

		final Table createDataSummaryTable = tableFactory.createDataSummaryTable();
		content.addComponent(createDataSummaryTable);
		content.setExpandRatio(createDataSummaryTable, ContentRatio.LARGE);

		content.setSizeFull();
		content.setMargin(false);
		content.setSpacing(true);

		final Button refreshViewsButton = new Button(REFRESH_VIEWS,FontAwesome.REFRESH);

		refreshViewsButton.addClickListener(new RefreshDataViewsClickListener());

		content.addComponent(refreshViewsButton);
		content.setExpandRatio(refreshViewsButton, ContentRatio.SMALL);

		final Button updateSearchIndexButton = new Button(UPDATE_SEARCH_INDEX,FontAwesome.REFRESH);

		updateSearchIndexButton.addClickListener(new UpdateSearchIndexClickListener());

		content.addComponent(updateSearchIndexButton);
		content.setExpandRatio(updateSearchIndexButton, ContentRatio.SMALL);

		return content;

	}

}

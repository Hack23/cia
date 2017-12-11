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

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.DataSummary;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.RefreshDataViewsClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.RemoveDataClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.UpdateSearchIndexClickListener;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DataSummaryOverviewPageModContentFactoryImpl.
 */
@Component
public final class DataSummaryOverviewPageModContentFactoryImpl extends AbstractDataSummaryPageModContentFactoryImpl {

	/** The Constant REMOVE_DOCUMENTS. */
	private static final String REMOVE_DOCUMENTS = "Remove Documents";

	/** The Constant REMOVE_POLITICIANS. */
	private static final String REMOVE_POLITICIANS = "Remove Politicians";

	/** The Constant REFRESH_ALL_VIEWS. */
	private static final String REFRESH_ALL_VIEWS = "Refresh all views";

	/** The Constant UPDATE_DOCUMENT_SEARCH_INDEX. */
	private static final String UPDATE_DOCUMENT_SEARCH_INDEX = "Update document search index";

	/** The Constant REMOVE_APPLICATION_HISTORY. */
	private static final String REMOVE_APPLICATION_HISTORY = "Remove Application History";

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME;

	/** The Constant UPDATE_SEARCH_INDEX. */
	private static final String UPDATE_SEARCH_INDEX = "Update Search Index";

	/** The Constant REFRESH_VIEWS. */
	private static final String REFRESH_VIEWS = "Refresh Views";

	/** The Constant ADMIN_DATA_SUMMARY. */
	private static final String ADMIN_DATA_SUMMARY = "Admin Data Summary";

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

		LabelFactory.createHeader2Label(content, ADMIN_DATA_SUMMARY);

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();

		content.addComponent(horizontalLayout);
		content.setExpandRatio(horizontalLayout, ContentRatio.LARGE);

		final DataContainer<DataSummary, String> dataContainer = getApplicationManager()
				.getDataContainer(DataSummary.class);

		final List<DataSummary> all = dataContainer.getAll();
		if (!all.isEmpty()) {
			final DataSummary dataSummary = all.get(0);

			getFormFactory().addFormPanelTextFields(horizontalLayout, dataSummary, DataSummary.class,
					Arrays.asList( "personSize", "totalBallotVotes", "committeeProposalSize", "voteSize",
							"documentStatusSize", "documentElementSize", "documentContentSize" ));
		}

		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		content.addComponent(overviewLayout);
		content.setExpandRatio(overviewLayout, ContentRatio.LARGE);

		final ResponsiveRow grid = createGridLayout(overviewLayout);

		final Button refreshViewsButton = new Button(REFRESH_VIEWS, VaadinIcons.REFRESH);
		refreshViewsButton.addClickListener(new RefreshDataViewsClickListener());
		createRowItem(grid, refreshViewsButton, REFRESH_ALL_VIEWS);

		final Button updateSearchIndexButton = new Button(UPDATE_SEARCH_INDEX, VaadinIcons.REFRESH);
		updateSearchIndexButton.addClickListener(new UpdateSearchIndexClickListener());
		createRowItem(grid, updateSearchIndexButton, UPDATE_DOCUMENT_SEARCH_INDEX);

		final Button removeDataButton = new Button(REMOVE_POLITICIANS, VaadinIcons.DEL);
		removeDataButton.addClickListener(new RemoveDataClickListener(RemoveDataRequest.DataType.POLITICIAN));
		createRowItem(grid, removeDataButton, REMOVE_POLITICIANS);

		final Button removeDocumentsButton = new Button(REMOVE_DOCUMENTS, VaadinIcons.DEL);
		removeDocumentsButton.addClickListener(new RemoveDataClickListener(RemoveDataRequest.DataType.DOCUMENTS));
		createRowItem(grid, removeDocumentsButton, REMOVE_DOCUMENTS);

		final Button removeApplicationHistoryButton = new Button(REMOVE_APPLICATION_HISTORY, VaadinIcons.DEL);
		removeApplicationHistoryButton
				.addClickListener(new RemoveDataClickListener(RemoveDataRequest.DataType.APPLICATION_HISTORY));
		createRowItem(grid, removeApplicationHistoryButton, REMOVE_APPLICATION_HISTORY);

		return content;

	}

}

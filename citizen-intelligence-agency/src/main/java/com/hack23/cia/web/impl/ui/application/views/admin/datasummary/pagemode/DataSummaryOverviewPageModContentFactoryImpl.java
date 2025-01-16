/*
 * Copyright 2010-2025 James Pether Sörling
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

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.data.audit.impl.ViewAuditDataSummary;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.admin.RefreshDataViewsRequest;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest;
import com.hack23.cia.service.api.action.admin.UpdateSearchIndexRequest;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
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

	/** The Constant ADMIN_DATA_SUMMARY. */
	private static final String ADMIN_DATA_SUMMARY = "Admin Data Summary";

	private static final String[] COLUMN_ORDER = { "dataType", "dataSize" };

	private static final String[] HIDE_COLUMNS = { "id" };

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME;

	/** The Constant REFRESH_ALL_VIEWS. */
	private static final String REFRESH_ALL_VIEWS = "Refresh all views";

	/** The Constant REFRESH_VIEWS. */
	private static final String REFRESH_VIEWS = "Refresh Views";

	/** The Constant REMOVE_APPLICATION_HISTORY. */
	private static final String REMOVE_APPLICATION_HISTORY = "Remove Application History";

	/** The Constant REMOVE_DOCUMENTS. */
	private static final String REMOVE_DOCUMENTS = "Remove Documents";

	/** The Constant REMOVE_POLITICIANS. */
	private static final String REMOVE_POLITICIANS = "Remove Politicians";

	/** The Constant UPDATE_DOCUMENT_SEARCH_INDEX. */
	private static final String UPDATE_DOCUMENT_SEARCH_INDEX = "Update document search index";

	/** The Constant UPDATE_SEARCH_INDEX. */
	private static final String UPDATE_SEARCH_INDEX = "Update Search Index";

	/**
	 * Instantiates a new data summary overview page mod content factory impl.
	 */
	public DataSummaryOverviewPageModContentFactoryImpl() {
		super("DataSummaryOverview");
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		CardInfoRowUtil.createPageHeader(panel, content, AdminViewConstants.ADMIN_DATA_SUMMARY_OVERVIEW, AdminViewConstants.DATA_SUMMARY_OVERVIEW, AdminViewConstants.DATA_SUMMARY_OVERVIEW);

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();
		horizontalLayout.addStyleName("v-layout-content-overview-panel-level1");

		content.addComponent(horizontalLayout);
		content.setExpandRatio(horizontalLayout, ContentRatio.LARGE);

		final DataContainer<ViewAuditDataSummary, Long> dataContainer = getApplicationManager()
					.getDataContainer(ViewAuditDataSummary.class);

		getGridFactory().createBasicBeanItemNestedPropertiesGrid(horizontalLayout, ViewAuditDataSummary.class,
				dataContainer.getAll(), ADMIN_DATA_SUMMARY, null, COLUMN_ORDER, HIDE_COLUMNS, null, null, null);

		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		overviewLayout.addStyleName("v-layout-content-overview-panel-level2");

		content.addComponent(overviewLayout);
		content.setExpandRatio(overviewLayout, ContentRatio.LARGE);

		final ResponsiveRow grid = RowUtil.createGridLayout(overviewLayout);

		final RefreshDataViewsRequest refreshRequest = new RefreshDataViewsRequest();
		refreshRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		final Button refreshViewsButton = new Button(REFRESH_VIEWS, VaadinIcons.REFRESH);
		refreshViewsButton.addClickListener(new RefreshDataViewsClickListener(refreshRequest));
		RowUtil.createRowItem(grid, refreshViewsButton, REFRESH_ALL_VIEWS);

		final Button updateSearchIndexButton = new Button(UPDATE_SEARCH_INDEX, VaadinIcons.REFRESH);

		final UpdateSearchIndexRequest updateIndexRequest = new UpdateSearchIndexRequest();
		updateIndexRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());

		updateSearchIndexButton.addClickListener(new UpdateSearchIndexClickListener(updateIndexRequest));
		RowUtil.createRowItem(grid, updateSearchIndexButton, UPDATE_DOCUMENT_SEARCH_INDEX);

		final Button removeDataButton = new Button(REMOVE_POLITICIANS, VaadinIcons.DEL);

		final RemoveDataRequest removePoliticianRequest = new RemoveDataRequest();
		removePoliticianRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		removePoliticianRequest.setDataType(RemoveDataRequest.DataType.POLITICIAN);

		removeDataButton.addClickListener(new RemoveDataClickListener(removePoliticianRequest));
		RowUtil.createRowItem(grid, removeDataButton, REMOVE_POLITICIANS);

		final RemoveDataRequest removeDocumentsRequest = new RemoveDataRequest();
		removeDocumentsRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		removeDocumentsRequest.setDataType(RemoveDataRequest.DataType.DOCUMENTS);

		final Button removeDocumentsButton = new Button(REMOVE_DOCUMENTS, VaadinIcons.DEL);
		removeDocumentsButton.addClickListener(new RemoveDataClickListener(removeDocumentsRequest));
		RowUtil.createRowItem(grid, removeDocumentsButton, REMOVE_DOCUMENTS);

		final RemoveDataRequest removeApplicationHistoryRequest = new RemoveDataRequest();
		removeApplicationHistoryRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		removeApplicationHistoryRequest.setDataType(RemoveDataRequest.DataType.APPLICATION_HISTORY);

		final Button removeApplicationHistoryButton = new Button(REMOVE_APPLICATION_HISTORY, VaadinIcons.DEL);
		removeApplicationHistoryButton.addClickListener(new RemoveDataClickListener(removeApplicationHistoryRequest));
		RowUtil.createRowItem(grid, removeApplicationHistoryButton, REMOVE_APPLICATION_HISTORY);

		return content;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page)
				&& (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

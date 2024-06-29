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
package com.hack23.cia.web.impl.ui.application.views.admin.datasummary.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.audit.impl.ViewAuditAuthorSummary;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DataSummaryPageMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DataSummaryAuthorPageModContentFactoryImpl.
 */
@Component
public final class DataSummaryAuthorPageModContentFactoryImpl extends AbstractDataSummaryPageModContentFactoryImpl {

	/** The Constant ADMIN_AUTHOR_SUMMARY. */
	private static final String ADMIN_AUTHOR_SUMMARY = "Admin Author Summary";

	private static final String[] COLUMN_ORDER = { "author", "changes", "firstDate", "lastDate" };

	private static final String[] HIDE_COLUMNS = { "id" };

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME;

	/**
	 * Instantiates a new data summary author page mod content factory impl.
	 */
	public DataSummaryAuthorPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		LabelFactory.createHeader2Label(content, ADMIN_AUTHOR_SUMMARY);

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();

		content.addComponent(horizontalLayout);
		content.setExpandRatio(horizontalLayout, ContentRatio.LARGE);

		final DataContainer<ViewAuditAuthorSummary, Long> dataContainer = getApplicationManager()
				.getDataContainer(ViewAuditAuthorSummary.class);

		getGridFactory()
		.createBasicBeanItemNestedPropertiesGrid(horizontalLayout, ViewAuditAuthorSummary.class, dataContainer.getAll(),
				ADMIN_AUTHOR_SUMMARY,null,
				COLUMN_ORDER, HIDE_COLUMNS,
				null, null, null);

		return content;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(DataSummaryPageMode.AUTHORS.toString());
	}

}

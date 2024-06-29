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
package com.hack23.cia.web.impl.ui.application.views.pageclicklisteners.handlers.impl;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.service.api.action.user.SearchDocumentResponse;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklisteners.handlers.api.SearchDocumentResponseHandler;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class SearchDocumentResponseHandlerImpl.
 */
public final class SearchDocumentResponseHandlerImpl implements SearchDocumentResponseHandler {

	/** The Constant DOCUMENT. */
	private static final String DOCUMENT = "Document";

	/** The Constant DOCUMENT_GRID_COLUMN_ORDER. */
	private static final String[] DOCUMENT_GRID_COLUMN_ORDER = { "rm", "createdDate", "documentName", "subType", "title", "subTitle", "status" };

	/** The Constant DOCUMENT_GRID_HIDE_COLUMNS. */
	private static final String[] DOCUMENT_GRID_HIDE_COLUMNS = { "rm", "lang", "noteTitle", "origin", "subType","note", "subTitle", "status", "label", "id", "hit", "madePublicDate", "databaseSource", "domainOrg", "relatedId",
			"org", "documentType","docType", "debateName", "tempLabel", "numberValue", "systemDate", "kallId",
			"documentFormat", "documentUrlText", "documentUrlHtml", "documentStatusUrlXml",
			"committeeReportUrlXml" };

	/** The Constant DOCUMENT_LISTENER. */
	private static final PageItemPropertyClickListener DOCUMENT_LISTENER = new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME, "id");

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The form panel. */
	private final Panel formPanel;

	/** The grid factory. */
	private final GridFactory gridFactory;

	/** The searchresult layout. */
	private final VerticalLayout searchresultLayout;

	/**
	 * Instantiates a new search document response handler impl.
	 *
	 * @param gridFactory
	 *            the grid factory
	 * @param formPanel
	 *            the form panel
	 * @param searchresultLayout
	 *            the searchresult layout
	 */
	public SearchDocumentResponseHandlerImpl(final GridFactory gridFactory, final Panel formPanel, final VerticalLayout searchresultLayout) {
		this.formPanel = formPanel;
		this.searchresultLayout = searchresultLayout;
		this.gridFactory = gridFactory;
	}

	@Override
	public void handle(final SearchDocumentResponse response) {
		searchresultLayout.removeAllComponents();

		searchresultLayout.addComponent(formPanel);
		searchresultLayout.setExpandRatio(formPanel, ContentRatio.SMALL3);

		gridFactory.createBasicBeanItemGrid(searchresultLayout, DocumentElement.class, response.getResultElement(), DOCUMENT,
				DOCUMENT_GRID_COLUMN_ORDER,
				DOCUMENT_GRID_HIDE_COLUMNS,
				DOCUMENT_LISTENER, null, null);

	}
}
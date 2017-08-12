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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.service.api.action.user.SearchDocumentResponse;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class SearchDocumentResponseHandlerImpl.
 */
public final class SearchDocumentResponseHandlerImpl implements SearchDocumentResponseHandler {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The searchresult layout. */
	private final VerticalLayout searchresultLayout;

	/** The form panel. */
	private final Panel formPanel;

	/** The grid factory. */
	private final GridFactory gridFactory;

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

		final BeanItemContainer<DocumentElement> documentActivityDataDataDataSource = new BeanItemContainer<>(
				DocumentElement.class, response.getResultElement());

		gridFactory.createBasicBeanItemGrid(searchresultLayout, documentActivityDataDataDataSource, "Document",
				new String[] { "rm", "createdDate", "documentName", "subType", "title", "subTitle", "status" },
				new String[] { "rm", "lang", "noteTitle", "origin", "subType","note", "subTitle", "status", "label", "id", "hit", "madePublicDate", "databaseSource", "domainOrg", "relatedId",
						"org", "documentType","docType", "debateName", "tempLabel", "numberValue", "systemDate", "kallId",
						"documentFormat", "documentUrlText", "documentUrlHtml", "documentStatusUrlXml",
						"committeeReportUrlXml" },
				new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME, "id"), null, null);

	}
}
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
package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentReferenceData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DocumentReferencesPageModContentFactoryImpl.
 */
@Component
public final class DocumentReferencesPageModContentFactoryImpl extends AbstractDocumentPageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = { "referenceType", "referenceDocumentId", "detail" };
	private static final String DOCUMENT_REFERENCES2 = "Document references";
	private static final String[] HIDE_COLUMNS = { "hjid" };

	/**
	 * Instantiates a new document references page mod content factory impl.
	 */
	public DocumentReferencesPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		getDocumentMenuItemFactory().createDocumentMenuBar(menuBar, pageId);

		final DataContainer<DocumentStatusContainer, String> documentStatusContainerDataContainer = getApplicationManager()
				.getDataContainer(DocumentStatusContainer.class);

		final DocumentStatusContainer documentStatusContainer = documentStatusContainerDataContainer
				.findByQueryProperty(DocumentStatusContainer.class, DocumentStatusContainer_.document,
						DocumentData.class, DocumentData_.id, pageId);

		createPageHeader(panel, panelContent, "Document References", "References Overview", "Analyze and review references within the document, including cross-references.");

		if (documentStatusContainer != null && documentStatusContainer.getDocumentReferenceContainer() != null
				&& documentStatusContainer.getDocumentReferenceContainer().getDocumentReferenceList() != null) {

			getGridFactory().createBasicBeanItemGrid(panelContent, DocumentReferenceData.class,
					documentStatusContainer.getDocumentReferenceContainer().getDocumentReferenceList(),
					DOCUMENT_REFERENCES2, COLUMN_ORDER, HIDE_COLUMNS, null, null, null);
		}

		panel.setContent(panelContent);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, DocumentPageMode.DOCUMENTREFERENCES.toString());
	}

}

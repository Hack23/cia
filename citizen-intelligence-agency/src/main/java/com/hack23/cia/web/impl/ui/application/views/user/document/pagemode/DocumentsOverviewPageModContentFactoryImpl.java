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
package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class DocumentOverviewPageModContentFactoryImpl.
 */
@Component
public final class DocumentsOverviewPageModContentFactoryImpl extends AbstractDocumentsPageModContentFactoryImpl {

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";

	/**
	 * Instantiates a new documents overview page mod content factory impl.
	 */
	public DocumentsOverviewPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page);
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);
		final int pageNr= getPageNr(parameters);

		getDocumentMenuItemFactory().createDocumentsMenuBar(menuBar);

		LabelFactory.createHeader2Label(panelContent,OVERVIEW);

		final DataContainer<DocumentElement, String> documentElementDataContainer = getApplicationManager()
				.getDataContainer(DocumentElement.class);

		final BeanItemContainer<DocumentElement> documentActivityDataDataDataSource = new BeanItemContainer<>(DocumentElement.class,
				documentElementDataContainer.getPageOrderBy(pageNr,DEFAULT_RESULTS_PER_PAGE, DocumentElement_.createdDate));

		createPagingControls(panelContent,NAME,pageId, documentElementDataContainer.getSize(), pageNr, DEFAULT_RESULTS_PER_PAGE);

		getGridFactory().createBasicBeanItemGrid(panelContent, documentActivityDataDataDataSource, "Document",
				new String[] { "rm", "createdDate", "documentName", "subType", "title", "subTitle", "status" },
				new String[] { "rm", "lang", "noteTitle", "origin", "subType", "note", "subTitle", "status", "label",
						"id", "hit", "madePublicDate", "databaseSource", "domainOrg", "relatedId", "org",
						"documentType", "docType", "debateName", "tempLabel", "numberValue", "systemDate", "kallId",
						"documentFormat", "documentUrlText", "documentUrlHtml", "documentStatusUrlXml",
						"committeeReportUrlXml" },
				new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME, "id"), null, null);

		panel.setContent(panelContent);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENTS_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

}

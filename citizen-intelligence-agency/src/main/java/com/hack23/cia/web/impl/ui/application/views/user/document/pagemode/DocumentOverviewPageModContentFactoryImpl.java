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
package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DocumentOverviewPageModContentFactoryImpl.
 */
@Component
public final class DocumentOverviewPageModContentFactoryImpl extends AbstractDocumentPageModContentFactoryImpl {

	/**
	 * Instantiates a new document overview page mod content factory impl.
	 */
	public DocumentOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DocumentElement documentElement = getItem(parameters);
		getDocumentMenuItemFactory().createDocumentMenuBar(menuBar, pageId);

		final DataContainer<DocumentStatusContainer, String> documentStatusContainerDataContainer = getApplicationManager()
				.getDataContainer(DocumentStatusContainer.class);

		final DocumentStatusContainer documentStatusContainer = documentStatusContainerDataContainer
				.findByQueryProperty(DocumentStatusContainer.class, DocumentStatusContainer_.document,
						DocumentData.class, DocumentData_.id, pageId);

		createPageHeader(panel, panelContent,
				"Document Overview " + documentElement.getTitle() + " " + documentElement.getSubTitle(),
				"Document Details", "Access and explore official documents and reports.");

		// Create a card panel for document info
		final Panel cardPanel = createCardPanel("Document Information");
		final VerticalLayout cardContent = (VerticalLayout) cardPanel.getContent();

		panelContent.addComponent(cardPanel);
		panelContent.setExpandRatio(cardPanel, ContentRatio.SMALL_GRID);

		// Two-column layout
		final HorizontalLayout attributesLayout = new HorizontalLayout();
		attributesLayout.setSpacing(true);
		attributesLayout.setWidth("100%");
		cardContent.addComponent(attributesLayout);

		// Left column: Document Profile (from DocumentElement)
		final VerticalLayout profileLayout = new VerticalLayout();
		profileLayout.setSpacing(true);
		profileLayout.addStyleName("card-details-column");
		profileLayout.setWidthUndefined();

		final com.vaadin.ui.Label profileHeader = new com.vaadin.ui.Label("Document Profile");
		profileHeader.addStyleName("card-section-title");
		profileLayout.addComponent(profileHeader);

		// Display a selection of DocumentElement fields
		// Choose key fields: title, subTitle, org, documentType, status, rm,
		// madePublicDate, createdDate
		addInfoRowsToLayout(profileLayout,
				new InfoRowItem("Title:", documentElement.getTitle(), VaadinIcons.FILE_TEXT_O),
				new InfoRowItem("SubTitle:", documentElement.getSubTitle(), VaadinIcons.FILE_TEXT),
				new InfoRowItem("Organization (Org):", documentElement.getOrg(), VaadinIcons.INSTITUTION),
				new InfoRowItem("Document Type:", documentElement.getDocumentType(), VaadinIcons.FILE_CODE),
				new InfoRowItem("Status:", documentElement.getStatus(), VaadinIcons.QUESTION_CIRCLE),
				new InfoRowItem("Made Public Date:", String.valueOf(documentElement.getMadePublicDate()), VaadinIcons.CALENDAR_USER));

		// Right column: Metadata & Status (from DocumentStatusContainer and
		// DocumentData)
		final VerticalLayout metadataLayout = new VerticalLayout();
		metadataLayout.setSpacing(true);
		metadataLayout.addStyleName("card-details-column");
		metadataLayout.setWidthUndefined();

		final com.vaadin.ui.Label metadataHeader = new com.vaadin.ui.Label("Metadata & Status");
		metadataHeader.addStyleName("card-section-title");
		metadataLayout.addComponent(metadataHeader);

		if (documentStatusContainer != null) {
			// DocumentCategory (from DocumentStatusContainer)
			addInfoRowsToLayout(metadataLayout,
					new InfoRowItem("Document Category:", documentStatusContainer.getDocumentCategory(), VaadinIcons.BOOK));

			// DocumentData fields (AS_LIST2)
			final DocumentData documentData = documentStatusContainer.getDocument();
			if (documentData != null) {
				// Choose a few key fields from DocumentData
				addInfoRowsToLayout(metadataLayout,
						new InfoRowItem("Label:", documentData.getLabel(), VaadinIcons.TAG),
						new InfoRowItem("Temp Label:", documentData.getTempLabel(), VaadinIcons.EDIT),
						new InfoRowItem("Hangar ID:", documentData.getHangarId(), VaadinIcons.CLIPBOARD),
						new InfoRowItem("Number Value:", String.valueOf(documentData.getNumberValue()), VaadinIcons.BAR_CHART));
			}
		}

		attributesLayout.addComponents(profileLayout, metadataLayout);

		// After the card, add the overview layout
		final VerticalLayout overviewLayout = createOverviewLayout();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getDocumentMenuItemFactory().createOverviewPage(overviewLayout, pageId);

		panel.setContent(panelContent);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

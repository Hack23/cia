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
import com.vaadin.server.Responsive;
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
		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("politician-overview-card");
		cardPanel.setWidth("100%");
		cardPanel.setHeightUndefined();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setWidth("100%");
		cardPanel.setContent(cardContent);

		panelContent.addComponent(cardPanel);
		panelContent.setExpandRatio(cardPanel, ContentRatio.SMALL_GRID);

        createCardHeader(cardContent,"Document Information");

		// Two-column layout
		final HorizontalLayout attributesLayout = new HorizontalLayout();
		attributesLayout.setSpacing(true);
		attributesLayout.setWidth("100%");
		cardContent.addComponent(attributesLayout);

		// Left column: Document Profile (from DocumentElement)
		final VerticalLayout profileLayout = createSectionLayout("Document Profile");

		// Display a selection of DocumentElement fields
		// Choose key fields: title, subTitle, org, documentType, status, rm,
		// madePublicDate, createdDate
		profileLayout.addComponent(createInfoRow("Title:", documentElement.getTitle(), VaadinIcons.FILE_TEXT_O,
				"Main title of the document"));
		if (!StringUtils.isEmpty(documentElement.getSubTitle())) {
			profileLayout.addComponent(createInfoRow("SubTitle:", documentElement.getSubTitle(), VaadinIcons.FILE_TEXT,
					"Subtitle or complementary title"));
		}
		profileLayout.addComponent(createInfoRow("Organization (Org):", documentElement.getOrg(),
				VaadinIcons.INSTITUTION, "Originating organization or entity"));
		profileLayout.addComponent(createInfoRow("Document Type:", documentElement.getDocumentType(),
				VaadinIcons.FILE_CODE, "Type of the document"));
		profileLayout.addComponent(createInfoRow("Status:", documentElement.getStatus(), VaadinIcons.QUESTION_CIRCLE,
				"Current status of the document"));
		profileLayout
				.addComponent(createInfoRow("Made Public Date:", String.valueOf(documentElement.getMadePublicDate()),
						VaadinIcons.CALENDAR_USER, "Date when the document was made public"));

		// Right column: Metadata & Status (from DocumentStatusContainer and
		// DocumentData)
		final VerticalLayout metadataLayout = createSectionLayout("Metadata & Status");

		if (documentStatusContainer != null) {
			// DocumentCategory (from DocumentStatusContainer)
			if (!StringUtils.isEmpty(documentStatusContainer.getDocumentCategory())) {
				metadataLayout.addComponent(createInfoRow("Document Category:",
						documentStatusContainer.getDocumentCategory(), VaadinIcons.BOOK, "Category of this document"));
			}

			// DocumentData fields (AS_LIST2)
			final DocumentData documentData = documentStatusContainer.getDocument();
			if (documentData != null) {
				// Choose a few key fields from DocumentData
				if (!StringUtils.isEmpty(documentData.getLabel())) {
					metadataLayout.addComponent(createInfoRow("Label:", documentData.getLabel(), VaadinIcons.TAG,
							"Short identifying label"));
				}
				if (!StringUtils.isEmpty(documentData.getTempLabel())) {
					metadataLayout.addComponent(createInfoRow("Temp Label:", documentData.getTempLabel(),
							VaadinIcons.EDIT, "Temporary label or note"));
				}
				if (!StringUtils.isEmpty(documentData.getHangarId())) {
					metadataLayout.addComponent(createInfoRow("Hangar ID:", documentData.getHangarId(),
							VaadinIcons.CLIPBOARD, "Storage or reference ID"));
				}
				if (documentData.getNumberValue() != null) {
					metadataLayout
							.addComponent(createInfoRow("Number Value:", String.valueOf(documentData.getNumberValue()),
									VaadinIcons.BAR_CHART, "Numeric value associated with the document"));
				}
			}
		}

		attributesLayout.addComponents(profileLayout, metadataLayout);

		// After the card, add the overview layout
		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
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

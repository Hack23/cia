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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentAttachment;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.impl.ExternalAttachmentDownloadLink;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.impl.StreamSourceImplementation;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.whitestein.vaadin.widgets.wtpdfviewer.WTPdfViewer;

/**
 * The Class DocumentAttachementsPageModContentFactoryImpl.
 */
@Component
public final class DocumentAttachementsPageModContentFactoryImpl extends AbstractDocumentPageModContentFactoryImpl {

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "fileName", "fileSize", "fileType", "fileUrl" };

	/** The Constant DOCUMENT_ATTACHMENTS. */
	private static final String DOCUMENT_ATTACHMENTS = "Document Attachments";

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "hjid" };

	/** The Constant PDF. */
	private static final String PDF = "pdf";

	/**
	 * Instantiates a new document attachements page mod content factory impl.
	 */
	public DocumentAttachementsPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Display document attachements.
	 *
	 * @param panelContent the panel content
	 * @param documentAttachmentList the document attachment list
	 */
	private static void displayDocumentAttachements(final VerticalLayout panelContent,
			final List<DocumentAttachment> documentAttachmentList) {
		for (final DocumentAttachment documentAttachment : documentAttachmentList) {

			if (PDF.equalsIgnoreCase(documentAttachment.getFileType())) {
				final WTPdfViewer wtPdfViewer = new WTPdfViewer();
				wtPdfViewer.setSizeFull();

				wtPdfViewer.setResource(new StreamResource(new StreamSourceImplementation(documentAttachment.getFileUrl()), documentAttachment.getFileName()));

				panelContent.addComponent(wtPdfViewer);
				panelContent.setExpandRatio(wtPdfViewer, ContentRatio.LARGE);
			} else {
				final VerticalLayout verticalLayout = new VerticalLayout();
				panelContent.addComponent(verticalLayout);
				panelContent.setExpandRatio(verticalLayout, ContentRatio.SMALL);
				final ExternalAttachmentDownloadLink link = new ExternalAttachmentDownloadLink(
						documentAttachment.getFileName(), documentAttachment.getFileType(),
						documentAttachment.getFileUrl());
				verticalLayout.addComponent(link);
			}
		}
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

		CardInfoRowUtil.createPageHeader(panel, panelContent, "Document Attachments", "Attachments Overview", "Explore and manage attachments associated with the document.");

		if (documentStatusContainer != null && documentStatusContainer.getDocumentAttachmentContainer() != null
				&& documentStatusContainer.getDocumentAttachmentContainer().getDocumentAttachmentList() != null) {

			getGridFactory().createBasicBeanItemGrid(panelContent, DocumentAttachment.class,
					documentStatusContainer.getDocumentAttachmentContainer().getDocumentAttachmentList(),
					DOCUMENT_ATTACHMENTS, COLUMN_ORDER, HIDE_COLUMNS, null, null, null);

			displayDocumentAttachements(panelContent,
					documentStatusContainer.getDocumentAttachmentContainer().getDocumentAttachmentList());
		}

		panel.setContent(panelContent);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, DocumentPageMode.DOCUMENTATTACHMENTS.toString());
	}

}

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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.DocumentMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandDocumentConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DocumentMenuItemFactoryImpl.
 */
@Service
public final class DocumentMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements DocumentMenuItemFactory {

	/** The application menu item factory. */
	@Autowired
	private ApplicationMenuItemFactory applicationMenuItemFactory;

	/**
	 * Instantiates a new document menu item factory impl.
	 */
	public DocumentMenuItemFactoryImpl() {
		super();
	}

	@Override
	public void createDocumentMenuBar(final MenuBar menuBar, final String pageId) {
		createDocumentsMenuBar(menuBar);

		final MenuItem documentItem = menuBar.addItem(DOCUMENT, VaadinIcons.FILE_TEXT, null);

		documentItem.addItem(DOCUMENT_OVERVIEW_TEXT, VaadinIcons.FILE_TEXT,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_OVERVIEW);

		documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_PROCESS,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_ACTIVITY);

		documentItem.addItem(PERSON_REFERENCES, VaadinIcons.USER,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_PERSON_REFERENCES);

		documentItem.addItem(DOCUMENT_DETAILS, VaadinIcons.FILE_TEXT,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_DETAILS);

		documentItem.addItem(DOCUMENT_DATA, VaadinIcons.FILE_TEXT,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_DATA);

		documentItem.addItem(DOCUMENT_REFERENCES, VaadinIcons.FILE_TREE,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_REFERENCES);

		documentItem.addItem(DOCUMENT_DECISION, VaadinIcons.CLIPBOARD_CHECK,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_DECISION);

		documentItem.addItem(DOCUMENT_ATTACHEMENTS, VaadinIcons.PAPERCLIP,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_ATTACHMENTS);

		documentItem.addItem(DOCUMENT_PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CLOCK,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_PAGEVISIT_HISTORY);

	}

	@Override
	public void createDocumentsMenuBar(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);
		final MenuItem documentsItem = menuBar.addItem(DOCUMENTS, VaadinIcons.FILE_TEXT, null);
		documentsItem.addItem(LIST_ALL, VaadinIcons.FILE_TEXT, COMMAND_DOCUMENTS_OVERVIEW);
		documentsItem.addItem(SEARCH_DOCUMENTS, VaadinIcons.SEARCH, COMMAND_SEARCH_DOCUMENT);

	}

	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid, DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE_PROCESS,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_ACTIVITY,
				DOCUMENT_ACTIVITIES_AND_UPDATES);

		createButtonLink(grid, PERSON_REFERENCES, VaadinIcons.USER,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_PERSON_REFERENCES,
				REFERENCES_TO_INDIVIDUALS_IN_THE_DOCUMENT);

		createButtonLink(grid, DOCUMENT_DETAILS, VaadinIcons.FILE_TEXT,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_DETAILS,
				DETAILED_INFORMATION_ABOUT_THE_DOCUMENT);

		createButtonLink(grid, DOCUMENT_DATA, VaadinIcons.FILE_TEXT,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_DATA,
				COMPLETE_DOCUMENT_TEXT_AND_DATA);

		createButtonLink(grid, DOCUMENT_REFERENCES, VaadinIcons.FILE_TREE,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_REFERENCES,
				REFERENCES_CITED_IN_THE_DOCUMENT);

		createButtonLink(grid, DOCUMENT_DECISION, VaadinIcons.CLIPBOARD_CHECK,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_DECISION,
				DECISIONS_AND_OUTCOMES_RELATED_TO_THE_DOCUMENT);

		createButtonLink(grid, DOCUMENT_ATTACHEMENTS, VaadinIcons.PAPERCLIP,
				PageCommandDocumentConstants.COMMAND_DOCUMENT_ATTACHMENTS,
				ATTACHMENTS_AND_SUPPLEMENTARY_FILES);
	}

}

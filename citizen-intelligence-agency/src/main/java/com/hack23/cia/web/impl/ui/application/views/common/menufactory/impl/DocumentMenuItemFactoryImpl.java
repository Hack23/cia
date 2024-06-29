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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.DocumentMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
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

	/** The Constant ATTACHEMENTS. */
	private static final String ATTACHEMENTS = "Attachements";

	/** The Constant COMMAND_DOCUMENTS. */
	private static final PageModeMenuCommand COMMAND_DOCUMENTS = new PageModeMenuCommand(UserViews.DOCUMENTS_VIEW_NAME,PageMode.OVERVIEW);

	/** The Constant COMMAND_SEARCH_DOCUMENT. */
	private static final PageModeMenuCommand COMMAND_SEARCH_DOCUMENT = new PageModeMenuCommand(UserViews.SEARCH_DOCUMENT_VIEW_NAME,"");

	/** The Constant COMPLETE_DOCUMENT_AS_TEXT. */
	private static final String COMPLETE_DOCUMENT_AS_TEXT = "Complete document as text";

	/** The Constant DOCUMENT. */
	private static final String DOCUMENT = "Document";

	/** The Constant DOCUMENT_ACTIVITIES. */
	private static final String DOCUMENT_ACTIVITIES = "Document activities";

	/** The Constant DOCUMENT_ACTIVITY_TEXT. */
	private static final String DOCUMENT_ACTIVITY_TEXT = "Document Activity";

	/** The Constant DOCUMENT_ATTACHEMENTS. */
	private static final String DOCUMENT_ATTACHEMENTS = "Document Attachements";

	/** The Constant DOCUMENT_DATA. */
	private static final String DOCUMENT_DATA = "Document data";

	/** The Constant DOCUMENT_DECISION. */
	private static final String DOCUMENT_DECISION = "Document Decision";


	/** The Constant DOCUMENT_DECISIONS. */
	private static final String DOCUMENT_DECISIONS = "Document decisions";

	/** The Constant DOCUMENT_DETAILS. */
	private static final String DOCUMENT_DETAILS = "Document details";

	/** The Constant DOCUMENT_REFERENCES. */
	private static final String DOCUMENT_REFERENCES = "Document References";

	/** The Constant DOCUMENTS. */
	private static final String DOCUMENTS = "Documents";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The Constant PERSON_REFERENCES. */
	private static final String PERSON_REFERENCES = "Person references";

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

			final MenuItem documentItem = menuBar.addItem(DOCUMENT, VaadinIcons.FILE, null);

			documentItem.addItem(OVERVIEW_TEXT, VaadinIcons.FILE,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW, pageId));

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTACTIVITY.toString(), pageId));

			documentItem.addItem(PERSON_REFERENCES, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.PERSONREFERENCES.toString(), pageId));

			documentItem.addItem(DOCUMENT_DETAILS, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTDETAILS.toString(), pageId));

			documentItem.addItem(DOCUMENT_DATA, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTDATA.toString(), pageId));

			documentItem.addItem(DOCUMENT_REFERENCES, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTREFERENCES.toString(), pageId));

			documentItem.addItem(DOCUMENT_DECISION, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTDECISION.toString(), pageId));

			documentItem.addItem(DOCUMENT_ATTACHEMENTS, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTATTACHMENTS.toString(), pageId));


			documentItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.PAGEVISITHISTORY,pageId));

	}


	@Override
	public void createDocumentsMenuBar(final MenuBar menuBar) {
		initApplicationMenuBar(menuBar);

		applicationMenuItemFactory.addRankingMenu(menuBar);
		final MenuItem documentsItem = menuBar.addItem(DOCUMENTS, VaadinIcons.FILE, null);
		documentsItem.addItem("List all",VaadinIcons.GROUP, COMMAND_DOCUMENTS);
		documentsItem.addItem("Search Documents",VaadinIcons.GROUP, COMMAND_SEARCH_DOCUMENT);

	}


	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

		createButtonLink(grid,DOCUMENT_ACTIVITY_TEXT, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTACTIVITY.toString(), pageId), DOCUMENT_ACTIVITIES);

		createButtonLink(grid,PERSON_REFERENCES, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.PERSONREFERENCES.toString(), pageId), PERSON_REFERENCES);

		createButtonLink(grid,DOCUMENT_DETAILS, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDETAILS.toString(), pageId), DOCUMENT_DETAILS);

		createButtonLink(grid,DOCUMENT_DATA, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDATA.toString(), pageId), COMPLETE_DOCUMENT_AS_TEXT);

		createButtonLink(grid,DOCUMENT_REFERENCES, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTREFERENCES.toString(), pageId), DOCUMENT_REFERENCES);

		createButtonLink(grid,DOCUMENT_DECISION, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDECISION.toString(), pageId), DOCUMENT_DECISIONS);

		createButtonLink(grid,DOCUMENT_ATTACHEMENTS, VaadinIcons.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTATTACHMENTS.toString(), pageId), ATTACHEMENTS);
	}

}

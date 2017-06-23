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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.DocumentMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class DocumentMenuItemFactoryImpl.
 */
@Service
public final class DocumentMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl implements DocumentMenuItemFactory {


	/** The Constant DOCUMENT_ATTACHEMENTS. */
	private static final String DOCUMENT_ATTACHEMENTS = "Document Attachements";

	/** The Constant DOCUMENT_DATA. */
	private static final String DOCUMENT_DATA = "Document data";

	/** The Constant DOCUMENT. */
	private static final String DOCUMENT = "Document";


	/** The Constant DOCUMENT_DETAILS. */
	private static final String DOCUMENT_DETAILS = "Document details";

	/** The Constant PERSON_REFERENCES. */
	private static final String PERSON_REFERENCES = "Person references";


	/** The Constant DOCUMENT_REFERENCES. */
	private static final String DOCUMENT_REFERENCES = "Document References";

	/** The Constant DOCUMENT_DECISION. */
	private static final String DOCUMENT_DECISION = "Document Decision";

	/** The Constant DOCUMENT_ACTIVITY_TEXT. */
	private static final String DOCUMENT_ACTIVITY_TEXT = "Document Activity";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";


	/**
	 * Instantiates a new document menu item factory impl.
	 */
	public DocumentMenuItemFactoryImpl() {
		super();
	}


	@Override
	public void createDocumentMenuBar(final MenuBar menuBar, final String pageId) {
			initApplicationMenuBar(menuBar);

			menuBar.addItem(OVERVIEW_TEXT, FontAwesome.FILE,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW, pageId));
			
			final MenuItem documentItem = menuBar.addItem(DOCUMENT, FontAwesome.FILE, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTACTIVITY.toString(), pageId));

			documentItem.addItem(PERSON_REFERENCES, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.PERSONREFERENCES.toString(), pageId));

			documentItem.addItem(DOCUMENT_DETAILS, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTDETAILS.toString(), pageId));

			documentItem.addItem(DOCUMENT_DATA, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTDATA.toString(), pageId));

			documentItem.addItem(DOCUMENT_REFERENCES, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTREFERENCES.toString(), pageId));

			documentItem.addItem(DOCUMENT_DECISION, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTDECISION.toString(), pageId));

			documentItem.addItem(DOCUMENT_ATTACHEMENTS, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTATTACHMENTS.toString(), pageId));


			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.PAGEVISITHISTORY,pageId));

	}


	@Override
	public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
		final ResponsiveRow grid = createGridLayout(panelContent);

		createButtonLink(grid,DOCUMENT_ACTIVITY_TEXT, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTACTIVITY.toString(), pageId), "Document activities");

		createButtonLink(grid,PERSON_REFERENCES, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.PERSONREFERENCES.toString(), pageId), "Person references");

		createButtonLink(grid,DOCUMENT_DETAILS, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDETAILS.toString(), pageId), "Document details");

		createButtonLink(grid,DOCUMENT_DATA, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDATA.toString(), pageId), "Complete document as text");

		createButtonLink(grid,DOCUMENT_REFERENCES, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTREFERENCES.toString(), pageId), "Document references");

		createButtonLink(grid,DOCUMENT_DECISION, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTDECISION.toString(), pageId), "Document decisions");

		createButtonLink(grid,DOCUMENT_ATTACHEMENTS, FontAwesome.FILE, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
				DocumentPageMode.DOCUMENTATTACHMENTS.toString(), pageId), "Attachements");



	}

}

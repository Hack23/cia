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
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class OverviewPageModContentFactoryImpl.
 */
@Component
public final class PoliticianDocumentHistoryPageModContentFactoryImpl
		extends AbstractPoliticianPageModContentFactoryImpl {

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "rm", "madePublicDate", "documentType", "subType",
			"title", "subTitle", "referenceName", "partyShortCode", "personReferenceId", "roleDescription", "org", "id",
			"docId", "tempLabel", "label", "numberValue", "orderNumber", "status" };

	/** The Constant DOCUMENTS. */
	private static final String DOCUMENTS = "Documents";

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "id", "partyShortCode", "personReferenceId",
			"numberValue", "orderNumber", "tempLabel", "referenceName", "docId", "label", "roleDescription" };

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.DOCUMENT_VIEW_NAME, "docId", true);

	/**
	 * Instantiates a new politician document history page mod content factory
	 * impl.
	 */
	public PoliticianDocumentHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);

		getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent, viewRiksdagenPolitician.getFirstName() + ' ' + viewRiksdagenPolitician.getLastName() + '(' + viewRiksdagenPolitician.getParty() + ')' + " Document History", "History Overview", "Historical record of documents associated with the politician's roles and activities.");

		final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPoliticianDocument.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenPoliticianDocument.class,
				politicianDocumentDataContainer.findOrderedListByProperty(
						ViewRiksdagenPoliticianDocument_.personReferenceId, viewRiksdagenPolitician.getPersonId(),
						ViewRiksdagenPoliticianDocument_.madePublicDate),
				DOCUMENTS, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
		UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PoliticianPageMode.DOCUMENTHISTORY.toString());
	}
}

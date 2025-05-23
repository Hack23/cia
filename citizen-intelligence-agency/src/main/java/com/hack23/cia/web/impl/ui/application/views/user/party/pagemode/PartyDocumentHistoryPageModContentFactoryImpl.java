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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DocumentHistoryPageModContentFactoryImpl.
 */
@Component
public final class PartyDocumentHistoryPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "rm", "madePublicDate", "title", "subTitle", "id",
			"docId", "referenceName", "partyShortCode", "personReferenceId", "roleDescription", "documentType",
			"subType", "org", "label", "numberValue", "status", "tempLabel", "orderNumber" };

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "id", "partyShortCode", "personReferenceId",
			"numberValue", "orderNumber", "tempLabel", "label", "docId", "roleDescription" };

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.DOCUMENT_VIEW_NAME, "docId", true);

	/** The Constant MEMBER_DOCUMENT_HISTORY. */
	private static final String MEMBER_DOCUMENT_HISTORY = PartyViewConstants.GRID_LABEL_DOCUMENT_HISTORY;

	/**
	 * Instantiates a new party document history page mod content factory impl.
	 */
	public PartyDocumentHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);
		final String pageId = getPageId(parameters);

		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent,
			PartyViewConstants.DOCUMENT_HISTORY_HEADER + " " + viewRiksdagenParty.getPartyName(),
			PartyViewConstants.DOCUMENT_HISTORY_TITLE,
			PartyViewConstants.DOCUMENT_HISTORY_DESC);

		final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPoliticianDocument.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenPoliticianDocument.class,
				politicianDocumentDataContainer.findOrderedListByProperty(
						ViewRiksdagenPoliticianDocument_.partyShortCode, pageId,
						ViewRiksdagenPoliticianDocument_.madePublicDate),
				MEMBER_DOCUMENT_HISTORY, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
		pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandPartyConstants.COMMAND_PARTY_DOCUMENT_HISTORY.matches(page, parameters);
	}

}

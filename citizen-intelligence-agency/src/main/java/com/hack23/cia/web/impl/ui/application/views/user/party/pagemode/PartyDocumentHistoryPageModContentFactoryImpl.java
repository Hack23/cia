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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class DocumentHistoryPageModContentFactoryImpl.
 */
@Component
public final class PartyDocumentHistoryPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The Constant DOCUMENT_HISTORY. */
	private static final String DOCUMENT_HISTORY = "Document History";

	/**
	 * Instantiates a new party document history page mod content factory impl.
	 */
	public PartyDocumentHistoryPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(PartyPageMode.DOCUMENTHISTORY.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenParty, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenParty.class);

		final ViewRiksdagenParty viewRiksdagenParty = dataContainer.load(pageId);

		if (viewRiksdagenParty != null) {

			getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);

			LabelFactory.createHeader2Label(panelContent,DOCUMENT_HISTORY);


			final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenPoliticianDocument.class);

			final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<>(
					ViewRiksdagenPoliticianDocument.class,
					politicianDocumentDataContainer.findOrderedListByProperty(
							ViewRiksdagenPoliticianDocument_.partyShortCode, pageId,
							ViewRiksdagenPoliticianDocument_.madePublicDate));

			getGridFactory().createBasicBeanItemGrid(
					panelContent, politicianDocumentDataSource,
					"Member Document history",
					new String[] { "rm", "madePublicDate", "title", "subTitle","id", "docId", "referenceName", "partyShortCode", "personReferenceId",
							"roleDescription", "documentType", "subType", "org", "label",
							"numberValue", "status", "tempLabel", "orderNumber" },
					new String[] { "id", "partyShortCode", "personReferenceId", "numberValue", "orderNumber",
							"tempLabel", "label", "docId" ,"roleDescription"}, new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME, "docId"), null, null);


			pageCompleted(parameters, panel, pageId, viewRiksdagenParty);
		}
		return panelContent;

	}

}

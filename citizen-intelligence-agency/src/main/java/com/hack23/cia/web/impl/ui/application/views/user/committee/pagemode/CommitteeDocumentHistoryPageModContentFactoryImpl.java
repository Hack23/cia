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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteeDocumentHistoryPageModContentFactoryImpl.
 */
@Component
public final class CommitteeDocumentHistoryPageModContentFactoryImpl
		extends AbstractCommitteePageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = { "rm", "madePublicDate", "id", "docId",
			"personReferenceId", "roleDescription", "title", "subTitle", "documentType", "subType", "org", "label",
			"numberValue", "status", "tempLabel", "orderNumber", "referenceName", "partyShortCode" };

	private static final String DOCUMENTS = "Documents";

	private static final String[] HIDE_COLUMNS = { "id", "numberValue", "orderNumber", "tempLabel",
			"personReferenceId", "org", "docId", "label", "roleDescription" };

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.DOCUMENT_VIEW_NAME, "docId", true);

	/**
	 * Instantiates a new committee document history page mod content factory
	 * impl.
	 */
	public CommitteeDocumentHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenCommittee viewRiksdagenCommittee = getItem(parameters);

		getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent, "Document History " + viewRiksdagenCommittee.getEmbeddedId().getDetail(), "Committee Document History", "Displays the historical progression of documents managed by committees.");

		final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPoliticianDocument.class);

		getGridFactory()
				.createBasicBeanItemGrid(panelContent, ViewRiksdagenPoliticianDocument.class,
						politicianDocumentDataContainer.findOrderedListByProperty(ViewRiksdagenPoliticianDocument_.org,
								viewRiksdagenCommittee.getEmbeddedId().getOrgCode().replace(" ", "").replace("_", "")
										.trim(),
								ViewRiksdagenPoliticianDocument_.madePublicDate),
						DOCUMENTS, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, CommitteePageMode.DOCUMENT_HISTORY.toString());
	}

}

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
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MinistryDocumentHistoryPageModContentFactoryImpl.
 */
@Component
public final class MinistryDocumentHistoryPageModContentFactoryImpl extends AbstractMinistryPageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = { "id", "docId", "personReferenceId", "roleDescription",
			"org", "label", "rm", "madePublicDate", "numberValue", "title", "subTitle", "tempLabel", "orderNumber",
			"documentType", "subType", "status", "partyShortCode", "referenceName" };

	/** The Constant DOCUMENT_HISTORY. */
	private static final String DOCUMENT_HISTORY = "Document History";

	private static final String DOCUMENTS = "Documents";

	private static final String[] HIDE_COLUMNS = { "id", "numberValue", "orderNumber", "tempLabel",
			"personReferenceId", "org", "roleDescription", "label", "subTitle", "docId" };

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.DOCUMENT_VIEW_NAME, "docId");

	/** The Constant MINISTRY. */
	private static final String MINISTRY = "Ministry:";

	/**
	 * Instantiates a new ministry document history page mod content factory
	 * impl.
	 */
	public MinistryDocumentHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenMinistry viewRiksdagenMinistry = getItem(parameters);

		getMinistryMenuItemFactory().createMinistryMenuBar(menuBar, pageId);

		LabelFactory.createHeader2Label(panelContent, DOCUMENT_HISTORY);

		final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPoliticianDocument.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenPoliticianDocument.class,
				politicianDocumentDataContainer.findOrderedListByProperty(ViewRiksdagenPoliticianDocument_.org,
						viewRiksdagenMinistry.getNameId(), ViewRiksdagenPoliticianDocument_.madePublicDate),
				DOCUMENTS, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		panel.setCaption(NAME + "::" + MINISTRY + viewRiksdagenMinistry.getNameId());
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, MinistryPageMode.DOCUMENTHISTORY.toString());
	}

}

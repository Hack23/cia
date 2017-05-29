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
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class CommitteeDocumentHistoryPageModContentFactoryImpl.
 */
@Component
public final class CommitteeDocumentHistoryPageModContentFactoryImpl
		extends AbstractCommitteePageModContentFactoryImpl {

	/** The Constant COMMITTEE. */
	private static final String COMMITTEE = "Committee:";

	/** The Constant DOCUMENT_HISTORY. */
	private static final String DOCUMENT_HISTORY = "Document History";

	/**
	 * Instantiates a new committee document history page mod content factory
	 * impl.
	 */
	public CommitteeDocumentHistoryPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (!StringUtils.isEmpty(parameters)
				&& parameters.contains(CommitteePageMode.DOCUMENT_HISTORY.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommittee.class);

		final ViewRiksdagenCommittee viewRiksdagenCommittee = dataContainer.load(pageId);

		if (viewRiksdagenCommittee != null) {

			getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

			LabelFactory.createHeader2Label(panelContent,DOCUMENT_HISTORY);


			final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenPoliticianDocument.class);

			final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<>(
					ViewRiksdagenPoliticianDocument.class,
					politicianDocumentDataContainer.findOrderedListByProperty(
							ViewRiksdagenPoliticianDocument_.org, viewRiksdagenCommittee.getEmbeddedId().getOrgCode()
									.replace(" ", "").replace("_", "").trim(),
							ViewRiksdagenPoliticianDocument_.madePublicDate));

			getGridFactory().createBasicBeanItemGrid(
					panelContent, politicianDocumentDataSource,
					"Documents",
					new String[] { "rm", "madePublicDate","id", "docId", "personReferenceId",
							"roleDescription", "title", "subTitle", "documentType", "subType", "org", "label",
							"numberValue", "status", "tempLabel", "orderNumber","referenceName", "partyShortCode" },
					new String[] { "id", "numberValue", "orderNumber", "tempLabel", "personReferenceId", "org", "docId", "label","roleDescription" }, new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME, "docId"), null, null);


			panel.setCaption(NAME + "::" + COMMITTEE + viewRiksdagenCommittee.getEmbeddedId().getDetail());
			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER,
					NAME, parameters, pageId);
		}
		return panelContent;

	}

}

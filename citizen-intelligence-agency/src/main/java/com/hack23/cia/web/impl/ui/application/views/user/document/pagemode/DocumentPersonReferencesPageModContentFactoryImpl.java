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
package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentPersonReferenceData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class DocumentPersonReferencesPageModContentFactoryImpl.
 */
@Component
public final class DocumentPersonReferencesPageModContentFactoryImpl extends AbstractDocumentPageModContentFactoryImpl {

	/** The Constant PERSON_REFERENCES. */
	private static final String PERSON_REFERENCES = "Person References";

	/**
	 * Instantiates a new document person references page mod content factory
	 * impl.
	 */
	public DocumentPersonReferencesPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (!StringUtils.isEmpty(parameters) && parameters.contains(DocumentPageMode.PERSONREFERENCES.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<DocumentElement, String> documentElementDataContainer = getApplicationManager()
				.getDataContainer(DocumentElement.class);

		final DataContainer<DocumentStatusContainer, String> documentStatusContainerDataContainer = getApplicationManager()
				.getDataContainer(DocumentStatusContainer.class);

		getApplicationManager()
				.getDataContainer(CommitteeProposalComponentData.class);

		final DocumentElement documentElement = documentElementDataContainer.load(pageId);

		if (documentElement != null) {

			getDocumentMenuItemFactory().createDocumentMenuBar(menuBar, pageId);

			final DocumentStatusContainer documentStatusContainer = documentStatusContainerDataContainer
					.findByQueryProperty(DocumentStatusContainer.class, DocumentStatusContainer_.document,
							DocumentData.class, DocumentData_.id, pageId);


			LabelFactory.createHeader2Label(panelContent,PERSON_REFERENCES);


			if (documentStatusContainer != null
					&& documentStatusContainer.getDocumentPersonReferenceContainer() != null
					&& documentStatusContainer.getDocumentPersonReferenceContainer()
							.getDocumentPersonReferenceList() != null) {
				final BeanItemContainer<DocumentPersonReferenceData> documentPersonReferenceDataDataSource = new BeanItemContainer<>(
						DocumentPersonReferenceData.class, documentStatusContainer
								.getDocumentPersonReferenceContainer().getDocumentPersonReferenceList());

				getGridFactory().createBasicBeanItemGrid(
						panelContent, documentPersonReferenceDataDataSource,
						"Document person references",
						new String[] { "personReferenceId", "referenceName", "partyShortCode", "orderNumber",
								"roleDescription" }, new String[] { "personReferenceId", "hjid" },
						new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personReferenceId"), null, null);

			}


			panel.setContent(panelContent);
			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);
		}

		return panelContent;

	}


}

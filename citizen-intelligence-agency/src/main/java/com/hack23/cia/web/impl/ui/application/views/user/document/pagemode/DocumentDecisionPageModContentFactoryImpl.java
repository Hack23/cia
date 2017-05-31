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

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentProposalData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class DocumentDecisionPageModContentFactoryImpl.
 */
@Component
public final class DocumentDecisionPageModContentFactoryImpl extends AbstractDocumentPageModContentFactoryImpl {

	/** The Constant DOCUMENT_DECISION. */
	private static final String DOCUMENT_DECISION = "Document Decision";

	/**
	 * Instantiates a new document decision page mod content factory impl.
	 */
	public DocumentDecisionPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (!StringUtils.isEmpty(parameters) && parameters.contains(DocumentPageMode.DOCUMENTDECISION.toString()));
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




			LabelFactory.createHeader2Label(panelContent,DOCUMENT_DECISION);

			if (documentStatusContainer != null && documentStatusContainer.getDocumentProposal() != null
					&& documentStatusContainer.getDocumentProposal().getProposal() != null) {

				getFormFactory().addFormPanelTextFields(panelContent,
						new BeanItem<>(
								documentStatusContainer.getDocumentProposal().getProposal()),
						DocumentProposalData.class,
						Arrays.asList(new String[] { "committee", "chamber", "processedIn", "decisionType",
								"proposalNumber", "designation", "wording", "wording2", "wording3",
								"wording4" }));

			}

			panel.setContent(panelContent);
			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);
		}

		return panelContent;

	}


}

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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData_;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DocumentDataPageModContentFactoryImpl.
 */
@Component
public final class DocumentDataPageModContentFactoryImpl extends AbstractDocumentPageModContentFactoryImpl {

	/** The Constant DOCUMENT_DATA. */
	private static final String DOCUMENT_DATA = "Document Data";


	/**
	 * Instantiates a new document data page mod content factory impl.
	 */
	public DocumentDataPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (!StringUtils.isEmpty(parameters) && parameters.contains(DocumentPageMode.DOCUMENTDATA.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<DocumentElement, String> documentElementDataContainer = getApplicationManager()
				.getDataContainer(DocumentElement.class);

		final DataContainer<DocumentContentData, String> documentContentDataDataContainer = getApplicationManager()
				.getDataContainer(DocumentContentData.class);

		getApplicationManager()
				.getDataContainer(CommitteeProposalComponentData.class);

		final DocumentElement documentElement = documentElementDataContainer.load(pageId);

		if (documentElement != null) {

			getDocumentMenuItemFactory().createDocumentMenuBar(menuBar, pageId);

			Label createHeader2Label = LabelFactory.createHeader2Label(DOCUMENT_DATA);
			panelContent.addComponent(createHeader2Label);

			final List<DocumentContentData> documentContentlist = documentContentDataDataContainer
					.getAllBy(DocumentContentData_.id, pageId);

			if (!documentContentlist.isEmpty()) {

				final Label htmlContent = new Label(documentContentlist.get(0).getContent(), ContentMode.HTML);

				panelContent.addComponent(htmlContent);
	
				panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
				panelContent.setExpandRatio(htmlContent, ContentRatio.GRID);

			}



			panel.setContent(panelContent);
			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);
		}

		return panelContent;

	}


}

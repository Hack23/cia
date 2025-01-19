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
package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Safelist;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.FormLayout;
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

	/**
	 * Instantiates a new document data page mod content factory impl.
	 */
	public DocumentDataPageModContentFactoryImpl() {
		super();
	}


	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		getDocumentMenuItemFactory().createDocumentMenuBar(menuBar, pageId);

		CardInfoRowUtil.createPageHeader(panel, panelContent, 
            DocumentViewConstants.DATA_TITLE,
            DocumentViewConstants.DATA_SUBTITLE,
            DocumentViewConstants.DATA_DESC);


		final DataContainer<DocumentContentData, String> documentContentDataDataContainer = getApplicationManager()
				.getDataContainer(DocumentContentData.class);

		final List<DocumentContentData> documentContentlist = documentContentDataDataContainer
				.getAllBy(DocumentContentData_.id, pageId);

		if (!documentContentlist.isEmpty()) {

			final Panel formPanel = new Panel();
			formPanel.setSizeFull();

			panelContent.addComponent(formPanel);

			final FormLayout formContent = new FormLayout();
			formPanel.setContent(formContent);

			final String cleanContent = Jsoup.clean(documentContentlist.get(0).getContent(), "", Safelist.simpleText(),
					new OutputSettings().indentAmount(4));

			final Label htmlContent = new Label(cleanContent, ContentMode.PREFORMATTED);

			formContent.addComponent(htmlContent);


			panelContent.setExpandRatio(formPanel, ContentRatio.GRID);

		}

		panel.setContent(panelContent);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, DocumentPageMode.DOCUMENTDATA.toString());
	}

}

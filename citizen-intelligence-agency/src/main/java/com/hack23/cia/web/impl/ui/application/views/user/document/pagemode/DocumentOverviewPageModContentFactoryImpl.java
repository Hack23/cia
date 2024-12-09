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
package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DocumentOverviewPageModContentFactoryImpl.
 */
@Component
public final class DocumentOverviewPageModContentFactoryImpl extends AbstractDocumentPageModContentFactoryImpl {

	private static final List<String> AS_LIST = Arrays.asList("id", "org", "documentType", "subType", "rm", "status",
			"title", "subTitle", "madePublicDate", "createdDate", "systemDate", "relatedId", "label", "tempLabel",
			"numberValue", "kallId", "documentFormat");
	private static final List<String> AS_LIST2 = Arrays.asList("id", "org", "documentType", "subType", "rm", "status",
			"title", "subTitle", "madePublicDate", "label", "tempLabel", "numberValue", "hangarId");
	private static final List<String> AS_LIST3 = Collections.singletonList("documentCategory");
	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";

	/**
	 * Instantiates a new document overview page mod content factory impl.
	 */
	public DocumentOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DocumentElement documentElement = getItem(parameters);
		getDocumentMenuItemFactory().createDocumentMenuBar(menuBar, pageId);

		final DataContainer<DocumentStatusContainer, String> documentStatusContainerDataContainer = getApplicationManager()
				.getDataContainer(DocumentStatusContainer.class);

		final DocumentStatusContainer documentStatusContainer = documentStatusContainerDataContainer
				.findByQueryProperty(DocumentStatusContainer.class, DocumentStatusContainer_.document,
						DocumentData.class, DocumentData_.id, pageId);

		createPageHeader(panel, panelContent, "Document Overview " + documentElement.getTitle() +" " + documentElement.getSubTitle(), "Document Details", "Access and explore official documents and reports.");

		getFormFactory().addFormPanelTextFields(panelContent, documentElement, DocumentElement.class, AS_LIST);

		if (documentStatusContainer != null) {
			getFormFactory().addFormPanelTextFields(panelContent, documentStatusContainer,
					DocumentStatusContainer.class, AS_LIST3);

			getFormFactory().addFormPanelTextFields(panelContent, documentStatusContainer.getDocument(),
					DocumentData.class, AS_LIST2);
		}

		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();

		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getDocumentMenuItemFactory().createOverviewPage(overviewLayout, pageId);

		panel.setContent(panelContent);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

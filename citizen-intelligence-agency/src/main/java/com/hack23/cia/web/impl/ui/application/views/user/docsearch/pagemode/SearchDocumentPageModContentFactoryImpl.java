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
package com.hack23.cia.web.impl.ui.application.views.user.docsearch.pagemode;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.action.user.SearchDocumentRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.DocumentMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMainViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractBasicPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SearchDocumentClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklisteners.handlers.impl.SearchDocumentResponseHandlerImpl;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class SearchDocumentPageModContentFactoryImpl.
 */
@Component
public final class SearchDocumentPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant AS_LIST. */
	private static final List<String> AS_LIST = Collections.singletonList( "searchExpression" );

	/** The Constant MAX_RESULT_SIZE. */
	private static final int MAX_RESULT_SIZE = 100;

	/** The Constant NAME. */
	public static final String NAME = UserViews.SEARCH_DOCUMENT_VIEW_NAME;

	/** The Constant SEARCH. */
	private static final String SEARCH = "Search";

	/** The document menu item factory. */
	@Autowired
	private DocumentMenuItemFactory documentMenuItemFactory;

	/**
	 * Instantiates a new search document page mod content factory impl.
	 */
	public SearchDocumentPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		documentMenuItemFactory.createDocumentsMenuBar(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent,"Search","Document Search","Search and access detailed parliamentary documents.");

		final VerticalLayout searchLayout = new VerticalLayout();
		searchLayout.setSizeFull();
		panelContent.addComponent(searchLayout);

		final VerticalLayout searchresultLayout = new VerticalLayout();
		searchresultLayout.setSizeFull();

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		searchresultLayout.addComponent(formPanel);

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);

		panelContent.addComponent(searchresultLayout);
		panelContent.setExpandRatio(searchresultLayout, ContentRatio.LARGE);

		final SearchDocumentRequest searchRequest = new SearchDocumentRequest();
		searchRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		searchRequest.setMaxResults(MAX_RESULT_SIZE);
		searchRequest.setSearchExpression("");
		getFormFactory().addRequestInputFormFields(formContent, searchRequest,
				SearchDocumentRequest.class, AS_LIST, SEARCH,
				new SearchDocumentClickListener(searchRequest, new SearchDocumentResponseHandlerImpl(getGridFactory(), formPanel,
						searchresultLayout)));

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandMainViewConstants.COMMAND_SEARCH_DOCUMENT.matches(page, parameters);
	}

}

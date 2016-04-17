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
package com.hack23.cia.web.impl.ui.application.views.user.document;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.user.SearchDocumentRequest;
import com.hack23.cia.service.api.action.user.SearchDocumentResponse;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SearchDocumentClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SearchDocumentResponseHandler;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractUserView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class PartyView.
 */
@Service
@Scope(value = "prototype")
@VaadinView(value = SearchDocumentView.NAME, cached = true)
public final class SearchDocumentView extends AbstractUserView {

	/** The Constant MAX_RESULT_SIZE. */
	private static final int MAX_RESULT_SIZE = 100;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.SEARCH_DOCUMENT_VIEW_NAME;

	/** The application manager. */
	@Autowired
	private transient ApplicationManager applicationManager;

	/** The grid factory. */
	@Autowired
	private transient GridFactory gridFactory;

	/** The form factory. */
	@Autowired
	private transient FormFactory formFactory;

	/** The page mode content factory map. */
	private final transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;

	/**
	 * Instantiates a new search document view.
	 *
	 * @param context
	 *            the context
	 */
	public SearchDocumentView(final ApplicationContext context) {
		super();
		pageModeContentFactoryMap = context.getBeansOfType(PageModeContentFactory.class);

	}


	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}

	// @Secured({ "ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
	@Override
	public void enter(final ViewChangeEvent event) {
		final String parameters = event.getParameters();

		for (final PageModeContentFactory pageModeContentFactory : pageModeContentFactoryMap.values()) {

			if (pageModeContentFactory.matches(NAME, parameters)) {

				getPanel().setContent(pageModeContentFactory.createContent(parameters, getBarmenu(), getPanel()));

				return;
			}
		}

		String pageId = null;
		if (parameters != null) {
			pageId = event.getParameters().substring(parameters.lastIndexOf('/') + "/".length(), parameters.length());
		}

		final VerticalLayout panelContent = new VerticalLayout();
		panelContent.setSizeFull();
		panelContent.setMargin(true);

		getPanel().setContent(panelContent);

		final VerticalLayout searchLayout = new VerticalLayout();
		searchLayout.setSizeFull();
		panelContent.addComponent(searchLayout);

		final VerticalLayout searchresultLayout = new VerticalLayout();
		searchresultLayout.setSizeFull();

		panelContent.addComponent(searchresultLayout);

		final SearchDocumentRequest searchRequest = new SearchDocumentRequest();
		searchRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		searchRequest.setMaxResults(MAX_RESULT_SIZE);
		searchRequest.setSearchExpression("");
		final SearchDocumentResponseHandler handler = new SearchDocumentResponseHandler() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void handle(final SearchDocumentResponse response) {
				searchresultLayout.removeAllComponents();

				final BeanItemContainer<DocumentElement> documentActivityDataDataDataSource = new BeanItemContainer<>(
						DocumentElement.class, response.getResultElement());

				final Grid documentActivityDataItemGrid = gridFactory.createBasicBeanItemGrid(
						documentActivityDataDataDataSource, "Document",
						new String[] { "rm", "createdDate", "madePublicDate", "documentType", "subType", "title",
								"subTitle", "status" },
						new String[] { "label", "id", "hit", "relatedId", "org", "tempLabel", "numberValue",
								"systemDate", "kallId", "documentFormat", "documentUrlText", "documentUrlHtml",
								"documentStatusUrlXml", "committeeReportUrlXml" },
						null, null, null);

				searchresultLayout.addComponent(documentActivityDataItemGrid);
			}
		};
		final ClickListener searchListener = new SearchDocumentClickListener(searchRequest, applicationManager,
				handler);
		formFactory.addRequestInputFormFields(searchLayout, new BeanItem<>(searchRequest), SearchDocumentRequest.class,
				Arrays.asList(new String[] { "searchExpression" }), "Search", searchListener);

		getPanel().setContent(panelContent);
		pageActionEventHelper.createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

	}

}
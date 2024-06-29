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
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.LanguageContentData;
import com.hack23.cia.model.internal.application.system.impl.LanguageContentData_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;



/**
 * The Class AdminLanguageContentPageModContentFactoryImpl.
 */
@Component
public final class AdminLanguageContentPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant ADMIN_LANGUAGE_CONTENT. */
	private static final String ADMIN_LANGUAGE_CONTENT = "Admin Language Content";

	private static final List<String> AS_LIST = Arrays.asList( "refKey", "fromLanguage", "toLanguage", "createdDate",
			"languageValue");

	private static final String[] COLUMN_ORDER = { "hjid", "languageContentType", "refKey", "fromLanguage", "toLanguage", "createdDate",
			"languageValue" };

	private static final String[] HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion" };

	private static final String LANGUAGE_CONTENT_DATA = "LanguageContentData";

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME, "hjid");

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME;

	/**
	 * Instantiates a new admin language content page mod content factory impl.
	 */
	public AdminLanguageContentPageModContentFactoryImpl() {
		super(NAME);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		final String pageId = getPageId(parameters);
		final int pageNr= getPageNr(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		LabelFactory.createHeader2Label(content,ADMIN_LANGUAGE_CONTENT);

		final DataContainer<LanguageContentData, Long> dataContainer = getApplicationManager()
				.getDataContainer(LanguageContentData.class);

		final List<LanguageContentData> pageOrderBy = dataContainer.getPageOrderBy(pageNr,DEFAULT_RESULTS_PER_PAGE,LanguageContentData_.toLanguage);

		getPagingUtil().createPagingControls(content,NAME,pageId, dataContainer.getSize(), pageNr, DEFAULT_RESULTS_PER_PAGE);

		getGridFactory().createBasicBeanItemGrid(content,
				LanguageContentData.class,pageOrderBy,
				LANGUAGE_CONTENT_DATA,
				COLUMN_ORDER, HIDE_COLUMNS,
				LISTENER, null, null);

		if (pageId != null && !pageId.isEmpty()) {

			final LanguageContentData languageContentData = dataContainer.load(Long.valueOf(pageId));

			if (languageContentData != null) {

				getFormFactory().addFormPanelTextFields(content, languageContentData, LanguageContentData.class,
						AS_LIST);
			}
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_LANGUAGE_CONTENT_VIEW,
				ApplicationEventGroup.ADMIN, NAME, null, pageId);

		return content;

	}

}

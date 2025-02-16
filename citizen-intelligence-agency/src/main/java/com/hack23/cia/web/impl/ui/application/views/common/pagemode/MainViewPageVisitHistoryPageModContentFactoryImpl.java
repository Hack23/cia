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
package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandMainViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MainViewPageVisitHistoryPageModContentFactoryImpl.
 */
@Component
public final class MainViewPageVisitHistoryPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = CommonsViews.MAIN_VIEW_NAME;

	/**
	 * Instantiates a new main view page visit history page mod content factory
	 * impl.
	 */
	public MainViewPageVisitHistoryPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		final String pageId = getPageId(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);
		CardInfoRowUtil.createPageHeader(panel, content,
			CommonViewConstants.VISIT_HISTORY_TITLE_HEADER,
			CommonViewConstants.VISIT_HISTORY_TITLE,
			CommonViewConstants.VISIT_HISTORY_DESCRIPTION);

		createPageVisitHistory(NAME,pageId,content);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MAIN_VIEW, ApplicationEventGroup.USER,
				CommonsViews.MAIN_VIEW_NAME, parameters, pageId);


		return content;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandMainViewConstants.COMMAND_MAINVIEW_PAGEVISITHISTORY.matches(page, parameters);
	}

}

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
package com.hack23.cia.web.impl.ui.application.views.user.home.pagemode;

import java.util.Optional;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.converters.ListPropertyConverter;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserHomePageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class UserHomeApplicationSessionsPageModContentFactoryImpl.
 */
@Component
public final class UserHomeApplicationSessionsPageModContentFactoryImpl
		extends AbstractUserHomePageModContentFactoryImpl {

	/** The Constant APPLICATION_SESSION. */
	private static final String APPLICATION_SESSION = "ApplicationSession";

	/** The Constant COLLECTION_PROPERTY_CONVERTERS. */
	private static final ListPropertyConverter[] COLLECTION_PROPERTY_CONVERTERS = {
			new ListPropertyConverter("page", "events", "actionName") };

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "hjid", "createdDate", "operatingSystem",
			"ipInformation", "events", "userAgentInformation" };

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion",
			"sessionId", "sessionType", "userId", "locale" };

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, "hjid");

	/** The user home menu item factory. */
	@Autowired
	private UserHomeMenuItemFactory userHomeMenuItemFactory;

	/**
	 * Instantiates a new user home security settings page mod content factory
	 * impl.
	 */
	public UserHomeApplicationSessionsPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		final String pageId = getPageId(parameters);
		final Optional<UserAccount> userAccount = getActiveUserAccount();

		if (userAccount.isPresent()) {
			userHomeMenuItemFactory.createUserHomeMenuBar(menuBar, pageId);
			CardInfoRowUtil.createPageHeader(panel, panelContent,
                UserHomeViewConstants.TITLE_PREFIX + UserHomeViewConstants.USER_VISITS_TITLE,
                UserHomeViewConstants.USER_VISITS_TITLE,
                UserHomeViewConstants.USER_VISITS_DESC);

			final DataContainer<ApplicationSession, Long> sessionDataContainer = getApplicationManager()
					.getDataContainer(ApplicationSession.class);

			getGridFactory().createBasicBeanItemGrid(panelContent, ApplicationSession.class,
					sessionDataContainer.findOrderedListByProperty(ApplicationSession_.userId,
							userAccount.get().getUserId(), ApplicationSession_.createdDate),
					APPLICATION_SESSION, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, COLLECTION_PROPERTY_CONVERTERS);
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_USER_HOME_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(UserHomePageMode.USER_VISITS.toString());
	}

}

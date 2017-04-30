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
package com.hack23.cia.web.impl.ui.application.views.user.home.pagemode;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.application.LogoutRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.util.UserContextUtil;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.LogoutClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class UserHomeOverviewPageModContentFactoryImpl.
 */
@Component
public final class UserHomeOverviewPageModContentFactoryImpl extends AbstractUserHomePageModContentFactoryImpl {

	/** The Constant LOGOUT. */
	private static final String LOGOUT = "Logout";

	/** The Constant USERHOME. */
	private static final String USERHOME = "Userhome:";

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "Overview";

	/** The user home menu item factory. */
	@Autowired
	private UserHomeMenuItemFactory userHomeMenuItemFactory;

	/**
	 * Instantiates a new user home overview page mod content factory impl.
	 */
	public UserHomeOverviewPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);


		userHomeMenuItemFactory.createUserHomeMenuBar(menuBar, pageId);

		LabelFactory.createHeader2Label(panelContent,OVERVIEW);


		final Button logoutButton = new Button(LOGOUT,FontAwesome.SIGN_OUT);

		final LogoutRequest logoutRequest = new LogoutRequest();
		logoutRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		logoutButton.addClickListener(new LogoutClickListener(logoutRequest));

		panelContent.addComponent(logoutButton);


		final DataContainer<UserAccount, Long> dataContainer = getApplicationManager().getDataContainer(UserAccount.class);


			final Long userIdFromSecurityContext = UserContextUtil.getUserInternalIdFromSecurityContext();

			if (userIdFromSecurityContext == null) {
				UI.getCurrent().getNavigator().navigateTo(CommonsViews.MAIN_VIEW_NAME);
			} else {

				final UserAccount userAccount = dataContainer.load(userIdFromSecurityContext);


				getFormFactory().addFormPanelTextFields(panelContent, new BeanItem<>(userAccount), UserAccount.class,
						Arrays.asList(new String[] { "username","createdDate","email","country","numberOfVisits" }));



				final DataContainer<ApplicationActionEvent, Long> eventDataContainer = getApplicationManager().getDataContainer(ApplicationActionEvent.class);


				final BeanItemContainer<ApplicationActionEvent> politicianDocumentDataSource = new BeanItemContainer<>(ApplicationActionEvent.class,
						eventDataContainer.findOrderedListByProperty(ApplicationActionEvent_.userId,userAccount.getUserId(),ApplicationActionEvent_.createdDate));

				getGridFactory().createBasicBeanItemGrid(panelContent, politicianDocumentDataSource,
						"ApplicationActionEvent",
						new String[] { "hjid", "createdDate", "eventGroup", "applicationOperation","actionName","page","pageMode","elementId", "applicationMessage","errorMessage", "modelObjectVersion" }, new String[] { "hjid","userId","sessionId", "modelObjectId","modelObjectVersion" },
						new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "hjid"), null, null);

				panelContent.setExpandRatio(logoutButton, ContentRatio.SMALL);

			}

			panel.setCaption(USERHOME);

			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_USER_HOME_VIEW, ApplicationEventGroup.USER, NAME,
					parameters, pageId);

		return panelContent;

	}

}

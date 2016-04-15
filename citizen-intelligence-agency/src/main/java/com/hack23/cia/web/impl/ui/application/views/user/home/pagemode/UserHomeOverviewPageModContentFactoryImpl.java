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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.application.LogoutRequest;
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.LogoutClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SetGoogleAuthenticatorCredentialClickListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class UserHomeOverviewPageModContentFactoryImpl.
 */
@Component
public final class UserHomeOverviewPageModContentFactoryImpl extends AbstractUserHomePageModContentFactoryImpl {

	private static final String ENABLE_GOOGLE_AUTHENTICATOR = "Enable Google Authenticator";

	private static final String LOGOUT = "Logout";

	private static final String USERHOME = "Userhome:";

	private static final String OVERVIEW = "Overview";

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


		getMenuItemFactory().createUserHomeMenuBar(menuBar, pageId);


		final Label createHeader2Label = LabelFactory.createHeader2Label(OVERVIEW);
		panelContent.addComponent(createHeader2Label);

		final Button logoutButton = new Button(LOGOUT);

		final LogoutRequest logoutRequest = new LogoutRequest();
		logoutRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		logoutButton.addClickListener(new LogoutClickListener(logoutRequest,getApplicationManager()));

		panelContent.addComponent(logoutButton);

		final Button googleAuthButton = new Button(ENABLE_GOOGLE_AUTHENTICATOR);

		final SetGoogleAuthenticatorCredentialRequest googleAuthRequest = new SetGoogleAuthenticatorCredentialRequest();
		googleAuthRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		googleAuthButton.addClickListener(new SetGoogleAuthenticatorCredentialClickListener(googleAuthRequest,getApplicationManager()));

		panelContent.addComponent(googleAuthButton);

		final DataContainer<UserAccount, Long> dataContainer = getApplicationManager().getDataContainer(UserAccount.class);


			final Long userIdFromSecurityContext = getUserIdFromSecurityContext();

			if (userIdFromSecurityContext == null) {
				UI.getCurrent().getNavigator().navigateTo(CommonsViews.MAIN_VIEW_NAME);
			} else {

				final UserAccount userAccount = dataContainer.load(userIdFromSecurityContext);


				final Panel formPanel = new Panel();
				formPanel.setSizeFull();

				panelContent.addComponent(formPanel);

				final FormLayout formContent = new FormLayout();
				formPanel.setContent(formContent);



				getFormFactory().addTextFields(formContent, new BeanItem<>(userAccount), UserAccount.class,
						Arrays.asList(new String[] { "username","createdDate","email","country","numberOfVisits" }));



				final DataContainer<ApplicationActionEvent, Long> eventDataContainer = getApplicationManager().getDataContainer(ApplicationActionEvent.class);


				final BeanItemContainer<ApplicationActionEvent> politicianDocumentDataSource = new BeanItemContainer<>(ApplicationActionEvent.class,
						eventDataContainer.findOrderedListByProperty(ApplicationActionEvent_.userId,userAccount.getUserId(),ApplicationActionEvent_.createdDate));

				final Grid createBasicBeanItemGrid = getGridFactory().createBasicBeanItemGrid(politicianDocumentDataSource, "ApplicationActionEvent",
						new String[] { "hjid", "createdDate", "eventGroup", "applicationOperation","page","pageMode","elementId","actionName","userId","sessionId","errorMessage","applicationMessage", "modelObjectVersion" },
						new String[] { "modelObjectId" }, "hjid",
						new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "hjid"), null);
				panelContent.addComponent(createBasicBeanItemGrid);


				panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
				panelContent.setExpandRatio(logoutButton, ContentRatio.SMALL);

				panelContent.setExpandRatio(formPanel, ContentRatio.GRID);
				panelContent.setExpandRatio(createBasicBeanItemGrid,ContentRatio.GRID);
			}

			panel.setCaption(USERHOME);

			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_USER_HOME_VIEW, ApplicationEventGroup.USER, NAME,
					parameters, pageId);

		return panelContent;

	}

	/**
	 * Gets the user id from security context.
	 *
	 * @return the user id from security context
	 */
	private static Long getUserIdFromSecurityContext() {

		Long result=null;

		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication authentication = context.getAuthentication();
			if (authentication != null) {
				final Object principal = authentication.getPrincipal();

				if (principal instanceof UserAccount) {
					final UserAccount userAccount = (UserAccount) principal;
					result = userAccount.getHjid();
				}
			}
		}

		return result;
	}

}

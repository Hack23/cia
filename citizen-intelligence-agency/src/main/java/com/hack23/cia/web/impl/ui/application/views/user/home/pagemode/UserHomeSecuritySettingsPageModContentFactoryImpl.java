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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.action.user.DisableGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.util.UserContextUtil;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserHomePageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.DisableGoogleAuthenticatorCredentialClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SetGoogleAuthenticatorCredentialClickListener;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;

/**
 * The Class UserHomeSecuritySettingsPageModContentFactoryImpl.
 */
@Component
public final class UserHomeSecuritySettingsPageModContentFactoryImpl extends AbstractUserHomePageModContentFactoryImpl {
	
	/** The Constant ENABLE_GOOGLE_AUTHENTICATOR. */
	private static final String ENABLE_GOOGLE_AUTHENTICATOR = "Enable Google Authenticator";

	/** The Constant DISABLE_GOOGLE_AUTHENTICATOR. */
	private static final String DISABLE_GOOGLE_AUTHENTICATOR = "Disable Google Authenticator";
	
	/** The Constant USERHOME. */
	private static final String USERHOME = "Userhome:";

	/** The Constant SECURITY_SETTINGS. */
	private static final String SECURITY_SETTINGS = "Security Settings";

	/** The user home menu item factory. */
	@Autowired
	private UserHomeMenuItemFactory userHomeMenuItemFactory;

	/**
	 * Instantiates a new user home security settings page mod content factory
	 * impl.
	 */
	public UserHomeSecuritySettingsPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(UserHomePageMode.SECURITY_SETTINGS.toString());
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		userHomeMenuItemFactory.createUserHomeMenuBar(menuBar, pageId);

		LabelFactory.createHeader2Label(panelContent, SECURITY_SETTINGS);

		final Long userIdFromSecurityContext = UserContextUtil.getUserInternalIdFromSecurityContext();

		if (userIdFromSecurityContext == null) {
			UI.getCurrent().getNavigator().navigateTo(CommonsViews.MAIN_VIEW_NAME);
		} else {
			
			final VerticalLayout overviewLayout = new VerticalLayout();
			overviewLayout.setSizeFull();
			panelContent.addComponent(overviewLayout);
			panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE);
			
			final ResponsiveRow grid = createGridLayout(overviewLayout);
			
			createRowItem(grid,createEnableGoogleAuthButton(),"Enable MFA using google authenticator");
			createRowItem(grid,createDisableGoogleAuthButton(),"Disable MFA using google authenticator");			
		}

		panel.setCaption(NAME + "::" + USERHOME + SECURITY_SETTINGS);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_USER_HOME_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	private Button createEnableGoogleAuthButton() {
		final Button googleAuthButton = new Button(ENABLE_GOOGLE_AUTHENTICATOR, VaadinIcons.SAFE_LOCK);
		googleAuthButton.setId(ENABLE_GOOGLE_AUTHENTICATOR);

		final SetGoogleAuthenticatorCredentialRequest googleAuthRequest = new SetGoogleAuthenticatorCredentialRequest();
		googleAuthRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		googleAuthButton.addClickListener(
				new SetGoogleAuthenticatorCredentialClickListener(googleAuthRequest));
		return googleAuthButton;
	}

	private Button createDisableGoogleAuthButton() {
		final Button googleAuthButton = new Button(DISABLE_GOOGLE_AUTHENTICATOR, VaadinIcons.SAFE_LOCK);
		googleAuthButton.setId(DISABLE_GOOGLE_AUTHENTICATOR);

		final DisableGoogleAuthenticatorCredentialRequest googleAuthRequest = new DisableGoogleAuthenticatorCredentialRequest();
		googleAuthRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		googleAuthButton.addClickListener(
				new DisableGoogleAuthenticatorCredentialClickListener(googleAuthRequest));
		return googleAuthButton;
	}

}

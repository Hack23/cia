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
package com.hack23.cia.web.impl.ui.application.views.user.home.pagemode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.action.user.ChangePasswordRequest;
import com.hack23.cia.service.api.action.user.DeleteAccountRequest;
import com.hack23.cia.service.api.action.user.DisableGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.service.api.action.user.SetGoogleAuthenticatorCredentialRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserHomePageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.ChangePasswordClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.DeleteAccountClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.DisableGoogleAuthenticatorCredentialClickListener;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SetGoogleAuthenticatorCredentialClickListener;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class UserHomeSecuritySettingsPageModContentFactoryImpl.
 */
@Component
public final class UserHomeSecuritySettingsPageModContentFactoryImpl extends AbstractUserHomePageModContentFactoryImpl {

	/** The Constant DELETE_ACCOUNT. */
	private static final String DELETE_ACCOUNT = "Delete Account";

	/** The Constant AS_LIST. */
	private static final List<String> AS_LIST = Collections.singletonList("userpassword");

	/** The Constant DISABLE_GOOGLE_AUTHENTICATOR. */
	private static final String DISABLE_GOOGLE_AUTHENTICATOR = "Disable Google Authenticator";

	/** The Constant ENABLE_GOOGLE_AUTHENTICATOR. */
	private static final String ENABLE_GOOGLE_AUTHENTICATOR = "Enable Google Authenticator";

	/** The Constant SECURITY_SETTINGS. */
	private static final String SECURITY_SETTINGS = "Security Settings";

	/** The user home menu item factory. */
	@Autowired
	private UserHomeMenuItemFactory userHomeMenuItemFactory;

	/**
	 * Instantiates a new user home security settings page mod content factory impl.
	 */
	public UserHomeSecuritySettingsPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the change password button.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout createChangePasswordButton() {

		final VerticalLayout formLayout = new VerticalLayout();
		formLayout.setSizeFull();

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		formLayout.addComponent(formPanel);

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);

		final ChangePasswordRequest request = new ChangePasswordRequest();
		request.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		request.setCurrentPassword("");
		request.setNewPassword("");
		request.setRepeatNewPassword("");

		final ClickListener listener = new ChangePasswordClickListener(request);
		getFormFactory().addRequestInputFormFields(formContent, request,
				ChangePasswordRequest.class, Arrays.asList("currentPassword","newPassword","repeatNewPassword"), "Change password", listener);

		return formLayout;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		userHomeMenuItemFactory.createUserHomeMenuBar(menuBar, pageId);
		createPageHeader(panel, panelContent,"CitizenIntelligence Agency::UserHome::Security Settings",SECURITY_SETTINGS,"Manage user security settings");
	

		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE);

		final ResponsiveRow grid = RowUtil.createGridLayout(overviewLayout);

		RowUtil.createRowComponent(grid, createChangePasswordButton(), "Change password");
		RowUtil.createRowComponent(grid, createEnableGoogleAuthButton(), "Enable MFA using google authenticator");
		RowUtil.createRowComponent(grid, createDisableGoogleAuthButton(), "Disable MFA using google authenticator");
		RowUtil.createRowComponent(grid, createDeleteAccountButton(), "Delete Account");

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_USER_HOME_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	/**
	 * Creates the disable google auth button.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout createDisableGoogleAuthButton() {

		final VerticalLayout formLayout = new VerticalLayout();
		formLayout.setSizeFull();

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		formLayout.addComponent(formPanel);

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);

		final DisableGoogleAuthenticatorCredentialRequest request = new DisableGoogleAuthenticatorCredentialRequest();
		request.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		request.setUserpassword("");
		final ClickListener listener = new DisableGoogleAuthenticatorCredentialClickListener(request);
		getFormFactory().addRequestInputFormFields(formContent, request,
				DisableGoogleAuthenticatorCredentialRequest.class, AS_LIST, DISABLE_GOOGLE_AUTHENTICATOR, listener);

		return formLayout;
	}

	/**
	 * Creates the enable google auth button.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout createEnableGoogleAuthButton() {
		final VerticalLayout formLayout = new VerticalLayout();
		formLayout.setSizeFull();

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		formLayout.addComponent(formPanel);

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);

		final SetGoogleAuthenticatorCredentialRequest request = new SetGoogleAuthenticatorCredentialRequest();
		request.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		request.setUserpassword("");
		final ClickListener listener = new SetGoogleAuthenticatorCredentialClickListener(request);
		getFormFactory().addRequestInputFormFields(formContent, request, SetGoogleAuthenticatorCredentialRequest.class,
				AS_LIST, ENABLE_GOOGLE_AUTHENTICATOR, listener);

		return formLayout;
	}

	private VerticalLayout createDeleteAccountButton() {

		final VerticalLayout formLayout = new VerticalLayout();
		formLayout.setSizeFull();

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		formLayout.addComponent(formPanel);

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);

		final DeleteAccountRequest request = new DeleteAccountRequest();
		request.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		request.setUserpassword("");
		final ClickListener listener = new DeleteAccountClickListener(request);
		getFormFactory().addRequestInputFormFields(formContent, request,
				DeleteAccountRequest.class, AS_LIST, DELETE_ACCOUNT, listener);

		return formLayout;
	}


	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && parameters.contains(UserHomePageMode.SECURITY_SETTINGS.toString());
	}

}

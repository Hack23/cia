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
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserHomeConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
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


	/** The Constant AS_LIST. */
	private static final List<String> AS_LIST = Collections.singletonList("userpassword");


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
		CardInfoRowUtil.createPageHeader(panel, panelContent,
            UserHomeViewConstants.TITLE_PREFIX + UserHomeViewConstants.SECURITY_SETTINGS_TITLE,
            UserHomeViewConstants.SECURITY_SETTINGS_TITLE,
            UserHomeViewConstants.SECURITY_SETTINGS_DESC);


		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE);

		final ResponsiveRow grid = RowUtil.createGridLayout(overviewLayout);

		RowUtil.createRowComponent(grid, createChangePasswordButton(), UserHomeViewConstants.LABEL_CHANGE_PASSWORD);
        RowUtil.createRowComponent(grid, createEnableGoogleAuthButton(), UserHomeViewConstants.LABEL_ENABLE_MFA);
        RowUtil.createRowComponent(grid, createDisableGoogleAuthButton(), UserHomeViewConstants.LABEL_DISABLE_MFA);
        RowUtil.createRowComponent(grid, createDeleteAccountButton(), UserHomeViewConstants.LABEL_DELETE_ACCOUNT);

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
				DisableGoogleAuthenticatorCredentialRequest.class, AS_LIST, UserHomeViewConstants.BUTTON_DISABLE_GOOGLE_AUTH, listener);

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
				AS_LIST, UserHomeViewConstants.BUTTON_ENABLE_GOOGLE_AUTH, listener);

		return formLayout;
	}

	/**
	 * Creates the delete account button.
	 *
	 * @return the vertical layout
	 */
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
				DeleteAccountRequest.class, AS_LIST, UserHomeViewConstants.BUTTON_DELETE_ACCOUNT, listener);

		return formLayout;
	}


	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandUserHomeConstants.COMMAND_USERHOME_SECURITY_SETTINGS.matches(page, parameters);
	}

}

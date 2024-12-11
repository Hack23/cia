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
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.action.admin.SendEmailRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.SendEmailClickListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class EmailPageModContentFactoryImpl.
 */
@Component
public final class EmailPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant EMAIL. */
	private static final String EMAIL = "Email";

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_EMAIL_VIEW_NAME;

	/** The Constant SEND_EMAIL_REQUEST_FORM_FIELDS. */
	private static final List<String> SEND_EMAIL_REQUEST_FORM_FIELDS = Arrays.asList( "email", "subject", "content" );

	/**
	 * Instantiates a new email page mod content factory impl.
	 */
	public EmailPageModContentFactoryImpl() {
		super(NAME);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		final String pageId = getPageId(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		createPageHeader(panel, content, "Admin Email Management", "Email Overview", "Page for managing email configurations and communications within the agency.");

		final VerticalLayout emailLayout = new VerticalLayout();
		emailLayout.setSizeFull();

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		emailLayout.addComponent(formPanel);

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);

		final SendEmailRequest sendEmailRequest = new SendEmailRequest();
		sendEmailRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		sendEmailRequest.setEmail("");
		sendEmailRequest.setSubject("");
		sendEmailRequest.setContent("");
		final ClickListener sendEmailListener = new SendEmailClickListener(sendEmailRequest);
		getFormFactory().addRequestInputFormFields(formContent, sendEmailRequest,
				SendEmailRequest.class, SEND_EMAIL_REQUEST_FORM_FIELDS, EMAIL,
				sendEmailListener);

		content.addComponent(emailLayout);
		content.setExpandRatio(emailLayout, ContentRatio.LARGE_FORM);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_EMAIL_VIEW, ApplicationEventGroup.ADMIN, NAME,
				null, pageId);

		return content;

	}

}

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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.admin.SendEmailRequest;
import com.hack23.cia.service.api.action.admin.SendEmailResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;

/**
 * The Class SendEmailClickListenerTest.
 */
public class SendEmailClickListenerTest extends AbstractUnitTest {

	/**
	 * Show notification failure test.
	 */
	@Test
	public void showNotificationFailureTest() {
		final SendEmailRequest request = new SendEmailRequest();
		final SendEmailClickListener listener = Mockito.spy(new SendEmailClickListener(request));
		final ApplicationManager applicationManager = Mockito.mock(ApplicationManager.class);
		Mockito.doReturn(applicationManager).when(listener).getApplicationManager();

		final SendEmailResponse sendEmailResponse = new SendEmailResponse(ServiceResult.FAILURE);
		sendEmailResponse.setErrorMessage("errorMessage");
		Mockito.when(applicationManager.service(request)).thenReturn(sendEmailResponse);

		Mockito.doNothing().when(listener).showNotification(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(Type.class));
		listener.buttonClick(new ClickEvent(new Panel()));
		Mockito.verify(listener).showNotification(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(Type.class));
	}
}

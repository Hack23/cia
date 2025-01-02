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

import static org.mockito.Mockito.times;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.DeleteAccountRequest;
import com.hack23.cia.service.api.action.user.DeleteAccountResponse;
import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;

/**
 * The Class DeleteAccountClickListenerTest.
 */
public class DeleteAccountClickListenerTest extends AbstractUnitTest {

	/**
	 * Show notification failure test.
	 */
	@Test
	public void showNotificationFailureTest() {
		final DeleteAccountRequest request = new DeleteAccountRequest();
		final DeleteAccountClickListener listener = Mockito.spy(new DeleteAccountClickListener(request));
		final ApplicationManager applicationManager = Mockito.mock(ApplicationManager.class);
		Mockito.doReturn(applicationManager).when(listener).getApplicationManager();

		final DeleteAccountResponse response = new DeleteAccountResponse(ServiceResult.FAILURE);
		response.setErrorMessage("errorMessage");
		Mockito.when(applicationManager.service(request)).thenReturn(response);

		Mockito.doNothing().when(listener).showNotification(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(Type.class));
		listener.buttonClick(new ClickEvent(new Panel()));
		Mockito.verify(listener).showNotification(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(Type.class));
	}

	/**
	 * Show notification success test.
	 */
	@Test
	public void showNotificationSuccessTest() {
		final DeleteAccountRequest request = new DeleteAccountRequest();
		final DeleteAccountClickListener listener = Mockito.spy(new DeleteAccountClickListener(request));
		final ApplicationManager applicationManager = Mockito.mock(ApplicationManager.class);
		Mockito.doReturn(applicationManager).when(listener).getApplicationManager();

		final DeleteAccountResponse response = new DeleteAccountResponse(ServiceResult.SUCCESS);
		Mockito.when(applicationManager.service(request)).thenReturn(response);
		listener.buttonClick(new ClickEvent(new Panel()));
		Mockito.verify(applicationManager,times(1)).service(request);
	}

}

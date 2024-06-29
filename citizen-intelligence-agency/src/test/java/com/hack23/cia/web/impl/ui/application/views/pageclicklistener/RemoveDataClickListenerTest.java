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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.admin.RemoveDataRequest;
import com.hack23.cia.service.api.action.admin.RemoveDataResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;

public class RemoveDataClickListenerTest extends AbstractUnitTest {

	/**
	 * Show notification success test.
	 */
	@Test
	public void showNotificationSuccessTest() {
		final RemoveDataRequest request = new RemoveDataRequest();
		final RemoveDataClickListener listener = Mockito.spy(new RemoveDataClickListener(request));
		final ApplicationManager applicationManager = Mockito.mock(ApplicationManager.class);
		Mockito.doReturn(applicationManager).when(listener).getApplicationManager();

		final RemoveDataResponse response = new RemoveDataResponse(ServiceResult.SUCCESS);
		Mockito.when(applicationManager.asyncService(request)).thenReturn(Mockito.mock(Future.class));

		Mockito.doNothing().when(listener).showNotification(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(Type.class));
		listener.buttonClick(new ClickEvent(new Panel()));
		Mockito.verify(listener).showNotification(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(Type.class));
	}

}

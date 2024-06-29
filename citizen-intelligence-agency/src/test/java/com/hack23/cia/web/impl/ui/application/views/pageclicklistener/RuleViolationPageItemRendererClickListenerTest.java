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

import static org.mockito.Mockito.times;

import java.util.HashSet;

import org.junit.Test;
import org.mockito.Mockito;

import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;

public class RuleViolationPageItemRendererClickListenerTest extends AbstractUnitTest {

	/**
	 * Click party test.
	 */
	@Test
	public void clickPartyTest() {
		final RuleViolationPageItemRendererClickListener listener = new RuleViolationPageItemRendererClickListener();
		final UI uiMock = Mockito.mock(UI.class);
		UI.setCurrent(uiMock);

		final Navigator navigatorMock = Mockito.mock(Navigator.class);
		Mockito.when(uiMock.getNavigator()).thenReturn(navigatorMock);

		final RendererClickEvent event = Mockito.mock(RendererClickEvent.class);
		Mockito.when(event.getItem()).thenReturn(new RuleViolation("partyid", null, ResourceType.PARTY, null, null, null, null, null));

		listener.click(event);

		Mockito.verify(navigatorMock, times(1)).navigateTo("party/partyid");
	}

	/**
	 * Click politician test.
	 */
	@Test
	public void clickPoliticianTest() {
		final RuleViolationPageItemRendererClickListener listener = new RuleViolationPageItemRendererClickListener();
		final UI uiMock = Mockito.mock(UI.class);
		UI.setCurrent(uiMock);

		final Navigator navigatorMock = Mockito.mock(Navigator.class);
		Mockito.when(uiMock.getNavigator()).thenReturn(navigatorMock);

		final RendererClickEvent event = Mockito.mock(RendererClickEvent.class);
		Mockito.when(event.getItem()).thenReturn(new RuleViolation("personid", null, ResourceType.POLITICIAN, null, null, null, null, null));

		listener.click(event);

		Mockito.verify(navigatorMock, times(1)).navigateTo("politician/personid");
	}


	/**
	 * Selection change event source empty test.
	 */
	@Test
	public void selectionChangeEventSourceEmptyTest() {
		final RuleViolationPageItemRendererClickListener listener = new RuleViolationPageItemRendererClickListener();

		final SelectionEvent event = Mockito.mock(SelectionEvent.class);
		Mockito.when(event.getAllSelectedItems()).thenReturn(new HashSet<>());

		listener.selectionChange(event);

		Mockito.verify(event,times(1)).getAllSelectedItems();

	}

}

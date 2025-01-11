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

import java.io.Serializable;
import java.util.HashSet;

import org.junit.Test;
import org.mockito.Mockito;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;

public final class PageItemPropertyClickListenerTest extends AbstractUnitTest {


	/**
	 * Check correct page id click success test.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void checkCorrectPageIdClickSuccessTest() {
		final PageItemPropertyClickListener pageItemPropertyClickListener = new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId");

		final String personIdValue = "personId";
		final ViewRiksdagenPolitician riksdagenPolitician = new ViewRiksdagenPolitician().withPersonId(personIdValue);
		final String pageId = pageItemPropertyClickListener.getPageId(riksdagenPolitician);

		assertEquals(personIdValue, pageId);

		final UI uiMock = Mockito.mock(UI.class);
		UI.setCurrent(uiMock);

		final Navigator navigatorMock = Mockito.mock(Navigator.class);
		Mockito.when(uiMock.getNavigator()).thenReturn(navigatorMock);

		pageItemPropertyClickListener.click(new RendererClickEvent(new Grid(), riksdagenPolitician, null, null) {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;});

		Mockito.verify(navigatorMock, times(1)).navigateTo(UserViews.POLITICIAN_VIEW_NAME + "/personId");
	}

	/**
	 * Check correct page id failure test.
	 */
	@Test
	public void checkCorrectPageIdFailureTest() {
		final PageItemPropertyClickListener pageItemPropertyClickListener = new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"wrongProperty");

		final String pageId = pageItemPropertyClickListener.getPageId(new ViewRiksdagenPolitician());

		assertEquals("ErrorGettingPageId", pageId);
	}

	/**
	 * Selection change empty test.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void selectionChangeEmptyTest() {
		final PageItemPropertyClickListener listener = new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"wrongProperty");

		final SelectionEvent<Serializable> event = Mockito.mock(SelectionEvent.class);
		Mockito.when(event.getAllSelectedItems()).thenReturn(new HashSet());
		listener.selectionChange(event);

		Mockito.verify(event,times(1)).getAllSelectedItems();
	}

}

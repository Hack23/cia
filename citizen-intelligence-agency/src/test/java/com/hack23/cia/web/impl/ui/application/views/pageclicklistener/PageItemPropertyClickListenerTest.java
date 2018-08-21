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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import static org.mockito.Mockito.times;

import org.junit.Test;
import org.mockito.Mockito;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;

public final class PageItemPropertyClickListenerTest extends AbstractUnitTest {


	/**
	 * Check correct page id click success test.
	 */
	@Test
	public void checkCorrectPageIdClickSuccessTest() {
		final PageItemPropertyClickListener pageItemPropertyClickListener = new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId");

		final String personIdValue = "personId";
		ViewRiksdagenPolitician riksdagenPolitician = new ViewRiksdagenPolitician().withPersonId(personIdValue);
		final String pageId = pageItemPropertyClickListener.getPageId(riksdagenPolitician);

		assertEquals(personIdValue, pageId);
		
		UI uiMock = Mockito.mock(UI.class);
		UI.setCurrent(uiMock);
		
		Navigator navigatorMock = Mockito.mock(Navigator.class);
		Mockito.when(uiMock.getNavigator()).thenReturn(navigatorMock);		
				
		pageItemPropertyClickListener.click(new RendererClickEvent(new Grid(), riksdagenPolitician, null, null) {});
		
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

}

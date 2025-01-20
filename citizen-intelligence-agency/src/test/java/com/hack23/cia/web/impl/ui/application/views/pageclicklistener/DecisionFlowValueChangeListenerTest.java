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
import org.mockito.Mockito;

import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.vaadin.data.HasValue;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;

/**
 * The Class DecisionFlowValueChangeListenerTest.
 */
public class DecisionFlowValueChangeListenerTest extends AbstractUnitTest {

	/**
	 * Selection value change event source empty test.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void selectionValueChangeEventSourceEmptyTest() {
		final String pageName = "pageName";
		final String pageId = "pageId";
		final DecisionFlowValueChangeListener listener = new DecisionFlowValueChangeListener(pageName, pageId);

		final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);

		final HasValue hasValue = Mockito.mock(HasValue.class);
		Mockito.when(hasValue.isEmpty()).thenReturn(true);

		Mockito.when(event.getSource()).thenReturn(hasValue);

		listener.valueChange(event);

		Mockito.verify(event,times(1)).getSource();

	}


	/**
	 * Selection value change test.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void selectionValueChangeTest() {
		final String pageName = "pageName";
		final String pageId = "pageId";
		final DecisionFlowValueChangeListener listener = new DecisionFlowValueChangeListener(pageName, pageId);
		final UI uiMock = Mockito.mock(UI.class);
		UI.setCurrent(uiMock);

		final Navigator navigatorMock = Mockito.mock(Navigator.class);
		Mockito.when(uiMock.getNavigator()).thenReturn(navigatorMock);

		final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);

		final HasValue hasValue = Mockito.mock(HasValue.class);
		Mockito.when(hasValue.isEmpty()).thenReturn(false);

		Mockito.when(event.getSource()).thenReturn(hasValue);

		final String value = "value";
		Mockito.when(event.getValue()).thenReturn("value");

		listener.valueChange(event);

		Mockito.verify(navigatorMock, times(1)).navigateTo(pageName + "/CHARTS/DECISION_FLOW_CHART/" + pageId + "[" +value + "]");
	}

}

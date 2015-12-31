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

import org.junit.Test;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

public class PageItemPropertyClickListenerTest extends AbstractUnitTest {


	/**
	 * Check correct page id success test.
	 */
	@Test
	public void checkCorrectPageIdSuccessTest() {

		final PageItemPropertyClickListener pageItemPropertyClickListener = new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"wrongProperty");

		final String pageId = pageItemPropertyClickListener.getPageId(new ViewRiksdagenPolitician());

		assertEquals("ErrorGettingPageId", pageId);
	}

	/**
	 * Check correct page id failure test.
	 */
	@Test
	public void checkCorrectPageIdFailureTest() {

		final PageItemPropertyClickListener pageItemPropertyClickListener = new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personId");

		final String personIdValue = "personId";
		final String pageId = pageItemPropertyClickListener.getPageId(new ViewRiksdagenPolitician().withPersonId(personIdValue));

		assertEquals(personIdValue, pageId);
	}

}

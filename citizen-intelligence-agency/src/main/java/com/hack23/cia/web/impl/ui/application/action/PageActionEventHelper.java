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
package com.hack23.cia.web.impl.ui.application.action;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;

/**
 * The Interface PageActionEventHelper.
 */
@FunctionalInterface
public interface PageActionEventHelper {

	/**
	 * Creates the page event.
	 *
	 * @param viewAction
	 *            the view action
	 * @param applicationEventGroup
	 *            the application event group
	 * @param page
	 *            the page
	 * @param pageMode
	 *            the page mode
	 * @param elementId
	 *            the element id
	 */
	void createPageEvent(ViewAction viewAction, ApplicationEventGroup applicationEventGroup, String page,
			String pageMode, String elementId);

}

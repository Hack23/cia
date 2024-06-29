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
package com.hack23.cia.web.impl.ui.application.views.common.paging;

import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Interface PagingUtil.
 */
public interface PagingUtil {

	/**
	 * Creates the paging controls.
	 *
	 * @param content       the content
	 * @param name          the name
	 * @param pageId        the page id
	 * @param size          the size
	 * @param pageNr        the page nr
	 * @param resultPerPage the result per page
	 */
	void createPagingControls(AbstractOrderedLayout content, String name, String pageId, Long size, int pageNr,
			int resultPerPage);

}

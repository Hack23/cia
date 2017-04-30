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
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;

import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Link;

/**
 * The Class AbstractAdminSystemPageModContentFactoryImpl.
 */
abstract class AbstractAdminSystemPageModContentFactoryImpl extends AbstractPageModContentFactoryImpl {

	/** The Constant LIMIT_FOR_DISPLAYING_START_END_LINKS. */
	private static final int LIMIT_FOR_DISPLAYING_START_END_LINKS = 5;

	/** The Constant PAGE_ONE. */
	private static final int PAGE_ONE = 1;

	/** The Constant PAGE_SEPARATOR. */
	private static final char PAGE_SEPARATOR = '/';

	/** The Constant SHOW. */
	private static final String SHOW = " :: Show ";

	/** The Constant RESULTS_PER_PAGE. */
	private static final String RESULTS_PER_PAGE = " results per page:";

	/** The Constant PAGES_TOTAL_RESULTS. */
	private static final String PAGES_TOTAL_RESULTS = " pages. Total results:";

	/** The Constant PAGE_HEADER. */
	private static final String PAGE_HEADER = "Page: ";

	/** The Constant NEXT_PAGE. */
	private static final String NEXT_PAGE = "next page";

	/** The Constant FIRST_PAGE. */
	private static final String FIRST_PAGE = "first page";

	/** The Constant LAST_PAGE. */
	private static final String LAST_PAGE = "last page";

	/** The Constant PREVIOUS_PAGE. */
	private static final String PREVIOUS_PAGE = "previous page";

	/** The Constant DEFAULT_RESULTS_PER_PAGE. */
	public static final int DEFAULT_RESULTS_PER_PAGE=250;

	/**
	 * Instantiates a new abstract admin system page mod content factory impl.
	 */
	AbstractAdminSystemPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the paging controls.
	 *
	 * @param content
	 *            the content
	 * @param name
	 *            the name
	 * @param pageId
	 *            the page id
	 * @param size
	 *            the size
	 * @param pageNr
	 *            the page nr
	 * @param resultPerPage
	 *            the result per page
	 */
	protected final void createPagingControls(final AbstractOrderedLayout content,final String name,final String pageId, final Long size, final int pageNr, final int resultPerPage) {
		final HorizontalLayout pagingControls = new HorizontalLayout();
		pagingControls.setSpacing(true);
		pagingControls.setMargin(true);

		final int maxPages = (int) ((size +(resultPerPage-1)) / resultPerPage);

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(PAGE_HEADER)
		.append(pageNr)
		.append(PAGE_SEPARATOR)
		.append(maxPages)
		.append(PAGES_TOTAL_RESULTS)
		.append(size)
		.append(RESULTS_PER_PAGE)
		.append(resultPerPage)
		.append(SHOW);
		final Label pageInfo = new Label(stringBuilder.toString());
		pagingControls.addComponent(pageInfo);
		pagingControls.setExpandRatio(pageInfo, ContentRatio.SMALL);


		if (pageNr > PAGE_ONE) {
			addPagingLink(PREVIOUS_PAGE,name, pageId, pageNr -1,pagingControls);
		}

		if (maxPages > PAGE_ONE && pageNr < maxPages) {
			addPagingLink(NEXT_PAGE,name, pageId, pageNr +1,pagingControls);
		}

		if (maxPages > LIMIT_FOR_DISPLAYING_START_END_LINKS && pageNr > PAGE_ONE) {
			addPagingLink(FIRST_PAGE,name, pageId, 1,pagingControls);
		}

		if (maxPages > LIMIT_FOR_DISPLAYING_START_END_LINKS && pageNr < maxPages) {
			addPagingLink(LAST_PAGE,name, pageId, maxPages,pagingControls);
		}

		content.addComponent(pagingControls);
		content.setExpandRatio(pagingControls, ContentRatio.SMALL2);

	}


	/**
	 * Adds the paging link.
	 *
	 * @param label
	 *            the label
	 * @param name
	 *            the name
	 * @param pageId
	 *            the page id
	 * @param pageNr
	 *            the page nr
	 * @param pagingControls
	 *            the paging controls
	 */
	private void addPagingLink(final String label,final String name, final String pageId, final int pageNr, final HorizontalLayout pagingControls) {
		final Link previousPageLink = getPageLinkFactory().createAdminPagingLink(label,name, pageId, String.valueOf(pageNr));
		pagingControls.addComponent(previousPageLink);
		pagingControls.setExpandRatio(previousPageLink, ContentRatio.SMALL);
	}

}

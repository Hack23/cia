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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

/**
 * The Class AbstractAdminSystemPageModContentFactoryImpl.
 */
public abstract class AbstractAdminSystemPageModContentFactoryImpl extends AbstractPageModContentFactoryImpl {

	/** The Constant PAGE_SEPARATOR. */
	private static final String PAGE_SEPARATOR = "/";
	
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
	protected AbstractAdminSystemPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the paging controls.
	 *
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
	 * @return the horizontal layout
	 */
	protected final HorizontalLayout createPagingControls(final String name,final String pageId, final Long size, final int pageNr, final int resultPerPage) {
		final HorizontalLayout pagingControls = new HorizontalLayout();
		pagingControls.setSpacing(true);
		pagingControls.setMargin(true);
		
		final int maxPages = (int) ((size +(resultPerPage-1)) / resultPerPage);
		
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(PAGE_HEADER);
		stringBuilder.append(pageNr);
		stringBuilder.append(PAGE_SEPARATOR);
		stringBuilder.append(maxPages);
		stringBuilder.append(PAGES_TOTAL_RESULTS);
		stringBuilder.append(size);
		stringBuilder.append(RESULTS_PER_PAGE);
		stringBuilder.append(resultPerPage);
		stringBuilder.append(SHOW);
		final Label pageInfo = new Label(stringBuilder.toString());
		pagingControls.addComponent(pageInfo);
		pagingControls.setExpandRatio(pageInfo, ContentRatio.SMALL);

		
		if (pageNr > 1) {		
			addPagingLink(PREVIOUS_PAGE,name, pageId, pageNr -1,pagingControls);			
		}

		if (maxPages > 1 && pageNr < maxPages) {		
			addPagingLink(NEXT_PAGE,name, pageId, pageNr +1,pagingControls);			
		}

		if (maxPages > 5 && pageNr > 1) {
			addPagingLink(FIRST_PAGE,name, pageId, 1,pagingControls);			
		}

		if (maxPages > 5 && pageNr < maxPages) {			
			addPagingLink(LAST_PAGE,name, pageId, maxPages,pagingControls);			
		}
		
		return pagingControls;
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

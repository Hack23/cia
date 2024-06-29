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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

/**
 * The Class PagingUtil.
 */
@Component
public final class PagingUtilImpl implements PagingUtil {

	/** The first page. */
	private static final String FIRST_PAGE = "first page";

	/** The last page. */
	private static final String LAST_PAGE = "last page";

	/** The limit for displaying start end links. */
	private static final int LIMIT_FOR_DISPLAYING_START_END_LINKS = 5;

	/** The next page. */
	private static final String NEXT_PAGE = "next page";

	/** The page header. */
	private static final String PAGE_HEADER = "Page: ";

	/** The page one. */
	private static final int PAGE_ONE = 1;

	/** The page separator. */
	private static final char PAGE_SEPARATOR = '/';

	/** The pages total results. */
	private static final String PAGES_TOTAL_RESULTS = " pages. Total results:";

	/** The previous page. */
	private static final String PREVIOUS_PAGE = "previous page";

	/** The results per page. */
	private static final String RESULTS_PER_PAGE = " results per page:";

	/** The show. */
	private static final String SHOW = " :: Show ";

	/** The page link factory. */
	@Autowired
	private PageLinkFactory pageLinkFactory;

	/**
	 * Adds the paging link.
	 *
	 * @param label          the label
	 * @param name           the name
	 * @param pageId         the page id
	 * @param maxPages       the max pages
	 * @param pagingControls the paging controls
	 */
	private void addPagingLink(final String label, final String name, final String pageId, final long maxPages, final HorizontalLayout pagingControls) {
		final Link previousPageLink = pageLinkFactory.createAdminPagingLink(label,name, pageId, String.valueOf(maxPages));
		pagingControls.addComponent(previousPageLink);
		pagingControls.setExpandRatio(previousPageLink, ContentRatio.SMALL);
	}

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
	@Override
	public void createPagingControls(final AbstractOrderedLayout content, final String name, final String pageId, final Long size, final int pageNr,
			final int resultPerPage) {
				final HorizontalLayout pagingControls = new HorizontalLayout();
				pagingControls.setSpacing(true);
				pagingControls.setMargin(true);

				final long maxPages = (size +resultPerPage-1) / resultPerPage;

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
					addPagingLink(PREVIOUS_PAGE,name, pageId, pageNr -1L,pagingControls);
				}

				if (maxPages > PAGE_ONE && pageNr < maxPages) {
					addPagingLink(NEXT_PAGE,name, pageId, pageNr +1L,pagingControls);
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

}

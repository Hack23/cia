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

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PageItemPropertyClickListener.
 *
 * @see com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent
 */
public final class PageItemPropertyClickListener extends AbstractPageItemRendererClickListener<Serializable> {

	/** The Constant ERROR_GETTING_PAGE_ID. */
	private static final String ERROR_GETTING_PAGE_ID = "ErrorGettingPageId";

	/** The Constant LOG_MSG_PROBLEM_GETTING_PROPERTY_FROM_OBJECT_EXCEPTION. */
	private static final String LOG_MSG_PROBLEM_GETTING_PROPERTY_FROM_OBJECT_EXCEPTION = "Problem getting property {} from object {} exception {}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PageItemPropertyClickListener.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/** The property. */
	private final String property;

	/** The lower case. */
	private boolean lowerCase = false;

	/**
	 * Instantiates a new page item property click listener.
	 *
	 * @param page
	 *            the page
	 * @param property
	 *            the property
	 */
	public PageItemPropertyClickListener(final String page, final String property) {
		super(page);
		this.property = property;
	}

	/**
	 * Instantiates a new page item property click listener.
	 *
	 * @param page the page
	 * @param property the property
	 * @param lowerCase the lower case
	 */
	public PageItemPropertyClickListener(final String page, final String property, final boolean lowerCase) {
		super(page);
		this.property = property;
		this.lowerCase = lowerCase;
	}

	@Override
	protected String getPageId(final Serializable t) {
		try {
			if (lowerCase) {
				return BeanUtils.getProperty(t, property).toLowerCase(Locale.ENGLISH);
			} else {
				return BeanUtils.getProperty(t, property);
			}
		} catch (IllegalAccessException | InvocationTargetException |
	            NoSuchMethodException e) {
			LOGGER.warn(LOG_MSG_PROBLEM_GETTING_PROPERTY_FROM_OBJECT_EXCEPTION,property,t,e);
			return ERROR_GETTING_PAGE_ID;
		}
	}

}

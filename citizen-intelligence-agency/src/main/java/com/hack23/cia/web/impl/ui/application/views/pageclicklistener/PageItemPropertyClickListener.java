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

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hack23.cia.model.common.api.ModelObject;

public final class PageItemPropertyClickListener extends AbstractPageItemRendererClickListener<ModelObject> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PageItemPropertyClickListener.class);


	/** The property. */
	private final String property;

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
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.web.impl.ui.application.views.common.
	 * AbstractPageItemRendererClickListener#getPageId(java.lang.Object)
	 */
	@Override
	protected String getPageId(final ModelObject t) {
		try {
			return BeanUtils.getProperty(t, property);
		} catch (IllegalAccessException | InvocationTargetException |
	            NoSuchMethodException e) {
			LOGGER.warn("Problem getting property {} from object {}",property,t,e);
			return "ErrorGettingPageId";
		}
	}

}

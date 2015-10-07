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

import org.apache.commons.beanutils.BeanUtils;

import com.hack23.cia.model.common.api.ModelObject;
import com.hack23.cia.web.impl.ui.application.views.common.AbstractPageItemRendererClickListener;

public final class PageItemPropertyClickListener extends AbstractPageItemRendererClickListener<ModelObject> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
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

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.web.impl.ui.application.views.common.
	 * AbstractPageItemRendererClickListener#getPageId(java.lang.Object)
	 */
	@Override
	protected String getPageId(final ModelObject t) {
		try {
			return BeanUtils.getProperty(t, property);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}

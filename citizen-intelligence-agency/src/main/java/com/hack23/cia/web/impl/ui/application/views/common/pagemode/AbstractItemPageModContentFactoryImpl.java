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
package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

/**
 * The Class AbstractItemPageModContentFactoryImpl.
 *
 * @param <T> the generic type
 */
public abstract class AbstractItemPageModContentFactoryImpl<T> extends AbstractPageModContentFactoryImpl {

	/**
	 * Instantiates a new abstract item page mod content factory impl.
	 */
	protected AbstractItemPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Gets the item.
	 *
	 * @param parameters the parameters
	 * @return the item
	 */
	protected abstract T getItem(String parameters);

	@Override
	public final boolean validReference(final String parameters) {
		return getItem(parameters) != null;
	}

}

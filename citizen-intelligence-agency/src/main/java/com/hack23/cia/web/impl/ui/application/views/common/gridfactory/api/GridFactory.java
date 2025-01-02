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
package com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api;

import java.io.Serializable;
import java.util.List;

import com.hack23.cia.web.impl.ui.application.views.common.converters.ListPropertyConverter;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemRendererClickListener;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * A factory for creating Grid objects.
 */
public interface GridFactory extends Serializable {

	/**
	 * Creates a new Grid object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param panelContent
	 *            the panel content
	 * @param dataType
	 *            the data type
	 * @param datasource
	 *            the datasource
	 * @param caption
	 *            the caption
	 * @param columnOrder
	 *            the column order
	 * @param hideColumns
	 *            the hide columns
	 * @param listener
	 *            the listener
	 * @param actionId
	 *            the action id
	 * @param collectionPropertyConverters
	 *            the collection property converters
	 */
	<T extends Serializable> void createBasicBeanItemGrid(AbstractOrderedLayout panelContent,Class<T> dataType, List<T> datasource, String caption, String[] columnOrder,
			String[] hideColumns, PageItemRendererClickListener<?> listener, String actionId, ListPropertyConverter[] collectionPropertyConverters);

	/**
	 * Creates a new Grid object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param panelContent
	 *            the panel content
	 * @param dataType
	 *            the data type
	 * @param datasource
	 *            the datasource
	 * @param caption
	 *            the caption
	 * @param nestedProperties
	 *            the nested properties
	 * @param columnOrder
	 *            the column order
	 * @param hideColumns
	 *            the hide columns
	 * @param listener
	 *            the listener
	 * @param actionId
	 *            the action id
	 * @param collectionPropertyConverters
	 *            the collection property converters
	 */
	<T extends Serializable> void createBasicBeanItemNestedPropertiesGrid(AbstractOrderedLayout panelContent,Class<T> dataType,List<T> datasource, String caption, String[] nestedProperties,String[] columnOrder, String[] hideColumns,
			PageItemRendererClickListener<?> listener, String actionId, ListPropertyConverter[] collectionPropertyConverters);

}

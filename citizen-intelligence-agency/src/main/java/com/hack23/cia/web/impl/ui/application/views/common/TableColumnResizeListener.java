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
package com.hack23.cia.web.impl.ui.application.views.common;

import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnResizeEvent;

/**
 * The listener interface for receiving tableColumnResize events. The class that
 * is interested in processing a tableColumnResize event implements this
 * interface, and the object created with that class is registered with a
 * component using the component's
 * <code>addTableColumnResizeListener</code> method. When
 * the tableColumnResize event occurs, that object's appropriate
 * method is invoked.
 *
 */
public final class TableColumnResizeListener implements
Table.ColumnResizeListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The table. */
	private final Table table;

	/**
	 * Instantiates a new table column resize listener.
	 *
	 * @param table
	 *            the table
	 */
	public TableColumnResizeListener(final Table table) {
		this.table = table;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.Table.ColumnResizeListener#columnResize(com.vaadin.ui.Table.ColumnResizeEvent)
	 */
	@Override
	public void columnResize(final ColumnResizeEvent event) {
		final int width = event.getCurrentWidth();
		final String column = (String) event.getPropertyId();
		table.setColumnFooter(column, String.valueOf(width) + "px");
	}
}
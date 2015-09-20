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
package com.hack23.cia.web.impl.ui.application.views.common.gridfactory;

import org.springframework.stereotype.Service;
import org.vaadin.gridutil.cell.GridCellFilter;

import com.hack23.cia.web.impl.ui.application.views.common.AbstractPageItemRendererClickListener;
import com.vaadin.data.Container;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;

@Service
public final class GridFactoryImpl implements GridFactory {


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory#createBasicBeanItemGrid(com.vaadin.data.Container.Indexed, java.lang.String, java.lang.Object[], java.lang.Object[], java.lang.String, com.hack23.cia.web.impl.ui.application.views.common.AbstractPageItemRendererClickListener)
	 */
	@Override
	public Grid createBasicBeanItemGrid(final Container.Indexed datasource, final String caption,
			final Object[] columnOrder, final Object[] hideColumns, final String idProprty,
			final AbstractPageItemRendererClickListener<?> listener) {
		final Grid grid = new Grid(datasource);

		grid.setCaption(caption);
		grid.setSelectionMode(SelectionMode.SINGLE);
		if (columnOrder != null) {
			grid.setColumnOrder(columnOrder);
		}

		if (hideColumns != null) {
			for (final Object o : hideColumns) {
				grid.removeColumn(o);
			}
		}

		if (idProprty != null && listener != null) {
			final Column column = grid.getColumn(idProprty);
			column.setRenderer(new ButtonRenderer(listener));
		}

		if (listener != null) {
			grid.addSelectionListener(listener);
		}

		grid.setSizeFull();

		grid.setImmediate(true);
		grid.setReadOnly(true);
		// HeaderRow headerRow = grid.appendHeaderRow();
		// FooterRow footerRow = grid.appendFooterRow();

		if (columnOrder != null) {
			final GridCellFilter gridCellFilter = new GridCellFilter(grid);

			for (final Object column : columnOrder) {
				if (grid.getColumn(column) != null) {
					gridCellFilter.setTextFilter(column.toString(), true, true);
				}
			}

		}

		return grid;

	}


}

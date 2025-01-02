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
package com.hack23.cia.web.impl.ui.application.views.common.gridfactory.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.vaadin.gridutil.cell.GridCellFilter;

import com.hack23.cia.web.impl.ui.application.views.common.converters.ListPropertyConverter;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemRendererClickListener;
import com.vaadin.data.BeanPropertySet;
import com.vaadin.data.ValueProvider;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;

/**
 * The Class GridFactoryImpl.
 */
@Service
public final class GridFactoryImpl implements GridFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GridFactoryImpl.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new grid factory impl.
	 */
	public GridFactoryImpl() {
		super();
	}

	/**
	 * Configure column orders and hidden fields.
	 *
	 * @param columnOrder
	 *            the column order
	 * @param hideColumns
	 *            the hide columns
	 * @param grid
	 *            the grid
	 */
	private static void configureColumnOrdersAndHiddenFields(final String[] columnOrder, final String[] hideColumns,
			final Grid grid) {
		if (columnOrder != null) {
			grid.setColumnOrder(columnOrder);
		}

		if (hideColumns != null) {
			for (final String o : hideColumns) {
				grid.removeColumn(o);
			}
		}
	}

	/**
	 * Configure listeners.
	 *
	 * @param listener
	 *            the listener
	 * @param grid
	 *            the grid
	 */
	private static void configureListeners(final SelectionListener listener, final Grid grid) {

		if (listener != null) {
			grid.addSelectionListener(listener);
		}
	}

	/**
	 * Creates the grid cell filter.
	 *
	 * @param columnOrder
	 *            the column order
	 * @param grid
	 *            the grid
	 * @param dataType
	 *            the data type
	 */
	private static void createGridCellFilter(final String[] columnOrder, final Grid grid, final Class dataType) {
		if (columnOrder != null) {
			final GridCellFilter filter = new GridCellFilter(grid, dataType);
			for (final String column : columnOrder) {
				if (grid.getColumn(column) != null) {
					filter.setTextFilter(column, true, true);
				}
			}
		}
	}

	private static <T extends Serializable> void createNestedProperties(final Grid<T> grid,
			final String[] nestedProperties) {
		if (nestedProperties != null) {
			for (final String property : nestedProperties) {
				final Column<T, ?> addColumn = grid.addColumn(new BeanNestedPropertyValueProvider<T>(property));
				addColumn.setId(property);
			}
		}
	}

	/**
	 * Sets the column converters.
	 *
	 * @param collectionPropertyConverter
	 *            the collection property converter
	 * @param grid
	 *            the grid
	 */
	private static void setColumnConverters(final ListPropertyConverter[] collectionPropertyConverter,
			final Grid grid) {
		if (collectionPropertyConverter != null) {
			for (final ListPropertyConverter converter : collectionPropertyConverter) {
				grid.removeColumn(converter.getColumn());
				final Column column = grid.addColumn(converter);
				column.setCaption(WordUtils.capitalize(converter.getColumn()));
				column.setId(converter.getColumn());
			}
		}
	}

	@Override
	public <T extends Serializable> void createBasicBeanItemGrid(final AbstractOrderedLayout panelContent,
			final Class<T> dataType, final List<T> datasource, final String caption, final String[] columnOrder,
			final String[] hideColumns, final PageItemRendererClickListener<?> listener, final String actionId,
			final ListPropertyConverter[] collectionPropertyConverters) {
		createBasicBeanItemNestedPropertiesGrid(panelContent, dataType, datasource, caption, null, columnOrder,
				hideColumns, listener, actionId, collectionPropertyConverters);

	}

	@Override
	public <T extends Serializable> void createBasicBeanItemNestedPropertiesGrid(
			final AbstractOrderedLayout panelContent, final Class<T> dataType, final List<T> datasource,
			final String caption, final String[] nestedProperties, final String[] columnOrder,
			final String[] hideColumns, final PageItemRendererClickListener<?> listener, final String actionId,
			final ListPropertyConverter[] collectionPropertyConverters) {

		final Grid<T> grid = Grid.withPropertySet(BeanPropertySet.get(dataType));
		grid.setCaption(caption);

		grid.setItems(datasource.stream().filter(Objects::nonNull).toList());

		grid.setSelectionMode(SelectionMode.SINGLE);

		createNestedProperties(grid, nestedProperties);

		setColumnConverters(collectionPropertyConverters, grid);

		configureColumnOrdersAndHiddenFields(columnOrder, hideColumns, grid);

		configureListeners(listener, grid);


		grid.setSizeFull();

		grid.setStyleName("Level2Header");

		createGridCellFilter(columnOrder, grid, dataType);

		grid.setResponsive(true);

		panelContent.addComponent(grid);
		panelContent.setExpandRatio(grid, ContentRatio.GRID);
	}

	/**
	 * The Class BeanNestedPropertyValueProvider.
	 *
	 * @param <T>
	 *            the generic type
	 */
	public static final class BeanNestedPropertyValueProvider<T> implements ValueProvider<T, String> {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The property. */
		private final String property;

		/**
		 * Instantiates a new bean nested property value provider.
		 *
		 * @param property
		 *            the property
		 */
		public BeanNestedPropertyValueProvider(final String property) {
			super();
			this.property = property;
		}

		@Override
		public String apply(final T source) {
			try {
				return BeanUtils.getProperty(source, property);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				LOGGER.warn("Problem getting property : {} from source : {} , exception: {}", property, source, e);
				return "";
			}
		}

	}

}

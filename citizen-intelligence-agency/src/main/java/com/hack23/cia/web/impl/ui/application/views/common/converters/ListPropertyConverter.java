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
package com.hack23.cia.web.impl.ui.application.views.common.converters;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.data.ValueProvider;

/**
 * The Class ListPropertyRenderer.
 */
public final class ListPropertyConverter implements Converter<String, List<?>>,ValueProvider<Object, String> {

	private static final char CONTENT_SEPARATOR = ' ';

	private static final char END_TAG = ']';

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ListPropertyConverter.class);

	private static final PropertyUtilsBean PROPERTY_UTILS_BEAN= new PropertyUtilsBean();

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private static final char START_TAG = '[';

	/** The column. */
	private final String column;

	private final String fallbackColumn;

	/** The property. */
	private final String property;

	/**
	 * Instantiates a new collection property converter.
	 * @param property
	 *            the property
	 * @param column
	 *            the column
	 */
	public ListPropertyConverter(final String property, final String column) {
		super();
		this.property = property;
		this.column = column;
		this.fallbackColumn = null;
	}

	/**
	 * Instantiates a new list property converter.
	 * @param property
	 *            the property
	 * @param column
	 *            the column
	 * @param fallbackColumn
	 *            the fallback column
	 */
	public ListPropertyConverter(final String property, final String column, final String fallbackColumn) {
		super();
		this.property = property;
		this.column = column;
		this.fallbackColumn = fallbackColumn;
	}


	/**
	 * Adds the fallback value.
	 *
	 * @param stringBuilder the string builder
	 * @param object        the object
	 * @throws IllegalAccessException    the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException     the no such method exception
	 */
	private void addFallbackValue(final StringBuilder stringBuilder, final Object object)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (fallbackColumn != null) {
			final String beanPropertyFallBack = BeanUtils.getProperty(object, fallbackColumn);
			if (beanPropertyFallBack != null) {
				stringBuilder.append(beanPropertyFallBack);
			}
		}
	}

	/**
	 * Append object presentation.
	 *
	 * @param stringBuilder
	 *            the string builder
	 * @param object
	 *            the object
	 */
	private void appendObjectPresentation(final StringBuilder stringBuilder, final Object object) {
		try {
			final String beanProperty = BeanUtils.getProperty(object, property);

			if (beanProperty != null) {
				stringBuilder.append(beanProperty);
			} else {
				addFallbackValue(stringBuilder, object);
			}

		} catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			LOGGER.warn("Problem getting property {}, object {} , exception {}", property, object, e);
		}
		stringBuilder.append(CONTENT_SEPARATOR);
	}

	@Override
	public String apply(final Object source) {
		List<?> list;
		try {
			list = (List<?>) PROPERTY_UTILS_BEAN.getProperty(source, column);
			return convertToPresentation(list,null);
		} catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			LOGGER.warn("Problem getting list {}, object {} , exception {}", property, column, e);
		}
		return "";
	}

	@Override
	public Result<List<?>> convertToModel(final String value, final ValueContext context) {
		return Result.ok(new ArrayList<>());
	}

	@Override
	public String convertToPresentation(final List value, final ValueContext context) {
		final StringBuilder stringBuilder = new StringBuilder();

		if (value != null) {
			stringBuilder.append(START_TAG);
			for (final Object object : value) {
				appendObjectPresentation(stringBuilder, object);
			}
			stringBuilder.append(END_TAG);
		}


		return stringBuilder.toString();
	}

	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

}

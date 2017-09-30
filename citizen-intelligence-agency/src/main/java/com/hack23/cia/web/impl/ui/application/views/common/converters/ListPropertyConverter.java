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
package com.hack23.cia.web.impl.ui.application.views.common.converters;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

/**
 * The Class ListPropertyRenderer.
 */
public final class ListPropertyConverter implements Converter<String, List> {

	private static final char CONTENT_SEPARATOR = ' ';

	private static final String START_TAG = "[ ";

	private static final char END_TAG = ']';

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The property. */
	private final String property;

	/** The column. */
	private final String column;

	private final String fallbackColumn;

	/**
	 * Instantiates a new collection property converter.
	 *
	 * @param modelType
	 *            the model type
	 * @param property
	 *            the property
	 * @param column
	 *            the column
	 */
	public ListPropertyConverter(final Class<List> modelType, final String property, final String column) {
		super();
		this.property = property;
		this.column = column;
		this.fallbackColumn = null;
	}

	/**
	 * Instantiates a new list property converter.
	 *
	 * @param modelType
	 *            the model type
	 * @param property
	 *            the property
	 * @param column
	 *            the column
	 * @param fallbackColumn
	 *            the fallback column
	 */
	public ListPropertyConverter(final Class<List> modelType, final String property, final String column,final String fallbackColumn) {
		super();
		this.property = property;
		this.column = column;
		this.fallbackColumn = fallbackColumn;
	}


	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	@Override
	public Result<List> convertToModel(final String value, final ValueContext context) {
		return Result.ok(new ArrayList<>());
	}

	@Override
	public String convertToPresentation(final List value, final ValueContext context) {
		final StringBuilder stringBuilder = new StringBuilder().append(START_TAG);

		if (value != null) {
			for (final Object object : value) {
				try {
					final String beanProperty = BeanUtils.getProperty(object, property);

					if (beanProperty != null) {
						stringBuilder.append(beanProperty);
					} else {
						if (fallbackColumn != null) {
							final String beanPropertyFallBack = BeanUtils.getProperty(object, fallbackColumn);
							if (beanPropertyFallBack != null) {
								stringBuilder.append(beanPropertyFallBack);
							}
						}


					}

				} catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				}
				stringBuilder.append(CONTENT_SEPARATOR);
			}
		}

		stringBuilder.append(END_TAG);

		return stringBuilder.toString();
	}

}

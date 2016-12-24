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
package com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;

import com.vaadin.data.util.converter.Converter;

/**
 * The Class ListPropertyRenderer.
 */
public final class ListPropertyConverter implements Converter<String, List> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The model type. */
	private final Class<List> modelType;

	/** The property. */
	private final String property;

	/** The column. */
	private final String column;

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
		this.modelType = modelType;
		this.property = property;
		this.column = column;
	}

	/**
	 * Gets the presentation type.
	 *
	 * @return the presentation type
	 */
	@Override
	public Class<String> getPresentationType() {
		return String.class;
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
	public List convertToModel(String value, Class<? extends List> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		return null;
	}

	@Override
	public String convertToPresentation(List value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		StringBuilder stringBuilder = new StringBuilder().append("[ ");

		for (Object object : value) {
			try {
				stringBuilder.append(BeanUtils.getProperty(object, property));
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new ConversionException(e);
			}
			stringBuilder.append(" ");
		}

		stringBuilder.append("]");

		return stringBuilder.toString();
	}

	@Override
	public Class<List> getModelType() {
		return modelType;
	}

}

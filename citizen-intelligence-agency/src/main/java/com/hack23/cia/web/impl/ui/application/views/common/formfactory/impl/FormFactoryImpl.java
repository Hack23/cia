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
package com.hack23.cia.web.impl.ui.application.views.common.formfactory.impl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.formfactory.api.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.CommitFormWrapperClickListener;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.Converter;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.converter.StringToBigIntegerConverter;
import com.vaadin.data.converter.StringToBooleanConverter;
import com.vaadin.data.converter.StringToDateConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class FormFactoryImpl.
 */
@Service
public final class FormFactoryImpl implements FormFactory {

	/** The Constant HIDDEN_FIELD_NAME. */
	private static final String HIDDEN_FIELD_NAME = "password";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FormFactoryImpl.class);

	/** The Constant SIZE_FOR_GRID. */
	private static final int SIZE_FOR_GRID = 8;

	/** The Constant TYPE_CONVERTERS. */
	private static final Map<Class<?>, Converter<String, ?>> TYPE_CONVERTERS = new HashMap<>();

	static {
	    TYPE_CONVERTERS.put(Date.class, new StringToDateConverter());
	    TYPE_CONVERTERS.put(Integer.class, new StringToIntegerConverter("Input value should be an integer"));
	    TYPE_CONVERTERS.put(Long.class, new StringToLongConverter("Input value should be a long"));
	    TYPE_CONVERTERS.put(BigInteger.class, new StringToBigIntegerConverter("Input value should be a biginteger"));
	    TYPE_CONVERTERS.put(BigDecimal.class, new StringToBigDecimalConverter("Input value should be a bigdecimal"));
	    TYPE_CONVERTERS.put(Boolean.class, new StringToBooleanConverter("Input value should be a boolean"));

	    // Map primitive types to their wrapper classes
	    TYPE_CONVERTERS.put(int.class, TYPE_CONVERTERS.get(Integer.class));
	    TYPE_CONVERTERS.put(long.class, TYPE_CONVERTERS.get(Long.class));
	    TYPE_CONVERTERS.put(boolean.class, TYPE_CONVERTERS.get(Boolean.class));
	}

	/**
	 * Default constructor for FormFactoryImpl.
	 */
	public FormFactoryImpl() {
		// Default constructor
	}

	/**
	 * Creates the display property converters.
	 *
	 * @param                     <T> the generic type
	 * @param displayProperties   the display properties
	 * @param formContent         the form content
	 * @param binder              the binder
	 * @param propertyDescriptors the property descriptors
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T extends Serializable> void createDisplayPropertyConverters(final List<String> displayProperties,
			final ComponentContainer formContent, final Binder<T> binder, final PropertyDescriptor[] propertyDescriptors) {
		for (final String property : displayProperties) {
			final Class<?> typeOfProperty = getTypeOfProperty(propertyDescriptors, property);

			if (typeOfProperty != null) {
				final AbstractField<?> field = new TextField();
				field.setReadOnly(true);
				field.setCaption(property);
				field.setWidth(ContentSize.FULL_SIZE);
				formContent.addComponent(field);
				final Converter converter = getConverterForType(typeOfProperty);

				if (converter != null) {
					binder.forField(field).withConverter(converter).bind(property);
				} else {
					binder.forField(field).bind(property);
				}
			}
		}
	}


	/**
	 * Creates the field.
	 *
	 * @param property the property
	 * @return the abstract field
	 */
	private static AbstractField<?> createField(final String property) {
		if (StringUtils.containsIgnoreCase(property,HIDDEN_FIELD_NAME)) {
			return new PasswordField();
		} else {
			return new TextField();
		}
	}


	/**
	 * Gets the converter for type.
	 *
	 * @param typeOfProperty
	 *            the type of property
	 * @return the converter for type
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Converter<String, ?> getConverterForType(final Class<?> typeOfProperty) {
	    if (typeOfProperty == null) {
	        return null;
	    }

	    // Handle enums separately since they're dynamic
	    if (typeOfProperty.isEnum()) {
	        return new StringToEnumConverter();
	    }

	    // Look up converter from map
	    return TYPE_CONVERTERS.get(typeOfProperty);
	}

	/**
	 * Gets the type of property.
	 *
	 * @param propertyDescriptors
	 *            the property descriptors
	 * @param property
	 *            the property
	 * @return the type of property
	 */
	private static Class<?> getTypeOfProperty(final PropertyDescriptor[] propertyDescriptors, final String property) {

		for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor.getName().equalsIgnoreCase(property)) {
				return propertyDescriptor.getPropertyType();
			}
		}
		return null;
	}

	@Override
	public <T extends Serializable> void addFormPanelTextFields(final AbstractOrderedLayout panelContent, final T item,
			final Class<T> beanType, final List<String> displayProperties) {


		final Panel formPanel = new Panel();
		formPanel.setSizeFull();


		panelContent.addComponent(formPanel);
		if (displayProperties.size() > SIZE_FOR_GRID) {
			panelContent.setExpandRatio(formPanel, ContentRatio.GRID);
		}
		else {
			panelContent.setExpandRatio(formPanel, ContentRatio.SMALL_GRID);
		}


		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);
		formContent.setWidth(ContentSize.FULL_SIZE);

		final Binder<T> binder = new Binder<>(beanType);
		binder.setBean(item);
		binder.setReadOnly(true);

		PropertyDescriptor[] propertyDescriptors=null;
		try {
			final BeanInfo info = Introspector.getBeanInfo(item.getClass());
			propertyDescriptors = info.getPropertyDescriptors();
		} catch (final IntrospectionException exception) {
			LOGGER.error("No able to getfieldtypes for type:+ item.getClass()", exception);
		}

		createDisplayPropertyConverters(displayProperties, formContent, binder, propertyDescriptors);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T extends Serializable> void addRequestInputFormFields(final FormLayout panelContent, final T item,
			final Class<T> beanType, final List<String> displayProperties,final String buttonLabel,final ClickListener buttonListener) {
		final BeanValidationBinder<T> binder = new BeanValidationBinder<>(beanType);
		binder.setBean(item);
		binder.setReadOnly(true);

		for (final String property : displayProperties) {

			final AbstractField buildAndBind = createField(property);
			binder.bind(buildAndBind,property);
			buildAndBind.setCaption(property);
			buildAndBind.setId(MessageFormat.format("{0}.{1}", buttonLabel, property));
			buildAndBind.setReadOnly(false);
			buildAndBind.setWidth(ContentSize.HALF_SIZE);

			panelContent.addComponent(buildAndBind);
		}

		final VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setWidth("50%");

		final Button button = new Button(buttonLabel,new CommitFormWrapperClickListener(binder,buttonListener));
		button.setId(buttonLabel);
		button.setWidth("100%");
		button.setIcon(VaadinIcons.BULLSEYE);
		button.setEnabled(false);
		binder.addStatusChangeListener(event -> button.setEnabled(event.getBinder().isValid()));


		verticalLayout.addComponent(button);
		verticalLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);

		panelContent.addComponent(verticalLayout);
	}

}

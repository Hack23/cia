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
package com.hack23.cia.web.impl.ui.application.views.common.formfactory;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.CommitFormWrapperClickListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;

/**
 * The Class FormFactoryImpl.
 */
@Service
public final class FormFactoryImpl implements FormFactory {

	/** The Constant LOG_MSG_PROPERTY. */
	private static final String LOG_MSG_PROPERTY = "property:{}";

	/** The Constant PASSWORD. */
	private static final String HIDDEN_FIELD_NAME = "password";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FormFactoryImpl.class);

	@Override
	public <T extends Serializable> void addTextFields(final Layout panelContent, final BeanItem<T> item, final Class<T> beanType,
			final List<String> displayProperties) {

		final BeanFieldGroup<T> fieldGroup = new BeanFieldGroup<>(beanType);
		fieldGroup.setItemDataSource(item);
		fieldGroup.setReadOnly(true);

		for (final String property : displayProperties) {
			final Field<?> buildAndBind = fieldGroup.buildAndBind(property);
			buildAndBind.setWidth(ContentSize.FULL_SIZE);

			panelContent.addComponent(buildAndBind);
		}
		final Collection<Object> unboundPropertyIds = fieldGroup.getUnboundPropertyIds();
		for (final Object property : unboundPropertyIds) {
			LOGGER.debug(LOG_MSG_PROPERTY, property);
		}

	}

	@Override
	public <T extends Serializable> void addRequestInputFormFields(final Layout panelContent, final BeanItem<T> item,
			final Class<T> beanType, final List<String> displayProperties,final String buttonLabel,final ClickListener buttonListener) {
		final BeanFieldGroup<T> fieldGroup = new BeanFieldGroup<>(beanType);
		fieldGroup.setItemDataSource(item);
		fieldGroup.setReadOnly(true);

		for (final String property : displayProperties) {

			final Field<?> buildAndBind;
			if (property.contains(HIDDEN_FIELD_NAME)) {
				buildAndBind = fieldGroup.buildAndBind(property,property, PasswordField.class);
			} else {
				buildAndBind = fieldGroup.buildAndBind(property);
			}

			buildAndBind.setId(MessageFormat.format("{0}.{1}", buttonLabel, property));
			buildAndBind.setReadOnly(false);
			buildAndBind.setWidth(ContentSize.HALF_SIZE);

			panelContent.addComponent(buildAndBind);
		}
		final Collection<Object> unboundPropertyIds = fieldGroup.getUnboundPropertyIds();
		for (final Object property : unboundPropertyIds) {
			LOGGER.debug(LOG_MSG_PROPERTY, property);
		}


		final Button button = new Button(buttonLabel,new CommitFormWrapperClickListener(fieldGroup,buttonListener));
		button.setId(buttonLabel);
		panelContent.addComponent(button);

	}



}

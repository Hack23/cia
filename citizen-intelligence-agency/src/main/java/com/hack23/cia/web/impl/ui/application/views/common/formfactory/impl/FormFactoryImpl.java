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
package com.hack23.cia.web.impl.ui.application.views.common.formfactory.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.formfactory.api.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.CommitFormWrapperClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.PasswordField;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class FormFactoryImpl.
 */
@Service
public final class FormFactoryImpl implements FormFactory {

	/** The Constant LOG_MSG_PROPERTY. */
	private static final String LOG_MSG_PROPERTY = "property:{}";

	/** The Constant HIDDEN_FIELD_NAME. */
	private static final String HIDDEN_FIELD_NAME = "password";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FormFactoryImpl.class);

	@Override
	public <T extends Serializable> void addRequestInputFormFields(final FormLayout panelContent, final BeanItem<T> item,
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

		final VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setWidth("50%");

		final Button button = new Button(buttonLabel,new CommitFormWrapperClickListener(fieldGroup,buttonListener));
		button.setId(buttonLabel);
		button.setWidth("25%");
		button.setIcon(FontAwesome.PAW);

		verticalLayout.addComponent(button);
		verticalLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);

		panelContent.addComponent(verticalLayout);
	}

	@Override
	public <T extends Serializable> void addFormPanelTextFields(final AbstractOrderedLayout panelContent, final BeanItem<T> item,
			final Class<T> beanType, final List<String> displayProperties) {


		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		
		panelContent.addComponent(formPanel);
		if (displayProperties.size() > 8) {
			panelContent.setExpandRatio(formPanel, ContentRatio.GRID);
		}
		else {
			panelContent.setExpandRatio(formPanel, ContentRatio.SMALL_GRID);				
		}
		

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);
		formContent.setWidth(ContentSize.FULL_SIZE);

		final BeanFieldGroup<T> fieldGroup = new BeanFieldGroup<>(beanType);
		fieldGroup.setItemDataSource(item);
		fieldGroup.setReadOnly(true);

		for (final String property : displayProperties) {

			final Field<?> buildAndBind = fieldGroup.buildAndBind(property);
			buildAndBind.setWidth(ContentSize.FULL_SIZE);

			formContent.addComponent(buildAndBind);
		}
		final Collection<Object> unboundPropertyIds = fieldGroup.getUnboundPropertyIds();
		for (final Object property : unboundPropertyIds) {
			LOGGER.debug(LOG_MSG_PROPERTY, property);
		}


	}



}

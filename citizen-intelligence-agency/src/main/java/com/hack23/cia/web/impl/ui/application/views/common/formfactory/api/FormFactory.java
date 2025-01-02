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
package com.hack23.cia.web.impl.ui.application.views.common.formfactory.api;

import java.io.Serializable;
import java.util.List;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;


/**
 * A factory for creating Form objects.
 */
public interface FormFactory {

	/**
	 * Adds the form panel text fields.
	 *
	 * @param <T>
	 *            the generic type
	 * @param panelContent
	 *            the panel content
	 * @param item
	 *            the item
	 * @param beanType
	 *            the bean type
	 * @param displayProperties
	 *            the display properties
	 */
	<T extends Serializable> void addFormPanelTextFields(AbstractOrderedLayout panelContent, T item, Class<T> beanType, List<String> displayProperties);


	/**
	 * Adds the request input form fields.
	 *
	 * @param <T>
	 *            the generic type
	 * @param panelContent
	 *            the panel content
	 * @param item
	 *            the item
	 * @param beanType
	 *            the bean type
	 * @param displayProperties
	 *            the display properties
	 * @param buttonLabel
	 *            the button label
	 * @param buttonListener
	 *            the button listener
	 */
	<T extends Serializable> void addRequestInputFormFields(FormLayout panelContent, T item, Class<T> beanType, List<String> displayProperties,String buttonLabel,ClickListener buttonListener);


}

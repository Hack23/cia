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
 *	$Id: AbstractView.java 6118 2015-07-31 17:41:55Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/citizen-intelligence-agency/src/main/java/com/hack23/cia/web/impl/ui/application/views/common/AbstractView.java $
*/
package com.hack23.cia.web.impl.ui.application.views.common;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageLinkFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

/**
 * The Class AbstractView.
 */
public abstract class AbstractView extends Panel implements View {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractView.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The page link factory. */
	@Autowired
	protected transient PageLinkFactory pageLinkFactory;

	/**
	 * Instantiates a new abstract view.
	 */
	protected AbstractView() {
		super();
	}

	/**
	 * Adds the text fields.
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
	protected final <T> void addTextFields(final Layout panelContent, final BeanItem<T> item,
			final Class<T> beanType, final List<String> displayProperties) {

		final BeanFieldGroup<T> fieldGroup = new BeanFieldGroup<>(beanType);
		fieldGroup.setItemDataSource(item);
		fieldGroup.setReadOnly(true);

		for (final String property : displayProperties) {
			final Field<?> buildAndBind = fieldGroup.buildAndBind(property);
			buildAndBind.setWidth("100%");
			panelContent.addComponent(buildAndBind);
		}
		final Collection<Object> unboundPropertyIds = fieldGroup.getUnboundPropertyIds();
		for (final Object property : unboundPropertyIds) {
			LOGGER.info("propery:{}", property);
		}
	}

}

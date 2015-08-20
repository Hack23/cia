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
package com.hack23.cia.web.impl.ui.application.views.user.common;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageLinkFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractUserView.
 */
public abstract class AbstractUserView extends VerticalLayout implements View {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUserView.class);

	/** The Constant DAYS_PER_STANDARD_YEAR. */
	private static final long DAYS_PER_STANDARD_YEAR = 365L;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The barmenu. */
	final MenuBar barmenu = new MenuBar();

	/** The panel. */
	private Panel panel;

	/** The page link factory. */
	@Autowired
	protected transient PageLinkFactory pageLinkFactory;

	/**
	 * Instantiates a new abstract user view.
	 */
	protected AbstractUserView() {
		super();
	}

	/**
	 * Creates the basic layout with panel and footer.
	 *
	 * @param panelName
	 *            the panel name
	 */
	protected final void createBasicLayoutWithPanelAndFooter(final String panelName) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		final VerticalLayout pageModeContent = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		layout.addComponent(pageModeContent);

		pageModeContent.addComponent(new Label("overview"));
		pageModeContent.addComponent(barmenu);

		panel = new Panel(panelName);

		panel.setSizeFull();
		pageModeContent.addComponent(panel);
		pageModeContent.setExpandRatio(panel, 1.0f);

		pageModeContent.addComponent(pageLinkFactory.createMainViewPageLink());
		addComponent(layout);
		setExpandRatio(layout, 1.0f);

	}

	/**
	 * Gets the barmenu.
	 *
	 * @return the barmenu
	 */
	public MenuBar getBarmenu() {
		return barmenu;
	}


	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	protected final Panel getPanel() {
		return panel;
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
			panelContent.addComponent(fieldGroup.buildAndBind(property));
		}
		final Collection<Object> unboundPropertyIds = fieldGroup.getUnboundPropertyIds();
		for (final Object property : unboundPropertyIds) {
			LOGGER.info("property:{}",property);
		}
	}


	/**
	 * Convert to years string.
	 *
	 * @param totalDays
	 *            the total days
	 * @return the string
	 */
	protected final String convertToYearsString(final long totalDays) {
		final long years = totalDays / DAYS_PER_STANDARD_YEAR;
		final long days = totalDays - years * DAYS_PER_STANDARD_YEAR;

		return Long.toString(years) + " Years " + Long.toString(days) + " days";
	}

}

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
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractBasicPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.paging.PagingUtil;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Component;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;

/**
 * The Class AbstractAdminSystemPageModContentFactoryImpl.
 */
public abstract class AbstractAdminSystemPageModContentFactoryImpl extends AbstractBasicPageModContentFactoryImpl {

	@Autowired
	private PagingUtil pagingUtil;

	private final String viewName;

	/**
	 * Instantiates a new abstract admin system page mod content factory impl.
	 *
	 * @param viewName the view name
	 */
	public AbstractAdminSystemPageModContentFactoryImpl(final String viewName) {
		super();
		this.viewName = viewName;
	}

	/**
	 * Gets the paging util.
	 *
	 * @return the paging util
	 */
	protected final PagingUtil getPagingUtil() {
		return pagingUtil;
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return viewName.equals(page);
	}

	/**
	 * Creates a consistent HorizontalLayout for panel content.
	 *
	 * @return the horizontal layout
	 */
	protected HorizontalLayout createHorizontalLayout() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);
		return layout;
	}

	/**
	 * Creates a consistent VerticalLayout for panel content.
	 *
	 * @return the vertical layout
	 */
	protected VerticalLayout createVerticalLayout() {
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);
		return layout;
	}

	/**
	 * Creates a card panel with a standardized layout and style.
	 *
	 * @param title the title of the card
	 * @return the panel
	 */
	protected Panel createCardPanel(String title) {
		Panel cardPanel = new Panel();
		cardPanel.addStyleName("overview-card");
		cardPanel.setWidth("100%");
		cardPanel.setHeightUndefined();
		Responsive.makeResponsive(cardPanel);

		VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setWidth("100%");
		cardPanel.setContent(cardContent);

		addCardHeader(cardContent, title);
		addCardDivider(cardContent);

		return cardPanel;
	}

	/**
	 * Adds a header to the card content.
	 *
	 * @param cardContent the card content layout
	 * @param title the title of the card
	 */
	protected void addCardHeader(VerticalLayout cardContent, String title) {
		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		Label titleLabel = new Label(title, ContentMode.HTML);
		titleLabel.addStyleName("card-title");
		titleLabel.setWidthUndefined();
		headerLayout.addComponent(titleLabel);

		cardContent.addComponent(headerLayout);
	}

	/**
	 * Adds a divider to the layout.
	 *
	 * @param layout the layout to add the divider to
	 */
	protected void addCardDivider(Layout layout) {
		Label divider = new Label("<hr/>", ContentMode.HTML);
		divider.addStyleName("card-divider");
		divider.setWidth("100%");
		layout.addComponent(divider);
	}

	/**
	 * Creates an info row with a caption, value, and optional icon.
	 *
	 * @param caption the caption
	 * @param value the value
	 * @return the component
	 */
	protected Component createInfoRow(String caption, String value) {
		return createInfoRow(caption, value, null);
	}

	/**
	 * Creates an info row with a caption, value, and optional icon.
	 *
	 * @param caption the caption
	 * @param value the value
	 * @param icon the icon
	 * @return the component
	 */
	protected Component createInfoRow(String caption, String value, VaadinIcons icon) {
		if (value == null || value.trim().isEmpty() || "null".equalsIgnoreCase(value)) {
			return null;
		}

		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("info-row");
		layout.setWidthUndefined();

		if (icon != null) {
			Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
			iconLabel.addStyleName("info-icon");
			layout.addComponent(iconLabel);
		}

		Label captionLabel = new Label(caption);
		captionLabel.addStyleName("info-caption");

		Label valueLabel = new Label(value);
		valueLabel.addStyleName("info-value");

		layout.addComponents(captionLabel, valueLabel);
		return layout;
	}

	/**
	 * Adds multiple info rows to a layout.
	 *
	 * @param layout the layout to add the rows to
	 * @param items the info row items
	 */
	protected void addInfoRowsToLayout(VerticalLayout layout, InfoRowItem... items) {
		for (InfoRowItem item : items) {
			Component row = createInfoRow(item.getCaption(), item.getValue(), item.getIcon());
			if (row != null) {
				layout.addComponent(row);
			}
		}
	}

	/**
	 * Creates a split layout with a consistent style.
	 *
	 * @return the horizontal layout
	 */
	protected HorizontalLayout createSplitLayout() {
		HorizontalLayout layout = createHorizontalLayout();
		layout.setWidth("100%");
		return layout;
	}

	/**
	 * Creates a card content layout with a consistent style.
	 *
	 * @return the vertical layout
	 */
	protected VerticalLayout createCardContentLayout() {
		VerticalLayout layout = createVerticalLayout();
		layout.addStyleName("card-content");
		return layout;
	}

	/**
	 * Creates an overview layout with a consistent style.
	 *
	 * @return the vertical layout
	 */
	protected VerticalLayout createOverviewLayout() {
		VerticalLayout layout = createVerticalLayout();
		layout.addStyleName("overview-content");
		return layout;
	}

	/**
	 * Helper class for info row items.
	 */
	protected static class InfoRowItem {
		private final String caption;
		private final String value;
		private final VaadinIcons icon;

		public InfoRowItem(String caption, String value) {
			this(caption, value, null);
		}

		public InfoRowItem(String caption, String value, VaadinIcons icon) {
			this.caption = caption;
			this.value = value;
			this.icon = icon;
		}

		public String getCaption() {
			return caption;
		}

		public String getValue() {
			return value;
		}

		public VaadinIcons getIcon() {
			return icon;
		}
	}
}

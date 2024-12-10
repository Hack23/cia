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
package com.hack23.cia.web.impl.ui.application.views.common.rows;

import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

/**
 * The Class RowUtil.
 */
public final class RowUtil {

	/** The Constant DISPLAY_SIZE_LG_DEVICE. */
	private static final int DISPLAY_SIZE_LG_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_MD_DEVICE. */
	private static final int DISPLAY_SIZE_MD_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_XS_DEVICE. */
	private static final int DISPLAY_SIZE_XS_DEVICE = 12;

	/** The Constant DISPLAYS_SIZE_XM_DEVICE. */
	private static final int DISPLAYS_SIZE_XM_DEVICE = 6;

	/** The Constant ITEMBOX. */
	private static final String ITEMBOX = "itembox";

	/** The Constant TITLE. */
	private static final String TITLE = "title";

	/**
	 * Default constructor for RowUtil.
	 */
	public RowUtil() {
		// Default constructor
	}

	/**
	 * Creates the grid layout.
	 *
	 * @param panelContent the panel content
	 * @return the responsive row
	 */
	public static ResponsiveRow createGridLayout(final AbstractOrderedLayout panelContent) {
		final ResponsiveLayout layout = new ResponsiveLayout();
		Responsive.makeResponsive(layout);
		layout.addStyleName("v-layout-content-overview-panel-level1");
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setHeight(100, Unit.PERCENTAGE);
		panelContent.addComponent(layout);
		panelContent.setExpandRatio(layout, ContentRatio.LARGE);
		final ResponsiveRow row = layout.addRow();
		row.setVerticalSpacing(true);
		row.setHorizontalSpacing(true);
		return row;
	}

	/**
	 * Creates the row component.
	 *
	 * @param row         the row
	 * @param component   the component
	 * @param description the description
	 */
	public static void createRowComponent(final ResponsiveRow row, final Component component,
			final String description) {
		final CssLayout layout = new CssLayout();
		layout.addStyleName(".v-layout-content-pagemode-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();

		final Label descriptionLabel = new Label(description);
		descriptionLabel.addStyleName(ITEMBOX);
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(descriptionLabel);

		component.addStyleName(ITEMBOX);
		component.addStyleName(TITLE);
		Responsive.makeResponsive(component);
		component.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(component);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	/**
	 * Creates the row item.
	 *
	 * @param row         the row
	 * @param button      the button
	 * @param description the description
	 */
	public static void createRowItem(final ResponsiveRow row, final Button button, final String description) {
		final CssLayout layout = new CssLayout();
		layout.addStyleName("v-layout-content-overview-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();

		button.addStyleName(ITEMBOX);
		button.addStyleName(TITLE);
		Responsive.makeResponsive(button);
		button.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(button);

		final Label descriptionLabel = new Label(description);
		descriptionLabel.addStyleName(ITEMBOX);
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(descriptionLabel);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

}

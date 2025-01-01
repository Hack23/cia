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

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminCountryPageModContentFactoryImpl.
 */
@Component
public final class AdminCountryPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = {
			"hjid",
			"id",
			"countryName",
			"iso2Code",
			"capitalCity",
			"longitude",
			"latitude"
	};

	private static final String COUNTRY2 = "Country";

	private static final String[] HIDE_COLUMNS = {
			"hjid",
			"id",
			"region",
			"adminregion",
			"incomeLevel",
			"lendingType",
			"longitude",
			"latitude"
	};

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(AdminViews.ADMIN_COUNTRY_VIEW_NAME, "hjid");

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_COUNTRY_VIEW_NAME;

	/**
	 * Instantiates a new admin country page mod content factory impl.
	 */
	public AdminCountryPageModContentFactoryImpl() {
		super(NAME);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();
		content.setSizeFull();
		content.addStyleName("admin-country-content");

		final String pageId = getPageId(parameters);
		final int pageNr = getPageNr(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		createPageHeader(panel, content,
				"Admin Country Management",
				"Country Overview",
				"Manage and review country-specific data, including metrics and geopolitical information.");

		final DataContainer<CountryElement, Long> dataContainer = getApplicationManager()
				.getDataContainer(CountryElement.class);

		final List<CountryElement> pageOrderBy = dataContainer.getPageOrderBy(
				pageNr,
				DEFAULT_RESULTS_PER_PAGE,
				CountryElement_.countryName);

		getPagingUtil().createPagingControls(
				content,
				NAME,
				pageId,
				dataContainer.getSize(),
				pageNr,
				DEFAULT_RESULTS_PER_PAGE);

		getGridFactory()
				.createBasicBeanItemNestedPropertiesGrid(
						content,
						CountryElement.class,
						pageOrderBy,
						COUNTRY2,
						null,
						COLUMN_ORDER,
						HIDE_COLUMNS,
						LISTENER,
						null,
						null);

		if (pageId != null && !pageId.isEmpty()) {
			final CountryElement country = dataContainer.load(Long.valueOf(pageId));
			if (country != null) {

				// Create a card panel for country details
				final Panel cardPanel = new Panel();
				cardPanel.addStyleName("politician-overview-card");
				cardPanel.setWidth("100%");
				cardPanel.setHeightUndefined();
				Responsive.makeResponsive(cardPanel);

				final VerticalLayout cardContent = new VerticalLayout();
				cardContent.setMargin(true);
				cardContent.setSpacing(true);
				cardContent.setWidth("100%");
				cardPanel.setContent(cardContent);

				content.addComponent(cardPanel);

				// Card Header
				final HorizontalLayout headerLayout = new HorizontalLayout();
				headerLayout.setSpacing(true);
				headerLayout.setWidth("100%");
				headerLayout.addStyleName("card-header-section");

				final String titleText = "Country Details";
				final Label titleLabel = new Label(titleText, ContentMode.HTML);
				titleLabel.addStyleName("card-title");
				titleLabel.setWidthUndefined();
				headerLayout.addComponent(titleLabel);

				cardContent.addComponent(headerLayout);

				// Divider
				final Label divider = new Label("<hr/>", ContentMode.HTML);
				divider.addStyleName("card-divider");
				divider.setWidth("100%");
				cardContent.addComponent(divider);

				// Attributes layout
				final VerticalLayout attributesLayout = new VerticalLayout();
				attributesLayout.setSpacing(true);
				attributesLayout.setWidth("100%");
				cardContent.addComponent(attributesLayout);

				// Show fields if not null or empty
				addInfoRowIfNotNull(attributesLayout, "Country ID:", country.getId(), VaadinIcons.FLAG_O);
				addInfoRowIfNotNull(attributesLayout, "Name:", country.getCountryName(), VaadinIcons.GLOBE);
				addInfoRowIfNotNull(attributesLayout, "ISO2 Code:", country.getIso2Code(), VaadinIcons.CODE);
				addInfoRowIfNotNull(attributesLayout, "Capital:", country.getCapitalCity(), VaadinIcons.BUILDING);
				addInfoRowIfNotNull(attributesLayout, "Longitude:", country.getLongitude(), VaadinIcons.ARROWS_LONG_H);
				addInfoRowIfNotNull(attributesLayout, "Latitude:", country.getLatitude(), VaadinIcons.ARROWS_LONG_H);
			}
		}

		getPageActionEventHelper().createPageEvent(
				ViewAction.VISIT_ADMIN_COUNTRY_VIEW,
				ApplicationEventGroup.ADMIN,
				NAME,
				null,
				pageId);

		return content;
	}

}

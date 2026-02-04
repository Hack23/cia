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
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminCountryPageModContentFactoryImpl.
 */
@Component
public final class AdminCountryPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = {
			"hjid",
			"id",
			"countryName",
			"iso2Code",
			"capitalCity",
			"longitude",
			"latitude"
	};

	/** The Constant COUNTRY2. */
	private static final String COUNTRY2 = "Country";

	/** The Constant HIDE_COLUMNS. */
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

	/** The Constant LISTENER. */
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

		CardInfoRowUtil.createPageHeader(panel, content,
				 AdminViewConstants.ADMIN_COUNTRY_MANAGEMENT,
				 AdminViewConstants.COUNTRY_OVERVIEW,
				 AdminViewConstants.COUNTRY_OVERVIEW_DESCRIPTION);

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

				CardInfoRowUtil.createCardHeader(cardContent,"Country Details");

				// Attributes layout
				final VerticalLayout attributesLayout = new VerticalLayout();
				attributesLayout.setSpacing(true);
				attributesLayout.setWidth("100%");
				cardContent.addComponent(attributesLayout);

				// Show fields if not null or empty
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Country ID:", country.getId(), VaadinIcons.FLAG_O);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Name:", country.getCountryName(), VaadinIcons.GLOBE);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "ISO2 Code:", country.getIso2Code(), VaadinIcons.CODE);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Capital:", country.getCapitalCity(), VaadinIcons.BUILDING);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Longitude:", country.getLongitude(), VaadinIcons.ARROWS_LONG_H);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, "Latitude:", country.getLatitude(), VaadinIcons.ARROWS_LONG_H);
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

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

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement;
import com.hack23.cia.model.external.worldbank.countries.impl.CountryElement_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;



/**
 * The Class AdminCountryPageModContentFactoryImpl.
 */
@Component
public final class AdminCountryPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant ADMIN_COUNTRY. */
	private static final String ADMIN_COUNTRY = "Admin Country";

	private static final List<String> AS_LIST = Arrays.asList( "hjid", "id", "countryName", "iso2Code", "capitalCity",
			"longitude", "latitude" );

	private static final String[] COLUMN_ORDER = { "hjid", "id", "countryName", "iso2Code", "capitalCity", "longitude",
			"latitude" };

	private static final String COUNTRY2 = "Country";

	private static final String[] HIDE_COLUMNS = { "hjid","id", "region", "adminregion" ,"incomeLevel", "lendingType","longitude", "latitude" };

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

		final String pageId = getPageId(parameters);
		final int pageNr= getPageNr(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		LabelFactory.createHeader2Label(content,ADMIN_COUNTRY);

		final DataContainer<CountryElement, Long> dataContainer = getApplicationManager()
				.getDataContainer(CountryElement.class);

		final List<CountryElement> pageOrderBy = dataContainer.getPageOrderBy(pageNr,DEFAULT_RESULTS_PER_PAGE,CountryElement_.countryName);

		getPagingUtil().createPagingControls(content,NAME,pageId, dataContainer.getSize(), pageNr, DEFAULT_RESULTS_PER_PAGE);

		getGridFactory()
				.createBasicBeanItemNestedPropertiesGrid(content, CountryElement.class, pageOrderBy,
						COUNTRY2,null,
						COLUMN_ORDER, HIDE_COLUMNS,
						LISTENER, null, null);

		if (pageId != null && !pageId.isEmpty()) {

			final CountryElement country = dataContainer.load(Long.valueOf(pageId));
			if (country != null) {

				getFormFactory().addFormPanelTextFields(content, country, CountryElement.class,
						AS_LIST);
			}
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_COUNTRY_VIEW, ApplicationEventGroup.ADMIN,
				NAME, null, pageId);

		return content;

	}

}

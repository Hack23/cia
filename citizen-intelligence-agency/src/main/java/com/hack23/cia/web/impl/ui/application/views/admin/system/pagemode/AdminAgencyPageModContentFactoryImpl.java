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
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.Agency;
import com.hack23.cia.model.internal.application.system.impl.Agency_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.Portal;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.converters.ListPropertyConverter;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class OverviewPageModContentFactoryImpl.
 */
@Component
public final class AdminAgencyPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant ADMIN_AGENCY. */
	private static final String ADMIN_AGENCY = "Admin Agency";

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_AGENCY_VIEW_NAME;

	/**
	 * Instantiates a new admin agency page mod content factory impl.
	 */
	public AdminAgencyPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		final String pageId = getPageId(parameters);
		final int pageNr= getPageNr(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);


		LabelFactory.createHeader2Label(content,ADMIN_AGENCY);

		final DataContainer<Agency, Long> dataContainer = getApplicationManager().getDataContainer(Agency.class);

		final BeanItemContainer<Agency> politicianDocumentDataSource = new BeanItemContainer<>(Agency.class,
				dataContainer.getPageOrderBy(pageNr,DEFAULT_RESULTS_PER_PAGE,Agency_.agencyName));

		createPagingControls(content,NAME,pageId, dataContainer.getSize(), pageNr, DEFAULT_RESULTS_PER_PAGE);

		getGridFactory().createBasicBeanItemGrid(content,
				politicianDocumentDataSource, "Agency",
				new String[] { "hjid", "agencyName", "description", "portals", "modelObjectVersion" }, new String[] { "hjid","modelObjectId", "modelObjectVersion" },
				new PageItemPropertyClickListener(AdminViews.ADMIN_AGENCY_VIEW_NAME, "hjid"), null, new ListPropertyConverter[] { new ListPropertyConverter(List.class, "portalName", "portals")});

		if (pageId != null && !pageId.isEmpty()) {

			final VerticalLayout leftLayout = new VerticalLayout();
			leftLayout.setSizeFull();
			final VerticalLayout rightLayout = new VerticalLayout();
			rightLayout.setSizeFull();
			final HorizontalLayout horizontalLayout = new HorizontalLayout();
			horizontalLayout.setWidth(ContentSize.FULL_SIZE);
			content.addComponent(horizontalLayout);

			content.setExpandRatio(horizontalLayout, ContentRatio.LARGE_FORM);


			horizontalLayout.addComponent(leftLayout);
			horizontalLayout.addComponent(rightLayout);

			final Agency agency = dataContainer.load(Long.valueOf(pageId));
			agency.getPortals().getClass();
			if (agency != null) {

				getFormFactory().addFormPanelTextFields(leftLayout, new BeanItem<>(agency), Agency.class,
						Arrays.asList(new String[] { "agencyName", "description"}));

				final BeanItemContainer<Portal> portalItemContainer = new BeanItemContainer<>(Portal.class,
						agency.getPortals());

				getGridFactory().createBasicBeanItemGrid(rightLayout, portalItemContainer,
						"Portal",
						new String[] { "hjid", "portalName", "description", "portalType", "googleMapApiKey",
								"modelObjectVersion" }, new String[] { "hjid","modelObjectId", "modelObjectVersion", "googleMapApiKey" },
						new PageItemPropertyClickListener(AdminViews.ADMIN_PORTAL_VIEW_NAME, "hjid"), null, null);
			}

		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_AGENCY_VIEW, ApplicationEventGroup.ADMIN,
				NAME, null, pageId);

		return content;

	}

}

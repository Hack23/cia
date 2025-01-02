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
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class MinistryRankingDataGridPageModContentFactoryImpl.
 */
@Service
public final class MinistryRankingDataGridPageModContentFactoryImpl
		extends AbstractMinistryRankingPageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = {
		    "nameId",
		    "currentMemberSize",
		    "activityLevel",
		    "avgDocumentsPerMember",
		    "totalPropositions",
		    "totalGovernmentBills",
		    "totalDocuments",
		    "documentsLastYear",
		    "totalDaysServed",
		    "totalAssignments",
		    "firstAssignmentDate",
		    "lastAssignmentDate"
		};
	/** The Constant DATAGRID. */
	private static final String[] HIDE_COLUMNS = {"active"};

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(UserViews.MINISTRY_VIEW_NAME, "nameId");

	/** The Constant MINISTRIES. */
	private static final String MINISTRIES = "Ministries";

	/**
	 * Instantiates a new ministry ranking data grid page mod content factory
	 * impl.
	 */
	public MinistryRankingDataGridPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getMinistryRankingMenuItemFactory().createMinistryRankingMenuBar(menuBar);
		createPageHeader(panel, panelContent, "Ministry Ranking Dashboard", "Ministry Rankings", "Compare ministry rankings to assess their assignments, performance, and overall impact.");

		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenMinistry, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenMinistry.class);

		getGridFactory().createBasicBeanItemGrid(panelContent, ViewRiksdagenMinistry.class, dataContainer.getAllOrderBy(ViewRiksdagenMinistry_.currentMemberSize),
				MINISTRIES,
				COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);


		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page)
				&& StringUtils.contains(parameters, PageMode.DATAGRID.toString());
	}

}

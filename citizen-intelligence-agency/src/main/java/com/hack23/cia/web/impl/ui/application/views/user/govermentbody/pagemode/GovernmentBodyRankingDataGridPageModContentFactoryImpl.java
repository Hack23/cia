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
package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyRankingDataGridPageModContentFactoryImpl.
 */
@Service
public final class GovernmentBodyRankingDataGridPageModContentFactoryImpl
		extends AbstractGovernmentBodyRankingPageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = { "name", "ministry", "orgNumber",
			"headCount", "annualWorkHeadCount" };

	/** The Constant GOVERNMENT_BODIES. */
	private static final String GOVERNMENT_BODIES = "Government bodies";

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "vat", "consecutiveNumber", "comment", "mCode","govermentBodyId" };

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.GOVERNMENT_BODY_VIEW_NAME, "orgNumber");

	/** The esv api. */
	@Autowired
	private EsvApi esvApi;

	/**
	 * Instantiates a new government body ranking data grid page mod content factory
	 * impl.
	 */
	public GovernmentBodyRankingDataGridPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getGovernmentBodyRankingMenuItemFactory().createGovernmentBodyRankingMenuBar(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent, "Government Body Ranking Overview", "Government Body Rankings", "Examine rankings of government bodies based on roles, efficiency, and achievements.");

		final String pageId = getPageId(parameters);

		final Map<Integer, List<GovernmentBodyAnnualSummary>> dataMap = esvApi.getData();

		final List<GovernmentBodyAnnualSummary> list = dataMap.get(2024);

		getGridFactory().createBasicBeanItemGrid(panelContent, GovernmentBodyAnnualSummary.class, list,
				GOVERNMENT_BODIES, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_GOVERNMENT_BODY_RANKING_VIEW,
				ApplicationEventGroup.USER, NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.DATAGRID.toString());
	}

}

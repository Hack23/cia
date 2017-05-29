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
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class PoliticianRankingDataGridPageModContentFactoryImpl.
 */
@Service
public final class PoliticianRankingDataGridPageModContentFactoryImpl
		extends AbstractPoliticianRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_RANKING_VIEW_NAME;

	/** The Constant DATAGRID. */
	private static final String DATAGRID = "Datagrid";

	/**
	 * Instantiates a new politician ranking data grid page mod content factory
	 * impl.
	 */
	public PoliticianRankingDataGridPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page)
				&& (!StringUtils.isEmpty(parameters) && parameters.contains(PageMode.DATAGRID.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getPoliticianRankingMenuItemFactory().createPoliticianRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPolitician.class);

		final BeanItemContainer<ViewRiksdagenPolitician> politicianDataSource = new BeanItemContainer<>(
				ViewRiksdagenPolitician.class,
				politicianDataContainer.getAllOrderBy(ViewRiksdagenPolitician_.currentAssignments));

		getGridFactory().createBasicBeanItemGrid(panelContent, politicianDataSource,
				"Politicians",
				new String[] { "personId", "firstName", "lastName", "party", "bornYear", "totalDaysServed",
						"currentAssignments", "totalAssignments", "firstAssignmentDate", "lastAssignmentDate",
						"totalDaysServedParliament", "totalDaysServedCommittee", "totalDaysServedGovernment",
						"totalDaysServedEu",

						"active", "activeEu", "activeGovernment", "activeCommittee", "activeParliament",

						"activeParty", "activeSpeaker", "totalDaysServedSpeaker", "totalDaysServedParty",

						"totalPartyAssignments", "totalMinistryAssignments", "totalCommitteeAssignments",
						"totalSpeakerAssignments",

						"currentPartyAssignments", "currentMinistryAssignments", "currentCommitteeAssignments",
						"currentSpeakerAssignments", "gender" }, new String[] { "personId", "active", "activeEu", "activeGovernment", "activeCommittee", "activeParliament", "activeParty", "activeSpeaker","bornYear" }, new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME, "personId"), null, null);

		panel.setCaption(NAME + "::" + DATAGRID);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

}

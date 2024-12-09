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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CommitteeRankingOverviewPageModContentFactoryImpl.
 */
@Component
public final class CommitteeRankingOverviewPageModContentFactoryImpl extends AbstractCommitteeRankingPageModContentFactoryImpl {

	/** The Constant COMMITTEE_RANKING_BY_TOPIC. */
	private static final String COMMITTEE_RANKING_BY_TOPIC = "Committee Ranking by topic";

	/** The Constant COMMITTEE_RANKING_BY_TOPIC_DESCRIPTION. */
	private static final String COMMITTEE_RANKING_BY_TOPIC_DESCRIPTION = "Time served in Committee:ALL:CURRENT:" + "\nPoliticans served in Committee:ALL:CURRENT:"
			+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType"
			+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType"

			+ "\nTop decisions NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
			+ "\nTop Votes NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"

			+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
			+ "\nSearch by name";

	/** The Constant NAME. */
	public static final String NAME = UserViews.COMMITTEE_RANKING_VIEW_NAME;

	/**
	 * Instantiates a new committee ranking overview page mod content factory
	 * impl.
	 */
	public CommitteeRankingOverviewPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the description.
	 *
	 * @return the text area
	 */
	private static TextArea createDescription() {
		final TextArea totalCommitteeRankinglistLabel = new TextArea(COMMITTEE_RANKING_BY_TOPIC,
				COMMITTEE_RANKING_BY_TOPIC_DESCRIPTION);
		totalCommitteeRankinglistLabel.setSizeFull();
		totalCommitteeRankinglistLabel.setStyleName("Level2Header");
		return totalCommitteeRankinglistLabel;
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getCommitteeRankingMenuItemFactory().createCommitteeeRankingMenuBar(menuBar);
		createPageHeader(panel, panelContent, "Committee Rankings", "Ranking Details", "Analyze and compare rankings of parliamentary committees based on performance.");

		final String pageId = getPageId(parameters);

		panelContent.addComponent(createDescription());

		getCommitteeRankingMenuItemFactory().createOverviewPage(panelContent);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}


	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString()));
	}


}

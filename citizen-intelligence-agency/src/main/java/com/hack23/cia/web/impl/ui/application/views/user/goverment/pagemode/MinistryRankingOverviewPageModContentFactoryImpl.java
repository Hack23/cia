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
package com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class MinistryRankingOverviewPageModContentFactoryImpl.
 */
@Component
public final class MinistryRankingOverviewPageModContentFactoryImpl extends AbstractMinistryRankingPageModContentFactoryImpl {

	/** The Constant MINISTRY_RANKING_BY_TOPIC. */
	private static final String MINISTRY_RANKING_BY_TOPIC = "Ministry Ranking by topic";

	/** The Constant MINISTRY_RANKING_BY_TOPIC_DESCRIPTION. */
	private static final String MINISTRY_RANKING_BY_TOPIC_DESCRIPTION = "Time served in Ministry:ALL:CURRENT:"
			+ "\nPoliticans served in Committee:ALL:CURRENT:"
			+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType"
			+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType"

			+ "\nTop decisions NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
			+ "\nTop Votes NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"

			+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::#Views:List,Timeline,BarChart,PieChart"
			+ "\nSearch by name";

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";


	/**
	 * Instantiates a new ministry ranking overview page mod content factory
	 * impl.
	 */
	public MinistryRankingOverviewPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the description.
	 *
	 * @return the text area
	 */
	private static TextArea createDescription() {
		final TextArea totalCommitteeRankinglistLabel = new TextArea(MINISTRY_RANKING_BY_TOPIC,
				MINISTRY_RANKING_BY_TOPIC_DESCRIPTION);
		totalCommitteeRankinglistLabel.setSizeFull();
		totalCommitteeRankinglistLabel.setStyleName("Level2Header");
		return totalCommitteeRankinglistLabel;
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getMinistryRankingMenuItemFactory().createMinistryRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		panelContent.addComponent(createDescription());

		getMinistryRankingMenuItemFactory().createOverviewPage(panelContent);

		panel.setCaption(NAME + "::" + OVERVIEW);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_MINISTRY_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}


	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString()));
	}


}

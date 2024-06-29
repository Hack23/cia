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
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

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
 * The Class PoliticianRankingOverviewPageModContentFactoryImpl.
 */
@Component
public final class PoliticianRankingOverviewPageModContentFactoryImpl
		extends AbstractPoliticianRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_RANKING_VIEW_NAME;

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";

	/**
	 * Instantiates a new politician ranking overview page mod content factory
	 * impl.
	 */
	public PoliticianRankingOverviewPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the description.
	 *
	 * @return the text area
	 */
	private static TextArea createDescription() {
		final TextArea totalpoliticantoplistLabel = new TextArea("Politician Ranking by topic",
				"Time served in Parliament:ALL:CURRENT:*FILTER:Gender,Party,ElectionRegion"
						+ "\nTime served in Committees:ALL:CURRENT:*FILTER:Gender,Party,ElectionRegion"
						+ "\nTime served in Government:ALL:CURRENT:*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop document author NR:ALL:YEAR:CURRENT:*FILTER:DocumnetType,Gender,Party,ElectionRegion"
						+ "\nTop document author SIZE:YEAR:ALL:CURRENT:*FILTER:DocumnetType,Gender,Party,ElectionRegion"

						+ "\nTop votes:ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop vote winner NR/PERCENTAGE :ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop vote party rebel NR/PERCENTAGE :ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nTop vote presence NR/PERCENTAGE :ALL:YEAR:CURRENT::*FILTER:Gender,Party,ElectionRegion"
						+ "\nSearch by name");
		totalpoliticantoplistLabel.setSizeFull();
		totalpoliticantoplistLabel.setStyleName("Level2Header");
		return totalpoliticantoplistLabel;
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		getPoliticianRankingMenuItemFactory().createPoliticianRankingMenuBar(menuBar);

		final String pageId = getPageId(parameters);

		panelContent.addComponent(createDescription());

		getPoliticianRankingMenuItemFactory().createOverviewPage(panelContent);

		panel.setCaption(NAME + "::" + OVERVIEW);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_RANKING_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

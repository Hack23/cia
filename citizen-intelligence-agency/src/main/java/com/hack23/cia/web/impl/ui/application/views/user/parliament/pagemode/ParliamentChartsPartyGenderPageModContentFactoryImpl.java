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
package com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode;

import static com.hack23.cia.web.impl.ui.application.views.common.constants.ParliamentPageTitleConstants.*;

import com.hack23.cia.web.impl.ui.application.views.common.constants.ParliamentPageTitleConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PartyChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandParliamentRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class TestChartsPartyGenderPageModContentFactoryImpl.
 */
@Component
public final class ParliamentChartsPartyGenderPageModContentFactoryImpl extends AbstractParliamentPageModContentFactoryImpl {

	/** The party chart data manager. */
	@Autowired
	private PartyChartDataManager partyChartDataManager;

	/**
	 * Instantiates a new test charts party gender page mod content factory
	 * impl.
	 */
	public ParliamentChartsPartyGenderPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		getParliamentMenuItemFactory().createParliamentTopicMenu(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent,
		    ParliamentPageTitleConstants.PARTY_GENDER_TITLE,
		    ParliamentPageTitleConstants.PARTY_GENDER_SUBTITLE,
		    ParliamentPageTitleConstants.PARTY_GENDER_DESC);

		final String pageId = getPageId(parameters);

		partyChartDataManager.createPartyGenderChart(panelContent);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARLIAMENT_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandParliamentRankingConstants.COMMAND_CHARTS_PARTY_GENDER.matches(page, parameters);
	}

}

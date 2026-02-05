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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PartySupportsChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PartySupportAnnualSummaryChartPageModContentFactoryImpl.
 */
@Component
public final class PartySupportAnnualSummaryChartPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The chart data manager. */
	@Autowired
	private PartySupportsChartDataManager chartDataManager;

	/**
	 * Instantiates a new party won daily summary chart page mod content factory
	 * impl.
	 */
	public PartySupportAnnualSummaryChartPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);
		CardInfoRowUtil.createPageHeader(panel, panelContent,
            PartyViewConstants.SUPPORT_SUMMARY_HEADER,
            PartyViewConstants.SUPPORT_SUMMARY_TITLE,
            PartyViewConstants.SUPPORT_SUMMARY_DESC);
		chartDataManager.createPartyChart(panelContent,pageId);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
		pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandPartyConstants.COMMAND_PARTY_SUPPORT_SUMMARY.matches(page, parameters);
	}

}

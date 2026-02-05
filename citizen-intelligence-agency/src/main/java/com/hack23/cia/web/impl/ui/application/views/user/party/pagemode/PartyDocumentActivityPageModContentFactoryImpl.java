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

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.views.common.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.PartyDocumentChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DocumentActivityPageModContentFactoryImpl.
 */
@Component
public final class PartyDocumentActivityPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/** The document chart data manager. */
	@Autowired
	private PartyDocumentChartDataManager documentChartDataManager;

	/**
	 * Instantiates a new party document activity page mod content factory impl.
	 */
	public PartyDocumentActivityPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);

		getPartyMenuItemFactory().createPartyMenuBar(menuBar, getPageId(parameters));

		CardInfoRowUtil.createPageHeader(panel, panelContent,
			PartyViewConstants.DOCUMENT_ACTIVITY_HEADER + viewRiksdagenParty.getPartyName(),
			PartyViewConstants.DOCUMENT_ACTIVITY_SUBTITLE,
			PartyViewConstants.DOCUMENT_ACTIVITY_DESC);

		documentChartDataManager.createDocumentHistoryPartyChart(panelContent, getPageId(parameters));

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
		getPageId(parameters));
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandPartyConstants.COMMAND_PARTY_DOCUMENT_ACTIVITY.matches(page, parameters);
	}

}

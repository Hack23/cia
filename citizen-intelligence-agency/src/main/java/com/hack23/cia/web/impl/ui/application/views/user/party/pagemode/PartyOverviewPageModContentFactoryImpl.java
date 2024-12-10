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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class OverviewPageModContentFactoryImpl.
 */
@Component
public final class PartyOverviewPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	private static final List<String> AS_LIST = Arrays.asList("partyName", "partyId", "headCount", "partyNumber",
			"registeredDate", "website");

	private static final List<String> AS_LIST2 = Arrays.asList("active", "firstAssignmentDate", "lastAssignmentDate",
			"currentAssignments", "totalAssignments", "totalDaysServed", "activeEu", "totalActiveEu",
			"totalDaysServedEu", "activeGovernment", "totalActiveGovernment", "totalDaysServedGovernment",
			"activeCommittee", "totalActiveCommittee", "totalDaysServedCommittee", "activeParliament",
			"totalActiveParliament", "totalDaysServedParliament");

	/**
	 * Instantiates a new party overview page mod content factory impl.
	 */
	public PartyOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);

		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);
		createPageHeader(panel, panelContent, "Party Overview " + viewRiksdagenParty.getPartyName(), "Party Details", "Explore detailed information about political parties and their activities.");

		final Link addPartyPageLink = getPageLinkFactory().addPartyPageLink(viewRiksdagenParty);
		panelContent.addComponent(addPartyPageLink);

		panelContent.setExpandRatio(addPartyPageLink, ContentRatio.SMALL);

		getFormFactory().addFormPanelTextFields(panelContent, viewRiksdagenParty, ViewRiksdagenParty.class, AS_LIST);

		final DataContainer<ViewRiksdagenPartySummary, String> partySummarydataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartySummary.class);

		final ViewRiksdagenPartySummary viewRiksdagenPartySummary = partySummarydataContainer.load(pageId);

		if (viewRiksdagenPartySummary != null) {

			getFormFactory().addFormPanelTextFields(panelContent, viewRiksdagenPartySummary,
					ViewRiksdagenPartySummary.class, AS_LIST2);

		}

		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();

		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getPartyMenuItemFactory().createOverviewPage(overviewLayout, pageId);

		pageCompleted(parameters, panel, pageId);
		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

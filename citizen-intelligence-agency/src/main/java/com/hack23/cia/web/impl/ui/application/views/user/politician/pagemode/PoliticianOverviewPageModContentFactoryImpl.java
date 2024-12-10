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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class OverviewPageModContentFactoryImpl.
 */
@Component
public final class PoliticianOverviewPageModContentFactoryImpl extends AbstractPoliticianPageModContentFactoryImpl {

	private static final List<String> AS_LIST = Arrays.asList("firstName", "lastName", "gender", "bornYear", "party",
			"active", "firstAssignmentDate", "lastAssignmentDate", "currentAssignments", "currentMinistryAssignments",
			"currentSpeakerAssignments", "currentCommitteeAssignments", "currentPartyAssignments",
			"totalMinistryAssignments", "totalCommitteeAssignments", "totalSpeakerAssignments", "totalPartyAssignments",
			"totalAssignments", "totalDaysServed", "activeEu", "totalDaysServedEu", "activeGovernment",
			"totalDaysServedGovernment", "activeSpeaker", "totalDaysServedSpeaker", "activeCommittee",
			"totalDaysServedCommittee", "activeParliament", "totalDaysServedParliament", "activeParty",
			"totalDaysServedParty");
	/**
	 * Instantiates a new politician overview page mod content factory impl.
	 */
	public PoliticianOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);
		getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);
		createPageHeader(panel, panelContent, "Politician Overview " + viewRiksdagenPolitician.getFirstName() + ' '
				+ viewRiksdagenPolitician.getLastName() + '(' + viewRiksdagenPolitician.getParty() + ')', "Politician Details", "Detailed profiles and activities of politicians.");

		final PersonData personData = getApplicationManager().getDataContainer(PersonData.class)
				.load(viewRiksdagenPolitician.getPersonId());

		createOverviewContent(panelContent, personData, viewRiksdagenPolitician, pageId);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
		UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);
		return panelContent;

	}




	/**
	 * Creates the overview content.
	 *
	 * @param panelContent the panel content
	 * @param personData the person data
	 * @param viewRiksdagenPolitician the view riksdagen politician
	 * @param pageId the page id
	 */
	private void createOverviewContent(final VerticalLayout panelContent, final PersonData personData,
			final ViewRiksdagenPolitician viewRiksdagenPolitician, final String pageId) {

		final Link createPoliticianPageLink = getPageLinkFactory().createPoliticianPageLink(personData);
		panelContent.addComponent(createPoliticianPageLink);

		final Image image = new Image("",
				new ExternalResource(personData.getImageUrl192().replace("http://", "https://")));

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();

		panelContent.addComponent(horizontalLayout);

		horizontalLayout.addComponent(image);

		getFormFactory().addFormPanelTextFields(horizontalLayout, viewRiksdagenPolitician,
				ViewRiksdagenPolitician.class, AS_LIST);

		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();

		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getPoliticianMenuItemFactory().createOverviewPage(overviewLayout, pageId);

		panelContent.setExpandRatio(createPoliticianPageLink, ContentRatio.SMALL);
		panelContent.setExpandRatio(horizontalLayout, ContentRatio.GRID);

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

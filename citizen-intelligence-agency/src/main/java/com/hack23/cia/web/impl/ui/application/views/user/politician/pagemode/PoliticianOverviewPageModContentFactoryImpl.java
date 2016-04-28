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

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.person.impl.DetailData;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
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

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "overview";


	/**
	 * Instantiates a new politician overview page mod content factory impl.
	 */
	public PoliticianOverviewPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);

		final DataContainer<PersonData, String> dataContainer = getApplicationManager()
				.getDataContainer(PersonData.class);

		final PersonData personData = dataContainer.load(pageId);
		if (personData != null) {

			final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
					.getDataContainer(ViewRiksdagenPolitician.class);

			final ViewRiksdagenPolitician viewRiksdagenPolitician = politicianDataContainer.load(personData.getId());

			getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

			createOverviewContent(panelContent, personData, viewRiksdagenPolitician);

			pageCompleted(parameters, panel, pageId, viewRiksdagenPolitician);

		}
		return panelContent;

	}

	/**
	 * Creates the overview content.
	 *
	 * @param panelContent
	 *            the panel content
	 * @param personData
	 *            the person data
	 * @param viewRiksdagenPolitician
	 *            the view riksdagen politician
	 */
	private void createOverviewContent(final VerticalLayout panelContent, final PersonData personData,
			final ViewRiksdagenPolitician viewRiksdagenPolitician) {
		final Label createHeader2Label = LabelFactory.createHeader2Label(OVERVIEW);
		panelContent.addComponent(createHeader2Label);

		final Link createPoliticianPageLink = getPageLinkFactory().createPoliticianPageLink(personData);
		panelContent.addComponent(createPoliticianPageLink);

		final Image image = new Image("",
				new ExternalResource(personData.getImageUrl192().replace("http://", "https://")));

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();

		panelContent.addComponent(horizontalLayout);

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		horizontalLayout.addComponent(formPanel);
		horizontalLayout.addComponent(image);

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);

		getFormFactory().addTextFields(formContent, new BeanItem<>(viewRiksdagenPolitician),
				ViewRiksdagenPolitician.class,
				Arrays.asList(new String[] { "firstName", "lastName", "gender", "bornYear", "party", "active",
						"firstAssignmentDate", "lastAssignmentDate", "currentAssignments", "currentMinistryAssignments",
						"currentSpeakerAssignments", "currentCommitteeAssignments", "currentPartyAssignments",
						"totalMinistryAssignments", "totalCommitteeAssignments", "totalSpeakerAssignments",
						"totalPartyAssignments", "totalAssignments", "totalDaysServed", "activeEu", "totalDaysServedEu",
						"activeGovernment", "totalDaysServedGovernment", "activeSpeaker", "totalDaysServedSpeaker",
						"activeCommittee", "totalDaysServedCommittee", "activeParliament", "totalDaysServedParliament",
						"activeParty", "totalDaysServedParty" }));

		final Grid createBasicBeanItemGrid = getGridFactory().createBasicBeanItemGrid(
				new BeanItemContainer<>(DetailData.class, personData.getPersonDetailData().getDetailList()), "Detail",
				new String[] { "detailType", "detail", "code" }, new String[] { "hjid", "intressentId" }, null, null,
				null);
		panelContent.addComponent(createBasicBeanItemGrid);

		panelContent.setExpandRatio(createHeader2Label, ContentRatio.SMALL);
		panelContent.setExpandRatio(createPoliticianPageLink, ContentRatio.SMALL);
		panelContent.setExpandRatio(horizontalLayout, ContentRatio.GRID);

		panelContent.setExpandRatio(createBasicBeanItemGrid, ContentRatio.SMALL_GRID);
	}

}

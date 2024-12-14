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
 *  $Id$
 *  $HeadURL$
 */
package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

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
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PoliticianOverviewPageModContentFactoryImpl.
 */
@Component
public final class PoliticianOverviewPageModContentFactoryImpl extends AbstractPoliticianPageModContentFactoryImpl {

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
		panel.setContent(panelContent);

		final String pageId = getPageId(parameters);
		final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);

		getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

		createPageHeader(
				panel,
				panelContent,
				"Politician Overview " + viewRiksdagenPolitician.getFirstName() + ' ' + viewRiksdagenPolitician.getLastName()
						+ " (" + viewRiksdagenPolitician.getParty() + ')',
				"Politician Details",
				"Detailed profiles and activities of politicians."
		);

		final PersonData personData = getApplicationManager().getDataContainer(PersonData.class)
				.load(viewRiksdagenPolitician.getPersonId());

		createOverviewContent(panelContent, personData, viewRiksdagenPolitician, pageId);

		getPageActionEventHelper().createPageEvent(
				ViewAction.VISIT_POLITICIAN_VIEW,
				ApplicationEventGroup.USER,
				UserViews.POLITICIAN_VIEW_NAME,
				parameters,
				pageId
		);

		return panelContent;
	}

	/**
	 * Creates the overview content in a card style similar to the scoreboard snippet.
	 *
	 * @param panelContent            the panel content
	 * @param personData              the person data
	 * @param viewRiksdagenPolitician the view riksdagen politician
	 * @param pageId                  the page id
	 */
	private void createOverviewContent(final VerticalLayout panelContent, final PersonData personData,
			final ViewRiksdagenPolitician viewRiksdagenPolitician, final String pageId) {

		// Link to politician detail page
		final Link createPoliticianPageLink = getPageLinkFactory().createPoliticianPageLink(personData);
		createPoliticianPageLink.addStyleName("card-subtitle");
		panelContent.addComponent(createPoliticianPageLink);
		panelContent.setExpandRatio(createPoliticianPageLink, ContentRatio.SMALL);

		// Create a panel (card) for the politician
		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("politician-overview-card");
		cardPanel.setWidth("100%");
		cardPanel.setHeightUndefined();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setWidth("100%");
		cardPanel.setContent(cardContent);

		panelContent.addComponent(cardPanel);
		panelContent.setExpandRatio(cardPanel, ContentRatio.SMALL_GRID);

		// Header layout similar to scoreboard snippet
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		final String titleText = viewRiksdagenPolitician.getFirstName() + " " + viewRiksdagenPolitician.getLastName()
				+ " (" + viewRiksdagenPolitician.getParty() + ")";
		final Label titleLabel = new Label(titleText, ContentMode.HTML);
		titleLabel.addStyleName("card-title");
		titleLabel.setWidthUndefined();
		headerLayout.addComponent(titleLabel);

		cardContent.addComponent(headerLayout);

		// Divider line for better separation
		final Label divider = new Label("<hr/>", ContentMode.HTML);
		divider.addStyleName("card-divider");
		divider.setWidth("100%");
		cardContent.addComponent(divider);

		// Image and details layout
		final HorizontalLayout imageAndDetailsLayout = new HorizontalLayout();
		imageAndDetailsLayout.setSpacing(true);
		imageAndDetailsLayout.setWidth("100%");
		cardContent.addComponent(imageAndDetailsLayout);

		// Politician image (smaller)
		final String imageUrl = personData.getImageUrl192().replace("http://", "https://");
		final Image image = new Image("", new ExternalResource(imageUrl));
		image.setDescription("Picture of " + viewRiksdagenPolitician.getFirstName() + " "
				+ viewRiksdagenPolitician.getLastName());
		image.setWidth(100, Unit.PIXELS);
		image.addStyleName("politician-image");

		imageAndDetailsLayout.addComponent(image);

		// Two-column layout for attributes
		final HorizontalLayout attributesLayout = new HorizontalLayout();
		attributesLayout.setSpacing(true);
		attributesLayout.setWidth("100%");
		imageAndDetailsLayout.addComponent(attributesLayout);
		imageAndDetailsLayout.setExpandRatio(attributesLayout, 1.0f);

		// First column: Profile Details
		final VerticalLayout profileDetailsLayout = new VerticalLayout();
		profileDetailsLayout.setSpacing(true);
		profileDetailsLayout.addStyleName("card-details-column");
		profileDetailsLayout.setWidthUndefined();

		Label profileDetailsHeader = new Label("Profile Details");
		profileDetailsHeader.addStyleName("card-section-title");
		profileDetailsLayout.addComponent(profileDetailsHeader);

		profileDetailsLayout.addComponent(createInfoRow("Gender:", viewRiksdagenPolitician.getGender(), VaadinIcons.USER, "Politician's gender"));
		profileDetailsLayout.addComponent(createInfoRow("Born Year:", String.valueOf(viewRiksdagenPolitician.getBornYear()), VaadinIcons.CALENDAR, "Year the politician was born"));
		profileDetailsLayout.addComponent(createInfoRow("Active:", String.valueOf(viewRiksdagenPolitician.isActive()), VaadinIcons.FLASH, "Indicates if the politician is currently active in politics"));
		profileDetailsLayout.addComponent(createInfoRow("Total Days Served:", String.valueOf(viewRiksdagenPolitician.getTotalDaysServed()), VaadinIcons.CLOCK, "Total number of days in public service"));

		attributesLayout.addComponent(profileDetailsLayout);

		// Second column: Service Statistics
		final VerticalLayout serviceStatsLayout = new VerticalLayout();
		serviceStatsLayout.setSpacing(true);
		serviceStatsLayout.addStyleName("card-details-column");
		serviceStatsLayout.setWidthUndefined();

		Label serviceStatsHeader = new Label("Service Statistics");
		serviceStatsHeader.addStyleName("card-section-title");
		serviceStatsLayout.addComponent(serviceStatsHeader);

		// Additional stats (Assuming these methods exist)
		serviceStatsLayout.addComponent(createInfoRow("Total Assignments:", String.valueOf(viewRiksdagenPolitician.getTotalAssignments()), VaadinIcons.BAR_CHART, "Total number of assignments held"));
		serviceStatsLayout.addComponent(createInfoRow("Ministry Assignments:", String.valueOf(viewRiksdagenPolitician.getTotalMinistryAssignments()), VaadinIcons.INSTITUTION, "Number of ministry level assignments"));
		serviceStatsLayout.addComponent(createInfoRow("Committee Assignments:", String.valueOf(viewRiksdagenPolitician.getTotalCommitteeAssignments()), VaadinIcons.GROUP, "Number of committee assignments"));
		serviceStatsLayout.addComponent(createInfoRow("Speaker Assignments:", String.valueOf(viewRiksdagenPolitician.getTotalSpeakerAssignments()), VaadinIcons.MICROPHONE, "Number of speaker assignments"));
		serviceStatsLayout.addComponent(createInfoRow("Party Assignments:", String.valueOf(viewRiksdagenPolitician.getTotalPartyAssignments()), VaadinIcons.GROUP, "Number of party-related assignments"));

		attributesLayout.addComponent(serviceStatsLayout);

		// After the card, add the overview layout for extended details
		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getPoliticianMenuItemFactory().createOverviewPage(overviewLayout, pageId);
	}

	/**
	 * Creates a row displaying a caption and value, with optional icon and tooltip.
	 *
	 * @param caption the field caption
	 * @param value   the field value
	 * @param icon    a VaadinIcons icon for better visual cue
	 * @param tooltip optional tooltip to provide more info
	 * @return a HorizontalLayout representing the info row
	 */
	private HorizontalLayout createInfoRow(final String caption, final String value, VaadinIcons icon, final String tooltip) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("metric-label");
		layout.setWidthUndefined();

		Label iconLabel = null;
		if (icon != null) {
			iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
			iconLabel.setDescription(tooltip);
			iconLabel.addStyleName("card-info-icon");
			layout.addComponent(iconLabel);
		}

		final Label captionLabel = new Label(caption);
		captionLabel.addStyleName("card-info-caption");
		if (tooltip != null && !tooltip.isEmpty()) {
			captionLabel.setDescription(tooltip);
		}

		final Label valueLabel = new Label(value != null ? value : "");
		valueLabel.addStyleName("card-info-value");

		layout.addComponents(captionLabel, valueLabel);
		return layout;
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}
}

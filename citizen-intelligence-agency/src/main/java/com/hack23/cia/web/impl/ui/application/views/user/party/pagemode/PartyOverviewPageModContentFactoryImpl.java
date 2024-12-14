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

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PartyOverviewPageModContentFactoryImpl.
 */
@Component
public final class PartyOverviewPageModContentFactoryImpl extends AbstractPartyPageModContentFactoryImpl {

	/**
	 * Instantiates a new party overview page mod content factory impl.
	 */
	public PartyOverviewPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the content.
	 *
	 * @param parameters the parameters
	 * @param menuBar the menu bar
	 * @param panel the panel
	 * @return the layout
	 */
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		panel.setContent(panelContent);

		final String pageId = getPageId(parameters);
		final ViewRiksdagenParty viewRiksdagenParty = getItem(parameters);

		getPartyMenuItemFactory().createPartyMenuBar(menuBar, pageId);
		createPageHeader(panel, panelContent, "Party Overview " + viewRiksdagenParty.getPartyName(),
				"Party Details", "Explore detailed information about political parties and their activities.");

		final Link addPartyPageLink = getPageLinkFactory().addPartyPageLink(viewRiksdagenParty);
		panelContent.addComponent(addPartyPageLink);
		panelContent.setExpandRatio(addPartyPageLink, ContentRatio.SMALL);

		// Load summary if available
		final DataContainer<ViewRiksdagenPartySummary, String> partySummaryDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartySummary.class);
		final ViewRiksdagenPartySummary viewRiksdagenPartySummary = partySummaryDataContainer.load(pageId);

		// Create a card panel similar to the politician overview
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

		// Header layout
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		final String titleText = viewRiksdagenParty.getPartyName();
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

		// Two-column layout for attributes (similar to politician)
		final HorizontalLayout attributesLayout = new HorizontalLayout();
		attributesLayout.setSpacing(true);
		attributesLayout.setWidth("100%");
		cardContent.addComponent(attributesLayout);

		// Column 1: Party Profile (From original AS_LIST)
		final VerticalLayout profileDetailsLayout = new VerticalLayout();
		profileDetailsLayout.setSpacing(true);
		profileDetailsLayout.addStyleName("card-details-column");
		profileDetailsLayout.setWidthUndefined();

		final Label profileDetailsHeader = new Label("Party Profile");
		profileDetailsHeader.addStyleName("card-section-title");
		profileDetailsLayout.addComponent(profileDetailsHeader);

		profileDetailsLayout.addComponent(createInfoRow("Party Name:", viewRiksdagenParty.getPartyName(), VaadinIcons.FLAG, "Name of the party"));
		profileDetailsLayout.addComponent(createInfoRow("Party ID:", viewRiksdagenParty.getPartyId(), VaadinIcons.CLIPBOARD_USER, "Internal party identifier"));
		profileDetailsLayout.addComponent(createInfoRow("Head Count:", String.valueOf(viewRiksdagenParty.getHeadCount()), VaadinIcons.GROUP, "Number of members currently associated with the party"));
		profileDetailsLayout.addComponent(createInfoRow("Registered Date:", String.valueOf(viewRiksdagenParty.getRegisteredDate()), VaadinIcons.CALENDAR, "Date when the party was registered"));
		profileDetailsLayout.addComponent(createInfoRow("First Assignment Date:", String.valueOf(viewRiksdagenPartySummary.getFirstAssignmentDate()), VaadinIcons.CALENDAR, "Date of the party's first assignment"));
		profileDetailsLayout.addComponent(createInfoRow("Last Assignment Date:", String.valueOf(viewRiksdagenPartySummary.getLastAssignmentDate()), VaadinIcons.CALENDAR_CLOCK, "Date of the party's last known assignment"));

		attributesLayout.addComponent(profileDetailsLayout);

		// Column 2: Service Statistics (From original AS_LIST2, using viewRiksdagenPartySummary)
		final VerticalLayout serviceStatsLayout = new VerticalLayout();
		serviceStatsLayout.setSpacing(true);
		serviceStatsLayout.addStyleName("card-details-column");
		serviceStatsLayout.setWidthUndefined();

		final Label serviceStatsHeader = new Label("Service Statistics");
		serviceStatsHeader.addStyleName("card-section-title");
		serviceStatsLayout.addComponent(serviceStatsHeader);

		if (viewRiksdagenPartySummary != null) {
			serviceStatsLayout.addComponent(createInfoRow("Current Ministers:", String.valueOf(viewRiksdagenPartySummary.getCurrentMinistryAssignments()), VaadinIcons.BULLSEYE, "Number of current assignments in government held by the party"));
			serviceStatsLayout.addComponent(createInfoRow("Current Speakers:", String.valueOf(viewRiksdagenPartySummary.getCurrentSpeakerAssignments()), VaadinIcons.BULLSEYE, "Number of current assignments held by the party"));
			serviceStatsLayout.addComponent(createInfoRow("Total Assignments:", String.valueOf(viewRiksdagenPartySummary.getTotalAssignments()), VaadinIcons.BAR_CHART, "Total number of assignments over time"));
			serviceStatsLayout.addComponent(createInfoRow("Total Days Served Government:", String.valueOf(viewRiksdagenPartySummary.getTotalDaysServedGovernment()), VaadinIcons.OFFICE, "Days served in government roles"));
			serviceStatsLayout.addComponent(createInfoRow("Total Days Served Committee:", String.valueOf(viewRiksdagenPartySummary.getTotalDaysServedCommittee()), VaadinIcons.USER_CHECK, "Days served in committee roles"));
			serviceStatsLayout.addComponent(createInfoRow("Total Days Served Parliament:", String.valueOf(viewRiksdagenPartySummary.getTotalDaysServedParliament()), VaadinIcons.CALENDAR_BRIEFCASE, "Days served in parliament roles"));
		} else {
			// If no summary is available, display a message or omit these fields
			serviceStatsLayout.addComponent(new Label("No extended statistics available."));
		}

		attributesLayout.addComponent(serviceStatsLayout);

		// After the card, add the overview layout
		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getPartyMenuItemFactory().createOverviewPage(overviewLayout, pageId);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_VIEW, ApplicationEventGroup.USER, NAME, parameters,
				pageId);
		return panelContent;
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

		if (icon != null) {
			final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
			iconLabel.addStyleName("card-info-icon");
			if (tooltip != null && !tooltip.isEmpty()) {
				iconLabel.setDescription(tooltip);
			}
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

	/**
	 * Matches.
	 *
	 * @param page the page
	 * @param parameters the parameters
	 * @return true, if successful
	 */
	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}
}

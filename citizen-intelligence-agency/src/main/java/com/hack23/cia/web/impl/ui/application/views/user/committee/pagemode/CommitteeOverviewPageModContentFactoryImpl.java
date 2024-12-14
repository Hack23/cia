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
package com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
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
 * The Class CommitteeOverviewPageModContentFactoryImpl.
 */
@Component
public final class CommitteeOverviewPageModContentFactoryImpl extends AbstractCommitteePageModContentFactoryImpl {

	/**
	 * Instantiates a new committee overview page mod content factory impl.
	 */
	public CommitteeOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		panel.setContent(panelContent);

		final String pageId = getPageId(parameters);

		final ViewRiksdagenCommittee viewRiksdagenCommittee = getItem(parameters);
		getCommitteeMenuItemFactory().createCommitteeeMenuBar(menuBar, pageId);

		createPageHeader(panel, panelContent,
				"Committee Overview " + viewRiksdagenCommittee.getEmbeddedId().getDetail(),
				"Committee Details",
				"Detailed insights into parliamentary committees and their activities.");

		final Link addCommitteePageLink = getPageLinkFactory().addCommitteePageLink(viewRiksdagenCommittee);
		panelContent.addComponent(addCommitteePageLink);
		panelContent.setExpandRatio(addCommitteePageLink, ContentRatio.SMALL);

		// Create a card panel for the committee overview
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

		final String titleText = "Committee: " + viewRiksdagenCommittee.getEmbeddedId().getDetail();
		final Label titleLabel = new Label(titleText, ContentMode.HTML);
		titleLabel.addStyleName("card-title");
		titleLabel.setWidthUndefined();
		headerLayout.addComponent(titleLabel);

		cardContent.addComponent(headerLayout);

		// Divider line
		final Label divider = new Label("<hr/>", ContentMode.HTML);
		divider.addStyleName("card-divider");
		divider.setWidth("100%");
		cardContent.addComponent(divider);

		// Two-column layout for committee attributes
		final HorizontalLayout attributesLayout = new HorizontalLayout();
		attributesLayout.setSpacing(true);
		attributesLayout.setWidth("100%");
		cardContent.addComponent(attributesLayout);

		// First column: Committee Profile
		final VerticalLayout profileDetailsLayout = new VerticalLayout();
		profileDetailsLayout.setSpacing(true);
		profileDetailsLayout.addStyleName("card-details-column");
		profileDetailsLayout.setWidthUndefined();

		final Label profileDetailsHeader = new Label("Committee Profile");
		profileDetailsHeader.addStyleName("card-section-title");
		profileDetailsLayout.addComponent(profileDetailsHeader);

		// Using AS_LIST fields:
		profileDetailsLayout.addComponent(createInfoRow("Detail:", viewRiksdagenCommittee.getEmbeddedId().getDetail(),
				VaadinIcons.INFO_CIRCLE, "Internal identifier detail for the committee"));
		profileDetailsLayout.addComponent(createInfoRow("Active:", String.valueOf(viewRiksdagenCommittee.isActive()),
				VaadinIcons.FLASH, "Is the committee currently active?"));
		profileDetailsLayout.addComponent(createInfoRow("First Assignment Date:", String.valueOf(viewRiksdagenCommittee.getFirstAssignmentDate()),
				VaadinIcons.CALENDAR, "Date the committee's first assignment started"));
		profileDetailsLayout.addComponent(createInfoRow("Last Assignment Date:", String.valueOf(viewRiksdagenCommittee.getLastAssignmentDate()),
				VaadinIcons.CALENDAR_CLOCK, "Date of the committee's most recent assignment"));

		attributesLayout.addComponent(profileDetailsLayout);

		// Second column: Service Statistics
		final VerticalLayout serviceStatsLayout = new VerticalLayout();
		serviceStatsLayout.setSpacing(true);
		serviceStatsLayout.addStyleName("card-details-column");
		serviceStatsLayout.setWidthUndefined();

		final Label serviceStatsHeader = new Label("Service Statistics");
		serviceStatsHeader.addStyleName("card-section-title");
		serviceStatsLayout.addComponent(serviceStatsHeader);

		serviceStatsLayout.addComponent(createInfoRow("Total Assignments:", String.valueOf(viewRiksdagenCommittee.getTotalAssignments()),
				VaadinIcons.BAR_CHART, "Total number of assignments handled by the committee"));
		serviceStatsLayout.addComponent(createInfoRow("Total Days Served:", String.valueOf(viewRiksdagenCommittee.getTotalDaysServed()),
				VaadinIcons.CLOCK, "Total days this committee has been active"));
		serviceStatsLayout.addComponent(createInfoRow("Current Member Size:", String.valueOf(viewRiksdagenCommittee.getCurrentMemberSize()),
				VaadinIcons.GROUP, "Number of members currently serving in the committee"));

		attributesLayout.addComponent(serviceStatsLayout);

		// After the card, add the overview layout
		final VerticalLayout overviewLayout = new VerticalLayout();
		overviewLayout.setSizeFull();
		panelContent.addComponent(overviewLayout);
		panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

		getCommitteeMenuItemFactory().createOverviewPage(overviewLayout, pageId);

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_COMMITTEE_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

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

	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

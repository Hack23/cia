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
package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyOverviewPageModContentFactoryImpl.
 */
@Component
public final class GovernmentBodyOverviewPageModContentFactoryImpl
		extends AbstractGovernmentBodyPageModContentFactoryImpl {

	/**
	 * Instantiates a new government body overview page mod content factory impl.
	 */
	public GovernmentBodyOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		panel.setContent(panelContent);

		final String pageId = getPageId(parameters);

		final List<GovernmentBodyAnnualSummary> item = getItem(parameters);

		if (item != null && !item.isEmpty()) {
			final GovernmentBodyAnnualSummary governmentBodyAnnualSummary = item.get(0);

			getGovernmentBodyMenuItemFactory().createGovernmentBodyMenuBar(menuBar, pageId, governmentBodyAnnualSummary.getName());

			createPageHeader(panel, panelContent,
					"Government Body Overview " + governmentBodyAnnualSummary.getName(),
					"Government Details",
					"Explore detailed information about government bodies and their functions.");

			// Create a card-style panel
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

			// Header layout for the card
			final HorizontalLayout headerLayout = new HorizontalLayout();
			headerLayout.setSpacing(true);
			headerLayout.setWidth("100%");
			headerLayout.addStyleName("card-header-section");

			final Label titleLabel = new Label("Government Body Information", ContentMode.HTML);
			titleLabel.addStyleName("card-title");
			titleLabel.setWidthUndefined();
			headerLayout.addComponent(titleLabel);
			cardContent.addComponent(headerLayout);

			// Divider line
			final Label divider = new Label("<hr/>", ContentMode.HTML);
			divider.addStyleName("card-divider");
			divider.setWidth("100%");
			cardContent.addComponent(divider);

			// Two-column layout
			final HorizontalLayout attributesLayout = new HorizontalLayout();
			attributesLayout.setSpacing(true);
			attributesLayout.setWidth("100%");
			cardContent.addComponent(attributesLayout);

			// Left column: Organization Profile
			final VerticalLayout profileDetailsLayout = new VerticalLayout();
			profileDetailsLayout.setSpacing(true);
			profileDetailsLayout.addStyleName("card-details-column");
			profileDetailsLayout.setWidthUndefined();

			final Label profileDetailsHeader = new Label("Organization Profile");
			profileDetailsHeader.addStyleName("card-section-title");
			profileDetailsLayout.addComponent(profileDetailsHeader);

			// Add fields for Organization Profile
			profileDetailsLayout.addComponent(createInfoRow("Name:", governmentBodyAnnualSummary.getName(), VaadinIcons.INFO_CIRCLE, "Government body name"));
			profileDetailsLayout.addComponent(createInfoRow("ID:", governmentBodyAnnualSummary.getGovermentBodyId(), VaadinIcons.CLIPBOARD_USER, "Unique government body identifier"));
			profileDetailsLayout.addComponent(createInfoRow("Ministry:", governmentBodyAnnualSummary.getMinistry(), VaadinIcons.INSTITUTION, "Ministry overseeing the body"));
			profileDetailsLayout.addComponent(createInfoRow("Org Number:", governmentBodyAnnualSummary.getOrgNumber(), VaadinIcons.BOOK, "Official organization number"));
			profileDetailsLayout.addComponent(createInfoRow("M Code:", governmentBodyAnnualSummary.getmCode(), VaadinIcons.CODE, "Internal M code identifier"));
			profileDetailsLayout.addComponent(createInfoRow("VAT:", governmentBodyAnnualSummary.getVat(), VaadinIcons.MONEY, "VAT number"));

			// Right column: Annual Statistics
			final VerticalLayout serviceStatsLayout = new VerticalLayout();
			serviceStatsLayout.setSpacing(true);
			serviceStatsLayout.addStyleName("card-details-column");
			serviceStatsLayout.setWidthUndefined();

			final Label serviceStatsHeader = new Label("Annual Statistics");
			serviceStatsHeader.addStyleName("card-section-title");
			serviceStatsLayout.addComponent(serviceStatsHeader);

			// Add fields for Annual Statistics
			serviceStatsLayout.addComponent(createInfoRow("Year:", String.valueOf(governmentBodyAnnualSummary.getYear()), VaadinIcons.CALENDAR, "The reporting year"));
			serviceStatsLayout.addComponent(createInfoRow("Annual Work Head Count:", String.valueOf(governmentBodyAnnualSummary.getAnnualWorkHeadCount()), VaadinIcons.USER_CHECK, "Annual average number of full-time equivalents"));
			serviceStatsLayout.addComponent(createInfoRow("Head Count:", String.valueOf(governmentBodyAnnualSummary.getHeadCount()), VaadinIcons.GROUP, "Total number of staff members"));
			serviceStatsLayout.addComponent(createInfoRow("Consecutive Number:", String.valueOf(governmentBodyAnnualSummary.getConsecutiveNumber()), VaadinIcons.LINES_LIST, "Internal consecutive reference number"));

			// If comment is short and informative, display it
			if (governmentBodyAnnualSummary.getComment() != null && !governmentBodyAnnualSummary.getComment().isEmpty()) {
				serviceStatsLayout.addComponent(createInfoRow("Comment:", governmentBodyAnnualSummary.getComment(), VaadinIcons.COMMENT_ELLIPSIS, "Additional remarks or notes"));
			}

			attributesLayout.addComponents(profileDetailsLayout, serviceStatsLayout);

			// After the card, add the overview layout
			final VerticalLayout overviewLayout = new VerticalLayout();
			overviewLayout.setSizeFull();
			panelContent.addComponent(overviewLayout);
			panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

			getGovernmentBodyMenuItemFactory().createOverviewPage(overviewLayout, pageId);

			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_GOVERNMENT_BODY_VIEW,
					ApplicationEventGroup.USER, NAME, parameters, pageId);
		}

		return panelContent;
	}


	@Override
	public boolean matches(final String page, final String parameters) {
		final String pageId = getPageId(parameters);
		return NAME.equals(page) && (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
				|| parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

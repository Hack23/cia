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
package com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;
import com.hack23.cia.web.impl.ui.application.views.common.constants.GovernmentBodyViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.constants.GovernmentBodyLayoutConstants;
import com.hack23.cia.web.impl.ui.application.views.common.constants.GovernmentBodyHeaderConstants;
import com.hack23.cia.web.impl.ui.application.views.common.constants.GovernmentBodyIconConstants;
import com.hack23.cia.web.impl.ui.application.views.common.constants.GovernmentBodyDescriptionConstants;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandGovernmentBodyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.HorizontalLayout;
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

			createPageHeader(panel, panelContent, governmentBodyAnnualSummary);
			createMainCard(panelContent, governmentBodyAnnualSummary, pageId);

			getPageActionEventHelper().createPageEvent(ViewAction.VISIT_GOVERNMENT_BODY_VIEW,
					ApplicationEventGroup.USER, NAME, parameters, pageId);
		}

		return panelContent;
	}

	/**
	 * Creates the page header.
	 *
	 * @param panel the panel
	 * @param panelContent the panel content
	 * @param govBody the gov body
	 */
	private void createPageHeader(final Panel panel, final VerticalLayout panelContent, final GovernmentBodyAnnualSummary govBody) {
		CardInfoRowUtil.createPageHeader(panel, panelContent,
			formatTitle(govBody, GovernmentBodyDescriptionConstants.OVERVIEW_HEADER),
			GovernmentBodyDescriptionConstants.OVERVIEW_SUBTITLE,
			GovernmentBodyDescriptionConstants.OVERVIEW_DESC);
	}

	/**
	 * Creates the main card.
	 *
	 * @param panelContent the panel content
	 * @param govBody the gov body
	 * @param pageId the page id
	 */
	private void createMainCard(final VerticalLayout panelContent, final GovernmentBodyAnnualSummary govBody, final String pageId) {
		final Panel cardPanel = createCardPanel();
		final VerticalLayout cardContent = createCardContent(cardPanel);
		panelContent.addComponent(cardPanel);
		panelContent.setExpandRatio(cardPanel, ContentRatio.SMALL_GRID);

		CardInfoRowUtil.createCardHeader(cardContent, GovernmentBodyViewConstants.BODY_INFO);

		final HorizontalLayout attributesLayout = createAttributesLayout();
		cardContent.addComponent(attributesLayout);

		addProfileAndStatsColumns(attributesLayout, govBody);
		addOverviewLayout(panelContent, pageId);
	}

	/**
	 * Creates the card panel.
	 *
	 * @return the panel
	 */
	private Panel createCardPanel() {
		final Panel cardPanel = new Panel();
		cardPanel.addStyleName(GovernmentBodyViewConstants.STYLE_GOVERNMENT_BODY_CARD);
		cardPanel.setWidth(GovernmentBodyViewConstants.WIDTH_100_PERCENT);
		cardPanel.setHeightUndefined();
		Responsive.makeResponsive(cardPanel);
		return cardPanel;
	}

	/**
	 * Creates the card content.
	 *
	 * @param cardPanel the card panel
	 * @return the vertical layout
	 */
	private VerticalLayout createCardContent(final Panel cardPanel) {
		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(GovernmentBodyLayoutConstants.USE_MARGIN);
		cardContent.setSpacing(GovernmentBodyLayoutConstants.USE_SPACING);
		cardContent.setWidth(GovernmentBodyLayoutConstants.WIDTH_100_PERCENT);
		cardPanel.setContent(cardContent);
		return cardContent;
	}

	/**
	 * Creates the attributes layout.
	 *
	 * @return the horizontal layout
	 */
	private HorizontalLayout createAttributesLayout() {
		final HorizontalLayout attributesLayout = new HorizontalLayout();
		attributesLayout.setSpacing(GovernmentBodyLayoutConstants.USE_SPACING);
		attributesLayout.setWidth(GovernmentBodyLayoutConstants.WIDTH_100_PERCENT);
		return attributesLayout;
	}

	/**
	 * Adds the profile and stats columns.
	 *
	 * @param attributesLayout the attributes layout
	 * @param govBody the gov body
	 */
	private void addProfileAndStatsColumns(final HorizontalLayout attributesLayout, final GovernmentBodyAnnualSummary govBody) {
		final VerticalLayout profileLayout = createProfileColumn(govBody);
		final VerticalLayout statsLayout = createStatsColumn(govBody);

		attributesLayout.addComponents(profileLayout, statsLayout);
	}

	/**
	 * Creates the profile column.
	 *
	 * @param govBody the gov body
	 * @return the vertical layout
	 */
	private VerticalLayout createProfileColumn(final GovernmentBodyAnnualSummary govBody) {
		final VerticalLayout profileLayout = CardInfoRowUtil.createSectionLayout(
			GovernmentBodyViewConstants.ORG_PROFILE);

		addProfileDetails(profileLayout, govBody);
		return profileLayout;
	}

	/**
	 * Adds the profile details.
	 *
	 * @param profileLayout the profile layout
	 * @param govBody the gov body
	 */
	private void addProfileDetails(final VerticalLayout profileLayout, final GovernmentBodyAnnualSummary govBody) {
		profileLayout.addComponents(
			createProfileInfoRow(GovernmentBodyHeaderConstants.NAME_FIELD,
				govBody.getName(),
				GovernmentBodyIconConstants.INFO_ICON,
				GovernmentBodyHeaderConstants.NAME_DESC),
			createProfileInfoRow(GovernmentBodyHeaderConstants.ID_FIELD,
				govBody.getGovermentBodyId(),
				GovernmentBodyIconConstants.USER_ICON,
				GovernmentBodyHeaderConstants.ID_DESC),
			// ...add other profile fields...
			createProfileInfoRow(GovernmentBodyHeaderConstants.VAT_FIELD,
				govBody.getVat(),
				GovernmentBodyIconConstants.MONEY_ICON,
				GovernmentBodyHeaderConstants.VAT_DESC)
		);
	}

	/**
	 * Creates the profile info row.
	 *
	 * @param label the label
	 * @param value the value
	 * @param icon the icon
	 * @param description the description
	 * @return the com.vaadin.ui. component
	 */
	private com.vaadin.ui.Component createProfileInfoRow(final String label, final String value, final VaadinIcons icon, final String description) {
		return CardInfoRowUtil.createInfoRow(label, value, icon, description);
	}

	/**
	 * Creates the stats column.
	 *
	 * @param govBody the gov body
	 * @return the vertical layout
	 */
	private VerticalLayout createStatsColumn(final GovernmentBodyAnnualSummary govBody) {
		final VerticalLayout statsLayout = CardInfoRowUtil.createSectionLayout(
			GovernmentBodyViewConstants.ANNUAL_STATS);

		addStatsDetails(statsLayout, govBody);
		return statsLayout;
	}

	/**
	 * Adds the stats details.
	 *
	 * @param statsLayout the stats layout
	 * @param govBody the gov body
	 */
	private void addStatsDetails(final VerticalLayout statsLayout, final GovernmentBodyAnnualSummary govBody) {
        statsLayout.addComponents(
            createStatsInfoRow(GovernmentBodyHeaderConstants.YEAR_FIELD,
                formatYear(govBody.getYear()),
                GovernmentBodyIconConstants.CALENDAR_ICON,
                GovernmentBodyHeaderConstants.YEAR_DESC),
            createStatsInfoRow(GovernmentBodyHeaderConstants.HEAD_COUNT_FIELD,
                formatCount(govBody.getHeadCount()),
                GovernmentBodyIconConstants.GROUP_ICON,
                GovernmentBodyHeaderConstants.HEAD_COUNT_DESC),
            createStatsInfoRow(GovernmentBodyHeaderConstants.ANNUAL_HEADCOUNT_FIELD,
                formatCount(govBody.getAnnualWorkHeadCount()),
                GovernmentBodyIconConstants.USER_CHECK_ICON,
                GovernmentBodyHeaderConstants.ANNUAL_HEAD_DESC)
        );

        if (govBody.getComment() != null && !govBody.getComment().isEmpty()) {
            statsLayout.addComponent(createStatsInfoRow(
                GovernmentBodyHeaderConstants.COMMENT_FIELD,
                govBody.getComment(),
                GovernmentBodyIconConstants.COMMENT_ICON,
                GovernmentBodyHeaderConstants.COMMENT_DESC));
        }
    }

    /**
     * Creates the stats info row.
     *
     * @param label the label
     * @param value the value
     * @param icon the icon
     * @param description the description
     * @return the com.vaadin.ui. component
     */
    private com.vaadin.ui.Component createStatsInfoRow(final String label, final String value, final VaadinIcons icon, final String description) {
        return CardInfoRowUtil.createInfoRow(label, value, icon, description);
    }

    /**
     * Adds the overview layout.
     *
     * @param panelContent the panel content
     * @param pageId the page id
     */
    private void addOverviewLayout(final VerticalLayout panelContent, final String pageId) {
        final VerticalLayout overviewLayout = new VerticalLayout();
        overviewLayout.setSizeFull();
        panelContent.addComponent(overviewLayout);
        panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);
        getGovernmentBodyMenuItemFactory().createOverviewPage(overviewLayout, pageId);
    }

	@Override
	public boolean matches(final String page, final String parameters) {
		return  PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_OVERVIEW.matches(page, parameters);
	}

}

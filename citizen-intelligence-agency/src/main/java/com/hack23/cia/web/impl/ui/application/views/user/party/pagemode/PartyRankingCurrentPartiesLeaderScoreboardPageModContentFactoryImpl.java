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
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
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
 * The Class PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl.
 */
@Service
public final class PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl
		extends AbstractPartyRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_RANKING_VIEW_NAME;

	/**
	 * Instantiates a new party ranking current parties leader scoreboard page mod content factory impl.
	 */
	public PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl() {
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
		getPartyRankingMenuItemFactory().createPartyRankingMenuBar(menuBar);

		createPageHeader(panel, panelContent, "Current Party Leaders Scoreboard", "Leader Performance",
				"Evaluate the performance of current party leaders including those not in government.");

		final HorizontalLayout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();
		panelContent.addComponent(chartLayout);
		panelContent.setExpandRatio(chartLayout, ContentRatio.LARGE_FORM);

		// Create a responsive row within chartLayout to hold the leader cards
		final VerticalLayout wrapper = new VerticalLayout();
		wrapper.setSizeFull();
		chartLayout.addComponent(wrapper);

		final ResponsiveRow row = RowUtil.createGridLayout(wrapper);
		row.setSizeFull();

		// Load party leaders and their politician data
		final Map<String, ViewRiksdagenPolitician> politicianMap = loadPoliticiansByPersonId();
		final Map<String, Boolean> partyLeaderMap = computePartyLeaders(politicianMap.keySet());

		// Filter only party leaders
		final List<ViewRiksdagenPolitician> partyLeaders = politicianMap.values().stream()
				.filter(p -> partyLeaderMap.getOrDefault(p.getPersonId(), false))
				.collect(Collectors.toList());

		// Sort leaders: those currently in government first, then non-government
		partyLeaders.sort((a, b) -> {
			boolean aInGov = a.isActiveGovernment();
			boolean bInGov = b.isActiveGovernment();
			if (aInGov == bInGov) {
				// If both are in the same category, sort by last name or some other criteria
				return a.getLastName().compareToIgnoreCase(b.getLastName());
			}
			// government first
			return Boolean.compare(!aInGov, !bInGov);
		});

		// Create cards for each party leader
		for (final ViewRiksdagenPolitician leader : partyLeaders) {
			final Panel cardPanel = createLeaderCard(leader);
			row.addColumn()
					.withDisplayRules(12, 6, 4, 4) // responsive column distribution
					.withComponent(cardPanel);
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;
	}

	/**
	 * Load politicians by person id.
	 *
	 * @return the map
	 */
	private Map<String, ViewRiksdagenPolitician> loadPoliticiansByPersonId() {
		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPolitician.class);

		// Load all active politicians, not just government ones, to include non-gov leaders
		final List<ViewRiksdagenPolitician> activePoliticians = politicianDataContainer.findListByProperty(
				new Object[] { Boolean.TRUE }, ViewRiksdagenPolitician_.active);

		return activePoliticians.stream()
				.collect(Collectors.toMap(ViewRiksdagenPolitician::getPersonId, p -> p));
	}

	/**
	 * Compute party leaders.
	 *
	 * @param personIds the person ids
	 * @return the map
	 */
	private Map<String, Boolean> computePartyLeaders(Iterable<String> personIds) {
		final DataContainer<ViewRiksdagenPartyRoleMember, String> partyRoleMemberDataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenPartyRoleMember.class);

		final Map<String, Boolean> result = new HashMap<>();
		for (final String personId : personIds) {
			final List<ViewRiksdagenPartyRoleMember> roles = partyRoleMemberDataContainer.findListByProperty(
					new Object[] { personId, Boolean.TRUE }, ViewRiksdagenPartyRoleMember_.personId,
					ViewRiksdagenPartyRoleMember_.active);

			final boolean isLeader = roles.stream().anyMatch(
					role -> role.getRoleCode() != null && "Partiledare".equalsIgnoreCase(role.getRoleCode().trim()));

			result.put(personId, isLeader);
		}
		return result;
	}

	/**
	 * Creates the leader card.
	 *
	 * @param leader the leader
	 * @return the panel
	 */
	private Panel createLeaderCard(final ViewRiksdagenPolitician leader) {
		final Panel cardPanel = new Panel();
		cardPanel.addStyleName("leader-baseball-card");
		cardPanel.setSizeFull();
		Responsive.makeResponsive(cardPanel);

		final VerticalLayout cardContent = new VerticalLayout();
		cardContent.setMargin(true);
		cardContent.setSpacing(true);
		cardContent.setSizeFull();
		cardPanel.setContent(cardContent);

		// Header
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		final String titleText = "Partiledare " + leader.getFirstName() + " " + leader.getLastName() + " ("
				+ leader.getParty() + ")";
		final Label titleLabel = new Label(titleText);
		titleLabel.addStyleName("card-title");
		titleLabel.setWidthUndefined();
		headerLayout.addComponent(titleLabel);

		cardContent.addComponent(headerLayout);

		// Politician detail link
		cardContent.addComponent(getPageLinkFactory().createPoliticianPageLink(leader));

		// Party link
		final Link partyLink = new Link("Party " + leader.getParty(),
				new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/" + leader.getParty()));
		partyLink.setIcon(VaadinIcons.GROUP);
		cardContent.addComponent(partyLink);

		// If leader is in government, note it
		if (leader.isActiveGovernment()) {
			final Label govLabel = new Label("Currently in Government");
			govLabel.addStyleName("card-subtitle");
			cardContent.addComponent(govLabel);
		} else {
			final Label nonGovLabel = new Label("Not in Government");
			nonGovLabel.addStyleName("card-subtitle-nongov");
			cardContent.addComponent(nonGovLabel);
		}

		// Add tenure and experience info
		final VerticalLayout statsContainer = new VerticalLayout();
		statsContainer.setSpacing(false);
		statsContainer.addStyleName("card-stats-container");
		statsContainer.setWidth("100%");

		// Tenure as party leader if available
		final Label tenureLabel = createTenureLabel(leader);
		if (tenureLabel != null) {
			statsContainer.addComponent(tenureLabel);
		}

		// Experience row
		statsContainer.addComponent(createExperienceRow(leader));

		cardContent.addComponent(statsContainer);

		return cardPanel;
	}

	/**
	 * Creates the tenure label.
	 *
	 * @param leader the leader
	 * @return the label
	 */
	private Label createTenureLabel(ViewRiksdagenPolitician leader) {
		// Assuming leader has a property or a way to compute total days as party leader
		// If not available, this may need to query ViewRiksdagenPartyRoleMember for fromDate
		// and compute tenure. For simplicity, assume total days served in party roles is a proxy.

		long daysAsPartyLeader = leader.getTotalDaysServedParty(); // hypothetical property
		if (daysAsPartyLeader > 0) {
			final Label tenureLabel = new Label("Party Leadership Tenure: " + daysAsPartyLeader + " days");
			tenureLabel.addStyleName("card-tenure-value");
			return tenureLabel;
		}
		return null;
	}

	/**
	 * Creates the experience row.
	 *
	 * @param leader the leader
	 * @return the horizontal layout
	 */
	private HorizontalLayout createExperienceRow(final ViewRiksdagenPolitician leader) {
		final HorizontalLayout experienceLayout = new HorizontalLayout();
		experienceLayout.setSpacing(true);
		experienceLayout.addStyleName("card-experience-section");

		final Label expIcon = new Label(VaadinIcons.USER_CHECK.getHtml(), ContentMode.HTML);
		expIcon.setDescription("Political Experience");

		final Label expLabel = new Label("Experience:");
		expLabel.addStyleName("card-experience-text");

		final int partyYears = (int) (leader.getTotalDaysServedParty() / 365);
		final int parliamentYears = (int) (leader.getTotalDaysServedParliament() / 365);
		final int govYears = (int) (leader.getTotalDaysServedGovernment() / 365);

		final String expText = String.format(Locale.ROOT, "Government: %dy, Party: %dy, Parliament: %dy", govYears,
				partyYears, parliamentYears);

		final Label expValue = new Label(expText);
		expValue.addStyleName("card-experience-value");

		experienceLayout.addComponents(expIcon, expLabel, expValue);
		return experienceLayout;
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
		return NAME.equals(page) && StringUtils.contains(parameters, PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.CURRENTPARTYLEADERSCORECARD.toString());
	}
}

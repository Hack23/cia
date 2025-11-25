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
package com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.impl.CoalitionPredictionService;
import com.hack23.cia.service.impl.CoalitionPredictionService.CoalitionScenario;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandParliamentRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Coalition prediction page mod content factory implementation.
 * 
 * Provides UI for coalition formation predictions, party alignment analysis,
 * and coalition stability assessment based on historical voting patterns.
 */
@Component
public final class CoalitionPredictionPageModContentFactoryImpl extends AbstractParliamentPageModContentFactoryImpl {

	private static final String DEFAULT_YEAR = "2023/24";

	@Autowired
	private CoalitionPredictionService coalitionPredictionService;

	/**
	 * Instantiates a new coalition prediction page mod content factory impl.
	 */
	public CoalitionPredictionPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		setupMenuAndHeader(menuBar, panel, panelContent);
		final String selectedYear = extractSelectedYear(parameters);

		addCoalitionScenarios(panelContent, selectedYear);
		addPartyAlignmentMatrix(panelContent, selectedYear);

		recordPageVisit(parameters, selectedYear);

		return panelContent;
	}

	/**
	 * Setup menu and header.
	 *
	 * @param menuBar the menu bar
	 * @param panel the panel
	 * @param panelContent the panel content
	 */
	private void setupMenuAndHeader(final MenuBar menuBar, final Panel panel, final VerticalLayout panelContent) {
		getParliamentMenuItemFactory().createParliamentTopicMenu(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent,
			ParliamentPageTitleConstants.COALITION_PREDICTION_TITLE,
			ParliamentPageTitleConstants.COALITION_PREDICTION_SUBTITLE,
			ParliamentPageTitleConstants.COALITION_PREDICTION_DESC);
	}

	/**
	 * Extract selected year from parameters.
	 *
	 * @param parameters the parameters
	 * @return the string
	 */
	private String extractSelectedYear(final String parameters) {
		if (parameters != null && parameters.contains("[") && parameters.contains("]")) {
			return parameters.substring(
				parameters.indexOf('[') + 1,
				parameters.lastIndexOf(']')
			);
		}
		return DEFAULT_YEAR;
	}

	/**
	 * Adds the coalition scenarios section.
	 *
	 * @param panelContent the panel content
	 * @param selectedYear the selected year
	 */
	private void addCoalitionScenarios(final VerticalLayout panelContent, final String selectedYear) {
		final List<CoalitionScenario> scenarios = coalitionPredictionService.predictCoalitions(selectedYear);

		final VerticalLayout scenariosLayout = new VerticalLayout();
		scenariosLayout.setSpacing(true);
		scenariosLayout.setMargin(true);
		scenariosLayout.setWidth(100, Unit.PERCENTAGE);

		final Label header = new Label("🔮 Coalition Formation Scenarios (Ranked by Probability)");
		header.addStyleName(ValoTheme.LABEL_H2);
		scenariosLayout.addComponent(header);

		int rank = 1;
		for (final CoalitionScenario scenario : scenarios) {
			scenariosLayout.addComponent(createScenarioRow(rank++, scenario));
		}

		panelContent.addComponent(scenariosLayout);
		panelContent.setExpandRatio(scenariosLayout, ContentRatio.LARGE);
	}

	/**
	 * Creates a single coalition scenario row.
	 *
	 * @param rank the rank
	 * @param scenario the scenario
	 * @return the horizontal layout
	 */
	private HorizontalLayout createScenarioRow(final int rank, final CoalitionScenario scenario) {
		final HorizontalLayout row = new HorizontalLayout();
		row.setSpacing(true);
		row.setWidth(100, Unit.PERCENTAGE);

		// Rank badge
		final Label rankLabel = new Label("#" + rank);
		rankLabel.addStyleName(ValoTheme.LABEL_H3);
		rankLabel.setWidth(50, Unit.PIXELS);
		row.addComponent(rankLabel);

		// Coalition parties
		final Label partiesLabel = new Label(String.join(" + ", scenario.getParties()));
		partiesLabel.addStyleName(ValoTheme.LABEL_BOLD);
		partiesLabel.setWidth(200, Unit.PIXELS);
		row.addComponent(partiesLabel);

		// Probability bar
		final ProgressBar probabilityBar = new ProgressBar();
		probabilityBar.setValue((float) scenario.getProbability());
		probabilityBar.setCaption(String.format("%.1f%% probability", scenario.getProbability() * 100));
		probabilityBar.setWidth(200, Unit.PIXELS);
		row.addComponent(probabilityBar);

		// Stability index
		final Label stabilityLabel = new Label(
			String.format("Stability: %d/100", scenario.getStabilityIndex()));
		stabilityLabel.addStyleName(
			scenario.getStabilityIndex() > 70 ? ValoTheme.LABEL_SUCCESS : ValoTheme.LABEL_FAILURE);
		stabilityLabel.setWidth(150, Unit.PIXELS);
		row.addComponent(stabilityLabel);

		// Seats
		final Label seatsLabel = new Label(String.format("%d seats", scenario.getTotalSeats()));
		seatsLabel.setWidth(100, Unit.PIXELS);
		row.addComponent(seatsLabel);

		// Bloc relationship
		final Label blocLabel = new Label(scenario.getBlocRelationship());
		blocLabel.addStyleName(ValoTheme.LABEL_TINY);
		blocLabel.setWidth(150, Unit.PIXELS);
		row.addComponent(blocLabel);

		return row;
	}

	/**
	 * Adds the party alignment matrix section.
	 *
	 * @param panelContent the panel content
	 * @param selectedYear the selected year
	 */
	private void addPartyAlignmentMatrix(final VerticalLayout panelContent, final String selectedYear) {
		final Map<String, Map<String, Double>> alignmentMatrix = 
			coalitionPredictionService.getAlignmentMatrix(selectedYear);

		final VerticalLayout matrixLayout = new VerticalLayout();
		matrixLayout.setSpacing(true);
		matrixLayout.setMargin(true);
		matrixLayout.setWidth(100, Unit.PERCENTAGE);

		final Label header = new Label("📊 Party Alignment Matrix (Voting Pattern Compatibility)");
		header.addStyleName(ValoTheme.LABEL_H2);
		matrixLayout.addComponent(header);

		final Label description = new Label(
			"Alignment rates show how often parties vote the same way. Higher rates indicate coalition compatibility.");
		description.addStyleName(ValoTheme.LABEL_SMALL);
		matrixLayout.addComponent(description);

		// Create a simple text-based matrix
		for (final Map.Entry<String, Map<String, Double>> entry : alignmentMatrix.entrySet()) {
			final String party = entry.getKey();
			final Map<String, Double> alignments = entry.getValue();

			final HorizontalLayout partyRow = new HorizontalLayout();
			partyRow.setSpacing(true);

			final Label partyLabel = new Label(party);
			partyLabel.addStyleName(ValoTheme.LABEL_BOLD);
			partyLabel.setWidth(50, Unit.PIXELS);
			partyRow.addComponent(partyLabel);

			for (final Map.Entry<String, Double> alignment : alignments.entrySet()) {
				final Label alignmentLabel = new Label(
					String.format("%s: %.1f%%", alignment.getKey(), alignment.getValue() * 100));
				alignmentLabel.setWidth(120, Unit.PIXELS);
				partyRow.addComponent(alignmentLabel);
			}

			matrixLayout.addComponent(partyRow);
		}

		panelContent.addComponent(matrixLayout);
		panelContent.setExpandRatio(matrixLayout, ContentRatio.SMALL_GRID);
	}

	/**
	 * Record page visit.
	 *
	 * @param parameters the parameters
	 * @param selectedYear the selected year
	 */
	private void recordPageVisit(final String parameters, final String selectedYear) {
		getPageActionEventHelper().createPageEvent(
			ViewAction.VISIT_PARLIAMENT_RANKING_VIEW,
			ApplicationEventGroup.USER,
			NAME,
			parameters,
			selectedYear
		);
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandParliamentRankingConstants.COMMAND_COALITION_PREDICTION.matches(page, parameters);
	}
}

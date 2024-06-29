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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.DecisionFlowChartManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.DecisionDataFactory;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.ProposalCommitteeeSummary;
import com.hack23.cia.web.widgets.charts.SankeyChart;
import com.vaadin.ui.TextArea;

/**
 * The Class DecisionFlowChartManagerImpl.
 */
@Service
public final class DecisionFlowChartManagerImpl implements DecisionFlowChartManager {

	/** The decision data factory. */
	@Autowired
	private DecisionDataFactory decisionDataFactory;

	/**
	 * Instantiates a new decision flow chart manager impl.
	 */
	public DecisionFlowChartManagerImpl() {
		super();
	}

	/**
	 * Adds the commitee summary.
	 *
	 * @param stringBuilder         the string builder
	 * @param entry                 the entry
	 * @param vewRiksdagenCommittee the vew riksdagen committee
	 */
	private static void addCommiteeSummary(final StringBuilder stringBuilder,
			final Entry<String, List<ProposalCommitteeeSummary>> entry,
			final Optional<ViewRiksdagenCommittee> vewRiksdagenCommittee) {
		if (vewRiksdagenCommittee.isPresent()) {

			final Map<String, List<ProposalCommitteeeSummary>> docTypeMap = entry.getValue().stream()
					.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

			stringBuilder.append('\n').append(vewRiksdagenCommittee.get().getEmbeddedId().getDetail());
			for (final Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
				if (docEntry.getKey().length() > 0 && entry.getKey().length() > 0) {
					addEntry(stringBuilder, docEntry);
				}
			}
		}
	}

	/**
	 * Adds the decision data rows.
	 *
	 * @param chart
	 *            the chart
	 * @param entry
	 *            the entry
	 * @param vewRiksdagenCommittee
	 *            the vew riksdagen committee
	 */
	private static void addDecisionDataRows(final SankeyChart chart,
			final Entry<String, List<ProposalCommitteeeSummary>> entry,
			final ViewRiksdagenCommittee vewRiksdagenCommittee) {
		final Map<String, List<ProposalCommitteeeSummary>> decisionMap = entry.getValue().stream()
				.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));

		for (final Entry<String, List<ProposalCommitteeeSummary>> decisionEntry : decisionMap.entrySet()) {
			if (decisionEntry.getKey().length() > 0 && entry.getKey().length() > 0) {
				chart.addDataRow(vewRiksdagenCommittee.getEmbeddedId().getDetail(), decisionEntry.getKey(),
						decisionEntry.getValue().size());
			}
		}
	}

	/**
	 * Adds the doc type data rows.
	 *
	 * @param chart
	 *            the chart
	 * @param entry
	 *            the entry
	 * @param vewRiksdagenCommittee
	 *            the vew riksdagen committee
	 */
	private static void addDocTypeDataRows(final SankeyChart chart,
			final Entry<String, List<ProposalCommitteeeSummary>> entry,
			final ViewRiksdagenCommittee vewRiksdagenCommittee) {
		final Map<String, List<ProposalCommitteeeSummary>> docTypeMap = entry.getValue().stream()
				.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

		for (final Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
			if (docEntry.getKey().length() > 0 && entry.getKey().length() > 0) {
				chart.addDataRow(docEntry.getKey(), vewRiksdagenCommittee.getEmbeddedId().getDetail(),
						docEntry.getValue().size());

			}
		}
	}

	/**
	 * Adds the doc type decision data rows.
	 *
	 * @param chart
	 *            the chart
	 * @param entry
	 *            the entry
	 */
	private static void addDocTypeDecisionDataRows(final SankeyChart chart,
			final Entry<String, List<ProposalCommitteeeSummary>> entry) {
		final Map<String, List<ProposalCommitteeeSummary>> docTypeMap = entry.getValue().stream()
				.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

		for (final Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {

			final Map<String, List<ProposalCommitteeeSummary>> decisionMap = docEntry.getValue().stream()
					.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));

			for (final Entry<String, List<ProposalCommitteeeSummary>> decisionEntry : decisionMap.entrySet()) {
				if (decisionEntry.getKey().length() > 0 && entry.getKey().length() > 0) {
					chart.addDataRow(docEntry.getKey(), decisionEntry.getKey(), decisionEntry.getValue().size());
				}
			}
		}
	}

	/**
	 * Adds the entry.
	 *
	 * @param stringBuilder the string builder
	 * @param entry         the entry
	 * @param docEntry      the doc entry
	 */
	private static void addEntry(final StringBuilder stringBuilder,
			final Entry<String, List<ProposalCommitteeeSummary>> docEntry) {
		stringBuilder.append("\n ( ").append(docEntry.getValue().size()).append(' ').append(docEntry.getKey()).append(" -> ");

		final Map<String, List<ProposalCommitteeeSummary>> decisionMap = docEntry.getValue().stream()
				.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));

		for (final Entry<String, List<ProposalCommitteeeSummary>> decisionEntry : decisionMap.entrySet()) {
			if (decisionEntry.getKey().length() > 0) {
				stringBuilder.append("\n   ").append(decisionEntry.getValue().size()).append(' ').append(decisionEntry.getKey()).append(' ');

				final List<ProposalCommitteeeSummary> decisionSummaryList = decisionEntry.getValue();

				for (final ProposalCommitteeeSummary proposalCommitteeeSummary : decisionSummaryList) {
					stringBuilder.append("\n    ").append(proposalCommitteeeSummary.getDecision()).append(':').append(proposalCommitteeeSummary.getWording()).append(' ').append(proposalCommitteeeSummary.getWording2()).append(' ');

				}

			}
		}
		stringBuilder.append(')');
	}

	@Override
	public SankeyChart createAllDecisionFlow(final Map<String, List<ViewRiksdagenCommittee>> committeeMap,final String rm) {
		final List<ProposalCommitteeeSummary> createCommitteeSummary = decisionDataFactory.createCommitteeSummary(rm);


		final SankeyChart chart = new SankeyChart();

		final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = createCommitteeSummary.stream()
				.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));


		for (final Entry<String, List<ProposalCommitteeeSummary>> entry : orgProposalMap.entrySet()) {

			if (committeeMap.containsKey(entry.getKey())) {

				final Optional<ViewRiksdagenCommittee> vewRiksdagenCommittee = committeeMap.get(entry.getKey()).stream().findFirst();
				if (vewRiksdagenCommittee.isPresent()) {
					addDocTypeDataRows(chart, entry, vewRiksdagenCommittee.get());
					addDecisionDataRows(chart, entry, vewRiksdagenCommittee.get());
				}
			}
		}

		chart.drawChart();
		return chart;
	}

	@Override
	public SankeyChart createCommitteeDecisionFlow(final ViewRiksdagenCommittee viewRiksdagenCommittee,
			final Map<String, List<ViewRiksdagenCommittee>> committeeMap, final String rm) {
		final List<ProposalCommitteeeSummary> createCommitteeSummary = decisionDataFactory.createCommitteeSummary(rm);

		final SankeyChart chart = new SankeyChart();

		final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = createCommitteeSummary.stream()
				.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));

		for (final Entry<String, List<ProposalCommitteeeSummary>> entry : orgProposalMap.entrySet()) {
			if (committeeMap.containsKey(entry.getKey().toUpperCase(Locale.ENGLISH))
					&& viewRiksdagenCommittee.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH).equals(entry.getKey().toUpperCase(Locale.ENGLISH))) {
				addDocTypeDecisionDataRows(chart, entry);
			}
		}

		chart.drawChart();
		return chart;
	}

	@Override
	public TextArea createCommitteeeDecisionSummary(final Map<String, List<ViewRiksdagenCommittee>> committeeMap,final String rm) {
		final TextArea area = new TextArea("Summary");
		final StringBuilder stringBuilder = new StringBuilder();
		final List<ProposalCommitteeeSummary> createCommitteeSummary = decisionDataFactory.createCommitteeSummary(rm);

		final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = createCommitteeSummary.stream()
				.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));

		for (final Entry<String, List<ProposalCommitteeeSummary>> entry : orgProposalMap.entrySet()) {
			if (committeeMap.containsKey(entry.getKey().toUpperCase(Locale.ENGLISH))) {
				addCommiteeSummary(stringBuilder, entry, committeeMap.get(entry.getKey()).stream().findFirst());
			}
		}
		area.setValue(stringBuilder.toString());
		return area;
	}

	@Override
	public TextArea createCommitteeeDecisionSummary(final ViewRiksdagenCommittee viewRiksdagenCommittee, final String rm) {
		final TextArea area = new TextArea("Summary");
		final StringBuilder stringBuilder = new StringBuilder();
		final List<ProposalCommitteeeSummary> createCommitteeSummary = decisionDataFactory.createCommitteeSummary(rm);

		final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = createCommitteeSummary.stream()
				.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));

		final List<ProposalCommitteeeSummary> list = orgProposalMap.get(viewRiksdagenCommittee.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH));

		addCommiteeSummary(stringBuilder, list,viewRiksdagenCommittee );

		area.setValue(stringBuilder.toString());
		return area;
	}


	/**
	 * Adds the commitee summary.
	 *
	 * @param stringBuilder the string builder
	 * @param entry the entry
	 * @param vewRiksdagenCommittee the vew riksdagen committee
	 */
	private static void addCommiteeSummary(final StringBuilder stringBuilder,
			final List<ProposalCommitteeeSummary> entry,
			final ViewRiksdagenCommittee vewRiksdagenCommittee) {
		if (vewRiksdagenCommittee != null) {

			final Map<String, List<ProposalCommitteeeSummary>> docTypeMap = entry.stream()
					.collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

			stringBuilder.append('\n').append(vewRiksdagenCommittee.getEmbeddedId().getDetail());
			for (final Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
					addEntry(stringBuilder, docEntry);
			}
		}
	}


}

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

    @Autowired
    private DecisionDataFactory decisionDataFactory;

    /**
     * Instantiates a new decision flow chart manager impl.
     */
    public DecisionFlowChartManagerImpl() {
        super();
    }

    @Override
    public SankeyChart createAllDecisionFlow(final Map<String, List<ViewRiksdagenCommittee>> committeeMap,
                                             final String rm) {
        // Retrieve all proposals for the given 'rm'
        final List<ProposalCommitteeeSummary> committeeSummaries = decisionDataFactory.createCommitteeSummary(rm);

        // Prepare the sankey chart
        final SankeyChart chart = new SankeyChart();

        // Group by organization
        final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap =
            committeeSummaries.stream()
                              .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));

        // For each org, if we have a matching committee in 'committeeMap', add data rows
        for (final Entry<String, List<ProposalCommitteeeSummary>> orgEntry : orgProposalMap.entrySet()) {
            final String orgKey = orgEntry.getKey();
            final List<ProposalCommitteeeSummary> orgSummaries = orgEntry.getValue();
            if (committeeMap.containsKey(orgKey)) {
                final Optional<ViewRiksdagenCommittee> committeeOpt =
                    committeeMap.get(orgKey).stream().findFirst();
                if (committeeOpt.isPresent()) {
                    final ViewRiksdagenCommittee committee = committeeOpt.get();
                    addDocTypeDataRows(chart, orgSummaries, committee);
                    addDecisionDataRows(chart, orgSummaries, committee);
                }
            }
        }

        chart.drawChart();
        return chart;
    }

    @Override
    public SankeyChart createCommitteeDecisionFlow(final ViewRiksdagenCommittee viewRiksdagenCommittee,
                                                   final Map<String, List<ViewRiksdagenCommittee>> committeeMap,
                                                   final String rm) {
        final List<ProposalCommitteeeSummary> committeeSummaries = decisionDataFactory.createCommitteeSummary(rm);

        final SankeyChart chart = new SankeyChart();
        // Group by organization
        final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap =
            committeeSummaries.stream()
                              .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));

        final String targetOrg = viewRiksdagenCommittee.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH);
        for (final Entry<String, List<ProposalCommitteeeSummary>> orgEntry : orgProposalMap.entrySet()) {
            final String orgKeyUpper = orgEntry.getKey().toUpperCase(Locale.ENGLISH);
            if (committeeMap.containsKey(orgKeyUpper) && targetOrg.equals(orgKeyUpper)) {
                addDocTypeDecisionDataRows(chart, orgEntry.getValue());
            }
        }

        chart.drawChart();
        return chart;
    }

    @Override
    public TextArea createCommitteeeDecisionSummary(final Map<String, List<ViewRiksdagenCommittee>> committeeMap,
                                                    final String rm) {
        final TextArea area = new TextArea("Summary");
        final StringBuilder builder = new StringBuilder();

        final List<ProposalCommitteeeSummary> committeeSummaries = decisionDataFactory.createCommitteeSummary(rm);

        // Group by organization
        final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap =
            committeeSummaries.stream()
                              .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));

        for (final Entry<String, List<ProposalCommitteeeSummary>> orgEntry : orgProposalMap.entrySet()) {
            final String orgKeyUpper = orgEntry.getKey().toUpperCase(Locale.ENGLISH);
            if (committeeMap.containsKey(orgKeyUpper)) {
                // Grab the first committee from the list
                final ViewRiksdagenCommittee committee =
                    committeeMap.get(orgKeyUpper).stream().findFirst().orElse(null);
                addCommiteeSummary(builder, orgEntry.getValue(), committee);
            }
        }
        area.setValue(builder.toString());
        return area;
    }

    @Override
    public TextArea createCommitteeeDecisionSummary(final ViewRiksdagenCommittee viewRiksdagenCommittee,
                                                    final String rm) {
        final TextArea area = new TextArea("Summary");
        final StringBuilder builder = new StringBuilder();

        final List<ProposalCommitteeeSummary> committeeSummaries = decisionDataFactory.createCommitteeSummary(rm);

        // Group by organization
        final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap =
            committeeSummaries.stream()
                              .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));

        final String targetOrg = viewRiksdagenCommittee.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH);

        // Retrieve the relevant summaries for the single committee
        final List<ProposalCommitteeeSummary> list = orgProposalMap.get(targetOrg);
        addCommiteeSummary(builder, list, viewRiksdagenCommittee);

        area.setValue(builder.toString());
        return area;
    }

    /**
     * Helper to add doc type data rows to sankey.
     *
     * @param chart    the chart
     * @param proposals the summary list for the org
     * @param committee the committee
     */
    private static void addDocTypeDataRows(final SankeyChart chart,
                                           final List<ProposalCommitteeeSummary> proposals,
                                           final ViewRiksdagenCommittee committee) {
        // Group by doc type
        final Map<String, List<ProposalCommitteeeSummary>> docTypeMap =
            proposals.stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

        final String committeeName = committee.getEmbeddedId().getDetail();
        for (final Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
            final String docType = docEntry.getKey();
            if (!docType.isEmpty()) {
                chart.addDataRow(docType, committeeName, docEntry.getValue().size());
            }
        }
    }

    /**
     * Helper to add decision data rows to sankey.
     *
     * @param chart    the chart
     * @param proposals the summary list for the org
     * @param committee the committee
     */
    private static void addDecisionDataRows(final SankeyChart chart,
                                            final List<ProposalCommitteeeSummary> proposals,
                                            final ViewRiksdagenCommittee committee) {
        // Group by decision
        final Map<String, List<ProposalCommitteeeSummary>> decisionMap =
            proposals.stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));

        final String committeeName = committee.getEmbeddedId().getDetail();
        for (final Entry<String, List<ProposalCommitteeeSummary>> decisionEntry : decisionMap.entrySet()) {
            final String decision = decisionEntry.getKey();
            if (!decision.isEmpty()) {
                chart.addDataRow(committeeName, decision, decisionEntry.getValue().size());
            }
        }
    }

    /**
     * Helper to add doc type -> decision data rows to sankey.
     *
     * @param chart   the chart
     * @param proposals the summary list for the org
     */
    private static void addDocTypeDecisionDataRows(final SankeyChart chart,
                                                   final List<ProposalCommitteeeSummary> proposals) {
        // Group by doc type
        final Map<String, List<ProposalCommitteeeSummary>> docTypeMap =
            proposals.stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

        for (final Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
            final String docType = docEntry.getKey();
            if (!docType.isEmpty()) {
                // Now group by decision
                final Map<String, List<ProposalCommitteeeSummary>> decisionMap =
                    docEntry.getValue().stream()
                            .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));
                for (final Entry<String, List<ProposalCommitteeeSummary>> decEntry : decisionMap.entrySet()) {
                    final String decision = decEntry.getKey();
                    if (!decision.isEmpty()) {
                        chart.addDataRow(docType, decision, decEntry.getValue().size());
                    }
                }
            }
        }
    }

    /**
     * Helper to create a textual committee summary in a StringBuilder.
     *
     * @param builder     the StringBuilder
     * @param proposals   the proposal summaries for this committee
     * @param committee   the committee
     */
    private static void addCommiteeSummary(final StringBuilder builder,
                                           final List<ProposalCommitteeeSummary> proposals,
                                           final ViewRiksdagenCommittee committee) {
        // If no committee or proposals, bail out
        if (committee == null || proposals == null || proposals.isEmpty()) {
            return;
        }

        final String detail = committee.getEmbeddedId().getDetail();
        builder.append('\n').append(detail);

        // Group by doc type
        final Map<String, List<ProposalCommitteeeSummary>> docTypeMap =
            proposals.stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

        for (final Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
            addEntry(builder, docEntry);
        }
    }

    /**
     * Build a textual representation of doc type -> decision -> proposals.
     *
     * @param builder the builder
     * @param docEntry the doc entry
     */
    private static void addEntry(final StringBuilder builder,
                                 final Entry<String, List<ProposalCommitteeeSummary>> docEntry) {
        final String docType = docEntry.getKey();
        final List<ProposalCommitteeeSummary> docTypeList = docEntry.getValue();

        builder.append("\n ( ")
               .append(docTypeList.size())
               .append(' ')
               .append(docType)
               .append(" -> ");

        // Group by decision
        final Map<String, List<ProposalCommitteeeSummary>> decisionMap =
            docTypeList.stream()
                       .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));

        for (final Entry<String, List<ProposalCommitteeeSummary>> decisionEntry : decisionMap.entrySet()) {
            final String decision = decisionEntry.getKey();
            if (!decision.isEmpty()) {
                builder.append("\n   ")
                       .append(decisionEntry.getValue().size())
                       .append(' ')
                       .append(decision)
                       .append(' ');

                for (final ProposalCommitteeeSummary summary : decisionEntry.getValue()) {
                    builder.append("\n    ")
                           .append(summary.getDecision())
                           .append(':')
                           .append(summary.getWording())
                           .append(' ')
                           .append(summary.getWording2())
                           .append(' ');
                }
            }
        }
        builder.append(')');
    }
}

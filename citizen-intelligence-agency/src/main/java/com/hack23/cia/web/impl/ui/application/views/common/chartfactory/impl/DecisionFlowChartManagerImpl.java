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

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
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
     * Gets the org proposal map.
     *
     * @param reportMonth the report month
     * @return the org proposal map
     */
    // Core helper methods for common operations
    private Map<String, List<ProposalCommitteeeSummary>> getOrgProposalMap(final String reportMonth) {
        return decisionDataFactory.createCommitteeSummary(reportMonth)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                    ProposalCommitteeeSummary::getOrg,
                    Collectors.mapping(
                        Function.identity(),
                        Collectors.toList()
                    )
                ));
    }

    /**
     * Find committee.
     *
     * @param committeeSummaryMap the committee summary map
     * @param orgKey the org key
     * @return the optional
     */
    private Optional<ViewRiksdagenCommittee> findCommittee(
            final Map<String, List<ViewRiksdagenCommittee>> committeeSummaryMap,
            final String orgKey) {
        return Optional.ofNullable(committeeSummaryMap.get(orgKey))
                .flatMap(list -> list.stream().findFirst());
    }

    /**
     * Creates the all decision flow.
     *
     * @param committeeSummaryMap the committee summary map
     * @param reportMonth the report month
     * @return the sankey chart
     */
    @Override
    public SankeyChart createAllDecisionFlow(
            final Map<String, List<ViewRiksdagenCommittee>> committeeSummaryMap,
            final String reportMonth) {

        Objects.requireNonNull(committeeSummaryMap, "Committee summary map cannot be null");
        Objects.requireNonNull(reportMonth, "Report month cannot be null");

        final SankeyChart sankeyChart = new SankeyChart();

        getOrgProposalMap(reportMonth).entrySet().stream()
            .filter(entry -> entry.getKey() != null && !entry.getValue().isEmpty())
            .forEach(entry ->
                findCommittee(committeeSummaryMap, entry.getKey())
                    .ifPresent(committee ->
                        addChartData(sankeyChart, entry.getValue(), committee)));

        sankeyChart.drawChart();
        return sankeyChart;
    }

    /**
     * Creates the committee decision flow.
     *
     * @param viewRiksdagenCommittee the view riksdagen committee
     * @param committeeSummaryMap the committee summary map
     * @param reportMonth the report month
     * @return the sankey chart
     */
    @Override
    public SankeyChart createCommitteeDecisionFlow(
            final ViewRiksdagenCommittee viewRiksdagenCommittee,
            final Map<String, List<ViewRiksdagenCommittee>> committeeSummaryMap,
            final String reportMonth) {

        Objects.requireNonNull(viewRiksdagenCommittee, "Committee cannot be null");

        final SankeyChart sankeyChart = new SankeyChart();
        final String targetOrg = viewRiksdagenCommittee.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH);

        getOrgProposalMap(reportMonth).entrySet().stream()
            .filter(entry -> targetOrg.equals(entry.getKey().toUpperCase(Locale.ENGLISH)))
            .forEach(entry -> addDocTypeDecisionDataRows(sankeyChart, entry.getValue()));

        sankeyChart.drawChart();
        return sankeyChart;
    }

    /**
     * Creates the committeee decision summary.
     *
     * @param committeeSummaryMap the committee summary map
     * @param reportMonth the report month
     * @return the text area
     */
    @Override
    public TextArea createCommitteeeDecisionSummary(
            final Map<String, List<ViewRiksdagenCommittee>> committeeSummaryMap,
            final String reportMonth) {

        final TextArea area = new TextArea("Summary");
        final StringBuilder builder = new StringBuilder();
        final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = getOrgProposalMap(reportMonth);

        orgProposalMap.forEach((orgKey, proposals) -> {
            final String orgKeyUpper = orgKey.toUpperCase(Locale.ENGLISH);
            findCommittee(committeeSummaryMap, orgKeyUpper)
                .ifPresent(committee -> addCommiteeSummary(builder, proposals, committee));
        });

        area.setValue(builder.toString());
        return area;
    }

    /**
     * Creates the committeee decision summary.
     *
     * @param viewRiksdagenCommittee the view riksdagen committee
     * @param reportMonth the report month
     * @return the text area
     */
    @Override
    public TextArea createCommitteeeDecisionSummary(
            final ViewRiksdagenCommittee viewRiksdagenCommittee,
            final String reportMonth) {

        final TextArea area = new TextArea("Summary");
        final StringBuilder builder = new StringBuilder();
        final String targetOrg = viewRiksdagenCommittee.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH);

        final List<ProposalCommitteeeSummary> proposals =
            getOrgProposalMap(reportMonth).get(targetOrg);

        addCommiteeSummary(builder, proposals, viewRiksdagenCommittee);
        area.setValue(builder.toString());
        return area;
    }

    /**
     * Adds the chart data.
     *
     * @param sankeyChart the sankey chart
     * @param proposals the proposals
     * @param committee the committee
     */
    private void addChartData(
            final SankeyChart sankeyChart,
            final List<ProposalCommitteeeSummary> proposals,
            final ViewRiksdagenCommittee committee) {

        addDocTypeDataRows(sankeyChart, proposals, committee);
        addDecisionDataRows(sankeyChart, proposals, committee);
    }

    /**
     * Adds the doc type data rows.
     *
     * @param sankeyChart the sankey chart
     * @param proposals the proposals
     * @param committee the committee
     */
    private static void addDocTypeDataRows(
            final SankeyChart sankeyChart,
            final List<ProposalCommitteeeSummary> proposals,
            final ViewRiksdagenCommittee committee) {

        final String committeeName = committee.getEmbeddedId().getDetail();

        Optional.ofNullable(proposals)
            .stream()
            .flatMap(Collection::stream)
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                ProposalCommitteeeSummary::getDocType,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    docProposals -> {
                        if (!docProposals.get(0).getDocType().isEmpty()) {
                            sankeyChart.addDataRow(
                                docProposals.get(0).getDocType(),
                                committeeName,
                                docProposals.size()
                            );
                        }
                        return docProposals;
                    }
                )
            ));
    }


    /**
     * Adds the decision data rows.
     *
     * @param sankeyChart the sankey chart
     * @param proposals the proposals
     * @param committee the committee
     */
    private static void addDecisionDataRows(
            final SankeyChart sankeyChart,
            final List<ProposalCommitteeeSummary> proposals,
            final ViewRiksdagenCommittee committee) {

        final String committeeName = committee.getEmbeddedId().getDetail();

        proposals.stream()
            .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision))
            .forEach((decision, decisionProposals) -> {
                if (!decision.isEmpty()) {
                    sankeyChart.addDataRow(committeeName, decision, decisionProposals.size());
                }
            });
    }

    /**
     * Adds the doc type decision data rows.
     *
     * @param sankeyChart the sankey chart
     * @param proposals the proposals
     */
    private static void addDocTypeDecisionDataRows(
            final SankeyChart sankeyChart,
            final List<ProposalCommitteeeSummary> proposals) {

        Optional.ofNullable(proposals)
            .stream()
            .flatMap(Collection::stream)
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                ProposalCommitteeeSummary::getDocType,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    docTypeProposals -> {
                        if (!docTypeProposals.get(0).getDocType().isEmpty()) {
                            addDecisionRowsForDocType(
                                sankeyChart,
                                docTypeProposals.get(0).getDocType(),
                                docTypeProposals
                            );
                        }
                        return docTypeProposals;
                    }
                )
            ));
    }

    /**
     * Adds the decision rows for doc type.
     *
     * @param sankeyChart the sankey chart
     * @param docType the doc type
     * @param proposals the proposals
     */
    private static void addDecisionRowsForDocType(
            final SankeyChart sankeyChart,
            final String docType,
            final List<ProposalCommitteeeSummary> proposals) {

        proposals.stream()
            .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision))
            .forEach((decision, decisionProposals) -> {
                if (!decision.isEmpty()) {
                    sankeyChart.addDataRow(docType, decision, decisionProposals.size());
                }
            });
    }

    /**
     * Adds the commitee summary.
     *
     * @param builder the builder
     * @param proposals the proposals
     * @param committee the committee
     */
    private static void addCommiteeSummary(
            final StringBuilder builder,
            final List<ProposalCommitteeeSummary> proposals,
            final ViewRiksdagenCommittee committee) {

        if (committee == null || proposals == null || proposals.isEmpty()) {
            return;
        }

        builder.append('\n').append(committee.getEmbeddedId().getDetail());

        proposals.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                ProposalCommitteeeSummary::getDocType,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    docProposals -> {
                        addSummaryEntry(builder, docProposals.get(0).getDocType(), docProposals);
                        return docProposals;
                    }
                )
            ));
    }

    /**
     * Adds the summary entry.
     *
     * @param builder the builder
     * @param docType the doc type
     * @param docTypeList the doc type list
     */
    private static void addSummaryEntry(
            final StringBuilder builder,
            final String docType,
            final List<ProposalCommitteeeSummary> docTypeList) {

        builder.append("\n ( ")
               .append(docTypeList.size())
               .append(' ')
               .append(docType)
               .append(" -> ");

        docTypeList.stream()
            .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision))
            .forEach((decision, decisionProposals) ->
                addDecisionDetails(builder, decision, decisionProposals));

        builder.append(')');
    }

    /**
     * Adds the decision details.
     *
     * @param builder the builder
     * @param decision the decision
     * @param summaries the summaries
     */
    private static void addDecisionDetails(
            final StringBuilder builder,
            final String decision,
            final List<ProposalCommitteeeSummary> summaries) {

        if (!decision.isEmpty()) {
            builder.append("\n   ")
                   .append(summaries.size())
                   .append(' ')
                   .append(decision)
                   .append(' ');

            summaries.stream()
                .filter(Objects::nonNull)
                .forEach(summary ->
                    builder.append("\n    ")
                          .append(summary.getDecision())
                          .append(':')
                          .append(summary.getWording())
                          .append(' ')
                          .append(summary.getWording2())
                          .append(' '));
        }
    }

}

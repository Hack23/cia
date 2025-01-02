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
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
     * Creates the all decision flow.
     *
     * @param committeeMap the committee map
     * @param rm the rm
     * @return the sankey chart
     */
    @Override
    public SankeyChart createAllDecisionFlow(final Map<String, List<ViewRiksdagenCommittee>> committeeMap,
                                           final String rm) {
        validateInput(committeeMap, rm);
        final List<ProposalCommitteeeSummary> committeeSummaries = decisionDataFactory.createCommitteeSummary(rm);
        return createAllDecisionFlowChart(committeeMap, committeeSummaries);
    }

    /**
     * Creates the committee decision flow.
     *
     * @param viewRiksdagenCommittee the view riksdagen committee
     * @param committeeMap the committee map
     * @param rm the rm
     * @return the sankey chart
     */
    @Override
    public SankeyChart createCommitteeDecisionFlow(final ViewRiksdagenCommittee viewRiksdagenCommittee,
                                                 final Map<String, List<ViewRiksdagenCommittee>> committeeMap,
                                                 final String rm) {
        validateCommitteeInput(viewRiksdagenCommittee, committeeMap, rm);
        final List<ProposalCommitteeeSummary> committeeSummaries = decisionDataFactory.createCommitteeSummary(rm);
        return createCommitteeSpecificChart(viewRiksdagenCommittee, committeeSummaries);
    }

    /**
     * Creates the committeee decision summary.
     *
     * @param committeeMap the committee map
     * @param rm the rm
     * @return the text area
     */
    @Override
    public TextArea createCommitteeeDecisionSummary(final Map<String, List<ViewRiksdagenCommittee>> committeeMap,
                                                  final String rm) {
        validateInput(committeeMap, rm);
        final List<ProposalCommitteeeSummary> committeeSummaries = decisionDataFactory.createCommitteeSummary(rm);
        return createGeneralSummaryTextArea(committeeMap, committeeSummaries);
    }

    /**
     * Creates the committeee decision summary.
     *
     * @param viewRiksdagenCommittee the view riksdagen committee
     * @param rm the rm
     * @return the text area
     */
    @Override
    public TextArea createCommitteeeDecisionSummary(final ViewRiksdagenCommittee viewRiksdagenCommittee,
                                                  final String rm) {
        validateCommitteeInput(viewRiksdagenCommittee, null, rm);
        final List<ProposalCommitteeeSummary> committeeSummaries = decisionDataFactory.createCommitteeSummary(rm);
        return createSpecificCommitteeSummary(viewRiksdagenCommittee, committeeSummaries);
    }

    /**
     * Validate input.
     *
     * @param committeeMap the committee map
     * @param rm the rm
     */
    private void validateInput(Map<String, List<ViewRiksdagenCommittee>> committeeMap, String rm) {
        if (committeeMap == null || committeeMap.isEmpty()) {
            throw new IllegalArgumentException("Committee map cannot be null or empty");
        }
        validateRm(rm);
    }

    /**
     * Validate committee input.
     *
     * @param committee the committee
     * @param committeeMap the committee map
     * @param rm the rm
     */
    private void validateCommitteeInput(ViewRiksdagenCommittee committee,
                                      Map<String, List<ViewRiksdagenCommittee>> committeeMap,
                                      String rm) {
        if (committee == null || committee.getEmbeddedId() == null) {
            throw new IllegalArgumentException("Committee cannot be null");
        }
        validateRm(rm);
    }

    /**
     * Validate rm.
     *
     * @param rm the rm
     */
    private void validateRm(String rm) {
        if (StringUtils.isBlank(rm)) {
            throw new IllegalArgumentException("RM parameter cannot be blank");
        }
    }

    /**
     * Creates the all decision flow chart.
     *
     * @param committeeMap the committee map
     * @param summaries the summaries
     * @return the sankey chart
     */
    private SankeyChart createAllDecisionFlowChart(Map<String, List<ViewRiksdagenCommittee>> committeeMap,
                                                 List<ProposalCommitteeeSummary> summaries) {
        final SankeyChart chart = initializeChart("Parliamentary Decision Flow");

        final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = summaries.stream()
            .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));

        orgProposalMap.forEach((org, orgSummaries) -> {
            if (committeeMap.containsKey(org) && !committeeMap.get(org).isEmpty()) {
                final ViewRiksdagenCommittee committee = committeeMap.get(org).get(0);
                addDocTypeDataRows(chart, orgSummaries, committee);
                addDecisionDataRows(chart, orgSummaries, committee);
            }
        });
        chart.drawChart();
        return chart;
    }

    /**
     * Creates the committee specific chart.
     *
     * @param committee the committee
     * @param summaries the summaries
     * @return the sankey chart
     */
    private SankeyChart createCommitteeSpecificChart(ViewRiksdagenCommittee committee,
                                                   List<ProposalCommitteeeSummary> summaries) {
        final SankeyChart chart = initializeChart("Committee Decision Flow: " + committee.getEmbeddedId().getDetail());
        final String targetOrg = committee.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH);

        final List<ProposalCommitteeeSummary> committeeSummaries = summaries.stream()
            .filter(summary -> targetOrg.equals(summary.getOrg().toUpperCase(Locale.ENGLISH)))
            .collect(Collectors.toList());

        addDocTypeDecisionFlowData(chart, committeeSummaries);
        chart.drawChart();
        return chart;
    }

    /**
     * Adds the doc type data rows.
     *
     * @param chart the chart
     * @param summaries the summaries
     * @param committee the committee
     */
    private void addDocTypeDataRows(SankeyChart chart,
                                  List<ProposalCommitteeeSummary> summaries,
                                  ViewRiksdagenCommittee committee) {
        final Map<String, Long> docTypeCounts = summaries.stream()
            .collect(Collectors.groupingBy(
                ProposalCommitteeeSummary::getDocType,
                Collectors.counting()
            ));

        final String committeeName = committee.getEmbeddedId().getDetail();
        docTypeCounts.forEach((docType, count) -> {
            if (StringUtils.isNotEmpty(docType)) {
                chart.addDataRow(docType, committeeName, count.intValue());
            }
        });
    }

    /**
     * Adds the decision data rows.
     *
     * @param chart the chart
     * @param summaries the summaries
     * @param committee the committee
     */
    private void addDecisionDataRows(SankeyChart chart,
                                   List<ProposalCommitteeeSummary> summaries,
                                   ViewRiksdagenCommittee committee) {
        final Map<String, Long> decisionCounts = summaries.stream()
            .collect(Collectors.groupingBy(
                ProposalCommitteeeSummary::getDecision,
                Collectors.counting()
            ));

        final String committeeName = committee.getEmbeddedId().getDetail();
        decisionCounts.forEach((decision, count) -> {
            if (StringUtils.isNotEmpty(decision)) {
                chart.addDataRow(committeeName, decision, count.intValue());
            }
        });
    }

    /**
     * Adds the doc type decision flow data.
     *
     * @param chart the chart
     * @param summaries the summaries
     */
    private void addDocTypeDecisionFlowData(SankeyChart chart,
                                          List<ProposalCommitteeeSummary> summaries) {
        summaries.stream()
            .filter(s -> StringUtils.isNotEmpty(s.getDocType()) && StringUtils.isNotEmpty(s.getDecision()))
            .collect(Collectors.groupingBy(
                ProposalCommitteeeSummary::getDocType,
                Collectors.groupingBy(
                    ProposalCommitteeeSummary::getDecision,
                    Collectors.counting()
                )
            ))
            .forEach((docType, decisionMap) ->
                decisionMap.forEach((decision, count) ->
                    chart.addDataRow(docType, decision, count.intValue())
                )
            );
    }

    /**
     * Creates the general summary text area.
     *
     * @param committeeMap the committee map
     * @param summaries the summaries
     * @return the text area
     */
    private TextArea createGeneralSummaryTextArea(Map<String, List<ViewRiksdagenCommittee>> committeeMap,
                                                List<ProposalCommitteeeSummary> summaries) {
        final StringBuilder builder = new StringBuilder();

        committeeMap.forEach((org, committees) -> {
            if (!committees.isEmpty()) {
                final List<ProposalCommitteeeSummary> orgSummaries = summaries.stream()
                    .filter(s -> org.equals(s.getOrg()))
                    .collect(Collectors.toList());

                if (!orgSummaries.isEmpty()) {
                    addCommitteeSummarySection(builder, orgSummaries, committees.get(0));
                }
            }
        });

        return createFormattedTextArea("Summary", builder.toString());
    }

    /**
     * Creates the specific committee summary.
     *
     * @param committee the committee
     * @param summaries the summaries
     * @return the text area
     */
    private TextArea createSpecificCommitteeSummary(ViewRiksdagenCommittee committee,
                                                  List<ProposalCommitteeeSummary> summaries) {
        final StringBuilder builder = new StringBuilder();
        final String targetOrg = committee.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH);

        final List<ProposalCommitteeeSummary> committeeSummaries = summaries.stream()
            .filter(s -> targetOrg.equals(s.getOrg().toUpperCase(Locale.ENGLISH)))
            .collect(Collectors.toList());

        addCommitteeSummarySection(builder, committeeSummaries, committee);
        return createFormattedTextArea("Committee Summary", builder.toString());
    }

    /**
     * Adds the committee summary section.
     *
     * @param builder the builder
     * @param summaries the summaries
     * @param committee the committee
     */
    private void addCommitteeSummarySection(StringBuilder builder,
                                          List<ProposalCommitteeeSummary> summaries,
                                          ViewRiksdagenCommittee committee) {
        if (committee == null || summaries.isEmpty()) {
            return;
        }

        builder.append('\n')
               .append(committee.getEmbeddedId().getDetail());

        final Map<String, List<ProposalCommitteeeSummary>> docTypeMap = summaries.stream()
            .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

        docTypeMap.forEach((docType, docTypeSummaries) ->
            addDocTypeSummary(builder, docType, docTypeSummaries));
    }

    /**
     * Adds the doc type summary.
     *
     * @param builder the builder
     * @param docType the doc type
     * @param summaries the summaries
     */
    private void addDocTypeSummary(StringBuilder builder,
                                 String docType,
                                 List<ProposalCommitteeeSummary> summaries) {
        if (StringUtils.isEmpty(docType)) {
            return;
        }

        builder.append("\n ( ")
               .append(summaries.size())
               .append(' ')
               .append(docType)
               .append(" -> ");

        final Map<String, List<ProposalCommitteeeSummary>> decisionMap = summaries.stream()
            .collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));

        decisionMap.forEach((decision, decisionSummaries) ->
            addDecisionSummary(builder, decision, decisionSummaries));

        builder.append(')');
    }

    /**
     * Adds the decision summary.
     *
     * @param builder the builder
     * @param decision the decision
     * @param summaries the summaries
     */
    private void addDecisionSummary(StringBuilder builder,
                                  String decision,
                                  List<ProposalCommitteeeSummary> summaries) {
        if (StringUtils.isEmpty(decision)) {
            return;
        }

        builder.append("\n   ")
               .append(summaries.size())
               .append(' ')
               .append(decision);

        summaries.forEach(summary ->
            builder.append("\n    ")
                   .append(decision)
                   .append(':')
                   .append(summary.getWording())
                   .append(' ')
                   .append(summary.getWording2()));
    }

    /**
     * Initialize chart.
     *
     * @param title the title
     * @return the sankey chart
     */
    private SankeyChart initializeChart(String title) {
        final SankeyChart chart = new SankeyChart();
        chart.setCaption(title);
        chart.setWidth("100%");
        return chart;
    }

    /**
     * Creates the formatted text area.
     *
     * @param caption the caption
     * @param content the content
     * @return the text area
     */
    private TextArea createFormattedTextArea(String caption, String content) {
        final TextArea area = new TextArea(caption);
        area.setValue(content);
        area.setWidth("100%");
        area.setReadOnly(true);
        return area;
    }
}
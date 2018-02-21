package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.DecisionFlowChartManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.DecisionDataFactory;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.ProposalCommitteeeSummary;
import com.hack23.cia.web.widgets.charts.SankeyChart;

@Service
public class DecisionFlowChartManagerImpl implements DecisionFlowChartManager {
	
	/** The decision data factory. */
	@Autowired
	private DecisionDataFactory decisionDataFactory;

	
	@Override
	public SankeyChart createAllDecisionFlow(final Map<String, List<ViewRiksdagenCommittee>> committeeMap) {
		final List<ProposalCommitteeeSummary> createCommitteeSummary = decisionDataFactory.createCommitteeSummary("20");
				
		final SankeyChart chart = new SankeyChart();
		
		final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = createCommitteeSummary.stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));
		
		for (final Entry<String, List<ProposalCommitteeeSummary>> entry : orgProposalMap.entrySet()) {
			
			if(committeeMap.containsKey(entry.getKey())) {
				
				final ViewRiksdagenCommittee vewRiksdagenCommittee = committeeMap.get(entry.getKey()).iterator().next();
			
				final Map<String, List<ProposalCommitteeeSummary>> docTypeMap = entry.getValue().stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));
	
				for (final Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
					if (docEntry.getKey().trim().length()> 0 && entry.getKey().trim().length() >0) {
						chart.addDataRow(docEntry.getKey(),vewRiksdagenCommittee.getEmbeddedId().getDetail(),docEntry.getValue().size());
	
					}
				}
							
				final Map<String, List<ProposalCommitteeeSummary>> decisionMap = entry.getValue().stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));
				
				for (final Entry<String, List<ProposalCommitteeeSummary>> decisionEntry : decisionMap.entrySet()) {
					if (decisionEntry.getKey().trim().length()> 0 && entry.getKey().trim().length() >0) {
						chart.addDataRow(vewRiksdagenCommittee.getEmbeddedId().getDetail(),decisionEntry.getKey(),decisionEntry.getValue().size());
					}
				}
			}
		}
				
        chart.drawChart();
		return chart;
	}

	@Override
	public SankeyChart createCommitteeDecisionFlow(final ViewRiksdagenCommittee viewRiksdagenCommittee,
			final Map<String, List<ViewRiksdagenCommittee>> committeeMap) {
		final List<ProposalCommitteeeSummary> createCommitteeSummary = decisionDataFactory.createCommitteeSummary("20");		
		
		final SankeyChart chart = new SankeyChart();
		
		final Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = createCommitteeSummary.stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));
		
		
		for (final Entry<String, List<ProposalCommitteeeSummary>> entry : orgProposalMap.entrySet()) {
							
			if(committeeMap.containsKey(entry.getKey()) && viewRiksdagenCommittee.getEmbeddedId().getOrgCode().equals(entry.getKey())) {
								
				final Map<String, List<ProposalCommitteeeSummary>> docTypeMap = entry.getValue().stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

				for (final Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
					
					final Map<String, List<ProposalCommitteeeSummary>> decisionMap = docEntry.getValue().stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));
					
					for (final Entry<String, List<ProposalCommitteeeSummary>> decisionEntry : decisionMap.entrySet()) {
						if (decisionEntry.getKey().trim().length()> 0 && entry.getKey().trim().length() >0) {
							chart.addDataRow(docEntry.getKey(),decisionEntry.getKey(),decisionEntry.getValue().size());
						}
					}
				}
			}
		}
			
      chart.drawChart();
		return chart;
	}


}

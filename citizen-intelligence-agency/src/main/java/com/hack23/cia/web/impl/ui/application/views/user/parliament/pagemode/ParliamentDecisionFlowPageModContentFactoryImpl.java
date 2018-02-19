/*
 * Copyright 2014 James Pether Sörling
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentProposalData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.widgets.charts.SankeyChart;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ParliamentDecisionFlowPageModContentFactoryImpl.
 */
@Component
public final class ParliamentDecisionFlowPageModContentFactoryImpl extends AbstractParliamentPageModContentFactoryImpl {


	/** The Constant PARLIAMENT_DECISION_FLOW. */
	private static final String PARLIAMENT_DECISION_FLOW = "Parliament decision flow";

	
	/**
	 * Instantiates a new parliament decision flow page mod content factory impl.
	 */
	public ParliamentDecisionFlowPageModContentFactoryImpl() {
		super();
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && !StringUtils.isEmpty(parameters) && parameters.contains(PageMode.CHARTS.toString())
				&& parameters.contains(ChartIndicators.DECISION_FLOW_CHART.toString());
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		getParliamentMenuItemFactory().createParliamentTopicMenu(menuBar);

		final String pageId = getPageId(parameters);

		
		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenCommittee.class);
		List<ViewRiksdagenCommittee> allCommittess = dataContainer.getAll();

		Map<String, List<ViewRiksdagenCommittee>> committeeMap = allCommittess.stream().collect(Collectors.groupingBy(c -> c.getEmbeddedId().getOrgCode().toUpperCase(Locale.ENGLISH)));
		
		
		List<ProposalCommitteeeSummary> createCommitteeSummary = createCommitteeSummary("20");
		
		
		SankeyChart chart = new SankeyChart();
		
		Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = createCommitteeSummary.stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));
		
		for (Entry<String, List<ProposalCommitteeeSummary>> entry : orgProposalMap.entrySet()) {
			
			if(committeeMap.containsKey(entry.getKey())) {
				
				ViewRiksdagenCommittee vewRiksdagenCommittee = committeeMap.get(entry.getKey()).iterator().next();
			
				Map<String, List<ProposalCommitteeeSummary>> docTypeMap = entry.getValue().stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));
	
				for (Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
					if (docEntry.getKey().trim().length()> 0 && entry.getKey().trim().length() >0) {
						chart.addDataRow(docEntry.getKey(),vewRiksdagenCommittee.getEmbeddedId().getDetail(),docEntry.getValue().size());
	
					}
				}
							
				Map<String, List<ProposalCommitteeeSummary>> decisionMap = entry.getValue().stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));
				
				for (Entry<String, List<ProposalCommitteeeSummary>> decisionEntry : decisionMap.entrySet()) {
					if (decisionEntry.getKey().trim().length()> 0 && entry.getKey().trim().length() >0) {
						chart.addDataRow(vewRiksdagenCommittee.getEmbeddedId().getDetail(),decisionEntry.getKey(),decisionEntry.getValue().size());
					}
				}
			}
		}
				
        chart.drawChart();

        panelContent.addComponent(chart);		

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARLIAMENT_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);
		panel.setCaption(new StringBuilder().append(NAME).append("::").append(PARLIAMENT_DECISION_FLOW).toString());

		return panelContent;

	}

	private List<ProposalCommitteeeSummary> createCommitteeSummary(final String processedIn) {
		List<ProposalCommitteeeSummary> summary = new ArrayList<>();

		final DataContainer<DocumentStatusContainer, Long> dataContainer = getApplicationManager()
				.getDataContainer(DocumentStatusContainer.class);

		for (final DocumentStatusContainer document : dataContainer.getAll()) {

			if (document.getDocumentProposal() != null && document.getDocumentProposal().getProposal() != null) {

				DocumentProposalData proposal = document.getDocumentProposal().getProposal();

				if (proposal.getProcessedIn() != null && !proposal.getProcessedIn().trim().isEmpty()
						&& proposal.getCommittee() != null && !proposal.getCommittee().trim().isEmpty()
						&& proposal.getProcessedIn().contains(processedIn) && !(proposal.getChamber().length() > "återförvisning till utskottet".length()) && proposal.getChamber().length() >= "avslag".length()) {
					
					summary.add(new ProposalCommitteeeSummary(getCommittteeShortName(proposal), getDocumentName(document) , cleanupDecision(proposal.getChamber()) , document.getDocument().getHangarId()));

				}
			}
		}
		return summary;
	}

	private String cleanupDecision(String chamber) {
		return chamber.toUpperCase(Locale.ENGLISH).replace("(UTSKOTTET)", "").replace("UTKOTTET", "UTSKOTTET").replace("UTBSKOTTET", "UTSKOTTET").replace("UBTSKOTTET", "UTSKOTTET").trim().replace("(", "").replace(")","").trim();
	}

	private String getDocumentName(final DocumentStatusContainer document) {
		if ("prop".equalsIgnoreCase(document.getDocument().getDocumentType())) {
			return "Proposition";
		} else if (document.getDocument().getSubType() != null & document.getDocument().getSubType().trim().length() > "motion".length()) {
			return document.getDocument().getSubType();
		} else {
			return "Motion";
		}
		
	}

	private String getCommittteeShortName(DocumentProposalData proposal) {
		String upperCase = proposal.getProcessedIn().replaceAll("\\d","").replace("/:","").trim().toUpperCase(Locale.ENGLISH);
				
		if (upperCase.contains(",")) {
			return upperCase.substring(0, upperCase.indexOf(","));
		} else {
			return upperCase;
		}
	}
	
	/**
	 * The Class ProposalCommitteeeSummary.
	 */
	public static class ProposalCommitteeeSummary {
		private final String org;
		private final String docType;
		private final String decision;
		private final String hangarId;
		
		public ProposalCommitteeeSummary(String org, String docType, String decision, String hangarId) {
			super();
			this.org = org.trim().toUpperCase(Locale.ENGLISH);
			this.docType = docType.toUpperCase(Locale.ENGLISH);
			this.decision = decision.replace("=", "").replaceAll(",", "").trim().toUpperCase(Locale.ENGLISH);
			this.hangarId = hangarId;
		}

		public String getOrg() {
			return org;
		}

		public String getDocType() {
			return docType;
		}

		public String getDecision() {
			return decision;
		}

		public String getHangarId() {
			return hangarId;
		}

		@Override
		public String toString() {
			return String.format("ProposalCommitteeeSummary [org=%s, docType=%s, decision=%s, hangarId=%s]", org,
					docType, decision, hangarId);
		}
		
		
	}

}

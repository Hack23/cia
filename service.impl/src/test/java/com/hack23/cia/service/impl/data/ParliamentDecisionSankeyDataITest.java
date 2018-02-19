/*
 * Copyright 2010 James Pether Sörling
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
package com.hack23.cia.service.impl.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentProposalData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class ParliamentDecisionSankeyDataITest.
 */
public final class ParliamentDecisionSankeyDataITest extends AbstractServiceFunctionalIntegrationTest {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Gets the rm org doc list map test.
	 *
	 * @return the rm org doc list map test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getRmOrgDocListMapTest() throws Exception {
		setAuthenticatedAnonymousUser();

		Map<String, Map<String, List<String>>> rmOrgDocListMap = getRmOrgDocListMap();
		assertNotNull(rmOrgDocListMap);

		for (Entry<String, Map<String, List<String>>> rmEntrySet : rmOrgDocListMap.entrySet()) {
			assertNotNull(rmEntrySet.getValue());
			for (Entry<String, List<String>> orgEntry : rmEntrySet.getValue().entrySet()) {
				assertNotNull(rmEntrySet.getKey());
				assertNotNull(orgEntry.getKey());
				assertNotNull(orgEntry.getValue().size());
			}
		}
	}

	/**
	 * Display rm summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void displayRmSummaryTest() throws Exception {
		setAuthenticatedAnonymousUser();

		Map<String, Map<String, List<String>>> rmOrgDocListMap = getRmOrgDocListMap();
		assertNotNull(rmOrgDocListMap);

		for (Entry<String, Map<String, List<String>>> rmEntrySet : rmOrgDocListMap.entrySet()) {
			assertNotNull(rmEntrySet.getValue());
			List<ProposalCommitteeeSummary> createCommitteeSummary = createCommitteeSummary(rmEntrySet.getKey());
			System.out.println("rm:" + rmEntrySet.getKey() +", decisions:"+ createCommitteeSummary.size());
		}
	}

	/**
	 * Display rm org summary test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void displayRmOrgSummaryTest() throws Exception {
		setAuthenticatedAnonymousUser();

		Map<String, Map<String, List<String>>> rmOrgDocListMap = getRmOrgDocListMap();
		assertNotNull(rmOrgDocListMap);

		for (Entry<String, Map<String, List<String>>> rmEntrySet : rmOrgDocListMap.entrySet()) {
			assertNotNull(rmEntrySet.getValue());
			
			
			for (Entry<String, List<String>> orgEntry : rmEntrySet.getValue().entrySet()) {
				assertNotNull(rmEntrySet.getKey());
				assertNotNull(orgEntry.getKey());
				assertNotNull(orgEntry.getValue().size());
				List<ProposalCommitteeeSummary> createCommitteeSummary = createCommitteeSummary(rmEntrySet.getKey()+":" + orgEntry.getKey());
				System.out.println("rm:" + rmEntrySet.getKey() +" org:" + orgEntry.getKey()  +", decisions:"+ createCommitteeSummary.size());
			}
			return;
		}
	}

	
	/**
	 * Creates the document proposal summary one report test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void createDocumentProposalSummaryOneReportTest() throws Exception {
		setAuthenticatedAnonymousUser();

		List<ProposalCommitteeeSummary> createCommitteeSummary = createCommitteeSummary("2013/14:KrU10");
		assertNotNull(createCommitteeSummary);
		assertFalse(createCommitteeSummary.isEmpty());		
				
	}

	/**
	 * Creates the document proposal summary one rm test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void createDocumentProposalSummaryOneRmTest() throws Exception {
		setAuthenticatedAnonymousUser();

		List<ProposalCommitteeeSummary> createCommitteeSummary = createCommitteeSummary("2013/14");
		assertNotNull(createCommitteeSummary);
		assertFalse(createCommitteeSummary.isEmpty());		
		
		
		Map<String, List<ProposalCommitteeeSummary>> orgProposalMap = createCommitteeSummary.stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getOrg));
		
		for (Entry<String, List<ProposalCommitteeeSummary>> entry : orgProposalMap.entrySet()) {
			
			Map<String, List<ProposalCommitteeeSummary>> docTypeMap = entry.getValue().stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDocType));

			for (Entry<String, List<ProposalCommitteeeSummary>> docEntry : docTypeMap.entrySet()) {
				if (docEntry.getKey().trim().length()> 0 && entry.getKey().trim().length() >0) {
					System.out.println(docEntry.getKey() + "->" + entry.getKey() +":"+ docEntry.getValue().size());
				}
			}
						
			Map<String, List<ProposalCommitteeeSummary>> decisionMap = entry.getValue().stream().collect(Collectors.groupingBy(ProposalCommitteeeSummary::getDecision));
			
			for (Entry<String, List<ProposalCommitteeeSummary>> decisionEntry : decisionMap.entrySet()) {
				if (decisionEntry.getKey().trim().length()> 0 && entry.getKey().trim().length() >0) {
					System.out.println(entry.getKey() + "->" + decisionEntry.getKey() +":"+ decisionEntry.getValue().size());
				}
			}

		}
		
	}

	/**
	 * Gets the rm org doc list map.
	 *
	 * @return the rm org doc list map
	 */
	private Map<String, Map<String, List<String>>> getRmOrgDocListMap() {
		Map<String, Map<String, List<String>>> rmOrgDocsMap = new HashMap<>();

		final DataContainer<DocumentElement, String> dataContainer = applicationManager
				.getDataContainer(DocumentElement.class);

		for (final DocumentElement data : dataContainer.getAllBy(DocumentElement_.documentType, "utskottsmöte")) {
			if (data.getRm().length() > 5 && data.getDocumentType().toLowerCase(Locale.ENGLISH).contains("utskottsmöte")
					&& !"planerat".equalsIgnoreCase(data.getStatus())) {

				Map<String, List<String>> orgDocsMap = rmOrgDocsMap.computeIfAbsent(data.getRm(),
						map -> new HashMap<String, List<String>>());

				List<String> docList = orgDocsMap.computeIfAbsent(data.getOrg(), l -> new ArrayList<>());
				docList.add(data.getOrg() + data.getLabel());
			}
		}

		return rmOrgDocsMap;
	}

	/**
	 * Creates the committee summary.
	 *
	 * @param processedIn
	 *            the processed in
	 * @return the list
	 */
	private List<ProposalCommitteeeSummary> createCommitteeSummary(final String processedIn) {
		List<ProposalCommitteeeSummary> summary = new ArrayList<>();

		final DataContainer<DocumentStatusContainer, Long> dataContainer = applicationManager
				.getDataContainer(DocumentStatusContainer.class);

		for (final DocumentStatusContainer document : dataContainer.getAll()) {

			if (document.getDocumentProposal() != null && document.getDocumentProposal().getProposal() != null) {

				DocumentProposalData proposal = document.getDocumentProposal().getProposal();

				if (proposal.getProcessedIn() != null && !proposal.getProcessedIn().trim().isEmpty()
						&& proposal.getCommittee() != null && !proposal.getCommittee().trim().isEmpty()
						&& proposal.getProcessedIn().contains(processedIn)) {
					
					summary.add(new ProposalCommitteeeSummary(document.getDocument().getOrg(), document.getDocument().getDocumentType()
							+ "." + document.getDocument().getSubType() , proposal.getChamber() , document.getDocument().getHangarId()));

				}
			}
		}
		return summary;
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
			this.org = org;
			this.docType = docType;
			this.decision = decision;
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

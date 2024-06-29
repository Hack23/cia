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
package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentProposalData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.DecisionDataFactory;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.ProposalCommitteeeSummary;

/**
 * The Class DecisionDataFactoryImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public final class DecisionDataFactoryImpl implements DecisionDataFactory {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new decision data factory impl.
	 */
	public DecisionDataFactoryImpl() {
		super();
	}

	/**
	 * Adds the proposal committeee summary.
	 *
	 * @param processedIn the processed in
	 * @param summary     the summary
	 * @param document    the document
	 */
	private static void addProposalCommitteeeSummary(final String processedIn,
			final List<ProposalCommitteeeSummary> summary, final DocumentStatusContainer document) {
		if (document.getDocumentProposal() != null && document.getDocumentProposal().getProposal() != null) {

			final DocumentProposalData proposal = document.getDocumentProposal().getProposal();

			if (proposal.getProcessedIn() != null && !proposal.getProcessedIn().isEmpty()
					&& proposal.getCommittee() != null && !proposal.getCommittee().isEmpty()
					&& proposal.getProcessedIn().contains(processedIn)
					&& proposal.getChamber().length() <= "återförvisning till utskottet".length()
					&& proposal.getChamber().length() >= "avslag".length()) {

				summary.add(new ProposalCommitteeeSummary(getCommittteeShortName(proposal), getDocumentName(document),
						cleanupDecision(proposal.getChamber()), document.getDocument().getHangarId(),
						proposal.getWording(), proposal.getWording2(), proposal.getDecisionType()));
			}
		}
	}

	/**
	 * Cleanup decision.
	 *
	 * @param chamber the chamber
	 * @return the string
	 */
	private static String cleanupDecision(final String chamber) {
		return chamber.toUpperCase(Locale.ENGLISH).replace("(UTSKOTTET)", "").replace("UTKOTTET", "UTSKOTTET")
				.replace("UTBSKOTTET", "UTSKOTTET").replace("UBTSKOTTET", "UTSKOTTET").replace("(", "")
				.replace(")", "");
	}

	/**
	 * Gets the committtee short name.
	 *
	 * @param proposal the proposal
	 * @return the committtee short name
	 */
	private static String getCommittteeShortName(final DocumentProposalData proposal) {
		final String upperCase = proposal.getProcessedIn().replaceAll("\\d", "").replace("/:", "")
				.toUpperCase(Locale.ENGLISH);

		if (upperCase.contains(",")) {
			return upperCase.substring(0, upperCase.indexOf(','));
		} else {
			return upperCase;
		}
	}

	/**
	 * Gets the document name.
	 *
	 * @param document the document
	 * @return the document name
	 */
	private static String getDocumentName(final DocumentStatusContainer document) {
		if ("prop".equalsIgnoreCase(document.getDocument().getDocumentType())) {
			return "Proposition";
		} else if (document.getDocument().getSubType() != null
				&& document.getDocument().getSubType().length() > "motion".length()) {
			return document.getDocument().getSubType();
		} else {
			return "Motion";
		}

	}

	@Override
	public List<ProposalCommitteeeSummary> createCommitteeSummary(final String processedIn) {
		final List<ProposalCommitteeeSummary> summary = new ArrayList<>();

		final DataContainer<DocumentStatusContainer, Long> dataContainer = applicationManager
				.getDataContainer(DocumentStatusContainer.class);

		for (final DocumentStatusContainer document : dataContainer.getAll()) {

			addProposalCommitteeeSummary(processedIn, summary, document);
		}
		return summary;
	}

}

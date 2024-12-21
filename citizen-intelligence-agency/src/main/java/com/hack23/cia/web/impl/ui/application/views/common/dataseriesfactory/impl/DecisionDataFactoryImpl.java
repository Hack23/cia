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
import java.util.regex.Pattern;

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

    /** Common text constants for doc type. */
    private static final String PROP = "Proposition";

    /** The Constant MOTION. */
    private static final String MOTION = "Motion";

    /** Known min and max length constraints from original code. */
    private static final int CHAMBER_MIN_LENGTH = "avslag".length(); // 6

    /** The Constant CHAMBER_MAX_LENGTH. */
    private static final int CHAMBER_MAX_LENGTH = "återförvisning till utskottet".length(); // 29

    /**
     * Pattern for cleaning up the 'chamber' string. Aggregates multiple .replace() calls
     * into a single regex. This pattern will remove or replace these tokens:
     *  (UTSKOTTET), ( or ), UTBSKOTTET, UBTSKOTTET, UTKOTTET
     *
     * We'll do it case-insensitively, but remember we do an uppercase pass anyway.
     */
    private static final Pattern CLEANUP_PATTERN = Pattern.compile(
        "(\\(UTSKOTTET\\))|(\\()|(\\))|(UTBSKOTTET)|(UBTSKOTTET)|(UTKOTTET)",
        Pattern.CASE_INSENSITIVE
    );

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
     * Creates the committee summary.
     *
     * @param processedIn the processed in
     * @return the list
     */
    @Override
    public List<ProposalCommitteeeSummary> createCommitteeSummary(final String processedIn) {
        final List<ProposalCommitteeeSummary> summary = new ArrayList<>();

        // NOTE: Still loads ALL DocumentStatusContainer rows
        final DataContainer<DocumentStatusContainer, Long> dataContainer =
                applicationManager.getDataContainer(DocumentStatusContainer.class);

        // For each doc, potentially add a summary
        for (final DocumentStatusContainer document : dataContainer.getAll()) {
            addProposalCommitteeeSummary(processedIn, summary, document);
        }
        return summary;
    }

    /**
     * Check and build a summary if the document qualifies based on 'processedIn' and
     * the proposal/chamber constraints.
     *
     * @param processedIn the processed in
     * @param summary the summary
     * @param document the document
     */
    private static void addProposalCommitteeeSummary(final String processedIn,
            final List<ProposalCommitteeeSummary> summary, final DocumentStatusContainer document) {

        // Null checks on 'documentProposal' and 'proposal'
        if (document.getDocumentProposal() == null ||
            document.getDocumentProposal().getProposal() == null) {
            return;
        }

        final DocumentProposalData proposal = document.getDocumentProposal().getProposal();
        final String chamber = proposal.getChamber();
        // Are we processed in the correct place?
        if ((chamber == null) || proposal.getProcessedIn() == null || proposal.getProcessedIn().isEmpty()) {
            return;
        }
        // Do we have a non-empty 'committee'?
        if (proposal.getCommittee() == null || proposal.getCommittee().isEmpty()) {
            return;
        }
        // Does 'processedIn' match?
        if (!proposal.getProcessedIn().contains(processedIn)) {
            return;
        }
        // Is the chamber length within the specified min/max?
        final int length = chamber.length();
        if (length < CHAMBER_MIN_LENGTH || length > CHAMBER_MAX_LENGTH) {
            return;
        }

        // If all checks pass, add the summary
        summary.add(new ProposalCommitteeeSummary(
            getCommitteeShortName(proposal),
            getDocumentName(document),
            cleanupDecision(chamber),
            document.getDocument().getHangarId(),
            proposal.getWording(),
            proposal.getWording2(),
            proposal.getDecisionType()
        ));
    }

    /**
     * Cleanup the 'chamber' string by uppercasing, removing tokens via regex,
     * and normalizing "UTKOTTET" to "UTSKOTTET" if it appears alone.
     *
     * @param chamber the chamber
     * @return the string
     */
    private static String cleanupDecision(final String chamber) {
        // Convert to uppercase English
        String upper = chamber.toUpperCase(Locale.ENGLISH);

        // Remove or replace known substrings with the pattern
        upper = CLEANUP_PATTERN.matcher(upper).replaceAll("");

        // If we find "UTKOTTET" alone, replace it with "UTSKOTTET"
        // (We already removed parentheses above).
        if (upper.contains("UTKOTTET")) {
            upper = upper.replace("UTKOTTET", "UTSKOTTET");
        }
        return upper.trim();
    }

    /**
     * Extract the committee short name from 'processedIn' by removing digits
     * and certain punctuation, converting to uppercase, and truncating if comma found.
     *
     * @param proposal the proposal
     * @return the committee short name
     */
    private static String getCommitteeShortName(final DocumentProposalData proposal) {
        // e.g. "UU12" => "UU" then uppercase => "UU"
        final String upperCase = proposal.getProcessedIn()
                                   .replaceAll("\\d", "")
                                   .replace("/:", "")
                                   .toUpperCase(Locale.ENGLISH);
        // If there's a comma, only take up to that comma
        final int commaIndex = upperCase.indexOf(',');
        return (commaIndex >= 0) ? upperCase.substring(0, commaIndex) : upperCase;
    }

    /**
     * Return a human-readable doc name: "Proposition" if docType="prop",
     * or doc.getSubType() if subType is long enough, else "Motion".
     *
     * @param document the document
     * @return the document name
     */
    private static String getDocumentName(final DocumentStatusContainer document) {
        if ("prop".equalsIgnoreCase(document.getDocument().getDocumentType())) {
            return PROP;
        } else if (document.getDocument().getSubType() != null
                   && document.getDocument().getSubType().length() > MOTION.length()) {
            return document.getDocument().getSubType();
        } else {
            return MOTION;
        }
    }
}

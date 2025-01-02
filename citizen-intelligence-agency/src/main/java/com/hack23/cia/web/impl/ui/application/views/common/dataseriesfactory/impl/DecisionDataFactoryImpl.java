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
package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.impl;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentProposalData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.DecisionDataFactory;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.ProposalCommitteeeSummary;

/**
 * Implementation of DecisionDataFactory for processing parliamentary committee decisions.
 * Handles document status data and creates summaries of committee proposals.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public final class DecisionDataFactoryImpl implements DecisionDataFactory {

    /**  Document type constants. */
    private static final String PROPOSITION = "Proposition";

    /** The Constant MOTION. */
    private static final String MOTION = "Motion";

    /** The Constant PROP_TYPE. */
    private static final String PROP_TYPE = "prop";

    /**  Chamber text length constraints for valid proposals. */
    private static final int CHAMBER_MIN_LENGTH = "avslag".length(); // 6

    /** The Constant CHAMBER_MAX_LENGTH. */
    private static final int CHAMBER_MAX_LENGTH = "återförvisning till utskottet".length(); // 29

    /**
     * Pattern for standardizing committee decision text.
     * Removes or replaces parliamentary specific tokens:
     * - (UTSKOTTET)
     * - Parentheses
     * - Various committee spelling variants
     */
    private static final Pattern COMMITTEE_TEXT_CLEANUP_PATTERN = Pattern.compile(
        "(\\(UTSKOTTET\\))|(\\()|(\\))|(UTBSKOTTET)|(UBTSKOTTET)|(UTKOTTET)",
        Pattern.CASE_INSENSITIVE
    );

    /** The application manager. */
    @Autowired
    private ApplicationManager applicationManager;

    /**
     * Creates committee summaries for a specific processing location.
     *
     * @param processedIn the location where proposals were processed
     * @return List of committee summaries
     * @throws IllegalArgumentException if processedIn is blank
     */
    @Override
    public List<ProposalCommitteeeSummary> createCommitteeSummary(final String processedIn) {
        validateProcessedIn(processedIn);

        return applicationManager.getDataContainer(DocumentStatusContainer.class)
                .getAll()
                .parallelStream()
                .filter(doc -> isValidDocument(doc, processedIn))
                .map(doc -> createProposalSummary(doc))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Validates the processedIn parameter.
     *
     * @param processedIn location to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateProcessedIn(final String processedIn) {
        if (StringUtils.isBlank(processedIn)) {
            throw new IllegalArgumentException("ProcessedIn parameter cannot be blank");
        }
    }

    /**
     * Validates if a document meets the criteria for processing.
     *
     * @param document the document to validate
     * @param processedIn the required processing location
     * @return true if document is valid for processing
     */
    private boolean isValidDocument(final DocumentStatusContainer document, final String processedIn) {
        if (document == null || document.getDocumentProposal() == null ||
            document.getDocumentProposal().getProposal() == null) {
            return false;
        }

        final DocumentProposalData proposal = document.getDocumentProposal().getProposal();
        return isValidProposal(proposal, processedIn);
    }

    /**
     * Validates proposal data for processing requirements.
     *
     * @param proposal the proposal to validate
     * @param processedIn the required processing location
     * @return true if proposal meets requirements
     */
    private boolean isValidProposal(final DocumentProposalData proposal, final String processedIn) {
        if (proposal == null || StringUtils.isBlank(proposal.getChamber()) ||
            StringUtils.isBlank(proposal.getProcessedIn()) ||
            StringUtils.isBlank(proposal.getCommittee())) {
            return false;
        }

        final int chamberLength = proposal.getChamber().length();
        return chamberLength >= CHAMBER_MIN_LENGTH &&
               chamberLength <= CHAMBER_MAX_LENGTH &&
               proposal.getProcessedIn().contains(processedIn);
    }

    /**
     * Creates a ProposalCommitteeeSummary from a valid document.
     *
     * @param document source document
     * @return ProposalCommitteeeSummary or null if invalid
     */
    private ProposalCommitteeeSummary createProposalSummary(final DocumentStatusContainer document) {
        try {
            final DocumentProposalData proposal = document.getDocumentProposal().getProposal();
            return new ProposalCommitteeeSummary(
                extractCommitteeShortName(proposal),
                determineDocumentType(document),
                standardizeDecisionText(proposal.getChamber()),
                document.getDocument().getHangarId(),
                proposal.getWording(),
                proposal.getWording2(),
                proposal.getDecisionType()
            );
        } catch (final Exception e) {
            // Log error if needed
            return null;
        }
    }

    /**
     * Standardizes the decision text by removing variations and normalizing format.
     *
     * @param chamber the original chamber text
     * @return standardized decision text
     */
    private static String standardizeDecisionText(final String chamber) {
        if (StringUtils.isBlank(chamber)) {
            return "";
        }

        String standardized = chamber.toUpperCase(Locale.ENGLISH);
        standardized = COMMITTEE_TEXT_CLEANUP_PATTERN.matcher(standardized).replaceAll("");

        // Normalize committee spelling
        if (standardized.contains("UTKOTTET")) {
            standardized = standardized.replace("UTKOTTET", "UTSKOTTET");
        }

        return standardized.trim();
    }

    /**
     * Extracts the standardized committee short name.
     *
     * @param proposal source proposal
     * @return committee short name
     */
    private static String extractCommitteeShortName(final DocumentProposalData proposal) {
        final String processedIn = proposal.getProcessedIn();
        if (StringUtils.isBlank(processedIn)) {
            return "";
        }

        final String shortName = processedIn
            .replaceAll("\\d", "")
            .replace("/:", "")
            .toUpperCase(Locale.ENGLISH);

        final int commaIndex = shortName.indexOf(',');
        return (commaIndex >= 0) ? shortName.substring(0, commaIndex) : shortName;
    }

    /**
     * Determines the human-readable document type.
     *
     * @param document source document
     * @return document type string
     */
    private static String determineDocumentType(final DocumentStatusContainer document) {
        if (PROP_TYPE.equalsIgnoreCase(document.getDocument().getDocumentType())) {
            return PROPOSITION;
        }

        final String subType = document.getDocument().getSubType();
        return (subType != null && subType.length() > MOTION.length())
               ? subType
               : MOTION;
    }
}
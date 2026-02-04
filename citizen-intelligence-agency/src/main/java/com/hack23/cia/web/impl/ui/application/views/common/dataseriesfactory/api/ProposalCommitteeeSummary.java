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
package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api;

import java.util.Locale;

/**
 * The Class ProposalCommitteeeSummary.
 * 
 * @param org the organization identifier
 * @param docType the document type
 * @param decision the decision value
 * @param hangarId the hangar identifier
 * @param wording the primary wording text
 * @param wording2 the secondary wording text
 * @param decisionType the type of decision
 */
public record ProposalCommitteeeSummary(
        String org,
        String docType,
        String decision,
        String hangarId,
        String wording,
        String wording2,
        String decisionType
) {
    /**
     * Compact constructor for ProposalCommitteeeSummary.
     * Validates and normalizes the input data.
     */
    public ProposalCommitteeeSummary {
        org = org.trim().toUpperCase(Locale.ENGLISH);
        docType = docType.toUpperCase(Locale.ENGLISH);
        decision = decision.replace("=", "").replace(",", "").trim().toUpperCase(Locale.ENGLISH);
    }

}
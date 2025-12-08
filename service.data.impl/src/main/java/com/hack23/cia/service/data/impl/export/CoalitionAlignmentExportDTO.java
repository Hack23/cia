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
package com.hack23.cia.service.data.impl.export;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for coalition alignment JSON export.
 * 
 * @author intelligence-operative
 * @since v1.36
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoalitionAlignmentExportDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("metadata")
	private ExportMetadata metadata;

	@JsonProperty("alignments")
	private List<PartyAlignment> alignments;

	public CoalitionAlignmentExportDTO() {
		this.alignments = new ArrayList<>();
	}

	public ExportMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(final ExportMetadata metadata) {
		this.metadata = metadata;
	}

	public List<PartyAlignment> getAlignments() {
		return alignments;
	}

	public void setAlignments(final List<PartyAlignment> alignments) {
		this.alignments = alignments;
	}

	/**
	 * Party alignment entry.
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class PartyAlignment implements Serializable {
		private static final long serialVersionUID = 1L;

		@JsonProperty("party1")
		private String party1;

		@JsonProperty("party2")
		private String party2;

		@JsonProperty("alignmentRate")
		private Double alignmentRate;

		@JsonProperty("sharedVotes")
		private Long sharedVotes;

		@JsonProperty("alignedVotes")
		private Long alignedVotes;

		// Getters and setters
		public String getParty1() {
			return party1;
		}

		public void setParty1(final String party1) {
			this.party1 = party1;
		}

		public String getParty2() {
			return party2;
		}

		public void setParty2(final String party2) {
			this.party2 = party2;
		}

		public Double getAlignmentRate() {
			return alignmentRate;
		}

		public void setAlignmentRate(final Double alignmentRate) {
			this.alignmentRate = alignmentRate;
		}

		public Long getSharedVotes() {
			return sharedVotes;
		}

		public void setSharedVotes(final Long sharedVotes) {
			this.sharedVotes = sharedVotes;
		}

		public Long getAlignedVotes() {
			return alignedVotes;
		}

		public void setAlignedVotes(final Long alignedVotes) {
			this.alignedVotes = alignedVotes;
		}
	}
}

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for risk assessment JSON export.
 * 
 * @author intelligence-operative
 * @since v1.36
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiskAssessmentExportDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("metadata")
	private ExportMetadata metadata;

	@JsonProperty("violations")
	private List<RiskViolation> violations;

	public RiskAssessmentExportDTO() {
		this.violations = new ArrayList<>();
	}

	public ExportMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(final ExportMetadata metadata) {
		this.metadata = metadata;
	}

	public List<RiskViolation> getViolations() {
		return violations;
	}

	public void setViolations(final List<RiskViolation> violations) {
		this.violations = violations;
	}

	/**
	 * Risk violation entry.
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class RiskViolation implements Serializable {
		private static final long serialVersionUID = 1L;

		@JsonProperty("id")
		private Long id;

		@JsonProperty("detectedDate")
		private Date detectedDate;

		@JsonProperty("referenceId")
		private String referenceId;

		@JsonProperty("name")
		private String name;

		@JsonProperty("resourceType")
		private String resourceType;

		@JsonProperty("ruleName")
		private String ruleName;

		@JsonProperty("ruleDescription")
		private String ruleDescription;

		@JsonProperty("ruleGroup")
		private String ruleGroup;

		@JsonProperty("status")
		private String status;

		@JsonProperty("positive")
		private String positive;

		// Getters and setters
		public Long getId() {
			return id;
		}

		public void setId(final Long id) {
			this.id = id;
		}

		public Date getDetectedDate() {
			return detectedDate != null ? new Date(detectedDate.getTime()) : null;
		}

		public void setDetectedDate(final Date detectedDate) {
			this.detectedDate = detectedDate != null ? new Date(detectedDate.getTime()) : null;
		}

		public String getReferenceId() {
			return referenceId;
		}

		public void setReferenceId(final String referenceId) {
			this.referenceId = referenceId;
		}

		public String getName() {
			return name;
		}

		public void setName(final String name) {
			this.name = name;
		}

		public String getResourceType() {
			return resourceType;
		}

		public void setResourceType(final String resourceType) {
			this.resourceType = resourceType;
		}

		public String getRuleName() {
			return ruleName;
		}

		public void setRuleName(final String ruleName) {
			this.ruleName = ruleName;
		}

		public String getRuleDescription() {
			return ruleDescription;
		}

		public void setRuleDescription(final String ruleDescription) {
			this.ruleDescription = ruleDescription;
		}

		public String getRuleGroup() {
			return ruleGroup;
		}

		public void setRuleGroup(final String ruleGroup) {
			this.ruleGroup = ruleGroup;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(final String status) {
			this.status = status;
		}

		public String getPositive() {
			return positive;
		}

		public void setPositive(final String positive) {
			this.positive = positive;
		}
	}
}

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
package com.hack23.cia.service.data.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hack23.cia.model.internal.application.data.impl.ViewDecisionTemporalTrends;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenCoalitionAlignmentMatrix;
import com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation;
import com.hack23.cia.service.data.api.IntelligenceExportService;
import com.hack23.cia.service.data.api.RuleViolationDAO;
import com.hack23.cia.service.data.api.ViewDecisionTemporalTrendsDAO;
import com.hack23.cia.service.data.api.ViewRiksdagenCoalitionAlignmentMatrixDAO;
import com.hack23.cia.service.data.impl.export.CoalitionAlignmentExportDTO;
import com.hack23.cia.service.data.impl.export.CoalitionAlignmentExportDTO.PartyAlignment;
import com.hack23.cia.service.data.impl.export.ExportMetadata;
import com.hack23.cia.service.data.impl.export.RiskAssessmentExportDTO;
import com.hack23.cia.service.data.impl.export.RiskAssessmentExportDTO.RiskViolation;
import com.hack23.cia.service.data.impl.export.TemporalTrendsExportDTO;
import com.hack23.cia.service.data.impl.export.TemporalTrendsExportDTO.TrendDataPoint;

/**
 * Implementation of IntelligenceExportService.
 * 
 * @author intelligence-operative
 * @since v1.36
 */
@Service
@Transactional(readOnly = true)
public class IntelligenceExportServiceImpl implements IntelligenceExportService {

	@Autowired
	private RuleViolationDAO ruleViolationDAO;

	@Autowired
	private ViewRiksdagenCoalitionAlignmentMatrixDAO coalitionAlignmentDAO;

	@Autowired
	private ViewDecisionTemporalTrendsDAO temporalTrendsDAO;

	private final ObjectMapper objectMapper;

	public IntelligenceExportServiceImpl() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	@Override
	public String exportRiskAssessments() throws IOException {
		final List<RuleViolation> violations = ruleViolationDAO.getAll();

		final RiskAssessmentExportDTO dto = new RiskAssessmentExportDTO();

		final ExportMetadata metadata = new ExportMetadata();
		metadata.setSchema("intelligence-schema");
		metadata.setRecordCount(violations.size());
		metadata.setDataDate(new Date());
		dto.setMetadata(metadata);

		for (final com.hack23.cia.model.internal.application.data.rules.impl.RuleViolation violation : violations) {
			final RiskViolation riskViolation = new RiskViolation();
			riskViolation.setId(violation.getId());
			riskViolation.setDetectedDate(violation.getDetectedDate());
			riskViolation.setReferenceId(violation.getReferenceId());
			riskViolation.setName(violation.getName());
			riskViolation.setResourceType(violation.getResourceType() != null ? violation.getResourceType().toString() : null);
			riskViolation.setRuleName(violation.getRuleName());
			riskViolation.setRuleDescription(violation.getRuleDescription());
			riskViolation.setRuleGroup(violation.getRuleGroup());
			riskViolation.setStatus(violation.getStatus() != null ? violation.getStatus().toString() : null);
			riskViolation.setPositive(violation.getPositive());
			dto.getViolations().add(riskViolation);
		}

		return objectMapper.writeValueAsString(dto);
	}

	@Override
	public String exportCoalitionAlignment() throws IOException {
		final List<ViewRiksdagenCoalitionAlignmentMatrix> alignments = coalitionAlignmentDAO.getAll();

		final CoalitionAlignmentExportDTO dto = new CoalitionAlignmentExportDTO();

		final ExportMetadata metadata = new ExportMetadata();
		metadata.setSchema("intelligence-schema");
		metadata.setRecordCount(alignments.size());
		metadata.setDataDate(new Date());
		dto.setMetadata(metadata);

		for (final ViewRiksdagenCoalitionAlignmentMatrix alignment : alignments) {
			final PartyAlignment partyAlignment = new PartyAlignment();
			if (alignment.getEmbeddedId() != null) {
				partyAlignment.setParty1(alignment.getEmbeddedId().getParty1());
				partyAlignment.setParty2(alignment.getEmbeddedId().getParty2());
			}
			partyAlignment.setAlignmentRate(alignment.getAlignmentRate());
			partyAlignment.setSharedVotes(alignment.getSharedVotes());
			partyAlignment.setAlignedVotes(alignment.getAlignedVotes());
			dto.getAlignments().add(partyAlignment);
		}

		return objectMapper.writeValueAsString(dto);
	}

	@Override
	public String exportTemporalTrends() throws IOException {
		final List<ViewDecisionTemporalTrends> trends = temporalTrendsDAO.getAll();

		final TemporalTrendsExportDTO dto = new TemporalTrendsExportDTO();

		final ExportMetadata metadata = new ExportMetadata();
		metadata.setSchema("intelligence-schema");
		metadata.setRecordCount(trends.size());
		metadata.setDataDate(new Date());
		dto.setMetadata(metadata);

		for (final ViewDecisionTemporalTrends trend : trends) {
			final TrendDataPoint dataPoint = new TrendDataPoint();
			dataPoint.setDecisionDay(trend.getDecisionDay());
			dataPoint.setDailyDecisions(trend.getDailyDecisions());
			dataPoint.setDailyApprovalRate(trend.getDailyApprovalRate());
			dataPoint.setApprovedDecisions(trend.getApprovedDecisions());
			dataPoint.setRejectedDecisions(trend.getRejectedDecisions());
			dataPoint.setReferredBackDecisions(trend.getReferredBackDecisions());
			dataPoint.setCommitteeReferralDecisions(trend.getCommitteeReferralDecisions());
			dataPoint.setMa7dayDecisions(trend.getMa7dayDecisions());
			dataPoint.setMa30dayDecisions(trend.getMa30dayDecisions());
			dataPoint.setMa90dayDecisions(trend.getMa90dayDecisions());
			dataPoint.setMa30dayApprovalRate(trend.getMa30dayApprovalRate());
			dataPoint.setDecisionsLastYear(trend.getDecisionsLastYear());
			dataPoint.setYoyDecisionsChange(trend.getYoyDecisionsChange());
			dataPoint.setYoyDecisionsChangePct(trend.getYoyDecisionsChangePct());
			dataPoint.setDecisionYear(trend.getDecisionYear());
			dataPoint.setDecisionMonth(trend.getDecisionMonth());
			dataPoint.setDecisionWeek(trend.getDecisionWeek());
			dataPoint.setDayOfWeek(trend.getDayOfWeek());
			dto.getTrends().add(dataPoint);
		}

		return objectMapper.writeValueAsString(dto);
	}

	@Override
	public void writeJsonToFile(final String jsonContent, final String fileName, final String outputDirectory)
			throws IOException {
		final File directory = new File(outputDirectory);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		final String filePath = outputDirectory + File.separator + fileName;
		Files.write(Paths.get(filePath), jsonContent.getBytes(StandardCharsets.UTF_8));
	}
}

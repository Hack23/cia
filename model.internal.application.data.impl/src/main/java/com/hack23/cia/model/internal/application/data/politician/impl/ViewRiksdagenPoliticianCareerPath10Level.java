/*
 * Copyright 2010-2026 James Pether Sörling
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
package com.hack23.cia.model.internal.application.data.politician.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;

/**
 * The Class ViewRiksdagenPoliticianCareerPath10Level.
 * Database view for 10-level politician career path classification with predictive intelligence.
 * Integrates behavioral trends, risk assessment, and network influence for comprehensive career analysis.
 */
@Entity
@Immutable
@Table(name = "view_riksdagen_politician_career_path_10level")
public class ViewRiksdagenPoliticianCareerPath10Level implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ViewRiksdagenPoliticianCareerPath10LevelEmbeddedId embeddedId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "party")
	private String party;

	@Column(name = "status")
	private String status;

	@Column(name = "assignment_type")
	private String assignmentType;

	@Column(name = "org_code")
	private String orgCode;

	@Column(name = "to_date")
	@Temporal(TemporalType.DATE)
	private Date toDate;

	@Column(name = "is_current_role")
	private Boolean isCurrentRole;

	// 10-Level Career Classification
	@Column(name = "career_level")
	private Integer careerLevel;

	@Column(name = "career_level_name")
	private String careerLevelName;

	@Column(name = "career_score")
	private Integer careerScore;

	// Career Timeline
	@Column(name = "career_step")
	private Long careerStep;

	@Column(name = "total_career_steps")
	private Long totalCareerSteps;

	@Column(name = "career_start_date")
	@Temporal(TemporalType.DATE)
	private Date careerStartDate;

	@Column(name = "career_end_date")
	@Temporal(TemporalType.DATE)
	private Date careerEndDate;

	@Column(name = "career_start_year")
	private Integer careerStartYear;

	@Column(name = "career_end_year")
	private Integer careerEndYear;

	@Column(name = "career_span_years")
	private BigDecimal careerSpanYears;

	// Role Duration
	@Column(name = "years_in_role")
	private BigDecimal yearsInRole;

	@Column(name = "years_since_prev_role")
	private BigDecimal yearsSincePrevRole;

	@Column(name = "total_years_at_level")
	private BigDecimal totalYearsAtLevel;

	// Peak Analysis
	@Column(name = "peak_career_level")
	private Integer peakCareerLevel;

	@Column(name = "peak_career_score")
	private Integer peakCareerScore;

	@Column(name = "is_peak_role")
	private Boolean isPeakRole;

	@Column(name = "years_at_peak")
	private BigDecimal yearsAtPeak;

	// Progression Metrics
	@Column(name = "prev_career_level")
	private Integer prevCareerLevel;

	@Column(name = "next_career_level")
	private Integer nextCareerLevel;

	@Column(name = "progression_direction")
	private String progressionDirection;

	@Column(name = "level_change")
	private Integer levelChange;

	@Column(name = "score_change")
	private Integer scoreChange;

	@Column(name = "advancement_velocity")
	private BigDecimal advancementVelocity;

	@Column(name = "promotions_count")
	private Long promotionsCount;

	@Column(name = "demotions_count")
	private Long demotionsCount;

	// Career Performance
	@Column(name = "avg_career_score")
	private BigDecimal avgCareerScore;

	@Column(name = "career_score_volatility")
	private BigDecimal careerScoreVolatility;

	@Column(name = "avg_years_per_promotion")
	private BigDecimal avgYearsPerPromotion;

	// Career Pattern Classification
	@Column(name = "career_pattern")
	private String careerPattern;

	@Column(name = "downward_spiral_flag")
	private Boolean downwardSpiralFlag;

	@Column(name = "exit_risk_score")
	private Integer exitRiskScore;

	// Comparative Rankings
	@Column(name = "overall_career_rank")
	private Long overallCareerRank;

	@Column(name = "career_percentile")
	private BigDecimal careerPercentile;

	@Column(name = "career_decile")
	private Integer careerDecile;

	// Behavioral Intelligence
	@Column(name = "behavioral_win_rate")
	private BigDecimal behavioralWinRate;

	@Column(name = "behavioral_rebel_rate")
	private BigDecimal behavioralRebelRate;

	@Column(name = "behavioral_absence_rate")
	private BigDecimal behavioralAbsenceRate;

	@Column(name = "behavioral_assessment")
	private String behavioralAssessment;

	@Column(name = "attendance_status")
	private String attendanceStatus;

	@Column(name = "effectiveness_status")
	private String effectivenessStatus;

	@Column(name = "discipline_status")
	private String disciplineStatus;

	// Risk Intelligence
	@Column(name = "risk_level")
	private String riskLevel;

	@Column(name = "risk_score")
	private BigDecimal riskScore;

	@Column(name = "total_violations")
	private Long totalViolations;

	@Column(name = "absenteeism_violations")
	private Long absenteeismViolations;

	@Column(name = "effectiveness_violations")
	private Long effectivenessViolations;

	@Column(name = "discipline_violations")
	private Long disciplineViolations;

	// Network Influence Intelligence
	@Column(name = "influence_classification")
	private String influenceClassification;

	@Column(name = "centrality_score")
	private BigDecimal centralityScore;

	@Column(name = "broker_score")
	private BigDecimal brokerScore;

	@Column(name = "strong_connections")
	private Integer strongConnections;

	@Column(name = "cross_party_connections")
	private Integer crossPartyConnections;

	// Predictive Scores
	@Column(name = "predictive_success_score")
	private BigDecimal predictiveSuccessScore;

	@Column(name = "comprehensive_risk_level")
	private String comprehensiveRiskLevel;

	@Column(name = "leadership_potential_score")
	private Integer leadershipPotentialScore;

	@Column(name = "high_retention_risk_flag")
	private Boolean highRetentionRiskFlag;

	// Career Stage Classification
	@Column(name = "career_stage")
	private String careerStage;

	// Career Health Indicator
	@Column(name = "career_health_score")
	private BigDecimal careerHealthScore;

	// Career Path Flags
	@Column(name = "is_typical_career_path")
	private Boolean isTypicalCareerPath;

	@Column(name = "is_atypical_career_path")
	private Boolean isAtypicalCareerPath;

	/**
	 * Instantiates a new view riksdagen politician career path 10 level.
	 */
	public ViewRiksdagenPoliticianCareerPath10Level() {
		super();
	}

	/**
	 * Gets the embedded id.
	 *
	 * @return the embedded id
	 */
	public ViewRiksdagenPoliticianCareerPath10LevelEmbeddedId getEmbeddedId() {
		return embeddedId;
	}

	/**
	 * Sets the embedded id.
	 *
	 * @param embeddedId the new embedded id
	 */
	public void setEmbeddedId(final ViewRiksdagenPoliticianCareerPath10LevelEmbeddedId embeddedId) {
		this.embeddedId = embeddedId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the party.
	 *
	 * @return the party
	 */
	public String getParty() {
		return party;
	}

	/**
	 * Sets the party.
	 *
	 * @param party the new party
	 */
	public void setParty(final String party) {
		this.party = party;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * Gets the assignment type.
	 *
	 * @return the assignment type
	 */
	public String getAssignmentType() {
		return assignmentType;
	}

	/**
	 * Sets the assignment type.
	 *
	 * @param assignmentType the new assignment type
	 */
	public void setAssignmentType(final String assignmentType) {
		this.assignmentType = assignmentType;
	}

	/**
	 * Gets the org code.
	 *
	 * @return the org code
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * Sets the org code.
	 *
	 * @param orgCode the new org code
	 */
	public void setOrgCode(final String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * Gets the to date.
	 *
	 * @return the to date
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * Sets the to date.
	 *
	 * @param toDate the new to date
	 */
	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * Gets the is current role.
	 *
	 * @return the is current role
	 */
	public Boolean getIsCurrentRole() {
		return isCurrentRole;
	}

	/**
	 * Sets the is current role.
	 *
	 * @param isCurrentRole the new is current role
	 */
	public void setIsCurrentRole(final Boolean isCurrentRole) {
		this.isCurrentRole = isCurrentRole;
	}

	/**
	 * Gets the career level.
	 *
	 * @return the career level
	 */
	public Integer getCareerLevel() {
		return careerLevel;
	}

	/**
	 * Sets the career level.
	 *
	 * @param careerLevel the new career level
	 */
	public void setCareerLevel(final Integer careerLevel) {
		this.careerLevel = careerLevel;
	}

	/**
	 * Gets the career level name.
	 *
	 * @return the career level name
	 */
	public String getCareerLevelName() {
		return careerLevelName;
	}

	/**
	 * Sets the career level name.
	 *
	 * @param careerLevelName the new career level name
	 */
	public void setCareerLevelName(final String careerLevelName) {
		this.careerLevelName = careerLevelName;
	}

	/**
	 * Gets the career score.
	 *
	 * @return the career score
	 */
	public Integer getCareerScore() {
		return careerScore;
	}

	/**
	 * Sets the career score.
	 *
	 * @param careerScore the new career score
	 */
	public void setCareerScore(final Integer careerScore) {
		this.careerScore = careerScore;
	}

	/**
	 * Gets the career step.
	 *
	 * @return the career step
	 */
	public Long getCareerStep() {
		return careerStep;
	}

	/**
	 * Sets the career step.
	 *
	 * @param careerStep the new career step
	 */
	public void setCareerStep(final Long careerStep) {
		this.careerStep = careerStep;
	}

	/**
	 * Gets the total career steps.
	 *
	 * @return the total career steps
	 */
	public Long getTotalCareerSteps() {
		return totalCareerSteps;
	}

	/**
	 * Sets the total career steps.
	 *
	 * @param totalCareerSteps the new total career steps
	 */
	public void setTotalCareerSteps(final Long totalCareerSteps) {
		this.totalCareerSteps = totalCareerSteps;
	}

	/**
	 * Gets the career start date.
	 *
	 * @return the career start date
	 */
	public Date getCareerStartDate() {
		return careerStartDate;
	}

	/**
	 * Sets the career start date.
	 *
	 * @param careerStartDate the new career start date
	 */
	public void setCareerStartDate(final Date careerStartDate) {
		this.careerStartDate = careerStartDate;
	}

	/**
	 * Gets the career end date.
	 *
	 * @return the career end date
	 */
	public Date getCareerEndDate() {
		return careerEndDate;
	}

	/**
	 * Sets the career end date.
	 *
	 * @param careerEndDate the new career end date
	 */
	public void setCareerEndDate(final Date careerEndDate) {
		this.careerEndDate = careerEndDate;
	}

	/**
	 * Gets the career start year.
	 *
	 * @return the career start year
	 */
	public Integer getCareerStartYear() {
		return careerStartYear;
	}

	/**
	 * Sets the career start year.
	 *
	 * @param careerStartYear the new career start year
	 */
	public void setCareerStartYear(final Integer careerStartYear) {
		this.careerStartYear = careerStartYear;
	}

	/**
	 * Gets the career end year.
	 *
	 * @return the career end year
	 */
	public Integer getCareerEndYear() {
		return careerEndYear;
	}

	/**
	 * Sets the career end year.
	 *
	 * @param careerEndYear the new career end year
	 */
	public void setCareerEndYear(final Integer careerEndYear) {
		this.careerEndYear = careerEndYear;
	}

	/**
	 * Gets the career span years.
	 *
	 * @return the career span years
	 */
	public BigDecimal getCareerSpanYears() {
		return careerSpanYears;
	}

	/**
	 * Sets the career span years.
	 *
	 * @param careerSpanYears the new career span years
	 */
	public void setCareerSpanYears(final BigDecimal careerSpanYears) {
		this.careerSpanYears = careerSpanYears;
	}

	/**
	 * Gets the years in role.
	 *
	 * @return the years in role
	 */
	public BigDecimal getYearsInRole() {
		return yearsInRole;
	}

	/**
	 * Sets the years in role.
	 *
	 * @param yearsInRole the new years in role
	 */
	public void setYearsInRole(final BigDecimal yearsInRole) {
		this.yearsInRole = yearsInRole;
	}

	/**
	 * Gets the years since prev role.
	 *
	 * @return the years since prev role
	 */
	public BigDecimal getYearsSincePrevRole() {
		return yearsSincePrevRole;
	}

	/**
	 * Sets the years since prev role.
	 *
	 * @param yearsSincePrevRole the new years since prev role
	 */
	public void setYearsSincePrevRole(final BigDecimal yearsSincePrevRole) {
		this.yearsSincePrevRole = yearsSincePrevRole;
	}

	/**
	 * Gets the total years at level.
	 *
	 * @return the total years at level
	 */
	public BigDecimal getTotalYearsAtLevel() {
		return totalYearsAtLevel;
	}

	/**
	 * Sets the total years at level.
	 *
	 * @param totalYearsAtLevel the new total years at level
	 */
	public void setTotalYearsAtLevel(final BigDecimal totalYearsAtLevel) {
		this.totalYearsAtLevel = totalYearsAtLevel;
	}

	/**
	 * Gets the peak career level.
	 *
	 * @return the peak career level
	 */
	public Integer getPeakCareerLevel() {
		return peakCareerLevel;
	}

	/**
	 * Sets the peak career level.
	 *
	 * @param peakCareerLevel the new peak career level
	 */
	public void setPeakCareerLevel(final Integer peakCareerLevel) {
		this.peakCareerLevel = peakCareerLevel;
	}

	/**
	 * Gets the peak career score.
	 *
	 * @return the peak career score
	 */
	public Integer getPeakCareerScore() {
		return peakCareerScore;
	}

	/**
	 * Sets the peak career score.
	 *
	 * @param peakCareerScore the new peak career score
	 */
	public void setPeakCareerScore(final Integer peakCareerScore) {
		this.peakCareerScore = peakCareerScore;
	}

	/**
	 * Gets the is peak role.
	 *
	 * @return the is peak role
	 */
	public Boolean getIsPeakRole() {
		return isPeakRole;
	}

	/**
	 * Sets the is peak role.
	 *
	 * @param isPeakRole the new is peak role
	 */
	public void setIsPeakRole(final Boolean isPeakRole) {
		this.isPeakRole = isPeakRole;
	}

	/**
	 * Gets the years at peak.
	 *
	 * @return the years at peak
	 */
	public BigDecimal getYearsAtPeak() {
		return yearsAtPeak;
	}

	/**
	 * Sets the years at peak.
	 *
	 * @param yearsAtPeak the new years at peak
	 */
	public void setYearsAtPeak(final BigDecimal yearsAtPeak) {
		this.yearsAtPeak = yearsAtPeak;
	}

	/**
	 * Gets the prev career level.
	 *
	 * @return the prev career level
	 */
	public Integer getPrevCareerLevel() {
		return prevCareerLevel;
	}

	/**
	 * Sets the prev career level.
	 *
	 * @param prevCareerLevel the new prev career level
	 */
	public void setPrevCareerLevel(final Integer prevCareerLevel) {
		this.prevCareerLevel = prevCareerLevel;
	}

	/**
	 * Gets the next career level.
	 *
	 * @return the next career level
	 */
	public Integer getNextCareerLevel() {
		return nextCareerLevel;
	}

	/**
	 * Sets the next career level.
	 *
	 * @param nextCareerLevel the new next career level
	 */
	public void setNextCareerLevel(final Integer nextCareerLevel) {
		this.nextCareerLevel = nextCareerLevel;
	}

	/**
	 * Gets the progression direction.
	 *
	 * @return the progression direction
	 */
	public String getProgressionDirection() {
		return progressionDirection;
	}

	/**
	 * Sets the progression direction.
	 *
	 * @param progressionDirection the new progression direction
	 */
	public void setProgressionDirection(final String progressionDirection) {
		this.progressionDirection = progressionDirection;
	}

	/**
	 * Gets the level change.
	 *
	 * @return the level change
	 */
	public Integer getLevelChange() {
		return levelChange;
	}

	/**
	 * Sets the level change.
	 *
	 * @param levelChange the new level change
	 */
	public void setLevelChange(final Integer levelChange) {
		this.levelChange = levelChange;
	}

	/**
	 * Gets the score change.
	 *
	 * @return the score change
	 */
	public Integer getScoreChange() {
		return scoreChange;
	}

	/**
	 * Sets the score change.
	 *
	 * @param scoreChange the new score change
	 */
	public void setScoreChange(final Integer scoreChange) {
		this.scoreChange = scoreChange;
	}

	/**
	 * Gets the advancement velocity.
	 *
	 * @return the advancement velocity
	 */
	public BigDecimal getAdvancementVelocity() {
		return advancementVelocity;
	}

	/**
	 * Sets the advancement velocity.
	 *
	 * @param advancementVelocity the new advancement velocity
	 */
	public void setAdvancementVelocity(final BigDecimal advancementVelocity) {
		this.advancementVelocity = advancementVelocity;
	}

	/**
	 * Gets the promotions count.
	 *
	 * @return the promotions count
	 */
	public Long getPromotionsCount() {
		return promotionsCount;
	}

	/**
	 * Sets the promotions count.
	 *
	 * @param promotionsCount the new promotions count
	 */
	public void setPromotionsCount(final Long promotionsCount) {
		this.promotionsCount = promotionsCount;
	}

	/**
	 * Gets the demotions count.
	 *
	 * @return the demotions count
	 */
	public Long getDemotionsCount() {
		return demotionsCount;
	}

	/**
	 * Sets the demotions count.
	 *
	 * @param demotionsCount the new demotions count
	 */
	public void setDemotionsCount(final Long demotionsCount) {
		this.demotionsCount = demotionsCount;
	}

	/**
	 * Gets the avg career score.
	 *
	 * @return the avg career score
	 */
	public BigDecimal getAvgCareerScore() {
		return avgCareerScore;
	}

	/**
	 * Sets the avg career score.
	 *
	 * @param avgCareerScore the new avg career score
	 */
	public void setAvgCareerScore(final BigDecimal avgCareerScore) {
		this.avgCareerScore = avgCareerScore;
	}

	/**
	 * Gets the career score volatility.
	 *
	 * @return the career score volatility
	 */
	public BigDecimal getCareerScoreVolatility() {
		return careerScoreVolatility;
	}

	/**
	 * Sets the career score volatility.
	 *
	 * @param careerScoreVolatility the new career score volatility
	 */
	public void setCareerScoreVolatility(final BigDecimal careerScoreVolatility) {
		this.careerScoreVolatility = careerScoreVolatility;
	}

	/**
	 * Gets the avg years per promotion.
	 *
	 * @return the avg years per promotion
	 */
	public BigDecimal getAvgYearsPerPromotion() {
		return avgYearsPerPromotion;
	}

	/**
	 * Sets the avg years per promotion.
	 *
	 * @param avgYearsPerPromotion the new avg years per promotion
	 */
	public void setAvgYearsPerPromotion(final BigDecimal avgYearsPerPromotion) {
		this.avgYearsPerPromotion = avgYearsPerPromotion;
	}

	/**
	 * Gets the career pattern.
	 *
	 * @return the career pattern
	 */
	public String getCareerPattern() {
		return careerPattern;
	}

	/**
	 * Sets the career pattern.
	 *
	 * @param careerPattern the new career pattern
	 */
	public void setCareerPattern(final String careerPattern) {
		this.careerPattern = careerPattern;
	}

	/**
	 * Gets the downward spiral flag.
	 *
	 * @return the downward spiral flag
	 */
	public Boolean getDownwardSpiralFlag() {
		return downwardSpiralFlag;
	}

	/**
	 * Sets the downward spiral flag.
	 *
	 * @param downwardSpiralFlag the new downward spiral flag
	 */
	public void setDownwardSpiralFlag(final Boolean downwardSpiralFlag) {
		this.downwardSpiralFlag = downwardSpiralFlag;
	}

	/**
	 * Gets the exit risk score.
	 *
	 * @return the exit risk score
	 */
	public Integer getExitRiskScore() {
		return exitRiskScore;
	}

	/**
	 * Sets the exit risk score.
	 *
	 * @param exitRiskScore the new exit risk score
	 */
	public void setExitRiskScore(final Integer exitRiskScore) {
		this.exitRiskScore = exitRiskScore;
	}

	/**
	 * Gets the overall career rank.
	 *
	 * @return the overall career rank
	 */
	public Long getOverallCareerRank() {
		return overallCareerRank;
	}

	/**
	 * Sets the overall career rank.
	 *
	 * @param overallCareerRank the new overall career rank
	 */
	public void setOverallCareerRank(final Long overallCareerRank) {
		this.overallCareerRank = overallCareerRank;
	}

	/**
	 * Gets the career percentile.
	 *
	 * @return the career percentile
	 */
	public BigDecimal getCareerPercentile() {
		return careerPercentile;
	}

	/**
	 * Sets the career percentile.
	 *
	 * @param careerPercentile the new career percentile
	 */
	public void setCareerPercentile(final BigDecimal careerPercentile) {
		this.careerPercentile = careerPercentile;
	}

	/**
	 * Gets the career decile.
	 *
	 * @return the career decile
	 */
	public Integer getCareerDecile() {
		return careerDecile;
	}

	/**
	 * Sets the career decile.
	 *
	 * @param careerDecile the new career decile
	 */
	public void setCareerDecile(final Integer careerDecile) {
		this.careerDecile = careerDecile;
	}

	/**
	 * Gets the behavioral win rate.
	 *
	 * @return the behavioral win rate
	 */
	public BigDecimal getBehavioralWinRate() {
		return behavioralWinRate;
	}

	/**
	 * Sets the behavioral win rate.
	 *
	 * @param behavioralWinRate the new behavioral win rate
	 */
	public void setBehavioralWinRate(final BigDecimal behavioralWinRate) {
		this.behavioralWinRate = behavioralWinRate;
	}

	/**
	 * Gets the behavioral rebel rate.
	 *
	 * @return the behavioral rebel rate
	 */
	public BigDecimal getBehavioralRebelRate() {
		return behavioralRebelRate;
	}

	/**
	 * Sets the behavioral rebel rate.
	 *
	 * @param behavioralRebelRate the new behavioral rebel rate
	 */
	public void setBehavioralRebelRate(final BigDecimal behavioralRebelRate) {
		this.behavioralRebelRate = behavioralRebelRate;
	}

	/**
	 * Gets the behavioral absence rate.
	 *
	 * @return the behavioral absence rate
	 */
	public BigDecimal getBehavioralAbsenceRate() {
		return behavioralAbsenceRate;
	}

	/**
	 * Sets the behavioral absence rate.
	 *
	 * @param behavioralAbsenceRate the new behavioral absence rate
	 */
	public void setBehavioralAbsenceRate(final BigDecimal behavioralAbsenceRate) {
		this.behavioralAbsenceRate = behavioralAbsenceRate;
	}

	/**
	 * Gets the behavioral assessment.
	 *
	 * @return the behavioral assessment
	 */
	public String getBehavioralAssessment() {
		return behavioralAssessment;
	}

	/**
	 * Sets the behavioral assessment.
	 *
	 * @param behavioralAssessment the new behavioral assessment
	 */
	public void setBehavioralAssessment(final String behavioralAssessment) {
		this.behavioralAssessment = behavioralAssessment;
	}

	/**
	 * Gets the attendance status.
	 *
	 * @return the attendance status
	 */
	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	/**
	 * Sets the attendance status.
	 *
	 * @param attendanceStatus the new attendance status
	 */
	public void setAttendanceStatus(final String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	/**
	 * Gets the effectiveness status.
	 *
	 * @return the effectiveness status
	 */
	public String getEffectivenessStatus() {
		return effectivenessStatus;
	}

	/**
	 * Sets the effectiveness status.
	 *
	 * @param effectivenessStatus the new effectiveness status
	 */
	public void setEffectivenessStatus(final String effectivenessStatus) {
		this.effectivenessStatus = effectivenessStatus;
	}

	/**
	 * Gets the discipline status.
	 *
	 * @return the discipline status
	 */
	public String getDisciplineStatus() {
		return disciplineStatus;
	}

	/**
	 * Sets the discipline status.
	 *
	 * @param disciplineStatus the new discipline status
	 */
	public void setDisciplineStatus(final String disciplineStatus) {
		this.disciplineStatus = disciplineStatus;
	}

	/**
	 * Gets the risk level.
	 *
	 * @return the risk level
	 */
	public String getRiskLevel() {
		return riskLevel;
	}

	/**
	 * Sets the risk level.
	 *
	 * @param riskLevel the new risk level
	 */
	public void setRiskLevel(final String riskLevel) {
		this.riskLevel = riskLevel;
	}

	/**
	 * Gets the risk score.
	 *
	 * @return the risk score
	 */
	public BigDecimal getRiskScore() {
		return riskScore;
	}

	/**
	 * Sets the risk score.
	 *
	 * @param riskScore the new risk score
	 */
	public void setRiskScore(final BigDecimal riskScore) {
		this.riskScore = riskScore;
	}

	/**
	 * Gets the total violations.
	 *
	 * @return the total violations
	 */
	public Long getTotalViolations() {
		return totalViolations;
	}

	/**
	 * Sets the total violations.
	 *
	 * @param totalViolations the new total violations
	 */
	public void setTotalViolations(final Long totalViolations) {
		this.totalViolations = totalViolations;
	}

	/**
	 * Gets the absenteeism violations.
	 *
	 * @return the absenteeism violations
	 */
	public Long getAbsenteeismViolations() {
		return absenteeismViolations;
	}

	/**
	 * Sets the absenteeism violations.
	 *
	 * @param absenteeismViolations the new absenteeism violations
	 */
	public void setAbsenteeismViolations(final Long absenteeismViolations) {
		this.absenteeismViolations = absenteeismViolations;
	}

	/**
	 * Gets the effectiveness violations.
	 *
	 * @return the effectiveness violations
	 */
	public Long getEffectivenessViolations() {
		return effectivenessViolations;
	}

	/**
	 * Sets the effectiveness violations.
	 *
	 * @param effectivenessViolations the new effectiveness violations
	 */
	public void setEffectivenessViolations(final Long effectivenessViolations) {
		this.effectivenessViolations = effectivenessViolations;
	}

	/**
	 * Gets the discipline violations.
	 *
	 * @return the discipline violations
	 */
	public Long getDisciplineViolations() {
		return disciplineViolations;
	}

	/**
	 * Sets the discipline violations.
	 *
	 * @param disciplineViolations the new discipline violations
	 */
	public void setDisciplineViolations(final Long disciplineViolations) {
		this.disciplineViolations = disciplineViolations;
	}

	/**
	 * Gets the influence classification.
	 *
	 * @return the influence classification
	 */
	public String getInfluenceClassification() {
		return influenceClassification;
	}

	/**
	 * Sets the influence classification.
	 *
	 * @param influenceClassification the new influence classification
	 */
	public void setInfluenceClassification(final String influenceClassification) {
		this.influenceClassification = influenceClassification;
	}

	/**
	 * Gets the centrality score.
	 *
	 * @return the centrality score
	 */
	public BigDecimal getCentralityScore() {
		return centralityScore;
	}

	/**
	 * Sets the centrality score.
	 *
	 * @param centralityScore the new centrality score
	 */
	public void setCentralityScore(final BigDecimal centralityScore) {
		this.centralityScore = centralityScore;
	}

	/**
	 * Gets the broker score.
	 *
	 * @return the broker score
	 */
	public BigDecimal getBrokerScore() {
		return brokerScore;
	}

	/**
	 * Sets the broker score.
	 *
	 * @param brokerScore the new broker score
	 */
	public void setBrokerScore(final BigDecimal brokerScore) {
		this.brokerScore = brokerScore;
	}

	/**
	 * Gets the strong connections.
	 *
	 * @return the strong connections
	 */
	public Integer getStrongConnections() {
		return strongConnections;
	}

	/**
	 * Sets the strong connections.
	 *
	 * @param strongConnections the new strong connections
	 */
	public void setStrongConnections(final Integer strongConnections) {
		this.strongConnections = strongConnections;
	}

	/**
	 * Gets the cross party connections.
	 *
	 * @return the cross party connections
	 */
	public Integer getCrossPartyConnections() {
		return crossPartyConnections;
	}

	/**
	 * Sets the cross party connections.
	 *
	 * @param crossPartyConnections the new cross party connections
	 */
	public void setCrossPartyConnections(final Integer crossPartyConnections) {
		this.crossPartyConnections = crossPartyConnections;
	}

	/**
	 * Gets the predictive success score.
	 *
	 * @return the predictive success score
	 */
	public BigDecimal getPredictiveSuccessScore() {
		return predictiveSuccessScore;
	}

	/**
	 * Sets the predictive success score.
	 *
	 * @param predictiveSuccessScore the new predictive success score
	 */
	public void setPredictiveSuccessScore(final BigDecimal predictiveSuccessScore) {
		this.predictiveSuccessScore = predictiveSuccessScore;
	}

	/**
	 * Gets the comprehensive risk level.
	 *
	 * @return the comprehensive risk level
	 */
	public String getComprehensiveRiskLevel() {
		return comprehensiveRiskLevel;
	}

	/**
	 * Sets the comprehensive risk level.
	 *
	 * @param comprehensiveRiskLevel the new comprehensive risk level
	 */
	public void setComprehensiveRiskLevel(final String comprehensiveRiskLevel) {
		this.comprehensiveRiskLevel = comprehensiveRiskLevel;
	}

	/**
	 * Gets the leadership potential score.
	 *
	 * @return the leadership potential score
	 */
	public Integer getLeadershipPotentialScore() {
		return leadershipPotentialScore;
	}

	/**
	 * Sets the leadership potential score.
	 *
	 * @param leadershipPotentialScore the new leadership potential score
	 */
	public void setLeadershipPotentialScore(final Integer leadershipPotentialScore) {
		this.leadershipPotentialScore = leadershipPotentialScore;
	}

	/**
	 * Gets the high retention risk flag.
	 *
	 * @return the high retention risk flag
	 */
	public Boolean getHighRetentionRiskFlag() {
		return highRetentionRiskFlag;
	}

	/**
	 * Sets the high retention risk flag.
	 *
	 * @param highRetentionRiskFlag the new high retention risk flag
	 */
	public void setHighRetentionRiskFlag(final Boolean highRetentionRiskFlag) {
		this.highRetentionRiskFlag = highRetentionRiskFlag;
	}

	/**
	 * Gets the career stage.
	 *
	 * @return the career stage
	 */
	public String getCareerStage() {
		return careerStage;
	}

	/**
	 * Sets the career stage.
	 *
	 * @param careerStage the new career stage
	 */
	public void setCareerStage(final String careerStage) {
		this.careerStage = careerStage;
	}

	/**
	 * Gets the career health score.
	 *
	 * @return the career health score
	 */
	public BigDecimal getCareerHealthScore() {
		return careerHealthScore;
	}

	/**
	 * Sets the career health score.
	 *
	 * @param careerHealthScore the new career health score
	 */
	public void setCareerHealthScore(final BigDecimal careerHealthScore) {
		this.careerHealthScore = careerHealthScore;
	}

	/**
	 * Gets the is typical career path.
	 *
	 * @return the is typical career path
	 */
	public Boolean getIsTypicalCareerPath() {
		return isTypicalCareerPath;
	}

	/**
	 * Sets the is typical career path.
	 *
	 * @param isTypicalCareerPath the new is typical career path
	 */
	public void setIsTypicalCareerPath(final Boolean isTypicalCareerPath) {
		this.isTypicalCareerPath = isTypicalCareerPath;
	}

	/**
	 * Gets the is atypical career path.
	 *
	 * @return the is atypical career path
	 */
	public Boolean getIsAtypicalCareerPath() {
		return isAtypicalCareerPath;
	}

	/**
	 * Sets the is atypical career path.
	 *
	 * @param isAtypicalCareerPath the new is atypical career path
	 */
	public void setIsAtypicalCareerPath(final Boolean isAtypicalCareerPath) {
		this.isAtypicalCareerPath = isAtypicalCareerPath;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ViewRiksdagenPoliticianCareerPath10Level that = (ViewRiksdagenPoliticianCareerPath10Level) o;
		return Objects.equals(embeddedId, that.embeddedId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(embeddedId);
	}

	@Override
	public String toString() {
		return "ViewRiksdagenPoliticianCareerPath10Level{" +
				"embeddedId=" + embeddedId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", party='" + party + '\'' +
				", careerLevel=" + careerLevel +
				", careerPattern='" + careerPattern + '\'' +
				", careerHealthScore=" + careerHealthScore +
				", predictiveSuccessScore=" + predictiveSuccessScore +
				", leadershipPotentialScore=" + leadershipPotentialScore +
				'}';
	}
}

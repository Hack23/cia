package com.hack23.cia.model.internal.application.data.politician.impl;

import java.util.List;
import java.util.Locale;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hack23.cia.model.common.api.ModelObject;


/**
 * The Class ViewRiksdagenPoliticianExperienceSummary.
 */
@Entity
@Table(name = "view_riksdagen_politician_experience_summary")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewRiksdagenPoliticianExperienceSummary")
@XmlRootElement(name = "ViewRiksdagenPoliticianExperienceSummary")
public class ViewRiksdagenPoliticianExperienceSummary implements ModelObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The person id. */
    @Id
    @Column(name = "person_id")
    private String personId;

    /** The first name. */
    @Basic
    @Column(name = "first_name")
    private String firstName;

    /** The last name. */
    @Basic
    @Column(name = "last_name")
    private String lastName;

    /** The total days. */
    @Basic
    @Column(name = "total_days")
    private Long totalDays;

    /** The total weighted exp. */
    @Basic
    @Column(name = "total_weighted_exp")
    private Long totalWeightedExp;

    /** The govt days. */
    @Basic
    @Column(name = "govt_days")
    private Long govtDays;

    /** The riksdag days. */
    @Basic
    @Column(name = "riksdag_days")
    private Long riksdagDays;

    /** The party days. */
    @Basic
    @Column(name = "party_days")
    private Long partyDays;

    /** The committee days. */
    @Basic
    @Column(name = "committee_days")
    private Long committeeDays;

    /** The total substitute days. */
    @Basic
    @Column(name = "total_substitute_days")
    private Long totalSubstituteDays;

    /** The total leadership days. */
    @Basic
    @Column(name = "total_leadership_days")
    private Long totalLeadershipDays;

    /** The knowledge areas json. */
    @Basic
    @Column(name = "knowledge_areas_json", columnDefinition = "text")
    private String knowledgeAreasJson;

    /** The roles json. */
    @Basic
    @Column(name = "roles_json", columnDefinition = "text")
    private String rolesJson;

    /** The experience level. */
    @Enumerated(EnumType.STRING)
    @Column(name = "experience_level")
    private ExperienceLevel experienceLevel;

    /** The experience breadth. */
    @Enumerated(EnumType.STRING)
    @Column(name = "experience_breadth")
    private ExperienceBreadth experienceBreadth;

    /** The leadership profile. */
    @Enumerated(EnumType.STRING)
    @Column(name = "leadership_profile")
    private LeadershipProfile leadershipProfile;

    /** The role stability. */
    @Enumerated(EnumType.STRING)
    @Column(name = "role_stability")
    private RoleStability roleStability;

    /** The career phase. */
    @Enumerated(EnumType.STRING)
    @Column(name = "career_phase")
    private CareerPhase careerPhase;

    /** The specialization level. */
    @Enumerated(EnumType.STRING)
    @Column(name = "specialization_level")
    private SpecializationLevel specializationLevel;

    /** The political analysis comment. */
    @Basic
    @Column(name = "political_analysis_comment", length = 4000)
    private String politicalAnalysisComment;

    /** The knowledge areas. */
    @Transient
    private List<KnowledgeArea> knowledgeAreas;

    /** The roles. */
    @Transient
    private List<PoliticalRole> roles;

    /**
     * Experience level enum.
     */
    public enum ExperienceLevel {
        /** The extensive experience. */
        EXTENSIVE_EXPERIENCE,
        /** The significant government. */
        SIGNIFICANT_GOVERNMENT,
        /** The long serving parliament. */
        LONG_SERVING_PARLIAMENT,
        /** The active committees. */
        ACTIVE_COMMITTEES,
        /** The party leadership. */
        PARTY_LEADERSHIP,
        /** The mixed experience. */
        MIXED_EXPERIENCE
    }

    /**
     * Experience breadth enum.
     */
    public enum ExperienceBreadth {
        /** The high. */
        HIGH,
        /** The medium. */
        MEDIUM,
        /** The low. */
        LOW
    }

    /**
     * Leadership profile enum.
     */
    public enum LeadershipProfile {
        /** The significant leadership. */
        SIGNIFICANT_LEADERSHIP,
        /** The moderate leadership. */
        MODERATE_LEADERSHIP,
        /** The some leadership. */
        SOME_LEADERSHIP,
        /** The no leadership. */
        NO_LEADERSHIP
    }

    /**
     * Role stability enum.
     */
    public enum RoleStability {
        /** The primarily substitute. */
        PRIMARILY_SUBSTITUTE,
        /** The frequent substitute. */
        FREQUENT_SUBSTITUTE,
        /** The occasional substitute. */
        OCCASIONAL_SUBSTITUTE,
        /** The regular roles. */
        REGULAR_ROLES
    }

    /**
     * Career phase enum.
     */
    public enum CareerPhase {
        /** The senior statesperson. */
        SENIOR_STATESPERSON,
        /** The established politician. */
        ESTABLISHED_POLITICIAN,
        /** The experienced politician. */
        EXPERIENCED_POLITICIAN,
        /** The mid career. */
        MID_CAREER,
        /** The early career. */
        EARLY_CAREER
    }

    /**
     * Specialization level enum.
     */
    public enum SpecializationLevel {
        /** The highly specialized. */
        HIGHLY_SPECIALIZED,
        /** The moderately specialized. */
        MODERATELY_SPECIALIZED,
        /** The broadly experienced. */
        BROADLY_EXPERIENCED
    }

    /**
     * The Class KnowledgeArea.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KnowledgeArea {
        /** The area. */
        private String area;
        /** The days. */
        private Long days;
        /** The weighted exp. */
        private Long weightedExp;

        /**
         * Gets the area.
         *
         * @return the area
         */
        public String getArea() {
            return area;
        }

        /**
         * Sets the area.
         *
         * @param area the new area
         */
        public void setArea(String area) {
            this.area = area;
        }

        /**
         * Gets the days.
         *
         * @return the days
         */
        public Long getDays() {
            return days;
        }

        /**
         * Sets the days.
         *
         * @param days the new days
         */
        public void setDays(Long days) {
            this.days = days;
        }

        /**
         * Gets the weighted exp.
         *
         * @return the weighted exp
         */
        public Long getWeightedExp() {
            return weightedExp;
        }

        /**
         * Sets the weighted exp.
         *
         * @param weightedExp the new weighted exp
         */
        public void setWeightedExp(Long weightedExp) {
            this.weightedExp = weightedExp;
        }
    }

    /**
     * The Class PoliticalRole.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PoliticalRole {
        /** The type. */
        private String type;
        /** The role. */
        private String role;
        /** The org. */
        private String org;
        /** The days. */
        private Long days;
        /** The weighted exp. */
        private Long weightedExp;
        /** The substitute days. */
        private Long substituteDays;
        /** The leadership days. */
        private Long leadershipDays;

        /**
         * Gets the type.
         *
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the type.
         *
         * @param type the new type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Gets the role.
         *
         * @return the role
         */
        public String getRole() {
            return role;
        }

        /**
         * Sets the role.
         *
         * @param role the new role
         */
        public void setRole(String role) {
            this.role = role;
        }

        /**
         * Gets the org.
         *
         * @return the org
         */
        public String getOrg() {
            return org;
        }

        /**
         * Sets the org.
         *
         * @param org the new org
         */
        public void setOrg(String org) {
            this.org = org;
        }

        /**
         * Gets the days.
         *
         * @return the days
         */
        public Long getDays() {
            return days;
        }

        /**
         * Sets the days.
         *
         * @param days the new days
         */
        public void setDays(Long days) {
            this.days = days;
        }

        /**
         * Gets the weighted exp.
         *
         * @return the weighted exp
         */
        public Long getWeightedExp() {
            return weightedExp;
        }

        /**
         * Sets the weighted exp.
         *
         * @param weightedExp the new weighted exp
         */
        public void setWeightedExp(Long weightedExp) {
            this.weightedExp = weightedExp;
        }

        /**
         * Gets the substitute days.
         *
         * @return the substitute days
         */
        public Long getSubstituteDays() {
            return substituteDays;
        }

        /**
         * Sets the substitute days.
         *
         * @param substituteDays the new substitute days
         */
        public void setSubstituteDays(Long substituteDays) {
            this.substituteDays = substituteDays;
        }

        /**
         * Gets the leadership days.
         *
         * @return the leadership days
         */
        public Long getLeadershipDays() {
            return leadershipDays;
        }

        /**
         * Sets the leadership days.
         *
         * @param leadershipDays the new leadership days
         */
        public void setLeadershipDays(Long leadershipDays) {
            this.leadershipDays = leadershipDays;
        }
    }

    /**
     * Load json data.
     */
    @PostLoad
    private void loadJsonData() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            if (knowledgeAreasJson != null) {
                knowledgeAreas = mapper.readValue(knowledgeAreasJson,
                    new TypeReference<List<KnowledgeArea>>() {});
            }
            if (rolesJson != null) {
                roles = mapper.readValue(rolesJson,
                    new TypeReference<List<PoliticalRole>>() {});
            }
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON data", e);
        }
    }

    /**
     * Gets the formatted experience summary.
     *
     * @return the formatted experience summary
     */
    public String getFormattedExperienceSummary() {
        return String.format(Locale.ENGLISH,"%s %s: %s - %s, %s, %s",
            firstName,
            lastName,
            experienceLevel,
            careerPhase,
            leadershipProfile,
            specializationLevel);
    }

    // Standard getters and setters for all fields


    /**
     * Gets the person id.
     *
     * @return the person id
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * Sets the person id.
     *
     * @param personId the new person id
     */
    public void setPersonId(String personId) {
        this.personId = personId;
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
	public void setFirstName(String firstName) {
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
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the total days.
	 *
	 * @return the total days
	 */
	public Long getTotalDays() {
		return totalDays;
	}

	/**
	 * Sets the total days.
	 *
	 * @param totalDays the new total days
	 */
	public void setTotalDays(Long totalDays) {
		this.totalDays = totalDays;
	}

	/**
	 * Gets the total weighted exp.
	 *
	 * @return the total weighted exp
	 */
	public Long getTotalWeightedExp() {
		return totalWeightedExp;
	}

	/**
	 * Sets the total weighted exp.
	 *
	 * @param totalWeightedExp the new total weighted exp
	 */
	public void setTotalWeightedExp(Long totalWeightedExp) {
		this.totalWeightedExp = totalWeightedExp;
	}

	/**
	 * Gets the govt days.
	 *
	 * @return the govt days
	 */
	public Long getGovtDays() {
		return govtDays;
	}

	/**
	 * Sets the govt days.
	 *
	 * @param govtDays the new govt days
	 */
	public void setGovtDays(Long govtDays) {
		this.govtDays = govtDays;
	}

	/**
	 * Gets the riksdag days.
	 *
	 * @return the riksdag days
	 */
	public Long getRiksdagDays() {
		return riksdagDays;
	}

	/**
	 * Sets the riksdag days.
	 *
	 * @param riksdagDays the new riksdag days
	 */
	public void setRiksdagDays(Long riksdagDays) {
		this.riksdagDays = riksdagDays;
	}

	/**
	 * Gets the party days.
	 *
	 * @return the party days
	 */
	public Long getPartyDays() {
		return partyDays;
	}

	/**
	 * Sets the party days.
	 *
	 * @param partyDays the new party days
	 */
	public void setPartyDays(Long partyDays) {
		this.partyDays = partyDays;
	}

	/**
	 * Gets the committee days.
	 *
	 * @return the committee days
	 */
	public Long getCommitteeDays() {
		return committeeDays;
	}

	/**
	 * Sets the committee days.
	 *
	 * @param committeeDays the new committee days
	 */
	public void setCommitteeDays(Long committeeDays) {
		this.committeeDays = committeeDays;
	}

	/**
	 * Gets the total substitute days.
	 *
	 * @return the total substitute days
	 */
	public Long getTotalSubstituteDays() {
		return totalSubstituteDays;
	}

	/**
	 * Sets the total substitute days.
	 *
	 * @param totalSubstituteDays the new total substitute days
	 */
	public void setTotalSubstituteDays(Long totalSubstituteDays) {
		this.totalSubstituteDays = totalSubstituteDays;
	}

	/**
	 * Gets the total leadership days.
	 *
	 * @return the total leadership days
	 */
	public Long getTotalLeadershipDays() {
		return totalLeadershipDays;
	}

	/**
	 * Sets the total leadership days.
	 *
	 * @param totalLeadershipDays the new total leadership days
	 */
	public void setTotalLeadershipDays(Long totalLeadershipDays) {
		this.totalLeadershipDays = totalLeadershipDays;
	}

	/**
	 * Gets the knowledge areas json.
	 *
	 * @return the knowledge areas json
	 */
	public String getKnowledgeAreasJson() {
		return knowledgeAreasJson;
	}

	/**
	 * Sets the knowledge areas json.
	 *
	 * @param knowledgeAreasJson the new knowledge areas json
	 */
	public void setKnowledgeAreasJson(String knowledgeAreasJson) {
		this.knowledgeAreasJson = knowledgeAreasJson;
	}

	/**
	 * Gets the roles json.
	 *
	 * @return the roles json
	 */
	public String getRolesJson() {
		return rolesJson;
	}

	/**
	 * Sets the roles json.
	 *
	 * @param rolesJson the new roles json
	 */
	public void setRolesJson(String rolesJson) {
		this.rolesJson = rolesJson;
	}

	/**
	 * Gets the experience level.
	 *
	 * @return the experience level
	 */
	public ExperienceLevel getExperienceLevel() {
		return experienceLevel;
	}

	/**
	 * Sets the experience level.
	 *
	 * @param experienceLevel the new experience level
	 */
	public void setExperienceLevel(ExperienceLevel experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	/**
	 * Gets the experience breadth.
	 *
	 * @return the experience breadth
	 */
	public ExperienceBreadth getExperienceBreadth() {
		return experienceBreadth;
	}

	/**
	 * Sets the experience breadth.
	 *
	 * @param experienceBreadth the new experience breadth
	 */
	public void setExperienceBreadth(ExperienceBreadth experienceBreadth) {
		this.experienceBreadth = experienceBreadth;
	}

	/**
	 * Gets the leadership profile.
	 *
	 * @return the leadership profile
	 */
	public LeadershipProfile getLeadershipProfile() {
		return leadershipProfile;
	}

	/**
	 * Sets the leadership profile.
	 *
	 * @param leadershipProfile the new leadership profile
	 */
	public void setLeadershipProfile(LeadershipProfile leadershipProfile) {
		this.leadershipProfile = leadershipProfile;
	}

	/**
	 * Gets the role stability.
	 *
	 * @return the role stability
	 */
	public RoleStability getRoleStability() {
		return roleStability;
	}

	/**
	 * Sets the role stability.
	 *
	 * @param roleStability the new role stability
	 */
	public void setRoleStability(RoleStability roleStability) {
		this.roleStability = roleStability;
	}

	/**
	 * Gets the career phase.
	 *
	 * @return the career phase
	 */
	public CareerPhase getCareerPhase() {
		return careerPhase;
	}

	/**
	 * Sets the career phase.
	 *
	 * @param careerPhase the new career phase
	 */
	public void setCareerPhase(CareerPhase careerPhase) {
		this.careerPhase = careerPhase;
	}

	/**
	 * Gets the specialization level.
	 *
	 * @return the specialization level
	 */
	public SpecializationLevel getSpecializationLevel() {
		return specializationLevel;
	}

	/**
	 * Sets the specialization level.
	 *
	 * @param specializationLevel the new specialization level
	 */
	public void setSpecializationLevel(SpecializationLevel specializationLevel) {
		this.specializationLevel = specializationLevel;
	}

	/**
	 * Gets the political analysis comment.
	 *
	 * @return the political analysis comment
	 */
	public String getPoliticalAnalysisComment() {
		return politicalAnalysisComment;
	}

	/**
	 * Sets the political analysis comment.
	 *
	 * @param politicalAnalysisComment the new political analysis comment
	 */
	public void setPoliticalAnalysisComment(String politicalAnalysisComment) {
		this.politicalAnalysisComment = politicalAnalysisComment;
	}

	/**
	 * Gets the knowledge areas.
	 *
	 * @return the knowledge areas
	 */
	public List<KnowledgeArea> getKnowledgeAreas() {
		return knowledgeAreas;
	}

	/**
	 * Sets the knowledge areas.
	 *
	 * @param knowledgeAreas the new knowledge areas
	 */
	public void setKnowledgeAreas(List<KnowledgeArea> knowledgeAreas) {
		this.knowledgeAreas = knowledgeAreas;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<PoliticalRole> getRoles() {
		return roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(List<PoliticalRole> roles) {
		this.roles = roles;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
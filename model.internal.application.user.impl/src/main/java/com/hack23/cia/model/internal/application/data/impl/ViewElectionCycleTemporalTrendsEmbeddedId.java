package com.hack23.cia.model.internal.application.data.impl;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ViewElectionCycleTemporalTrendsEmbeddedId implements Serializable {
private static final long serialVersionUID = 1L;

@Column(name = "election_cycle_id")
private String electionCycleId;

@Column(name = "cycle_year")
private Integer cycleYear;

@Column(name = "semester")
private String semester;

public ViewElectionCycleTemporalTrendsEmbeddedId() {
}

public String getElectionCycleId() {
 electionCycleId;
}

public void setElectionCycleId(String electionCycleId) {
CycleId = electionCycleId;
}

public Integer getCycleYear() {
 cycleYear;
}

public void setCycleYear(Integer cycleYear) {
cleYear = cycleYear;
}

public String getSemester() {
 semester;
}

public void setSemester(String semester) {
= semester;
}

@Override
public boolean equals(Object o) {
(this == o) return true;
(o == null || getClass() != o.getClass()) return false;
CycleTemporalTrendsEmbeddedId that = (ViewElectionCycleTemporalTrendsEmbeddedId) o;
 Objects.equals(electionCycleId, that.electionCycleId) &&
cleYear, that.cycleYear) &&
that.semester);
}

@Override
public int hashCode() {
 Objects.hash(electionCycleId, cycleYear, semester);
}
}

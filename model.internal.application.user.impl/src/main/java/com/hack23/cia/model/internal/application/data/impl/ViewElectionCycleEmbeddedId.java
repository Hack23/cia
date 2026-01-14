package com.hack23.cia.model.internal.application.data.impl;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ViewElectionCycleEmbeddedId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "election_cycle_id")
	private String electionCycleId;
	
	@Column(name = "semester")
	private String semester;
	
	public ViewElectionCycleEmbeddedId() {
	}
	
	public String getElectionCycleId() {
		return electionCycleId;
	}
	
	public void setElectionCycleId(String electionCycleId) {
		this.electionCycleId = electionCycleId;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ViewElectionCycleEmbeddedId that = (ViewElectionCycleEmbeddedId) o;
		return Objects.equals(electionCycleId, that.electionCycleId) &&
				Objects.equals(semester, that.semester);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(electionCycleId, semester);
	}
}

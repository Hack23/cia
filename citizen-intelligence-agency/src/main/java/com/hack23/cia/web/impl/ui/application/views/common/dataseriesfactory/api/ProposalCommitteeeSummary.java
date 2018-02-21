package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api;

import java.util.Locale;

public class ProposalCommitteeeSummary {

	private final String org;
	private final String docType;
	private final String decision;
	private final String hangarId;
	
	public ProposalCommitteeeSummary(String org, String docType, String decision, String hangarId) {
		super();
		this.org = org.trim().toUpperCase(Locale.ENGLISH);
		this.docType = docType.toUpperCase(Locale.ENGLISH);
		this.decision = decision.replace("=", "").replaceAll(",", "").trim().toUpperCase(Locale.ENGLISH);
		this.hangarId = hangarId;
	}

	public String getOrg() {
		return org;
	}

	public String getDocType() {
		return docType;
	}

	public String getDecision() {
		return decision;
	}

	public String getHangarId() {
		return hangarId;
	}

	@Override
	public String toString() {
		return String.format("ProposalCommitteeeSummary [org=%s, docType=%s, decision=%s, hangarId=%s]", org,
				docType, decision, hangarId);
	}				
}
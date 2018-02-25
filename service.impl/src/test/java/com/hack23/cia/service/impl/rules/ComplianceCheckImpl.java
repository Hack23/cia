package com.hack23.cia.service.impl.rules;

import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.service.api.action.kpi.ResourceIdentifier;
import com.hack23.cia.service.api.action.kpi.ResourceType;
import com.hack23.cia.service.api.action.kpi.Status;

public final class ComplianceCheckImpl implements ComplianceCheck {

	private ResourceType resourceType;
	
	private Status status;
	
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String getRuleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRuleDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceIdentifier getResourceIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}



}

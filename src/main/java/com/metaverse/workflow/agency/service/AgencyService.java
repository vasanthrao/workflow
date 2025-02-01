package com.metaverse.workflow.agency.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.Agency;

public interface AgencyService {

	public String saveAgency(Agency agency);
	
	public Agency getAgencyById(Long agencyId);

	public WorkflowResponse getAgencies();
}

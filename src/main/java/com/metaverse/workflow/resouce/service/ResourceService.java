package com.metaverse.workflow.resouce.service;

import com.metaverse.workflow.common.response.WorkflowResponse;

public interface ResourceService {
	
	public ResourceResponse saveResource(ResourceRequest resourceRequest);
	public WorkflowResponse getResources();
}

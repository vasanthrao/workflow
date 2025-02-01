package com.metaverse.workflow.location.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
	
	public LocationResponse saveLocation(LocationRequest location);
	
	public List<LocationResponse> getLocationByAgencyId(Long agencyId);

	public WorkflowResponse getLocations();

}

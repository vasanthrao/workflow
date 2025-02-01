package com.metaverse.workflow.agency.controller;

import com.metaverse.workflow.agency.service.AgencyResponse;
import com.metaverse.workflow.agency.service.AgencyResponseMapper;
import com.metaverse.workflow.agency.service.AgencyService;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.location.service.LocationResponse;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.resouce.service.ResourceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgencyController {

	@Autowired
	private AgencyService service;

	@GetMapping("/agency/get/{id}")
	public ResponseEntity<WorkflowResponse> getAgencyById(@PathVariable("id") Long id)
	{
		Agency agency = service.getAgencyById(id);
		AgencyResponse response = AgencyResponseMapper.map(agency);
		return ResponseEntity.ok(WorkflowResponse.builder().message("Success").status(200).data(response).build());
	}

	@GetMapping("/agency/locations/{id}")
	public ResponseEntity<WorkflowResponse> getLocationsByAgencyId(@PathVariable("id") Long id)
	{
		Agency agency = service.getAgencyById(id);
		List<LocationResponse> response = AgencyResponseMapper.mapLocations(agency.getLocations());
		return ResponseEntity.ok(WorkflowResponse.builder().message("Success").status(200).data(response).build());
	}

	@GetMapping("/agency/resources/{id}")
	public ResponseEntity<WorkflowResponse> getResourcesByAgencyId(@PathVariable("id") Long id)
	{
		Agency agency = service.getAgencyById(id);
		List<ResourceResponse> response = AgencyResponseMapper.mapResources(agency.getResources());
		return ResponseEntity.ok(WorkflowResponse.builder().message("Success").status(200).data(response).build());
	}

	@GetMapping("/agencies")
	public ResponseEntity<WorkflowResponse> getAgencies()
	{
		WorkflowResponse response = service.getAgencies();
		return ResponseEntity.ok(response);
	}

}

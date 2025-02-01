package com.metaverse.workflow.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.location.service.LocationRequest;
import com.metaverse.workflow.location.service.LocationResponse;
import com.metaverse.workflow.location.service.LocationService;

@RestController
public class LocationController {
	@Autowired
	private LocationService locationSercice;
	
	@PostMapping("/location/save")
	public ResponseEntity<WorkflowResponse> saveLocation(@RequestBody LocationRequest location)
	{
	LocationResponse response = locationSercice.saveLocation(location);
		if(response==null)return  ResponseEntity.internalServerError().body(WorkflowResponse.builder().message("Invalid Agency Id").status(400).build());
		return ResponseEntity.ok(WorkflowResponse.builder().status(200).message("Created").data(response).build());
	}
	
	@GetMapping("/location/{id}")
	public ResponseEntity<WorkflowResponse> getLocationByAgencyId(@PathVariable("id")Long id)
	{
		List<LocationResponse> locationresponces = locationSercice.getLocationByAgencyId(id);
		return ResponseEntity.ok(WorkflowResponse.builder().message("Success").status(200).data(locationresponces).build());
	}

	@GetMapping("/locations")
	public ResponseEntity<WorkflowResponse> getLocations()
	{
		WorkflowResponse response = locationSercice.getLocations();
		return ResponseEntity.ok(response);
	}
	
}

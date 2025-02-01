package com.metaverse.workflow.resouce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.resouce.service.ResourceRequest;
import com.metaverse.workflow.resouce.service.ResourceResponse;
import com.metaverse.workflow.resouce.service.ResourceService;

@RestController
public class ResouceController {
	
	@Autowired
	private ResourceService service;
	@PostMapping("/resource/save")
	public ResponseEntity<WorkflowResponse> saveResource(@RequestBody ResourceRequest resourceRequest)
	{
		ResourceResponse saveResource = service.saveResource(resourceRequest);
		return ResponseEntity.ok(WorkflowResponse.builder().status(200).message("Created").data(saveResource).build());
	}

	@GetMapping("/resources")
	public ResponseEntity<WorkflowResponse> getResourses()
	{
		WorkflowResponse response = service.getResources();
		return ResponseEntity.ok(response);
	}

}

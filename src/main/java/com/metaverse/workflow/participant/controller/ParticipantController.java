package com.metaverse.workflow.participant.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.participant.service.ParticipantRequest;
import com.metaverse.workflow.participant.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParticipantController {
	
	@Autowired
	private ParticipantService participantService;
	
	@PostMapping("/participant/save")
	public ResponseEntity<WorkflowResponse> saveParticipant(@RequestBody ParticipantRequest participantRequest)
	{
		WorkflowResponse response = participantService.saveParticipant(participantRequest);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/participants")
	public ResponseEntity<WorkflowResponse> getParticipants()
	{
		WorkflowResponse response = participantService.getParticipants();
		return ResponseEntity.ok(response);
	}

}

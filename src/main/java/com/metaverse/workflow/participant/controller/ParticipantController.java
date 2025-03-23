package com.metaverse.workflow.participant.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.participant.service.ParticipantRequest;
import com.metaverse.workflow.participant.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	@GetMapping("getParticipantsByMobileNo/{mobileNo}")
	public ResponseEntity<WorkflowResponse> getParticipantsByMobileNo(@PathVariable Long mobileNo)
	{
		WorkflowResponse response=	participantService.getParticipantsByMobileNo(mobileNo);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/getOrganizationByParticipant/{participantId}")
	public ResponseEntity<WorkflowResponse> getOrganizationByParticipant(@PathVariable Long participantId)
	{
		WorkflowResponse response = participantService.getOrganizationByParticipant(participantId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getParticipantByTypeOfProgram/{typeOfProgram}")
	public ResponseEntity<WorkflowResponse> getParticipantByTypeOfProgram(@PathVariable String typeOfProgram)
	{
		WorkflowResponse response = participantService.getParticipantByTypeOfProgram(typeOfProgram);
		return ResponseEntity.ok(response);
	}

}

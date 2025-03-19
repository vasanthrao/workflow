package com.metaverse.workflow.ESDPTrainingProgramApplication.controller;


import com.metaverse.workflow.ESDPTrainingProgramApplication.service.ESDPTrainingProgramRequest;
import com.metaverse.workflow.ESDPTrainingProgramApplication.service.ESDPTrainingProgramService;
import com.metaverse.workflow.common.response.WorkflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ESDPTrainingProgramController {
    @Autowired
    ESDPTrainingProgramService esdpTrainingProgramService;

    @PostMapping("/SaveESDPTrainingApplication")
    public ResponseEntity<WorkflowResponse> SaveESDPTrainingProgram(@RequestBody ESDPTrainingProgramRequest esdpTrainingProgramRequest)
    {
        WorkflowResponse response=  esdpTrainingProgramService.saveESDPTrainingProgram(esdpTrainingProgramRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getESDPTrainingApplication")
    public ResponseEntity<WorkflowResponse> getESDPTrainingProgramData()
    {
       WorkflowResponse response= esdpTrainingProgramService.getESDPTrainingProgramData();
       return ResponseEntity.ok(response);
    }

}

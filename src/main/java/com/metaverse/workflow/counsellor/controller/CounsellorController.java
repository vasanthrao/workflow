package com.metaverse.workflow.counsellor.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.counsellor.service.CounsellorRegistrationRequest;
import com.metaverse.workflow.counsellor.service.CounsellorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class CounsellorController {

    @Autowired
    CounsellorService counsellorService;


    @PostMapping("/saveCounsellor")
    public ResponseEntity<WorkflowResponse> saveCounsellor(@RequestBody CounsellorRegistrationRequest counsellorRequest) {
        log.info("Save counsellor");
        WorkflowResponse response = counsellorService.saveCounseller(counsellorRequest);
        return ResponseEntity.ok(response);
    }

    /*@GetMapping("/getCounsellorByMandal")
     public WorkflowResponse getCounsellorByMandal(@RequestParam String mandal)
     {
         CounsellorRegistrationResponse counsellorResponse=   counsellorService.getCounsellerByMandal(mandal);
         return WorkflowResponse.builder().message("Counsellor details By mandal").status(200).data(counsellorResponse).build();
     }*/


}

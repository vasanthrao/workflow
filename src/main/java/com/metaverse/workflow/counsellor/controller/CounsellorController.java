package com.metaverse.workflow.counsellor.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.counsellor.service.CounsellorRegistrationResponse;
import com.metaverse.workflow.counsellor.service.CounsellorService;
import com.metaverse.workflow.counsellor.service.CounsllorRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public WorkflowResponse saveCounsellor(@RequestBody CounsllorRegistrationRequest counsellorRequest) {
        log.info("Save counsellor");
        CounsellorRegistrationResponse counsellorResponse = counsellorService.saveCounseller(counsellorRequest);
        return WorkflowResponse.builder().message("Counsellor details saved").status(200).data(counsellorResponse).build();
    }

    /*@GetMapping("/getCounsellorByMandal")
     public WorkflowResponse getCounsellorByMandal(@RequestParam String mandal)
     {
         CounsellorRegistrationResponse counsellorResponse=   counsellorService.getCounsellerByMandal(mandal);
         return WorkflowResponse.builder().message("Counsellor details By mandal").status(200).data(counsellorResponse).build();
     }*/


}

package com.metaverse.workflow.expenditure.controller;

import com.metaverse.workflow.ESDPTraining.service.ESDPTrainingRequest;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.exceptions.ActivityDetailsException;
import com.metaverse.workflow.exceptions.ProgramDetailsException;
import com.metaverse.workflow.exceptions.SubActivityDetailsException;
import com.metaverse.workflow.expenditure.service.ExpenditureRequest;
import com.metaverse.workflow.expenditure.service.ExpenditureService;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpenditureController {
    @Autowired
    ExpenditureService expenditureService;

    @PostMapping("/expenditure/save")
    public ResponseEntity<?> saveExpenditure(@RequestBody ExpenditureRequest request) {
        try {
            return ResponseEntity.ok(expenditureService.saveExpenditure(request));
        }
        catch(ActivityDetailsException | SubActivityDetailsException | ProgramDetailsException exception )
        {
            return RestControllerBase.error(exception);
        }
    }
}



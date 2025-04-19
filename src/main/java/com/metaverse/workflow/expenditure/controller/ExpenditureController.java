package com.metaverse.workflow.expenditure.controller;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.exceptions.AgencyDetailsException;
import com.metaverse.workflow.expenditure.service.BulkExpenditureRequest;
import com.metaverse.workflow.expenditure.service.ExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpenditureController {
    @Autowired
    ExpenditureService expenditureService;

    @PostMapping("/bulk/expenditure/save")
    public ResponseEntity<?> saveBulkExpenditure(@RequestBody BulkExpenditureRequest request) {
        try {
            return ResponseEntity.ok(expenditureService.saveBulkExpenditure(request));
        }
        catch(AgencyDetailsException exception)
        {
            return RestControllerBase.error(exception);
        }
    }


}



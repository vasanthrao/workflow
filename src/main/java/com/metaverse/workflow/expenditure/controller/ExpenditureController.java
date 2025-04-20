package com.metaverse.workflow.expenditure.controller;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.exceptions.*;
import com.metaverse.workflow.expenditure.service.BulkExpenditureRequest;
import com.metaverse.workflow.expenditure.service.ExpenditureService;
import com.metaverse.workflow.expenditure.service.ProgramExpenditureRequest;
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
        catch(AgencyDetailsException | HeadOfExpenseException exception)
        {
            return RestControllerBase.error(exception);
        }
    }
        @PostMapping("/program/expenditure/save")
    public ResponseEntity<?> saveProgramExpenditure(@RequestBody ProgramExpenditureRequest request) {
        try {
            return ResponseEntity.ok(expenditureService.saveProgramExpenditure(request));
        }
        catch (AgencyDetailsException | SubActivityDetailsException |
               ProgramDetailsException | ActivityDetailsException |
               HeadOfExpenseException exception) {
            return RestControllerBase.error(exception);
        }
    }

    @GetMapping("/program/expenditure/{expenditureType}")
    public ResponseEntity<?> getAllProgramExpenditure(@PathVariable ExpenditureType expenditureType) {
            return ResponseEntity.ok(expenditureService.getAllProgramExpenditure(expenditureType));
    }

    @GetMapping("/bulk/expenditure")
    public ResponseEntity<?> getAllProgramExpenditure() {
        return ResponseEntity.ok(expenditureService.getAllBulkExpenditure());
    }


}



package com.metaverse.workflow.expenditure.controller;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.exceptions.*;
import com.metaverse.workflow.expenditure.service.*;
import com.metaverse.workflow.model.HeadOfExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenditureController {
    @Autowired
    ExpenditureService expenditureService;

    @PostMapping("/bulk/expenditure/save")
    public ResponseEntity<?> saveBulkExpenditure(@RequestBody BulkExpenditureRequest request) {
        try {
            return ResponseEntity.ok(expenditureService.saveBulkExpenditure(request));
        }
        catch(DataException exception)
        {
            return RestControllerBase.error(exception);
        } 
    }
    @PostMapping("/program/expenditure/save")
    public ResponseEntity<?> saveProgramExpenditure(@RequestBody ProgramExpenditureRequest request) {
        try {
            return ResponseEntity.ok(expenditureService.saveProgramExpenditure(request));
        }
        catch (DataException exception) {
            return RestControllerBase.error(exception);
        }
    }

    @GetMapping("/program/expenditure/{expenditureType}")
    public ResponseEntity<?> getAllProgramExpenditure(@PathVariable ExpenditureType expenditureType) {
            return ResponseEntity.ok(expenditureService.getAllProgramExpenditure(expenditureType));
    }

    @GetMapping("/bulk/expenditure")
    public ResponseEntity<WorkflowResponse> getAllBulkExpenditure( ) {
        return ResponseEntity.ok(expenditureService.getAllBulkExpenditure());
    }

    @GetMapping("/program/expenditure")
    public ResponseEntity<?> getAllProgramExpenditure(
            @RequestParam ExpenditureType expenditureType,
            @RequestParam Long programId) {
        return ResponseEntity.ok(expenditureService.getAllProgramExpenditureByProgram(expenditureType, programId));
    }

    @PostMapping("/bulk/transactions/save")
    public ResponseEntity<?> saveTransaction(
            @RequestBody BulkExpenditureTransactionRequest request) throws DataException {
        try {
            BulkExpenditureTransactionResponse response = expenditureService.saveTransaction(request);
            return ResponseEntity.ok(response);
        }
        catch (DataException ex) {
            return RestControllerBase.error(ex);
        }
    }

    @PostMapping("/bulk/transactions/lookup")
    public ResponseEntity<?> getExpendituresByExpenseAndItem(
            @RequestBody BulkExpenditureLookupRequest request) throws DataException {
        try {
            BulkExpenditureLookupResponse result = expenditureService.getBulkExpendituresByExpenseAndItem(request);
            return ResponseEntity.ok(result);
        }
        catch (DataException ex) {
            return RestControllerBase.error(ex);
        }
    }

    @GetMapping("/bulk/transactions/items")
    public ResponseEntity<List<String>> getItemsByExpense(@RequestParam Integer expenseId) throws DataException {
        List<String> items = expenditureService.getItemsByHeadOfExpense(expenseId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/bulk/transactions")
    public ResponseEntity<WorkflowResponse> getAllBulkExpenditureTransactionByProgram(
            @RequestParam Long programId) {
        return ResponseEntity.ok(expenditureService.getAllBulkExpenditureTransactionByProgram(programId));
    }
    @GetMapping("/expenses")
    public List<HeadOfExpense> getAllExpenses()
    {
        return expenditureService.getAllHeadOfExpenses();
    }
}


